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

import io.netty.buffer.ByteBuf;

import java.util.Objects;

class BaseTimeUpdateClientboundV47 implements TimeUpdateClientboundV47 {

    private long worldAge;
    private long timeOfDay;

    BaseTimeUpdateClientboundV47() {
    }

    @Override
    public long getWorldAge() {
        return worldAge;
    }

    @Override
    public void setWorldAge(long worldAge) {
        this.worldAge = worldAge;
    }

    @Override
    public long getTimeOfDay() {
        return timeOfDay;
    }

    @Override
    public void setTimeOfDay(long timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeLong(getWorldAge());
        output.writeLong(getTimeOfDay());
    }

    @Override
    public void read(ByteBuf input) {
        setWorldAge(input.readLong());
        setTimeOfDay(input.readLong());
    }

    @Override
    public void reset() {
        setWorldAge(0);
        setTimeOfDay(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TimeUpdateClientboundV47))
            return false;
        TimeUpdateClientboundV47 that = (TimeUpdateClientboundV47) o;
        return getWorldAge() == that.getWorldAge() &&
                getTimeOfDay() == that.getTimeOfDay();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorldAge(), getTimeOfDay());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{worldAge=" + getWorldAge() + ", timeOfDay=" + getTimeOfDay() + '}';
    }
}
