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

public class DefaultDataAllocator implements DataAllocator {

    public static final DefaultDataAllocator INSTANCE = new DefaultDataAllocator();

    @Override
    public BlockChangeRecord getBlockChangeRecord() {
        return new BaseBlockChangeRecord();
    }

    @Override
    public ByteTuple3 getByteTuple3() {
        return new BaseByteTuple3();
    }

    @Override
    public Chunk getChunk() {
        return new BaseChunk();
    }

    @Override
    public ChunkMeta getChunkMeta() {
        return new BaseChunkMeta();
    }

    @Override
    public EntityMetadata getEntityMetadata() {
        return new BaseEntityMetadata(this);
    }

    @Override
    public EntityMetadata.EntityMetadataEntry getEntityMetadataEntry() {
        return new BaseEntityMetadata.BaseEntityMetadataEntry(this);
    }

    @Override
    public EntityProperty getEntityProperty() {
        return new BaseEntityProperty(this);
    }

    @Override
    public EntityProperty.EntityPropertyModifier getEntityPropertyModifier() {
        return new BaseEntityProperty.BaseEntityPropertyModifier();
    }

    @Override
    public MapIcon getMapIcon() {
        return new BaseMapIcon();
    }

    @Override
    public PlayerListUpdate getPlayerListUpdate() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PlayerListUpdate.PlayerListAddPlayerAction getPlayerListAddPlayerAction() {
        return null;
    }

    @Override
    public PlayerListUpdate.PlayerListAddPlayerAction.PlayerListAddPlayerActionPlayerProperty getPlayerListAddPlayerActionPlayerProperty() {
        return new BasePlayerListUpdate.BasePlayerListAddPlayerAction.BasePlayerListAddPlayerActionPlayerProperty();
    }

    @Override
    public PlayerListUpdate.PlayerListUpdateGamemodeAction getPlayerListUpdateGamemodeAction() {
        return new BasePlayerListUpdate.BasePlayerListUpdateGamemodeAction();
    }

    @Override
    public PlayerListUpdate.PlayerListUpdateLatencyAction getPlayerListUpdateLatencyAction() {
        return new BasePlayerListUpdate.BasePlayerListUpdateLatencyAction();
    }

    @Override
    public PlayerListUpdate.PlayerListUpdateDisplayNameAction getPlayerListUpdateDisplayNameAction() {
        return new BasePlayerListUpdate.BasePlayerListUpdateDisplayNameAction();
    }

    @Override
    public PlayerListUpdate.PlayerListRemovePlayerAction getPlayerListRemovePlayerAction() {
        return new BasePlayerListUpdate.BasePlayerListRemovePlayerAction();
    }

    @Override
    public Rotation getRotation() {
        return new BaseRotation();
    }

    @Override
    public SlotData getSlotData() {
        return new BaseSlotData();
    }

    @Override
    public Statistic getStatistic() {
        return new BaseStatistic();
    }

    @Override
    public TitleAction.TitleActionSetTitle getTitleActionSetTitle() {
        return new BaseTitleAction.BaseTitleActionSetTitle();
    }

    @Override
    public TitleAction.TitleActionSetSubtitle getTitleActionSetSubtitle() {
        return new BaseTitleAction.BaseTitleActionSetSubtitle();
    }

    @Override
    public TitleAction.TitleActionSetTimesAndDisplay getTitleActionSetTimesAndDisplay() {
        return new BaseTitleAction.BaseTitleActionSetTimesAndDisplay();
    }

    @Override
    public TitleAction.TitleActionHide getTitleActionHide() {
        return new BaseTitleAction.BaseTitleActionHide();
    }

    @Override
    public TitleAction.TitleActionReset getTitleActionReset() {
        return new BaseTitleAction.BaseTitleActionReset();
    }

    @Override
    public WorldBorderAction.WorldBorderSetSizeAction getWorldBorderSetSizeAction() {
        return new BaseWorldBorderAction.BaseWorldBorderSetSizeAction();
    }

    @Override
    public WorldBorderAction.WorldBorderLerpSize getWorldBorderLerpSize() {
        return new BaseWorldBorderAction.BaseWorldBorderLerpSize();
    }

    @Override
    public WorldBorderAction.WorldBorderSetCenter getWorldBorderSetCenter() {
        return new BaseWorldBorderAction.BaseWorldBorderSetCenter();
    }

    @Override
    public WorldBorderAction.WorldBorderInitialize getWorldBorderInitialize() {
        return new BaseWorldBorderAction.BaseWorldBorderInitialize();
    }

    @Override
    public WorldBorderAction.WorldBorderSetWarningTime getWorldBorderSetWarningTime() {
        return new BaseWorldBorderAction.BaseWorldBorderSetWarningTime();
    }

    @Override
    public WorldBorderAction.WorldBorderSetWarningBlocks getWorldBorderSetWarningBlocks() {
        return new BaseWorldBorderAction.BaseWorldBorderSetWarningBlocks();
    }
}
