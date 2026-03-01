package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: WidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c7 implements q6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7148a;
    public ConstraintWidget b;
    public z6 c;
    public ConstraintWidget.DimensionBehaviour d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t6 f7149e = new t6(this);
    public int f = 0;
    public boolean g = false;
    public s6 h = new s6(this);
    public s6 i = new s6(this);
    public b j = b.NONE;

    /* JADX INFO: compiled from: WidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7150a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f7150a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7150a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7150a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7150a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7150a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: WidgetRun.java */
    public enum b {
        NONE,
        START,
        END,
        CENTER
    }

    public c7(ConstraintWidget constraintWidget) {
        this.b = constraintWidget;
    }

    public final s6 a(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.d;
        int i = a.f7150a[constraintAnchor2.f1225e.ordinal()];
        if (i == 1) {
            return constraintWidget.d.h;
        }
        if (i == 2) {
            return constraintWidget.d.i;
        }
        if (i == 3) {
            return constraintWidget.f1228e.h;
        }
        if (i == 4) {
            return constraintWidget.f1228e.k;
        }
        if (i != 5) {
            return null;
        }
        return constraintWidget.f1228e.i;
    }

    public abstract void a();

    @Override // supwisdom.q6
    public void a(q6 q6Var) {
    }

    public abstract void b();

    public final void b(int i, int i2) {
        int i3 = this.f7148a;
        if (i3 == 0) {
            this.f7149e.a(a(i2, i));
            return;
        }
        if (i3 == 1) {
            this.f7149e.a(Math.min(a(this.f7149e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetW = this.b.w();
            if (constraintWidgetW != null) {
                if ((i == 0 ? constraintWidgetW.d : constraintWidgetW.f1228e).f7149e.j) {
                    ConstraintWidget constraintWidget = this.b;
                    this.f7149e.a(a((int) ((r8.f7149e.g * (i == 0 ? constraintWidget.s : constraintWidget.v)) + 0.5f), i));
                    return;
                }
                return;
            }
            return;
        }
        if (i3 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.b;
        y6 y6Var = constraintWidget2.d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = y6Var.d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && y6Var.f7148a == 3) {
            a7 a7Var = constraintWidget2.f1228e;
            if (a7Var.d == dimensionBehaviour2 && a7Var.f7148a == 3) {
                return;
            }
        }
        ConstraintWidget constraintWidget3 = this.b;
        if ((i == 0 ? constraintWidget3.f1228e : constraintWidget3.d).f7149e.j) {
            float fJ = this.b.j();
            this.f7149e.a(i == 1 ? (int) ((r8.f7149e.g / fJ) + 0.5f) : (int) ((fJ * r8.f7149e.g) + 0.5f));
        }
    }

    public void b(q6 q6Var) {
    }

    public abstract void c();

    public void c(q6 q6Var) {
    }

    public long d() {
        if (this.f7149e.j) {
            return r0.g;
        }
        return 0L;
    }

    public boolean e() {
        return this.g;
    }

    public abstract boolean f();

    public void a(q6 q6Var, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        s6 s6VarA = a(constraintAnchor);
        s6 s6VarA2 = a(constraintAnchor2);
        if (s6VarA.j && s6VarA2.j) {
            int iC = s6VarA.g + constraintAnchor.c();
            int iC2 = s6VarA2.g - constraintAnchor2.c();
            int i2 = iC2 - iC;
            if (!this.f7149e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                b(i, i2);
            }
            t6 t6Var = this.f7149e;
            if (t6Var.j) {
                if (t6Var.g == i2) {
                    this.h.a(iC);
                    this.i.a(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.b;
                float fM = i == 0 ? constraintWidget.m() : constraintWidget.y();
                if (s6VarA == s6VarA2) {
                    iC = s6VarA.g;
                    iC2 = s6VarA2.g;
                    fM = 0.5f;
                }
                this.h.a((int) (iC + 0.5f + (((iC2 - iC) - this.f7149e.g) * fM)));
                this.i.a(this.h.g + this.f7149e.g);
            }
        }
    }

    public final int a(int i, int i2) {
        int iMax;
        if (i2 == 0) {
            ConstraintWidget constraintWidget = this.b;
            int i3 = constraintWidget.r;
            iMax = Math.max(constraintWidget.q, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.b;
            int i4 = constraintWidget2.u;
            iMax = Math.max(constraintWidget2.t, i);
            if (i4 > 0) {
                iMax = Math.min(i4, i);
            }
            if (iMax == i) {
                return i;
            }
        }
        return iMax;
    }

    public final s6 a(ConstraintAnchor constraintAnchor, int i) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.d;
        c7 c7Var = i == 0 ? constraintWidget.d : constraintWidget.f1228e;
        int i2 = a.f7150a[constraintAnchor.f.f1225e.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        return null;
                    }
                }
            }
            return c7Var.i;
        }
        return c7Var.h;
    }

    public final void a(s6 s6Var, s6 s6Var2, int i) {
        s6Var.l.add(s6Var2);
        s6Var.f = i;
        s6Var2.k.add(s6Var);
    }

    public final void a(s6 s6Var, s6 s6Var2, int i, t6 t6Var) {
        s6Var.l.add(s6Var2);
        s6Var.l.add(this.f7149e);
        s6Var.h = i;
        s6Var.i = t6Var;
        s6Var2.k.add(s6Var);
        t6Var.k.add(s6Var);
    }
}
