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

public class BaseEntityRelativeMoveClientboundV47Test extends AbstractPacketTest {

    @Override
    protected Packet createPacket(boolean content) {
        BaseEntityRelativeMoveClientboundV47 packet = new BaseEntityRelativeMoveClientboundV47();
        if (content) {
            packet.setEntityId(42);
            packet.setDeltaX((byte) 4);
            packet.setDeltaY((byte) 8);
            packet.setDeltaZ((byte) 15);
            packet.setOnGround(true);
        }
        return packet;
    }
}
