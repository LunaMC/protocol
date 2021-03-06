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

public interface ByteTuple3 extends NetworkSerializable {

    byte getByte1();

    void setByte1(byte byte1);

    byte getByte2();

    void setByte2(byte byte2);

    byte getByte3();

    void setByte3(byte byte3);

    @Override
    default void reset() {
        setByte1((byte) 0);
        setByte2((byte) 0);
        setByte3((byte) 0);
    }
}
