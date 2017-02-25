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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.Arrays;
import java.util.Objects;

class BaseEncryptionResponseServerboundV47 implements EncryptionResponseServerboundV47 {

    private byte[] sharedSecret;
    private byte[] verifyToken;

    BaseEncryptionResponseServerboundV47() {
    }

    @Override
    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    @Override
    public void setSharedSecret(byte[] sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    @Override
    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void setVerifyToken(byte[] verifyToken) {
        this.verifyToken = verifyToken;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeByteArray(output, getSharedSecret());
        ProtocolUtils.writeByteArray(output, getVerifyToken());
    }

    @Override
    public void read(ByteBuf input) {
        setSharedSecret(ProtocolUtils.readByteArray(input));
        setVerifyToken(ProtocolUtils.readByteArray(input));
    }

    @Override
    public void reset() {
        setSharedSecret(null);
        setVerifyToken(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EncryptionResponseServerboundV47))
            return false;
        EncryptionResponseServerboundV47 that = (EncryptionResponseServerboundV47) o;
        return Arrays.equals(getSharedSecret(), that.getSharedSecret()) &&
                Arrays.equals(getVerifyToken(), that.getVerifyToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSharedSecret(), getVerifyToken());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName())
                .append("{sharedSecret=");
        byte[] sharedSecret = getSharedSecret();
        if (sharedSecret == null)
            sb.append("null");
        else
            sb.append('[').append(ByteBufUtil.hexDump(sharedSecret)).append(']');
        sb.append(", verifyToken=");
        byte[] verifyToken = getVerifyToken();
        if (verifyToken == null)
            sb.append("null");
        else
            sb.append('[').append(ByteBufUtil.hexDump(verifyToken)).append(']');
        sb.append('}');
        return sb.toString();
    }
}
