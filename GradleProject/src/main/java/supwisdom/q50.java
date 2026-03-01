package supwisdom;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Arrays;

/* JADX INFO: compiled from: MetadataRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public final class q50 extends t10 implements Handler.Callback {
    public final o50 i;
    public final a j;
    public final Handler k;
    public final e90 l;
    public final p50 m;
    public final com.google.android.exoplayer2.f.a[] n;
    public final long[] o;
    public int p;
    public int q;
    public m50 r;
    public boolean s;

    /* JADX INFO: compiled from: MetadataRenderer.java */
    public interface a {
        void onMetadata(com.google.android.exoplayer2.f.a aVar);
    }

    public q50(a aVar, Looper looper) {
        this(aVar, looper, o50.f8629a);
    }

    @Override // supwisdom.i90
    public int a(com.google.android.exoplayer2.j jVar) {
        return this.i.a(jVar) ? 3 : 0;
    }

    public final void b(com.google.android.exoplayer2.f.a aVar) {
        this.j.onMetadata(aVar);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            throw new IllegalStateException();
        }
        b((com.google.android.exoplayer2.f.a) message.obj);
        return true;
    }

    @Override // supwisdom.h90
    public boolean n() {
        return true;
    }

    @Override // supwisdom.t10
    public void s() {
        x();
        this.r = null;
    }

    @Override // supwisdom.h90
    public boolean u() {
        return this.s;
    }

    public final void x() {
        Arrays.fill(this.n, (Object) null);
        this.p = 0;
        this.q = 0;
    }

    public q50(a aVar, Looper looper, o50 o50Var) {
        super(4);
        e80.a(aVar);
        this.j = aVar;
        this.k = looper == null ? null : new Handler(looper, this);
        e80.a(o50Var);
        this.i = o50Var;
        this.l = new e90();
        this.m = new p50();
        this.n = new com.google.android.exoplayer2.f.a[5];
        this.o = new long[5];
    }

    @Override // supwisdom.t10
    public void a(com.google.android.exoplayer2.j[] jVarArr) throws com.google.android.exoplayer2.e {
        this.r = this.i.b(jVarArr[0]);
    }

    @Override // supwisdom.t10
    public void a(long j, boolean z) {
        x();
        this.s = false;
    }

    @Override // supwisdom.h90
    public void a(long j, long j2) throws com.google.android.exoplayer2.e {
        if (!this.s && this.q < 5) {
            this.m.a();
            if (a(this.l, (y10) this.m, false) == -4) {
                if (this.m.d()) {
                    this.s = true;
                } else if (!this.m.c()) {
                    p50 p50Var = this.m;
                    p50Var.f = this.l.f7455a.w;
                    p50Var.h();
                    try {
                        int i = (this.p + this.q) % 5;
                        this.n[i] = this.r.a(this.m);
                        this.o[i] = this.m.d;
                        this.q++;
                    } catch (com.google.android.exoplayer2.f.c e2) {
                        throw com.google.android.exoplayer2.e.a(e2, v());
                    }
                }
            }
        }
        if (this.q > 0) {
            long[] jArr = this.o;
            int i2 = this.p;
            if (jArr[i2] <= j) {
                a(this.n[i2]);
                com.google.android.exoplayer2.f.a[] aVarArr = this.n;
                int i3 = this.p;
                aVarArr[i3] = null;
                this.p = (i3 + 1) % 5;
                this.q--;
            }
        }
    }

    public final void a(com.google.android.exoplayer2.f.a aVar) {
        Handler handler = this.k;
        if (handler != null) {
            handler.obtainMessage(0, aVar).sendToTarget();
        } else {
            b(aVar);
        }
    }
}
