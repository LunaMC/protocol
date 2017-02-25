/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet;

import io.lunamc.protocol.packet.handshake.HandshakePacketAllocator;
import io.lunamc.protocol.packet.login.LoginPacketAllocator;
import io.lunamc.protocol.packet.play.PlayPacketAllocator;
import io.lunamc.protocol.packet.status.StatusPacketAllocator;
import io.netty.util.AttributeKey;

public interface PacketAllocator {

    AttributeKey<PacketAllocator> ATTR = AttributeKey.newInstance("io.lunamc.packetAllocator");

    HandshakePacketAllocator getHandshakePacketAllocator();

    LoginPacketAllocator getLoginPacketAllocator();

    PlayPacketAllocator getPlayPacketAllocator();

    StatusPacketAllocator getStatusPacketAllocator();
}
