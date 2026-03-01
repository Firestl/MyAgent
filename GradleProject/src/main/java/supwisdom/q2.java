package supwisdom;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class q2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f8864a;
    public n3 b;
    public n3 c;
    public n3 d;

    public q2(ImageView imageView) {
        this.f8864a = imageView;
    }

    public void a(AttributeSet attributeSet, int i) {
        int iG;
        p3 p3VarA = p3.a(this.f8864a.getContext(), attributeSet, R.styleable.AppCompatImageView, i, 0);
        ImageView imageView = this.f8864a;
        lb.a(imageView, imageView.getContext(), R.styleable.AppCompatImageView, attributeSet, p3VarA.a(), i, 0);
        try {
            Drawable drawable = this.f8864a.getDrawable();
            if (drawable == null && (iG = p3VarA.g(R.styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = b1.c(this.f8864a.getContext(), iG)) != null) {
                this.f8864a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                y2.b(drawable);
            }
            if (p3VarA.g(R.styleable.AppCompatImageView_tint)) {
                jc.a(this.f8864a, p3VarA.a(R.styleable.AppCompatImageView_tint));
            }
            if (p3VarA.g(R.styleable.AppCompatImageView_tintMode)) {
                jc.a(this.f8864a, y2.a(p3VarA.d(R.styleable.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            p3VarA.b();
        }
    }

    public ColorStateList b() {
        n3 n3Var = this.c;
        if (n3Var != null) {
            return n3Var.f8470a;
        }
        return null;
    }

    public PorterDuff.Mode c() {
        n3 n3Var = this.c;
        if (n3Var != null) {
            return n3Var.b;
        }
        return null;
    }

    public boolean d() {
        return Build.VERSION.SDK_INT < 21 || !(this.f8864a.getBackground() instanceof RippleDrawable);
    }

    public final boolean e() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.b != null : i == 21;
    }

    public void a(int i) {
        if (i != 0) {
            Drawable drawableC = b1.c(this.f8864a.getContext(), i);
            if (drawableC != null) {
                y2.b(drawableC);
            }
            this.f8864a.setImageDrawable(drawableC);
        } else {
            this.f8864a.setImageDrawable(null);
        }
        a();
    }

    public void a(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new n3();
        }
        n3 n3Var = this.c;
        n3Var.f8470a = colorStateList;
        n3Var.d = true;
        a();
    }

    public void a(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new n3();
        }
        n3 n3Var = this.c;
        n3Var.b = mode;
        n3Var.c = true;
        a();
    }

    public void a() {
        Drawable drawable = this.f8864a.getDrawable();
        if (drawable != null) {
            y2.b(drawable);
        }
        if (drawable != null) {
            if (e() && a(drawable)) {
                return;
            }
            n3 n3Var = this.c;
            if (n3Var != null) {
                o2.a(drawable, n3Var, this.f8864a.getDrawableState());
                return;
            }
            n3 n3Var2 = this.b;
            if (n3Var2 != null) {
                o2.a(drawable, n3Var2, this.f8864a.getDrawableState());
            }
        }
    }

    public final boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new n3();
        }
        n3 n3Var = this.d;
        n3Var.a();
        ColorStateList colorStateListA = jc.a(this.f8864a);
        if (colorStateListA != null) {
            n3Var.d = true;
            n3Var.f8470a = colorStateListA;
        }
        PorterDuff.Mode modeB = jc.b(this.f8864a);
        if (modeB != null) {
            n3Var.c = true;
            n3Var.b = modeB;
        }
        if (!n3Var.d && !n3Var.c) {
            return false;
        }
        o2.a(drawable, n3Var, this.f8864a.getDrawableState());
        return true;
    }
}
