package supwisdom;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class mj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Unsafe f8398a;

    public mj0(Unsafe unsafe) {
        this.f8398a = unsafe;
    }

    public final long a(Field field) {
        return this.f8398a.objectFieldOffset(field);
    }

    public final int b(Class<?> cls) {
        return this.f8398a.arrayIndexScale(cls);
    }

    public final int a(Class<?> cls) {
        return this.f8398a.arrayBaseOffset(cls);
    }
}
