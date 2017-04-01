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

import io.lunamc.protocol.packet.data.DataAllocator;
import io.lunamc.protocol.packet.data.DefaultDataAllocator;

import java.util.Objects;

public class UnpooledPlayPacketAllocator implements PlayPacketAllocator {

    public static final UnpooledPlayPacketAllocator INSTANCE = new UnpooledPlayPacketAllocator(DefaultDataAllocator.INSTANCE);

    private DataAllocator dataAllocator;

    public UnpooledPlayPacketAllocator(DataAllocator dataAllocator) {
        this.dataAllocator = Objects.requireNonNull(dataAllocator, "dataAllocator must not be null");
    }

    @Override
    public JoinGameClientboundV47 getJoinGameClientboundV47() {
        return new BaseJoinGameClientboundV47();
    }

    @Override
    public ChatMessageClientboundV47 getChatMessageClientboundV47() {
        return new BaseChatMessageClientboundV47();
    }

    @Override
    public TimeUpdateClientboundV47 getTimeUpdateClientboundV47() {
        return new BaseTimeUpdateClientboundV47();
    }

    @Override
    public EntityEquipmentClientboundV47 getEntityEquipmentClientboundV47() {
        return new BaseEntityEquipmentClientboundV47(dataAllocator);
    }

    @Override
    public SpawnPositionClientboundV47 getSpawnPositionClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UpdateHealthClientboundV47 getUpdateHealthClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public RespawnClientboundV47 getRespawnClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerPositionAndLookClientboundV47 getPlayerPositionAndLookClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UseBedClientboundV47 getUseBedClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AnimationClientboundV47 getAnimationClientboundV47() {
        return new BaseAnimationClientboundV47();
    }

    @Override
    public SpawnPlayerClientboundV47 getSpawnPlayerClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public CollectItemClientboundV47 getCollectItemClientboundV47() {
        return new BaseCollectItemClientboundV47();
    }

    @Override
    public SpawnObjectClientboundV47 getSpawnObjectClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SpawnMobClientboundV47 getSpawnMobClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SpawnPaintingClientboundV47 getSpawnPaintingClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SpawnExperienceOrbClientboundV47 getSpawnExperienceOrbClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityVelocityClientboundV47 getEntityVelocityClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public DestroyEntitiesClientboundV47 getDestroyEntitiesClientboundV47() {
        return new BaseDestroyEntitiesClientboundV47();
    }

    @Override
    public EntityClientboundV47 getEntityClientboundV47() {
        return new BaseEntityClientboundV47();
    }

    @Override
    public EntityRelativeMoveClientboundV47 getEntityRelativeMoveClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityLookClientboundV47 getEntityLookClientboundV47() {
        return new BaseEntityLookClientboundV47();
    }

    @Override
    public EntityLookAndRelativeMoveClientboundV47 getEntityLookAndRelativeMoveClientboundV47() {
        return new BaseEntityLookAndRelativeMoveClientboundV47();
    }

    @Override
    public EntityTeleportClientboundV47 getEntityTeleportClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityHeadLookClientboundV47 getEntityHeadLookClientboundV47() {
        return new BaseEntityHeadLookClientboundV47();
    }

    @Override
    public EntityStatusClientboundV47 getEntityStatusClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AttachEntityClientboundV47 getAttachEntityClientboundV47() {
        return new BaseAttachEntityClientboundV47();
    }

    @Override
    public EntityMetadataClientboundV47 getEntityMetadataClientboundV47() {
        return new BaseEntityMetadataClientboundV47(dataAllocator);
    }

    @Override
    public EntityEffectClientboundV47 getEntityEffectClientboundV47() {
        return new BaseEntityEffectClientboundV47();
    }

    @Override
    public RemoveEntityEffectClientboundV47 getRemoveEntityEffectClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SetExperienceClientboundV47 getSetExperienceClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityPropertiesClientboundV47 getEntityPropertiesClientboundV47() {
        return new BaseEntityPropertiesClientboundV47(dataAllocator);
    }

    @Override
    public ChunkDataClientboundV47 getChunkDataClientboundV47() {
        return new BaseChunkDataClientboundV47(dataAllocator);
    }

    @Override
    public MultiBlockChangeClientboundV47 getMultiBlockChangeClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BlockChangeClientboundV47 getBlockChangeClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BlockActionClientboundV47 getBlockActionClientboundV47() {
        return new BaseBlockActionClientboundV47();
    }

    @Override
    public BlockBreakAnimationClientboundV47 getBlockBreakAnimationClientboundV47() {
        return new BaseBlockBreakAnimationClientboundV47();
    }

    @Override
    public MapChunkBulkClientboundV47 getMapChunkBulkClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ExplosionClientboundV47 getExplosionClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EffectClientboundV47 getEffectClientboundV47() {
        return new BaseEffectClientboundV47();
    }

