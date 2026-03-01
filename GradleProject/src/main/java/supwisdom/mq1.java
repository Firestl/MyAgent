package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permission;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java9.util.concurrent.CountedCompleter;
import java9.util.concurrent.ForkJoinTask;
import sun.misc.Unsafe;
import supwisdom.nq1;

/* JADX INFO: compiled from: ForkJoinPool.java */
/* JADX INFO: loaded from: classes3.dex */
public class mq1 extends AbstractExecutorService {
    public static final c l;
    public static final RuntimePermission m;
    public static final mq1 n;
    public static final int o;
    public static int p;
    public static final Unsafe q;
    public static final long r;
    public static final long s;
    public static final int t;
    public static final int u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile long f8416a;
    public volatile long b;
    public final long c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8417e;
    public volatile int f;
    public f[] g;
    public final String h;
    public final c i;
    public final Thread.UncaughtExceptionHandler j;
    public final rq1<? super mq1> k;

    /* JADX INFO: compiled from: ForkJoinPool.java */
    public static class a implements PrivilegedAction<mq1> {
        @Override // java.security.PrivilegedAction
        public mq1 run() {
            return new mq1((byte) 0, null);
        }
    }

    /* JADX INFO: compiled from: ForkJoinPool.java */
    public static final class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AccessControlContext f8418a = mq1.a(new RuntimePermission("getClassLoader"));

        /* JADX INFO: compiled from: ForkJoinPool.java */
        public class a implements PrivilegedAction<nq1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ mq1 f8419a;

            public a(b bVar, mq1 mq1Var) {
                this.f8419a = mq1Var;
            }

