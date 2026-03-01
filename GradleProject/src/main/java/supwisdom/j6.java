package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class j6 extends ConstraintWidget implements i6 {
    public ConstraintWidget[] K0 = new ConstraintWidget[4];
    public int L0 = 0;

    @Override // supwisdom.i6
    public void a(ConstraintWidget constraintWidget) {
        if (constraintWidget == this || constraintWidget == null) {
            return;
        }
        int i = this.L0 + 1;
        ConstraintWidget[] constraintWidgetArr = this.K0;
        if (i > constraintWidgetArr.length) {
            this.K0 = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.K0;
        int i2 = this.L0;
        constraintWidgetArr2[i2] = constraintWidget;
        this.L0 = i2 + 1;
    }

    @Override // supwisdom.i6
    public void a(f6 f6Var) {
    }

    public int x(int i) {
        int i2;
        int i3;
        for (int i4 = 0; i4 < this.L0; i4++) {
            ConstraintWidget constraintWidget = this.K0[i4];
            if (i == 0 && (i3 = constraintWidget.H0) != -1) {
                return i3;
            }
            if (i == 1 && (i2 = constraintWidget.I0) != -1) {
                return i2;
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.a(constraintWidget, map);
        j6 j6Var = (j6) constraintWidget;
        this.L0 = 0;
        int i = j6Var.L0;
        for (int i2 = 0; i2 < i; i2++) {
            a(map.get(j6Var.K0[i2]));
        }
    }

    @Override // supwisdom.i6
    public void a() {
        this.L0 = 0;
        Arrays.fill(this.K0, (Object) null);
    }

    public void a(ArrayList<b7> arrayList, int i, b7 b7Var) {
        for (int i2 = 0; i2 < this.L0; i2++) {
            b7Var.a(this.K0[i2]);
        }
        for (int i3 = 0; i3 < this.L0; i3++) {
            v6.a(this.K0[i3], i, arrayList, b7Var);
        }
    }
}
