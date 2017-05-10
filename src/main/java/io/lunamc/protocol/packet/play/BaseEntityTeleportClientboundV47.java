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

class BaseEntityTeleportClientboundV47 implements EntityTeleportClientboundV47 {

    private int entityId;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    BaseEntityTeleportClientboundV47() {
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
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
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
        output.writeInt(getX());
        output.writeInt(getY());
        output.writeInt(getZ());
        output.writeByte(getYaw());
        output.writeByte(getPitch());
        output.writeBoolean(isOnGround());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setX(input.readInt());
        setY(input.readInt());
        setZ(input.readInt());
        setYaw(input.readByte());
        setPitch(input.readByte());
        setOnGround(input.readBoolean());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityTeleportClientboundV47))
            return false;
        EntityTeleportClientboundV47 that = (EntityTeleportClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getX() == that.getX() &&
                getY() == that.getY() &&
                getZ() == that.getZ() &&
                getYaw() == that.getYaw() &&
                getPitch() == that.getPitch() &&
                isOnGround() == that.isOnGround();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getX(), getY(), getZ(), getYaw(), getPitch(), isOnGround());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                ", yaw=" + getYaw() +
                ", pitch=" + getPitch() +
                ", onGround=" + isOnGround() +
                '}';
    }
}
