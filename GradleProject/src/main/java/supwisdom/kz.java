package supwisdom;

import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class kz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final lz f8221a = new lz();

    public static dz a(iz izVar) throws FormatException, NotFoundException {
        int[] iArrD;
        if (izVar == null || (iArrD = izVar.d()) == null) {
            return null;
        }
        int iB = b(iArrD);
        int i = 0;
        int i2 = 0;
        for (int i3 : iArrD) {
            i2 += iB - i3;
            if (i3 > 0) {
                break;
            }
        }
        ez[] ezVarArrB = izVar.b();
        for (int i4 = 0; i2 > 0 && ezVarArrB[i4] == null; i4++) {
            i2--;
        }
        for (int length = iArrD.length - 1; length >= 0; length--) {
            i += iB - iArrD[length];
            if (iArrD[length] > 0) {
                break;
            }
        }
        for (int length2 = ezVarArrB.length - 1; i > 0 && ezVarArrB[length2] == null; length2--) {
            i--;
        }
        return izVar.a().a(i2, i, izVar.e());
    }

    public static boolean a(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    public static hw b(gz gzVar) throws ChecksumException, FormatException, NotFoundException {
        cz[][] czVarArrA = a(gzVar);
        a(gzVar, czVarArrA);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[gzVar.h() * gzVar.f()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < gzVar.h(); i++) {
            int i2 = 0;
            while (i2 < gzVar.f()) {
                int i3 = i2 + 1;
                int[] iArrA = czVarArrA[i][i3].a();
                int iF = (gzVar.f() * i) + i2;
                if (iArrA.length == 0) {
                    arrayList.add(Integer.valueOf(iF));
                } else if (iArrA.length == 1) {
                    iArr[iF] = iArrA[0];
                } else {
                    arrayList3.add(Integer.valueOf(iF));
                    arrayList2.add(iArrA);
                }
                i2 = i3;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size][];
        for (int i4 = 0; i4 < size; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return a(gzVar.g(), iArr, yy.a(arrayList), yy.a(arrayList3), iArr2);
    }

    public static int c(int i) {
        return 2 << i;
    }

    public static void a(gz gzVar, cz[][] czVarArr) throws NotFoundException {
        int[] iArrA = czVarArr[0][1].a();
        int iF = (gzVar.f() * gzVar.h()) - c(gzVar.g());
        if (iArrA.length != 0) {
            if (iArrA[0] != iF) {
                czVarArr[0][1].a(iF);
            }
        } else {
            if (iF >= 1 && iF <= 928) {
                czVarArr[0][1].a(iF);
                return;
            }
            throw NotFoundException.getNotFoundInstance();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0023, code lost:
    
        r0 = -r0;
        r8 = !r8;
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(supwisdom.fw r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r9
        L7:
            r3 = 2
            if (r1 >= r3) goto L29
        La:
            if (r8 == 0) goto Le
            if (r2 >= r6) goto L12
        Le:
            if (r8 != 0) goto L23
            if (r2 >= r7) goto L23
        L12:
            boolean r4 = r5.b(r2, r10)
            if (r8 != r4) goto L23
            int r4 = r9 - r2
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L21
            return r9
        L21:
            int r2 = r2 + r0
            goto La
        L23:
            int r0 = -r0
            r8 = r8 ^ 1
            int r1 = r1 + 1
            goto L7
        L29:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.kz.a(supwisdom.fw, int, int, boolean, int, int):int");
    }

    public static int b(int i) {
        return a(a(i));
    }

    public static int b(int[] iArr) {
        int iMax = -1;
        for (int i : iArr) {
            iMax = Math.max(iMax, i);
        }
        return iMax;
    }

    public static int a(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return f8221a.a(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    public static gz b(iz izVar, iz izVar2) throws FormatException, NotFoundException {
        bz bzVarA;
        if ((izVar == null && izVar2 == null) || (bzVarA = a(izVar, izVar2)) == null) {
            return null;
        }
        return new gz(bzVarA, dz.a(a(izVar), a(izVar2)));
    }

    public static cz[][] a(gz gzVar) throws FormatException {
        int iC;
        cz[][] czVarArr = (cz[][]) Array.newInstance((Class<?>) cz.class, gzVar.h(), gzVar.f() + 2);
        for (int i = 0; i < czVarArr.length; i++) {
            for (int i2 = 0; i2 < czVarArr[i].length; i2++) {
                czVarArr[i][i2] = new cz();
            }
        }
        int i3 = 0;
        for (hz hzVar : gzVar.j()) {
            if (hzVar != null) {
                for (ez ezVar : hzVar.b()) {
                    if (ezVar != null && (iC = ezVar.c()) >= 0 && iC < czVarArr.length) {
                        czVarArr[iC][i3].a(ezVar.e());
                    }
                }
            }
            i3++;
        }
        return czVarArr;
    }

    public static int[] b(fw fwVar, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        int i6 = 0;
        boolean z2 = z;
        while (true) {
            if (((!z || i3 >= i2) && (z || i3 < i)) || i6 >= 8) {
                break;
            }
            if (fwVar.b(i3, i4) == z2) {
                iArr[i6] = iArr[i6] + 1;
                i3 += i5;
            } else {
                i6++;
                z2 = !z2;
            }
        }
        if (i6 == 8 || (((z && i3 == i2) || (!z && i3 == i)) && i6 == 7)) {
            return iArr;
        }
        return null;
    }

    public static hw a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws ChecksumException, FormatException {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (int i4 = 0; i4 < length; i4++) {
                    iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
                }
                try {
                    return a(iArr, i, iArr2);
                } catch (ChecksumException unused) {
                    if (length == 0) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    int i5 = 0;
                    while (true) {
                        if (i5 >= length) {
                            break;
                        }
                        if (iArr5[i5] < iArr4[i5].length - 1) {
                            iArr5[i5] = iArr5[i5] + 1;
                            break;
                        }
                        iArr5[i5] = 0;
                        if (i5 == length - 1) {
                            throw ChecksumException.getChecksumInstance();
                        }
                        i5++;
                    }
                    i2 = i3;
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public static hw a(fw fwVar, yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4, int i, int i2) throws ChecksumException, FormatException, NotFoundException {
        hz izVar;
        int i3;
        int i4;
        int i5;
        iz izVarA = null;
        iz izVarA2 = null;
        gz gzVarB = null;
        dz dzVar = new dz(fwVar, yvVar, yvVar2, yvVar3, yvVar4);
        for (int i6 = 0; i6 < 2; i6++) {
            if (yvVar != null) {
                izVarA = a(fwVar, dzVar, yvVar, true, i, i2);
            }
            if (yvVar3 != null) {
                izVarA2 = a(fwVar, dzVar, yvVar3, false, i, i2);
            }
            gzVarB = b(izVarA, izVarA2);
            if (gzVarB != null) {
                if (i6 == 0 && gzVarB.i() != null && (gzVarB.i().g() < dzVar.g() || gzVarB.i().e() > dzVar.e())) {
                    dzVar = gzVarB.i();
                } else {
                    gzVarB.a(dzVar);
                    break;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        int iF = gzVarB.f() + 1;
        gzVarB.a(0, izVarA);
        gzVarB.a(iF, izVarA2);
        boolean z = izVarA != null;
        int iMin = i;
        int iMax = i2;
        for (int i7 = 1; i7 <= iF; i7++) {
            int i8 = z ? i7 : iF - i7;
            if (gzVarB.a(i8) == null) {
                if (i8 != 0 && i8 != iF) {
                    izVar = new hz(dzVar);
                } else {
                    izVar = new iz(dzVar, i8 == 0);
                }
                gzVarB.a(i8, izVar);
                int i9 = -1;
                int iG = dzVar.g();
                int i10 = -1;
                while (iG <= dzVar.e()) {
                    int iA = a(gzVarB, i8, iG, z);
                    if (iA >= 0 && iA <= dzVar.d()) {
                        i3 = iA;
                    } else if (i10 == i9) {
                        i4 = i10;
                        i5 = iG;
                        i10 = i4;
                        iG = i5 + 1;
                        i9 = -1;
                    } else {
                        i3 = i10;
                    }
                    i4 = i10;
                    int i11 = iG;
                    ez ezVarA = a(fwVar, dzVar.f(), dzVar.d(), z, i3, i11, iMin, iMax);
                    i5 = i11;
                    if (ezVarA != null) {
                        izVar.a(i5, ezVarA);
                        iMin = Math.min(iMin, ezVarA.f());
                        iMax = Math.max(iMax, ezVarA.f());
                        i10 = i3;
                    } else {
                        i10 = i4;
                    }
                    iG = i5 + 1;
                    i9 = -1;
                }
            }
        }
        return b(gzVarB);
    }

    public static hw a(int[] iArr, int i, int[] iArr2) throws ChecksumException, FormatException {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int iA = a(iArr, iArr2, i2);
            a(iArr, i2);
            hw hwVarA = fz.a(iArr, String.valueOf(i));
            hwVarA.b(Integer.valueOf(iA));
            hwVarA.a(Integer.valueOf(iArr2.length));
            return hwVarA;
        }
        throw FormatException.getFormatInstance();
    }

    public static ez a(fw fwVar, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int iD;
        int iA;
        int iA2 = a(fwVar, i, i2, z, i3, i4);
        int[] iArrB = b(fwVar, i, i2, z, iA2, i4);
        if (iArrB == null) {
            return null;
        }
        int iA3 = yy.a(iArrB);
        if (z) {
            i7 = iA2 + iA3;
        } else {
            for (int i8 = 0; i8 < iArrB.length / 2; i8++) {
                int i9 = iArrB[i8];
                iArrB[i8] = iArrB[(iArrB.length - 1) - i8];
                iArrB[(iArrB.length - 1) - i8] = i9;
            }
            iA2 -= iA3;
            i7 = iA2;
        }
        if (a(iA3, i5, i6) && (iA = yy.a((iD = jz.d(iArrB)))) != -1) {
            return new ez(iA2, i7, b(iD), iA);
        }
        return null;
    }

    public static bz a(iz izVar, iz izVar2) {
        bz bzVarC;
        bz bzVarC2;
        if (izVar == null || (bzVarC = izVar.c()) == null) {
            if (izVar2 == null) {
                return null;
            }
            return izVar2.c();
        }
        if (izVar2 == null || (bzVarC2 = izVar2.c()) == null || bzVarC.a() == bzVarC2.a() || bzVarC.b() == bzVarC2.b() || bzVarC.c() == bzVarC2.c()) {
            return bzVarC;
        }
        return null;
    }

    public static iz a(fw fwVar, dz dzVar, yv yvVar, boolean z, int i, int i2) {
        int iB;
        iz izVar = new iz(dzVar, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int iA = (int) yvVar.a();
            for (int iB2 = (int) yvVar.b(); iB2 <= dzVar.e() && iB2 >= dzVar.g(); iB2 += i4) {
                ez ezVarA = a(fwVar, 0, fwVar.e(), z, iA, iB2, i, i2);
                if (ezVarA != null) {
                    izVar.a(iB2, ezVarA);
                    if (z) {
                        iB = ezVarA.d();
                    } else {
                        iB = ezVarA.b();
                    }
                    iA = iB;
                }
            }
            i3++;
        }
        return izVar;
    }

    public static int a(gz gzVar, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        int i4 = i - i3;
        ez ezVarA = a(gzVar, i4) ? gzVar.a(i4).a(i2) : null;
        if (ezVarA != null) {
            return z ? ezVarA.b() : ezVarA.d();
        }
        ez ezVarB = gzVar.a(i).b(i2);
        if (ezVarB != null) {
            return z ? ezVarB.d() : ezVarB.b();
        }
        if (a(gzVar, i4)) {
            ezVarB = gzVar.a(i4).b(i2);
        }
        if (ezVarB != null) {
            return z ? ezVarB.b() : ezVarB.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (!a(gzVar, i)) {
                return z ? gzVar.i().f() : gzVar.i().d();
            }
            for (ez ezVar : gzVar.a(i).b()) {
                if (ezVar != null) {
                    return (z ? ezVar.b() : ezVar.d()) + (i3 * i5 * (ezVar.b() - ezVar.d()));
                }
            }
            i5++;
        }
    }

    public static boolean a(gz gzVar, int i) {
        return i >= 0 && i <= gzVar.f() + 1;
    }

    public static void a(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            }
            if (i2 == 0) {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            return;
        }
        throw FormatException.getFormatInstance();
    }

    public static int a(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    public static int[] a(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }
}
