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

public interface EntityVelocityClientboundV47 extends Packet {

    int getEntityId();

    void setEntityId(int entityId);

    short getVelocityX();

    void setVelocityX(short velocityX);

    short getVelocityY();

    void setVelocityY(short velocityY);

    short getVelocityZ();

    void setVelocityZ(short velocityZ);

    @Override
    default void reset() {
        setEntityId(0);
        setVelocityX((byte) 0);
        setVelocityY((byte) 0);
        setVelocityZ((byte) 0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return EntityVelocityClientboundV47.class;
    }
}
