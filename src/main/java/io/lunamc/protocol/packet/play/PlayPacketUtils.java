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
        Map<Class<?>, Integer> protocolV47ClientboundPacketIds = new HashMap<>();
        protocolV47ClientboundPacketIds.put(KeepAliveMultiboundV47.class, 0x00);
        protocolV47ClientboundPacketIds.put(JoinGameClientboundV47.class, 0x01);
        protocolV47ClientboundPacketIds.put(ChatMessageClientboundV47.class, 0x02);
        protocolV47ClientboundPacketIds.put(TimeUpdateClientboundV47.class, 0x03);
        protocolV47ClientboundPacketIds.put(EntityEquipmentClientboundV47.class, 0x04);
        protocolV47ClientboundPacketIds.put(SpawnPositionClientboundV47.class, 0x05);
        protocolV47ClientboundPacketIds.put(UpdateHealthClientboundV47.class, 0x06);
        protocolV47ClientboundPacketIds.put(RespawnClientboundV47.class, 0x07);
        protocolV47ClientboundPacketIds.put(PlayerPositionAndLookClientboundV47.class, 0x08);
        protocolV47ClientboundPacketIds.put(HeldItemChangeMultiboundV47.class, 0x09);
        protocolV47ClientboundPacketIds.put(UseBedClientboundV47.class, 0x0a);
        protocolV47ClientboundPacketIds.put(AnimationClientboundV47.class, 0x0b);
        protocolV47ClientboundPacketIds.put(SpawnPlayerClientboundV47.class, 0x0c);
        protocolV47ClientboundPacketIds.put(CollectItemClientboundV47.class, 0x0d);
        protocolV47ClientboundPacketIds.put(SpawnObjectClientboundV47.class, 0x0e);
        protocolV47ClientboundPacketIds.put(SpawnMobClientboundV47.class, 0x0f);
        protocolV47ClientboundPacketIds.put(SpawnPaintingClientboundV47.class, 0x10);
        protocolV47ClientboundPacketIds.put(SpawnExperienceOrbClientboundV47.class, 0x11);
        protocolV47ClientboundPacketIds.put(EntityVelocityClientboundV47.class, 0x12);
        protocolV47ClientboundPacketIds.put(DestroyEntitiesClientboundV47.class, 0x13);
        protocolV47ClientboundPacketIds.put(EntityClientboundV47.class, 0x14);
        protocolV47ClientboundPacketIds.put(EntityRelativeMoveClientboundV47.class, 0x15);
        protocolV47ClientboundPacketIds.put(EntityLookClientboundV47.class, 0x16);
        protocolV47ClientboundPacketIds.put(EntityLookAndRelativeMoveClientboundV47.class, 0x17);
        protocolV47ClientboundPacketIds.put(EntityTeleportClientboundV47.class, 0x18);
        protocolV47ClientboundPacketIds.put(EntityHeadLookClientboundV47.class, 0x19);
        protocolV47ClientboundPacketIds.put(EntityStatusClientboundV47.class, 0x1a);
        protocolV47ClientboundPacketIds.put(AttachEntityClientboundV47.class, 0x1b);
        protocolV47ClientboundPacketIds.put(EntityMetadataClientboundV47.class, 0x1c);
        protocolV47ClientboundPacketIds.put(EntityEffectClientboundV47.class, 0x1d);
        protocolV47ClientboundPacketIds.put(RemoveEntityEffectClientboundV47.class, 0x1e);
        protocolV47ClientboundPacketIds.put(SetExperienceClientboundV47.class, 0x1f);
        protocolV47ClientboundPacketIds.put(EntityPropertiesClientboundV47.class, 0x20);
        protocolV47ClientboundPacketIds.put(ChunkDataClientboundV47.class, 0x21);
        protocolV47ClientboundPacketIds.put(MultiBlockChangeClientboundV47.class, 0x22);
        protocolV47ClientboundPacketIds.put(BlockChangeClientboundV47.class, 0x23);
        protocolV47ClientboundPacketIds.put(BlockActionClientboundV47.class, 0x24);
        protocolV47ClientboundPacketIds.put(BlockBreakAnimationClientboundV47.class, 0x25);
        protocolV47ClientboundPacketIds.put(MapChunkBulkClientboundV47.class, 0x26);
        protocolV47ClientboundPacketIds.put(ExplosionClientboundV47.class, 0x27);
        protocolV47ClientboundPacketIds.put(EffectClientboundV47.class, 0x28);
        protocolV47ClientboundPacketIds.put(SoundEffectClientboundV47.class, 0x29);
        protocolV47ClientboundPacketIds.put(ParticleClientboundV47.class, 0x2a);
        protocolV47ClientboundPacketIds.put(ChangeGameStateClientboundV47.class, 0x2b);
        protocolV47ClientboundPacketIds.put(SpawnGlobalEntityClientboundV47.class, 0x2c);
        protocolV47ClientboundPacketIds.put(OpenWindowClientboundV47.class, 0x2d);
        protocolV47ClientboundPacketIds.put(CloseWindowMultiboundV47.class, 0x2e);
        protocolV47ClientboundPacketIds.put(SetSlotClientboundV47.class, 0x2f);
        protocolV47ClientboundPacketIds.put(WindowItemsClientboundV47.class, 0x30);
        protocolV47ClientboundPacketIds.put(WindowPropertyClientboundV47.class, 0x31);
        protocolV47ClientboundPacketIds.put(ConfirmTransactionMultiboundV47.class, 0x32);
        protocolV47ClientboundPacketIds.put(UpdateSignMultiboundV47.class, 0x33);
        protocolV47ClientboundPacketIds.put(MapClientboundV47.class, 0x34);
        protocolV47ClientboundPacketIds.put(UpdateBlockEntityClientboundV47.class, 0x35);
        protocolV47ClientboundPacketIds.put(OpenSignEditorClientboundV47.class, 0x36);
        protocolV47ClientboundPacketIds.put(StatisticsClientboundV47.class, 0x37);
        protocolV47ClientboundPacketIds.put(PlayerListItemClientboundV47.class, 0x38);
        protocolV47ClientboundPacketIds.put(PlayerAbilitiesClientboundV47.class, 0x39);
        protocolV47ClientboundPacketIds.put(TabCompleteClientboundV47.class, 0x3a);
        protocolV47ClientboundPacketIds.put(ScoreboardObjectiveClientboundV47.class, 0x3b);
        protocolV47ClientboundPacketIds.put(UpdateScoreClientboundV47.class, 0x3c);
        protocolV47ClientboundPacketIds.put(DisplayScoreboardClientboundV47.class, 0x3d);
        protocolV47ClientboundPacketIds.put(TeamsClientboundV47.class, 0x3e);
        protocolV47ClientboundPacketIds.put(PluginMessageMultiboundV47.class, 0x3f);
        protocolV47ClientboundPacketIds.put(DisconnectClientboundV47.class, 0x40);
        protocolV47ClientboundPacketIds.put(ServerDifficultyClientboundV47.class, 0x41);
        protocolV47ClientboundPacketIds.put(CombatEventClientboundV47.class, 0x42);
        protocolV47ClientboundPacketIds.put(CameraClientboundV47.class, 0x43);
        protocolV47ClientboundPacketIds.put(WorldBorderClientboundV47.class, 0x44);
        protocolV47ClientboundPacketIds.put(TitleClientboundV47.class, 0x45);
        // Skip 0x46 (Set Compression) since it's broken
        protocolV47ClientboundPacketIds.put(PlayerListHeaderAndFooterClientboundV47.class, 0x47);
        protocolV47ClientboundPacketIds.put(ResourcePackSendClientboundV47.class, 0x48);
        protocolV47ClientboundPacketIds.put(UpdateEntityNbtClientboundV47.class, 0x49);
        PROTOCOL_V47_CLIENTBOUND_PACKET_IDS = Collections.unmodifiableMap(protocolV47ClientboundPacketIds);

        Map<Class<?>, Integer> protocolV47ServerboundPacketIds = new HashMap<>();
        protocolV47ServerboundPacketIds.put(KeepAliveMultiboundV47.class, 0x00);
        protocolV47ServerboundPacketIds.put(ChatMessageServerboundV47.class, 0x01);
        protocolV47ServerboundPacketIds.put(UseEntityServerboundV47.class, 0x02);
        protocolV47ServerboundPacketIds.put(PlayerServerboundV47.class, 0x03);
        protocolV47ServerboundPacketIds.put(PlayerPositionServerboundV47.class, 0x04);
        protocolV47ServerboundPacketIds.put(PlayerLookServerboundV47.class, 0x05);
        protocolV47ServerboundPacketIds.put(PlayerPositionAndLookServerboundV47.class, 0x06);
        protocolV47ServerboundPacketIds.put(PlayerDiggingServerboundV47.class, 0x07);
        protocolV47ServerboundPacketIds.put(PlayerBlockPlacementServerboundV47.class, 0x08);
        protocolV47ServerboundPacketIds.put(HeldItemChangeMultiboundV47.class, 0x09);
        protocolV47ServerboundPacketIds.put(AnimationServerboundV47.class, 0x0a);
        protocolV47ServerboundPacketIds.put(EntityActionServerboundV47.class, 0x0b);
        protocolV47ServerboundPacketIds.put(SteerVehicleServerboundV47.class, 0x0c);
        protocolV47ServerboundPacketIds.put(CloseWindowMultiboundV47.class, 0x0d);
        protocolV47ServerboundPacketIds.put(ClickWindowServerboundV47.class, 0x0e);
        protocolV47ServerboundPacketIds.put(ConfirmTransactionMultiboundV47.class, 0x0f);
        protocolV47ServerboundPacketIds.put(CreativeInventoryActionServerboundV47.class, 0x10);
        protocolV47ServerboundPacketIds.put(EnchantItemServerboundV47.class, 0x11);
        protocolV47ServerboundPacketIds.put(UpdateSignMultiboundV47.class, 0x12);
        protocolV47ServerboundPacketIds.put(PlayerAbilitiesServerboundV47.class, 0x13);
        protocolV47ServerboundPacketIds.put(TabCompleteServerboundV47.class, 0x14);
        protocolV47ServerboundPacketIds.put(ClientSettingsServerboundV47.class, 0x15);
        protocolV47ServerboundPacketIds.put(ClientStatusServerboundV47.class, 0x16);
        protocolV47ServerboundPacketIds.put(PluginMessageMultiboundV47.class, 0x17);
        protocolV47ServerboundPacketIds.put(SpectateServerboundV47.class, 0x18);
        protocolV47ServerboundPacketIds.put(ResourcePackStatusServerboundV47.class, 0x19);
        PROTOCOL_V47_SERVERBOUND_PACKET_IDS = Collections.unmodifiableMap(protocolV47ServerboundPacketIds);

        Map<Integer, Function<PlayPacketAllocator, Packet>> protocolV47ClientboundPacketAllocators = new HashMap<>();
        protocolV47ClientboundPacketAllocators.put(0x00, PlayPacketAllocator::getKeepAliveMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x01, PlayPacketAllocator::getJoinGameClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x02, PlayPacketAllocator::getChatMessageClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x03, PlayPacketAllocator::getTimeUpdateClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x04, PlayPacketAllocator::getEntityEquipmentClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x05, PlayPacketAllocator::getSpawnPositionClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x06, PlayPacketAllocator::getUpdateHealthClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x07, PlayPacketAllocator::getRespawnClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x08, PlayPacketAllocator::getPlayerPositionAndLookClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x09, PlayPacketAllocator::getHeldItemChangeMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x0a, PlayPacketAllocator::getUseBedClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x0b, PlayPacketAllocator::getAnimationClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x0c, PlayPacketAllocator::getSpawnPlayerClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x0d, PlayPacketAllocator::getCollectItemClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x0e, PlayPacketAllocator::getSpawnObjectClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x0f, PlayPacketAllocator::getSpawnMobClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x10, PlayPacketAllocator::getSpawnPaintingClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x11, PlayPacketAllocator::getSpawnExperienceOrbClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x12, PlayPacketAllocator::getEntityVelocityClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x13, PlayPacketAllocator::getDestroyEntitiesClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x14, PlayPacketAllocator::getEntityClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x15, PlayPacketAllocator::getEntityRelativeMoveClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x16, PlayPacketAllocator::getEntityLookClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x17, PlayPacketAllocator::getEntityLookAndRelativeMoveClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x18, PlayPacketAllocator::getEntityTeleportClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x19, PlayPacketAllocator::getEntityHeadLookClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x1a, PlayPacketAllocator::getEntityStatusClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x1b, PlayPacketAllocator::getAttachEntityClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x1c, PlayPacketAllocator::getEntityMetadataClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x1d, PlayPacketAllocator::getEntityEffectClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x1e, PlayPacketAllocator::getRemoveEntityEffectClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x1f, PlayPacketAllocator::getSetExperienceClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x20, PlayPacketAllocator::getEntityPropertiesClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x21, PlayPacketAllocator::getChunkDataClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x22, PlayPacketAllocator::getMultiBlockChangeMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x23, PlayPacketAllocator::getBlockChangeClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x24, PlayPacketAllocator::getBlockActionClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x25, PlayPacketAllocator::getBlockBreakAnimationClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x26, PlayPacketAllocator::getMapChunkBulkClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x27, PlayPacketAllocator::getExplosionClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x28, PlayPacketAllocator::getEffectClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x29, PlayPacketAllocator::getSoundEffectClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x2a, PlayPacketAllocator::getParticleClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x2b, PlayPacketAllocator::getChangeGameStateClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x2c, PlayPacketAllocator::getSpawnGlobalEntityClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x2d, PlayPacketAllocator::getOpenWindowClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x2e, PlayPacketAllocator::getCloseWindowMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x2f, PlayPacketAllocator::getSetSlotClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x30, PlayPacketAllocator::getWindowItemsClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x31, PlayPacketAllocator::getWindowPropertyClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x32, PlayPacketAllocator::getConfirmTransactionMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x33, PlayPacketAllocator::getUpdateSignMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x34, PlayPacketAllocator::getMapClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x35, PlayPacketAllocator::getUpdateBlockEntityClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x36, PlayPacketAllocator::getOpenSignEditorClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x37, PlayPacketAllocator::getStatisticsClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x38, PlayPacketAllocator::getPlayerListItemClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x39, PlayPacketAllocator::getPlayerAbilitiesClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x3a, PlayPacketAllocator::getTabCompleteClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x3b, PlayPacketAllocator::getScoreboardObjectiveClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x3c, PlayPacketAllocator::getUpdateScoreClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x3d, PlayPacketAllocator::getDisplayScoreboardClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x3e, PlayPacketAllocator::getTeamsClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x3f, PlayPacketAllocator::getPluginMessageMultiboundV47);
        protocolV47ClientboundPacketAllocators.put(0x40, PlayPacketAllocator::getDisconnectClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x41, PlayPacketAllocator::getServerDifficultyClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x42, PlayPacketAllocator::getCombatEventClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x43, PlayPacketAllocator::getCameraClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x44, PlayPacketAllocator::getWorldBorderClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x45, PlayPacketAllocator::getTitleClientboundV47);
        // Skip 0x46 (Set Compression) since it's broken
        protocolV47ClientboundPacketAllocators.put(0x47, PlayPacketAllocator::getPlayerListHeaderAndFooterClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x48, PlayPacketAllocator::getResourcePackSendClientboundV47);
        protocolV47ClientboundPacketAllocators.put(0x49, PlayPacketAllocator::getUpdateEntityNbtClientboundV47);
        PROTOCOL_V47_CLIENTBOUND_PACKET_ALLOCATORS = Collections.unmodifiableMap(protocolV47ClientboundPacketAllocators);

        Map<Integer, Function<PlayPacketAllocator, Packet>> protocolV47ServerboundPacketAllocators = new HashMap<>();
        protocolV47ServerboundPacketAllocators.put(0x00, PlayPacketAllocator::getKeepAliveMultiboundV47);
        protocolV47ServerboundPacketAllocators.put(0x01, PlayPacketAllocator::getChatMessageServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x02, PlayPacketAllocator::getUseEntityServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x03, PlayPacketAllocator::getPlayerServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x04, PlayPacketAllocator::getPlayerPositionServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x05, PlayPacketAllocator::getPlayerLookServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x06, PlayPacketAllocator::getPlayerPositionAndLookServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x07, PlayPacketAllocator::getPlayerDiggingServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x08, PlayPacketAllocator::getPlayerBlockPlacementServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x09, PlayPacketAllocator::getHeldItemChangeMultiboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0a, PlayPacketAllocator::getAnimationServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0b, PlayPacketAllocator::getEntityActionServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0c, PlayPacketAllocator::getSteerVehicleServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0d, PlayPacketAllocator::getCloseWindowMultiboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0e, PlayPacketAllocator::getClickWindowServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x0f, PlayPacketAllocator::getConfirmTransactionMultiboundV47);
        protocolV47ServerboundPacketAllocators.put(0x10, PlayPacketAllocator::getCreativeInventoryActionServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x11, PlayPacketAllocator::getEnchantItemServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x12, PlayPacketAllocator::getUpdateSignMultiboundV47);
        protocolV47ServerboundPacketAllocators.put(0x13, PlayPacketAllocator::getPlayerAbilitiesServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x14, PlayPacketAllocator::getTabCompleteServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x15, PlayPacketAllocator::getClientSettingsServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x16, PlayPacketAllocator::getClientStatusServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x17, PlayPacketAllocator::getPluginMessageMultiboundV47);
        protocolV47ServerboundPacketAllocators.put(0x18, PlayPacketAllocator::getSpectateServerboundV47);
        protocolV47ServerboundPacketAllocators.put(0x19, PlayPacketAllocator::getResourcePackStatusServerboundV47);
        PROTOCOL_V47_SERVERBOUND_PACKET_ALLOCATORS = Collections.unmodifiableMap(protocolV47ServerboundPacketAllocators);
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
