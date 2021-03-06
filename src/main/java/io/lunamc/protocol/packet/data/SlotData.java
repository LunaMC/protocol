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
import io.lunamc.protocol.packet.NetworkSerializable;

public interface SlotData extends NetworkSerializable {

    short EMPTY_BLOCK_ID = -1;

    short getBlockId();

    void setBlockId(short blockId);

    byte getItemCount();

    void setItemCount(byte itemCount);

    short getItemDamage();

    void setItemDamage(short itemDamage);

    Tag<?> getNbt();

    void setNbt(Tag<?> nbt);

    @Override
    default void reset() {
        setBlockId((short) 0);
        setItemCount((byte) 0);
        setItemDamage((short) 0);
        setNbt(null);
    }
}
