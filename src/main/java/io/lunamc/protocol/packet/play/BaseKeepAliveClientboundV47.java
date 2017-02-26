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

class BaseKeepAliveClientboundV47 implements KeepAliveClientboundV47 {

    private int keepAliveId;

    BaseKeepAliveClientboundV47() {
    }

    @Override
    public int getKeepAliveId() {
        return keepAliveId;
    }

    @Override
    public void setKeepAliveId(int keepAliveId) {
        this.keepAliveId = keepAliveId;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getKeepAliveId());
    }

    @Override
    public void read(ByteBuf input) {
        setKeepAliveId(ProtocolUtils.readVarInt(input));
    }

    @Override
    public void reset() {
        setKeepAliveId(0);
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof KeepAliveClientboundV47 && getKeepAliveId() == ((KeepAliveClientboundV47) o).getKeepAliveId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeepAliveId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{keepAliveId=" + getKeepAliveId() + '}';
    }
}
