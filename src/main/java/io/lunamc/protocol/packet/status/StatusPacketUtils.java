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

package io.lunamc.protocol.packet.status;

import io.lunamc.protocol.internal.utils.PacketMapper;
import io.lunamc.protocol.packet.Packet;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class StatusPacketUtils {

    private static final PacketMapper<StatusPacketAllocator> V47_MAPPER;

    static {
        V47_MAPPER = PacketMapper.<StatusPacketAllocator>newBuilder()
                .registerClientboundPacket(ResponseClientboundV47.class, 0x00, StatusPacketAllocator::getResponseClientboundV47)
                .registerClientboundPacket(PingMultiboundV47.class, 0x01, StatusPacketAllocator::getPingMultiboundV47)

                .registerServerboundPacket(RequestServerboundV47.class, 0x00, StatusPacketAllocator::getRequestServerboundV47)
                .registerServerboundPacket(PingMultiboundV47.class, 0x01, StatusPacketAllocator::getPingMultiboundV47)
                .build();
    }

    private StatusPacketUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static int getServerboundPacketId(Packet packet, int protocolVersion) {
        Objects.requireNonNull(packet, "packet must not be null");
        if (protocolVersion >= 47)
            return V47_MAPPER.getServerboundPacketId(packet);
        throw new NoSuchElementException("Cannot find packet id for " + packet.getClass().getName() + " for protocol version " + protocolVersion);
    }

    public static int getClientboundPacketId(Packet packet, int protocolVersion) {
        Objects.requireNonNull(packet, "packet must not be null");
        if (protocolVersion >= 47)
            return V47_MAPPER.getClientboundPacketId(packet);
        throw new NoSuchElementException("Cannot find packet id for " + packet.getClass().getName() + " for protocol version " + protocolVersion);
    }

    public static Packet allocateServerboundPacket(StatusPacketAllocator allocator, int packetId, int protocolVersion) {
        Objects.requireNonNull(allocator, "allocator must not be null");
        Function<StatusPacketAllocator, Packet> allocatorFunction = null;
        if (protocolVersion >= 47)
            allocatorFunction = V47_MAPPER.getServerboundPacketAllocator(packetId);

        if (allocatorFunction != null)
            return allocatorFunction.apply(allocator);
        else
            throw new NoSuchElementException("Cannot allocate serverbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }

    public static Packet allocateClientboundPacket(StatusPacketAllocator allocator, int packetId, int protocolVersion) {
        Objects.requireNonNull(allocator, "allocator must not be null");
        Function<StatusPacketAllocator, Packet> allocatorFunction = null;
        if (protocolVersion >= 47)
            allocatorFunction = V47_MAPPER.getClientboundPacketAllocator(packetId);

        if (allocatorFunction != null)
            return allocatorFunction.apply(allocator);
        else
            throw new NoSuchElementException("Cannot allocate clientbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }
}
