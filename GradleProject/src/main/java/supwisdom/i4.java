package supwisdom;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.cardview.R;

/* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
/* JADX INFO: loaded from: classes.dex */
public class i4 extends Drawable {
    public static final double q = Math.cos(Math.toRadians(45.0d));
    public static a r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7907a;
    public Paint c;
    public Paint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f7908e;
    public float f;
    public Path g;
    public float h;
    public float i;
    public float j;
    public ColorStateList k;
    public final int m;
    public final int n;
    public boolean l = true;
    public boolean o = true;
    public boolean p = false;
    public Paint b = new Paint(5);

    /* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
    public interface a {
        void a(Canvas canvas, RectF rectF, float f, Paint paint);
    }

    public i4(Resources resources, ColorStateList colorStateList, float f, float f2, float f3) {
        this.m = resources.getColor(R.color.cardview_shadow_start_color);
        this.n = resources.getColor(R.color.cardview_shadow_end_color);
        this.f7907a = resources.getDimensionPixelSize(R.dimen.cardview_compat_inset_shadow);
        a(colorStateList);
        Paint paint = new Paint(5);
        this.c = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f = (int) (f + 0.5f);
        this.f7908e = new RectF();
        Paint paint2 = new Paint(this.c);
        this.d = paint2;
        paint2.setAntiAlias(false);
        a(f2, f3);
    }

    public static float b(float f, float f2, boolean z) {
        return z ? (float) (((double) (f * 1.5f)) + ((1.0d - q) * ((double) f2))) : f * 1.5f;
    }

    public final void a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.k = colorStateList;
        this.b.setColor(colorStateList.getColorForState(getState(), this.k.getDefaultColor()));
    }

    public float c() {
        return this.f;
    }

    public final int d(float f) {
        int i = (int) (f + 0.5f);
        return i % 2 == 1 ? i - 1 : i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.l) {
            a(getBounds());
            this.l = false;
        }
        canvas.translate(0.0f, this.j / 2.0f);
        a(canvas);
        canvas.translate(0.0f, (-this.j) / 2.0f);
        r.a(canvas, this.f7908e, this.f, this.b);
    }

    public float e() {
        float f = this.h;
        return (Math.max(f, this.f + this.f7907a + ((f * 1.5f) / 2.0f)) * 2.0f) + (((this.h * 1.5f) + this.f7907a) * 2.0f);
    }

    public float f() {
        float f = this.h;
        return (Math.max(f, this.f + this.f7907a + (f / 2.0f)) * 2.0f) + ((this.h + this.f7907a) * 2.0f);
    }

    public float g() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(b(this.h, this.f, this.o));
        int iCeil2 = (int) Math.ceil(a(this.h, this.f, this.o));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.k;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.l = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.k;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.b.getColor() == colorForState) {
            return false;
        }
        this.b.setColor(colorForState);
        this.l = true;
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.b.setAlpha(i);
        this.c.setAlpha(i);
        this.d.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
    }

    public void b(Rect rect) {
        getPadding(rect);
    }

    public void c(float f) {
        a(f, this.h);
    }

    public float d() {
        return this.h;
    }

    public void a(boolean z) {
        this.o = z;
        invalidateSelf();
    }

    public void b(float f) {
        a(this.j, f);
    }

    public void b(ColorStateList colorStateList) {
        a(colorStateList);
        invalidateSelf();
    }

    public final void a(float f, float f2) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f + ". Must be >= 0");
        }
        if (f2 >= 0.0f) {
            float fD = d(f);
            float fD2 = d(f2);
            if (fD > fD2) {
                if (!this.p) {
                    this.p = true;
                }
                fD = fD2;
            }
            if (this.j == fD && this.h == fD2) {
                return;
            }
            this.j = fD;
            this.h = fD2;
            this.i = (int) ((fD * 1.5f) + this.f7907a + 0.5f);
            this.l = true;
            invalidateSelf();
            return;
        }
        throw new IllegalArgumentException("Invalid max shadow size " + f2 + ". Must be >= 0");
    }

    public ColorStateList b() {
        return this.k;
    }

    public static float a(float f, float f2, boolean z) {
        return z ? (float) (((double) f) + ((1.0d - q) * ((double) f2))) : f;
    }

    public void a(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f + ". Must be >= 0");
        }
        float f2 = (int) (f + 0.5f);
        if (this.f == f2) {
            return;
        }
        this.f = f2;
        this.l = true;
        invalidateSelf();
    }

    public final void a(Canvas canvas) {
        float f = this.f;
        float f2 = (-f) - this.i;
        float f3 = f + this.f7907a + (this.j / 2.0f);
        float f4 = f3 * 2.0f;
        boolean z = this.f7908e.width() - f4 > 0.0f;
        boolean z2 = this.f7908e.height() - f4 > 0.0f;
        int iSave = canvas.save();
        RectF rectF = this.f7908e;
        canvas.translate(rectF.left + f3, rectF.top + f3);
        canvas.drawPath(this.g, this.c);
        if (z) {
            canvas.drawRect(0.0f, f2, this.f7908e.width() - f4, -this.f, this.d);
        }
        canvas.restoreToCount(iSave);
        int iSave2 = canvas.save();
        RectF rectF2 = this.f7908e;
        canvas.translate(rectF2.right - f3, rectF2.bottom - f3);
        canvas.rotate(180.0f);
        canvas.drawPath(this.g, this.c);
        if (z) {
            canvas.drawRect(0.0f, f2, this.f7908e.width() - f4, (-this.f) + this.i, this.d);
        }
        canvas.restoreToCount(iSave2);
        int iSave3 = canvas.save();
        RectF rectF3 = this.f7908e;
        canvas.translate(rectF3.left + f3, rectF3.bottom - f3);
        canvas.rotate(270.0f);
        canvas.drawPath(this.g, this.c);
        if (z2) {
            canvas.drawRect(0.0f, f2, this.f7908e.height() - f4, -this.f, this.d);
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF4 = this.f7908e;
        canvas.translate(rectF4.right - f3, rectF4.top + f3);
        canvas.rotate(90.0f);
        canvas.drawPath(this.g, this.c);
        if (z2) {
            canvas.drawRect(0.0f, f2, this.f7908e.height() - f4, -this.f, this.d);
        }
        canvas.restoreToCount(iSave4);
    }

    public final void a() {
        float f = this.f;
        RectF rectF = new RectF(-f, -f, f, f);
        RectF rectF2 = new RectF(rectF);
        float f2 = this.i;
        rectF2.inset(-f2, -f2);
        Path path = this.g;
        if (path == null) {
            this.g = new Path();
        } else {
            path.reset();
        }
        this.g.setFillType(Path.FillType.EVEN_ODD);
        this.g.moveTo(-this.f, 0.0f);
        this.g.rLineTo(-this.i, 0.0f);
        this.g.arcTo(rectF2, 180.0f, 90.0f, false);
        this.g.arcTo(rectF, 270.0f, -90.0f, false);
        this.g.close();
        float f3 = this.f;
        float f4 = f3 / (this.i + f3);
        Paint paint = this.c;
        float f5 = this.f + this.i;
        int i = this.m;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f5, new int[]{i, i, this.n}, new float[]{0.0f, f4, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.d;
        float f6 = this.f;
        float f7 = this.i;
        int i2 = this.m;
        paint2.setShader(new LinearGradient(0.0f, (-f6) + f7, 0.0f, (-f6) - f7, new int[]{i2, i2, this.n}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.d.setAntiAlias(false);
    }

    public final void a(Rect rect) {
        float f = this.h;
        float f2 = 1.5f * f;
        this.f7908e.set(rect.left + f, rect.top + f2, rect.right - f, rect.bottom - f2);
        a();
    }
}
