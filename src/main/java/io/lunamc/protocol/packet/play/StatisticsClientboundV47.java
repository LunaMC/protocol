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
import io.lunamc.protocol.packet.data.Statistic;
import io.lunamc.protocol.utils.PacketUtils;

import java.util.List;

public interface StatisticsClientboundV47 extends Packet {

    List<Statistic> getStatistics();

    void setStatistics(List<Statistic> statistics);

    @Override
    default void reset() {
        PacketUtils.resetAll(getStatistics());
        setStatistics(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return StatisticsClientboundV47.class;
    }
}
