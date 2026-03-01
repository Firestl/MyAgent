package supwisdom;

import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: loaded from: classes.dex */
public final class rw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final qw f9095a;
    public final int[] b;

    public rw(qw qwVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f9095a = qwVar;
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

    public rw a(rw rwVar) {
        if (!this.f9095a.equals(rwVar.f9095a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (b()) {
            return rwVar;
        }
        if (rwVar.b()) {
            return this;
        }
        int[] iArr = this.b;
        int[] iArr2 = rwVar.b;
        if (iArr.length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length);
        for (int i = length; i < iArr.length; i++) {
            iArr3[i] = qw.c(iArr2[i - length], iArr[i]);
        }
        return new rw(this.f9095a, iArr3);
    }

    public int b(int i) {
        return this.b[(r0.length - 1) - i];
    }

    public rw c(int i) {
        if (i == 0) {
            return this.f9095a.d();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f9095a.b(this.b[i2], i);
        }
        return new rw(this.f9095a, iArr);
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
                    int iC = this.f9095a.c(iB);
                    if (iC == 0) {
                        sb.append('1');
                    } else if (iC == 1) {
                        sb.append(FunctionParser.Lexer.A_LOWER);
                    } else {
                        sb.append("a^");
                        sb.append(iC);
                    }
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

    public rw b(rw rwVar) {
        if (this.f9095a.equals(rwVar.f9095a)) {
            if (!b() && !rwVar.b()) {
                int[] iArr = this.b;
                int length = iArr.length;
                int[] iArr2 = rwVar.b;
                int length2 = iArr2.length;
                int[] iArr3 = new int[(length + length2) - 1];
                for (int i = 0; i < length; i++) {
                    int i2 = iArr[i];
                    for (int i3 = 0; i3 < length2; i3++) {
                        int i4 = i + i3;
                        iArr3[i4] = qw.c(iArr3[i4], this.f9095a.b(i2, iArr2[i3]));
                    }
                }
                return new rw(this.f9095a, iArr3);
            }
            return this.f9095a.d();
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    public int a(int i) {
        if (i == 0) {
            return b(0);
        }
        int[] iArr = this.b;
        int length = iArr.length;
        if (i == 1) {
            int iC = 0;
            for (int i2 : iArr) {
                iC = qw.c(iC, i2);
            }
            return iC;
        }
        int iC2 = iArr[0];
        for (int i3 = 1; i3 < length; i3++) {
            iC2 = qw.c(this.f9095a.b(i, iC2), this.b[i3]);
        }
        return iC2;
    }

    public int a() {
        return this.b.length - 1;
    }

    public rw a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f9095a.d();
        }
        int length = this.b.length;
        int[] iArr = new int[i + length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.f9095a.b(this.b[i3], i2);
        }
        return new rw(this.f9095a, iArr);
    }
}
