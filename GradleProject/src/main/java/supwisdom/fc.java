package supwisdom;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class fc implements View.OnTouchListener {
    public static final int r = ViewConfiguration.getTapTimeout();
    public final View c;
    public Runnable d;
    public int g;
    public int h;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f7583a = new a();
    public final Interpolator b = new AccelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f7584e = {0.0f, 0.0f};
    public float[] f = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] i = {0.0f, 0.0f};
    public float[] j = {0.0f, 0.0f};
    public float[] k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7585a;
        public int b;
        public float c;
        public float d;
        public float j;
        public int k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f7586e = Long.MIN_VALUE;
        public long i = -1;
        public long f = 0;
        public int g = 0;
        public int h = 0;

        public final float a(float f) {
            return ((-4.0f) * f * f) + (f * 4.0f);
        }

        public void a(int i) {
            this.b = i;
        }

        public void b(int i) {
            this.f7585a = i;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            float f = this.c;
            return (int) (f / Math.abs(f));
        }

        public int e() {
            float f = this.d;
            return (int) (f / Math.abs(f));
        }

        public boolean f() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void g() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = fc.a((int) (jCurrentAnimationTimeMillis - this.f7586e), 0, this.b);
            this.j = a(jCurrentAnimationTimeMillis);
            this.i = jCurrentAnimationTimeMillis;
        }

        public void h() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f7586e = jCurrentAnimationTimeMillis;
            this.i = -1L;
            this.f = jCurrentAnimationTimeMillis;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        public final float a(long j) {
            if (j < this.f7586e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 < 0 || j < j2) {
                return fc.a((j - this.f7586e) / this.f7585a, 0.0f, 1.0f) * 0.5f;
            }
            long j3 = j - j2;
            float f = this.j;
            return (1.0f - f) + (f * fc.a(j3 / this.k, 0.0f, 1.0f));
        }

        public int b() {
            return this.g;
        }

        public void a() {
            if (this.f != 0) {
                long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float fA = a(a(jCurrentAnimationTimeMillis));
                long j = jCurrentAnimationTimeMillis - this.f;
                this.f = jCurrentAnimationTimeMillis;
                float f = j * fA;
                this.g = (int) (this.c * f);
                this.h = (int) (f * this.d);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public void a(float f, float f2) {
            this.c = f;
            this.d = f2;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fc fcVar = fc.this;
            if (fcVar.o) {
                if (fcVar.m) {
                    fcVar.m = false;
                    fcVar.f7583a.h();
                }
                a aVar = fc.this.f7583a;
                if (aVar.f() || !fc.this.c()) {
                    fc.this.o = false;
                    return;
                }
                fc fcVar2 = fc.this;
                if (fcVar2.n) {
                    fcVar2.n = false;
                    fcVar2.a();
                }
                aVar.a();
                fc.this.a(aVar.b(), aVar.c());
                lb.a(fc.this.c, this);
            }
        }
    }

    public fc(View view) {
        this.c = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = (int) ((1575.0f * f) + 0.5f);
        c(f2, f2);
        float f3 = (int) ((f * 315.0f) + 0.5f);
        d(f3, f3);
        d(1);
        b(Float.MAX_VALUE, Float.MAX_VALUE);
        e(0.2f, 0.2f);
        f(1.0f, 1.0f);
        c(r);
        f(500);
        e(500);
    }

    public static float a(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    public static int a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public fc a(boolean z) {
        if (this.p && !z) {
            b();
        }
        this.p = z;
        return this;
    }

    public abstract void a(int i, int i2);

    public abstract boolean a(int i);

    public fc b(float f, float f2) {
        float[] fArr = this.f;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    public abstract boolean b(int i);

    public fc c(float f, float f2) {
        float[] fArr = this.k;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    public fc d(float f, float f2) {
        float[] fArr = this.j;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    public fc e(float f, float f2) {
        float[] fArr = this.f7584e;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    public fc f(float f, float f2) {
        float[] fArr = this.i;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.p
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.b()
            goto L58
        L1a:
            r5.n = r2
            r5.l = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.c
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.c
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.a(r2, r7, r6, r3)
            supwisdom.fc$a r7 = r5.f7583a
            r7.a(r0, r6)
            boolean r6 = r5.o
            if (r6 != 0) goto L58
            boolean r6 = r5.c()
            if (r6 == 0) goto L58
            r5.d()
        L58:
            boolean r6 = r5.q
            if (r6 == 0) goto L61
            boolean r6 = r5.o
            if (r6 == 0) goto L61
            r1 = 1
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fc.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void b() {
        if (this.m) {
            this.o = false;
        } else {
            this.f7583a.g();
        }
    }

    public fc c(int i) {
        this.h = i;
        return this;
    }

    public fc d(int i) {
        this.g = i;
        return this;
    }

    public fc e(int i) {
        this.f7583a.a(i);
        return this;
    }

    public fc f(int i) {
        this.f7583a.b(i);
        return this;
    }

    public final float a(int i, float f, float f2, float f3) {
        float fA = a(this.f7584e[i], f2, this.f[i], f);
        if (fA == 0.0f) {
            return 0.0f;
        }
        float f4 = this.i[i];
        float f5 = this.j[i];
        float f6 = this.k[i];
        float f7 = f4 * f3;
        if (fA > 0.0f) {
            return a(fA * f7, f5, f6);
        }
        return -a((-fA) * f7, f5, f6);
    }

    public boolean c() {
        a aVar = this.f7583a;
        int iE = aVar.e();
        int iD = aVar.d();
        return (iE != 0 && b(iE)) || (iD != 0 && a(iD));
    }

    public final void d() {
        int i;
        if (this.d == null) {
            this.d = new b();
        }
        this.o = true;
        this.m = true;
        if (!this.l && (i = this.h) > 0) {
            lb.a(this.c, this.d, i);
        } else {
            this.d.run();
        }
        this.l = true;
    }

    public final float a(float f, float f2, float f3, float f4) {
        float interpolation;
        float fA = a(f * f2, 0.0f, f3);
        float fA2 = a(f2 - f4, fA) - a(f4, fA);
        if (fA2 < 0.0f) {
            interpolation = -this.b.getInterpolation(-fA2);
        } else {
            if (fA2 <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.b.getInterpolation(fA2);
        }
        return a(interpolation, -1.0f, 1.0f);
    }

    public final float a(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.g;
        if (i == 0 || i == 1) {
            if (f < f2) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.o && this.g == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    public void a() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.c.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }
}
