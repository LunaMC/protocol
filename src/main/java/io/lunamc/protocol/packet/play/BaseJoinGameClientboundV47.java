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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

class BaseJoinGameClientboundV47 implements JoinGameClientboundV47 {

    private int entityId;
    private short gamemode;
    private byte dimension;
    private short difficulty;
    private short maxPlayers;
    private String levelType;
    private boolean reducedDebugInfo;

    BaseJoinGameClientboundV47() {
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public short getGamemode() {
        return gamemode;
    }

    @Override
    public void setGamemode(short gamemode) {
        this.gamemode = gamemode;
    }

    @Override
    public byte getDimension() {
        return dimension;
    }

    @Override
    public void setDimension(byte dimension) {
        this.dimension = dimension;
    }

    @Override
    public short getDifficulty() {
        return difficulty;
    }

    @Override
    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public short getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public void setMaxPlayers(short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public String getLevelType() {
        return levelType;
    }

    @Override
    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    @Override
    public boolean isReducedDebugInfo() {
        return reducedDebugInfo;
    }

    @Override
    public void setReducedDebugInfo(boolean reducedDebugInfo) {
        this.reducedDebugInfo = reducedDebugInfo;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(getEntityId());
        output.writeByte(getGamemode());
        output.writeByte(getDimension());
        output.writeByte(getDifficulty());
        output.writeByte(getMaxPlayers());
        ProtocolUtils.writeString(output, getLevelType());
        output.writeBoolean(isReducedDebugInfo());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(input.readInt());
        setGamemode(input.readUnsignedByte());
        setDimension(input.readByte());
        setDifficulty(input.readUnsignedByte());
        setMaxPlayers(input.readUnsignedByte());
        setLevelType(ProtocolUtils.readString(input));
        setReducedDebugInfo(input.readBoolean());
    }

    @Override
    public void reset() {
        setEntityId(0);
        setGamemode((short) 0);
        setDimension((byte) 0);
        setDifficulty((short) 0);
        setMaxPlayers((short) 0);
        setLevelType(null);
        setReducedDebugInfo(false);
    }
}
