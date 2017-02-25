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

package io.lunamc.protocol.handler.cipher;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;

/**
 * Internal utilities for {@link CipherDecoder} and {@link CipherEncoder}.
 */
class CipherUtils {

    private static final String TRANSFORMATION = "AES/CFB8/NoPadding";

    private CipherUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    /**
     * Creates an {@link Cipher} (AES, CFB8, no padding) and initialize it with the given {@code key}.
     *
     * @see #create()
     * @see Cipher#init(int, Key, AlgorithmParameterSpec)
     * @see IvParameterSpec#IvParameterSpec(byte[])
     * @param mode {@link Cipher#DECRYPT_MODE} for decryption or {@link Cipher#ENCRYPT_MODE} for encryption
     * @param key The {@link Key}
     * @return The created and initialized {@link Cipher}
     */
    static Cipher createInitialized(int mode, Key key) {
        Objects.requireNonNull(key, "key must not be null");

        Cipher cipher = create();
        try {
            cipher.init(mode, key, new IvParameterSpec(key.getEncoded()));
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
        return cipher;
    }

    /**
     * Creates an {@link Cipher} (AES, CFB8, no padding).
     *
     * @return The created {@link Cipher}
     */
    private static Cipher create() {
        try {
            return Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
