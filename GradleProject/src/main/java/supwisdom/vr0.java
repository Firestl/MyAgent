package supwisdom;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: UnsafeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class vr0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f9538a = Logger.getLogger(vr0.class.getName());
    public static final Unsafe b = c();
    public static final Class<?> c = lp0.a();
    public static final boolean d = d(Long.TYPE);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f9539e = d(Integer.TYPE);
    public static final e f = b();
    public static final boolean g = g();
    public static final boolean h = f();
    public static final long i = b((Class<?>) byte[].class);
    public static final long j;
    public static final boolean k;

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public class a implements PrivilegedExceptionAction<Unsafe> {
        @Override // java.security.PrivilegedExceptionAction
        public Unsafe run() throws Exception {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static final class b extends e {
        public b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // supwisdom.vr0.e
        public byte a(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // supwisdom.vr0.e
        public long b(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // supwisdom.vr0.e
        public double c(Object obj, long j) {
            return Double.longBitsToDouble(f(obj, j));
        }

        @Override // supwisdom.vr0.e
        public float d(Object obj, long j) {
            return Float.intBitsToFloat(e(obj, j));
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, byte b) {
            if (vr0.k) {
                vr0.c(obj, j, b);
            } else {
                vr0.d(obj, j, b);
            }
        }

        @Override // supwisdom.vr0.e
        public byte b(Object obj, long j) {
            return vr0.k ? vr0.h(obj, j) : vr0.i(obj, j);
        }

        @Override // supwisdom.vr0.e
        public boolean a(Object obj, long j) {
            return vr0.k ? vr0.f(obj, j) : vr0.g(obj, j);
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, boolean z) {
            if (vr0.k) {
                vr0.d(obj, j, z);
            } else {
                vr0.e(obj, j, z);
            }
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, float f) {
            a(obj, j, Float.floatToIntBits(f));
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, double d) {
            a(obj, j, Double.doubleToLongBits(d));
        }

        @Override // supwisdom.vr0.e
        public void a(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static final class c extends e {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // supwisdom.vr0.e
        public byte a(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // supwisdom.vr0.e
        public long b(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // supwisdom.vr0.e
        public double c(Object obj, long j) {
            return Double.longBitsToDouble(f(obj, j));
        }

        @Override // supwisdom.vr0.e
        public float d(Object obj, long j) {
            return Float.intBitsToFloat(e(obj, j));
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, byte b) {
            if (vr0.k) {
                vr0.c(obj, j, b);
            } else {
                vr0.d(obj, j, b);
            }
        }

        @Override // supwisdom.vr0.e
        public byte b(Object obj, long j) {
            return vr0.k ? vr0.h(obj, j) : vr0.i(obj, j);
        }

        @Override // supwisdom.vr0.e
        public boolean a(Object obj, long j) {
            return vr0.k ? vr0.f(obj, j) : vr0.g(obj, j);
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, boolean z) {
            if (vr0.k) {
                vr0.d(obj, j, z);
            } else {
                vr0.e(obj, j, z);
            }
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, float f) {
            a(obj, j, Float.floatToIntBits(f));
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, double d) {
            a(obj, j, Double.doubleToLongBits(d));
        }

        @Override // supwisdom.vr0.e
        public void a(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static final class d extends e {
        public d(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // supwisdom.vr0.e
        public byte a(long j) {
            return this.f9540a.getByte(j);
        }

        @Override // supwisdom.vr0.e
        public long b(long j) {
            return this.f9540a.getLong(j);
        }

        @Override // supwisdom.vr0.e
        public double c(Object obj, long j) {
            return this.f9540a.getDouble(obj, j);
        }

        @Override // supwisdom.vr0.e
        public float d(Object obj, long j) {
            return this.f9540a.getFloat(obj, j);
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, byte b) {
            this.f9540a.putByte(obj, j, b);
        }

        @Override // supwisdom.vr0.e
        public byte b(Object obj, long j) {
            return this.f9540a.getByte(obj, j);
        }

        @Override // supwisdom.vr0.e
        public boolean a(Object obj, long j) {
            return this.f9540a.getBoolean(obj, j);
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, boolean z) {
            this.f9540a.putBoolean(obj, j, z);
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, float f) {
            this.f9540a.putFloat(obj, j, f);
        }

        @Override // supwisdom.vr0.e
        public void a(Object obj, long j, double d) {
            this.f9540a.putDouble(obj, j, d);
        }

        @Override // supwisdom.vr0.e
        public void a(long j, byte[] bArr, long j2, long j3) {
            this.f9540a.copyMemory((Object) null, j, bArr, vr0.i + j2, j3);
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Unsafe f9540a;

        public e(Unsafe unsafe) {
            this.f9540a = unsafe;
        }

        public abstract byte a(long j);

        public final long a(Field field) {
            return this.f9540a.objectFieldOffset(field);
        }

        public abstract void a(long j, byte[] bArr, long j2, long j3);

        public abstract void a(Object obj, long j, byte b);

        public abstract void a(Object obj, long j, double d);

        public abstract void a(Object obj, long j, float f);

        public abstract void a(Object obj, long j, boolean z);

        public abstract boolean a(Object obj, long j);

        public abstract byte b(Object obj, long j);

        public final int b(Class<?> cls) {
            return this.f9540a.arrayIndexScale(cls);
        }

        public abstract long b(long j);

        public abstract double c(Object obj, long j);

        public abstract float d(Object obj, long j);

        public final int e(Object obj, long j) {
            return this.f9540a.getInt(obj, j);
        }

        public final long f(Object obj, long j) {
            return this.f9540a.getLong(obj, j);
        }

        public final Object g(Object obj, long j) {
            return this.f9540a.getObject(obj, j);
        }

        public final void a(Object obj, long j, int i) {
            this.f9540a.putInt(obj, j, i);
        }

        public final void a(Object obj, long j, long j2) {
            this.f9540a.putLong(obj, j, j2);
        }

        public final void a(Object obj, long j, Object obj2) {
            this.f9540a.putObject(obj, j, obj2);
        }

        public final int a(Class<?> cls) {
            return this.f9540a.arrayBaseOffset(cls);
        }
    }

    static {
        b((Class<?>) boolean[].class);
        c(boolean[].class);
        b((Class<?>) int[].class);
        c(int[].class);
        b((Class<?>) long[].class);
        c(long[].class);
        b((Class<?>) float[].class);
        c(float[].class);
        b((Class<?>) double[].class);
        c(double[].class);
        b((Class<?>) Object[].class);
        c(Object[].class);
        j = a(a());
        k = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static boolean e() {
        return g;
    }

    public static boolean f() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (lp0.b()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            f9538a.log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    public static boolean g() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (a() == null) {
                return false;
            }
            if (lp0.b()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            f9538a.log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    public static byte h(Object obj, long j2) {
        return (byte) ((l(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255);
    }

    public static byte i(Object obj, long j2) {
        return (byte) ((l(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255);
    }

    public static double j(Object obj, long j2) {
        return f.c(obj, j2);
    }

    public static float k(Object obj, long j2) {
        return f.d(obj, j2);
    }

    public static int l(Object obj, long j2) {
        return f.e(obj, j2);
    }

    public static long m(Object obj, long j2) {
        return f.f(obj, j2);
    }

    public static Object n(Object obj, long j2) {
        return f.g(obj, j2);
    }

    public static int c(Class<?> cls) {
        if (h) {
            return f.b(cls);
        }
        return -1;
    }

    public static boolean d() {
        return h;
    }

    public static boolean e(Object obj, long j2) {
        return f.a(obj, j2);
    }

    public static void c(Object obj, long j2, boolean z) {
        f.a(obj, j2, z);
    }

    public static boolean d(Class<?> cls) {
        if (!lp0.b()) {
            return false;
        }
        try {
            Class<?> cls2 = c;
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

    public static void e(Object obj, long j2, boolean z) {
        d(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static <T> T a(Class<T> cls) {
        try {
            return (T) b.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static long b(Field field) {
        return f.a(field);
    }

    public static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new a());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int b(Class<?> cls) {
        if (h) {
            return f.a(cls);
        }
        return -1;
    }

    public static void a(Object obj, long j2, int i2) {
        f.a(obj, j2, i2);
    }

    public static long b(long j2) {
        return f.b(j2);
    }

    public static void c(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int iL = l(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        a(obj, j3, ((255 & b2) << i2) | (iL & (~(255 << i2))));
    }

    public static void a(Object obj, long j2, long j3) {
        f.a(obj, j2, j3);
    }

    public static e b() {
        if (b == null) {
            return null;
        }
        if (lp0.b()) {
            if (d) {
                return new c(b);
            }
            if (f9539e) {
                return new b(b);
            }
            return null;
        }
        return new d(b);
    }

    public static void a(Object obj, long j2, float f2) {
        f.a(obj, j2, f2);
    }

    public static void a(Object obj, long j2, double d2) {
        f.a(obj, j2, d2);
    }

    public static void a(Object obj, long j2, Object obj2) {
        f.a(obj, j2, obj2);
    }

    public static byte a(byte[] bArr, long j2) {
        return f.b(bArr, i + j2);
    }

    public static void a(byte[] bArr, long j2, byte b2) {
        f.a((Object) bArr, i + j2, b2);
    }

    public static void a(long j2, byte[] bArr, long j3, long j4) {
        f.a(j2, bArr, j3, j4);
    }

    public static void d(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        a(obj, j3, ((255 & b2) << i2) | (l(obj, j3) & (~(255 << i2))));
    }

    public static byte a(long j2) {
        return f.a(j2);
    }

    public static long a(ByteBuffer byteBuffer) {
        return f.f(byteBuffer, j);
    }

    public static void d(Object obj, long j2, boolean z) {
        c(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static Field a() {
        Field fieldA;
        if (lp0.b() && (fieldA = a((Class<?>) Buffer.class, "effectiveDirectAddress")) != null) {
            return fieldA;
        }
        Field fieldA2 = a((Class<?>) Buffer.class, "address");
        if (fieldA2 == null || fieldA2.getType() != Long.TYPE) {
            return null;
        }
        return fieldA2;
    }

    public static boolean g(Object obj, long j2) {
        return i(obj, j2) != 0;
    }

    public static long a(Field field) {
        e eVar;
        if (field == null || (eVar = f) == null) {
            return -1L;
        }
        return eVar.a(field);
    }

    public static Field a(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean f(Object obj, long j2) {
        return h(obj, j2) != 0;
    }
}
