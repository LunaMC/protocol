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

public interface WorldBorderAction {

    interface WorldBorderSetSizeAction {

        double getRadius();

        void setRadius(double radius);
    }

    interface WorldBorderLerpSize {

        double getOldRadius();

        void setOldRadius(double oldRadius);

        double getNewRadius();

        void setNewRadius(double newRadius);

        long getSpeed();

        void setSpeed(long speed);
    }

    interface WorldBorderSetCenter {

        double getX();

        void setX(double x);

        double getZ();

        void setZ(double z);
    }

    interface WorldBorderInitialize {

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
    }

    interface WorldBorderSetWarningTime {

        int getWarningTime();

        void setWarningTime(int warningTime);
    }

    interface WorldBorderSetWarningBlocks {

        int getWarningBlocks();

        void setWarningBlocks(int warningBlocks);
    }
}
