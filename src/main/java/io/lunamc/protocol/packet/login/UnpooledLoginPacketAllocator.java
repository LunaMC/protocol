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
