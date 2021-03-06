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

public interface CombatEventClientboundV47 extends Packet {

    int EVENT_ENTER_COMBAT = 0;
    int EVENT_END_COMBAT = 1;
    int EVENT_ENTITY_DEAD = 2;

    int getEvent();

    void setEvent(int event);

    int getDuration();

    void setDuration(int duration);

    int getPlayerId();

    void setPlayerId(int playerId);

    int getEntityId();

    void setEntityId(int entityId);

    String getMessage();

    void setMessage(String message);

    @Override
    default void reset() {
        setEntityId(0);
        setDuration(0);
        setPlayerId(0);
        setEntityId(0);
        setMessage(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return CombatEventClientboundV47.class;
    }
}
