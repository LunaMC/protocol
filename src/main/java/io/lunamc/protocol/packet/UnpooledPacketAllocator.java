/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet;

import io.lunamc.protocol.packet.handshake.UnpooledHandshakePacketAllocator;
import io.lunamc.protocol.packet.login.UnpooledLoginPacketAllocator;
import io.lunamc.protocol.packet.play.UnpooledPlayPacketAllocator;
import io.lunamc.protocol.packet.status.UnpooledStatusPacketAllocator;

public class UnpooledPacketAllocator extends StaticPacketAllocator {

    public static final UnpooledPacketAllocator INSTANCE = new UnpooledPacketAllocator();

    public UnpooledPacketAllocator() {
        super(
                UnpooledHandshakePacketAllocator.INSTANCE,
                UnpooledLoginPacketAllocator.INSTANCE,
                UnpooledPlayPacketAllocator.INSTANCE,
                UnpooledStatusPacketAllocator.INSTANCE
        );
    }
}
