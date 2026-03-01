package supwisdom;

import com.dcloud.zxing2.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f6836a;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6837e;
    public final int f;
    public final float g;
    public final zv i;
    public final List<zz> b = new ArrayList(5);
    public final int[] h = new int[3];

    public a00(fw fwVar, int i, int i2, int i3, int i4, float f, zv zvVar) {
        this.f6836a = fwVar;
        this.c = i;
        this.d = i2;
        this.f6837e = i3;
        this.f = i4;
        this.g = f;
        this.i = zvVar;
    }

    public static float a(int[] iArr, int i) {
        return (i - iArr[2]) - (iArr[1] / 2.0f);
    }

    public final float a(int i, int i2, int i3, int i4) {
        fw fwVar = this.f6836a;
        int iC = fwVar.c();
        int[] iArr = this.h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i5 = i;
        while (i5 >= 0 && fwVar.b(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5--;
        }
        if (i5 < 0 || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && !fwVar.b(i2, i5) && iArr[0] <= i3) {
            iArr[0] = iArr[0] + 1;
            i5--;
        }
        if (iArr[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < iC && fwVar.b(i2, i6) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i6++;
        }
        if (i6 == iC || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i6 < iC && !fwVar.b(i2, i6) && iArr[2] <= i3) {
            iArr[2] = iArr[2] + 1;
            i6++;
        }
        if (iArr[2] <= i3 && Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 < i4 * 2 && a(iArr)) {
            return a(iArr, i6);
        }
        return Float.NaN;
    }

    public zz a() throws NotFoundException {
        zz zzVarA;
        zz zzVarA2;
        int i = this.c;
        int i2 = this.f;
        int i3 = this.f6837e + i;
        int i4 = this.d + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.f6836a.b(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.f6836a.b(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 2) {
                    if (a(iArr) && (zzVarA2 = a(iArr, i6, i7)) != null) {
                        return zzVarA2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                } else {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                }
                i7++;
            }
            if (a(iArr) && (zzVarA = a(iArr, i6, i3)) != null) {
                return zzVarA;
            }
        }
        if (!this.b.isEmpty()) {
            return this.b.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final boolean a(int[] iArr) {
        float f = this.g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - iArr[i]) >= f2) {
                return false;
            }
        }
        return true;
    }

    public final zz a(int[] iArr, int i, int i2) {
        int i3 = iArr[0] + iArr[1] + iArr[2];
        float fA = a(iArr, i2);
        float fA2 = a(i, (int) fA, iArr[1] * 2, i3);
        if (Float.isNaN(fA2)) {
            return null;
        }
        float f = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (zz zzVar : this.b) {
            if (zzVar.a(f, fA2, fA)) {
                return zzVar.b(fA2, fA, f);
            }
        }
        zz zzVar2 = new zz(fA, fA2, f);
        this.b.add(zzVar2);
        zv zvVar = this.i;
        if (zvVar == null) {
            return null;
        }
        zvVar.foundPossibleResultPoint(zzVar2);
        return null;
    }
}
