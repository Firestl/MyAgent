package io.dcloud.feature.gallery.imageedit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import io.dcloud.base.R;
import io.dcloud.feature.gallery.imageedit.c.j.a;
import io.dcloud.feature.gallery.imageedit.c.j.b;
import io.dcloud.feature.gallery.imageedit.c.j.c;
import io.dcloud.feature.gallery.imageedit.c.j.d;
import io.dcloud.feature.gallery.imageedit.c.j.e;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IMGStickerView extends ViewGroup implements a, View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f6550a;
    public float b;
    public int c;
    public d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c<IMGStickerView> f6551e;
    public ImageView f;
    public ImageView g;
    public Paint h;
    public Matrix i;
    public RectF j;
    public Rect k;

    public IMGStickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private ViewGroup.LayoutParams getAnchorLayoutParams() {
        return new ViewGroup.LayoutParams(48, 48);
    }

    private ViewGroup.LayoutParams getContentLayoutParams() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    public abstract View a(Context context);

    @Override // io.dcloud.feature.gallery.imageedit.c.e
    public void a(float f) {
        setScale(getScale() * f);
    }

    public void b(Context context) {
        setBackgroundColor(0);
        View viewA = a(context);
        this.f6550a = viewA;
        addView(viewA, getContentLayoutParams());
        ImageView imageView = new ImageView(context);
        this.f = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f.setImageResource(R.mipmap.image_ic_delete);
        addView(this.f, getAnchorLayoutParams());
        this.f.setOnClickListener(this);
        ImageView imageView2 = new ImageView(context);
        this.g = imageView2;
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        this.g.setImageResource(R.mipmap.image_ic_adjust);
        addView(this.g, getAnchorLayoutParams());
        new b(this, this.g);
        this.f6551e = new c<>(this);
        this.d = new d(this);
    }

    public void c() {
    }

    public void d() {
        this.f6551e.c();
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public boolean dismiss() {
        return this.f6551e.dismiss();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (b()) {
            canvas.drawRect(24.0f, 24.0f, getWidth() - 24, getHeight() - 24, this.h);
        }
        super.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        return b() && super.drawChild(canvas, view, j);
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public RectF getFrame() {
        return this.f6551e.getFrame();
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.e
    public float getScale() {
        return this.b;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.f) {
            d();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (b() || motionEvent.getAction() != 0) {
            return b() && super.onInterceptTouchEvent(motionEvent);
        }
        this.c = 0;
        a();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.j.set(i, i2, i3, i4);
        if (getChildCount() == 0) {
            return;
        }
        ImageView imageView = this.f;
        imageView.layout(0, 0, imageView.getMeasuredWidth(), this.f.getMeasuredHeight());
        ImageView imageView2 = this.g;
        int i5 = i3 - i;
        int i6 = i4 - i2;
        imageView2.layout(i5 - imageView2.getMeasuredWidth(), i6 - this.g.getMeasuredHeight(), i5, i6);
        int i7 = i5 >> 1;
        int i8 = i6 >> 1;
        int measuredWidth = this.f6550a.getMeasuredWidth() >> 1;
        int measuredHeight = this.f6550a.getMeasuredHeight() >> 1;
        this.f6550a.layout(i7 - measuredWidth, i8 - measuredHeight, i7 + measuredWidth, i8 + measuredHeight);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int iRound = 0;
        int iCombineMeasuredStates = 0;
        int iRound2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                childAt.measure(i, i2);
                iRound2 = Math.round(Math.max(iRound2, childAt.getMeasuredWidth() * childAt.getScaleX()));
                iRound = Math.round(Math.max(iRound, childAt.getMeasuredHeight() * childAt.getScaleY()));
                iCombineMeasuredStates = ViewGroup.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(iRound2, getSuggestedMinimumWidth()), i, iCombineMeasuredStates), ViewGroup.resolveSizeAndState(Math.max(iRound, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates << 16));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zA = this.d.a(this, motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.c++;
        } else if (actionMasked == 1 && this.c > 1 && motionEvent.getEventTime() - motionEvent.getDownTime() < ViewConfiguration.getTapTimeout()) {
            c();
            return true;
        }
        return super.onTouchEvent(motionEvent) | zA;
    }

    public void setScale(float f) {
        this.b = f;
        this.f6550a.setScaleX(f);
        this.f6550a.setScaleY(this.b);
        float left = (getLeft() + getRight()) >> 1;
        float top = (getTop() + getBottom()) >> 1;
        this.j.set(left, top, left, top);
        this.j.inset(-(this.f6550a.getMeasuredWidth() >> 1), -(this.f6550a.getMeasuredHeight() >> 1));
        Matrix matrix = this.i;
        float f2 = this.b;
        matrix.setScale(f2, f2, this.j.centerX(), this.j.centerY());
        this.i.mapRect(this.j);
        this.j.round(this.k);
        Rect rect = this.k;
        layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    public IMGStickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 1.0f;
        this.c = 0;
        this.i = new Matrix();
        this.j = new RectF();
        this.k = new Rect();
        Paint paint = new Paint(1);
        this.h = paint;
        paint.setColor(-1);
        this.h.setStyle(Paint.Style.STROKE);
        this.h.setStrokeWidth(3.0f);
        b(context);
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public boolean a() {
        return this.f6551e.a();
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public void a(Canvas canvas) {
        canvas.translate(this.f6550a.getX(), this.f6550a.getY());
        this.f6550a.draw(canvas);
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public void a(e.a aVar) {
        this.f6551e.a(aVar);
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public boolean b() {
        return this.f6551e.b();
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public void b(e.a aVar) {
        this.f6551e.b(aVar);
    }
}
