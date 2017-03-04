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

public interface ClientSettingsServerboundV47 extends Packet {

    short SKIN_PART_CAPE = 0b00000001;
    short SKIN_PART_JACKET = 0b00000010;
    short SKIN_PART_LEFT_SLEEVE = 0b00000100;
    short SKIN_PART_RIGHT_SLEEVE = 0b00001000;
    short SKIN_PART_LEFT_PANTS = 0b00010000;
    short SKIN_PART_RIGHT_PANTS = 0b00100000;
    short SKIN_PART_HAT = 0b01000000;

    byte CHAT_MODE_ENABLED = 0;
    byte CHAT_MODE_COMMANDS_ONLY = 1;
    byte CHAT_MODE_HIDDEN = 2;

    String getLocale();

    void setLocale(String locale);

    byte getViewDistance();

    void setViewDistance(byte viewDistance);

    byte getChatMode();

    void setChatMode(byte chatMode);

    boolean isChatColorsEnabled();

    void setChatColorsEnabled(boolean chatColorsEnabled);

    short getDisplayedSkinParts();

    void setDisplayedSkinParts(short displayedSkinParts);

    @Override
    default Class<? extends Packet> getModelClass() {
        return ClientSettingsServerboundV47.class;
    }
}
