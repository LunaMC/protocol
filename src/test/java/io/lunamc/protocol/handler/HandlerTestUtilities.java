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
