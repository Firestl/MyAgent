package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import supwisdom.o6;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class l6 extends j6 {
    public int M0 = 0;
    public int N0 = 0;
    public int O0 = 0;
    public int P0 = 0;
    public int Q0 = 0;
    public int R0 = 0;
    public boolean S0 = false;
    public int T0 = 0;
    public int U0 = 0;
    public o6.a V0 = new o6.a();
    public o6.b W0 = null;

    public void A(int i) {
        this.P0 = i;
    }

    public void B(int i) {
        this.Q0 = i;
    }

    public void C(int i) {
        this.R0 = i;
    }

    public void D(int i) {
        this.O0 = i;
        this.Q0 = i;
        this.R0 = i;
    }

    public void E(int i) {
        this.M0 = i;
    }

    public void T() {
        for (int i = 0; i < this.L0; i++) {
            ConstraintWidget constraintWidget = this.K0[i];
            if (constraintWidget != null) {
                constraintWidget.c(true);
            }
        }
    }

    public int U() {
        return this.U0;
    }

    public int V() {
        return this.T0;
    }

    public int W() {
        return this.N0;
    }

    public int X() {
        return this.Q0;
    }

    public int Y() {
        return this.R0;
    }

    public int Z() {
        return this.M0;
    }

    @Override // supwisdom.j6, supwisdom.i6
    public void a(f6 f6Var) {
        T();
    }

    public boolean a0() {
        ConstraintWidget constraintWidget = this.T;
        o6.b bVarW = constraintWidget != null ? ((f6) constraintWidget).W() : null;
        if (bVarW == null) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= this.L0) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.K0[i];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof h6)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourB = constraintWidget2.b(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourB2 = constraintWidget2.b(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviourB == dimensionBehaviour && constraintWidget2.n != 1 && dimensionBehaviourB2 == dimensionBehaviour && constraintWidget2.o != 1)) {
                    if (dimensionBehaviourB == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        dimensionBehaviourB = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviourB2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        dimensionBehaviourB2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    o6.a aVar = this.V0;
                    aVar.f8631a = dimensionBehaviourB;
                    aVar.b = dimensionBehaviourB2;
                    aVar.c = constraintWidget2.D();
                    this.V0.d = constraintWidget2.l();
                    bVarW.a(constraintWidget2, this.V0);
                    constraintWidget2.u(this.V0.f8632e);
                    constraintWidget2.m(this.V0.f);
                    constraintWidget2.i(this.V0.g);
                }
            }
            i++;
        }
    }

    public void b(int i, int i2, int i3, int i4) {
    }

    public boolean b0() {
        return this.S0;
    }

    public void e(boolean z) {
        if (this.O0 > 0 || this.P0 > 0) {
            if (z) {
                this.Q0 = this.P0;
                this.R0 = this.O0;
            } else {
                this.Q0 = this.O0;
                this.R0 = this.P0;
            }
        }
    }

    public void f(boolean z) {
        this.S0 = z;
    }

    public void g(int i, int i2) {
        this.T0 = i;
        this.U0 = i2;
    }

    public void y(int i) {
        this.M0 = i;
        this.N0 = i;
        this.O0 = i;
        this.P0 = i;
    }

    public void z(int i) {
        this.N0 = i;
    }

    public void a(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        while (this.W0 == null && w() != null) {
            this.W0 = ((f6) w()).W();
        }
        o6.a aVar = this.V0;
        aVar.f8631a = dimensionBehaviour;
        aVar.b = dimensionBehaviour2;
        aVar.c = i;
        aVar.d = i2;
        this.W0.a(constraintWidget, aVar);
        constraintWidget.u(this.V0.f8632e);
        constraintWidget.m(this.V0.f);
        constraintWidget.a(this.V0.h);
        constraintWidget.i(this.V0.g);
    }
}
