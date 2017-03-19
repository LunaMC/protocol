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

public class BaseEntityHeadLookClientboundV47 implements EntityHeadLookClientboundV47 {

    private int entityId;
    private byte headYaw;

    BaseEntityHeadLookClientboundV47() {
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
    public byte getHeadYaw() {
        return headYaw;
    }

    @Override
    public void setHeadYaw(byte headYaw) {
        this.headYaw = headYaw;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        output.writeByte(getHeadYaw());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setHeadYaw(input.readByte());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityHeadLookClientboundV47))
            return false;
        EntityHeadLookClientboundV47 that = (EntityHeadLookClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getHeadYaw() == that.getHeadYaw();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getHeadYaw());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", headYaw=" + getHeadYaw() +
                '}';
    }
}
