/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseHandshakeRequestV47 implements HandshakeRequestV47 {

    private int protocolVersion;
    private String serverAddress;
    private int serverPort;
    private NextProtocolState nextState;

    @Override
    public int getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public String getServerAddress() {
        return serverAddress;
    }

    @Override
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public NextProtocolState getNextState() {
        return nextState;
    }

    @Override
    public void setNextState(NextProtocolState nextState) {
        this.nextState = nextState;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getProtocolVersion());
        ProtocolUtils.writeString(output, getServerAddress());
        output.writeShort(getServerPort());
        ProtocolUtils.writeVarInt(output, getNextState().getReference());
    }

    @Override
    public void read(ByteBuf input) {
        setProtocolVersion(ProtocolUtils.readVarInt(input));
        setServerAddress(ProtocolUtils.readString(input));
        setServerPort(input.readUnsignedShort());
        setNextState(NextProtocolState.valueOfReference(ProtocolUtils.readVarInt(input)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BaseHandshakeRequestV47))
            return false;
        BaseHandshakeRequestV47 that = (BaseHandshakeRequestV47) o;
        return getProtocolVersion() == that.getProtocolVersion() &&
                getServerPort() == that.getServerPort() &&
                Objects.equals(getServerAddress(), that.getServerAddress()) &&
                getNextState() == that.getNextState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProtocolVersion(), getServerAddress(), getServerPort(), getNextState());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" + "protocolVersion=" + getProtocolVersion() +
                ", serverAddress=\"" + getServerAddress() + '"' +
                ", serverPort=" + getServerPort() +
                ", nextState=" + getNextState() +
                '}';
    }
}
