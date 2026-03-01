package com.igexin.push.c;

import android.text.TextUtils;
import com.igexin.push.c.b;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends h implements i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static g f3266e;

    public g() {
        super(com.igexin.push.core.e.ap, com.igexin.push.core.e.ar);
        this.d.j = false;
    }

    public static synchronized g a() {
        if (f3266e == null) {
            f3266e = new g();
        }
        return f3266e;
    }

    @Override // com.igexin.push.c.i
    public final void a(int i, d dVar) {
        e eVarA;
        if (dVar == null || TextUtils.isEmpty(dVar.a()) || (eVarA = a(dVar.a())) == null) {
            return;
        }
        a(dVar);
        eVarA.a();
        m();
        if (i == b.a.f3256a) {
            l();
        }
    }

    @Override // com.igexin.push.c.i
    public final void b() {
    }

    @Override // com.igexin.push.c.h
    public final int c() {
        return b.EnumC0076b.b;
    }

    @Override // com.igexin.push.c.h
    public final i d() {
        return this;
    }
}
