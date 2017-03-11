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

public class BaseAnimationClientboundV47 implements AnimationClientboundV47 {

    private int entityId;
    private short animation;

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public short getAnimation() {
        return animation;
    }

    @Override
    public void setAnimation(short animation) {
        this.animation = animation;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        output.writeByte(getAnimation());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        setAnimation(input.readUnsignedByte());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AnimationClientboundV47))
            return false;
        AnimationClientboundV47 that = (AnimationClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getAnimation() == that.getAnimation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getAnimation());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() + ", animation=" + getAnimation() + '}';
    }
}
