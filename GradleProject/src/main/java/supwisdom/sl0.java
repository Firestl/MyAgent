package supwisdom;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class sl0 {
    public static final boolean w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaterialButton f9188a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9189e;
    public int f;
    public int g;
    public PorterDuff.Mode h;
    public ColorStateList i;
    public ColorStateList j;
    public ColorStateList k;
    public GradientDrawable o;
    public Drawable p;
    public GradientDrawable q;
    public Drawable r;
    public GradientDrawable s;
    public GradientDrawable t;
    public GradientDrawable u;
    public final Paint l = new Paint(1);
    public final Rect m = new Rect();
    public final RectF n = new RectF();
    public boolean v = false;

    static {
        w = Build.VERSION.SDK_INT >= 21;
    }

    public sl0(MaterialButton materialButton) {
        this.f9188a = materialButton;
    }

    public void a(TypedArray typedArray) {
        this.b = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.c = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.d = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.f9189e = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        this.f = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, 0);
        this.g = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.h = qm0.a(typedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.i = sm0.a(this.f9188a.getContext(), typedArray, R.styleable.MaterialButton_backgroundTint);
        this.j = sm0.a(this.f9188a.getContext(), typedArray, R.styleable.MaterialButton_strokeColor);
        this.k = sm0.a(this.f9188a.getContext(), typedArray, R.styleable.MaterialButton_rippleColor);
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(this.g);
        Paint paint = this.l;
        ColorStateList colorStateList = this.j;
        paint.setColor(colorStateList != null ? colorStateList.getColorForState(this.f9188a.getDrawableState(), 0) : 0);
        int iR = lb.r(this.f9188a);
        int paddingTop = this.f9188a.getPaddingTop();
        int iQ = lb.q(this.f9188a);
        int paddingBottom = this.f9188a.getPaddingBottom();
        this.f9188a.setInternalBackground(w ? b() : a());
        lb.b(this.f9188a, iR + this.b, paddingTop + this.d, iQ + this.c, paddingBottom + this.f9189e);
    }

    @TargetApi(21)
    public final Drawable b() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.s = gradientDrawable;
        gradientDrawable.setCornerRadius(this.f + 1.0E-5f);
        this.s.setColor(-1);
        n();
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.t = gradientDrawable2;
        gradientDrawable2.setCornerRadius(this.f + 1.0E-5f);
        this.t.setColor(0);
        this.t.setStroke(this.g, this.j);
        InsetDrawable insetDrawableA = a(new LayerDrawable(new Drawable[]{this.s, this.t}));
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        this.u = gradientDrawable3;
        gradientDrawable3.setCornerRadius(this.f + 1.0E-5f);
        this.u.setColor(-1);
        return new rl0(vm0.a(this.k), insetDrawableA, this.u);
    }

    public void c(ColorStateList colorStateList) {
        if (this.i != colorStateList) {
            this.i = colorStateList;
            if (w) {
                n();
                return;
            }
            Drawable drawable = this.p;
            if (drawable != null) {
                y8.a(drawable, colorStateList);
            }
        }
    }

    public ColorStateList d() {
        return this.k;
    }

    public ColorStateList e() {
        return this.j;
    }

    public int f() {
        return this.g;
    }

    public ColorStateList g() {
        return this.i;
    }

    public PorterDuff.Mode h() {
        return this.h;
    }

    public boolean i() {
        return this.v;
    }

    public void j() {
        this.v = true;
        this.f9188a.setSupportBackgroundTintList(this.i);
        this.f9188a.setSupportBackgroundTintMode(this.h);
    }

    public final GradientDrawable k() {
        if (!w || this.f9188a.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.f9188a.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }

    public final GradientDrawable l() {
        if (!w || this.f9188a.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.f9188a.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }

    public final void m() {
        if (w && this.t != null) {
            this.f9188a.setInternalBackground(b());
        } else {
            if (w) {
                return;
            }
            this.f9188a.invalidate();
        }
    }

    public final void n() {
        GradientDrawable gradientDrawable = this.s;
        if (gradientDrawable != null) {
            y8.a(gradientDrawable, this.i);
            PorterDuff.Mode mode = this.h;
            if (mode != null) {
                y8.a(this.s, mode);
            }
        }
    }

    public void c(int i) {
        if (this.g != i) {
            this.g = i;
            this.l.setStrokeWidth(i);
            m();
        }
    }

    public int c() {
        return this.f;
    }

    public void b(ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            this.l.setColor(colorStateList != null ? colorStateList.getColorForState(this.f9188a.getDrawableState(), 0) : 0);
            m();
        }
    }

    public void b(int i) {
        GradientDrawable gradientDrawable;
        if (this.f != i) {
            this.f = i;
            if (w && this.s != null && this.t != null && this.u != null) {
                if (Build.VERSION.SDK_INT == 21) {
                    float f = i + 1.0E-5f;
                    k().setCornerRadius(f);
                    l().setCornerRadius(f);
                }
                float f2 = i + 1.0E-5f;
                this.s.setCornerRadius(f2);
                this.t.setCornerRadius(f2);
                this.u.setCornerRadius(f2);
                return;
            }
            if (w || (gradientDrawable = this.o) == null || this.q == null) {
                return;
            }
            float f3 = i + 1.0E-5f;
            gradientDrawable.setCornerRadius(f3);
            this.q.setCornerRadius(f3);
            this.f9188a.invalidate();
        }
    }

    public void a(Canvas canvas) {
        if (canvas == null || this.j == null || this.g <= 0) {
            return;
        }
        this.m.set(this.f9188a.getBackground().getBounds());
        RectF rectF = this.n;
        float f = this.m.left;
        int i = this.g;
        rectF.set(f + (i / 2.0f) + this.b, r1.top + (i / 2.0f) + this.d, (r1.right - (i / 2.0f)) - this.c, (r1.bottom - (i / 2.0f)) - this.f9189e);
        float f2 = this.f - (this.g / 2.0f);
        canvas.drawRoundRect(this.n, f2, f2, this.l);
    }

    public final Drawable a() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.o = gradientDrawable;
        gradientDrawable.setCornerRadius(this.f + 1.0E-5f);
        this.o.setColor(-1);
        Drawable drawableI = y8.i(this.o);
        this.p = drawableI;
        y8.a(drawableI, this.i);
        PorterDuff.Mode mode = this.h;
        if (mode != null) {
            y8.a(this.p, mode);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.q = gradientDrawable2;
        gradientDrawable2.setCornerRadius(this.f + 1.0E-5f);
        this.q.setColor(-1);
        Drawable drawableI2 = y8.i(this.q);
        this.r = drawableI2;
        y8.a(drawableI2, this.k);
        return a(new LayerDrawable(new Drawable[]{this.p, this.r}));
    }

    public final InsetDrawable a(Drawable drawable) {
        return new InsetDrawable(drawable, this.b, this.d, this.c, this.f9189e);
    }

    public void a(PorterDuff.Mode mode) {
        if (this.h != mode) {
            this.h = mode;
            if (w) {
                n();
                return;
            }
            Drawable drawable = this.p;
            if (drawable == null || mode == null) {
                return;
            }
            y8.a(drawable, mode);
        }
    }

    public void a(int i, int i2) {
        GradientDrawable gradientDrawable = this.u;
        if (gradientDrawable != null) {
            gradientDrawable.setBounds(this.b, this.d, i2 - this.c, i - this.f9189e);
        }
    }

    public void a(int i) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (w && (gradientDrawable2 = this.s) != null) {
            gradientDrawable2.setColor(i);
        } else {
            if (w || (gradientDrawable = this.o) == null) {
                return;
            }
            gradientDrawable.setColor(i);
        }
    }

    public void a(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.k != colorStateList) {
            this.k = colorStateList;
            if (w && (this.f9188a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f9188a.getBackground()).setColor(colorStateList);
            } else {
                if (w || (drawable = this.r) == null) {
                    return;
                }
                y8.a(drawable, colorStateList);
            }
        }
    }
}
