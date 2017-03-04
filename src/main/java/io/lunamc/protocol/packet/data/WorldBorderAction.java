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

public interface WorldBorderAction {

    interface WorldBorderSetSizeAction extends NetworkSerializable {

        double getRadius();

        void setRadius(double radius);

        @Override
        default void reset() {
            setRadius(0);
        }
    }

    interface WorldBorderLerpSize extends NetworkSerializable {

        double getOldRadius();

        void setOldRadius(double oldRadius);

        double getNewRadius();

        void setNewRadius(double newRadius);

        long getSpeed();

        void setSpeed(long speed);

        @Override
        default void reset() {
            setOldRadius(0);
            setNewRadius(0);
            setSpeed(0);
        }
    }

    interface WorldBorderSetCenter extends NetworkSerializable {

        double getX();

        void setX(double x);

        double getZ();

        void setZ(double z);

        @Override
        default void reset() {
            setX(0);
            setZ(0);
        }
    }

    interface WorldBorderInitialize extends NetworkSerializable {

        double getX();

        void setX(double x);

        double getZ();

        void setZ(double z);

        double getOldRadius();

        void setOldRadius(double oldRadius);

        double getNewRadius();

        void setNewRadius(double newRadius);

        long getSpeed();

        void setSpeed(long speed);

        int getPortalTeleportationBoundary();

        void setPortalTeleportationBoundary(int portalTeleportationBoundary);

        int getWarningTime();

        void setWarningTime(int warningTime);

        int getWarningBlocks();

        void setWarningBlocks(int warningBlocks);

        @Override
        default void reset() {
            setX(0);
            setZ(0);
            setOldRadius(0);
            setNewRadius(0);
            setSpeed(0);
            setPortalTeleportationBoundary(0);
            setWarningTime(0);
            setWarningBlocks(0);
        }
    }

    interface WorldBorderSetWarningTime extends NetworkSerializable {

        int getWarningTime();

        void setWarningTime(int warningTime);

        @Override
        default void reset() {
            setWarningTime(0);
        }
    }

    interface WorldBorderSetWarningBlocks extends NetworkSerializable {

        int getWarningBlocks();

        void setWarningBlocks(int warningBlocks);

        @Override
        default void reset() {
            setWarningBlocks(0);
        }
    }
}
