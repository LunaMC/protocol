/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler;

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class PacketLengthPrepender extends MessageToByteEncoder<ByteBuf> {

    public static final String HANDLER_NAME = "length-prepender";
    public static final PacketLengthPrepender INSTANCE = new PacketLengthPrepender();

    public PacketLengthPrepender() {
        super(ByteBuf.class);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        ProtocolUtils.writeVarInt(out, msg.readableBytes());
        out.writeBytes(msg);
    }
}
