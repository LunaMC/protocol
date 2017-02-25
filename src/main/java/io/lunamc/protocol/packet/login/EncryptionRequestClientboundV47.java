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
        packetId = 0x01,
        minProtocolVersion = 47,
        protocolBound = ProtocolBound.CLIENTBOUND,
        protocolState = ProtocolState.LOGIN
)
public interface EncryptionRequestClientboundV47 extends Packet {

    String getServerId();

    void setServerId(String serverId);

    byte[] getPublicKey();

    void setPublicKey(byte[] publicKey);

    byte[] getVerifyToken();

    void setVerifyToken(byte[] verifyToken);
}