    @Override
    public SoundEffectClientboundV47 getSoundEffectClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ParticleClientboundV47 getParticleClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ChangeGameStateClientboundV47 getChangeGameStateClientboundV47() {
        return new BaseChangeGameStateClientboundV47();
    }

    @Override
    public SpawnGlobalEntityClientboundV47 getSpawnGlobalEntityClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public OpenWindowClientboundV47 getOpenWindowClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SetSlotClientboundV47 getSetSlotClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public WindowItemsClientboundV47 getWindowItemsClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public WindowPropertyClientboundV47 getWindowPropertyClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public MapClientboundV47 getMapClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UpdateBlockEntityClientboundV47 getUpdateBlockEntityClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public OpenSignEditorClientboundV47 getOpenSignEditorClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public StatisticsClientboundV47 getStatisticsClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerListItemClientboundV47 getPlayerListItemClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerAbilitiesClientboundV47 getPlayerAbilitiesClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public TabCompleteClientboundV47 getTabCompleteClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ScoreboardObjectiveClientboundV47 getScoreboardObjectiveClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UpdateScoreClientboundV47 getUpdateScoreClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public DisplayScoreboardClientboundV47 getDisplayScoreboardClientboundV47() {
        return new BaseDisplayScoreboardClientboundV47();
    }

    @Override
    public TeamsClientboundV47 getTeamsClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public DisconnectClientboundV47 getDisconnectClientboundV47() {
        return new BaseDisconnectClientboundV47();
    }

    @Override
    public ServerDifficultyClientboundV47 getServerDifficultyClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public CombatEventClientboundV47 getCombatEventClientboundV47() {
        return new BaseCombatEventClientboundV47();
    }

    @Override
    public CameraClientboundV47 getCameraClientboundV47() {
        return new BaseCameraClientboundV47();
    }

    @Override
    public WorldBorderClientboundV47 getWorldBorderClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public TitleClientboundV47 getTitleClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerListHeaderAndFooterClientboundV47 getPlayerListHeaderAndFooterClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResourcePackSendClientboundV47 getResourcePackSendClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UpdateEntityNbtClientboundV47 getUpdateEntityNbtClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ChatMessageServerboundV47 getChatMessageServerboundV47() {
        return new BaseChatMessageServerboundV47();
    }

    @Override
    public UseEntityServerboundV47 getUseEntityServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerServerboundV47 getPlayerServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerPositionServerboundV47 getPlayerPositionServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerLookServerboundV47 getPlayerLookServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerPositionAndLookServerboundV47 getPlayerPositionAndLookServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerDiggingServerboundV47 getPlayerDiggingServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerBlockPlacementServerboundV47 getPlayerBlockPlacementServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AnimationServerboundV47 getAnimationServerboundV47() {
        return BaseAnimationServerboundV47.INSTANCE;
    }

    @Override
    public EntityActionServerboundV47 getEntityActionServerboundV47() {
        return new BaseEntityActionServerboundV47();
    }

    @Override
    public SteerVehicleServerboundV47 getSteerVehicleServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ClickWindowServerboundV47 getClickWindowServerboundV47() {
        return new BaseClickWindowServerboundV47(dataAllocator);
    }

    @Override
    public CreativeInventoryActionServerboundV47 getCreativeInventoryActionServerboundV47() {
        return new BaseCreativeInventoryActionServerboundV47(dataAllocator);
    }

    @Override
    public EnchantItemServerboundV47 getEnchantItemServerboundV47() {
        return new BaseEnchantItemServerboundV47();
    }

    @Override
    public PlayerAbilitiesServerboundV47 getPlayerAbilitiesServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public TabCompleteServerboundV47 getTabCompleteServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ClientSettingsServerboundV47 getClientSettingsServerboundV47() {
        return new BaseClientSettingsServerboundV47();
    }

    @Override
    public ClientStatusServerboundV47 getClientStatusServerboundV47() {
        return new BaseClientStatusServerboundV47();
    }

    @Override
    public SpectateServerboundV47 getSpectateServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ResourcePackStatusServerboundV47 getResourcePackStatusServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public KeepAliveMultiboundV47 getKeepAliveMultiboundV47() {
        return new BaseKeepAliveMultiboundV47();
    }

    @Override
    public HeldItemChangeMultiboundV47 getHeldItemChangeMultiboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public CloseWindowMultiboundV47 getCloseWindowMultiboundV47() {
        return new BaseCloseWindowMultiboundV47();
    }

    @Override
    public ConfirmTransactionMultiboundV47 getConfirmTransactionMultiboundV47() {
        return new BaseConfirmTransactionMultiboundV47();
    }

    @Override
    public UpdateSignMultiboundV47 getUpdateSignMultiboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PluginMessageMultiboundV47 getPluginMessageMultiboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
