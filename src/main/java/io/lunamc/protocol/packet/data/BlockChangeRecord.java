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

public interface BlockChangeRecord extends NetworkSerializable {

    short getHorizontalPosition();

    void setHorizontalPosition(short horizontalPosition);

    short getYCoordinate();

    void setYCoordinate(short yCoordinate);

    int getBlockId();

    void setBlockId(int blockId);

    @Override
    default void reset() {
        setHorizontalPosition((short) 0);
        setYCoordinate((short) 0);
        setBlockId(0);
    }
}
