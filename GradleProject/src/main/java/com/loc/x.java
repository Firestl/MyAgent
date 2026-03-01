package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.java.com.sangfor.sdk.utils.SPUtil;
import android.os.Build;
import android.text.TextUtils;
import java.util.Arrays;

/* JADX INFO: compiled from: SPUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static byte[] f3841a;
    public static byte[] b;
    public String c;

    public x(String str) {
        this.c = r.a(TextUtils.isDigitsOnly(str) ? SPUtil.TAG : str);
    }

    public static int a(Context context, String str, String str2, int i) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            ab.b(th, "csp", "giv");
            return i;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
            editorEdit.putString(str2, u.g(a(context, u.a(str3))));
            a(editorEdit);
        } catch (Throwable unused) {
        }
    }

    public static void a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        try {
            editor.apply();
        } catch (Throwable th) {
            y.a(th, "sp", "cm");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str) {
        if (editor != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                editor.remove(str);
            } catch (Throwable th) {
                y.a(th, "sp", "rk");
            }
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, int i) {
        try {
            editor.putInt(str, i);
        } catch (Throwable th) {
            ab.b(th, "csp", "putPrefsInt");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, long j) {
        if (editor == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            editor.putLong(str, j);
        } catch (Throwable th) {
            ab.b(th, "csp", "plv");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, String str2) {
        if (editor != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    editor.putString(str, str2);
                }
            } catch (Throwable th) {
                y.a(th, "sp", "ps");
            }
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, boolean z) {
        try {
            editor.putBoolean(str, z);
        } catch (Throwable th) {
            ab.b(th, "csp", "setPrefsStr");
        }
    }

    public static boolean a(Context context, String str, String str2, boolean z) {
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            ab.b(th, "csp", "gbv");
            return z;
        }
    }

    public static byte[] a(Context context) {
        if (context == null) {
            return new byte[0];
        }
        byte[] bArr = f3841a;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        byte[] bytes = k.f(context).getBytes();
        f3841a = bytes;
        return bytes;
    }

    public static byte[] a(Context context, byte[] bArr) {
        try {
            return o.b(a(context), bArr, b(context));
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    public static SharedPreferences.Editor b(Context context, String str) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    return context.getSharedPreferences(str, 0).edit();
                }
            } catch (Throwable th) {
                y.a(th, "sp", "ge");
            }
        }
        return null;
    }

    public static String b(Context context, String str, String str2) {
        if (context == null) {
            return "";
        }
        try {
            return u.a(b(context, u.d(context.getSharedPreferences(str, 0).getString(str2, ""))));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b(Context context, String str, String str2, String str3) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            ab.b(th, "csp", "gsv");
            return str3;
        }
    }

    public static byte[] b(Context context) {
        byte[] bArr = b;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        int i = 0;
        if (Build.VERSION.SDK_INT < 9) {
            b = new byte[a(context).length / 2];
            while (true) {
                byte[] bArr2 = b;
                if (i >= bArr2.length) {
                    break;
                }
                bArr2[i] = a(context)[i];
                i++;
            }
        } else {
            b = Arrays.copyOfRange(a(context), 0, a(context).length / 2);
        }
        return b;
    }

    public static byte[] b(Context context, byte[] bArr) {
        try {
            return o.a(a(context), bArr, b(context));
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    public static long c(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getLong(str2, 0L);
        } catch (Throwable th) {
            ab.b(th, "csp", "glv");
            return 0L;
        }
    }

    public final String a(Context context, String str) {
        return context == null ? "" : b(context, this.c, str);
    }

    public final void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        a(context, this.c, str, str2);
    }
}
