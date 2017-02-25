/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.packet.AbstractPacketTest;
import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.testutils.DataUtils;

public class BaseEncryptionRequestClientboundV47Test extends AbstractPacketTest {

    private static final byte[] PUBLIC_KEY = DataUtils.createRandomByteArray(128);
    private static final byte[] VERIFY_TOKEN = DataUtils.createRandomByteArray(4);

    @Override
    protected Packet createPacket(boolean content) {
        BaseEncryptionRequestClientboundV47 packet = new BaseEncryptionRequestClientboundV47();
        if (content) {
            packet.setServerId("AnServerId");
            packet.setPublicKey(PUBLIC_KEY);
            packet.setVerifyToken(VERIFY_TOKEN);
        }
        return packet;
    }
}
