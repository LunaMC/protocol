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

public interface SpawnGlobalEntityClientboundV47 extends Packet {

    int getEntityId();

    void setEntityId(int entityId);

    byte getType();

    void setType(byte type);

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getZ();

    void setZ(int z);

    @Override
    default Class<? extends Packet> getModelClass() {
        return SpawnGlobalEntityClientboundV47.class;
    }
}
