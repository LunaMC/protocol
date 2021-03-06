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

public interface ClientStatusServerboundV47 extends Packet {

    int ACTION_PERFORM_RESPAWN = 0;
    int ACTION_REQUEST_STATS = 1;
    int ACTION_TAKING_INVENTORY_ACHIEVEMENT = 2;

    int getActionId();

    void setActionId(int actionId);

    @Override
    default void reset() {
        setActionId(0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return ClientStatusServerboundV47.class;
    }
}
