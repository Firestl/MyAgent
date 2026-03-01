package com.umeng.ccg;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: CcgSwitch.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f5343a = true;
    public static volatile boolean b = true;
    public static volatile boolean c = true;
    public static volatile boolean d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object f5344e;
    public static Map<String, Boolean> f;

    static {
        if (0 == 0) {
            f = new HashMap();
            f5344e = new Object();
        }
    }

    public static boolean a() {
        boolean z;
        synchronized (f5344e) {
            z = f5343a;
        }
        return z;
    }

    public static boolean b() {
        boolean z;
        synchronized (f5344e) {
            z = b;
        }
        return z;
    }

    public static boolean c() {
        boolean z;
        synchronized (f5344e) {
            z = c;
        }
        return z;
    }

    public static boolean d() {
        boolean z;
        synchronized (f5344e) {
            z = d;
        }
        return z;
    }

    public static void a(boolean z) {
        synchronized (f5344e) {
            d = z;
            f.put(a.f5342e, Boolean.valueOf(z));
        }
    }

    public static boolean a(String str) {
        boolean zBooleanValue;
        synchronized (f5344e) {
            zBooleanValue = f.containsKey(str) ? f.get(str).booleanValue() : true;
        }
        return zBooleanValue;
    }
}
