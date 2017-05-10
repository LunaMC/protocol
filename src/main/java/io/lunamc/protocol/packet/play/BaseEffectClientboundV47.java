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

class BaseEffectClientboundV47 implements EffectClientboundV47 {

    private int effectId;
    private long location;
    private int data;
    private boolean disableRelativeVolume;

    BaseEffectClientboundV47() {
    }

    @Override
    public int getEffectId() {
        return effectId;
    }

    @Override
    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }

    @Override
    public long getLocation() {
        return location;
    }

    @Override
    public void setLocation(long location) {
        this.location = location;
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public void setData(int data) {
        this.data = data;
    }

    @Override
    public boolean isDisableRelativeVolume() {
        return disableRelativeVolume;
    }

    @Override
    public void setDisableRelativeVolume(boolean disableRelativeVolume) {
        this.disableRelativeVolume = disableRelativeVolume;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(getEffectId());
        output.writeLong(getLocation());
        output.writeInt(getData());
        output.writeBoolean(isDisableRelativeVolume());
    }

    @Override
    public void read(ByteBuf input) {
        setEffectId(input.readInt());
        setLocation(input.readLong());
        setData(input.readInt());
        setDisableRelativeVolume(input.readBoolean());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EffectClientboundV47))
            return false;
        EffectClientboundV47 that = (EffectClientboundV47) o;
        return getEffectId() == that.getEffectId() &&
                getLocation() == that.getLocation() &&
                getData() == that.getData() &&
                isDisableRelativeVolume() == that.isDisableRelativeVolume();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEffectId(), getLocation(), getData(), isDisableRelativeVolume());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{effectId=" + getEffectId() +
                ", location=" + getLocation() +
                ", data=" + getData() +
                ", disableRelativeVolume=" + isDisableRelativeVolume() +
                '}';
    }
}
