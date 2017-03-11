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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseBlockActionClientboundV47 implements BlockActionClientboundV47 {

    private long location;
    private short byte1;
    private short byte2;
    private int blockType;

    BaseBlockActionClientboundV47() {
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
    public short getByte1() {
        return byte1;
    }

    @Override
    public void setByte1(short byte1) {
        this.byte1 = byte1;
    }

    @Override
    public short getByte2() {
        return byte2;
    }

    @Override
    public void setByte2(short byte2) {
        this.byte2 = byte2;
    }

    @Override
    public int getBlockType() {
        return blockType;
    }

    @Override
    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeLong(getLocation());
        output.writeByte(getByte1());
        output.writeByte(getByte2());
        ProtocolUtils.writeVarInt(output, getBlockType());
    }

    @Override
    public void read(ByteBuf input) {
        setLocation(input.readLong());
        setByte1(input.readUnsignedByte());
        setByte2(input.readUnsignedByte());
        setBlockType(ProtocolUtils.readVarInt(input));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BlockActionClientboundV47))
            return false;
        BlockActionClientboundV47 that = (BlockActionClientboundV47) o;
        return getLocation() == that.getLocation() &&
                getByte1() == that.getByte1() &&
                getByte2() == that.getByte2() &&
                getBlockType() == that.getBlockType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getByte1(), getByte2(), getBlockType());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{location=" + getLocation() + ", byte1=" + getByte1() + ", byte2=" + getByte2() + ", blockType=" + getBlockType() + "}";
    }
}
