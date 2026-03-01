package com.g.gysdk.a;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ap {
    public static void a(Object obj) {
        if (obj == null) {
            obj = "";
        }
        if (obj instanceof Throwable) {
            ak.a((Throwable) obj);
            return;
        }
        ak.a(obj + "");
    }

    public static void a(String str) {
        ak.e(str);
    }

    public static void a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        ak.a(str + str2);
    }

    public static void a(Throwable th) {
        ak.e(th);
    }

    public static void b(String str) {
        aj.a(str);
    }
}
