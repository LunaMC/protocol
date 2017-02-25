/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
