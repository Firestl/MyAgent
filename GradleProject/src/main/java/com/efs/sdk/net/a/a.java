package com.efs.sdk.net.a;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static a c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, c> f1899a;
    public HashMap<String, d> b;

    public a() {
        b();
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private void b() {
        if (this.f1899a == null) {
            this.f1899a = new HashMap<>();
        }
        this.f1899a.clear();
    }

    public final d c(String str) {
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        d dVar = new d();
        dVar.A = str;
        dVar.D = System.currentTimeMillis();
        this.b.put(str, dVar);
        return dVar;
    }

    public final void d(String str) {
        HashMap<String, d> map = this.b;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        this.b.remove(str);
    }

    public final c a(String str) {
        if (this.f1899a == null) {
            b();
        }
        c cVar = this.f1899a.get(str);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c();
        cVar2.f1909a = str;
        cVar2.b = System.currentTimeMillis();
        this.f1899a.put(str, cVar2);
        return cVar2;
    }

    public final void b(String str) {
        HashMap<String, c> map = this.f1899a;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        this.f1899a.remove(str);
    }
}
