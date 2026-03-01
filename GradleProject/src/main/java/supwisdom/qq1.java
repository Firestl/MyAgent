package supwisdom;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: UnsafeAccess.java */
/* JADX INFO: loaded from: classes3.dex */
public class qq1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f8946a;

    static {
        Field declaredField;
        try {
            try {
                declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            } catch (NoSuchFieldException unused) {
                declaredField = Unsafe.class.getDeclaredField("THE_ONE");
            }
            declaredField.setAccessible(true);
            f8946a = (Unsafe) declaredField.get(null);
        } catch (Exception e2) {
            throw new Error(e2);
        }
    }
}
