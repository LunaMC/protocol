/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet;

import io.lunamc.protocol.packet.handshake.PooledHandshakePacketAllocator;
import io.lunamc.protocol.packet.login.PooledLoginPacketAllocator;
import io.lunamc.protocol.packet.play.PooledPlayPacketAllocator;
import io.lunamc.protocol.packet.status.PooledStatusPacketAllocator;

public class PooledPacketAllocator extends StaticPacketAllocator {

    public static final PooledPacketAllocator INSTANCE = new PooledPacketAllocator();

    public PooledPacketAllocator() {
        super(
                PooledHandshakePacketAllocator.INSTANCE,
                PooledLoginPacketAllocator.INSTANCE,
                PooledPlayPacketAllocator.INSTANCE,
                PooledStatusPacketAllocator.INSTANCE
        );
    }
}
