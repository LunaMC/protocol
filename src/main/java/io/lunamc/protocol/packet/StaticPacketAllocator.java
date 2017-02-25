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

import io.lunamc.protocol.packet.handshake.HandshakePacketAllocator;
import io.lunamc.protocol.packet.login.LoginPacketAllocator;
import io.lunamc.protocol.packet.play.PlayPacketAllocator;
import io.lunamc.protocol.packet.status.StatusPacketAllocator;

import java.util.Objects;

public class StaticPacketAllocator implements PacketAllocator {

    private final HandshakePacketAllocator handshakePacketAllocator;
    private final LoginPacketAllocator loginPacketAllocator;
    private final PlayPacketAllocator playPacketAllocator;
    private final StatusPacketAllocator statusPacketAllocator;

    public StaticPacketAllocator(HandshakePacketAllocator handshakePacketAllocator,
                                 LoginPacketAllocator loginPacketAllocator,
                                 PlayPacketAllocator playPacketAllocator,
                                 StatusPacketAllocator statusPacketAllocator) {
        this.handshakePacketAllocator = Objects.requireNonNull(handshakePacketAllocator, "handshakePacketAllocator must not be null");
        this.loginPacketAllocator = Objects.requireNonNull(loginPacketAllocator, "loginPacketAllocator must not be null");
        this.playPacketAllocator = Objects.requireNonNull(playPacketAllocator, "playPacketAllocator must not be null");
        this.statusPacketAllocator = Objects.requireNonNull(statusPacketAllocator, "statusPacketAllocator must not be null");
    }

    @Override
    public HandshakePacketAllocator getHandshakePacketAllocator() {
        return handshakePacketAllocator;
    }

    @Override
    public LoginPacketAllocator getLoginPacketAllocator() {
        return loginPacketAllocator;
    }

    @Override
    public PlayPacketAllocator getPlayPacketAllocator() {
        return playPacketAllocator;
    }

    @Override
    public StatusPacketAllocator getStatusPacketAllocator() {
        return statusPacketAllocator;
    }
}
