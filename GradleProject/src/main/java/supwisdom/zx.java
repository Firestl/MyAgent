package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zx extends vx {
    public static final int[] i = {1, 10, 34, 70, 126};
    public static final int[] j = {4, 20, 48, 81};
    public static final int[] k = {0, 161, 961, 2015, 2715};
    public static final int[] l = {0, 336, 1036, 1516};
    public static final int[] m = {8, 6, 4, 3, 1};
    public static final int[] n = {2, 4, 6, 8};
    public static final int[][] o = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    public final List<yx> g = new ArrayList();
    public final List<yx> h = new ArrayList();

    public static void a(Collection<yx> collection, yx yxVar) {
        if (yxVar == null) {
            return;
        }
        boolean z = false;
        Iterator<yx> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            yx next = it.next();
            if (next.b() == yxVar.b()) {
                next.e();
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(yxVar);
    }

    public static xv b(yx yxVar, yx yxVar2) {
        String strValueOf = String.valueOf((((long) yxVar.b()) * 4537077) + ((long) yxVar2.b()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - strValueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(strValueOf);
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int iCharAt = sb.charAt(i3) - '0';
            if ((i3 & 1) == 0) {
                iCharAt *= 3;
            }
            i2 += iCharAt;
        }
        int i4 = 10 - (i2 % 10);
        if (i4 == 10) {
            i4 = 0;
        }
        sb.append(i4);
        yv[] yvVarArrA = yxVar.d().a();
        yv[] yvVarArrA2 = yxVar2.d().a();
        return new xv(String.valueOf(sb.toString()), null, new yv[]{yvVarArrA[0], yvVarArrA[1], yvVarArrA2[0], yvVarArrA2[1]}, BarcodeFormat.RSS_14);
    }

    @Override // supwisdom.ox, supwisdom.wv
    public void reset() {
        this.g.clear();
        this.h.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x004f A[PHI: r7 r8
  0x004f: PHI (r7v6 boolean) = (r7v3 boolean), (r7v12 boolean) binds: [B:34:0x004d, B:21:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x004f: PHI (r8v4 boolean) = (r8v1 boolean), (r8v10 boolean) binds: [B:34:0x004d, B:21:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0052 A[PHI: r7 r8
  0x0052: PHI (r7v8 boolean) = (r7v3 boolean), (r7v12 boolean) binds: [B:34:0x004d, B:21:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x0052: PHI (r8v8 boolean) = (r8v1 boolean), (r8v10 boolean) binds: [B:34:0x004d, B:21:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(boolean r10, int r11) throws com.dcloud.zxing2.NotFoundException {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.zx.a(boolean, int):void");
    }

    public static boolean a(yx yxVar, yx yxVar2) {
        int iA = (yxVar.a() + (yxVar2.a() * 16)) % 79;
        int iC = (yxVar.d().c() * 9) + yxVar2.d().c();
        if (iC > 72) {
            iC--;
        }
        if (iC > 8) {
            iC--;
        }
        return iA == iC;
    }

    public final wx a(ew ewVar, xx xxVar, boolean z) throws NotFoundException {
        int[] iArrA = a();
        iArrA[0] = 0;
        iArrA[1] = 0;
        iArrA[2] = 0;
        iArrA[3] = 0;
        iArrA[4] = 0;
        iArrA[5] = 0;
        iArrA[6] = 0;
        iArrA[7] = 0;
        if (z) {
            ox.b(ewVar, xxVar.b()[0], iArrA);
        } else {
            ox.a(ewVar, xxVar.b()[1] + 1, iArrA);
            int i2 = 0;
            for (int length = iArrA.length - 1; i2 < length; length--) {
                int i3 = iArrA[i2];
                iArrA[i2] = iArrA[length];
                iArrA[length] = i3;
                i2++;
            }
        }
        int i4 = z ? 16 : 15;
        float fA = vx.a(iArrA) / i4;
        int[] iArrE = e();
        int[] iArrC = c();
        float[] fArrF = f();
        float[] fArrD = d();
        for (int i5 = 0; i5 < iArrA.length; i5++) {
            float f = iArrA[i5] / fA;
            int i6 = (int) (0.5f + f);
            if (i6 < 1) {
                i6 = 1;
            } else if (i6 > 8) {
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                iArrE[i7] = i6;
                fArrF[i7] = f - i6;
            } else {
                iArrC[i7] = i6;
                fArrD[i7] = f - i6;
            }
        }
        a(z, i4);
        int i8 = 0;
        int i9 = 0;
        for (int length2 = iArrE.length - 1; length2 >= 0; length2--) {
            i9 = (i9 * 9) + iArrE[length2];
            i8 += iArrE[length2];
        }
        int i10 = 0;
        int i11 = 0;
        for (int length3 = iArrC.length - 1; length3 >= 0; length3--) {
            i10 = (i10 * 9) + iArrC[length3];
            i11 += iArrC[length3];
        }
        int i12 = i9 + (i10 * 3);
        if (!z) {
            if ((i11 & 1) == 0 && i11 <= 10 && i11 >= 4) {
                int i13 = (10 - i11) / 2;
                int i14 = n[i13];
                return new wx((ay.a(iArrC, 9 - i14, false) * j[i13]) + ay.a(iArrE, i14, true) + l[i13], i12);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        if ((i8 & 1) == 0 && i8 <= 12 && i8 >= 4) {
            int i15 = (12 - i8) / 2;
            int i16 = m[i15];
            return new wx((ay.a(iArrE, i16, false) * i[i15]) + ay.a(iArrC, 9 - i16, true) + k[i15], i12);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final yx a(ew ewVar, boolean z, int i2, Map<DecodeHintType, ?> map) {
        try {
            xx xxVarA = a(ewVar, i2, z, a(ewVar, 0, z));
            zv zvVar = map == null ? null : (zv) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (zvVar != null) {
                float fC = (r2[0] + r2[1]) / 2.0f;
                if (z) {
                    fC = (ewVar.c() - 1) - fC;
                }
                zvVar.foundPossibleResultPoint(new yv(fC, i2));
            }
            wx wxVarA = a(ewVar, xxVarA, true);
            wx wxVarA2 = a(ewVar, xxVarA, false);
            return new yx((wxVarA.b() * 1597) + wxVarA2.b(), wxVarA.a() + (wxVarA2.a() * 4), xxVarA);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    @Override // supwisdom.ox
    public xv a(int i2, ew ewVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        a(this.g, a(ewVar, false, i2, map));
        ewVar.d();
        a(this.h, a(ewVar, true, i2, map));
        ewVar.d();
        int size = this.g.size();
        for (int i3 = 0; i3 < size; i3++) {
            yx yxVar = this.g.get(i3);
            if (yxVar.c() > 1) {
                int size2 = this.h.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    yx yxVar2 = this.h.get(i4);
                    if (yxVar2.c() > 1 && a(yxVar, yxVar2)) {
                        return b(yxVar, yxVar2);
                    }
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final int[] a(ew ewVar, int i2, boolean z) throws NotFoundException {
        int[] iArrB = b();
        iArrB[0] = 0;
        iArrB[1] = 0;
        iArrB[2] = 0;
        iArrB[3] = 0;
        int iC = ewVar.c();
        boolean z2 = false;
        while (i2 < iC) {
            z2 = !ewVar.a(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < iC) {
            if (ewVar.a(i2) ^ z2) {
                iArrB[i4] = iArrB[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else {
                    if (vx.b(iArrB)) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArrB[0] + iArrB[1];
                    iArrB[0] = iArrB[2];
                    iArrB[1] = iArrB[3];
                    iArrB[2] = 0;
                    iArrB[3] = 0;
                    i4--;
                }
                iArrB[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final xx a(ew ewVar, int i2, boolean z, int[] iArr) throws NotFoundException {
        int iC;
        int i3;
        boolean zA = ewVar.a(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && (ewVar.a(i4) ^ zA)) {
            i4--;
        }
        int i5 = i4 + 1;
        int i6 = iArr[0] - i5;
        int[] iArrB = b();
        System.arraycopy(iArrB, 0, iArrB, 1, iArrB.length - 1);
        iArrB[0] = i6;
        int iA = vx.a(iArrB, o);
        int i7 = iArr[1];
        if (z) {
            int iC2 = (ewVar.c() - 1) - i5;
            iC = (ewVar.c() - 1) - i7;
            i3 = iC2;
        } else {
            iC = i7;
            i3 = i5;
        }
        return new xx(iA, new int[]{i5, iArr[1]}, i3, iC, i2);
    }
}
