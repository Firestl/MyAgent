package supwisdom;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import supwisdom.k8;

/* JADX INFO: compiled from: ChipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class vl0 extends Drawable implements z8, Drawable.Callback {
    public static final int[] h0 = {R.attr.state_enabled};
    public float A;
    public float B;
    public float C;
    public float D;
    public float E;
    public float F;
    public final Context G;
    public final Paint J;
    public int N;
    public int O;
    public int P;
    public int Q;
    public boolean R;
    public int S;
    public ColorFilter U;
    public PorterDuffColorFilter V;
    public ColorStateList W;
    public int[] Y;
    public boolean Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ColorStateList f9519a;
    public ColorStateList a0;
    public float b;
    public float c;
    public ColorStateList d;
    public float d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9520e;
    public TextUtils.TruncateAt e0;
    public ColorStateList f;
    public boolean f0;
    public int g0;
    public CharSequence h;
    public tm0 i;
    public boolean k;
    public Drawable l;
    public ColorStateList m;
    public float n;
    public boolean o;
    public Drawable p;
    public ColorStateList q;
    public float r;
    public CharSequence s;
    public boolean t;
    public boolean u;
    public Drawable v;
    public jl0 w;
    public jl0 x;
    public float y;
    public float z;
    public final k8.c j = new a();
    public final TextPaint H = new TextPaint(1);
    public final Paint I = new Paint(1);
    public final Paint.FontMetrics K = new Paint.FontMetrics();
    public final RectF L = new RectF();
    public final PointF M = new PointF();
    public int T = 255;
    public PorterDuff.Mode X = PorterDuff.Mode.SRC_IN;
    public WeakReference<b> b0 = new WeakReference<>(null);
    public boolean c0 = true;
    public CharSequence g = "";

    /* JADX INFO: compiled from: ChipDrawable.java */
    public class a extends k8.c {
        public a() {
        }

        @Override // supwisdom.k8.c
        public void a(int i) {
        }

        @Override // supwisdom.k8.c
        public void a(Typeface typeface) {
            vl0.this.c0 = true;
            vl0.this.N();
            vl0.this.invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface b {
        void a();
    }

    public vl0(Context context) {
        Paint paint = null;
        this.G = context;
        this.H.density = context.getResources().getDisplayMetrics().density;
        this.J = null;
        if (0 != 0) {
            paint.setStyle(Paint.Style.STROKE);
        }
        setState(h0);
        a(h0);
        this.f0 = true;
    }

    public ColorStateList A() {
        return this.f;
    }

    public jl0 B() {
        return this.w;
    }

    public CharSequence C() {
        return this.g;
    }

    public tm0 D() {
        return this.i;
    }

    public float E() {
        return this.C;
    }

    public float F() {
        return this.B;
    }

    public final float G() {
        if (!this.c0) {
            return this.d0;
        }
        float fA = a(this.h);
        this.d0 = fA;
        this.c0 = false;
        return fA;
    }

    public final ColorFilter H() {
        ColorFilter colorFilter = this.U;
        return colorFilter != null ? colorFilter : this.V;
    }

    public boolean I() {
        return this.t;
    }

    public boolean J() {
        return this.u;
    }

    public boolean K() {
        return this.k;
    }

    public boolean L() {
        return f(this.p);
    }

    public boolean M() {
        return this.o;
    }

    public void N() {
        b bVar = this.b0.get();
        if (bVar != null) {
            bVar.a();
        }
    }

    public boolean O() {
        return this.f0;
    }

    public final boolean P() {
        return this.u && this.v != null && this.R;
    }

    public final boolean Q() {
        return this.k && this.l != null;
    }

    public final boolean R() {
        return this.o && this.p != null;
    }

    public final void S() {
        this.a0 = this.Z ? vm0.a(this.f) : null;
    }

    public final float b() {
        if (R()) {
            return this.D + this.r + this.E;
        }
        return 0.0f;
    }

    public final void c(Canvas canvas, Rect rect) {
        if (Q()) {
            a(rect, this.L);
            RectF rectF = this.L;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.l.setBounds(0, 0, (int) this.L.width(), (int) this.L.height());
            this.l.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public final boolean d() {
        return this.u && this.v != null && this.t;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.T;
        int iA = i < 255 ? tl0.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        b(canvas, bounds);
        d(canvas, bounds);
        f(canvas, bounds);
        c(canvas, bounds);
        a(canvas, bounds);
        if (this.f0) {
            h(canvas, bounds);
        }
        e(canvas, bounds);
        g(canvas, bounds);
        if (this.T < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public final void e(Canvas canvas, Rect rect) {
        if (R()) {
            c(rect, this.L);
            RectF rectF = this.L;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.p.setBounds(0, 0, (int) this.L.width(), (int) this.L.height());
            this.p.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public void f(boolean z) {
        if (this.Z != z) {
            this.Z = z;
            S();
            onStateChange(getState());
        }
    }

    public final void g(Canvas canvas, Rect rect) {
        Paint paint = this.J;
        if (paint != null) {
            paint.setColor(n8.c(-16777216, 127));
            canvas.drawRect(rect, this.J);
            if (Q() || P()) {
                a(rect, this.L);
                canvas.drawRect(this.L, this.J);
            }
            if (this.h != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.J);
            }
            if (R()) {
                c(rect, this.L);
                canvas.drawRect(this.L, this.J);
            }
            this.J.setColor(n8.c(-65536, 127));
            b(rect, this.L);
            canvas.drawRect(this.L, this.J);
            this.J.setColor(n8.c(-16711936, 127));
            d(rect, this.L);
            canvas.drawRect(this.L, this.J);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.T;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.U;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.y + a() + this.B + G() + this.C + b() + this.F), this.g0);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.c);
        } else {
            outline.setRoundRect(bounds, this.c);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    public final void h(Canvas canvas, Rect rect) {
        if (this.h != null) {
            Paint.Align alignA = a(rect, this.M);
            e(rect, this.L);
            if (this.i != null) {
                this.H.drawableState = getState();
                this.i.b(this.G, this.H, this.j);
            }
            this.H.setTextAlign(alignA);
            int iSave = 0;
            boolean z = Math.round(G()) > Math.round(this.L.width());
            if (z) {
                iSave = canvas.save();
                canvas.clipRect(this.L);
            }
            CharSequence charSequenceEllipsize = this.h;
            if (z && this.e0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.H, this.L.width(), this.e0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.M;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.H);
            if (z) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public Drawable i() {
        Drawable drawable = this.l;
        if (drawable != null) {
            return y8.h(drawable);
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return f(this.f9519a) || f(this.d) || (this.Z && f(this.a0)) || b(this.i) || d() || f(this.l) || f(this.v) || f(this.W);
    }

    public void j(int i) {
        c(this.G.getResources().getBoolean(i));
    }

    public void k(int i) {
        d(this.G.getResources().getDimension(i));
    }

    public float l() {
        return this.b;
    }

    public void m(int i) {
        c(b1.b(this.G, i));
    }

    public ColorStateList n() {
        return this.d;
    }

    public float o() {
        return this.f9520e;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(23)
    public boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (Q()) {
            zOnLayoutDirectionChanged |= this.l.setLayoutDirection(i);
        }
        if (P()) {
            zOnLayoutDirectionChanged |= this.v.setLayoutDirection(i);
        }
        if (R()) {
            zOnLayoutDirectionChanged |= this.p.setLayoutDirection(i);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        boolean zOnLevelChange = super.onLevelChange(i);
        if (Q()) {
            zOnLevelChange |= this.l.setLevel(i);
        }
        if (P()) {
            zOnLevelChange |= this.v.setLevel(i);
        }
        if (R()) {
            zOnLevelChange |= this.p.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        return a(iArr, u());
    }

    public Drawable p() {
        Drawable drawable = this.p;
        if (drawable != null) {
            return y8.h(drawable);
        }
        return null;
    }

    public void q(int i) {
        h(this.G.getResources().getDimension(i));
    }

    public void r(int i) {
        i(this.G.getResources().getDimension(i));
    }

    public void s(int i) {
        d(b1.b(this.G, i));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.T != i) {
            this.T = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.U != colorFilter) {
            this.U = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintList(ColorStateList colorStateList) {
        if (this.W != colorStateList) {
            this.W = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.X != mode) {
            this.X = mode;
            this.V = zl0.a(this, this.W, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (Q()) {
            visible |= this.l.setVisible(z, z2);
        }
        if (P()) {
            visible |= this.v.setVisible(z, z2);
        }
        if (R()) {
            visible |= this.p.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public void t(int i) {
        d(this.G.getResources().getBoolean(i));
    }

    public int[] u() {
        return this.Y;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public ColorStateList v() {
        return this.q;
    }

    public TextUtils.TruncateAt w() {
        return this.e0;
    }

    public jl0 x() {
        return this.x;
    }

    public void y(int i) {
        e(b1.b(this.G, i));
    }

    public void z(int i) {
        b(jl0.a(this.G, i));
    }

    public static vl0 a(Context context, AttributeSet attributeSet, int i, int i2) {
        vl0 vl0Var = new vl0(context);
        vl0Var.a(attributeSet, i, i2);
        return vl0Var;
    }

    public void A(int i) {
        a(new tm0(this.G, i));
    }

    public void B(int i) {
        l(this.G.getResources().getDimension(i));
    }

    public void C(int i) {
        m(this.G.getResources().getDimension(i));
    }

    public final void d(Canvas canvas, Rect rect) {
        if (this.f9520e > 0.0f) {
            this.I.setColor(this.O);
            this.I.setStyle(Paint.Style.STROKE);
            this.I.setColorFilter(H());
            RectF rectF = this.L;
            float f = rect.left;
            float f2 = this.f9520e;
            rectF.set(f + (f2 / 2.0f), rect.top + (f2 / 2.0f), rect.right - (f2 / 2.0f), rect.bottom - (f2 / 2.0f));
            float f3 = this.c - (this.f9520e / 2.0f);
            canvas.drawRoundRect(this.L, f3, f3, this.I);
        }
    }

    public void i(int i) {
        b(b1.b(this.G, i));
    }

    public float j() {
        return this.n;
    }

    public ColorStateList k() {
        return this.m;
    }

    public void l(int i) {
        e(this.G.getResources().getDimension(i));
    }

    public float m() {
        return this.y;
    }

    public void n(int i) {
        f(this.G.getResources().getDimension(i));
    }

    public void o(int i) {
        g(this.G.getResources().getDimension(i));
    }

    public void p(int i) {
        d(b1.c(this.G, i));
    }

    public CharSequence q() {
        return this.s;
    }

    public float r() {
        return this.E;
    }

    public float s() {
        return this.r;
    }

    public float t() {
        return this.D;
    }

    public void u(int i) {
        a(jl0.a(this.G, i));
    }

    public void v(int i) {
        j(this.G.getResources().getDimension(i));
    }

    public void w(int i) {
        k(this.G.getResources().getDimension(i));
    }

    public void x(int i) {
        this.g0 = i;
    }

    public float y() {
        return this.A;
    }

    public float z() {
        return this.z;
    }

    public final void b(Canvas canvas, Rect rect) {
        this.I.setColor(this.N);
        this.I.setStyle(Paint.Style.FILL);
        this.I.setColorFilter(H());
        this.L.set(rect);
        RectF rectF = this.L;
        float f = this.c;
        canvas.drawRoundRect(rectF, f, f, this.I);
    }

    public void i(float f) {
        if (this.D != f) {
            this.D = f;
            invalidateSelf();
            if (R()) {
                N();
            }
        }
    }

    public void j(float f) {
        if (this.A != f) {
            float fA = a();
            this.A = f;
            float fA2 = a();
            invalidateSelf();
            if (fA != fA2) {
                N();
            }
        }
    }

    public void k(float f) {
        if (this.z != f) {
            float fA = a();
            this.z = f;
            float fA2 = a();
            invalidateSelf();
            if (fA != fA2) {
                N();
            }
        }
    }

    public void l(float f) {
        if (this.C != f) {
            this.C = f;
            invalidateSelf();
            N();
        }
    }

    public void m(float f) {
        if (this.B != f) {
            this.B = f;
            invalidateSelf();
            N();
        }
    }

    public final void a(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayC = pm0.c(this.G, attributeSet, com.google.android.material.R.styleable.Chip, i, i2, new int[0]);
        a(sm0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_chipBackgroundColor));
        d(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_chipMinHeight, 0.0f));
        a(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_chipCornerRadius, 0.0f));
        c(sm0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_chipStrokeColor));
        f(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_chipStrokeWidth, 0.0f));
        e(sm0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_rippleColor));
        c(typedArrayC.getText(com.google.android.material.R.styleable.Chip_android_text));
        a(sm0.c(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_android_textAppearance));
        int i3 = typedArrayC.getInt(com.google.android.material.R.styleable.Chip_android_ellipsize, 0);
        if (i3 == 1) {
            a(TextUtils.TruncateAt.START);
        } else if (i3 == 2) {
            a(TextUtils.TruncateAt.MIDDLE);
        } else if (i3 == 3) {
            a(TextUtils.TruncateAt.END);
        }
        c(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            c(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_chipIconEnabled, false));
        }
        c(sm0.b(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_chipIcon));
        b(sm0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_chipIconTint));
        c(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_chipIconSize, 0.0f));
        d(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            d(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_closeIconEnabled, false));
        }
        d(sm0.b(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_closeIcon));
        d(sm0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_closeIconTint));
        h(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_closeIconSize, 0.0f));
        a(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_android_checkable, false));
        b(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            b(typedArrayC.getBoolean(com.google.android.material.R.styleable.Chip_checkedIconEnabled, false));
        }
        b(sm0.b(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_checkedIcon));
        b(jl0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_showMotionSpec));
        a(jl0.a(this.G, typedArrayC, com.google.android.material.R.styleable.Chip_hideMotionSpec));
        e(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_chipStartPadding, 0.0f));
        k(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_iconStartPadding, 0.0f));
        j(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_iconEndPadding, 0.0f));
        m(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_textStartPadding, 0.0f));
        l(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_textEndPadding, 0.0f));
        i(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_closeIconStartPadding, 0.0f));
        g(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_closeIconEndPadding, 0.0f));
        b(typedArrayC.getDimension(com.google.android.material.R.styleable.Chip_chipEndPadding, 0.0f));
        x(typedArrayC.getDimensionPixelSize(com.google.android.material.R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        typedArrayC.recycle();
    }

    public final void f(Canvas canvas, Rect rect) {
        this.I.setColor(this.P);
        this.I.setStyle(Paint.Style.FILL);
        this.L.set(rect);
        RectF rectF = this.L;
        float f = this.c;
        canvas.drawRoundRect(rectF, f, f, this.I);
    }

    public final void b(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (R()) {
            float f = this.F + this.E + this.r + this.D + this.C;
            if (y8.e(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    public static boolean f(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public final float c() {
        this.H.getFontMetrics(this.K);
        Paint.FontMetrics fontMetrics = this.K;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final void d(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (R()) {
            float f = this.F + this.E + this.r + this.D + this.C;
            if (y8.e(this) == 0) {
                float f2 = rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public final void e(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.h != null) {
            float fA = this.y + a() + this.B;
            float fB = this.F + b() + this.C;
            if (y8.e(this) == 0) {
                rectF.left = rect.left + fA;
                rectF.right = rect.right - fB;
            } else {
                rectF.left = rect.left + fB;
                rectF.right = rect.right - fA;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public static boolean f(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public final void c(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (R()) {
            float f = this.F + this.E;
            if (y8.e(this) == 0) {
                float f2 = rect.right - f;
                rectF.right = f2;
                rectF.left = f2 - this.r;
            } else {
                float f3 = rect.left + f;
                rectF.left = f3;
                rectF.right = f3 + this.r;
            }
            float fExactCenterY = rect.exactCenterY();
            float f4 = this.r;
            float f5 = fExactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    public ColorStateList f() {
        return this.f9519a;
    }

    public void f(float f) {
        if (this.f9520e != f) {
            this.f9520e = f;
            this.I.setStrokeWidth(f);
            invalidateSelf();
        }
    }

    public static boolean b(tm0 tm0Var) {
        ColorStateList colorStateList;
        return (tm0Var == null || (colorStateList = tm0Var.b) == null || !colorStateList.isStateful()) ? false : true;
    }

    public void b(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            if (Q()) {
                y8.a(this.l, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void f(int i) {
        b(this.G.getResources().getDimension(i));
    }

    public void h(int i) {
        c(this.G.getResources().getDimension(i));
    }

    public void h(float f) {
        if (this.r != f) {
            this.r = f;
            invalidateSelf();
            if (R()) {
                N();
            }
        }
    }

    public void d(int i) {
        a(b1.b(this.G, i));
    }

    public void d(float f) {
        if (this.b != f) {
            this.b = f;
            invalidateSelf();
            N();
        }
    }

    public final void e(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public float g() {
        return this.c;
    }

    public void b(CharSequence charSequence) {
        if (this.s != charSequence) {
            this.s = z9.b().a(charSequence);
            invalidateSelf();
        }
    }

    public void c(ColorStateList colorStateList) {
        if (this.d != colorStateList) {
            this.d = colorStateList;
            onStateChange(getState());
        }
    }

    public void e(int i) {
        a(this.G.getResources().getDimension(i));
    }

    public void g(int i) {
        c(b1.c(this.G, i));
    }

    public void e(ColorStateList colorStateList) {
        if (this.f != colorStateList) {
            this.f = colorStateList;
            S();
            onStateChange(getState());
        }
    }

    public void g(float f) {
        if (this.E != f) {
            this.E = f;
            invalidateSelf();
            if (R()) {
                N();
            }
        }
    }

    public float h() {
        return this.F;
    }

    public void b(boolean z) {
        if (this.u != z) {
            boolean zP = P();
            this.u = z;
            boolean zP2 = P();
            if (zP != zP2) {
                if (zP2) {
                    a(this.v);
                } else {
                    e(this.v);
                }
                invalidateSelf();
                N();
            }
        }
    }

    public void c(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (this.g != charSequence) {
            this.g = charSequence;
            this.h = z9.b().a(charSequence);
            this.c0 = true;
            invalidateSelf();
            N();
        }
    }

    public void d(boolean z) {
        if (this.o != z) {
            boolean zR = R();
            this.o = z;
            boolean zR2 = R();
            if (zR != zR2) {
                if (zR2) {
                    a(this.p);
                } else {
                    e(this.p);
                }
                invalidateSelf();
                N();
            }
        }
    }

    public Drawable e() {
        return this.v;
    }

    public void e(float f) {
        if (this.y != f) {
            this.y = f;
            invalidateSelf();
            N();
        }
    }

    public void c(boolean z) {
        if (this.k != z) {
            boolean zQ = Q();
            this.k = z;
            boolean zQ2 = Q();
            if (zQ != zQ2) {
                if (zQ2) {
                    a(this.l);
                } else {
                    e(this.l);
                }
                invalidateSelf();
                N();
            }
        }
    }

    public void e(boolean z) {
        this.f0 = z;
    }

    public void b(int i) {
        b(b1.c(this.G, i));
    }

    public void d(Drawable drawable) {
        Drawable drawableP = p();
        if (drawableP != drawable) {
            float fB = b();
            this.p = drawable != null ? y8.i(drawable).mutate() : null;
            float fB2 = b();
            e(drawableP);
            if (R()) {
                a(this.p);
            }
            invalidateSelf();
            if (fB != fB2) {
                N();
            }
        }
    }

    public void b(Drawable drawable) {
        if (this.v != drawable) {
            float fA = a();
            this.v = drawable;
            float fA2 = a();
            e(this.v);
            a(this.v);
            invalidateSelf();
            if (fA != fA2) {
                N();
            }
        }
    }

    public void c(Drawable drawable) {
        Drawable drawableI = i();
        if (drawableI != drawable) {
            float fA = a();
            this.l = drawable != null ? y8.i(drawable).mutate() : null;
            float fA2 = a();
            e(drawableI);
            if (Q()) {
                a(this.l);
            }
            invalidateSelf();
            if (fA != fA2) {
                N();
            }
        }
    }

    public void b(jl0 jl0Var) {
        this.w = jl0Var;
    }

    public void d(ColorStateList colorStateList) {
        if (this.q != colorStateList) {
            this.q = colorStateList;
            if (R()) {
                y8.a(this.p, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void b(float f) {
        if (this.F != f) {
            this.F = f;
            invalidateSelf();
            N();
        }
    }

    public void c(float f) {
        if (this.n != f) {
            float fA = a();
            this.n = f;
            float fA2 = a();
            invalidateSelf();
            if (fA != fA2) {
                N();
            }
        }
    }

    public void c(int i) {
        b(this.G.getResources().getBoolean(i));
    }

    public void a(b bVar) {
        this.b0 = new WeakReference<>(bVar);
    }

    public void a(RectF rectF) {
        d(getBounds(), rectF);
    }

    public float a() {
        if (Q() || P()) {
            return this.z + this.n + this.A;
        }
        return 0.0f;
    }

    public final float a(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.H.measureText(charSequence, 0, charSequence.length());
    }

    public final void a(Canvas canvas, Rect rect) {
        if (P()) {
            a(rect, this.L);
            RectF rectF = this.L;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.v.setBounds(0, 0, (int) this.L.width(), (int) this.L.height());
            this.v.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public final void a(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (Q() || P()) {
            float f = this.y + this.z;
            if (y8.e(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + this.n;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - this.n;
            }
            float fExactCenterY = rect.exactCenterY();
            float f4 = this.n;
            float f5 = fExactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    public Paint.Align a(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.h != null) {
            float fA = this.y + a() + this.B;
            if (y8.e(this) == 0) {
                pointF.x = rect.left + fA;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - fA;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - c();
        }
        return align;
    }

    public boolean a(int[] iArr) {
        if (Arrays.equals(this.Y, iArr)) {
            return false;
        }
        this.Y = iArr;
        if (R()) {
            return a(getState(), iArr);
        }
        return false;
    }

    public final boolean a(int[] iArr, int[] iArr2) {
        boolean z;
        ColorStateList colorStateList;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.f9519a;
        int colorForState = colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.N) : 0;
        boolean state = true;
        if (this.N != colorForState) {
            this.N = colorForState;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.d;
        int colorForState2 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.O) : 0;
        if (this.O != colorForState2) {
            this.O = colorForState2;
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.a0;
        int colorForState3 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.P) : 0;
        if (this.P != colorForState3) {
            this.P = colorForState3;
            if (this.Z) {
                zOnStateChange = true;
            }
        }
        tm0 tm0Var = this.i;
        int colorForState4 = (tm0Var == null || (colorStateList = tm0Var.b) == null) ? 0 : colorStateList.getColorForState(iArr, this.Q);
        if (this.Q != colorForState4) {
            this.Q = colorForState4;
            zOnStateChange = true;
        }
        boolean z2 = a(getState(), R.attr.state_checked) && this.t;
        if (this.R == z2 || this.v == null) {
            z = false;
        } else {
            float fA = a();
            this.R = z2;
            if (fA != a()) {
                zOnStateChange = true;
                z = true;
            } else {
                zOnStateChange = true;
                z = false;
            }
        }
        ColorStateList colorStateList5 = this.W;
        int colorForState5 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.S) : 0;
        if (this.S != colorForState5) {
            this.S = colorForState5;
            this.V = zl0.a(this, this.W, this.X);
        } else {
            state = zOnStateChange;
        }
        if (f(this.l)) {
            state |= this.l.setState(iArr);
        }
        if (f(this.v)) {
            state |= this.v.setState(iArr);
        }
        if (f(this.p)) {
            state |= this.p.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z) {
            N();
        }
        return state;
    }

    public final void a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            y8.a(drawable, y8.e(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.p) {
                if (drawable.isStateful()) {
                    drawable.setState(u());
                }
                y8.a(drawable, this.q);
            } else if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    public static boolean a(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public void a(ColorStateList colorStateList) {
        if (this.f9519a != colorStateList) {
            this.f9519a = colorStateList;
            onStateChange(getState());
        }
    }

    public void a(float f) {
        if (this.c != f) {
            this.c = f;
            invalidateSelf();
        }
    }

    public void a(tm0 tm0Var) {
        if (this.i != tm0Var) {
            this.i = tm0Var;
            if (tm0Var != null) {
                tm0Var.c(this.G, this.H, this.j);
                this.c0 = true;
            }
            onStateChange(getState());
            N();
        }
    }

    public void a(TextUtils.TruncateAt truncateAt) {
        this.e0 = truncateAt;
    }

    public void a(int i) {
        a(this.G.getResources().getBoolean(i));
    }

    public void a(boolean z) {
        if (this.t != z) {
            this.t = z;
            float fA = a();
            if (!z && this.R) {
                this.R = false;
            }
            float fA2 = a();
            invalidateSelf();
            if (fA != fA2) {
                N();
            }
        }
    }

    public void a(jl0 jl0Var) {
        this.x = jl0Var;
    }
}
