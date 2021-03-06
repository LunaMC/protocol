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

package io.lunamc.protocol.packet.login;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;

import java.util.Objects;

class ReferenceCountedEncryptionRequestClientboundV47 extends BaseEncryptionRequestClientboundV47 implements ReferenceCounted {

    private static final ResourceLeakDetector<ReferenceCountedEncryptionRequestClientboundV47> LEAK_DETECTOR =
            ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedEncryptionRequestClientboundV47.class);

    private final AbstractReferenceCounted refCnt = new AbstractReferenceCounted() {
        @Override
        protected void deallocate() {
            if (leakTracker != null)
                assert leakTracker.close(ReferenceCountedEncryptionRequestClientboundV47.this);
            reset();
            handle.recycle(ReferenceCountedEncryptionRequestClientboundV47.this);
        }

        @Override
        public ReferenceCounted touch(Object hint) {
            if (leakTracker != null)
                leakTracker.record(hint);
            return ReferenceCountedEncryptionRequestClientboundV47.this;
        }
    };
    private final Recycler.Handle<ReferenceCountedEncryptionRequestClientboundV47> handle;
    private final ResourceLeakTracker<ReferenceCountedEncryptionRequestClientboundV47> leakTracker;

    ReferenceCountedEncryptionRequestClientboundV47(Recycler.Handle<ReferenceCountedEncryptionRequestClientboundV47> handle) {
        this(handle, ResourceLeakDetector.isEnabled());
    }

    ReferenceCountedEncryptionRequestClientboundV47(Recycler.Handle<ReferenceCountedEncryptionRequestClientboundV47> handle,
                                            boolean leakDetection) {
        this.handle = Objects.requireNonNull(handle, "handle must not be null");

        leakTracker = leakDetection ? LEAK_DETECTOR.track(this) : null;
    }

    @Override
    public int refCnt() {
        return refCnt.refCnt();
    }

    @Override
    public ReferenceCounted retain() {
        return refCnt.retain();
    }

    @Override
    public ReferenceCounted retain(int increment) {
        return refCnt.retain(increment);
    }

    @Override
    public ReferenceCounted touch() {
        return refCnt.touch();
    }

    @Override
    public ReferenceCounted touch(Object hint) {
        return refCnt.touch(hint);
    }

    @Override
    public boolean release() {
        return refCnt.release();
    }

    @Override
    public boolean release(int decrement) {
        return refCnt.release(decrement);
    }
}
