package com.efs.sdk.net.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static <T extends Throwable> void a(Throwable th, Class<T> cls) throws Throwable {
        if (cls.isInstance(th)) {
            throw th;
        }
    }
}
