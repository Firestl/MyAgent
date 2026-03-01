package com.igexin.push.f.b;

import com.igexin.push.d.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3559a = 20160629;
    public static final long b = 604800000;
    public static final String c = "PollingTimerTask";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3560e;
    public AtomicBoolean f;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f3561a = new d();
    }

    public d() {
        super(604800000L, (byte) 0);
        this.f3560e = com.igexin.push.config.d.y;
        this.f = new AtomicBoolean(false);
        this.p = true;
    }

    private void a(long j) {
        a(j, TimeUnit.MILLISECONDS);
    }

    public static d p() {
        return a.f3561a;
    }

    @Override // com.igexin.push.f.b.f
    public final void b() {
        com.igexin.push.d.b bVar;
        a(this.f3560e, TimeUnit.MILLISECONDS);
        if (!com.igexin.push.core.e.u && com.igexin.push.core.e.n && com.igexin.push.core.e.p && com.igexin.push.core.e.s && com.igexin.push.g.c.a()) {
            com.igexin.c.a.c.a.a("PollingTimerTask|run = true", new Object[0]);
            com.igexin.push.d.c cVar = c.b.f3517a;
            if (cVar.b && (bVar = cVar.f3515e) != null && !(bVar instanceof com.igexin.push.d.d)) {
                cVar.f3515e = new com.igexin.push.d.d();
            }
            com.igexin.push.core.e.b(100L);
            e.g().a(com.igexin.push.core.e.O);
        }
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return f3559a;
    }

    public final void g() {
        if (!this.f.getAndSet(true)) {
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) this, false, true);
        }
        a(this.f3560e);
    }

    public final void h() {
        a(604800000L, TimeUnit.MILLISECONDS);
    }
}
