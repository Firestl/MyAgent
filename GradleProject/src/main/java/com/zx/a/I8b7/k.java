package com.zx.a.I8b7;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<Class<? extends b>, b> f6236a = new HashMap();

    public static void a(Context context, Class<? extends b> cls, Class<? extends c>... clsArr) throws IllegalAccessException, InstantiationException {
        Context applicationContext = context.getApplicationContext();
        b bVarNewInstance = (b) ((HashMap) f6236a).get(cls);
        if (bVarNewInstance == null) {
            bVarNewInstance = cls.newInstance();
            ((HashMap) f6236a).put(cls, bVarNewInstance);
        }
        for (Class<? extends c> cls2 : clsArr) {
            c cVarNewInstance = bVarNewInstance.f6199a.get(cls2);
            if (cVarNewInstance == null) {
                cVarNewInstance = cls2.newInstance();
                bVarNewInstance.f6199a.put(cls2, cVarNewInstance);
            }
            cVarNewInstance.f6202a = bVarNewInstance;
        }
        if (bVarNewInstance.c.getAndSet(true)) {
            return;
        }
        bVarNewInstance.b = new a(bVarNewInstance, applicationContext, bVarNewInstance.a(), null, bVarNewInstance.c());
        Iterator<Class<? extends c>> it = bVarNewInstance.f6199a.keySet().iterator();
        while (it.hasNext()) {
            bVarNewInstance.f6199a.get(it.next()).getClass();
        }
    }

    public static <T extends c> T a(Class<? extends b> cls, Class<T> cls2) {
        b bVar = (b) ((HashMap) f6236a).get(cls);
        if (bVar != null) {
            T t = (T) bVar.f6199a.get(cls2);
            if (t != null) {
                return t;
            }
            StringBuilder sbA = m2.a("table ");
            sbA.append(cls2.getSimpleName());
            sbA.append(" has not been added to db ");
            sbA.append(bVar.a());
            throw new RuntimeException(sbA.toString());
        }
        StringBuilder sbA2 = m2.a("db ");
        sbA2.append(cls.getSimpleName());
        sbA2.append(" has not been initialized");
        throw new RuntimeException(sbA2.toString());
    }
}
