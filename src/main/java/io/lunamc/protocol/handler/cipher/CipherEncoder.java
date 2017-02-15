/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler.cipher;

import io.lunamc.protocol.internal.utils.DynamicBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * Encrypts outgoing {@link ByteBuf}s with a {@link SecretKey}.
 * <p>
 * Until {@code preferReadIntoBuffer} is set to {@code true} this handler will tries to access the message buffers
 * array directly. When {@code preferReadIntoBuffer} is set to {@code true} or {@link ByteBuf#hasArray()} returns
 * {@code false} the content will be read into a cached buffer baked by {@link DynamicBuffer}.
 */
public class CipherEncoder extends MessageToByteEncoder<ByteBuf> {

    /**
     * A name for this handler.
     */
    public static final String HANDLER_NAME = "encryptor";

    private final DynamicBuffer buffer = new DynamicBuffer();
    private final Cipher cipher;
    private final boolean preferReadIntoBuffer;

    /**
     * Constructs a new {@link CipherEncoder} with the specified {@link SecretKey} which won't force reading into the
     * internal buffer.
     *
     * @param key The {@link SecretKey} used for encryption
     */
    public CipherEncoder(SecretKey key) {
        this(key, false);
    }

    /**
     * Constructs a new {@link CipherEncoder} with the specified {@link SecretKey}. Additionally by setting
     * {@code preferReadIntoBuffer} to {@code true} the handler can be forced to read the data into a buffer
     * instead of accessing the buffer array by {@link ByteBuf#array()} directly.
     *
     * @param key The {@link SecretKey} used for encryption
     * @param preferReadIntoBuffer {@code true} if the {@link ByteBuf}s array should <strong>not</strong> be accessed
     *                             directly even if it is available. Otherwise the buffers array is used if available
     */
    public CipherEncoder(SecretKey key, boolean preferReadIntoBuffer) {
        super(false);

        this.preferReadIntoBuffer = preferReadIntoBuffer;

        cipher = CipherUtils.createInitialized(Cipher.ENCRYPT_MODE, key);
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf msg, boolean preferDirect) throws Exception {
        int expectedSize = cipher.getOutputSize(msg.readableBytes());
        return preferDirect ? ctx.alloc().directBuffer(expectedSize) : ctx.alloc().heapBuffer(expectedSize);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int readableBytes = msg.readableBytes();
        int written;
        if (preferReadIntoBuffer || !msg.hasArray()) {
            written = cipher.update(buffer.readIntoBuffer(msg, readableBytes), 0, readableBytes, out.array(), out.arrayOffset());
        } else {
            written = cipher.update(msg.array(), msg.readerIndex(), readableBytes, out.array(), out.arrayOffset());
            msg.readerIndex(msg.readerIndex() + readableBytes);
        }
        out.writerIndex(out.writerIndex() + written);
    }
}
