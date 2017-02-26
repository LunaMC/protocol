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

public class EntityStatus {

    // Protocol version 47+
    public static final byte STATUS_RESET_MOB_SPAWN_MINECART_TIMER = 1;
    public static final byte STATUS_RABBIT_JUMP = 1;
    public static final byte STATUS_HURT = 2;
    public static final byte STATUS_DEAD = 3;
    public static final byte STATUS_IRON_GOLEM_THROWING_ARMS = 4;
    public static final byte STATUS_TAME_HEART_PARTICLES = 6;
    public static final byte STATUS_TAME_SMOKE_PARTICLES = 7;
    public static final byte STATUS_WOLF_SHAKING_WATER = 8;
    public static final byte STATUS_EATING_ACCEPTED = 9;
    public static final byte STATUS_SHEEP_EATING_GRASS = 10;
    public static final byte STATUS_TNT_IGNITE_SOUND = 10;
    public static final byte STATUS_IRON_GOLEM_HANDING_OVER_ROSE = 11;
    public static final byte STATUS_VILLAGER_MATING = 12;
    public static final byte STATUS_VILLAGER_ANGRY = 13;
    public static final byte STATUS_VILLAGER_HAPPY = 14;
    public static final byte STATUS_WITCH_MAGIC_PARTICLES = 15;
    public static final byte STATUS_ZOMBIE_VILLAGER_CONVERSION = 16;
    public static final byte STATUS_FIREWORK_EXPLODING = 17;
    public static final byte STATUS_ANIMAL_IN_LOVE = 18;
    public static final byte STATUS_RESET_SQUID_ROTATION = 19;
    public static final byte STATUS_SPAWN_EXPLOSION_PARTICLES = 20;
    public static final byte STATUS_GUARDIAN_SOUND = 21;
    public static final byte STATUS_ENABLE_REDUCED_DEBUG = 22;
    public static final byte STATUS_DISABLE_REDUCED_DEBUG = 23;

    // Protocol version 107+
    public static final byte STATUS_OP_LEVEL_0 = 24;
    public static final byte STATUS_OP_LEVEL_1 = 25;
    public static final byte STATUS_OP_LEVEL_2 = 26;
    public static final byte STATUS_OP_LEVEL_3 = 27;
    public static final byte STATUS_OP_LEVEL_4 = 28;

    private EntityStatus() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }
}
