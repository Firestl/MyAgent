package supwisdom;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: CircularBorderDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class fm0 extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f7615a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f7616e;
    public int f;
    public int g;
    public int h;
    public int i;
    public ColorStateList j;
    public int k;
    public float m;
    public final Rect b = new Rect();
    public final RectF c = new RectF();
    public final b d = new b();
    public boolean l = true;

    /* JADX INFO: compiled from: CircularBorderDrawable.java */
    public class b extends Drawable.ConstantState {
        public b() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return fm0.this;
        }
    }

    public fm0() {
        Paint paint = new Paint(1);
        this.f7615a = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.f = i;
        this.g = i2;
        this.h = i3;
        this.i = i4;
    }

    public final void b(float f) {
        if (f != this.m) {
            this.m = f;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.l) {
            this.f7615a.setShader(a());
            this.l = false;
        }
        float strokeWidth = this.f7615a.getStrokeWidth() / 2.0f;
        RectF rectF = this.c;
        copyBounds(this.b);
        rectF.set(this.b);
        rectF.left += strokeWidth;
        rectF.top += strokeWidth;
        rectF.right -= strokeWidth;
        rectF.bottom -= strokeWidth;
        canvas.save();
        canvas.rotate(this.m, rectF.centerX(), rectF.centerY());
        canvas.drawOval(rectF, this.f7615a);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f7616e > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iRound = Math.round(this.f7616e);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.j;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.l = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.j;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.k)) != this.k) {
            this.l = true;
            this.k = colorForState;
        }
        if (this.l) {
            invalidateSelf();
        }
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f7615a.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f7615a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void a(float f) {
        if (this.f7616e != f) {
            this.f7616e = f;
            this.f7615a.setStrokeWidth(f * 1.3333f);
            this.l = true;
            invalidateSelf();
        }
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.k = colorStateList.getColorForState(getState(), this.k);
        }
        this.j = colorStateList;
        this.l = true;
        invalidateSelf();
    }

    public final Shader a() {
        copyBounds(this.b);
        float fHeight = this.f7616e / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{n8.b(this.f, this.k), n8.b(this.g, this.k), n8.b(n8.c(this.g, 0), this.k), n8.b(n8.c(this.i, 0), this.k), n8.b(this.i, this.k), n8.b(this.h, this.k)}, new float[]{0.0f, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP);
    }
}
