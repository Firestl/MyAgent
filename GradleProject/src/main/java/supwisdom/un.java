package supwisdom;

import android.graphics.Path;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.lang.reflect.Array;
import java.util.List;

/* JADX INFO: compiled from: Interpolation.java */
/* JADX INFO: loaded from: classes.dex */
public class un {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Chart f9425a;
    public float b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9426e;
    public float f;
    public int g;

    public un(Chart chart) {
        this.f9425a = chart;
    }

    public final float a(float f) {
        return f < 0.0f ? -1.0f : 1.0f;
    }

    public Path a(ok okVar, pk pkVar, float f, float f2, float f3, float f4) {
        return a(a(okVar, pkVar), pkVar, f, f2, f3, f4);
    }

    public float b(float f, float f2) {
        float f3 = this.b;
        float f4 = f3 - this.c;
        float f5 = f - f3;
        float f6 = this.f9426e;
        float f7 = f6 - this.d;
        float f8 = f2 - f6;
        float f9 = f7 == 0.0f ? 0.0f : f7 / f4;
        float f10 = f8 == 0.0f ? 0.0f : f8 / f5;
        float f11 = ((f9 * f5) + (f10 * f4)) / (f4 + f5);
        float fA = a(f9) + a(f10);
        if (fA == 0.0f) {
            return 0.0f;
        }
        float fA2 = a(a(Math.abs(f9), Math.abs(f10)), Math.abs(f11) * 0.5f);
        return Float.isNaN(fA2) ? fA < 0.0f ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY : fA2 * fA;
    }

    public void c(Path path, float f, float f2) {
        float fB;
        if (f == this.b && f2 == this.f9426e) {
            return;
        }
        int i = this.g;
        if (i == 0) {
            this.g = 1;
            b(path, f, f2);
        } else {
            if (i != 1) {
                if (i != 2) {
                    fB = b(f, f2);
                    d(path, this.f, fB);
                } else {
                    this.g = 3;
                    fB = b(f, f2);
                    d(path, fB, fB);
                }
                this.c = this.b;
                this.b = f;
                this.d = this.f9426e;
                this.f9426e = f2;
                this.f = fB;
            }
            this.g = 2;
        }
        fB = Float.NaN;
        this.c = this.b;
        this.b = f;
        this.d = this.f9426e;
        this.f9426e = f2;
        this.f = fB;
    }

    public void d(Path path, float f, float f2) {
        float f3 = this.c;
        float f4 = this.b;
        float f5 = this.d;
        float f6 = this.f9426e;
        float f7 = (f4 - f3) / 3.0f;
        path.cubicTo(f3 + f7, f5 + (f * f7), f4 - f7, f6 - (f7 * f2), f4, f6);
    }

    public Path a(float[][] fArr, pk pkVar, float f, float f2, float f3, float f4) {
        return a(a(fArr, f, f2, f3, f4));
    }

    public float[][] a(float[][] fArr, float f, float f2, float f3, float f4) {
        float[] fArr2 = new float[fArr.length * 2];
        int i = 0;
        for (int i2 = 0; i2 < fArr.length; i2++) {
            for (int i3 = 0; i3 < fArr[i2].length; i3++) {
                fArr2[i] = fArr[i2][i3];
                i++;
            }
        }
        this.f9425a.getTransformUtil().a(fArr2, f, f2, f3, f4);
        float[][] fArr3 = (float[][]) Array.newInstance((Class<?>) float.class, fArr.length, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < fArr.length; i5++) {
            fArr3[i5][0] = fArr2[i4];
            int i6 = i4 + 1;
            fArr3[i5][1] = fArr2[i6];
            i4 = i6 + 1;
        }
        return fArr3;
    }

    public float b(float f) {
        float f2 = this.b - this.c;
        return !Float.isNaN(f2) ? ((((this.f9426e - this.d) * 3.0f) / f2) - f) / 2.0f : f;
    }

    public void b(Path path, float f, float f2) {
        path.moveTo(f, f2);
        path.addCircle(f, f2, 1.0f, Path.Direction.CW);
    }

    public final Path a(float[][] fArr) {
        Path path = new Path();
        a();
        double length = fArr.length;
        for (int i = 0; i < length; i++) {
            c(path, fArr[i][0], fArr[i][1]);
        }
        a(path);
        return path;
    }

    public float[][] a(ok okVar, pk pkVar) {
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, pkVar.i().size(), 2);
        List<yk<Double>> listI = pkVar.i();
        List listD = okVar.d();
        for (int i = 0; i < listD.size() && i < listI.size() && listI.get(i).b() != null; i++) {
            fArr[i][0] = ((yk) listD.get(i)).a();
            fArr[i][1] = listI.get(i).b().floatValue();
        }
        return fArr;
    }

    public final float a(float f, float f2) {
        return (Float.isNaN(f) || Float.valueOf(f).equals(Float.valueOf(Float.POSITIVE_INFINITY)) || f >= f2) ? f2 : f;
    }

    public void a() {
        this.f = Float.NaN;
        this.f9426e = Float.NaN;
        this.d = Float.NaN;
        this.b = Float.NaN;
        this.c = Float.NaN;
        this.g = 0;
    }

    public void a(Path path) {
        int i = this.g;
        if (i == 2) {
            a(path, this.b, this.f9426e);
        } else {
            if (i != 3) {
                return;
            }
            float f = this.f;
            d(path, f, b(f));
        }
    }

    public void a(Path path, float f, float f2) {
        path.lineTo(f, f2);
    }
}
