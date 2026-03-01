package supwisdom;

/* JADX INFO: compiled from: ExtensionSchemas.java */
/* JADX INFO: loaded from: classes.dex */
public final class aq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final yp0<?> f6967a = new zp0();
    public static final yp0<?> b = c();

    public static yp0<?> a() {
        yp0<?> yp0Var = b;
        if (yp0Var != null) {
            return yp0Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static yp0<?> b() {
        return f6967a;
    }

    public static yp0<?> c() {
        try {
            return (yp0) Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
