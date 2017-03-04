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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseHandshakeServerboundV47 implements HandshakeServerboundV47 {

    private int protocolVersion;
    private String serverAddress;
    private int serverPort;
    private NextProtocolState nextState;

    BaseHandshakeServerboundV47() {
    }

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
        if (!(o instanceof HandshakeServerboundV47))
            return false;
        HandshakeServerboundV47 that = (HandshakeServerboundV47) o;
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
        return getClass().getName() + "{protocolVersion=" + getProtocolVersion() +
                ", serverAddress=\"" + getServerAddress() + '"' +
                ", serverPort=" + getServerPort() +
                ", nextState=" + getNextState() +
                '}';
    }
}
