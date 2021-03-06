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

package io.lunamc.protocol.packet.play;

import io.lunamc.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;

public interface PluginMessageMultiboundV47 extends Packet {

    String getChannel();

    void setChannel(String channel);

    ByteBuf getData();

    void setData(ByteBuf data);

    @Override
    default void reset() {
        setChannel(null);
        ReferenceCountUtil.release(getData());
        setData(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return PluginMessageMultiboundV47.class;
    }
}
