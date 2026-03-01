package com.igexin.push.f.b;

import android.os.SystemClock;
import android.text.TextUtils;
import com.igexin.push.core.e.f.AnonymousClass13;
import com.igexin.push.core.k;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends f {
    public static final int b = -2147483641;
    public static final String c = "RNTT";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static e f3562e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3563a;
    public long f;

    public e() {
        super(com.igexin.push.config.c.g, (byte) 0);
        this.p = true;
        this.f = System.currentTimeMillis();
        this.f3563a = SystemClock.elapsedRealtime();
    }

    private void c(long j) {
        this.f3563a = j;
    }

    public static synchronized e g() {
        if (f3562e == null) {
            f3562e = new e();
        }
        return f3562e;
    }

    private void h() {
        a(com.igexin.push.core.e.O);
    }

    public final void a(long j) {
        com.igexin.c.a.c.a.a("RNTT|refreshDelayTime, delay = ".concat(String.valueOf(j)), new Object[0]);
        a(j, TimeUnit.MILLISECONDS);
    }

    @Override // com.igexin.push.f.b.f
    public final void b() {
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.k();
        boolean zA = com.igexin.push.g.c.a();
        com.igexin.push.core.e.n = com.igexin.push.g.c.e();
        boolean z = com.igexin.push.core.e.u;
        boolean z2 = com.igexin.push.core.e.p;
        boolean z3 = com.igexin.push.core.e.s;
        com.igexin.c.a.c.a.a("RNTT|networkAvailable = " + com.igexin.push.core.e.n + "|,sdkOnline = " + com.igexin.push.core.e.u + ", sdkOn= " + com.igexin.push.core.e.p + ", pushOn =" + com.igexin.push.core.e.s + ", blockEndTime= " + zA, new Object[0]);
        if (!com.igexin.push.core.e.n || !com.igexin.push.core.e.p || !com.igexin.push.core.e.s || com.igexin.push.core.e.u || !zA) {
            com.igexin.c.a.c.a.a("RNTT reconnect timer task stop, connect interval = 20min #######", new Object[0]);
            a(com.igexin.push.config.c.g, TimeUnit.MILLISECONDS);
            return;
        }
        if (!com.igexin.push.g.c.f() && TextUtils.isEmpty(com.igexin.push.core.e.A)) {
            a(900000L, TimeUnit.MILLISECONDS);
            com.igexin.c.a.c.a.a(c, "date is error, set connect interval = 15min");
            com.igexin.c.a.c.a.a("RNTT|date is error, set connect interval = 15min", new Object[0]);
            return;
        }
        com.igexin.c.a.c.a.a("RNTT reconnect timer task isOnline = false, try login...", new Object[0]);
        if (System.currentTimeMillis() - this.f < 2500) {
            com.igexin.push.core.e.r++;
        }
        if (com.igexin.push.core.e.r > 30 && Math.abs(SystemClock.elapsedRealtime() - this.f3563a) < 72000.0d) {
            com.igexin.push.core.e.f.a();
            String str = com.igexin.push.core.e.A;
            com.igexin.c.a.c.a.a(com.igexin.push.core.e.f.f3417a + "| found a duplicate cid " + com.igexin.push.core.e.A, new Object[0]);
            com.igexin.push.core.e.L = null;
            com.igexin.push.core.e.f.d();
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.core.e.f.a().new AnonymousClass13(com.igexin.push.core.e.L), false, true);
            com.igexin.push.core.e.f.a().b();
            com.igexin.push.core.e.r = 0;
            g().f3563a = SystemClock.elapsedRealtime();
        }
        this.f = System.currentTimeMillis();
        k.a();
        k.b();
        a(1800000L, TimeUnit.MILLISECONDS);
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return b;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d() {
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d_() {
        super.d_();
    }
}
