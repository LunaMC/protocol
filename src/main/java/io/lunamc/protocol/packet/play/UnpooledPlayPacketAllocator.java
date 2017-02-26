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

public class UnpooledPlayPacketAllocator implements PlayPacketAllocator {

    public static final UnpooledPlayPacketAllocator INSTANCE = new UnpooledPlayPacketAllocator();

    @Override
    public KeepAliveClientboundV47 getKeepAliveClientboundV47() {
        return new BaseKeepAliveClientboundV47();
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
        throw new UnsupportedOperationException("Not implemented");
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
    public HeldItemChangeClientboundV47 getHeldItemChangeClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UseBedClientboundV47 getUseBedClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AnimationClientboundV47 getAnimationClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public SpawnPlayerClientboundV47 getSpawnPlayerClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public CollectItemClientboundV47 getCollectItemClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
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
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityClientboundV47 getEntityClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityRelativeMoveClientboundV47 getEntityRelativeMoveClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityLookClientboundV47 getEntityLookClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityLookAndRelativeMoveClientboundV47 getEntityLookAndRelativeMoveClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityTeleportClientboundV47 getEntityTeleportClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityHeadLookClientboundV47 getEntityHeadLookClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityStatusClientboundV47 getEntityStatusClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AttachEntityClientboundV47 getAttachEntityClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityMetadataClientboundV47 getEntityMetadataClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public EntityEffectClientboundV47 getEntityEffectClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
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
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ChunkDataClientboundV47 getChunkDataClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
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
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BlockBreakAnimationClientboundV47 getBlockBreakAnimationClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
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
        throw new UnsupportedOperationException("Not implemented");
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
        throw new UnsupportedOperationException("Not implemented");
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
    public CloseWindowClientboundV47 getCloseWindowClientboundV47() {
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
    public ConfirmTransactionClientboundV47 getConfirmTransactionClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UpdateSignClientboundV47 getUpdateSignClientboundV47() {
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
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public TeamsClientboundV47 getTeamsClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PluginMessageClientboundV47 getPluginMessageClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public DisconnectClientboundV47 getDisconnectClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ServerDifficultyClientboundV47 getServerDifficultyClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public CombatEventClientboundV47 getCombatEventClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public CameraClientboundV47 getCameraClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
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
}
