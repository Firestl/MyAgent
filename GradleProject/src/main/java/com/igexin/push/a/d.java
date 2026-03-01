package com.igexin.push.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends ImageView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3215e = 1000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Movie f3216a;
    public long b;
    public int c;
    public volatile boolean d;
    public float f;
    public float g;
    public float h;

    public d(Context context) {
        super(context, null);
        this.d = true;
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
    }

    private void a(Canvas canvas) {
        this.f3216a.setTime(this.c);
        canvas.save();
        float f = this.h;
        canvas.scale(f, f);
        Movie movie = this.f3216a;
        float f2 = this.f;
        float f3 = this.h;
        movie.draw(canvas, f2 / f3, this.g / f3);
        canvas.restore();
    }

    private boolean a() {
        return !this.d;
    }

    private void b() {
        if (this.d) {
            this.d = false;
            if (this.f3216a != null) {
                this.b = SystemClock.uptimeMillis() - ((long) this.c);
                invalidate();
            }
        }
    }

    private void c() {
        if (this.d) {
            return;
        }
        this.d = true;
        if (this.f3216a != null) {
            invalidate();
        }
    }

    private void d() {
        if (getVisibility() == 0) {
            if (Build.VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    private void e() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.b == 0) {
            this.b = jUptimeMillis;
        }
        int iDuration = this.f3216a.duration();
        if (iDuration == 0) {
            iDuration = 1000;
        }
        this.c = (int) ((jUptimeMillis - this.b) % ((long) iDuration));
    }

    private void setGifMovie$304a7d5c(Movie movie) {
        this.f3216a = movie;
        this.b = 0L;
        this.c = 0;
        setLayerType(1, null);
        setImageDrawable(null);
        requestLayout();
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f3216a != null) {
            if (this.d) {
                a(canvas);
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (this.b == 0) {
                this.b = jUptimeMillis;
            }
            int iDuration = this.f3216a.duration();
            if (iDuration == 0) {
                iDuration = 1000;
            }
            this.c = (int) ((jUptimeMillis - this.b) % ((long) iDuration));
            a(canvas);
            d();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f3216a != null) {
            int width = getWidth();
            int height = getHeight();
            this.h = 1.0f / Math.max(width != 0 ? this.f3216a.width() / width : 1.0f, height != 0 ? this.f3216a.height() / height : 1.0f);
            this.f = (width - ((int) (r6 * r8))) / 2.0f;
            this.g = (height - ((int) (r7 * r8))) / 2.0f;
        }
    }

    @Override // android.view.View
    public final void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        d();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        d();
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        d();
    }
}
