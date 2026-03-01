package com.loc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;

/* JADX INFO: compiled from: Aps.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public final class dn {
    public static int D = -1;
    public static boolean K = false;
    public static volatile boolean P = false;
    public Handler N;
    public String O;
    public Cdo Q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3735a = null;
    public ConnectivityManager b = null;
    public dx c = null;
    public dw d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public dz f3736e = null;
    public dp f = null;
    public eg g = null;
    public ArrayList<ScanResult> h = new ArrayList<>();
    public a i = null;
    public AMapLocationClientOption j = new AMapLocationClientOption();
    public ds k = null;
    public long l = 0;
    public int I = 0;
    public eh m = null;
    public boolean n = false;
    public String J = null;
    public ee o = null;
    public StringBuilder p = new StringBuilder();
    public boolean q = true;
    public boolean r = true;
    public AMapLocationClientOption.GeoLanguage s = AMapLocationClientOption.GeoLanguage.DEFAULT;
    public boolean t = true;
    public boolean u = false;
    public WifiInfo v = null;
    public boolean w = true;
    public String L = null;
    public StringBuilder x = null;
    public boolean y = false;
    public boolean z = false;
    public int A = 12;
    public boolean M = true;
    public dt B = null;
    public boolean C = false;
    public dr E = null;
    public String F = null;
    public IntentFilter G = null;
    public LocationManager H = null;

    /* JADX INFO: renamed from: com.loc.dn$1, reason: invalid class name */
    /* JADX INFO: compiled from: Aps.java */
    public static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3737a;

        static {
            int[] iArr = new int[AMapLocationClientOption.GeoLanguage.values().length];
            f3737a = iArr;
            try {
                iArr[AMapLocationClientOption.GeoLanguage.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3737a[AMapLocationClientOption.GeoLanguage.ZH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3737a[AMapLocationClientOption.GeoLanguage.EN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: Aps.java */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                return;
            }
            try {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (!action.equals("android.net.wifi.SCAN_RESULTS")) {
                    if (!action.equals("android.net.wifi.WIFI_STATE_CHANGED") || dn.this.c == null) {
                        return;
                    }
                    dn.this.c.j();
                    return;
                }
                if (dn.this.c != null) {
                    dn.this.c.i();
                }
                try {
                    if (intent.getExtras() == null || !intent.getExtras().getBoolean("resultsUpdated", true) || dn.this.c == null) {
                        return;
                    }
                    dn.this.c.h();
                } catch (Throwable unused) {
                }
            } catch (Throwable th) {
                ej.a(th, "Aps", "onReceive");
            }
        }
    }

    public static ds a(int i, String str) {
        ds dsVar = new ds("");
        dsVar.setErrorCode(i);
        dsVar.setLocationDetail(str);
        if (i == 15) {
            en.a((String) null, 2151);
        }
        return dsVar;
    }

    private ds a(ds dsVar, aw awVar, dm dmVar) {
        if (awVar != null) {
            try {
                if (awVar.f3662a != null && awVar.f3662a.length != 0) {
                    eg egVar = new eg();
                    String str = new String(awVar.f3662a, "UTF-8");
                    if (str.contains("\"status\":\"0\"")) {
                        ds dsVarA = egVar.a(str, this.f3735a, awVar, dmVar);
                        dsVarA.h(this.x.toString());
                        return dsVarA;
                    }
                    if (!str.contains("</body></html>")) {
                        return null;
                    }
                    dsVar.setErrorCode(5);
                    if (this.c == null || !this.c.a(this.b)) {
                        dmVar.f("#0502");
                        this.p.append("请求可能被劫持了#0502");
                        en.a((String) null, 2052);
                    } else {
                        dmVar.f("#0501");
                        this.p.append("您连接的是一个需要登录的网络，请确认已经登入网络#0501");
                        en.a((String) null, 2051);
                    }
                    dsVar.setLocationDetail(this.p.toString());
                    return dsVar;
                }
            } catch (Throwable th) {
                dsVar.setErrorCode(4);
                ej.a(th, "Aps", "checkResponseEntity");
                dmVar.f("#0403");
                this.p.append("check response exception ex is" + th.getMessage() + "#0403");
                dsVar.setLocationDetail(this.p.toString());
                return dsVar;
            }
        }
        dsVar.setErrorCode(4);
        this.p.append("网络异常,请求异常#0403");
        dmVar.f("#0403");
        dsVar.h(this.x.toString());
        dsVar.setLocationDetail(this.p.toString());
        if (awVar != null) {
            en.a(awVar.d, 2041);
        }
        return dsVar;
    }

    private StringBuilder a(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        sb.append(this.d.m());
        sb.append(this.c.m());
        return sb;
    }

    private void a(dm dmVar, ef efVar, String str, String str2, String str3, int i) {
        dmVar.b(str3);
        dmVar.c("FAIL");
        ec.a(this.f3735a).a(false, i);
        efVar.a(str);
        efVar.b(str2);
        if (this.o.a() > ei.q()) {
            efVar.a(ei.q() * 1000);
            efVar.b(ei.q() * 1000);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [com.loc.ee] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v2, types: [int] */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.loc.dn] */
    /* JADX WARN: Type inference failed for: r21v0, types: [com.loc.dm] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @SuppressLint({"NewApi"})
    private ds b(boolean z, dm dmVar) {
        ?? A;
        StringBuilder sb;
        StringBuilder sb2;
        String str;
        ef efVarA;
        String strC;
        String strD;
        boolean z2;
        aw awVarA;
        long jB;
        ec ecVarA;
        int i;
        String str2;
        char c;
        try {
            if (TextUtils.isEmpty(this.O)) {
                this.O = u.b(n.a(this.f3735a) + "," + n.h(this.f3735a));
            }
            StringBuilder sb3 = this.p;
            sb3.append("#id:");
            sb3.append(this.O);
        } catch (Throwable unused) {
        }
        ds dsVar = new ds("");
        try {
            if (this.m == null) {
                this.m = new eh();
            }
            if (this.j == null) {
                this.j = new AMapLocationClientOption();
            }
            this.m.a(this.f3735a, this.j.isNeedAddress(), this.j.isOffset(), this.d, this.c, this.b, this.F);
            byte[] bArrA = this.m.a();
            long jB2 = ep.b();
            this.l = jB2;
            dmVar.a(jB2);
            try {
                ej.c(this.f3735a);
                ?? r11 = this.o;
                Context context = this.f3735a;
                A = ej.a();
                efVarA = r11.a(context, bArrA, A, ej.b(), z);
                strC = efVarA.c();
                strD = efVarA.d();
                l.a(this.f3735a);
                z2 = !TextUtils.isEmpty(strD) && strD.contains("dualstack");
            } catch (Throwable th) {
                th = th;
                A = 4;
            }
            try {
                if (l.a() && l.c() && z2) {
                    dmVar.a("v6");
                    A = l.b() ? null : ec.a(this.f3735a).a(efVarA, ec.b);
                    if (TextUtils.isEmpty(A)) {
                        c = 4;
                        awVarA = this.o.a(efVarA);
                        jB = ep.b();
                        dmVar.d("SUCCESS");
                    } else {
                        try {
                            awVarA = this.o.a(efVarA, 2);
                            jB = ep.b();
                            dmVar.b(A);
                            dmVar.c("SUCCESS");
                            c = 4;
                        } catch (Throwable unused2) {
                            c = 4;
                            a(dmVar, efVarA, strC, strD, A, ec.b);
                            awVarA = l.b() ? this.o.a(efVarA, 4) : this.o.a(efVarA, 1);
                            jB = ep.b();
                            dmVar.d("SUCCESS");
                            ec.a(this.f3735a).a(ec.b);
                        }
                    }
                    ecVarA = ec.a(this.f3735a);
                    i = ec.b;
                    A = c;
                } else {
                    A = 4;
                    A = 4;
                    A = 4;
                    dmVar.a("v4");
                    String strA = ec.a(this.f3735a).a(efVarA, ec.f3771a);
                    if (TextUtils.isEmpty(strA)) {
                        awVarA = this.o.a(efVarA, 1);
                        jB = ep.b();
                        dmVar.d("SUCCESS");
                    } else {
                        try {
                            awVarA = this.o.a(efVarA, 1);
                            jB = ep.b();
                            dmVar.b(strA);
                            dmVar.c("SUCCESS");
                            ec.a(this.f3735a).a(true, ec.f3771a);
                        } catch (Throwable unused3) {
                            a(dmVar, efVarA, strC, strD, strA, ec.f3771a);
                            awVarA = this.o.a(efVarA, 1);
                            jB = ep.b();
                            dmVar.d("SUCCESS");
                            ec.a(this.f3735a).a(ec.f3771a);
                        }
                    }
                    ecVarA = ec.a(this.f3735a);
                    i = ec.f3771a;
                }
                ecVarA.a(true, i);
                if (this.Q != null) {
                    this.Q.d();
                }
                dmVar.b(jB);
                if (awVarA != null) {
                    if (!TextUtils.isEmpty(awVarA.c)) {
                        this.p.append("#csid:" + awVarA.c);
                    }
                    str2 = awVarA.d;
                    dsVar.h(this.x.toString());
                } else {
                    str2 = "";
                }
                ds dsVarA = a(dsVar, awVarA, dmVar);
                if (dsVarA != null) {
                    return dsVarA;
                }
                byte[] bArrB = dy.b(awVarA.f3662a);
                if (bArrB == null) {
                    dsVar.setErrorCode(5);
                    dmVar.f("#0503");
                    this.p.append("解密数据失败#0503");
                    dsVar.setLocationDetail(this.p.toString());
                    en.a(str2, 2053);
                    return dsVar;
                }
                ds dsVarA2 = this.g.a(dsVar, bArrB, dmVar);
                if (!ep.a(dsVarA2)) {
                    String strB = dsVarA2.b();
                    this.J = strB;
                    en.a(str2, !TextUtils.isEmpty(strB) ? 2062 : 2061);
                    dsVarA2.setErrorCode(6);
                    dmVar.f("#0601");
                    StringBuilder sb4 = this.p;
                    StringBuilder sb5 = new StringBuilder("location faile retype:");
                    sb5.append(dsVarA2.d());
                    sb5.append(" rdesc:");
                    sb5.append(TextUtils.isEmpty(this.J) ? "" : this.J);
                    sb5.append("#0601");
                    sb4.append(sb5.toString());
                    dsVarA2.h(this.x.toString());
                    dsVarA2.setLocationDetail(this.p.toString());
                    return dsVarA2;
                }
                if (dsVarA2.getErrorCode() == 0 && dsVarA2.getLocationType() == 0) {
                    if ("-5".equals(dsVarA2.d()) || "1".equals(dsVarA2.d()) || "2".equals(dsVarA2.d()) || Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(dsVarA2.d()) || Constants.VIA_REPORT_TYPE_CHAT_AIO.equals(dsVarA2.d()) || "-1".equals(dsVarA2.d())) {
                        dsVarA2.setLocationType(5);
                    } else {
                        dsVarA2.setLocationType(6);
                    }
                }
                dsVarA2.setOffset(this.r);
                dsVarA2.a(this.q);
                dsVarA2.f(String.valueOf(this.s));
                dsVarA2.e("new");
                dsVarA2.setLocationDetail(this.p.toString());
                this.F = dsVarA2.a();
                return dsVarA2;
            } catch (Throwable th2) {
                th = th2;
                ep.b();
                dmVar.d("FAIL");
                ec.a(this.f3735a).a(false, ec.f3771a);
                ej.a(th, "Aps", "getApsLoc req");
                en.a("/mobile/binary", th);
                if (ep.d(this.f3735a)) {
                    if (th instanceof j) {
                        j jVar = th;
                        if (jVar.a().contains("网络异常状态码")) {
                            dmVar.f("#0404");
                            StringBuilder sb6 = this.p;
                            sb6.append("网络异常，状态码错误#0404");
                            sb6.append(jVar.f());
                            ds dsVarA3 = a((int) A, this.p.toString());
                            dsVarA3.h(this.x.toString());
                            return dsVarA3;
                        }
                        if (jVar.f() == 23 || Math.abs((ep.b() - this.l) - this.j.getHttpTimeOut()) < 500) {
                            dmVar.f("#0402");
                            sb2 = this.p;
                            str = "网络异常，连接超时#0402";
                        } else {
                            sb = new StringBuilder("#0403,");
                        }
                    } else {
                        sb = new StringBuilder("#0403,");
                    }
                    sb.append(th.getMessage());
                    dmVar.f(sb.toString());
                    this.p.append("网络异常,请求异常#0403");
                    ds dsVarA32 = a((int) A, this.p.toString());
                    dsVarA32.h(this.x.toString());
                    return dsVarA32;
                }
                dmVar.f("#0401");
                sb2 = this.p;
                str = "网络异常，未连接到网络，请连接网络#0401";
                sb2.append(str);
                ds dsVarA322 = a((int) A, this.p.toString());
                dsVarA322.h(this.x.toString());
                return dsVarA322;
            }
        } catch (Throwable th3) {
            dmVar.f("#0301");
            this.p.append("get parames error:" + th3.getMessage() + "#0301");
            en.a((String) null, 2031);
            ds dsVarA4 = a(3, this.p.toString());
            dsVarA4.h(this.x.toString());
            return dsVarA4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x0249 A[PHI: r15
  0x0249: PHI (r15v14 java.lang.StringBuilder) = 
  (r15v13 java.lang.StringBuilder)
  (r15v13 java.lang.StringBuilder)
  (r15v16 java.lang.StringBuilder)
  (r15v16 java.lang.StringBuilder)
 binds: [B:84:0x0243, B:86:0x0247, B:78:0x0209, B:80:0x020d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String c(com.loc.dm r15) {
        /*
            Method dump skipped, instruction units count: 827
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dn.c(com.loc.dm):java.lang.String");
    }

    private void c(ds dsVar) {
        if (dsVar != null) {
            this.k = dsVar;
        }
    }

    private void i() {
        int i;
        if (this.o != null) {
            try {
                if (this.j == null) {
                    this.j = new AMapLocationClientOption();
                }
                int i2 = 2;
                boolean z = true;
                if (this.j.getGeoLanguage() == null || (i = AnonymousClass1.f3737a[this.j.getGeoLanguage().ordinal()]) == 1) {
                    i2 = 0;
                } else if (i == 2) {
                    i2 = 1;
                } else if (i != 3) {
                    i2 = 0;
                }
                ee eeVar = this.o;
                long httpTimeOut = this.j.getHttpTimeOut();
                if (!this.j.getLocationProtocol().equals(AMapLocationClientOption.AMapLocationProtocol.HTTPS)) {
                    z = false;
                }
                eeVar.a(httpTimeOut, z, i2);
            } catch (Throwable unused) {
            }
        }
    }

    private void j() {
        try {
            if (this.i == null) {
                this.i = new a();
            }
            if (this.G == null) {
                IntentFilter intentFilter = new IntentFilter();
                this.G = intentFilter;
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                this.G.addAction("android.net.wifi.SCAN_RESULTS");
            }
            this.f3735a.registerReceiver(this.i, this.G);
        } catch (Throwable th) {
            ej.a(th, "Aps", "initBroadcastListener");
        }
    }

    private boolean k() {
        ArrayList<ScanResult> arrayListE = this.c.e();
        this.h = arrayListE;
        return arrayListE == null || arrayListE.size() <= 0;
    }

    public final ds a(double d, double d2) {
        try {
            String strA = this.o.a(this.f3735a, d, d2);
            if (!strA.contains("\"status\":\"1\"")) {
                return null;
            }
            ds dsVarA = this.g.a(strA);
            dsVarA.setLatitude(d);
            dsVarA.setLongitude(d2);
            return dsVarA;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:64|(1:70)(1:66)|71|(1:73)(2:75|(1:77)(9:78|(1:80)|107|81|(2:84|(1:86)(2:87|(1:89)(2:90|(1:92)(1:93))))|96|(3:98|(1:103)(1:102)|104)|105|106))|74|107|81|(2:84|(0)(0))|96|(0)|105|106) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:6|(1:10)|11|(1:13)(4:15|(0)(4:17|(1:19)(1:20)|21|(1:23))|24|(10:35|(2:37|(1:39)(1:40))|110|41|(1:47)(1:46)|48|108|52|56|(2:58|59)(2:60|(2:62|63)(12:64|(1:70)(1:66)|71|(1:73)(2:75|(1:77)(9:78|(1:80)|107|81|(2:84|(1:86)(2:87|(1:89)(2:90|(1:92)(1:93))))|96|(3:98|(1:103)(1:102)|104)|105|106))|74|107|81|(2:84|(0)(0))|96|(0)|105|106)))(4:28|(1:32)|33|34))|14|24|(1:26)|35|(0)|110|41|(4:43|45|47|48)(0)|108|52|56|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c2, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c3, code lost:
    
        com.loc.ej.a(r0, "Aps", "getLocation getScanResultsParam");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d2, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d3, code lost:
    
        com.loc.ej.a(r0, "Aps", "getLocation getCgiListParam");
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b3 A[Catch: all -> 0x01d0, TryCatch #0 {all -> 0x01d0, blocks: (B:81:0x01a3, B:84:0x01a9, B:86:0x01b3, B:89:0x01bd, B:92:0x01c7, B:93:0x01cc), top: B:107:0x01a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.dm r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dn.a(com.loc.dm):com.loc.ds");
    }

    public final ds a(ds dsVar) {
        this.E.a(this.t);
        return this.E.a(dsVar);
    }

    public final ds a(boolean z, dm dmVar) {
        dmVar.e(z ? "statics" : "first");
        if (this.f3735a == null) {
            dmVar.f("#0101");
            this.p.append("context is null#0101");
            en.a((String) null, 2011);
            return a(1, this.p.toString());
        }
        if (this.c.l()) {
            dmVar.f("#1502");
            return a(15, "networkLocation has been mocked!#1502");
        }
        a();
        if (TextUtils.isEmpty(this.L)) {
            return a(this.A, this.p.toString());
        }
        ds dsVarB = b(z, dmVar);
        if (ep.a(dsVarB) && !P) {
            this.f3736e.a(this.x.toString());
            this.f3736e.a(this.d.d());
            c(dsVarB);
        }
        P = true;
        return dsVarB;
    }

    public final void a() {
        this.o = ee.a(this.f3735a);
        i();
        if (this.b == null) {
            this.b = (ConnectivityManager) ep.a(this.f3735a, "connectivity");
        }
        if (this.m == null) {
            this.m = new eh();
        }
    }

    public final void a(Context context) {
        try {
            if (this.f3735a != null) {
                return;
            }
            this.E = new dr();
            Context applicationContext = context.getApplicationContext();
            this.f3735a = applicationContext;
            ep.b(applicationContext);
            if (this.c == null) {
                this.c = new dx(this.f3735a, (WifiManager) ep.a(this.f3735a, "wifi"));
            }
            if (this.d == null) {
                this.d = new dw(this.f3735a);
            }
            if (this.f3736e == null) {
                this.f3736e = new dz();
            }
            if (this.g == null) {
                this.g = new eg();
            }
        } catch (Throwable th) {
            ej.a(th, "Aps", "initBase");
        }
    }

    public final void a(Handler handler) {
        this.N = handler;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        boolean zIsNeedAddress;
        boolean z;
        boolean zIsOffset;
        boolean zIsLocationCacheEnable;
        this.j = aMapLocationClientOption;
        if (aMapLocationClientOption == null) {
            this.j = new AMapLocationClientOption();
        }
        dx dxVar = this.c;
        if (dxVar != null) {
            this.j.isWifiActiveScan();
            dxVar.a(this.j.isWifiScan(), this.j.isMockEnable(), AMapLocationClientOption.isOpenAlwaysScanWifi(), aMapLocationClientOption.getScanWifiInterval());
        }
        i();
        dz dzVar = this.f3736e;
        if (dzVar != null) {
            dzVar.a(this.j);
        }
        eg egVar = this.g;
        if (egVar != null) {
            egVar.a(this.j);
        }
        AMapLocationClientOption.GeoLanguage geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT;
        boolean z2 = true;
        try {
            geoLanguage = this.j.getGeoLanguage();
            zIsNeedAddress = this.j.isNeedAddress();
            try {
                zIsOffset = this.j.isOffset();
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            zIsNeedAddress = true;
        }
        try {
            zIsLocationCacheEnable = this.j.isLocationCacheEnable();
            try {
                this.u = this.j.isOnceLocationLatest();
                this.C = this.j.isSensorEnable();
                if (zIsOffset != this.r || zIsNeedAddress != this.q || zIsLocationCacheEnable != this.t || geoLanguage != this.s) {
                    try {
                        if (this.f3736e != null) {
                            this.f3736e.a();
                        }
                        c((ds) null);
                        this.M = false;
                        if (this.E != null) {
                            this.E.a();
                        }
                    } catch (Throwable th) {
                        ej.a(th, "Aps", "cleanCache");
                    }
                }
            } catch (Throwable unused3) {
                z = zIsLocationCacheEnable;
                z2 = zIsOffset;
                boolean z3 = z;
                zIsOffset = z2;
                zIsLocationCacheEnable = z3;
            }
        } catch (Throwable unused4) {
            z2 = zIsOffset;
            z = true;
            boolean z32 = z;
            zIsOffset = z2;
            zIsLocationCacheEnable = z32;
            this.r = zIsOffset;
            this.q = zIsNeedAddress;
            this.t = zIsLocationCacheEnable;
            this.s = geoLanguage;
        }
        this.r = zIsOffset;
        this.q = zIsNeedAddress;
        this.t = zIsLocationCacheEnable;
        this.s = geoLanguage;
    }

    public final void b() {
        if (this.B == null) {
            this.B = new dt(this.f3735a);
        }
        j();
        this.c.b(false);
        this.h = this.c.e();
        this.d.a(false, k());
        this.f3736e.a(this.f3735a);
        try {
            if (this.f3735a.checkCallingOrSelfPermission(u.c("EYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFQ1VSRV9TRVRUSU5HUw==")) == 0) {
                this.n = true;
            }
        } catch (Throwable unused) {
        }
        this.z = true;
    }

    public final void b(dm dmVar) {
        try {
            if (this.y) {
                return;
            }
            if (this.L != null) {
                this.L = null;
            }
            if (this.x != null) {
                this.x.delete(0, this.x.length());
            }
            if (this.u) {
                j();
            }
            this.c.b(this.u);
            this.h = this.c.e();
            this.d.a(true, k());
            String strC = c(dmVar);
            this.L = strC;
            if (!TextUtils.isEmpty(strC)) {
                this.x = a(this.x);
            }
        } catch (Throwable th) {
            ej.a(th, "Aps", "initFirstLocateParam");
        }
        this.y = true;
    }

    public final void b(ds dsVar) {
        if (ep.a(dsVar)) {
            this.f3736e.a(this.L, this.x, dsVar, this.f3735a, true);
        }
    }

    public final void c() {
        if (this.p.length() > 0) {
            StringBuilder sb = this.p;
            sb.delete(0, sb.length());
        }
    }

    @SuppressLint({"NewApi"})
    public final void d() {
        this.F = null;
        this.y = false;
        this.z = false;
        dz dzVar = this.f3736e;
        if (dzVar != null) {
            dzVar.b(this.f3735a);
        }
        dr drVar = this.E;
        if (drVar != null) {
            drVar.a();
        }
        if (this.g != null) {
            this.g = null;
        }
        try {
            if (this.f3735a != null && this.i != null) {
                this.f3735a.unregisterReceiver(this.i);
            }
        } finally {
            try {
            } finally {
            }
        }
        dw dwVar = this.d;
        if (dwVar != null) {
            dwVar.h();
        }
        dx dxVar = this.c;
        if (dxVar != null) {
            dxVar.n();
        }
        ArrayList<ScanResult> arrayList = this.h;
        if (arrayList != null) {
            arrayList.clear();
        }
        dt dtVar = this.B;
        if (dtVar != null) {
            dtVar.e();
        }
        this.k = null;
        this.f3735a = null;
        this.x = null;
        this.H = null;
    }

    public final void e() {
        Cdo cdo = this.Q;
        if (cdo != null) {
            cdo.d();
        }
    }

    public final ds f() {
        int i;
        String string;
        if (this.c.l()) {
            i = 15;
            string = "networkLocation has been mocked!#1502";
        } else {
            if (!TextUtils.isEmpty(this.L)) {
                ds dsVarA = this.f3736e.a(this.f3735a, this.L, this.x, true);
                if (ep.a(dsVarA)) {
                    c(dsVarA);
                }
                return dsVarA;
            }
            i = this.A;
            string = this.p.toString();
        }
        return a(i, string);
    }

    public final void g() {
        try {
            if (ei.d && this.f3735a != null) {
                if (this.Q == null) {
                    this.Q = new Cdo(this.f3735a);
                }
                this.Q.a(this.d, this.c, this.N);
            }
        } catch (Throwable th) {
            ab.b(th, "as", "stc");
        }
    }

    public final void h() {
        Cdo cdo = this.Q;
        if (cdo != null) {
            cdo.a();
        }
    }
}
