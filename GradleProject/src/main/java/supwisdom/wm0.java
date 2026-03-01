package supwisdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.google.android.material.R;

/* JADX INFO: compiled from: ShadowDrawableWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class wm0 extends e1 {
    public static final double q = Math.cos(Math.toRadians(45.0d));
    public final Paint b;
    public final Paint c;
    public final RectF d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9645e;
    public Path f;
    public float g;
    public float h;
    public float i;
    public boolean j;
    public final int k;
    public final int l;
    public final int m;
    public boolean n;
    public float o;
    public boolean p;

    public wm0(Context context, Drawable drawable, float f, float f2, float f3) {
        super(drawable);
        this.j = true;
        this.n = true;
        this.p = false;
        this.k = y7.a(context, R.color.design_fab_shadow_start_color);
        this.l = y7.a(context, R.color.design_fab_shadow_mid_color);
        this.m = y7.a(context, R.color.design_fab_shadow_end_color);
        Paint paint = new Paint(5);
        this.b = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f9645e = Math.round(f);
        this.d = new RectF();
        Paint paint2 = new Paint(this.b);
        this.c = paint2;
        paint2.setAntiAlias(false);
        a(f2, f3);
    }

    public static int c(float f) {
        int iRound = Math.round(f);
        return iRound % 2 == 1 ? iRound - 1 : iRound;
    }

    public void a(boolean z) {
        this.n = z;
        invalidateSelf();
    }

    public void b(float f) {
        a(f, this.g);
    }

    @Override // supwisdom.e1, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.j) {
            a(getBounds());
            this.j = false;
        }
        a(canvas);
        super.draw(canvas);
    }

    @Override // supwisdom.e1, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // supwisdom.e1, android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(b(this.g, this.f9645e, this.n));
        int iCeil2 = (int) Math.ceil(a(this.g, this.f9645e, this.n));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    @Override // supwisdom.e1, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.j = true;
    }

    @Override // supwisdom.e1, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.b.setAlpha(i);
        this.c.setAlpha(i);
    }

    public static float b(float f, float f2, boolean z) {
        return z ? (float) (((double) (f * 1.5f)) + ((1.0d - q) * ((double) f2))) : f * 1.5f;
    }

    public void a(float f, float f2) {
        if (f >= 0.0f && f2 >= 0.0f) {
            float fC = c(f);
            float fC2 = c(f2);
            if (fC > fC2) {
                if (!this.p) {
                    this.p = true;
                }
                fC = fC2;
            }
            if (this.i == fC && this.g == fC2) {
                return;
            }
            this.i = fC;
            this.g = fC2;
            this.h = Math.round(fC * 1.5f);
            this.j = true;
            invalidateSelf();
            return;
        }
        throw new IllegalArgumentException("invalid shadow size");
    }

    public final void b() {
        float f = this.f9645e;
        RectF rectF = new RectF(-f, -f, f, f);
        RectF rectF2 = new RectF(rectF);
        float f2 = this.h;
        rectF2.inset(-f2, -f2);
        Path path = this.f;
        if (path == null) {
            this.f = new Path();
        } else {
            path.reset();
        }
        this.f.setFillType(Path.FillType.EVEN_ODD);
        this.f.moveTo(-this.f9645e, 0.0f);
        this.f.rLineTo(-this.h, 0.0f);
        this.f.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f.arcTo(rectF, 270.0f, -90.0f, false);
        this.f.close();
        float f3 = -rectF2.top;
        if (f3 > 0.0f) {
            float f4 = this.f9645e / f3;
            this.b.setShader(new RadialGradient(0.0f, 0.0f, f3, new int[]{0, this.k, this.l, this.m}, new float[]{0.0f, f4, ((1.0f - f4) / 2.0f) + f4, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.c.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.k, this.l, this.m}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.c.setAntiAlias(false);
    }

    public float c() {
        return this.i;
    }

    public static float a(float f, float f2, boolean z) {
        return z ? (float) (((double) f) + ((1.0d - q) * ((double) f2))) : f;
    }

    public final void a(float f) {
        if (this.o != f) {
            this.o = f;
            invalidateSelf();
        }
    }

    public final void a(Canvas canvas) {
        int i;
        float f;
        int i2;
        float f2;
        float f3;
        float f4;
        int iSave = canvas.save();
        canvas.rotate(this.o, this.d.centerX(), this.d.centerY());
        float f5 = this.f9645e;
        float f6 = (-f5) - this.h;
        float f7 = f5 * 2.0f;
        boolean z = this.d.width() - f7 > 0.0f;
        boolean z2 = this.d.height() - f7 > 0.0f;
        float f8 = this.i;
        float f9 = f5 / ((f8 - (0.5f * f8)) + f5);
        float f10 = f5 / ((f8 - (0.25f * f8)) + f5);
        float f11 = f5 / ((f8 - (f8 * 1.0f)) + f5);
        int iSave2 = canvas.save();
        RectF rectF = this.d;
        canvas.translate(rectF.left + f5, rectF.top + f5);
        canvas.scale(f9, f10);
        canvas.drawPath(this.f, this.b);
        if (z) {
            canvas.scale(1.0f / f9, 1.0f);
            i = iSave2;
            f = f11;
            i2 = iSave;
            f2 = f10;
            canvas.drawRect(0.0f, f6, this.d.width() - f7, -this.f9645e, this.c);
        } else {
            i = iSave2;
            f = f11;
            i2 = iSave;
            f2 = f10;
        }
        canvas.restoreToCount(i);
        int iSave3 = canvas.save();
        RectF rectF2 = this.d;
        canvas.translate(rectF2.right - f5, rectF2.bottom - f5);
        float f12 = f;
        canvas.scale(f9, f12);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f, this.b);
        if (z) {
            canvas.scale(1.0f / f9, 1.0f);
            f3 = f2;
            f4 = f12;
            canvas.drawRect(0.0f, f6, this.d.width() - f7, (-this.f9645e) + this.h, this.c);
        } else {
            f3 = f2;
            f4 = f12;
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF3 = this.d;
        canvas.translate(rectF3.left + f5, rectF3.bottom - f5);
        canvas.scale(f9, f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f, this.b);
        if (z2) {
            canvas.scale(1.0f / f4, 1.0f);
            canvas.drawRect(0.0f, f6, this.d.height() - f7, -this.f9645e, this.c);
        }
        canvas.restoreToCount(iSave4);
        int iSave5 = canvas.save();
        RectF rectF4 = this.d;
        canvas.translate(rectF4.right - f5, rectF4.top + f5);
        float f13 = f3;
        canvas.scale(f9, f13);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f, this.b);
        if (z2) {
            canvas.scale(1.0f / f13, 1.0f);
            canvas.drawRect(0.0f, f6, this.d.height() - f7, -this.f9645e, this.c);
        }
        canvas.restoreToCount(iSave5);
        canvas.restoreToCount(i2);
    }

    public final void a(Rect rect) {
        float f = this.g;
        float f2 = 1.5f * f;
        this.d.set(rect.left + f, rect.top + f2, rect.right - f, rect.bottom - f2);
        Drawable drawableA = a();
        RectF rectF = this.d;
        drawableA.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        b();
    }
}
