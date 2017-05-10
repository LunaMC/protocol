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

class BaseEnchantItemServerboundV47 implements EnchantItemServerboundV47 {

    private byte windowId;
    private byte enchantment;

    BaseEnchantItemServerboundV47() {
    }

    @Override
    public byte getWindowId() {
        return windowId;
    }

    @Override
    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    @Override
    public byte getEnchantment() {
        return enchantment;
    }

    @Override
    public void setEnchantment(byte enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getWindowId());
        output.writeByte(getEnchantment());
    }

    @Override
    public void read(ByteBuf input) {
        setWindowId(input.readByte());
        setEnchantment(input.readByte());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EnchantItemServerboundV47))
            return false;
        EnchantItemServerboundV47 that = (EnchantItemServerboundV47) o;
        return getWindowId() == that.getWindowId() &&
                getEnchantment() == that.getEnchantment();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWindowId(), getEnchantment());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{windowId=" + getWindowId() +
                ", enchantment=" + getEnchantment() +
                '}';
    }
}
