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

import io.netty.util.Recycler;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class RecyclerUtils {

    private RecyclerUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static <T> T get(Supplier<Recycler<T>> supplier) {
        return supplier.get().get();
    }

    public static <T> Supplier<Recycler<T>> createLazy(Function<Recycler.Handle<T>, T> constructor) {
        return () -> create(constructor);
    }

    public static <T> Recycler<T> create(Function<Recycler.Handle<T>, T> constructor) {
        Objects.requireNonNull(constructor, "constructor must not be null");
        return new Recycler<T>() {
            @Override
            protected T newObject(Handle<T> handle) {
                return constructor.apply(handle);
            }
        };
    }
}
