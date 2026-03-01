package supwisdom;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: TextRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public final class c70 extends t10 implements Handler.Callback {
    public final Handler i;
    public final a j;
    public final z60 k;
    public final e90 l;
    public boolean m;
    public boolean n;
    public int o;
    public com.google.android.exoplayer2.j p;
    public p60 q;
    public a70 r;
    public b70 s;
    public b70 t;
    public int u;

    /* JADX INFO: compiled from: TextRenderer.java */
    public interface a {
        void a(List<y50> list);
    }

    public c70(a aVar, Looper looper) {
        this(aVar, looper, z60.f9971a);
    }

    @Override // supwisdom.i90
    public int a(com.google.android.exoplayer2.j jVar) {
        if (this.k.a(jVar)) {
            return 3;
        }
        return l80.c(jVar.f) ? 1 : 0;
    }

    public final void b(List<y50> list) {
        this.j.a(list);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            throw new IllegalStateException();
        }
        b((List<y50>) message.obj);
        return true;
    }

    @Override // supwisdom.h90
    public boolean n() {
        return true;
    }

    public final void o() {
        x();
        this.q.d();
        this.q = null;
        this.o = 0;
    }

    public final void p() {
        o();
        this.q = this.k.b(this.p);
    }

    @Override // supwisdom.t10
    public void s() {
        this.p = null;
        z();
        o();
    }

    @Override // supwisdom.h90
    public boolean u() {
        return this.n;
    }

    public final void x() {
        this.r = null;
        this.u = -1;
        b70 b70Var = this.s;
        if (b70Var != null) {
            b70Var.f();
            this.s = null;
        }
        b70 b70Var2 = this.t;
        if (b70Var2 != null) {
            b70Var2.f();
            this.t = null;
        }
    }

    public final long y() {
        int i = this.u;
        if (i == -1 || i >= this.s.b()) {
            return Long.MAX_VALUE;
        }
        return this.s.a(this.u);
    }

    public final void z() {
        a(Collections.emptyList());
    }

    public c70(a aVar, Looper looper, z60 z60Var) {
        super(3);
        e80.a(aVar);
        this.j = aVar;
        this.i = looper == null ? null : new Handler(looper, this);
        this.k = z60Var;
        this.l = new e90();
    }

    @Override // supwisdom.t10
    public void a(com.google.android.exoplayer2.j[] jVarArr) throws com.google.android.exoplayer2.e {
        com.google.android.exoplayer2.j jVar = jVarArr[0];
        this.p = jVar;
        if (this.q != null) {
            this.o = 1;
        } else {
            this.q = this.k.b(jVar);
        }
    }

    @Override // supwisdom.t10
    public void a(long j, boolean z) {
        z();
        this.m = false;
        this.n = false;
        if (this.o != 0) {
            p();
        } else {
            x();
            this.q.c();
        }
    }

    @Override // supwisdom.h90
    public void a(long j, long j2) throws Exception {
        boolean z;
        if (this.n) {
            return;
        }
        if (this.t == null) {
            this.q.a(j);
            try {
                this.t = this.q.b();
            } catch (com.google.android.exoplayer2.g.f e2) {
                throw com.google.android.exoplayer2.e.a(e2, v());
            }
        }
        if (d() != 2) {
            return;
        }
        if (this.s != null) {
            long jY = y();
            z = false;
            while (jY <= j) {
                this.u++;
                jY = y();
                z = true;
            }
        } else {
            z = false;
        }
        b70 b70Var = this.t;
        if (b70Var != null) {
            if (b70Var.d()) {
                if (!z && y() == Long.MAX_VALUE) {
                    if (this.o == 2) {
                        p();
                    } else {
                        x();
                        this.n = true;
                    }
                }
            } else if (this.t.b <= j) {
                b70 b70Var2 = this.s;
                if (b70Var2 != null) {
                    b70Var2.f();
                }
                b70 b70Var3 = this.t;
                this.s = b70Var3;
                this.t = null;
                this.u = b70Var3.a(j);
                z = true;
            }
        }
        if (z) {
            a(this.s.b(j));
        }
        if (this.o == 2) {
            return;
        }
        while (!this.m) {
            try {
                if (this.r == null) {
                    a70 a70VarA = this.q.a();
                    this.r = a70VarA;
                    if (a70VarA == null) {
                        return;
                    }
                }
                if (this.o == 1) {
                    this.r.b(4);
                    this.q.a(this.r);
                    this.r = null;
                    this.o = 2;
                    return;
                }
                int iA = a(this.l, (y10) this.r, false);
                if (iA == -4) {
                    if (this.r.d()) {
                        this.m = true;
                    } else {
                        this.r.f = this.l.f7455a.w;
                        this.r.h();
                    }
                    this.q.a(this.r);
                    this.r = null;
                } else if (iA == -3) {
                    return;
                }
            } catch (com.google.android.exoplayer2.g.f e3) {
                throw com.google.android.exoplayer2.e.a(e3, v());
            }
        }
    }

    public final void a(List<y50> list) {
        Handler handler = this.i;
        if (handler != null) {
            handler.obtainMessage(0, list).sendToTarget();
        } else {
            b(list);
        }
    }
}
