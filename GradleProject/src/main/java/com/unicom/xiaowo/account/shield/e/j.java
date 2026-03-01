package com.unicom.xiaowo.account.shield.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class j {
    public static String a(Context context) {
        return c(context, "auth300");
    }

    public static void a(Context context, long j) {
        a(context, "auth301", j);
    }

    public static void a(Context context, String str) {
        a(context, "auth300", str);
    }

    public static void a(Context context, String str, long j) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("cu_auth", 0).edit();
            editorEdit.putLong(str, j);
            editorEdit.commit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("cu_auth", 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static long b(Context context) {
        return d(context, "auth301");
    }

    public static void b(Context context, String str) {
        a(context, "auth02", str);
    }

    public static String c(Context context) {
        String strC = c(context, "auth02");
        if (!TextUtils.isEmpty(strC)) {
            return strC;
        }
        String strA = i.a(UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis());
        b(context, strA);
        return strA;
    }

    public static String c(Context context, String str) {
        try {
            return context.getSharedPreferences("cu_auth", 0).getString(str, "");
        } catch (Exception unused) {
            a(context, str, "");
            return "";
        }
    }

    public static long d(Context context, String str) {
        try {
            return context.getSharedPreferences("cu_auth", 0).getLong(str, 0L);
        } catch (Exception unused) {
            return 0L;
        }
    }
}
