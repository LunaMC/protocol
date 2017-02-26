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

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadSafeHolderTest {

    @Test
    public void testGet() {
        AtomicBoolean called = new AtomicBoolean();
        ThreadSafeHolder<String> holder = new ThreadSafeHolder<>(() -> {
            if (!called.compareAndSet(false, true))
                Assert.fail("Already called");
            return "Test";
        });

        for (int i = 0; i < 100; i++)
            Assert.assertEquals("Test", holder.get());
    }
}
