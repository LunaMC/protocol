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

class BaseChunkMeta implements ChunkMeta {

    private int chunkX;
    private int chunkZ;
    private int primaryBitMask;

    BaseChunkMeta() {
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
    public int getPrimaryBitMask() {
        return primaryBitMask;
    }

    @Override
    public void setPrimaryBitMask(int primaryBitMask) {
        this.primaryBitMask = primaryBitMask;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(getChunkX());
        output.writeInt(getChunkZ());
        output.writeShort(getPrimaryBitMask());
    }

    @Override
    public void read(ByteBuf input) {
        setChunkX(input.readInt());
        setChunkZ(input.readInt());
        setPrimaryBitMask(input.readUnsignedShort());
    }

    @Override
    public void reset() {
        setChunkX(0);
        setChunkZ(0);
        setPrimaryBitMask(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ChunkMeta))
            return false;
        ChunkMeta that = (ChunkMeta) o;
        return getChunkX() == that.getChunkX() &&
                getChunkZ() == that.getChunkZ() &&
                getPrimaryBitMask() == that.getPrimaryBitMask();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChunkX(), getChunkZ(), getPrimaryBitMask());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{chunkX=" + getChunkX() +
                ", chunkZ=" + getChunkZ() +
                ", primaryBitMask=" + getPrimaryBitMask() +
                '}';
    }
}
