package supwisdom;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.OverScroller;

/* JADX INFO: loaded from: classes.dex */
public class nv implements View.OnTouchListener, hv, View.OnLayoutChangeListener {
    public static float B = 3.0f;
    public static float C = 1.75f;
    public static float D = 1.0f;
    public static int E = 200;
    public static int F = 1;
    public ImageView h;
    public GestureDetector i;
    public gv j;
    public iv p;
    public kv q;
    public jv r;
    public View.OnClickListener s;
    public View.OnLongClickListener t;
    public lv u;
    public mv v;
    public e w;
    public float y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Interpolator f8572a = new AccelerateDecelerateInterpolator();
    public int b = E;
    public float c = D;
    public float d = C;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8573e = B;
    public boolean f = true;
    public boolean g = false;
    public final Matrix k = new Matrix();
    public final Matrix l = new Matrix();
    public final Matrix m = new Matrix();
    public final RectF n = new RectF();
    public final float[] o = new float[9];
    public int x = 2;
    public boolean z = true;
    public ImageView.ScaleType A = ImageView.ScaleType.FIT_CENTER;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (nv.this.v == null || nv.this.j() > nv.D || xa.b(motionEvent) > nv.F || xa.b(motionEvent2) > nv.F) {
                return false;
            }
            return nv.this.v.onFling(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (nv.this.t != null) {
                nv.this.t.onLongClick(nv.this.h);
            }
        }
    }

    public class b implements GestureDetector.OnDoubleTapListener {
        public b() {
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            try {
                float fJ = nv.this.j();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (fJ < nv.this.h()) {
                    nv nvVar = nv.this;
                    nvVar.a(nvVar.h(), x, y, true);
                } else if (fJ < nv.this.h() || fJ >= nv.this.g()) {
                    nv nvVar2 = nv.this;
                    nvVar2.a(nvVar2.i(), x, y, true);
                } else {
                    nv nvVar3 = nv.this;
                    nvVar3.a(nvVar3.g(), x, y, true);
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
            return true;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (nv.this.s != null) {
                nv.this.s.onClick(nv.this.h);
            }
            RectF rectFD = nv.this.d();
            if (rectFD == null) {
                return false;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (!rectFD.contains(x, y)) {
                if (nv.this.r == null) {
                    return false;
                }
                nv.this.r.a(nv.this.h);
                return false;
            }
            float fWidth = (x - rectFD.left) / rectFD.width();
            float fHeight = (y - rectFD.top) / rectFD.height();
            if (nv.this.q == null) {
                return true;
            }
            nv.this.q.onPhotoTap(nv.this.h, fWidth, fHeight);
            return true;
        }
    }

    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8576a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            f8576a = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8576a[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8576a[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8576a[ImageView.ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f8577a;
        public final float b;
        public final long c = System.currentTimeMillis();
        public final float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f8578e;

        public d(float f, float f2, float f3, float f4) {
            this.f8577a = f3;
            this.b = f4;
            this.d = f;
            this.f8578e = f2;
        }

        public final float a() {
            return nv.this.f8572a.getInterpolation(Math.min(1.0f, ((System.currentTimeMillis() - this.c) * 1.0f) / nv.this.b));
        }

        @Override // java.lang.Runnable
        public void run() {
            float fA = a();
            float f = this.d;
            nv.this.a((f + ((this.f8578e - f) * fA)) / nv.this.j(), this.f8577a, this.b);
            if (fA < 1.0f) {
                fv.a(nv.this.h, this);
            }
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final OverScroller f8579a;
        public int b;
        public int c;

        public e(Context context) {
            this.f8579a = new OverScroller(context);
        }

        public void a() {
            this.f8579a.forceFinished(true);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f8579a.isFinished() && this.f8579a.computeScrollOffset()) {
                int currX = this.f8579a.getCurrX();
                int currY = this.f8579a.getCurrY();
                nv.this.m.postTranslate(this.b - currX, this.c - currY);
                nv nvVar = nv.this;
                nvVar.b(nvVar.e());
                this.b = currX;
                this.c = currY;
                fv.a(nv.this.h, this);
            }
        }

        public void a(int i, int i2, int i3, int i4) {
            int i5;
            int iRound;
            int i6;
            int iRound2;
            RectF rectFD = nv.this.d();
            if (rectFD == null) {
                return;
            }
            int iRound3 = Math.round(-rectFD.left);
            float f = i;
            if (f < rectFD.width()) {
                iRound = Math.round(rectFD.width() - f);
                i5 = 0;
            } else {
                i5 = iRound3;
                iRound = i5;
            }
            int iRound4 = Math.round(-rectFD.top);
            float f2 = i2;
            if (f2 < rectFD.height()) {
                iRound2 = Math.round(rectFD.height() - f2);
                i6 = 0;
            } else {
                i6 = iRound4;
                iRound2 = i6;
            }
            this.b = iRound3;
            this.c = iRound4;
            if (iRound3 == iRound && iRound4 == iRound2) {
                return;
            }
            this.f8579a.fling(iRound3, iRound4, i3, i4, i5, iRound, i6, iRound2, 0, 0);
        }
    }

    public nv(ImageView imageView) {
        this.h = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (imageView.isInEditMode()) {
            return;
        }
        this.y = 0.0f;
        this.j = new gv(imageView.getContext(), this);
        GestureDetector gestureDetector = new GestureDetector(imageView.getContext(), new a());
        this.i = gestureDetector;
        gestureDetector.setOnDoubleTapListener(new b());
    }

    public ImageView.ScaleType k() {
        return this.A;
    }

    public final void l() {
        this.m.reset();
        d(this.y);
        b(e());
        c();
    }

    public void m() {
        if (this.z) {
            a(this.h.getDrawable());
        } else {
            l();
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        a(this.h.getDrawable());
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            r10 = this;
            boolean r0 = r10.z
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L95
            r0 = r11
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            boolean r0 = supwisdom.ov.a(r0)
            if (r0 == 0) goto L95
            int r0 = r12.getAction()
            if (r0 == 0) goto L45
            if (r0 == r2) goto L1b
            r3 = 3
            if (r0 == r3) goto L1b
            goto L51
        L1b:
            float r0 = r10.j()
            float r3 = r10.c
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L51
            android.graphics.RectF r0 = r10.d()
            if (r0 == 0) goto L51
            supwisdom.nv$d r9 = new supwisdom.nv$d
            float r5 = r10.j()
            float r6 = r10.c
            float r7 = r0.centerX()
            float r8 = r0.centerY()
            r3 = r9
            r4 = r10
            r3.<init>(r5, r6, r7, r8)
            r11.post(r9)
            r11 = 1
            goto L52
        L45:
            android.view.ViewParent r11 = r11.getParent()
            if (r11 == 0) goto L4e
            r11.requestDisallowInterceptTouchEvent(r2)
        L4e:
            r10.a()
        L51:
            r11 = 0
        L52:
            supwisdom.gv r0 = r10.j
            if (r0 == 0) goto L89
            boolean r11 = r0.b()
            supwisdom.gv r0 = r10.j
            boolean r0 = r0.a()
            supwisdom.gv r3 = r10.j
            boolean r3 = r3.c(r12)
            if (r11 != 0) goto L72
            supwisdom.gv r11 = r10.j
            boolean r11 = r11.b()
            if (r11 != 0) goto L72
            r11 = 1
            goto L73
        L72:
            r11 = 0
        L73:
            if (r0 != 0) goto L7f
            supwisdom.gv r0 = r10.j
            boolean r0 = r0.a()
            if (r0 != 0) goto L7f
            r0 = 1
            goto L80
        L7f:
            r0 = 0
        L80:
            if (r11 == 0) goto L85
            if (r0 == 0) goto L85
            r1 = 1
        L85:
            r10.g = r1
            r1 = r3
            goto L8a
        L89:
            r1 = r11
        L8a:
            android.view.GestureDetector r11 = r10.i
            if (r11 == 0) goto L95
            boolean r11 = r11.onTouchEvent(r12)
            if (r11 == 0) goto L95
            r1 = 1
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.nv.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void b() {
        if (c()) {
            b(e());
        }
    }

    public final boolean c() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        RectF rectFA = a(e());
        if (rectFA == null) {
            return false;
        }
        float fHeight = rectFA.height();
        float fWidth = rectFA.width();
        float fA = a(this.h);
        float f6 = 0.0f;
        if (fHeight <= fA) {
            int i = c.f8576a[this.A.ordinal()];
            if (i != 2) {
                if (i != 3) {
                    fA = (fA - fHeight) / 2.0f;
                    f2 = rectFA.top;
                } else {
                    fA -= fHeight;
                    f2 = rectFA.top;
                }
            } else {
                f = rectFA.top;
                f3 = -f;
            }
        } else {
            f = rectFA.top;
            if (f > 0.0f) {
                f3 = -f;
            } else {
                f2 = rectFA.bottom;
                f3 = f2 < fA ? fA - f2 : 0.0f;
            }
        }
        float fB = b(this.h);
        if (fWidth <= fB) {
            int i2 = c.f8576a[this.A.ordinal()];
            if (i2 != 2) {
                if (i2 != 3) {
                    f4 = (fB - fWidth) / 2.0f;
                    f5 = rectFA.left;
                } else {
                    f4 = fB - fWidth;
                    f5 = rectFA.left;
                }
                f6 = f4 - f5;
            } else {
                f6 = -rectFA.left;
            }
            this.x = 2;
        } else {
            float f7 = rectFA.left;
            if (f7 > 0.0f) {
                this.x = 0;
                f6 = -f7;
            } else {
                float f8 = rectFA.right;
                if (f8 < fB) {
                    f6 = fB - f8;
                    this.x = 1;
                } else {
                    this.x = -1;
                }
            }
        }
        this.m.postTranslate(f6, f3);
        return true;
    }

    public RectF d() {
        c();
        return a(e());
    }

    public final Matrix e() {
        this.l.set(this.k);
        this.l.postConcat(this.m);
        return this.l;
    }

    public Matrix f() {
        return this.l;
    }

    public float g() {
        return this.f8573e;
    }

    public float h() {
        return this.d;
    }

    public float i() {
        return this.c;
    }

    public float j() {
        return (float) Math.sqrt(((float) Math.pow(a(this.m, 0), 2.0d)) + ((float) Math.pow(a(this.m, 3), 2.0d)));
    }

    public final void a() {
        e eVar = this.w;
        if (eVar != null) {
            eVar.a();
            this.w = null;
        }
    }

    public void f(float f) {
        a(f, false);
    }

    public final int b(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    public void d(float f) {
        this.m.postRotate(f % 360.0f);
        b();
    }

    public final void b(Matrix matrix) {
        RectF rectFA;
        this.h.setImageMatrix(matrix);
        if (this.p == null || (rectFA = a(matrix)) == null) {
            return;
        }
        this.p.a(rectFA);
    }

    public void e(float f) {
        this.m.setRotate(f % 360.0f);
        b();
    }

    public final int a(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    public final float a(Matrix matrix, int i) {
        matrix.getValues(this.o);
        return this.o[i];
    }

    @Override // supwisdom.hv
    public void a(float f, float f2) {
        if (this.j.b()) {
            return;
        }
        this.m.postTranslate(f, f2);
        b();
        ViewParent parent = this.h.getParent();
        if (!this.f || this.j.b() || this.g) {
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
                return;
            }
            return;
        }
        int i = this.x;
        if ((i == 2 || ((i == 0 && f >= 1.0f) || (i == 1 && f <= -1.0f))) && parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    public void b(float f) {
        ov.a(this.c, f, this.f8573e);
        this.d = f;
    }

    public void b(boolean z) {
        this.z = z;
        m();
    }

    @Override // supwisdom.hv
    public void a(float f, float f2, float f3, float f4) {
        e eVar = new e(this.h.getContext());
        this.w = eVar;
        eVar.a(b(this.h), a(this.h), (int) f3, (int) f4);
        this.h.post(this.w);
    }

    @Override // supwisdom.hv
    public void a(float f, float f2, float f3) {
        if (j() < this.f8573e || f < 1.0f) {
            if (j() > this.c || f > 1.0f) {
                lv lvVar = this.u;
                if (lvVar != null) {
                    lvVar.a(f, f2, f3);
                }
                this.m.postScale(f, f, f2, f3);
                b();
            }
        }
    }

    public void c(float f) {
        ov.a(f, this.d, this.f8573e);
        this.c = f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(float f) {
        ov.a(this.c, this.d, f);
        this.f8573e = f;
    }

    public void a(View.OnClickListener onClickListener) {
        this.s = onClickListener;
    }

    public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.i.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void a(View.OnLongClickListener onLongClickListener) {
        this.t = onLongClickListener;
    }

    public void a(iv ivVar) {
        this.p = ivVar;
    }

    public void a(jv jvVar) {
        this.r = jvVar;
    }

    public void a(kv kvVar) {
        this.q = kvVar;
    }

    public void a(lv lvVar) {
        this.u = lvVar;
    }

    public void a(mv mvVar) {
        this.v = mvVar;
    }

    public void a(ImageView.ScaleType scaleType) {
        if (!ov.a(scaleType) || scaleType == this.A) {
            return;
        }
        this.A = scaleType;
        m();
    }

    public void a(int i) {
        this.b = i;
    }

    public final void a(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        float fB = b(this.h);
        float fA = a(this.h);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.k.reset();
        float f = intrinsicWidth;
        float f2 = fB / f;
        float f3 = intrinsicHeight;
        float f4 = fA / f3;
        ImageView.ScaleType scaleType = this.A;
        if (scaleType == ImageView.ScaleType.CENTER) {
            this.k.postTranslate((fB - f) / 2.0f, (fA - f3) / 2.0f);
        } else if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            float fMax = Math.max(f2, f4);
            this.k.postScale(fMax, fMax);
            this.k.postTranslate((fB - (f * fMax)) / 2.0f, (fA - (f3 * fMax)) / 2.0f);
        } else if (scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            float fMin = Math.min(1.0f, Math.min(f2, f4));
            this.k.postScale(fMin, fMin);
            this.k.postTranslate((fB - (f * fMin)) / 2.0f, (fA - (f3 * fMin)) / 2.0f);
        } else {
            RectF rectF = new RectF(0.0f, 0.0f, f, f3);
            RectF rectF2 = new RectF(0.0f, 0.0f, fB, fA);
            if (((int) this.y) % 180 != 0) {
                rectF = new RectF(0.0f, 0.0f, f3, f);
            }
            int i = c.f8576a[this.A.ordinal()];
            if (i == 1) {
                this.k.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            } else if (i == 2) {
                this.k.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
            } else if (i == 3) {
                this.k.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
            } else if (i == 4) {
                this.k.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
            }
        }
        l();
    }

    public void a(float f, boolean z) {
        a(f, this.h.getRight() / 2, this.h.getBottom() / 2, z);
    }

    public final RectF a(Matrix matrix) {
        if (this.h.getDrawable() == null) {
            return null;
        }
        this.n.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        matrix.mapRect(this.n);
        return this.n;
    }

    public void a(float f, float f2, float f3, boolean z) {
        if (f < this.c || f > this.f8573e) {
            throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
        }
        if (z) {
            this.h.post(new d(j(), f, f2, f3));
        } else {
            this.m.setScale(f, f, f2, f3);
            b();
        }
    }
}
