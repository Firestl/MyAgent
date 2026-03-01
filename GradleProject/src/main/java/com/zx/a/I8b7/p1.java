package com.zx.a.I8b7;

import android.text.TextUtils;
import android.util.Log;
import com.zx.a.I8b7.u2;
import com.zx.module.base.Callback;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class p1 {
    public static final Set<String> b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v2 f6263a;

    static {
        HashSet hashSet = new HashSet();
        b = hashSet;
        t2.c = "core-n";
        t2.d = "3.3.2.25477";
        hashSet.add("fd39c63f1732f201");
        hashSet.add("182215c3273d3c96");
        hashSet.add("30c3b906fa3a6c10");
        hashSet.add("83e1f70a049353e0");
        hashSet.add("a14a9b473d09b4a4");
        hashSet.add("c5d0f5289411bfb1");
        hashSet.add("888db8aca12678cf");
        hashSet.add("4d34408b292920ff");
        Set<String> set = t2.G;
        set.add("fd39c63f1732f201");
        set.add("182215c3273d3c96");
        set.add("30c3b906fa3a6c10");
        set.add("83e1f70a049353e0");
        set.add("888db8aca12678cf");
    }

    public final String a(String str, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", str);
        jSONObject.put("code", i);
        return jSONObject.toString();
    }

    public String f182215c3273d3c96(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", false);
        jSONObject.put("code", 0);
        return jSONObject.toString();
    }

    public String f30c3b906fa3a6c10(String str) throws JSONException {
        try {
            boolean z = new JSONObject(str).getBoolean("isDebug");
            Log.d("setDebug", "isDebug: " + z);
            l.f6238a = z;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", 0);
            return jSONObject.toString();
        } catch (Throwable th) {
            Log.e("ZXCoreModule setDebug", th.getMessage());
            return a(th.getMessage(), 1);
        }
    }

    public void f4d34408b292920ff(String str, Callback callback) {
        y1.a("getAuthToken:" + str + "with cb");
        try {
            u2.b.f6294a.b(new JSONObject(str), callback, 1);
        } catch (Throwable th) {
            y1.b("getAuthToken error:" + th);
            if (callback != null) {
                try {
                    callback.callback(a(th.getMessage(), 1));
                } catch (Throwable th2) {
                    y1.a(th2);
                }
            }
        }
    }

    public String f83e1f70a049353e0(String str) throws JSONException {
        t2.b = new JSONObject(str).getString("version");
        return a("", 0);
    }

    public String f888db8aca12678cf(String str) throws JSONException {
        return a("lib not work", 1);
    }

    public void fa14a9b473d09b4a4(String str, Callback callback) {
        y1.a("getUAID:" + str + "with cb");
        try {
            u2.b.f6294a.b(new JSONObject(str), callback, 0);
        } catch (Throwable th) {
            y1.b("getUAID error:" + th);
            if (callback != null) {
                try {
                    callback.callback(a(th.getMessage(), 1));
                } catch (Throwable th2) {
                    y1.a(th2);
                }
            }
        }
    }

    public void fc5d0f5289411bfb1(String str, Callback callback) {
        y1.a("getTag:" + str + "with cb");
        try {
            callback.callback(a(j1.c(), 0));
        } catch (Throwable th) {
            y1.b("getTag error:" + th);
            if (callback != null) {
                try {
                    callback.callback(a(th.getMessage(), 1));
                } catch (Throwable th2) {
                    y1.a(th2);
                }
            }
        }
    }

    public String ffd39c63f1732f201(String str) throws JSONException {
        String strA;
        boolean z = new JSONObject(str).getBoolean("allowExpired");
        ((a3) this.f6263a).getClass();
        if (z) {
            strA = t2.a();
        } else {
            String str2 = t2.i;
            boolean z2 = true;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    if (System.currentTimeMillis() < Long.parseLong(str2.split("-")[1]) * 1000) {
                        z2 = false;
                    }
                } catch (Exception e2) {
                    y1.b("zid判断过期异常:" + str2 + ", err :" + e2.getMessage());
                }
            }
            strA = !z2 ? t2.a() : null;
        }
        return a(strA, 0);
    }
}
