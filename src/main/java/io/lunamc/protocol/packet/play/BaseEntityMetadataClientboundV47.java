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
import io.lunamc.protocol.packet.data.DataAllocator;
import io.lunamc.protocol.packet.data.EntityMetadata;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseEntityMetadataClientboundV47 implements EntityMetadataClientboundV47 {

    private final transient DataAllocator dataAllocator;
    private int entityId;
    private EntityMetadata metadata;

    BaseEntityMetadataClientboundV47(DataAllocator dataAllocator) {
        this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
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
    public EntityMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(EntityMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        getMetadata().write(output);
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        EntityMetadata metadata = dataAllocator.getEntityMetadata();
        metadata.read(input);
        setMetadata(metadata);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityMetadataClientboundV47))
            return false;
        EntityMetadataClientboundV47 that = (EntityMetadataClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                Objects.equals(getMetadata(), that.getMetadata());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getMetadata());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", metadata=" + getMetadata() +
                '}';
    }
}
