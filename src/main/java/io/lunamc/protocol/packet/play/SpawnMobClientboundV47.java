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

public interface SpawnMobClientboundV47 extends Packet {

    int getEntityId();

    void setEntityId(int entityId);

    short getType();

    void setType(short type);

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

    byte getHeadPitch();

    void setHeadPitch(byte headPitch);

    short getVelocityX();

    void setVelocityX(short velocityX);

    short getVelocityY();

    void setVelocityY(short velocityY);

    short getVelocityZ();

    void setVelocityZ(short velocityZ);

    EntityMetadata getMetadata();

    void setMetadata(EntityMetadata metadata);

    @Override
    default void reset() {
        setEntityId(0);
        setType((byte) 0);
        setX(0);
        setY(0);
        setZ(0);
        setYaw((byte) 0);
        setPitch((byte) 0);
        setHeadPitch((byte) 0);
        setVelocityX((short) 0);
        setVelocityY((short) 0);
        setVelocityZ((short) 0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return SpawnMobClientboundV47.class;
    }
}
