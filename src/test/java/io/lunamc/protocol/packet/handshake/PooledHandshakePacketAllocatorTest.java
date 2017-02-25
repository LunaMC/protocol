/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.testutils.PoolTestUtility;
import org.junit.Test;

public class PooledHandshakePacketAllocatorTest {

    @Test
    public void testPooledHandshakeRequestV47() {
        PooledHandshakePacketAllocator alloc = new PooledHandshakePacketAllocator();
        PoolTestUtility.testPoolCapability(alloc::handshakeRequestV47);
    }
}
