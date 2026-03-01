package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: Settings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8173a;
    public int b;
    public int c;
    public final int[] d = new int[10];

    public void a() {
        this.c = 0;
        this.b = 0;
        this.f8173a = 0;
        Arrays.fill(this.d, 0);
    }

    public int b(int i) {
        return this.d[i];
    }

    public int c() {
        return Integer.bitCount(this.f8173a);
    }

    public int d(int i) {
        return (this.f8173a & 32) != 0 ? this.d[5] : i;
    }

    public boolean e(int i) {
        return ((1 << i) & this.c) != 0;
    }

    public boolean f(int i) {
        return ((1 << i) & this.f8173a) != 0;
    }

    public boolean g(int i) {
        return ((1 << i) & this.b) != 0;
    }

    public int b() {
        if ((this.f8173a & 2) != 0) {
            return this.d[1];
        }
        return -1;
    }

    public int c(int i) {
        return (this.f8173a & 128) != 0 ? this.d[7] : i;
    }

    public kg1 a(int i, int i2, int i3) {
        if (i >= this.d.length) {
            return this;
        }
        int i4 = 1 << i;
        this.f8173a |= i4;
        if ((i2 & 1) != 0) {
            this.b |= i4;
        } else {
            this.b &= ~i4;
        }
        if ((i2 & 2) != 0) {
            this.c |= i4;
        } else {
            this.c &= ~i4;
        }
        this.d[i] = i3;
        return this;
    }

    public int a(int i) {
        int i2 = e(i) ? 2 : 0;
        return g(i) ? i2 | 1 : i2;
    }

    public void a(kg1 kg1Var) {
        for (int i = 0; i < 10; i++) {
            if (kg1Var.f(i)) {
                a(i, kg1Var.a(i), kg1Var.b(i));
            }
        }
    }
}
