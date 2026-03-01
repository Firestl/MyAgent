package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class g6 extends l6 {
    public ConstraintWidget[] u1;
    public int X0 = -1;
    public int Y0 = -1;
    public int Z0 = -1;
    public int a1 = -1;
    public int b1 = -1;
    public int c1 = -1;
    public float d1 = 0.5f;
    public float e1 = 0.5f;
    public float f1 = 0.5f;
    public float g1 = 0.5f;
    public float h1 = 0.5f;
    public float i1 = 0.5f;
    public int j1 = 0;
    public int k1 = 0;
    public int l1 = 2;
    public int m1 = 2;
    public int n1 = 0;
    public int o1 = -1;
    public int p1 = 0;
    public ArrayList<a> q1 = new ArrayList<>();
    public ConstraintWidget[] r1 = null;
    public ConstraintWidget[] s1 = null;
    public int[] t1 = null;
    public int v1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7687a;
        public ConstraintAnchor d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ConstraintAnchor f7688e;
        public ConstraintAnchor f;
        public ConstraintAnchor g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int q;
        public ConstraintWidget b = null;
        public int c = 0;
        public int l = 0;
        public int m = 0;
        public int n = 0;
        public int o = 0;
        public int p = 0;

        public a(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.f7687a = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.f7687a = i;
            this.d = constraintAnchor;
            this.f7688e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = g6.this.X();
            this.i = g6.this.Z();
            this.j = g6.this.Y();
            this.k = g6.this.W();
            this.q = i2;
        }

        public void b(int i) {
            this.n = i;
        }

        public int c() {
            return this.f7687a == 0 ? this.l - g6.this.j1 : this.l;
        }

        public final void d() {
            this.l = 0;
            this.m = 0;
            this.b = null;
            this.c = 0;
            int i = this.o;
            for (int i2 = 0; i2 < i && this.n + i2 < g6.this.v1; i2++) {
                ConstraintWidget constraintWidget = g6.this.u1[this.n + i2];
                if (this.f7687a == 0) {
                    int iD = constraintWidget.D();
                    int i3 = g6.this.j1;
                    if (constraintWidget.C() == 8) {
                        i3 = 0;
                    }
                    this.l += iD + i3;
                    int iA = g6.this.a(constraintWidget, this.q);
                    if (this.b == null || this.c < iA) {
                        this.b = constraintWidget;
                        this.c = iA;
                        this.m = iA;
                    }
                } else {
                    int iB = g6.this.b(constraintWidget, this.q);
                    int iA2 = g6.this.a(constraintWidget, this.q);
                    int i4 = g6.this.k1;
                    if (constraintWidget.C() == 8) {
                        i4 = 0;
                    }
                    this.m += iA2 + i4;
                    if (this.b == null || this.c < iB) {
                        this.b = constraintWidget;
                        this.c = iB;
                        this.l = iB;
                    }
                }
            }
        }

        public void a(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.f7687a = i;
            this.d = constraintAnchor;
            this.f7688e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = i2;
            this.i = i3;
            this.j = i4;
            this.k = i5;
            this.q = i6;
        }

        public int b() {
            return this.f7687a == 1 ? this.m - g6.this.k1 : this.m;
        }

        public void a() {
            this.c = 0;
            this.b = null;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
        }

        public void a(ConstraintWidget constraintWidget) {
            if (this.f7687a == 0) {
                int iB = g6.this.b(constraintWidget, this.q);
                if (constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iB = 0;
                }
                this.l += iB + (constraintWidget.C() != 8 ? g6.this.j1 : 0);
                int iA = g6.this.a(constraintWidget, this.q);
                if (this.b == null || this.c < iA) {
                    this.b = constraintWidget;
                    this.c = iA;
                    this.m = iA;
                }
            } else {
                int iB2 = g6.this.b(constraintWidget, this.q);
                int iA2 = g6.this.a(constraintWidget, this.q);
                if (constraintWidget.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iA2 = 0;
                }
                this.m += iA2 + (constraintWidget.C() != 8 ? g6.this.k1 : 0);
                if (this.b == null || this.c < iB2) {
                    this.b = constraintWidget;
                    this.c = iB2;
                    this.l = iB2;
                }
            }
            this.o++;
        }

        public void a(boolean z, int i, boolean z2) {
            ConstraintWidget constraintWidget;
            int i2 = this.o;
            for (int i3 = 0; i3 < i2 && this.n + i3 < g6.this.v1; i3++) {
                ConstraintWidget constraintWidget2 = g6.this.u1[this.n + i3];
                if (constraintWidget2 != null) {
                    constraintWidget2.R();
                }
            }
            if (i2 == 0 || this.b == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i4 = -1;
            int i5 = -1;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = z ? (i2 - 1) - i6 : i6;
                if (this.n + i7 >= g6.this.v1) {
                    break;
                }
                if (g6.this.u1[this.n + i7].C() == 0) {
                    if (i4 == -1) {
                        i4 = i6;
                    }
                    i5 = i6;
                }
            }
            ConstraintWidget constraintWidget3 = null;
            if (this.f7687a == 0) {
                ConstraintWidget constraintWidget4 = this.b;
                constraintWidget4.s(g6.this.Y0);
                int i8 = this.i;
                if (i > 0) {
                    i8 += g6.this.k1;
                }
                constraintWidget4.I.a(this.f7688e, i8);
                if (z2) {
                    constraintWidget4.K.a(this.g, this.k);
                }
                if (i > 0) {
                    this.f7688e.d.K.a(constraintWidget4.I, 0);
                }
                if (g6.this.m1 != 3 || constraintWidget4.G()) {
                    constraintWidget = constraintWidget4;
                } else {
                    for (int i9 = 0; i9 < i2; i9++) {
                        int i10 = z ? (i2 - 1) - i9 : i9;
                        if (this.n + i10 >= g6.this.v1) {
                            break;
                        }
                        constraintWidget = g6.this.u1[this.n + i10];
                        if (constraintWidget.G()) {
                            break;
                        }
                    }
                    constraintWidget = constraintWidget4;
                }
                int i11 = 0;
                while (i11 < i2) {
                    int i12 = z ? (i2 - 1) - i11 : i11;
                    if (this.n + i12 >= g6.this.v1) {
                        return;
                    }
                    ConstraintWidget constraintWidget5 = g6.this.u1[this.n + i12];
                    if (i11 == 0) {
                        constraintWidget5.a(constraintWidget5.H, this.d, this.h);
                    }
                    if (i12 == 0) {
                        int i13 = g6.this.X0;
                        float f = g6.this.d1;
                        if (this.n != 0 || g6.this.Z0 == -1) {
                            if (z2 && g6.this.b1 != -1) {
                                i13 = g6.this.b1;
                                f = g6.this.h1;
                            }
                        } else {
                            i13 = g6.this.Z0;
                            f = g6.this.f1;
                        }
                        constraintWidget5.n(i13);
                        constraintWidget5.a(f);
                    }
                    if (i11 == i2 - 1) {
                        constraintWidget5.a(constraintWidget5.J, this.f, this.j);
                    }
                    if (constraintWidget3 != null) {
                        constraintWidget5.H.a(constraintWidget3.J, g6.this.j1);
                        if (i11 == i4) {
                            constraintWidget5.H.b(this.h);
                        }
                        constraintWidget3.J.a(constraintWidget5.H, 0);
                        if (i11 == i5 + 1) {
                            constraintWidget3.J.b(this.j);
                        }
                    }
                    if (constraintWidget5 != constraintWidget4) {
                        if (g6.this.m1 != 3 || !constraintWidget.G() || constraintWidget5 == constraintWidget || !constraintWidget5.G()) {
                            int i14 = g6.this.m1;
                            if (i14 == 0) {
                                constraintWidget5.I.a(constraintWidget4.I, 0);
                            } else if (i14 == 1) {
                                constraintWidget5.K.a(constraintWidget4.K, 0);
                            } else if (z3) {
                                constraintWidget5.I.a(this.f7688e, this.i);
                                constraintWidget5.K.a(this.g, this.k);
                            } else {
                                constraintWidget5.I.a(constraintWidget4.I, 0);
                                constraintWidget5.K.a(constraintWidget4.K, 0);
                            }
                        } else {
                            constraintWidget5.L.a(constraintWidget.L, 0);
                        }
                    }
                    i11++;
                    constraintWidget3 = constraintWidget5;
                }
                return;
            }
            ConstraintWidget constraintWidget6 = this.b;
            constraintWidget6.n(g6.this.X0);
            int i15 = this.h;
            if (i > 0) {
                i15 += g6.this.j1;
            }
            if (z) {
                constraintWidget6.J.a(this.f, i15);
                if (z2) {
                    constraintWidget6.H.a(this.d, this.j);
                }
                if (i > 0) {
                    this.f.d.H.a(constraintWidget6.J, 0);
                }
            } else {
                constraintWidget6.H.a(this.d, i15);
                if (z2) {
                    constraintWidget6.J.a(this.f, this.j);
                }
                if (i > 0) {
                    this.d.d.J.a(constraintWidget6.H, 0);
                }
            }
            int i16 = 0;
            while (i16 < i2 && this.n + i16 < g6.this.v1) {
                ConstraintWidget constraintWidget7 = g6.this.u1[this.n + i16];
                if (i16 == 0) {
                    constraintWidget7.a(constraintWidget7.I, this.f7688e, this.i);
                    int i17 = g6.this.Y0;
                    float f2 = g6.this.e1;
                    if (this.n != 0 || g6.this.a1 == -1) {
                        if (z2 && g6.this.c1 != -1) {
                            i17 = g6.this.c1;
                            f2 = g6.this.i1;
                        }
                    } else {
                        i17 = g6.this.a1;
                        f2 = g6.this.g1;
                    }
                    constraintWidget7.s(i17);
                    constraintWidget7.c(f2);
                }
                if (i16 == i2 - 1) {
                    constraintWidget7.a(constraintWidget7.K, this.g, this.k);
                }
                if (constraintWidget3 != null) {
                    constraintWidget7.I.a(constraintWidget3.K, g6.this.k1);
                    if (i16 == i4) {
                        constraintWidget7.I.b(this.i);
                    }
                    constraintWidget3.K.a(constraintWidget7.I, 0);
                    if (i16 == i5 + 1) {
                        constraintWidget3.K.b(this.k);
                    }
                }
                if (constraintWidget7 != constraintWidget6) {
                    if (z) {
                        int i18 = g6.this.l1;
                        if (i18 == 0) {
                            constraintWidget7.J.a(constraintWidget6.J, 0);
                        } else if (i18 == 1) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        } else if (i18 == 2) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                            constraintWidget7.J.a(constraintWidget6.J, 0);
                        }
                    } else {
                        int i19 = g6.this.l1;
                        if (i19 == 0) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        } else if (i19 == 1) {
                            constraintWidget7.J.a(constraintWidget6.J, 0);
                        } else if (i19 == 2) {
                            if (z3) {
                                constraintWidget7.H.a(this.d, this.h);
                                constraintWidget7.J.a(this.f, this.j);
                            } else {
                                constraintWidget7.H.a(constraintWidget6.H, 0);
                                constraintWidget7.J.a(constraintWidget6.J, 0);
                            }
                        }
                    }
                }
                i16++;
                constraintWidget3 = constraintWidget7;
            }
        }

        public void a(int i) {
            int i2 = this.p;
            if (i2 == 0) {
                return;
            }
            int i3 = this.o;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3 && this.n + i5 < g6.this.v1; i5++) {
                ConstraintWidget constraintWidget = g6.this.u1[this.n + i5];
                if (this.f7687a == 0) {
                    if (constraintWidget != null && constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.n == 0) {
                        g6.this.a(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.A(), constraintWidget.l());
                    }
                } else if (constraintWidget != null && constraintWidget.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.o == 0) {
                    g6.this.a(constraintWidget, constraintWidget.o(), constraintWidget.D(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
            }
            d();
        }
    }

    public void F(int i) {
        this.Z0 = i;
    }

    public void G(int i) {
        this.a1 = i;
    }

    public void H(int i) {
        this.l1 = i;
    }

    public void I(int i) {
        this.j1 = i;
    }

    public void J(int i) {
        this.X0 = i;
    }

    public void K(int i) {
        this.b1 = i;
    }

    public void L(int i) {
        this.c1 = i;
    }

    public void M(int i) {
        this.o1 = i;
    }

    public void N(int i) {
        this.p1 = i;
    }

    public void O(int i) {
        this.m1 = i;
    }

    public void P(int i) {
        this.k1 = i;
    }

    public void Q(int i) {
        this.Y0 = i;
    }

    public void R(int i) {
        this.n1 = i;
    }

    public final void c(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        a aVar;
        if (i == 0) {
            return;
        }
        if (this.q1.size() == 0) {
            aVar = new a(i2, this.H, this.I, this.J, this.K, i3);
            this.q1.add(aVar);
        } else {
            a aVar2 = this.q1.get(0);
            aVar2.a();
            aVar = aVar2;
            aVar.a(i2, this.H, this.I, this.J, this.K, X(), Z(), Y(), W(), i3);
        }
        for (int i4 = 0; i4 < i; i4++) {
            aVar.a(constraintWidgetArr[i4]);
        }
        iArr[0] = aVar.c();
        iArr[1] = aVar.b();
    }

    public void e(float f) {
        this.f1 = f;
    }

    public void f(float f) {
        this.g1 = f;
    }

    public void g(float f) {
        this.d1 = f;
    }

    public void h(float f) {
        this.h1 = f;
    }

    public void i(float f) {
        this.i1 = f;
    }

    public void j(float f) {
        this.e1 = f;
    }

    @Override // supwisdom.j6, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.a(constraintWidget, map);
        g6 g6Var = (g6) constraintWidget;
        this.X0 = g6Var.X0;
        this.Y0 = g6Var.Y0;
        this.Z0 = g6Var.Z0;
        this.a1 = g6Var.a1;
        this.b1 = g6Var.b1;
        this.c1 = g6Var.c1;
        this.d1 = g6Var.d1;
        this.e1 = g6Var.e1;
        this.f1 = g6Var.f1;
        this.g1 = g6Var.g1;
        this.h1 = g6Var.h1;
        this.i1 = g6Var.i1;
        this.j1 = g6Var.j1;
        this.k1 = g6Var.k1;
        this.l1 = g6Var.l1;
        this.m1 = g6Var.m1;
        this.n1 = g6Var.n1;
        this.o1 = g6Var.o1;
        this.p1 = g6Var.p1;
    }

    public final int b(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.n;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.s * i);
                if (i3 != constraintWidget.D()) {
                    constraintWidget.d(true);
                    a(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i3, constraintWidget.A(), constraintWidget.l());
                }
                return i3;
            }
            if (i2 == 1) {
                return constraintWidget.D();
            }
            if (i2 == 3) {
                return (int) ((constraintWidget.l() * constraintWidget.W) + 0.5f);
            }
        }
        return constraintWidget.D();
    }

    public final void g(boolean z) {
        ConstraintWidget constraintWidget;
        if (this.t1 == null || this.s1 == null || this.r1 == null) {
            return;
        }
        for (int i = 0; i < this.v1; i++) {
            this.u1[i].R();
        }
        int[] iArr = this.t1;
        int i2 = iArr[0];
        int i3 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i4 = 0; i4 < i2; i4++) {
            ConstraintWidget constraintWidget3 = this.s1[z ? (i2 - i4) - 1 : i4];
            if (constraintWidget3 != null && constraintWidget3.C() != 8) {
                if (i4 == 0) {
                    constraintWidget3.a(constraintWidget3.H, this.H, X());
                    constraintWidget3.n(this.X0);
                    constraintWidget3.a(this.d1);
                }
                if (i4 == i2 - 1) {
                    constraintWidget3.a(constraintWidget3.J, this.J, Y());
                }
                if (i4 > 0) {
                    constraintWidget3.a(constraintWidget3.H, constraintWidget2.J, this.j1);
                    constraintWidget2.a(constraintWidget2.J, constraintWidget3.H, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ConstraintWidget constraintWidget4 = this.r1[i5];
            if (constraintWidget4 != null && constraintWidget4.C() != 8) {
                if (i5 == 0) {
                    constraintWidget4.a(constraintWidget4.I, this.I, Z());
                    constraintWidget4.s(this.Y0);
                    constraintWidget4.c(this.e1);
                }
                if (i5 == i3 - 1) {
                    constraintWidget4.a(constraintWidget4.K, this.K, W());
                }
                if (i5 > 0) {
                    constraintWidget4.a(constraintWidget4.I, constraintWidget2.K, this.k1);
                    constraintWidget2.a(constraintWidget2.K, constraintWidget4.I, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i6 = 0; i6 < i2; i6++) {
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = (i7 * i2) + i6;
                if (this.p1 == 1) {
                    i8 = (i6 * i3) + i7;
                }
                ConstraintWidget[] constraintWidgetArr = this.u1;
                if (i8 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i8]) != null && constraintWidget.C() != 8) {
                    ConstraintWidget constraintWidget5 = this.s1[i6];
                    ConstraintWidget constraintWidget6 = this.r1[i7];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.a(constraintWidget.H, constraintWidget5.H, 0);
                        constraintWidget.a(constraintWidget.J, constraintWidget5.J, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.a(constraintWidget.I, constraintWidget6.I, 0);
                        constraintWidget.a(constraintWidget.K, constraintWidget6.K, 0);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0106  */
    @Override // supwisdom.l6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(int r19, int r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.g6.b(int, int, int, int):void");
    }

    public final int a(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.o;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.v * i);
                if (i3 != constraintWidget.l()) {
                    constraintWidget.d(true);
                    a(constraintWidget, constraintWidget.o(), constraintWidget.D(), ConstraintWidget.DimensionBehaviour.FIXED, i3);
                }
                return i3;
            }
            if (i2 == 1) {
                return constraintWidget.l();
            }
            if (i2 == 3) {
                return (int) ((constraintWidget.D() * constraintWidget.W) + 0.5f);
            }
        }
        return constraintWidget.l();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0068  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:105:0x011b -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x011d -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x0123 -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x0125 -> B:42:0x0063). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.g6.a(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    public final void b(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int iY;
        ConstraintAnchor constraintAnchor2;
        int iW;
        int i7;
        if (i == 0) {
            return;
        }
        this.q1.clear();
        a aVar = new a(i2, this.H, this.I, this.J, this.K, i3);
        this.q1.add(aVar);
        if (i2 == 0) {
            i4 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i9 < i) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i9];
                int iB = b(constraintWidget, i3);
                if (constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i10 = i4;
                boolean z = (i8 == i3 || (this.j1 + i8) + iB > i3) && aVar.b != null;
                if (!z && i9 > 0 && (i7 = this.o1) > 0 && i9 % i7 == 0) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i2, this.H, this.I, this.J, this.K, i3);
                    aVar.b(i9);
                    this.q1.add(aVar);
                } else {
                    if (i9 > 0) {
                        i8 += this.j1 + iB;
                    }
                    aVar.a(constraintWidget);
                    i9++;
                    i4 = i10;
                }
                i8 = iB;
                aVar.a(constraintWidget);
                i9++;
                i4 = i10;
            }
        } else {
            i4 = 0;
            int i11 = 0;
            int i12 = 0;
            while (i12 < i) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i12];
                int iA = a(constraintWidget2, i3);
                if (constraintWidget2.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i13 = i4;
                boolean z2 = (i11 == i3 || (this.k1 + i11) + iA > i3) && aVar.b != null;
                if (!z2 && i12 > 0 && (i5 = this.o1) > 0 && i12 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i2, this.H, this.I, this.J, this.K, i3);
                    aVar.b(i12);
                    this.q1.add(aVar);
                } else {
                    if (i12 > 0) {
                        i11 += this.k1 + iA;
                    }
                    aVar.a(constraintWidget2);
                    i12++;
                    i4 = i13;
                }
                i11 = iA;
                aVar.a(constraintWidget2);
                i12++;
                i4 = i13;
            }
        }
        int size = this.q1.size();
        ConstraintAnchor constraintAnchor3 = this.H;
        ConstraintAnchor constraintAnchor4 = this.I;
        ConstraintAnchor constraintAnchor5 = this.J;
        ConstraintAnchor constraintAnchor6 = this.K;
        int iX = X();
        int iZ = Z();
        int iY2 = Y();
        int iW2 = W();
        boolean z3 = o() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || A() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i4 > 0 && z3) {
            for (int i14 = 0; i14 < size; i14++) {
                a aVar2 = this.q1.get(i14);
                if (i2 == 0) {
                    aVar2.a(i3 - aVar2.c());
                } else {
                    aVar2.a(i3 - aVar2.b());
                }
            }
        }
        int i15 = iZ;
        int i16 = iY2;
        int iB2 = 0;
        int iC = 0;
        int i17 = 0;
        int i18 = iX;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i19 = iW2;
        while (i17 < size) {
            a aVar3 = this.q1.get(i17);
            if (i2 == 0) {
                if (i17 < size - 1) {
                    constraintAnchor2 = this.q1.get(i17 + 1).b.I;
                    iW = 0;
                } else {
                    constraintAnchor2 = this.K;
                    iW = W();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.b.K;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i20 = iB2;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i21 = iC;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i17;
                aVar3.a(i2, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i18, i15, i16, iW, i3);
                int iMax = Math.max(i21, aVar3.c());
                iB2 = i20 + aVar3.b();
                if (i6 > 0) {
                    iB2 += this.k1;
                }
                constraintAnchor8 = constraintAnchor11;
                iC = iMax;
                constraintAnchor7 = constraintAnchor9;
                i15 = 0;
                constraintAnchor = constraintAnchor14;
                int i22 = iW;
                constraintAnchor6 = constraintAnchor2;
                i19 = i22;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i23 = iB2;
                int i24 = iC;
                i6 = i17;
                if (i6 < size - 1) {
                    constraintAnchor = this.q1.get(i6 + 1).b.H;
                    iY = 0;
                } else {
                    constraintAnchor = this.J;
                    iY = Y();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.b.J;
                aVar3.a(i2, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i18, i15, iY, i19, i3);
                iC = i24 + aVar3.c();
                int iMax2 = Math.max(i23, aVar3.b());
                if (i6 > 0) {
                    iC += this.j1;
                }
                iB2 = iMax2;
                i16 = iY;
                constraintAnchor8 = constraintAnchor16;
                i18 = 0;
            }
            i17 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iC;
        iArr[1] = iB2;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(w5 w5Var, boolean z) {
        super.a(w5Var, z);
        boolean zD0 = w() != null ? ((f6) w()).d0() : false;
        int i = this.n1;
        if (i != 0) {
            if (i == 1) {
                int size = this.q1.size();
                int i2 = 0;
                while (i2 < size) {
                    this.q1.get(i2).a(zD0, i2, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2) {
                g(zD0);
            }
        } else if (this.q1.size() > 0) {
            this.q1.get(0).a(zD0, 0, true);
        }
        f(false);
    }
}
