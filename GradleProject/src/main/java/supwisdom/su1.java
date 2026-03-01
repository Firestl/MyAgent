package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: Settings.java */
/* JADX INFO: loaded from: classes3.dex */
public final class su1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9214a;
    public final int[] b = new int[10];

    public void a() {
        this.f9214a = 0;
        Arrays.fill(this.b, 0);
    }

    public int b() {
        if ((this.f9214a & 2) != 0) {
            return this.b[1];
        }
        return -1;
    }

    public int c(int i) {
        return (this.f9214a & 32) != 0 ? this.b[5] : i;
    }

    public boolean d(int i) {
        return ((1 << i) & this.f9214a) != 0;
    }

    public int b(int i) {
        return (this.f9214a & 16) != 0 ? this.b[4] : i;
    }

    public int c() {
        if ((this.f9214a & 128) != 0) {
            return this.b[7];
        }
        return 65535;
    }

    public int d() {
        return Integer.bitCount(this.f9214a);
    }

    public su1 a(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.b;
            if (i < iArr.length) {
                this.f9214a = (1 << i) | this.f9214a;
                iArr[i] = i2;
            }
        }
        return this;
    }

    public int a(int i) {
        return this.b[i];
    }

    public void a(su1 su1Var) {
        for (int i = 0; i < 10; i++) {
            if (su1Var.d(i)) {
                a(i, su1Var.a(i));
            }
        }
    }
}
