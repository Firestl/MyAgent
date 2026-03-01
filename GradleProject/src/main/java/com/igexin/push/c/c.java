package com.igexin.push.c;

import com.igexin.push.c.a;
import com.igexin.push.c.b;
import com.igexin.push.config.SDKUrlConfig;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3258a = b.f3254a + c.class.getName();
    public static c b;
    public static int c;

    public c() {
        c = com.igexin.push.g.c.b() ? b.EnumC0076b.f3257a : b.EnumC0076b.b;
    }

    public static synchronized c a() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    public static void b() {
        if (SDKUrlConfig.hasMultipleXfr()) {
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) f.g(), false, true);
            return;
        }
        com.igexin.c.a.c.a.a(f3258a + "|xfr len = 1, detect = false", new Object[0]);
    }

    public final void c() {
        if (SDKUrlConfig.hasMultipleXfr()) {
            try {
                f().e();
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    public final a d() {
        return f().d;
    }

    public final void e() {
        if (SDKUrlConfig.hasMultipleXfr()) {
            try {
                j.a();
                h.k();
                j.a().g();
                g.a().g();
                h hVarF = f();
                if (hVarF != null) {
                    hVarF.i();
                    return;
                }
                return;
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
                return;
            }
        }
        j.a().f();
        g.a().f();
        f.g().h();
        try {
            g.a().d.a((List<a.b>) null);
            j.a().d.a((List<a.b>) null);
            j.a().h();
            g.a().h();
            j.a();
            h.k();
        } catch (Throwable th2) {
            com.igexin.c.a.c.a.a(th2);
        }
    }

    public final synchronized h f() {
        h hVarA;
        h hVarA2;
        hVarA = com.igexin.push.g.c.b() ? j.a() : g.a();
        int iC = hVarA.c();
        if (iC != c) {
            if (iC == b.EnumC0076b.f3257a) {
                hVarA2 = g.a();
            } else if (iC == b.EnumC0076b.b) {
                hVarA2 = j.a();
            }
            hVarA2.f();
        }
        c = iC;
        return hVarA;
    }
}
