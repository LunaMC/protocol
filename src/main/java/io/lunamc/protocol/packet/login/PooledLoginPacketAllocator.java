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

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.internal.utils.RecyclerUtils;
import io.lunamc.protocol.internal.utils.ThreadSafeHolder;
import io.netty.util.Recycler;

import java.util.Objects;
import java.util.function.Supplier;

public class PooledLoginPacketAllocator implements LoginPacketAllocator {

    public static final PooledLoginPacketAllocator INSTANCE = new PooledLoginPacketAllocator();

    private final ThreadSafeHolder<Recycler<ReferenceCountedDisconnectClientboundV47>> disconnectClientboundV47Recycler;
    private final ThreadSafeHolder<Recycler<ReferenceCountedEncryptionRequestClientboundV47>> encryptionRequestClientboundV47Recycler;
    private final ThreadSafeHolder<Recycler<ReferenceCountedLoginSuccessClientboundV47>> loginSuccessClientboundV47Recycler;
    private final ThreadSafeHolder<Recycler<ReferenceCountedSetCompressionClientboundV47>> setCompressionClientboundV47Recycler;
    private final ThreadSafeHolder<Recycler<ReferenceCountedLoginStartServerboundV47>> loginStartServerboundV47Recycler;
    private final ThreadSafeHolder<Recycler<ReferenceCountedEncryptionResponseServerboundV47>> encryptionResponseServerboundV47Recycler;

    public PooledLoginPacketAllocator() {
        this(
                RecyclerUtils.createLazy(ReferenceCountedDisconnectClientboundV47::new),
                RecyclerUtils.createLazy(ReferenceCountedEncryptionRequestClientboundV47::new),
                RecyclerUtils.createLazy(ReferenceCountedLoginSuccessClientboundV47::new),
                RecyclerUtils.createLazy(ReferenceCountedSetCompressionClientboundV47::new),
                RecyclerUtils.createLazy(ReferenceCountedLoginStartServerboundV47::new),
                RecyclerUtils.createLazy(ReferenceCountedEncryptionResponseServerboundV47::new)
        );
    }

    public PooledLoginPacketAllocator(Supplier<Recycler<ReferenceCountedDisconnectClientboundV47>> disconnectClientboundV47RecyclerSupplier,
                                      Supplier<Recycler<ReferenceCountedEncryptionRequestClientboundV47>> encryptionRequestClientboundV47RecyclerSupplier,
                                      Supplier<Recycler<ReferenceCountedLoginSuccessClientboundV47>> loginSuccessClientboundV47RecyclerSupplier,
                                      Supplier<Recycler<ReferenceCountedSetCompressionClientboundV47>> setCompressionClientboundV47RecyclerSupplier,
                                      Supplier<Recycler<ReferenceCountedLoginStartServerboundV47>> loginStartServerboundV47RecyclerSupplier,
                                      Supplier<Recycler<ReferenceCountedEncryptionResponseServerboundV47>> encryptionResponseServerboundV47RecyclerSupplier) {
        Objects.requireNonNull(disconnectClientboundV47RecyclerSupplier, "disconnectClientboundV47RecyclerSupplier must not be null");
        disconnectClientboundV47Recycler = new ThreadSafeHolder<>(disconnectClientboundV47RecyclerSupplier);

        Objects.requireNonNull(encryptionRequestClientboundV47RecyclerSupplier, "encryptionRequestClientboundV47RecyclerSupplier must not be null");
        encryptionRequestClientboundV47Recycler = new ThreadSafeHolder<>(encryptionRequestClientboundV47RecyclerSupplier);

        Objects.requireNonNull(loginSuccessClientboundV47RecyclerSupplier, "loginSuccessClientboundV47RecyclerSupplier must not be null");
        loginSuccessClientboundV47Recycler = new ThreadSafeHolder<>(loginSuccessClientboundV47RecyclerSupplier);

        Objects.requireNonNull(setCompressionClientboundV47RecyclerSupplier, "setCompressionClientboundV47RecyclerSupplier must not be null");
        setCompressionClientboundV47Recycler = new ThreadSafeHolder<>(setCompressionClientboundV47RecyclerSupplier);

        Objects.requireNonNull(loginStartServerboundV47RecyclerSupplier, "loginStartServerboundV47RecyclerSupplier must not be null");
        loginStartServerboundV47Recycler = new ThreadSafeHolder<>(loginStartServerboundV47RecyclerSupplier);

        Objects.requireNonNull(encryptionResponseServerboundV47RecyclerSupplier, "encryptionResponseServerboundV47RecyclerSupplier must not be null");
        encryptionResponseServerboundV47Recycler = new ThreadSafeHolder<>(encryptionResponseServerboundV47RecyclerSupplier);
    }

    @Override
    public DisconnectClientboundV47 getDisconnectClientboundV47() {
        return RecyclerUtils.get(disconnectClientboundV47Recycler);
    }

    @Override
    public EncryptionRequestClientboundV47 getEncryptionRequestClientboundV47() {
        return RecyclerUtils.get(encryptionRequestClientboundV47Recycler);
    }

    @Override
    public LoginSuccessClientboundV47 getLoginSuccessClientboundV47() {
        return RecyclerUtils.get(loginSuccessClientboundV47Recycler);
    }

    @Override
    public SetCompressionClientboundV47 getSetCompressionClientboundV47() {
        return RecyclerUtils.get(setCompressionClientboundV47Recycler);
    }

    @Override
    public LoginStartServerboundV47 getLoginStartServerboundV47() {
        return RecyclerUtils.get(loginStartServerboundV47Recycler);
    }

    @Override
    public EncryptionResponseServerboundV47 getEncryptionResponseServerboundV47() {
        return RecyclerUtils.get(encryptionResponseServerboundV47Recycler);
    }
}
