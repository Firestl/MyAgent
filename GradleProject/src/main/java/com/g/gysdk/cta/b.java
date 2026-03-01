package com.g.gysdk.cta;

import android.app.Activity;
import android.text.TextUtils;
import com.cmic.sso.sdk.AuthRegisterViewConfig;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.ap;
import com.g.gysdk.a.c;
import com.g.gysdk.a.s;
import com.g.gysdk.view.ELoginActivity;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b implements AuthPageListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ELoginThemeConfig f2053a;
    public com.g.gysdk.a b;
    public int c;
    public HashMap<String, AuthRegisterViewConfig> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f2054e;
    public AuthPageListener f;

    public interface a {
        void a();

        void b();

        boolean c();
    }

    /* JADX INFO: renamed from: com.g.gysdk.cta.b$b, reason: collision with other inner class name */
    public static class C0029b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2055a = new b();
    }

    public b() {
        this.d = new HashMap<>();
    }

    public static b a() {
        return C0029b.f2055a;
    }

    public void a(int i, ELoginThemeConfig eLoginThemeConfig, com.g.gysdk.a aVar) {
        boolean z = eLoginThemeConfig != null;
        if (!z) {
            c.a(z, null, i, aVar);
            return;
        }
        this.f2053a = eLoginThemeConfig;
        this.b = aVar;
        this.c = i;
        if (s.a().e()) {
            ELoginActivity.start();
        } else {
            com.g.gysdk.a.a(aVar, new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.INVALID_PRELOGIN, "预登录无效").a("eLogin", ""));
        }
    }

    public void a(AuthPageListener authPageListener) {
        this.f = authPageListener;
    }

    public void a(ELoginThemeConfig eLoginThemeConfig, com.g.gysdk.a aVar) {
        this.f2053a = eLoginThemeConfig;
        this.b = aVar;
        this.c = 5000;
        if (s.a().e()) {
            ELoginActivity.start();
        } else {
            com.g.gysdk.a.a(aVar, new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.INVALID_PRELOGIN, "预登录无效").a("eLogin", ""));
        }
    }

    public void a(a aVar) {
        this.f2054e = aVar;
    }

    public void a(String str, AuthRegisterViewConfig authRegisterViewConfig) {
        try {
            if (TextUtils.isEmpty(str) || authRegisterViewConfig == null) {
                ap.b("id或viewConfig为空");
            } else {
                this.d.put(str, authRegisterViewConfig);
            }
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    public void b() {
        try {
            if (this.f2054e != null) {
                this.f2054e.a();
            }
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    public void c() {
        b();
        try {
            this.f2053a = null;
            this.d.clear();
            this.f2054e = null;
            this.f = null;
            ap.a("GyELoginHelper", "cancelELogin");
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    public void d() {
        HashMap<String, AuthRegisterViewConfig> map = this.d;
        if (map != null) {
            map.clear();
        }
    }

    public HashMap<String, AuthRegisterViewConfig> e() {
        return this.d;
    }

    public void f() {
        this.f2054e = null;
    }

    public ELoginThemeConfig g() {
        return this.f2053a;
    }

    public com.g.gysdk.a h() {
        return this.b;
    }

    public int i() {
        return this.c;
    }

    public boolean j() {
        try {
            if (this.f2054e != null) {
                return this.f2054e.c();
            }
            return false;
        } catch (Throwable th) {
            ap.a(th);
            return false;
        }
    }

    public void k() {
        try {
            if (this.f2054e != null) {
                this.f2054e.b();
            }
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    @Override // com.g.gysdk.cta.AuthPageListener
    public void onAuthActivityCreate(Activity activity) {
        try {
            ap.a("GyELoginHelper", "onAuthActivityCreate");
            if (this.f != null) {
                this.f.onAuthActivityCreate(activity);
            }
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("onAuthActivityCreate:" + th));
        }
    }

    @Override // com.g.gysdk.cta.AuthPageListener
    public void onAuthWebActivityCreate(Activity activity) {
        try {
            ap.a("GyELoginHelper", "onAuthWebActivityCreate");
            if (this.f != null) {
                this.f.onAuthWebActivityCreate(activity);
            }
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("onAuthWebActivityCreate:" + th));
        }
    }

    @Override // com.g.gysdk.cta.AuthPageListener
    public void onLoginButtonClick() {
        try {
            ap.a("GyELoginHelper", "onLoginButtonClick");
            if (this.f != null) {
                this.f.onLoginButtonClick();
            }
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("onLoginButtonClick:" + th));
        }
    }

    @Override // com.g.gysdk.cta.AuthPageListener
    public void onPrivacyCheckBoxClick(boolean z) {
        try {
            ap.a("GyELoginHelper", "onPrivacyCheckBoxClick:" + z);
            if (this.f != null) {
                this.f.onPrivacyCheckBoxClick(z);
            }
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("onPrivacyCheckBoxClick:" + th));
        }
    }

    @Override // com.g.gysdk.cta.AuthPageListener
    public void onPrivacyClick(String str, String str2) {
        try {
            ap.a("GyELoginHelper", "onPrivacyClick:" + str + str2);
            if (this.f != null) {
                this.f.onPrivacyClick(str, str2);
            }
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("onPrivacyClick:" + th));
        }
    }
}
