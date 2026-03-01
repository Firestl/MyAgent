package com.vivo.push.cache;

import android.content.Context;
import com.vivo.push.util.o;
import com.vivo.push.util.z;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ConfigManagerFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile b f5601a;
    public d b;

    public static synchronized b a() {
        if (f5601a == null) {
            f5601a = new b();
        }
        return f5601a;
    }

    public final d a(Context context) {
        d dVar = this.b;
        if (dVar != null) {
            return dVar;
        }
        try {
            String str = z.a(context) ? "com.vivo.push.cache.ServerConfigManagerImpl" : "com.vivo.push.cache.ClientConfigManagerImpl";
            Method method = Class.forName(str).getMethod("getInstance", Context.class);
            o.d("ConfigManagerFactory", "createConfig success is ".concat(str));
            d dVar2 = (d) method.invoke(null, context);
            this.b = dVar2;
            return dVar2;
        } catch (Exception e2) {
            e2.printStackTrace();
            o.b("ConfigManagerFactory", "createConfig error", e2);
            return null;
        }
    }
}
