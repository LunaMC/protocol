/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler;

import io.lunamc.protocol.ChannelHandlerContextUtils;
import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * An inbound handler for handling incoming packets.
 */
public abstract class PacketInboundHandlerAdapter extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacketInboundHandlerAdapter.class);
    private static final Marker MARKER_PROTOCOL = MarkerFactory.getMarker("PROTOCOL");
    private static final Marker MARKER_CONNECTION = MarkerFactory.getMarker("CONNECTION");

    /**
     * Constructs a new {@link PacketInboundHandlerAdapter}.
     */
    public PacketInboundHandlerAdapter() {
        super(ByteBuf.class, true);
    }

    @Override
    protected final void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        int packetId = ProtocolUtils.readVarInt(msg);
        ByteBuf content = msg.slice();
        handlePacket(ctx, packetId, content);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (!ctx.channel().isActive())
            return;

        if (cause instanceof IndexOutOfBoundsException)
            LOGGER.warn(MARKER_PROTOCOL, "Reached unexpected end while parsing a packet from {}", ChannelHandlerContextUtils.client(ctx));
        else if (cause instanceof ReadTimeoutException)
            LOGGER.info(MARKER_CONNECTION, "{} read timed out", ChannelHandlerContextUtils.client(ctx));
        else
            LOGGER.error(MARKER_CONNECTION, "{} caused an exception", ChannelHandlerContextUtils.client(ctx), cause);
        ctx.close();
    }

    /**
     * Handles an incoming packet.
     *
     * @param ctx The {@link ChannelHandlerContext} on which the packet was received
     * @param packetId The packet id
     * @param content The content of the packet
     * @throws Exception thrown if an error occurred while handling packet
     */
    protected abstract void handlePacket(ChannelHandlerContext ctx, int packetId, ByteBuf content) throws Exception;
}
