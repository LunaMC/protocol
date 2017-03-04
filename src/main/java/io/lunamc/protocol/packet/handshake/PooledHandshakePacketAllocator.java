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

import io.lunamc.protocol.internal.utils.RecyclerUtils;
import io.lunamc.protocol.internal.utils.ThreadSafeHolder;
import io.netty.util.Recycler;

import java.util.function.Supplier;

public class PooledHandshakePacketAllocator implements HandshakePacketAllocator {

    public static final PooledHandshakePacketAllocator INSTANCE = new PooledHandshakePacketAllocator();

    private ThreadSafeHolder<Recycler<ReferenceCountedHandshakeServerboundV47>> handshakeServerboundV47Recycler;

    protected PooledHandshakePacketAllocator() {
        this(
                RecyclerUtils.createLazy(ReferenceCountedHandshakeServerboundV47::new)
        );
    }

    protected PooledHandshakePacketAllocator(Supplier<Recycler<ReferenceCountedHandshakeServerboundV47>> handshakeServerboundV47RecyclerSupplier) {
        handshakeServerboundV47Recycler = new ThreadSafeHolder<>(handshakeServerboundV47RecyclerSupplier);
    }

    @Override
    public HandshakeServerboundV47 getHandshakeServerboundV47() {
        return handshakeServerboundV47Recycler.get().get();
    }
}
