/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.testutils.PoolTestUtility;
import org.junit.BeforeClass;
import org.junit.Test;

public class PooledLoginPacketAllocatorTest {

    private static PooledLoginPacketAllocator alloc;

    @BeforeClass
    public static void prepare() {
        alloc = new PooledLoginPacketAllocator();
    }

    @Test
    public void testGetDisconnectClientboundV47() {
        PoolTestUtility.testPoolCapability(alloc::getDisconnectClientboundV47);
    }

    @Test
    public void testGetEncryptionRequestClientboundV47() {
        PoolTestUtility.testPoolCapability(alloc::getEncryptionRequestClientboundV47);
    }

    @Test
    public void testGetLoginSuccessClientboundV47() {
        PoolTestUtility.testPoolCapability(alloc::getLoginSuccessClientboundV47);
    }

    @Test
    public void testGetSetCompressionClientboundV47() {
        PoolTestUtility.testPoolCapability(alloc::getSetCompressionClientboundV47);
    }

    @Test
    public void testGetLoginStartServerboundV47() {
        PoolTestUtility.testPoolCapability(alloc::getLoginStartServerboundV47);
    }

    @Test
    public void testGetEncryptionResponseServerboundV47() {
        PoolTestUtility.testPoolCapability(alloc::getEncryptionResponseServerboundV47);
    }
}
