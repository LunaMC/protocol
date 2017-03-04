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
import io.netty.buffer.ByteBufUtil;

import java.util.Arrays;
import java.util.Objects;

class BaseChunk implements Chunk {

    private int[] blockTypes;
    private int bitsOfDataPerBlock;
    private byte[] blockLightData;
    private byte[] skylightData;
    private byte[] biomes;

    BaseChunk() {
    }

    @Override
    public int[] getBlockTypes() {
        return blockTypes;
    }

    @Override
    public void setBlockTypes(int[] blockTypes) {
        this.blockTypes = blockTypes;
    }

    @Override
    public int getBitsOfDataPerBlock() {
        return bitsOfDataPerBlock;
    }

    @Override
    public void setBitsOfDataPerBlock(int bitsOfDataPerBlock) {
        this.bitsOfDataPerBlock = bitsOfDataPerBlock;
    }

    @Override
    public byte[] getBlockLightData() {
        return blockLightData;
    }

    @Override
    public void setBlockLightData(byte[] blockLightData) {
        this.blockLightData = blockLightData;
    }

    @Override
    public byte[] getSkylightData() {
        return skylightData;
    }

    @Override
    public void setSkylightData(byte[] skylightData) {
        this.skylightData = skylightData;
    }

    @Override
    public byte[] getBiomes() {
        return biomes;
    }

    @Override
    public void setBiomes(byte[] biomes) {
        this.biomes = biomes;
    }

    @Override
    public void write(ByteBuf output) {
        // ToDo: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void read(ByteBuf input) {
        // ToDo: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Chunk))
            return false;
        Chunk baseChunk = (Chunk) o;
        return getBitsOfDataPerBlock() == baseChunk.getBitsOfDataPerBlock() &&
                Arrays.equals(getBlockTypes(), baseChunk.getBlockTypes()) &&
                Arrays.equals(getBlockLightData(), baseChunk.getBlockLightData()) &&
                Arrays.equals(getSkylightData(), baseChunk.getSkylightData()) &&
                Arrays.equals(getBiomes(), baseChunk.getBiomes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBlockTypes(), getBitsOfDataPerBlock(), getBlockLightData(), getSkylightData(), getBiomes());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseChunk{blockTypes=");
        int[] blockTypes = getBlockTypes();
        if (blockTypes == null)
            sb.append("null");
        else
            sb.append(Arrays.toString(blockTypes));

        sb.append(", bitsOfDataPerBlock=").append(getBitsOfDataPerBlock());

        sb.append(", blockLightData=");
        byte[] blockLightData = getBlockLightData();
        if (blockLightData == null)
            sb.append("null");
        else
            sb.append('[').append(ByteBufUtil.hexDump(blockLightData)).append(']');

        sb.append(", skylightData=");
        byte[] skylightData = getSkylightData();
        if (skylightData == null)
            sb.append("null");
        else
            sb.append('[').append(ByteBufUtil.hexDump(skylightData)).append(']');

        sb.append(", biomes=");
        byte[] biomes = getBiomes();
        if (biomes == null)
            sb.append("null");
        else
            sb.append('[').append(ByteBufUtil.hexDump(biomes)).append(']');
        sb.append('}');

        return sb.toString();
    }
}
