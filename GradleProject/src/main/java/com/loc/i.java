package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.taobao.weex.common.Constants;
import io.dcloud.common.util.Md5Utils;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: LastLocationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i {
    public static ea b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ae f3805e;
    public static long g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3806a = null;
    public ea c = null;
    public ea d = null;
    public long f = 0;
    public boolean h = false;
    public Context i;

    public i(Context context) {
        this.i = context.getApplicationContext();
    }

    private void e() {
        if (b == null || ep.b() - g > 180000) {
            ea eaVarF = f();
            g = ep.b();
            if (eaVarF == null || !ep.a(eaVarF.a())) {
                return;
            }
            b = eaVarF;
        }
    }

    private ea f() {
        Throwable th;
        ea eaVar;
        byte[] bArrB;
        byte[] bArrB2;
        String str = null;
        if (this.i == null) {
            return null;
        }
        a();
        try {
        } catch (Throwable th2) {
            th = th2;
            eaVar = null;
        }
        if (f3805e == null) {
            return null;
        }
        List listA = f3805e.a("_id=1", ea.class);
        if (listA.size() > 0) {
            eaVar = (ea) listA.get(0);
            try {
                byte[] bArrB3 = o.b(eaVar.c());
                String str2 = (bArrB3 == null || bArrB3.length <= 0 || (bArrB2 = dy.b(bArrB3, this.f3806a)) == null || bArrB2.length <= 0) ? null : new String(bArrB2, "UTF-8");
                byte[] bArrB4 = o.b(eaVar.b());
                if (bArrB4 != null && bArrB4.length > 0 && (bArrB = dy.b(bArrB4, this.f3806a)) != null && bArrB.length > 0) {
                    str = new String(bArrB, "UTF-8");
                }
                eaVar.a(str);
                str = str2;
            } catch (Throwable th3) {
                th = th3;
                ej.a(th, "LastLocationManager", "readLastFix");
            }
        } else {
            eaVar = null;
        }
        if (!TextUtils.isEmpty(str)) {
            AMapLocation aMapLocation = new AMapLocation("");
            ej.a(aMapLocation, new JSONObject(str));
            if (ep.b(aMapLocation)) {
                eaVar.a(aMapLocation);
            }
        }
        return eaVar;
        ej.a(th, "LastLocationManager", "readLastFix");
        return eaVar;
    }

    public final AMapLocation a(AMapLocation aMapLocation, String str, long j) {
        if (aMapLocation == null || aMapLocation.getErrorCode() == 0 || aMapLocation.getLocationType() == 1 || aMapLocation.getErrorCode() == 7) {
            return aMapLocation;
        }
        try {
            e();
        } catch (Throwable th) {
            th = th;
        }
        if (b != null && b.a() != null) {
            boolean zA = false;
            if (TextUtils.isEmpty(str)) {
                long jB = ep.b() - b.d();
                if (jB >= 0 && jB <= j) {
                    zA = true;
                }
                aMapLocation.setTrustedLevel(3);
            } else {
                zA = ep.a(b.b(), str);
                aMapLocation.setTrustedLevel(2);
            }
            if (!zA) {
                return aMapLocation;
            }
            AMapLocation aMapLocationA = b.a();
            try {
                aMapLocationA.setLocationType(9);
                aMapLocationA.setFixLastLocation(true);
                aMapLocationA.setLocationDetail(aMapLocation.getLocationDetail());
                return aMapLocationA;
            } catch (Throwable th2) {
                th = th2;
                aMapLocation = aMapLocationA;
            }
            ej.a(th, "LastLocationManager", "fixLastLocation");
            return aMapLocation;
        }
        return aMapLocation;
    }

    public final void a() {
        if (this.h) {
            return;
        }
        try {
            if (this.f3806a == null) {
                this.f3806a = dy.a(Md5Utils.ALGORITHM, n.x(this.i));
            }
            if (f3805e == null) {
                f3805e = new ae(this.i, ae.a((Class<? extends ad>) eb.class));
            }
        } catch (Throwable th) {
            ej.a(th, "LastLocationManager", "<init>:DBOperation");
        }
        this.h = true;
    }

    public final boolean a(AMapLocation aMapLocation, String str) {
        if (this.i != null && aMapLocation != null && ep.a(aMapLocation) && aMapLocation.getLocationType() != 2 && !aMapLocation.isMock() && !aMapLocation.isFixLastLocation()) {
            ea eaVar = new ea();
            eaVar.a(aMapLocation);
            if (aMapLocation.getLocationType() == 1) {
                eaVar.a((String) null);
            } else {
                eaVar.a(str);
            }
            try {
                b = eaVar;
                g = ep.b();
                this.c = eaVar;
                if (this.d != null && ep.a(this.d.a(), eaVar.a()) <= 500.0f) {
                    return false;
                }
                if (ep.b() - this.f > com.igexin.push.config.c.k) {
                    return true;
                }
            } catch (Throwable th) {
                ej.a(th, "LastLocationManager", "setLastFix");
            }
        }
        return false;
    }

    public final AMapLocation b() {
        e();
        ea eaVar = b;
        if (eaVar != null && ep.a(eaVar.a())) {
            return b.a();
        }
        return null;
    }

    public final void c() {
        try {
            d();
            this.f = 0L;
            this.h = false;
            this.c = null;
            this.d = null;
        } catch (Throwable th) {
            ej.a(th, "LastLocationManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
    }

    public final void d() {
        String strB;
        try {
            a();
            if (this.c != null && ep.a(this.c.a()) && f3805e != null && this.c != this.d && this.c.d() == 0) {
                String str = this.c.a().toStr();
                String strB2 = this.c.b();
                this.d = this.c;
                if (TextUtils.isEmpty(str)) {
                    strB = null;
                } else {
                    String strB3 = o.b(dy.a(str.getBytes("UTF-8"), this.f3806a));
                    strB = TextUtils.isEmpty(strB2) ? null : o.b(dy.a(strB2.getBytes("UTF-8"), this.f3806a));
                    str = strB3;
                }
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                ea eaVar = new ea();
                eaVar.b(str);
                eaVar.a(ep.b());
                eaVar.a(strB);
                f3805e.a(eaVar, "_id=1");
                this.f = ep.b();
                if (b != null) {
                    b.a(ep.b());
                }
            }
        } catch (Throwable th) {
            ej.a(th, "LastLocationManager", "saveLastFix");
        }
    }
}
