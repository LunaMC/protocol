/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol;

/**
 * Thrown to indicate a malformed data stream.
 */
public class MalformedDataException extends RuntimeException {

    /**
     * Constructs a {@link MalformedDataException} with no detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     */
    public MalformedDataException() {
    }

    /**
     * Constructs an {@link MalformedDataException} with the specified detail message. The cause is not initialized,
     * and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message The detail message.
     */
    public MalformedDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link MalformedDataException} with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method) (a {@code null}
     *              value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public MalformedDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
