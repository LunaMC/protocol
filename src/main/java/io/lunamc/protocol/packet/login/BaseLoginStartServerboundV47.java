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
