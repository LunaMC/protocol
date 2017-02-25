/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
}
