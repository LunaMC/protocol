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

import io.lunamc.protocol.handler.LengthLimitedFrameDecoder;
import io.lunamc.protocol.internal.utils.DynamicBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.List;
import java.util.Objects;

/**
 * Decrypts incoming {@link ByteBuf}s.
 * <p>
 * Until {@code preferReadIntoBuffer} is set to {@code true} this handler will tries to access the incoming buffers
 * array directly. When {@code preferReadIntoBuffer} is set to {@code true} or {@link ByteBuf#hasArray()} returns
 * {@code false} the content will be read into a cached buffer baked by {@link DynamicBuffer}.
 */
public class CipherDecoder extends MessageToMessageDecoder<ByteBuf> {

    /**
     * A name for this handler.
     */
    public static final String HANDLER_NAME = "decryptor";

    private final Cipher cipher;
    private final boolean preferReadIntoBuffer;
    private final DynamicBuffer buffer;

    /**
     * Constructs a new {@link CipherDecoder} with the specified {@link SecretKey} and a default maximum size which
     * won't force reading an incoming message into the internal buffer.
     *
     * @param key The {@link SecretKey} used for encryption
     * @throws NullPointerException Will be thrown if {@code key} is {@code null}
     */
    public CipherDecoder(SecretKey key) {
        this(key, LengthLimitedFrameDecoder.MAX_LENGTH, false);
    }

    /**
     * Constructs a new {@link CipherDecoder} with the specified {@link SecretKey}. Additionally by setting
     * {@code preferReadIntoBuffer} to {@code true} the handler can be forced to copy incoming data into a buffer
     * instead of accessing the buffer array by {@link ByteBuf#array()} directly.
     *
     * @param key The {@link SecretKey} used for encryption
     * @param maxSize The maximum (compressed) size for an incoming packet
     * @param preferReadIntoBuffer {@code true} if the incoming {@link ByteBuf}s array should <strong>not</strong> be
     *                             accessed directly even if it is available. Otherwise the buffers array is used if
     *                             available
     * @throws NullPointerException Will be thrown if {@code key} is {@code null}
     */
    public CipherDecoder(SecretKey key, int maxSize, boolean preferReadIntoBuffer) {
        Objects.requireNonNull(key, "key must not be null");

        this.preferReadIntoBuffer = preferReadIntoBuffer;

        cipher = CipherUtils.createInitialized(Cipher.DECRYPT_MODE, key);
        buffer = new DynamicBuffer(maxSize);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int readableBytes = msg.readableBytes();
        ByteBuf output = ctx.alloc().heapBuffer(readableBytes);
        try {
            int bytes = msg.readableBytes();
            int written;
            if (preferReadIntoBuffer || !msg.hasArray()) {
                written = cipher.update(buffer.readIntoBuffer(msg, bytes), 0, bytes, output.array(), output.arrayOffset());
            } else {
                written = cipher.update(msg.array(), msg.arrayOffset() + msg.readerIndex(), bytes, output.array(), output.arrayOffset());
                msg.readerIndex(msg.readerIndex() + bytes);
            }
            output.writerIndex(written);
            out.add(output);
        } catch (Exception e) {
            output.release();
            throw e;
        }
    }
}
