package supwisdom;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: compiled from: TextBoundingRectF.java */
/* JADX INFO: loaded from: classes.dex */
public class yn {

    /* JADX INFO: compiled from: TextBoundingRectF.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9902a;

        static {
            int[] iArr = new int[Paint.Align.values().length];
            f9902a = iArr;
            try {
                iArr[Paint.Align.RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9902a[Paint.Align.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9902a[Paint.Align.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static RectF a(Paint paint, String str, float f, float f2, Paint.Align align, float f3, float f4) {
        RectF rectF = new RectF();
        float fMeasureText = paint.measureText(str);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f5 = fontMetrics.bottom - fontMetrics.top;
        int i = a.f9902a[align.ordinal()];
        if (i == 1) {
            rectF.left = f - fMeasureText;
            rectF.right = f;
        } else if (i == 2) {
            float f6 = fMeasureText / 2.0f;
            rectF.left = f - f6;
            rectF.right = f + f6;
        } else if (i == 3) {
            rectF.left = f;
            rectF.right = f + fMeasureText;
        }
        float f7 = f2 - f5;
        rectF.top = f7;
        rectF.bottom = f2;
        rectF.left -= f3;
        rectF.right += f3;
        rectF.top = f7 - f4;
        rectF.bottom = f2 + f4;
        return rectF;
    }

    public static Rect a(Paint paint, String str, int i, int i2, Paint.Align align, int i3, int i4) {
        Rect rect = new Rect();
        int iCeil = (int) Math.ceil(paint.measureText(str));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int iCeil2 = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        int i5 = a.f9902a[align.ordinal()];
        if (i5 == 1) {
            rect.left = i - iCeil;
            rect.right = i;
        } else if (i5 == 2) {
            int i6 = iCeil / 2;
            rect.left = i - i6;
            rect.right = i + i6;
        } else if (i5 == 3) {
            rect.left = i;
            rect.right = i + iCeil;
        }
        int i7 = i2 - iCeil2;
        rect.top = i7;
        rect.bottom = i2;
        rect.left -= i3;
        rect.right += i3;
        rect.top = i7 - i4;
        rect.bottom = i2 + i4;
        return rect;
    }
}
