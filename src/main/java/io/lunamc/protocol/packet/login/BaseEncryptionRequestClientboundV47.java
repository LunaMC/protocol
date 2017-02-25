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

class BaseEncryptionRequestClientboundV47 implements EncryptionRequestClientboundV47 {

    private String serverId;
    private byte[] publicKey;
    private byte[] verifyToken;

    BaseEncryptionRequestClientboundV47() {
    }

    @Override
    public String getServerId() {
        return serverId;
    }

    @Override
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    @Override
    public byte[] getPublicKey() {
        return publicKey;
    }

    @Override
    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
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
        ProtocolUtils.writeString(output, getServerId());
        ProtocolUtils.writeByteArray(output, getPublicKey());
        ProtocolUtils.writeByteArray(output, getVerifyToken());
    }

    @Override
    public void read(ByteBuf input) {
        setServerId(ProtocolUtils.readString(input));
        setPublicKey(ProtocolUtils.readByteArray(input));
        setVerifyToken(ProtocolUtils.readByteArray(input));
    }

    @Override
    public void reset() {
        setServerId(null);
        setPublicKey(null);
        setVerifyToken(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EncryptionRequestClientboundV47))
            return false;
        EncryptionRequestClientboundV47 that = (EncryptionRequestClientboundV47) o;
        return Objects.equals(getServerId(), that.getServerId()) &&
                Arrays.equals(getPublicKey(), that.getPublicKey()) &&
                Arrays.equals(getVerifyToken(), that.getVerifyToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServerId(), getPublicKey(), getVerifyToken());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName())
                .append("{serverId=\"").append(getServerId()).append("\", publicKey=");
        byte[] publicKey = getPublicKey();
        if (publicKey == null)
            sb.append("null");
        else
            sb.append('[').append(ByteBufUtil.hexDump(publicKey)).append(']');
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
