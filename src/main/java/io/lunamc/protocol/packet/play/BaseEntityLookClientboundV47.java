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

public class BaseEntityLookClientboundV47 implements EntityLookClientboundV47 {

    private int entityId;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    BaseEntityLookClientboundV47() {
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
    public byte getYaw() {
        return yaw;
    }

    @Override
    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }

    @Override
    public byte getPitch() {
        return pitch;
    }

    @Override
    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        output.writeByte(getYaw());
        output.writeByte(getPitch());
        output.writeBoolean(isOnGround());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setYaw(input.readByte());
        setPitch(input.readByte());
        setOnGround(input.readBoolean());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityLookClientboundV47))
            return false;
        EntityLookClientboundV47 that = (EntityLookClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getYaw() == that.getYaw() &&
                getPitch() == that.getPitch() &&
                isOnGround() == that.isOnGround();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getYaw(), getPitch(), isOnGround());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", yaw=" + getYaw() +
                ", pitch=" + getPitch() +
                ", onGround=" + isOnGround() +
                '}';
    }
}
