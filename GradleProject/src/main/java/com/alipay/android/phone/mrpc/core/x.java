package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f1576a;
    public z b = new z(this);

    public x(g gVar) {
        this.f1576a = gVar;
    }

    public final g a() {
        return this.f1576a;
    }

    public final <T> T a(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new y(this.f1576a, cls, this.b));
    }
}
