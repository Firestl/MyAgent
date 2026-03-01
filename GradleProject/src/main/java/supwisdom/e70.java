package supwisdom;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import supwisdom.j50;
import supwisdom.o70;
import supwisdom.yb0;

/* JADX INFO: compiled from: ExoPlayerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class e70 implements j50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m70 f7442a;
    public final l70 b;
    public final Handler c;
    public final o70 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CopyOnWriteArraySet<j50.a> f7443e;
    public final yb0.c f;
    public final yb0.b g;
    public boolean h;
    public boolean i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public yb0 n;
    public Object o;
    public qb0 p;
    public l70 q;
    public g90 r;
    public o70.b s;
    public int t;
    public long u;

    /* JADX INFO: compiled from: ExoPlayerImpl.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            e70.this.a(message);
        }
    }

    @SuppressLint({"HandlerLeak"})
    public e70(h90[] h90VarArr, m70 m70Var, f90 f90Var) {
        Log.i("ExoPlayerImpl", "Init ExoPlayerLib/2.4.1 [" + x80.f9719e + Operators.ARRAY_END_STR);
        e80.b(h90VarArr.length > 0);
        e80.a(h90VarArr);
        e80.a(m70Var);
        this.f7442a = m70Var;
        this.i = false;
        this.j = 1;
        this.f7443e = new CopyOnWriteArraySet<>();
        this.b = new l70(new k70[h90VarArr.length]);
        this.n = yb0.f9863a;
        this.f = new yb0.c();
        this.g = new yb0.b();
        this.p = qb0.d;
        this.q = this.b;
        this.r = g90.d;
        this.c = new a(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        o70.b bVar = new o70.b(0, 0L);
        this.s = bVar;
        this.d = new o70(h90VarArr, m70Var, f90Var, this.i, this.c, bVar, this);
    }

    @Override // supwisdom.j50
    public void a(j50.a aVar) {
        this.f7443e.remove(aVar);
    }

    @Override // supwisdom.j50
    public void b(j50.a aVar) {
        this.f7443e.add(aVar);
    }

    @Override // supwisdom.j50
    public g90 c() {
        return this.r;
    }

    @Override // supwisdom.j50
    public void d() {
        this.d.a();
        this.c.removeCallbacksAndMessages(null);
    }

    @Override // supwisdom.j50
    public long e() {
        if (this.n.a()) {
            return -9223372036854775807L;
        }
        return this.n.a(h(), this.f).b();
    }

    @Override // supwisdom.j50
    public long f() {
        if (this.n.a() || this.k > 0) {
            return this.u;
        }
        this.n.a(this.s.f8639a, this.g);
        return this.g.c() + b20.a(this.s.c);
    }

    @Override // supwisdom.j50
    public int g() {
        if (this.n.a()) {
            return 0;
        }
        long jI = i();
        long jE = e();
        if (jI == -9223372036854775807L || jE == -9223372036854775807L) {
            return 0;
        }
        return (int) (jE != 0 ? (jI * 100) / jE : 100L);
    }

    public int h() {
        return (this.n.a() || this.k > 0) ? this.t : this.n.a(this.s.f8639a, this.g).c;
    }

    public long i() {
        if (this.n.a() || this.k > 0) {
            return this.u;
        }
        this.n.a(this.s.f8639a, this.g);
        return this.g.c() + b20.a(this.s.d);
    }

    @Override // supwisdom.j50
    public int a() {
        return this.j;
    }

    @Override // supwisdom.j50
    public boolean b() {
        return this.i;
    }

    @Override // supwisdom.j50
    public void a(ua0 ua0Var) {
        a(ua0Var, true, true);
    }

    @Override // supwisdom.j50
    public void b(j50.c... cVarArr) {
        this.d.b(cVarArr);
    }

    @Override // supwisdom.j50
    public void a(ua0 ua0Var, boolean z, boolean z2) {
        if (z2) {
            if (!this.n.a() || this.o != null) {
                this.n = yb0.f9863a;
                this.o = null;
                Iterator<j50.a> it = this.f7443e.iterator();
                while (it.hasNext()) {
                    it.next().onTimelineChanged(this.n, this.o);
                }
            }
            if (this.h) {
                this.h = false;
                this.p = qb0.d;
                this.q = this.b;
                this.f7442a.a((Object) null);
                Iterator<j50.a> it2 = this.f7443e.iterator();
                while (it2.hasNext()) {
                    it2.next().onTracksChanged(this.p, this.q);
                }
            }
        }
        this.l++;
        this.d.a(ua0Var, z);
    }

    @Override // supwisdom.j50
    public void a(boolean z) {
        if (this.i != z) {
            this.i = z;
            this.d.a(z);
            Iterator<j50.a> it = this.f7443e.iterator();
            while (it.hasNext()) {
                it.next().onPlayerStateChanged(z, this.j);
            }
        }
    }

    @Override // supwisdom.j50
    public void a(long j) {
        a(h(), j);
    }

    public void a(int i, long j) {
        if (i >= 0 && (this.n.a() || i < this.n.b())) {
            this.k++;
            this.t = i;
            if (!this.n.a()) {
                this.n.a(i, this.f);
                long jA = j == -9223372036854775807L ? this.f.a() : j;
                yb0.c cVar = this.f;
                int i2 = cVar.d;
                long jC = cVar.c() + b20.b(jA);
                long jB = this.n.a(i2, this.g).b();
                while (jB != -9223372036854775807L && jC >= jB && i2 < this.f.f9867e) {
                    jC -= jB;
                    i2++;
                    jB = this.n.a(i2, this.g).b();
                }
            }
            if (j == -9223372036854775807L) {
                this.u = 0L;
                this.d.a(this.n, i, -9223372036854775807L);
                return;
            }
            this.u = j;
            this.d.a(this.n, i, b20.b(j));
            Iterator<j50.a> it = this.f7443e.iterator();
            while (it.hasNext()) {
                it.next().onPositionDiscontinuity();
            }
            return;
        }
        throw new com.google.android.exoplayer2.l(this.n, i, j);
    }

    @Override // supwisdom.j50
    public void a(g90 g90Var) {
        if (g90Var == null) {
            g90Var = g90.d;
        }
        this.d.a(g90Var);
    }

    @Override // supwisdom.j50
    public void a(j50.c... cVarArr) {
        this.d.a(cVarArr);
    }

    public void a(Message message) {
        switch (message.what) {
            case 0:
                this.l--;
                return;
            case 1:
                this.j = message.arg1;
                Iterator<j50.a> it = this.f7443e.iterator();
                while (it.hasNext()) {
                    it.next().onPlayerStateChanged(this.i, this.j);
                }
                return;
            case 2:
                this.m = message.arg1 != 0;
                Iterator<j50.a> it2 = this.f7443e.iterator();
                while (it2.hasNext()) {
                    it2.next().onLoadingChanged(this.m);
                }
                return;
            case 3:
                if (this.l == 0) {
                    n70 n70Var = (n70) message.obj;
                    this.h = true;
                    this.p = n70Var.f8481a;
                    this.q = n70Var.b;
                    this.f7442a.a(n70Var.c);
                    Iterator<j50.a> it3 = this.f7443e.iterator();
                    while (it3.hasNext()) {
                        it3.next().onTracksChanged(this.p, this.q);
                    }
                    return;
                }
                return;
            case 4:
                int i = this.k - 1;
                this.k = i;
                if (i == 0) {
                    this.s = (o70.b) message.obj;
                    if (message.arg1 != 0) {
                        Iterator<j50.a> it4 = this.f7443e.iterator();
                        while (it4.hasNext()) {
                            it4.next().onPositionDiscontinuity();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.k == 0) {
                    this.s = (o70.b) message.obj;
                    Iterator<j50.a> it5 = this.f7443e.iterator();
                    while (it5.hasNext()) {
                        it5.next().onPositionDiscontinuity();
                    }
                    return;
                }
                return;
            case 6:
                o70.d dVar = (o70.d) message.obj;
                this.k -= dVar.d;
                if (this.l == 0) {
                    this.n = dVar.f8641a;
                    this.o = dVar.b;
                    this.s = dVar.c;
                    Iterator<j50.a> it6 = this.f7443e.iterator();
                    while (it6.hasNext()) {
                        it6.next().onTimelineChanged(this.n, this.o);
                    }
                    return;
                }
                return;
            case 7:
                g90 g90Var = (g90) message.obj;
                if (this.r.equals(g90Var)) {
                    return;
                }
                this.r = g90Var;
                Iterator<j50.a> it7 = this.f7443e.iterator();
                while (it7.hasNext()) {
                    it7.next().onPlaybackParametersChanged(g90Var);
                }
                return;
            case 8:
                com.google.android.exoplayer2.e eVar = (com.google.android.exoplayer2.e) message.obj;
                Iterator<j50.a> it8 = this.f7443e.iterator();
                while (it8.hasNext()) {
                    it8.next().onPlayerError(eVar);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
