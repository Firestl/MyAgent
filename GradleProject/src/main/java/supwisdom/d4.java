package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class d4 implements g4 {
    @Override // supwisdom.g4
    public void a() {
    }

    @Override // supwisdom.g4
    public void a(f4 f4Var, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        f4Var.a(new h4(colorStateList, f));
        View viewD = f4Var.d();
        viewD.setClipToOutline(true);
        viewD.setElevation(f2);
        c(f4Var, f3);
    }

    @Override // supwisdom.g4
    public float b(f4 f4Var) {
        return j(f4Var).c();
    }

    @Override // supwisdom.g4
    public void c(f4 f4Var, float f) {
        j(f4Var).a(f, f4Var.b(), f4Var.a());
        f(f4Var);
    }

    @Override // supwisdom.g4
    public float d(f4 f4Var) {
        return j(f4Var).b();
    }

    @Override // supwisdom.g4
    public ColorStateList e(f4 f4Var) {
        return j(f4Var).a();
    }

    @Override // supwisdom.g4
    public void f(f4 f4Var) {
        if (!f4Var.b()) {
            f4Var.a(0, 0, 0, 0);
            return;
        }
        float fD = d(f4Var);
        float fB = b(f4Var);
        int iCeil = (int) Math.ceil(i4.a(fD, fB, f4Var.a()));
        int iCeil2 = (int) Math.ceil(i4.b(fD, fB, f4Var.a()));
        f4Var.a(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // supwisdom.g4
    public float g(f4 f4Var) {
        return b(f4Var) * 2.0f;
    }

    @Override // supwisdom.g4
    public float h(f4 f4Var) {
        return b(f4Var) * 2.0f;
    }

    @Override // supwisdom.g4
    public void i(f4 f4Var) {
        c(f4Var, d(f4Var));
    }

    public final h4 j(f4 f4Var) {
        return (h4) f4Var.c();
    }

    @Override // supwisdom.g4
    public void b(f4 f4Var, float f) {
        f4Var.d().setElevation(f);
    }

    @Override // supwisdom.g4
    public void c(f4 f4Var) {
        c(f4Var, d(f4Var));
    }

    @Override // supwisdom.g4
    public void a(f4 f4Var, float f) {
        j(f4Var).a(f);
    }

    @Override // supwisdom.g4
    public float a(f4 f4Var) {
        return f4Var.d().getElevation();
    }

    @Override // supwisdom.g4
    public void a(f4 f4Var, ColorStateList colorStateList) {
        j(f4Var).b(colorStateList);
    }
}
