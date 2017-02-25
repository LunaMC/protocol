/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
