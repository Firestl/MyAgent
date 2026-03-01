package supwisdom;

import android.graphics.Rect;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: compiled from: ViewCaculateUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class ao {
    public static float a(Chart chart, View view, float[] fArr, boolean z) {
        float measuredWidth;
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        Rect rect = new Rect();
        if (chart == null || view == null || fArr == null || fArr.length < 2) {
            return 0.0f;
        }
        rect.top = chart.getTop();
        rect.left = chart.getLeft();
        rect.right = chart.getRight();
        rect.bottom = chart.getBottom();
        if (z) {
            measuredWidth = view.getMeasuredHeight() / 2;
            float f5 = fArr[1] - measuredWidth;
            i = rect.top;
            if (f5 <= i) {
                f2 = fArr[1];
                f3 = f2 - measuredWidth;
                f4 = i;
            } else {
                float f6 = fArr[1] + measuredWidth;
                i2 = rect.bottom;
                if (f6 < i2) {
                    return 0.0f;
                }
                f = fArr[1];
                f3 = f + measuredWidth;
                f4 = i2;
            }
        } else {
            measuredWidth = view.getMeasuredWidth() / 2;
            float f7 = fArr[0] - measuredWidth;
            i = rect.left;
            if (f7 <= i) {
                f2 = fArr[0];
                f3 = f2 - measuredWidth;
                f4 = i;
            } else {
                float f8 = fArr[0] + measuredWidth;
                i2 = rect.right;
                if (f8 < i2) {
                    return 0.0f;
                }
                f = fArr[0];
                f3 = f + measuredWidth;
                f4 = i2;
            }
        }
        return f3 - f4;
    }

    public static float b(Chart chart, View view, float[] fArr, boolean z) {
        float f;
        float f2;
        Rect rect = new Rect();
        if (chart == null || view == null || fArr == null || fArr.length < 2) {
            return 0.0f;
        }
        rect.top = chart.getTop();
        rect.left = chart.getLeft();
        rect.right = chart.getRight();
        rect.bottom = chart.getBottom();
        float measuredHeight = view.getMeasuredHeight();
        float f3 = fArr[1] - measuredHeight;
        int i = rect.top;
        if (f3 <= i) {
            f = fArr[1] - measuredHeight;
            f2 = i;
        } else {
            float f4 = fArr[1] + measuredHeight;
            int i2 = rect.bottom;
            if (f4 < i2) {
                return 0.0f;
            }
            f = fArr[1] + measuredHeight;
            f2 = i2;
        }
        return f - f2;
    }

    public static boolean c(Chart chart, View view, float[] fArr, boolean z) {
        Rect rect = new Rect();
        if (chart != null && view != null && fArr != null && fArr.length >= 2) {
            rect.top = chart.getTop();
            rect.left = chart.getLeft();
            rect.right = chart.getRight();
            rect.bottom = chart.getBottom();
            if (z) {
                float measuredHeight = view.getMeasuredHeight() / 2;
                if (fArr[1] - measuredHeight >= rect.top && fArr[1] + measuredHeight <= rect.bottom) {
                    return true;
                }
            } else {
                float measuredWidth = view.getMeasuredWidth() / 2;
                if (fArr[0] - measuredWidth >= rect.left && fArr[0] + measuredWidth <= rect.right) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean d(Chart chart, View view, float[] fArr, boolean z) {
        boolean z2;
        boolean z3;
        Rect rect = new Rect();
        if (chart != null && view != null && fArr != null && fArr.length >= 2) {
            rect.top = chart.getTop();
            rect.left = chart.getLeft();
            rect.right = chart.getRight();
            rect.bottom = chart.getBottom();
            float measuredHeight = view.getMeasuredHeight();
            z2 = fArr[1] - measuredHeight >= ((float) rect.top) && fArr[1] + measuredHeight <= ((float) rect.bottom);
            float measuredWidth = view.getMeasuredWidth() / 2;
            if (fArr[0] - measuredWidth >= rect.left && fArr[0] + measuredWidth <= rect.right) {
                z3 = true;
            }
            return !z3 && z2;
        }
        z2 = false;
        z3 = false;
        if (z3) {
        }
    }

    public static boolean c(Chart chart, View view, float[] fArr) {
        boolean z;
        boolean z2;
        Rect rect = new Rect();
        if (chart != null && view != null && fArr != null && fArr.length >= 2) {
            rect.top = chart.getTop();
            rect.left = chart.getLeft();
            rect.right = chart.getRight();
            rect.bottom = chart.getBottom();
            float measuredWidth = view.getMeasuredWidth();
            float measuredHeight = view.getMeasuredHeight();
            z = fArr[0] - measuredWidth >= ((float) rect.left) && fArr[0] + measuredWidth <= ((float) rect.right);
            if (fArr[1] - measuredHeight >= rect.top && fArr[1] + measuredHeight <= rect.bottom) {
                z2 = true;
            }
            return !z && z2;
        }
        z = false;
        z2 = false;
        if (z) {
        }
    }

    public static float b(Chart chart, View view, float[] fArr) {
        float f;
        float f2;
        Rect rect = new Rect();
        if (chart == null || view == null || fArr == null || fArr.length < 2) {
            return 0.0f;
        }
        rect.top = chart.getTop();
        rect.left = chart.getLeft();
        rect.right = chart.getRight();
        rect.bottom = chart.getBottom();
        float measuredWidth = view.getMeasuredWidth();
        float f3 = fArr[1] - measuredWidth;
        int i = rect.top;
        if (f3 <= i) {
            f = fArr[1] - measuredWidth;
            f2 = i;
        } else {
            float f4 = fArr[1] + measuredWidth;
            int i2 = rect.bottom;
            if (f4 < i2) {
                return 0.0f;
            }
            f = fArr[1] + measuredWidth;
            f2 = i2;
        }
        return f - f2;
    }

    public static float a(Chart chart, View view, float[] fArr) {
        float f;
        float f2;
        Rect rect = new Rect();
        if (chart == null || view == null || fArr == null || fArr.length < 2) {
            return 0.0f;
        }
        rect.top = chart.getTop();
        rect.left = chart.getLeft();
        rect.right = chart.getRight();
        rect.bottom = chart.getBottom();
        float measuredWidth = view.getMeasuredWidth();
        float f3 = fArr[0] - measuredWidth;
        int i = rect.left;
        if (f3 <= i) {
            f = fArr[0] - measuredWidth;
            f2 = i;
        } else {
            float f4 = fArr[0] + measuredWidth;
            int i2 = rect.right;
            if (f4 < i2) {
                return 0.0f;
            }
            f = fArr[0] + measuredWidth;
            f2 = i2;
        }
        return f - f2;
    }
}
