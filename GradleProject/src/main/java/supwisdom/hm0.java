package supwisdom;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.R;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class hm0 {
    public static final boolean T;
    public static final Paint U;
    public Paint A;
    public float B;
    public float C;
    public float D;
    public float E;
    public int[] F;
    public boolean G;
    public TimeInterpolator J;
    public TimeInterpolator K;
    public float L;
    public float M;
    public float N;
    public int O;
    public float P;
    public float Q;
    public float R;
    public int S;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7849a;
    public boolean b;
    public float c;
    public ColorStateList k;
    public ColorStateList l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public Typeface s;
    public Typeface t;
    public Typeface u;
    public CharSequence v;
    public CharSequence w;
    public boolean x;
    public boolean y;
    public Bitmap z;
    public int g = 16;
    public int h = 16;
    public float i = 15.0f;
    public float j = 15.0f;
    public final TextPaint H = new TextPaint(129);
    public final TextPaint I = new TextPaint(this.H);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f7850e = new Rect();
    public final Rect d = new Rect();
    public final RectF f = new RectF();

    static {
        T = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        U = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            U.setColor(-65281);
        }
    }

    public hm0(View view) {
        this.f7849a = view;
    }

    public void a(TimeInterpolator timeInterpolator) {
        this.J = timeInterpolator;
        r();
    }

    public void b(TimeInterpolator timeInterpolator) {
        this.K = timeInterpolator;
        r();
    }

    public void c(int i) {
        if (this.h != i) {
            this.h = i;
            r();
        }
    }

    public void d(float f) {
        if (this.i != f) {
            this.i = f;
            r();
        }
    }

    public void e(int i) {
        if (this.g != i) {
            this.g = i;
            r();
        }
    }

    public final void f(float f) {
        b(f);
        boolean z = T && this.D != 1.0f;
        this.y = z;
        if (z) {
            e();
        }
        lb.Q(this.f7849a);
    }

    public int g() {
        return this.h;
    }

    public float h() {
        a(this.I);
        return -this.I.ascent();
    }

    public Typeface i() {
        Typeface typeface = this.s;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int j() {
        int[] iArr = this.F;
        return iArr != null ? this.l.getColorForState(iArr, 0) : this.l.getDefaultColor();
    }

    public final int k() {
        int[] iArr = this.F;
        return iArr != null ? this.k.getColorForState(iArr, 0) : this.k.getDefaultColor();
    }

    public int l() {
        return this.g;
    }

    public Typeface m() {
        Typeface typeface = this.t;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float n() {
        return this.c;
    }

    public CharSequence o() {
        return this.v;
    }

    public final boolean p() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.l;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.k) != null && colorStateList.isStateful());
    }

    public void q() {
        this.b = this.f7850e.width() > 0 && this.f7850e.height() > 0 && this.d.width() > 0 && this.d.height() > 0;
    }

    public void r() {
        if (this.f7849a.getHeight() <= 0 || this.f7849a.getWidth() <= 0) {
            return;
        }
        a();
        c();
    }

    public void a(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            r();
        }
    }

    public void b(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            r();
        }
    }

    public void c(Typeface typeface) {
        this.t = typeface;
        this.s = typeface;
        r();
    }

    public void d(int i) {
        p3 p3VarA = p3.a(this.f7849a.getContext(), i, R.styleable.TextAppearance);
        if (p3VarA.g(R.styleable.TextAppearance_android_textColor)) {
            this.k = p3VarA.a(R.styleable.TextAppearance_android_textColor);
        }
        if (p3VarA.g(R.styleable.TextAppearance_android_textSize)) {
            this.i = p3VarA.c(R.styleable.TextAppearance_android_textSize, (int) this.i);
        }
        this.S = p3VarA.d(R.styleable.TextAppearance_android_shadowColor, 0);
        this.Q = p3VarA.b(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.R = p3VarA.b(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.P = p3VarA.b(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        p3VarA.b();
        if (Build.VERSION.SDK_INT >= 16) {
            this.t = a(i);
        }
        r();
    }

    public void e(float f) {
        float fA = i9.a(f, 0.0f, 1.0f);
        if (fA != this.c) {
            this.c = fA;
            c();
        }
    }

    public ColorStateList f() {
        return this.l;
    }

    public void a(int i, int i2, int i3, int i4) {
        if (a(this.f7850e, i, i2, i3, i4)) {
            return;
        }
        this.f7850e.set(i, i2, i3, i4);
        this.G = true;
        q();
    }

    public void b(int i, int i2, int i3, int i4) {
        if (a(this.d, i, i2, i3, i4)) {
            return;
        }
        this.d.set(i, i2, i3, i4);
        this.G = true;
        q();
    }

    public final void c() {
        a(this.c);
    }

    public final void c(float f) {
        this.f.left = a(this.d.left, this.f7850e.left, f, this.J);
        this.f.top = a(this.m, this.n, f, this.J);
        this.f.right = a(this.d.right, this.f7850e.right, f, this.J);
        this.f.bottom = a(this.d.bottom, this.f7850e.bottom, f, this.J);
    }

    public final void e() {
        if (this.z != null || this.d.isEmpty() || TextUtils.isEmpty(this.w)) {
            return;
        }
        a(0.0f);
        this.B = this.H.ascent();
        this.C = this.H.descent();
        TextPaint textPaint = this.H;
        CharSequence charSequence = this.w;
        int iRound = Math.round(textPaint.measureText(charSequence, 0, charSequence.length()));
        int iRound2 = Math.round(this.C - this.B);
        if (iRound <= 0 || iRound2 <= 0) {
            return;
        }
        this.z = Bitmap.createBitmap(iRound, iRound2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.z);
        CharSequence charSequence2 = this.w;
        canvas.drawText(charSequence2, 0, charSequence2.length(), 0.0f, iRound2 - this.H.descent(), this.H);
        if (this.A == null) {
            this.A = new Paint(3);
        }
    }

    public void a(RectF rectF) {
        boolean zA = a(this.v);
        Rect rect = this.f7850e;
        float fB = !zA ? rect.left : rect.right - b();
        rectF.left = fB;
        Rect rect2 = this.f7850e;
        rectF.top = rect2.top;
        rectF.right = !zA ? fB + b() : rect2.right;
        rectF.bottom = this.f7850e.top + h();
    }

    public float b() {
        if (this.v == null) {
            return 0.0f;
        }
        a(this.I);
        TextPaint textPaint = this.I;
        CharSequence charSequence = this.v;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void b(int i) {
        p3 p3VarA = p3.a(this.f7849a.getContext(), i, R.styleable.TextAppearance);
        if (p3VarA.g(R.styleable.TextAppearance_android_textColor)) {
            this.l = p3VarA.a(R.styleable.TextAppearance_android_textColor);
        }
        if (p3VarA.g(R.styleable.TextAppearance_android_textSize)) {
            this.j = p3VarA.c(R.styleable.TextAppearance_android_textSize, (int) this.j);
        }
        this.O = p3VarA.d(R.styleable.TextAppearance_android_shadowColor, 0);
        this.M = p3VarA.b(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.N = p3VarA.b(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.L = p3VarA.b(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        p3VarA.b();
        if (Build.VERSION.SDK_INT >= 16) {
            this.s = a(i);
        }
        r();
    }

    public final void a(TextPaint textPaint) {
        textPaint.setTextSize(this.j);
        textPaint.setTypeface(this.s);
    }

    public final Typeface a(int i) {
        TypedArray typedArrayObtainStyledAttributes = this.f7849a.getContext().obtainStyledAttributes(i, new int[]{android.R.attr.fontFamily});
        try {
            String string = typedArrayObtainStyledAttributes.getString(0);
            if (string != null) {
                return Typeface.create(string, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            return null;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void a(Typeface typeface) {
        if (this.s != typeface) {
            this.s = typeface;
            r();
        }
    }

    public final void d() {
        Bitmap bitmap = this.z;
        if (bitmap != null) {
            bitmap.recycle();
            this.z = null;
        }
    }

    public final boolean a(int[] iArr) {
        this.F = iArr;
        if (!p()) {
            return false;
        }
        r();
        return true;
    }

    public final void a(float f) {
        c(f);
        this.q = a(this.o, this.p, f, this.J);
        this.r = a(this.m, this.n, f, this.J);
        f(a(this.i, this.j, f, this.K));
        if (this.l != this.k) {
            this.H.setColor(a(k(), j(), f));
        } else {
            this.H.setColor(j());
        }
        this.H.setShadowLayer(a(this.P, this.L, f, (TimeInterpolator) null), a(this.Q, this.M, f, (TimeInterpolator) null), a(this.R, this.N, f, (TimeInterpolator) null), a(this.S, this.O, f));
        lb.Q(this.f7849a);
    }

    public void b(Typeface typeface) {
        if (this.t != typeface) {
            this.t = typeface;
            r();
        }
    }

    public final void b(float f) {
        boolean z;
        float f2;
        boolean z2;
        if (this.v == null) {
            return;
        }
        float fWidth = this.f7850e.width();
        float fWidth2 = this.d.width();
        if (a(f, this.j)) {
            f2 = this.j;
            this.D = 1.0f;
            Typeface typeface = this.u;
            Typeface typeface2 = this.s;
            if (typeface != typeface2) {
                this.u = typeface2;
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            float f3 = this.i;
            Typeface typeface3 = this.u;
            Typeface typeface4 = this.t;
            if (typeface3 != typeface4) {
                this.u = typeface4;
                z = true;
            } else {
                z = false;
            }
            if (a(f, this.i)) {
                this.D = 1.0f;
            } else {
                this.D = f / this.i;
            }
            float f4 = this.j / this.i;
            fWidth = fWidth2 * f4 > fWidth ? Math.min(fWidth / f4, fWidth2) : fWidth2;
            f2 = f3;
            z2 = z;
        }
        if (fWidth > 0.0f) {
            z2 = this.E != f2 || this.G || z2;
            this.E = f2;
            this.G = false;
        }
        if (this.w == null || z2) {
            this.H.setTextSize(this.E);
            this.H.setTypeface(this.u);
            this.H.setLinearText(this.D != 1.0f);
            CharSequence charSequenceEllipsize = TextUtils.ellipsize(this.v, this.H, fWidth, TextUtils.TruncateAt.END);
            if (TextUtils.equals(charSequenceEllipsize, this.w)) {
                return;
            }
            this.w = charSequenceEllipsize;
            this.x = a(charSequenceEllipsize);
        }
    }

    public final void a() {
        float f = this.E;
        b(this.j);
        CharSequence charSequence = this.w;
        float fMeasureText = charSequence != null ? this.H.measureText(charSequence, 0, charSequence.length()) : 0.0f;
        int iA = sa.a(this.h, this.x ? 1 : 0);
        int i = iA & 112;
        if (i == 48) {
            this.n = this.f7850e.top - this.H.ascent();
        } else if (i != 80) {
            this.n = this.f7850e.centerY() + (((this.H.descent() - this.H.ascent()) / 2.0f) - this.H.descent());
        } else {
            this.n = this.f7850e.bottom;
        }
        int i2 = iA & 8388615;
        if (i2 == 1) {
            this.p = this.f7850e.centerX() - (fMeasureText / 2.0f);
        } else if (i2 != 5) {
            this.p = this.f7850e.left;
        } else {
            this.p = this.f7850e.right - fMeasureText;
        }
        b(this.i);
        CharSequence charSequence2 = this.w;
        float fMeasureText2 = charSequence2 != null ? this.H.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iA2 = sa.a(this.g, this.x ? 1 : 0);
        int i3 = iA2 & 112;
        if (i3 == 48) {
            this.m = this.d.top - this.H.ascent();
        } else if (i3 != 80) {
            this.m = this.d.centerY() + (((this.H.descent() - this.H.ascent()) / 2.0f) - this.H.descent());
        } else {
            this.m = this.d.bottom;
        }
        int i4 = iA2 & 8388615;
        if (i4 == 1) {
            this.o = this.d.centerX() - (fMeasureText2 / 2.0f);
        } else if (i4 != 5) {
            this.o = this.d.left;
        } else {
            this.o = this.d.right - fMeasureText2;
        }
        d();
        f(f);
    }

    public void b(CharSequence charSequence) {
        if (charSequence == null || !charSequence.equals(this.v)) {
            this.v = charSequence;
            this.w = null;
            d();
            r();
        }
    }

    public void a(Canvas canvas) {
        float fAscent;
        int iSave = canvas.save();
        if (this.w != null && this.b) {
            float f = this.q;
            float f2 = this.r;
            boolean z = this.y && this.z != null;
            if (z) {
                fAscent = this.B * this.D;
            } else {
                fAscent = this.H.ascent() * this.D;
                this.H.descent();
            }
            if (z) {
                f2 += fAscent;
            }
            float f3 = f2;
            float f4 = this.D;
            if (f4 != 1.0f) {
                canvas.scale(f4, f4, f, f3);
            }
            if (z) {
                canvas.drawBitmap(this.z, f, f3, this.A);
            } else {
                CharSequence charSequence = this.w;
                canvas.drawText(charSequence, 0, charSequence.length(), f, f3, this.H);
            }
        }
        canvas.restoreToCount(iSave);
    }

    public final boolean a(CharSequence charSequence) {
        return (lb.n(this.f7849a) == 1 ? da.d : da.c).a(charSequence, 0, charSequence.length());
    }

    public static boolean a(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    public static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i) * f2) + (Color.alpha(i2) * f)), (int) ((Color.red(i) * f2) + (Color.red(i2) * f)), (int) ((Color.green(i) * f2) + (Color.green(i2) * f)), (int) ((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    public static float a(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return cl0.a(f, f2, f3);
    }

    public static boolean a(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }
}
