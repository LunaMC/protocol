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

import io.netty.buffer.ByteBuf;

import java.util.Objects;

/**
 * Dynamically expanding, reusable byte buffer.
 */
public class DynamicBuffer {

    private final int maxLength;
    private byte[] buffer;

    /**
     * Constructs a new instance with a maximum length of {@link Integer#MAX_VALUE}.
     */
    public DynamicBuffer() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Constructs a new instance with the given maximum length.
     *
     * @param maxLength The maximum length of bytes which can be read
     * @throws IllegalArgumentException Will be thrown if {@code maxLength} is smaller than {@code 0}
     */
    public DynamicBuffer(int maxLength) {
        if (maxLength < 0)
            throw new IllegalArgumentException("maxLength must be greater or equal 0");

        this.maxLength = maxLength;
    }

    /**
     * Reads bytes from a {@link ByteBuf} into this buffer.
     *
     * @param msg The source {@link ByteBuf}
     * @param bytes The amount of bytes which should be read
     * @throws NullPointerException Will be thrown if {@code msg} is null
     * @throws IllegalArgumentException Will be thrown if {@code bytes} is smaller than {@code 0}
     * @return The buffer array
     */
    public byte[] readIntoBuffer(ByteBuf msg, int bytes) {
        Objects.requireNonNull(msg, "msg must not be null");
        if (bytes < 0)
            throw new IllegalArgumentException("bytes must be greater or equal 0");

        if (buffer == null || buffer.length < bytes) {
            if (bytes > maxLength)
                throw new IndexOutOfBoundsException("Length exceeded");
            buffer = new byte[bytes];
        }
        msg.readBytes(buffer, 0, bytes);
        return buffer;
    }
}
