/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.packet.ProtocolBound;
import io.lunamc.protocol.packet.PacketMeta;
import io.lunamc.protocol.packet.ProtocolState;

@PacketMeta(
        packetId = 0x00,
        minProtocolVersion = 47,
        protocolBound = ProtocolBound.SERVERBOUND,
        protocolState = ProtocolState.HANDSHAKE
)
public interface HandshakeServerboundV47 extends Packet {

    int getProtocolVersion();

    void setProtocolVersion(int protocolVersion);

    String getServerAddress();

    void setServerAddress(String serverAddress);

    int getServerPort();

    void setServerPort(int serverPort);

    NextProtocolState getNextState();

    void setNextState(NextProtocolState nextState);
}
