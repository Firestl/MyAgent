package supwisdom;

import java.io.IOException;
import supwisdom.e50;

/* JADX INFO: compiled from: RawCcExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class x30 implements y30 {
    public static final int i = x80.g("RCC\u0001");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.android.exoplayer2.j f9705a;
    public f50 c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9706e;
    public long f;
    public int g;
    public int h;
    public final o80 b = new o80(9);
    public int d = 0;

    public x30(com.google.android.exoplayer2.j jVar) {
        this.f9705a = jVar;
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        z40Var.a(new e50.a(-9223372036854775807L));
        this.c = z40Var.a(0, 3);
        z40Var.a();
        this.c.a(this.f9705a);
    }

    public final boolean b(v40 v40Var) throws InterruptedException, IOException {
        this.b.a();
        if (!v40Var.a(this.b.f8644a, 0, 8, true)) {
            return false;
        }
        if (this.b.n() != i) {
            throw new IOException("Input not RawCC");
        }
        this.f9706e = this.b.g();
        return true;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public final boolean c(v40 v40Var) throws InterruptedException, IOException {
        this.b.a();
        int i2 = this.f9706e;
        if (i2 == 0) {
            if (!v40Var.a(this.b.f8644a, 0, 5, true)) {
                return false;
            }
            this.f = (this.b.l() * 1000) / 45;
        } else {
            if (i2 != 1) {
                throw new com.google.android.exoplayer2.n("Unsupported version number: " + this.f9706e);
            }
            if (!v40Var.a(this.b.f8644a, 0, 9, true)) {
                return false;
            }
            this.f = this.b.p();
        }
        this.g = this.b.g();
        this.h = 0;
        return true;
    }

    public final void d(v40 v40Var) throws InterruptedException, IOException {
        while (this.g > 0) {
            this.b.a();
            v40Var.b(this.b.f8644a, 0, 3);
            this.c.a(this.b, 3);
            this.h += 3;
            this.g--;
        }
        int i2 = this.h;
        if (i2 > 0) {
            this.c.a(this.f, 1, i2, 0, null);
        }
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        this.b.a();
        v40Var.c(this.b.f8644a, 0, 8);
        return this.b.n() == i;
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        while (true) {
            int i2 = this.d;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2) {
                        d(v40Var);
                        this.d = 1;
                        return 0;
                    }
                    throw new IllegalStateException();
                }
                if (c(v40Var)) {
                    this.d = 2;
                } else {
                    this.d = 0;
                    return -1;
                }
            } else {
                if (!b(v40Var)) {
                    return -1;
                }
                this.d = 1;
            }
        }
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.d = 0;
    }
}
