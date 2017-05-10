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

package io.lunamc.protocol.packet.play;

import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseConfirmTransactionMultiboundV47 implements ConfirmTransactionMultiboundV47 {

    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    BaseConfirmTransactionMultiboundV47() {
    }

    @Override
    public byte getWindowId() {
        return windowId;
    }

    @Override
    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    @Override
    public short getActionNumber() {
        return actionNumber;
    }

    @Override
    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    @Override
    public boolean isAccepted() {
        return accepted;
    }

    @Override
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getWindowId());
        output.writeShort(getActionNumber());
        output.writeBoolean(isAccepted());
    }

    @Override
    public void read(ByteBuf input) {
        setWindowId(input.readByte());
        setActionNumber(input.readShort());
        setAccepted(input.readBoolean());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ConfirmTransactionMultiboundV47))
            return false;
        ConfirmTransactionMultiboundV47 that = (ConfirmTransactionMultiboundV47) o;
        return getWindowId() == that.getWindowId() &&
                getActionNumber() == that.getActionNumber() &&
                isAccepted() == that.isAccepted();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWindowId(), getActionNumber(), isAccepted());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{windowId=" + getWindowId() +
                ", actionNumber=" + getActionNumber() +
                ", accepted=" + isAccepted() +
                '}';
    }
}
