/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.netty.util.Recycler;

public class PooledHandshakePacketAllocator implements HandshakePacketAllocator {

    public static final PooledHandshakePacketAllocator INSTANCE = new PooledHandshakePacketAllocator();

    private final Recycler<ReferenceCountedHandshakeServerboundV47> handshakeRequestV47Recycler;

    protected PooledHandshakePacketAllocator() {
        this(new Recycler<ReferenceCountedHandshakeServerboundV47>() {
            @Override
            protected ReferenceCountedHandshakeServerboundV47 newObject(Handle<ReferenceCountedHandshakeServerboundV47> handle) {
                return new ReferenceCountedHandshakeServerboundV47(handle);
            }
        });
    }

    protected PooledHandshakePacketAllocator(Recycler<ReferenceCountedHandshakeServerboundV47> handshakeRequestV47Recycler) {
        this.handshakeRequestV47Recycler = handshakeRequestV47Recycler;
    }

    @Override
    public HandshakeServerboundV47 handshakeServerboundV47() {
        return handshakeRequestV47Recycler.get();
    }
}
