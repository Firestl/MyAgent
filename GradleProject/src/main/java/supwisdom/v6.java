package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: Grouping.java */
/* JADX INFO: loaded from: classes.dex */
public class v6 {
    public static boolean a(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        return (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT));
    }

    /* JADX WARN: Removed duplicated region for block: B:179:0x0348  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(supwisdom.f6 r16, supwisdom.o6.b r17) {
        /*
            Method dump skipped, instruction units count: 918
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.v6.a(supwisdom.f6, supwisdom.o6$b):boolean");
    }

    public static b7 a(ArrayList<b7> arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            b7 b7Var = arrayList.get(i2);
            if (i == b7Var.b) {
                return b7Var;
            }
        }
        return null;
    }

    public static b7 a(ConstraintWidget constraintWidget, int i, ArrayList<b7> arrayList, b7 b7Var) {
        int i2;
        int iX;
        if (i == 0) {
            i2 = constraintWidget.H0;
        } else {
            i2 = constraintWidget.I0;
        }
        if (i2 != -1 && (b7Var == null || i2 != b7Var.b)) {
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList.size()) {
                    break;
                }
                b7 b7Var2 = arrayList.get(i3);
                if (b7Var2.a() == i2) {
                    if (b7Var != null) {
                        b7Var.a(i, b7Var2);
                        arrayList.remove(b7Var);
                    }
                    b7Var = b7Var2;
                } else {
                    i3++;
                }
            }
        } else if (i2 != -1) {
            return b7Var;
        }
        if (b7Var == null) {
            if ((constraintWidget instanceof j6) && (iX = ((j6) constraintWidget).x(i)) != -1) {
                int i4 = 0;
                while (true) {
                    if (i4 >= arrayList.size()) {
                        break;
                    }
                    b7 b7Var3 = arrayList.get(i4);
                    if (b7Var3.a() == iX) {
                        b7Var = b7Var3;
                        break;
                    }
                    i4++;
                }
            }
            if (b7Var == null) {
                b7Var = new b7(i);
            }
            arrayList.add(b7Var);
        }
        if (b7Var.a(constraintWidget)) {
            if (constraintWidget instanceof h6) {
                h6 h6Var = (h6) constraintWidget;
                h6Var.T().a(h6Var.U() == 0 ? 1 : 0, arrayList, b7Var);
            }
            if (i == 0) {
                constraintWidget.H0 = b7Var.a();
                constraintWidget.H.a(i, arrayList, b7Var);
                constraintWidget.J.a(i, arrayList, b7Var);
            } else {
                constraintWidget.I0 = b7Var.a();
                constraintWidget.I.a(i, arrayList, b7Var);
                constraintWidget.L.a(i, arrayList, b7Var);
                constraintWidget.K.a(i, arrayList, b7Var);
            }
            constraintWidget.O.a(i, arrayList, b7Var);
        }
        return b7Var;
    }
}
