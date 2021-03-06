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

package io.lunamc.protocol.packet.handshake;

import io.lunamc.protocol.packet.Packet;

public interface HandshakeServerboundV47 extends Packet {

    int getProtocolVersion();

    void setProtocolVersion(int protocolVersion);

    String getServerAddress();

    void setServerAddress(String serverAddress);

    int getServerPort();

    void setServerPort(int serverPort);

    NextProtocolState getNextState();

    void setNextState(NextProtocolState nextState);

    @Override
    default void reset() {
        setProtocolVersion(0);
        setServerAddress(null);
        setServerPort(0);
        setNextState(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return HandshakeServerboundV47.class;
    }
}
