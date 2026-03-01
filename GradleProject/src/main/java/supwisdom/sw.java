package supwisdom;

import com.dcloud.zxing2.common.reedsolomon.ReedSolomonException;

/* JADX INFO: loaded from: classes.dex */
public final class sw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final qw f9217a;

    public sw(qw qwVar) {
        this.f9217a = qwVar;
    }

    public void a(int[] iArr, int i) throws ReedSolomonException {
        rw rwVar = new rw(this.f9217a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            qw qwVar = this.f9217a;
            int iA = rwVar.a(qwVar.a(qwVar.a() + i2));
            iArr2[(i - 1) - i2] = iA;
            if (iA != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        rw[] rwVarArrA = a(this.f9217a.a(i, 1), new rw(this.f9217a, iArr2), i);
        rw rwVar2 = rwVarArrA[0];
        rw rwVar3 = rwVarArrA[1];
        int[] iArrA = a(rwVar2);
        int[] iArrA2 = a(rwVar3, iArrA);
        for (int i3 = 0; i3 < iArrA.length; i3++) {
            int length = (iArr.length - 1) - this.f9217a.c(iArrA[i3]);
            if (length < 0) {
                throw new ReedSolomonException("Bad error location");
            }
            iArr[length] = qw.c(iArr[length], iArrA2[i3]);
        }
    }

    public final int[] a(rw rwVar) throws ReedSolomonException {
        int iA = rwVar.a();
        int i = 0;
        if (iA == 1) {
            return new int[]{rwVar.b(1)};
        }
        int[] iArr = new int[iA];
        for (int i2 = 1; i2 < this.f9217a.c() && i < iA; i2++) {
            if (rwVar.a(i2) == 0) {
                iArr[i] = this.f9217a.b(i2);
                i++;
            }
        }
        if (i == iA) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    public final int[] a(rw rwVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int iB = this.f9217a.b(iArr[i]);
            int iB2 = 1;
            for (int i2 = 0; i2 < length; i2++) {
                if (i != i2) {
                    int iB3 = this.f9217a.b(iArr[i2], iB);
                    iB2 = this.f9217a.b(iB2, (iB3 & 1) == 0 ? iB3 | 1 : iB3 & (-2));
                }
            }
            iArr2[i] = this.f9217a.b(rwVar.a(iB), this.f9217a.b(iB2));
            if (this.f9217a.a() != 0) {
                iArr2[i] = this.f9217a.b(iArr2[i], iB);
            }
        }
        return iArr2;
    }

    public final rw[] a(rw rwVar, rw rwVar2, int i) throws ReedSolomonException {
        if (rwVar.a() >= rwVar2.a()) {
            rwVar2 = rwVar;
            rwVar = rwVar2;
        }
        rw rwVarD = this.f9217a.d();
        rw rwVarB = this.f9217a.b();
        while (rwVar.a() >= i / 2) {
            if (!rwVar.b()) {
                rw rwVarD2 = this.f9217a.d();
                int iB = this.f9217a.b(rwVar.b(rwVar.a()));
                while (rwVar2.a() >= rwVar.a() && !rwVar2.b()) {
                    int iA = rwVar2.a() - rwVar.a();
                    int iB2 = this.f9217a.b(rwVar2.b(rwVar2.a()), iB);
                    rwVarD2 = rwVarD2.a(this.f9217a.a(iA, iB2));
                    rwVar2 = rwVar2.a(rwVar.a(iA, iB2));
                }
                rw rwVarA = rwVarD2.b(rwVarB).a(rwVarD);
                if (rwVar2.a() >= rwVar.a()) {
                    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
                rw rwVar3 = rwVar2;
                rwVar2 = rwVar;
                rwVar = rwVar3;
                rwVarD = rwVarB;
                rwVarB = rwVarA;
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        }
        int iB3 = rwVarB.b(0);
        if (iB3 != 0) {
            int iB4 = this.f9217a.b(iB3);
            return new rw[]{rwVarB.c(iB4), rwVar.c(iB4)};
        }
        throw new ReedSolomonException("sigmaTilde(0) was zero");
    }
}
