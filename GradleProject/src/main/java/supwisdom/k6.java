package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class k6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean[] f8126a = new boolean[3];

    public static void a(f6 f6Var, w5 w5Var, ConstraintWidget constraintWidget) {
        constraintWidget.l = -1;
        constraintWidget.m = -1;
        if (f6Var.S[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.S[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.H.g;
            int iD = f6Var.D() - constraintWidget.J.g;
            ConstraintAnchor constraintAnchor = constraintWidget.H;
            constraintAnchor.i = w5Var.a(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.J;
            constraintAnchor2.i = w5Var.a(constraintAnchor2);
            w5Var.a(constraintWidget.H.i, i);
            w5Var.a(constraintWidget.J.i, iD);
            constraintWidget.l = 2;
            constraintWidget.c(i, iD);
        }
        if (f6Var.S[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || constraintWidget.S[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i2 = constraintWidget.I.g;
        int iL = f6Var.l() - constraintWidget.K.g;
        ConstraintAnchor constraintAnchor3 = constraintWidget.I;
        constraintAnchor3.i = w5Var.a(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.K;
        constraintAnchor4.i = w5Var.a(constraintAnchor4);
        w5Var.a(constraintWidget.I.i, i2);
        w5Var.a(constraintWidget.K.i, iL);
        if (constraintWidget.e0 > 0 || constraintWidget.C() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.L;
            constraintAnchor5.i = w5Var.a(constraintAnchor5);
            w5Var.a(constraintWidget.L.i, constraintWidget.e0 + i2);
        }
        constraintWidget.m = 2;
        constraintWidget.f(i2, iL);
    }

    public static final boolean a(int i, int i2) {
        return (i & i2) == i2;
    }
}
