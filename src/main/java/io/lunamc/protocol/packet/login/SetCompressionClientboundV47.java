/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.packet.PacketMeta;
import io.lunamc.protocol.packet.ProtocolBound;
import io.lunamc.protocol.packet.ProtocolState;

@PacketMeta(
        packetId = 0x03,
        minProtocolVersion = 47,
        protocolBound = ProtocolBound.CLIENTBOUND,
        protocolState = ProtocolState.LOGIN
)
public interface SetCompressionClientboundV47 extends Packet {

    int getThreshold();

    void setThreshold(int threshold);
}
