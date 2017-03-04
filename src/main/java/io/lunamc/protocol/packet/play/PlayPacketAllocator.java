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

public interface PlayPacketAllocator {

    JoinGameClientboundV47 getJoinGameClientboundV47();

    ChatMessageClientboundV47 getChatMessageClientboundV47();

    TimeUpdateClientboundV47 getTimeUpdateClientboundV47();

    EntityEquipmentClientboundV47 getEntityEquipmentClientboundV47();

    SpawnPositionClientboundV47 getSpawnPositionClientboundV47();

    UpdateHealthClientboundV47 getUpdateHealthClientboundV47();

    RespawnClientboundV47 getRespawnClientboundV47();

    PlayerPositionAndLookClientboundV47 getPlayerPositionAndLookClientboundV47();

    UseBedClientboundV47 getUseBedClientboundV47();

    AnimationClientboundV47 getAnimationClientboundV47();

    SpawnPlayerClientboundV47 getSpawnPlayerClientboundV47();

    CollectItemClientboundV47 getCollectItemClientboundV47();

    SpawnObjectClientboundV47 getSpawnObjectClientboundV47();

    SpawnMobClientboundV47 getSpawnMobClientboundV47();

    SpawnPaintingClientboundV47 getSpawnPaintingClientboundV47();

    SpawnExperienceOrbClientboundV47 getSpawnExperienceOrbClientboundV47();

    EntityVelocityClientboundV47 getEntityVelocityClientboundV47();

    DestroyEntitiesClientboundV47 getDestroyEntitiesClientboundV47();

    EntityClientboundV47 getEntityClientboundV47();

    EntityRelativeMoveClientboundV47 getEntityRelativeMoveClientboundV47();

    EntityLookClientboundV47 getEntityLookClientboundV47();

    EntityLookAndRelativeMoveClientboundV47 getEntityLookAndRelativeMoveClientboundV47();

    EntityTeleportClientboundV47 getEntityTeleportClientboundV47();

    EntityHeadLookClientboundV47 getEntityHeadLookClientboundV47();

    EntityStatusClientboundV47 getEntityStatusClientboundV47();

    AttachEntityClientboundV47 getAttachEntityClientboundV47();

    EntityMetadataClientboundV47 getEntityMetadataClientboundV47();

    EntityEffectClientboundV47 getEntityEffectClientboundV47();

    RemoveEntityEffectClientboundV47 getRemoveEntityEffectClientboundV47();

    SetExperienceClientboundV47 getSetExperienceClientboundV47();

    EntityPropertiesClientboundV47 getEntityPropertiesClientboundV47();

    ChunkDataClientboundV47 getChunkDataClientboundV47();

    MultiBlockChangeClientboundV47 getMultiBlockChangeClientboundV47();

    BlockChangeClientboundV47 getBlockChangeClientboundV47();

    BlockActionClientboundV47 getBlockActionClientboundV47();

    BlockBreakAnimationClientboundV47 getBlockBreakAnimationClientboundV47();

    MapChunkBulkClientboundV47 getMapChunkBulkClientboundV47();

    ExplosionClientboundV47 getExplosionClientboundV47();

    EffectClientboundV47 getEffectClientboundV47();

    SoundEffectClientboundV47 getSoundEffectClientboundV47();

    ParticleClientboundV47 getParticleClientboundV47();

    ChangeGameStateClientboundV47 getChangeGameStateClientboundV47();

    SpawnGlobalEntityClientboundV47 getSpawnGlobalEntityClientboundV47();

    OpenWindowClientboundV47 getOpenWindowClientboundV47();

    SetSlotClientboundV47 getSetSlotClientboundV47();

    WindowItemsClientboundV47 getWindowItemsClientboundV47();

    WindowPropertyClientboundV47 getWindowPropertyClientboundV47();

    MapClientboundV47 getMapClientboundV47();

    UpdateBlockEntityClientboundV47 getUpdateBlockEntityClientboundV47();

    OpenSignEditorClientboundV47 getOpenSignEditorClientboundV47();

    StatisticsClientboundV47 getStatisticsClientboundV47();

    PlayerListItemClientboundV47 getPlayerListItemClientboundV47();

    PlayerAbilitiesClientboundV47 getPlayerAbilitiesClientboundV47();

    TabCompleteClientboundV47 getTabCompleteClientboundV47();

    ScoreboardObjectiveClientboundV47 getScoreboardObjectiveClientboundV47();

    UpdateScoreClientboundV47 getUpdateScoreClientboundV47();

    DisplayScoreboardClientboundV47 getDisplayScoreboardClientboundV47();

    TeamsClientboundV47 getTeamsClientboundV47();

    DisconnectClientboundV47 getDisconnectClientboundV47();

    ServerDifficultyClientboundV47 getServerDifficultyClientboundV47();

    CombatEventClientboundV47 getCombatEventClientboundV47();

    CameraClientboundV47 getCameraClientboundV47();

    WorldBorderClientboundV47 getWorldBorderClientboundV47();

    TitleClientboundV47 getTitleClientboundV47();

    PlayerListHeaderAndFooterClientboundV47 getPlayerListHeaderAndFooterClientboundV47();

    ResourcePackSendClientboundV47 getResourcePackSendClientboundV47();

    UpdateEntityNbtClientboundV47 getUpdateEntityNbtClientboundV47();

    ChatMessageServerboundV47 getChatMessageServerboundV47();

    UseEntityServerboundV47 getUseEntityServerboundV47();

    PlayerServerboundV47 getPlayerServerboundV47();

    PlayerPositionServerboundV47 getPlayerPositionServerboundV47();

    PlayerLookServerboundV47 getPlayerLookServerboundV47();

    PlayerPositionAndLookServerboundV47 getPlayerPositionAndLookServerboundV47();

    PlayerDiggingServerboundV47 getPlayerDiggingServerboundV47();

    PlayerBlockPlacementServerboundV47 getPlayerBlockPlacementServerboundV47();

    AnimationServerboundV47 getAnimationServerboundV47();

    EntityActionServerboundV47 getEntityActionServerboundV47();

    SteerVehicleServerboundV47 getSteerVehicleServerboundV47();

    ClickWindowServerboundV47 getClickWindowServerboundV47();

    CreativeInventoryActionServerboundV47 getCreativeInventoryActionServerboundV47();

    EnchantItemServerboundV47 getEnchantItemServerboundV47();

    PlayerAbilitiesServerboundV47 getPlayerAbilitiesServerboundV47();

    TabCompleteServerboundV47 getTabCompleteServerboundV47();

    ClientSettingsServerboundV47 getClientSettingsServerboundV47();

    ClientStatusServerboundV47 getClientStatusServerboundV47();

    SpectateServerboundV47 getSpectateServerboundV47();

    ResourcePackStatusServerboundV47 getResourcePackStatusServerboundV47();

    KeepAliveMultiboundV47 getKeepAliveMultiboundV47();

    HeldItemChangeMultiboundV47 getHeldItemChangeMultiboundV47();

    UpdateSignMultiboundV47 getUpdateSignMultiboundV47();

    CloseWindowMultiboundV47 getCloseWindowMultiboundV47();

    PluginMessageMultiboundV47 getPluginMessageMultiboundV47();

    ConfirmTransactionMultiboundV47 getConfirmTransactionMultiboundV47();
}
