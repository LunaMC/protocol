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

public interface EntityActionServerboundV47 extends Packet {

    // Protocol version 47+
    int ACTION_START_SNEAKING = 0;
    int ACTION_STOP_SNEAKING = 1;
    int ACTION_LEAVE_BED = 2;
    int ACTION_START_SPRINTING = 3;
    int ACTION_STOP_SPRINTING = 4;
    int ACTION_JUMP_WITH_HORSE = 5;
    int ACTION_OPEN_RIDDEN_HORSE_INVENTORY = 6;

    int getEntityId();

    void setEntityId(int entityId);

    int getActionId();

    void setActionId(int actionId);

    int getActionParameter();

    void setActionParameter(int actionParameter);

    @Override
    default void reset() {
        setEntityId(0);
        setActionId(0);
        setActionId(0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return EntityActionServerboundV47.class;
    }
}
