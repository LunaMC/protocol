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

import java.util.List;
import java.util.UUID;

public interface PlayerListUpdate {

    UUID getUuid();

    void setUuid(UUID uuid);

    List<Object> getPlayers();

    void setPlayers(List<Object> players);

    interface PlayerListAddPlayerAction {

        String getName();

        void setName(String name);

        List<? extends PlayerListAddPlayerActionPlayerProperty> getProperties();

        void setProperties(List<? extends PlayerListAddPlayerActionPlayerProperty> properties);

        int getGamemode();

        void setGamemode(int gamemode);

        int getPing();

        void setPing(int ping);

        String getDisplayName();

        void setDisplayName(String displayName);

        interface PlayerListAddPlayerActionPlayerProperty {

            String getName();

            void setName(String name);

            String getValue();

            void setValue(String value);

            String getSignature();

            void setSignature(String signature);
        }
    }

    interface PlayerListUpdateGamemodeAction {

        int getGamemode();

        void setGamemode(int gamemode);
    }

    interface PlayerListUpdateLatencyAction {

        int getPing();

        void setPing(int ping);
    }

    interface PlayerListUpdateDisplayNameAction {

        String getDisplayName();

        void setDisplayName(String displayName);
    }

    interface PlayerListRemovePlayerAction {}
}
