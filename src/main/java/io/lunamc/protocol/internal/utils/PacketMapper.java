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

package io.lunamc.protocol.internal.utils;

import io.lunamc.protocol.packet.Packet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class PacketMapper<T> {

    private final Map<Class<?>, Integer> serverboundPacketIds;
    private final Map<Class<?>, Integer> clientboundPacketIds;
    private final Map<Integer, Function<T, Packet>> serverboundPacketAllocators;
    private final Map<Integer, Function<T, Packet>> clientboundPacketAllocators;

    public PacketMapper(Map<Class<?>, Integer> serverboundPacketIds,
                        Map<Class<?>, Integer> clientboundPacketIds,
                        Map<Integer, Function<T, Packet>> serverboundPacketAllocators,
                        Map<Integer, Function<T, Packet>> clientboundPacketAllocators) {
        Objects.requireNonNull(serverboundPacketIds, "serverboundPacketIds must not be null");
        this.serverboundPacketIds = Collections.unmodifiableMap(new HashMap<>(serverboundPacketIds));
        Objects.requireNonNull(clientboundPacketIds, "clientboundPacketIds must not be null");
        this.clientboundPacketIds = Collections.unmodifiableMap(new HashMap<>(clientboundPacketIds));
        Objects.requireNonNull(serverboundPacketAllocators, "serverboundPacketAllocators must not be null");
        this.serverboundPacketAllocators = Collections.unmodifiableMap(new HashMap<>(serverboundPacketAllocators));
        Objects.requireNonNull(clientboundPacketAllocators, "clientboundPacketAllocators must not be null");
        this.clientboundPacketAllocators = Collections.unmodifiableMap(new HashMap<>(clientboundPacketAllocators));
    }

    public int getServerboundPacketId(Packet packet) {
        Objects.requireNonNull(packet, "packet must not be null");
        return serverboundPacketIds.get(packet.getModelClass());
    }

    public int getClientboundPacketId(Packet packet) {
        Objects.requireNonNull(packet, "packet must not be null");
        return clientboundPacketIds.get(packet.getModelClass());
    }

    public Function<T, Packet> getServerboundPacketAllocator(int packetId) {
        return serverboundPacketAllocators.get(packetId);
    }

    public Function<T, Packet> getClientboundPacketAllocator(int packetId) {
        return clientboundPacketAllocators.get(packetId);
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }

    public static class Builder<T> {

        private final Map<Class<?>, Integer> serverboundPacketIds = new HashMap<>();
        private final Map<Class<?>, Integer> clientboundPacketIds = new HashMap<>();
        private final Map<Integer, Function<T, Packet>> serverboundPacketAllocators = new HashMap<>();
        private final Map<Integer, Function<T, Packet>> clientboundPacketAllocators = new HashMap<>();

        private Builder() {
        }

        public Builder<T> registerServerboundPacket(Class<?> modelClass, int packetId, Function<T, Packet> allocator) {
            Objects.requireNonNull(modelClass, "modelClass must not be null");
            Objects.requireNonNull(allocator, "allocator must not be null");

            registerPacket(serverboundPacketIds, serverboundPacketAllocators, modelClass, packetId, allocator);
            return this;
        }

        public Builder<T> registerClientboundPacket(Class<?> modelClass, int packetId, Function<T, Packet> allocator) {
            Objects.requireNonNull(modelClass, "modelClass must not be null");
            Objects.requireNonNull(allocator, "allocator must not be null");

            registerPacket(clientboundPacketIds, clientboundPacketAllocators, modelClass, packetId, allocator);
            return this;
        }

        public PacketMapper<T> build() {
            return new PacketMapper<>(
                    serverboundPacketIds,
                    clientboundPacketIds,
                    serverboundPacketAllocators,
                    clientboundPacketAllocators
            );
        }

        private void registerPacket(Map<Class<?>, Integer> packetIds,
                                    Map<Integer, Function<T, Packet>> allocators,
                                    Class<?> modelClass,
                                    int packetId,
                                    Function<T, Packet> allocator) {
            packetIds.put(modelClass, packetId);
            allocators.put(packetId, allocator);
        }
    }
}
