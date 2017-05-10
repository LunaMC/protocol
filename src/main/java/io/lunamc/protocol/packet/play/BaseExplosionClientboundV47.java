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

import io.lunamc.protocol.packet.data.ByteTuple3;
import io.lunamc.protocol.packet.data.DataAllocator;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class BaseExplosionClientboundV47 implements ExplosionClientboundV47 {

    private final transient DataAllocator dataAllocator;
    private float x;
    private float y;
    private float z;
    private float radius;
    private List<ByteTuple3> records;
    private float playerMotionX;
    private float playerMotionY;
    private float playerMotionZ;

    BaseExplosionClientboundV47(DataAllocator dataAllocator) {
        this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getZ() {
        return z;
    }

    @Override
    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public List<ByteTuple3> getRecords() {
        return records;
    }

    @Override
    public void setRecords(List<ByteTuple3> records) {
        this.records = records;
    }

    @Override
    public float getPlayerMotionX() {
        return playerMotionX;
    }

    @Override
    public void setPlayerMotionX(float playerMotionX) {
        this.playerMotionX = playerMotionX;
    }

    @Override
    public float getPlayerMotionY() {
        return playerMotionY;
    }

    @Override
    public void setPlayerMotionY(float playerMotionY) {
        this.playerMotionY = playerMotionY;
    }

    @Override
    public float getPlayerMotionZ() {
        return playerMotionZ;
    }

    @Override
    public void setPlayerMotionZ(float playerMotionZ) {
        this.playerMotionZ = playerMotionZ;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeFloat(getX());
        output.writeFloat(getY());
        output.writeFloat(getZ());
        output.writeFloat(getRadius());
        List<ByteTuple3> records = getRecords();
        output.writeInt(records.size());
        for (ByteTuple3 record : records)
            record.write(output);
        output.writeFloat(getPlayerMotionX());
        output.writeFloat(getPlayerMotionY());
        output.writeFloat(getPlayerMotionZ());
    }

    @Override
    public void read(ByteBuf input) {
        setX(input.readFloat());
        setY(input.readFloat());
        setZ(input.readFloat());
        setRadius(input.readFloat());
        int recordsSize = input.readInt();
        List<ByteTuple3> records = new ArrayList<>(recordsSize);
        for (int i = 0; i < recordsSize; i++) {
            ByteTuple3 record = dataAllocator.getByteTuple3();
            record.read(input);
            records.add(record);
        }
        setRecords(records);
        setPlayerMotionX(input.readFloat());
        setPlayerMotionY(input.readFloat());
        setPlayerMotionZ(input.readFloat());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExplosionClientboundV47))
            return false;
        ExplosionClientboundV47 that = (ExplosionClientboundV47) o;
        return Float.compare(getX(), that.getX()) == 0 &&
                Float.compare(getY(), that.getY()) == 0 &&
                Float.compare(getZ(), that.getZ()) == 0 &&
                Float.compare(getRadius(), that.getRadius()) == 0 &&
                Objects.equals(getRecords(), that.getRecords()) &&
                Float.compare(getPlayerMotionX(), that.getPlayerMotionX()) == 0 &&
                Float.compare(getPlayerMotionY(), that.getPlayerMotionY()) == 0 &&
                Float.compare(getPlayerMotionZ(), that.getPlayerMotionZ()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getX(),
                getY(),
                getZ(),
                getRadius(),
                getRecords(),
                getPlayerMotionX(),
                getPlayerMotionY(),
                getPlayerMotionZ()
        );
    }

    @Override
    public String toString() {
        return getClass().getName() + "{x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                ", radius=" + getRadius() +
                ", records=" + getRecords() +
                ", playerMotionX=" + getPlayerMotionX() +
                ", playerMotionY=" + getPlayerMotionY() +
                ", playerMotionZ=" + getPlayerMotionZ() +
                '}';
    }
}
