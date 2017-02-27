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

public interface DataAllocator {

    BlockChangeRecord getBlockChangeRecord();

    ByteTuple3 getByteTuple3();

    Chunk getChunk();

    ChunkMeta getChunkMeta();

    EntityMetadata getEntityMetadata();

    EntityMetadata.EntityMetadataEntry getEntityMetadataEntry();

    EntityProperty getEntityProperty();

    EntityProperty.EntityPropertyModifier getEntityPropertyModifier();

    MapIcon getMapIcon();

    PlayerListUpdate getPlayerListUpdate();

    PlayerListUpdate.PlayerListAddPlayerAction getPlayerListAddPlayerAction();

    PlayerListUpdate.PlayerListAddPlayerAction.PlayerListAddPlayerActionPlayerProperty getPlayerListAddPlayerActionPlayerProperty();

    PlayerListUpdate.PlayerListUpdateGamemodeAction getPlayerListUpdateGamemodeAction();

    PlayerListUpdate.PlayerListUpdateLatencyAction getPlayerListUpdateLatencyAction();

    PlayerListUpdate.PlayerListUpdateDisplayNameAction getPlayerListUpdateDisplayNameAction();

    PlayerListUpdate.PlayerListRemovePlayerAction getPlayerListRemovePlayerAction();

    SlotData getSlotData();

    Statistic getStatistic();

    TitleAction.TitleActionSetTitle getTitleActionSetTitle();

    TitleAction.TitleActionSetSubtitle getTitleActionSetSubtitle();

    TitleAction.TitleActionSetTimesAndDisplay getTitleActionSetTimesAndDisplay();

    TitleAction.TitleActionHide getTitleActionHide();

    TitleAction.TitleActionReset getTitleActionReset();

    WorldBorderAction.WorldBorderSetSizeAction getWorldBorderSetSizeAction();

    WorldBorderAction.WorldBorderLerpSize getWorldBorderLerpSize();

    WorldBorderAction.WorldBorderSetCenter getWorldBorderSetCenter();

    WorldBorderAction.WorldBorderInitialize getWorldBorderInitialize();

    WorldBorderAction.WorldBorderSetWarningTime getWorldBorderSetWarningTime();

    WorldBorderAction.WorldBorderSetWarningBlocks getWorldBorderSetWarningBlocks();
}
