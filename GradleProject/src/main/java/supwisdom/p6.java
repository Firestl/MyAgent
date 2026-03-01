package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public class p6 extends c7 {
    public ArrayList<c7> k;
    public int l;

    public p6(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.k = new ArrayList<>();
        this.f = i;
        g();
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x01c8 A[PHI: r7 r19 r20 r21
  0x01c8: PHI (r7v61 int) = (r7v59 int), (r7v67 int) binds: [B:116:0x01c6, B:107:0x019e] A[DONT_GENERATE, DONT_INLINE]
  0x01c8: PHI (r19v3 float) = (r19v2 float), (r19v5 float) binds: [B:116:0x01c6, B:107:0x019e] A[DONT_GENERATE, DONT_INLINE]
  0x01c8: PHI (r20v5 int) = (r20v4 int), (r20v7 int) binds: [B:116:0x01c6, B:107:0x019e] A[DONT_GENERATE, DONT_INLINE]
  0x01c8: PHI (r21v6 int) = (r21v5 int), (r21v8 int) binds: [B:116:0x01c6, B:107:0x019e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // supwisdom.c7, supwisdom.q6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(supwisdom.q6 r23) {
        /*
            Method dump skipped, instruction units count: 1067
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.p6.a(supwisdom.q6):void");
    }

    @Override // supwisdom.c7
    public void b() {
        for (int i = 0; i < this.k.size(); i++) {
            this.k.get(i).b();
        }
    }

    @Override // supwisdom.c7
    public void c() {
        this.c = null;
        Iterator<c7> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().c();
        }
    }

    @Override // supwisdom.c7
    public long d() {
        int size = this.k.size();
        long jD = 0;
        for (int i = 0; i < size; i++) {
            c7 c7Var = this.k.get(i);
            jD = jD + ((long) c7Var.h.f) + c7Var.d() + ((long) c7Var.i.f);
        }
        return jD;
    }

    @Override // supwisdom.c7
    public boolean f() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (!this.k.get(i).f()) {
                return false;
            }
        }
        return true;
    }

    public final void g() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.b;
        ConstraintWidget constraintWidgetE = constraintWidget2.e(this.f);
        while (true) {
            ConstraintWidget constraintWidget3 = constraintWidgetE;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                constraintWidgetE = constraintWidget2.e(this.f);
            }
        }
        this.b = constraintWidget;
        this.k.add(constraintWidget.f(this.f));
        ConstraintWidget constraintWidgetD = constraintWidget.d(this.f);
        while (constraintWidgetD != null) {
            this.k.add(constraintWidgetD.f(this.f));
            constraintWidgetD = constraintWidgetD.d(this.f);
        }
        for (c7 c7Var : this.k) {
            int i = this.f;
            if (i == 0) {
                c7Var.b.b = this;
            } else if (i == 1) {
                c7Var.b.c = this;
            }
        }
        if ((this.f == 0 && ((f6) this.b.w()).d0()) && this.k.size() > 1) {
            ArrayList<c7> arrayList = this.k;
            this.b = arrayList.get(arrayList.size() - 1).b;
        }
        this.l = this.f == 0 ? this.b.n() : this.b.z();
    }

    public final ConstraintWidget h() {
        for (int i = 0; i < this.k.size(); i++) {
            c7 c7Var = this.k.get(i);
            if (c7Var.b.C() != 8) {
                return c7Var.b;
            }
        }
        return null;
    }

    public final ConstraintWidget i() {
        for (int size = this.k.size() - 1; size >= 0; size--) {
            c7 c7Var = this.k.get(size);
            if (c7Var.b.C() != 8) {
                return c7Var.b;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.f == 0 ? "horizontal : " : "vertical : ");
        String string = sb.toString();
        for (c7 c7Var : this.k) {
            string = ((string + Operators.L) + c7Var) + "> ";
        }
        return string;
    }

    @Override // supwisdom.c7
    public void a() {
        Iterator<c7> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        int size = this.k.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.k.get(0).b;
        ConstraintWidget constraintWidget2 = this.k.get(size - 1).b;
        if (this.f == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.H;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.J;
            s6 s6VarA = a(constraintAnchor, 0);
            int iC = constraintAnchor.c();
            ConstraintWidget constraintWidgetH = h();
            if (constraintWidgetH != null) {
                iC = constraintWidgetH.H.c();
            }
            if (s6VarA != null) {
                a(this.h, s6VarA, iC);
            }
            s6 s6VarA2 = a(constraintAnchor2, 0);
            int iC2 = constraintAnchor2.c();
            ConstraintWidget constraintWidgetI = i();
            if (constraintWidgetI != null) {
                iC2 = constraintWidgetI.J.c();
            }
            if (s6VarA2 != null) {
                a(this.i, s6VarA2, -iC2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.I;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.K;
            s6 s6VarA3 = a(constraintAnchor3, 1);
            int iC3 = constraintAnchor3.c();
            ConstraintWidget constraintWidgetH2 = h();
            if (constraintWidgetH2 != null) {
                iC3 = constraintWidgetH2.I.c();
            }
            if (s6VarA3 != null) {
                a(this.h, s6VarA3, iC3);
            }
            s6 s6VarA4 = a(constraintAnchor4, 1);
            int iC4 = constraintAnchor4.c();
            ConstraintWidget constraintWidgetI2 = i();
            if (constraintWidgetI2 != null) {
                iC4 = constraintWidgetI2.K.c();
            }
            if (s6VarA4 != null) {
                a(this.i, s6VarA4, -iC4);
            }
        }
        this.h.f9126a = this;
        this.i.f9126a = this;
    }
}
