package supwisdom;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class tu implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f9316a;
    public final float b;

    public tu(float[] fArr) {
        this.f9316a = fArr;
        this.b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f9316a;
        int iMin = Math.min((int) ((fArr.length - 1) * f), fArr.length - 2);
        float f2 = this.b;
        float f3 = (f - (iMin * f2)) / f2;
        float[] fArr2 = this.f9316a;
        return fArr2[iMin] + (f3 * (fArr2[iMin + 1] - fArr2[iMin]));
    }
}
