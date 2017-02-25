/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

import io.lunamc.protocol.packet.Packet;

public interface LoginSuccessClientboundV47 extends Packet {

    String getUuid();

    void setUuid(String uuid);

    String getUsername();

    void setUsername(String username);
}
