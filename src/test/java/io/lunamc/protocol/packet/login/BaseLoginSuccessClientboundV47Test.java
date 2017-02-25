/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.packet.AbstractPacketTest;
import io.lunamc.protocol.packet.Packet;

import java.util.UUID;

public class BaseLoginSuccessClientboundV47Test extends AbstractPacketTest {

    private static final String UUID = java.util.UUID.randomUUID().toString();

    @Override
    protected Packet createPacket(boolean content) {
        BaseLoginSuccessClientboundV47 packet = new BaseLoginSuccessClientboundV47();
        if (content) {
            packet.setUuid(UUID);
            packet.setUsername("a_sample_player");
        }
        return packet;
    }
}
