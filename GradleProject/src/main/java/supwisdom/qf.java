package supwisdom;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class qf extends RecyclerView.l {
    public boolean g = true;

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.b0 b0Var) {
        return !this.g || b0Var.isInvalid();
    }

    public abstract boolean a(RecyclerView.b0 b0Var, int i, int i2, int i3, int i4);

    public abstract boolean a(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2, int i, int i2, int i3, int i4);

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.b0 b0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i = cVar.f1367a;
        int i2 = cVar.b;
        View view = b0Var.itemView;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1367a;
        int top = cVar2 == null ? view.getTop() : cVar2.b;
        if (b0Var.isRemoved() || (i == left && i2 == top)) {
            return g(b0Var);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return a(b0Var, i, i2, left, top);
    }

    public void c(RecyclerView.b0 b0Var, boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.b0 b0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        if (cVar.f1367a != cVar2.f1367a || cVar.b != cVar2.b) {
            return a(b0Var, cVar.f1367a, cVar.b, cVar2.f1367a, cVar2.b);
        }
        j(b0Var);
        return false;
    }

    public void d(RecyclerView.b0 b0Var, boolean z) {
    }

    public abstract boolean f(RecyclerView.b0 b0Var);

    public abstract boolean g(RecyclerView.b0 b0Var);

    public final void h(RecyclerView.b0 b0Var) {
        n(b0Var);
        b(b0Var);
    }

    public final void i(RecyclerView.b0 b0Var) {
        o(b0Var);
    }

    public final void j(RecyclerView.b0 b0Var) {
        p(b0Var);
        b(b0Var);
    }

    public final void k(RecyclerView.b0 b0Var) {
        q(b0Var);
    }

    public final void l(RecyclerView.b0 b0Var) {
        r(b0Var);
        b(b0Var);
    }

    public final void m(RecyclerView.b0 b0Var) {
        s(b0Var);
    }

    public void n(RecyclerView.b0 b0Var) {
    }

    public void o(RecyclerView.b0 b0Var) {
    }

    public void p(RecyclerView.b0 b0Var) {
    }

    public void q(RecyclerView.b0 b0Var) {
    }

    public void r(RecyclerView.b0 b0Var) {
    }

    public void s(RecyclerView.b0 b0Var) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.b0 b0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        return (cVar == null || (cVar.f1367a == cVar2.f1367a && cVar.b == cVar2.b)) ? f(b0Var) : a(b0Var, cVar.f1367a, cVar.b, cVar2.f1367a, cVar2.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i;
        int i2;
        int i3 = cVar.f1367a;
        int i4 = cVar.b;
        if (b0Var2.shouldIgnore()) {
            int i5 = cVar.f1367a;
            i2 = cVar.b;
            i = i5;
        } else {
            i = cVar2.f1367a;
            i2 = cVar2.b;
        }
        return a(b0Var, b0Var2, i3, i4, i, i2);
    }

    public final void b(RecyclerView.b0 b0Var, boolean z) {
        d(b0Var, z);
    }

    public final void a(RecyclerView.b0 b0Var, boolean z) {
        c(b0Var, z);
        b(b0Var);
    }
}
