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

import io.lunamc.protocol.packet.Packet;

import java.util.NoSuchElementException;

public class LoginPacketUtils {

    private LoginPacketUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static Packet allocateServerboundPacket(LoginPacketAllocator allocator, int packetId, int protocolVersion) {
        switch (packetId) {
            case 0x00:
                if (protocolVersion >= 47)
                    return allocator.getLoginStartServerboundV47();
                break;
            case 0x01:
                if (protocolVersion >= 47)
                    return allocator.getEncryptionResponseServerboundV47();
                break;
        }
        throw new NoSuchElementException("Cannot allocate serverbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }

    public static Packet allocateClientboundPacket(LoginPacketAllocator allocator, int packetId, int protocolVersion) {
        switch (packetId) {
            case 0x00:
                if (protocolVersion >= 47)
                    return allocator.getDisconnectClientboundV47();
                break;
            case 0x01:
                if (protocolVersion >= 47)
                    return allocator.getEncryptionRequestClientboundV47();
                break;
            case 0x02:
                if (protocolVersion >= 47)
                    return allocator.getLoginSuccessClientboundV47();
                break;
            case 0x03:
                if (protocolVersion >= 47)
                    return allocator.getSetCompressionClientboundV47();
                break;
        }
        throw new NoSuchElementException("Cannot allocate clientbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }
}
