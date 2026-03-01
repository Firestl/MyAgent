package supwisdom;

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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class wu extends Drawable implements Animatable {
    public static final Interpolator k = new LinearInterpolator();
    public static final Interpolator l = new su();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<Animation> f9657a = new ArrayList<>();
    public final d b;
    public float c;
    public Resources d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f9658e;
    public Animation f;
    public float g;
    public double h;
    public double i;
    public boolean j;

    public class a extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f9659a;

        public a(d dVar) {
            this.f9659a = dVar;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            wu wuVar = wu.this;
            if (wuVar.j) {
                wuVar.a(f, this.f9659a);
                return;
            }
            float fA = wuVar.a(this.f9659a);
            float fH = this.f9659a.h();
            float fJ = this.f9659a.j();
            float fI = this.f9659a.i();
            wu.this.b(f, this.f9659a);
            if (f <= 0.5f) {
                this.f9659a.d(fJ + ((0.8f - fA) * wu.l.getInterpolation(f / 0.5f)));
            }
            if (f > 0.5f) {
                this.f9659a.b(fH + ((0.8f - fA) * wu.l.getInterpolation((f - 0.5f) / 0.5f)));
            }
            this.f9659a.c(fI + (0.25f * f));
            wu.this.c((f * 216.0f) + ((wu.this.g / 5.0f) * 1080.0f));
        }
    }

    public class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f9660a;

        public b(d dVar) {
            this.f9660a = dVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            this.f9660a.o();
            this.f9660a.l();
            d dVar = this.f9660a;
            dVar.d(dVar.c());
            wu wuVar = wu.this;
            if (!wuVar.j) {
                wuVar.g = (wuVar.g + 1.0f) % 5.0f;
                return;
            }
            wuVar.j = false;
            animation.setDuration(1332L);
            this.f9660a.a(false);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            wu.this.g = 0.0f;
        }
    }

    public class c implements Drawable.Callback {
        public c() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            wu.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            wu.this.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            wu.this.unscheduleSelf(runnable);
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final RectF f9662a = new RectF();
        public final Paint b;
        public final Paint c;
        public final Drawable.Callback d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f9663e;
        public float f;
        public float g;
        public float h;
        public float i;
        public int[] j;
        public int k;
        public float l;
        public float m;
        public float n;
        public boolean o;
        public Path p;
        public float q;
        public double r;
        public int s;
        public int t;
        public int u;
        public final Paint v;
        public int w;
        public int x;

        public d(Drawable.Callback callback) {
            Paint paint = new Paint();
            this.b = paint;
            Paint paint2 = new Paint();
            this.c = paint2;
            this.f9663e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 5.0f;
            this.i = 2.5f;
            this.v = new Paint(1);
            this.d = callback;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f9662a;
            rectF.set(rect);
            float f = this.i;
            rectF.inset(f, f);
            float f2 = this.f9663e;
            float f3 = this.g;
            float f4 = (f2 + f3) * 360.0f;
            float f5 = ((this.f + f3) * 360.0f) - f4;
            this.b.setColor(this.x);
            canvas.drawArc(rectF, f4, f5, false, this.b);
            a(canvas, f4, f5, rect);
            if (this.u < 255) {
                this.v.setColor(this.w);
                this.v.setAlpha(255 - this.u);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), rect.width() / 2, this.v);
            }
        }

        public double b() {
            return this.r;
        }

        public float c() {
            return this.f;
        }

        public int d() {
            return this.j[e()];
        }

        public final int e() {
            return (this.k + 1) % this.j.length;
        }

        public float f() {
            return this.f9663e;
        }

        public int g() {
            return this.j[this.k];
        }

        public float h() {
            return this.m;
        }

        public float i() {
            return this.n;
        }

        public float j() {
            return this.l;
        }

        public float k() {
            return this.h;
        }

        public void l() {
            d(e());
        }

        public final void m() {
            this.d.invalidateDrawable(null);
        }

        public void n() {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 0.0f;
            d(0.0f);
            b(0.0f);
            c(0.0f);
        }

        public void o() {
            this.l = this.f9663e;
            this.m = this.f;
            this.n = this.g;
        }

        public void b(int i) {
            this.w = i;
        }

        public void c(int i) {
            this.x = i;
        }

        public void d(int i) {
            this.k = i;
            this.x = this.j[i];
        }

        public void e(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
            m();
        }

        public void b(float f) {
            this.f = f;
            m();
        }

        public void c(float f) {
            this.g = f;
            m();
        }

        public void d(float f) {
            this.f9663e = f;
            m();
        }

        public final void a(Canvas canvas, float f, float f2, Rect rect) {
            if (this.o) {
                Path path = this.p;
                if (path == null) {
                    Path path2 = new Path();
                    this.p = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float f3 = (((int) this.i) / 2) * this.q;
                float fCos = (float) ((this.r * Math.cos(0.0d)) + ((double) rect.exactCenterX()));
                float fSin = (float) ((this.r * Math.sin(0.0d)) + ((double) rect.exactCenterY()));
                this.p.moveTo(0.0f, 0.0f);
                this.p.lineTo(this.s * this.q, 0.0f);
                Path path3 = this.p;
                float f4 = this.s;
                float f5 = this.q;
                path3.lineTo((f4 * f5) / 2.0f, this.t * f5);
                this.p.offset(fCos - f3, fSin);
                this.p.close();
                this.c.setColor(this.x);
                canvas.rotate((f + f2) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.p, this.c);
            }
        }

        public int a() {
            return this.u;
        }

        public void a(int i) {
            this.u = i;
        }

        public void a(float f, float f2) {
            this.s = (int) f;
            this.t = (int) f2;
        }

        public void a(float f) {
            if (f != this.q) {
                this.q = f;
                m();
            }
        }

        public void a(double d) {
            this.r = d;
        }

        public void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
            m();
        }

        public void a(int[] iArr) {
            this.j = iArr;
            d(0);
        }

        public void a(int i, int i2) {
            float fMin = Math.min(i, i2);
            double d = this.r;
            this.i = (float) ((d <= 0.0d || fMin < 0.0f) ? Math.ceil(this.h / 2.0f) : ((double) (fMin / 2.0f)) - d);
        }

        public void a(boolean z) {
            if (this.o != z) {
                this.o = z;
                m();
            }
        }
    }

    public wu(Context context, View view) {
        c cVar = new c();
        this.f9658e = view;
        this.d = context.getResources();
        d dVar = new d(cVar);
        this.b = dVar;
        dVar.a(new int[]{-16777216});
        b(1);
        a();
    }

    public void c(float f) {
        this.c = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int iSave = canvas.save();
        canvas.rotate(this.c, bounds.exactCenterX(), bounds.exactCenterY());
        this.b.a(canvas, bounds);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.b.a();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        ArrayList<Animation> arrayList = this.f9657a;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animation animation = arrayList.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.b.a(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.b.a(colorFilter);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f.reset();
        this.b.o();
        if (this.b.c() != this.b.f()) {
            this.j = true;
            this.f.setDuration(666L);
            this.f9658e.startAnimation(this.f);
        } else {
            this.b.d(0);
            this.b.n();
            this.f.setDuration(1332L);
            this.f9658e.startAnimation(this.f);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f9658e.clearAnimation();
        c(0.0f);
        this.b.a(false);
        this.b.d(0);
        this.b.n();
    }

    public void b(float f) {
        this.b.c(f);
    }

    public final void b(float f, d dVar) {
        if (f > 0.75f) {
            dVar.c(a((f - 0.75f) / 0.25f, dVar.g(), dVar.d()));
        }
    }

    public final void a(float f, d dVar) {
        b(f, dVar);
        float fFloor = (float) (Math.floor(dVar.i() / 0.8f) + 1.0d);
        dVar.d(dVar.j() + (((dVar.h() - a(dVar)) - dVar.j()) * f));
        dVar.b(dVar.h());
        dVar.c(dVar.i() + ((fFloor - dVar.i()) * f));
    }

    public void b(int i) {
        if (i == 0) {
            a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public final int a(float f, int i, int i2) {
        int iIntValue = Integer.valueOf(i).intValue();
        int i3 = (iIntValue >> 24) & 255;
        int i4 = (iIntValue >> 16) & 255;
        int i5 = (iIntValue >> 8) & 255;
        int i6 = iIntValue & 255;
        int iIntValue2 = Integer.valueOf(i2).intValue();
        return ((i3 + ((int) ((((iIntValue2 >> 24) & 255) - i3) * f))) << 24) | ((i4 + ((int) ((((iIntValue2 >> 16) & 255) - i4) * f))) << 16) | ((i5 + ((int) ((((iIntValue2 >> 8) & 255) - i5) * f))) << 8) | (i6 + ((int) (f * ((iIntValue2 & 255) - i6))));
    }

    public final float a(d dVar) {
        return (float) Math.toRadians(((double) dVar.k()) / (dVar.b() * 6.283185307179586d));
    }

    public void a(float f) {
        this.b.a(f);
    }

    public void a(int i) {
        this.b.b(i);
    }

    public void a(int... iArr) {
        this.b.a(iArr);
        this.b.d(0);
    }

    public final void a(double d2, double d3, double d4, double d5, float f, float f2) {
        d dVar = this.b;
        float f3 = this.d.getDisplayMetrics().density;
        double d6 = f3;
        this.h = d2 * d6;
        this.i = d3 * d6;
        dVar.e(((float) d5) * f3);
        dVar.a(d4 * d6);
        dVar.d(0);
        dVar.a(f * f3, f2 * f3);
        dVar.a((int) this.h, (int) this.i);
    }

    public void a(float f, float f2) {
        this.b.d(f);
        this.b.b(f2);
    }

    public final void a() {
        d dVar = this.b;
        a aVar = new a(dVar);
        aVar.setRepeatCount(-1);
        aVar.setRepeatMode(1);
        aVar.setInterpolator(k);
        aVar.setAnimationListener(new b(dVar));
        this.f = aVar;
    }

    public void a(boolean z) {
        this.b.a(z);
    }
}
