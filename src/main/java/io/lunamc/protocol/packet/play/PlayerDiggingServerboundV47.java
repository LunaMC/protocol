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

public interface PlayerDiggingServerboundV47 extends Packet {

    byte STATUS_STARTED_DIGGING = 0;
    byte STATUS_CANCELLED_DIGGING = 1;
    byte STATUS_FINISHED_DIGGING = 2;
    byte STATUS_DROP_ITEM_STACK = 3;
    byte STATUS_DROP_ITEM = 4;
    byte STATUS_SHOOT_ARROW = 5;
    byte STATUS_FINISH_EATING = 5;

    byte FACE_BOTTOM = 0;
    byte FACE_TOP = 1;
    byte FACE_NORTH = 2;
    byte FACE_SOUTH = 3;
    byte FACE_WEST = 4;
    byte FACE_EAST = 5;

    byte getStatus();

    void setStatus(byte status);

    long getLocation();

    void setLocation(long location);

    byte getFace();

    void setFace(byte face);

    @Override
    default void reset() {
        setStatus((byte) 0);
        setLocation(0);
        setFace((byte) 0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return PlayerDiggingServerboundV47.class;
    }
}
