package supwisdom;

import com.taobao.weex.el.parse.Operators;
import org.bouncycastle.crypto.digests.WhirlpoolDigest;

/* JADX INFO: loaded from: classes.dex */
public final class qw {
    public static final qw h = new qw(4201, 4096, 1);
    public static final qw i = new qw(1033, 1024, 1);
    public static final qw j;
    public static final qw k;
    public static final qw l;
    public static final qw m;
    public static final qw n;
    public static final qw o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f8963a;
    public final int[] b;
    public final rw c;
    public final rw d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8964e;
    public final int f;
    public final int g;

    static {
        qw qwVar = new qw(67, 64, 1);
        j = qwVar;
        k = new qw(19, 16, 1);
        l = new qw(WhirlpoolDigest.REDUCTION_POLYNOMIAL, 256, 0);
        qw qwVar2 = new qw(301, 256, 1);
        m = qwVar2;
        n = qwVar2;
        o = qwVar;
    }

    public qw(int i2, int i3, int i4) {
        this.f = i2;
        this.f8964e = i3;
        this.g = i4;
        this.f8963a = new int[i3];
        this.b = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.f8963a[i6] = i5;
            i5 *= 2;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.b[this.f8963a[i7]] = i7;
        }
        this.c = new rw(this, new int[]{0});
        this.d = new rw(this, new int[]{1});
    }

    public static int c(int i2, int i3) {
        return i2 ^ i3;
    }

    public rw a(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.c;
        }
        int[] iArr = new int[i2 + 1];
        iArr[0] = i3;
        return new rw(this, iArr);
    }

    public rw b() {
        return this.d;
    }

    public int c() {
        return this.f8964e;
    }

    public rw d() {
        return this.c;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f) + ',' + this.f8964e + Operators.BRACKET_END;
    }

    public int b(int i2) {
        if (i2 != 0) {
            return this.f8963a[(this.f8964e - this.b[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int c(int i2) {
        if (i2 != 0) {
            return this.b[i2];
        }
        throw new IllegalArgumentException();
    }

    public int b(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.f8963a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.f8964e - 1)];
    }

    public int a(int i2) {
        return this.f8963a[i2];
    }

    public int a() {
        return this.g;
    }
}
