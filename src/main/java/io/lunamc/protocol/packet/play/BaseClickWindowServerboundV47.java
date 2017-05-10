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

import io.lunamc.protocol.packet.data.DataAllocator;
import io.lunamc.protocol.packet.data.SlotData;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseClickWindowServerboundV47 implements ClickWindowServerboundV47 {

    private final transient DataAllocator dataAllocator;
    private short windowId;
    private short slot;
    private byte button;
    private short actionNumber;
    private byte mode;
    private SlotData clickedItem;

    BaseClickWindowServerboundV47(DataAllocator dataAllocator) {
        this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
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
    public short getSlot() {
        return slot;
    }

    @Override
    public void setSlot(short slot) {
        this.slot = slot;
    }

    @Override
    public byte getButton() {
        return button;
    }

    @Override
    public void setButton(byte button) {
        this.button = button;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    @Override
    public byte getMode() {
        return mode;
    }

    @Override
    public void setMode(byte mode) {
        this.mode = mode;
    }

    @Override
    public SlotData getClickedItem() {
        return clickedItem;
    }

    @Override
    public void setClickedItem(SlotData clickedItem) {
        this.clickedItem = clickedItem;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getWindowId());
        output.writeShort(getSlot());
        output.writeByte(getButton());
        output.writeShort(getActionNumber());
        output.writeByte(getMode());
        SlotData clickedItem = getClickedItem();
        if (clickedItem != null)
            clickedItem.write(output);
    }

    @Override
    public void read(ByteBuf input) {
        setWindowId(input.readUnsignedByte());
        setSlot(input.readShort());
        setButton(input.readByte());
        setActionNumber(input.readShort());
        setMode(input.readByte());
        SlotData clickedItem = dataAllocator.getSlotData();
        clickedItem.read(input);
        setClickedItem(clickedItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ClickWindowServerboundV47))
            return false;
        ClickWindowServerboundV47 that = (ClickWindowServerboundV47) o;
        return getWindowId() == that.getWindowId() &&
                getSlot() == that.getSlot() &&
                getButton() == that.getButton() &&
                getActionNumber() == that.getActionNumber() &&
                getMode() == that.getMode() &&
                Objects.equals(getClickedItem(), that.getClickedItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWindowId(), getSlot(), getButton(), getActionNumber(), getMode(), getClickedItem());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{windowId=" + getWindowId() +
                ", slot=" + getSlot() +
                ", button=" + getButton() +
                ", actionNumber=" + getActionNumber() +
                ", mode=" + getMode() +
                ", clickedItem=" + getClickedItem() +
                '}';
    }
}
