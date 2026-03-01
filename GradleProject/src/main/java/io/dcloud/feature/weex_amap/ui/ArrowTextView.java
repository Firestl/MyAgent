package io.dcloud.feature.weex_amap.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class ArrowTextView extends TextView {
    public int defPadding;
    public boolean isSharp;
    public int mPadding;
    public int mRadius;
    public int mStrokeColor;
    public int mStrokeWidth;
    public int mTextBgColor;

    public ArrowTextView(Context context, boolean z) {
        super(context);
        this.mRadius = 0;
        this.mTextBgColor = -16777216;
        this.defPadding = 10;
        this.mStrokeWidth = 0;
        this.mPadding = 0;
        this.mStrokeColor = 0;
        this.isSharp = true;
        this.isSharp = z;
        if (z) {
            return;
        }
        this.defPadding = 0;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int height = getHeight();
        int width = getWidth();
        paint.setStrokeWidth(1.0f);
        int i = this.defPadding + this.mStrokeWidth;
        float f = i;
        float f2 = width - i;
        float f3 = height - i;
        RectF rectF = new RectF(f, f, f2, f3);
        if (this.mStrokeWidth > 0) {
            int i2 = this.defPadding;
            RectF rectF2 = new RectF(i2, i2, width - i2, height - i2);
            paint.setColor(this.mStrokeColor);
            int i3 = this.mRadius;
            canvas.drawRoundRect(rectF2, i3, i3, paint);
        }
        paint.setColor(this.mTextBgColor);
        int i4 = this.mRadius;
        canvas.drawRoundRect(rectF, i4, i4, paint);
        if (this.isSharp) {
            if (this.mStrokeWidth > 0) {
                Path path = new Path();
                path.moveTo(width / 2, height);
                int i5 = this.defPadding;
                path.lineTo(r5 - i5, height - i5);
                int i6 = this.defPadding;
                path.lineTo(r5 + i6, height - i6);
                paint.setColor(this.mStrokeColor);
                path.close();
                canvas.drawPath(path, paint);
            }
            Path path2 = new Path();
            path2.moveTo(width / 2, height - this.mStrokeWidth);
            path2.lineTo(r2 - this.defPadding, f3);
            path2.lineTo(r2 + this.defPadding, f3);
            paint.setColor(this.mTextBgColor);
            path2.close();
            canvas.drawPath(path2, paint);
        }
        super.onDraw(canvas);
    }

    public void setBgColor(int i) {
        this.mTextBgColor = i;
        invalidate();
    }

    public void setRadius(int i) {
        this.mRadius = i;
        invalidate();
    }

    public void setStrokeColor(int i) {
        this.mStrokeColor = i;
    }

    public void setStrokeWidth(int i) {
        this.mStrokeWidth = i * 2;
        setTextPadding(this.mPadding);
    }

    public void setTextPadding(int i) {
        this.mPadding = i;
        int i2 = i + this.defPadding + this.mStrokeWidth;
        setPadding(i2, i2, i2, i2);
        invalidate();
    }
}
