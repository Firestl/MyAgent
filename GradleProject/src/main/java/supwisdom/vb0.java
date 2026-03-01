package supwisdom;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.i.r;
import java.io.IOException;
import java.util.ArrayList;
import supwisdom.b80;
import supwisdom.c80;
import supwisdom.k90;
import supwisdom.s70;
import supwisdom.sb0;
import supwisdom.tb0;
import supwisdom.ua0;

/* JADX INFO: compiled from: SsMediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class vb0 implements r.a<c80<sb0>>, ua0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f9499a;
    public final s70.a b;
    public final tb0.a c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f9500e;
    public final k90.a f;
    public final c80.a<? extends sb0> g;
    public final ArrayList<ub0> h;
    public ua0.a i;
    public s70 j;
    public com.google.android.exoplayer2.i.r k;
    public b80 l;
    public long m;
    public sb0 n;
    public Handler o;

    /* JADX INFO: compiled from: SsMediaSource.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            vb0.this.e();
        }
    }

    public vb0(Uri uri, s70.a aVar, tb0.a aVar2, Handler handler, k90 k90Var) {
        this(uri, aVar, aVar2, 3, com.igexin.push.config.c.k, handler, k90Var);
    }

    @Override // supwisdom.ua0
    public void b() {
        this.i = null;
        this.n = null;
        this.j = null;
        this.m = 0L;
        com.google.android.exoplayer2.i.r rVar = this.k;
        if (rVar != null) {
            rVar.c();
            this.k = null;
        }
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.o = null;
        }
    }

    public final void c() {
        ob0 ob0Var;
        sb0 sb0Var;
        for (int i = 0; i < this.h.size(); i++) {
            this.h.get(i).a(this.n);
        }
        sb0 sb0Var2 = this.n;
        if (sb0Var2.f9144a) {
            long jMax = Long.MIN_VALUE;
            long jMax2 = Long.MAX_VALUE;
            int i2 = 0;
            while (true) {
                sb0Var = this.n;
                sb0.b[] bVarArr = sb0Var.c;
                if (i2 >= bVarArr.length) {
                    break;
                }
                sb0.b bVar = bVarArr[i2];
                if (bVar.d > 0) {
                    jMax2 = Math.min(jMax2, bVar.a(0));
                    jMax = Math.max(jMax, bVar.a(bVar.d - 1) + bVar.b(bVar.d - 1));
                }
                i2++;
            }
            if (jMax2 == Long.MAX_VALUE) {
                ob0Var = new ob0(-9223372036854775807L, false);
            } else {
                long j = sb0Var.f9145e;
                if (j != -9223372036854775807L && j > 0) {
                    jMax2 = Math.max(jMax2, jMax - j);
                }
                long j2 = jMax2;
                long j3 = jMax - j2;
                long jB = j3 - b20.b(this.f9500e);
                if (jB < 5000000) {
                    jB = Math.min(5000000L, j3 / 2);
                }
                ob0Var = new ob0(-9223372036854775807L, j3, j2, jB, true, true);
            }
        } else {
            ob0Var = new ob0(this.n.d, sb0Var2.d != -9223372036854775807L);
        }
        this.i.a(ob0Var, this.n);
    }

    public final void d() {
        if (this.n.f9144a) {
            this.o.postDelayed(new a(), Math.max(0L, (this.m + 5000) - SystemClock.elapsedRealtime()));
        }
    }

    public final void e() {
        c80 c80Var = new c80(this.j, this.f9499a, 4, this.g);
        this.f.a(c80Var.f7160a, c80Var.b, this.k.a(c80Var, this, this.d));
    }

    public vb0(Uri uri, s70.a aVar, tb0.a aVar2, int i, long j, Handler handler, k90 k90Var) {
        this(uri, aVar, new com.google.android.exoplayer2.source.smoothstreaming.a.b(), aVar2, i, j, handler, k90Var);
    }

    public vb0(Uri uri, s70.a aVar, c80.a<? extends sb0> aVar2, tb0.a aVar3, int i, long j, Handler handler, k90 k90Var) {
        this(null, uri, aVar, aVar2, aVar3, i, j, handler, k90Var);
    }

    public vb0(sb0 sb0Var, Uri uri, s70.a aVar, c80.a<? extends sb0> aVar2, tb0.a aVar3, int i, long j, Handler handler, k90 k90Var) {
        e80.b(sb0Var == null || !sb0Var.f9144a);
        this.n = sb0Var;
        if (uri == null) {
            uri = null;
        } else if (!x80.d(uri.getLastPathSegment()).equals("manifest")) {
            uri = Uri.withAppendedPath(uri, "Manifest");
        }
        this.f9499a = uri;
        this.b = aVar;
        this.g = aVar2;
        this.c = aVar3;
        this.d = i;
        this.f9500e = j;
        this.f = new k90.a(handler, k90Var);
        this.h = new ArrayList<>();
    }

    @Override // supwisdom.ua0
    public void a(j50 j50Var, boolean z, ua0.a aVar) {
        this.i = aVar;
        if (this.n != null) {
            this.l = new b80.a();
            c();
            return;
        }
        this.j = this.b.a();
        com.google.android.exoplayer2.i.r rVar = new com.google.android.exoplayer2.i.r("Loader:Manifest");
        this.k = rVar;
        this.l = rVar;
        this.o = new Handler();
        e();
    }

    @Override // supwisdom.ua0
    public void a() throws IOException {
        this.l.d();
    }

    @Override // supwisdom.ua0
    public ta0 a(int i, q70 q70Var, long j) {
        e80.a(i == 0);
        ub0 ub0Var = new ub0(this.n, this.c, this.d, this.f, this.l, q70Var);
        this.h.add(ub0Var);
        return ub0Var;
    }

    @Override // supwisdom.ua0
    public void a(ta0 ta0Var) {
        ((ub0) ta0Var).a();
        this.h.remove(ta0Var);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(c80<sb0> c80Var, long j, long j2) {
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e());
        this.n = c80Var.d();
        this.m = j - j2;
        c();
        d();
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(c80<sb0> c80Var, long j, long j2, boolean z) {
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e());
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public int a(c80<sb0> c80Var, long j, long j2, IOException iOException) {
        boolean z = iOException instanceof com.google.android.exoplayer2.n;
        this.f.a(c80Var.f7160a, c80Var.b, j, j2, c80Var.e(), iOException, z);
        return z ? 3 : 0;
    }
}
