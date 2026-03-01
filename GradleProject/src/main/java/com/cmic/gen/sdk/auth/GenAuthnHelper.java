package com.cmic.gen.sdk.auth;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.cmic.gen.sdk.auth.c;
import com.cmic.gen.sdk.e.e;
import com.cmic.gen.sdk.e.h;
import com.cmic.gen.sdk.e.n;
import com.cmic.gen.sdk.view.GenAuthThemeConfig;
import com.cmic.gen.sdk.view.GenLoginPageInListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GenAuthnHelper extends c {
    public static GenAuthnHelper f;
    public GenAuthThemeConfig g;
    public GenLoginPageInListener h;

    public GenAuthnHelper(Context context) {
        super(context);
        this.h = null;
    }

    public GenAuthnHelper(Context context, String str) {
        super(context);
        this.h = null;
        this.f1677e = str;
    }

    public static void b(Context context, com.cmic.gen.sdk.a aVar) {
        String strB = aVar.b("traceId");
        Intent intent = new Intent();
        intent.putExtra("traceId", strB);
        e.a(aVar.b("traceId"), aVar);
        intent.setClassName(context, "com.cmic.gen.sdk.view.GenLoginAuthActivity");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static GenAuthnHelper getInstance(Context context) {
        if (f == null) {
            synchronized (GenAuthnHelper.class) {
                if (f == null) {
                    f = new GenAuthnHelper(context);
                }
            }
        }
        return f;
    }

    public static GenAuthnHelper getInstance(Context context, String str) {
        if (f == null) {
            synchronized (GenAuthnHelper.class) {
                if (f == null) {
                    f = new GenAuthnHelper(context, str);
                }
            }
        }
        return f;
    }

    @Override // com.cmic.gen.sdk.auth.c
    public void a(com.cmic.gen.sdk.a aVar) {
        final c.a aVar2 = new c.a(aVar);
        this.d.postDelayed(aVar2, this.c);
        this.f1676a.a(aVar, new b() { // from class: com.cmic.gen.sdk.auth.GenAuthnHelper.4
            @Override // com.cmic.gen.sdk.auth.b
            public void a(String str, String str2, com.cmic.gen.sdk.a aVar3, JSONObject jSONObject) {
                com.cmic.gen.sdk.e.c.b("onBusinessComplete", "onBusinessComplete");
                GenAuthnHelper.this.d.removeCallbacks(aVar2);
                if (!"103000".equals(str) || e.a(aVar3.b("traceId"))) {
                    GenAuthnHelper.this.callBackResult(str, str2, aVar3, jSONObject);
                } else {
                    GenAuthnHelper.b(GenAuthnHelper.this.b, aVar3);
                }
            }
        });
    }

    public GenAuthThemeConfig getAuthThemeConfig() {
        if (this.g == null) {
            this.g = new GenAuthThemeConfig.Builder().build();
        }
        return this.g;
    }

    public long getOverTime() {
        return this.c;
    }

    @Override // com.cmic.gen.sdk.auth.c
    public void getPhoneInfo(String str, String str2, GenTokenListener genTokenListener) {
        getPhoneInfo(str, str2, genTokenListener, -1);
    }

    public void getPhoneInfo(final String str, final String str2, final GenTokenListener genTokenListener, int i) {
        final com.cmic.gen.sdk.a aVarA = a(genTokenListener);
        aVarA.a("SDKRequestCode", i);
        n.a(new n.a(this.b, aVarA) { // from class: com.cmic.gen.sdk.auth.GenAuthnHelper.1
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if (GenAuthnHelper.this.a(aVarA, str, str2, "preGetMobile", 3, genTokenListener)) {
                    GenAuthnHelper.super.a(aVarA);
                }
            }
        });
    }

    @Override // com.cmic.gen.sdk.auth.c
    public void loginAuth(String str, String str2, GenTokenListener genTokenListener) {
        loginAuth(str, str2, genTokenListener, -1);
    }

    public void loginAuth(final String str, final String str2, final GenTokenListener genTokenListener, int i) {
        final com.cmic.gen.sdk.a aVarA = a(genTokenListener);
        aVarA.a("SDKRequestCode", i);
        n.a(new n.a(this.b, aVarA) { // from class: com.cmic.gen.sdk.auth.GenAuthnHelper.2
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if (GenAuthnHelper.this.a(aVarA, str, str2, "loginAuth", 3, genTokenListener)) {
                    String strA = h.a(GenAuthnHelper.this.b);
                    if (!TextUtils.isEmpty(strA)) {
                        aVarA.a("phonescrip", strA);
                    }
                    GenAuthnHelper.this.a(aVarA);
                }
            }
        });
    }

    public void loginPageInCallBack(String str, JSONObject jSONObject) {
        GenLoginPageInListener genLoginPageInListener = this.h;
        if (genLoginPageInListener != null) {
            genLoginPageInListener.onLoginPageInComplete(str, jSONObject);
        }
    }

    @Override // com.cmic.gen.sdk.auth.c
    public void mobileAuth(String str, String str2, GenTokenListener genTokenListener) {
        mobileAuth(str, str2, genTokenListener, -1);
    }

    public void mobileAuth(final String str, final String str2, final GenTokenListener genTokenListener, int i) {
        final com.cmic.gen.sdk.a aVarA = a(genTokenListener);
        aVarA.a("SDKRequestCode", i);
        n.a(new n.a(this.b, aVarA) { // from class: com.cmic.gen.sdk.auth.GenAuthnHelper.3
            @Override // com.cmic.gen.sdk.e.n.a
            public void a() {
                if (GenAuthnHelper.this.a(aVarA, str, str2, "mobileAuth", 0, genTokenListener)) {
                    GenAuthnHelper.super.a(aVarA);
                }
            }
        });
    }

    public void quitAuthActivity() {
        try {
            if (com.cmic.gen.sdk.view.b.a().b() != null) {
                com.cmic.gen.sdk.view.b.a().b().a();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            com.cmic.gen.sdk.e.c.a("AuthnHelper", "关闭授权页失败");
        }
    }

    public void setAuthThemeConfig(GenAuthThemeConfig genAuthThemeConfig) {
        this.g = genAuthThemeConfig;
    }

    public void setPageInListener(GenLoginPageInListener genLoginPageInListener) {
        this.h = genLoginPageInListener;
    }
}
