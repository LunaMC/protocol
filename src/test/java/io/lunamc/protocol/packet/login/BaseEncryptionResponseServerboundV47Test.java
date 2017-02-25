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

public class BaseEncryptionResponseServerboundV47Test extends AbstractPacketTest {

    private static final byte[] SHARED_SECRET = DataUtils.createRandomByteArray(128);
    private static final byte[] VERIFY_TOKEN = DataUtils.createRandomByteArray(4);

    @Override
    protected Packet createPacket(boolean content) {
        BaseEncryptionResponseServerboundV47 packet = new BaseEncryptionResponseServerboundV47();
        if (content) {
            packet.setSharedSecret(SHARED_SECRET);
            packet.setVerifyToken(VERIFY_TOKEN);
        }
        return packet;
    }
}
