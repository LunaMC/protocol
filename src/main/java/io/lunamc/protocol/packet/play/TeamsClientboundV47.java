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

public interface TeamsClientboundV47 extends Packet {

    String getTeamName();

    void setTeamName(String teamName);

    byte getMode();

    void setMode(byte mode);

    String getTeamDisplayName();

    void setTeamDisplayName(String teamDisplayName);

    String getTeamPrefix();

    void setTeamPrefix(String teamPrefix);

    byte getFriendlyFire();

    void setFriendlyFire(byte friendlyFire);

    String getNameTagVisibility();

    void setNameTagVisibility(String nameTagVisibility);

    byte getColor();

    void setColor(byte color);

    String[] getPlayers();

    void setPlayers(String[] players);

    @Override
    default Class<? extends Packet> getModelClass() {
        return TeamsClientboundV47.class;
    }
}
