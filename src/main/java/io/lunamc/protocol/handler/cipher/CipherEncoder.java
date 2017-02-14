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

public class CipherEncoder extends MessageToByteEncoder<ByteBuf> {

    public static final String HANDLER_NAME = "encryptor";

    private final DynamicBuffer buffer = new DynamicBuffer();
    private final Cipher cipher;
    private final boolean preferReadIntoBuffer;

    public CipherEncoder(SecretKey key) {
        this(key, false);
    }

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
