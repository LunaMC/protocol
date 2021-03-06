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

public interface RespawnClientboundV47 extends Packet {

    int getDimension();

    void setDimension(int dimension);

    short getDifficulty();

    void setDifficulty(short difficulty);

    short getGamemode();

    void setGamemode(short gamemode);

    String getLevelType();

    void setLevelType(String levelType);

    @Override
    default void reset() {
        setDimension(0);
        setDifficulty((short) 0);
        setGamemode((short) 0);
        setLevelType(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return RespawnClientboundV47.class;
    }
}
