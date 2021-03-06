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

public interface BlockActionClientboundV47 extends Packet {

    long getLocation();

    void setLocation(long location);

    short getByte1();

    void setByte1(short byte1);

    short getByte2();

    void setByte2(short byte2);

    int getBlockType();

    void setBlockType(int blockType);

    @Override
    default void reset() {
        setLocation(0);
        setByte1((short) 0);
        setByte2((short) 0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return BlockActionClientboundV47.class;
    }
}
