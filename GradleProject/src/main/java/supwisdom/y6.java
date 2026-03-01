package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import supwisdom.c7;
import supwisdom.s6;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class y6 extends c7 {
    public static int[] k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9848a;

        static {
            int[] iArr = new int[c7.b.values().length];
            f9848a = iArr;
            try {
                iArr[c7.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9848a[c7.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9848a[c7.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public y6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.h.f9127e = s6.a.LEFT;
        this.i.f9127e = s6.a.RIGHT;
        this.f = 0;
    }

    @Override // supwisdom.c7
    public void a() {
        ConstraintWidget constraintWidgetW;
        ConstraintWidget constraintWidgetW2;
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget.f1227a) {
            this.f7149e.a(constraintWidget.D());
        }
        if (!this.f7149e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourO = this.b.o();
            this.d = dimensionBehaviourO;
            if (dimensionBehaviourO != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviourO == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (((constraintWidgetW2 = this.b.w()) != null && constraintWidgetW2.o() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetW2.o() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
                    int iD = (constraintWidgetW2.D() - this.b.H.c()) - this.b.J.c();
                    a(this.h, constraintWidgetW2.d.h, this.b.H.c());
                    a(this.i, constraintWidgetW2.d.i, -this.b.J.c());
                    this.f7149e.a(iD);
                    return;
                }
                if (this.d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f7149e.a(this.b.D());
                }
            }
        } else if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (((constraintWidgetW = this.b.w()) != null && constraintWidgetW.o() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetW.o() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
            a(this.h, constraintWidgetW.d.h, this.b.H.c());
            a(this.i, constraintWidgetW.d.i, -this.b.J.c());
            return;
        }
        if (this.f7149e.j) {
            ConstraintWidget constraintWidget2 = this.b;
            if (constraintWidget2.f1227a) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.P;
                if (constraintAnchorArr[0].f != null && constraintAnchorArr[1].f != null) {
                    if (constraintWidget2.I()) {
                        this.h.f = this.b.P[0].c();
                        this.i.f = -this.b.P[1].c();
                        return;
                    }
                    s6 s6VarA = a(this.b.P[0]);
                    if (s6VarA != null) {
                        a(this.h, s6VarA, this.b.P[0].c());
                    }
                    s6 s6VarA2 = a(this.b.P[1]);
                    if (s6VarA2 != null) {
                        a(this.i, s6VarA2, -this.b.P[1].c());
                    }
                    this.h.b = true;
                    this.i.b = true;
                    return;
                }
                ConstraintWidget constraintWidget3 = this.b;
                ConstraintAnchor[] constraintAnchorArr2 = constraintWidget3.P;
                if (constraintAnchorArr2[0].f != null) {
                    s6 s6VarA3 = a(constraintAnchorArr2[0]);
                    if (s6VarA3 != null) {
                        a(this.h, s6VarA3, this.b.P[0].c());
                        a(this.i, this.h, this.f7149e.g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr2[1].f != null) {
                    s6 s6VarA4 = a(constraintAnchorArr2[1]);
                    if (s6VarA4 != null) {
                        a(this.i, s6VarA4, -this.b.P[1].c());
                        a(this.h, this.i, -this.f7149e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget3 instanceof i6) || constraintWidget3.w() == null || this.b.a(ConstraintAnchor.Type.CENTER).f != null) {
                    return;
                }
                a(this.h, this.b.w().d.h, this.b.E());
                a(this.i, this.h, this.f7149e.g);
                return;
            }
        }
        if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget4 = this.b;
            int i = constraintWidget4.n;
            if (i == 2) {
                ConstraintWidget constraintWidgetW3 = constraintWidget4.w();
                if (constraintWidgetW3 != null) {
                    t6 t6Var = constraintWidgetW3.f1228e.f7149e;
                    this.f7149e.l.add(t6Var);
                    t6Var.k.add(this.f7149e);
                    t6 t6Var2 = this.f7149e;
                    t6Var2.b = true;
                    t6Var2.k.add(this.h);
                    this.f7149e.k.add(this.i);
                }
            } else if (i == 3) {
                if (constraintWidget4.o == 3) {
                    this.h.f9126a = this;
                    this.i.f9126a = this;
                    a7 a7Var = constraintWidget4.f1228e;
                    a7Var.h.f9126a = this;
                    a7Var.i.f9126a = this;
                    this.f7149e.f9126a = this;
                    if (constraintWidget4.K()) {
                        this.f7149e.l.add(this.b.f1228e.f7149e);
                        this.b.f1228e.f7149e.k.add(this.f7149e);
                        a7 a7Var2 = this.b.f1228e;
                        a7Var2.f7149e.f9126a = this;
                        this.f7149e.l.add(a7Var2.h);
                        this.f7149e.l.add(this.b.f1228e.i);
                        this.b.f1228e.h.k.add(this.f7149e);
                        this.b.f1228e.i.k.add(this.f7149e);
                    } else if (this.b.I()) {
                        this.b.f1228e.f7149e.l.add(this.f7149e);
                        this.f7149e.k.add(this.b.f1228e.f7149e);
                    } else {
                        this.b.f1228e.f7149e.l.add(this.f7149e);
                    }
                } else {
                    t6 t6Var3 = constraintWidget4.f1228e.f7149e;
                    this.f7149e.l.add(t6Var3);
                    t6Var3.k.add(this.f7149e);
                    this.b.f1228e.h.k.add(this.f7149e);
                    this.b.f1228e.i.k.add(this.f7149e);
                    t6 t6Var4 = this.f7149e;
                    t6Var4.b = true;
                    t6Var4.k.add(this.h);
                    this.f7149e.k.add(this.i);
                    this.h.l.add(this.f7149e);
                    this.i.l.add(this.f7149e);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.b;
        ConstraintAnchor[] constraintAnchorArr3 = constraintWidget5.P;
        if (constraintAnchorArr3[0].f != null && constraintAnchorArr3[1].f != null) {
            if (constraintWidget5.I()) {
                this.h.f = this.b.P[0].c();
                this.i.f = -this.b.P[1].c();
                return;
            }
            s6 s6VarA5 = a(this.b.P[0]);
            s6 s6VarA6 = a(this.b.P[1]);
            s6VarA5.b(this);
            s6VarA6.b(this);
            this.j = c7.b.CENTER;
            return;
        }
        ConstraintWidget constraintWidget6 = this.b;
        ConstraintAnchor[] constraintAnchorArr4 = constraintWidget6.P;
        if (constraintAnchorArr4[0].f != null) {
            s6 s6VarA7 = a(constraintAnchorArr4[0]);
            if (s6VarA7 != null) {
                a(this.h, s6VarA7, this.b.P[0].c());
                a(this.i, this.h, 1, this.f7149e);
                return;
            }
            return;
        }
        if (constraintAnchorArr4[1].f != null) {
            s6 s6VarA8 = a(constraintAnchorArr4[1]);
            if (s6VarA8 != null) {
                a(this.i, s6VarA8, -this.b.P[1].c());
                a(this.h, this.i, -1, this.f7149e);
                return;
            }
            return;
        }
        if ((constraintWidget6 instanceof i6) || constraintWidget6.w() == null) {
            return;
        }
        a(this.h, this.b.w().d.h, this.b.E());
        a(this.i, this.h, 1, this.f7149e);
    }

    @Override // supwisdom.c7
    public void b() {
        s6 s6Var = this.h;
        if (s6Var.j) {
            this.b.v(s6Var.g);
        }
    }

    @Override // supwisdom.c7
    public void c() {
        this.c = null;
        this.h.a();
        this.i.a();
        this.f7149e.a();
        this.g = false;
    }

    @Override // supwisdom.c7
    public boolean f() {
        return this.d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.b.n == 0;
    }

    public void g() {
        this.g = false;
        this.h.a();
        this.h.j = false;
        this.i.a();
        this.i.j = false;
        this.f7149e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.b.i();
    }

    public final void a(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
        if (i8 <= i6 && i7 <= i7) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else {
            if (i6 > i6 || i9 > i7) {
                return;
            }
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02ec  */
    @Override // supwisdom.c7, supwisdom.q6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(supwisdom.q6 r17) {
        /*
            Method dump skipped, instruction units count: 1105
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.y6.a(supwisdom.q6):void");
    }
}
