package supwisdom;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class an0 extends Drawable implements z8 {
    public cn0 m;
    public PorterDuffColorFilter x;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f6963a = new Paint();
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final bn0[] d = new bn0[4];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Matrix f6964e = new Matrix();
    public final Path f = new Path();
    public final PointF g = new PointF();
    public final bn0 h = new bn0();
    public final Region i = new Region();
    public final Region j = new Region();
    public final float[] k = new float[2];
    public final float[] l = new float[2];
    public boolean n = false;
    public boolean o = false;
    public float p = 1.0f;
    public int q = -16777216;
    public int r = 5;
    public int s = 10;
    public int t = 255;
    public float u = 1.0f;
    public float v = 0.0f;
    public Paint.Style w = Paint.Style.FILL_AND_STROKE;
    public PorterDuff.Mode y = PorterDuff.Mode.SRC_IN;
    public ColorStateList z = null;

    public an0(cn0 cn0Var) {
        this.m = null;
        this.m = cn0Var;
        for (int i = 0; i < 4; i++) {
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
            this.d[i] = new bn0();
        }
    }

    public static int a(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    public void a(boolean z) {
        this.n = z;
        invalidateSelf();
    }

    public ColorStateList b() {
        return this.z;
    }

    public final void c(int i, int i2, int i3) {
        a(i, i2, i3, this.g);
        a(i).a(a(i, i2, i3), this.p, this.d[i]);
        float fB = b(((i - 1) + 4) % 4, i2, i3) + 1.5707964f;
        this.b[i].reset();
        Matrix matrix = this.b[i];
        PointF pointF = this.g;
        matrix.setTranslate(pointF.x, pointF.y);
        this.b[i].preRotate((float) Math.toDegrees(fB));
    }

    public final void d(int i, int i2, int i3) {
        float[] fArr = this.k;
        bn0[] bn0VarArr = this.d;
        fArr[0] = bn0VarArr[i].c;
        fArr[1] = bn0VarArr[i].d;
        this.b[i].mapPoints(fArr);
        float fB = b(i, i2, i3);
        this.c[i].reset();
        Matrix matrix = this.c[i];
        float[] fArr2 = this.k;
        matrix.setTranslate(fArr2[0], fArr2[1]);
        this.c[i].preRotate((float) Math.toDegrees(fB));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f6963a.setColorFilter(this.x);
        int alpha = this.f6963a.getAlpha();
        this.f6963a.setAlpha(a(alpha, this.t));
        this.f6963a.setStrokeWidth(this.v);
        this.f6963a.setStyle(this.w);
        int i = this.r;
        if (i > 0 && this.n) {
            this.f6963a.setShadowLayer(this.s, 0.0f, i, this.q);
        }
        if (this.m != null) {
            a(canvas.getWidth(), canvas.getHeight(), this.f);
            canvas.drawPath(this.f, this.f6963a);
        } else {
            canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.f6963a);
        }
        this.f6963a.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Rect bounds = getBounds();
        this.i.set(bounds);
        a(bounds.width(), bounds.height(), this.f);
        this.j.setPath(this.f, this.i);
        this.i.op(this.j, Region.Op.DIFFERENCE);
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.t = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f6963a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintList(ColorStateList colorStateList) {
        this.z = colorStateList;
        c();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintMode(PorterDuff.Mode mode) {
        this.y = mode;
        c();
        invalidateSelf();
    }

    public void b(int i, int i2, Path path) {
        path.rewind();
        if (this.m == null) {
            return;
        }
        for (int i3 = 0; i3 < 4; i3++) {
            c(i3, i, i2);
            d(i3, i, i2);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            a(i4, path);
            b(i4, path);
        }
        path.close();
    }

    public float a() {
        return this.p;
    }

    public void a(float f) {
        this.p = f;
        invalidateSelf();
    }

    public void a(Paint.Style style) {
        this.w = style;
        invalidateSelf();
    }

    public final void a(int i, Path path) {
        float[] fArr = this.k;
        bn0[] bn0VarArr = this.d;
        fArr[0] = bn0VarArr[i].f7079a;
        fArr[1] = bn0VarArr[i].b;
        this.b[i].mapPoints(fArr);
        if (i == 0) {
            float[] fArr2 = this.k;
            path.moveTo(fArr2[0], fArr2[1]);
        } else {
            float[] fArr3 = this.k;
            path.lineTo(fArr3[0], fArr3[1]);
        }
        this.d[i].a(this.b[i], path);
    }

    public final void c() {
        ColorStateList colorStateList = this.z;
        if (colorStateList != null && this.y != null) {
            int colorForState = colorStateList.getColorForState(getState(), 0);
            this.x = new PorterDuffColorFilter(colorForState, this.y);
            if (this.o) {
                this.q = colorForState;
                return;
            }
            return;
        }
        this.x = null;
    }

    public final void b(int i, Path path) {
        int i2 = (i + 1) % 4;
        float[] fArr = this.k;
        bn0[] bn0VarArr = this.d;
        fArr[0] = bn0VarArr[i].c;
        fArr[1] = bn0VarArr[i].d;
        this.b[i].mapPoints(fArr);
        float[] fArr2 = this.l;
        bn0[] bn0VarArr2 = this.d;
        fArr2[0] = bn0VarArr2[i2].f7079a;
        fArr2[1] = bn0VarArr2[i2].b;
        this.b[i2].mapPoints(fArr2);
        float f = this.k[0];
        float[] fArr3 = this.l;
        float fHypot = (float) Math.hypot(f - fArr3[0], r0[1] - fArr3[1]);
        this.h.b(0.0f, 0.0f);
        b(i).a(fHypot, this.p, this.h);
        this.h.a(this.c[i], path);
    }

    public final ym0 a(int i) {
        if (i == 1) {
            return this.m.h();
        }
        if (i == 2) {
            return this.m.c();
        }
        if (i != 3) {
            return this.m.g();
        }
        return this.m.b();
    }

    public final void a(int i, int i2, int i3, PointF pointF) {
        if (i == 1) {
            pointF.set(i2, 0.0f);
            return;
        }
        if (i == 2) {
            pointF.set(i2, i3);
        } else if (i != 3) {
            pointF.set(0.0f, 0.0f);
        } else {
            pointF.set(0.0f, i3);
        }
    }

    public final zm0 b(int i) {
        if (i == 1) {
            return this.m.e();
        }
        if (i == 2) {
            return this.m.a();
        }
        if (i != 3) {
            return this.m.f();
        }
        return this.m.d();
    }

    public final float a(int i, int i2, int i3) {
        a(((i - 1) + 4) % 4, i2, i3, this.g);
        PointF pointF = this.g;
        float f = pointF.x;
        float f2 = pointF.y;
        a((i + 1) % 4, i2, i3, pointF);
        PointF pointF2 = this.g;
        float f3 = pointF2.x;
        float f4 = pointF2.y;
        a(i, i2, i3, pointF2);
        PointF pointF3 = this.g;
        float f5 = pointF3.x;
        float f6 = pointF3.y;
        float fAtan2 = ((float) Math.atan2(f2 - f6, f - f5)) - ((float) Math.atan2(f4 - f6, f3 - f5));
        return fAtan2 < 0.0f ? (float) (((double) fAtan2) + 6.283185307179586d) : fAtan2;
    }

    public final float b(int i, int i2, int i3) {
        int i4 = (i + 1) % 4;
        a(i, i2, i3, this.g);
        PointF pointF = this.g;
        float f = pointF.x;
        float f2 = pointF.y;
        a(i4, i2, i3, pointF);
        PointF pointF2 = this.g;
        return (float) Math.atan2(pointF2.y - f2, pointF2.x - f);
    }

    public final void a(int i, int i2, Path path) {
        b(i, i2, path);
        if (this.u == 1.0f) {
            return;
        }
        this.f6964e.reset();
        Matrix matrix = this.f6964e;
        float f = this.u;
        matrix.setScale(f, f, i / 2, i2 / 2);
        path.transform(this.f6964e);
    }
}
