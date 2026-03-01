package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import supwisdom.c7;
import supwisdom.s6;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class a7 extends c7 {
    public s6 k;
    public t6 l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6872a;

        static {
            int[] iArr = new int[c7.b.values().length];
            f6872a = iArr;
            try {
                iArr[c7.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6872a[c7.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6872a[c7.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public a7(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        s6 s6Var = new s6(this);
        this.k = s6Var;
        this.l = null;
        this.h.f9127e = s6.a.TOP;
        this.i.f9127e = s6.a.BOTTOM;
        s6Var.f9127e = s6.a.BASELINE;
        this.f = 1;
    }

    @Override // supwisdom.c7, supwisdom.q6
    public void a(q6 q6Var) {
        float f;
        float fJ;
        float fJ2;
        int i;
        int i2 = a.f6872a[this.j.ordinal()];
        if (i2 == 1) {
            c(q6Var);
        } else if (i2 == 2) {
            b(q6Var);
        } else if (i2 == 3) {
            ConstraintWidget constraintWidget = this.b;
            a(q6Var, constraintWidget.I, constraintWidget.K, 1);
            return;
        }
        t6 t6Var = this.f7149e;
        if (t6Var.c && !t6Var.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.b;
            int i3 = constraintWidget2.o;
            if (i3 == 2) {
                ConstraintWidget constraintWidgetW = constraintWidget2.w();
                if (constraintWidgetW != null) {
                    if (constraintWidgetW.f1228e.f7149e.j) {
                        this.f7149e.a((int) ((r7.g * this.b.v) + 0.5f));
                    }
                }
            } else if (i3 == 3 && constraintWidget2.d.f7149e.j) {
                int iK = constraintWidget2.k();
                if (iK == -1) {
                    ConstraintWidget constraintWidget3 = this.b;
                    f = constraintWidget3.d.f7149e.g;
                    fJ = constraintWidget3.j();
                } else if (iK == 0) {
                    fJ2 = r7.d.f7149e.g * this.b.j();
                    i = (int) (fJ2 + 0.5f);
                    this.f7149e.a(i);
                } else if (iK != 1) {
                    i = 0;
                    this.f7149e.a(i);
                } else {
                    ConstraintWidget constraintWidget4 = this.b;
                    f = constraintWidget4.d.f7149e.g;
                    fJ = constraintWidget4.j();
                }
                fJ2 = f / fJ;
                i = (int) (fJ2 + 0.5f);
                this.f7149e.a(i);
            }
        }
        s6 s6Var = this.h;
        if (s6Var.c) {
            s6 s6Var2 = this.i;
            if (s6Var2.c) {
                if (s6Var.j && s6Var2.j && this.f7149e.j) {
                    return;
                }
                if (!this.f7149e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.b;
                    if (constraintWidget5.n == 0 && !constraintWidget5.K()) {
                        s6 s6Var3 = this.h.l.get(0);
                        s6 s6Var4 = this.i.l.get(0);
                        int i4 = s6Var3.g;
                        s6 s6Var5 = this.h;
                        int i5 = i4 + s6Var5.f;
                        int i6 = s6Var4.g + this.i.f;
                        s6Var5.a(i5);
                        this.i.a(i6);
                        this.f7149e.a(i6 - i5);
                        return;
                    }
                }
                if (!this.f7149e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f7148a == 1 && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    s6 s6Var6 = this.h.l.get(0);
                    int i7 = (this.i.l.get(0).g + this.i.f) - (s6Var6.g + this.h.f);
                    t6 t6Var2 = this.f7149e;
                    int i8 = t6Var2.m;
                    if (i7 < i8) {
                        t6Var2.a(i7);
                    } else {
                        t6Var2.a(i8);
                    }
                }
                if (this.f7149e.j && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    s6 s6Var7 = this.h.l.get(0);
                    s6 s6Var8 = this.i.l.get(0);
                    int i9 = s6Var7.g + this.h.f;
                    int i10 = s6Var8.g + this.i.f;
                    float fY = this.b.y();
                    if (s6Var7 == s6Var8) {
                        i9 = s6Var7.g;
                        i10 = s6Var8.g;
                        fY = 0.5f;
                    }
                    this.h.a((int) (i9 + 0.5f + (((i10 - i9) - this.f7149e.g) * fY)));
                    this.i.a(this.h.g + this.f7149e.g);
                }
            }
        }
    }

    @Override // supwisdom.c7
    public void b() {
        s6 s6Var = this.h;
        if (s6Var.j) {
            this.b.w(s6Var.g);
        }
    }

    @Override // supwisdom.c7
    public void c() {
        this.c = null;
        this.h.a();
        this.i.a();
        this.k.a();
        this.f7149e.a();
        this.g = false;
    }

    @Override // supwisdom.c7
    public boolean f() {
        return this.d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.b.o == 0;
    }

    public void g() {
        this.g = false;
        this.h.a();
        this.h.j = false;
        this.i.a();
        this.i.j = false;
        this.k.a();
        this.k.j = false;
        this.f7149e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.b.i();
    }

    @Override // supwisdom.c7
    public void a() {
        ConstraintWidget constraintWidgetW;
        ConstraintWidget constraintWidgetW2;
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget.f1227a) {
            this.f7149e.a(constraintWidget.l());
        }
        if (!this.f7149e.j) {
            this.d = this.b.A();
            if (this.b.G()) {
                this.l = new n6(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetW2 = this.b.w()) != null && constraintWidgetW2.A() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iL = (constraintWidgetW2.l() - this.b.I.c()) - this.b.K.c();
                    a(this.h, constraintWidgetW2.f1228e.h, this.b.I.c());
                    a(this.i, constraintWidgetW2.f1228e.i, -this.b.K.c());
                    this.f7149e.a(iL);
                    return;
                }
                if (this.d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f7149e.a(this.b.l());
                }
            }
        } else if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetW = this.b.w()) != null && constraintWidgetW.A() == ConstraintWidget.DimensionBehaviour.FIXED) {
            a(this.h, constraintWidgetW.f1228e.h, this.b.I.c());
            a(this.i, constraintWidgetW.f1228e.i, -this.b.K.c());
            return;
        }
        if (this.f7149e.j) {
            ConstraintWidget constraintWidget2 = this.b;
            if (constraintWidget2.f1227a) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.P;
                if (constraintAnchorArr[2].f != null && constraintAnchorArr[3].f != null) {
                    if (constraintWidget2.K()) {
                        this.h.f = this.b.P[2].c();
                        this.i.f = -this.b.P[3].c();
                    } else {
                        s6 s6VarA = a(this.b.P[2]);
                        if (s6VarA != null) {
                            a(this.h, s6VarA, this.b.P[2].c());
                        }
                        s6 s6VarA2 = a(this.b.P[3]);
                        if (s6VarA2 != null) {
                            a(this.i, s6VarA2, -this.b.P[3].c());
                        }
                        this.h.b = true;
                        this.i.b = true;
                    }
                    if (this.b.G()) {
                        a(this.k, this.h, this.b.f());
                        return;
                    }
                    return;
                }
                ConstraintWidget constraintWidget3 = this.b;
                ConstraintAnchor[] constraintAnchorArr2 = constraintWidget3.P;
                if (constraintAnchorArr2[2].f != null) {
                    s6 s6VarA3 = a(constraintAnchorArr2[2]);
                    if (s6VarA3 != null) {
                        a(this.h, s6VarA3, this.b.P[2].c());
                        a(this.i, this.h, this.f7149e.g);
                        if (this.b.G()) {
                            a(this.k, this.h, this.b.f());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr2[3].f != null) {
                    s6 s6VarA4 = a(constraintAnchorArr2[3]);
                    if (s6VarA4 != null) {
                        a(this.i, s6VarA4, -this.b.P[3].c());
                        a(this.h, this.i, -this.f7149e.g);
                    }
                    if (this.b.G()) {
                        a(this.k, this.h, this.b.f());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr2[4].f != null) {
                    s6 s6VarA5 = a(constraintAnchorArr2[4]);
                    if (s6VarA5 != null) {
                        a(this.k, s6VarA5, 0);
                        a(this.h, this.k, -this.b.f());
                        a(this.i, this.h, this.f7149e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget3 instanceof i6) || constraintWidget3.w() == null || this.b.a(ConstraintAnchor.Type.CENTER).f != null) {
                    return;
                }
                a(this.h, this.b.w().f1228e.h, this.b.F());
                a(this.i, this.h, this.f7149e.g);
                if (this.b.G()) {
                    a(this.k, this.h, this.b.f());
                    return;
                }
                return;
            }
        }
        if (!this.f7149e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget4 = this.b;
            int i = constraintWidget4.o;
            if (i != 2) {
                if (i == 3 && !constraintWidget4.K()) {
                    ConstraintWidget constraintWidget5 = this.b;
                    if (constraintWidget5.n != 3) {
                        t6 t6Var = constraintWidget5.d.f7149e;
                        this.f7149e.l.add(t6Var);
                        t6Var.k.add(this.f7149e);
                        t6 t6Var2 = this.f7149e;
                        t6Var2.b = true;
                        t6Var2.k.add(this.h);
                        this.f7149e.k.add(this.i);
                    }
                }
            } else {
                ConstraintWidget constraintWidgetW3 = constraintWidget4.w();
                if (constraintWidgetW3 != null) {
                    t6 t6Var3 = constraintWidgetW3.f1228e.f7149e;
                    this.f7149e.l.add(t6Var3);
                    t6Var3.k.add(this.f7149e);
                    t6 t6Var4 = this.f7149e;
                    t6Var4.b = true;
                    t6Var4.k.add(this.h);
                    this.f7149e.k.add(this.i);
                }
            }
        } else {
            this.f7149e.b(this);
        }
        ConstraintWidget constraintWidget6 = this.b;
        ConstraintAnchor[] constraintAnchorArr3 = constraintWidget6.P;
        if (constraintAnchorArr3[2].f != null && constraintAnchorArr3[3].f != null) {
            if (constraintWidget6.K()) {
                this.h.f = this.b.P[2].c();
                this.i.f = -this.b.P[3].c();
            } else {
                s6 s6VarA6 = a(this.b.P[2]);
                s6 s6VarA7 = a(this.b.P[3]);
                s6VarA6.b(this);
                s6VarA7.b(this);
                this.j = c7.b.CENTER;
            }
            if (this.b.G()) {
                a(this.k, this.h, 1, this.l);
            }
        } else {
            ConstraintWidget constraintWidget7 = this.b;
            ConstraintAnchor[] constraintAnchorArr4 = constraintWidget7.P;
            if (constraintAnchorArr4[2].f != null) {
                s6 s6VarA8 = a(constraintAnchorArr4[2]);
                if (s6VarA8 != null) {
                    a(this.h, s6VarA8, this.b.P[2].c());
                    a(this.i, this.h, 1, this.f7149e);
                    if (this.b.G()) {
                        a(this.k, this.h, 1, this.l);
                    }
                    if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.b.j() > 0.0f) {
                        y6 y6Var = this.b.d;
                        if (y6Var.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            y6Var.f7149e.k.add(this.f7149e);
                            this.f7149e.l.add(this.b.d.f7149e);
                            this.f7149e.f9126a = this;
                        }
                    }
                }
            } else if (constraintAnchorArr4[3].f != null) {
                s6 s6VarA9 = a(constraintAnchorArr4[3]);
                if (s6VarA9 != null) {
                    a(this.i, s6VarA9, -this.b.P[3].c());
                    a(this.h, this.i, -1, this.f7149e);
                    if (this.b.G()) {
                        a(this.k, this.h, 1, this.l);
                    }
                }
            } else if (constraintAnchorArr4[4].f != null) {
                s6 s6VarA10 = a(constraintAnchorArr4[4]);
                if (s6VarA10 != null) {
                    a(this.k, s6VarA10, 0);
                    a(this.h, this.k, -1, this.l);
                    a(this.i, this.h, 1, this.f7149e);
                }
            } else if (!(constraintWidget7 instanceof i6) && constraintWidget7.w() != null) {
                a(this.h, this.b.w().f1228e.h, this.b.F());
                a(this.i, this.h, 1, this.f7149e);
                if (this.b.G()) {
                    a(this.k, this.h, 1, this.l);
                }
                if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.b.j() > 0.0f) {
                    y6 y6Var2 = this.b.d;
                    if (y6Var2.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        y6Var2.f7149e.k.add(this.f7149e);
                        this.f7149e.l.add(this.b.d.f7149e);
                        this.f7149e.f9126a = this;
                    }
                }
            }
        }
        if (this.f7149e.l.size() == 0) {
            this.f7149e.c = true;
        }
    }
}
