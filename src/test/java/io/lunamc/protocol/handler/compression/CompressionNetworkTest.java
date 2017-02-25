/*
 *  Copyright 2017 LunaMC.io
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.lunamc.protocol.handler.compression;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.Deflater;

public class CompressionNetworkTest {

    private static final int PORT = Integer.getInteger("NETWORK_TEST_PORT", 65_321);
    private static final Random RANDOM = new Random();

    private static Receiver serverReceiver;
    private static EventLoopGroup bossGroup;
    private static EventLoopGroup workerGroup;
    private static Channel serverChannel;
    private static Channel clientChannel;

    @BeforeClass
    public static void prepare() {
        serverReceiver = new Receiver();
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup(2);
        serverChannel = new ServerBootstrap()
                .channel(NioServerSocketChannel.class)
                .group(bossGroup, workerGroup)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new PacketDecompressor(),
                                serverReceiver
                        );
                    }
                })
                .bind("localhost", PORT)
                .syncUninterruptibly()
                .channel();
        clientChannel = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(workerGroup)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new PacketCompressor(0, Deflater.DEFAULT_COMPRESSION)
                        );
                    }
                })
                .connect("localhost", PORT)
                .syncUninterruptibly()
                .channel();
    }

    @Test
    public void testServerDecompression8Bytes() throws Throwable {
        testServerDecompression(8);
    }

    @Test
    public void testServerDecompression32Bytes() throws Throwable {
        testServerDecompression(32);
    }

    @Test
    public void testServerDecompression128Bytes() throws Throwable {
        testServerDecompression(128);
    }

    @AfterClass
    public static void shutdown() {
        clientChannel.close();
        clientChannel.closeFuture().syncUninterruptibly();

        serverChannel.close();
        serverChannel.closeFuture().syncUninterruptibly();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        bossGroup.terminationFuture().syncUninterruptibly();
        workerGroup.terminationFuture().syncUninterruptibly();
    }

    private static void testServerDecompression(int size) throws InterruptedException, ExecutionException, TimeoutException {
        byte[] data = new byte[size];
        RANDOM.nextBytes(data);

        serverReceiver.reset();
        clientChannel.writeAndFlush(Unpooled.wrappedBuffer(data)).syncUninterruptibly();
        byte[] received = serverReceiver.getLastMessage().get(1, TimeUnit.SECONDS);
        Assert.assertArrayEquals(data, received);
    }

    private static class Receiver extends SimpleChannelInboundHandler<ByteBuf> {

        private CompletableFuture<byte[]> lastMessage;

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            byte[] lastMessage = new byte[msg.readableBytes()];
            msg.readBytes(lastMessage);
            this.lastMessage.complete(lastMessage);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            if (lastMessage != null)
                lastMessage.completeExceptionally(cause);
        }

        public void reset() {
            lastMessage = new CompletableFuture<>();
        }

        public Future<byte[]> getLastMessage() {
            return lastMessage;
        }
    }
}
