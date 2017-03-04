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
import io.lunamc.protocol.packet.data.SlotData;

public interface PlayerBlockPlacementServerboundV47 extends Packet {

    byte FACE_BOTTOM = 0;
    byte FACE_TOP = 1;
    byte FACE_NORTH = 2;
    byte FACE_SOUTH = 3;
    byte FACE_WEST = 4;
    byte FACE_EAST = 5;

    long getLocation();

    void setLocation(long location);

    byte getFace();

    void setFace(byte face);

    SlotData getHeldItem();

    void setHeldItem(SlotData heldItem);

    byte getCursorPositionX();

    void setCursorPositionX(byte cursorPositionX);

    byte getCursorPositionY();

    void setCursorPositionY(byte cursorPositionY);

    byte getCursorPositionZ();

    void setCursorPositionZ(byte cursorPositionZ);

    @Override
    default Class<? extends Packet> getModelClass() {
        return PlayerBlockPlacementServerboundV47.class;
    }
}
