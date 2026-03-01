package supwisdom;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.i.r;
import com.google.gson.internal.bind.util.ISO8601Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import supwisdom.aa0;
import supwisdom.b80;
import supwisdom.c80;
import supwisdom.k90;
import supwisdom.s70;
import supwisdom.ua0;
import supwisdom.yb0;

/* JADX INFO: compiled from: DashMediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class na0 implements ua0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8488a;
    public final s70.a b;
    public final aa0.a c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f8489e;
    public final k90.a f;
    public final c80.a<? extends ca0> g;
    public final e h;
    public final Object i;
    public final SparseArray<ma0> j;
    public final Runnable k;
    public final Runnable l;
    public ua0.a m;
    public s70 n;
    public com.google.android.exoplayer2.i.r o;
    public b80 p;
    public Uri q;
    public long r;
    public long s;
    public ca0 t;
    public Handler u;
    public long v;
    public int w;

    /* JADX INFO: compiled from: DashMediaSource.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            na0.this.c();
        }
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            na0.this.a(false);
        }
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public static final class d implements c80.a<Long> {
        public d() {
        }

        @Override // supwisdom.c80.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Long a(Uri uri, InputStream inputStream) throws IOException {
            String line = new BufferedReader(new InputStreamReader(inputStream)).readLine();
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ISO8601Utils.UTC_ID));
                return Long.valueOf(simpleDateFormat.parse(line).getTime());
            } catch (ParseException e2) {
                throw new com.google.android.exoplayer2.n(e2);
            }
        }

        public /* synthetic */ d(a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public final class e implements r.a<c80<ca0>> {
        public e() {
        }

        public /* synthetic */ e(na0 na0Var, a aVar) {
            this();
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public void a(c80<ca0> c80Var, long j, long j2) {
            na0.this.a(c80Var, j, j2);
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public void a(c80<ca0> c80Var, long j, long j2, boolean z) {
            na0.this.c(c80Var, j, j2);
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public int a(c80<ca0> c80Var, long j, long j2, IOException iOException) {
            return na0.this.a(c80Var, j, j2, iOException);
        }
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f8494a;
        public final long b;
        public final long c;

        public f(boolean z, long j, long j2) {
            this.f8494a = z;
            this.b = j;
            this.c = j2;
        }

        public static f a(ea0 ea0Var, long j) {
            int i;
            int size = ea0Var.c.size();
            int i2 = 0;
            long jMin = Long.MAX_VALUE;
            int i3 = 0;
            boolean z = false;
            boolean zB = false;
            long jMax = 0;
            while (i3 < size) {
                oa0 oa0VarE = ea0Var.c.get(i3).c.get(i2).e();
                if (oa0VarE == null) {
                    return new f(true, 0L, j);
                }
                zB |= oa0VarE.b();
                int iA = oa0VarE.a(j);
                if (iA == 0) {
                    i = i3;
                    z = true;
                    jMax = 0;
                    jMin = 0;
                } else if (z) {
                    i = i3;
                } else {
                    int iA2 = oa0VarE.a();
                    i = i3;
                    jMax = Math.max(jMax, oa0VarE.a(iA2));
                    if (iA != -1) {
                        int i4 = (iA2 + iA) - 1;
                        jMin = Math.min(jMin, oa0VarE.a(i4) + oa0VarE.a(i4, j));
                    }
                }
                i3 = i + 1;
                i2 = 0;
            }
            return new f(zB, jMax, jMin);
        }
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public final class g implements r.a<c80<Long>> {
        public g() {
        }

        public /* synthetic */ g(na0 na0Var, a aVar) {
            this();
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public void a(c80<Long> c80Var, long j, long j2) {
            na0.this.b(c80Var, j, j2);
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public void a(c80<Long> c80Var, long j, long j2, boolean z) {
            na0.this.c(c80Var, j, j2);
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public int a(c80<Long> c80Var, long j, long j2, IOException iOException) {
            return na0.this.b(c80Var, j, j2, iOException);
        }
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public static final class h implements c80.a<Long> {
        public h() {
        }

        @Override // supwisdom.c80.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Long a(Uri uri, InputStream inputStream) throws IOException {
            return Long.valueOf(x80.f(new BufferedReader(new InputStreamReader(inputStream)).readLine()));
        }

        public /* synthetic */ h(a aVar) {
            this();
        }
    }

    public na0(Uri uri, s70.a aVar, aa0.a aVar2, Handler handler, k90 k90Var) {
        this(uri, aVar, aVar2, 3, -1L, handler, k90Var);
    }

    @Override // supwisdom.ua0
    public void b() {
        this.n = null;
        this.p = null;
        com.google.android.exoplayer2.i.r rVar = this.o;
        if (rVar != null) {
            rVar.c();
            this.o = null;
        }
        this.r = 0L;
        this.s = 0L;
        this.t = null;
        Handler handler = this.u;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.u = null;
        }
        this.v = 0L;
        this.j.clear();
    }

    public void c(c80<?> c80Var, long j, long j2) {
        this.f.b(c80Var.f7160a, c80Var.b, j, j2, c80Var.e());
    }

    public final void d() {
        ca0 ca0Var = this.t;
        if (ca0Var.c) {
            long j = ca0Var.d;
            if (j == 0) {
                j = 5000;
            }
            this.u.postDelayed(this.k, Math.max(0L, (this.r + j) - SystemClock.elapsedRealtime()));
        }
    }

    public final long e() {
        return this.v != 0 ? b20.b(SystemClock.elapsedRealtime() + this.v) : b20.b(System.currentTimeMillis());
    }

    public na0(Uri uri, s70.a aVar, aa0.a aVar2, int i, long j, Handler handler, k90 k90Var) {
        this(uri, aVar, new da0(), aVar2, i, j, handler, k90Var);
    }

    public na0(Uri uri, s70.a aVar, c80.a<? extends ca0> aVar2, aa0.a aVar3, int i, long j, Handler handler, k90 k90Var) {
        this(null, uri, aVar, aVar2, aVar3, i, j, handler, k90Var);
    }

    @Override // supwisdom.ua0
    public void a(j50 j50Var, boolean z, ua0.a aVar) {
        this.m = aVar;
        if (this.f8488a) {
            this.p = new b80.a();
            a(false);
            return;
        }
        this.n = this.b.a();
        com.google.android.exoplayer2.i.r rVar = new com.google.android.exoplayer2.i.r("Loader:DashMediaSource");
        this.o = rVar;
        this.p = rVar;
        this.u = new Handler();
        c();
    }

    public na0(ca0 ca0Var, Uri uri, s70.a aVar, c80.a<? extends ca0> aVar2, aa0.a aVar3, int i, long j, Handler handler, k90 k90Var) {
        this.t = ca0Var;
        this.q = uri;
        this.b = aVar;
        this.g = aVar2;
        this.c = aVar3;
        this.d = i;
        this.f8489e = j;
        this.f8488a = ca0Var != null;
        this.f = new k90.a(handler, k90Var);
        this.i = new Object();
        this.j = new SparseArray<>();
        a aVar4 = null;
        if (this.f8488a) {
            e80.b(!ca0Var.c);
            this.h = null;
            this.k = null;
            this.l = null;
            return;
        }
        this.h = new e(this, aVar4);
        this.k = new a();
        this.l = new b();
    }

    public final void c() {
        Uri uri;
        synchronized (this.i) {
            uri = this.q;
        }
        a(new c80(this.n, uri, 4, this.g), this.h, this.d);
    }

    /* JADX INFO: compiled from: DashMediaSource.java */
    public static final class c extends yb0 {
        public final long b;
        public final long c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f8492e;
        public final long f;
        public final long g;
        public final ca0 h;

        public c(long j, long j2, int i, long j3, long j4, long j5, ca0 ca0Var) {
            this.b = j;
            this.c = j2;
            this.d = i;
            this.f8492e = j3;
            this.f = j4;
            this.g = j5;
            this.h = ca0Var;
        }

        @Override // supwisdom.yb0
        public yb0.b a(int i, yb0.b bVar, boolean z) {
            e80.a(i, 0, this.h.a());
            Integer numValueOf = null;
            String str = z ? this.h.a(i).f7459a : null;
            if (z) {
                int i2 = this.d;
                e80.a(i, 0, this.h.a());
                numValueOf = Integer.valueOf(i2 + i);
            }
            bVar.a(str, numValueOf, 0, this.h.c(i), b20.b(this.h.a(i).b - this.h.a(0).b) - this.f8492e, false);
            return bVar;
        }

        @Override // supwisdom.yb0
        public int b() {
            return 1;
        }

        @Override // supwisdom.yb0
        public int c() {
            return this.h.a();
        }

        @Override // supwisdom.yb0
        public yb0.c a(int i, yb0.c cVar, boolean z, long j) {
            e80.a(i, 0, 1);
            long jA = a(j);
            cVar.a(null, this.b, this.c, true, this.h.c, jA, this.f, 0, r2.a() - 1, this.f8492e);
            return cVar;
        }

        @Override // supwisdom.yb0
        public int a(Object obj) {
            int iIntValue;
            int i;
            if ((obj instanceof Integer) && (iIntValue = ((Integer) obj).intValue()) >= (i = this.d) && iIntValue < i + c()) {
                return iIntValue - this.d;
            }
            return -1;
        }

        public final long a(long j) {
            oa0 oa0VarE;
            long j2 = this.g;
            if (!this.h.c) {
                return j2;
            }
            if (j > 0) {
                j2 += j;
                if (j2 > this.f) {
                    return -9223372036854775807L;
                }
            }
            long j3 = this.f8492e + j2;
            long jC = this.h.c(0);
            int i = 0;
            while (i < this.h.a() - 1 && j3 >= jC) {
                j3 -= jC;
                i++;
                jC = this.h.c(i);
            }
            ea0 ea0VarA = this.h.a(i);
            int iA = ea0VarA.a(2);
            return (iA == -1 || (oa0VarE = ea0VarA.c.get(iA).c.get(0).e()) == null || oa0VarE.a(jC) == 0) ? j2 : (j2 + oa0VarE.a(oa0VarE.a(j3, jC))) - j3;
        }
    }

    @Override // supwisdom.ua0
    public void a() throws IOException {
        this.p.d();
    }

    @Override // supwisdom.ua0
    public ta0 a(int i, q70 q70Var, long j) {
        ma0 ma0Var = new ma0(this.w + i, this.t, i, this.c, this.d, this.f.a(this.t.a(i).b), this.v, this.p, q70Var);
        this.j.put(ma0Var.f8369a, ma0Var);
        return ma0Var;
    }

    public void b(c80<Long> c80Var, long j, long j2) {
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e());
        a(c80Var.d().longValue() - j);
    }

    @Override // supwisdom.ua0
    public void a(ta0 ta0Var) {
        ma0 ma0Var = (ma0) ta0Var;
        ma0Var.a();
        this.j.remove(ma0Var.f8369a);
    }

    public int b(c80<Long> c80Var, long j, long j2, IOException iOException) {
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e(), iOException, true);
        a(iOException);
        return 2;
    }

    public void a(c80<ca0> c80Var, long j, long j2) {
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e());
        ca0 ca0VarD = c80Var.d();
        ca0 ca0Var = this.t;
        int i = 0;
        int iA = ca0Var == null ? 0 : ca0Var.a();
        long j3 = ca0VarD.a(0).b;
        while (i < iA && this.t.a(i).b < j3) {
            i++;
        }
        if (iA - i > ca0VarD.a()) {
            Log.w("DashMediaSource", "Out of sync manifest");
            d();
            return;
        }
        this.t = ca0VarD;
        this.r = j - j2;
        this.s = j;
        if (ca0VarD.h != null) {
            synchronized (this.i) {
                if (c80Var.f7160a.f9382a == this.q) {
                    this.q = this.t.h;
                }
            }
        }
        if (iA == 0) {
            la0 la0Var = this.t.g;
            if (la0Var != null) {
                a(la0Var);
                return;
            } else {
                a(true);
                return;
            }
        }
        this.w += i;
        a(true);
    }

    public final void b(la0 la0Var) {
        try {
            a(x80.f(la0Var.b) - this.s);
        } catch (com.google.android.exoplayer2.n e2) {
            a(e2);
        }
    }

    public int a(c80<ca0> c80Var, long j, long j2, IOException iOException) {
        boolean z = iOException instanceof com.google.android.exoplayer2.n;
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e(), iOException, z);
        return z ? 3 : 0;
    }

    public final void a(la0 la0Var) {
        String str = la0Var.f8267a;
        if (x80.a(str, "urn:mpeg:dash:utc:direct:2012")) {
            b(la0Var);
            return;
        }
        a aVar = null;
        if (x80.a(str, "urn:mpeg:dash:utc:http-iso:2014")) {
            a(la0Var, new d(aVar));
        } else if (!x80.a(str, "urn:mpeg:dash:utc:http-xsdate:2012") && !x80.a(str, "urn:mpeg:dash:utc:http-xsdate:2014")) {
            a(new IOException("Unsupported UTC timing scheme"));
        } else {
            a(la0Var, new h(aVar));
        }
    }

    public final void a(la0 la0Var, c80.a<Long> aVar) {
        a(new c80(this.n, Uri.parse(la0Var.b), 5, aVar), new g(this, null), 1);
    }

    public final void a(long j) {
        this.v = j;
        a(true);
    }

    public final void a(IOException iOException) {
        Log.e("DashMediaSource", "Failed to resolve UtcTiming element.", iOException);
        a(true);
    }

    public final void a(boolean z) {
        long j;
        boolean z2;
        long jC;
        for (int i = 0; i < this.j.size(); i++) {
            int iKeyAt = this.j.keyAt(i);
            if (iKeyAt >= this.w) {
                this.j.valueAt(i).a(this.t, iKeyAt - this.w);
            }
        }
        int iA = this.t.a() - 1;
        f fVarA = f.a(this.t.a(0), this.t.c(0));
        f fVarA2 = f.a(this.t.a(iA), this.t.c(iA));
        long j2 = fVarA.b;
        long jMin = fVarA2.c;
        long jB = 0;
        if (!this.t.c || fVarA2.f8494a) {
            j = j2;
            z2 = false;
        } else {
            jMin = Math.min((e() - b20.b(this.t.f7168a)) - b20.b(this.t.a(iA).b), jMin);
            long j3 = this.t.f7169e;
            if (j3 != -9223372036854775807L) {
                long jB2 = jMin - b20.b(j3);
                while (jB2 < 0 && iA > 0) {
                    iA--;
                    jB2 += this.t.c(iA);
                }
                if (iA == 0) {
                    jC = Math.max(j2, jB2);
                } else {
                    jC = this.t.c(0);
                }
                j2 = jC;
            }
            j = j2;
            z2 = true;
        }
        long jC2 = jMin - j;
        for (int i2 = 0; i2 < this.t.a() - 1; i2++) {
            jC2 += this.t.c(i2);
        }
        ca0 ca0Var = this.t;
        if (ca0Var.c) {
            long j4 = this.f8489e;
            if (j4 == -1) {
                long j5 = ca0Var.f;
                if (j5 == -9223372036854775807L) {
                    j5 = com.igexin.push.config.c.k;
                }
                j4 = j5;
            }
            jB = jC2 - b20.b(j4);
            if (jB < 5000000) {
                jB = Math.min(5000000L, jC2 / 2);
            }
        }
        ca0 ca0Var2 = this.t;
        long jA = ca0Var2.f7168a + ca0Var2.a(0).b + b20.a(j);
        ca0 ca0Var3 = this.t;
        this.m.a(new c(ca0Var3.f7168a, jA, this.w, j, jC2, jB, ca0Var3), this.t);
        if (this.f8488a) {
            return;
        }
        this.u.removeCallbacks(this.l);
        if (z2) {
            this.u.postDelayed(this.l, 5000L);
        }
        if (z) {
            d();
        }
    }

    public final <T> void a(c80<T> c80Var, r.a<c80<T>> aVar, int i) {
        this.f.a(c80Var.f7160a, c80Var.b, this.o.a(c80Var, aVar, i));
    }
}
