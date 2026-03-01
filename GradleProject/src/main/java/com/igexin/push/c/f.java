package com.igexin.push.c;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class f extends com.igexin.push.f.b.f {
    public static f c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3265e;
    public static final String b = b.f3254a + f.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f3264a = new AtomicBoolean(false);

    public f() {
        super(10L, (byte) 0);
        this.p = true;
    }

    private void a(long j) {
        a(j, TimeUnit.MILLISECONDS);
    }

    public static synchronized f g() {
        if (c == null) {
            c = new f();
        }
        return c;
    }

    @Override // com.igexin.push.f.b.f
    public final void b() {
        a(b.c, TimeUnit.MILLISECONDS);
        if (this.f3265e) {
            com.igexin.c.a.c.a.a(b, "detect task already stop");
            com.igexin.c.a.c.a.a(b + "|detect task already stop", new Object[0]);
            return;
        }
        long j = b.c;
        com.igexin.c.a.c.a.a(b + "|" + (b.c / 1000) + "s passed, do task method, start redect ~~~~", new Object[0]);
        boolean zE = com.igexin.push.g.c.e();
        com.igexin.push.core.e.n = zE;
        if (zE) {
            c.a().c();
            return;
        }
        long j2 = b.c;
        com.igexin.c.a.c.a.a(b + "|" + (b.c / 1000) + "s passed, network is unavailable, stop ###", new Object[0]);
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return 20150607;
    }

    public final void h() {
        this.p = false;
        this.f3265e = true;
        k();
    }
}
