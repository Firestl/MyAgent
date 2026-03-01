package com.zx.a.I8b7;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.zx.module.annotation.Java2C;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class h1 {
    @Java2C.Method2C
    public static native String a(String str) throws NoSuchAlgorithmException, InvalidKeyException;

    public static JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        try {
            Bundle bundleB = b(t2.f6286a);
            if (bundleB == null) {
                return jSONArray;
            }
            for (String str : bundleB.keySet()) {
                if (str.startsWith("ZX_APPID_")) {
                    String string = bundleB.getString(str);
                    if (!TextUtils.isEmpty(string)) {
                        jSONArray.put(string);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            y1.a(e2);
        }
        return jSONArray;
    }

    public static Bundle b(Context context) throws PackageManager.NameNotFoundException {
        if (t2.I == null) {
            PackageManager packageManagerC = d3.c(context.getApplicationContext());
            if (TextUtils.isEmpty(t2.g)) {
                t2.g = context.getPackageName();
            }
            t2.I = packageManagerC.getApplicationInfo(t2.g, 128).metaData;
        }
        return t2.I;
    }

    @Java2C.Method2C
    public static native String b();

    public static void c(Context context) {
        try {
            t2.f6287e = b(context).getString("ZX_CHANNEL_ID");
            y1.a("initChannelId: , channelId = '" + t2.f6287e + "'");
        } catch (Exception e2) {
            y1.a(e2);
        }
    }

    public static String a(Context context) throws Exception {
        String strA;
        if (!TextUtils.isEmpty(t2.f)) {
            return t2.f.trim();
        }
        if (context != null) {
            try {
                strA = b(context).getString("ZX_APPID");
            } catch (Exception e2) {
                y1.a(e2);
                strA = null;
            }
            if (TextUtils.isEmpty(strA)) {
                if (t2.f6287e == null) {
                    c(context);
                }
                if (!TextUtils.isEmpty(t2.f6287e)) {
                    if (TextUtils.isEmpty(t2.g)) {
                        t2.g = context.getPackageName();
                    }
                    strA = a(t2.g);
                } else {
                    throw new IllegalStateException("ZX_APPID not found");
                }
            }
            return strA.trim();
        }
        throw new RuntimeException("context not provided, cannot be null");
    }
}
