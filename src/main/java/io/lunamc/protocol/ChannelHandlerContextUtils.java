/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol;

import io.netty.channel.ChannelHandlerContext;

import java.util.Objects;

/**
 * Utilities for dealing with Netty's {@link ChannelHandlerContext}.
 */
public class ChannelHandlerContextUtils {

    private ChannelHandlerContextUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    /**
     * Returns printable information about the connected client.
     *
     * @param ctx The {@link ChannelHandlerContext}
     * @return A string represents the connected client
     */
    public static String client(ChannelHandlerContext ctx) {
        Objects.requireNonNull(ctx, "ctx must not be null");

        return ctx.channel().remoteAddress().toString();
    }
}
