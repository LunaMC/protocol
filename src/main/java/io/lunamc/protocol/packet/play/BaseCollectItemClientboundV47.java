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

public class BaseCollectItemClientboundV47 implements CollectItemClientboundV47 {

    private int collectedEntityId;
    private int collectorEntityId;

    BaseCollectItemClientboundV47() {
    }

    @Override
    public int getCollectedEntityId() {
        return collectedEntityId;
    }

    @Override
    public void setCollectedEntityId(int collectedEntityId) {
        this.collectedEntityId = collectedEntityId;
    }

    @Override
    public int getCollectorEntityId() {
        return collectorEntityId;
    }

    @Override
    public void setCollectorEntityId(int collectorEntityId) {
        this.collectorEntityId = collectorEntityId;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getCollectedEntityId());
        ProtocolUtils.writeVarInt(output, getCollectorEntityId());
    }

    @Override
    public void read(ByteBuf input) {
        setCollectedEntityId(ProtocolUtils.readVarInt(input));
        setCollectorEntityId(ProtocolUtils.readVarInt(input));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CollectItemClientboundV47))
            return false;
        CollectItemClientboundV47 that = (CollectItemClientboundV47) o;
        return getCollectedEntityId() == that.getCollectedEntityId() &&
                getCollectorEntityId() == that.getCollectorEntityId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCollectedEntityId(), getCollectorEntityId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{collectedEntityId=" + getCollectedEntityId() +
                ", collectorEntityId=" + getCollectorEntityId() +
                '}';
    }
}
