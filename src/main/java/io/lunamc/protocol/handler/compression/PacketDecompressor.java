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
/**
 * Decompresses incoming {@link ByteBuf}s.
 * <p>
 * Until {@code preferReadIntoBuffer} is set to {@code true} this handler will tries to access the incoming buffers
 * array directly. When {@code preferReadIntoBuffer} is set to {@code true} or {@link ByteBuf#hasArray()} returns
 * {@code false} the content will be read into a cached buffer baked by {@link DynamicBuffer}.
 */
public class PacketDecompressor extends MessageToMessageDecoder<ByteBuf> {

    /**
     * A name for this handler.
     */
    public static final String HANDLER_NAME = "decompressor";

    private boolean preferReadIntoBuffer;
    private final Inflater inflater;
    private final DynamicBuffer buffer;

    /**
     * Constructs a new {@link PacketDecompressor} with a default max size which won't force reading an incoming
     * message into the internal buffer.
     */
    public PacketDecompressor() {
        this(LengthLimitedFrameDecoder.MAX_LENGTH, false);
    }

    /**
     * Constructs a new {@link PacketDecompressor} with the specified maximum packet size. Additionally by setting
     * {@code preferReadIntoBuffer} to {@code true} the handler can be forced to read the incoming data into a buffer
     * instead of accessing the buffer array by {@link ByteBuf#array()} directly.
     *
     * @param maxSize The maximum (compressed) size for an incoming packet
     * @param preferReadIntoBuffer {@code true} if the incoming {@link ByteBuf}s array should <strong>not</strong> be
     *                             accessed directly even if it is available. Otherwise the buffers array is used if
     *                             available
     */
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
