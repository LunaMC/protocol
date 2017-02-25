/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

public class BaseHandshakeRequestV47Test {

    @Test
    public void testWriteRead() {
        ByteBuf buf = Unpooled.buffer();
        BaseHandshakeRequestV47 packet = createHandshakeRequest();
        packet.write(buf);

        BaseHandshakeRequestV47 other = new BaseHandshakeRequestV47();
        other.read(buf);

        Assert.assertEquals(packet, other);
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(createHandshakeRequest(), createHandshakeRequest());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(createHandshakeRequest().hashCode(), createHandshakeRequest().hashCode());
    }

    private static BaseHandshakeRequestV47 createHandshakeRequest() {
        BaseHandshakeRequestV47 packet = new BaseHandshakeRequestV47();
        packet.setProtocolVersion(47);
        packet.setServerAddress("localhost");
        packet.setServerPort(25565);
        packet.setNextState(NextProtocolState.STATUS);
        return packet;
    }
}
