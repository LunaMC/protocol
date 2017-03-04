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

import io.lunamc.protocol.packet.NetworkSerializable;

import java.util.List;
import java.util.UUID;

public interface EntityProperty extends NetworkSerializable {

    String getKey();

    void setKey(String key);

    double getValue();

    void setValue(double value);

    List<EntityPropertyModifier> getModifiers();

    void setModifiers(List<EntityPropertyModifier> modifiers);

    @Override
    default void reset() {
        setKey(null);
        setValue(0);
        setModifiers(null);
    }

    interface EntityPropertyModifier extends NetworkSerializable {

        UUID getUuid();

        void setUuid(UUID uuid);

        double getAmount();

        void setAmount(double amount);

        byte getOperation();

        void setOperation(byte operation);

        @Override
        default void reset() {
            setUuid(null);
            setAmount(0);
            setOperation((byte) 0);
        }
    }
}
