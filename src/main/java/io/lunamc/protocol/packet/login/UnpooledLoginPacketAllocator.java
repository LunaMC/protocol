/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.login;

public class UnpooledLoginPacketAllocator implements LoginPacketAllocator {

    public static final UnpooledLoginPacketAllocator INSTANCE = new UnpooledLoginPacketAllocator();
}
