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

import io.lunamc.protocol.packet.NetworkSerializable;
import io.netty.buffer.ByteBuf;

public interface TitleAction {

    interface TitleActionSetTitle extends NetworkSerializable {

        String getTitleText();

        void setTitleText(String titleText);

        @Override
        default void reset() {
            setTitleText(null);
        }
    }

    interface TitleActionSetSubtitle extends NetworkSerializable {

        String getSubtitleText();

        void setSubtitleText(String subtitleText);

        @Override
        default void reset() {
            setSubtitleText(null);
        }
    }

    interface TitleActionSetTimesAndDisplay extends NetworkSerializable {

        int getFadeIn();

        void setFadeIn(int fadeIn);

        int getStay();

        void setStay(int stay);

        int getFadeOut();

        void setFadeOut(int fadeOut);

        @Override
        default void reset() {
            setFadeIn(0);
            setStay(0);
            setFadeOut(0);
        }
    }

    interface TitleActionHide extends NetworkSerializable {

        @Override
        default void write(ByteBuf output) {
        }

        @Override
        default void read(ByteBuf input) {
        }

        @Override
        default void reset() {
        }
    }

    interface TitleActionReset extends NetworkSerializable {

        @Override
        default void write(ByteBuf output) {
        }

        @Override
        default void read(ByteBuf input) {
        }

        @Override
        default void reset() {
        }
    }
}
