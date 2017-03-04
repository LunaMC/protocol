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

package io.lunamc.protocol.utils;

import io.lunamc.protocol.packet.NetworkSerializable;

public class PacketUtils {

    private PacketUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static void reset(Object item) {
        if (item instanceof NetworkSerializable)
            ((NetworkSerializable) item).reset();
    }

    public static void resetAll(Iterable<? extends NetworkSerializable> iterable) {
        if (iterable != null)
            iterable.forEach(PacketUtils::reset);
    }

    public static <T extends NetworkSerializable> void resetAll(T[] array) {
        if (array != null) {
            for (T item : array)
                reset(item);
        }
    }
}
