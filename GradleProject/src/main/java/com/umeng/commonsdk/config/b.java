package com.umeng.commonsdk.config;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: CollectController.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<String, Boolean> f5368a = new HashMap();
    public static Object b = new Object();

    /* JADX INFO: compiled from: CollectController.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5369a = new b();
    }

    public static b b() {
        return a.f5369a;
    }

    public void a() {
        synchronized (b) {
            f5368a.clear();
        }
    }

    public b() {
    }

    public static boolean a(String str) {
        if (!d.a(str)) {
            return false;
        }
        synchronized (b) {
            if (!f5368a.containsKey(str)) {
                return true;
            }
            return f5368a.get(str).booleanValue();
        }
    }

    @Override // com.umeng.commonsdk.config.f
    public void a(String str, Boolean bool) {
        if (d.a(str)) {
            synchronized (b) {
                if (f5368a != null) {
                    f5368a.put(str, bool);
                }
            }
        }
    }
}
