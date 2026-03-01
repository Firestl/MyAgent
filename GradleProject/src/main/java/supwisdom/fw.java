package supwisdom;

import com.bumptech.glide.load.engine.GlideException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class fw implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7661a;
    public final int b;
    public final int c;
    public final int[] d;

    public fw(int i) {
        this(i, i);
    }

    public void a(int i, int i2) {
        int i3 = (i2 * this.c) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = (1 << (i & 31)) ^ iArr[i3];
    }

    public boolean b(int i, int i2) {
        return ((this.d[(i2 * this.c) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public int c() {
        return this.b;
    }

    public int[] d() {
        int[] iArr;
        int i = 0;
        while (true) {
            iArr = this.d;
            if (i >= iArr.length || iArr[i] != 0) {
                break;
            }
            i++;
        }
        if (i == iArr.length) {
            return null;
        }
        int i2 = this.c;
        int i3 = i / i2;
        int i4 = (i % i2) * 32;
        int i5 = iArr[i];
        int i6 = 0;
        while ((i5 << (31 - i6)) == 0) {
            i6++;
        }
        return new int[]{i4 + i6, i3};
    }

    public int e() {
        return this.f7661a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof fw)) {
            return false;
        }
        fw fwVar = (fw) obj;
        return this.f7661a == fwVar.f7661a && this.b == fwVar.b && this.c == fwVar.c && Arrays.equals(this.d, fwVar.d);
    }

    public void f() {
        int iE = e();
        int iC = c();
        ew ewVar = new ew(iE);
        ew ewVar2 = new ew(iE);
        for (int i = 0; i < (iC + 1) / 2; i++) {
            ewVar = a(i, ewVar);
            int i2 = (iC - 1) - i;
            ewVar2 = a(i2, ewVar2);
            ewVar.d();
            ewVar2.d();
            b(i, ewVar2);
            b(i2, ewVar);
        }
    }

    public int hashCode() {
        int i = this.f7661a;
        return (((((((i * 31) + i) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
    }

    public String toString() {
        return a("X ", GlideException.IndentedAppendable.INDENT);
    }

    public fw(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f7661a = i;
        this.b = i2;
        int i3 = (i + 31) / 32;
        this.c = i3;
        this.d = new int[i3 * i2];
    }

    public void c(int i, int i2) {
        int i3 = (i2 * this.c) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    public fw clone() {
        return new fw(this.f7661a, this.b, this.c, (int[]) this.d.clone());
    }

    public int[] a() {
        int length = this.d.length - 1;
        while (length >= 0 && this.d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = this.c;
        int i2 = length / i;
        int i3 = (length % i) * 32;
        int i4 = 31;
        while ((this.d[length] >>> i4) == 0) {
            i4--;
        }
        return new int[]{i3 + i4, i2};
    }

    public int[] b() {
        int i = this.f7661a;
        int i2 = this.b;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < this.b; i5++) {
            int i6 = 0;
            while (true) {
                int i7 = this.c;
                if (i6 < i7) {
                    int i8 = this.d[(i7 * i5) + i6];
                    if (i8 != 0) {
                        if (i5 < i2) {
                            i2 = i5;
                        }
                        if (i5 > i4) {
                            i4 = i5;
                        }
                        int i9 = i6 * 32;
                        int i10 = 31;
                        if (i9 < i) {
                            int i11 = 0;
                            while ((i8 << (31 - i11)) == 0) {
                                i11++;
                            }
                            int i12 = i11 + i9;
                            if (i12 < i) {
                                i = i12;
                            }
                        }
                        if (i9 + 31 > i3) {
                            while ((i8 >>> i10) == 0) {
                                i10--;
                            }
                            int i13 = i9 + i10;
                            if (i13 > i3) {
                                i3 = i13;
                            }
                        }
                    }
                    i6++;
                }
            }
        }
        int i14 = i3 - i;
        int i15 = i4 - i2;
        if (i14 < 0 || i15 < 0) {
            return null;
        }
        return new int[]{i, i2, i14, i15};
    }

    public fw(int i, int i2, int i3, int[] iArr) {
        this.f7661a = i;
        this.b = i2;
        this.c = i3;
        this.d = iArr;
    }

    public ew a(int i, ew ewVar) {
        if (ewVar != null && ewVar.c() >= this.f7661a) {
            ewVar.a();
        } else {
            ewVar = new ew(this.f7661a);
        }
        int i2 = i * this.c;
        for (int i3 = 0; i3 < this.c; i3++) {
            ewVar.a(i3 * 32, this.d[i2 + i3]);
        }
        return ewVar;
    }

    public void b(int i, ew ewVar) {
        int[] iArrB = ewVar.b();
        int[] iArr = this.d;
        int i2 = this.c;
        System.arraycopy(iArrB, 0, iArr, i * i2, i2);
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i4 >= 1 && i3 >= 1) {
            int i5 = i3 + i;
            int i6 = i4 + i2;
            if (i6 > this.b || i5 > this.f7661a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = this.c * i2;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.d;
                    int i9 = (i8 / 32) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("Height and width must be at least 1");
    }

    public String a(String str, String str2) {
        return a(str, str2, "\n");
    }

    @Deprecated
    public String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.b * (this.f7661a + 1));
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.f7661a; i2++) {
                sb.append(b(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }
}
