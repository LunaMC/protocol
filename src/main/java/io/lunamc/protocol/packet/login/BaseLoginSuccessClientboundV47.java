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

class BaseLoginSuccessClientboundV47 implements LoginSuccessClientboundV47 {

    private String uuid;
    private String username;

    BaseLoginSuccessClientboundV47() {
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeString(output, getUuid());
        ProtocolUtils.writeString(output, getUsername());
    }

    @Override
    public void read(ByteBuf input) {
        setUuid(ProtocolUtils.readString(input));
        setUsername(ProtocolUtils.readString(input));
    }

    @Override
    public void reset() {
        setUuid(null);
        setUsername(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LoginSuccessClientboundV47))
            return false;
        LoginSuccessClientboundV47 that = (LoginSuccessClientboundV47) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getUsername());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{uuid=\"" + getUuid() + "\", username='" + getUsername() + "\"}";
    }
}
