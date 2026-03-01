package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FieldMetaData.java */
/* JADX INFO: loaded from: classes2.dex */
public class ch implements Serializable {
    public static Map<Class<? extends bv>, Map<? extends cc, ch>> d = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5227a;
    public final byte b;
    public final ci c;

    public ch(String str, byte b, ci ciVar) {
        this.f5227a = str;
        this.b = b;
        this.c = ciVar;
    }

    public static void a(Class<? extends bv> cls, Map<? extends cc, ch> map) {
        d.put(cls, map);
    }

    public static Map<? extends cc, ch> a(Class<? extends bv> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            } catch (InstantiationException e3) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e3.getMessage());
            }
        }
        return d.get(cls);
    }
}
