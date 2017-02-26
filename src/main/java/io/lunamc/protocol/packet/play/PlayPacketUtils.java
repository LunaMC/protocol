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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class PlayPacketUtils {

    private static final Map<Class<?>, Integer> PROTOCOL_V47_SERVERBOUND_PACKET_IDS;
    private static final Map<Class<?>, Integer> PROTOCOL_V47_CLIENTBOUND_PACKET_IDS;
    private static final Map<Integer, Function<PlayPacketAllocator, Packet>> PROTOCOL_V47_SERVERBOUND_PACKET_ALLOCATORS;
    private static final Map<Integer, Function<PlayPacketAllocator, Packet>> PROTOCOL_V47_CLIENTBOUND_PACKET_ALLOCATORS;

    static {
        Map<Class<?>, Integer> protocolV47ServerboundPacketIds = new HashMap<>();
        protocolV47ServerboundPacketIds.put(KeepAliveClientboundV47.class, 0x00);
        protocolV47ServerboundPacketIds.put(JoinGameClientboundV47.class, 0x01);
        protocolV47ServerboundPacketIds.put(ChatMessageClientboundV47.class, 0x02);
        protocolV47ServerboundPacketIds.put(TimeUpdateClientboundV47.class, 0x03);
        protocolV47ServerboundPacketIds.put(EntityEquipmentClientboundV47.class, 0x04);
        protocolV47ServerboundPacketIds.put(SpawnPositionClientboundV47.class, 0x05);
        protocolV47ServerboundPacketIds.put(UpdateHealthClientboundV47.class, 0x06);
        protocolV47ServerboundPacketIds.put(RespawnClientboundV47.class, 0x07);
        protocolV47ServerboundPacketIds.put(PlayerPositionAndLookClientboundV47.class, 0x08);
        protocolV47ServerboundPacketIds.put(HeldItemChangeClientboundV47.class, 0x09);
        protocolV47ServerboundPacketIds.put(UseBedClientboundV47.class, 0x0a);
        protocolV47ServerboundPacketIds.put(AnimationClientboundV47.class, 0x0b);
        protocolV47ServerboundPacketIds.put(SpawnPlayerClientboundV47.class, 0x0c);
        protocolV47ServerboundPacketIds.put(CollectItemClientboundV47.class, 0x0d);
        protocolV47ServerboundPacketIds.put(SpawnObjectClientboundV47.class, 0x0e);
        protocolV47ServerboundPacketIds.put(SpawnMobClientboundV47.class, 0x0f);
        protocolV47ServerboundPacketIds.put(SpawnPaintingClientboundV47.class, 0x10);
        protocolV47ServerboundPacketIds.put(SpawnExperienceOrbClientboundV47.class, 0x11);
        protocolV47ServerboundPacketIds.put(EntityVelocityClientboundV47.class, 0x12);
        protocolV47ServerboundPacketIds.put(DestroyEntitiesClientboundV47.class, 0x13);
        protocolV47ServerboundPacketIds.put(EntityClientboundV47.class, 0x14);
        protocolV47ServerboundPacketIds.put(EntityRelativeMoveClientboundV47.class, 0x15);
        protocolV47ServerboundPacketIds.put(EntityLookClientboundV47.class, 0x16);
        protocolV47ServerboundPacketIds.put(EntityLookAndRelativeMoveClientboundV47.class, 0x17);
        protocolV47ServerboundPacketIds.put(EntityTeleportClientboundV47.class, 0x18);
        protocolV47ServerboundPacketIds.put(EntityHeadLookClientboundV47.class, 0x19);
        protocolV47ServerboundPacketIds.put(EntityStatusClientboundV47.class, 0x1a);
        protocolV47ServerboundPacketIds.put(AttachEntityClientboundV47.class, 0x1b);
        protocolV47ServerboundPacketIds.put(EntityMetadataClientboundV47.class, 0x1c);
        protocolV47ServerboundPacketIds.put(EntityEffectClientboundV47.class, 0x1d);
        protocolV47ServerboundPacketIds.put(RemoveEntityEffectClientboundV47.class, 0x1e);
        protocolV47ServerboundPacketIds.put(SetExperienceClientboundV47.class, 0x1f);
        protocolV47ServerboundPacketIds.put(EntityPropertiesClientboundV47.class, 0x20);
        protocolV47ServerboundPacketIds.put(ChunkDataClientboundV47.class, 0x21);
        protocolV47ServerboundPacketIds.put(MultiBlockChangeClientboundV47.class, 0x22);
        protocolV47ServerboundPacketIds.put(BlockChangeClientboundV47.class, 0x23);
        protocolV47ServerboundPacketIds.put(BlockActionClientboundV47.class, 0x24);
        protocolV47ServerboundPacketIds.put(BlockBreakAnimationClientboundV47.class, 0x25);
        protocolV47ServerboundPacketIds.put(MapChunkBulkClientboundV47.class, 0x26);
        protocolV47ServerboundPacketIds.put(ExplosionClientboundV47.class, 0x27);
        protocolV47ServerboundPacketIds.put(EffectClientboundV47.class, 0x28);
        protocolV47ServerboundPacketIds.put(SoundEffectClientboundV47.class, 0x29);
        protocolV47ServerboundPacketIds.put(ParticleClientboundV47.class, 0x2a);
        protocolV47ServerboundPacketIds.put(ChangeGameStateClientboundV47.class, 0x2b);
        protocolV47ServerboundPacketIds.put(SpawnGlobalEntityClientboundV47.class, 0x2c);
        protocolV47ServerboundPacketIds.put(OpenWindowClientboundV47.class, 0x2d);
        protocolV47ServerboundPacketIds.put(CloseWindowClientboundV47.class, 0x2e);
        protocolV47ServerboundPacketIds.put(SetSlotClientboundV47.class, 0x2f);
        protocolV47ServerboundPacketIds.put(WindowItemsClientboundV47.class, 0x30);
        protocolV47ServerboundPacketIds.put(WindowPropertyClientboundV47.class, 0x31);
        protocolV47ServerboundPacketIds.put(ConfirmTransactionClientboundV47.class, 0x32);
        protocolV47ServerboundPacketIds.put(UpdateSignClientboundV47.class, 0x33);
        protocolV47ServerboundPacketIds.put(MapClientboundV47.class, 0x34);
        protocolV47ServerboundPacketIds.put(UpdateBlockEntityClientboundV47.class, 0x35);
        protocolV47ServerboundPacketIds.put(OpenSignEditorClientboundV47.class, 0x36);
        protocolV47ServerboundPacketIds.put(StatisticsClientboundV47.class, 0x37);
        protocolV47ServerboundPacketIds.put(PlayerListItemClientboundV47.class, 0x38);
        protocolV47ServerboundPacketIds.put(PlayerAbilitiesClientboundV47.class, 0x39);
        protocolV47ServerboundPacketIds.put(TabCompleteClientboundV47.class, 0x3a);
        protocolV47ServerboundPacketIds.put(ScoreboardObjectiveClientboundV47.class, 0x3b);
        protocolV47ServerboundPacketIds.put(UpdateScoreClientboundV47.class, 0x3c);
        protocolV47ServerboundPacketIds.put(DisplayScoreboardClientboundV47.class, 0x3d);
        protocolV47ServerboundPacketIds.put(TeamsClientboundV47.class, 0x3e);
        protocolV47ServerboundPacketIds.put(PluginMessageClientboundV47.class, 0x3f);
        protocolV47ServerboundPacketIds.put(DisconnectClientboundV47.class, 0x40);
        protocolV47ServerboundPacketIds.put(ServerDifficultyClientboundV47.class, 0x41);
        protocolV47ServerboundPacketIds.put(CombatEventClientboundV47.class, 0x42);
        protocolV47ServerboundPacketIds.put(CameraClientboundV47.class, 0x43);
        protocolV47ServerboundPacketIds.put(WorldBorderClientboundV47.class, 0x44);
        protocolV47ServerboundPacketIds.put(TitleClientboundV47.class, 0x45);
        // Skip 0x46 (Set Compression) since it's broken
        protocolV47ServerboundPacketIds.put(PlayerListHeaderAndFooterClientboundV47.class, 0x47);
        protocolV47ServerboundPacketIds.put(ResourcePackSendClientboundV47.class, 0x48);
        protocolV47ServerboundPacketIds.put(UpdateEntityNbtClientboundV47.class, 0x49);
        PROTOCOL_V47_SERVERBOUND_PACKET_IDS = Collections.unmodifiableMap(protocolV47ServerboundPacketIds);

        Map<Class<?>, Integer> protocolV47ClientboundPacketIds = new HashMap<>();
        PROTOCOL_V47_CLIENTBOUND_PACKET_IDS = Collections.unmodifiableMap(protocolV47ClientboundPacketIds);

        Map<Integer, Function<PlayPacketAllocator, Packet>> protocolV47ServerboundPacketAllocators = new HashMap<>();
        protocolV47ServerboundPacketAllocators.put(0x00, PlayPacketAllocator::getKeepAliveClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x01, PlayPacketAllocator::getJoinGameClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x02, PlayPacketAllocator::getChatMessageClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x03, PlayPacketAllocator::getTimeUpdateClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x04, PlayPacketAllocator::getEntityEquipmentClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x05, PlayPacketAllocator::getSpawnPositionClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x06, PlayPacketAllocator::getUpdateHealthClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x07, PlayPacketAllocator::getRespawnClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x08, PlayPacketAllocator::getPlayerPositionAndLookClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x09, PlayPacketAllocator::getHeldItemChangeClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0a, PlayPacketAllocator::getUseBedClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0b, PlayPacketAllocator::getAnimationClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0c, PlayPacketAllocator::getSpawnPlayerClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0d, PlayPacketAllocator::getCollectItemClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0e, PlayPacketAllocator::getSpawnObjectClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0f, PlayPacketAllocator::getSpawnMobClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x10, PlayPacketAllocator::getSpawnPaintingClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x11, PlayPacketAllocator::getSpawnExperienceOrbClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x12, PlayPacketAllocator::getEntityVelocityClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x13, PlayPacketAllocator::getDestroyEntitiesClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x14, PlayPacketAllocator::getEntityClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x15, PlayPacketAllocator::getEntityRelativeMoveClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x16, PlayPacketAllocator::getEntityLookClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x17, PlayPacketAllocator::getEntityLookAndRelativeMoveClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x18, PlayPacketAllocator::getEntityTeleportClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x19, PlayPacketAllocator::getEntityHeadLookClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x1a, PlayPacketAllocator::getEntityStatusClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x1b, PlayPacketAllocator::getAttachEntityClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x1c, PlayPacketAllocator::getEntityMetadataClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x1d, PlayPacketAllocator::getEntityEffectClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x1e, PlayPacketAllocator::getRemoveEntityEffectClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x1f, PlayPacketAllocator::getSetExperienceClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x20, PlayPacketAllocator::getEntityPropertiesClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x21, PlayPacketAllocator::getChunkDataClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x22, PlayPacketAllocator::getMultiBlockChangeClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x23, PlayPacketAllocator::getBlockChangeClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x24, PlayPacketAllocator::getBlockActionClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x25, PlayPacketAllocator::getBlockBreakAnimationClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x26, PlayPacketAllocator::getMapChunkBulkClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x27, PlayPacketAllocator::getExplosionClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x28, PlayPacketAllocator::getEffectClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x29, PlayPacketAllocator::getSoundEffectClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x2a, PlayPacketAllocator::getParticleClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x2b, PlayPacketAllocator::getChangeGameStateClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x2c, PlayPacketAllocator::getSpawnGlobalEntityClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x2d, PlayPacketAllocator::getOpenWindowClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x2e, PlayPacketAllocator::getCloseWindowClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x2f, PlayPacketAllocator::getSetSlotClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x30, PlayPacketAllocator::getWindowItemsClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x31, PlayPacketAllocator::getWindowPropertyClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x32, PlayPacketAllocator::getConfirmTransactionClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x33, PlayPacketAllocator::getUpdateHealthClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x34, PlayPacketAllocator::getMapClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x35, PlayPacketAllocator::getUpdateBlockEntityClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x36, PlayPacketAllocator::getOpenSignEditorClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x37, PlayPacketAllocator::getStatisticsClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x38, PlayPacketAllocator::getPlayerListItemClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x39, PlayPacketAllocator::getPlayerAbilitiesClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x3a, PlayPacketAllocator::getTabCompleteClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x3b, PlayPacketAllocator::getScoreboardObjectiveClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x3c, PlayPacketAllocator::getUpdateScoreClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x3d, PlayPacketAllocator::getDisplayScoreboardClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x3e, PlayPacketAllocator::getTeamsClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x3f, PlayPacketAllocator::getPluginMessageClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x40, PlayPacketAllocator::getDisconnectClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x41, PlayPacketAllocator::getServerDifficultyClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x42, PlayPacketAllocator::getCombatEventClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x43, PlayPacketAllocator::getCameraClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x44, PlayPacketAllocator::getWorldBorderClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x45, PlayPacketAllocator::getTitleClientboundV47);
        // Skip 0x46 (Set Compression) since it's broken
        protocolV47ServerboundPacketAllocators.put(0x47, PlayPacketAllocator::getPlayerListHeaderAndFooterClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x48, PlayPacketAllocator::getResourcePackSendClientboundV47);
        protocolV47ServerboundPacketAllocators.put(0x49, PlayPacketAllocator::getUpdateEntityNbtClientboundV47);
        PROTOCOL_V47_SERVERBOUND_PACKET_ALLOCATORS = Collections.unmodifiableMap(protocolV47ServerboundPacketAllocators);

        Map<Integer, Function<PlayPacketAllocator, Packet>> protocolV47ClientboundPacketAllocators = new HashMap<>();
        PROTOCOL_V47_CLIENTBOUND_PACKET_ALLOCATORS = Collections.unmodifiableMap(protocolV47ClientboundPacketAllocators);
    }

    private PlayPacketUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static int getServerboundPacketId(Packet packet, int protocolVersion) {
        Class<? extends Packet> modelClass = packet.getModelClass();
        if (protocolVersion >= 47) {
            Integer packetId = PROTOCOL_V47_SERVERBOUND_PACKET_IDS.get(modelClass);
            if (packetId != null)
                return packetId;
        }
        throw new NoSuchElementException("Cannot find packet id for " + modelClass.getName() + " for protocol version " + protocolVersion);
    }

    public static int getClientboundPacketId(Packet packet, int protocolVersion) {
        Class<? extends Packet> modelClass = packet.getModelClass();
        if (protocolVersion >= 47) {
            Integer packetId = PROTOCOL_V47_CLIENTBOUND_PACKET_IDS.get(modelClass);
            if (packetId != null)
                return packetId;
        }
        throw new NoSuchElementException("Cannot find packet id for " + modelClass.getName() + " for protocol version " + protocolVersion);
    }

    public static Packet allocateServerboundPacket(PlayPacketAllocator allocator, int packetId, int protocolVersion) {
        if (protocolVersion >= 47) {
            Function<PlayPacketAllocator, Packet> factory = PROTOCOL_V47_SERVERBOUND_PACKET_ALLOCATORS.get(packetId);
            if (factory != null)
                return factory.apply(allocator);
        }
        throw new NoSuchElementException("Cannot allocate serverbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }

    public static Packet allocateClientboundPacket(PlayPacketAllocator allocator, int packetId, int protocolVersion) {
        if (protocolVersion >= 47) {
            Function<PlayPacketAllocator, Packet> factory = PROTOCOL_V47_CLIENTBOUND_PACKET_ALLOCATORS.get(packetId);
            if (factory != null)
                return factory.apply(allocator);
        }
        throw new NoSuchElementException("Cannot allocate clientbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }
}
