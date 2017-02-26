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

public class ParticleId {

    // Protocol version 47+
    public static final int PARTICLE_EXPLODE = 0;
    public static final int PARTICLE_LARGE_EXPLOSION = 1;
    public static final int PARTICLE_HUGE_EXPLOSION = 2;
    public static final int PARTICLE_FIREWORKS_SPARK = 3;
    public static final int PARTICLE_BUBBLE = 4;
    public static final int PARTICLE_SPLASH = 5;
    public static final int PARTICLE_WAKE = 6;
    public static final int PARTICLE_SUSPENDED = 7;
    public static final int PARTICLE_DEPTH_SUSPENDED = 8;
    public static final int PARTICLE_CRITICAL_HIT = 9;
    public static final int PARTICLE_MAGIC_CRITICAL_HIT = 10;
    public static final int PARTICLE_SMOKE = 11;
    public static final int PARTICLE_LARGE_SMOKE = 12;
    public static final int PARTICLE_SPELL = 13;
    public static final int PARTICLE_INSTANT_SPELL = 14;
    public static final int PARTICLE_MOB_SPELL = 15;
    public static final int PARTICLE_MOB_SPELL_AMBIENT = 16;
    public static final int PARTICLE_WITCH_MAGIC = 17;
    public static final int PARTICLE_DRIP_WATER = 18;
    public static final int PARTICLE_DRIP_LAVA = 19;
    public static final int PARTICLE_ANGRY_VILLAGER = 20;
    public static final int PARTICLE_HAPPY_VILLAGER = 21;
    public static final int PARTICLE_TOWN_AURA = 22;
    public static final int PARTICLE_NOTE = 23;
    public static final int PARTICLE_PORTAL = 24;
    public static final int PARTICLE_ENCHANTMENT_TABLE = 25;
    public static final int PARTICLE_FLAME = 26;
    public static final int PARTICLE_LAVA = 27;
    public static final int PARTICLE_FOOD_STEP = 28;
    public static final int PARTICLE_CLOUD = 29;
    public static final int PARTICLE_RED_DUST = 30;
    public static final int PARTICLE_SNOWBALL_POOF = 31;
    public static final int PARTICLE_SNOW_SHOVEL = 32;
    public static final int PARTICLE_SLIME = 33;
    public static final int PARTICLE_HEART = 34;
    public static final int PARTICLE_BARRIER = 35;
    public static final int PARTICLE_ICON_CRACK = 36;
    public static final int PARTICLE_BLOCK_CRACK = 37;
    public static final int PARTICLE_BLOCK_DUST = 38;
    public static final int PARTICLE_DROPLET = 39;
    public static final int PARTICLE_TAKE = 40;
    public static final int PARTICLE_MOB_APPEaRANCE = 41;

    // Protocol version 107+
    public static final int PARTICLE_DRAGON_BREATH = 42;
    public static final int PARTICLE_END_ROD = 43;
    public static final int PARTICLE_DAMAGE_INDICATOR = 44;
    public static final int PARTICLE_SWEEP_ATTACK = 45;

    private ParticleId() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }
}
