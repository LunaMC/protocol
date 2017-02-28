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

package io.lunamc.protocol.packet.data;

import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseMapIcon implements MapIcon {

    private byte directionAndType;
    private byte x;
    private byte z;

    BaseMapIcon() {
    }

    @Override
    public byte getDirectionAndType() {
        return directionAndType;
    }

    @Override
    public void setDirectionAndType(byte directionAndType) {
        this.directionAndType = directionAndType;
    }

    @Override
    public byte getX() {
        return x;
    }

    @Override
    public void setX(byte x) {
        this.x = x;
    }

    @Override
    public byte getZ() {
        return z;
    }

    @Override
    public void setZ(byte z) {
        this.z = z;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getDirectionAndType());
        output.writeByte(getX());
        output.writeByte(getZ());
    }

    @Override
    public void read(ByteBuf input) {
        setDirectionAndType(input.readByte());
        setX(input.readByte());
        setZ(input.readByte());
    }

    @Override
    public void reset() {
        setDirectionAndType((byte) 0);
        setX((byte) 0);
        setZ((byte) 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MapIcon))
            return false;
        MapIcon that = (MapIcon) o;
        return getDirectionAndType() == that.getDirectionAndType() &&
                getX() == that.getX() &&
                getZ() == that.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDirectionAndType(), getX(), getZ());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{directionAndType=" + getDirectionAndType() +
                ", x=" + getX() +
                ", z=" + getZ() +
                '}';
    }
}
