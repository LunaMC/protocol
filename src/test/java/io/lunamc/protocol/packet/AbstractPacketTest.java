/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
