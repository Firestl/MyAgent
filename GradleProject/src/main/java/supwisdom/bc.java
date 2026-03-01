package supwisdom;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: PathInterpolatorApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class bc implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f7051a;
    public final float[] b;

    public bc(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i = ((int) (length / 0.002f)) + 1;
        this.f7051a = new float[i];
        this.b = new float[i];
        float[] fArr = new float[2];
        for (int i2 = 0; i2 < i; i2++) {
            pathMeasure.getPosTan((i2 * length) / (i - 1), fArr, null);
            this.f7051a[i2] = fArr[0];
            this.b[i2] = fArr[1];
        }
    }

    public static Path a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        return path;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.f7051a.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < this.f7051a[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.f7051a;
        float f2 = fArr[length] - fArr[i];
        if (f2 == 0.0f) {
            return this.b[i];
        }
        float f3 = (f - fArr[i]) / f2;
        float[] fArr2 = this.b;
        float f4 = fArr2[i];
        return f4 + (f3 * (fArr2[length] - f4));
    }

    public bc(float f, float f2, float f3, float f4) {
        this(a(f, f2, f3, f4));
    }
}
