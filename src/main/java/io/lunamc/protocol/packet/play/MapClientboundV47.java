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
import io.lunamc.protocol.packet.data.MapIcon;

import java.util.List;

public interface MapClientboundV47 extends Packet {

    int getItemDamage();

    void setItemDamage(int itemDamage);

    byte getScale();

    void setScale(byte scale);

    List<MapIcon> getIcons();

    void setIcons(List<MapIcon> icons);

    byte getColumns();

    void setColumns(byte columns);

    byte getRows();

    void setRows(byte rows);

    byte getX();

    void setX(byte x);

    byte getZ();

    void setZ(byte z);

    short[] getData();

    void setData(short[] data);

    @Override
    default void reset() {
        setItemDamage(0);
        setScale((byte) 0);
        setIcons(null);
        setColumns((byte) 0);
        setRows((byte) 0);
        setX((byte) 0);
        setZ((byte) 0);
        setData(null);
    }

    @Override
    default Class<? extends Packet> getModelClass() {
        return MapClientboundV47.class;
    }
}
