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

import io.lunamc.protocol.internal.utils.PacketMapper;
import io.lunamc.protocol.packet.Packet;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class PlayPacketUtils {

    private static final PacketMapper<PlayPacketAllocator> V47_MAPPER;

    /*
    Helper: If you're copy and paste .register... calls you can do the following:

        1. Make sure the first argument (class reference) and the second argument (packet id) are set
        2. Replace the following regex:
            \.(register(?:Clientbound|Serverbound)Packet)\((\w+)\.class,\s*(0x[\da-f]{2}),\s*([a-z_][a-z0-9_]+)::[a-z_][a-z0-9_]+\)
           by
            .$1($2.class, $3, $4::get$2)
     */
    static {
        V47_MAPPER = PacketMapper.<PlayPacketAllocator>newBuilder()
                .registerClientboundPacket(KeepAliveMultiboundV47.class, 0x00, PlayPacketAllocator::getKeepAliveMultiboundV47)
                .registerClientboundPacket(JoinGameClientboundV47.class, 0x01, PlayPacketAllocator::getJoinGameClientboundV47)
                .registerClientboundPacket(ChatMessageClientboundV47.class, 0x02, PlayPacketAllocator::getChatMessageClientboundV47)
                .registerClientboundPacket(TimeUpdateClientboundV47.class, 0x03, PlayPacketAllocator::getTimeUpdateClientboundV47)
                .registerClientboundPacket(EntityEquipmentClientboundV47.class, 0x04, PlayPacketAllocator::getEntityEquipmentClientboundV47)
                .registerClientboundPacket(SpawnPositionClientboundV47.class, 0x05, PlayPacketAllocator::getSpawnPositionClientboundV47)
                .registerClientboundPacket(UpdateHealthClientboundV47.class, 0x06, PlayPacketAllocator::getUpdateHealthClientboundV47)
                .registerClientboundPacket(RespawnClientboundV47.class, 0x07, PlayPacketAllocator::getRespawnClientboundV47)
                .registerClientboundPacket(PlayerPositionAndLookClientboundV47.class, 0x08, PlayPacketAllocator::getPlayerPositionAndLookClientboundV47)
                .registerClientboundPacket(HeldItemChangeMultiboundV47.class, 0x09, PlayPacketAllocator::getHeldItemChangeMultiboundV47)
                .registerClientboundPacket(UseBedClientboundV47.class, 0x0a, PlayPacketAllocator::getUseBedClientboundV47)
                .registerClientboundPacket(AnimationClientboundV47.class, 0x0b, PlayPacketAllocator::getAnimationClientboundV47)
                .registerClientboundPacket(SpawnPlayerClientboundV47.class, 0x0c, PlayPacketAllocator::getSpawnPlayerClientboundV47)
                .registerClientboundPacket(CollectItemClientboundV47.class, 0x0d, PlayPacketAllocator::getCollectItemClientboundV47)
                .registerClientboundPacket(SpawnObjectClientboundV47.class, 0x0e, PlayPacketAllocator::getSpawnObjectClientboundV47)
                .registerClientboundPacket(SpawnMobClientboundV47.class, 0x0f, PlayPacketAllocator::getSpawnMobClientboundV47)
                .registerClientboundPacket(SpawnPaintingClientboundV47.class, 0x10, PlayPacketAllocator::getSpawnPaintingClientboundV47)
                .registerClientboundPacket(SpawnExperienceOrbClientboundV47.class, 0x11, PlayPacketAllocator::getSpawnExperienceOrbClientboundV47)
                .registerClientboundPacket(EntityVelocityClientboundV47.class, 0x12, PlayPacketAllocator::getEntityVelocityClientboundV47)
                .registerClientboundPacket(DestroyEntitiesClientboundV47.class, 0x13, PlayPacketAllocator::getDestroyEntitiesClientboundV47)
                .registerClientboundPacket(EntityClientboundV47.class, 0x14, PlayPacketAllocator::getEntityClientboundV47)
                .registerClientboundPacket(EntityRelativeMoveClientboundV47.class, 0x15, PlayPacketAllocator::getEntityRelativeMoveClientboundV47)
                .registerClientboundPacket(EntityLookClientboundV47.class, 0x16, PlayPacketAllocator::getEntityLookClientboundV47)
                .registerClientboundPacket(EntityLookAndRelativeMoveClientboundV47.class, 0x17, PlayPacketAllocator::getEntityLookAndRelativeMoveClientboundV47)
                .registerClientboundPacket(EntityTeleportClientboundV47.class, 0x18, PlayPacketAllocator::getEntityTeleportClientboundV47)
                .registerClientboundPacket(EntityHeadLookClientboundV47.class, 0x19, PlayPacketAllocator::getEntityHeadLookClientboundV47)
                .registerClientboundPacket(EntityStatusClientboundV47.class, 0x1a, PlayPacketAllocator::getEntityStatusClientboundV47)
                .registerClientboundPacket(AttachEntityClientboundV47.class, 0x1b, PlayPacketAllocator::getAttachEntityClientboundV47)
                .registerClientboundPacket(EntityMetadataClientboundV47.class, 0x1c, PlayPacketAllocator::getEntityMetadataClientboundV47)
                .registerClientboundPacket(EntityEffectClientboundV47.class, 0x1d, PlayPacketAllocator::getEntityEffectClientboundV47)
                .registerClientboundPacket(RemoveEntityEffectClientboundV47.class, 0x1e, PlayPacketAllocator::getRemoveEntityEffectClientboundV47)
                .registerClientboundPacket(SetExperienceClientboundV47.class, 0x1f, PlayPacketAllocator::getSetExperienceClientboundV47)
                .registerClientboundPacket(EntityPropertiesClientboundV47.class, 0x20, PlayPacketAllocator::getEntityPropertiesClientboundV47)
                .registerClientboundPacket(ChunkDataClientboundV47.class, 0x21, PlayPacketAllocator::getChunkDataClientboundV47)
                .registerClientboundPacket(MultiBlockChangeClientboundV47.class, 0x22, PlayPacketAllocator::getMultiBlockChangeClientboundV47)
                .registerClientboundPacket(BlockChangeClientboundV47.class, 0x23, PlayPacketAllocator::getBlockChangeClientboundV47)
                .registerClientboundPacket(BlockActionClientboundV47.class, 0x24, PlayPacketAllocator::getBlockActionClientboundV47)
                .registerClientboundPacket(BlockBreakAnimationClientboundV47.class, 0x25, PlayPacketAllocator::getBlockBreakAnimationClientboundV47)
                .registerClientboundPacket(MapChunkBulkClientboundV47.class, 0x26, PlayPacketAllocator::getMapChunkBulkClientboundV47)
                .registerClientboundPacket(ExplosionClientboundV47.class, 0x27, PlayPacketAllocator::getExplosionClientboundV47)
                .registerClientboundPacket(EffectClientboundV47.class, 0x28, PlayPacketAllocator::getEffectClientboundV47)
                .registerClientboundPacket(SoundEffectClientboundV47.class, 0x29, PlayPacketAllocator::getSoundEffectClientboundV47)
                .registerClientboundPacket(ParticleClientboundV47.class, 0x2a, PlayPacketAllocator::getParticleClientboundV47)
                .registerClientboundPacket(ChangeGameStateClientboundV47.class, 0x2b, PlayPacketAllocator::getChangeGameStateClientboundV47)
                .registerClientboundPacket(SpawnGlobalEntityClientboundV47.class, 0x2c, PlayPacketAllocator::getSpawnGlobalEntityClientboundV47)
                .registerClientboundPacket(OpenWindowClientboundV47.class, 0x2d, PlayPacketAllocator::getOpenWindowClientboundV47)
                .registerClientboundPacket(CloseWindowMultiboundV47.class, 0x2e, PlayPacketAllocator::getCloseWindowMultiboundV47)
                .registerClientboundPacket(SetSlotClientboundV47.class, 0x2f, PlayPacketAllocator::getSetSlotClientboundV47)
                .registerClientboundPacket(WindowItemsClientboundV47.class, 0x30, PlayPacketAllocator::getWindowItemsClientboundV47)
                .registerClientboundPacket(WindowPropertyClientboundV47.class, 0x31, PlayPacketAllocator::getWindowPropertyClientboundV47)
                .registerClientboundPacket(ConfirmTransactionMultiboundV47.class, 0x32, PlayPacketAllocator::getConfirmTransactionMultiboundV47)
                .registerClientboundPacket(UpdateSignMultiboundV47.class, 0x33, PlayPacketAllocator::getUpdateSignMultiboundV47)
                .registerClientboundPacket(MapClientboundV47.class, 0x34, PlayPacketAllocator::getMapClientboundV47)
                .registerClientboundPacket(UpdateBlockEntityClientboundV47.class, 0x35, PlayPacketAllocator::getUpdateBlockEntityClientboundV47)
                .registerClientboundPacket(OpenSignEditorClientboundV47.class, 0x36, PlayPacketAllocator::getOpenSignEditorClientboundV47)
                .registerClientboundPacket(StatisticsClientboundV47.class, 0x37, PlayPacketAllocator::getStatisticsClientboundV47)
                .registerClientboundPacket(PlayerListItemClientboundV47.class, 0x38, PlayPacketAllocator::getPlayerListItemClientboundV47)
                .registerClientboundPacket(PlayerAbilitiesClientboundV47.class, 0x39, PlayPacketAllocator::getPlayerAbilitiesClientboundV47)
                .registerClientboundPacket(TabCompleteClientboundV47.class, 0x3a, PlayPacketAllocator::getTabCompleteClientboundV47)
                .registerClientboundPacket(ScoreboardObjectiveClientboundV47.class, 0x3b, PlayPacketAllocator::getScoreboardObjectiveClientboundV47)
                .registerClientboundPacket(UpdateScoreClientboundV47.class, 0x3c, PlayPacketAllocator::getUpdateScoreClientboundV47)
                .registerClientboundPacket(DisplayScoreboardClientboundV47.class, 0x3d, PlayPacketAllocator::getDisplayScoreboardClientboundV47)
                .registerClientboundPacket(TeamsClientboundV47.class, 0x3e, PlayPacketAllocator::getTeamsClientboundV47)
                .registerClientboundPacket(PluginMessageMultiboundV47.class, 0x3f, PlayPacketAllocator::getPluginMessageMultiboundV47)
                .registerClientboundPacket(DisconnectClientboundV47.class, 0x40, PlayPacketAllocator::getDisconnectClientboundV47)
                .registerClientboundPacket(ServerDifficultyClientboundV47.class, 0x41, PlayPacketAllocator::getServerDifficultyClientboundV47)
                .registerClientboundPacket(CombatEventClientboundV47.class, 0x42, PlayPacketAllocator::getCombatEventClientboundV47)
                .registerClientboundPacket(CameraClientboundV47.class, 0x43, PlayPacketAllocator::getCameraClientboundV47)
                .registerClientboundPacket(WorldBorderClientboundV47.class, 0x44, PlayPacketAllocator::getWorldBorderClientboundV47)
                .registerClientboundPacket(TitleClientboundV47.class, 0x45, PlayPacketAllocator::getTitleClientboundV47)
                // Skip 0x46 (Set Compression) since it's broken
                .registerClientboundPacket(PlayerListHeaderAndFooterClientboundV47.class, 0x47, PlayPacketAllocator::getPlayerListHeaderAndFooterClientboundV47)
                .registerClientboundPacket(ResourcePackSendClientboundV47.class, 0x48, PlayPacketAllocator::getResourcePackSendClientboundV47)
                .registerClientboundPacket(UpdateEntityNbtClientboundV47.class, 0x49, PlayPacketAllocator::getUpdateEntityNbtClientboundV47)

                .registerServerboundPacket(KeepAliveMultiboundV47.class, 0x00, PlayPacketAllocator::getKeepAliveMultiboundV47)
                .registerServerboundPacket(ChatMessageServerboundV47.class, 0x01, PlayPacketAllocator::getChatMessageServerboundV47)
                .registerServerboundPacket(UseEntityServerboundV47.class, 0x02, PlayPacketAllocator::getUseEntityServerboundV47)
                .registerServerboundPacket(PlayerServerboundV47.class, 0x03, PlayPacketAllocator::getPlayerServerboundV47)
                .registerServerboundPacket(PlayerPositionServerboundV47.class, 0x04, PlayPacketAllocator::getPlayerPositionServerboundV47)
                .registerServerboundPacket(PlayerLookServerboundV47.class, 0x05, PlayPacketAllocator::getPlayerLookServerboundV47)
                .registerServerboundPacket(PlayerPositionAndLookServerboundV47.class, 0x06, PlayPacketAllocator::getPlayerPositionAndLookServerboundV47)
                .registerServerboundPacket(PlayerDiggingServerboundV47.class, 0x07, PlayPacketAllocator::getPlayerDiggingServerboundV47)
                .registerServerboundPacket(PlayerBlockPlacementServerboundV47.class, 0x08, PlayPacketAllocator::getPlayerBlockPlacementServerboundV47)
                .registerServerboundPacket(HeldItemChangeMultiboundV47.class, 0x09, PlayPacketAllocator::getHeldItemChangeMultiboundV47)
                .registerServerboundPacket(AnimationServerboundV47.class, 0x0a, PlayPacketAllocator::getAnimationServerboundV47)
                .registerServerboundPacket(EntityActionServerboundV47.class, 0x0b, PlayPacketAllocator::getEntityActionServerboundV47)
                .registerServerboundPacket(SteerVehicleServerboundV47.class, 0x0c, PlayPacketAllocator::getSteerVehicleServerboundV47)
                .registerServerboundPacket(CloseWindowMultiboundV47.class, 0x0d, PlayPacketAllocator::getCloseWindowMultiboundV47)
                .registerServerboundPacket(ClickWindowServerboundV47.class, 0x0e, PlayPacketAllocator::getClickWindowServerboundV47)
                .registerServerboundPacket(ConfirmTransactionMultiboundV47.class, 0x0f, PlayPacketAllocator::getConfirmTransactionMultiboundV47)
                .registerServerboundPacket(CreativeInventoryActionServerboundV47.class, 0x10, PlayPacketAllocator::getCreativeInventoryActionServerboundV47)
                .registerServerboundPacket(EnchantItemServerboundV47.class, 0x11, PlayPacketAllocator::getEnchantItemServerboundV47)
                .registerServerboundPacket(UpdateSignMultiboundV47.class, 0x12, PlayPacketAllocator::getUpdateSignMultiboundV47)
                .registerServerboundPacket(PlayerAbilitiesServerboundV47.class, 0x13, PlayPacketAllocator::getPlayerAbilitiesServerboundV47)
                .registerServerboundPacket(TabCompleteServerboundV47.class, 0x14, PlayPacketAllocator::getTabCompleteServerboundV47)
                .registerServerboundPacket(ClientSettingsServerboundV47.class, 0x15, PlayPacketAllocator::getClientSettingsServerboundV47)
                .registerServerboundPacket(ClientStatusServerboundV47.class, 0x16, PlayPacketAllocator::getClientStatusServerboundV47)
                .registerServerboundPacket(PluginMessageMultiboundV47.class, 0x17, PlayPacketAllocator::getPluginMessageMultiboundV47)
                .registerServerboundPacket(SpectateServerboundV47.class, 0x18, PlayPacketAllocator::getSpectateServerboundV47)
                .registerServerboundPacket(ResourcePackStatusServerboundV47.class, 0x19, PlayPacketAllocator::getResourcePackStatusServerboundV47)
                .build();
    }

    private PlayPacketUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    public static int getServerboundPacketId(Packet packet, int protocolVersion) {
        Objects.requireNonNull(packet, "packet must not be null");
        if (protocolVersion >= 47)
            return V47_MAPPER.getServerboundPacketId(packet);
        throw new NoSuchElementException("Cannot find packet id for " + packet.getClass().getName() + " for protocol version " + protocolVersion);
    }

    public static int getClientboundPacketId(Packet packet, int protocolVersion) {
        Objects.requireNonNull(packet, "packet must not be null");
        if (protocolVersion >= 47)
            return V47_MAPPER.getClientboundPacketId(packet);
        throw new NoSuchElementException("Cannot find packet id for " + packet.getClass().getName() + " for protocol version " + protocolVersion);
    }

    public static Packet allocateServerboundPacket(PlayPacketAllocator allocator, int packetId, int protocolVersion) {
        Objects.requireNonNull(allocator, "allocator must not be null");
        Function<PlayPacketAllocator, Packet> allocatorFunction = null;
        if (protocolVersion >= 47)
            allocatorFunction = V47_MAPPER.getServerboundPacketAllocator(packetId);

        if (allocatorFunction != null)
            return allocatorFunction.apply(allocator);
        else
            throw new NoSuchElementException("Cannot allocate serverbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }

    public static Packet allocateClientboundPacket(PlayPacketAllocator allocator, int packetId, int protocolVersion) {
        Objects.requireNonNull(allocator, "allocator must not be null");
        Function<PlayPacketAllocator, Packet> allocatorFunction = null;
        if (protocolVersion >= 47)
            allocatorFunction = V47_MAPPER.getClientboundPacketAllocator(packetId);

        if (allocatorFunction != null)
            return allocatorFunction.apply(allocator);
        else
            throw new NoSuchElementException("Cannot allocate clientbound packet with packetId=" + packetId + " and protocolVersion=" + protocolVersion);
    }
}
