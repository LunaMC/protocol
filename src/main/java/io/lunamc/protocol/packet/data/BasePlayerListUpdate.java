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

import io.lunamc.protocol.ProtocolUtils;
import io.lunamc.protocol.packet.NetworkSerializable;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// Notice: This implementation requires settings the action before reading
class BasePlayerListUpdate implements PlayerListUpdate {

    private UUID uuid;
    private NetworkSerializable action;

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
    public NetworkSerializable getAction() {
        return action;
    }

    @Override
    public void setAction(NetworkSerializable action) {
        if (action != null &&
                !(action instanceof PlayerListAddPlayerAction) &&
                !(action instanceof PlayerListUpdateGamemodeAction) &&
                !(action instanceof PlayerListUpdateLatencyAction) &&
                !(action instanceof PlayerListUpdateDisplayNameAction) &&
                !(action instanceof PlayerListRemovePlayerAction)) {
            throw new IllegalArgumentException("Invalid action");
        }
        this.action = action;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeUuid(output, getUuid());
        action.write(output);
    }

    @Override
    public void read(ByteBuf input) {
        setUuid(ProtocolUtils.readUuid(input));
        action.read(input);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PlayerListUpdate))
            return false;
        PlayerListUpdate that = (PlayerListUpdate) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getAction(), that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getAction());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{uuid=" + getUuid() + ", action=" + getAction() + '}';
    }

    static class BasePlayerListAddPlayerAction implements PlayerListAddPlayerAction {

        private final DataAllocator dataAllocator;
        private String name;
        private List<PlayerListAddPlayerActionPlayerProperty> properties;
        private int gamemode;
        private int ping;
        private String displayName;

        BasePlayerListAddPlayerAction(DataAllocator dataAllocator) {
            this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
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
        public List<PlayerListAddPlayerActionPlayerProperty> getProperties() {
            return properties;
        }

        @Override
        public void setProperties(List<PlayerListAddPlayerActionPlayerProperty> properties) {
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
        public void write(ByteBuf output) {
            ProtocolUtils.writeString(output, getName());

            List<PlayerListAddPlayerActionPlayerProperty> properties = getProperties();
            ProtocolUtils.writeVarInt(output, properties.size());
            properties.forEach(property -> property.write(output));

            ProtocolUtils.writeVarInt(output, getGamemode());
            ProtocolUtils.writeVarInt(output, getPing());

            String displayName = getDisplayName();
            output.writeBoolean(displayName != null);
            if (displayName != null)
                ProtocolUtils.writeString(output, displayName);
        }

        @Override
        public void read(ByteBuf input) {
            setName(ProtocolUtils.readString(input));

            int length = ProtocolUtils.readVarInt(input);
            List<PlayerListAddPlayerActionPlayerProperty> properties = getProperties();
            if (properties == null) {
                properties = new ArrayList<>(length);
                setProperties(properties);
            }
            for (int i = 0; i < length; i++) {
                PlayerListAddPlayerActionPlayerProperty property = dataAllocator.getPlayerListAddPlayerActionPlayerProperty();
                property.read(input);
                properties.add(property);
            }

            setGamemode(ProtocolUtils.readVarInt(input));
            setPing(ProtocolUtils.readVarInt(input));
            setDisplayName(input.readBoolean() ? ProtocolUtils.readString(input) : null);
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
            public void write(ByteBuf output) {
                ProtocolUtils.writeString(output, getName());
                ProtocolUtils.writeString(output, getValue());
                String signature = getSignature();
                output.writeBoolean(signature != null);
                if (signature != null)
                    ProtocolUtils.writeString(output, signature);
            }

            @Override
            public void read(ByteBuf input) {
                setName(ProtocolUtils.readString(input));
                setValue(ProtocolUtils.readString(input));
                if (input.readBoolean())
                    setSignature(ProtocolUtils.readString(input));
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
        public void write(ByteBuf output) {
            ProtocolUtils.writeVarInt(output, getGamemode());
        }

        @Override
        public void read(ByteBuf input) {
            setGamemode(ProtocolUtils.readVarInt(input));
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
        public void write(ByteBuf output) {
            ProtocolUtils.writeVarInt(output, getPing());
        }

        @Override
        public void read(ByteBuf input) {
            setPing(ProtocolUtils.readVarInt(input));
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
        public void write(ByteBuf output) {
            ProtocolUtils.writeString(output, getDisplayName());
        }

        @Override
        public void read(ByteBuf input) {
            setDisplayName(ProtocolUtils.readString(input));
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
