package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public class h6 extends ConstraintWidget {
    public float K0 = -1.0f;
    public int L0 = -1;
    public int M0 = -1;
    public ConstraintAnchor N0 = this.I;
    public int O0 = 0;
    public boolean P0;

    /* JADX INFO: compiled from: Guideline.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7804a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f7804a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7804a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7804a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7804a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7804a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7804a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7804a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7804a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7804a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public h6() {
        this.Q.clear();
        this.Q.add(this.N0);
        int length = this.P.length;
        for (int i = 0; i < length; i++) {
            this.P[i] = this.N0;
        }
    }

    public void A(int i) {
        if (this.O0 == i) {
            return;
        }
        this.O0 = i;
        this.Q.clear();
        if (this.O0 == 1) {
            this.N0 = this.H;
        } else {
            this.N0 = this.I;
        }
        this.Q.add(this.N0);
        int length = this.P.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.P[i2] = this.N0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean N() {
        return this.P0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean O() {
        return this.P0;
    }

    public ConstraintAnchor T() {
        return this.N0;
    }

    public int U() {
        return this.O0;
    }

    public int V() {
        return this.L0;
    }

    public int W() {
        return this.M0;
    }

    public float X() {
        return this.K0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.a(constraintWidget, map);
        h6 h6Var = (h6) constraintWidget;
        this.K0 = h6Var.K0;
        this.L0 = h6Var.L0;
        this.M0 = h6Var.M0;
        A(h6Var.O0);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void b(w5 w5Var, boolean z) {
        if (w() == null) {
            return;
        }
        int iB = w5Var.b(this.N0);
        if (this.O0 == 1) {
            v(iB);
            w(0);
            m(w().l());
            u(0);
            return;
        }
        v(0);
        w(iB);
        u(w().D());
        m(0);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean d() {
        return true;
    }

    public void e(float f) {
        if (f > -1.0f) {
            this.K0 = f;
            this.L0 = -1;
            this.M0 = -1;
        }
    }

    public void x(int i) {
        this.N0.a(i);
        this.P0 = true;
    }

    public void y(int i) {
        if (i > -1) {
            this.K0 = -1.0f;
            this.L0 = i;
            this.M0 = -1;
        }
    }

    public void z(int i) {
        if (i > -1) {
            this.K0 = -1.0f;
            this.L0 = -1;
            this.M0 = i;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public ConstraintAnchor a(ConstraintAnchor.Type type) {
        switch (a.f7804a[type.ordinal()]) {
            case 1:
            case 2:
                if (this.O0 == 1) {
                    return this.N0;
                }
                break;
            case 3:
            case 4:
                if (this.O0 == 0) {
                    return this.N0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(type.name());
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(w5 w5Var, boolean z) {
        f6 f6Var = (f6) w();
        if (f6Var == null) {
            return;
        }
        ConstraintAnchor constraintAnchorA = f6Var.a(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorA2 = f6Var.a(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.T;
        boolean z2 = constraintWidget != null && constraintWidget.S[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.O0 == 0) {
            constraintAnchorA = f6Var.a(ConstraintAnchor.Type.TOP);
            constraintAnchorA2 = f6Var.a(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.T;
            z2 = constraintWidget2 != null && constraintWidget2.S[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.P0 && this.N0.k()) {
            SolverVariable solverVariableA = w5Var.a(this.N0);
            w5Var.a(solverVariableA, this.N0.b());
            if (this.L0 != -1) {
                if (z2) {
                    w5Var.b(w5Var.a(constraintAnchorA2), solverVariableA, 0, 5);
                }
            } else if (this.M0 != -1 && z2) {
                SolverVariable solverVariableA2 = w5Var.a(constraintAnchorA2);
                w5Var.b(solverVariableA, w5Var.a(constraintAnchorA), 0, 5);
                w5Var.b(solverVariableA2, solverVariableA, 0, 5);
            }
            this.P0 = false;
            return;
        }
        if (this.L0 != -1) {
            SolverVariable solverVariableA3 = w5Var.a(this.N0);
            w5Var.a(solverVariableA3, w5Var.a(constraintAnchorA), this.L0, 8);
            if (z2) {
                w5Var.b(w5Var.a(constraintAnchorA2), solverVariableA3, 0, 5);
                return;
            }
            return;
        }
        if (this.M0 == -1) {
            if (this.K0 != -1.0f) {
                w5Var.a(w5.a(w5Var, w5Var.a(this.N0), w5Var.a(constraintAnchorA2), this.K0));
                return;
            }
            return;
        }
        SolverVariable solverVariableA4 = w5Var.a(this.N0);
        SolverVariable solverVariableA5 = w5Var.a(constraintAnchorA2);
        w5Var.a(solverVariableA4, solverVariableA5, -this.M0, 8);
        if (z2) {
            w5Var.b(solverVariableA4, w5Var.a(constraintAnchorA), 0, 5);
            w5Var.b(solverVariableA5, solverVariableA4, 0, 5);
        }
    }
}
