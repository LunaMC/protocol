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

class BaseLoginStartServerboundV47 implements LoginStartServerboundV47 {

    private String name;

    BaseLoginStartServerboundV47() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeString(output, getName());
    }

    @Override
    public void read(ByteBuf input) {
        setName(ProtocolUtils.readString(input));
    }

    @Override
    public void reset() {
        setName(null);
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof LoginStartServerboundV47 && Objects.equals(getName(), ((LoginStartServerboundV47) o).getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{name=\"" + getName() + "\"}";
    }
}
