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

package io.lunamc.protocol.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

/**
 * A frame decoder for decoding incoming packets. The length of each frame is limited to 32,767 byte as in the vanilla
 * Minecraft server.
 */
public class LengthLimitedFrameDecoder extends ByteToMessageDecoder {

    /**
     * A name for this handler.
     */
    public static final String HANDLER_NAME = "frame-decoder";

    /**
     * The max length of a packet which can be decoded by this decoder.
     */
    public static final int MAX_LENGTH = 32_767;

    // ToDo: Store the length once the same value was read two times?

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        int length = readVarInt(in);

        // Check length
        if (length < 0)
            throw new CorruptedFrameException("Negative packet length");
        else if (length > MAX_LENGTH)
            throw new CorruptedFrameException("Packet exceed maximum length of " + MAX_LENGTH + " bytes");

        if (length > 0 && length <= in.readableBytes())
            out.add(in.readRetainedSlice(length));
        else
            in.resetReaderIndex();
    }

    // The maximum length can be represented by only 3 bytes so the VarInt can be decoded a little bit more efficient.
    private static int readVarInt(ByteBuf in) {
        // First byte
        if (!in.isReadable())
            return 0;
        byte b = in.readByte();
        if ((b & 0b10000000) == 0)
            return b & 0b01111111;

        // Second byte
        if (!in.isReadable())
            return 0;
        int result = b & 0b01111111;
        b = in.readByte();
        result |= (b & 0b01111111) << 7;
        if ((b & 0b10000000) == 0)
            return result;

        // Third byte
        if (!in.isReadable())
            return 0;
        b = in.readByte();
        result |= (b & 0b01111111) << 14;
        if ((b & 0b10000000) == 0)
            return result;

        // A packet is limited to 32,767 bytes which can be represented by 3 bytes. At this point the length has already
        // exceeded this limit
        throw new CorruptedFrameException("Packet length exceed maximum of 3 bytes or an invalid VarInt was sent");
    }
}
