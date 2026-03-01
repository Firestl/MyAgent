package supwisdom;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class nj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f8535a = d();
    public static final Class<?> b = qi0.a();
    public static final boolean c = b(Long.TYPE);
    public static final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final mj0 f8536e;
    public static final boolean f;
    public static final boolean g;

    /* JADX WARN: Removed duplicated region for block: B:12:0x004c  */
    static {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.nj0.<clinit>():void");
    }

    public static int a(Class<?> cls) {
        if (g) {
            return f8536e.b(cls);
        }
        return -1;
    }

    public static boolean b() {
        return g;
    }

    public static boolean b(Class<?> cls) {
        int i = qi0.f8927a;
        try {
            Class<?> cls2 = b;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int c(Class<?> cls) {
        if (g) {
            return f8536e.a(cls);
        }
        return -1;
    }

    public static boolean c() {
        return f;
    }

    public static Unsafe d() {
        try {
            return (Unsafe) AccessController.doPrivileged(new jj0());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Field a() {
        int i = qi0.f8927a;
        Field fieldA = a(Buffer.class, "effectiveDirectAddress");
        if (fieldA != null) {
            return fieldA;
        }
        Field fieldA2 = a(Buffer.class, "address");
        if (fieldA2 == null || fieldA2.getType() != Long.TYPE) {
            return null;
        }
        return fieldA2;
    }

    public static Field a(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ void a(Throwable th) {
        Logger logger = Logger.getLogger(nj0.class.getName());
        Level level = Level.WARNING;
        String strValueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 71);
        sb.append("platform method missing - proto runtime falling back to safer methods: ");
        sb.append(strValueOf);
        logger.logp(level, "com.google.protobuf.UnsafeUtil", "logMissingMethod", sb.toString());
    }
}
