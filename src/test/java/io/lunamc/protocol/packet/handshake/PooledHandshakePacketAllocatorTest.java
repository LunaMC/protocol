/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.testutils.PoolTestUtility;
import org.junit.BeforeClass;
import org.junit.Test;

public class PooledHandshakePacketAllocatorTest {

    private static PooledHandshakePacketAllocator alloc;

    @BeforeClass
    public static void prepare() {
        alloc = new PooledHandshakePacketAllocator();
    }

    @Test
    public void testPooledHandshakeRequestV47() {
        PoolTestUtility.testPoolCapability(alloc::getHandshakeServerboundV47);
    }
}
