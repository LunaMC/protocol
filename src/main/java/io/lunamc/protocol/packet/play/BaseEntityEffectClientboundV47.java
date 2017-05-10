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

class BaseEntityEffectClientboundV47 implements EntityEffectClientboundV47 {

    private int entityId;
    private byte effectId;
    private byte amplifier;
    private int duration;
    private boolean hideParticles;

    BaseEntityEffectClientboundV47() {
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
    public byte getEffectId() {
        return effectId;
    }

    @Override
    public void setEffectId(byte effectId) {
        this.effectId = effectId;
    }

    @Override
    public byte getAmplifier() {
        return amplifier;
    }

    @Override
    public void setAmplifier(byte amplifier) {
        this.amplifier = amplifier;
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
    public boolean isHideParticles() {
        return hideParticles;
    }

    @Override
    public void setHideParticles(boolean hideParticles) {
        this.hideParticles = hideParticles;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        output.writeByte(getEffectId());
        output.writeByte(getAmplifier());
        ProtocolUtils.writeVarInt(output, getDuration());
        output.writeBoolean(isHideParticles());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setEffectId(input.readByte());
        setAmplifier(input.readByte());
        setDuration(ProtocolUtils.readVarInt(input));
        setHideParticles(input.readBoolean());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityEffectClientboundV47))
            return false;
        EntityEffectClientboundV47 that = (EntityEffectClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getEffectId() == that.getEffectId() &&
                getAmplifier() == that.getAmplifier() &&
                getDuration() == that.getDuration() &&
                isHideParticles() == that.isHideParticles();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getEffectId(), getAmplifier(), getDuration(), isHideParticles());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", effectId=" + getEffectId() +
                ", amplifier=" + getAmplifier() +
                ", duration=" + getDuration() +
                ", hideParticles=" + isHideParticles() +
                '}';
    }
}
