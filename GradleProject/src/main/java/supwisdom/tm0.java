package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import com.google.android.material.R;
import supwisdom.k8;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public class tm0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f9302a;
    public final ColorStateList b;
    public final ColorStateList c;
    public final ColorStateList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9303e;
    public final int f;
    public final String g;
    public final ColorStateList h;
    public final float i;
    public final float j;
    public final float k;
    public final int l;
    public boolean m = false;
    public Typeface n;

    public tm0(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.TextAppearance);
        this.f9302a = typedArrayObtainStyledAttributes.getDimension(R.styleable.TextAppearance_android_textSize, 0.0f);
        this.b = sm0.a(context, typedArrayObtainStyledAttributes, R.styleable.TextAppearance_android_textColor);
        this.c = sm0.a(context, typedArrayObtainStyledAttributes, R.styleable.TextAppearance_android_textColorHint);
        this.d = sm0.a(context, typedArrayObtainStyledAttributes, R.styleable.TextAppearance_android_textColorLink);
        this.f9303e = typedArrayObtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, 0);
        this.f = typedArrayObtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, 1);
        int iA = sm0.a(typedArrayObtainStyledAttributes, R.styleable.TextAppearance_fontFamily, R.styleable.TextAppearance_android_fontFamily);
        this.l = typedArrayObtainStyledAttributes.getResourceId(iA, 0);
        this.g = typedArrayObtainStyledAttributes.getString(iA);
        typedArrayObtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        this.h = sm0.a(context, typedArrayObtainStyledAttributes, R.styleable.TextAppearance_android_shadowColor);
        this.i = typedArrayObtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.j = typedArrayObtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void b(Context context, TextPaint textPaint, k8.c cVar) {
        c(context, textPaint, cVar);
        ColorStateList colorStateList = this.b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f = this.k;
        float f2 = this.i;
        float f3 = this.j;
        ColorStateList colorStateList2 = this.h;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void c(Context context, TextPaint textPaint, k8.c cVar) {
        if (um0.a()) {
            a(textPaint, a(context));
            return;
        }
        a(context, textPaint, cVar);
        if (this.m) {
            return;
        }
        a(textPaint, this.n);
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    public class a extends k8.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextPaint f9304a;
        public final /* synthetic */ k8.c b;

        public a(TextPaint textPaint, k8.c cVar) {
            this.f9304a = textPaint;
            this.b = cVar;
        }

        @Override // supwisdom.k8.c
        public void a(Typeface typeface) {
            tm0 tm0Var = tm0.this;
            tm0Var.n = Typeface.create(typeface, tm0Var.f9303e);
            tm0.this.a(this.f9304a, typeface);
            tm0.this.m = true;
            this.b.a(typeface);
        }

        @Override // supwisdom.k8.c
        public void a(int i) {
            tm0.this.a();
            tm0.this.m = true;
            this.b.a(i);
        }
    }

    public Typeface a(Context context) {
        if (this.m) {
            return this.n;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceA = k8.a(context, this.l);
                this.n = typefaceA;
                if (typefaceA != null) {
                    this.n = Typeface.create(typefaceA, this.f9303e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                Log.d("TextAppearance", "Error loading font " + this.g, e2);
            }
        }
        a();
        this.m = true;
        return this.n;
    }

    public void a(Context context, TextPaint textPaint, k8.c cVar) {
        if (this.m) {
            a(textPaint, this.n);
            return;
        }
        a();
        if (context.isRestricted()) {
            this.m = true;
            a(textPaint, this.n);
            return;
        }
        try {
            k8.a(context, this.l, new a(textPaint, cVar), null);
        } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
        } catch (Exception e2) {
            Log.d("TextAppearance", "Error loading font " + this.g, e2);
        }
    }

    public final void a() {
        if (this.n == null) {
            this.n = Typeface.create(this.g, this.f9303e);
        }
        if (this.n == null) {
            int i = this.f;
            if (i == 1) {
                this.n = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.n = Typeface.SERIF;
            } else if (i != 3) {
                this.n = Typeface.DEFAULT;
            } else {
                this.n = Typeface.MONOSPACE;
            }
            Typeface typeface = this.n;
            if (typeface != null) {
                this.n = Typeface.create(typeface, this.f9303e);
            }
        }
    }

    public void a(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.f9303e;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f9302a);
    }
}
