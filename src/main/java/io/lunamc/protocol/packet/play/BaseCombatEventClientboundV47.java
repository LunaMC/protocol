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

import java.util.Objects;

public class BaseCombatEventClientboundV47 implements CombatEventClientboundV47 {

    private int event;
    private int duration;
    private int playerId;
    private int entityId;
    private String message;

    BaseCombatEventClientboundV47() {
    }

    @Override
    public int getEvent() {
        return event;
    }

    @Override
    public void setEvent(int event) {
        this.event = event;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void write(ByteBuf output) {
        int event = getEvent();
        ProtocolUtils.writeVarInt(output, event);
        switch (event) {
            case CombatEventClientboundV47.EVENT_END_COMBAT:
                ProtocolUtils.writeVarInt(output, getDuration());
                ProtocolUtils.writeVarInt(output, getEntityId());
                break;
            case CombatEventClientboundV47.EVENT_ENTITY_DEAD:
                ProtocolUtils.writeVarInt(output, getPlayerId());
                ProtocolUtils.writeVarInt(output, getEntityId());
                ProtocolUtils.writeString(output, getMessage());
                break;
        }
    }

    @Override
    public void read(ByteBuf input) {
        int event = ProtocolUtils.readVarInt(input);
        setEvent(event);
        switch (event) {
            case CombatEventClientboundV47.EVENT_END_COMBAT:
                setDuration(ProtocolUtils.readVarInt(input));
                setEntityId(ProtocolUtils.readVarInt(input));
                break;
            case CombatEventClientboundV47.EVENT_ENTITY_DEAD:
                setPlayerId(ProtocolUtils.readVarInt(input));
                setEntityId(ProtocolUtils.readVarInt(input));
                setMessage(ProtocolUtils.readString(input));
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CombatEventClientboundV47))
            return false;
        CombatEventClientboundV47 that = (CombatEventClientboundV47) o;
        return getEvent() == that.getEvent() &&
                getDuration() == that.getDuration() &&
                getPlayerId() == that.getPlayerId() &&
                getEntityId() == that.getEntityId() &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEvent(), getDuration(), getPlayerId(), getEntityId(), getMessage());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{event=" + getEvent() +
                ", duration=" + getDuration() +
                ", playerId=" + getPlayerId() +
                ", entityId=" + getEntityId() +
                ", message=\"" + getMessage() + "\"}";
    }
}
