/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.function.Consumer;

public class HandlerTestUtilities {

    public static ChannelHandlerContext mockCtx(ByteBuf targetBuffer) {
        return mockCtx(targetBuffer, null);
    }

    public static ChannelHandlerContext mockCtx(ByteBuf targetBuffer, Consumer<Object> readCallback) {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        Mockito.when(ctx.alloc()).thenReturn(ByteBufAllocator.DEFAULT);
        Answer<ChannelPromise> writeAnswer = invocationOnMock -> {
            targetBuffer.writeBytes(invocationOnMock.<ByteBuf>getArgument(0));
            return mockChannelPromise();
        };
        Mockito.when(ctx.write(Mockito.any())).then(writeAnswer);
        Mockito.when(ctx.write(Mockito.any(), Mockito.any())).then(writeAnswer);
        Mockito.when(ctx.writeAndFlush(Mockito.any())).then(writeAnswer);
        Mockito.when(ctx.writeAndFlush(Mockito.any(), Mockito.any())).then(writeAnswer);
        if (readCallback != null) {
            Mockito.when(ctx.fireChannelRead(Mockito.any())).then(invocation -> {
                readCallback.accept(invocation.getArgument(0));
                return ctx;
            });
        } else {
            Mockito.when(ctx.fireChannelRead(Mockito.any())).thenThrow(UnsupportedOperationException.class);
        }
        return ctx;
    }

    public static ChannelPromise mockChannelPromise() {
        return Mockito.mock(ChannelPromise.class);
    }
}
