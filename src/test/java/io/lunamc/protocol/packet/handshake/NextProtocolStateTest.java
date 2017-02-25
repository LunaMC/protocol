/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import org.junit.Assert;
import org.junit.Test;

public class NextProtocolStateTest {

    @Test
    public void testValueOfReference() {
        for (NextProtocolState state : NextProtocolState.values())
            Assert.assertEquals(state, NextProtocolState.valueOfReference(state.getReference()));
    }
}
