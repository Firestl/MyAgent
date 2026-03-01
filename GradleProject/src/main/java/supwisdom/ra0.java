package supwisdom;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import com.google.android.exoplayer2.i.r;
import java.io.EOFException;
import java.io.IOException;
import supwisdom.sa0;
import supwisdom.ta0;
import supwisdom.ua0;
import supwisdom.x20;

/* JADX INFO: compiled from: ExtractorMediaPeriod.java */
/* JADX INFO: loaded from: classes.dex */
public final class ra0 implements x20.d, z40, r.a<e>, ta0 {
    public boolean A;
    public long C;
    public int E;
    public boolean F;
    public boolean G;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f9030a;
    public final s70 b;
    public final int c;
    public final Handler d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final sa0.a f9031e;
    public final ua0.a f;
    public final q70 g;
    public final String h;
    public final f j;
    public ta0.a p;
    public e50 q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public qb0 w;
    public long x;
    public boolean[] y;
    public boolean[] z;
    public final com.google.android.exoplayer2.i.r i = new com.google.android.exoplayer2.i.r("Loader:ExtractorMediaPeriod");
    public final h80 k = new h80();
    public final Runnable l = new a();
    public final Runnable m = new b();
    public final Handler n = new Handler();
    public long D = -9223372036854775807L;
    public final SparseArray<x20> o = new SparseArray<>();
    public long B = -1;

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ra0.this.h();
        }
    }

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ra0.this.G) {
                return;
            }
            ra0.this.p.a(ra0.this);
        }
    }

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f f9034a;

        public c(f fVar) {
            this.f9034a = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9034a.a();
            int size = ra0.this.o.size();
            for (int i = 0; i < size; i++) {
                ((x20) ra0.this.o.valueAt(i)).c();
            }
        }
    }

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IOException f9035a;

        public d(IOException iOException) {
            this.f9035a = iOException;
        }

        @Override // java.lang.Runnable
        public void run() {
            ra0.this.f9031e.onLoadError(this.f9035a);
        }
    }

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public final class e implements r.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f9036a;
        public final s70 b;
        public final f c;
        public final h80 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final d50 f9037e;
        public volatile boolean f;
        public boolean g;
        public long h;
        public long i;

        public e(Uri uri, s70 s70Var, f fVar, h80 h80Var) {
            e80.a(uri);
            this.f9036a = uri;
            e80.a(s70Var);
            this.b = s70Var;
            e80.a(fVar);
            this.c = fVar;
            this.d = h80Var;
            this.f9037e = new d50();
            this.g = true;
            this.i = -1L;
        }

        @Override // com.google.android.exoplayer2.i.r.c
        public boolean b() {
            return this.f;
        }

        @Override // com.google.android.exoplayer2.i.r.c
        public void c() throws Throwable {
            long jC;
            r20 r20Var;
            int iA = 0;
            while (iA == 0 && !this.f) {
                r20 r20Var2 = null;
                try {
                    jC = this.f9037e.f7296a;
                    long jA = this.b.a(new u70(this.f9036a, jC, -1L, ra0.this.h));
                    this.i = jA;
                    if (jA != -1) {
                        this.i = jA + jC;
                    }
                    r20Var = new r20(this.b, jC, this.i);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    y30 y30VarA = this.c.a(r20Var, this.b.b());
                    if (this.g) {
                        y30VarA.a(jC, this.h);
                        this.g = false;
                    }
                    while (iA == 0 && !this.f) {
                        this.d.c();
                        iA = y30VarA.a(r20Var, this.f9037e);
                        if (r20Var.c() > 1048576 + jC) {
                            jC = r20Var.c();
                            this.d.b();
                            ra0.this.n.post(ra0.this.m);
                        }
                    }
                    if (iA == 1) {
                        iA = 0;
                    } else {
                        this.f9037e.f7296a = r20Var.c();
                    }
                    x80.a(this.b);
                } catch (Throwable th2) {
                    th = th2;
                    r20Var2 = r20Var;
                    if (iA != 1 && r20Var2 != null) {
                        this.f9037e.f7296a = r20Var2.c();
                    }
                    x80.a(this.b);
                    throw th;
                }
            }
        }

        public void a(long j, long j2) {
            this.f9037e.f7296a = j;
            this.h = j2;
            this.g = true;
        }

        @Override // com.google.android.exoplayer2.i.r.c
        public void a() {
            this.f = true;
        }
    }

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public final class g implements mb0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9039a;

        public g(int i) {
            this.f9039a = i;
        }

        @Override // supwisdom.mb0
        public void b() throws IOException {
            ra0.this.g();
        }

        @Override // supwisdom.mb0
        public void c(long j) {
            ra0.this.a(this.f9039a, j);
        }

        @Override // supwisdom.mb0
        public boolean a() {
            return ra0.this.a(this.f9039a);
        }

        @Override // supwisdom.mb0
        public int a(e90 e90Var, y10 y10Var, boolean z) {
            return ra0.this.a(this.f9039a, e90Var, y10Var, z);
        }
    }

    public ra0(Uri uri, s70 s70Var, y30[] y30VarArr, int i, Handler handler, sa0.a aVar, ua0.a aVar2, q70 q70Var, String str) {
        this.f9030a = uri;
        this.b = s70Var;
        this.c = i;
        this.d = handler;
        this.f9031e = aVar;
        this.f = aVar2;
        this.g = q70Var;
        this.h = str;
        this.j = new f(y30VarArr, this);
    }

    @Override // supwisdom.ta0
    public void b(long j) {
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public long i() {
        if (this.v == 0) {
            return Long.MIN_VALUE;
        }
        return f();
    }

    public final void j() {
        e50 e50Var;
        e eVar = new e(this.f9030a, this.b, this.j, this.k);
        if (this.s) {
            e80.b(m());
            long j = this.x;
            if (j != -9223372036854775807L && this.D >= j) {
                this.F = true;
                this.D = -9223372036854775807L;
                return;
            } else {
                eVar.a(this.q.b(this.D), this.D);
                this.D = -9223372036854775807L;
            }
        }
        this.E = k();
        int i = this.c;
        if (i == -1) {
            i = (this.s && this.B == -1 && ((e50Var = this.q) == null || e50Var.b() == -9223372036854775807L)) ? 6 : 3;
        }
        this.i.a(eVar, this, i);
    }

    public final int k() {
        int size = this.o.size();
        int iB = 0;
        for (int i = 0; i < size; i++) {
            iB += this.o.valueAt(i).b();
        }
        return iB;
    }

    public final long l() {
        int size = this.o.size();
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            jMax = Math.max(jMax, this.o.valueAt(i).h());
        }
        return jMax;
    }

    public final boolean m() {
        return this.D != -9223372036854775807L;
    }

    public void b() {
        this.i.a(new c(this.j));
        this.n.removeCallbacksAndMessages(null);
        this.G = true;
    }

    @Override // supwisdom.ta0
    public void c() throws IOException {
        g();
    }

    @Override // supwisdom.ta0
    public qb0 d() {
        return this.w;
    }

    @Override // supwisdom.ta0
    public long e() {
        if (!this.u) {
            return -9223372036854775807L;
        }
        this.u = false;
        return this.C;
    }

    @Override // supwisdom.ta0
    public long f() {
        long jL;
        if (this.F) {
            return Long.MIN_VALUE;
        }
        if (m()) {
            return this.D;
        }
        if (this.A) {
            jL = Long.MAX_VALUE;
            int size = this.o.size();
            for (int i = 0; i < size; i++) {
                if (this.z[i]) {
                    jL = Math.min(jL, this.o.valueAt(i).h());
                }
            }
        } else {
            jL = l();
        }
        return jL == Long.MIN_VALUE ? this.C : jL;
    }

    public void g() throws IOException {
        this.i.d();
    }

    public final void h() {
        if (this.G || this.s || this.q == null || !this.r) {
            return;
        }
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            if (this.o.valueAt(i).g() == null) {
                return;
            }
        }
        this.k.b();
        pb0[] pb0VarArr = new pb0[size];
        this.z = new boolean[size];
        this.y = new boolean[size];
        this.x = this.q.b();
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= size) {
                this.w = new qb0(pb0VarArr);
                this.s = true;
                this.f.a(new ob0(this.x, this.q.a()), null);
                this.p.a((ta0) this);
                return;
            }
            com.google.android.exoplayer2.j jVarG = this.o.valueAt(i2).g();
            pb0VarArr[i2] = new pb0(jVarG);
            String str = jVarG.f;
            if (!l80.b(str) && !l80.a(str)) {
                z = false;
            }
            this.z[i2] = z;
            this.A = z | this.A;
            i2++;
        }
    }

    @Override // supwisdom.ta0
    public long d(long j) {
        if (!this.q.a()) {
            j = 0;
        }
        this.C = j;
        int size = this.o.size();
        boolean zA = !m();
        for (int i = 0; zA && i < size; i++) {
            if (this.y[i]) {
                zA = this.o.valueAt(i).a(j, false);
            }
        }
        if (!zA) {
            this.D = j;
            this.F = false;
            if (this.i.a()) {
                this.i.b();
            } else {
                for (int i2 = 0; i2 < size; i2++) {
                    this.o.valueAt(i2).a(this.y[i2]);
                }
            }
        }
        this.u = false;
        return j;
    }

    @Override // supwisdom.ta0
    public void a(ta0.a aVar) {
        this.p = aVar;
        this.k.a();
        j();
    }

    public final void b(e eVar) {
        if (this.B == -1) {
            e50 e50Var = this.q;
            if (e50Var == null || e50Var.b() == -9223372036854775807L) {
                this.C = 0L;
                this.u = this.s;
                int size = this.o.size();
                for (int i = 0; i < size; i++) {
                    this.o.valueAt(i).a(!this.s || this.y[i]);
                }
                eVar.a(0L, 0L);
            }
        }
    }

    @Override // supwisdom.ta0
    public long a(k70[] k70VarArr, boolean[] zArr, mb0[] mb0VarArr, boolean[] zArr2, long j) {
        e80.b(this.s);
        for (int i = 0; i < k70VarArr.length; i++) {
            if (mb0VarArr[i] != null && (k70VarArr[i] == null || !zArr[i])) {
                int i2 = ((g) mb0VarArr[i]).f9039a;
                e80.b(this.y[i2]);
                this.v--;
                this.y[i2] = false;
                this.o.valueAt(i2).c();
                mb0VarArr[i] = null;
            }
        }
        boolean z = false;
        for (int i3 = 0; i3 < k70VarArr.length; i3++) {
            if (mb0VarArr[i3] == null && k70VarArr[i3] != null) {
                k70 k70Var = k70VarArr[i3];
                e80.b(k70Var.e() == 1);
                e80.b(k70Var.b(0) == 0);
                int iA = this.w.a(k70Var.d());
                e80.b(!this.y[iA]);
                this.v++;
                this.y[iA] = true;
                mb0VarArr[i3] = new g(iA);
                zArr2[i3] = true;
                z = true;
            }
        }
        if (!this.t) {
            int size = this.o.size();
            for (int i4 = 0; i4 < size; i4++) {
                if (!this.y[i4]) {
                    this.o.valueAt(i4).c();
                }
            }
        }
        if (this.v == 0) {
            this.u = false;
            if (this.i.a()) {
                this.i.b();
            }
        } else if (!this.t ? j != 0 : z) {
            j = d(j);
            for (int i5 = 0; i5 < mb0VarArr.length; i5++) {
                if (mb0VarArr[i5] != null) {
                    zArr2[i5] = true;
                }
            }
        }
        this.t = true;
        return j;
    }

    /* JADX INFO: compiled from: ExtractorMediaPeriod.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final y30[] f9038a;
        public final z40 b;
        public y30 c;

        public f(y30[] y30VarArr, z40 z40Var) {
            this.f9038a = y30VarArr;
            this.b = z40Var;
        }

        public y30 a(v40 v40Var, Uri uri) throws InterruptedException, IOException {
            y30 y30Var = this.c;
            if (y30Var != null) {
                return y30Var;
            }
            y30[] y30VarArr = this.f9038a;
            int length = y30VarArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                y30 y30Var2 = y30VarArr[i];
                try {
                    if (y30Var2.a(v40Var)) {
                        this.c = y30Var2;
                        v40Var.a();
                        break;
                    }
                    continue;
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    v40Var.a();
                    throw th;
                }
                v40Var.a();
                i++;
            }
            y30 y30Var3 = this.c;
            if (y30Var3 != null) {
                y30Var3.a(this.b);
                return this.c;
            }
            throw new com.google.android.exoplayer2.source.n("None of the available extractors (" + x80.a(this.f9038a) + ") could read the stream.", uri);
        }

        public void a() {
            y30 y30Var = this.c;
            if (y30Var != null) {
                y30Var.c();
                this.c = null;
            }
        }
    }

    public final void b(IOException iOException) {
        Handler handler = this.d;
        if (handler == null || this.f9031e == null) {
            return;
        }
        handler.post(new d(iOException));
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public boolean a(long j) {
        if (this.F) {
            return false;
        }
        if (this.s && this.v == 0) {
            return false;
        }
        boolean zA = this.k.a();
        if (this.i.a()) {
            return zA;
        }
        j();
        return true;
    }

    public boolean a(int i) {
        return this.F || !(m() || this.o.valueAt(i).d());
    }

    public int a(int i, e90 e90Var, y10 y10Var, boolean z) {
        if (this.u || m()) {
            return -3;
        }
        return this.o.valueAt(i).a(e90Var, y10Var, z, this.F, this.C);
    }

    public void a(int i, long j) {
        x20 x20VarValueAt = this.o.valueAt(i);
        if (this.F && j > x20VarValueAt.h()) {
            x20VarValueAt.i();
        } else {
            x20VarValueAt.a(j, true);
        }
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(e eVar, long j, long j2) {
        a(eVar);
        this.F = true;
        if (this.x == -9223372036854775807L) {
            long jL = l();
            this.x = jL == Long.MIN_VALUE ? 0L : jL + 10000;
            this.f.a(new ob0(this.x, this.q.a()), null);
        }
        this.p.a(this);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(e eVar, long j, long j2, boolean z) {
        a(eVar);
        if (z || this.v <= 0) {
            return;
        }
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            this.o.valueAt(i).a(this.y[i]);
        }
        this.p.a(this);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public int a(e eVar, long j, long j2, IOException iOException) {
        a(eVar);
        b(iOException);
        if (a(iOException)) {
            return 3;
        }
        int i = k() > this.E ? 1 : 0;
        b(eVar);
        this.E = k();
        return i;
    }

    @Override // supwisdom.z40
    public f50 a(int i, int i2) {
        x20 x20Var = this.o.get(i);
        if (x20Var != null) {
            return x20Var;
        }
        x20 x20Var2 = new x20(this.g);
        x20Var2.a(this);
        this.o.put(i, x20Var2);
        return x20Var2;
    }

    @Override // supwisdom.z40
    public void a() {
        this.r = true;
        this.n.post(this.l);
    }

    @Override // supwisdom.z40
    public void a(e50 e50Var) {
        this.q = e50Var;
        this.n.post(this.l);
    }

    @Override // supwisdom.x20.d
    public void a(com.google.android.exoplayer2.j jVar) {
        this.n.post(this.l);
    }

    public final void a(e eVar) {
        if (this.B == -1) {
            this.B = eVar.i;
        }
    }

    public final boolean a(IOException iOException) {
        return iOException instanceof com.google.android.exoplayer2.source.n;
    }
}
