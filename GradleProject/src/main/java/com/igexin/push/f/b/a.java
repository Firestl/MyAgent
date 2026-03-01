package com.igexin.push.f.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class a extends f {
    public static volatile a b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<c> f3557a;

    public a() {
        super(60000L, (byte) 0);
        this.p = true;
        this.f3557a = new ArrayList();
    }

    public static a g() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private void h() {
        a(com.igexin.push.f.a.b, TimeUnit.MILLISECONDS);
    }

    public final boolean a(c cVar) {
        List<c> list = this.f3557a;
        return (list == null || list.contains(cVar) || !this.f3557a.add(cVar)) ? false : true;
    }

    @Override // com.igexin.push.f.b.f
    public final void b() {
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.k();
        for (c cVar : this.f3557a) {
            if (cVar.c()) {
                cVar.b();
                cVar.a(System.currentTimeMillis());
            }
        }
        a(com.igexin.push.f.a.b, TimeUnit.MILLISECONDS);
        com.igexin.c.a.b.e.a().a((Object) this);
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return 0;
    }
}
