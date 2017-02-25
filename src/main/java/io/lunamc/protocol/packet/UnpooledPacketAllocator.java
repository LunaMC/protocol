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

package io.lunamc.protocol.packet;

import io.lunamc.protocol.packet.handshake.UnpooledHandshakePacketAllocator;
import io.lunamc.protocol.packet.login.UnpooledLoginPacketAllocator;
import io.lunamc.protocol.packet.play.UnpooledPlayPacketAllocator;
import io.lunamc.protocol.packet.status.UnpooledStatusPacketAllocator;

public class UnpooledPacketAllocator extends StaticPacketAllocator {

    public static final UnpooledPacketAllocator INSTANCE = new UnpooledPacketAllocator();

    public UnpooledPacketAllocator() {
        super(
                UnpooledHandshakePacketAllocator.INSTANCE,
                UnpooledLoginPacketAllocator.INSTANCE,
                UnpooledPlayPacketAllocator.INSTANCE,
                UnpooledStatusPacketAllocator.INSTANCE
        );
    }
}
