package com.loc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: AbstractBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class bt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public bv f3692a;
    public ByteBuffer b;

    public bt(int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        this.b = byteBufferAllocate;
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        this.f3692a = new bv(this.b);
    }

    public final bt a() {
        this.f3692a.a(this.b);
        return this;
    }
}
