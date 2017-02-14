/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler.cipher;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Internal utilities for {@link CipherDecoder} and {@link CipherEncoder}.
 */
class CipherUtils {

    private static final String TRANSFORMATION = "AES/CFB8/NoPadding";

    private CipherUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

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

    private static Cipher create() {
        try {
            return Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
