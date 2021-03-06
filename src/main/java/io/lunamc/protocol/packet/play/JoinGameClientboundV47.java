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

public interface JoinGameClientboundV47 extends Packet {

    int getEntityId();

    void setEntityId(int entityId);

    short getGamemode();

    void setGamemode(short gamemode);

    byte getDimension();

    void setDimension(byte dimension);

    short getDifficulty();

    void setDifficulty(short difficulty);

    short getMaxPlayers();

    void setMaxPlayers(short maxPlayers);

    String getLevelType();

    void setLevelType(String levelType);

    boolean isReducedDebugInfo();

    void setReducedDebugInfo(boolean reducedDebugInfo);

    @Override
    default void reset() {
        setEntityId(0);
        setGamemode((short) 0);
        setDimension((byte) 0);
        setDifficulty((short) 0);
        setMaxPlayers((short) 0);
        setLevelType(null);
        setReducedDebugInfo(false);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return JoinGameClientboundV47.class;
    }
}
