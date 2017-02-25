/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

public class UnpooledLoginPacketAllocator implements LoginPacketAllocator {

    public static final UnpooledLoginPacketAllocator INSTANCE = new UnpooledLoginPacketAllocator();

    @Override
    public DisconnectClientboundV47 getDisconnectClientboundV47() {
        return new BaseDisconnectClientboundV47();
    }

    @Override
    public EncryptionRequestClientboundV47 getEncryptionRequestClientboundV47() {
        return new BaseEncryptionRequestClientboundV47();
    }

    @Override
    public LoginSuccessClientboundV47 getLoginSuccessClientboundV47() {
        return new BaseLoginSuccessClientboundV47();
    }

    @Override
    public SetCompressionClientboundV47 getSetCompressionClientboundV47() {
        return new BaseSetCompressionClientboundV47();
    }

    @Override
    public LoginStartServerboundV47 getLoginStartServerboundV47() {
        return new BaseLoginStartServerboundV47();
    }

    @Override
    public EncryptionResponseServerboundV47 getEncryptionResponseServerboundV47() {
        return new BaseEncryptionResponseServerboundV47();
    }
}
