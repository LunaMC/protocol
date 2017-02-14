/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler.compression;

import io.lunamc.protocol.handler.HandlerTestUtilities;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;

public class CompressionTest {

    @Test
    public void testCompression1Byte() throws Throwable {
        // Is the buffer allocated rightly?
        testMockedCompression(0, 1);
    }

    @Test
    public void testCompression16Byte() throws Throwable {
        testMockedCompression(0, 16);
    }

    @Test
    public void testCompression64Byte() throws Throwable {
        // This should be already compressed
        testMockedCompression(0, 64);
    }

    @Test
    public void testCompression128Byte() throws Throwable {
        // > 127 bytes needs more than one byte for representing the length as a VarInt
        testMockedCompression(0, 256);
    }

    @Test
    public void testCompression4MegaByte() throws Throwable {
        // Test multiple flushes for the compressor
        testMockedCompression(0, 4 * 1_024 * 1_024);
    }

    @Test
    public void testCompressionBelowThreshold() throws Throwable {
        testMockedCompression(16, 8);
    }

    private static void testMockedCompression(int threshold, int bytes) throws Exception {
        ByteBuf channelBuffer = Unpooled.buffer();
        channelBuffer.retain();

        byte[] msgBytes = new byte[bytes];
        new Random().nextBytes(msgBytes);
        ByteBuf msg = Unpooled.wrappedBuffer(msgBytes);

        ChannelHandlerContext writerContext = HandlerTestUtilities.mockCtx(channelBuffer, null);
        PacketCompressor compressor = new PacketCompressor(threshold, Deflater.DEFAULT_COMPRESSION);
        compressor.write(writerContext, msg, HandlerTestUtilities.mockChannelPromise());

        PacketDecompressor decompressor = new PacketDecompressor();
        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
        ChannelHandlerContext readerContext = HandlerTestUtilities.mockCtx(channelBuffer, completableFuture::complete);
        decompressor.channelRead(readerContext, channelBuffer);

        Object result = completableFuture.get(1, TimeUnit.SECONDS);
        Assert.assertTrue(result instanceof ByteBuf);
        ByteBuf read = (ByteBuf) result;
        Assert.assertEquals(msgBytes.length, read.readableBytes());
        byte[] readBytes = new byte[read.readableBytes()];
        read.readBytes(readBytes);
        Assert.assertArrayEquals(msgBytes, readBytes);
    }
}
