package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import supwisdom.kq;
import supwisdom.lq;
import supwisdom.vq;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static synchronized String a(Context context, String str) {
        String strA = vq.a(context, "openapi_file_pri", "openApi" + str, "");
        if (lq.a(strA)) {
            return "";
        }
        String strB = kq.b(kq.a(), strA);
        return lq.a(strB) ? "" : strB;
    }

    public static synchronized void a() {
    }

    public static synchronized void a(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("openapi_file_pri", 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
            editorEdit.commit();
        }
    }

    public static synchronized void a(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (editorEdit != null) {
                editorEdit.putString("openApi" + str, kq.a(kq.a(), str2));
                editorEdit.commit();
            }
        } catch (Throwable unused) {
        }
    }
}
