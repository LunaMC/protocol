/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler.compression;

import io.lunamc.protocol.ProtocolUtils;
import io.lunamc.protocol.internal.utils.DynamicBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.zip.Deflater;

public class PacketCompressor extends MessageToByteEncoder<ByteBuf> {

    public static final String HANDLER_NAME = "compressor";
    // See recommendation of http://www.bolet.org/~pornin/deflate-flush.html > Full Flush:
    // A full flush degrades the compression efficiency since it removes sequence sharing opportunities for the next
    // 32 kB of data; however, this degradation is very slight if full flushes are applied only rarely with regards to
    // the 32 kB window size, e.g. every 1 MB or so. However I'm not sure if zlib bets on 1 MB = 1,024 * 1,024 bytes or
    // 1 MB = 1,000 * 1,000 bytes so let's using the smaller one here.
    //
    // However this is completely unnecessary since Minecraft will have some other serious issues than an inefficient
    // compression when it sends >1MB by a single packet.
    private static final int MAX_FLUSH_LENGTH = 1_000 * 1_000;

    private final DynamicBuffer buffer = new DynamicBuffer();
    private final int threshold;
    private final Deflater deflater;
    private boolean preferReadIntoBuffer;

    public PacketCompressor(int threshold, int compression) {
        this(threshold, compression, false);
    }

    public PacketCompressor(int threshold, int compression, boolean preferReadIntoBuffer) {
        super(false);

        if (threshold < 0)
            throw new IllegalArgumentException("threshold must be greater or equal 0");
        this.threshold = threshold;
        this.preferReadIntoBuffer = preferReadIntoBuffer;

        if (compression != Deflater.DEFAULT_COMPRESSION && (compression > Deflater.BEST_COMPRESSION || compression < Deflater.BEST_SPEED))
            throw new IllegalArgumentException("compression must be " + Deflater.DEFAULT_COMPRESSION + " or between " + Deflater.BEST_SPEED + " and " + Deflater.BEST_COMPRESSION);
        deflater = new Deflater(compression);
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf msg, boolean preferDirect) throws Exception {
        int dataSize = msg.readableBytes();
        // According to http://www.zlib.net/zlib_tech.html the overhead is 6 bytes for the entire stream plus 5 bytes
        // per started 16KB block.
        // The buffer must be able to store this overhead, a VarInt (max. 5 bytes) and at least the size of the
        // original data.
        int bufferSize = 6 + (5 * ((dataSize / 16_000) + 1)) + 5 + dataSize;
        return preferDirect ? ctx.alloc().directBuffer(bufferSize) : ctx.alloc().heapBuffer(bufferSize);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        deflater.end();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int packetSize = msg.readableBytes();
        if (packetSize >= threshold) {
            ProtocolUtils.writeVarInt(out, packetSize);
            writeCompressedBytes(msg, out);
        } else {
            ProtocolUtils.writeVarInt(out, 0);
            out.writeBytes(msg);
        }
    }

    private void writeCompressedBytes(ByteBuf msg, ByteBuf out) {
        int bytes = msg.readableBytes();
        if (preferReadIntoBuffer || !msg.hasArray()) {
            deflater.setInput(buffer.readIntoBuffer(msg, bytes), 0, bytes);
        } else {
            deflater.setInput(msg.array(), msg.arrayOffset() + msg.readerIndex(), bytes);
            msg.readerIndex(msg.readerIndex() + bytes);
        }

        try {
            deflater.finish();

            while (!deflater.finished()) {
                int bytesWritten = deflater.deflate(out.array(), out.arrayOffset() + out.writerIndex(), MAX_FLUSH_LENGTH, Deflater.FULL_FLUSH);
                out.writerIndex(out.writerIndex() + bytesWritten);
            }
        } finally {
            deflater.reset();
        }
    }
}
