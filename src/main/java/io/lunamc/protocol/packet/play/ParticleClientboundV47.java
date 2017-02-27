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

public interface ParticleClientboundV47 extends Packet {

    // Protocol version 47+
    int PARTICLE_EXPLODE = 0;
    int PARTICLE_LARGE_EXPLOSION = 1;
    int PARTICLE_HUGE_EXPLOSION = 2;
    int PARTICLE_FIREWORKS_SPARK = 3;
    int PARTICLE_BUBBLE = 4;
    int PARTICLE_SPLASH = 5;
    int PARTICLE_WAKE = 6;
    int PARTICLE_SUSPENDED = 7;
    int PARTICLE_DEPTH_SUSPENDED = 8;
    int PARTICLE_CRITICAL_HIT = 9;
    int PARTICLE_MAGIC_CRITICAL_HIT = 10;
    int PARTICLE_SMOKE = 11;
    int PARTICLE_LARGE_SMOKE = 12;
    int PARTICLE_SPELL = 13;
    int PARTICLE_INSTANT_SPELL = 14;
    int PARTICLE_MOB_SPELL = 15;
    int PARTICLE_MOB_SPELL_AMBIENT = 16;
    int PARTICLE_WITCH_MAGIC = 17;
    int PARTICLE_DRIP_WATER = 18;
    int PARTICLE_DRIP_LAVA = 19;
    int PARTICLE_ANGRY_VILLAGER = 20;
    int PARTICLE_HAPPY_VILLAGER = 21;
    int PARTICLE_TOWN_AURA = 22;
    int PARTICLE_NOTE = 23;
    int PARTICLE_PORTAL = 24;
    int PARTICLE_ENCHANTMENT_TABLE = 25;
    int PARTICLE_FLAME = 26;
    int PARTICLE_LAVA = 27;
    int PARTICLE_FOOD_STEP = 28;
    int PARTICLE_CLOUD = 29;
    int PARTICLE_RED_DUST = 30;
    int PARTICLE_SNOWBALL_POOF = 31;
    int PARTICLE_SNOW_SHOVEL = 32;
    int PARTICLE_SLIME = 33;
    int PARTICLE_HEART = 34;
    int PARTICLE_BARRIER = 35;
    int PARTICLE_ICON_CRACK = 36;
    int PARTICLE_BLOCK_CRACK = 37;
    int PARTICLE_BLOCK_DUST = 38;
    int PARTICLE_DROPLET = 39;
    int PARTICLE_TAKE = 40;
    int PARTICLE_MOB_APPEaRANCE = 41;

    // Protocol version 107+
    int PARTICLE_DRAGON_BREATH = 42;
    int PARTICLE_END_ROD = 43;
    int PARTICLE_DAMAGE_INDICATOR = 44;
    int PARTICLE_SWEEP_ATTACK = 45;

    int getParticleId();

    void setParticleId(int particleId);

    boolean isLongDistance();

    void setLongDistance(boolean longDistance);

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getZ();

    void setZ(float z);

    float getOffsetX();

    void  setOffsetX(float offsetX);

    float getOffsetY();

    void setOffsetY(float offsetY);

    float getOffsetZ();

    void setOffsetZ(float offsetZ);

    float getParticleData();

    void setParticleData(float particleData);

    int getParticleCount();

    void setParticleCount(int particleCount);

    int[] getData();

    void setData(int[] data);

    @Override
    default Class<? extends Packet> getModelClass() {
        return ParticleClientboundV47.class;
    }
}
