package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class m6 extends ConstraintWidget {
    public ArrayList<ConstraintWidget> K0 = new ArrayList<>();

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void Q() {
        this.K0.clear();
        super.Q();
    }

    public ArrayList<ConstraintWidget> T() {
        return this.K0;
    }

    public void U() {
        ArrayList<ConstraintWidget> arrayList = this.K0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.K0.get(i);
            if (constraintWidget instanceof m6) {
                ((m6) constraintWidget).U();
            }
        }
    }

    public void V() {
        this.K0.clear();
    }

    public void a(ConstraintWidget constraintWidget) {
        this.K0.add(constraintWidget);
        if (constraintWidget.w() != null) {
            ((m6) constraintWidget.w()).c(constraintWidget);
        }
        constraintWidget.b(this);
    }

    public void c(ConstraintWidget constraintWidget) {
        this.K0.remove(constraintWidget);
        constraintWidget.Q();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void a(v5 v5Var) {
        super.a(v5Var);
        int size = this.K0.size();
        for (int i = 0; i < size; i++) {
            this.K0.get(i).a(v5Var);
        }
    }
}
