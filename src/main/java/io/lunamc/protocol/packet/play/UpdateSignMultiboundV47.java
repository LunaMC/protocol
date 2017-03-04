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

public interface UpdateSignMultiboundV47 extends Packet {

    long getLocation();

    void setLocation(long location);

    String getLine1();

    void setLine1(String line1);

    String getLine2();

    void setLine2(String line2);

    String getLine3();

    void setLine3(String line3);

    String getLine4();

    void setLine4(String line4);

    @Override
    default Class<? extends Packet> getModelClass() {
        return UpdateSignMultiboundV47.class;
    }
}
