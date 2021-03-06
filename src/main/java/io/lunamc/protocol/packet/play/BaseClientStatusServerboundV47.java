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

class BaseClientStatusServerboundV47 implements ClientStatusServerboundV47 {

    private int actionId;

    BaseClientStatusServerboundV47() {
    }

    @Override
    public int getActionId() {
        return actionId;
    }

    @Override
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getActionId());
    }

    @Override
    public void read(ByteBuf input) {
        setActionId(ProtocolUtils.readVarInt(input));
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof ClientStatusServerboundV47 && getActionId() == ((ClientStatusServerboundV47) o).getActionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{actionId=" + getActionId() + '}';
    }
}
