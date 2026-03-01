package supwisdom;

/* JADX INFO: compiled from: ExtensionRegistryFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class wp0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f9650a = b();

    public static xp0 a() {
        xp0 xp0VarA = a("getEmptyRegistry");
        return xp0VarA != null ? xp0VarA : xp0.d;
    }

    public static Class<?> b() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static final xp0 a(String str) {
        Class<?> cls = f9650a;
        if (cls == null) {
            return null;
        }
        try {
            return (xp0) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
