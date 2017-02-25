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
