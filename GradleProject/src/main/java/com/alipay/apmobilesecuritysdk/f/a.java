package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
import supwisdom.kq;
import supwisdom.lq;
import supwisdom.sq;
import supwisdom.tq;
import supwisdom.vq;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        if (context == null || lq.a(str) || lq.a(str2)) {
            return null;
        }
        try {
            String strA = vq.a(context, str, str2, "");
            if (lq.a(strA)) {
                return null;
            }
            return kq.b(kq.a(), strA);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(String str, String str2) {
        synchronized (a.class) {
            if (lq.a(str) || lq.a(str2)) {
                return null;
            }
            try {
                String strA = sq.a(str);
                if (lq.a(strA)) {
                    return null;
                }
                String string = new JSONObject(strA).getString(str2);
                if (lq.a(string)) {
                    return null;
                }
                return kq.b(kq.a(), string);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (lq.a(str) || lq.a(str2) || context == null) {
            return;
        }
        try {
            String strA = kq.a(kq.a(), str3);
            HashMap map = new HashMap();
            map.put(str2, strA);
            vq.a(context, str, map);
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, String str2, String str3) {
        synchronized (a.class) {
            if (lq.a(str) || lq.a(str2)) {
                return;
            }
            try {
                String strA = sq.a(str);
                JSONObject jSONObject = new JSONObject();
                if (lq.b(strA)) {
                    try {
                        jSONObject = new JSONObject(strA);
                    } catch (Exception unused) {
                        jSONObject = new JSONObject();
                    }
                }
                jSONObject.put(str2, kq.a(kq.a(), str3));
                jSONObject.toString();
                try {
                    System.clearProperty(str);
                } catch (Throwable unused2) {
                }
                if (tq.a()) {
                    String str4 = ".SystemConfig" + File.separator + str;
                    if (tq.a()) {
                        File file = new File(Environment.getExternalStorageDirectory(), str4);
                        if (file.exists() && file.isFile()) {
                            file.delete();
                        }
                    }
                }
            } catch (Throwable unused3) {
            }
        }
    }
}
