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

import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseRotation implements Rotation {

    private float rotationX;
    private float rotationY;
    private float rotationZ;

    @Override
    public float getRotationX() {
        return rotationX;
    }

    @Override
    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    @Override
    public float getRotationY() {
        return rotationY;
    }

    @Override
    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    @Override
    public float getRotationZ() {
        return rotationZ;
    }

    @Override
    public void setRotationZ(float rotationZ) {
        this.rotationZ = rotationZ;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeFloat(getRotationX());
        output.writeFloat(getRotationY());
        output.writeFloat(getRotationZ());
    }

    @Override
    public void read(ByteBuf input) {
        setRotationX(input.readFloat());
        setRotationY(input.readFloat());
        setRotationZ(input.readFloat());
    }

    @Override
    public void reset() {
        setRotationX(0);
        setRotationY(0);
        setRotationZ(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Rotation))
            return false;
        Rotation that = (Rotation) o;
        return Float.compare(that.getRotationX(), getRotationX()) == 0 &&
                Float.compare(that.getRotationY(), getRotationY()) == 0 &&
                Float.compare(that.getRotationZ(), getRotationZ()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRotationX(), getRotationY(), getRotationZ());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{rotationX=" + getRotationX() +
                ", rotationY=" + getRotationY() +
                ", rotationZ=" + getRotationZ() +
                '}';
    }
}
