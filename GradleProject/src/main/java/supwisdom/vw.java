package supwisdom;

import supwisdom.yw;

/* JADX INFO: loaded from: classes.dex */
public final class vw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9554a;
    public final byte[] b;

    public vw(int i, byte[] bArr) {
        this.f9554a = i;
        this.b = bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public int b() {
        return this.f9554a;
    }

    public static vw[] a(byte[] bArr, yw ywVar) {
        yw.c cVarC = ywVar.c();
        yw.b[] bVarArrA = cVarC.a();
        int iA = 0;
        for (yw.b bVar : bVarArrA) {
            iA += bVar.a();
        }
        vw[] vwVarArr = new vw[iA];
        int i = 0;
        for (yw.b bVar2 : bVarArrA) {
            int i2 = 0;
            while (i2 < bVar2.a()) {
                int iB = bVar2.b();
                vwVarArr[i] = new vw(iB, new byte[cVarC.b() + iB]);
                i2++;
                i++;
            }
        }
        int length = vwVarArr[0].b.length - cVarC.b();
        int i3 = length - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = 0;
            while (i6 < i) {
                vwVarArr[i6].b[i5] = bArr[i4];
                i6++;
                i4++;
            }
        }
        boolean z = ywVar.g() == 24;
        int i7 = z ? 8 : i;
        int i8 = 0;
        while (i8 < i7) {
            vwVarArr[i8].b[i3] = bArr[i4];
            i8++;
            i4++;
        }
        int length2 = vwVarArr[0].b.length;
        while (length < length2) {
            int i9 = 0;
            while (i9 < i) {
                int i10 = z ? (i9 + 8) % i : i9;
                vwVarArr[i10].b[(!z || i10 <= 7) ? length : length - 1] = bArr[i4];
                i9++;
                i4++;
            }
            length++;
        }
        if (i4 == bArr.length) {
            return vwVarArr;
        }
        throw new IllegalArgumentException();
    }
}
