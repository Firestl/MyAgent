package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R;
import java.lang.ref.WeakReference;
import java.util.Locale;
import supwisdom.k8;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class u2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f9354a;
    public n3 b;
    public n3 c;
    public n3 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public n3 f9355e;
    public n3 f;
    public n3 g;
    public n3 h;
    public final v2 i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends k8.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f9356a;
        public final /* synthetic */ int b;
        public final /* synthetic */ WeakReference c;

        public a(int i, int i2, WeakReference weakReference) {
            this.f9356a = i;
            this.b = i2;
            this.c = weakReference;
        }

        @Override // supwisdom.k8.c
        public void a(int i) {
        }

        @Override // supwisdom.k8.c
        public void a(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f9356a) != -1) {
                typeface = Typeface.create(typeface, i, (this.b & 2) != 0);
            }
            u2.this.a(this.c, typeface);
        }
    }

    public u2(TextView textView) {
        this.f9354a = textView;
        this.i = new v2(this.f9354a);
    }

    @SuppressLint({"NewApi"})
    public void a(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListA;
        String strD;
        boolean zA;
        boolean z;
        ColorStateList colorStateListA2;
        String strD2;
        ColorStateList colorStateListA3;
        boolean z2;
        int i2;
        Context context = this.f9354a.getContext();
        o2 o2VarB = o2.b();
        p3 p3VarA = p3.a(context, attributeSet, R.styleable.AppCompatTextHelper, i, 0);
        TextView textView = this.f9354a;
        lb.a(textView, textView.getContext(), R.styleable.AppCompatTextHelper, attributeSet, p3VarA.a(), i, 0);
        int iG = p3VarA.g(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.b = a(context, o2VarB, p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableTop)) {
            this.c = a(context, o2VarB, p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableRight)) {
            this.d = a(context, o2VarB, p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.f9355e = a(context, o2VarB, p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableStart)) {
                this.f = a(context, o2VarB, p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableEnd)) {
                this.g = a(context, o2VarB, p3VarA.g(R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        p3VarA.b();
        boolean z3 = this.f9354a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (iG != -1) {
            p3 p3VarA2 = p3.a(context, iG, R.styleable.TextAppearance);
            if (z3 || !p3VarA2.g(R.styleable.TextAppearance_textAllCaps)) {
                zA = false;
                z = false;
            } else {
                zA = p3VarA2.a(R.styleable.TextAppearance_textAllCaps, false);
                z = true;
            }
            a(context, p3VarA2);
            if (Build.VERSION.SDK_INT < 23) {
                colorStateListA3 = p3VarA2.g(R.styleable.TextAppearance_android_textColor) ? p3VarA2.a(R.styleable.TextAppearance_android_textColor) : null;
                colorStateListA = p3VarA2.g(R.styleable.TextAppearance_android_textColorHint) ? p3VarA2.a(R.styleable.TextAppearance_android_textColorHint) : null;
                colorStateListA2 = p3VarA2.g(R.styleable.TextAppearance_android_textColorLink) ? p3VarA2.a(R.styleable.TextAppearance_android_textColorLink) : null;
            } else {
                colorStateListA = null;
                colorStateListA2 = null;
                colorStateListA3 = null;
            }
            strD2 = p3VarA2.g(R.styleable.TextAppearance_textLocale) ? p3VarA2.d(R.styleable.TextAppearance_textLocale) : null;
            strD = (Build.VERSION.SDK_INT < 26 || !p3VarA2.g(R.styleable.TextAppearance_fontVariationSettings)) ? null : p3VarA2.d(R.styleable.TextAppearance_fontVariationSettings);
            p3VarA2.b();
        } else {
            colorStateListA = null;
            strD = null;
            zA = false;
            z = false;
            colorStateListA2 = null;
            strD2 = null;
            colorStateListA3 = null;
        }
        p3 p3VarA3 = p3.a(context, attributeSet, R.styleable.TextAppearance, i, 0);
        if (z3 || !p3VarA3.g(R.styleable.TextAppearance_textAllCaps)) {
            z2 = z;
        } else {
            zA = p3VarA3.a(R.styleable.TextAppearance_textAllCaps, false);
            z2 = true;
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (p3VarA3.g(R.styleable.TextAppearance_android_textColor)) {
                colorStateListA3 = p3VarA3.a(R.styleable.TextAppearance_android_textColor);
            }
            if (p3VarA3.g(R.styleable.TextAppearance_android_textColorHint)) {
                colorStateListA = p3VarA3.a(R.styleable.TextAppearance_android_textColorHint);
            }
            if (p3VarA3.g(R.styleable.TextAppearance_android_textColorLink)) {
                colorStateListA2 = p3VarA3.a(R.styleable.TextAppearance_android_textColorLink);
            }
        }
        if (p3VarA3.g(R.styleable.TextAppearance_textLocale)) {
            strD2 = p3VarA3.d(R.styleable.TextAppearance_textLocale);
        }
        if (Build.VERSION.SDK_INT >= 26 && p3VarA3.g(R.styleable.TextAppearance_fontVariationSettings)) {
            strD = p3VarA3.d(R.styleable.TextAppearance_fontVariationSettings);
        }
        if (Build.VERSION.SDK_INT >= 28 && p3VarA3.g(R.styleable.TextAppearance_android_textSize) && p3VarA3.c(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.f9354a.setTextSize(0, 0.0f);
        }
        a(context, p3VarA3);
        p3VarA3.b();
        if (colorStateListA3 != null) {
            this.f9354a.setTextColor(colorStateListA3);
        }
        if (colorStateListA != null) {
            this.f9354a.setHintTextColor(colorStateListA);
        }
        if (colorStateListA2 != null) {
            this.f9354a.setLinkTextColor(colorStateListA2);
        }
        if (!z3 && z2) {
            a(zA);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.k == -1) {
                this.f9354a.setTypeface(typeface, this.j);
            } else {
                this.f9354a.setTypeface(typeface);
            }
        }
        if (strD != null) {
            this.f9354a.setFontVariationSettings(strD);
        }
        if (strD2 != null) {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 24) {
                this.f9354a.setTextLocales(LocaleList.forLanguageTags(strD2));
            } else if (i3 >= 21) {
                this.f9354a.setTextLocale(Locale.forLanguageTag(strD2.substring(0, strD2.indexOf(44))));
            }
        }
        this.i.a(attributeSet, i);
        if (gc.C1 && this.i.g() != 0) {
            int[] iArrF = this.i.f();
            if (iArrF.length > 0) {
                if (this.f9354a.getAutoSizeStepGranularity() != -1.0f) {
                    this.f9354a.setAutoSizeTextTypeUniformWithConfiguration(this.i.d(), this.i.c(), this.i.e(), 0);
                } else {
                    this.f9354a.setAutoSizeTextTypeUniformWithPresetSizes(iArrF, 0);
                }
            }
        }
        p3 p3VarA4 = p3.a(context, attributeSet, R.styleable.AppCompatTextView);
        int iG2 = p3VarA4.g(R.styleable.AppCompatTextView_drawableLeftCompat, -1);
        Drawable drawableA = iG2 != -1 ? o2VarB.a(context, iG2) : null;
        int iG3 = p3VarA4.g(R.styleable.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableA2 = iG3 != -1 ? o2VarB.a(context, iG3) : null;
        int iG4 = p3VarA4.g(R.styleable.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableA3 = iG4 != -1 ? o2VarB.a(context, iG4) : null;
        int iG5 = p3VarA4.g(R.styleable.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableA4 = iG5 != -1 ? o2VarB.a(context, iG5) : null;
        int iG6 = p3VarA4.g(R.styleable.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableA5 = iG6 != -1 ? o2VarB.a(context, iG6) : null;
        int iG7 = p3VarA4.g(R.styleable.AppCompatTextView_drawableEndCompat, -1);
        a(drawableA, drawableA2, drawableA3, drawableA4, drawableA5, iG7 != -1 ? o2VarB.a(context, iG7) : null);
        if (p3VarA4.g(R.styleable.AppCompatTextView_drawableTint)) {
            nc.a(this.f9354a, p3VarA4.a(R.styleable.AppCompatTextView_drawableTint));
        }
        if (p3VarA4.g(R.styleable.AppCompatTextView_drawableTintMode)) {
            i2 = -1;
            nc.a(this.f9354a, y2.a(p3VarA4.d(R.styleable.AppCompatTextView_drawableTintMode, -1), null));
        } else {
            i2 = -1;
        }
        int iC = p3VarA4.c(R.styleable.AppCompatTextView_firstBaselineToTopHeight, i2);
        int iC2 = p3VarA4.c(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, i2);
        int iC3 = p3VarA4.c(R.styleable.AppCompatTextView_lineHeight, i2);
        p3VarA4.b();
        if (iC != i2) {
            nc.a(this.f9354a, iC);
        }
        if (iC2 != i2) {
            nc.b(this.f9354a, iC2);
        }
        if (iC3 != i2) {
            nc.c(this.f9354a, iC3);
        }
    }

    public void b() {
        this.i.a();
    }

    public int c() {
        return this.i.c();
    }

    public int d() {
        return this.i.d();
    }

    public int e() {
        return this.i.e();
    }

    public int[] f() {
        return this.i.f();
    }

    public int g() {
        return this.i.g();
    }

    public ColorStateList h() {
        n3 n3Var = this.h;
        if (n3Var != null) {
            return n3Var.f8470a;
        }
        return null;
    }

    public PorterDuff.Mode i() {
        n3 n3Var = this.h;
        if (n3Var != null) {
            return n3Var.b;
        }
        return null;
    }

    public boolean j() {
        return this.i.h();
    }

    public void k() {
        a();
    }

    public final void l() {
        n3 n3Var = this.h;
        this.b = n3Var;
        this.c = n3Var;
        this.d = n3Var;
        this.f9355e = n3Var;
        this.f = n3Var;
        this.g = n3Var;
    }

    public final void b(int i, float f) {
        this.i.a(i, f);
    }

    public final void a(Context context, p3 p3Var) {
        int i;
        String strD;
        this.j = p3Var.d(R.styleable.TextAppearance_android_textStyle, this.j);
        if (Build.VERSION.SDK_INT >= 28) {
            int iD = p3Var.d(R.styleable.TextAppearance_android_textFontWeight, -1);
            this.k = iD;
            if (iD != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        if (!p3Var.g(R.styleable.TextAppearance_android_fontFamily) && !p3Var.g(R.styleable.TextAppearance_fontFamily)) {
            if (p3Var.g(R.styleable.TextAppearance_android_typeface)) {
                this.m = false;
                int iD2 = p3Var.d(R.styleable.TextAppearance_android_typeface, 1);
                if (iD2 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iD2 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iD2 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        if (p3Var.g(R.styleable.TextAppearance_fontFamily)) {
            i = R.styleable.TextAppearance_fontFamily;
        } else {
            i = R.styleable.TextAppearance_android_fontFamily;
        }
        int i2 = this.k;
        int i3 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceA = p3Var.a(i, this.j, new a(i2, i3, new WeakReference(this.f9354a)));
                if (typefaceA != null) {
                    if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
                        this.l = Typeface.create(Typeface.create(typefaceA, 0), this.k, (this.j & 2) != 0);
                    } else {
                        this.l = typefaceA;
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strD = p3Var.d(i)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
            this.l = Typeface.create(Typeface.create(strD, 0), this.k, (this.j & 2) != 0);
        } else {
            this.l = Typeface.create(strD, this.j);
        }
    }

    public void a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.m) {
            this.l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.j);
            }
        }
    }

    public void a(Context context, int i) {
        String strD;
        ColorStateList colorStateListA;
        p3 p3VarA = p3.a(context, i, R.styleable.TextAppearance);
        if (p3VarA.g(R.styleable.TextAppearance_textAllCaps)) {
            a(p3VarA.a(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && p3VarA.g(R.styleable.TextAppearance_android_textColor) && (colorStateListA = p3VarA.a(R.styleable.TextAppearance_android_textColor)) != null) {
            this.f9354a.setTextColor(colorStateListA);
        }
        if (p3VarA.g(R.styleable.TextAppearance_android_textSize) && p3VarA.c(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.f9354a.setTextSize(0, 0.0f);
        }
        a(context, p3VarA);
        if (Build.VERSION.SDK_INT >= 26 && p3VarA.g(R.styleable.TextAppearance_fontVariationSettings) && (strD = p3VarA.d(R.styleable.TextAppearance_fontVariationSettings)) != null) {
            this.f9354a.setFontVariationSettings(strD);
        }
        p3VarA.b();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f9354a.setTypeface(typeface, this.j);
        }
    }

    public void a(boolean z) {
        this.f9354a.setAllCaps(z);
    }

    public void a() {
        if (this.b != null || this.c != null || this.d != null || this.f9355e != null) {
            Drawable[] compoundDrawables = this.f9354a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.f9355e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f9354a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    public final void a(Drawable drawable, n3 n3Var) {
        if (drawable == null || n3Var == null) {
            return;
        }
        o2.a(drawable, n3Var, this.f9354a.getDrawableState());
    }

    public static n3 a(Context context, o2 o2Var, int i) {
        ColorStateList colorStateListB = o2Var.b(context, i);
        if (colorStateListB == null) {
            return null;
        }
        n3 n3Var = new n3();
        n3Var.d = true;
        n3Var.f8470a = colorStateListB;
        return n3Var;
    }

    public void a(boolean z, int i, int i2, int i3, int i4) {
        if (gc.C1) {
            return;
        }
        b();
    }

    public void a(int i, float f) {
        if (gc.C1 || j()) {
            return;
        }
        b(i, f);
    }

    public void a(int i) {
        this.i.b(i);
    }

    public void a(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.i.a(i, i2, i3, i4);
    }

    public void a(int[] iArr, int i) throws IllegalArgumentException {
        this.i.a(iArr, i);
    }

    public void a(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new n3();
        }
        n3 n3Var = this.h;
        n3Var.f8470a = colorStateList;
        n3Var.d = colorStateList != null;
        l();
    }

    public void a(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new n3();
        }
        n3 n3Var = this.h;
        n3Var.b = mode;
        n3Var.c = mode != null;
        l();
    }

    public final void a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (Build.VERSION.SDK_INT >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f9354a.getCompoundDrawablesRelative();
            TextView textView = this.f9354a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f9354a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f9354a;
                Drawable drawable7 = compoundDrawablesRelative2[0];
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable8 = compoundDrawablesRelative2[2];
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative2[3];
                }
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                return;
            }
        }
        Drawable[] compoundDrawables = this.f9354a.getCompoundDrawables();
        TextView textView3 = this.f9354a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }
}
