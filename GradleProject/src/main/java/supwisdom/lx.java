package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class lx extends ox {
    public static final int[] b = {6, 8, 10, 12, 14};
    public static final int[] c = {1, 1, 1, 1};
    public static final int[] d = {1, 1, 3};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[][] f8325e = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8326a = -1;

    public static int a(int[] iArr) throws NotFoundException {
        int length = f8325e.length;
        float f = 0.38f;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            float fA = ox.a(iArr, f8325e[i2], 0.78f);
            if (fA < f) {
                i = i2;
                f = fA;
            }
        }
        if (i >= 0) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static int[] c(ew ewVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int iC = ewVar.c();
        int i2 = i;
        boolean z = false;
        int i3 = 0;
        while (i < iC) {
            if (ewVar.a(i) ^ z) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 != i4) {
                    i3++;
                } else {
                    if (ox.a(iArr2, iArr, 0.78f) < 0.38f) {
                        return new int[]{i2, i};
                    }
                    i2 += iArr2[0] + iArr2[1];
                    int i5 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                z = !z;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public int[] b(ew ewVar) throws NotFoundException {
        int[] iArrC = c(ewVar, c(ewVar), c);
        this.f8326a = (iArrC[1] - iArrC[0]) / 4;
        a(ewVar, iArrC[0]);
        return iArrC;
    }

    public int[] a(ew ewVar) throws NotFoundException {
        ewVar.d();
        try {
            int[] iArrC = c(ewVar, c(ewVar), d);
            a(ewVar, iArrC[0]);
            int i = iArrC[0];
            iArrC[0] = ewVar.c() - iArrC[1];
            iArrC[1] = ewVar.c() - i;
            return iArrC;
        } finally {
            ewVar.d();
        }
    }

    public static int c(ew ewVar) throws NotFoundException {
        int iC = ewVar.c();
        int iB = ewVar.b(0);
        if (iB != iC) {
            return iB;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        boolean z;
        int[] iArrB = b(ewVar);
        int[] iArrA = a(ewVar);
        StringBuilder sb = new StringBuilder(20);
        a(ewVar, iArrB[1], iArrA[0], sb);
        String string = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = b;
        }
        int length = string.length();
        int length2 = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length2) {
                z = false;
                break;
            }
            int i4 = iArr[i2];
            if (length == i4) {
                z = true;
                break;
            }
            if (i4 > i3) {
                i3 = i4;
            }
            i2++;
        }
        if (!z && length > i3) {
            z = true;
        }
        if (z) {
            float f = i;
            return new xv(string, null, new yv[]{new yv(iArrB[1], f), new yv(iArrA[0], f)}, BarcodeFormat.ITF);
        }
        throw FormatException.getFormatInstance();
    }

    public final void a(ew ewVar, int i) throws NotFoundException {
        int i2 = this.f8326a * 10;
        if (i2 >= i) {
            i2 = i;
        }
        for (int i3 = i - 1; i2 > 0 && i3 >= 0 && !ewVar.a(i3); i3--) {
            i2--;
        }
        if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public static void a(ew ewVar, int i, int i2, StringBuilder sb) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            ox.a(ewVar, i, iArr);
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                iArr2[i3] = iArr[i4];
                iArr3[i3] = iArr[i4 + 1];
            }
            sb.append((char) (a(iArr2) + 48));
            sb.append((char) (a(iArr3) + 48));
            for (int i5 = 0; i5 < 10; i5++) {
                i += iArr[i5];
            }
        }
    }
}
