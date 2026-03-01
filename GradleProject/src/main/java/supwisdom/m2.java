package supwisdom;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f8347a;
    public n3 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public n3 f8348e;
    public n3 f;
    public int c = -1;
    public final o2 b = o2.b();

    public m2(View view) {
        this.f8347a = view;
    }

    public void a(AttributeSet attributeSet, int i) {
        p3 p3VarA = p3.a(this.f8347a.getContext(), attributeSet, R.styleable.ViewBackgroundHelper, i, 0);
        View view = this.f8347a;
        lb.a(view, view.getContext(), R.styleable.ViewBackgroundHelper, attributeSet, p3VarA.a(), i, 0);
        try {
            if (p3VarA.g(R.styleable.ViewBackgroundHelper_android_background)) {
                this.c = p3VarA.g(R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList colorStateListB = this.b.b(this.f8347a.getContext(), this.c);
                if (colorStateListB != null) {
                    a(colorStateListB);
                }
            }
            if (p3VarA.g(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                lb.a(this.f8347a, p3VarA.a(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (p3VarA.g(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                lb.a(this.f8347a, y2.a(p3VarA.d(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            p3VarA.b();
        }
    }

    public void b(Drawable drawable) {
        this.c = -1;
        a((ColorStateList) null);
        a();
    }

    public PorterDuff.Mode c() {
        n3 n3Var = this.f8348e;
        if (n3Var != null) {
            return n3Var.b;
        }
        return null;
    }

    public final boolean d() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.d != null : i == 21;
    }

    public void b(ColorStateList colorStateList) {
        if (this.f8348e == null) {
            this.f8348e = new n3();
        }
        n3 n3Var = this.f8348e;
        n3Var.f8470a = colorStateList;
        n3Var.d = true;
        a();
    }

    public ColorStateList b() {
        n3 n3Var = this.f8348e;
        if (n3Var != null) {
            return n3Var.f8470a;
        }
        return null;
    }

    public void a(int i) {
        this.c = i;
        o2 o2Var = this.b;
        a(o2Var != null ? o2Var.b(this.f8347a.getContext(), i) : null);
        a();
    }

    public void a(PorterDuff.Mode mode) {
        if (this.f8348e == null) {
            this.f8348e = new n3();
        }
        n3 n3Var = this.f8348e;
        n3Var.b = mode;
        n3Var.c = true;
        a();
    }

    public void a() {
        Drawable background = this.f8347a.getBackground();
        if (background != null) {
            if (d() && a(background)) {
                return;
            }
            n3 n3Var = this.f8348e;
            if (n3Var != null) {
                o2.a(background, n3Var, this.f8347a.getDrawableState());
                return;
            }
            n3 n3Var2 = this.d;
            if (n3Var2 != null) {
                o2.a(background, n3Var2, this.f8347a.getDrawableState());
            }
        }
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new n3();
            }
            n3 n3Var = this.d;
            n3Var.f8470a = colorStateList;
            n3Var.d = true;
        } else {
            this.d = null;
        }
        a();
    }

    public final boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new n3();
        }
        n3 n3Var = this.f;
        n3Var.a();
        ColorStateList colorStateListF = lb.f(this.f8347a);
        if (colorStateListF != null) {
            n3Var.d = true;
            n3Var.f8470a = colorStateListF;
        }
        PorterDuff.Mode modeG = lb.g(this.f8347a);
        if (modeG != null) {
            n3Var.c = true;
            n3Var.b = modeG;
        }
        if (!n3Var.d && !n3Var.c) {
            return false;
        }
        o2.a(drawable, n3Var, this.f8347a.getDrawableState());
        return true;
    }
}
