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

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.packet.AbstractPacketTest;
import io.lunamc.protocol.packet.Packet;

public class BaseLoginStartServerboundV47Test extends AbstractPacketTest {

    @Override
    protected Packet createPacket(boolean content) {
        BaseLoginStartServerboundV47 packet = new BaseLoginStartServerboundV47();
        if (content)
            packet.setName("a_sample_player");
        return packet;
    }
}
