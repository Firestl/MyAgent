package supwisdom;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: compiled from: AnimationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class cl0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f7227a = new LinearInterpolator();
    public static final TimeInterpolator b = new od();
    public static final TimeInterpolator c = new nd();
    public static final TimeInterpolator d = new pd();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final TimeInterpolator f7228e = new DecelerateInterpolator();

    public static float a(float f, float f2, float f3) {
        return f + (f3 * (f2 - f));
    }

    public static int a(int i, int i2, float f) {
        return i + Math.round(f * (i2 - i));
    }
}
