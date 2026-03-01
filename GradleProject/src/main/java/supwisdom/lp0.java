package supwisdom;

/* JADX INFO: compiled from: Android.java */
/* JADX INFO: loaded from: classes.dex */
public final class lp0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f8308a = a("libcore.io.Memory");
    public static final boolean b;

    static {
        b = a("org.robolectric.Robolectric") != null;
    }

    public static Class<?> a() {
        return f8308a;
    }

    public static boolean b() {
        return (f8308a == null || b) ? false : true;
    }

    public static <T> Class<T> a(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
