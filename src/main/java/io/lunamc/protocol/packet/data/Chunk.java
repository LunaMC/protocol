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

package io.lunamc.protocol.packet.data;

import io.lunamc.protocol.packet.NetworkSerializable;

// See http://wiki.vg/index.php?title=SMP_Map_Format&oldid=7164#Data
public interface Chunk extends NetworkSerializable {

    int[] getBlockTypes();

    void setBlockTypes(int[] blockTypes);

    int getBitsOfDataPerBlock();

    void setBitsOfDataPerBlock(int bitsOfDataPerBlock);

    byte[] getBlockLightData();

    void setBlockLightData(byte[] blockLightData);

    byte[] getSkylightData();

    void setSkylightData(byte[] skylightData);

    byte[] getBiomes();

    void setBiomes(byte[] biomes);

    @Override
    default void reset() {
        setBlockTypes(null);
        setBitsOfDataPerBlock(0);
        setBlockLightData(null);
        setSkylightData(null);
        setBiomes(null);
    }
}
