package com.g.gysdk.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import com.g.gysdk.R;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class GTGifView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2071a;
    public Movie b;
    public long c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2072e;
    public float f;
    public float g;
    public int h;
    public int i;
    public volatile boolean j;
    public boolean k;

    public GTGifView(Context context) {
        this(context, null);
    }

    public GTGifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.styleable.gtCustomTheme_gtGifViewStyle);
    }

    public GTGifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = true;
        a(context, attributeSet, i);
    }

    @SuppressLint({"NewApi"})
    private void a(Context context, AttributeSet attributeSet, int i) {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.gtGifView, i, R.style.gtWidget_GifView);
        this.f2071a = typedArrayObtainStyledAttributes.getResourceId(R.styleable.gtGifView_gtGif, -1);
        this.j = typedArrayObtainStyledAttributes.getBoolean(R.styleable.gtGifView_gtPaused, false);
        typedArrayObtainStyledAttributes.recycle();
        if (this.f2071a != -1) {
            this.b = Movie.decodeStream(getResources().openRawResource(this.f2071a));
        }
    }

    private void a(Canvas canvas) {
        this.b.setTime(this.d);
        canvas.save();
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        float f = this.g;
        canvas.scale(f, f);
        Movie movie = this.b;
        float f2 = this.f2072e;
        float f3 = this.g;
        movie.draw(canvas, f2 / f3, this.f / f3);
        canvas.restore();
    }

    @SuppressLint({"NewApi"})
    private void c() {
        if (this.k) {
            if (Build.VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    private void d() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.c == 0) {
            this.c = jUptimeMillis;
        }
        int iDuration = this.b.duration();
        if (iDuration == 0) {
            iDuration = 1000;
        }
        this.d = (int) ((jUptimeMillis - this.c) % ((long) iDuration));
    }

    public void a() {
        if (this.j) {
            this.j = false;
            setVisibility(0);
            this.c = SystemClock.uptimeMillis() - ((long) this.d);
            invalidate();
        }
    }

    public void b() {
        if (this.j) {
            return;
        }
        this.j = true;
        setVisibility(8);
        invalidate();
    }

    public int getGifResource() {
        return this.f2071a;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.b != null) {
            if (this.j) {
                a(canvas);
                return;
            }
            d();
            a(canvas);
            c();
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f2072e = (getWidth() - this.h) / 2.0f;
        this.f = (getHeight() - this.i) / 2.0f;
        this.k = getVisibility() == 0;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int suggestedMinimumWidth;
        int suggestedMinimumHeight;
        int size;
        int size2;
        Movie movie = this.b;
        if (movie != null) {
            int iWidth = movie.width();
            int iHeight = this.b.height();
            float fMax = 1.0f / Math.max((View.MeasureSpec.getMode(i) == 0 || iWidth <= (size2 = View.MeasureSpec.getSize(i))) ? 1.0f : iWidth / size2, (View.MeasureSpec.getMode(i2) == 0 || iHeight <= (size = View.MeasureSpec.getSize(i2))) ? 1.0f : iHeight / size);
            this.g = fMax;
            suggestedMinimumWidth = (int) (iWidth * fMax);
            this.h = suggestedMinimumWidth;
            suggestedMinimumHeight = (int) (iHeight * fMax);
            this.i = suggestedMinimumHeight;
        } else {
            suggestedMinimumWidth = getSuggestedMinimumWidth();
            suggestedMinimumHeight = getSuggestedMinimumHeight();
        }
        setMeasuredDimension(suggestedMinimumWidth, suggestedMinimumHeight);
    }

    @Override // android.view.View
    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        this.k = i == 1;
        c();
    }

    @Override // android.view.View
    @SuppressLint({"NewApi"})
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.k = i == 0;
        c();
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.k = i == 0;
        c();
    }

    public void setGifResource(int i) {
        this.f2071a = i;
        this.b = Movie.decodeStream(getResources().openRawResource(this.f2071a));
        requestLayout();
    }
}
