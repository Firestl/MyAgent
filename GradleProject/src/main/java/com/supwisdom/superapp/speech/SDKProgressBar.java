package com.supwisdom.superapp.speech;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import supwisdom.nj1;

/* JADX INFO: loaded from: classes2.dex */
public class SDKProgressBar extends View {
    public static final int[] j = {-4725762, -6892035, -8138755, -8992259, -10108420, -10371589, -10701318, -11031047, -11360520, -11427850, -11627534, -11760142, -11892239};
    public static final int[] k = {-15584414, -15645323, -15708289, -15770997, -15832934, -15829850, -15892302, -15954753, -16017976, -16014892, -16077600, -16140051, -16140051};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f4018a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4019e;
    public int f;
    public int g;
    public Paint h;
    public ColorFilter i;

    public SDKProgressBar(Context context) {
        super(context);
        this.h = new Paint();
        a();
    }

    public final void a() {
        this.b = getLeft();
        this.c = getTop();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.h.setColorFilter(this.i);
        int i = 0;
        while (true) {
            int i2 = this.g;
            if (i > i2) {
                return;
            }
            if (i2 <= 12) {
                this.h.setColor(this.f4018a[12 - (i2 - i)]);
            } else if (i <= i2 - 12) {
                this.h.setColor(this.f4018a[0]);
            } else {
                this.h.setColor(this.f4018a[12 - (i2 - i)]);
            }
            int i3 = this.b;
            int i4 = this.f4019e;
            canvas.drawRect((i4 * i) + i3 + i, this.c, i3 + (i4 * i) + i + i4, r4 + this.d, this.h);
            i++;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        this.f = size;
        int i3 = size / 80;
        this.d = i3;
        this.f4019e = i3;
        setMeasuredDimension(size, i3);
    }

    public void setHsvFilter(ColorFilter colorFilter) {
        this.i = colorFilter;
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > 80) {
            i = 80;
        }
        this.g = i;
        invalidate();
    }

    public void setTheme(int i) {
        this.f4018a = nj1.a(i) ? k : j;
    }

    public SDKProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new Paint();
        a();
    }
}
