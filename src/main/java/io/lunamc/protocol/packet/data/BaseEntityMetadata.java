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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class BaseEntityMetadata implements EntityMetadata {

    private final DataAllocator dataAllocator;
    private List<EntityMetadataEntry> entries;

    BaseEntityMetadata(DataAllocator dataAllocator) {
        this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
    }

    @Override
    public List<EntityMetadataEntry> getMetadata() {
        return entries;
    }

    @Override
    public void setMetadata(List<EntityMetadataEntry> entries) {
        this.entries = entries;
    }

    @Override
    public void write(ByteBuf output) {
        for (EntityMetadataEntry entry : getMetadata())
            entry.write(output);
        TerminatorEntityMetadataEntry.TERMINATOR.write(output);
    }

    @Override
    public void read(ByteBuf input) {
        if (entries == null)
            setMetadata(new ArrayList<>());
        else
            entries.clear();
        while (true) {
            EntityMetadataEntry entry = dataAllocator.getEntityMetadataEntry();
            entry.read(input);
            if (entry.getIndex() == TerminatorEntityMetadataEntry.TERMINATOR_INDEX)
                break;
            else
                entries.add(entry);
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof BaseEntityMetadata && Objects.equals(entries, ((BaseEntityMetadata) o).entries));
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entries=" + entries + '}';
    }

    static class BaseEntityMetadataEntry implements EntityMetadataEntry {

        private final DataAllocator dataAllocator;
        private short index;
        private byte type;
        private Object value;

        BaseEntityMetadataEntry(DataAllocator dataAllocator) {
            this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
        }

        @Override
        public short getIndex() {
            return index;
        }

        @Override
        public void setIndex(short index) {
            this.index = index;
        }

        @Override
        public byte getType() {
            return type;
        }

        @Override
        public void setType(byte type) {
            this.type = type;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public void write(ByteBuf output) {
            short index = getIndex();
            output.writeByte(index);
            if (index != TerminatorEntityMetadataEntry.TERMINATOR_INDEX) {
                short type = getType();
                output.writeByte(type);
                Object value = getValue();
                switch (type) {
                    case TYPE_BYTE:
                        output.writeByte((Byte) value);
                        break;
                    case TYPE_VARINT:
                    case TYPE_DIRECTION:
                        ProtocolUtils.writeVarInt(output, (Integer) value);
                        break;
                    case TYPE_FLOAT:
                        output.writeFloat((Float) value);
                        break;
                    case TYPE_STRING:
                    case TYPE_CHAT:
                        ProtocolUtils.writeString(output, (String) value);
                        break;
                    case TYPE_SLOT:
                        ((SlotData) value).write(output);
                        break;
                    case TYPE_BOOLEAN:
                        output.writeBoolean((Boolean) value);
                        break;
                    case TYPE_ROTATION:
                        ((Rotation) value).write(output);
                        break;
                    case TYPE_POSITION:
                        output.writeLong((Long) value);
                        break;
                    case TYPE_OPT_POSITION:
                        output.writeBoolean(value != null);
                        if (value != null)
                            output.writeLong((Long) value);
                        break;
                    case TYPE_OPT_UUID:
                        output.writeBoolean(value != null);
                        if (value != null)
                            ProtocolUtils.writeUuid(output, (UUID) value);
                        break;
                    case TYPE_OPT_BLOCK_ID:
                        if (value != null)
                            ProtocolUtils.writeVarInt(output, (Integer) value);
                        else
                            ProtocolUtils.writeVarInt(output, 0);
                        break;

                }
            }
        }

        @Override
        public void read(ByteBuf input) {
            short index = input.readUnsignedByte();
            setIndex(index);
            if (index != TerminatorEntityMetadataEntry.TERMINATOR_INDEX) {
                byte type = input.readByte();
                setType(type);
                switch (type) {
                    case TYPE_BYTE:
                        setValue(input.readByte());
                        break;
                    case TYPE_VARINT:
                    case TYPE_DIRECTION:
                        setValue(ProtocolUtils.readVarInt(input));
                        break;
                    case TYPE_FLOAT:
                        setValue(input.readFloat());
                        break;
                    case TYPE_STRING:
                    case TYPE_CHAT:
                        setValue(ProtocolUtils.readString(input));
                        break;
                    case TYPE_SLOT:
                        SlotData tempSlot = dataAllocator.getSlotData();
                        tempSlot.read(input);
                        setValue(tempSlot);
                        break;
                    case TYPE_BOOLEAN:
                        setValue(input.readBoolean());
                        break;
                    case TYPE_ROTATION:
                        Rotation tempRotation = dataAllocator.getRotation();
                        tempRotation.read(input);
                        setValue(tempRotation);
                        break;
                    case TYPE_POSITION:
                        setValue(input.readLong());
                        break;
                    case TYPE_OPT_POSITION:
                        if (input.readBoolean())
                            setValue(input.readLong());
                        break;
                    case TYPE_OPT_UUID:
                        if (input.readBoolean())
                            setValue(ProtocolUtils.readUuid(input));
                        break;
                    case TYPE_OPT_BLOCK_ID:
                        int tempBlockId = ProtocolUtils.readVarInt(input);
                        if (tempBlockId != 0)
                            setValue(tempBlockId);
                        break;
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof EntityMetadataEntry))
                return false;
            EntityMetadataEntry that = (EntityMetadataEntry) o;
            return getIndex() == that.getIndex() &&
                    getType() == that.getType() &&
                    Objects.equals(getValue(), that.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getIndex(), getType(), getValue());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{index=" + getIndex() +
                    ", type=" + getType() +
                    ", value=" + getValue() +
                    '}';
        }
    }

    private static class TerminatorEntityMetadataEntry implements EntityMetadataEntry {

        private static final short TERMINATOR_INDEX = (short) 0xff;
        private static final TerminatorEntityMetadataEntry TERMINATOR = new TerminatorEntityMetadataEntry();

        static {
            TERMINATOR.setIndex(TERMINATOR_INDEX);
        }

        @Override
        public short getIndex() {
            return TERMINATOR_INDEX;
        }

        @Override
        public void setIndex(short index) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public byte getType() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void setType(byte type) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Object getValue() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void setValue(Object value) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void write(ByteBuf output) {
            output.writeByte(getIndex());
        }

        @Override
        public void read(ByteBuf input) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void reset() {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}
