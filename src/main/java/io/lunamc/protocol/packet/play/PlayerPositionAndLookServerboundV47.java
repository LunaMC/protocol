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

public interface PlayerPositionAndLookServerboundV47 extends Packet {

    double getX();

    void setX(double x);

    double getFeetY();

    void setFeetY(double feetY);

    double getZ();

    void setZ(double z);

    float getYaw();

    void setYaw(float yaw);

    float getPitch();

    void setPitch(float pitch);

    boolean isOnGround();

    void setOnGround(boolean onGround);

    @Override
    default void reset() {
        setX(0);
        setFeetY(0);
        setZ(0);
        setYaw(0);
        setPitch(0);
        setOnGround(false);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return PlayerPositionAndLookServerboundV47.class;
    }
}
