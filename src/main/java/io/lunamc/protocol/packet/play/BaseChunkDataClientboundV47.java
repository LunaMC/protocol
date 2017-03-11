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
import io.lunamc.protocol.packet.data.Chunk;
import io.lunamc.protocol.packet.data.DataAllocator;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseChunkDataClientboundV47 implements ChunkDataClientboundV47 {

    private final DataAllocator dataAllocator;
    private int chunkX;
    private int chunkZ;
    private boolean groundUpContinuous;
    private int primaryBitMask;
    private Chunk data;

    public BaseChunkDataClientboundV47(DataAllocator dataAllocator) {
        this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
    }

    @Override
    public int getChunkX() {
        return chunkX;
    }

    @Override
    public void setChunkX(int chunkX) {
        this.chunkX = chunkX;
    }

    @Override
    public int getChunkZ() {
        return chunkZ;
    }

    @Override
    public void setChunkZ(int chunkZ) {
        this.chunkZ = chunkZ;
    }

    @Override
    public boolean isGroundUpContinuous() {
        return groundUpContinuous;
    }

    @Override
    public void setGroundUpContinuous(boolean groundUpContinuous) {
        this.groundUpContinuous = groundUpContinuous;
    }

    @Override
    public int getPrimaryBitMask() {
        return primaryBitMask;
    }

    @Override
    public void setPrimaryBitMask(int primaryBitMask) {
        this.primaryBitMask = primaryBitMask;
    }

    @Override
    public Chunk getData() {
        return data;
    }

    @Override
    public void setData(Chunk data) {
        this.data = data;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(getChunkX());
        output.writeInt(getChunkZ());
        output.writeBoolean(isGroundUpContinuous());
        output.writeShort(getPrimaryBitMask());
        Chunk data = getData();
        if (data != null)
            data.write(output);
        else
            ProtocolUtils.writeVarInt(output, 0);
    }

    @Override
    public void read(ByteBuf input) {
        setChunkX(input.readInt());
        setChunkZ(input.readInt());
        setGroundUpContinuous(input.readBoolean());
        setPrimaryBitMask(input.readUnsignedShort());
        Chunk data = dataAllocator.getChunk();
        data.read(input);
        setData(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ChunkDataClientboundV47))
            return false;
        ChunkDataClientboundV47 that = (ChunkDataClientboundV47) o;
        return getChunkX() == that.getChunkX() &&
                getChunkZ() == that.getChunkZ() &&
                isGroundUpContinuous() == that.isGroundUpContinuous() &&
                getPrimaryBitMask() == that.getPrimaryBitMask() &&
                Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChunkX(), getChunkZ(), isGroundUpContinuous(), getPrimaryBitMask(), getData());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{chunkX=" + getChunkX() +
                ", chunkZ=" + getChunkZ() +
                ", groundUpContinuous=" + isGroundUpContinuous() +
                ", primaryBitMask=" + getPrimaryBitMask() +
                ", data=" + getData() +
                '}';
    }
}
