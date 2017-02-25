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
