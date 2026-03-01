package supwisdom;

/* JADX INFO: compiled from: Args.java */
/* JADX INFO: loaded from: classes2.dex */
public class yo1 {
    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static <T extends CharSequence> T b(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        }
        if (!bp1.b(t)) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be empty");
    }

    public static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static <T extends CharSequence> T a(T t, String str) {
        if (t != null) {
            if (!bp1.a(t)) {
                return t;
            }
            throw new IllegalArgumentException(str + " may not be blank");
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static int a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }
}
