package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class ix extends tx {
    public static final int[] i = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    public final int[] h = new int[4];

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
        int i6 = tx.a(ewVar, i2, true, tx.f9326e)[1];
        for (int i7 = 0; i7 < 6 && i6 < iC; i7++) {
            sb.append((char) (tx.a(ewVar, iArr2, i6, tx.f) + 48));
            for (int i8 : iArr2) {
                i6 += i8;
            }
        }
        return i6;
    }

    public static void a(StringBuilder sb, int i2) throws NotFoundException {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 == i[i3]) {
                sb.insert(0, (char) (i3 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.tx
    public BarcodeFormat a() {
        return BarcodeFormat.EAN_13;
    }
}
