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

import io.lunamc.protocol.packet.Packet;

public interface EntityStatusClientboundV47 extends Packet {

    // Protocol version 47+
    byte STATUS_RESET_MOB_SPAWN_MINECART_TIMER = 1;
    byte STATUS_RABBIT_JUMP = 1;
    byte STATUS_HURT = 2;
    byte STATUS_DEAD = 3;
    byte STATUS_IRON_GOLEM_THROWING_ARMS = 4;
    byte STATUS_TAME_HEART_PARTICLES = 6;
    byte STATUS_TAME_SMOKE_PARTICLES = 7;
    byte STATUS_WOLF_SHAKING_WATER = 8;
    byte STATUS_EATING_ACCEPTED = 9;
    byte STATUS_SHEEP_EATING_GRASS = 10;
    byte STATUS_TNT_IGNITE_SOUND = 10;
    byte STATUS_IRON_GOLEM_HANDING_OVER_ROSE = 11;
    byte STATUS_VILLAGER_MATING = 12;
    byte STATUS_VILLAGER_ANGRY = 13;
    byte STATUS_VILLAGER_HAPPY = 14;
    byte STATUS_WITCH_MAGIC_PARTICLES = 15;
    byte STATUS_ZOMBIE_VILLAGER_CONVERSION = 16;
    byte STATUS_FIREWORK_EXPLODING = 17;
    byte STATUS_ANIMAL_IN_LOVE = 18;
    byte STATUS_RESET_SQUID_ROTATION = 19;
    byte STATUS_SPAWN_EXPLOSION_PARTICLES = 20;
    byte STATUS_GUARDIAN_SOUND = 21;
    byte STATUS_ENABLE_REDUCED_DEBUG = 22;
    byte STATUS_DISABLE_REDUCED_DEBUG = 23;

    // Protocol version 107+
    byte STATUS_OP_LEVEL_0 = 24;
    byte STATUS_OP_LEVEL_1 = 25;
    byte STATUS_OP_LEVEL_2 = 26;
    byte STATUS_OP_LEVEL_3 = 27;
    byte STATUS_OP_LEVEL_4 = 28;

    int getEntityId();

    void setEntityId(int entityId);

    byte getEntityStatus();

    void setEntityStatus(byte entityStatus);

    @Override
    default Class<? extends Packet> getModelClass() {
        return EntityStatusClientboundV47.class;
    }
}
