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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseClientSettingsServerboundV47 implements ClientSettingsServerboundV47 {

    private String locale;
    private byte viewDistance;
    private byte chatMode;
    private boolean chatColorsEnabled;
    private short displayedSkinParts;

    BaseClientSettingsServerboundV47() {
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public byte getViewDistance() {
        return viewDistance;
    }

    @Override
    public void setViewDistance(byte viewDistance) {
        this.viewDistance = viewDistance;
    }

    @Override
    public byte getChatMode() {
        return chatMode;
    }

    @Override
    public void setChatMode(byte chatMode) {
        this.chatMode = chatMode;
    }

    @Override
    public boolean isChatColorsEnabled() {
        return chatColorsEnabled;
    }

    @Override
    public void setChatColorsEnabled(boolean chatColorsEnabled) {
        this.chatColorsEnabled = chatColorsEnabled;
    }

    @Override
    public short getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    @Override
    public void setDisplayedSkinParts(short displayedSkinParts) {
        this.displayedSkinParts = displayedSkinParts;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeString(output, getLocale());
        output.writeByte(getViewDistance());
        output.writeByte(getChatMode());
        output.writeBoolean(isChatColorsEnabled());
        output.writeByte(getDisplayedSkinParts());
    }

    @Override
    public void read(ByteBuf input) {
        setLocale(ProtocolUtils.readString(input));
        setViewDistance(input.readByte());
        setChatMode(input.readByte());
        setChatColorsEnabled(input.readBoolean());
        setDisplayedSkinParts(input.readUnsignedByte());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ClientSettingsServerboundV47))
            return false;
        ClientSettingsServerboundV47 that = (ClientSettingsServerboundV47) o;
        return getViewDistance() == that.getViewDistance() &&
                getChatMode() == that.getChatMode() &&
                isChatColorsEnabled() == that.isChatColorsEnabled() &&
                getDisplayedSkinParts() == that.getDisplayedSkinParts() &&
                Objects.equals(getLocale(), that.getLocale());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocale(), getViewDistance(), getChatMode(), isChatColorsEnabled(), getDisplayedSkinParts());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{locale=\"" + getLocale() +
                "\", viewDistance=" + getViewDistance() +
                ", chatMode=" + getChatMode() +
                ", chatColorsEnabled=" + isChatColorsEnabled() +
                ", displayedSkinParts=" + getDisplayedSkinParts() +
                '}';
    }
}
