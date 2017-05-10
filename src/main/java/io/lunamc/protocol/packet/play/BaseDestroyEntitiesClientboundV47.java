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

import java.util.Arrays;

class BaseDestroyEntitiesClientboundV47 implements DestroyEntitiesClientboundV47 {

    private int[] entityIds;

    BaseDestroyEntitiesClientboundV47() {
    }

    @Override
    public int[] getEntityIds() {
        return entityIds;
    }

    @Override
    public void setEntityIds(int[] entityIds) {
        this.entityIds = entityIds;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarIntArray(output, getEntityIds());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityIds(ProtocolUtils.readVarIntArray(input));
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof DestroyEntitiesClientboundV47 && Arrays.equals(getEntityIds(), ((DestroyEntitiesClientboundV47) o).getEntityIds()));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getEntityIds());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityIds=" + Arrays.toString(getEntityIds()) + '}';
    }
}
