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

public class PlayerAbilityFlag {

    // Protocol version 47+
    public static final byte FLAG_INVULNERABLE = 0b00000001;
    public static final byte FLAG_FLYING = 0b00000010;
    public static final byte FLAG_ALLOW_FLYING = 0b00000100;
    public static final byte FLAG_CREATIVE_MODE = 0b00001000;

    private PlayerAbilityFlag() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }
}
