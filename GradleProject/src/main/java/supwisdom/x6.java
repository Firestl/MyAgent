package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Iterator;
import supwisdom.s6;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
public class x6 extends c7 {
    public x6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    public final void a(s6 s6Var) {
        this.h.k.add(s6Var);
        s6Var.l.add(this.h);
    }

    @Override // supwisdom.c7
    public void b() {
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget instanceof c6) {
            int iV = ((c6) constraintWidget).V();
            if (iV == 0 || iV == 1) {
                this.b.v(this.h.g);
            } else {
                this.b.w(this.h.g);
            }
        }
    }

    @Override // supwisdom.c7
    public void c() {
        this.c = null;
        this.h.a();
    }

    @Override // supwisdom.c7
    public boolean f() {
        return false;
    }

    @Override // supwisdom.c7
    public void a() {
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget instanceof c6) {
            this.h.b = true;
            c6 c6Var = (c6) constraintWidget;
            int iV = c6Var.V();
            boolean zU = c6Var.U();
            int i = 0;
            if (iV == 0) {
                this.h.f9127e = s6.a.LEFT;
                while (i < c6Var.L0) {
                    ConstraintWidget constraintWidget2 = c6Var.K0[i];
                    if (zU || constraintWidget2.C() != 8) {
                        s6 s6Var = constraintWidget2.d.h;
                        s6Var.k.add(this.h);
                        this.h.l.add(s6Var);
                    }
                    i++;
                }
                a(this.b.d.h);
                a(this.b.d.i);
                return;
            }
            if (iV == 1) {
                this.h.f9127e = s6.a.RIGHT;
                while (i < c6Var.L0) {
                    ConstraintWidget constraintWidget3 = c6Var.K0[i];
                    if (zU || constraintWidget3.C() != 8) {
                        s6 s6Var2 = constraintWidget3.d.i;
                        s6Var2.k.add(this.h);
                        this.h.l.add(s6Var2);
                    }
                    i++;
                }
                a(this.b.d.h);
                a(this.b.d.i);
                return;
            }
            if (iV == 2) {
                this.h.f9127e = s6.a.TOP;
                while (i < c6Var.L0) {
                    ConstraintWidget constraintWidget4 = c6Var.K0[i];
                    if (zU || constraintWidget4.C() != 8) {
                        s6 s6Var3 = constraintWidget4.f1228e.h;
                        s6Var3.k.add(this.h);
                        this.h.l.add(s6Var3);
                    }
                    i++;
                }
                a(this.b.f1228e.h);
                a(this.b.f1228e.i);
                return;
            }
            if (iV != 3) {
                return;
            }
            this.h.f9127e = s6.a.BOTTOM;
            while (i < c6Var.L0) {
                ConstraintWidget constraintWidget5 = c6Var.K0[i];
                if (zU || constraintWidget5.C() != 8) {
                    s6 s6Var4 = constraintWidget5.f1228e.i;
                    s6Var4.k.add(this.h);
                    this.h.l.add(s6Var4);
                }
                i++;
            }
            a(this.b.f1228e.h);
            a(this.b.f1228e.i);
        }
    }

    @Override // supwisdom.c7, supwisdom.q6
    public void a(q6 q6Var) {
        c6 c6Var = (c6) this.b;
        int iV = c6Var.V();
        Iterator<s6> it = this.h.l.iterator();
        int i = 0;
        int i2 = -1;
        while (it.hasNext()) {
            int i3 = it.next().g;
            if (i2 == -1 || i3 < i2) {
                i2 = i3;
            }
            if (i < i3) {
                i = i3;
            }
        }
        if (iV != 0 && iV != 2) {
            this.h.a(i + c6Var.W());
        } else {
            this.h.a(i2 + c6Var.W());
        }
    }
}
