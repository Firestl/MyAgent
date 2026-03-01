package supwisdom;

import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class yz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p41 f9938a;
    public final int b;
    public final h41 c;
    public final dz0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final uz0 f9939e;
    public final c f;
    public final int g;
    public int[] h = null;
    public final int i;
    public final boolean j;

    /* JADX INFO: compiled from: Proguard */
    public static class a extends e41.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean[] f9940a;
        public final /* synthetic */ int b;
        public final /* synthetic */ int c;

        public a(boolean[] zArr, int i, int i2) {
            this.f9940a = zArr;
            this.b = i;
            this.c = i2;
        }

        @Override // supwisdom.e41.b
        public void a(i41 i41Var) {
            if (i41Var.f().d() == 3) {
                int iG = ((h51) i41Var.j()).g();
                boolean[] zArr = this.f9940a;
                zArr[0] = zArr[0] && (this.b - this.c) + iG == i41Var.h().f();
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b extends c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final h41 f9941e;

        public b(yz0 yz0Var, uz0 uz0Var, h41 h41Var) {
            super(uz0Var);
            this.f9941e = h41Var;
        }

        public void a(e41 e41Var) {
            this.f9941e.a(e41Var);
            throw null;
        }

        @Override // supwisdom.yz0.c, supwisdom.e41.b
        public void a(i41 i41Var) {
            super.a(i41Var);
            a((e41) i41Var);
            throw null;
        }

        @Override // supwisdom.yz0.c, supwisdom.e41.b
        public void a(j41 j41Var) {
            super.a(j41Var);
            a((e41) j41Var);
            throw null;
        }

        @Override // supwisdom.yz0.c, supwisdom.e41.b
        public void a(s41 s41Var) {
            super.a(s41Var);
            a((e41) s41Var);
            throw null;
        }

        @Override // supwisdom.yz0.c, supwisdom.e41.b
        public void a(t41 t41Var) {
            super.a(t41Var);
            a((e41) t41Var);
            throw null;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class c implements e41.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final uz0 f9942a;
        public a41 b;
        public hz0 c;

        public c(uz0 uz0Var) {
            this.f9942a = uz0Var;
        }

        public void a(kz0 kz0Var) {
            this.f9942a.a(kz0Var);
        }

        public final l41 a() {
            int iE = this.b.e();
            if (iE < 0) {
                return null;
            }
            e41 e41VarD = yz0.this.f9938a.b().g(iE).c().d(0);
            if (e41VarD.f().d() != 56) {
                return null;
            }
            return e41VarD.h();
        }

        public void a(a41 a41Var, hz0 hz0Var) {
            this.b = a41Var;
            this.c = hz0Var;
        }

        @Override // supwisdom.e41.b
        public void a(i41 i41Var) {
            r41 r41VarG = i41Var.g();
            mz0 mz0VarA = xz0.a(i41Var);
            o41 o41VarF = i41Var.f();
            int iD = o41VarF.d();
            if (o41VarF.b() != 1) {
                throw new RuntimeException("shouldn't happen");
            }
            if (iD == 3) {
                if (yz0.this.j) {
                    return;
                }
                l41 l41VarH = i41Var.h();
                a(new zz0(mz0VarA, r41VarG, m41.a(l41VarH, l41.a((yz0.this.g - yz0.this.i) + ((h51) i41Var.j()).g(), l41VarH.getType()))));
                return;
            }
            a(new iz0(mz0VarA, r41VarG, yz0.b(i41Var), i41Var.j()));
        }

        @Override // supwisdom.e41.b
        public void a(j41 j41Var) {
            kz0 zz0Var;
            o41 o41VarF = j41Var.f();
            if (o41VarF.d() == 54 || o41VarF.d() == 56) {
                return;
            }
            r41 r41VarG = j41Var.g();
            mz0 mz0VarA = xz0.a(j41Var);
            int iB = o41VarF.b();
            if (iB == 1 || iB == 2) {
                zz0Var = new zz0(mz0VarA, r41VarG, yz0.b(j41Var));
            } else {
                if (iB == 3) {
                    return;
                }
                if (iB == 4) {
                    zz0Var = new b01(mz0VarA, r41VarG, yz0.b(j41Var), yz0.this.d.a(this.b.g().d(1)));
                } else {
                    if (iB != 6) {
                        throw new RuntimeException("shouldn't happen");
                    }
                    zz0Var = new zz0(mz0VarA, r41VarG, yz0.b(j41Var));
                }
            }
            a(zz0Var);
        }

        @Override // supwisdom.e41.b
        public void a(s41 s41Var) {
            kz0 iz0Var;
            r41 r41VarG = s41Var.g();
            mz0 mz0VarA = xz0.a(s41Var);
            o41 o41VarF = s41Var.f();
            u41 u41VarJ = s41Var.j();
            if (o41VarF.b() == 6) {
                a(this.c);
                if (o41VarF.e()) {
                    a(new iz0(mz0VarA, r41VarG, s41Var.i(), u41VarJ));
                    return;
                }
                l41 l41VarA = a();
                m41 m41VarB = yz0.b(s41Var, l41VarA);
                if ((mz0VarA.g() || o41VarF.d() == 43) == (l41VarA != null)) {
                    if (o41VarF.d() == 41 && mz0VarA.e() != 35) {
                        iz0Var = new zz0(mz0VarA, r41VarG, m41VarB);
                    } else {
                        iz0Var = new iz0(mz0VarA, r41VarG, m41VarB, u41VarJ);
                    }
                    a(iz0Var);
                    return;
                }
                throw new RuntimeException("Insn with result/move-result-pseudo mismatch " + s41Var);
            }
            throw new RuntimeException("Expected BRANCH_THROW got " + o41VarF.b());
        }

        @Override // supwisdom.e41.b
        public void a(t41 t41Var) {
            r41 r41VarG = t41Var.g();
            mz0 mz0VarA = xz0.a(t41Var);
            if (t41Var.f().b() == 6) {
                l41 l41VarA = a();
                if (mz0VarA.g() == (l41VarA != null)) {
                    a(this.c);
                    a(new zz0(mz0VarA, r41VarG, yz0.b(t41Var, l41VarA)));
                    return;
                } else {
                    throw new RuntimeException("Insn with result/move-result-pseudo mismatch" + t41Var);
                }
            }
            throw new RuntimeException("shouldn't happen");
        }
    }

    public yz0(p41 p41Var, int i, h41 h41Var, int i2, cz0 cz0Var) {
        this.f9938a = p41Var;
        this.b = i;
        this.c = h41Var;
        this.d = new dz0(p41Var);
        this.i = i2;
        boolean zA = a(p41Var, i2);
        this.j = zA;
        b41 b41VarB = p41Var.b();
        int size = b41VarB.size() * 3;
        int i3 = size + b41VarB.i();
        if (h41Var != null) {
            h41Var.h();
            throw null;
        }
        int iJ = b41VarB.j() + (zA ? 0 : i2);
        this.g = iJ;
        uz0 uz0Var = new uz0(cz0Var, i3, size, iJ, i2);
        this.f9939e = uz0Var;
        if (h41Var != null) {
            this.f = new b(this, uz0Var, h41Var);
        } else {
            this.f = new c(uz0Var);
        }
    }

    public static m41 b(e41 e41Var) {
        return b(e41Var, e41Var.h());
    }

    public final jz0 c() {
        b();
        a();
        return new jz0(this.b, this.f9939e.b(), new a01(this.f9938a, this.h, this.d));
    }

    public final void b() {
        int iD;
        b41 b41VarB = this.f9938a.b();
        int size = b41VarB.size();
        int iH = b41VarB.h();
        int[] iArrA = i61.a(iH);
        int[] iArrA2 = i61.a(iH);
        for (int i = 0; i < size; i++) {
            i61.d(iArrA, b41VarB.f(i).a());
        }
        int[] iArr = new int[size];
        int iC = this.f9938a.c();
        int i2 = 0;
        while (iC != -1) {
            while (true) {
                o61 o61VarA = this.f9938a.a(iC);
                int size2 = o61VarA.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    iD = o61VarA.d(i3);
                    if (i61.c(iArrA2, iD)) {
                        break;
                    }
                    if (i61.c(iArrA, iD) && b41VarB.g(iD).e() == iC) {
                        break;
                    }
                }
                i61.d(iArrA2, iD);
                iC = iD;
            }
            while (iC != -1) {
                i61.a(iArrA, iC);
                i61.a(iArrA2, iC);
                iArr[i2] = iC;
                i2++;
                a41 a41VarG = b41VarB.g(iC);
                a41 a41VarA = b41VarB.a(a41VarG);
                if (a41VarA == null) {
                    break;
                }
                int iA = a41VarA.a();
                int iE = a41VarG.e();
                if (i61.c(iArrA, iA)) {
                    iC = iA;
                } else {
                    if (iE == iA || iE < 0 || !i61.c(iArrA, iE)) {
                        o61 o61VarG = a41VarG.g();
                        int size3 = o61VarG.size();
                        for (int i4 = 0; i4 < size3; i4++) {
                            int iD2 = o61VarG.d(i4);
                            if (i61.c(iArrA, iD2)) {
                                iC = iD2;
                                break;
                            }
                        }
                        iE = -1;
                    }
                    iC = iE;
                }
            }
            iC = i61.b(iArrA, 0);
        }
        if (i2 == size) {
            this.h = iArr;
            return;
        }
        throw new RuntimeException("shouldn't happen");
    }

    public final void a(a41 a41Var, int i) {
        this.f9939e.a(this.d.c(a41Var));
        h41 h41Var = this.c;
        if (h41Var == null) {
            this.f.a(a41Var, this.d.b(a41Var));
            a41Var.c().a(this.f);
            this.f9939e.a(this.d.a(a41Var));
            int iE = a41Var.e();
            e41 e41VarD = a41Var.d();
            if (iE < 0 || iE == i) {
                return;
            }
            if (e41VarD.f().b() == 4 && a41Var.f() == i) {
                this.f9939e.a(1, this.d.a(iE));
                return;
            } else {
                this.f9939e.a(new b01(nz0.Q, e41VarD.g(), m41.c, this.d.a(iE)));
                return;
            }
        }
        h41Var.a(a41Var);
        throw null;
    }

    public final void a() {
        b41 b41VarB = this.f9938a.b();
        int[] iArr = this.h;
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            a(b41VarB.g(iArr[i]), i2 == iArr.length ? -1 : iArr[i2]);
            i = i2;
        }
    }

    public static jz0 a(p41 p41Var, int i, h41 h41Var, int i2, cz0 cz0Var) {
        return new yz0(p41Var, i, h41Var, i2, cz0Var).c();
    }

    public static boolean a(p41 p41Var, int i) {
        boolean[] zArr = {true};
        p41Var.b().a(new a(zArr, p41Var.b().j(), i));
        return zArr[0];
    }

    public static m41 b(e41 e41Var, l41 l41Var) {
        m41 m41VarI = e41Var.i();
        if (e41Var.f().f() && m41VarI.size() == 2 && l41Var.f() == m41VarI.d(1).f()) {
            m41VarI = m41.a(m41VarI.d(1), m41VarI.d(0));
        }
        return l41Var == null ? m41VarI : m41VarI.a(l41Var);
    }
}
