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

    private final Recycler<ReferenceCountedHandshakeRequestV47> handshakeRequestV47Recycler;

    protected PooledHandshakePacketAllocator() {
        this(new Recycler<ReferenceCountedHandshakeRequestV47>() {
            @Override
            protected ReferenceCountedHandshakeRequestV47 newObject(Handle<ReferenceCountedHandshakeRequestV47> handle) {
                return new ReferenceCountedHandshakeRequestV47(handle);
            }
        });
    }

    protected PooledHandshakePacketAllocator(Recycler<ReferenceCountedHandshakeRequestV47> handshakeRequestV47Recycler) {
        this.handshakeRequestV47Recycler = handshakeRequestV47Recycler;
    }

    @Override
    public HandshakeRequestV47 handshakeRequestV47() {
        return handshakeRequestV47Recycler.get();
    }
}
