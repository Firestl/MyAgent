package com.igexin.push.core.i;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Long f3464a = Long.valueOf(System.currentTimeMillis());
    public Activity b;
    public String c;

    private void a(Long l) {
        this.f3464a = l;
    }

    private void a(String str) {
        this.c = str;
    }

    private Activity n() {
        return this.b;
    }

    public final Long a() {
        return this.f3464a;
    }

    public final void a(Activity activity) {
        this.b = activity;
    }

    public final String b() {
        return this.c;
    }

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract boolean j();

    public abstract void k();

    public abstract boolean l();

    public abstract void m();
}
