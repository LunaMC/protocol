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
