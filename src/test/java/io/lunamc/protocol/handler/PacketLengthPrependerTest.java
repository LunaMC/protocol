/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler;

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class PacketLengthPrependerTest {

    @Test
    public void testPacketLengthPrepender() throws Throwable {
        ByteBuf channelBuffer = Unpooled.buffer();

        byte[] msgBytes = new byte[128];
        new Random().nextBytes(msgBytes);
        ByteBuf msg = Unpooled.wrappedBuffer(msgBytes);

        ChannelHandlerContext writerContext = HandlerTestUtilities.mockCtx(channelBuffer, null);
        PacketLengthPrepender prepender = new PacketLengthPrepender();
        prepender.write(writerContext, msg, HandlerTestUtilities.mockChannelPromise());

        int length = ProtocolUtils.readVarInt(channelBuffer);
        Assert.assertEquals(msgBytes.length, length);
        Assert.assertEquals(msgBytes.length, channelBuffer.readableBytes());
    }
}
