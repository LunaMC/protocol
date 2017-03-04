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

import com.flowpowered.nbt.Tag;
import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.utils.PacketUtils;

public interface UpdateEntityNbtClientboundV47 extends Packet {

    int getEntityId();

    void setEntityId(int entityId);

    Tag<?> getTag();

    void setTag(Tag<?> tag);

    @Override
    default void reset() {
        setEntityId(0);
        PacketUtils.reset(getTag());
        setTag(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return UpdateEntityNbtClientboundV47.class;
    }
}
