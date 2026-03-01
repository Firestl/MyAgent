package supwisdom;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.bouncycastle.crypto.engines.RC564Engine;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: TLRandom.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pq1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f8820a = qq1.f8946a;
    public static final boolean b;
    public static final boolean c;
    public static final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f8821e;
    public static final long f;
    public static final long g;
    public static final ThreadLocal<c> h;
    public static final AtomicInteger i;
    public static final AtomicLong j;

    /* JADX INFO: compiled from: TLRandom.java */
    public static class a extends ThreadLocal<c> {
        @Override // java.lang.ThreadLocal
        public c initialValue() {
            return new c(null);
        }
    }

    /* JADX INFO: compiled from: TLRandom.java */
    public static class b implements PrivilegedAction<Boolean> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Boolean run() {
            return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
        }
    }

    /* JADX INFO: compiled from: TLRandom.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f8822a;
        public int b;
        public int c;

        public c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    static {
        try {
            b = f();
            boolean zE = e();
            c = zE;
            if (zE) {
                d = 0L;
                f8821e = 0L;
                f = 0L;
                g = 0L;
            } else {
                d = f8820a.objectFieldOffset(Thread.class.getDeclaredField("threadLocals"));
                f8821e = f8820a.objectFieldOffset(Thread.class.getDeclaredField("inheritableThreadLocals"));
                f = f8820a.objectFieldOffset(Thread.class.getDeclaredField(b ? "accessControlContext" : "inheritedAccessControlContext"));
                g = f8820a.objectFieldOffset(Thread.class.getDeclaredField("contextClassLoader"));
            }
            h = new a();
            i = new AtomicInteger();
            j = new AtomicLong(b(System.currentTimeMillis()) ^ b(System.nanoTime()));
            if (((Boolean) AccessController.doPrivileged(new b())).booleanValue()) {
                byte[] seed = SecureRandom.getSeed(8);
                long j2 = ((long) seed[0]) & 255;
                for (int i2 = 1; i2 < 8; i2++) {
                    j2 = (j2 << 8) | (((long) seed[i2]) & 255);
                }
                j.set(j2);
            }
        } catch (Exception e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    public static final int a() {
        return b();
    }

    public static int a(long j2) {
        long j3 = (j2 ^ (j2 >>> 33)) * (-49064778989728563L);
        return (int) (((j3 ^ (j3 >>> 33)) * (-4265267296055464877L)) >>> 32);
    }

    public static int b() {
        return h.get().b;
    }

    public static long b(long j2) {
        long j3 = (j2 ^ (j2 >>> 33)) * (-49064778989728563L);
        long j4 = (j3 ^ (j3 >>> 33)) * (-4265267296055464877L);
        return j4 ^ (j4 >>> 33);
    }

    public static void c(long j2) {
        h.get().f8822a = j2;
    }

    public static long d() {
        return h.get().f8822a;
    }

    public static boolean e() {
        if (a("android.util.DisplayMetrics")) {
            return true;
        }
        return a("org.robovm.rt.bro.Bro");
    }

    public static boolean f() {
        String property;
        return a("com.ibm.misc.JarVersion") && (property = System.getProperty("java.class.version", "45")) != null && property.length() >= 2 && "52".compareTo(property.substring(0, 2)) > 0;
    }

    public static final void g() {
        int iAddAndGet = i.addAndGet(-1640531527);
        if (iAddAndGet == 0) {
            iAddAndGet = 1;
        }
        c(b(j.getAndAdd(-4942790177534073029L)));
        b(iAddAndGet);
    }

    public static final int h() {
        int iA;
        int iC = c();
        if (iC != 0) {
            int i2 = iC ^ (iC << 13);
            int i3 = i2 ^ (i2 >>> 17);
            iA = i3 ^ (i3 << 5);
        } else {
            iA = a(j.getAndAdd(-4942790177534073029L));
            if (iA == 0) {
                iA = 1;
            }
        }
        c(iA);
        return iA;
    }

    public static final long i() {
        long jD = d() + RC564Engine.Q64;
        c(jD);
        return jD;
    }

    public static final int a(int i2) {
        int i3 = i2 ^ (i2 << 13);
        int i4 = i3 ^ (i3 >>> 17);
        int i5 = i4 ^ (i4 << 5);
        b(i5);
        return i5;
    }

    public static void b(int i2) {
        h.get().b = i2;
    }

    public static int c() {
        return h.get().c;
    }

    public static final void a(Thread thread) {
        if (c) {
            return;
        }
        f8820a.putObject(thread, d, (Object) null);
        f8820a.putObject(thread, f8821e, (Object) null);
    }

    public static void c(int i2) {
        h.get().c = i2;
    }

    public static final void a(Thread thread, AccessControlContext accessControlContext) {
        if (c) {
            return;
        }
        f8820a.putOrderedObject(thread, f, accessControlContext);
    }

    public static final void a(Thread thread, ClassLoader classLoader) {
        if (c) {
            return;
        }
        f8820a.putObject(thread, g, classLoader);
    }

    public static boolean a(String str) {
        Class<?> cls;
        try {
            cls = Class.forName(str, false, pq1.class.getClassLoader());
        } catch (Throwable unused) {
            cls = null;
        }
        return cls != null;
    }
}
