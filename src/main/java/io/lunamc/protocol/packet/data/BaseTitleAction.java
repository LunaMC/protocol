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

package io.lunamc.protocol.packet.data;

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseTitleAction implements TitleAction {

    private BaseTitleAction() {
    }

    static class BaseTitleActionSetTitle implements TitleActionSetTitle {

        private String titleText;

        BaseTitleActionSetTitle() {
        }

        @Override
        public String getTitleText() {
            return titleText;
        }

        @Override
        public void setTitleText(String titleText) {
            this.titleText = titleText;
        }

        @Override
        public void write(ByteBuf output) {
            ProtocolUtils.writeString(output, getTitleText());
        }

        @Override
        public void read(ByteBuf input) {
            setTitleText(ProtocolUtils.readString(input));
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof TitleActionSetTitle && Objects.equals(getTitleText(), ((TitleActionSetTitle) o).getTitleText()));
        }

        @Override
        public int hashCode() {
            return Objects.hash(getTitleText());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{titleText=\"" + getTitleText() + "\"}";
        }
    }

    static class BaseTitleActionSetSubtitle implements TitleActionSetSubtitle {

        private String subtitleText;

        BaseTitleActionSetSubtitle() {
        }

        @Override
        public String getSubtitleText() {
            return subtitleText;
        }

        @Override
        public void setSubtitleText(String subtitleText) {
            this.subtitleText = subtitleText;
        }

        @Override
        public void write(ByteBuf output) {
            ProtocolUtils.writeString(output, getSubtitleText());
        }

        @Override
        public void read(ByteBuf input) {
            setSubtitleText(ProtocolUtils.readString(input));
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof TitleActionSetSubtitle && Objects.equals(getSubtitleText(), ((TitleActionSetSubtitle) o).getSubtitleText()));
        }

        @Override
        public int hashCode() {
            return Objects.hash(getSubtitleText());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{subtitleText=\"" + getSubtitleText() + "\"}";
        }
    }

    static class BaseTitleActionSetTimesAndDisplay implements TitleActionSetTimesAndDisplay {

        private int fadeIn;
        private int stay;
        private int fadeOut;

        BaseTitleActionSetTimesAndDisplay() {
        }

        @Override
        public int getFadeIn() {
            return fadeIn;
        }

        @Override
        public void setFadeIn(int fadeIn) {
            this.fadeIn = fadeIn;
        }

        @Override
        public int getStay() {
            return stay;
        }

        @Override
        public void setStay(int stay) {
            this.stay = stay;
        }

        @Override
        public int getFadeOut() {
            return fadeOut;
        }

        @Override
        public void setFadeOut(int fadeOut) {
            this.fadeOut = fadeOut;
        }

        @Override
        public void write(ByteBuf output) {
            output.writeInt(getFadeIn());
            output.writeInt(getStay());
            output.writeInt(getFadeOut());
        }

        @Override
        public void read(ByteBuf input) {
            setFadeIn(input.readInt());
            setStay(input.readInt());
            setFadeOut(input.readInt());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof TitleActionSetTimesAndDisplay))
                return false;
            TitleActionSetTimesAndDisplay that = (TitleActionSetTimesAndDisplay) o;
            return getFadeIn() == that.getFadeIn() &&
                    getStay() == that.getStay() &&
                    getFadeOut() == that.getFadeOut();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFadeIn(), getStay(), getFadeOut());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{fadeIn=" + getFadeIn() +
                    ", stay=" + getStay() +
                    ", fadeOut=" + getFadeOut() +
                    '}';
        }
    }

    static class BaseTitleActionHide implements TitleActionHide {

        BaseTitleActionHide() {
        }

        @Override
        public void write(ByteBuf output) {
        }

        @Override
        public void read(ByteBuf input) {
        }

        @Override
        public void reset() {
        }

        @Override
        public String toString() {
            return getClass().getName() + "{}";
        }
    }

    static class BaseTitleActionReset implements TitleActionReset {

        BaseTitleActionReset() {
        }

        @Override
        public void write(ByteBuf output) {
        }

        @Override
        public void read(ByteBuf input) {
        }

        @Override
        public void reset() {
        }

        @Override
        public String toString() {
            return getClass().getName() + "{}";
        }
    }
}
