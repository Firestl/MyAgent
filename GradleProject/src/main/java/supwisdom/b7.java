package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: WidgetGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class b7 {
    public static int f;
    public int b;
    public int c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<ConstraintWidget> f7031a = new ArrayList<>();
    public ArrayList<a> d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7032e = -1;

    /* JADX INFO: compiled from: WidgetGroup.java */
    public class a {
        public a(b7 b7Var, ConstraintWidget constraintWidget, w5 w5Var, int i) {
            new WeakReference(constraintWidget);
            w5Var.b(constraintWidget.H);
            w5Var.b(constraintWidget.I);
            w5Var.b(constraintWidget.J);
            w5Var.b(constraintWidget.K);
            w5Var.b(constraintWidget.L);
        }
    }

    public b7(int i) {
        this.b = -1;
        this.c = 0;
        int i2 = f;
        f = i2 + 1;
        this.b = i2;
        this.c = i;
    }

    public int a() {
        return this.b;
    }

    public void a(boolean z) {
    }

    public int b() {
        return this.c;
    }

    public final String c() {
        int i = this.c;
        return i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown";
    }

    public String toString() {
        String str = c() + " [" + this.b + "] <";
        Iterator<ConstraintWidget> it = this.f7031a.iterator();
        while (it.hasNext()) {
            str = str + Operators.SPACE_STR + it.next().i();
        }
        return str + " >";
    }

    public boolean a(ConstraintWidget constraintWidget) {
        if (this.f7031a.contains(constraintWidget)) {
            return false;
        }
        this.f7031a.add(constraintWidget);
        return true;
    }

    public void a(int i, b7 b7Var) {
        for (ConstraintWidget constraintWidget : this.f7031a) {
            b7Var.a(constraintWidget);
            if (i == 0) {
                constraintWidget.H0 = b7Var.a();
            } else {
                constraintWidget.I0 = b7Var.a();
            }
        }
        this.f7032e = b7Var.b;
    }

    public int a(w5 w5Var, int i) {
        if (this.f7031a.size() == 0) {
            return 0;
        }
        return a(w5Var, this.f7031a, i);
    }

    public final int a(w5 w5Var, ArrayList<ConstraintWidget> arrayList, int i) {
        int iB;
        int iB2;
        f6 f6Var = (f6) arrayList.get(0).w();
        w5Var.i();
        f6Var.a(w5Var, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).a(w5Var, false);
        }
        if (i == 0 && f6Var.T0 > 0) {
            d6.a(f6Var, w5Var, arrayList, 0);
        }
        if (i == 1 && f6Var.U0 > 0) {
            d6.a(f6Var, w5Var, arrayList, 1);
        }
        try {
            w5Var.g();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.d = new ArrayList<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.d.add(new a(this, arrayList.get(i3), w5Var, i));
        }
        if (i == 0) {
            iB = w5Var.b(f6Var.H);
            iB2 = w5Var.b(f6Var.J);
            w5Var.i();
        } else {
            iB = w5Var.b(f6Var.I);
            iB2 = w5Var.b(f6Var.K);
            w5Var.i();
        }
        return iB2 - iB;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(ArrayList<b7> arrayList) {
        int size = this.f7031a.size();
        if (this.f7032e != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                b7 b7Var = arrayList.get(i);
                if (this.f7032e == b7Var.b) {
                    a(this.c, b7Var);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }
}
