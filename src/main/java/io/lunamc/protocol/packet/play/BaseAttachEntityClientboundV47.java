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

import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseAttachEntityClientboundV47 implements AttachEntityClientboundV47 {

    private int entityId;
    private int vehicleId;
    private boolean leash;

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public int getVehicleId() {
        return vehicleId;
    }

    @Override
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean isLeash() {
        return leash;
    }

    @Override
    public void setLeash(boolean leash) {
        this.leash = leash;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(getEntityId());
        output.writeInt(getVehicleId());
        output.writeBoolean(isLeash());
    }

    @Override
    public void read(ByteBuf input) {
        setEntityId(input.readInt());
        setVehicleId(input.readInt());
        setLeash(input.readBoolean());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AttachEntityClientboundV47))
            return false;
        AttachEntityClientboundV47 that = (AttachEntityClientboundV47) o;
        return getEntityId() == that.getEntityId() &&
                getVehicleId() == that.getVehicleId() &&
                isLeash() == that.isLeash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityId(), getVehicleId(), isLeash());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{entityId=" + getEntityId() + ", vehicleId=" + getVehicleId() + ", leash=" + isLeash() + '}';
    }
}
