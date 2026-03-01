package supwisdom;

import java.io.IOException;
import supwisdom.e50;

/* JADX INFO: compiled from: StreamReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public o30 f9116a;
    public f50 b;
    public z40 c;
    public q30 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f9117e;
    public long f;
    public long g;
    public int h;
    public int i;
    public b j;
    public long k;
    public boolean l;
    public boolean m;

    /* JADX INFO: compiled from: StreamReader.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public com.google.android.exoplayer2.j f9118a;
        public q30 b;
    }

    /* JADX INFO: compiled from: StreamReader.java */
    public static final class c implements q30 {
        public c() {
        }

        @Override // supwisdom.q30
        public long a(long j) {
            return 0L;
        }

        @Override // supwisdom.q30
        public long a(v40 v40Var) throws InterruptedException, IOException {
            return -1L;
        }

        @Override // supwisdom.q30
        public e50 c() {
            return new e50.a(-9223372036854775807L);
        }
    }

    public abstract long a(o80 o80Var);

    public void a(z40 z40Var, f50 f50Var) {
        this.c = z40Var;
        this.b = f50Var;
        this.f9116a = new o30();
        a(true);
    }

    public abstract boolean a(o80 o80Var, long j, b bVar) throws InterruptedException, IOException;

    public final int b(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        long jA = this.d.a(v40Var);
        if (jA >= 0) {
            d50Var.f7296a = jA;
            return 1;
        }
        if (jA < -1) {
            c(-(jA + 2));
        }
        if (!this.l) {
            this.c.a(this.d.c());
            this.l = true;
        }
        if (this.k <= 0 && !this.f9116a.a(v40Var)) {
            this.h = 3;
            return -1;
        }
        this.k = 0L;
        o80 o80VarC = this.f9116a.c();
        long jA2 = a(o80VarC);
        if (jA2 >= 0) {
            long j = this.g;
            if (j + jA2 >= this.f9117e) {
                long jA3 = a(j);
                this.b.a(o80VarC, o80VarC.c());
                this.b.a(jA3, 1, o80VarC.c(), 0, null);
                this.f9117e = -1L;
            }
        }
        this.g += jA2;
        return 0;
    }

    public void c(long j) {
        this.g = j;
    }

    public void a(boolean z) {
        if (z) {
            this.j = new b();
            this.f = 0L;
            this.h = 0;
        } else {
            this.h = 1;
        }
        this.f9117e = -1L;
        this.g = 0L;
    }

    public final void a(long j, long j2) {
        this.f9116a.a();
        if (j == 0) {
            a(!this.l);
        } else if (this.h != 0) {
            this.f9117e = this.d.a(j2);
            this.h = 2;
        }
    }

    public final int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        int i = this.h;
        if (i == 0) {
            return a(v40Var);
        }
        if (i != 1) {
            if (i == 2) {
                return b(v40Var, d50Var);
            }
            throw new IllegalStateException();
        }
        v40Var.b((int) this.f);
        this.h = 2;
        return 0;
    }

    public long b(long j) {
        return (((long) this.i) * j) / 1000000;
    }

    public final int a(v40 v40Var) throws InterruptedException, IOException {
        boolean zA = true;
        while (zA) {
            if (!this.f9116a.a(v40Var)) {
                this.h = 3;
                return -1;
            }
            this.k = v40Var.c() - this.f;
            zA = a(this.f9116a.c(), this.f, this.j);
            if (zA) {
                this.f = v40Var.c();
            }
        }
        com.google.android.exoplayer2.j jVar = this.j.f9118a;
        this.i = jVar.s;
        if (!this.m) {
            this.b.a(jVar);
            this.m = true;
        }
        q30 q30Var = this.j.b;
        if (q30Var != null) {
            this.d = q30Var;
        } else if (v40Var.d() == -1) {
            this.d = new c();
        } else {
            p30 p30VarB = this.f9116a.b();
            this.d = new l30(this.f, v40Var.d(), this, p30VarB.f8764e + p30VarB.f, p30VarB.c);
        }
        this.j = null;
        this.h = 2;
        this.f9116a.d();
        return 0;
    }

    public long a(long j) {
        return (j * 1000000) / ((long) this.i);
    }
}
