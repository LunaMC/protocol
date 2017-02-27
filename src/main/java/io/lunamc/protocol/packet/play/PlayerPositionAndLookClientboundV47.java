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

public interface PlayerPositionAndLookClientboundV47 extends Packet {

    int X_RELATIVE = 0b00000001;
    int Y_RELATIVE = 0b00000010;
    int Z_RELATIVE = 0b00000100;
    int Y_ROT_RELATIVE = 0b00001000;
    int X_ROT_RELATIVE = 0b00010000;

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getZ();

    void setZ(double z);

    float getYaw();

    void setYaw(float yaw);

    float getPitch();

    void setPitch(float pitch);

    byte getFlags();

    void setFlags(byte flags);

    @Override
    default Class<? extends Packet> getModelClass() {
        return PlayerPositionAndLookClientboundV47.class;
    }
}
