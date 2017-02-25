/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet;

import io.netty.buffer.ByteBuf;

public interface Packet {

    void write(ByteBuf output);

    void read(ByteBuf input);
}
