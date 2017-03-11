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

import io.lunamc.protocol.packet.AbstractPacketTest;
import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.packet.data.DataAllocator;
import io.lunamc.protocol.packet.data.SlotData;
import org.mockito.Mockito;

public class BaseClickWindowServerboundV47Test extends AbstractPacketTest {

    private static final SlotData SLOT_DATA = Mockito.mock(SlotData.class);

    @Override
    protected Packet createPacket(boolean content) {
        BaseClickWindowServerboundV47 packet = new BaseClickWindowServerboundV47(mockDataAllocator());
        if (content) {
            packet.setMode(ClickWindowServerboundV47.MODE_CLICK);
            packet.setClickedItem(SLOT_DATA);
            packet.setButton(ClickWindowServerboundV47.BUTTON_LEFT_CLICK);
            packet.setActionNumber((short) 3);
            packet.setWindowId((short) 42);
        }
        return packet;
    }

    private static DataAllocator mockDataAllocator() {
        DataAllocator mock = Mockito.mock(DataAllocator.class);
        Mockito.when(mock.getSlotData()).thenReturn(SLOT_DATA);
        return mock;
    }
}
