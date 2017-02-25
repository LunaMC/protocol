/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

import io.netty.util.Recycler;

import java.util.Objects;

public class PooledLoginPacketAllocator implements LoginPacketAllocator {

    public static final PooledLoginPacketAllocator INSTANCE = new PooledLoginPacketAllocator();

    private final Recycler<ReferenceCountedDisconnectClientboundV47> disconnectClientboundV47Recycler;
    private final Recycler<ReferenceCountedEncryptionRequestClientboundV47> encryptionRequestClientboundV47Recycler;
    private final Recycler<ReferenceCountedLoginSuccessClientboundV47> loginSuccessClientboundV47Recycler;
    private final Recycler<ReferenceCountedSetCompressionClientboundV47> setCompressionClientboundV47Recycler;
    private final Recycler<ReferenceCountedLoginStartServerboundV47> loginStartServerboundV47Recycler;
    private final Recycler<ReferenceCountedEncryptionResponseServerboundV47> encryptionResponseServerboundV47Recycler;

    public PooledLoginPacketAllocator() {
        this(
                new Recycler<ReferenceCountedDisconnectClientboundV47>() {
                    @Override
                    protected ReferenceCountedDisconnectClientboundV47 newObject(Handle<ReferenceCountedDisconnectClientboundV47> handle) {
                        return new ReferenceCountedDisconnectClientboundV47(handle);
                    }
                },
                new Recycler<ReferenceCountedEncryptionRequestClientboundV47>() {
                    @Override
                    protected ReferenceCountedEncryptionRequestClientboundV47 newObject(Handle<ReferenceCountedEncryptionRequestClientboundV47> handle) {
                        return new ReferenceCountedEncryptionRequestClientboundV47(handle);
                    }
                },
                new Recycler<ReferenceCountedLoginSuccessClientboundV47>() {
                    @Override
                    protected ReferenceCountedLoginSuccessClientboundV47 newObject(Handle<ReferenceCountedLoginSuccessClientboundV47> handle) {
                        return new ReferenceCountedLoginSuccessClientboundV47(handle);
                    }
                },
                new Recycler<ReferenceCountedSetCompressionClientboundV47>() {
                    @Override
                    protected ReferenceCountedSetCompressionClientboundV47 newObject(Handle<ReferenceCountedSetCompressionClientboundV47> handle) {
                        return new ReferenceCountedSetCompressionClientboundV47(handle);
                    }
                },
                new Recycler<ReferenceCountedLoginStartServerboundV47>() {
                    @Override
                    protected ReferenceCountedLoginStartServerboundV47 newObject(Handle<ReferenceCountedLoginStartServerboundV47> handle) {
                        return new ReferenceCountedLoginStartServerboundV47(handle);
                    }
                },
                new Recycler<ReferenceCountedEncryptionResponseServerboundV47>() {
                    @Override
                    protected ReferenceCountedEncryptionResponseServerboundV47 newObject(Handle<ReferenceCountedEncryptionResponseServerboundV47> handle) {
                        return new ReferenceCountedEncryptionResponseServerboundV47(handle);
                    }
                }
        );
    }

    public PooledLoginPacketAllocator(Recycler<ReferenceCountedDisconnectClientboundV47> disconnectClientboundV47Recycler,
                                      Recycler<ReferenceCountedEncryptionRequestClientboundV47> encryptionRequestClientboundV47Recycler,
                                      Recycler<ReferenceCountedLoginSuccessClientboundV47> loginSuccessClientboundV47Recycler,
                                      Recycler<ReferenceCountedSetCompressionClientboundV47> setCompressionClientboundV47Recycler,
                                      Recycler<ReferenceCountedLoginStartServerboundV47> loginStartServerboundV47Recycler,
                                      Recycler<ReferenceCountedEncryptionResponseServerboundV47> encryptionResponseServerboundV47Recycler) {
        this.disconnectClientboundV47Recycler = Objects.requireNonNull(disconnectClientboundV47Recycler, "disconnectClientboundV47Recycler must not be null");
        this.encryptionRequestClientboundV47Recycler = Objects.requireNonNull(encryptionRequestClientboundV47Recycler, "encryptionRequestClientboundV47Recycler must not be null");
        this.loginSuccessClientboundV47Recycler = Objects.requireNonNull(loginSuccessClientboundV47Recycler, "loginSuccessClientboundV47Recycler must not be null");
        this.setCompressionClientboundV47Recycler = Objects.requireNonNull(setCompressionClientboundV47Recycler, "setCompressionClientboundV47Recycler must not be null");
        this.loginStartServerboundV47Recycler = Objects.requireNonNull(loginStartServerboundV47Recycler, "loginStartServerboundV47Recycler must not be null");
        this.encryptionResponseServerboundV47Recycler = Objects.requireNonNull(encryptionResponseServerboundV47Recycler, "encryptionResponseServerboundV47Recycler must not be null");
    }

    @Override
    public DisconnectClientboundV47 getDisconnectClientboundV47() {
        return disconnectClientboundV47Recycler.get();
    }

    @Override
    public EncryptionRequestClientboundV47 getEncryptionRequestClientboundV47() {
        return encryptionRequestClientboundV47Recycler.get();
    }

    @Override
    public LoginSuccessClientboundV47 getLoginSuccessClientboundV47() {
        return loginSuccessClientboundV47Recycler.get();
    }

    @Override
    public SetCompressionClientboundV47 getSetCompressionClientboundV47() {
        return setCompressionClientboundV47Recycler.get();
    }

    @Override
    public LoginStartServerboundV47 getLoginStartServerboundV47() {
        return loginStartServerboundV47Recycler.get();
    }

    @Override
    public EncryptionResponseServerboundV47 getEncryptionResponseServerboundV47() {
        return encryptionResponseServerboundV47Recycler.get();
    }
}
