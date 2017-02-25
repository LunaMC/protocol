/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

public interface LoginPacketAllocator {

    DisconnectClientboundV47 getDisconnectClientboundV47();

    EncryptionRequestClientboundV47 getEncryptionRequestClientboundV47();

    LoginSuccessClientboundV47 getLoginSuccessClientboundV47();

    SetCompressionClientboundV47 getSetCompressionClientboundV47();

    LoginStartServerboundV47 getLoginStartServerboundV47();

    EncryptionResponseServerboundV47 getEncryptionResponseServerboundV47();
}
