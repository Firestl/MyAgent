package com.alibaba.dt.AChartsLib.decorators.blockDeocators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.alibaba.dt.AChartsLib.charts.Chart;
import supwisdom.im;

/* JADX INFO: loaded from: classes.dex */
public class PolarLabelDecorator extends im {

    public enum LabelPosition {
        LEFT("left", 0),
        RIGHT("right", 1),
        TOP("top", 2),
        BOTTOM("bottom", 3);

        public int index;
        public String name;

        LabelPosition(String str, int i) {
            this.name = str;
            this.index = i;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1544a;

        static {
            int[] iArr = new int[LabelPosition.values().length];
            f1544a = iArr;
            try {
                iArr[LabelPosition.RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1544a[LabelPosition.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PolarLabelDecorator(Chart chart) {
        super(chart);
    }

    public void a(Canvas canvas, float f, float f2, float f3, float f4, String str, Float f5) {
        this.d.setTextSize(f5.floatValue());
        if (f - f3 > 0.0f) {
            this.d.setTextAlign(Paint.Align.LEFT);
        } else {
            this.d.setTextAlign(Paint.Align.RIGHT);
        }
        this.d.setStyle(Paint.Style.FILL);
        canvas.drawText(str, f, f2, this.d);
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02be  */
    @Override // supwisdom.im
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(android.graphics.Canvas r47) {
        /*
            Method dump skipped, instruction units count: 1502
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.dt.AChartsLib.decorators.blockDeocators.PolarLabelDecorator.b(android.graphics.Canvas):void");
    }

    @Override // supwisdom.im, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
    }

    public RectF a(RectF rectF, RectF rectF2, LabelPosition labelPosition, LabelPosition labelPosition2, String str) {
        float f;
        float f2;
        float f3;
        float f4;
        RectF rectF3 = new RectF(rectF2);
        int i = a.f1544a[labelPosition.ordinal()];
        if (i == 1) {
            if (rectF != null) {
                f2 = rectF.right - rectF2.left;
                f = rectF.bottom - rectF2.top;
            } else {
                f = 0.0f;
                f2 = 0.0f;
            }
            float f5 = rectF2.right;
            float f6 = this.q.right;
            if (f5 > f6) {
                rectF3.left = rectF2.left - (f5 - f6);
                rectF3.right = f5 - (f5 - f6);
            }
            if (f2 > 0.0f && f > 0.0f) {
                if (labelPosition2 == LabelPosition.TOP) {
                    rectF3.left += f2;
                    rectF3.right += f2;
                } else {
                    rectF3.top += f;
                    rectF3.bottom += f;
                }
            }
            float f7 = rectF3.right;
            float f8 = this.q.right;
            if (f7 > f8) {
                float f9 = f7 - f8;
                rectF3.right = f7 - f9;
                rectF3.left -= f9;
                if (f > 0.0f) {
                    rectF3.top = rectF2.top + f;
                    rectF3.bottom = rectF2.bottom + f;
                }
            }
        } else if (i == 2) {
            if (rectF != null) {
                f3 = rectF2.right - rectF.left;
                f4 = rectF2.bottom - rectF.top;
            } else {
                f3 = 0.0f;
                f4 = 0.0f;
            }
            float f10 = rectF2.left;
            RectF rectF4 = this.q;
            float f11 = rectF4.left;
            if (f10 < f11) {
                rectF3.left = f11;
                rectF3.right = rectF2.right - (rectF2.left - rectF4.left);
            }
            if (f3 > 0.0f && f4 > 0.0f) {
                if (labelPosition2 == LabelPosition.BOTTOM) {
                    rectF3.left = rectF2.left - f3;
                    rectF3.right = rectF2.right - f3;
                } else {
                    rectF3.top = rectF2.top - f4;
                    rectF3.bottom = rectF2.bottom - f4;
                }
            }
            float f12 = rectF3.left;
            float f13 = this.q.left;
            if (f12 < f13) {
                float f14 = f12 - f13;
                rectF3.right -= f14;
                rectF3.left = f12 - f14;
                if (f4 > 0.0f) {
                    rectF3.top = rectF2.top - f4;
                    rectF3.bottom = rectF2.bottom - f4;
                }
            }
        }
        return rectF3;
    }
}