            @Override // java.security.PrivilegedAction
            public nq1 run() {
                return new nq1(this.f8419a, ClassLoader.getSystemClassLoader());
            }
        }

        public b() {
        }

        @Override // supwisdom.mq1.c
        public final nq1 a(mq1 mq1Var) {
            return (nq1) AccessController.doPrivileged(new a(this, mq1Var), f8418a);
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: ForkJoinPool.java */
    public interface c {
        nq1 a(mq1 mq1Var);
    }

    /* JADX INFO: compiled from: ForkJoinPool.java */
    public static final class d implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AccessControlContext f8420a = mq1.a(mq1.m, new RuntimePermission("enableContextClassLoaderOverride"), new RuntimePermission("modifyThreadGroup"), new RuntimePermission("getClassLoader"), new RuntimePermission("setContextClassLoader"));

        /* JADX INFO: compiled from: ForkJoinPool.java */
        public class a implements PrivilegedAction<nq1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ mq1 f8421a;

            public a(d dVar, mq1 mq1Var) {
                this.f8421a = mq1Var;
            }

            @Override // java.security.PrivilegedAction
            public nq1 run() {
                return new nq1.a(this.f8421a);
            }
        }

        public d() {
        }

        @Override // supwisdom.mq1.c
        public final nq1 a(mq1 mq1Var) {
            return (nq1) AccessController.doPrivileged(new a(this, mq1Var), f8420a);
        }

        public /* synthetic */ d(a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: ForkJoinPool.java */
    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final oq1 f8422a = new oq1();
        public static final Unsafe b;
        public static final long c;

        static {
            Unsafe unsafe = qq1.f8946a;
            b = unsafe;
            try {
                c = unsafe.objectFieldOffset(oq1.class.getDeclaredField("a"));
            } catch (Exception e2) {
                throw new ExceptionInInitializerError(e2);
            }
        }

        public static void a() {
            b.putIntVolatile(f8422a, c, 0);
        }

        public static void b() {
            b.putOrderedInt(f8422a, c, 0);
        }
    }

    /* JADX INFO: compiled from: ForkJoinPool.java */
    public static final class f {
        public static final Unsafe k;
        public static final long l;
        public static final int m;
        public static final int n;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public volatile int f8423a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public volatile int f8424e;
        public ForkJoinTask<?>[] h;
        public final mq1 i;
        public final nq1 j;
        public int g = 4096;
        public volatile int f = 4096;

        static {
            Unsafe unsafe = qq1.f8946a;
            k = unsafe;
            try {
                l = unsafe.objectFieldOffset(f.class.getDeclaredField("a"));
                m = k.arrayBaseOffset(ForkJoinTask[].class);
                int iArrayIndexScale = k.arrayIndexScale(ForkJoinTask[].class);
                if (((iArrayIndexScale - 1) & iArrayIndexScale) != 0) {
                    throw new ExceptionInInitializerError("array index scale not a power of two");
                }
                n = 31 - Integer.numberOfLeadingZeros(iArrayIndexScale);
            } catch (Exception e2) {
                throw new ExceptionInInitializerError(e2);
            }
        }

        public f(mq1 mq1Var, nq1 nq1Var) {
            this.i = mq1Var;
            this.j = nq1Var;
        }

        public final void a(ForkJoinTask<?> forkJoinTask) throws Throwable {
            int length;
            int i = this.g;
            ForkJoinTask<?>[] forkJoinTaskArr = this.h;
            if (forkJoinTaskArr == null || (length = forkJoinTaskArr.length) <= 0) {
                return;
            }
            long j = (((long) ((length - 1) & i)) << n) + ((long) m);
            mq1 mq1Var = this.i;
            this.g = i + 1;
            k.putOrderedObject(forkJoinTaskArr, j, forkJoinTask);
            int i2 = this.f - i;
            if (i2 == 0 && mq1Var != null) {
                e.a();
                mq1Var.d();
            } else if (i2 + length == 1) {
                b();
            }
        }

        public final ForkJoinTask<?>[] b() {
            int i;
            ForkJoinTask<?>[] forkJoinTaskArr = this.h;
            int length = forkJoinTaskArr != null ? forkJoinTaskArr.length : 0;
            int i2 = length > 0 ? length << 1 : 8192;
            if (i2 < 8192 || i2 > 67108864) {
                throw new RejectedExecutionException("Queue capacity exceeded");
            }
            ForkJoinTask<?>[] forkJoinTaskArr2 = new ForkJoinTask[i2];
            this.h = forkJoinTaskArr2;
            if (forkJoinTaskArr != null && length - 1 > 0) {
                int i3 = this.g;
                int i4 = this.f;
                if (i3 - i4 > 0) {
                    int i5 = i2 - 1;
                    int i6 = i4;
                    do {
                        long j = ((long) m) + (((long) (i6 & i)) << n);
                        ForkJoinTask<?> forkJoinTask = (ForkJoinTask) k.getObjectVolatile(forkJoinTaskArr, j);
                        if (forkJoinTask != null && k.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, (Object) null)) {
                            forkJoinTaskArr2[i6 & i5] = forkJoinTask;
                        }
                        i6++;
                    } while (i6 != i3);
                    e.b();
                }
            }
            return forkJoinTaskArr2;
        }

        public final boolean c(ForkJoinTask<?> forkJoinTask) {
            int length;
            boolean z = true;
            int i = this.g - 1;
            ForkJoinTask<?>[] forkJoinTaskArr = this.h;
            if (forkJoinTaskArr == null || (length = forkJoinTaskArr.length) <= 0) {
                return false;
            }
            long j = (((long) ((length - 1) & i)) << n) + ((long) m);
            if (((ForkJoinTask) k.getObject(forkJoinTaskArr, j)) != forkJoinTask || !k.compareAndSwapInt(this, l, 0, 1)) {
                return false;
            }
            if (this.g == i + 1 && this.h == forkJoinTaskArr && k.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, (Object) null)) {
                this.g = i;
            } else {
                z = false;
            }
            k.putOrderedInt(this, l, 0);
            return z;
        }

        public final ForkJoinTask<?> d() {
            return (this.d & 65536) != 0 ? f() : g();
        }

        public final ForkJoinTask<?> e() {
            int length;
            ForkJoinTask<?>[] forkJoinTaskArr = this.h;
            if (forkJoinTaskArr == null || (length = forkJoinTaskArr.length) <= 0) {
                return null;
            }
            return forkJoinTaskArr[(length - 1) & ((this.d & 65536) != 0 ? this.f : this.g - 1)];
        }

        public final ForkJoinTask<?> f() {
            int i;
            int length;
            while (true) {
                int i2 = this.f;
                int i3 = this.g;
                ForkJoinTask<?>[] forkJoinTaskArr = this.h;
                if (forkJoinTaskArr == null || (i = i2 - i3) >= 0 || (length = forkJoinTaskArr.length) <= 0) {
                    return null;
                }
                long j = (((long) ((length - 1) & i2)) << n) + ((long) m);
                ForkJoinTask<?> forkJoinTask = (ForkJoinTask) k.getObjectVolatile(forkJoinTaskArr, j);
                int i4 = i2 + 1;
                if (i2 == this.f) {
                    if (forkJoinTask != null) {
                        if (k.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, (Object) null)) {
                            this.f = i4;
                            return forkJoinTask;
                        }
                    } else if (i == -1) {
                        return null;
                    }
                }
            }
        }

        public final ForkJoinTask<?> g() {
            int length;
            int i = this.f;
            int i2 = this.g;
            ForkJoinTask<?>[] forkJoinTaskArr = this.h;
            if (forkJoinTaskArr == null || i == i2 || (length = forkJoinTaskArr.length) <= 0) {
                return null;
            }
            int i3 = i2 - 1;
            long j = (((long) ((length - 1) & i3)) << n) + ((long) m);
            ForkJoinTask<?> forkJoinTask = (ForkJoinTask) k.getObject(forkJoinTaskArr, j);
            if (forkJoinTask == null || !k.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, (Object) null)) {
                return null;
            }
            this.g = i3;
            e.b();
            return forkJoinTask;
        }

        public final int h() {
            int i = this.f - this.g;
            if (i >= 0) {
                return 0;
            }
            return -i;
        }

        public final boolean i() {
            return k.compareAndSwapInt(this, l, 0, 1);
        }

        public final boolean d(ForkJoinTask<?> forkJoinTask) {
            int length;
            int i = this.f;
            int i2 = this.g;
            ForkJoinTask<?>[] forkJoinTaskArr = this.h;
            if (forkJoinTaskArr == null || i == i2 || (length = forkJoinTaskArr.length) <= 0) {
                return false;
            }
            int i3 = i2 - 1;
            if (!k.compareAndSwapObject(forkJoinTaskArr, (((long) ((length - 1) & i3)) << n) + ((long) m), forkJoinTask, (Object) null)) {
                return false;
            }
            this.g = i3;
            e.b();
            return true;
        }

        public final void a() {
            while (true) {
                ForkJoinTask<?> forkJoinTaskF = f();
                if (forkJoinTaskF == null) {
                    return;
                } else {
                    ForkJoinTask.cancelIgnoringExceptions(forkJoinTaskF);
                }
            }
        }

        public final boolean c() {
            Thread.State state;
            nq1 nq1Var = this.j;
            return (nq1Var == null || (state = nq1Var.getState()) == Thread.State.BLOCKED || state == Thread.State.WAITING || state == Thread.State.TIMED_WAITING) ? false : true;
        }

        public final void a(int i) {
            int i2;
            int length;
            do {
                int i3 = 0;
                while (true) {
                    int i4 = this.f;
                    int i5 = this.g;
                    ForkJoinTask<?>[] forkJoinTaskArr = this.h;
                    if (forkJoinTaskArr != null && (i2 = i4 - i5) < 0 && (length = forkJoinTaskArr.length) > 0) {
                        int i6 = i4 + 1;
                        ForkJoinTask forkJoinTask = (ForkJoinTask) mq1.a(forkJoinTaskArr, (((long) (i4 & (length - 1))) << n) + ((long) m), (Object) null);
                        if (forkJoinTask == null) {
                            break;
                        }
                        this.f = i6;
                        forkJoinTask.doExec();
                        if (i != 0 && (i3 = i3 + 1) == i) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (i2 != -1);
        }

        public final void b(int i) {
            int length;
            while (true) {
                int i2 = this.f;
                int i3 = this.g;
                ForkJoinTask<?>[] forkJoinTaskArr = this.h;
                if (forkJoinTaskArr == null || i2 == i3 || (length = forkJoinTaskArr.length) <= 0) {
                    return;
                }
                int i4 = i3 - 1;
                ForkJoinTask forkJoinTask = (ForkJoinTask) mq1.a(forkJoinTaskArr, (((long) ((length - 1) & i4)) << n) + ((long) m), (Object) null);
                if (forkJoinTask == null) {
                    return;
                }
                this.g = i4;
                e.b();
                forkJoinTask.doExec();
                if (i != 0 && i - 1 == 0) {
                    return;
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x004b A[EDGE_INSN: B:38:0x004b->B:21:0x004b BREAK  A[LOOP:1: B:14:0x0030->B:40:?]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int a(java9.util.concurrent.CountedCompleter<?> r10, int r11) {
            /*
                r9 = this;
                r0 = 0
                if (r10 == 0) goto L5b
                int r1 = r10.status
                if (r1 < 0) goto L5a
            L7:
                int r1 = r9.f
                int r2 = r9.g
                java9.util.concurrent.ForkJoinTask<?>[] r4 = r9.h
                if (r4 == 0) goto L4b
                if (r1 == r2) goto L4b
                int r1 = r4.length
                if (r1 <= 0) goto L4b
                int r1 = r1 + (-1)
                int r2 = r2 + (-1)
                r1 = r1 & r2
                long r5 = (long) r1
                int r1 = supwisdom.mq1.f.n
                long r5 = r5 << r1
                int r1 = supwisdom.mq1.f.m
                long r7 = (long) r1
                long r5 = r5 + r7
                sun.misc.Unsafe r1 = supwisdom.mq1.f.k
                java.lang.Object r1 = r1.getObject(r4, r5)
                java9.util.concurrent.ForkJoinTask r1 = (java9.util.concurrent.ForkJoinTask) r1
                boolean r3 = r1 instanceof java9.util.concurrent.CountedCompleter
                if (r3 == 0) goto L4b
                java9.util.concurrent.CountedCompleter r1 = (java9.util.concurrent.CountedCompleter) r1
                r3 = r1
            L30:
                if (r3 == r10) goto L37
                java9.util.concurrent.CountedCompleter<?> r3 = r3.completer
                if (r3 != 0) goto L30
                goto L4b
            L37:
                sun.misc.Unsafe r3 = supwisdom.mq1.f.k
                r8 = 0
                r7 = r1
                boolean r3 = r3.compareAndSwapObject(r4, r5, r7, r8)
                if (r3 == 0) goto L4b
                r9.g = r2
                supwisdom.mq1.e.b()
                r1.doExec()
                r1 = 1
                goto L4c
            L4b:
                r1 = 0
            L4c:
                int r2 = r10.status
                if (r2 < 0) goto L58
                if (r1 == 0) goto L58
                if (r11 == 0) goto L7
                int r11 = r11 + (-1)
                if (r11 != 0) goto L7
            L58:
                r0 = r2
                goto L5b
            L5a:
                r0 = r1
            L5b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.f.a(java9.util.concurrent.CountedCompleter, int):int");
        }

        public final void b(ForkJoinTask<?> forkJoinTask) {
            ForkJoinTask<?>[] forkJoinTaskArr;
            int length;
            int i = this.f;
            int i2 = this.g;
            if (i - i2 >= 0 || (forkJoinTaskArr = this.h) == null || (length = forkJoinTaskArr.length) <= 0) {
                return;
            }
            int i3 = length - 1;
            int i4 = i2 - 1;
            int i5 = i4;
            while (true) {
                long j = ((i5 & i3) << n) + m;
                ForkJoinTask<?> forkJoinTask2 = (ForkJoinTask) k.getObject(forkJoinTaskArr, j);
                if (forkJoinTask2 == null) {
                    return;
                }
                if (forkJoinTask2 == forkJoinTask) {
                    if (k.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask2, (Object) null)) {
                        this.g = i4;
                        while (i5 != i4) {
                            int i6 = i5 + 1;
                            long j2 = ((i6 & i3) << n) + m;
                            ForkJoinTask forkJoinTask3 = (ForkJoinTask) k.getObject(forkJoinTaskArr, j2);
                            k.putObjectVolatile(forkJoinTaskArr, j2, (Object) null);
                            k.putOrderedObject(forkJoinTaskArr, ((i5 & i3) << n) + m, forkJoinTask3);
                            i5 = i6;
                        }
                        e.b();
                        forkJoinTask2.doExec();
                        return;
                    }
                    return;
                }
                i5--;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0076 A[EDGE_INSN: B:49:0x0076->B:32:0x0076 BREAK  A[LOOP:1: B:15:0x003a->B:51:?]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int b(java9.util.concurrent.CountedCompleter<?> r19, int r20) {
            /*
                r18 = this;
                r6 = r18
                r7 = r19
                r8 = 0
                if (r7 == 0) goto L86
                int r0 = r7.status
                if (r0 < 0) goto L85
                r9 = r20
            Ld:
                int r0 = r6.f
                int r10 = r6.g
                java9.util.concurrent.ForkJoinTask<?>[] r12 = r6.h
                if (r12 == 0) goto L76
                if (r0 == r10) goto L76
                int r0 = r12.length
                if (r0 <= 0) goto L76
                int r0 = r0 + (-1)
                int r15 = r10 + (-1)
                r0 = r0 & r15
                long r0 = (long) r0
                int r2 = supwisdom.mq1.f.n
                long r0 = r0 << r2
                int r2 = supwisdom.mq1.f.m
                long r2 = (long) r2
                long r13 = r0 + r2
                sun.misc.Unsafe r0 = supwisdom.mq1.f.k
                java.lang.Object r0 = r0.getObject(r12, r13)
                java9.util.concurrent.ForkJoinTask r0 = (java9.util.concurrent.ForkJoinTask) r0
                boolean r1 = r0 instanceof java9.util.concurrent.CountedCompleter
                if (r1 == 0) goto L76
                r17 = r0
                java9.util.concurrent.CountedCompleter r17 = (java9.util.concurrent.CountedCompleter) r17
                r0 = r17
            L3a:
                if (r0 == r7) goto L41
                java9.util.concurrent.CountedCompleter<?> r0 = r0.completer
                if (r0 != 0) goto L3a
                goto L76
            L41:
                sun.misc.Unsafe r0 = supwisdom.mq1.f.k
                long r2 = supwisdom.mq1.f.l
                r4 = 0
                r5 = 1
                r1 = r18
                boolean r0 = r0.compareAndSwapInt(r1, r2, r4, r5)
                if (r0 == 0) goto L76
                int r0 = r6.g
                if (r0 != r10) goto L68
                java9.util.concurrent.ForkJoinTask<?>[] r0 = r6.h
                if (r0 != r12) goto L68
                sun.misc.Unsafe r11 = supwisdom.mq1.f.k
                r16 = 0
                r10 = r15
                r15 = r17
                boolean r0 = r11.compareAndSwapObject(r12, r13, r15, r16)
                if (r0 == 0) goto L68
                r6.g = r10
                r0 = 1
                goto L69
            L68:
                r0 = 0
            L69:
                sun.misc.Unsafe r1 = supwisdom.mq1.f.k
                long r2 = supwisdom.mq1.f.l
                r1.putOrderedInt(r6, r2, r8)
                if (r0 == 0) goto L77
                r17.doExec()
                goto L77
            L76:
                r0 = 0
            L77:
                int r1 = r7.status
                if (r1 < 0) goto L83
                if (r0 == 0) goto L83
                if (r9 == 0) goto Ld
                int r9 = r9 + (-1)
                if (r9 != 0) goto Ld
            L83:
                r8 = r1
                goto L86
            L85:
                r8 = r0
            L86:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.f.b(java9.util.concurrent.CountedCompleter, int):int");
        }
    }

    static {
        Unsafe unsafe = qq1.f8946a;
        q = unsafe;
        try {
            r = unsafe.objectFieldOffset(mq1.class.getDeclaredField("a"));
            s = q.objectFieldOffset(mq1.class.getDeclaredField(cn.com.chinatelecom.account.api.e.f.f1517a));
            t = q.arrayBaseOffset(ForkJoinTask[].class);
            int iArrayIndexScale = q.arrayIndexScale(ForkJoinTask[].class);
            if (((iArrayIndexScale - 1) & iArrayIndexScale) != 0) {
                throw new ExceptionInInitializerError("array index scale not a power of two");
            }
            u = 31 - Integer.numberOfLeadingZeros(iArrayIndexScale);
            int i = 256;
            try {
                String property = System.getProperty("java.util.concurrent.ForkJoinPool.common.maximumSpares");
                if (property != null) {
                    i = Integer.parseInt(property);
                }
            } catch (Exception unused) {
            }
            o = i;
            l = new b(null);
            m = new RuntimePermission("modifyThread");
            mq1 mq1Var = (mq1) AccessController.doPrivileged(new a());
            n = mq1Var;
            Math.max(mq1Var.f & 65535, 1);
            try {
                Class.forName("java9.util.concurrent.CompletableFuture$AsynchronousCompletionTask");
            } catch (Exception unused2) {
            }
        } catch (Exception e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    public /* synthetic */ mq1(byte b2, a aVar) {
        this(b2);
    }

    public static AccessControlContext a(Permission... permissionArr) {
        Permissions permissions = new Permissions();
        for (Permission permission : permissionArr) {
            permissions.add(permission);
        }
        return new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, permissions)});
    }

    public static void e() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(m);
        }
    }

    public static f f() {
        f[] fVarArr;
        int length;
        mq1 mq1Var = n;
        int iA = pq1.a();
        if (mq1Var == null || (fVarArr = mq1Var.g) == null || (length = fVarArr.length) <= 0) {
            return null;
        }
        return fVarArr[iA & (length - 1) & 126];
    }

    public static int g() {
        nq1 nq1Var;
        mq1 mq1Var;
        f fVar;
        Thread threadCurrentThread = Thread.currentThread();
        int i = 0;
        if (!(threadCurrentThread instanceof nq1) || (mq1Var = (nq1Var = (nq1) threadCurrentThread).f8555a) == null || (fVar = nq1Var.b) == null) {
            return 0;
        }
        int i2 = mq1Var.f & 65535;
        int i3 = ((int) (mq1Var.f8416a >> 48)) + i2;
        int i4 = fVar.g - fVar.f;
        int i5 = i2 >>> 1;
        if (i3 <= i5) {
            int i6 = i5 >>> 1;
            if (i3 > i6) {
                i = 1;
            } else {
                int i7 = i6 >>> 1;
                i = i3 > i7 ? 2 : i3 > (i7 >>> 1) ? 4 : 8;
            }
        }
        return i4 - i;
    }

    public static final synchronized int h() {
        int i;
        i = p + 1;
        p = i;
        return i;
    }

    public static void i() {
        n.a(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this == n) {
            a(j, timeUnit);
            return false;
        }
        long nanos = timeUnit.toNanos(j);
        if (isTerminated()) {
            return true;
        }
        if (nanos <= 0) {
            return false;
        }
        long jNanoTime = System.nanoTime() + nanos;
        synchronized (this) {
            while (!isTerminated()) {
                if (nanos <= 0) {
                    return false;
                }
                long millis = TimeUnit.NANOSECONDS.toMillis(nanos);
                if (millis <= 0) {
                    millis = 1;
                }
                wait(millis);
                nanos = jNanoTime - System.nanoTime();
            }
            return true;
        }
    }

    public final ForkJoinTask<?> b(f fVar) {
        if (fVar != null) {
            ForkJoinTask<?> forkJoinTaskF = (fVar.d & 65536) != 0 ? fVar.f() : fVar.g();
            if (forkJoinTaskF != null) {
                return forkJoinTaskF;
            }
        }
        return a(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bd, code lost:
    
        r10 = r24.f8423a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c9, code lost:
    
        if (r10 < 0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00cb, code lost:
    
        r15 = (r10 + 65536) | Integer.MIN_VALUE;
        r24.f8423a = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d4, code lost:
    
        r4 = r23.f8416a;
        r24.b = (int) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ec, code lost:
    
        if (supwisdom.mq1.q.compareAndSwapLong(r23, supwisdom.mq1.r, r4, ((r4 - 281474976710656L) & (-4294967296L)) | (((long) r15) & 4294967295L)) == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ef, code lost:
    
        r15 = r24.b;
        r24.f8424e = java9.util.concurrent.ForkJoinTask.CANCELLED;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f8, code lost:
    
        if (r24.f8423a < 0) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00fa, code lost:
    
        r24.f8424e = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0101, code lost:
    
        r0 = r23.f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0103, code lost:
    
        if (r0 >= 0) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0105, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0106, code lost:
    
        r4 = r23.f8416a;
        r1 = (65535 & r0) + ((int) (r4 >> 48));
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0112, code lost:
    
        if (r1 > 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0117, code lost:
    
        if ((r0 & 262144) == 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x011e, code lost:
    
        if (a(false, false) == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0120, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0122, code lost:
    
        r17 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0128, code lost:
    
        if ((r17 & 1) != 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x012a, code lost:
    
        java.lang.Thread.interrupted();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0130, code lost:
    
        if (r1 > 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0132, code lost:
    
        if (r15 == 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0135, code lost:
    
        if (r10 != ((int) r4)) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0137, code lost:
    
        r0 = r23.c + java.lang.System.currentTimeMillis();
        java.util.concurrent.locks.LockSupport.parkUntil(r23, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0145, code lost:
    
        if (r23.f8416a != r4) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0150, code lost:
    
        if ((r0 - java.lang.System.currentTimeMillis()) > 20) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x016c, code lost:
    
        if (supwisdom.mq1.q.compareAndSwapLong(r23, supwisdom.mq1.r, r4, ((r4 - 4294967296L) & (-4294967296L)) | (((long) r15) & 4294967295L)) == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x016e, code lost:
    
        r24.f8423a = 1073741824;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0172, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0173, code lost:
    
        java.util.concurrent.locks.LockSupport.park(r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0178, code lost:
    
        r6 = r17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(supwisdom.mq1.f r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.c(supwisdom.mq1$f):void");
    }

    public final void d() throws Throwable {
        int i;
        f fVar;
        while (true) {
            long j = this.f8416a;
            if (j >= 0) {
                return;
            }
            int i2 = (int) j;
            if (i2 == 0) {
                if ((140737488355328L & j) != 0) {
                    a(j);
                    return;
                }
                return;
            }
            f[] fVarArr = this.g;
            if (fVarArr == null || fVarArr.length <= (i = 65535 & i2) || (fVar = fVarArr[i]) == null) {
                return;
            }
            int i3 = i2 & Integer.MAX_VALUE;
            int i4 = fVar.f8423a;
            long j2 = (((long) fVar.b) & 4294967295L) | ((-4294967296L) & (281474976710656L + j));
            nq1 nq1Var = fVar.j;
            if (i2 == i4 && q.compareAndSwapLong(this, r, j, j2)) {
                fVar.f8423a = i3;
                if (fVar.f8424e < 0) {
                    LockSupport.unpark(nq1Var);
                    return;
                }
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) throws Throwable {
        jq1.a(runnable);
        b(runnable instanceof ForkJoinTask ? (ForkJoinTask) runnable : new ForkJoinTask.e(runnable));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        try {
            Iterator<? extends Callable<T>> it = collection.iterator();
            while (it.hasNext()) {
                ForkJoinTask.a aVar = new ForkJoinTask.a(it.next());
                arrayList.add(aVar);
                b(aVar);
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((ForkJoinTask) arrayList.get(i)).quietlyJoin();
            }
            return arrayList;
        } catch (Throwable th) {
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((Future) arrayList.get(i2)).cancel(false);
            }
            throw th;
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return (this.f & 262144) != 0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return (this.f & 524288) != 0;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t2) {
        return new ForkJoinTask.b(runnable, t2);
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        e();
        a(false, true);
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        e();
        a(true, true);
        return Collections.emptyList();
    }

    public String toString() {
        int i;
        long j = this.b;
        f[] fVarArr = this.g;
        long j2 = 0;
        long j3 = 0;
        if (fVarArr != null) {
            i = 0;
            for (int i2 = 0; i2 < fVarArr.length; i2++) {
                f fVar = fVarArr[i2];
                if (fVar != null) {
                    int iH = fVar.h();
                    if ((i2 & 1) == 0) {
                        j3 += (long) iH;
                    } else {
                        j2 += (long) iH;
                        j += ((long) fVar.c) & 4294967295L;
                        if (fVar.c()) {
                            i++;
                        }
                    }
                }
            }
        } else {
            i = 0;
        }
        int i3 = this.f;
        int i4 = 65535 & i3;
        int i5 = ((short) (r10 >>> 32)) + i4;
        int i6 = ((int) (this.f8416a >> 48)) + i4;
        int i7 = i6 >= 0 ? i6 : 0;
        return super.toString() + Operators.ARRAY_START_STR + ((524288 & i3) != 0 ? "Terminated" : (Integer.MIN_VALUE & i3) != 0 ? "Terminating" : (i3 & 262144) != 0 ? "Shutting down" : "Running") + ", parallelism = " + i4 + ", size = " + i5 + ", active = " + i7 + ", running = " + i + ", steals = " + j + ", tasks = " + j2 + ", submissions = " + j3 + Operators.ARRAY_END_STR;
    }

    public mq1() {
        this(Math.min(32767, Runtime.getRuntime().availableProcessors()), l, null, false, 0, 32767, 1, null, 60000L, TimeUnit.MILLISECONDS);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new ForkJoinTask.a(callable);
    }

    public mq1(int i, c cVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z, int i2, int i3, int i4, rq1<? super mq1> rq1Var, long j, TimeUnit timeUnit) {
        if (i > 0 && i <= 32767 && i3 >= i && j > 0) {
            jq1.a(cVar);
            long jMax = Math.max(timeUnit.toMillis(j), 20L);
            long j2 = ((((long) (-Math.min(Math.max(i2, i), 32767))) << 32) & 281470681743360L) | ((((long) (-i)) << 48) & (-281474976710656L));
            int i5 = (z ? 65536 : 0) | i;
            int iMin = ((Math.min(i3, 32767) - i) << 16) | ((Math.min(Math.max(i4, 0), 32767) - i) & 65535);
            int i6 = i > 1 ? i - 1 : 1;
            int i7 = i6 | (i6 >>> 1);
            int i8 = i7 | (i7 >>> 2);
            int i9 = i8 | (i8 >>> 4);
            int i10 = i9 | (i9 >>> 8);
            this.h = "ForkJoinPool-" + h() + "-worker-";
            this.g = new f[((i10 | (i10 >>> 16)) + 1) << 1];
            this.i = cVar;
            this.j = uncaughtExceptionHandler;
            this.k = rq1Var;
            this.c = jMax;
            this.f8417e = iMin;
            this.f = i5;
            this.f8416a = j2;
            e();
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <T> java9.util.concurrent.ForkJoinTask<T> b(java9.util.concurrent.ForkJoinTask<T> r3) throws java.lang.Throwable {
        /*
            r2 = this;
            supwisdom.jq1.a(r3)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof supwisdom.nq1
            if (r1 == 0) goto L19
            supwisdom.nq1 r0 = (supwisdom.nq1) r0
            supwisdom.mq1 r1 = r0.f8555a
            if (r1 != r2) goto L19
            supwisdom.mq1$f r0 = r0.b
            if (r0 == 0) goto L19
            r0.a(r3)
            goto L1c
        L19:
            r2.a(r3)
        L1c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.b(java9.util.concurrent.ForkJoinTask):java9.util.concurrent.ForkJoinTask");
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ForkJoinTask<T> submit(Callable<T> callable) throws Throwable {
        ForkJoinTask.a aVar = new ForkJoinTask.a(callable);
        b(aVar);
        return aVar;
    }

    public static long a(Object obj, long j, long j2) {
        long longVolatile;
        do {
            longVolatile = q.getLongVolatile(obj, j);
        } while (!q.compareAndSwapLong(obj, j, longVolatile, longVolatile + j2));
        return longVolatile;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ForkJoinTask<T> submit(Runnable runnable, T t2) throws Throwable {
        ForkJoinTask.b bVar = new ForkJoinTask.b(runnable, t2);
        b(bVar);
        return bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public ForkJoinTask<?> submit(Runnable runnable) throws Throwable {
        jq1.a(runnable);
        ForkJoinTask<?> cVar = runnable instanceof ForkJoinTask ? (ForkJoinTask) runnable : new ForkJoinTask.c(runnable);
        b(cVar);
        return cVar;
    }

    public static Object a(Object obj, long j, Object obj2) {
        Object objectVolatile;
        do {
            objectVolatile = q.getObjectVolatile(obj, j);
        } while (!q.compareAndSwapObject(obj, j, objectVolatile, obj2));
        return objectVolatile;
    }

    public boolean b() {
        while (true) {
            long j = this.f8416a;
            int i = this.f;
            int i2 = 65535 & i;
            int i3 = ((short) (j >>> 32)) + i2;
            int i4 = i2 + ((int) (j >> 48));
            if ((i & (-2146959360)) != 0) {
                return true;
            }
            if (i4 > 0) {
                return false;
            }
            f[] fVarArr = this.g;
            if (fVarArr != null) {
                for (int i5 = 1; i5 < fVarArr.length; i5 += 2) {
                    f fVar = fVarArr[i5];
                    if (fVar != null) {
                        if ((fVar.f8424e & 1073741824) == 0) {
                            return false;
                        }
                        i3--;
                    }
                }
            }
            if (i3 == 0 && this.f8416a == j) {
                return true;
            }
        }
    }

    public final boolean a() throws Throwable {
        nq1 nq1VarA;
        c cVar = this.i;
        Throwable th = null;
        if (cVar != null) {
            try {
                nq1VarA = cVar.a(this);
                if (nq1VarA != null) {
                    try {
                        nq1VarA.start();
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                nq1VarA = null;
            }
        } else {
            nq1VarA = null;
        }
        a(nq1VarA, th);
        return false;
    }

    public final void a(long j) throws Throwable {
        long j2 = j;
        do {
            long j3 = ((-281474976710656L) & (281474976710656L + j2)) | (281470681743360L & (4294967296L + j2));
            if (this.f8416a == j2 && q.compareAndSwapLong(this, r, j2, j3)) {
                a();
                return;
            } else {
                j2 = this.f8416a;
                if ((140737488355328L & j2) == 0) {
                    return;
                }
            }
        } while (((int) j2) == 0);
    }

    public final int d(f fVar) throws Throwable {
        int length;
        boolean z;
        Thread.State state;
        long j = this.f8416a;
        f[] fVarArr = this.g;
        short s2 = (short) (j >>> 32);
        if (s2 >= 0) {
            if (fVarArr == null || (length = fVarArr.length) <= 0 || fVar == null) {
                return 0;
            }
            int i = (int) j;
            if (i != 0) {
                f fVar2 = fVarArr[i & (length - 1)];
                int i2 = fVar.f8423a;
                long j2 = (-4294967296L) & (i2 < 0 ? 281474976710656L + j : j);
                int i3 = i & Integer.MAX_VALUE;
                if (fVar2 == null) {
                    return 0;
                }
                int i4 = fVar2.f8423a;
                nq1 nq1Var = fVar2.j;
                long j3 = (((long) fVar2.b) & 4294967295L) | j2;
                if (i4 != i || !q.compareAndSwapLong(this, r, j, j3)) {
                    return 0;
                }
                fVar2.f8423a = i3;
                if (fVar2.f8424e < 0) {
                    LockSupport.unpark(nq1Var);
                }
                return i2 < 0 ? -1 : 1;
            }
            if (((int) (j >> 48)) - ((short) (this.f8417e & 65535)) > 0) {
                return q.compareAndSwapLong(this, r, j, ((-281474976710656L) & (j - 281474976710656L)) | (281474976710655L & j)) ? 1 : 0;
            }
            int i5 = this.f & 65535;
            int i6 = i5 + s2;
            int i7 = i6;
            int i8 = 0;
            int i9 = 1;
            while (true) {
                if (i9 >= length) {
                    z = false;
                    break;
                }
                f fVar3 = fVarArr[i9];
                if (fVar3 != null) {
                    if (fVar3.f8424e == 0) {
                        z = true;
                        break;
                    }
                    i7--;
                    nq1 nq1Var2 = fVar3.j;
                    if (nq1Var2 != null && ((state = nq1Var2.getState()) == Thread.State.BLOCKED || state == Thread.State.WAITING)) {
                        i8++;
                    }
                }
                i9 += 2;
            }
            if (z || i7 != 0 || this.f8416a != j) {
                return 0;
            }
            if (i6 >= 32767 || s2 >= (this.f8417e >>> 16)) {
                rq1<? super mq1> rq1Var = this.k;
                if (rq1Var != null && rq1Var.a(this)) {
                    return -1;
                }
                if (i8 < i5) {
                    Thread.yield();
                    return 0;
                }
                throw new RejectedExecutionException("Thread limit exceeded replacing blocked worker");
            }
        }
        return (q.compareAndSwapLong(this, r, j, ((4294967296L + j) & 281470681743360L) | ((-281470681743361L) & j)) && a()) ? 1 : 0;
    }

    public final f a(nq1 nq1Var) {
        int i;
        int length;
        nq1Var.setDaemon(true);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.j;
        if (uncaughtExceptionHandler != null) {
            nq1Var.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        f fVar = new f(this, nq1Var);
        int i2 = this.f & 65536;
        String str = this.h;
        if (str != null) {
            synchronized (str) {
                f[] fVarArr = this.g;
                int i3 = this.d - 1640531527;
                this.d = i3;
                i = 0;
                if (fVarArr != null && (length = fVarArr.length) > 1) {
                    int i4 = length - 1;
                    int i5 = i3 & i4;
                    int i6 = ((i3 << 1) | 1) & i4;
                    int i7 = length >>> 1;
                    while (true) {
                        f fVar2 = fVarArr[i6];
                        if (fVar2 == null || fVar2.f8423a == 1073741824) {
                            break;
                        }
                        i7--;
                        if (i7 == 0) {
                            i6 = length | 1;
                            break;
                        }
                        i6 = (i6 + 2) & i4;
                    }
                    int i8 = i2 | i6 | (i3 & 1073610752);
                    fVar.d = i8;
                    fVar.f8423a = i8;
                    if (i6 < length) {
                        fVarArr[i6] = fVar;
                    } else {
                        int i9 = length << 1;
                        f[] fVarArr2 = new f[i9];
                        fVarArr2[i6] = fVar;
                        int i10 = i9 - 1;
                        while (i < length) {
                            f fVar3 = fVarArr[i];
                            if (fVar3 != null) {
                                fVarArr2[fVar3.d & i10 & 126] = fVar3;
                            }
                            int i11 = i + 1;
                            if (i11 >= length) {
                                break;
                            }
                            fVarArr2[i11] = fVarArr[i11];
                            i = i11 + 1;
                        }
                        this.g = fVarArr2;
                    }
                    i = i5;
                }
            }
            nq1Var.setName(str.concat(Integer.toString(i)));
        }
        return fVar;
    }

    public mq1(byte b2) {
        c dVar;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        a aVar = null;
        try {
            String property = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
            iAvailableProcessors = property != null ? Integer.parseInt(property) : -1;
            dVar = (c) a("java.util.concurrent.ForkJoinPool.common.threadFactory");
        } catch (Exception unused) {
            dVar = null;
        }
        try {
            uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) a("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
        } catch (Exception unused2) {
            uncaughtExceptionHandler = null;
        }
        if (dVar == null) {
            if (System.getSecurityManager() == null) {
                dVar = l;
            } else {
                dVar = new d(aVar);
            }
        }
        if (iAvailableProcessors < 0 && (iAvailableProcessors = Runtime.getRuntime().availableProcessors() - 1) <= 0) {
            iAvailableProcessors = 1;
        }
        iAvailableProcessors = iAvailableProcessors > 32767 ? 32767 : iAvailableProcessors;
        long j = -iAvailableProcessors;
        long j2 = ((j << 48) & (-281474976710656L)) | ((j << 32) & 281470681743360L);
        int i = ((1 - iAvailableProcessors) & 65535) | (o << 16);
        int i2 = iAvailableProcessors > 1 ? iAvailableProcessors - 1 : 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        this.h = "ForkJoinPool.commonPool-worker-";
        this.g = new f[((i6 | (i6 >>> 16)) + 1) << 1];
        this.i = dVar;
        this.j = uncaughtExceptionHandler;
        this.k = null;
        this.c = 60000L;
        this.f8417e = i;
        this.f = iAvailableProcessors;
        this.f8416a = j2;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0040 A[LOOP:0: B:27:0x0040->B:44:?, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(supwisdom.nq1 r18, java.lang.Throwable r19) throws java.lang.Throwable {
        /*
            r17 = this;
            r9 = r17
            r0 = r18
            r10 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r1 = 0
            r12 = 0
            if (r0 == 0) goto L3a
            supwisdom.mq1$f r0 = r0.b
            if (r0 == 0) goto L3b
            java.lang.String r2 = r9.h
            int r3 = r0.c
            long r3 = (long) r3
            long r3 = r3 & r10
            int r5 = r0.d
            r6 = 65535(0xffff, float:9.1834E-41)
            r5 = r5 & r6
            if (r2 == 0) goto L37
            monitor-enter(r2)
            supwisdom.mq1$f[] r6 = r9.g     // Catch: java.lang.Throwable -> L34
            if (r6 == 0) goto L2d
            int r7 = r6.length     // Catch: java.lang.Throwable -> L34
            if (r7 <= r5) goto L2d
            r7 = r6[r5]     // Catch: java.lang.Throwable -> L34
            if (r7 != r0) goto L2d
            r6[r5] = r1     // Catch: java.lang.Throwable -> L34
        L2d:
            long r5 = r9.b     // Catch: java.lang.Throwable -> L34
            long r5 = r5 + r3
            r9.b = r5     // Catch: java.lang.Throwable -> L34
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L34
            goto L37
        L34:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L34
            throw r0
        L37:
            int r1 = r0.f8423a
            goto L3c
        L3a:
            r0 = r1
        L3b:
            r1 = 0
        L3c:
            r2 = 1073741824(0x40000000, float:2.0)
            if (r1 == r2) goto L66
        L40:
            sun.misc.Unsafe r1 = supwisdom.mq1.q
            long r3 = supwisdom.mq1.r
            long r5 = r9.f8416a
            r7 = -281474976710656(0xffff000000000000, double:NaN)
            r13 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            long r13 = r5 - r13
            long r7 = r7 & r13
            r13 = 281470681743360(0xffff00000000, double:1.39064994160909E-309)
            r15 = 4294967296(0x100000000, double:2.121995791E-314)
            long r15 = r5 - r15
            long r13 = r13 & r15
            long r7 = r7 | r13
            long r13 = r5 & r10
            long r7 = r7 | r13
            r2 = r17
            boolean r1 = r1.compareAndSwapLong(r2, r3, r5, r7)
            if (r1 == 0) goto L40
        L66:
            if (r0 == 0) goto L6b
            r0.a()
        L6b:
            boolean r1 = r9.a(r12, r12)
            if (r1 != 0) goto L7a
            if (r0 == 0) goto L7a
            java9.util.concurrent.ForkJoinTask<?>[] r0 = r0.h
            if (r0 == 0) goto L7a
            r17.d()
        L7a:
            if (r19 != 0) goto L80
            java9.util.concurrent.ForkJoinTask.helpExpungeStaleExceptions()
            goto L83
        L80:
            java9.util.concurrent.ForkJoinTask.rethrow(r19)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.a(supwisdom.nq1, java.lang.Throwable):void");
    }

    public final boolean c(ForkJoinTask<?> forkJoinTask) {
        int length;
        f fVar;
        int iA = pq1.a();
        f[] fVarArr = this.g;
        return fVarArr != null && (length = fVarArr.length) > 0 && (fVar = fVarArr[(iA & (length - 1)) & 126]) != null && fVar.c(forkJoinTask);
    }

    public ForkJoinTask<?> c() {
        return a(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d1 A[EDGE_INSN: B:64:0x00d1->B:61:0x00d1 BREAK  A[LOOP:0: B:12:0x0025->B:67:0x0025], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(supwisdom.mq1.f r22, java9.util.concurrent.ForkJoinTask<?> r23, long r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.a(supwisdom.mq1$f, java9.util.concurrent.ForkJoinTask, long):int");
    }

    public final void a(f fVar) {
        boolean z;
        boolean z2;
        int i;
        byte b2;
        f fVar2;
        ForkJoinTask<?>[] forkJoinTaskArr;
        int length;
        int i2 = fVar.f8424e;
        int i3 = fVar.d & 65536;
        byte b3 = -1;
        int i4 = i2;
        byte b4 = -1;
        while (true) {
            if (i3 != 0) {
                fVar.a(0);
            } else {
                fVar.b(0);
            }
            if (b4 == b3 && fVar.f8423a >= 0) {
                b4 = 1;
            }
            int iH = pq1.h();
            f[] fVarArr = this.g;
            if (fVarArr != null) {
                int length2 = fVarArr.length;
                int i5 = length2 - 1;
                int i6 = length2;
                z2 = true;
                while (true) {
                    if (i6 <= 0) {
                        z = true;
                        break;
                    }
                    int i7 = (iH - i6) & i5;
                    if (i7 >= 0 && i7 < length2 && (fVar2 = fVarArr[i7]) != null) {
                        int i8 = fVar2.f;
                        if (i8 - fVar2.g < 0 && (forkJoinTaskArr = fVar2.h) != null && (length = forkJoinTaskArr.length) > 0) {
                            int i9 = fVar2.d;
                            if (b4 == 0) {
                                a(this, r, 281474976710656L);
                                b4 = 1;
                            }
                            long j = (((long) ((length - 1) & i8)) << u) + ((long) t);
                            ForkJoinTask forkJoinTask = (ForkJoinTask) q.getObjectVolatile(forkJoinTaskArr, j);
                            if (forkJoinTask != null) {
                                int i10 = i8 + 1;
                                if (i8 == fVar2.f && q.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, (Object) null)) {
                                    fVar2.f = i10;
                                    fVar.f8424e = fVar2.d;
                                    forkJoinTask.doExec();
                                    fVar.f8424e = i2;
                                    i4 = i2;
                                }
                            }
                            z = false;
                            z2 = false;
                        } else if ((fVar2.f8424e & 1073741824) == 0) {
                            z2 = false;
                        }
                    }
                    i6--;
                }
            } else {
                z = true;
                z2 = true;
            }
            if (z2) {
                break;
            }
            if (z) {
                if (i4 != 1073741824) {
                    fVar.f8424e = 1073741824;
                    b2 = 1;
                    i = 1073741824;
                } else {
                    i = i4;
                    b2 = 1;
                }
                if (b4 == b2) {
                    a(this, r, -281474976710656L);
                    i4 = i;
                    b4 = 0;
                } else {
                    i4 = i;
                }
            }
            b3 = -1;
        }
        if (b4 == 0) {
            a(this, r, 281474976710656L);
        }
        fVar.f8424e = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        r14 = (((long) ((r11 - 1) & r10)) << supwisdom.mq1.u) + ((long) supwisdom.mq1.t);
        r1 = (java9.util.concurrent.ForkJoinTask) supwisdom.mq1.q.getObjectVolatile(r13, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
    
        if (r1 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
    
        r2 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        if (r10 != r9.f) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0064, code lost:
    
        if (supwisdom.mq1.q.compareAndSwapObject(r13, r14, r1, (java.lang.Object) null) == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0066, code lost:
    
        r9.f = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0068, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0002, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java9.util.concurrent.ForkJoinTask<?> a(boolean r19) {
        /*
            r18 = this;
            r0 = r18
        L2:
            int r1 = r0.f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 != 0) goto L73
            supwisdom.mq1$f[] r1 = r0.g
            if (r1 == 0) goto L73
            int r2 = r1.length
            if (r2 <= 0) goto L73
            int r2 = r2 + (-1)
            int r3 = supwisdom.pq1.h()
            int r4 = r3 >>> 16
            if (r19 == 0) goto L22
            r3 = r3 & (-2)
            r3 = r3 & r2
            r4 = r4 & (-2)
            r4 = r4 | 2
            goto L25
        L22:
            r3 = r3 & r2
            r4 = r4 | 1
        L25:
            r5 = 0
            r6 = r3
            r7 = 0
            r8 = 0
        L29:
            r9 = r1[r6]
            if (r9 == 0) goto L69
            int r10 = r9.f
            int r7 = r7 + r10
            int r11 = r9.g
            int r11 = r10 - r11
            if (r11 >= 0) goto L69
            java9.util.concurrent.ForkJoinTask<?>[] r13 = r9.h
            if (r13 == 0) goto L69
            int r11 = r13.length
            if (r11 <= 0) goto L69
            int r11 = r11 + (-1)
            r1 = r11 & r10
            long r1 = (long) r1
            int r3 = supwisdom.mq1.u
            long r1 = r1 << r3
            int r3 = supwisdom.mq1.t
            long r3 = (long) r3
            long r14 = r1 + r3
            sun.misc.Unsafe r1 = supwisdom.mq1.q
            java.lang.Object r1 = r1.getObjectVolatile(r13, r14)
            java9.util.concurrent.ForkJoinTask r1 = (java9.util.concurrent.ForkJoinTask) r1
            if (r1 == 0) goto L2
            int r2 = r10 + 1
            int r3 = r9.f
            if (r10 != r3) goto L2
            sun.misc.Unsafe r12 = supwisdom.mq1.q
            r17 = 0
            r16 = r1
            boolean r3 = r12.compareAndSwapObject(r13, r14, r16, r17)
            if (r3 == 0) goto L2
            r9.f = r2
            return r1
        L69:
            int r6 = r6 + r4
            r6 = r6 & r2
            if (r6 != r3) goto L29
            if (r8 != r7) goto L70
            goto L73
        L70:
            r8 = r7
            r7 = 0
            goto L29
        L73:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.a(boolean):java9.util.concurrent.ForkJoinTask");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00bd, code lost:
    
        throw new java.util.concurrent.RejectedExecutionException();
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java9.util.concurrent.ForkJoinTask<?> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            int r0 = supwisdom.pq1.a()
            if (r0 != 0) goto Ld
            supwisdom.pq1.g()
            int r0 = supwisdom.pq1.a()
        Ld:
            int r1 = r9.f
            supwisdom.mq1$f[] r2 = r9.g
            r3 = 262144(0x40000, float:3.67342E-40)
            r1 = r1 & r3
            if (r1 != 0) goto Lb8
            if (r2 == 0) goto Lb8
            int r1 = r2.length
            if (r1 <= 0) goto Lb8
            int r1 = r1 + (-1)
            r1 = r1 & r0
            r1 = r1 & 126(0x7e, float:1.77E-43)
            r1 = r2[r1]
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L5f
            java.lang.String r1 = r9.h
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = r0 | r4
            r6 = -65538(0xfffffffffffefffe, float:NaN)
            r5 = r5 & r6
            supwisdom.mq1$f r6 = new supwisdom.mq1$f
            r7 = 0
            r6.<init>(r9, r7)
            r6.d = r5
            r6.f8424e = r4
            r6.f8423a = r3
            if (r1 == 0) goto L5b
            monitor-enter(r1)
            supwisdom.mq1$f[] r4 = r9.g     // Catch: java.lang.Throwable -> L58
            if (r4 == 0) goto L54
            int r7 = r4.length     // Catch: java.lang.Throwable -> L58
            if (r7 <= 0) goto L54
            int r7 = r7 + (-1)
            r5 = r5 & r7
            r5 = r5 & 126(0x7e, float:1.77E-43)
            r7 = r4[r5]     // Catch: java.lang.Throwable -> L58
            if (r7 != 0) goto L54
            r4[r5] = r6     // Catch: java.lang.Throwable -> L58
            r4 = 1
            r5 = 1
            goto L56
        L54:
            r4 = 0
            r5 = 0
        L56:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L58
            goto L5d
        L58:
            r10 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L58
            throw r10
        L5b:
            r4 = 0
            r5 = 0
        L5d:
            r1 = r6
            goto L90
        L5f:
            boolean r4 = r1.i()
            if (r4 == 0) goto L8e
            int r4 = r1.f
            int r5 = r1.g
            java9.util.concurrent.ForkJoinTask<?>[] r6 = r1.h
            if (r6 == 0) goto L8b
            int r7 = r6.length
            if (r7 <= 0) goto L8b
            int r7 = r7 + (-1)
            int r4 = r4 - r5
            int r8 = r7 + r4
            if (r8 <= 0) goto L8b
            r7 = r7 & r5
            r6[r7] = r10
            int r6 = r5 + 1
            r1.g = r6
            r1.f8423a = r2
            if (r4 >= 0) goto L89
            int r4 = r1.f
            int r4 = r4 - r5
            r5 = -1
            if (r4 >= r5) goto L89
            goto Lb1
        L89:
            r5 = 0
            goto L8c
        L8b:
            r5 = 1
        L8c:
            r4 = 1
            goto L90
        L8e:
            r4 = 0
            r5 = 0
        L90:
            if (r4 == 0) goto Lb2
            if (r5 == 0) goto Lae
            r1.b()     // Catch: java.lang.Throwable -> Laa
            int r0 = r1.g     // Catch: java.lang.Throwable -> Laa
            java9.util.concurrent.ForkJoinTask<?>[] r4 = r1.h     // Catch: java.lang.Throwable -> Laa
            if (r4 == 0) goto La7
            int r5 = r4.length     // Catch: java.lang.Throwable -> Laa
            if (r5 <= 0) goto La7
            int r5 = r5 - r3
            r5 = r5 & r0
            r4[r5] = r10     // Catch: java.lang.Throwable -> Laa
            int r0 = r0 + r3
            r1.g = r0     // Catch: java.lang.Throwable -> Laa
        La7:
            r1.f8423a = r2
            goto Lae
        Laa:
            r10 = move-exception
            r1.f8423a = r2
            throw r10
        Lae:
            r9.d()
        Lb1:
            return
        Lb2:
            int r0 = supwisdom.pq1.a(r0)
            goto Ld
        Lb8:
            java.util.concurrent.RejectedExecutionException r10 = new java.util.concurrent.RejectedExecutionException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq1.a(java9.util.concurrent.ForkJoinTask):void");
    }

    public final int a(CountedCompleter<?> countedCompleter, int i) {
        int length;
        f fVar;
        int iA = pq1.a();
        f[] fVarArr = this.g;
        if (fVarArr == null || (length = fVarArr.length) <= 0 || (fVar = fVarArr[iA & (length - 1) & 126]) == null) {
            return 0;
        }
        return fVar.b(countedCompleter, i);
    }

    public final int a(f fVar, CountedCompleter<?> countedCompleter, int i) {
        if (fVar == null) {
            return 0;
        }
        return fVar.a(countedCompleter, i);
    }

    public final boolean a(boolean z, boolean z2) {
        int i;
        int i2;
        while (true) {
            int i3 = this.f;
            if ((i3 & 262144) != 0) {
                while (true) {
                    int i4 = this.f;
                    int i5 = 65535;
                    long j = 0;
                    int i6 = 1;
                    if ((i4 & Integer.MIN_VALUE) != 0) {
                        while ((this.f & 524288) == 0) {
                            long j2 = 0;
                            while (true) {
                                long j3 = this.f8416a;
                                f[] fVarArr = this.g;
                                if (fVarArr != null) {
                                    for (f fVar : fVarArr) {
                                        if (fVar != null) {
                                            nq1 nq1Var = fVar.j;
                                            fVar.a();
                                            if (nq1Var != null) {
                                                try {
                                                    nq1Var.interrupt();
                                                } catch (Throwable unused) {
                                                }
                                            }
                                            j3 += (((long) fVar.f8423a) << 32) + ((long) fVar.f);
                                        }
                                    }
                                }
                                i = this.f;
                                i2 = i & 524288;
                                if (i2 != 0) {
                                    break;
                                }
                                if (this.g == fVarArr) {
                                    if (j2 == j3) {
                                        break;
                                    }
                                    j2 = j3;
                                }
                            }
                            if (i2 != 0 || (i & 65535) + ((short) (this.f8416a >>> 32)) > 0) {
                                return true;
                            }
                            if (q.compareAndSwapInt(this, s, i, i | 524288)) {
                                synchronized (this) {
                                    notifyAll();
                                }
                                return true;
                            }
                        }
                        return true;
                    }
                    if (!z) {
                        while (true) {
                            long j4 = this.f8416a;
                            f[] fVarArr2 = this.g;
                            char c2 = '0';
                            if ((i4 & i5) + ((int) (j4 >> 48)) <= 0) {
                                if (fVarArr2 != null) {
                                    int i7 = 0;
                                    while (i7 < fVarArr2.length) {
                                        f fVar2 = fVarArr2[i7];
                                        if (fVar2 != null) {
                                            int i8 = fVar2.f8424e;
                                            int i9 = fVar2.f8423a;
                                            int i10 = fVar2.d;
                                            int i11 = fVar2.f;
                                            if (i11 != fVar2.g || ((i10 & 1) == i6 && (i8 >= 0 || i9 >= 0))) {
                                                i6 = 1;
                                                break;
                                            }
                                            j4 += (((long) i8) << c2) + (((long) i9) << 32) + (((long) i11) << 16) + ((long) i10);
                                        }
                                        i7++;
                                        c2 = '0';
                                        i6 = 1;
                                    }
                                    i6 = 0;
                                } else {
                                    i6 = 0;
                                }
                            }
                            i4 = this.f;
                            if ((i4 & Integer.MIN_VALUE) != 0) {
                                break;
                            }
                            if (i6 != 0) {
                                return false;
                            }
                            if (this.g == fVarArr2) {
                                if (j == j4) {
                                    break;
                                }
                                j = j4;
                            }
                            i5 = 65535;
                            i6 = 1;
                        }
                    }
                    int i12 = i4;
                    if ((i12 & Integer.MIN_VALUE) == 0) {
                        q.compareAndSwapInt(this, s, i12, i12 | Integer.MIN_VALUE);
                    }
                }
            } else {
                if (!z2 || this == n) {
                    break;
                }
                q.compareAndSwapInt(this, s, i3, i3 | 262144);
            }
        }
        return false;
    }

    public static Object a(String str) throws Exception {
        String property = System.getProperty(str);
        if (property == null) {
            return null;
        }
        return ClassLoader.getSystemClassLoader().loadClass(property).getConstructor(new Class[0]).newInstance(new Object[0]);
    }

    public boolean a(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof nq1) {
            nq1 nq1Var = (nq1) threadCurrentThread;
            if (nq1Var.f8555a == this) {
                a(nq1Var.b);
                return true;
            }
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            ForkJoinTask<?> forkJoinTaskA = a(false);
            if (forkJoinTaskA != null) {
                forkJoinTaskA.doExec();
            } else {
                if (b()) {
                    return true;
                }
                if (System.nanoTime() - jNanoTime > nanos) {
                    return false;
                }
                Thread.yield();
            }
        }
    }
}
