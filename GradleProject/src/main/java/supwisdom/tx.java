package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ReaderException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class tx extends ox {
    public static final int[] d = {1, 1, 1};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f9326e = {1, 1, 1, 1, 1};
    public static final int[][] f;
    public static final int[][] g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f9327a = new StringBuilder(20);
    public final sx b = new sx();
    public final kx c = new kx();

    static {
        int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        f = iArr;
        int[][] iArr2 = new int[20][];
        g = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr3 = f[i - 10];
            int[] iArr4 = new int[iArr3.length];
            for (int i2 = 0; i2 < iArr3.length; i2++) {
                iArr4[i2] = iArr3[(iArr3.length - i2) - 1];
            }
            g[i] = iArr4;
        }
    }

    public abstract int a(ew ewVar, int[] iArr, StringBuilder sb) throws NotFoundException;

    public abstract BarcodeFormat a();

    public boolean a(String str) throws FormatException {
        return a((CharSequence) str);
    }

    public static boolean a(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            int iCharAt = charSequence.charAt(i2) - '0';
            if (iCharAt < 0 || iCharAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i += iCharAt;
        }
        int i3 = i * 3;
        for (int i4 = length - 1; i4 >= 0; i4 -= 2) {
            int iCharAt2 = charSequence.charAt(i4) - '0';
            if (iCharAt2 < 0 || iCharAt2 > 9) {
                throw FormatException.getFormatInstance();
            }
            i3 += iCharAt2;
        }
        return i3 % 10 == 0;
    }

    public static int a(ew ewVar, int[] iArr, int i, int[][] iArr2) throws NotFoundException {
        ox.a(ewVar, i, iArr);
        int length = iArr2.length;
        float f2 = 0.48f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float fA = ox.a(iArr, iArr2[i3], 0.7f);
            if (fA < f2) {
                i2 = i3;
                f2 = fA;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public int[] a(ew ewVar, int i) throws NotFoundException {
        return a(ewVar, i, false, d);
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        return a(i, ewVar, a(ewVar), map);
    }

    public static int[] a(ew ewVar, int i, boolean z, int[] iArr) throws NotFoundException {
        return a(ewVar, i, z, iArr, new int[iArr.length]);
    }

    public static int[] a(ew ewVar) throws NotFoundException {
        int[] iArr = new int[d.length];
        int[] iArrA = null;
        boolean zA = false;
        int i = 0;
        while (!zA) {
            int[] iArr2 = d;
            Arrays.fill(iArr, 0, iArr2.length, 0);
            iArrA = a(ewVar, i, false, iArr2, iArr);
            int i2 = iArrA[0];
            int i3 = iArrA[1];
            int i4 = i2 - (i3 - i2);
            if (i4 >= 0) {
                zA = ewVar.a(i4, i2, false);
            }
            i = i3;
        }
        return iArrA;
    }

    public xv a(int i, ew ewVar, int[] iArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        int length;
        String strA;
        zv zvVar = map == null ? null : (zv) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        boolean z = true;
        if (zvVar != null) {
            zvVar.foundPossibleResultPoint(new yv((iArr[0] + iArr[1]) / 2.0f, i));
        }
        StringBuilder sb = this.f9327a;
        sb.setLength(0);
        int iA = a(ewVar, iArr, sb);
        if (zvVar != null) {
            zvVar.foundPossibleResultPoint(new yv(iA, i));
        }
        int[] iArrA = a(ewVar, iA);
        if (zvVar != null) {
            zvVar.foundPossibleResultPoint(new yv((iArrA[0] + iArrA[1]) / 2.0f, i));
        }
        int i2 = iArrA[1];
        int i3 = (i2 - iArrA[0]) + i2;
        if (i3 < ewVar.c() && ewVar.a(i2, i3, false)) {
            String string = sb.toString();
            if (string.length() >= 8) {
                if (a(string)) {
                    BarcodeFormat barcodeFormatA = a();
                    float f2 = i;
                    xv xvVar = new xv(string, null, new yv[]{new yv((iArr[1] + iArr[0]) / 2.0f, f2), new yv((iArrA[1] + iArrA[0]) / 2.0f, f2)}, barcodeFormatA);
                    try {
                        xv xvVarA = this.b.a(i, ewVar, iArrA[1]);
                        xvVar.a(ResultMetadataType.UPC_EAN_EXTENSION, xvVarA.e());
                        xvVar.a(xvVarA.c());
                        xvVar.a(xvVarA.d());
                        length = xvVarA.e().length();
                    } catch (ReaderException unused) {
                        length = 0;
                    }
                    int[] iArr2 = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS) : null;
                    if (iArr2 != null) {
                        int length2 = iArr2.length;
                        int i4 = 0;
                        while (true) {
                            if (i4 >= length2) {
                                z = false;
                                break;
                            }
                            if (length == iArr2[i4]) {
                                break;
                            }
                            i4++;
                        }
                        if (!z) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if ((barcodeFormatA == BarcodeFormat.EAN_13 || barcodeFormatA == BarcodeFormat.UPC_A) && (strA = this.c.a(string)) != null) {
                        xvVar.a(ResultMetadataType.POSSIBLE_COUNTRY, strA);
                    }
                    return xvVar;
                }
                throw ChecksumException.getChecksumInstance();
            }
            throw FormatException.getFormatInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static int[] a(ew ewVar, int i, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int length = iArr.length;
        int iC = ewVar.c();
        int iC2 = z ? ewVar.c(i) : ewVar.b(i);
        boolean z2 = z;
        int i2 = 0;
        int i3 = iC2;
        while (iC2 < iC) {
            if (ewVar.a(iC2) ^ z2) {
                iArr2[i2] = iArr2[i2] + 1;
            } else {
                int i4 = length - 1;
                if (i2 != i4) {
                    i2++;
                } else {
                    if (ox.a(iArr2, iArr, 0.7f) < 0.48f) {
                        return new int[]{i3, iC2};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i5 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i2--;
                }
                iArr2[i2] = 1;
                z2 = !z2;
            }
            iC2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
