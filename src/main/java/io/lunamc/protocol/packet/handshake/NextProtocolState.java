/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import java.util.NoSuchElementException;

public enum NextProtocolState {

    STATUS(1),
    LOGIN(2);

    private final int reference;

    NextProtocolState(int reference) {
        this.reference = reference;
    }

    public int getReference() {
        return reference;
    }

    public static NextProtocolState valueOfReference(int reference) {
        switch (reference) {
            case 1:
                return STATUS;
            case 2:
                return LOGIN;
            default:
                throw new NoSuchElementException("Unknown reference: " + reference);
        }
    }
}
