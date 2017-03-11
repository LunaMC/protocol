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

import io.lunamc.protocol.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class BaseChatMessageServerboundV47 implements ChatMessageServerboundV47 {

    private String jsonData;

    @Override
    public String getJsonData() {
        return jsonData;
    }

    @Override
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public void write(ByteBuf output) {
        ProtocolUtils.writeString(output, getJsonData());
    }

    @Override
    public void read(ByteBuf input) {
        setJsonData(ProtocolUtils.readString(input));
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (o instanceof BaseChatMessageServerboundV47 && Objects.equals(getJsonData(), ((ChatMessageServerboundV47) o).getJsonData()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJsonData());
    }

    @Override
    public String toString() {
        return getClass().getName() + "{jsonData=\"" + getJsonData() + "\"}";
    }
}
