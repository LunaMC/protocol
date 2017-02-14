/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
