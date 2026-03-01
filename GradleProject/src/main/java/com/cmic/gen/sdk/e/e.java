package com.cmic.gen.sdk.e;

import com.cmic.gen.sdk.auth.GenTokenListener;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, GenTokenListener> f1729a = new ConcurrentHashMap<>(16);
    public static ConcurrentHashMap<String, com.cmic.gen.sdk.a> b = new ConcurrentHashMap<>();

    public static void a(String str, com.cmic.gen.sdk.a aVar) {
        if (str == null || aVar == null) {
            return;
        }
        b.put(str, aVar);
    }

    public static void a(String str, GenTokenListener genTokenListener) {
        f1729a.put(str, genTokenListener);
    }

    public static boolean a() {
        return f1729a.isEmpty();
    }

    public static boolean a(String str) {
        return !f1729a.containsKey(str);
    }

    public static void b(String str) {
        f1729a.remove(str);
    }

    public static GenTokenListener c(String str) {
        return f1729a.get(str);
    }

    public static com.cmic.gen.sdk.a d(String str) {
        return str != null ? b.get(str) : new com.cmic.gen.sdk.a(0);
    }
}
