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

import io.lunamc.protocol.packet.AbstractPacketTest;
import io.lunamc.protocol.packet.Packet;
import io.lunamc.protocol.packet.data.DataAllocator;
import io.lunamc.protocol.packet.data.EntityMetadata;
import org.mockito.Mockito;

public class BaseEntityMetadataClientboundV47Test extends AbstractPacketTest {

    private static final EntityMetadata ENTITY_METADATA = Mockito.mock(EntityMetadata.class);

    @Override
    protected Packet createPacket(boolean content) {
        BaseEntityMetadataClientboundV47 packet = new BaseEntityMetadataClientboundV47(mockDataAllocator());
        if (content) {
            packet.setEntityId(42);
            packet.setMetadata(ENTITY_METADATA);
        }
        return packet;
    }

    private static DataAllocator mockDataAllocator() {
        DataAllocator dataAllocator = Mockito.mock(DataAllocator.class);
        Mockito.when(dataAllocator.getEntityMetadata()).thenReturn(ENTITY_METADATA);
        return dataAllocator;
    }
}
