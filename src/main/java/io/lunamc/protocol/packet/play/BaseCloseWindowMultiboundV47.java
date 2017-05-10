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

class BaseCloseWindowMultiboundV47 implements CloseWindowMultiboundV47 {

    private short windowId;

    BaseCloseWindowMultiboundV47() {
    }

    @Override
    public short getWindowId() {
        return windowId;
    }

    @Override
    public void setWindowId(short windowId) {
        this.windowId = windowId;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getWindowId());
    }

    @Override
    public void read(ByteBuf input) {
        setWindowId(input.readUnsignedByte());
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof CloseWindowMultiboundV47 && getWindowId() == ((CloseWindowMultiboundV47) o).getWindowId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWindowId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{windowId=" + getWindowId() + '}';
    }
}
