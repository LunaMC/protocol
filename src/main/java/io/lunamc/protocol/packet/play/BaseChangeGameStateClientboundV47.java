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

import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseChangeGameStateClientboundV47 implements ChangeGameStateClientboundV47 {

    private short reason;
    private float value;

    @Override
    public short getReason() {
        return reason;
    }

    @Override
    public void setReason(short reason) {
        this.reason = reason;
    }

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getReason());
        output.writeFloat(getValue());
    }

    @Override
    public void read(ByteBuf input) {
        setReason(input.readUnsignedByte());
        setValue(input.readFloat());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ChangeGameStateClientboundV47))
            return false;
        ChangeGameStateClientboundV47 that = (ChangeGameStateClientboundV47) o;
        return getReason() == that.getReason() &&
                Float.compare(that.getValue(), getValue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReason(), getValue());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{reason=" + getReason() + ", value=" + getValue() + '}';
    }
}
