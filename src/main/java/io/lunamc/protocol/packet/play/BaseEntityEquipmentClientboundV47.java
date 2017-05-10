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
import io.lunamc.protocol.packet.data.DataAllocator;
import io.lunamc.protocol.packet.data.SlotData;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseEntityEquipmentClientboundV47 implements EntityEquipmentClientboundV47 {

    private final transient DataAllocator dataAllocator;
    private int entityId;
    private short slot;
    private SlotData item;

    BaseEntityEquipmentClientboundV47(DataAllocator dataAllocator) {
        this.dataAllocator = dataAllocator;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public short getSlot() {
        return slot;
    }

    @Override
    public void setSlot(short slot) {
        this.slot = slot;
    }

    @Override
    public SlotData getItem() {
        return item;
    }

    @Override
    public void setItem(SlotData item) {
        this.item = item;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        output.writeShort(getSlot());
        getItem().write(output);
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setSlot(input.readShort());
        SlotData item = dataAllocator.getSlotData();
        item.read(input);
        setItem(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityEquipmentClientboundV47))
            return false;
        EntityEquipmentClientboundV47 that = (EntityEquipmentClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getSlot() == that.getSlot() &&
                Objects.equals(getItem(), that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getSlot(), getItem());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", slot=" + getSlot() +
                ", item=" + getItem() +
                '}';
    }
}
