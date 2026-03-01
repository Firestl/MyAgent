package supwisdom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public enum di0 {
    DEFAULT(0),
    UNKNOWN_CERT(1),
    TEST_KEYS_REJECTED(2),
    PACKAGE_NOT_FOUND(3),
    GENERIC_ERROR(4);


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7346a;

    di0(int i) {
        this.f7346a = i;
    }

    public static di0 a(int i) {
        for (di0 di0Var : values()) {
            if (di0Var.f7346a == i) {
                return di0Var;
            }
        }
        return DEFAULT;
    }
}
