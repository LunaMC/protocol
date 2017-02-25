/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
