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

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class BaseEntityProperty implements EntityProperty {

    private String key;
    private double value;
    private List<? extends EntityPropertyModifier> modifiers;

    BaseEntityProperty() {
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public List<? extends EntityPropertyModifier> getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(List<? extends EntityPropertyModifier> modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EntityProperty))
            return false;
        EntityProperty that = (EntityProperty) o;
        return Double.compare(that.getValue(), getValue()) == 0 &&
                Objects.equals(getKey(), that.getKey()) &&
                Objects.equals(getModifiers(), that.getModifiers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue(), getModifiers());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{key\"" + getKey() +
                "\", value=" + getValue() +
                ", modifiers=" + getModifiers() +
                '}';
    }

    static class BaseEntityPropertyModifier implements EntityPropertyModifier {

        private UUID uuid;
        private double amount;
        private byte operation;

        BaseEntityPropertyModifier() {
        }

        @Override
        public UUID getUuid() {
            return uuid;
        }

        @Override
        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        @Override
        public double getAmount() {
            return amount;
        }

        @Override
        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public byte getOperation() {
            return operation;
        }

        @Override
        public void setOperation(byte operation) {
            this.operation = operation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof EntityPropertyModifier))
                return false;
            EntityPropertyModifier that = (EntityPropertyModifier) o;
            return Double.compare(that.getAmount(), getAmount()) == 0 &&
                    getOperation() == that.getOperation() &&
                    Objects.equals(getUuid(), that.getUuid());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUuid(), getAmount(), getOperation());
        }

        @Override
        public String toString() {
            return getClass().getName() + "{uuid=" + getUuid() +
                    ", amount=" + getAmount() +
                    ", operation=" + getOperation() +
                    '}';
        }
    }
}
