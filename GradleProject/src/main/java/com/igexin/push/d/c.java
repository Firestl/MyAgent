package com.igexin.push.d;

import android.content.Intent;
import android.os.Bundle;
import com.igexin.push.f.b.d;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3514a = "ConnectModelCoordinator";
    public static final long i = 20000;
    public static final long j = 200000;
    public boolean b;
    public long c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public com.igexin.push.d.b f3515e;
    public int f;
    public int g;
    public int h;
    public long k;
    public a l;

    public enum a {
        WIFI,
        MOBILE
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f3517a = new c(0);
    }

    public c() {
        this.f = com.igexin.push.config.d.x;
        this.g = com.igexin.push.config.d.z;
        this.f3515e = new d();
        this.l = com.igexin.push.g.c.b() ? a.WIFI : a.MOBILE;
    }

    public /* synthetic */ c(byte b2) {
        this();
    }

    public static void a(int i2) {
        if (com.igexin.push.core.e.l == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("com.igexin.sdk.action.polling");
            Bundle bundle = new Bundle();
            bundle.putInt("code", i2);
            intent.putExtras(bundle);
            intent.setPackage(com.igexin.push.core.e.l.getPackageName());
            com.igexin.push.core.e.l.sendBroadcast(intent);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(f3514a, th.toString());
        }
    }

    private void a(boolean z) {
        this.b = z;
        com.igexin.c.a.c.a.a("ConnectModelCoordinator|init, current is polling mdl = ".concat(String.valueOf(z)), new Object[0]);
        if (z) {
            d.a.f3561a.g();
        }
    }

    private com.igexin.push.d.b e() {
        return this.f3515e;
    }

    public static c f() {
        return b.f3517a;
    }

    private void g() {
        this.c = System.currentTimeMillis();
        if (!this.b) {
            b();
            return;
        }
        com.igexin.c.a.c.a.a(f3514a, "loginRsp| enter polling");
        this.f3515e = new e();
        d.a.f3561a.g();
        this.d = 0;
    }

    private void h() {
        com.igexin.push.d.b bVar;
        if (!this.b || (bVar = this.f3515e) == null || (bVar instanceof d)) {
            return;
        }
        this.f3515e = new d();
    }

    public static void i() {
        a(0);
    }

    public static void j() {
        a(1);
    }

    public final synchronized void a() {
        a aVar = com.igexin.push.g.c.b() ? a.WIFI : a.MOBILE;
        if (aVar != this.l) {
            com.igexin.c.a.c.a.a(f3514a, "net type changed " + this.l + "->" + aVar);
            com.igexin.c.a.c.a.a("ConnectModelCoordinator|net type changed " + this.l + "->" + aVar, new Object[0]);
            b();
            this.l = aVar;
        }
    }

    public final void b() {
        com.igexin.c.a.c.a.a("ConnectModelCoordinator|reset current mdl = normal", new Object[0]);
        com.igexin.push.d.b bVar = this.f3515e;
        if (bVar != null && !(bVar instanceof d)) {
            this.f3515e = new d();
        }
        d.a.f3561a.h();
        this.d = 0;
        this.h = 0;
        this.b = false;
        com.igexin.push.core.e.f.a().b(this.b);
    }

    public final synchronized void c() {
        if (this.b) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
        if (jCurrentTimeMillis > i && jCurrentTimeMillis < j) {
            this.h++;
            com.igexin.c.a.c.a.a(f3514a, "read len = -1, interval = " + jCurrentTimeMillis + ", tcpDisconnectSuccess =" + this.h);
            com.igexin.c.a.c.a.a("ConnectModelCoordinator|read len = -1, interval = " + jCurrentTimeMillis + ", tcpDisconnectSuccess =" + this.h, new Object[0]);
            if (this.h >= this.f) {
                com.igexin.c.a.c.a.a(f3514a, "enter polling mode #####");
                com.igexin.c.a.c.a.a("ConnectModelCoordinator|enter polling mode ####", new Object[0]);
                a(0);
                this.b = true;
                this.f3515e = new e();
                d.a.f3561a.g();
                com.igexin.push.core.e.f.a().b(this.b);
            }
        }
    }

    public final synchronized void d() {
        if (this.b) {
            this.d++;
            com.igexin.c.a.c.a.a(f3514a, "polling mode, cur heartbeat = " + this.d);
            com.igexin.c.a.c.a.a("ConnectModelCoordinator|polling mode, cur heartbeat =" + this.d, new Object[0]);
            if (this.d >= this.g) {
                com.igexin.c.a.c.a.a(f3514a, "enter normal mode #####");
                com.igexin.c.a.c.a.a("ConnectModelCoordinator|enter normal mode ####", new Object[0]);
                a(1);
                com.igexin.push.core.e.b(0L);
                b();
            }
        }
    }
}
