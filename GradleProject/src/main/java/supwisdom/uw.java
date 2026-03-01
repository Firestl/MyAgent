package supwisdom;

import com.dcloud.zxing2.FormatException;

/* JADX INFO: loaded from: classes.dex */
public final class uw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f9448a;
    public final fw b;
    public final yw c;

    public uw(fw fwVar) throws FormatException {
        int iC = fwVar.c();
        if (iC < 8 || iC > 144 || (iC & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.c = b(fwVar);
        fw fwVarA = a(fwVar);
        this.f9448a = fwVarA;
        this.b = new fw(fwVarA.e(), fwVarA.c());
    }

    public fw a(fw fwVar) {
        int iE = this.c.e();
        int iD = this.c.d();
        if (fwVar.c() != iE) {
            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
        }
        int iB = this.c.b();
        int iA = this.c.a();
        int i = iE / iB;
        int i2 = iD / iA;
        fw fwVar2 = new fw(i2 * iA, i * iB);
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * iB;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = i5 * iA;
                for (int i7 = 0; i7 < iB; i7++) {
                    int i8 = ((iB + 2) * i3) + 1 + i7;
                    int i9 = i4 + i7;
                    for (int i10 = 0; i10 < iA; i10++) {
                        if (fwVar.b(((iA + 2) * i5) + 1 + i10, i8)) {
                            fwVar2.c(i6 + i10, i9);
                        }
                    }
                }
            }
        }
        return fwVar2;
    }

    public byte[] b() throws FormatException {
        byte[] bArr = new byte[this.c.f()];
        int iC = this.f9448a.c();
        int iE = this.f9448a.e();
        int i = 0;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i3 = 4;
        while (true) {
            if (i3 == iC && i == 0 && !z) {
                bArr[i2] = (byte) a(iC, iE);
                i3 -= 2;
                i += 2;
                i2++;
                z = true;
            } else {
                int i4 = iC - 2;
                if (i3 == i4 && i == 0 && (iE & 3) != 0 && !z2) {
                    bArr[i2] = (byte) b(iC, iE);
                    i3 -= 2;
                    i += 2;
                    i2++;
                    z2 = true;
                } else if (i3 == iC + 4 && i == 2 && (iE & 7) == 0 && !z3) {
                    bArr[i2] = (byte) c(iC, iE);
                    i3 -= 2;
                    i += 2;
                    i2++;
                    z3 = true;
                } else if (i3 == i4 && i == 0 && (iE & 7) == 4 && !z4) {
                    bArr[i2] = (byte) d(iC, iE);
                    i3 -= 2;
                    i += 2;
                    i2++;
                    z4 = true;
                } else {
                    do {
                        if (i3 < iC && i >= 0 && !this.b.b(i, i3)) {
                            bArr[i2] = (byte) b(i3, i, iC, iE);
                            i2++;
                        }
                        i3 -= 2;
                        i += 2;
                        if (i3 < 0) {
                            break;
                        }
                    } while (i < iE);
                    int i5 = i3 + 1;
                    int i6 = i + 3;
                    do {
                        if (i5 >= 0 && i6 < iE && !this.b.b(i6, i5)) {
                            bArr[i2] = (byte) b(i5, i6, iC, iE);
                            i2++;
                        }
                        i5 += 2;
                        i6 -= 2;
                        if (i5 >= iC) {
                            break;
                        }
                    } while (i6 >= 0);
                    i3 = i5 + 3;
                    i = i6 + 1;
                }
            }
            if (i3 >= iC && i >= iE) {
                break;
            }
        }
        if (i2 == this.c.f()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    public int c(int i, int i2) {
        int i3 = i - 1;
        int i4 = (a(i3, 0, i, i2) ? 1 : 0) << 1;
        int i5 = i2 - 1;
        if (a(i3, i5, i, i2)) {
            i4 |= 1;
        }
        int i6 = i4 << 1;
        int i7 = i2 - 3;
        if (a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        int i9 = i2 - 2;
        if (a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (a(0, i5, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (a(1, i7, i, i2)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (a(1, i9, i, i2)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        return a(1, i5, i, i2) ? i13 | 1 : i13;
    }

    public int d(int i, int i2) {
        int i3 = (a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (a(0, i2 - 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        int i7 = i2 - 1;
        if (a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        if (a(1, i7, i, i2)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        if (a(2, i7, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        return a(3, i7, i, i2) ? i10 | 1 : i10;
    }

    public yw a() {
        return this.c;
    }

    public int a(int i, int i2) {
        int i3 = i - 1;
        int i4 = (a(i3, 0, i, i2) ? 1 : 0) << 1;
        if (a(i3, 1, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (a(i3, 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (a(0, i2 - 2, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        int i8 = i2 - 1;
        if (a(0, i8, i, i2)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (a(1, i8, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (a(2, i8, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        return a(3, i8, i, i2) ? i11 | 1 : i11;
    }

    public int b(int i, int i2) {
        int i3 = (a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (a(0, i2 - 4, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (a(0, i2 - 3, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (a(0, i2 - 2, i, i2)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i2 - 1;
        if (a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        return a(1, i9, i, i2) ? i10 | 1 : i10;
    }

    public boolean a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += i3;
            i2 += 4 - ((i3 + 4) & 7);
        }
        if (i2 < 0) {
            i2 += i4;
            i += 4 - ((i4 + 4) & 7);
        }
        this.b.c(i2, i);
        return this.f9448a.b(i2, i);
    }

    public int b(int i, int i2, int i3, int i4) {
        int i5 = i - 2;
        int i6 = i2 - 2;
        int i7 = (a(i5, i6, i3, i4) ? 1 : 0) << 1;
        int i8 = i2 - 1;
        if (a(i5, i8, i3, i4)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        int i10 = i - 1;
        if (a(i10, i6, i3, i4)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (a(i10, i8, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (a(i10, i2, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (a(i, i6, i3, i4)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (a(i, i8, i3, i4)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        return a(i, i2, i3, i4) ? i15 | 1 : i15;
    }

    public static yw b(fw fwVar) throws FormatException {
        return yw.a(fwVar.c(), fwVar.e());
    }
}
