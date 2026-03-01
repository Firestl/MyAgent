package supwisdom;

import android.graphics.ColorMatrix;

/* JADX INFO: compiled from: ColorFilterGenerator.java */
/* JADX INFO: loaded from: classes2.dex */
public class oj1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float[] f8681a = {0.0f, 0.01f, 0.02f, 0.04f, 0.05f, 0.06f, 0.07f, 0.08f, 0.1f, 0.11f, 0.12f, 0.14f, 0.15f, 0.16f, 0.17f, 0.18f, 0.2f, 0.21f, 0.22f, 0.24f, 0.25f, 0.27f, 0.28f, 0.3f, 0.32f, 0.34f, 0.36f, 0.38f, 0.4f, 0.42f, 0.44f, 0.46f, 0.48f, 0.5f, 0.53f, 0.56f, 0.59f, 0.62f, 0.65f, 0.68f, 0.71f, 0.74f, 0.77f, 0.8f, 0.83f, 0.86f, 0.89f, 0.92f, 0.95f, 0.98f, 1.0f, 1.06f, 1.12f, 1.18f, 1.24f, 1.3f, 1.36f, 1.42f, 1.48f, 1.54f, 1.6f, 1.66f, 1.72f, 1.78f, 1.84f, 1.9f, 1.96f, 2.0f, 2.12f, 2.25f, 2.37f, 2.5f, 2.62f, 2.75f, 2.87f, 3.0f, 3.2f, 3.4f, 3.6f, 3.8f, 4.0f, 4.3f, 4.7f, 4.9f, 5.0f, 5.5f, 6.0f, 6.5f, 6.8f, 7.0f, 7.3f, 7.5f, 7.8f, 8.0f, 8.4f, 8.7f, 9.0f, 9.4f, 9.6f, 9.8f, 10.0f};

    public static void a(ColorMatrix colorMatrix, float f, float f2, float f3, float f4) {
        c(colorMatrix, f4);
        b(colorMatrix, f2);
        a(colorMatrix, f);
        d(colorMatrix, f3);
    }

    public static void b(ColorMatrix colorMatrix, float f) {
        float f2;
        float fA = a(f, 100.0f);
        if (fA == 0.0f || Float.isNaN(fA)) {
            return;
        }
        if (fA < 0.0f) {
            f2 = fA / 100.0f;
        } else {
            float f3 = fA % 1.0f;
            if (f3 == 0.0f) {
                f2 = f8681a[(int) fA];
            } else {
                float[] fArr = f8681a;
                int i = ((int) fA) << 0;
                f2 = (fArr[i + 1] * f3) + (fArr[i] * (1.0f - f3));
            }
        }
        float f4 = (f2 * 127.0f) + 127.0f;
        float f5 = f4 / 127.0f;
        float f6 = (127.0f - f4) * 0.5f;
        colorMatrix.postConcat(new ColorMatrix(new float[]{f5, 0.0f, 0.0f, 0.0f, f6, 0.0f, f5, 0.0f, 0.0f, f6, 0.0f, 0.0f, f5, 0.0f, f6, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}));
    }

    public static void c(ColorMatrix colorMatrix, float f) {
        float fA = (a(f, 180.0f) / 180.0f) * 3.1415927f;
        if (fA == 0.0f) {
            return;
        }
        double d = fA;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = (fCos * (-0.715f)) + 0.715f;
        float f3 = ((-0.072f) * fCos) + 0.072f;
        float f4 = ((-0.213f) * fCos) + 0.213f;
        colorMatrix.postConcat(new ColorMatrix(new float[]{(0.787f * fCos) + 0.213f + (fSin * (-0.213f)), ((-0.715f) * fSin) + f2, (fSin * 0.928f) + f3, 0.0f, 0.0f, (0.143f * fSin) + f4, (0.28500003f * fCos) + 0.715f + (0.14f * fSin), f3 + ((-0.283f) * fSin), 0.0f, 0.0f, f4 + ((-0.787f) * fSin), f2 + (0.715f * fSin), (fCos * 0.928f) + 0.072f + (fSin * 0.072f), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}));
    }

    public static void d(ColorMatrix colorMatrix, float f) {
        float fA = a(f, 100.0f);
        if (fA == 0.0f || Float.isNaN(fA)) {
            return;
        }
        if (fA > 0.0f) {
            fA *= 3.0f;
        }
        float f2 = (fA / 100.0f) + 1.0f;
        float f3 = 1.0f - f2;
        float f4 = 0.3086f * f3;
        float f5 = 0.6094f * f3;
        float f6 = f3 * 0.082f;
        colorMatrix.postConcat(new ColorMatrix(new float[]{f4 + f2, f5, f6, 0.0f, 0.0f, f4, f5 + f2, f6, 0.0f, 0.0f, f4, f5, f6 + f2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}));
    }

    public static void a(ColorMatrix colorMatrix, float f) {
        float fA = a(f, 100.0f);
        if (fA == 0.0f || Float.isNaN(fA)) {
            return;
        }
        colorMatrix.postConcat(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, fA, 0.0f, 1.0f, 0.0f, 0.0f, fA, 0.0f, 0.0f, 1.0f, 0.0f, fA, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}));
    }

    public static float a(float f, float f2) {
        return Math.min(f2, Math.max(-f2, f));
    }
}
