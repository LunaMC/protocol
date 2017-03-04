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
