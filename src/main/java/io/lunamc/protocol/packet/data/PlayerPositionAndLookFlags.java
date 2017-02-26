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

public class PlayerPositionAndLookFlags {

    public static final int X_RELATIVE = 0b00000001;
    public static final int Y_RELATIVE = 0b00000010;
    public static final int Z_RELATIVE = 0b00000100;
    public static final int Y_ROT_RELATIVE = 0b00001000;
    public static final int X_ROT_RELATIVE = 0b00010000;

    private PlayerPositionAndLookFlags() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }
}
