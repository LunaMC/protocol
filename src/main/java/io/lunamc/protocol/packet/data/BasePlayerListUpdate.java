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
import java.util.Objects;
import java.util.UUID;

class BasePlayerListUpdate implements PlayerListUpdate {

    private UUID uuid;
    private List<Object> players;

    BasePlayerListUpdate() {
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public List<Object> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<Object> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PlayerListUpdate))
            return false;
        PlayerListUpdate that = (PlayerListUpdate) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getPlayers(), that.getPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getPlayers());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{uuid=" + getUuid() + ", players=" + getPlayers() + '}';
    }

    static class BasePlayerListAddPlayerAction implements PlayerListAddPlayerAction {

        private String name;
        private List<? extends PlayerListAddPlayerActionPlayerProperty> properties;
        private int gamemode;
        private int ping;
        private String displayName;

        BasePlayerListAddPlayerAction() {
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public List<? extends PlayerListAddPlayerActionPlayerProperty> getProperties() {
            return properties;
        }

        @Override
        public void setProperties(List<? extends PlayerListAddPlayerActionPlayerProperty> properties) {
            this.properties = properties;
        }

        @Override
        public int getGamemode() {
            return gamemode;
        }

        @Override
        public void setGamemode(int gamemode) {
            this.gamemode = gamemode;
        }

        @Override
        public int getPing() {
            return ping;
        }

        @Override
        public void setPing(int ping) {
            this.ping = ping;
        }

        @Override
        public String getDisplayName() {
            return displayName;
        }

        @Override
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof PlayerListAddPlayerAction))
                return false;
            PlayerListAddPlayerAction that = (PlayerListAddPlayerAction) o;
            return getGamemode() == that.getGamemode() &&
                    getPing() == that.getPing() &&
                    Objects.equals(getName(), that.getName()) &&
                    Objects.equals(getProperties(), that.getProperties()) &&
                    Objects.equals(getDisplayName(), that.getDisplayName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getProperties(), getGamemode(), getPing(), getDisplayName());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{name=\"" + getName() +
                    "\", properties=" + getProperties() +
                    ", gamemode=" + getGamemode() +
                    ", ping=" + getPing() +
                    ", displayName=\"" + getDisplayName() +
                    "\"}";
        }

        static class BasePlayerListAddPlayerActionPlayerProperty implements PlayerListAddPlayerActionPlayerProperty {

            private String name;
            private String value;
            private String signature;

            BasePlayerListAddPlayerActionPlayerProperty() {
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public String getSignature() {
                return signature;
            }

            @Override
            public void setSignature(String signature) {
                this.signature = signature;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o)
                    return true;
                if (!(o instanceof PlayerListAddPlayerActionPlayerProperty))
                    return false;
                PlayerListAddPlayerActionPlayerProperty that = (PlayerListAddPlayerActionPlayerProperty) o;
                return Objects.equals(getName(), that.getName()) &&
                        Objects.equals(getValue(), that.getValue()) &&
                        Objects.equals(getSignature(), that.getSignature());
            }

            @Override
            public int hashCode() {
                return Objects.hash(getName(), getValue(), getSignature());
            }

            @Override
            public String toString() {
                return getClass().getName() + "{name=\"" + getName() +
                        "\", value=\"" + getValue() +
                        "\", signature=\"" + getSignature() +
                        "\"}";
            }
        }
    }

    static class BasePlayerListUpdateGamemodeAction implements PlayerListUpdateGamemodeAction {

        private int gamemode;

        BasePlayerListUpdateGamemodeAction() {
        }

        @Override
        public int getGamemode() {
            return gamemode;
        }

        @Override
        public void setGamemode(int gamemode) {
            this.gamemode = gamemode;
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof PlayerListUpdateGamemodeAction && getGamemode() == ((PlayerListUpdateGamemodeAction) o).getGamemode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getGamemode());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{gamemode=" + getGamemode() + '}';
        }
    }

    static class BasePlayerListUpdateLatencyAction implements PlayerListUpdateLatencyAction {

        private int ping;

        BasePlayerListUpdateLatencyAction() {
        }

        @Override
        public int getPing() {
            return ping;
        }

        @Override
        public void setPing(int ping) {
            this.ping = ping;
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof PlayerListUpdateLatencyAction && getPing() == ((PlayerListUpdateLatencyAction) o).getPing());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPing());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{ping=" + getPing() + '}';
        }
    }

    static class BasePlayerListUpdateDisplayNameAction implements PlayerListUpdateDisplayNameAction {

        private String displayName;

        BasePlayerListUpdateDisplayNameAction() {
        }

        @Override
        public String getDisplayName() {
            return displayName;
        }

        @Override
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof PlayerListUpdateDisplayNameAction && Objects.equals(getDisplayName(), ((PlayerListUpdateDisplayNameAction) o).getDisplayName()));
        }

        @Override
        public int hashCode() {
            return Objects.hash(getDisplayName());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{displayName=\"" + getDisplayName() + "\"}";
        }
    }

    static class BasePlayerListRemovePlayerAction implements PlayerListRemovePlayerAction {

        BasePlayerListRemovePlayerAction() {
        }

        @Override
        public String toString() {
            return getClass().getName() + "{}";
        }
    }
}
