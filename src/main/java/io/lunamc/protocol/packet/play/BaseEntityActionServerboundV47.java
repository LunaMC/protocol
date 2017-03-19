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

public class BaseEntityActionServerboundV47 implements EntityActionServerboundV47 {

    private int entityId;
    private int actionId;
    private int actionParameter;

    BaseEntityActionServerboundV47() {
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
    public int getActionId() {
        return actionId;
    }

    @Override
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public int getActionParameter() {
        return actionParameter;
    }

    @Override
    public void setActionParameter(int actionParameter) {
        this.actionParameter = actionParameter;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        ProtocolUtils.writeVarInt(output, getActionId());
        ProtocolUtils.writeVarInt(output, getActionParameter());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setActionId(ProtocolUtils.readVarInt(input));
        setActionParameter(ProtocolUtils.readVarInt(input));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityActionServerboundV47))
            return false;
        EntityActionServerboundV47 that = (EntityActionServerboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getActionId() == that.getActionId() &&
                getActionParameter() == that.getActionParameter();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getActionId(), getActionParameter());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", actionId=" + getActionId() +
                ", actionParameter=" + getActionParameter() +
                '}';
    }
}
