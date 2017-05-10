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

class BaseEntityVelocityClientboundV47 implements EntityVelocityClientboundV47 {

    private int entityId;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    BaseEntityVelocityClientboundV47() {
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
    public short getVelocityX() {
        return velocityX;
    }

    @Override
    public void setVelocityX(short velocityX) {
        this.velocityX = velocityX;
    }

    @Override
    public short getVelocityY() {
        return velocityY;
    }

    @Override
    public void setVelocityY(short velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public short getVelocityZ() {
        return velocityZ;
    }

    @Override
    public void setVelocityZ(short velocityZ) {
        this.velocityZ = velocityZ;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        output.writeShort(getVelocityX());
        output.writeShort(getVelocityY());
        output.writeShort(getVelocityZ());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setVelocityX(input.readShort());
        setVelocityY(input.readShort());
        setVelocityZ(input.readShort());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityVelocityClientboundV47))
            return false;
        EntityVelocityClientboundV47 that = (EntityVelocityClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getVelocityX() == that.getVelocityX() &&
                getVelocityY() == that.getVelocityY() &&
                getVelocityZ() == that.getVelocityZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getVelocityX(), getVelocityY(), getVelocityZ());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", velocityX=" + getVelocityX() +
                ", velocityY=" + getVelocityY() +
                ", velocityZ=" + getVelocityZ() +
                '}';
    }
}
