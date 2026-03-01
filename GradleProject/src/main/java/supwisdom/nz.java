package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class nz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final mz f8590a;
    public final int[] b;

    public nz(mz mzVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f8590a = mzVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        int i = 1;
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.b = new int[]{0};
            return;
        }
        int i2 = length - i;
        int[] iArr2 = new int[i2];
        this.b = iArr2;
        System.arraycopy(iArr, i, iArr2, 0, i2);
    }

    public nz a(nz nzVar) {
        if (!this.f8590a.equals(nzVar.f8590a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (b()) {
            return nzVar;
        }
        if (nzVar.b()) {
            return this;
        }
        int[] iArr = this.b;
        int[] iArr2 = nzVar.b;
        if (iArr.length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length);
        for (int i = length; i < iArr.length; i++) {
            iArr3[i] = this.f8590a.a(iArr2[i - length], iArr[i]);
        }
        return new nz(this.f8590a, iArr3);
    }

    public int b(int i) {
        return this.b[(r0.length - 1) - i];
    }

    public nz c() {
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.f8590a.d(0, this.b[i]);
        }
        return new nz(this.f8590a, iArr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(a() * 8);
        for (int iA = a(); iA >= 0; iA--) {
            int iB = b(iA);
            if (iB != 0) {
                if (iB < 0) {
                    sb.append(" - ");
                    iB = -iB;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (iA == 0 || iB != 1) {
                    sb.append(iB);
                }
                if (iA != 0) {
                    if (iA == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(iA);
                    }
                }
            }
        }
        return sb.toString();
    }

    public boolean b() {
        return this.b[0] == 0;
    }

    public nz b(nz nzVar) {
        if (this.f8590a.equals(nzVar.f8590a)) {
            if (!b() && !nzVar.b()) {
                int[] iArr = this.b;
                int length = iArr.length;
                int[] iArr2 = nzVar.b;
                int length2 = iArr2.length;
                int[] iArr3 = new int[(length + length2) - 1];
                for (int i = 0; i < length; i++) {
                    int i2 = iArr[i];
                    for (int i3 = 0; i3 < length2; i3++) {
                        int i4 = i + i3;
                        mz mzVar = this.f8590a;
                        iArr3[i4] = mzVar.a(iArr3[i4], mzVar.c(i2, iArr2[i3]));
                    }
                }
                return new nz(this.f8590a, iArr3);
            }
            return this.f8590a.c();
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    public nz c(nz nzVar) {
        if (this.f8590a.equals(nzVar.f8590a)) {
            return nzVar.b() ? this : a(nzVar.c());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    public nz c(int i) {
        if (i == 0) {
            return this.f8590a.c();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f8590a.c(this.b[i2], i);
        }
        return new nz(this.f8590a, iArr);
    }

    public int a(int i) {
        if (i == 0) {
            return b(0);
        }
        int[] iArr = this.b;
        int length = iArr.length;
        if (i == 1) {
            int iA = 0;
            for (int i2 : iArr) {
                iA = this.f8590a.a(iA, i2);
            }
            return iA;
        }
        int iA2 = iArr[0];
        for (int i3 = 1; i3 < length; i3++) {
            mz mzVar = this.f8590a;
            iA2 = mzVar.a(mzVar.c(i, iA2), this.b[i3]);
        }
        return iA2;
    }

    public int a() {
        return this.b.length - 1;
    }

    public nz a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f8590a.c();
        }
        int length = this.b.length;
        int[] iArr = new int[i + length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.f8590a.c(this.b[i3], i2);
        }
        return new nz(this.f8590a, iArr);
    }
}
