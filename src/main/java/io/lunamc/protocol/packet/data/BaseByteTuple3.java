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

package io.lunamc.protocol.packet.data;

import java.util.Objects;

class BaseByteTuple3 implements ByteTuple3 {

    private byte byte1;
    private byte byte2;
    private byte byte3;

    BaseByteTuple3() {
    }

    @Override
    public byte getByte1() {
        return byte1;
    }

    @Override
    public void setByte1(byte byte1) {
        this.byte1 = byte1;
    }

    @Override
    public byte getByte2() {
        return byte2;
    }

    @Override
    public void setByte2(byte byte2) {
        this.byte2 = byte2;
    }

    @Override
    public byte getByte3() {
        return byte3;
    }

    @Override
    public void setByte3(byte byte3) {
        this.byte3 = byte3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ByteTuple3))
            return false;
        ByteTuple3 that = (ByteTuple3) o;
        return getByte1() == that.getByte1() &&
                getByte2() == that.getByte2() &&
                getByte3() == that.getByte3();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getByte1(), getByte2(), getByte3());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{byte1=" + getByte1() +
                ", byte2=" + getByte2() +
                ", byte3=" + getByte3() +
                '}';
    }
}
