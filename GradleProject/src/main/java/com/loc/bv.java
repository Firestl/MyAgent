package com.loc;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: RobustFlatBufferBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bv extends er {
    public bv(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // com.loc.er
    public final int a(CharSequence charSequence) {
        try {
            return super.a(charSequence);
        } catch (Throwable th) {
            dg.a(th);
            return super.a("");
        }
    }
}
