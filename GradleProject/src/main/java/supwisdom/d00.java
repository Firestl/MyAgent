package supwisdom;

import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f7278a;
    public boolean c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final zv f7279e;
    public final List<c00> b = new ArrayList();
    public final int[] d = new int[5];

    public static final class b implements Comparator<c00>, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f7280a;

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c00 c00Var, c00 c00Var2) {
            if (c00Var2.c() != c00Var.c()) {
                return c00Var2.c() - c00Var.c();
            }
            float fAbs = Math.abs(c00Var2.d() - this.f7280a);
            float fAbs2 = Math.abs(c00Var.d() - this.f7280a);
            if (fAbs < fAbs2) {
                return 1;
            }
            return fAbs == fAbs2 ? 0 : -1;
        }

        public b(float f) {
            this.f7280a = f;
        }
    }

    public static final class c implements Comparator<c00>, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f7281a;

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c00 c00Var, c00 c00Var2) {
            float fAbs = Math.abs(c00Var2.d() - this.f7281a);
            float fAbs2 = Math.abs(c00Var.d() - this.f7281a);
            if (fAbs < fAbs2) {
                return -1;
            }
            return fAbs == fAbs2 ? 0 : 1;
        }

        public c(float f) {
            this.f7281a = f;
        }
    }

    public d00(fw fwVar, zv zvVar) {
        this.f7278a = fwVar;
        this.f7279e = zvVar;
    }

    public static float a(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    public final float b(int i, int i2, int i3, int i4) {
        fw fwVar = this.f7278a;
        int iE = fwVar.e();
        int[] iArrB = b();
        int i5 = i;
        while (i5 >= 0 && fwVar.b(i5, i2)) {
            iArrB[2] = iArrB[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !fwVar.b(i5, i2) && iArrB[1] <= i3) {
            iArrB[1] = iArrB[1] + 1;
            i5--;
        }
        if (i5 < 0 || iArrB[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && fwVar.b(i5, i2) && iArrB[0] <= i3) {
            iArrB[0] = iArrB[0] + 1;
            i5--;
        }
        if (iArrB[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < iE && fwVar.b(i6, i2)) {
            iArrB[2] = iArrB[2] + 1;
            i6++;
        }
        if (i6 == iE) {
            return Float.NaN;
        }
        while (i6 < iE && !fwVar.b(i6, i2) && iArrB[3] < i3) {
            iArrB[3] = iArrB[3] + 1;
            i6++;
        }
        if (i6 == iE || iArrB[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < iE && fwVar.b(i6, i2) && iArrB[4] < i3) {
            iArrB[4] = iArrB[4] + 1;
            i6++;
        }
        if (iArrB[4] < i3 && Math.abs(((((iArrB[0] + iArrB[1]) + iArrB[2]) + iArrB[3]) + iArrB[4]) - i4) * 5 < i4 && a(iArrB)) {
            return a(iArrB, i6);
        }
        return Float.NaN;
    }

    public final float c(int i, int i2, int i3, int i4) {
        fw fwVar = this.f7278a;
        int iC = fwVar.c();
        int[] iArrB = b();
        int i5 = i;
        while (i5 >= 0 && fwVar.b(i2, i5)) {
            iArrB[2] = iArrB[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !fwVar.b(i2, i5) && iArrB[1] <= i3) {
            iArrB[1] = iArrB[1] + 1;
            i5--;
        }
        if (i5 < 0 || iArrB[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && fwVar.b(i2, i5) && iArrB[0] <= i3) {
            iArrB[0] = iArrB[0] + 1;
            i5--;
        }
        if (iArrB[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < iC && fwVar.b(i2, i6)) {
            iArrB[2] = iArrB[2] + 1;
            i6++;
        }
        if (i6 == iC) {
            return Float.NaN;
        }
        while (i6 < iC && !fwVar.b(i2, i6) && iArrB[3] < i3) {
            iArrB[3] = iArrB[3] + 1;
            i6++;
        }
        if (i6 == iC || iArrB[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < iC && fwVar.b(i2, i6) && iArrB[4] < i3) {
            iArrB[4] = iArrB[4] + 1;
            i6++;
        }
        if (iArrB[4] < i3 && Math.abs(((((iArrB[0] + iArrB[1]) + iArrB[2]) + iArrB[3]) + iArrB[4]) - i4) * 5 < i4 * 2 && a(iArrB)) {
            return a(iArrB, i6);
        }
        return Float.NaN;
    }

    public final c00[] d() throws NotFoundException {
        int size = this.b.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float fD = 0.0f;
        if (size > 3) {
            Iterator<c00> it = this.b.iterator();
            float f = 0.0f;
            float f2 = 0.0f;
            while (it.hasNext()) {
                float fD2 = it.next().d();
                f += fD2;
                f2 += fD2 * fD2;
            }
            float f3 = f / size;
            float fSqrt = (float) Math.sqrt((f2 / r0) - (f3 * f3));
            Collections.sort(this.b, new c(f3));
            float fMax = Math.max(0.2f * f3, fSqrt);
            int i = 0;
            while (i < this.b.size() && this.b.size() > 3) {
                if (Math.abs(this.b.get(i).d() - f3) > fMax) {
                    this.b.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.b.size() > 3) {
            Iterator<c00> it2 = this.b.iterator();
            while (it2.hasNext()) {
                fD += it2.next().d();
            }
            Collections.sort(this.b, new b(fD / this.b.size()));
            List<c00> list = this.b;
            list.subList(3, list.size()).clear();
        }
        return new c00[]{this.b.get(0), this.b.get(1), this.b.get(2)};
    }

    public final boolean a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int[] iArrB = b();
        int i10 = 0;
        while (i >= i10 && i2 >= i10 && this.f7278a.b(i2 - i10, i - i10)) {
            iArrB[2] = iArrB[2] + 1;
            i10++;
        }
        if (i < i10 || i2 < i10) {
            return false;
        }
        while (i >= i10 && i2 >= i10 && !this.f7278a.b(i2 - i10, i - i10) && iArrB[1] <= i3) {
            iArrB[1] = iArrB[1] + 1;
            i10++;
        }
        if (i < i10 || i2 < i10 || iArrB[1] > i3) {
            return false;
        }
        while (i >= i10 && i2 >= i10 && this.f7278a.b(i2 - i10, i - i10) && iArrB[0] <= i3) {
            iArrB[0] = iArrB[0] + 1;
            i10++;
        }
        if (iArrB[0] > i3) {
            return false;
        }
        int iC = this.f7278a.c();
        int iE = this.f7278a.e();
        int i11 = 1;
        while (true) {
            i5 = i + i11;
            if (i5 >= iC || (i9 = i2 + i11) >= iE || !this.f7278a.b(i9, i5)) {
                break;
            }
            iArrB[2] = iArrB[2] + 1;
            i11++;
        }
        if (i5 >= iC || i2 + i11 >= iE) {
            return false;
        }
        while (true) {
            i6 = i + i11;
            if (i6 >= iC || (i8 = i2 + i11) >= iE || this.f7278a.b(i8, i6) || iArrB[3] >= i3) {
                break;
            }
            iArrB[3] = iArrB[3] + 1;
            i11++;
        }
        if (i6 >= iC || i2 + i11 >= iE || iArrB[3] >= i3) {
            return false;
        }
        while (true) {
            int i12 = i + i11;
            if (i12 >= iC || (i7 = i2 + i11) >= iE || !this.f7278a.b(i7, i12) || iArrB[4] >= i3) {
                break;
            }
            iArrB[4] = iArrB[4] + 1;
            i11++;
        }
        return iArrB[4] < i3 && Math.abs(((((iArrB[0] + iArrB[1]) + iArrB[2]) + iArrB[3]) + iArrB[4]) - i4) < i4 * 2 && a(iArrB);
    }

    public final int[] b() {
        int[] iArr = this.d;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    public final boolean c() {
        int size = this.b.size();
        float fAbs = 0.0f;
        int i = 0;
        float fD = 0.0f;
        for (c00 c00Var : this.b) {
            if (c00Var.c() >= 2) {
                i++;
                fD += c00Var.d();
            }
        }
        if (i < 3) {
            return false;
        }
        float f = fD / size;
        Iterator<c00> it = this.b.iterator();
        while (it.hasNext()) {
            fAbs += Math.abs(it.next().d() - f);
        }
        return fAbs <= fD * 0.05f;
    }

    public final e00 a(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int iC = this.f7278a.c();
        int iE = this.f7278a.e();
        int i = (iC * 3) / 228;
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        boolean zC = false;
        while (i2 < iC && !zC) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i3 = 0;
            int i4 = 0;
            while (i3 < iE) {
                if (this.f7278a.b(i3, i2)) {
                    if ((i4 & 1) == 1) {
                        i4++;
                    }
                    iArr[i4] = iArr[i4] + 1;
                } else if ((i4 & 1) != 0) {
                    iArr[i4] = iArr[i4] + 1;
                } else if (i4 == 4) {
                    if (a(iArr) && a(iArr, i2, i3, z2)) {
                        if (this.c) {
                            zC = c();
                        } else {
                            int iA = a();
                            if (iA > iArr[2]) {
                                i2 += (iA - iArr[2]) - 2;
                                i3 = iE - 1;
                            }
                        }
                        iArr[0] = 0;
                        iArr[1] = 0;
                        iArr[2] = 0;
                        iArr[3] = 0;
                        iArr[4] = 0;
                        i = 2;
                        i4 = 0;
                    } else {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                        i4 = 3;
                    }
                } else {
                    i4++;
                    iArr[i4] = iArr[i4] + 1;
                }
                i3++;
            }
            if (a(iArr) && a(iArr, i2, iE, z2)) {
                i = iArr[0];
                if (this.c) {
                    zC = c();
                }
            }
            i2 += i;
        }
        c00[] c00VarArrD = d();
        yv.a(c00VarArrD);
        return new e00(c00VarArrD);
    }

    public final int a() {
        if (this.b.size() <= 1) {
            return 0;
        }
        c00 c00Var = null;
        for (c00 c00Var2 : this.b) {
            if (c00Var2.c() >= 2) {
                if (c00Var != null) {
                    this.c = true;
                    return ((int) (Math.abs(c00Var.a() - c00Var2.a()) - Math.abs(c00Var.b() - c00Var2.b()))) / 2;
                }
                c00Var = c00Var2;
            }
        }
        return 0;
    }

    public static boolean a(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    public final boolean a(int[] iArr, int i, int i2, boolean z) {
        boolean z2 = false;
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int iA = (int) a(iArr, i2);
        float fC = c(i, iA, iArr[2], i3);
        if (!Float.isNaN(fC)) {
            int i4 = (int) fC;
            float fB = b(iA, i4, iArr[2], i3);
            if (!Float.isNaN(fB) && (!z || a(i4, (int) fB, iArr[2], i3))) {
                float f = i3 / 7.0f;
                int i5 = 0;
                while (true) {
                    if (i5 >= this.b.size()) {
                        break;
                    }
                    c00 c00Var = this.b.get(i5);
                    if (c00Var.a(f, fC, fB)) {
                        this.b.set(i5, c00Var.b(fC, fB, f));
                        z2 = true;
                        break;
                    }
                    i5++;
                }
                if (!z2) {
                    c00 c00Var2 = new c00(fB, fC, f);
                    this.b.add(c00Var2);
                    zv zvVar = this.f7279e;
                    if (zvVar != null) {
                        zvVar.foundPossibleResultPoint(c00Var2);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
