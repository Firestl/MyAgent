package io.dcloud.feature.speech.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public class VolumeView extends View {
    public int mCurrentVolume;
    public int mMaxVolume;
    public Paint mPaint;
    public int mVolumeColor;

    public VolumeView(Context context) {
        super(context);
        this.mCurrentVolume = 0;
        this.mMaxVolume = 1;
        this.mPaint = null;
        this.mVolumeColor = -65536;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mVolumeColor);
        this.mPaint.setAntiAlias(true);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = this.mCurrentVolume;
        if (1 > i || i > this.mMaxVolume) {
            return;
        }
        float height = getHeight();
        float width = getWidth();
        int i2 = this.mMaxVolume;
        float f = height / ((i2 + i2) - 1);
        float f2 = height / ((i2 + i2) - 1);
        for (int i3 = 1; i3 <= this.mCurrentVolume; i3++) {
            float f3 = (f + f2) * (r5 - i3);
            canvas.drawRect(0.0f, f3, (i3 * width) / this.mMaxVolume, f3 + f, this.mPaint);
        }
    }

    public int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public int getMaxVolume() {
        return this.mMaxVolume;
    }

    public int getVolumeColor() {
        return this.mVolumeColor;
    }

    public void setCurrentVolume(int i) {
        this.mCurrentVolume = i;
        invalidate();
    }

    public void setMaxVolume(int i) {
        this.mMaxVolume = i;
    }

    public void setVolumeColor(int i) {
        this.mVolumeColor = i;
        Paint paint = this.mPaint;
        if (paint != null) {
            try {
                paint.setColor(i);
            } catch (Exception unused) {
            }
        }
    }

    public VolumeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentVolume = 0;
        this.mMaxVolume = 1;
        this.mPaint = null;
        this.mVolumeColor = -65536;
        init();
    }

    public VolumeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentVolume = 0;
        this.mMaxVolume = 1;
        this.mPaint = null;
        this.mVolumeColor = -65536;
        init();
    }

    @TargetApi(21)
    public VolumeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentVolume = 0;
        this.mMaxVolume = 1;
        this.mPaint = null;
        this.mVolumeColor = -65536;
        init();
    }
}
