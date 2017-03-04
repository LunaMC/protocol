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

import io.lunamc.protocol.packet.NetworkSerializable;
import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.UUID;

public interface PlayerListUpdate extends NetworkSerializable {

    UUID getUuid();

    void setUuid(UUID uuid);

    NetworkSerializable getAction();

    void setAction(NetworkSerializable action);

    @Override
    default void reset() {
        setUuid(null);
        setAction(null);
    }

    interface PlayerListAddPlayerAction extends NetworkSerializable {

        String getName();

        void setName(String name);

        List<PlayerListAddPlayerActionPlayerProperty> getProperties();

        void setProperties(List<PlayerListAddPlayerActionPlayerProperty> properties);

        int getGamemode();

        void setGamemode(int gamemode);

        int getPing();

        void setPing(int ping);

        String getDisplayName();

        void setDisplayName(String displayName);

        @Override
        default void reset() {
            setName(null);
            setProperties(null);
            setGamemode(0);
            setPing(0);
            setDisplayName(null);
        }

        interface PlayerListAddPlayerActionPlayerProperty extends NetworkSerializable {

            String getName();

            void setName(String name);

            String getValue();

            void setValue(String value);

            String getSignature();

            void setSignature(String signature);

            @Override
            default void reset() {
                setName(null);
                setValue(null);
                setSignature(null);
            }
        }
    }

    interface PlayerListUpdateGamemodeAction extends NetworkSerializable {

        int getGamemode();

        void setGamemode(int gamemode);

        @Override
        default void reset() {
            setGamemode(0);
        }
    }

    interface PlayerListUpdateLatencyAction extends NetworkSerializable {

        int getPing();

        void setPing(int ping);

        @Override
        default void reset() {
            setPing(0);
        }
    }

    interface PlayerListUpdateDisplayNameAction extends NetworkSerializable {

        String getDisplayName();

        void setDisplayName(String displayName);

        @Override
        default void reset() {
            setDisplayName(null);
        }
    }

    interface PlayerListRemovePlayerAction extends NetworkSerializable {

        @Override
        default void write(ByteBuf output) {
        }

        @Override
        default void read(ByteBuf input) {
        }

        @Override
        default void reset() {
        }
    }
}
