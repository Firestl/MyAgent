package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class w4 {
    public double[] c;
    public int d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f9581a = new float[0];
    public double[] b = new double[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f9582e = 6.283185307179586d;

    public void a(int i) {
        this.d = i;
    }

    public double b(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.b, d);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.f9581a;
        int i2 = i - 1;
        double d2 = fArr[i] - fArr[i2];
        double[] dArr = this.b;
        double d3 = d2 / (dArr[i] - dArr[i2]);
        return this.c[i2] + ((((double) fArr[i2]) - (dArr[i2] * d3)) * (d - dArr[i2])) + ((d3 * ((d * d) - (dArr[i2] * dArr[i2]))) / 2.0d);
    }

    public double c(double d) {
        double dA;
        double dSignum;
        double dA2;
        double dA3;
        double dSin;
        switch (this.d) {
            case 1:
                return 0.0d;
            case 2:
                dA = a(d) * 4.0d;
                dSignum = Math.signum((((b(d) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dA * dSignum;
            case 3:
                dA2 = a(d);
                return dA2 * 2.0d;
            case 4:
                dA2 = -a(d);
                return dA2 * 2.0d;
            case 5:
                dA3 = (-this.f9582e) * a(d);
                dSin = Math.sin(this.f9582e * b(d));
                return dA3 * dSin;
            case 6:
                dA = a(d) * 4.0d;
                dSignum = (((b(d) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dA * dSignum;
            default:
                dA3 = this.f9582e * a(d);
                dSin = Math.cos(this.f9582e * b(d));
                return dA3 * dSin;
        }
    }

    public double d(double d) {
        double dAbs;
        switch (this.d) {
            case 1:
                return Math.signum(0.5d - (b(d) % 1.0d));
            case 2:
                dAbs = Math.abs((((b(d) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((b(d) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((b(d) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.f9582e * b(d));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((b(d) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.f9582e * b(d));
        }
        return 1.0d - dAbs;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.b) + " period=" + Arrays.toString(this.f9581a);
    }

    public void a(double d, float f) {
        int length = this.f9581a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.b, d);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.b = Arrays.copyOf(this.b, length);
        this.f9581a = Arrays.copyOf(this.f9581a, length);
        this.c = new double[length];
        double[] dArr = this.b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.b[iBinarySearch] = d;
        this.f9581a[iBinarySearch] = f;
    }

    public void a() {
        double d = 0.0d;
        int i = 0;
        while (true) {
            float[] fArr = this.f9581a;
            if (i >= fArr.length) {
                break;
            }
            d += (double) fArr[i];
            i++;
        }
        int i2 = 1;
        double d2 = 0.0d;
        int i3 = 1;
        while (true) {
            float[] fArr2 = this.f9581a;
            if (i3 >= fArr2.length) {
                break;
            }
            int i4 = i3 - 1;
            float f = (fArr2[i4] + fArr2[i3]) / 2.0f;
            double[] dArr = this.b;
            d2 += (dArr[i3] - dArr[i4]) * ((double) f);
            i3++;
        }
        int i5 = 0;
        while (true) {
            float[] fArr3 = this.f9581a;
            if (i5 >= fArr3.length) {
                break;
            }
            fArr3[i5] = (float) (((double) fArr3[i5]) * (d / d2));
            i5++;
        }
        this.c[0] = 0.0d;
        while (true) {
            float[] fArr4 = this.f9581a;
            if (i2 >= fArr4.length) {
                return;
            }
            int i6 = i2 - 1;
            float f2 = (fArr4[i6] + fArr4[i2]) / 2.0f;
            double[] dArr2 = this.b;
            double d3 = dArr2[i2] - dArr2[i6];
            double[] dArr3 = this.c;
            dArr3[i2] = dArr3[i6] + (d3 * ((double) f2));
            i2++;
        }
    }

    public double a(double d) {
        if (d <= 0.0d) {
            d = 1.0E-5d;
        } else if (d >= 1.0d) {
            d = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.b, d);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.f9581a;
        int i2 = i - 1;
        double d2 = fArr[i] - fArr[i2];
        double[] dArr = this.b;
        double d3 = d2 / (dArr[i] - dArr[i2]);
        return (((double) fArr[i2]) - (d3 * dArr[i2])) + (d * d3);
    }
}
