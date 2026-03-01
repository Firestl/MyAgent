package com.igexin.push.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public static final String c = "ExtensionTask";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3556a = 0;
    public long b = 0;

    private void a(long j) {
        this.f3556a = j;
    }

    private boolean b() {
        System.currentTimeMillis();
        return System.currentTimeMillis() - this.f3556a > this.b;
    }

    public abstract void a();
}
