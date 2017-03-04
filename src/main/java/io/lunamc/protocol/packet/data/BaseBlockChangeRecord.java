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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseBlockChangeRecord implements BlockChangeRecord {

    private short horizontalPosition;
    private short yCoordinate;
    private int blockId;

    BaseBlockChangeRecord() {
    }

    @Override
    public short getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public void setHorizontalPosition(short horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    @Override
    public short getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public void setYCoordinate(short yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public int getBlockId() {
        return blockId;
    }

    @Override
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getHorizontalPosition());
        output.writeByte(getYCoordinate());
        ProtocolUtils.writeVarInt(output, getBlockId());
    }

    @Override
    public void read(ByteBuf input) {
        setHorizontalPosition(input.readUnsignedByte());
        setYCoordinate(input.readUnsignedByte());
        setBlockId(ProtocolUtils.readVarInt(input));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BlockChangeRecord))
            return false;
        BlockChangeRecord that = (BlockChangeRecord) o;
        return getHorizontalPosition() == that.getHorizontalPosition() &&
                yCoordinate == that.getYCoordinate() &&
                getBlockId() == that.getBlockId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHorizontalPosition(), getYCoordinate(), getBlockId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{horizontalPosition=" + getHorizontalPosition() +
                ", yCoordinate=" + getYCoordinate() +
                ", blockId=" + getBlockId() + '}';
    }
}
