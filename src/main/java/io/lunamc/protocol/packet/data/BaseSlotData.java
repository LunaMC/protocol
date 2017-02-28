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

import com.flowpowered.nbt.Tag;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseSlotData implements SlotData {

    private short blockId;
    private byte itemCount;
    private short itemDamage;
    private Tag<?> nbt;

    BaseSlotData() {
    }

    @Override
    public short getBlockId() {
        return blockId;
    }

    @Override
    public void setBlockId(short blockId) {
        this.blockId = blockId;
    }

    @Override
    public byte getItemCount() {
        return itemCount;
    }

    @Override
    public void setItemCount(byte itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public short getItemDamage() {
        return itemDamage;
    }

    @Override
    public void setItemDamage(short itemDamage) {
        this.itemDamage = itemDamage;
    }

    @Override
    public Tag<?> getNbt() {
        return nbt;
    }

    @Override
    public void setNbt(Tag<?> nbt) {
        this.nbt = nbt;
    }

    @Override
    public void write(ByteBuf output) {
        short blockId = getBlockId();
        output.writeShort(blockId);
    }

    @Override
    public void read(ByteBuf input) {

    }

    @Override
    public void reset() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SlotData))
            return false;
        SlotData that = (SlotData) o;
        return getBlockId() == that.getBlockId() &&
                getItemCount() == that.getItemCount() &&
                getItemDamage() == that.getItemDamage() &&
                Objects.equals(getNbt(), that.getNbt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBlockId(), getItemCount(), getItemDamage(), getNbt());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{blockId=" + getBlockId() +
                ", itemCount=" + getItemCount() +
                ", itemDamage=" + getItemDamage() +
                ", nbt=" + getNbt() +
                '}';
    }
}
