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

package io.lunamc.protocol.packet.status;

public class UnpooledStatusPacketAllocator implements StatusPacketAllocator {

    public static final UnpooledStatusPacketAllocator INSTANCE = new UnpooledStatusPacketAllocator();

    @Override
    public ResponseClientboundV47 getResponseClientboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public RequestServerboundV47 getRequestServerboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public PingMultiboundV47 getPingMultiboundV47() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
