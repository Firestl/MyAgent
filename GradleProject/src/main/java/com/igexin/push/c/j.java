package com.igexin.push.c;

import android.text.TextUtils;
import com.igexin.push.c.b;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends h implements i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static j f3271e;

    public j() {
        super(com.igexin.push.core.e.aq, com.igexin.push.core.e.as);
        this.d.j = true;
    }

    public static synchronized j a() {
        if (f3271e == null) {
            f3271e = new j();
        }
        return f3271e;
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
        return b.EnumC0076b.f3257a;
    }

    @Override // com.igexin.push.c.h
    public final i d() {
        return this;
    }
}
