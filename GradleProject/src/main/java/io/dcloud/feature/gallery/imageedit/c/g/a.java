package io.dcloud.feature.gallery.imageedit.c.g;

import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public interface a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f6536a = {0.0f, 1.0f, 0.33f, 0.66f};
    public static final float[] b = {0.0f, 3.0f, -3.0f};
    public static final float[] c = {0.0f, 48.0f, -48.0f};
    public static final byte[] d = {8, 8, 9, 8, 6, 8, 4, 8, 4, 8, 4, 1, 4, 10, 4, 8, 4, 4, 6, 4, 9, 4, 8, 4, 8, 4, 8, 6, 8, 9, 8, 8};

    /* JADX INFO: renamed from: io.dcloud.feature.gallery.imageedit.c.g.a$a, reason: collision with other inner class name */
    public enum EnumC0172a {
        LEFT(1),
        RIGHT(2),
        TOP(4),
        BOTTOM(8),
        LEFT_TOP(5),
        RIGHT_TOP(6),
        LEFT_BOTTOM(9),
        RIGHT_BOTTOM(10);

        public static final int[] j = {1, -1};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6538a;

        EnumC0172a(int i) {
            this.f6538a = i;
        }

        public void a(RectF rectF, RectF rectF2, float f, float f2, float[] fArr, boolean z) {
            float fAbs;
            float fHeight;
            float fAbs2;
            int i;
            float[] fArrA = a(rectF, 0.0f, 0.0f);
            float[] fArrA2 = a(rectF2, 150.72f, 150.72f);
            float[] fArrA3 = a(rectF2, 0.0f, 0.0f);
            int i2 = this.f6538a;
            if (z) {
                fArrA2 = a(rectF2, 150.72f, (150.72f / fArr[0]) * fArr[1]);
                int[] iArr = {(int) (Math.abs(f) / f), (int) (Math.abs(f2) / f2)};
                int i3 = this.f6538a;
                if (i3 == 4) {
                    rectF2.top += f2;
                    fAbs = iArr[1] * Math.abs(rectF2.width() - ((rectF2.height() / fArr[1]) * fArr[0]));
                    fHeight = f2;
                    i2 = 5;
                } else if (i3 == 8) {
                    rectF2.bottom += f2;
                    fAbs = iArr[1] * Math.abs(rectF2.width() - ((rectF2.height() / fArr[1]) * fArr[0]));
                    fHeight = f2;
                    i2 = 10;
                } else {
                    if (i3 == 1) {
                        i2 = 5;
                    }
                    if (i3 == 2) {
                        i2 = 10;
                    }
                    if ((i2 & 2) != 0) {
                        rectF2.right += f;
                    } else if ((i2 & 1) != 0) {
                        rectF2.left += f;
                    }
                    fHeight = rectF2.height() - ((rectF2.width() / fArr[0]) * fArr[1]);
                    if (i2 == 5 || i2 == 10) {
                        fAbs2 = Math.abs(fHeight);
                        i = iArr[0];
                    } else {
                        if (i2 == 6 || i2 == 9) {
                            fAbs2 = Math.abs(fHeight);
                            i = -iArr[0];
                        }
                        fAbs = f;
                    }
                    fHeight = i * fAbs2;
                    fAbs = f;
                }
            } else {
                fAbs = f;
                fHeight = f2;
            }
            float[] fArr2 = {fAbs, 0.0f, fHeight};
            int i4 = 0;
            for (int i5 = 4; i4 < i5; i5 = 4) {
                if (((1 << i4) & i2) != 0) {
                    int[] iArr2 = j;
                    int i6 = i4 & 1;
                    float f3 = iArr2[i6];
                    fArrA3[i4] = f3 * a(f3 * (fArrA3[i4] + fArr2[i4 & 2]), f3 * fArrA[i4], fArrA2[iArr2[i6] + i4] * f3);
                }
                i4++;
            }
            rectF2.set(fArrA3[0], fArrA3[2], fArrA3[1], fArrA3[3]);
        }

        public static float a(float f, float f2, float f3) {
            return Math.min(Math.max(f, f2), f3);
        }

        public static float[] a(RectF rectF, float f, float f2) {
            return new float[]{rectF.left + f, rectF.right - f, rectF.top + f2, rectF.bottom - f2};
        }

        public static boolean a(RectF rectF, float f, float f2, float f3) {
            return rectF.left + f < f2 && rectF.right - f > f2 && rectF.top + f < f3 && rectF.bottom - f > f3;
        }

        public static EnumC0172a a(int i) {
            for (EnumC0172a enumC0172a : values()) {
                if (enumC0172a.f6538a == i) {
                    return enumC0172a;
                }
            }
            return null;
        }
    }
}
