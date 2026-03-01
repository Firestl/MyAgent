package supwisdom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: compiled from: CircularProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class xf extends Drawable implements Animatable {
    public static final Interpolator g = new LinearInterpolator();
    public static final Interpolator h = new od();
    public static final int[] i = {-16777216};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f9756a;
    public float b;
    public Resources c;
    public Animator d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9757e;
    public boolean f;

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f9758a;

        public a(c cVar) {
            this.f9758a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            xf.this.b(fFloatValue, this.f9758a);
            xf.this.a(fFloatValue, this.f9758a, false);
            xf.this.invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public class b implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f9759a;

        public b(c cVar) {
            this.f9759a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            xf.this.a(1.0f, this.f9759a, true);
            this.f9759a.l();
            this.f9759a.j();
            xf xfVar = xf.this;
            if (!xfVar.f) {
                xfVar.f9757e += 1.0f;
                return;
            }
            xfVar.f = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.f9759a.a(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            xf.this.f9757e = 0.0f;
        }
    }

    /* JADX INFO: compiled from: CircularProgressDrawable.java */
    public static class c {
        public int[] i;
        public int j;
        public float k;
        public float l;
        public float m;
        public boolean n;
        public Path o;
        public float q;
        public int r;
        public int s;
        public int u;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final RectF f9760a = new RectF();
        public final Paint b = new Paint();
        public final Paint c = new Paint();
        public final Paint d = new Paint();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f9761e = 0.0f;
        public float f = 0.0f;
        public float g = 0.0f;
        public float h = 5.0f;
        public float p = 1.0f;
        public int t = 255;

        public c() {
            this.b.setStrokeCap(Paint.Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Paint.Style.STROKE);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setAntiAlias(true);
            this.d.setColor(0);
        }

        public void a(float f, float f2) {
            this.r = (int) f;
            this.s = (int) f2;
        }

        public void b(int i) {
            this.u = i;
        }

        public void c(int i) {
            this.j = i;
            this.u = this.i[i];
        }

        public int d() {
            return (this.j + 1) % this.i.length;
        }

        public void e(float f) {
            this.f9761e = f;
        }

        public void f(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
        }

        public float g() {
            return this.l;
        }

        public float h() {
            return this.m;
        }

        public float i() {
            return this.k;
        }

        public void j() {
            c(d());
        }

        public void k() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            e(0.0f);
            c(0.0f);
            d(0.0f);
        }

        public void l() {
            this.k = this.f9761e;
            this.l = this.f;
            this.m = this.g;
        }

        public float b() {
            return this.f;
        }

        public void d(float f) {
            this.g = f;
        }

        public float e() {
            return this.f9761e;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f9760a;
            float f = this.q;
            float fMin = (this.h / 2.0f) + f;
            if (f <= 0.0f) {
                fMin = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - fMin, rect.centerY() - fMin, rect.centerX() + fMin, rect.centerY() + fMin);
            float f2 = this.f9761e;
            float f3 = this.g;
            float f4 = (f2 + f3) * 360.0f;
            float f5 = ((this.f + f3) * 360.0f) - f4;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            float f6 = this.h / 2.0f;
            rectF.inset(f6, f6);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.d);
            float f7 = -f6;
            rectF.inset(f7, f7);
            canvas.drawArc(rectF, f4, f5, false, this.b);
            a(canvas, f4, f5, rectF);
        }

        public void b(float f) {
            this.q = f;
        }

        public int c() {
            return this.i[d()];
        }

        public int f() {
            return this.i[this.j];
        }

        public void c(float f) {
            this.f = f;
        }

        public void a(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    Path path2 = new Path();
                    this.o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float fMin = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f3 = (this.r * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path3 = this.o;
                float f4 = this.r;
                float f5 = this.p;
                path3.lineTo((f4 * f5) / 2.0f, this.s * f5);
                this.o.offset((fMin + rectF.centerX()) - f3, rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.c.setColor(this.u);
                this.c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.c);
                canvas.restore();
            }
        }

        public void a(int[] iArr) {
            this.i = iArr;
            c(0);
        }

        public void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        public void a(int i) {
            this.t = i;
        }

        public int a() {
            return this.t;
        }

        public void a(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        public void a(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }
    }

    public xf(Context context) {
        na.a(context);
        this.c = context.getResources();
        c cVar = new c();
        this.f9756a = cVar;
        cVar.a(i);
        d(2.5f);
        a();
    }

    public final int a(float f, int i2, int i3) {
        return ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r0) * f))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r1) * f))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r2) * f))) << 8) | ((i2 & 255) + ((int) (f * ((i3 & 255) - r8))));
    }

    public final void a(float f, float f2, float f3, float f4) {
        c cVar = this.f9756a;
        float f5 = this.c.getDisplayMetrics().density;
        cVar.f(f2 * f5);
        cVar.b(f * f5);
        cVar.c(0);
        cVar.a(f3 * f5, f4 * f5);
    }

    public void b(float f) {
        this.f9756a.d(f);
        invalidateSelf();
    }

    public final void c(float f) {
        this.b = f;
    }

    public void d(float f) {
        this.f9756a.f(f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.b, bounds.exactCenterX(), bounds.exactCenterY());
        this.f9756a.a(canvas, bounds);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f9756a.a();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.d.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f9756a.a(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f9756a.a(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.d.cancel();
        this.f9756a.l();
        if (this.f9756a.b() != this.f9756a.e()) {
            this.f = true;
            this.d.setDuration(666L);
            this.d.start();
        } else {
            this.f9756a.c(0);
            this.f9756a.k();
            this.d.setDuration(1332L);
            this.d.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.d.cancel();
        c(0.0f);
        this.f9756a.a(false);
        this.f9756a.c(0);
        this.f9756a.k();
        invalidateSelf();
    }

    public void b(float f, c cVar) {
        if (f > 0.75f) {
            cVar.b(a((f - 0.75f) / 0.25f, cVar.f(), cVar.c()));
        } else {
            cVar.b(cVar.f());
        }
    }

    public void a(int i2) {
        if (i2 == 0) {
            a(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            a(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public void a(boolean z) {
        this.f9756a.a(z);
        invalidateSelf();
    }

    public void a(float f) {
        this.f9756a.a(f);
        invalidateSelf();
    }

    public void a(float f, float f2) {
        this.f9756a.e(f);
        this.f9756a.c(f2);
        invalidateSelf();
    }

    public void a(int... iArr) {
        this.f9756a.a(iArr);
        this.f9756a.c(0);
        invalidateSelf();
    }

    public final void a(float f, c cVar) {
        b(f, cVar);
        float fFloor = (float) (Math.floor(cVar.h() / 0.8f) + 1.0d);
        cVar.e(cVar.i() + (((cVar.g() - 0.01f) - cVar.i()) * f));
        cVar.c(cVar.g());
        cVar.d(cVar.h() + ((fFloor - cVar.h()) * f));
    }

    public void a(float f, c cVar, boolean z) {
        float interpolation;
        float interpolation2;
        if (this.f) {
            a(f, cVar);
            return;
        }
        if (f != 1.0f || z) {
            float fH = cVar.h();
            if (f < 0.5f) {
                interpolation = cVar.i();
                interpolation2 = (h.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float fI = cVar.i() + 0.79f;
                interpolation = fI - (((1.0f - h.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = fI;
            }
            float f2 = fH + (0.20999998f * f);
            float f3 = (f + this.f9757e) * 216.0f;
            cVar.e(interpolation);
            cVar.c(interpolation2);
            cVar.d(f2);
            c(f3);
        }
    }

    public final void a() {
        c cVar = this.f9756a;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new a(cVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(g);
        valueAnimatorOfFloat.addListener(new b(cVar));
        this.d = valueAnimatorOfFloat;
    }
}
