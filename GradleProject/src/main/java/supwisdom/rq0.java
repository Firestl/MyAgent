package supwisdom;

/* JADX INFO: compiled from: MapFieldSchemas.java */
/* JADX INFO: loaded from: classes.dex */
public final class rq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final pq0 f9086a = c();
    public static final pq0 b = new qq0();

    public static pq0 a() {
        return f9086a;
    }

    public static pq0 b() {
        return b;
    }

    public static pq0 c() {
        try {
            return (pq0) Class.forName("com.google.crypto.tink.shaded.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
