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

class BaseDisplayScoreboardClientboundV47 implements DisplayScoreboardClientboundV47 {

    private byte position;
    private String scoreName;

    BaseDisplayScoreboardClientboundV47() {
    }

    @Override
    public byte getPosition() {
        return position;
    }

    @Override
    public void setPosition(byte position) {
        this.position = position;
    }

    @Override
    public String getScoreName() {
        return scoreName;
    }

    @Override
    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeByte(getPosition());
        ProtocolUtils.writeString(output, getScoreName());
    }

    @Override
    public void read(ByteBuf input) {
        setPosition(input.readByte());
        setScoreName(ProtocolUtils.readString(input));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DisplayScoreboardClientboundV47))
            return false;
        DisplayScoreboardClientboundV47 that = (DisplayScoreboardClientboundV47) o;
        return getPosition() == that.getPosition() &&
                Objects.equals(getScoreName(), that.getScoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getScoreName());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{position=" + getPosition() +
                ", scoreName='" + getScoreName() + '\'' +
                '}';
    }
}
