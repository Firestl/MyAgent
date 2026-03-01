package com.igexin.c.a.d;

import android.os.PowerManager;
import com.igexin.c.a.d.a.d;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f extends b implements com.igexin.c.a.d.a.a, com.igexin.c.a.d.a.f {
    public static g H;
    public int A;
    public int B;
    public int C;
    public int D;
    public Exception E;
    public Object F;
    public com.igexin.c.a.d.a.g G;
    public final ReentrantLock I;
    public final Condition J;
    public Thread K;
    public volatile boolean L;
    public PowerManager.WakeLock M;
    public int N;
    public com.igexin.c.a.d.a.d O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f3199a;
    public volatile boolean m;
    public volatile boolean n;
    public volatile boolean o;
    public volatile boolean p;
    public volatile boolean q;
    public volatile boolean r;
    public volatile boolean s;
    public volatile boolean t;
    public volatile boolean u;
    public volatile boolean v;
    public volatile long w;
    public volatile int x;
    public long z;

    public f(int i) {
        this(i, (byte) 0);
    }

    public f(int i, byte b) {
        this.C = i;
        this.O = null;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.I = reentrantLock;
        this.J = reentrantLock.newCondition();
    }

    private int A() {
        return this.f3199a & 15;
    }

    private boolean B() {
        byte b = this.f3199a;
        return (b >> 4) > (b & 15);
    }

    private Thread C() {
        return this.K;
    }

    public static void D() throws Exception {
    }

    private void E() {
        this.n = true;
    }

    private Object F() {
        return this.F;
    }

    private com.igexin.c.a.d.a.d G() {
        return this.O;
    }

    private void a(int i, TimeUnit timeUnit) {
        this.v = false;
        this.E = null;
        this.w = 0L;
        byte b = this.f3199a;
        this.f3199a = (byte) (b + ((b & 15) < 15 ? (byte) 1 : (byte) 0));
        this.m = false;
        this.q = false;
        this.t = false;
        a(i, timeUnit);
    }

    private void a(long j) {
        this.z = j;
    }

    private void a(PowerManager.WakeLock wakeLock) {
        this.M = wakeLock;
    }

    private boolean a(Object obj) {
        if (!this.m) {
            return false;
        }
        this.q = false;
        this.n = false;
        this.m = false;
        this.F = obj;
        return true;
    }

    private void b(int i) {
        if (i != this.D) {
            this.D = i;
            H.s.b(this);
        }
    }

    private void b(Object obj) {
        this.F = obj;
    }

    private ReentrantLock g() {
        ReentrantLock reentrantLock = this.I;
        if (reentrantLock != null) {
            return reentrantLock;
        }
        throw null;
    }

    private PowerManager.WakeLock h() {
        return this.M;
    }

    private void p() {
        this.z = System.currentTimeMillis();
    }

    private boolean q() {
        return this.v;
    }

    private int r() {
        long jA = a(TimeUnit.MILLISECONDS);
        int i = this.N;
        this.N = jA > 0 ? i | 134217728 : i & 1090519038;
        return this.N;
    }

    private void s() {
        int i = this.N + 1;
        this.N = i;
        this.N = i & 1090519038;
    }

    private long t() {
        return this.w - System.currentTimeMillis();
    }

    private boolean u() {
        return this.q;
    }

    private boolean v() {
        return this.u;
    }

    private boolean w() {
        return this.m;
    }

    private boolean x() {
        return this.s;
    }

    private boolean y() {
        return this.t;
    }

    private void z() {
        this.v = false;
        this.E = null;
        this.w = 0L;
        byte b = this.f3199a;
        this.f3199a = (byte) (b + ((b & 15) < 15 ? (byte) 1 : (byte) 0));
        this.m = false;
        this.q = false;
        this.t = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(long r7, java.util.concurrent.TimeUnit r9) {
        /*
            r6 = this;
            r0 = 1
            r1 = -1
            r2 = -2
            r3 = 0
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 <= 0) goto L29
            com.igexin.c.a.d.g r3 = com.igexin.c.a.d.f.H
            com.igexin.c.a.d.e<com.igexin.c.a.d.f> r3 = r3.s
            int r3 = r3.a(r6, r7, r9)
            if (r3 == r2) goto L27
            if (r3 == r1) goto L18
            if (r3 == r0) goto L2a
            goto L29
        L18:
            long r2 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r4 = r0.convert(r7, r9)
            long r2 = r2 + r4
            r6.w = r2
            r0 = -1
            goto L2a
        L27:
            r0 = -2
            goto L2a
        L29:
            r0 = 0
        L2a:
            java.lang.Class r1 = r6.getClass()
            r1.getSimpleName()
            r6.hashCode()
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS
            r1.convert(r7, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.c.a.d.f.a(long, java.util.concurrent.TimeUnit):int");
    }

    public final long a(TimeUnit timeUnit) {
        return timeUnit.convert(t(), TimeUnit.MILLISECONDS);
    }

    @Override // com.igexin.c.a.d.a.a
    public void a() {
        this.F = null;
        this.E = null;
        this.K = null;
    }

    public final void a(int i) {
        byte b = (byte) (this.f3199a & 15);
        this.f3199a = b;
        this.f3199a = (byte) (((i & 15) << 4) | b);
    }

    public final void a(int i, com.igexin.c.a.d.a.g gVar) {
        if (i < 0) {
            throw new IllegalArgumentException("second must > 0");
        }
        this.B = i;
        this.G = gVar;
    }

    public final void a(com.igexin.c.a.d.a.d dVar) {
        this.O = dVar;
    }

    public final void a(f fVar) {
        this.C = fVar.C;
        this.f3199a = (byte) (fVar.f3199a & 240);
        this.A = fVar.A;
        this.D = fVar.D;
        this.O = fVar.O;
        this.B = fVar.B;
        this.G = fVar.G;
    }

    public void b_() throws Exception {
        this.K = Thread.currentThread();
        this.q = true;
        getClass().getName();
        hashCode();
        this.K.getName();
    }

    public void d() {
        this.t = true;
    }

    @Override // com.igexin.c.a.d.a.f
    public void d_() {
        if (this.m || this.n) {
            a();
        }
    }

    public abstract void e();

    public abstract void f();

    public final void k() {
        this.m = true;
    }

    public final boolean l() {
        return this.o;
    }

    public final boolean m() {
        return this.n;
    }

    public final void n() {
        if (!this.p && !this.r && !this.s) {
            this.m = true;
            this.q = false;
        } else if (this.r && !this.m) {
            this.q = false;
        } else {
            if (!this.p || this.o || this.m) {
                return;
            }
            this.q = false;
        }
    }

    public final void o() {
        if (this.O != null) {
            int i = d.a.f3192a;
        }
    }
}
