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

import io.lunamc.protocol.packet.NetworkSerializable;

import java.util.List;

public interface EntityMetadata extends NetworkSerializable {

    List<EntityMetadataEntry> getMetadata();

    void setMetadata(List<EntityMetadataEntry> entries);

    @Override
    default void reset() {
        setMetadata(null);
    }

    interface EntityMetadataEntry extends NetworkSerializable {

        int TYPE_BYTE = 0;
        int TYPE_VARINT = 1;
        int TYPE_FLOAT = 2;
        int TYPE_STRING = 3;
        int TYPE_CHAT = 4;
        int TYPE_SLOT = 5;
        int TYPE_BOOLEAN = 6;
        int TYPE_ROTATION = 7;
        int TYPE_POSITION = 8;
        int TYPE_OPT_POSITION = 9;
        int TYPE_DIRECTION = 10;
        int TYPE_OPT_UUID = 11;
        int TYPE_OPT_BLOCK_ID = 12;

        short getIndex();

        void setIndex(short index);

        byte getType();

        void setType(byte type);

        Object getValue();

        void setValue(Object value);

        @Override
        default void reset() {
            setIndex((short) 0);
            setType((byte) 0);
            setValue(null);
        }
    }
}
