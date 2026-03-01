package com.zx.a.I8b7;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.zx.a.I8b7.c3;
import com.zx.a.I8b7.s;
import com.zx.sdk.api.Callback;
import com.zx.sdk.api.PermissionCallback;
import com.zx.sdk.api.SAIDCallback;
import com.zx.sdk.api.ZXApi;
import com.zx.sdk.api.ZXIDChangedListener;
import com.zx.sdk.api.ZXIDListener;

/* JADX INFO: loaded from: classes2.dex */
public class o1 implements ZXApi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6245a;

    public o1(String str) throws IllegalStateException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalStateException("ZX_APPID not found");
        }
        this.f6245a = str;
    }

    @Override // com.zx.sdk.api.ZXApi
    public void addZXIDChangedListener(ZXIDChangedListener zXIDChangedListener) {
        try {
            s.b.f6278a.a(this.f6245a, "addZXIDChangedListener", "");
            e2 e2VarB = e2.b();
            String str = this.f6245a;
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new l2(e2VarB, str, zXIDChangedListener));
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.registerListener(listener) failed: "));
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void allowPermissionDialog(boolean z) {
        try {
            s.b.f6278a.a(this.f6245a, "allowPermissionDialog", "enable=" + z);
            e2 e2VarB = e2.b();
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new j2(e2VarB, z));
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.allowPermissionDialog failed: "));
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void checkPermission(Activity activity, PermissionCallback permissionCallback) {
        try {
            s.b.f6278a.a(this.f6245a, "checkPermission", "");
            if (permissionCallback == null) {
                return;
            }
            e2 e2VarB = e2.b();
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new d2(e2VarB, permissionCallback, activity));
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void getAuthToken(Callback callback) {
        try {
            s.b.f6278a.a(this.f6245a, "getAuthToken", "");
            if (callback == null) {
                return;
            }
            e2 e2VarB = e2.b();
            String str = this.f6245a;
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new h2(e2VarB, str, callback));
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void getOpenID(Callback callback, Context context) {
        try {
            s.b.f6278a.a(this.f6245a, "getOpenID", "");
            if (callback != null) {
                e2 e2VarB = e2.b();
                e2VarB.getClass();
                c3.e.f6204a.c.execute(new a2(e2VarB, context, callback));
            }
        } catch (Throwable th) {
            if (callback != null) {
                callback.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getOpenID(cb) failed: "));
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void getSAID(String str, String str2, String str3, String str4, String str5, SAIDCallback sAIDCallback) {
        try {
            s.b.f6278a.a(this.f6245a, "getUAID", "");
            if (sAIDCallback != null) {
                e2 e2VarB = e2.b();
                String str6 = this.f6245a;
                e2VarB.getClass();
                c3.e.f6204a.f6203a.execute(new f2(e2VarB, str6, str, str2, str3, str4, str5, sAIDCallback));
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager getSAID onFailed:"));
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void getTag(Callback callback) {
        try {
            s.b.f6278a.a(this.f6245a, "getTag", "");
            if (callback == null) {
                return;
            }
            e2 e2VarB = e2.b();
            String str = this.f6245a;
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new g2(e2VarB, str, callback));
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public String getVersion() {
        s.b.f6278a.a(this.f6245a, "getVersion", "");
        return "3.3.2.25477";
    }

    @Override // com.zx.sdk.api.ZXApi
    public void getZXID(ZXIDListener zXIDListener) {
        try {
            s.b.f6278a.a(this.f6245a, "getZXID", "");
            if (zXIDListener != null) {
                e2 e2VarB = e2.b();
                String str = this.f6245a;
                e2VarB.getClass();
                c3.e.f6204a.f6203a.execute(new z1(e2VarB, str, zXIDListener));
            }
        } catch (Throwable th) {
            if (zXIDListener != null) {
                zXIDListener.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getZXID(zxidListener) failed: "));
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void init(Context context) {
        try {
            s.b.f6278a.a(this.f6245a, "init", "");
            e2.a(context);
        } catch (Throwable th) {
            l.b("ZXManager.init failed:" + th);
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public String invoke(String str, String str2) {
        try {
            s.b.f6278a.a(this.f6245a, "invoke", "method=" + str + "&argument" + str2);
            return e2.b().a(str, str2);
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.invoke failed: "));
            return null;
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public boolean isAllowPermissionDialog() {
        try {
            s.b.f6278a.a(this.f6245a, "isAllowPermissionDialog", "");
            e2.b().getClass();
            return t2.r == 1;
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.isAllowPermissionDialog failed: "));
            return false;
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public boolean isEnable() {
        try {
            s.b.f6278a.a(this.f6245a, "isEnable", "");
            e2.b().getClass();
            return t2.q == 1;
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.isEnable failed: "));
            return false;
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void setDebug(boolean z) {
        try {
            s.b.f6278a.a(this.f6245a, "setDebug", "isDebug=" + z);
            e2 e2VarB = e2.b();
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new k2(e2VarB, z));
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }

    @Override // com.zx.sdk.api.ZXApi
    public void setEnable(boolean z) {
        try {
            s.b.f6278a.a(this.f6245a, "setEnable", "enable=" + z);
            e2 e2VarB = e2.b();
            e2VarB.getClass();
            c3.e.f6204a.f6203a.execute(new i2(e2VarB, z));
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.setEnable failed: "));
        }
    }
}
