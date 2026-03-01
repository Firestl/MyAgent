package supwisdom;

/* JADX INFO: compiled from: PlatformDependent.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cy1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f7271a;
    public static final boolean b;

    static {
        int iC = c();
        f7271a = iC;
        b = iC != 0;
    }

    public static int a() {
        return f7271a;
    }

    public static boolean b() {
        return b;
    }

    public static int c() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }
}
