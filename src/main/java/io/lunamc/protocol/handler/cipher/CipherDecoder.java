/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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

public class CipherDecoder extends MessageToMessageDecoder<ByteBuf> {

    public static final String HANDLER_NAME = "decryptor";

    private final Cipher cipher;
    private final boolean preferReadIntoBuffer;
    private final DynamicBuffer buffer;

    public CipherDecoder(SecretKey key) {
        this(key, LengthLimitedFrameDecoder.MAX_LENGTH, false);
    }

    public CipherDecoder(SecretKey key, int maxLength, boolean preferReadIntoBuffer) {
        cipher = CipherUtils.createInitialized(Cipher.DECRYPT_MODE, key);
        this.preferReadIntoBuffer = preferReadIntoBuffer;

        buffer = new DynamicBuffer(maxLength);
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
