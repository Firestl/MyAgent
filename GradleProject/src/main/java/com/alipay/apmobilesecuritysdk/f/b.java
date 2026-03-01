package com.alipay.apmobilesecuritysdk.f;

import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b f1591a = new b();
    public Thread b = null;
    public LinkedList<Runnable> c = new LinkedList<>();

    public static b a() {
        return f1591a;
    }

    public static /* synthetic */ Thread b(b bVar) {
        bVar.b = null;
        return null;
    }

    public final synchronized void a(Runnable runnable) {
        this.c.add(runnable);
        if (this.b == null) {
            Thread thread = new Thread(new c(this));
            this.b = thread;
            thread.start();
        }
    }
}
