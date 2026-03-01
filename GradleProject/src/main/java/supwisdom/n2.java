package supwisdom;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R;

/* JADX INFO: compiled from: AppCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class n2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f8468a;
    public ColorStateList b = null;
    public PorterDuff.Mode c = null;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8469e = false;
    public boolean f;

    public n2(CompoundButton compoundButton) {
        this.f8468a = compoundButton;
    }

    public void a(AttributeSet attributeSet, int i) {
        boolean z;
        int iG;
        int iG2;
        p3 p3VarA = p3.a(this.f8468a.getContext(), attributeSet, R.styleable.CompoundButton, i, 0);
        CompoundButton compoundButton = this.f8468a;
        lb.a(compoundButton, compoundButton.getContext(), R.styleable.CompoundButton, attributeSet, p3VarA.a(), i, 0);
        try {
            if (!p3VarA.g(R.styleable.CompoundButton_buttonCompat) || (iG2 = p3VarA.g(R.styleable.CompoundButton_buttonCompat, 0)) == 0) {
                z = false;
            } else {
                try {
                    this.f8468a.setButtonDrawable(b1.c(this.f8468a.getContext(), iG2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z && p3VarA.g(R.styleable.CompoundButton_android_button) && (iG = p3VarA.g(R.styleable.CompoundButton_android_button, 0)) != 0) {
                this.f8468a.setButtonDrawable(b1.c(this.f8468a.getContext(), iG));
            }
            if (p3VarA.g(R.styleable.CompoundButton_buttonTint)) {
                hc.a(this.f8468a, p3VarA.a(R.styleable.CompoundButton_buttonTint));
            }
            if (p3VarA.g(R.styleable.CompoundButton_buttonTintMode)) {
                hc.a(this.f8468a, y2.a(p3VarA.d(R.styleable.CompoundButton_buttonTintMode, -1), null));
            }
        } finally {
            p3VarA.b();
        }
    }

    public ColorStateList b() {
        return this.b;
    }

    public PorterDuff.Mode c() {
        return this.c;
    }

    public void d() {
        if (this.f) {
            this.f = false;
        } else {
            this.f = true;
            a();
        }
    }

    public void a(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.d = true;
        a();
    }

    public void a(PorterDuff.Mode mode) {
        this.c = mode;
        this.f8469e = true;
        a();
    }

    public void a() {
        Drawable drawableA = hc.a(this.f8468a);
        if (drawableA != null) {
            if (this.d || this.f8469e) {
                Drawable drawableMutate = y8.i(drawableA).mutate();
                if (this.d) {
                    y8.a(drawableMutate, this.b);
                }
                if (this.f8469e) {
                    y8.a(drawableMutate, this.c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f8468a.getDrawableState());
                }
                this.f8468a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int a(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = hc.a(this.f8468a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }
}
