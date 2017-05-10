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
import org.mockito.Mockito;

import java.util.Collections;

public class BaseExplosionClientboundV47Test extends AbstractPacketTest {

    @Override
    protected Packet createPacket(boolean content) {
        BaseExplosionClientboundV47 packet = new BaseExplosionClientboundV47(mockDataAllocator());
        if (content) {
            packet.setX(4f);
            packet.setY(8f);
            packet.setZ(15f);
            packet.setRadius(16f);
            packet.setRecords(Collections.emptyList());
            packet.setPlayerMotionX(0.4f);
            packet.setPlayerMotionY(0.8f);
            packet.setPlayerMotionZ(0.15f);
        }
        return packet;
    }

    private static DataAllocator mockDataAllocator() {
        return Mockito.mock(DataAllocator.class);
    }
}