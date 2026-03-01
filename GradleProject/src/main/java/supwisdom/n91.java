package supwisdom;

import android.content.Context;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class n91 {
    public static n91 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8487a;
    public Object b;
    public String c;

    public static Object a() {
        return c().b;
    }

    public static Context b() {
        return c().f8487a;
    }

    public static n91 c() {
        synchronized (n91.class) {
            if (d == null) {
                d = new n91();
            }
        }
        return d;
    }

    public static String d() {
        return c().c;
    }
}
