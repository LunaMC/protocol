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

public class BaseCombatEventClientboundV47EndCombatTest extends AbstractPacketTest {

    @Override
    protected Packet createPacket(boolean content) {
        BaseCombatEventClientboundV47 packet = new BaseCombatEventClientboundV47();
        if (content) {
            packet.setEvent(CombatEventClientboundV47.EVENT_END_COMBAT);
            packet.setDuration(42);
            packet.setEntityId(1337);
        }
        return packet;
    }
}
