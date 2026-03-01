package supwisdom;

/* JADX INFO: loaded from: classes2.dex */
public class cx0 extends zw0 {
    public static volatile cx0 f;

    public cx0() {
        new kx0();
    }

    public static cx0 f() {
        if (f == null) {
            synchronized (cx0.class) {
                if (f == null) {
                    f = new cx0();
                }
            }
        }
        return f;
    }
}
