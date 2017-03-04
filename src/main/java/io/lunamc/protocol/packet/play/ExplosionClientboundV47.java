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
import io.lunamc.protocol.packet.data.ByteTuple3;
import io.lunamc.protocol.utils.PacketUtils;

import java.util.List;

public interface ExplosionClientboundV47 extends Packet {

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getZ();

    void setZ(float z);

    float getRadius();

    void setRadius(float radius);

    List<ByteTuple3> getRecords();

    void setRecords(List<ByteTuple3> records);

    float getPlayerMotionX();

    void setPlayerMotionX(float playerMotionX);

    float getPlayerMotionY();

    void setPlayerMotionY(float playerMotionY);

    float getPlayerMotionZ();

    void setPlayerMotionZ(float playerMotionZ);

    @Override
    default void reset() {
        setX(0);
        setY(0);
        setZ(0);
        setRadius(0);
        PacketUtils.resetAll(getRecords());
        setRecords(null);
        setPlayerMotionX(0);
        setPlayerMotionY(0);
        setPlayerMotionZ(0);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return ExplosionClientboundV47.class;
    }
}
