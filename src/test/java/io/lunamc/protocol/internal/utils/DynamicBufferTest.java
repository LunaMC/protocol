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

import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class DynamicBufferTest {

    private static final Random RANDOM = new Random();

    @Test
    public void testDynamicBuffer() {
        DynamicBuffer buffer = new DynamicBuffer();

        for (int size : new int[] { 4, 8, 42, 16, 15}) {
            byte[] data = new byte[size];
            RANDOM.nextBytes(data);
            byte[] result = buffer.readIntoBuffer(Unpooled.wrappedBuffer(data), size);

            Assert.assertTrue(result.length >= data.length);
            for (int i = 0; i < data.length; i++)
                Assert.assertEquals(data[i], result[i]);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDynamicBufferLimitedSize() {
        new DynamicBuffer(16).readIntoBuffer(Unpooled.wrappedBuffer(new byte[32]), 32);
    }
}
