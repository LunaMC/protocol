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

public interface ChangeGameStateClientboundV47 extends Packet {

    // Protocol version 47+
    short REASON_INVALID_BED = 0;
    short REASON_END_RAINING = 1;
    short REASON_BEGIN_RAINING = 2;
    short REASON_CHANGE_GAME_MODE = 3;
    short REASON_ENTER_CREDITS = 4;
    short REASON_DEMO_MESSAGE = 5;
    short REASON_ARROW_HITTING_PLAYER = 6;
    short REASON_FADE_VALUE = 7;
    short REASON_FADE_TIME = 8;
    short REASON_PLAYER_MOB_APPEARANCE = 10;

    short getReason();

    void setReason(short reason);

    float getValue();

    void setValue(float value);

    @Override
    default void reset() {
        setReason((short) 0);
        setValue(0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return ChangeGameStateClientboundV47.class;
    }
}
