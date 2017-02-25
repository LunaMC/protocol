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
