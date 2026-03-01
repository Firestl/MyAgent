package com.igexin.push.g;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.igexin.push.core.ServiceManager;
import com.igexin.sdk.PushService;

/* JADX INFO: loaded from: classes2.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3602a = "us";
    public static final String b = "ups";
    public static final String c = "uis";
    public static final String d = "ua";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3603e = "sc";
    public static final String f = "it";
    public static final String g = "logkey2";
    public static final String h = "hwBadgeNum";
    public static final String i = "vivoBadgeNum";
    public static final String j = "oppoBadgeNum";
    public static String k = "idvph";
    public static final String l = "lkm";
    public static final String m = "emgph";
    public static final String n = "secnph";
    public static final String o = "SpUtils";
    public static final String p = "getui_sp";

    public static void a(Context context, Intent intent) {
        String str;
        Throwable th;
        try {
            String str2 = (String) b(context, f3602a, "");
            if (!TextUtils.isEmpty(str2)) {
                try {
                    if (PushService.class.isAssignableFrom(Class.forName(str2))) {
                        try {
                            a(context, f3602a, "");
                            str2 = "";
                        } catch (Throwable th2) {
                            th = th2;
                            str = "";
                            com.igexin.c.a.c.a.a(o, th.toString());
                            com.igexin.c.a.c.a.a("SpUtils|" + th.toString(), new Object[0]);
                            str2 = str;
                        }
                    }
                } catch (Throwable th3) {
                    str = str2;
                    th = th3;
                }
            }
            if (intent.hasExtra(f3602a)) {
                String strB = com.igexin.c.b.a.b(intent.getStringExtra(f3602a), "");
                if (!str2.equals(strB)) {
                    if (!com.igexin.push.core.b.ap.equals(strB)) {
                        a(context, f3602a, strB);
                    } else if (!TextUtils.isEmpty(str2)) {
                        a(context, f3602a, "");
                    }
                }
            }
            if (intent.hasExtra(b)) {
                String strB2 = com.igexin.c.b.a.b(intent.getStringExtra(b), "");
                String str3 = (String) b(context, b, "");
                if (!str3.equals(strB2)) {
                    if (!com.igexin.push.core.b.ap.equals(strB2)) {
                        a(context, b, strB2);
                    } else if (!TextUtils.isEmpty(str3)) {
                        a(context, b, "");
                    }
                }
            }
            if (intent.hasExtra(c)) {
                String str4 = (String) b(context, c, "");
                String strB3 = com.igexin.c.b.a.b(intent.getStringExtra(c), "");
                if (!str4.equals(strB3)) {
                    a(context, c, strB3);
                }
            }
            if (intent.hasExtra("ua")) {
                ServiceManager.getInstance();
                String strD = ServiceManager.d(context);
                String strB4 = com.igexin.c.b.a.b(intent.getStringExtra("ua"), "");
                if (strD.equals(strB4)) {
                    return;
                }
                a(context, "ua", strB4);
            }
        } catch (Throwable th4) {
            com.igexin.c.a.c.a.a(th4);
        }
    }

    public static void a(Context context, String str, Object obj) {
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(p, 0).edit();
        if (obj instanceof String) {
            editorEdit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        }
        editorEdit.apply();
    }

    public static boolean a(Context context) {
        try {
            String str = (String) b(context, f3602a, "");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Class.forName(str);
            return true;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(o, e2.toString());
            com.igexin.c.a.c.a.a("SpUtils|" + e2.toString(), new Object[0]);
            return false;
        }
    }

    public static Object b(Context context, String str, Object obj) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(p, 0);
        return obj instanceof String ? sharedPreferences.getString(str, (String) obj) : obj instanceof Integer ? Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue())) : obj instanceof Boolean ? Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue())) : obj instanceof Float ? Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue())) : obj instanceof Long ? Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue())) : obj;
    }
}
