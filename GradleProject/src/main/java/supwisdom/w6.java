package supwisdom;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public class w6 extends c7 {
    public w6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.d.c();
        constraintWidget.f1228e.c();
        this.f = ((h6) constraintWidget).U();
    }

    public final void a(s6 s6Var) {
        this.h.k.add(s6Var);
        s6Var.l.add(this.h);
    }

    @Override // supwisdom.c7
    public void b() {
        if (((h6) this.b).U() == 1) {
            this.b.v(this.h.g);
        } else {
            this.b.w(this.h.g);
        }
    }

    @Override // supwisdom.c7
    public void c() {
        this.h.a();
    }

    @Override // supwisdom.c7
    public boolean f() {
        return false;
    }

    @Override // supwisdom.c7, supwisdom.q6
    public void a(q6 q6Var) {
        s6 s6Var = this.h;
        if (s6Var.c && !s6Var.j) {
            this.h.a((int) ((s6Var.l.get(0).g * ((h6) this.b).X()) + 0.5f));
        }
    }

    @Override // supwisdom.c7
    public void a() {
        h6 h6Var = (h6) this.b;
        int iV = h6Var.V();
        int iW = h6Var.W();
        h6Var.X();
        if (h6Var.U() == 1) {
            if (iV != -1) {
                this.h.l.add(this.b.T.d.h);
                this.b.T.d.h.k.add(this.h);
                this.h.f = iV;
            } else if (iW != -1) {
                this.h.l.add(this.b.T.d.i);
                this.b.T.d.i.k.add(this.h);
                this.h.f = -iW;
            } else {
                s6 s6Var = this.h;
                s6Var.b = true;
                s6Var.l.add(this.b.T.d.i);
                this.b.T.d.i.k.add(this.h);
            }
            a(this.b.d.h);
            a(this.b.d.i);
            return;
        }
        if (iV != -1) {
            this.h.l.add(this.b.T.f1228e.h);
            this.b.T.f1228e.h.k.add(this.h);
            this.h.f = iV;
        } else if (iW != -1) {
            this.h.l.add(this.b.T.f1228e.i);
            this.b.T.f1228e.i.k.add(this.h);
            this.h.f = -iW;
        } else {
            s6 s6Var2 = this.h;
            s6Var2.b = true;
            s6Var2.l.add(this.b.T.f1228e.i);
            this.b.T.f1228e.i.k.add(this.h);
        }
        a(this.b.f1228e.h);
        a(this.b.f1228e.i);
    }
}
