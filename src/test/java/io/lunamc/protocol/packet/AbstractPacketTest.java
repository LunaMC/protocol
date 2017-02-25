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

package io.lunamc.protocol.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractPacketTest {

    @Test
    public void testWriteRead() {
        ByteBuf buf = Unpooled.buffer();
        Packet packet = createPacket(true);
        packet.write(buf);

        Packet other = createPacket(false);
        other.read(buf);

        Assert.assertEquals(packet, other);
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(createPacket(true), createPacket(true));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(createPacket(true).hashCode(), createPacket(true).hashCode());
    }

    protected abstract Packet createPacket(boolean content);
}
