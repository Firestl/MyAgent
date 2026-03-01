package supwisdom;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class c6 extends j6 {
    public int M0 = 0;
    public boolean N0 = true;
    public int O0 = 0;
    public boolean P0 = false;

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean N() {
        return this.P0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean O() {
        return this.P0;
    }

    public boolean T() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        boolean z = true;
        while (true) {
            i = this.L0;
            if (i4 >= i) {
                break;
            }
            ConstraintWidget constraintWidget = this.K0[i4];
            if ((this.N0 || constraintWidget.d()) && ((((i2 = this.M0) == 0 || i2 == 1) && !constraintWidget.N()) || (((i3 = this.M0) == 2 || i3 == 3) && !constraintWidget.O()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.L0; i5++) {
            ConstraintWidget constraintWidget2 = this.K0[i5];
            if (this.N0 || constraintWidget2.d()) {
                if (!z2) {
                    int i6 = this.M0;
                    if (i6 == 0) {
                        iMax = constraintWidget2.a(ConstraintAnchor.Type.LEFT).b();
                    } else if (i6 == 1) {
                        iMax = constraintWidget2.a(ConstraintAnchor.Type.RIGHT).b();
                    } else if (i6 == 2) {
                        iMax = constraintWidget2.a(ConstraintAnchor.Type.TOP).b();
                    } else if (i6 == 3) {
                        iMax = constraintWidget2.a(ConstraintAnchor.Type.BOTTOM).b();
                    }
                    z2 = true;
                }
                int i7 = this.M0;
                if (i7 == 0) {
                    iMax = Math.min(iMax, constraintWidget2.a(ConstraintAnchor.Type.LEFT).b());
                } else if (i7 == 1) {
                    iMax = Math.max(iMax, constraintWidget2.a(ConstraintAnchor.Type.RIGHT).b());
                } else if (i7 == 2) {
                    iMax = Math.min(iMax, constraintWidget2.a(ConstraintAnchor.Type.TOP).b());
                } else if (i7 == 3) {
                    iMax = Math.max(iMax, constraintWidget2.a(ConstraintAnchor.Type.BOTTOM).b());
                }
            }
        }
        int i8 = iMax + this.O0;
        int i9 = this.M0;
        if (i9 == 0 || i9 == 1) {
            a(i8, i8);
        } else {
            b(i8, i8);
        }
        this.P0 = true;
        return true;
    }

    public boolean U() {
        return this.N0;
    }

    public int V() {
        return this.M0;
    }

    public int W() {
        return this.O0;
    }

    public int X() {
        int i = this.M0;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    public void Y() {
        for (int i = 0; i < this.L0; i++) {
            ConstraintWidget constraintWidget = this.K0[i];
            int i2 = this.M0;
            if (i2 == 0 || i2 == 1) {
                constraintWidget.a(0, true);
            } else if (i2 == 2 || i2 == 3) {
                constraintWidget.a(1, true);
            }
        }
    }

    @Override // supwisdom.j6, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.a(constraintWidget, map);
        c6 c6Var = (c6) constraintWidget;
        this.M0 = c6Var.M0;
        this.N0 = c6Var.N0;
        this.O0 = c6Var.O0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean d() {
        return true;
    }

    public void e(boolean z) {
        this.N0 = z;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + i() + " {";
        for (int i = 0; i < this.L0; i++) {
            ConstraintWidget constraintWidget = this.K0[i];
            if (i > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.i();
        }
        return str + Operators.BLOCK_END_STR;
    }

    public void y(int i) {
        this.M0 = i;
    }

    public void z(int i) {
        this.O0 = i;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(w5 w5Var, boolean z) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z2;
        int i;
        int i2;
        ConstraintAnchor[] constraintAnchorArr2 = this.P;
        constraintAnchorArr2[0] = this.H;
        constraintAnchorArr2[2] = this.I;
        constraintAnchorArr2[1] = this.J;
        constraintAnchorArr2[3] = this.K;
        int i3 = 0;
        while (true) {
            constraintAnchorArr = this.P;
            if (i3 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i3].i = w5Var.a(constraintAnchorArr[i3]);
            i3++;
        }
        int i4 = this.M0;
        if (i4 < 0 || i4 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i4];
        if (!this.P0) {
            T();
        }
        if (this.P0) {
            this.P0 = false;
            int i5 = this.M0;
            if (i5 == 0 || i5 == 1) {
                w5Var.a(this.H.i, this.Y);
                w5Var.a(this.J.i, this.Y);
                return;
            } else {
                if (i5 == 2 || i5 == 3) {
                    w5Var.a(this.I.i, this.Z);
                    w5Var.a(this.K.i, this.Z);
                    return;
                }
                return;
            }
        }
        for (int i6 = 0; i6 < this.L0; i6++) {
            ConstraintWidget constraintWidget = this.K0[i6];
            if ((this.N0 || constraintWidget.d()) && ((((i = this.M0) == 0 || i == 1) && constraintWidget.o() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.H.f != null && constraintWidget.J.f != null) || (((i2 = this.M0) == 2 || i2 == 3) && constraintWidget.A() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.I.f != null && constraintWidget.K.f != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.H.i() || this.J.i();
        boolean z4 = this.I.i() || this.K.i();
        int i7 = !z2 && ((this.M0 == 0 && z3) || ((this.M0 == 2 && z4) || ((this.M0 == 1 && z3) || (this.M0 == 3 && z4)))) ? 5 : 4;
        for (int i8 = 0; i8 < this.L0; i8++) {
            ConstraintWidget constraintWidget2 = this.K0[i8];
            if (this.N0 || constraintWidget2.d()) {
                SolverVariable solverVariableA = w5Var.a(constraintWidget2.P[this.M0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.P;
                int i9 = this.M0;
                constraintAnchorArr3[i9].i = solverVariableA;
                int i10 = (constraintAnchorArr3[i9].f == null || constraintAnchorArr3[i9].f.d != this) ? 0 : constraintAnchorArr3[i9].g + 0;
                int i11 = this.M0;
                if (i11 != 0 && i11 != 2) {
                    w5Var.a(constraintAnchor.i, solverVariableA, this.O0 + i10, z2);
                } else {
                    w5Var.b(constraintAnchor.i, solverVariableA, this.O0 - i10, z2);
                }
                w5Var.a(constraintAnchor.i, solverVariableA, this.O0 + i10, i7);
            }
        }
        int i12 = this.M0;
        if (i12 == 0) {
            w5Var.a(this.J.i, this.H.i, 0, 8);
            w5Var.a(this.H.i, this.T.J.i, 0, 4);
            w5Var.a(this.H.i, this.T.H.i, 0, 0);
            return;
        }
        if (i12 == 1) {
            w5Var.a(this.H.i, this.J.i, 0, 8);
            w5Var.a(this.H.i, this.T.H.i, 0, 4);
            w5Var.a(this.H.i, this.T.J.i, 0, 0);
        } else if (i12 == 2) {
            w5Var.a(this.K.i, this.I.i, 0, 8);
            w5Var.a(this.I.i, this.T.K.i, 0, 4);
            w5Var.a(this.I.i, this.T.I.i, 0, 0);
        } else if (i12 == 3) {
            w5Var.a(this.I.i, this.K.i, 0, 8);
            w5Var.a(this.I.i, this.T.I.i, 0, 4);
            w5Var.a(this.I.i, this.T.K.i, 0, 0);
        }
    }
}
