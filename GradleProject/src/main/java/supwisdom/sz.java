package supwisdom;

import com.dcloud.zxing2.qrcode.decoder.ErrorCorrectionLevel;
import supwisdom.yz;

/* JADX INFO: loaded from: classes.dex */
public final class sz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9220a;
    public final byte[] b;

    public sz(int i, byte[] bArr) {
        this.f9220a = i;
        this.b = bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public int b() {
        return this.f9220a;
    }

    public static sz[] a(byte[] bArr, yz yzVar, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length != yzVar.d()) {
            throw new IllegalArgumentException();
        }
        yz.b bVarA = yzVar.a(errorCorrectionLevel);
        yz.a[] aVarArrA = bVarA.a();
        int iA = 0;
        for (yz.a aVar : aVarArrA) {
            iA += aVar.a();
        }
        sz[] szVarArr = new sz[iA];
        int i = 0;
        for (yz.a aVar2 : aVarArrA) {
            int i2 = 0;
            while (i2 < aVar2.a()) {
                int iB = aVar2.b();
                szVarArr[i] = new sz(iB, new byte[bVarA.b() + iB]);
                i2++;
                i++;
            }
        }
        int length = szVarArr[0].b.length;
        int i3 = iA - 1;
        while (i3 >= 0 && szVarArr[i3].b.length != length) {
            i3--;
        }
        int i4 = i3 + 1;
        int iB2 = length - bVarA.b();
        int i5 = 0;
        for (int i6 = 0; i6 < iB2; i6++) {
            int i7 = 0;
            while (i7 < i) {
                szVarArr[i7].b[i6] = bArr[i5];
                i7++;
                i5++;
            }
        }
        int i8 = i4;
        while (i8 < i) {
            szVarArr[i8].b[iB2] = bArr[i5];
            i8++;
            i5++;
        }
        int length2 = szVarArr[0].b.length;
        while (iB2 < length2) {
            int i9 = 0;
            while (i9 < i) {
                szVarArr[i9].b[i9 < i4 ? iB2 : iB2 + 1] = bArr[i5];
                i9++;
                i5++;
            }
            iB2++;
        }
        return szVarArr;
    }
}
