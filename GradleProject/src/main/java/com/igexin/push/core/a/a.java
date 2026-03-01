package com.igexin.push.core.a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3306a;

    private void d() {
        if (this.f3306a) {
            return;
        }
        this.f3306a = true;
    }

    private boolean e() {
        return this.f3306a;
    }

    public abstract void a();

    public boolean a(Object obj) {
        return true;
    }

    public abstract void b();

    public boolean c() {
        return true;
    }
}
