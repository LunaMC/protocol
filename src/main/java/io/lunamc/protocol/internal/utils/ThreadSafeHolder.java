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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class ThreadSafeHolder<T> {

    private final AtomicReference<T> value = new AtomicReference<>();
    private Supplier<T> supplier;

    public ThreadSafeHolder(Supplier<T> supplier) {
        this.supplier = Objects.requireNonNull(supplier, "supplier must not be null");
    }

    public T get() {
        T val = value.get();
        if (val == null) {
            synchronized(value) {
                val = value.get();
                if (val == null) {
                    val = Objects.requireNonNull(supplier.get());
                    value.set(val);
                    supplier = null;
                }
            }
        }
        return val;
    }
}
