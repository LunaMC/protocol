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

public class BaseBlockChangeClientboundV47 implements BlockChangeClientboundV47 {

    private long location;
    private int blockId;

    @Override
    public long getLocation() {
        return location;
    }

    @Override
    public void setLocation(long location) {
        this.location = location;
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
        output.writeLong(getLocation());
        ProtocolUtils.writeVarInt(output, getBlockId());
    }

    @Override
    public void read(ByteBuf input) {
        setLocation(input.readLong());
        setBlockId(ProtocolUtils.readVarInt(input));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BlockChangeClientboundV47))
            return false;
        BlockChangeClientboundV47 that = (BlockChangeClientboundV47) o;
        return getLocation() == that.getLocation() &&
                getBlockId() == that.getBlockId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getBlockId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{location=" + getLocation() + ", blockId=" + getBlockId() + '}';
    }
}
