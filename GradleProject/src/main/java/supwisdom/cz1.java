package supwisdom;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: UnsafeAccess.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cz1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f7274a;
    public static final boolean b;

    static {
        b = System.getProperty("rx.unsafe-disable") != null;
        Unsafe unsafe = null;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Throwable unused) {
        }
        f7274a = unsafe;
    }

    public static boolean a() {
        return (f7274a == null || b) ? false : true;
    }

    public static long a(Class<?> cls, String str) {
        try {
            return f7274a.objectFieldOffset(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e2) {
            InternalError internalError = new InternalError();
            internalError.initCause(e2);
            throw internalError;
        }
    }
}
