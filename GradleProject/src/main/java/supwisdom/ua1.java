package supwisdom;

import android.content.pm.PackageManager;
import com.sangfor.sdk.sandbox.business.ConfigManager;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ua1 extends db1 {
    public static ua1 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public wa1 f9388a;
    public xa1 b;
    public qb1 c = (qb1) ConfigManager.a("share_restriction");

    public ua1() {
        f();
    }

    public static ua1 g() {
        synchronized (ua1.class) {
            if (d == null) {
                d = new ua1();
            }
        }
        return d;
    }

    public xa1 a() {
        return this.b;
    }

    public final void b() {
        if (a(u91.d())) {
            t91.c("ShareBusiness", "hook activityManagerService success in share business");
        } else {
            t91.b("ShareBusiness", "hook activityManagerService failed in share business");
        }
        if (lb1.a(n91.d())) {
            if (a(u91.d())) {
                t91.c("ShareBusiness", "hook activityManagerService success in share business for camera");
            } else {
                t91.b("ShareBusiness", "hook activityManagerService failed in share business for camera");
            }
        }
        if (a(u91.d())) {
            t91.c("ShareBusiness", "hook activityManagerService success in share business");
        } else {
            t91.b("ShareBusiness", "hook activityManagerService failed in share business");
        }
    }

    public final void c() {
        if (mb1.b()) {
            if (a(w91.e())) {
                t91.c("ShareBusiness", "hook TransactionHandler success in share business above android9.0");
                return;
            } else {
                t91.b("ShareBusiness", "hook TransactionHandler failed in share business above android9.0");
                return;
            }
        }
        if (!a(v91.f())) {
            t91.b("ShareBusiness", "hook HCallBack failed in share business below android9.0");
        } else {
            v91.f().e();
            t91.c("ShareBusiness", "hook HCallBack success in share business below android9.0");
        }
    }

    public final void d() {
        if (x91.f() != null) {
            t91.c("ShareBusiness", "hook Instrumentation success in share business");
        } else {
            t91.b("ShareBusiness", "hook Instrumentation failed in share business");
        }
    }

    public final void e() {
        if (a(y91.d())) {
            t91.c("ShareBusiness", "hook PackageManager success in share business");
        } else {
            t91.b("ShareBusiness", "hook PackageManager failed in share business");
        }
        try {
            PackageManager packageManager = n91.b().getPackageManager();
            kb1.a(packageManager, "mPM").set(packageManager, aa1.c.a());
        } catch (Exception e2) {
            t91.a("ShareBusiness", "hookPackageManager failed", "replace packagemanager for context failed", e2);
        }
    }

    public final void f() {
        hb1.a(this.c);
        if (!this.c.b()) {
            t91.d("ShareBusiness", "shared hook invalid by config");
            return;
        }
        t91.c("ShareBusiness", "shared hook valid, config : " + this.c.toString());
        this.f9388a = new wa1(n91.b(), this.c.a(), this.c.c());
        this.b = new xa1(n91.b(), this.f9388a);
        e();
        b();
        d();
        c();
    }
}
