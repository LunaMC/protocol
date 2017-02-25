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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * A {@link MessageToByteEncoder} which prepends each outgoing {@link ByteBuf} with its length as a {@code VarInt}.
 * <p>
 * This encoder is stateless and can be shared across multiple channels.
 */
@ChannelHandler.Sharable
public class PacketLengthPrepender extends MessageToByteEncoder<ByteBuf> {

    /**
     * A name for this handler.
     */
    public static final String HANDLER_NAME = "length-prepender";

    /**
     * A singleton instance.
     */
    public static final PacketLengthPrepender INSTANCE = new PacketLengthPrepender();

    /**
     * Constructs a new {@link PacketLengthPrepender}
     */
    protected PacketLengthPrepender() {
        super(ByteBuf.class);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        ProtocolUtils.writeVarInt(out, msg.readableBytes());
        out.writeBytes(msg);
    }
}
