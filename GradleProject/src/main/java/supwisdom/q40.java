package supwisdom;

import supwisdom.u40;

/* JADX INFO: compiled from: SectionReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class q40 implements u40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p40 f8874a;
    public final o80 b = new o80(32);
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8875e;
    public boolean f;

    public q40(p40 p40Var) {
        this.f8874a = p40Var;
    }

    @Override // supwisdom.u40
    public void a(u80 u80Var, z40 z40Var, u40.d dVar) {
        this.f8874a.a(u80Var, z40Var, dVar);
        this.f = true;
    }

    @Override // supwisdom.u40
    public void a() {
        this.f = true;
    }

    @Override // supwisdom.u40
    public void a(o80 o80Var, boolean z) {
        int iD = z ? o80Var.d() + o80Var.g() : -1;
        if (this.f) {
            if (!z) {
                return;
            }
            this.f = false;
            o80Var.c(iD);
            this.d = 0;
        }
        while (o80Var.b() > 0) {
            int i = this.d;
            if (i < 3) {
                if (i == 0) {
                    int iG = o80Var.g();
                    o80Var.c(o80Var.d() - 1);
                    if (iG == 255) {
                        this.f = true;
                        return;
                    }
                }
                int iMin = Math.min(o80Var.b(), 3 - this.d);
                o80Var.a(this.b.f8644a, this.d, iMin);
                int i2 = this.d + iMin;
                this.d = i2;
                if (i2 == 3) {
                    this.b.a(3);
                    this.b.d(1);
                    int iG2 = this.b.g();
                    int iG3 = this.b.g();
                    this.f8875e = (iG2 & 128) != 0;
                    this.c = (((iG2 & 15) << 8) | iG3) + 3;
                    int iE = this.b.e();
                    int i3 = this.c;
                    if (iE < i3) {
                        o80 o80Var2 = this.b;
                        byte[] bArr = o80Var2.f8644a;
                        o80Var2.a(Math.min(4098, Math.max(i3, bArr.length * 2)));
                        System.arraycopy(bArr, 0, this.b.f8644a, 0, 3);
                    }
                }
            } else {
                int iMin2 = Math.min(o80Var.b(), this.c - this.d);
                o80Var.a(this.b.f8644a, this.d, iMin2);
                int i4 = this.d + iMin2;
                this.d = i4;
                int i5 = this.c;
                if (i4 != i5) {
                    continue;
                } else {
                    if (this.f8875e) {
                        if (x80.a(this.b.f8644a, 0, i5, -1) != 0) {
                            this.f = true;
                            return;
                        }
                        this.b.a(this.c - 4);
                    } else {
                        this.b.a(i5);
                    }
                    this.f8874a.a(this.b);
                    this.d = 0;
                }
            }
        }
    }
}
