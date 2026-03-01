package supwisdom;

import java.util.Formatter;

/* JADX INFO: loaded from: classes.dex */
public final class gz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final bz f7783a;
    public final hz[] b;
    public dz c;
    public final int d;

    public gz(bz bzVar, dz dzVar) {
        this.f7783a = bzVar;
        int iA = bzVar.a();
        this.d = iA;
        this.c = dzVar;
        this.b = new hz[iA + 2];
    }

    public final void a(hz hzVar) {
        if (hzVar != null) {
            ((iz) hzVar).a(this.f7783a);
        }
    }

    public final int b() {
        c();
        return d() + e();
    }

    public final void c() {
        hz[] hzVarArr = this.b;
        if (hzVarArr[0] == null || hzVarArr[this.d + 1] == null) {
            return;
        }
        ez[] ezVarArrB = hzVarArr[0].b();
        ez[] ezVarArrB2 = this.b[this.d + 1].b();
        for (int i = 0; i < ezVarArrB.length; i++) {
            if (ezVarArrB[i] != null && ezVarArrB2[i] != null && ezVarArrB[i].c() == ezVarArrB2[i].c()) {
                for (int i2 = 1; i2 <= this.d; i2++) {
                    ez ezVar = this.b[i2].b()[i];
                    if (ezVar != null) {
                        ezVar.b(ezVarArrB[i].c());
                        if (!ezVar.g()) {
                            this.b[i2].b()[i] = null;
                        }
                    }
                }
            }
        }
    }

    public final int d() {
        hz[] hzVarArr = this.b;
        if (hzVarArr[0] == null) {
            return 0;
        }
        ez[] ezVarArrB = hzVarArr[0].b();
        int i = 0;
        for (int i2 = 0; i2 < ezVarArrB.length; i2++) {
            if (ezVarArrB[i2] != null) {
                int iC = ezVarArrB[i2].c();
                int iA = 0;
                for (int i3 = 1; i3 < this.d + 1 && iA < 2; i3++) {
                    ez ezVar = this.b[i3].b()[i2];
                    if (ezVar != null) {
                        iA = a(iC, iA, ezVar);
                        if (!ezVar.g()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    public final int e() {
        hz[] hzVarArr = this.b;
        int i = this.d + 1;
        if (hzVarArr[i] == null) {
            return 0;
        }
        ez[] ezVarArrB = hzVarArr[i].b();
        int i2 = 0;
        for (int i3 = 0; i3 < ezVarArrB.length; i3++) {
            if (ezVarArrB[i3] != null) {
                int iC = ezVarArrB[i3].c();
                int iA = 0;
                for (int i4 = this.d + 1; i4 > 0 && iA < 2; i4--) {
                    ez ezVar = this.b[i4].b()[i3];
                    if (ezVar != null) {
                        iA = a(iC, iA, ezVar);
                        if (!ezVar.g()) {
                            i2++;
                        }
                    }
                }
            }
        }
        return i2;
    }

    public int f() {
        return this.d;
    }

    public int g() {
        return this.f7783a.b();
    }

    public int h() {
        return this.f7783a.c();
    }

    public dz i() {
        return this.c;
    }

    public hz[] j() {
        a(this.b[0]);
        a(this.b[this.d + 1]);
        int i = 928;
        while (true) {
            int iA = a();
            if (iA <= 0 || iA >= i) {
                break;
            }
            i = iA;
        }
        return this.b;
    }

    public String toString() {
        hz[] hzVarArr = this.b;
        hz hzVar = hzVarArr[0];
        if (hzVar == null) {
            hzVar = hzVarArr[this.d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < hzVar.b().length; i++) {
            formatter.format("CW %3d:", Integer.valueOf(i));
            for (int i2 = 0; i2 < this.d + 2; i2++) {
                hz[] hzVarArr2 = this.b;
                if (hzVarArr2[i2] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    ez ezVar = hzVarArr2[i2].b()[i];
                    if (ezVar == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", Integer.valueOf(ezVar.c()), Integer.valueOf(ezVar.e()));
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }

    public static boolean a(ez ezVar, ez ezVar2) {
        if (ezVar2 == null || !ezVar2.g() || ezVar2.a() != ezVar.a()) {
            return false;
        }
        ezVar.b(ezVar2.c());
        return true;
    }

    public static int a(int i, int i2, ez ezVar) {
        if (ezVar == null || ezVar.g()) {
            return i2;
        }
        if (!ezVar.a(i)) {
            return i2 + 1;
        }
        ezVar.b(i);
        return 0;
    }

    public final int a() {
        int iB = b();
        if (iB == 0) {
            return 0;
        }
        for (int i = 1; i < this.d + 1; i++) {
            ez[] ezVarArrB = this.b[i].b();
            for (int i2 = 0; i2 < ezVarArrB.length; i2++) {
                if (ezVarArrB[i2] != null && !ezVarArrB[i2].g()) {
                    a(i, i2, ezVarArrB);
                }
            }
        }
        return iB;
    }

    public hz a(int i) {
        return this.b[i];
    }

    public void a(dz dzVar) {
        this.c = dzVar;
    }

    public void a(int i, hz hzVar) {
        this.b[i] = hzVar;
    }

    public final void a(int i, int i2, ez[] ezVarArr) {
        ez ezVar = ezVarArr[i2];
        ez[] ezVarArrB = this.b[i - 1].b();
        hz[] hzVarArr = this.b;
        int i3 = i + 1;
        ez[] ezVarArrB2 = hzVarArr[i3] != null ? hzVarArr[i3].b() : ezVarArrB;
        ez[] ezVarArr2 = new ez[14];
        ezVarArr2[2] = ezVarArrB[i2];
        ezVarArr2[3] = ezVarArrB2[i2];
        if (i2 > 0) {
            int i4 = i2 - 1;
            ezVarArr2[0] = ezVarArr[i4];
            ezVarArr2[4] = ezVarArrB[i4];
            ezVarArr2[5] = ezVarArrB2[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            ezVarArr2[8] = ezVarArr[i5];
            ezVarArr2[10] = ezVarArrB[i5];
            ezVarArr2[11] = ezVarArrB2[i5];
        }
        if (i2 < ezVarArr.length - 1) {
            int i6 = i2 + 1;
            ezVarArr2[1] = ezVarArr[i6];
            ezVarArr2[6] = ezVarArrB[i6];
            ezVarArr2[7] = ezVarArrB2[i6];
        }
        if (i2 < ezVarArr.length - 2) {
            int i7 = i2 + 2;
            ezVarArr2[9] = ezVarArr[i7];
            ezVarArr2[12] = ezVarArrB[i7];
            ezVarArr2[13] = ezVarArrB2[i7];
        }
        for (int i8 = 0; i8 < 14 && !a(ezVar, ezVarArr2[i8]); i8++) {
        }
    }
}
