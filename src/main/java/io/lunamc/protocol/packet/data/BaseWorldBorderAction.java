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

class BaseWorldBorderAction implements WorldBorderAction {

    private BaseWorldBorderAction() {
    }

    static class BaseWorldBorderSetSizeAction implements WorldBorderSetSizeAction {

        private double radius;

        BaseWorldBorderSetSizeAction() {
        }

        @Override
        public double getRadius() {
            return radius;
        }

        @Override
        public void setRadius(double radius) {
            this.radius = radius;
        }

        @Override
        public void write(ByteBuf output) {
            output.writeDouble(getRadius());
        }

        @Override
        public void read(ByteBuf input) {
            setRadius(input.readDouble());
        }

        @Override
        public void reset() {
            setRadius(0);
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof WorldBorderSetSizeAction && Double.compare(((WorldBorderSetSizeAction) o).getRadius(), getRadius()) == 0);
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRadius());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{radius=" + getRadius() + '}';
        }
    }

    static class BaseWorldBorderLerpSize implements WorldBorderLerpSize {

        private double oldRadius;
        private double newRadius;
        private long speed;

        BaseWorldBorderLerpSize() {
        }

        @Override
        public double getOldRadius() {
            return oldRadius;
        }

        @Override
        public void setOldRadius(double oldRadius) {
            this.oldRadius = oldRadius;
        }

        @Override
        public double getNewRadius() {
            return newRadius;
        }

        @Override
        public void setNewRadius(double newRadius) {
            this.newRadius = newRadius;
        }

        @Override
        public long getSpeed() {
            return speed;
        }

        @Override
        public void setSpeed(long speed) {
            this.speed = speed;
        }

        @Override
        public void write(ByteBuf output) {
            output.writeDouble(getOldRadius());
            output.writeDouble(getNewRadius());
            ProtocolUtils.writeVarLong(output, getSpeed());
        }

        @Override
        public void read(ByteBuf input) {
            setOldRadius(input.readDouble());
            setNewRadius(input.readDouble());
            setSpeed(ProtocolUtils.readVarLong(input));
        }

        @Override
        public void reset() {
            setOldRadius(0);
            setNewRadius(0);
            setSpeed(0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof WorldBorderLerpSize))
                return false;
            WorldBorderLerpSize that = (WorldBorderLerpSize) o;
            return Double.compare(that.getOldRadius(), getOldRadius()) == 0 &&
                    Double.compare(that.getNewRadius(), getNewRadius()) == 0 &&
                    getSpeed() == that.getSpeed();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getOldRadius(), getNewRadius(), getSpeed());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{oldRadius=" + getOldRadius() +
                    ", newRadius=" + getNewRadius() +
                    ", speed=" + getSpeed() +
                    '}';
        }
    }

    static class BaseWorldBorderSetCenter implements WorldBorderSetCenter {

        private double x;
        private double z;

        BaseWorldBorderSetCenter() {
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public void setX(double x) {
            this.x = x;
        }

        @Override
        public double getZ() {
            return z;
        }

        @Override
        public void setZ(double z) {
            this.z = z;
        }

        @Override
        public void write(ByteBuf output) {
            output.writeDouble(getX());
            output.writeDouble(getZ());
        }

        @Override
        public void read(ByteBuf input) {
            setX(input.readDouble());
            setZ(input.readDouble());
        }

        @Override
        public void reset() {
            setX(0);
            setZ(0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof WorldBorderSetCenter))
                return false;
            WorldBorderSetCenter that = (WorldBorderSetCenter) o;
            return Double.compare(that.getX(), getX()) == 0 &&
                    Double.compare(that.getZ(), getZ()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getZ());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{x=" + getX() + ", z=" + getZ() + '}';
        }
    }

    static class BaseWorldBorderInitialize implements WorldBorderInitialize {

        private double x;
        private double z;
        private double oldRadius;
        private double newRadius;
        private long speed;
        private int portalTeleportationBoundary;
        private int warningTime;
        private int warningBlocks;

        BaseWorldBorderInitialize() {
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public void setX(double x) {
            this.x = x;
        }

        @Override
        public double getZ() {
            return z;
        }

        @Override
        public void setZ(double z) {
            this.z = z;
        }

        @Override
        public double getOldRadius() {
            return oldRadius;
        }

        @Override
        public void setOldRadius(double oldRadius) {
            this.oldRadius = oldRadius;
        }

        @Override
        public double getNewRadius() {
            return newRadius;
        }

        @Override
        public void setNewRadius(double newRadius) {
            this.newRadius = newRadius;
        }

        @Override
        public long getSpeed() {
            return speed;
        }

        @Override
        public void setSpeed(long speed) {
            this.speed = speed;
        }

        @Override
        public int getPortalTeleportationBoundary() {
            return portalTeleportationBoundary;
        }

        @Override
        public void setPortalTeleportationBoundary(int portalTeleportationBoundary) {
            this.portalTeleportationBoundary = portalTeleportationBoundary;
        }

        @Override
        public int getWarningTime() {
            return warningTime;
        }

        @Override
        public void setWarningTime(int warningTime) {
            this.warningTime = warningTime;
        }

        @Override
        public int getWarningBlocks() {
            return warningBlocks;
        }

        @Override
        public void setWarningBlocks(int warningBlocks) {
            this.warningBlocks = warningBlocks;
        }

        @Override
        public void write(ByteBuf output) {
            output.writeDouble(getX());
            output.writeDouble(getZ());
            output.writeDouble(getOldRadius());
            output.writeDouble(getNewRadius());
            ProtocolUtils.writeVarLong(output, getSpeed());
            ProtocolUtils.writeVarInt(output, getPortalTeleportationBoundary());
            ProtocolUtils.writeVarInt(output, getWarningTime());
            ProtocolUtils.writeVarInt(output, getWarningBlocks());
        }

        @Override
        public void read(ByteBuf input) {
            setX(input.readDouble());
            setZ(input.readDouble());
            setOldRadius(input.readDouble());
            setNewRadius(input.readDouble());
            setSpeed(ProtocolUtils.readVarLong(input));
            setPortalTeleportationBoundary(ProtocolUtils.readVarInt(input));
            setWarningTime(ProtocolUtils.readVarInt(input));
            setWarningBlocks(ProtocolUtils.readVarInt(input));
        }

        @Override
        public void reset() {
            setX(0);
            setZ(0);
            setOldRadius(0);
            setNewRadius(0);
            setSpeed(0);
            setPortalTeleportationBoundary(0);
            setWarningTime(0);
            setWarningBlocks(0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof WorldBorderInitialize))
                return false;
            WorldBorderInitialize that = (WorldBorderInitialize) o;
            return Double.compare(that.getX(), getX()) == 0 &&
                    Double.compare(that.getZ(), getZ()) == 0 &&
                    Double.compare(that.getOldRadius(), getOldRadius()) == 0 &&
                    Double.compare(that.getNewRadius(), getNewRadius()) == 0 &&
                    getSpeed() == that.getSpeed() &&
                    getPortalTeleportationBoundary() == that.getPortalTeleportationBoundary() &&
                    getWarningTime() == that.getWarningTime() &&
                    getWarningBlocks() == that.getWarningBlocks();
        }

        @Override
        public int hashCode() {
            return Objects.hash(
                    getX(),
                    getZ(),
                    getOldRadius(),
                    getNewRadius(),
                    getSpeed(),
                    getPortalTeleportationBoundary(),
                    getWarningTime(),
                    getWarningBlocks()
            );
        }

        @Override
        public String toString() {
            return getClass().getName() + "{x=" + getX() +
                    ", z=" + getZ() +
                    ", oldRadius=" + getOldRadius() +
                    ", newRadius=" + getNewRadius() +
                    ", speed=" + getSpeed() +
                    ", portalTeleportationBoundary=" + getPortalTeleportationBoundary() +
                    ", warningTime=" + getWarningTime() +
                    ", warningBlocks=" + getWarningBlocks() +
                    '}';
        }
    }

    static class BaseWorldBorderSetWarningTime implements WorldBorderSetWarningTime {

        private int warningTime;

        BaseWorldBorderSetWarningTime() {
        }

        @Override
        public int getWarningTime() {
            return warningTime;
        }

        @Override
        public void setWarningTime(int warningTime) {
            this.warningTime = warningTime;
        }

        @Override
        public void write(ByteBuf output) {
            ProtocolUtils.writeVarInt(output, getWarningTime());
        }

        @Override
        public void read(ByteBuf input) {
            setWarningTime(ProtocolUtils.readVarInt(input));
        }

        @Override
        public void reset() {
            setWarningTime(0);
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof WorldBorderSetWarningTime && getWarningTime() == ((WorldBorderSetWarningTime) o).getWarningTime());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getWarningTime());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{warningTime=" + getWarningTime() + '}';
        }
    }

    static class BaseWorldBorderSetWarningBlocks implements WorldBorderSetWarningBlocks {

        private int warningBlocks;

        BaseWorldBorderSetWarningBlocks() {
        }

        @Override
        public int getWarningBlocks() {
            return warningBlocks;
        }

        @Override
        public void setWarningBlocks(int warningBlocks) {
            this.warningBlocks = warningBlocks;
        }

        @Override
        public void write(ByteBuf output) {
            ProtocolUtils.writeVarInt(output, getWarningBlocks());
        }

        @Override
        public void read(ByteBuf input) {
            setWarningBlocks(ProtocolUtils.readVarInt(input));
        }

        @Override
        public void reset() {
            setWarningBlocks(0);
        }

        @Override
        public boolean equals(Object o) {
            return this == o ||
                    (o instanceof WorldBorderSetWarningBlocks && getWarningBlocks() == ((WorldBorderSetWarningBlocks) o).getWarningBlocks());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getWarningBlocks());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{warningBlocks=" + getWarningBlocks() + '}';
        }
    }
}
