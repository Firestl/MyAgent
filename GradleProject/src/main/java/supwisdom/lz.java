package supwisdom;

import com.dcloud.zxing2.ChecksumException;

/* JADX INFO: loaded from: classes.dex */
public final class lz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final mz f8332a = mz.f;

    public int a(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        nz nzVar = new nz(this.f8332a, iArr);
        int[] iArr3 = new int[i];
        boolean z = false;
        for (int i2 = i; i2 > 0; i2--) {
            int iA = nzVar.a(this.f8332a.a(i2));
            iArr3[i - i2] = iA;
            if (iA != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        nz nzVarA = this.f8332a.a();
        if (iArr2 != null) {
            for (int i3 : iArr2) {
                int iA2 = this.f8332a.a((iArr.length - 1) - i3);
                mz mzVar = this.f8332a;
                nzVarA = nzVarA.b(new nz(mzVar, new int[]{mzVar.d(0, iA2), 1}));
            }
        }
        nz[] nzVarArrA = a(this.f8332a.b(i, 1), new nz(this.f8332a, iArr3), i);
        nz nzVar2 = nzVarArrA[0];
        nz nzVar3 = nzVarArrA[1];
        int[] iArrA = a(nzVar2);
        int[] iArrA2 = a(nzVar3, nzVar2, iArrA);
        for (int i4 = 0; i4 < iArrA.length; i4++) {
            int length = (iArr.length - 1) - this.f8332a.c(iArrA[i4]);
            if (length < 0) {
                throw ChecksumException.getChecksumInstance();
            }
            iArr[length] = this.f8332a.d(iArr[length], iArrA2[i4]);
        }
        return iArrA.length;
    }

    public final int[] a(nz nzVar) throws ChecksumException {
        int iA = nzVar.a();
        int[] iArr = new int[iA];
        int i = 0;
        for (int i2 = 1; i2 < this.f8332a.b() && i < iA; i2++) {
            if (nzVar.a(i2) == 0) {
                iArr[i] = this.f8332a.b(i2);
                i++;
            }
        }
        if (i == iA) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    public final int[] a(nz nzVar, nz nzVar2, int[] iArr) {
        int iA = nzVar2.a();
        int[] iArr2 = new int[iA];
        for (int i = 1; i <= iA; i++) {
            iArr2[iA - i] = this.f8332a.c(i, nzVar2.b(i));
        }
        nz nzVar3 = new nz(this.f8332a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int iB = this.f8332a.b(iArr[i2]);
            iArr3[i2] = this.f8332a.c(this.f8332a.d(0, nzVar.a(iB)), this.f8332a.b(nzVar3.a(iB)));
        }
        return iArr3;
    }

    public final nz[] a(nz nzVar, nz nzVar2, int i) throws ChecksumException {
        if (nzVar.a() >= nzVar2.a()) {
            nzVar2 = nzVar;
            nzVar = nzVar2;
        }
        nz nzVarC = this.f8332a.c();
        nz nzVarA = this.f8332a.a();
        while (nzVar.a() >= i / 2) {
            if (!nzVar.b()) {
                nz nzVarC2 = this.f8332a.c();
                int iB = this.f8332a.b(nzVar.b(nzVar.a()));
                while (nzVar2.a() >= nzVar.a() && !nzVar2.b()) {
                    int iA = nzVar2.a() - nzVar.a();
                    int iC = this.f8332a.c(nzVar2.b(nzVar2.a()), iB);
                    nzVarC2 = nzVarC2.a(this.f8332a.b(iA, iC));
                    nzVar2 = nzVar2.c(nzVar.a(iA, iC));
                }
                nz nzVarC3 = nzVarC2.b(nzVarA).c(nzVarC).c();
                nz nzVar3 = nzVar2;
                nzVar2 = nzVar;
                nzVar = nzVar3;
                nzVarC = nzVarA;
                nzVarA = nzVarC3;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        int iB2 = nzVarA.b(0);
        if (iB2 != 0) {
            int iB3 = this.f8332a.b(iB2);
            return new nz[]{nzVarA.c(iB3), nzVar.c(iB3)};
        }
        throw ChecksumException.getChecksumInstance();
    }
}
