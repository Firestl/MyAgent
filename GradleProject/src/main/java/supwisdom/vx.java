package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public abstract class vx extends ox {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f9556a = new int[4];
    public final int[] b = new int[8];
    public final float[] c = new float[4];
    public final float[] d = new float[4];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f9557e = new int[4];
    public final int[] f = new int[4];

    public static int a(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        return i;
    }

    public final int[] b() {
        return this.f9556a;
    }

    public final int[] c() {
        return this.f;
    }

    public final float[] d() {
        return this.d;
    }

    public final int[] e() {
        return this.f9557e;
    }

    public final float[] f() {
        return this.c;
    }

    public static void a(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    public static void b(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    public static boolean b(int[] iArr) {
        float f = (iArr[0] + iArr[1]) / ((iArr[2] + r1) + iArr[3]);
        if (f < 0.7916667f || f > 0.89285713f) {
            return false;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
            if (i3 < i) {
                i = i3;
            }
        }
        return i2 < i * 10;
    }

    public final int[] a() {
        return this.b;
    }

    public static int a(int[] iArr, int[][] iArr2) throws NotFoundException {
        for (int i = 0; i < iArr2.length; i++) {
            if (ox.a(iArr, iArr2[i], 0.45f) < 0.2f) {
                return i;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
