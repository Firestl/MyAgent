package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class jx extends tx {
    public final int[] h = new int[4];

    @Override // supwisdom.tx
    public int a(ew ewVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iC = ewVar.c();
        int i = iArr[1];
        for (int i2 = 0; i2 < 4 && i < iC; i2++) {
            sb.append((char) (tx.a(ewVar, iArr2, i, tx.f) + 48));
            for (int i3 : iArr2) {
                i += i3;
            }
        }
        int i4 = tx.a(ewVar, i, true, tx.f9326e)[1];
        for (int i5 = 0; i5 < 4 && i4 < iC; i5++) {
            sb.append((char) (tx.a(ewVar, iArr2, i4, tx.f) + 48));
            for (int i6 : iArr2) {
                i4 += i6;
            }
        }
        return i4;
    }

    @Override // supwisdom.tx
    public BarcodeFormat a() {
        return BarcodeFormat.EAN_8;
    }
}
