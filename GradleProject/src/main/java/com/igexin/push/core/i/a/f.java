package com.igexin.push.core.i.a;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends b<e> implements l {
    public f(e eVar) {
        super(eVar);
    }

    @Override // com.igexin.push.core.i.a.b, com.igexin.push.core.i.a.l
    public final void b() {
        ((e) this.f3466a).a().prepareToDraw();
    }

    @Override // com.igexin.push.core.i.a.m
    public final Class<e> d() {
        return e.class;
    }

    @Override // com.igexin.push.core.i.a.m
    public final int e() {
        h hVar = ((e) this.f3466a).c.f3472a;
        return hVar.f3475a.m() + hVar.j;
    }

    @Override // com.igexin.push.core.i.a.m
    public final void f() {
        ((e) this.f3466a).stop();
        e eVar = (e) this.f3466a;
        eVar.f3471e = true;
        h hVar = eVar.c.f3472a;
        hVar.b.clear();
        hVar.b();
        hVar.c = false;
        if (hVar.f3476e != null) {
            hVar.f3476e = null;
        }
        if (hVar.g != null) {
            hVar.g = null;
        }
        if (hVar.i != null) {
            hVar.i = null;
        }
        hVar.f3475a.o();
        hVar.f = true;
    }
}
