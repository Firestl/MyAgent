package com.zx.sdk.api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.zx.a.I8b7.h1;
import com.zx.a.I8b7.l;
import com.zx.a.I8b7.m2;
import com.zx.a.I8b7.n2;
import com.zx.a.I8b7.o1;

/* JADX INFO: loaded from: classes2.dex */
public class ZXManager {
    public static final String TAG = "ZXManager";
    public static ZXApi api;
    public static Context ctx;

    static {
        try {
            System.loadLibrary("zxprotect");
        } catch (Throwable th) {
            StringBuilder sbA = m2.a("ZXLoadLibraryError:");
            sbA.append(th.getMessage());
            Log.e(TAG, sbA.toString());
        }
    }

    public static void addZXIDChangedListener(ZXIDChangedListener zXIDChangedListener) {
        try {
            if (checkAPI()) {
                api.addZXIDChangedListener(zXIDChangedListener);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.registerListener(listener) failed: "));
        }
    }

    public static void allowPermissionDialog(boolean z) {
        try {
            if (checkAPI()) {
                api.allowPermissionDialog(z);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.allowPermissionDialog failed: "));
        }
    }

    public static boolean checkAPI() {
        if (api != null) {
            return true;
        }
        l.b("ZXManager not init, should init firstly");
        return false;
    }

    public static void checkPermission(Activity activity, PermissionCallback permissionCallback) {
        if (permissionCallback == null) {
            return;
        }
        try {
            if (checkAPI()) {
                api.checkPermission(activity, permissionCallback);
            }
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }

    public static void getAuthToken(Callback callback) {
        try {
            if (checkAPI() && callback != null) {
                api.getAuthToken(callback);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager getAuthToken onFailed:"));
        }
    }

    public static void getOpenID(Callback callback) {
        try {
            if (checkAPI() && callback != null) {
                Context context = ctx;
                if (context != null) {
                    api.getOpenID(callback, context);
                } else {
                    l.b("Context is empty！Please should init firstly");
                    callback.onFailed(10009, "Context is empty！Please should init firstly");
                }
            }
        } catch (Throwable th) {
            if (callback != null) {
                callback.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getOpenID(Callback) failed: "));
        }
    }

    public static void getSAID(String str, String str2, String str3, String str4, String str5, SAIDCallback sAIDCallback) {
        try {
            if (checkAPI() && sAIDCallback != null) {
                api.getSAID(str, str2, str3, str4, str5, sAIDCallback);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager getSAID onFailed:"));
        }
    }

    public static void getTag(Callback callback) {
        try {
            if (checkAPI() && callback != null) {
                api.getTag(callback);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager getTag onFailed:"));
        }
    }

    public static String getVersion() {
        try {
            if (!checkAPI()) {
                return "3.3.2.25477";
            }
            api.getVersion();
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.getVersion failed: "));
        }
        return "3.3.2.25477";
    }

    public static void getZXID(ZXIDListener zXIDListener) {
        try {
            if (checkAPI() && zXIDListener != null) {
                api.getZXID(zXIDListener);
            }
        } catch (Throwable th) {
            if (zXIDListener != null) {
                zXIDListener.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getZXID(zxidListener) failed: "));
        }
    }

    public static void init(Context context) {
        try {
            if (api == null) {
                api = new o1(h1.a(context));
            }
            api.init(context);
            if (context != null) {
                ctx = context.getApplicationContext();
            }
        } catch (Throwable th) {
            Log.e(TAG, "ZXManager.init failed: " + th);
        }
    }

    public static String invoke(String str, String str2) {
        try {
            return !checkAPI() ? "ZXManager is not init" : api.invoke(str, str2);
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.invoke failed: "));
            return null;
        }
    }

    public static boolean isAllowPermissionDialog() {
        try {
            if (checkAPI()) {
                return api.isAllowPermissionDialog();
            }
            return false;
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.isAllowPermissionDialog failed: "));
            return false;
        }
    }

    public static boolean isEnable() {
        try {
            if (checkAPI()) {
                return api.isEnable();
            }
            return false;
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.isEnable failed: "));
            return false;
        }
    }

    public static ZXApi newSDK(String str) {
        try {
            return new o1(str);
        } catch (Throwable th) {
            l.b("ZXManager.newProxy failed:" + th);
            return null;
        }
    }

    public static void setDebug(boolean z) {
        try {
            l.f6238a = z;
            ZXApi zXApi = api;
            if (zXApi != null) {
                zXApi.setDebug(z);
            }
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }

    public static void setEnable(boolean z) {
        try {
            if (checkAPI()) {
                api.setEnable(z);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.setEnable failed: "));
        }
    }
}
