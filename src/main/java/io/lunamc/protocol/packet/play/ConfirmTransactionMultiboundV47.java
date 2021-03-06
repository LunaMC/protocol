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

package io.lunamc.protocol.packet.play;

import io.lunamc.protocol.packet.Packet;

public interface ConfirmTransactionMultiboundV47 extends Packet {

    byte getWindowId();

    void setWindowId(byte windowId);

    short getActionNumber();

    void setActionNumber(short actionNumber);

    boolean isAccepted();

    void setAccepted(boolean accepted);

    @Override
    default void reset() {
        setWindowId((byte) 0);
        setActionNumber((short) 0);
        setAccepted(false);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return ConfirmTransactionMultiboundV47.class;
    }
}
