/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.testutils;

import java.util.Random;

public class DataUtils {

    public static final Random RANDOM = new Random();

    private DataUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static byte[] createRandomByteArray(int length) {
        byte[] array = new byte[length];
        RANDOM.nextBytes(array);
        return array;
    }
}
