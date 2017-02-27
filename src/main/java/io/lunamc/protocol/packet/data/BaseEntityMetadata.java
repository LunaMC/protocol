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

import java.util.List;
import java.util.Objects;

class BaseEntityMetadata implements EntityMetadata {

    private List<? extends EntityMetadataEntry> entries;

    BaseEntityMetadata() {
    }

    @Override
    public List<? extends EntityMetadataEntry> getMetadata() {
        return entries;
    }

    @Override
    public void setMetadata(List<? extends EntityMetadataEntry> entries) {
        this.entries = entries;
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

        private short index;
        private byte type;
        private Object value;

        BaseEntityMetadataEntry() {
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
}
