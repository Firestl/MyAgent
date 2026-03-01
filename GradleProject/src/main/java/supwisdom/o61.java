package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class o61 extends r61 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final o61 f8634e;
    public int[] b;
    public int c;
    public boolean d;

    static {
        o61 o61Var = new o61(0);
        f8634e = o61Var;
        o61Var.e();
    }

    public o61() {
        this(4);
    }

    public void a(int i) {
        f();
        h();
        int[] iArr = this.b;
        int i2 = this.c;
        int i3 = i2 + 1;
        this.c = i3;
        iArr[i2] = i;
        if (this.d) {
            if (i3 > 1) {
                this.d = i >= iArr[i3 + (-2)];
            }
        }
    }

    public int b(int i) {
        int i2 = this.c;
        if (!this.d) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.b[i3] == i) {
                    return i3;
                }
            }
            return -i2;
        }
        int i4 = -1;
        int i5 = i2;
        while (i5 > i4 + 1) {
            int i6 = ((i5 - i4) >> 1) + i4;
            if (i <= this.b[i6]) {
                i5 = i6;
            } else {
                i4 = i6;
            }
        }
        return i5 != i2 ? i == this.b[i5] ? i5 : (-i5) - 1 : (-i2) - 1;
    }

    public boolean c(int i) {
        return e(i) >= 0;
    }

    public int d(int i) {
        if (i >= this.c) {
            throw new IndexOutOfBoundsException("n >= size()");
        }
        try {
            return this.b[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IndexOutOfBoundsException("n < 0");
        }
    }

    public int e(int i) {
        int iB = b(i);
        if (iB >= 0) {
            return iB;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof o61)) {
            return false;
        }
        o61 o61Var = (o61) obj;
        if (this.d != o61Var.d || this.c != o61Var.c) {
            return false;
        }
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != o61Var.b[i]) {
                return false;
            }
        }
        return true;
    }

    public void f(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize < 0");
        }
        if (i > this.c) {
            throw new IllegalArgumentException("newSize > size");
        }
        f();
        this.c = i;
    }

    public final void h() {
        int i = this.c;
        int[] iArr = this.b;
        if (i == iArr.length) {
            int[] iArr2 = new int[((i * 3) / 2) + 10];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.b = iArr2;
        }
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + this.b[i2];
        }
        return i;
    }

    public void i() {
        f();
        if (this.d) {
            return;
        }
        Arrays.sort(this.b, 0, this.c);
        this.d = true;
    }

    public int size() {
        return this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.c * 5) + 10);
        sb.append(Operators.BLOCK_START);
        for (int i = 0; i < this.c; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(this.b[i]);
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public o61(int i) {
        super(true);
        try {
            this.b = new int[i];
            this.c = 0;
            this.d = true;
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    public void a(int i, int i2) {
        f();
        if (i < this.c) {
            try {
                this.b[i] = i2;
                this.d = false;
                return;
            } catch (ArrayIndexOutOfBoundsException unused) {
                if (i < 0) {
                    throw new IllegalArgumentException("n < 0");
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException("n >= size()");
    }
}
