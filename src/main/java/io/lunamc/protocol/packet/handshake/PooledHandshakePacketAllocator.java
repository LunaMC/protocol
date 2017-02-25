/*
 *  Copyright 2017 LunaMC.io
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.lunamc.protocol.packet.handshake;

import io.netty.util.Recycler;

public class PooledHandshakePacketAllocator implements HandshakePacketAllocator {

    public static final PooledHandshakePacketAllocator INSTANCE = new PooledHandshakePacketAllocator();

    private final Recycler<ReferenceCountedHandshakeServerboundV47> handshakeServerboundV47Recycler;

    protected PooledHandshakePacketAllocator() {
        this(new Recycler<ReferenceCountedHandshakeServerboundV47>() {
            @Override
            protected ReferenceCountedHandshakeServerboundV47 newObject(Handle<ReferenceCountedHandshakeServerboundV47> handle) {
                return new ReferenceCountedHandshakeServerboundV47(handle);
            }
        });
    }

    protected PooledHandshakePacketAllocator(Recycler<ReferenceCountedHandshakeServerboundV47> handshakeServerboundV47Recycler) {
        this.handshakeServerboundV47Recycler = handshakeServerboundV47Recycler;
    }

    @Override
    public HandshakeServerboundV47 getHandshakeServerboundV47() {
        return handshakeServerboundV47Recycler.get();
    }
}
