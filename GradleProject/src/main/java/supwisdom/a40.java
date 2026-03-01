package supwisdom;

import supwisdom.k10;
import supwisdom.u40;

/* JADX INFO: compiled from: Ac3Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class a40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n80 f6853a;
    public final o80 b;
    public final String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f50 f6854e;
    public int f;
    public int g;
    public boolean h;
    public long i;
    public com.google.android.exoplayer2.j j;
    public int k;
    public long l;

    public a40() {
        this(null);
    }

    @Override // supwisdom.g40
    public void a() {
        this.f = 0;
        this.g = 0;
        this.h = false;
    }

    @Override // supwisdom.g40
    public void b() {
    }

    public final boolean b(o80 o80Var) {
        while (true) {
            if (o80Var.b() <= 0) {
                return false;
            }
            if (this.h) {
                int iG = o80Var.g();
                if (iG == 119) {
                    this.h = false;
                    return true;
                }
                this.h = iG == 11;
            } else {
                this.h = o80Var.g() == 11;
            }
        }
    }

    public final void c() {
        this.f6853a.a(0);
        k10.b bVarA = k10.a(this.f6853a);
        com.google.android.exoplayer2.j jVar = this.j;
        if (jVar == null || bVarA.c != jVar.r || bVarA.b != jVar.s || bVarA.f8110a != jVar.f) {
            com.google.android.exoplayer2.j jVarA = com.google.android.exoplayer2.j.a(this.d, bVarA.f8110a, null, -1, -1, bVarA.c, bVarA.b, null, null, 0, this.c);
            this.j = jVarA;
            this.f6854e.a(jVarA);
        }
        this.k = bVarA.d;
        this.i = (((long) bVarA.f8111e) * 1000000) / ((long) this.j.s);
    }

    public a40(String str) {
        n80 n80Var = new n80(new byte[8]);
        this.f6853a = n80Var;
        this.b = new o80(n80Var.f8483a);
        this.f = 0;
        this.c = str;
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.d = dVar.c();
        this.f6854e = z40Var.a(dVar.b(), 1);
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        this.l = j;
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        while (o80Var.b() > 0) {
            int i = this.f;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int iMin = Math.min(o80Var.b(), this.k - this.g);
                        this.f6854e.a(o80Var, iMin);
                        int i2 = this.g + iMin;
                        this.g = i2;
                        int i3 = this.k;
                        if (i2 == i3) {
                            this.f6854e.a(this.l, 1, i3, 0, null);
                            this.l += this.i;
                            this.f = 0;
                        }
                    }
                } else if (a(o80Var, this.b.f8644a, 8)) {
                    c();
                    this.b.c(0);
                    this.f6854e.a(this.b, 8);
                    this.f = 2;
                }
            } else if (b(o80Var)) {
                this.f = 1;
                byte[] bArr = this.b.f8644a;
                bArr[0] = 11;
                bArr[1] = 119;
                this.g = 2;
            }
        }
    }

    public final boolean a(o80 o80Var, byte[] bArr, int i) {
        int iMin = Math.min(o80Var.b(), i - this.g);
        o80Var.a(bArr, this.g, iMin);
        int i2 = this.g + iMin;
        this.g = i2;
        return i2 == i;
    }
}
