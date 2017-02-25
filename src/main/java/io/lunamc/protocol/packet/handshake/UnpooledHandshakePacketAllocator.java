/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

public class UnpooledHandshakePacketAllocator implements HandshakePacketAllocator {

    public static final UnpooledHandshakePacketAllocator INSTANCE = new UnpooledHandshakePacketAllocator();

    protected UnpooledHandshakePacketAllocator() {
    }

    @Override
    public HandshakeServerboundV47 handshakeServerboundV47() {
        return new BaseHandshakeServerboundV47();
    }
}
