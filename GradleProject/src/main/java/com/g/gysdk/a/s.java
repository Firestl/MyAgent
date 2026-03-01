package com.g.gysdk.a;

import android.text.TextUtils;
import android.util.Pair;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyConfig;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.al;
import com.g.gysdk.a.an;
import com.g.gysdk.a.d;
import com.g.gysdk.a.n;
import com.g.gysdk.a.y;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2026a = false;
    public static boolean b = false;
    public final n c;
    public final n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f2027e;
    public volatile n.e f;
    public volatile n.e g;
    public volatile n.e h;
    public volatile n.e i;
    public volatile n.e j;
    public volatile n.e k;

    /* JADX INFO: renamed from: com.g.gysdk.a.s$7, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass7 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2041a;

        static {
            int[] iArr = new int[as.values().length];
            f2041a = iArr;
            try {
                iArr[as.CT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2041a[as.CM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2041a[as.CU.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final s f2042a = new s();
    }

    public s() {
        this.c = new p();
        this.f2027e = new o();
        this.d = new q();
    }

    private n a(boolean z, int i) throws RuntimeException {
        as asVarA = ar.a(z, i);
        int i2 = AnonymousClass7.f2041a[asVarA.ordinal()];
        n nVar = i2 != 1 ? i2 != 2 ? i2 != 3 ? null : this.d : this.f2027e : this.c;
        if (nVar != null) {
            return nVar;
        }
        throw new IllegalStateException("unSupport operator:" + asVarA.f1981e);
    }

    public static s a() {
        return a.f2042a;
    }

    public static com.g.gysdk.b a(Throwable th, GyCode gyCode, String str) {
        GyErrorCode gyErrorCode = GyErrorCode.UNSUPPORT_OPERATOR;
        if (!ar.c(d.b)) {
            gyErrorCode = GyErrorCode.NO_SIM_CARD;
        }
        com.g.gysdk.b bVar = new com.g.gysdk.b(gyCode, gyErrorCode, am.a(th));
        if (!TextUtils.isEmpty(str)) {
            bVar.a(str, "");
        }
        return bVar;
    }

    public static void a(final n nVar, final n.e eVar, final boolean z) {
        if (nVar == null) {
            return;
        }
        String str = nVar.f2015a.f1981e;
        if (eVar == null) {
            ak.c("ELogin_" + str + " init with key=null,builtin=" + z);
            return;
        }
        if (nVar.c() == 0) {
            al.a(al.b.Work, new Runnable() { // from class: com.g.gysdk.a.s.6
                @Override // java.lang.Runnable
                public void run() {
                    n nVar2 = nVar;
                    n.e eVar2 = eVar;
                    nVar2.a((String) ((Pair) eVar2).first, (String) ((Pair) eVar2).second, z);
                }
            }, true);
            return;
        }
        ak.a("ELogin_" + str + " inited before, will use new key in next start up");
    }

    public static long b(long j, long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (j > jCurrentTimeMillis) {
            j = jCurrentTimeMillis;
        }
        long j3 = j2 - (jCurrentTimeMillis - j);
        if (j3 < 0) {
            return 5000L;
        }
        return j3;
    }

    public void a(long j, final int i, final r rVar) {
        try {
            final n nVarA = a(true, -1);
            an.a("operator_preLogin", b(j, i)).a(nVarA.d()).a(new an.b() { // from class: com.g.gysdk.a.s.1
                @Override // com.g.gysdk.a.an.b
                public void a(final an anVar) {
                    v vVarE;
                    if (nVarA.c() != 2) {
                        anVar.a(new com.g.gysdk.b(GyCode.PRELOGIN_ERROR, GyErrorCode.UNKNOWN_ERROR, "运营商SDK初始化异常"));
                        return;
                    }
                    if (s.b && (vVarE = nVarA.e()) != null && vVarE.k()) {
                        aj.a("preLogin return cache, fromDisk=" + vVarE.j());
                        com.g.gysdk.b bVarL = vVarE.l();
                        bVarL.a("isFrom", Integer.valueOf(vVarE.j() ? 2 : 1));
                        anVar.a(bVarL);
                        return;
                    }
                    int iF = nVarA.f2015a != as.CM ? j.f() : 1;
                    final AtomicInteger atomicInteger = new AtomicInteger();
                    final AtomicBoolean atomicBoolean = new AtomicBoolean();
                    for (int i2 = 0; i2 < iF; i2++) {
                        final int i3 = iF;
                        nVarA.a(i, new n.b() { // from class: com.g.gysdk.a.s.1.1
                            @Override // com.g.gysdk.a.n.b
                            public void a(v vVar) {
                                if ((atomicInteger.incrementAndGet() >= i3 || vVar.b()) && atomicBoolean.compareAndSet(false, true)) {
                                    com.g.gysdk.b bVarL2 = vVar.l();
                                    if (vVar.b()) {
                                        bVarL2.a("isFrom", (Serializable) 0);
                                        nVarA.a(vVar);
                                    }
                                    b.a("operator_preLogin", bVarL2.g(), bVarL2.e(), bVarL2.h());
                                    anVar.a(bVarL2);
                                }
                            }
                        });
                    }
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    com.g.gysdk.b bVarA = com.g.gysdk.b.a(obj);
                    if (bVarA == null) {
                        bVarA = com.g.gysdk.b.a(GyCode.PRELOGIN_ERROR, th);
                    }
                    rVar.a(bVarA);
                }
            });
        } catch (Throwable th) {
            rVar.a(a(th, GyCode.PRELOGIN_ERROR, "preLogin"));
        }
    }

    public void a(GyConfig gyConfig) {
        this.f = n.e.a(gyConfig.telecomAppId(), gyConfig.telecomAppKey());
        this.g = n.e.a(gyConfig.unicomAppId(), gyConfig.unicomAppKey());
        this.h = n.e.a(gyConfig.mobileAppId(), gyConfig.mobileAppKey());
        b = gyConfig.preLoginUseCache();
        a(gyConfig.eLoginDebug());
    }

    public void a(n.e eVar, n.e eVar2, n.e eVar3) {
        if (n.e.a(eVar, this.i) && n.e.a(eVar2, this.k) && n.e.a(eVar3, this.j)) {
            ak.a("ELogin saveKey ignored, because no changed");
            return;
        }
        this.i = eVar;
        this.k = eVar2;
        this.j = eVar3;
        a(this.f2027e, this.k, false);
        a(this.c, this.i, false);
        a(this.d, this.j, false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0", d.f2000e);
            if (eVar != null) {
                jSONObject.put("a", ((Pair) eVar).first);
                jSONObject.put("b", ((Pair) eVar).second);
            }
            if (eVar2 != null) {
                jSONObject.put("c", ((Pair) eVar2).first);
                jSONObject.put(cn.com.chinatelecom.account.api.a.d.f1473a, ((Pair) eVar2).second);
            }
            if (eVar3 != null) {
                jSONObject.put("e", ((Pair) eVar3).first);
                jSONObject.put(cn.com.chinatelecom.account.api.e.f.f1517a, ((Pair) eVar3).second);
            }
            ak.a("ELogin saveKey, ctKey:" + eVar + " cuKey:" + eVar3 + " cmKey:" + eVar2);
        } catch (Throwable th) {
            ak.e("ELogin saveKey new json error", th);
        }
        g.a().a(2, jSONObject.toString());
    }

    public void a(final String str, long j, final int i, final r rVar) {
        try {
            final n nVarA = a(true, -1);
            an.a("operator_preVerify", b(j, i)).a(nVarA.d()).a(new an.b() { // from class: com.g.gysdk.a.s.3
                @Override // com.g.gysdk.a.an.b
                public void a(an anVar) {
                    if (nVarA.c() == 2) {
                        nVarA.a(str, i, new n.c() { // from class: com.g.gysdk.a.s.3.1
                            @Override // com.g.gysdk.a.n.c
                            public void a(w wVar) {
                                rVar.a(wVar.i());
                            }
                        });
                    } else {
                        anVar.a(new com.g.gysdk.b(GyCode.PREVERIFY_ERROR, GyErrorCode.UNKNOWN_ERROR, "运营商SDK初始化异常"));
                    }
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    com.g.gysdk.b bVarA = com.g.gysdk.b.a(obj);
                    if (bVarA == null) {
                        bVarA = com.g.gysdk.b.a(GyCode.PREVERIFY_ERROR, th);
                    }
                    rVar.a(bVarA);
                }
            });
        } catch (Throwable th) {
            rVar.a(a(th, GyCode.PREVERIFY_ERROR, "getAccessCode"));
        }
    }

    public void a(final String str, final String str2, final String str3, int i, long j, final int i2, final r rVar) {
        try {
            final n nVarA = a(false, i);
            an.a("verity_wait_register", b(j, i2)).a(MiPushClient.COMMAND_REGISTER).a(new an.c() { // from class: com.g.gysdk.a.s.4
                @Override // com.g.gysdk.a.an.c, com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    if (e.b() == 2) {
                        nVarA.a(str, str2, str3, i2, new n.d() { // from class: com.g.gysdk.a.s.4.1
                            @Override // com.g.gysdk.a.n.d
                            public void a(x xVar) {
                                rVar.a(xVar.i());
                            }
                        });
                    } else {
                        rVar.a(new com.g.gysdk.b(GyCode.VERIFY_ERROR, GyErrorCode.UNKNOWN_ERROR, "注册失败或超时"));
                    }
                }
            });
        } catch (Throwable th) {
            rVar.a(a(th, GyCode.VERIFY_ERROR, (String) null));
        }
    }

    public void a(boolean z) {
        f2026a = z;
        n nVar = this.c;
        if (nVar != null) {
            nVar.a(z);
        }
        n nVar2 = this.f2027e;
        if (nVar2 != null) {
            nVar2.a(z);
        }
        n nVar3 = this.d;
        if (nVar3 != null) {
            nVar3.a(z);
        }
    }

    public String b() {
        try {
            return a(false, -1).a();
        } catch (Throwable unused) {
            return null;
        }
    }

    public void b(final long j, final int i, final r rVar) {
        if (!e()) {
            rVar.a(new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.INVALID_PRELOGIN, "预登录无效").a("eLogin", ""));
            return;
        }
        try {
            a(false, -1).a(i, new n.a() { // from class: com.g.gysdk.a.s.2
                @Override // com.g.gysdk.a.n.a
                public void a(final u uVar) {
                    final com.g.gysdk.b bVarJ = uVar.j();
                    if (uVar.b()) {
                        an.a("login_wait_register", s.b(j, i)).a(MiPushClient.COMMAND_REGISTER).a(new an.c() { // from class: com.g.gysdk.a.s.2.1
                            @Override // com.g.gysdk.a.an.c, com.g.gysdk.a.an.b
                            public void a(Object obj, Throwable th) {
                                if (e.b() != 2) {
                                    com.g.gysdk.b bVarA = e.a();
                                    if (bVarA == null) {
                                        bVarA = new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.UNKNOWN_ERROR, "注册失败或超时");
                                    }
                                    bVarA.a("eLogin", s.a().f());
                                    rVar.a(bVarA);
                                    return;
                                }
                                rVar.a(bVarJ);
                                if (System.currentTimeMillis() - j < i - 100) {
                                    aa.a(uVar.f(), uVar.c(), uVar.d(), uVar.e().g, uVar.i());
                                }
                            }
                        });
                    } else {
                        rVar.a(bVarJ);
                    }
                }
            });
        } catch (Throwable th) {
            rVar.a(a(th, GyCode.LOGIN_ERROR, "eLogin"));
        }
    }

    public boolean b(boolean z) {
        try {
            return a(z, -1).f();
        } catch (Throwable th) {
            ak.a(th);
            return false;
        }
    }

    public void c() {
        al.a(al.b.Queue, new Runnable() { // from class: com.g.gysdk.a.s.5
            @Override // java.lang.Runnable
            public void run() {
                y.b bVarA = y.a();
                if (bVarA != null) {
                    s.this.a(n.e.a(bVarA.a(), bVarA.b()), n.e.a(bVarA.e(), bVarA.f()), n.e.a(bVarA.c(), bVarA.d()));
                }
            }
        });
    }

    public void d() {
        boolean z;
        n nVar;
        n.e eVar;
        try {
            String strA = g.a().a(2);
            if (TextUtils.isEmpty(strA)) {
                z = true;
                a(this.f2027e, this.h, true);
                a(this.c, this.f, true);
                nVar = this.d;
                eVar = this.g;
            } else {
                JSONObject jSONObject = new JSONObject(strA);
                if (!TextUtils.equals(jSONObject.getString("0"), d.f2000e)) {
                    throw new IllegalStateException("not to init eLogin, gy appId changed");
                }
                String strOptString = jSONObject.optString("a");
                String strOptString2 = jSONObject.optString("b");
                String strOptString3 = jSONObject.optString("c");
                String strOptString4 = jSONObject.optString(cn.com.chinatelecom.account.api.a.d.f1473a);
                String strOptString5 = jSONObject.optString("e");
                String strOptString6 = jSONObject.optString(cn.com.chinatelecom.account.api.e.f.f1517a);
                this.i = n.e.a(strOptString, strOptString2);
                this.k = n.e.a(strOptString3, strOptString4);
                this.j = n.e.a(strOptString5, strOptString6);
                z = false;
                a(this.f2027e, this.k, false);
                a(this.c, this.i, false);
                nVar = this.d;
                eVar = this.j;
            }
            a(nVar, eVar, z);
        } catch (Throwable th) {
            ak.e("ELogin init error", th);
        }
    }

    public boolean e() {
        return b(false);
    }

    public String f() {
        if (d.a(d.a.DEBUG_UI)) {
            return "proc***id";
        }
        try {
            return a(false, -1).e().f();
        } catch (Throwable th) {
            ak.a(th);
            return "";
        }
    }

    public String g() {
        if (d.a(d.a.DEBUG_UI)) {
            return "888***999";
        }
        try {
            return a(false, -1).e().i();
        } catch (Throwable th) {
            ak.a(th);
            return "";
        }
    }

    public String h() {
        if (d.a(d.a.DEBUG_UI)) {
            return "DEBUG_UI服务";
        }
        int i = AnonymousClass7.f2041a[ar.a(false, -1).ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? "Unknown服务" : "认证服务由联通统一认证提供" : "中国移动提供认证服务" : "天翼账号提供认证服务";
    }

    public String[] i() {
        String str;
        String str2;
        if (d.a(d.a.DEBUG_UI)) {
            str = "DEBUG_UI条款";
            str2 = "https://www.getui.com/verification";
        } else {
            int i = AnonymousClass7.f2041a[ar.a(false, -1).ordinal()];
            if (i == 1) {
                str = "天翼账号服务与隐私协议";
                str2 = "https://e.189.cn/sdk/agreement/detail.do?hidetop=true";
            } else if (i == 2) {
                str = "中国移动认证服务条款";
                str2 = "https://wap.cmpassport.com/resources/html/contract.html";
            } else if (i != 3) {
                str = "Unknown条款";
                str2 = "https://unknown";
            } else {
                str = "联通统一认证服务条款";
                str2 = "https://opencloud.wostore.cn/authz/resource/html/disclaimer.html?fromsdk=true";
            }
        }
        return new String[]{str, str2};
    }
}
