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
import io.lunamc.protocol.packet.data.BlockChangeRecord;
import io.lunamc.protocol.utils.PacketUtils;

import java.util.List;

public interface MultiBlockChangeClientboundV47 extends Packet {

    int getChunkX();

    void setChunkX(int chunkX);

    int getChunkZ();

    void setChunkZ(int chunkZ);

    List<BlockChangeRecord> getRecords();

    void setRecords(List<BlockChangeRecord> records);

    @Override
    default void reset() {
        setChunkX(0);
        setChunkZ(0);
        PacketUtils.reset(getRecords());
        setRecords(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return MultiBlockChangeClientboundV47.class;
    }
}
