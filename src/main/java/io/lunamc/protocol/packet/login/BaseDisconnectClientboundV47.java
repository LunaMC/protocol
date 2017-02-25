/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
    public void reset() {
        setReason(null);
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
