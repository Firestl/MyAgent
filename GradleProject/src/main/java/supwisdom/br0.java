package supwisdom;

/* JADX INFO: compiled from: NewInstanceSchemas.java */
/* JADX INFO: loaded from: classes.dex */
public final class br0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final zq0 f7092a = c();
    public static final zq0 b = new ar0();

    public static zq0 a() {
        return f7092a;
    }

    public static zq0 b() {
        return b;
    }

    public static zq0 c() {
        try {
            return (zq0) Class.forName("com.google.crypto.tink.shaded.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
