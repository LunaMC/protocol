/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler.cipher;

import io.lunamc.protocol.handler.HandlerTestUtilities;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CipherTest {

    @Test
    public void testMockedCipher() throws Throwable {
        SecretKey secretKey = generateKey();
        ByteBuf channelBuffer = Unpooled.buffer();
        channelBuffer.retain();

        byte[] msgBytes = new byte[128];
        new Random().nextBytes(msgBytes);
        ByteBuf msg = Unpooled.wrappedBuffer(msgBytes);

        ChannelHandlerContext writerContext = HandlerTestUtilities.mockCtx(channelBuffer, null);
        CipherEncoder encoder = new CipherEncoder(secretKey);
        encoder.write(writerContext, msg, HandlerTestUtilities.mockChannelPromise());

        CipherDecoder decoder = new CipherDecoder(secretKey);
        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
        ChannelHandlerContext readerContext = HandlerTestUtilities.mockCtx(channelBuffer, completableFuture::complete);
        decoder.channelRead(readerContext, channelBuffer);

        Object result = completableFuture.get(1, TimeUnit.SECONDS);
        Assert.assertTrue(result instanceof ByteBuf);
        ByteBuf read = (ByteBuf) result;
        Assert.assertEquals(msgBytes.length, read.readableBytes());
        byte[] readBytes = new byte[read.readableBytes()];
        read.readBytes(readBytes);
        Assert.assertArrayEquals(msgBytes, readBytes);
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }
}
