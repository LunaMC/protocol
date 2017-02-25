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

class BaseSetCompressionClientboundV47 implements SetCompressionClientboundV47 {

    private int threshold;

    BaseSetCompressionClientboundV47() {
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeVarInt(output, getThreshold());
    }

    @Override
    public void read(ByteBuf input) {
        setThreshold(ProtocolUtils.readVarInt(input));
    }

    @Override
    public void reset() {
        setThreshold(0);
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof SetCompressionClientboundV47 && getThreshold() == ((SetCompressionClientboundV47) o).getThreshold());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getThreshold());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{threshold=" + threshold + '}';
    }
}
