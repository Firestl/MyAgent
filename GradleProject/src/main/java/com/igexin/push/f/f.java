package com.igexin.push.f;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements com.igexin.push.f.b.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3568a = "SilentTask";
    public static f b;
    public boolean c = com.igexin.push.g.c.a(System.currentTimeMillis());

    public static f a() {
        if (b == null) {
            b = new f();
        }
        return b;
    }

    @Override // com.igexin.push.f.b.c
    public final void a(long j) {
    }

    @Override // com.igexin.push.f.b.c
    public final void b() {
        d();
    }

    @Override // com.igexin.push.f.b.c
    public final boolean c() {
        return com.igexin.push.config.d.c != 0;
    }

    public final void d() {
        boolean z = this.c;
        boolean zA = com.igexin.push.g.c.a(System.currentTimeMillis());
        this.c = zA;
        if (!z || zA) {
            return;
        }
        com.igexin.c.a.c.a.b(f3568a, "out silence time");
        a.a().a(false);
    }
}
