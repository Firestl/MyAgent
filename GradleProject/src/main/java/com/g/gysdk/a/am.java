package com.g.gysdk.a;

/* JADX INFO: loaded from: classes.dex */
public class am {
    public static String a(Throwable th) {
        return a(th, null);
    }

    public static String a(Throwable th, String str) {
        if (th == null) {
            return str != null ? str : "nullThrowable";
        }
        Throwable cause = th.getCause();
        String message = cause != null ? cause.getMessage() : th.getMessage();
        return message != null ? message : th.getClass().getName();
    }
}
