package com.cmic.gen.sdk.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.cmic.gen.sdk.e.e;
import com.cmic.gen.sdk.e.g;
import com.cmic.gen.sdk.e.h;
import com.cmic.gen.sdk.e.j;
import com.cmic.gen.sdk.e.k;
import com.cmic.gen.sdk.e.m;
import com.cmic.gen.sdk.e.n;
import com.cmic.gen.sdk.e.o;
import com.cmic.gen.sdk.e.q;
import com.cmic.gen.sdk.e.r;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static final String SDK_VERSION = "quick_login_android_5.9.3";

    @SuppressLint({"StaticFieldLeak"})
    public static c f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.cmic.gen.sdk.auth.a f1676a;
    public final Context b;
    public long c;
    public final Handler d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1677e;
    public final Object g;

    public class a implements Runnable {
        public final com.cmic.gen.sdk.a b;

        public a(com.cmic.gen.sdk.a aVar) {
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObjectA = (r.a(c.this.b).a() || !this.b.b("doNetworkSwitch", false)) ? d.a("200023", "登录超时") : d.a("102508", "数据网络切换失败");
            c.this.callBackResult(jSONObjectA.optString("resultCode", "200023"), jSONObjectA.optString(SocialConstants.PARAM_APP_DESC, "登录超时"), this.b, jSONObjectA);
        }
    }

    public c(Context context) {
        this.c = 8000L;
        this.g = new Object();
        this.b = context.getApplicationContext();
        this.d = new Handler(this.b.getMainLooper());
        this.f1676a = com.cmic.gen.sdk.auth.a.a(this.b);
        r.a(this.b);
        k.a(this.b);
        j.a(this.b);
        n.a(new n.a() { // from class: com.cmic.gen.sdk.auth.c.1
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                String strB = k.b("AID", "");
                com.cmic.gen.sdk.e.c.b("AuthnHelperCore", "aid = " + strB);
                if (TextUtils.isEmpty(strB)) {
                    c.this.a();
                }
                com.cmic.gen.sdk.e.c.b("AuthnHelperCore", com.cmic.gen.sdk.e.b.a(c.this.b, true) ? "生成androidkeystore成功" : "生成androidkeystore失败");
            }
        });
    }

    public c(Context context, String str) {
        this(context);
        this.f1677e = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        String str = "%" + q.b();
        com.cmic.gen.sdk.e.c.b("AuthnHelperCore", "generate aid = " + str);
        k.a("AID", str);
    }

    private void a(final Context context, final String str, final com.cmic.gen.sdk.a aVar) {
        n.a(new n.a() { // from class: com.cmic.gen.sdk.auth.c.7
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if ("200023".equals(str)) {
                    SystemClock.sleep(8000L);
                }
                new com.cmic.gen.sdk.d.d().a(context, str, aVar);
            }
        });
    }

    public static c getInstance(Context context) {
        if (f == null) {
            synchronized (c.class) {
                if (f == null) {
                    f = new c(context);
                }
            }
        }
        return f;
    }

    public static c getInstance(Context context, String str) {
        if (f == null) {
            synchronized (c.class) {
                if (f == null) {
                    f = new c(context, str);
                }
            }
        }
        return f;
    }

    public static void setDebugMode(boolean z) {
        com.cmic.gen.sdk.e.c.a(z);
    }

    public com.cmic.gen.sdk.a a(GenTokenListener genTokenListener) {
        com.cmic.gen.sdk.a aVar = new com.cmic.gen.sdk.a(64);
        String strC = q.c();
        aVar.a(new com.cmic.gen.sdk.d.b());
        aVar.a("traceId", strC);
        com.cmic.gen.sdk.e.c.a("traceId", strC);
        if (genTokenListener != null) {
            e.a(strC, genTokenListener);
        }
        return aVar;
    }

    public void a(com.cmic.gen.sdk.a aVar) {
        final a aVar2 = new a(aVar);
        this.d.postDelayed(aVar2, this.c);
        this.f1676a.a(aVar, new b() { // from class: com.cmic.gen.sdk.auth.c.5
            @Override // com.cmic.gen.sdk.auth.b
            public void a(String str, String str2, com.cmic.gen.sdk.a aVar3, JSONObject jSONObject) {
                c.this.d.removeCallbacks(aVar2);
                c.this.callBackResult(str, str2, aVar3, jSONObject);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(com.cmic.gen.sdk.a r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11, com.cmic.gen.sdk.auth.GenTokenListener r12) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.gen.sdk.auth.c.a(com.cmic.gen.sdk.a, java.lang.String, java.lang.String, java.lang.String, int, com.cmic.gen.sdk.auth.GenTokenListener):boolean");
    }

    public void callBackResult(String str, String str2, com.cmic.gen.sdk.a aVar, JSONObject jSONObject) {
        try {
            String strB = aVar.b("traceId");
            final int iB = aVar.b("SDKRequestCode", -1);
            if (e.a(strB)) {
                return;
            }
            synchronized (this) {
                final GenTokenListener genTokenListenerC = e.c(strB);
                if (jSONObject == null || !jSONObject.optBoolean("keepListener", false)) {
                    e.b(strB);
                }
                if (genTokenListenerC == null) {
                    return;
                }
                aVar.a("systemEndTime", SystemClock.elapsedRealtime());
                aVar.a("endtime", o.a());
                int iC = aVar.c("logintype");
                if (jSONObject == null) {
                    jSONObject = d.a(str, str2);
                }
                final JSONObject jSONObjectA = iC == 3 ? d.a(str, aVar, jSONObject) : d.a(str, str2, aVar, jSONObject);
                jSONObjectA.put("scripExpiresIn", String.valueOf(h.a()));
                this.d.post(new Runnable() { // from class: com.cmic.gen.sdk.auth.c.6
                    @Override // java.lang.Runnable
                    public void run() {
                        genTokenListenerC.onGetTokenComplete(iB, jSONObjectA);
                    }
                });
                com.cmic.gen.sdk.a.c.a(this.b).a(aVar);
                if (!aVar.b().j() && !q.a(aVar.b())) {
                    a(this.b, str, aVar);
                }
                if (e.a()) {
                    r.a(this.b).b();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void delScrip() {
        try {
            h.a(true, true);
            com.cmic.gen.sdk.e.c.b("AuthnHelperCore", "删除scrip");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject getNetworkType(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                boolean zA = m.a(this.b);
                com.cmic.gen.sdk.b.a.a().a(context, g.a(context, "android.permission.READ_PHONE_STATE"), zA);
                String strA = j.a().a((String) null);
                int iA = m.a(context, zA, new com.cmic.gen.sdk.a(1));
                jSONObject.put("operatortype", strA);
                jSONObject.put("networktype", iA + "");
                com.cmic.gen.sdk.e.c.b("AuthnHelperCore", "网络类型: " + iA);
                com.cmic.gen.sdk.e.c.b("AuthnHelperCore", "运营商类型: " + strA);
                return jSONObject;
            } catch (Exception unused) {
                jSONObject.put("errorDes", "发生未知错误");
                return jSONObject;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    public void getPhoneInfo(final String str, final String str2, final GenTokenListener genTokenListener) {
        final com.cmic.gen.sdk.a aVarA = a(genTokenListener);
        n.a(new n.a(this.b, aVarA) { // from class: com.cmic.gen.sdk.auth.c.4
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if (c.this.a(aVarA, str, str2, "preGetMobile", 3, genTokenListener)) {
                    c.this.a(aVarA);
                }
            }
        });
    }

    public void loginAuth(final String str, final String str2, final GenTokenListener genTokenListener) {
        final com.cmic.gen.sdk.a aVarA = a(genTokenListener);
        n.a(new n.a(this.b, aVarA) { // from class: com.cmic.gen.sdk.auth.c.2
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if (c.this.a(aVarA, str, str2, "loginAuth", 1, genTokenListener)) {
                    c.this.a(aVarA);
                }
            }
        });
    }

    public void mobileAuth(final String str, final String str2, final GenTokenListener genTokenListener) {
        final com.cmic.gen.sdk.a aVarA = a(genTokenListener);
        n.a(new n.a(this.b, aVarA) { // from class: com.cmic.gen.sdk.auth.c.3
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if (c.this.a(aVarA, str, str2, "mobileAuth", 0, genTokenListener)) {
                    c.this.a(aVarA);
                }
            }
        });
    }

    public void setOverTime(long j) {
        this.c = j;
    }
}
