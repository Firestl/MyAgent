package io.dcloud.feature.nativeObj.richtext.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* JADX INFO: loaded from: classes3.dex */
public class HrSpan extends ReplacementSpan {
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public int align;
    public int color;
    public int hrWidth;
    public int size;
    public int width;

    public HrSpan(int i, int i2, int i3, int i4, int i5) {
        this.align = i;
        this.size = i2;
        this.width = i3;
        this.color = i4;
        this.hrWidth = i5;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        paint.setStrokeWidth(this.size);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.color);
        int i6 = this.align;
        if (i6 == 0) {
            int i7 = this.hrWidth;
            int i8 = this.width;
            float f2 = (i7 - i8) / 2;
            canvas.drawRect(f + f2, i4, f + i8 + f2, i4 + this.size, paint);
            return;
        }
        if (i6 == 2) {
            float f3 = f + this.hrWidth;
            canvas.drawRect(f3 - this.width, i4, f3, i4 + this.size, paint);
        } else if (i6 == 1) {
            canvas.drawRect(f, i4, f + this.width, i4 + this.size, paint);
        }
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = 0;
            int i3 = this.size;
            fontMetricsInt.descent = i3;
            fontMetricsInt.bottom = i3;
            fontMetricsInt.top = 0;
        }
        return this.hrWidth;
    }
}
