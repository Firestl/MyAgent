package com.igexin.push.f.b;

import com.igexin.push.core.j;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3558a = -2147483642;
    public static final String b = "HeartBeatTimerTask";
    public static b c;

    public b() {
        super(j.a().b(), (byte) 0);
        this.p = true;
    }

    public static b g() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    public static void p() {
    }

    @Override // com.igexin.push.f.b.f
    public final void b() {
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.k();
        com.igexin.push.core.e.P = System.currentTimeMillis();
        if (!com.igexin.push.core.e.u) {
            com.igexin.c.a.c.a.a("HeartBeatTimerTask doTaskMethod isOnline = false, refresh wait time !!!!!!", new Object[0]);
            h();
        } else {
            System.currentTimeMillis();
            com.igexin.c.a.c.a.a("heartbeatReq", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.f();
        }
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return f3558a;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d() {
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d_() {
        super.d_();
        if (this.m) {
            return;
        }
        h();
    }

    public final void h() {
        a(j.a().b(), TimeUnit.MILLISECONDS);
    }
}
