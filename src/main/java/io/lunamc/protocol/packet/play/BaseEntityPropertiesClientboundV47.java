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
import io.lunamc.protocol.packet.data.EntityProperty;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class BaseEntityPropertiesClientboundV47 implements EntityPropertiesClientboundV47 {

    private final transient DataAllocator dataAllocator;
    private int entityId;
    private List<EntityProperty> properties;

    BaseEntityPropertiesClientboundV47(DataAllocator dataAllocator) {
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
    public List<EntityProperty> getProperties() {
        return properties;
    }

    @Override
    public void setProperties(List<EntityProperty> properties) {
        this.properties = properties;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getEntityId());
        List<EntityProperty> properties = getProperties();
        output.writeInt(properties.size());
        properties.forEach(p -> p.write(output));
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(ProtocolUtils.readVarInt(input));
        int length = input.readInt();
        List<EntityProperty> properties = getProperties();
        if (properties == null)
            properties = new ArrayList<>(length);
        else
            properties.clear();
        while (length-- > 0) {
            EntityProperty property = dataAllocator.getEntityProperty();
            property.read(input);
            properties.add(property);
        }
        setProperties(properties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityPropertiesClientboundV47))
            return false;
        EntityPropertiesClientboundV47 that = (EntityPropertiesClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                Objects.equals(getProperties(), that.getProperties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getProperties());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() +
                ", properties=" + getProperties() +
                '}';
    }
}
