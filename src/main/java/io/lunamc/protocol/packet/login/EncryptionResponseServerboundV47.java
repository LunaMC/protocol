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
        protocolBound = ProtocolBound.SERVERBOUND,
        protocolState = ProtocolState.LOGIN
)
public interface EncryptionResponseServerboundV47 extends Packet {

    byte[] getSharedSecret();

    void setSharedSecret(byte[] sharedSecret);

    byte[] getVerifyToken();

    void setVerifyToken(byte[] verifyToken);
}
