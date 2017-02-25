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
