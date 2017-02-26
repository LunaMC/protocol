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

public interface AnimationClientboundV47 extends Packet {

    short ANIMATION_SWING_ARM = 0;
    short ANIMATION_TAKE_DAMAGE = 1;
    short ANIMATION_LEAVE_BED = 2;
    short ANIMATION_EAT_FOOD = 3;
    short ANIMATION_CRITICAL_EFFECT = 4;
    short ANIMATION_MAGIC_CRITICAL_EFFECT = 5;

    int getEntityId();

    void setEntityId(int entityId);

    short getAnimation();

    void setAnimation(short animation);

    @Override
    default int getPacketId() {
        return 0x0b;
    }
}
