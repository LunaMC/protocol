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

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.packet.Packet;

import java.util.NoSuchElementException;
import java.util.Objects;

public class HandshakePacketUtils {

    // Handshake state has only one packet. It's okay to keep the things simple by not using PacketMapper

    private HandshakePacketUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static int getServerboundPacketId(Packet packet, int protocolVersion) {
        Objects.requireNonNull(packet, "packet must not be null");
        if (protocolVersion >= 47 && HandshakeServerboundV47.class.isAssignableFrom(packet.getModelClass()))
            return 0x00;
        throw new NoSuchElementException("Cannot find packet id for " + packet.getClass().getName() + " for protocol version " + protocolVersion);
    }

    public static int getClientboundPacketId(Packet packet, int protocolVersion) {
        Objects.requireNonNull(packet, "packet must not be null");
        throw new NoSuchElementException("Cannot find packet id for " + packet.getClass().getName() + " for protocol version " + protocolVersion);
    }

    public static Packet allocateServerboundPacket(HandshakePacketAllocator allocator, int packetId, int protocolVersion) {
        Objects.requireNonNull(allocator, "allocator must not be null");
        if (protocolVersion >= 47 && packetId == 0x00)
            return allocator.getHandshakeServerboundV47();
        throw new NoSuchElementException("Cannot allocate serverbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }

    public static Packet allocateClientboundPacket(HandshakePacketAllocator allocator, int packetId, int protocolVersion) {
        Objects.requireNonNull(allocator, "allocator must not be null");
        throw new NoSuchElementException("Cannot allocate clientbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }
}
