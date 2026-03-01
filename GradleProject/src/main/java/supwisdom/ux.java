package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class ux extends tx {
    public static final int[] i = {1, 1, 1, 1, 1, 1};
    public static final int[][] j = {new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};
    public final int[] h = new int[4];

    public static String b(String str) {
        char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        StringBuilder sb = new StringBuilder(12);
        sb.append(str.charAt(0));
        char c = cArr[5];
        switch (c) {
            case '0':
            case '1':
            case '2':
                sb.append(cArr, 0, 2);
                sb.append(c);
                sb.append("0000");
                sb.append(cArr, 2, 3);
                break;
            case '3':
                sb.append(cArr, 0, 3);
                sb.append("00000");
                sb.append(cArr, 3, 2);
                break;
            case '4':
                sb.append(cArr, 0, 4);
                sb.append("00000");
                sb.append(cArr[4]);
                break;
            default:
                sb.append(cArr, 0, 5);
                sb.append("0000");
                sb.append(c);
                break;
        }
        sb.append(str.charAt(7));
        return sb.toString();
    }

    @Override // supwisdom.tx
    public boolean a(String str) throws FormatException {
        return super.a(b(str));
    }

    @Override // supwisdom.tx
    public int[] a(ew ewVar, int i2) throws NotFoundException {
        return tx.a(ewVar, i2, true, i);
    }

    @Override // supwisdom.tx
    public int a(ew ewVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iC = ewVar.c();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; i4 < 6 && i2 < iC; i4++) {
            int iA = tx.a(ewVar, iArr2, i2, tx.g);
            sb.append((char) ((iA % 10) + 48));
            for (int i5 : iArr2) {
                i2 += i5;
            }
            if (iA >= 10) {
                i3 |= 1 << (5 - i4);
            }
        }
        a(sb, i3);
        return i2;
    }

    public static void a(StringBuilder sb, int i2) throws NotFoundException {
        for (int i3 = 0; i3 <= 1; i3++) {
            for (int i4 = 0; i4 < 10; i4++) {
                if (i2 == j[i3][i4]) {
                    sb.insert(0, (char) (i3 + 48));
                    sb.append((char) (i4 + 48));
                    return;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.tx
    public BarcodeFormat a() {
        return BarcodeFormat.UPC_E;
    }
}
