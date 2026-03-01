package com.efs.sdk.net.a.a;

import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f1906a;
    public ByteArrayOutputStream b;
    public a c;
    public final String d;

    public h(f fVar, String str) {
        this.f1906a = fVar;
        this.d = str;
    }

    public final boolean a() {
        return this.b != null;
    }

    public final void b() {
        if (!a()) {
            throw new IllegalStateException("No body found; has createBodySink been called?");
        }
    }
}
