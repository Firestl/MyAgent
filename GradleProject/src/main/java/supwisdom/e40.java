package supwisdom;

import supwisdom.u40;

/* JADX INFO: compiled from: DtsReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f7421a;
    public final String b;
    public String c;
    public f50 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7422e;
    public int f;
    public int g;
    public long h;
    public com.google.android.exoplayer2.j i;
    public int j;
    public long k;

    public e40(String str) {
        o80 o80Var = new o80(new byte[15]);
        this.f7421a = o80Var;
        byte[] bArr = o80Var.f8644a;
        bArr[0] = 127;
        bArr[1] = -2;
        bArr[2] = com.igexin.c.a.d.g.n;
        bArr[3] = 1;
        this.f7422e = 0;
        this.b = str;
    }

    @Override // supwisdom.g40
    public void a() {
        this.f7422e = 0;
        this.f = 0;
        this.g = 0;
    }

    @Override // supwisdom.g40
    public void b() {
    }

    public final boolean b(o80 o80Var) {
        while (o80Var.b() > 0) {
            int i = this.g << 8;
            this.g = i;
            int iG = i | o80Var.g();
            this.g = iG;
            if (iG == 2147385345) {
                this.g = 0;
                return true;
            }
        }
        return false;
    }

    public final void c() {
        byte[] bArr = this.f7421a.f8644a;
        if (this.i == null) {
            com.google.android.exoplayer2.j jVarA = o10.a(bArr, this.c, this.b, null);
            this.i = jVarA;
            this.d.a(jVarA);
        }
        this.j = o10.b(bArr);
        this.h = (int) ((((long) o10.a(bArr)) * 1000000) / ((long) this.i.s));
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.c = dVar.c();
        this.d = z40Var.a(dVar.b(), 1);
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        this.k = j;
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        while (o80Var.b() > 0) {
            int i = this.f7422e;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int iMin = Math.min(o80Var.b(), this.j - this.f);
                        this.d.a(o80Var, iMin);
                        int i2 = this.f + iMin;
                        this.f = i2;
                        int i3 = this.j;
                        if (i2 == i3) {
                            this.d.a(this.k, 1, i3, 0, null);
                            this.k += this.h;
                            this.f7422e = 0;
                        }
                    }
                } else if (a(o80Var, this.f7421a.f8644a, 15)) {
                    c();
                    this.f7421a.c(0);
                    this.d.a(this.f7421a, 15);
                    this.f7422e = 2;
                }
            } else if (b(o80Var)) {
                this.f = 4;
                this.f7422e = 1;
            }
        }
    }

    public final boolean a(o80 o80Var, byte[] bArr, int i) {
        int iMin = Math.min(o80Var.b(), i - this.f);
        o80Var.a(bArr, this.f, iMin);
        int i2 = this.f + iMin;
        this.f = i2;
        return i2 == i;
    }
}
