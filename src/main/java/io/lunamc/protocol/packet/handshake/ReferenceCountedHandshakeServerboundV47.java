/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol.packet.handshake;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;

import java.util.Objects;

class ReferenceCountedHandshakeServerboundV47 extends BaseHandshakeServerboundV47 implements ReferenceCounted {

    private static final ResourceLeakDetector<ReferenceCountedHandshakeServerboundV47> LEAK_DETECTOR =
            ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedHandshakeServerboundV47.class);

    private final AbstractReferenceCounted refCnt = new AbstractReferenceCounted() {
        @Override
        protected void deallocate() {
            if (leakTracker != null)
                assert leakTracker.close(ReferenceCountedHandshakeServerboundV47.this);
            reset();
            handle.recycle(ReferenceCountedHandshakeServerboundV47.this);
        }

        @Override
        public ReferenceCounted touch(Object hint) {
            if (leakTracker != null)
                leakTracker.record(hint);
            return ReferenceCountedHandshakeServerboundV47.this;
        }
    };
    private final Recycler.Handle<ReferenceCountedHandshakeServerboundV47> handle;
    private final ResourceLeakTracker<ReferenceCountedHandshakeServerboundV47> leakTracker;

    ReferenceCountedHandshakeServerboundV47(Recycler.Handle<ReferenceCountedHandshakeServerboundV47> handle) {
        this(handle, ResourceLeakDetector.isEnabled());
    }

    ReferenceCountedHandshakeServerboundV47(Recycler.Handle<ReferenceCountedHandshakeServerboundV47> handle,
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

    private void reset() {
        setProtocolVersion(0);
        setServerAddress(null);
        setServerPort(0);
        setNextState(null);
    }
}
