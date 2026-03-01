package supwisdom;

import android.util.Log;
import supwisdom.u40;

/* JADX INFO: compiled from: PesReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class n40 implements u40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g40 f8475a;
    public final n80 b = new n80(new byte[10]);
    public int c = 0;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public u80 f8476e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public int j;
    public boolean k;
    public long l;

    public n40(g40 g40Var) {
        this.f8475a = g40Var;
    }

    @Override // supwisdom.u40
    public void a(u80 u80Var, z40 z40Var, u40.d dVar) {
        this.f8476e = u80Var;
        this.f8475a.a(z40Var, dVar);
    }

    public final boolean b() {
        this.b.a(0);
        int iC = this.b.c(24);
        if (iC != 1) {
            Log.w("PesReader", "Unexpected start code prefix: " + iC);
            this.j = -1;
            return false;
        }
        this.b.b(8);
        int iC2 = this.b.c(16);
        this.b.b(5);
        this.k = this.b.d();
        this.b.b(2);
        this.f = this.b.d();
        this.g = this.b.d();
        this.b.b(6);
        int iC3 = this.b.c(8);
        this.i = iC3;
        if (iC2 == 0) {
            this.j = -1;
        } else {
            this.j = ((iC2 + 6) - 9) - iC3;
        }
        return true;
    }

    public final void c() {
        this.b.a(0);
        this.l = -9223372036854775807L;
        if (this.f) {
            this.b.b(4);
            long jC = ((long) this.b.c(3)) << 30;
            this.b.b(1);
            long jC2 = jC | ((long) (this.b.c(15) << 15));
            this.b.b(1);
            long jC3 = jC2 | ((long) this.b.c(15));
            this.b.b(1);
            if (!this.h && this.g) {
                this.b.b(4);
                long jC4 = ((long) this.b.c(3)) << 30;
                this.b.b(1);
                long jC5 = jC4 | ((long) (this.b.c(15) << 15));
                this.b.b(1);
                long jC6 = jC5 | ((long) this.b.c(15));
                this.b.b(1);
                this.f8476e.b(jC6);
                this.h = true;
            }
            this.l = this.f8476e.b(jC3);
        }
    }

    @Override // supwisdom.u40
    public final void a() {
        this.c = 0;
        this.d = 0;
        this.h = false;
        this.f8475a.a();
    }

    @Override // supwisdom.u40
    public final void a(o80 o80Var, boolean z) {
        if (z) {
            int i = this.c;
            if (i == 2) {
                Log.w("PesReader", "Unexpected start indicator reading extended header");
            } else if (i == 3) {
                if (this.j != -1) {
                    Log.w("PesReader", "Unexpected start indicator: expected " + this.j + " more bytes");
                }
                this.f8475a.b();
            }
            a(1);
        }
        while (o80Var.b() > 0) {
            int i2 = this.c;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (a(o80Var, this.b.f8483a, Math.min(10, this.i)) && a(o80Var, (byte[]) null, this.i)) {
                            c();
                            this.f8475a.a(this.l, this.k);
                            a(3);
                        }
                    } else if (i2 == 3) {
                        int iB = o80Var.b();
                        int i3 = this.j;
                        int i4 = i3 != -1 ? iB - i3 : 0;
                        if (i4 > 0) {
                            iB -= i4;
                            o80Var.b(o80Var.d() + iB);
                        }
                        this.f8475a.a(o80Var);
                        int i5 = this.j;
                        if (i5 != -1) {
                            int i6 = i5 - iB;
                            this.j = i6;
                            if (i6 == 0) {
                                this.f8475a.b();
                                a(1);
                            }
                        }
                    }
                } else if (a(o80Var, this.b.f8483a, 9)) {
                    a(b() ? 2 : 0);
                }
            } else {
                o80Var.d(o80Var.b());
            }
        }
    }

    public final void a(int i) {
        this.c = i;
        this.d = 0;
    }

    public final boolean a(o80 o80Var, byte[] bArr, int i) {
        int iMin = Math.min(o80Var.b(), i - this.d);
        if (iMin <= 0) {
            return true;
        }
        if (bArr == null) {
            o80Var.d(iMin);
        } else {
            o80Var.a(bArr, this.d, iMin);
        }
        int i2 = this.d + iMin;
        this.d = i2;
        return i2 == i;
    }
}
