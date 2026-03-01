package com.loc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: ByteJoinDataStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class be extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ByteArrayOutputStream f3677a;

    public be() {
        this.f3677a = new ByteArrayOutputStream();
    }

    public be(bk bkVar) {
        super(bkVar);
        this.f3677a = new ByteArrayOutputStream();
    }

    @Override // com.loc.bk
    public final byte[] a(byte[] bArr) {
        byte[] byteArray = this.f3677a.toByteArray();
        try {
            this.f3677a.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.f3677a = new ByteArrayOutputStream();
        return byteArray;
    }

    @Override // com.loc.bk
    public final void b(byte[] bArr) {
        try {
            this.f3677a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
