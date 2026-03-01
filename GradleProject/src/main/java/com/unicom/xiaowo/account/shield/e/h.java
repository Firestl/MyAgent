package com.unicom.xiaowo.account.shield.e;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5552a = File.separator + new String(l.b("LnVuaWFjY291bnQ=")) + File.separator;
    public static final String b = new String(l.b("dW5pYWNjb3VudC5qYXI="));
    public static final String c = new String(l.b("dW5pY29tX3VwZGF0ZQ==")) + File.separator + new String(l.b("dW5pYWNjb3VudF9jb3JlLmRhdA=="));
    public static final String d = new String(l.b("dW5pYWNjb3VudF9jb3JlLmRhdA=="));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static DexClassLoader f5553e = null;

    public static DexClassLoader a() {
        return f5553e;
    }

    public static DexClassLoader a(Context context, String str) {
        try {
            String str2 = e(context) + "optdex";
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            DexClassLoader dexClassLoader = new DexClassLoader(str, str2, null, context.getClassLoader());
            f5553e = dexClassLoader;
            return dexClassLoader;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(Context context) {
        return e(context) + b;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            byte[] bArr2 = new byte[15];
            for (int i = 0; i < 15; i++) {
                bArr2[i] = bArr[i];
            }
            return new String(bArr2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean a(Context context, byte[] bArr) throws Throwable {
        try {
            int length = bArr.length - 16;
            int i = (length >> 2) << 2;
            byte[] bArr2 = new byte[i];
            byte[] bArr3 = new byte[length];
            for (int i2 = 0; i2 < i; i2++) {
                bArr2[i2] = bArr[i2 + 16];
            }
            for (int i3 = 0; i3 < length; i3++) {
                bArr3[i3] = bArr[i3 + 16];
            }
            byte[] bArrA = l.a(bArr2);
            for (int i4 = 0; i4 < i; i4++) {
                bArr3[i4] = bArrA[i4];
            }
            File file = new File(e(context));
            if (!file.exists()) {
                file.mkdirs();
            }
            String strA = a(context);
            File file2 = new File(strA);
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            b.a(bArr3, strA);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static byte[] a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            return b.a(inputStream);
        } catch (Exception unused) {
            return null;
        }
    }

    public static InputStream b(Context context) {
        try {
            String str = e(context) + c;
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            f.a("find:" + str);
            return new FileInputStream(file);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void b(Context context, byte[] bArr) throws Throwable {
        try {
            File file = new File(e(context));
            if (!file.exists()) {
                file.mkdirs();
            }
            String strA = a(context);
            File file2 = new File(strA);
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            b.a(bArr, strA);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c(Context context) {
        try {
            b.a(new File(a(context)));
        } catch (Exception unused) {
        }
    }

    public static void d(Context context) {
        try {
            String str = e(context) + c;
            File file = new File(str);
            if (file.exists()) {
                f.a("delete " + str + " result:" + file.delete());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String e(Context context) {
        return context.getFilesDir().getParent() + f5552a;
    }
}
