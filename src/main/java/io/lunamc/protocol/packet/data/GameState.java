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

package io.lunamc.protocol.packet.data;

public class GameState {

    // Protocol version 47+
    public static final short REASON_INVALID_BED = 0;
    public static final short REASON_END_RAINING = 1;
    public static final short REASON_BEGIN_RAINING = 2;
    public static final short REASON_CHANGE_GAME_MODE = 3;
    public static final short REASON_ENTER_CREDITS = 4;
    public static final short REASON_DEMO_MESSAGE = 5;
    public static final short REASON_ARROW_HITTING_PLAYER = 6;
    public static final short REASON_FADE_VALUE = 7;
    public static final short REASON_FADE_TIME = 8;
    public static final short REASON_PLAYER_MOB_APPEARANCE = 10;

    private GameState() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }
}
