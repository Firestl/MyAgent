package supwisdom;

import android.opengl.GLES10;
import com.nostra13.dcloudimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes2.dex */
public final class zx0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ix0 f10042a;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f10043a;

        static {
            int[] iArr = new int[ViewScaleType.values().length];
            f10043a = iArr;
            try {
                iArr[ViewScaleType.FIT_INSIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10043a[ViewScaleType.CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int iMax = Math.max(iArr[0], 2048);
        f10042a = new ix0(iMax, iMax);
    }

    public static int a(ix0 ix0Var, ix0 ix0Var2, ViewScaleType viewScaleType, boolean z) {
        int iMax;
        int iB = ix0Var.b();
        int iA = ix0Var.a();
        int iB2 = ix0Var2.b();
        int iA2 = ix0Var2.a();
        int i = a.f10043a[viewScaleType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                iMax = 1;
            } else if (z) {
                int i2 = iB / 2;
                int i3 = iA / 2;
                iMax = 1;
                while (i2 / iMax > iB2 && i3 / iMax > iA2) {
                    iMax *= 2;
                }
            } else {
                iMax = Math.min(iB / iB2, iA / iA2);
            }
        } else if (z) {
            int i4 = iB / 2;
            int i5 = iA / 2;
            iMax = 1;
            while (true) {
                if (i4 / iMax <= iB2 && i5 / iMax <= iA2) {
                    break;
                }
                iMax *= 2;
            }
        } else {
            iMax = Math.max(iB / iB2, iA / iA2);
        }
        return a(iB, iA, iMax >= 1 ? iMax : 1, z);
    }

    public static float b(ix0 ix0Var, ix0 ix0Var2, ViewScaleType viewScaleType, boolean z) {
        int iB = ix0Var.b();
        int iA = ix0Var.a();
        int iB2 = ix0Var2.b();
        int iA2 = ix0Var2.a();
        float f = iB;
        float f2 = f / iB2;
        float f3 = iA;
        float f4 = f3 / iA2;
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f2 < f4) && (viewScaleType != ViewScaleType.CROP || f2 >= f4)) {
            iB2 = (int) (f / f4);
        } else {
            iA2 = (int) (f3 / f2);
        }
        if ((z || iB2 >= iB || iA2 >= iA) && (!z || iB2 == iB || iA2 == iA)) {
            return 1.0f;
        }
        return iB2 / f;
    }

    public static int a(int i, int i2, int i3, boolean z) {
        int iB = f10042a.b();
        int iA = f10042a.a();
        while (true) {
            if (i / i3 <= iB && i2 / i3 <= iA) {
                return i3;
            }
            i3 = z ? i3 * 2 : i3 + 1;
        }
    }

    public static ix0 a(wx0 wx0Var, ix0 ix0Var) {
        int iMin;
        int iMin2;
        int width = wx0Var.getWidth();
        if (width <= 0) {
            iMin = ix0Var.b();
        } else {
            iMin = Math.min(width, ix0Var.b());
        }
        int height = wx0Var.getHeight();
        if (height <= 0) {
            iMin2 = ix0Var.a();
        } else {
            iMin2 = Math.min(height, ix0Var.a());
        }
        return new ix0(iMin, iMin2);
    }
}
