/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.CorruptedFrameException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LengthLimitedFrameDecoderTest {

    private static final Method READ_VAR_INT;

    static {
        try {
            READ_VAR_INT = LengthLimitedFrameDecoder.class.getDeclaredMethod("readVarInt", ByteBuf.class);
            READ_VAR_INT.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReadVarInt() throws Throwable {
        Assert.assertEquals(1, readVarInt(new byte[] { 0x01 }));
        Assert.assertEquals(2, readVarInt(new byte[] { 0x02 }));
        Assert.assertEquals(127, readVarInt(new byte[] { 0x7f }));
        Assert.assertEquals(128, readVarInt(new byte[] { (byte) 0x80, 0x01 }));
        Assert.assertEquals(255, readVarInt(new byte[] { (byte) 0xff, 0x01 }));
        Assert.assertEquals(LengthLimitedFrameDecoder.MAX_LENGTH, readVarInt(new byte[] { (byte) 0xff, (byte) 0xff, 0x01 }));
    }

    @Test(expected = CorruptedFrameException.class)
    public void testReadTooLongVarInt() throws Throwable {
        readVarInt(new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x01 });
    }

    private static int readVarInt(byte[] data) throws Throwable {
        ByteBuf buffer = Unpooled.copiedBuffer(data);
        try {
            return (int) READ_VAR_INT.invoke(null, buffer);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
