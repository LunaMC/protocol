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

import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.packet.data.EntityMetadata;
import io.lunamc.protocol.utils.PacketUtils;

import java.util.UUID;

public interface SpawnPlayerClientboundV47 extends Packet {

    int getEntityId();

    void setEntityId(int entityId);

    UUID getPlayerUuid();

    void setPlayerUuid(UUID playerUuid);

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getZ();

    void setZ(int z);

    byte getYaw();

    void setYaw(byte yaw);

    byte getPitch();

    void setPitch(byte pitch);

    short getCurrentItem();

    void setCurrentItem(short currentItem);

    EntityMetadata getMetadata();

    void setMetadata(EntityMetadata metadata);

    @Override
    default void reset() {
        setEntityId(0);
        setPlayerUuid(null);
        setX(0);
        setY(0);
        setZ(0);
        setYaw((byte) 0);
        setPitch((byte) 0);
        setCurrentItem((short) 0);
        PacketUtils.reset(getMetadata());
        setMetadata(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return SpawnPlayerClientboundV47.class;
    }
}
