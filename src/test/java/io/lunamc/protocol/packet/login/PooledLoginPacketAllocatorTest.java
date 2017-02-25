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
