package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class o6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<ConstraintWidget> f8630a = new ArrayList<>();
    public a b = new a();
    public f6 c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {
        public static int k = 0;
        public static int l = 1;
        public static int m = 2;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f8631a;
        public ConstraintWidget.DimensionBehaviour b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8632e;
        public int f;
        public int g;
        public boolean h;
        public boolean i;
        public int j;
    }

    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface b {
        void a();

        void a(ConstraintWidget constraintWidget, a aVar);
    }

    public o6(f6 f6Var) {
        this.c = f6Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x009e A[PHI: r9
  0x009e: PHI (r9v3 boolean) = (r9v2 boolean), (r9v2 boolean), (r9v2 boolean), (r9v5 boolean), (r9v5 boolean) binds: [B:32:0x0062, B:34:0x0068, B:36:0x006c, B:57:0x009b, B:55:0x0094] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00b2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(supwisdom.f6 r13) {
        /*
            r12 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r13.K0
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.x(r1)
            supwisdom.o6$b r2 = r13.W()
            r3 = 0
            r4 = 0
        L12:
            if (r4 >= r0) goto Lb6
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r5 = r13.K0
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof supwisdom.h6
            if (r6 == 0) goto L22
            goto Lb2
        L22:
            boolean r6 = r5 instanceof supwisdom.c6
            if (r6 == 0) goto L28
            goto Lb2
        L28:
            boolean r6 = r5.L()
            if (r6 == 0) goto L30
            goto Lb2
        L30:
            if (r1 == 0) goto L48
            supwisdom.y6 r6 = r5.d
            if (r6 == 0) goto L48
            supwisdom.a7 r7 = r5.f1228e
            if (r7 == 0) goto L48
            supwisdom.t6 r6 = r6.f7149e
            boolean r6 = r6.j
            if (r6 == 0) goto L48
            supwisdom.t6 r6 = r7.f7149e
            boolean r6 = r6.j
            if (r6 == 0) goto L48
            goto Lb2
        L48:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.b(r3)
            r7 = 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.b(r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L61
            int r10 = r5.n
            if (r10 == r7) goto L61
            if (r8 != r9) goto L61
            int r9 = r5.o
            if (r9 == r7) goto L61
            r9 = 1
            goto L62
        L61:
            r9 = 0
        L62:
            if (r9 != 0) goto L9e
            boolean r10 = r13.x(r7)
            if (r10 == 0) goto L9e
            boolean r10 = r5 instanceof supwisdom.l6
            if (r10 != 0) goto L9e
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r10) goto L7f
            int r11 = r5.n
            if (r11 != 0) goto L7f
            if (r8 == r10) goto L7f
            boolean r10 = r5.I()
            if (r10 != 0) goto L7f
            r9 = 1
        L7f:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r10) goto L90
            int r11 = r5.o
            if (r11 != 0) goto L90
            if (r6 == r10) goto L90
            boolean r10 = r5.I()
            if (r10 != 0) goto L90
            r9 = 1
        L90:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 == r10) goto L96
            if (r8 != r10) goto L9e
        L96:
            float r6 = r5.W
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L9e
            goto L9f
        L9e:
            r7 = r9
        L9f:
            if (r7 == 0) goto La2
            goto Lb2
        La2:
            int r6 = supwisdom.o6.a.k
            r12.a(r2, r5, r6)
            supwisdom.x5 r5 = r13.P0
            if (r5 == 0) goto Lb2
            long r6 = r5.f9711a
            r8 = 1
            long r6 = r6 + r8
            r5.f9711a = r6
        Lb2:
            int r4 = r4 + 1
            goto L12
        Lb6:
            r2.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.o6.a(supwisdom.f6):void");
    }

    public void b(f6 f6Var) {
        this.f8630a.clear();
        int size = f6Var.K0.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = f6Var.K0.get(i);
            if (constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                this.f8630a.add(constraintWidget);
            }
        }
        f6Var.a0();
    }

    public final void a(f6 f6Var, String str, int i, int i2) {
        int iV = f6Var.v();
        int iU = f6Var.u();
        f6Var.r(0);
        f6Var.q(0);
        f6Var.u(i);
        f6Var.m(i2);
        f6Var.r(iV);
        f6Var.q(iU);
        this.c.U();
    }

    public long a(f6 f6Var, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        boolean zA;
        int i10;
        int i11;
        boolean z;
        boolean z2;
        boolean z3;
        int i12;
        b bVar;
        int i13;
        int i14;
        int i15;
        boolean z4;
        x5 x5Var;
        b bVarW = f6Var.W();
        int size = f6Var.K0.size();
        int iD = f6Var.D();
        int iL = f6Var.l();
        boolean zA2 = k6.a(i, 128);
        boolean z5 = zA2 || k6.a(i, 64);
        if (z5) {
            for (int i16 = 0; i16 < size; i16++) {
                ConstraintWidget constraintWidget = f6Var.K0.get(i16);
                boolean z6 = (constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (constraintWidget.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.j() > 0.0f;
                if ((constraintWidget.I() && z6) || ((constraintWidget.K() && z6) || (constraintWidget instanceof l6) || constraintWidget.I() || constraintWidget.K())) {
                    z5 = false;
                    break;
                }
            }
        }
        if (z5 && (x5Var = w5.x) != null) {
            x5Var.c++;
        }
        boolean z7 = z5 & ((i4 == 1073741824 && i6 == 1073741824) || zA2);
        if (z7) {
            int iMin = Math.min(f6Var.t(), i5);
            int iMin2 = Math.min(f6Var.s(), i7);
            if (i4 == 1073741824 && f6Var.D() != iMin) {
                f6Var.u(iMin);
                f6Var.a0();
            }
            if (i6 == 1073741824 && f6Var.l() != iMin2) {
                f6Var.m(iMin2);
                f6Var.a0();
            }
            if (i4 == 1073741824 && i6 == 1073741824) {
                zA = f6Var.e(zA2);
                i10 = 2;
            } else {
                boolean zF = f6Var.f(zA2);
                if (i4 == 1073741824) {
                    zF &= f6Var.a(zA2, 0);
                    i10 = 1;
                } else {
                    i10 = 0;
                }
                if (i6 == 1073741824) {
                    zA = f6Var.a(zA2, 1) & zF;
                    i10++;
                } else {
                    zA = zF;
                }
            }
            if (zA) {
                f6Var.a(i4 == 1073741824, i6 == 1073741824);
            }
        } else {
            zA = false;
            i10 = 0;
        }
        if (zA && i10 == 2) {
            return 0L;
        }
        int iX = f6Var.X();
        if (size > 0) {
            a(f6Var);
        }
        b(f6Var);
        int size2 = this.f8630a.size();
        if (size > 0) {
            a(f6Var, "First pass", iD, iL);
        }
        if (size2 > 0) {
            boolean z8 = f6Var.o() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z9 = f6Var.A() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int iMax = Math.max(f6Var.D(), this.c.v());
            int iMax2 = Math.max(f6Var.l(), this.c.u());
            int i17 = 0;
            boolean zB0 = false;
            while (i17 < size2) {
                ConstraintWidget constraintWidget2 = this.f8630a.get(i17);
                if (constraintWidget2 instanceof l6) {
                    int iD2 = constraintWidget2.D();
                    i13 = iX;
                    int iL2 = constraintWidget2.l();
                    i14 = iD;
                    boolean zA3 = a(bVarW, constraintWidget2, a.l) | zB0;
                    x5 x5Var2 = f6Var.P0;
                    i15 = iL;
                    if (x5Var2 != null) {
                        x5Var2.b++;
                    }
                    int iD3 = constraintWidget2.D();
                    int iL3 = constraintWidget2.l();
                    if (iD3 != iD2) {
                        constraintWidget2.u(iD3);
                        if (z8 && constraintWidget2.x() > iMax) {
                            iMax = Math.max(iMax, constraintWidget2.x() + constraintWidget2.a(ConstraintAnchor.Type.RIGHT).c());
                        }
                        z4 = true;
                    } else {
                        z4 = zA3;
                    }
                    if (iL3 != iL2) {
                        constraintWidget2.m(iL3);
                        if (z9 && constraintWidget2.g() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget2.g() + constraintWidget2.a(ConstraintAnchor.Type.BOTTOM).c());
                        }
                        z4 = true;
                    }
                    zB0 = z4 | ((l6) constraintWidget2).b0();
                } else {
                    i13 = iX;
                    i14 = iD;
                    i15 = iL;
                }
                i17++;
                iX = i13;
                iD = i14;
                iL = i15;
            }
            int i18 = iX;
            int i19 = iD;
            int i20 = iL;
            int i21 = 0;
            int i22 = 2;
            while (i21 < i22) {
                int i23 = 0;
                while (i23 < size2) {
                    ConstraintWidget constraintWidget3 = this.f8630a.get(i23);
                    if (((constraintWidget3 instanceof i6) && !(constraintWidget3 instanceof l6)) || (constraintWidget3 instanceof h6) || constraintWidget3.C() == 8 || ((z7 && constraintWidget3.d.f7149e.j && constraintWidget3.f1228e.f7149e.j) || (constraintWidget3 instanceof l6))) {
                        z3 = z7;
                        i12 = size2;
                        bVar = bVarW;
                    } else {
                        int iD4 = constraintWidget3.D();
                        int iL4 = constraintWidget3.l();
                        int iF = constraintWidget3.f();
                        int i24 = a.l;
                        z3 = z7;
                        if (i21 == 1) {
                            i24 = a.m;
                        }
                        boolean zA4 = a(bVarW, constraintWidget3, i24) | zB0;
                        x5 x5Var3 = f6Var.P0;
                        i12 = size2;
                        bVar = bVarW;
                        if (x5Var3 != null) {
                            x5Var3.b++;
                        }
                        int iD5 = constraintWidget3.D();
                        int iL5 = constraintWidget3.l();
                        if (iD5 != iD4) {
                            constraintWidget3.u(iD5);
                            if (z8 && constraintWidget3.x() > iMax) {
                                iMax = Math.max(iMax, constraintWidget3.x() + constraintWidget3.a(ConstraintAnchor.Type.RIGHT).c());
                            }
                            zA4 = true;
                        }
                        if (iL5 != iL4) {
                            constraintWidget3.m(iL5);
                            if (z9 && constraintWidget3.g() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget3.g() + constraintWidget3.a(ConstraintAnchor.Type.BOTTOM).c());
                            }
                            zA4 = true;
                        }
                        zB0 = (!constraintWidget3.G() || iF == constraintWidget3.f()) ? zA4 : true;
                    }
                    i23++;
                    size2 = i12;
                    bVarW = bVar;
                    z7 = z3;
                }
                boolean z10 = z7;
                int i25 = size2;
                b bVar2 = bVarW;
                if (!zB0) {
                    break;
                }
                a(f6Var, "intermediate pass", i19, i20);
                i21++;
                bVarW = bVar2;
                z7 = z10;
                i22 = 2;
                zB0 = false;
                size2 = i25;
            }
            if (zB0) {
                a(f6Var, "2nd pass", i19, i20);
                if (f6Var.D() < iMax) {
                    f6Var.u(iMax);
                    z = true;
                } else {
                    z = false;
                }
                if (f6Var.l() < iMax2) {
                    f6Var.m(iMax2);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    a(f6Var, "3rd pass", i19, i20);
                }
            }
            i11 = i18;
        } else {
            i11 = iX;
        }
        f6Var.y(i11);
        return 0L;
    }

    public final boolean a(b bVar, ConstraintWidget constraintWidget, int i) {
        this.b.f8631a = constraintWidget.o();
        this.b.b = constraintWidget.A();
        this.b.c = constraintWidget.D();
        this.b.d = constraintWidget.l();
        a aVar = this.b;
        aVar.i = false;
        aVar.j = i;
        boolean z = aVar.f8631a == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = this.b.b == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z3 = z && constraintWidget.W > 0.0f;
        boolean z4 = z2 && constraintWidget.W > 0.0f;
        if (z3 && constraintWidget.p[0] == 4) {
            this.b.f8631a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z4 && constraintWidget.p[1] == 4) {
            this.b.b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        bVar.a(constraintWidget, this.b);
        constraintWidget.u(this.b.f8632e);
        constraintWidget.m(this.b.f);
        constraintWidget.a(this.b.h);
        constraintWidget.i(this.b.g);
        a aVar2 = this.b;
        aVar2.j = a.k;
        return aVar2.i;
    }
}
