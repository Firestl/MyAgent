package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SharedPreferencesUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class en1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SharedPreferences f7514a;

    public static String a(Context context, String str, String str2) {
        if (f7514a == null) {
            f7514a = context.getSharedPreferences(com.igexin.push.core.b.Y, 0);
        }
        return f7514a.getString(str, str2);
    }

    public static void b(Context context, String str, String str2) {
        if (f7514a == null) {
            f7514a = context.getSharedPreferences(com.igexin.push.core.b.Y, 0);
        }
        f7514a.edit().putString(str, str2).apply();
    }

    public static int a(Context context, String str, int i) {
        if (f7514a == null) {
            f7514a = context.getSharedPreferences(com.igexin.push.core.b.Y, 0);
        }
        return f7514a.getInt(str, i);
    }

    public static void b(Context context, String str, int i) {
        if (f7514a == null) {
            f7514a = context.getSharedPreferences(com.igexin.push.core.b.Y, 0);
        }
        f7514a.edit().putInt(str, i).apply();
    }

    public static void a(Context context, String str) {
        if (f7514a == null) {
            f7514a = context.getSharedPreferences(com.igexin.push.core.b.Y, 0);
        }
        f7514a.edit().remove(str).apply();
    }
}
