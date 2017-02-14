/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler.compression;

import io.lunamc.protocol.ProtocolUtils;
import io.lunamc.protocol.handler.LengthLimitedFrameDecoder;
import io.lunamc.protocol.internal.utils.DynamicBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.compression.DecompressionException;

import java.util.List;
import java.util.zip.Inflater;

public class PacketDecompressor extends MessageToMessageDecoder<ByteBuf> {

    public static final String HANDLER_NAME = "decompressor";

    private boolean preferReadIntoBuffer;
    private final Inflater inflater;
    private final DynamicBuffer buffer;

    public PacketDecompressor() {
        this(LengthLimitedFrameDecoder.MAX_LENGTH, false);
    }

    public PacketDecompressor(int maxSize, boolean preferReadIntoBuffer) {
        this.inflater = new Inflater();
        this.buffer = new DynamicBuffer(maxSize);

        this.preferReadIntoBuffer = preferReadIntoBuffer;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int size = ProtocolUtils.readVarInt(msg);
        if (size == 0) {
            // Data is not compressed (size below threshold)
            out.add(msg.retainedSlice());
        } else if (size < 0) {
            throw new DecompressionException("Negative decompression size");
        } else {
            int bytes = msg.readableBytes();
            if (preferReadIntoBuffer || !msg.hasArray()) {
                inflater.setInput(buffer.readIntoBuffer(msg, bytes), 0, bytes);
            } else {
                inflater.setInput(msg.array(), msg.arrayOffset() + msg.readerIndex(), bytes);
                msg.readerIndex(msg.readerIndex() + bytes);
            }
            ByteBuf decompressed = ctx.alloc().heapBuffer(size);

            boolean erroneous = false;
            try {
                int bytesWritten = inflater.inflate(decompressed.array(), decompressed.arrayOffset(), size);
                decompressed.writerIndex(decompressed.writerIndex() + bytesWritten);
                if (!inflater.finished())
                    throw new DecompressionException("Invalid compressed data");
                out.add(decompressed);
            } catch (DecompressionException e){
                erroneous = true;
                throw e;
            } catch (Throwable e) {
                erroneous = true;
                throw new DecompressionException("An exception occurred while decompressing data", e);
            } finally {
                if (erroneous)
                    decompressed.release();
                inflater.reset();
            }
        }
    }
}
