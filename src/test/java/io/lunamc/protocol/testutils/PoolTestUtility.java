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

package io.lunamc.protocol.testutils;

import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class PoolTestUtility {

    private PoolTestUtility() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static <T> void testPoolCapability(Supplier<T> allocator) {
        List<T> objects = new ArrayList<>(1000);
        Set<Integer> hashes = new HashSet<>(1000);

        // Create 1000 objects
        for (int i = 0; i < 1000; i++) {
            T element = allocator.get();
            if (!(element instanceof ReferenceCounted))
                Assert.fail("Gathered element is not reference counted");
            Assert.assertEquals(1, ((ReferenceCounted) element).refCnt());
            Assert.assertTrue(hashes.add(System.identityHashCode(element)));
            objects.add(element);
        }

        // Release all previously created objects
        for (T object : objects)
            Assert.assertTrue(ReferenceCountUtil.release(object));

        // Get 10 objects which should be pooled
        for (int i = 0; i < 10; i++)
            Assert.assertTrue(hashes.contains(System.identityHashCode(allocator.get())));
    }
}
