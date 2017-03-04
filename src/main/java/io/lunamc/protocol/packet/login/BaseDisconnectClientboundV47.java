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

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseDisconnectClientboundV47 implements DisconnectClientboundV47 {

    private String reason;

    BaseDisconnectClientboundV47() {
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeString(output, getReason());
    }

    @Override
    public void read(ByteBuf input) {
        setReason(ProtocolUtils.readString(input));
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof DisconnectClientboundV47 && Objects.equals(getReason(), ((DisconnectClientboundV47) o).getReason()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReason());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" + "reason=\"" + reason + "\"}";
    }
}
