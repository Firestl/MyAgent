package io.dcloud.feature.gallery.imageedit.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import io.dcloud.feature.gallery.imageedit.c.a;
import io.dcloud.feature.gallery.imageedit.c.d;
import io.dcloud.feature.gallery.imageedit.c.j.e;

/* JADX INFO: loaded from: classes2.dex */
public class IMGView extends FrameLayout implements Runnable, ScaleGestureDetector.OnScaleGestureListener, ValueAnimator.AnimatorUpdateListener, e.a, Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public io.dcloud.feature.gallery.imageedit.c.b f6552a;
    public io.dcloud.feature.gallery.imageedit.c.a b;
    public GestureDetector c;
    public ScaleGestureDetector d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public io.dcloud.feature.gallery.imageedit.c.f.a f6553e;
    public c f;
    public int g;
    public Paint h;
    public Paint i;

    public class a implements a.c {
        public a() {
        }

        @Override // io.dcloud.feature.gallery.imageedit.c.a.c
        public void a() {
            IMGView.this.e();
            IMGView.this.b.b(this);
        }
    }

    public class b extends GestureDetector.SimpleOnGestureListener {
        public b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return IMGView.this.a(f, f2);
        }

        public /* synthetic */ b(IMGView iMGView, a aVar) {
            this();
        }
    }

    public IMGView(Context context) {
        this(context, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        invalidate();
        j();
        a(this.b.c(getScrollX(), getScrollY()), this.b.b(getScrollX(), getScrollY()));
    }

    private boolean f(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            return b(motionEvent);
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                return c(motionEvent);
            }
            if (actionMasked != 3) {
                return false;
            }
        }
        return this.f.b(motionEvent.getPointerId(0)) && f();
    }

    private void j() {
        io.dcloud.feature.gallery.imageedit.c.f.a aVar = this.f6553e;
        if (aVar != null) {
            aVar.cancel();
        }
    }

    public void c() {
        if (d()) {
            return;
        }
        this.b.a(-90);
        e();
    }

    public boolean d() {
        io.dcloud.feature.gallery.imageedit.c.f.a aVar = this.f6553e;
        return aVar != null && aVar.isRunning();
    }

    public boolean g() {
        Log.d("IMGView", "onSteady: isHoming=" + d());
        if (d()) {
            return false;
        }
        this.b.e(getScrollX(), getScrollY());
        e();
        return true;
    }

    public io.dcloud.feature.gallery.imageedit.c.b getMode() {
        return this.b.c();
    }

    public void h() {
        this.b.s();
        e();
    }

    public Bitmap i() {
        this.b.t();
        float fE = 1.0f / this.b.e();
        RectF rectF = new RectF(this.b.b());
        Matrix matrix = new Matrix();
        matrix.setRotate(this.b.d(), rectF.centerX(), rectF.centerY());
        matrix.mapRect(rectF);
        matrix.setScale(fE, fE, rectF.left, rectF.top);
        matrix.mapRect(rectF);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(Math.round(rectF.width()), Math.round(rectF.height()), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.translate(-rectF.left, -rectF.top);
        canvas.scale(fE, fE, rectF.left, rectF.top);
        a(canvas);
        return bitmapCreateBitmap;
    }

    public void k() {
        this.b.w();
        invalidate();
    }

    public void l() {
        this.b.x();
        invalidate();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        Log.d("IMGView", "onAnimationCancel");
        this.b.a(this.f6553e.a());
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Log.d("IMGView", "onAnimationEnd");
        if (this.b.a(getScrollX(), getScrollY(), this.f6553e.a())) {
            a(this.b.a(getScrollX(), getScrollY()));
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Log.d("IMGView", "onAnimationStart");
        this.b.b(this.f6553e.a());
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.b.a(valueAnimator.getAnimatedFraction());
        a((io.dcloud.feature.gallery.imageedit.c.i.a) valueAnimator.getAnimatedValue());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
        this.b.r();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        a(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 0 ? a(motionEvent) || super.onInterceptTouchEvent(motionEvent) : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.b.h(i3 - i, i4 - i2);
        }
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (this.g <= 1) {
            return false;
        }
        this.b.a(scaleGestureDetector.getScaleFactor(), getScrollX() + scaleGestureDetector.getFocusX(), getScrollY() + scaleGestureDetector.getFocusY());
        invalidate();
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (this.g <= 1) {
            return false;
        }
        this.b.p();
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.b.q();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            removeCallbacks(this);
        } else if (actionMasked == 1 || actionMasked == 3) {
            postDelayed(this, 1200L);
        }
        return d(motionEvent);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (g()) {
            return;
        }
        postDelayed(this, 500L);
    }

    public void setDoodleTouchListener(a.b bVar) {
        this.b.a(bVar);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.b.a(bitmap);
        invalidate();
    }

    public void setMode(io.dcloud.feature.gallery.imageedit.c.b bVar) {
        this.f6552a = this.b.c();
        this.b.a(bVar);
        this.f.a(bVar);
        if (this.b.j()) {
            e();
        } else {
            this.b.a(new a());
        }
    }

    public void setPenColor(int i) {
        this.f.a(i);
    }

    public static class c extends io.dcloud.feature.gallery.imageedit.c.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6556e;

        public c() {
            this.f6556e = Integer.MIN_VALUE;
        }

        public void a(float f, float f2) {
            this.f6533a.lineTo(f, f2);
        }

        public void b(float f, float f2) {
            this.f6533a.reset();
            this.f6533a.moveTo(f, f2);
            this.f6556e = Integer.MIN_VALUE;
        }

        public void c(int i) {
            this.f6556e = i;
        }

        public boolean e() {
            return this.f6533a.isEmpty();
        }

        public void f() {
            this.f6533a.reset();
            this.f6556e = Integer.MIN_VALUE;
        }

        public io.dcloud.feature.gallery.imageedit.c.c g() {
            if (b() == io.dcloud.feature.gallery.imageedit.c.b.DOODLE) {
                a(14.0f);
            } else {
                a(72.0f);
            }
            return new io.dcloud.feature.gallery.imageedit.c.c(new Path(this.f6533a), b(), a(), d());
        }

        public /* synthetic */ c(a aVar) {
            this();
        }

        public boolean b(int i) {
            return this.f6556e == i;
        }
    }

    public IMGView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void b() {
        this.b.a(getScrollX(), getScrollY());
        setMode(this.f6552a);
        e();
    }

    public IMGView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6552a = io.dcloud.feature.gallery.imageedit.c.b.NONE;
        this.b = new io.dcloud.feature.gallery.imageedit.c.a();
        this.f = new c(null);
        this.g = 0;
        this.h = new Paint(1);
        this.i = new Paint(1);
        this.h.setStyle(Paint.Style.STROKE);
        this.h.setStrokeWidth(14.0f);
        this.h.setColor(-65536);
        this.h.setPathEffect(new CornerPathEffect(14.0f));
        this.h.setStrokeCap(Paint.Cap.ROUND);
        this.h.setStrokeJoin(Paint.Join.ROUND);
        this.i.setStyle(Paint.Style.STROKE);
        this.i.setStrokeWidth(72.0f);
        this.i.setColor(-16777216);
        this.i.setPathEffect(new CornerPathEffect(72.0f));
        this.i.setStrokeCap(Paint.Cap.ROUND);
        this.i.setStrokeJoin(Paint.Join.ROUND);
        a(context);
        this.b.b(io.dcloud.feature.gallery.imageedit.c.k.b.a(getContext()));
    }

    private void a(Context context) {
        this.f.a(this.b.c());
        GestureDetector gestureDetector = new GestureDetector(context, new b(this, null));
        this.c = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.d = new ScaleGestureDetector(context, this);
    }

    public boolean d(MotionEvent motionEvent) {
        boolean zE;
        if (d()) {
            return false;
        }
        this.g = motionEvent.getPointerCount();
        boolean zOnTouchEvent = this.d.onTouchEvent(motionEvent);
        io.dcloud.feature.gallery.imageedit.c.b bVarC = this.b.c();
        if (bVarC != io.dcloud.feature.gallery.imageedit.c.b.NONE && bVarC != io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            if (this.g > 1) {
                f();
                zE = e(motionEvent);
            } else {
                zE = f(motionEvent);
            }
        } else {
            zE = e(motionEvent);
        }
        boolean z = zOnTouchEvent | zE;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.b.f(motionEvent.getX(), motionEvent.getY());
            if (this.b.c() == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
                invalidate();
            }
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.b.g(getScrollX(), getScrollY());
            e();
        }
        return z;
    }

    private boolean c(MotionEvent motionEvent) {
        if (!this.f.b(motionEvent.getPointerId(0))) {
            return false;
        }
        this.f.a(motionEvent.getX(), motionEvent.getY());
        invalidate();
        return true;
    }

    private boolean f() {
        if (this.f.e()) {
            return false;
        }
        this.b.a(this.f.g(), getScrollX(), getScrollY());
        this.f.f();
        invalidate();
        return true;
    }

    public void b(int i, int i2) {
        io.dcloud.feature.gallery.imageedit.c.a aVar = this.b;
        if (aVar != null) {
            aVar.a(i, i2);
        }
    }

    private boolean e(MotionEvent motionEvent) {
        return this.c.onTouchEvent(motionEvent);
    }

    private void a(io.dcloud.feature.gallery.imageedit.c.i.a aVar, io.dcloud.feature.gallery.imageedit.c.i.a aVar2) {
        if (this.f6553e == null) {
            io.dcloud.feature.gallery.imageedit.c.f.a aVar3 = new io.dcloud.feature.gallery.imageedit.c.f.a();
            this.f6553e = aVar3;
            aVar3.addUpdateListener(this);
            this.f6553e.addListener(this);
        }
        this.f6553e.a(aVar, aVar2);
        this.f6553e.start();
    }

    private boolean b(MotionEvent motionEvent) {
        this.f.b(motionEvent.getX(), motionEvent.getY());
        this.f.c(motionEvent.getPointerId(0));
        return true;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e.a
    public <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> void c(V v) {
        this.b.f(v);
        invalidate();
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e.a
    public <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> void b(V v) {
        this.b.d(v);
        invalidate();
    }

    public void a() {
        this.b.u();
        setMode(this.f6552a);
    }

    private void a(Canvas canvas) {
        canvas.save();
        RectF rectFB = this.b.b();
        canvas.rotate(this.b.d(), rectFB.centerX(), rectFB.centerY());
        this.b.b(canvas);
        if (!this.b.k() || (this.b.c() == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC && !this.f.e())) {
            int iC = this.b.c(canvas);
            if (this.b.c() == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC && !this.f.e()) {
                this.h.setStrokeWidth(72.0f);
                canvas.save();
                RectF rectFB2 = this.b.b();
                canvas.rotate(-this.b.d(), rectFB2.centerX(), rectFB2.centerY());
                canvas.translate(getScrollX(), getScrollY());
                canvas.drawPath(this.f.c(), this.h);
                canvas.restore();
            }
            this.b.a(canvas, iC);
        }
        this.b.a(canvas);
        if (this.b.c() == io.dcloud.feature.gallery.imageedit.c.b.DOODLE && !this.f.e()) {
            this.h.setColor(this.f.a());
            this.h.setStrokeWidth(14.0f);
            canvas.save();
            RectF rectFB3 = this.b.b();
            canvas.rotate(-this.b.d(), rectFB3.centerX(), rectFB3.centerY());
            canvas.translate(getScrollX(), getScrollY());
            canvas.drawPath(this.f.c(), this.h);
            canvas.restore();
        }
        if (this.b.i()) {
            this.b.f(canvas);
        }
        this.b.d(canvas);
        canvas.restore();
        if (!this.b.i()) {
            this.b.e(canvas);
            this.b.f(canvas);
        }
        if (this.b.c() == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            canvas.save();
            canvas.translate(getScrollX(), getScrollY());
            this.b.a(canvas, getScrollX(), getScrollY());
            canvas.restore();
        }
    }

    public <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> void a(V v, FrameLayout.LayoutParams layoutParams) {
        if (v != null) {
            addView(v, layoutParams);
            ((e) v).a(this);
            this.b.a(v);
        }
    }

    public void a(d dVar) {
        IMGStickerTextView iMGStickerTextView = new IMGStickerTextView(getContext());
        iMGStickerTextView.setText(dVar);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        iMGStickerTextView.setX(getScrollX());
        iMGStickerTextView.setY(getScrollY());
        a(iMGStickerTextView, layoutParams);
    }

    public boolean a(MotionEvent motionEvent) {
        if (!d()) {
            return this.b.c() == io.dcloud.feature.gallery.imageedit.c.b.CLIP;
        }
        j();
        return true;
    }

    private void a(io.dcloud.feature.gallery.imageedit.c.i.a aVar) {
        this.b.d(aVar.c);
        this.b.c(aVar.d);
        if (a(Math.round(aVar.f6541a), Math.round(aVar.b))) {
            return;
        }
        invalidate();
    }

    private boolean a(int i, int i2) {
        if (getScrollX() == i && getScrollY() == i2) {
            return false;
        }
        scrollTo(i, i2);
        return true;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e.a
    public <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> boolean a(V v) {
        io.dcloud.feature.gallery.imageedit.c.a aVar = this.b;
        if (aVar != null) {
            aVar.e(v);
        }
        ((e) v).b(this);
        ViewParent parent = v.getParent();
        if (parent == null) {
            return true;
        }
        ((ViewGroup) parent).removeView(v);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(float f, float f2) {
        io.dcloud.feature.gallery.imageedit.c.i.a aVarA = this.b.a(getScrollX(), getScrollY(), -f, -f2);
        if (aVarA != null) {
            a(aVarA);
            return true;
        }
        return a(getScrollX() + Math.round(f), getScrollY() + Math.round(f2));
    }
}
