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

public interface UseEntityServerboundV47 extends Packet {

    int TYPE_INTERACT = 0;
    int TYPE_ATTACK = 1;
    int TYPE_INTERACT_AT = 2;

    int getTarget();

    void setTarget(int target);

    int getType();

    void setType(int type);

    float getTargetX();

    void setTargetX(float targetX);

    float getTargetY();

    void setTargetY(float targetY);

    float getTargetZ();

    void setTargetZ(float targetZ);

    @Override
    default void reset() {
        setTarget(0);
        setType(0);
        setTargetX(0);
        setTargetY(0);
        setTargetZ(0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return UseEntityServerboundV47.class;
    }
}
