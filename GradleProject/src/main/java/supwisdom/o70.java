package supwisdom;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;
import supwisdom.j50;
import supwisdom.m70;
import supwisdom.ta0;
import supwisdom.ua0;
import supwisdom.yb0;

/* JADX INFO: compiled from: ExoPlayerImplInternal.java */
/* JADX INFO: loaded from: classes.dex */
public final class o70 implements Handler.Callback, m70.a, ta0.a, ua0.a {
    public c A;
    public long B;
    public a C;
    public a D;
    public a E;
    public yb0 F;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h90[] f8635a;
    public final i90[] b;
    public final m70 c;
    public final f90 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t80 f8636e;
    public final Handler f;
    public final HandlerThread g;
    public final Handler h;
    public final j50 i;
    public final yb0.c j;
    public final yb0.b k;
    public b l;
    public g90 m;
    public h90 n;
    public k80 o;
    public ua0 p;
    public h90[] q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v = 1;
    public int w;
    public int x;
    public long y;
    public int z;

    /* JADX INFO: compiled from: ExoPlayerImplInternal.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ta0 f8637a;
        public final Object b;
        public final mb0[] c;
        public final boolean[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f8638e;
        public int f;
        public long g;
        public boolean h;
        public boolean i;
        public boolean j;
        public a k;
        public boolean l;
        public n70 m;
        public final h90[] n;
        public final i90[] o;
        public final m70 p;
        public final f90 q;
        public final ua0 r;
        public n70 s;

        public a(h90[] h90VarArr, i90[] i90VarArr, long j, m70 m70Var, f90 f90Var, ua0 ua0Var, Object obj, int i, boolean z, long j2) {
            this.n = h90VarArr;
            this.o = i90VarArr;
            this.f8638e = j;
            this.p = m70Var;
            this.q = f90Var;
            this.r = ua0Var;
            e80.a(obj);
            this.b = obj;
            this.f = i;
            this.h = z;
            this.g = j2;
            this.c = new mb0[h90VarArr.length];
            this.d = new boolean[h90VarArr.length];
            this.f8637a = ua0Var.a(i, f90Var.d(), j2);
        }

        public long a(long j) {
            return j + a();
        }

        public long b(long j) {
            return j - a();
        }

        public void c() throws com.google.android.exoplayer2.e {
            this.i = true;
            d();
            this.g = a(this.g, false);
        }

        public boolean d() throws com.google.android.exoplayer2.e {
            n70 n70VarA = this.p.a(this.o, this.f8637a.d());
            if (n70VarA.a(this.s)) {
                return false;
            }
            this.m = n70VarA;
            return true;
        }

        public void e() {
            try {
                this.r.a(this.f8637a);
            } catch (RuntimeException e2) {
                Log.e("ExoPlayerImplInternal", "Period release failed.", e2);
            }
        }

        public long a() {
            return this.f8638e - this.g;
        }

        public boolean b() {
            return this.i && (!this.j || this.f8637a.f() == Long.MIN_VALUE);
        }

        public void a(int i, boolean z) {
            this.f = i;
            this.h = z;
        }

        public long a(long j, boolean z) {
            return a(j, z, new boolean[this.n.length]);
        }

        public long a(long j, boolean z, boolean[] zArr) {
            l70 l70Var = this.m.b;
            int i = 0;
            while (true) {
                boolean z2 = true;
                if (i >= l70Var.f8261a) {
                    break;
                }
                boolean[] zArr2 = this.d;
                if (z || !this.m.a(this.s, i)) {
                    z2 = false;
                }
                zArr2[i] = z2;
                i++;
            }
            long jA = this.f8637a.a(l70Var.a(), this.d, this.c, zArr, j);
            this.s = this.m;
            this.j = false;
            int i2 = 0;
            while (true) {
                mb0[] mb0VarArr = this.c;
                if (i2 < mb0VarArr.length) {
                    if (mb0VarArr[i2] != null) {
                        e80.b(l70Var.a(i2) != null);
                        this.j = true;
                    } else {
                        e80.b(l70Var.a(i2) == null);
                    }
                    i2++;
                } else {
                    this.q.a(this.n, this.m.f8481a, l70Var);
                    return jA;
                }
            }
        }
    }

    /* JADX INFO: compiled from: ExoPlayerImplInternal.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8639a;
        public final long b;
        public volatile long c;
        public volatile long d;

        public b(int i, long j) {
            this.f8639a = i;
            this.b = j;
            this.c = j;
            this.d = j;
        }

        public b a(int i) {
            b bVar = new b(i, this.b);
            bVar.c = this.c;
            bVar.d = this.d;
            return bVar;
        }
    }

    /* JADX INFO: compiled from: ExoPlayerImplInternal.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final yb0 f8640a;
        public final int b;
        public final long c;

        public c(yb0 yb0Var, int i, long j) {
            this.f8640a = yb0Var;
            this.b = i;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: ExoPlayerImplInternal.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final yb0 f8641a;
        public final Object b;
        public final b c;
        public final int d;

        public d(yb0 yb0Var, Object obj, b bVar, int i) {
            this.f8641a = yb0Var;
            this.b = obj;
            this.c = bVar;
            this.d = i;
        }
    }

    public o70(h90[] h90VarArr, m70 m70Var, f90 f90Var, boolean z, Handler handler, b bVar, j50 j50Var) {
        this.f8635a = h90VarArr;
        this.c = m70Var;
        this.d = f90Var;
        this.s = z;
        this.h = handler;
        this.l = bVar;
        this.i = j50Var;
        this.b = new i90[h90VarArr.length];
        for (int i = 0; i < h90VarArr.length; i++) {
            h90VarArr[i].a(i);
            this.b[i] = h90VarArr[i].b();
        }
        this.f8636e = new t80();
        this.q = new h90[0];
        this.j = new yb0.c();
        this.k = new yb0.b();
        m70Var.a((m70.a) this);
        this.m = g90.d;
        HandlerThread handlerThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.g = handlerThread;
        handlerThread.start();
        this.f = new Handler(this.g.getLooper(), this);
    }

    public synchronized void b(j50.c... cVarArr) {
        if (this.r) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            return;
        }
        int i = this.w;
        this.w = i + 1;
        this.f.obtainMessage(11, cVarArr).sendToTarget();
        while (this.x <= i) {
            try {
                wait();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public final void c(boolean z) throws com.google.android.exoplayer2.e {
        this.t = false;
        this.s = z;
        if (!z) {
            c();
            d();
            return;
        }
        int i = this.v;
        if (i == 3) {
            b();
            this.f.sendEmptyMessage(2);
        } else if (i == 2) {
            this.f.sendEmptyMessage(2);
        }
    }

    public final void d() throws com.google.android.exoplayer2.e {
        a aVar = this.E;
        if (aVar == null) {
            return;
        }
        long jE = aVar.f8637a.e();
        if (jE != -9223372036854775807L) {
            a(jE);
        } else {
            h90 h90Var = this.n;
            if (h90Var == null || h90Var.u()) {
                this.B = this.f8636e.o();
            } else {
                long jO = this.o.o();
                this.B = jO;
                this.f8636e.a(jO);
            }
            jE = this.E.b(this.B);
        }
        this.l.c = jE;
        this.y = SystemClock.elapsedRealtime() * 1000;
        long jF = this.q.length == 0 ? Long.MIN_VALUE : this.E.f8637a.f();
        b bVar = this.l;
        if (jF == Long.MIN_VALUE) {
            jF = this.F.a(this.E.f, this.k).b();
        }
        bVar.d = jF;
    }

    public final void e() throws IOException, com.google.android.exoplayer2.e {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        j();
        if (this.E == null) {
            i();
            a(jElapsedRealtime, 10L);
            return;
        }
        v80.a("doSomeWork");
        d();
        this.E.f8637a.b(this.l.c);
        boolean zB = true;
        boolean z = true;
        for (h90 h90Var : this.q) {
            h90Var.a(this.B, this.y);
            z = z && h90Var.u();
            boolean z2 = h90Var.n() || h90Var.u();
            if (!z2) {
                h90Var.j();
            }
            zB = zB && z2;
        }
        if (!zB) {
            i();
        }
        k80 k80Var = this.o;
        if (k80Var != null) {
            g90 g90VarP = k80Var.p();
            if (!g90VarP.equals(this.m)) {
                this.m = g90VarP;
                this.f8636e.a(this.o);
                this.h.obtainMessage(7, g90VarP).sendToTarget();
            }
        }
        long jB = this.F.a(this.E.f, this.k).b();
        if (!z || ((jB != -9223372036854775807L && jB > this.l.c) || !this.E.h)) {
            int i = this.v;
            if (i == 2) {
                if (this.q.length > 0 ? zB && e(this.t) : b(jB)) {
                    a(3);
                    if (this.s) {
                        b();
                    }
                }
            } else if (i == 3) {
                if (this.q.length <= 0) {
                    zB = b(jB);
                }
                if (!zB) {
                    this.t = this.s;
                    a(2);
                    c();
                }
            }
        } else {
            a(4);
            c();
        }
        if (this.v == 2) {
            for (h90 h90Var2 : this.q) {
                h90Var2.j();
            }
        }
        if ((this.s && this.v == 3) || this.v == 2) {
            a(jElapsedRealtime, 10L);
        } else if (this.q.length != 0) {
            a(jElapsedRealtime, 1000L);
        } else {
            this.f.removeMessages(2);
        }
        v80.a();
    }

    public final void f() {
        d(true);
        this.d.b();
        a(1);
    }

    public final void g() {
        d(true);
        this.d.c();
        a(1);
        synchronized (this) {
            this.r = true;
            notifyAll();
        }
    }

    public final void h() throws com.google.android.exoplayer2.e {
        a aVar = this.E;
        if (aVar == null) {
            return;
        }
        boolean z = true;
        while (aVar != null && aVar.i) {
            if (aVar.d()) {
                if (z) {
                    boolean z2 = this.D != this.E;
                    a(this.E.k);
                    a aVar2 = this.E;
                    aVar2.k = null;
                    this.C = aVar2;
                    this.D = aVar2;
                    boolean[] zArr = new boolean[this.f8635a.length];
                    long jA = aVar2.a(this.l.c, z2, zArr);
                    if (jA != this.l.c) {
                        this.l.c = jA;
                        a(jA);
                    }
                    boolean[] zArr2 = new boolean[this.f8635a.length];
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        h90[] h90VarArr = this.f8635a;
                        if (i >= h90VarArr.length) {
                            break;
                        }
                        h90 h90Var = h90VarArr[i];
                        zArr2[i] = h90Var.d() != 0;
                        mb0 mb0Var = this.E.c[i];
                        if (mb0Var != null) {
                            i2++;
                        }
                        if (zArr2[i]) {
                            if (mb0Var != h90Var.f()) {
                                if (h90Var == this.n) {
                                    if (mb0Var == null) {
                                        this.f8636e.a(this.o);
                                    }
                                    this.o = null;
                                    this.n = null;
                                }
                                a(h90Var);
                                h90Var.l();
                            } else if (zArr[i]) {
                                h90Var.a(this.B);
                            }
                        }
                        i++;
                    }
                    this.h.obtainMessage(3, aVar.m).sendToTarget();
                    a(zArr2, i2);
                } else {
                    this.C = aVar;
                    for (a aVar3 = aVar.k; aVar3 != null; aVar3 = aVar3.k) {
                        aVar3.e();
                    }
                    a aVar4 = this.C;
                    aVar4.k = null;
                    if (aVar4.i) {
                        this.C.a(Math.max(aVar4.g, aVar4.b(this.B)), false);
                    }
                }
                l();
                d();
                this.f.sendEmptyMessage(2);
                return;
            }
            if (aVar == this.D) {
                z = false;
            }
            aVar = aVar.k;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    b((ua0) message.obj, message.arg1 != 0);
                    return true;
                case 1:
                    c(message.arg1 != 0);
                    return true;
                case 2:
                    e();
                    return true;
                case 3:
                    a((c) message.obj);
                    return true;
                case 4:
                    b((g90) message.obj);
                    return true;
                case 5:
                    f();
                    return true;
                case 6:
                    g();
                    return true;
                case 7:
                    a((Pair<yb0, Object>) message.obj);
                    return true;
                case 8:
                    c((ta0) message.obj);
                    return true;
                case 9:
                    d((ta0) message.obj);
                    return true;
                case 10:
                    h();
                    return true;
                case 11:
                    c((j50.c[]) message.obj);
                    return true;
                default:
                    return false;
            }
        } catch (com.google.android.exoplayer2.e e2) {
            Log.e("ExoPlayerImplInternal", "Renderer error.", e2);
            this.h.obtainMessage(8, e2).sendToTarget();
            f();
            return true;
        } catch (IOException e3) {
            Log.e("ExoPlayerImplInternal", "Source error.", e3);
            this.h.obtainMessage(8, com.google.android.exoplayer2.e.a(e3)).sendToTarget();
            f();
            return true;
        } catch (RuntimeException e4) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", e4);
            this.h.obtainMessage(8, com.google.android.exoplayer2.e.a(e4)).sendToTarget();
            f();
            return true;
        }
    }

    public final void i() throws IOException {
        a aVar = this.C;
        if (aVar == null || aVar.i) {
            return;
        }
        a aVar2 = this.D;
        if (aVar2 == null || aVar2.k == aVar) {
            for (h90 h90Var : this.q) {
                if (!h90Var.g()) {
                    return;
                }
            }
            this.C.f8637a.c();
        }
    }

    public final void j() throws IOException, com.google.android.exoplayer2.e {
        if (this.F == null) {
            this.p.a();
            return;
        }
        k();
        a aVar = this.C;
        int i = 0;
        if (aVar == null || aVar.b()) {
            b(false);
        } else {
            a aVar2 = this.C;
            if (aVar2 != null && aVar2.l) {
                l();
            }
        }
        if (this.E == null) {
            return;
        }
        while (true) {
            a aVar3 = this.E;
            if (aVar3 == this.D || this.B < aVar3.k.f8638e) {
                break;
            }
            aVar3.e();
            b(this.E.k);
            a aVar4 = this.E;
            this.l = new b(aVar4.f, aVar4.g);
            d();
            this.h.obtainMessage(5, this.l).sendToTarget();
        }
        if (this.D.h) {
            while (true) {
                h90[] h90VarArr = this.f8635a;
                if (i >= h90VarArr.length) {
                    return;
                }
                h90 h90Var = h90VarArr[i];
                mb0 mb0Var = this.D.c[i];
                if (mb0Var != null && h90Var.f() == mb0Var && h90Var.g()) {
                    h90Var.h();
                }
                i++;
            }
        } else {
            int i2 = 0;
            while (true) {
                h90[] h90VarArr2 = this.f8635a;
                if (i2 < h90VarArr2.length) {
                    h90 h90Var2 = h90VarArr2[i2];
                    mb0 mb0Var2 = this.D.c[i2];
                    if (h90Var2.f() != mb0Var2) {
                        return;
                    }
                    if (mb0Var2 != null && !h90Var2.g()) {
                        return;
                    } else {
                        i2++;
                    }
                } else {
                    a aVar5 = this.D;
                    a aVar6 = aVar5.k;
                    if (aVar6 == null || !aVar6.i) {
                        return;
                    }
                    n70 n70Var = aVar5.m;
                    this.D = aVar6;
                    n70 n70Var2 = aVar6.m;
                    boolean z = aVar6.f8637a.e() != -9223372036854775807L;
                    int i3 = 0;
                    while (true) {
                        h90[] h90VarArr3 = this.f8635a;
                        if (i3 >= h90VarArr3.length) {
                            return;
                        }
                        h90 h90Var3 = h90VarArr3[i3];
                        if (n70Var.b.a(i3) != null) {
                            if (z) {
                                h90Var3.h();
                            } else if (!h90Var3.i()) {
                                k70 k70VarA = n70Var2.b.a(i3);
                                j90 j90Var = n70Var.d[i3];
                                j90 j90Var2 = n70Var2.d[i3];
                                if (k70VarA == null || !j90Var2.equals(j90Var)) {
                                    h90Var3.h();
                                } else {
                                    int iE = k70VarA.e();
                                    com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[iE];
                                    for (int i4 = 0; i4 < iE; i4++) {
                                        jVarArr[i4] = k70VarA.a(i4);
                                    }
                                    a aVar7 = this.D;
                                    h90Var3.a(jVarArr, aVar7.c[i3], aVar7.a());
                                }
                            }
                        }
                        i3++;
                    }
                }
            }
        }
    }

    public final void k() throws IOException {
        int i;
        a aVar = this.C;
        if (aVar == null) {
            i = this.l.f8639a;
        } else {
            int i2 = aVar.f;
            if (aVar.h || !aVar.b() || this.F.a(i2, this.k).b() == -9223372036854775807L) {
                return;
            }
            a aVar2 = this.E;
            if (aVar2 != null && i2 - aVar2.f == 100) {
                return;
            } else {
                i = this.C.f + 1;
            }
        }
        if (i >= this.F.c()) {
            this.p.a();
            return;
        }
        long jLongValue = 0;
        if (this.C == null) {
            jLongValue = this.l.c;
        } else {
            int i3 = this.F.a(i, this.k).c;
            if (i == this.F.a(i3, this.j).d) {
                Pair<Integer, Long> pairA = a(this.F, i3, -9223372036854775807L, Math.max(0L, (this.C.a() + this.F.a(this.C.f, this.k).b()) - this.B));
                if (pairA == null) {
                    return;
                }
                int iIntValue = ((Integer) pairA.first).intValue();
                jLongValue = ((Long) pairA.second).longValue();
                i = iIntValue;
            }
        }
        long j = jLongValue;
        a aVar3 = this.C;
        long jA = aVar3 == null ? j + 60000000 : aVar3.a() + this.F.a(this.C.f, this.k).b();
        this.F.a(i, this.k, true);
        a aVar4 = new a(this.f8635a, this.b, jA, this.c, this.d, this.p, this.k.b, i, i == this.F.c() - 1 && !this.F.a(this.k.c, this.j).c, j);
        a aVar5 = this.C;
        if (aVar5 != null) {
            aVar5.k = aVar4;
        }
        this.C = aVar4;
        aVar4.f8637a.a(this);
        b(true);
    }

    public final void l() {
        a aVar = this.C;
        long jI = !aVar.i ? 0L : aVar.f8637a.i();
        if (jI == Long.MIN_VALUE) {
            b(false);
            return;
        }
        long jB = this.C.b(this.B);
        boolean zA = this.d.a(jI - jB);
        b(zA);
        if (!zA) {
            this.C.l = true;
            return;
        }
        a aVar2 = this.C;
        aVar2.l = false;
        aVar2.f8637a.a(jB);
    }

    public void a(ua0 ua0Var, boolean z) {
        this.f.obtainMessage(0, z ? 1 : 0, 0, ua0Var).sendToTarget();
    }

    public void a(boolean z) {
        this.f.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void a(yb0 yb0Var, int i, long j) {
        this.f.obtainMessage(3, new c(yb0Var, i, j)).sendToTarget();
    }

    public void a(g90 g90Var) {
        this.f.obtainMessage(4, g90Var).sendToTarget();
    }

    public void a(j50.c... cVarArr) {
        if (this.r) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        } else {
            this.w++;
            this.f.obtainMessage(11, cVarArr).sendToTarget();
        }
    }

    public final void c() throws com.google.android.exoplayer2.e {
        this.f8636e.b();
        for (h90 h90Var : this.q) {
            a(h90Var);
        }
    }

    @Override // supwisdom.nb0.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(ta0 ta0Var) {
        this.f.obtainMessage(9, ta0Var).sendToTarget();
    }

    public final void b(boolean z) {
        if (this.u != z) {
            this.u = z;
            this.h.obtainMessage(2, z ? 1 : 0, 0).sendToTarget();
        }
    }

    public synchronized void a() {
        if (this.r) {
            return;
        }
        this.f.sendEmptyMessage(6);
        while (!this.r) {
            try {
                wait();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        this.g.quit();
    }

    public final void c(j50.c[] cVarArr) throws com.google.android.exoplayer2.e {
        try {
            for (j50.c cVar : cVarArr) {
                cVar.f8023a.a(cVar.b, cVar.c);
            }
            if (this.p != null) {
                this.f.sendEmptyMessage(2);
            }
            synchronized (this) {
                this.x++;
                notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.x++;
                notifyAll();
                throw th;
            }
        }
    }

    public final void b(ua0 ua0Var, boolean z) {
        this.h.sendEmptyMessage(0);
        d(true);
        this.d.a();
        if (z) {
            this.l = new b(0, -9223372036854775807L);
        }
        this.p = ua0Var;
        ua0Var.a(this.i, true, (ua0.a) this);
        a(2);
        this.f.sendEmptyMessage(2);
    }

    public final void d(boolean z) {
        this.f.removeMessages(2);
        this.t = false;
        this.f8636e.b();
        this.o = null;
        this.n = null;
        this.B = 60000000L;
        for (h90 h90Var : this.q) {
            try {
                a(h90Var);
                h90Var.l();
            } catch (com.google.android.exoplayer2.e | RuntimeException e2) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", e2);
            }
        }
        this.q = new h90[0];
        a aVar = this.E;
        if (aVar == null) {
            aVar = this.C;
        }
        a(aVar);
        this.C = null;
        this.D = null;
        this.E = null;
        b(false);
        if (z) {
            ua0 ua0Var = this.p;
            if (ua0Var != null) {
                ua0Var.b();
                this.p = null;
            }
            this.F = null;
        }
    }

    @Override // supwisdom.ua0.a
    public void a(yb0 yb0Var, Object obj) {
        this.f.obtainMessage(7, Pair.create(yb0Var, obj)).sendToTarget();
    }

    @Override // supwisdom.ta0.a
    public void a(ta0 ta0Var) {
        this.f.obtainMessage(8, ta0Var).sendToTarget();
    }

    public final void a(int i) {
        if (this.v != i) {
            this.v = i;
            this.h.obtainMessage(1, i, 0).sendToTarget();
        }
    }

    public final void b() throws com.google.android.exoplayer2.e {
        this.t = false;
        this.f8636e.a();
        for (h90 h90Var : this.q) {
            h90Var.e();
        }
    }

    public final void c(ta0 ta0Var) throws com.google.android.exoplayer2.e {
        a aVar = this.C;
        if (aVar == null || aVar.f8637a != ta0Var) {
            return;
        }
        aVar.c();
        if (this.E == null) {
            a aVar2 = this.C;
            this.D = aVar2;
            a(aVar2.g);
            b(this.D);
        }
        l();
    }

    public final void a(long j, long j2) {
        this.f.removeMessages(2);
        long jElapsedRealtime = (j + j2) - SystemClock.elapsedRealtime();
        if (jElapsedRealtime <= 0) {
            this.f.sendEmptyMessage(2);
        } else {
            this.f.sendEmptyMessageDelayed(2, jElapsedRealtime);
        }
    }

    public final void b(g90 g90Var) {
        k80 k80Var = this.o;
        if (k80Var != null) {
            g90Var = k80Var.a(g90Var);
        } else {
            this.f8636e.a(g90Var);
        }
        this.m = g90Var;
        this.h.obtainMessage(7, g90Var).sendToTarget();
    }

    public final void a(c cVar) throws com.google.android.exoplayer2.e {
        if (this.F == null) {
            this.z++;
            this.A = cVar;
            return;
        }
        Pair<Integer, Long> pairB = b(cVar);
        if (pairB == null) {
            b bVar = new b(0, 0L);
            this.l = bVar;
            this.h.obtainMessage(4, 1, 0, bVar).sendToTarget();
            this.l = new b(0, -9223372036854775807L);
            a(4);
            d(false);
            return;
        }
        int i = cVar.c == -9223372036854775807L ? 1 : 0;
        int iIntValue = ((Integer) pairB.first).intValue();
        long jLongValue = ((Long) pairB.second).longValue();
        try {
            if (iIntValue == this.l.f8639a && jLongValue / 1000 == this.l.c / 1000) {
                return;
            }
            long jA = a(iIntValue, jLongValue);
            int i2 = i | (jLongValue == jA ? 0 : 1);
            b bVar2 = new b(iIntValue, jA);
            this.l = bVar2;
            this.h.obtainMessage(4, i2, 0, bVar2).sendToTarget();
        } finally {
            b bVar3 = new b(iIntValue, jLongValue);
            this.l = bVar3;
            this.h.obtainMessage(4, i, 0, bVar3).sendToTarget();
        }
    }

    public final boolean b(long j) {
        a aVar;
        return j == -9223372036854775807L || this.l.c < j || ((aVar = this.E.k) != null && aVar.i);
    }

    public final void b(Object obj, int i) {
        this.h.obtainMessage(6, new d(this.F, obj, this.l, i)).sendToTarget();
    }

    public final Pair<Integer, Long> b(c cVar) {
        yb0 yb0Var = cVar.f8640a;
        if (yb0Var.a()) {
            yb0Var = this.F;
        }
        try {
            Pair<Integer, Long> pairB = b(yb0Var, cVar.b, cVar.c);
            yb0 yb0Var2 = this.F;
            if (yb0Var2 == yb0Var) {
                return pairB;
            }
            int iA = yb0Var2.a(yb0Var.a(((Integer) pairB.first).intValue(), this.k, true).b);
            if (iA != -1) {
                return Pair.create(Integer.valueOf(iA), pairB.second);
            }
            int iA2 = a(((Integer) pairB.first).intValue(), yb0Var, this.F);
            if (iA2 != -1) {
                return b(this.F.a(iA2, this.k).c, -9223372036854775807L);
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
            throw new com.google.android.exoplayer2.l(this.F, cVar.b, cVar.c);
        }
    }

    public final void d(ta0 ta0Var) {
        a aVar = this.C;
        if (aVar == null || aVar.f8637a != ta0Var) {
            return;
        }
        l();
    }

    public final Pair<Integer, Long> b(int i, long j) {
        return b(this.F, i, j);
    }

    public final Pair<Integer, Long> b(yb0 yb0Var, int i, long j) {
        return a(yb0Var, i, j, 0L);
    }

    public final void b(a aVar) throws com.google.android.exoplayer2.e {
        if (this.E == aVar) {
            return;
        }
        boolean[] zArr = new boolean[this.f8635a.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            h90[] h90VarArr = this.f8635a;
            if (i < h90VarArr.length) {
                h90 h90Var = h90VarArr[i];
                zArr[i] = h90Var.d() != 0;
                k70 k70VarA = aVar.m.b.a(i);
                if (k70VarA != null) {
                    i2++;
                }
                if (zArr[i] && (k70VarA == null || (h90Var.i() && h90Var.f() == this.E.c[i]))) {
                    if (h90Var == this.n) {
                        this.f8636e.a(this.o);
                        this.o = null;
                        this.n = null;
                    }
                    a(h90Var);
                    h90Var.l();
                }
                i++;
            } else {
                this.E = aVar;
                this.h.obtainMessage(3, aVar.m).sendToTarget();
                a(zArr, i2);
                return;
            }
        }
    }

    public final boolean e(boolean z) {
        a aVar = this.C;
        long jF = !aVar.i ? aVar.g : aVar.f8637a.f();
        if (jF == Long.MIN_VALUE) {
            a aVar2 = this.C;
            if (aVar2.h) {
                return true;
            }
            jF = this.F.a(aVar2.f, this.k).b();
        }
        return this.d.a(jF - this.C.b(this.B), z);
    }

    public final long a(int i, long j) throws com.google.android.exoplayer2.e {
        a aVar;
        c();
        this.t = false;
        a(2);
        a aVar2 = this.E;
        if (aVar2 == null) {
            a aVar3 = this.C;
            if (aVar3 != null) {
                aVar3.e();
            }
            aVar = null;
        } else {
            aVar = null;
            while (aVar2 != null) {
                if (aVar2.f == i && aVar2.i) {
                    aVar = aVar2;
                } else {
                    aVar2.e();
                }
                aVar2 = aVar2.k;
            }
        }
        a aVar4 = this.E;
        if (aVar4 != aVar || aVar4 != this.D) {
            for (h90 h90Var : this.q) {
                h90Var.l();
            }
            this.q = new h90[0];
            this.o = null;
            this.n = null;
            this.E = null;
        }
        if (aVar != null) {
            aVar.k = null;
            this.C = aVar;
            this.D = aVar;
            b(aVar);
            a aVar5 = this.E;
            if (aVar5.j) {
                j = aVar5.f8637a.d(j);
            }
            a(j);
            l();
        } else {
            this.C = null;
            this.D = null;
            this.E = null;
            a(j);
        }
        this.f.sendEmptyMessage(2);
        return j;
    }

    public final void a(long j) throws com.google.android.exoplayer2.e {
        a aVar = this.E;
        long jA = aVar == null ? j + 60000000 : aVar.a(j);
        this.B = jA;
        this.f8636e.a(jA);
        for (h90 h90Var : this.q) {
            h90Var.a(this.B);
        }
    }

    public final void a(h90 h90Var) throws com.google.android.exoplayer2.e {
        if (h90Var.d() == 2) {
            h90Var.k();
        }
    }

    public final void a(Pair<yb0, Object> pair) throws com.google.android.exoplayer2.e {
        int i;
        yb0 yb0Var = this.F;
        yb0 yb0Var2 = (yb0) pair.first;
        this.F = yb0Var2;
        Object obj = pair.second;
        if (yb0Var != null) {
            i = 0;
        } else if (this.z > 0) {
            Pair<Integer, Long> pairB = b(this.A);
            i = this.z;
            this.z = 0;
            this.A = null;
            if (pairB == null) {
                a(obj, i);
                return;
            }
            this.l = new b(((Integer) pairB.first).intValue(), ((Long) pairB.second).longValue());
        } else {
            if (this.l.b == -9223372036854775807L) {
                if (yb0Var2.a()) {
                    a(obj, 0);
                    return;
                } else {
                    Pair<Integer, Long> pairB2 = b(0, -9223372036854775807L);
                    this.l = new b(((Integer) pairB2.first).intValue(), ((Long) pairB2.second).longValue());
                }
            }
            i = 0;
        }
        a aVar = this.E;
        if (aVar == null) {
            aVar = this.C;
        }
        if (aVar == null) {
            b(obj, i);
            return;
        }
        int iA = this.F.a(aVar.b);
        if (iA == -1) {
            int iA2 = a(aVar.f, yb0Var, this.F);
            if (iA2 == -1) {
                a(obj, i);
                return;
            }
            Pair<Integer, Long> pairB3 = b(this.F.a(iA2, this.k).c, -9223372036854775807L);
            int iIntValue = ((Integer) pairB3.first).intValue();
            long jLongValue = ((Long) pairB3.second).longValue();
            this.F.a(iIntValue, this.k, true);
            Object obj2 = this.k.b;
            aVar.f = -1;
            while (true) {
                aVar = aVar.k;
                if (aVar != null) {
                    aVar.f = aVar.b.equals(obj2) ? iIntValue : -1;
                } else {
                    this.l = new b(iIntValue, a(iIntValue, jLongValue));
                    b(obj, i);
                    return;
                }
            }
        } else {
            this.F.a(iA, this.k);
            aVar.a(iA, iA == this.F.c() - 1 && !this.F.a(this.k.c, this.j).c);
            boolean z = aVar == this.D;
            b bVar = this.l;
            if (iA != bVar.f8639a) {
                this.l = bVar.a(iA);
            }
            while (true) {
                a aVar2 = aVar.k;
                if (aVar2 == null) {
                    break;
                }
                iA++;
                this.F.a(iA, this.k, true);
                boolean z2 = iA == this.F.c() - 1 && !this.F.a(this.k.c, this.j).c;
                if (aVar2.b.equals(this.k.b)) {
                    aVar2.a(iA, z2);
                    z |= aVar2 == this.D;
                    aVar = aVar2;
                } else if (!z) {
                    int i2 = this.E.f;
                    this.l = new b(i2, a(i2, this.l.c));
                } else {
                    this.C = aVar;
                    aVar.k = null;
                    a(aVar2);
                }
            }
            b(obj, i);
        }
    }

    public final void a(Object obj, int i) {
        this.l = new b(0, 0L);
        b(obj, i);
        this.l = new b(0, -9223372036854775807L);
        a(4);
        d(false);
    }

    public final int a(int i, yb0 yb0Var, yb0 yb0Var2) {
        int iA = -1;
        while (iA == -1 && i < yb0Var.c() - 1) {
            i++;
            iA = yb0Var2.a(yb0Var.a(i, this.k, true).b);
        }
        return iA;
    }

    public final Pair<Integer, Long> a(yb0 yb0Var, int i, long j, long j2) {
        e80.a(i, 0, yb0Var.b());
        yb0Var.a(i, this.j, false, j2);
        if (j == -9223372036854775807L) {
            j = this.j.a();
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        yb0.c cVar = this.j;
        int i2 = cVar.d;
        long jC = cVar.c() + j;
        long jB = yb0Var.a(i2, this.k).b();
        while (jB != -9223372036854775807L && jC >= jB && i2 < this.j.f9867e) {
            jC -= jB;
            i2++;
            jB = yb0Var.a(i2, this.k).b();
        }
        return Pair.create(Integer.valueOf(i2), Long.valueOf(jC));
    }

    public final void a(a aVar) {
        while (aVar != null) {
            aVar.e();
            aVar = aVar.k;
        }
    }

    public final void a(boolean[] zArr, int i) throws com.google.android.exoplayer2.e {
        this.q = new h90[i];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            h90[] h90VarArr = this.f8635a;
            if (i2 >= h90VarArr.length) {
                return;
            }
            h90 h90Var = h90VarArr[i2];
            k70 k70VarA = this.E.m.b.a(i2);
            if (k70VarA != null) {
                int i4 = i3 + 1;
                this.q[i3] = h90Var;
                if (h90Var.d() == 0) {
                    j90 j90Var = this.E.m.d[i2];
                    boolean z = this.s && this.v == 3;
                    boolean z2 = !zArr[i2] && z;
                    int iE = k70VarA.e();
                    com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[iE];
                    for (int i5 = 0; i5 < iE; i5++) {
                        jVarArr[i5] = k70VarA.a(i5);
                    }
                    a aVar = this.E;
                    h90Var.a(j90Var, jVarArr, aVar.c[i2], this.B, z2, aVar.a());
                    k80 k80VarC = h90Var.c();
                    if (k80VarC != null) {
                        if (this.o == null) {
                            this.o = k80VarC;
                            this.n = h90Var;
                            k80VarC.a(this.m);
                        } else {
                            throw com.google.android.exoplayer2.e.a(new IllegalStateException("Multiple renderer media clocks enabled."));
                        }
                    }
                    if (z) {
                        h90Var.e();
                    }
                }
                i3 = i4;
            }
            i2++;
        }
    }
}
