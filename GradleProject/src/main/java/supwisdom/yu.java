package supwisdom;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/* JADX INFO: loaded from: classes.dex */
public class yu {
    public static void a(Object obj) {
        ((OverScroller) obj).abortAnimation();
    }

    public static boolean b(Object obj) {
        return ((OverScroller) obj).computeScrollOffset();
    }

    public static int c(Object obj) {
        return ((OverScroller) obj).getCurrX();
    }

    public static int d(Object obj) {
        return ((OverScroller) obj).getCurrY();
    }

    public static int e(Object obj) {
        return ((OverScroller) obj).getFinalX();
    }

    public static int f(Object obj) {
        return ((OverScroller) obj).getFinalY();
    }

    public static boolean g(Object obj) {
        return ((OverScroller) obj).isFinished();
    }

    public static Object a(Context context, Interpolator interpolator) {
        return interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    public static void a(Object obj, int i, int i2, int i3, int i4, int i5) {
        ((OverScroller) obj).startScroll(i, i2, i3, i4, i5);
    }
}
