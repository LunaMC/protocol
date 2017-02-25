/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.packet.AbstractPacketTest;
import io.lunamc.protocol.packet.Packet;

public class BaseHandshakeServerboundV47Test extends AbstractPacketTest {

    @Override
    protected Packet createPacket(boolean content) {
        BaseHandshakeServerboundV47 packet = new BaseHandshakeServerboundV47();
        if (content) {
            packet.setProtocolVersion(47);
            packet.setServerAddress("localhost");
            packet.setServerPort(25565);
            packet.setNextState(NextProtocolState.STATUS);
        }
        return packet;
    }
}
