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

import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.packet.data.SlotData;
import io.lunamc.protocol.utils.PacketUtils;

public interface ClickWindowServerboundV47 extends Packet {

    byte MODE_CLICK = 0;
    byte MODE_SHIFT_CLICK = 1;
    byte MODE_NUMBER_KEY = 2;
    byte MODE_MIDDLE_CLICK = 3;
    byte MODE_UNASSIGNED_CLICK = 4;
    byte MODE_DRAG = 5;
    byte MODE_DOUBLE_CLICK = 6;

    byte BUTTON_LEFT_CLICK = 0;
    byte BUTTON_RIGHT_CLICK = 1;
    byte BUTTON_LEFT_SHIFT_CLICK = 0;
    byte BUTTON_RIGHT_SHIFT_CLICK = 1;
    byte BUTTON_NUMBER_KEY_1 = 0;
    byte BUTTON_NUMBER_KEY_2 = 1;
    byte BUTTON_NUMBER_KEY_3 = 2;
    byte BUTTON_NUMBER_KEY_4 = 3;
    byte BUTTON_NUMBER_KEY_5 = 4;
    byte BUTTON_NUMBER_KEY_6 = 5;
    byte BUTTON_NUMBER_KEY_7 = 6;
    byte BUTTON_NUMBER_KEY_8 = 7;
    byte BUTTON_NUMBER_KEY_9 = 8;
    byte BUTTON_DROP_KEY = 0;
    byte BUTTON_CTR_DROP_KEY = 1;
    byte BUTTON_LEFT_CLICK_OUTSIDE = 0;
    byte BUTTON_RIGHT_CLICK_OUTSIDE = 1;
    byte BUTTON_STARTING_LEFT_MOUSE_DRAG = 0;
    byte BUTTON_ADD_SLOT_FOR_LEFT_MOSE_DRAG = 1;
    byte BUTTON_END_LEFT_MOUSE_DRAG = 2;
    // 3 seems to not existing
    byte BUTTON_STARTING_RIGHT_MOUSE_DRAG = 4;
    byte BUTTON_ADD_SLOT_FOR_RIGHT_MOUSE_DRAG = 5;
    byte BUTTON_END_RIGHT_MOUSE_DRAG = 6;
    byte BUTTON_DOUBLE_CLICK = 0;

    short getWindowId();

    void setWindowId(short windowId);

    short getSlot();

    void setSlot(short slot);

    byte getButton();

    void setButton(byte button);

    short getActionNumber();

    void setActionNumber(short actionNumber);

    byte getMode();

    void setMode(byte mode);

    SlotData getClickedItem();

    void setClickedItem(SlotData clickedItem);

    @Override
    default void reset() {
        setWindowId((short) 0);
        setSlot((short) 0);
        setButton((byte) 0);
        setActionNumber((short) 0);
        setMode((byte) 0);
        PacketUtils.reset(getClickedItem());
        setClickedItem(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return ClickWindowServerboundV47.class;
    }
}
