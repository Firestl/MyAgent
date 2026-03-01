package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: Sniffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class p20 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f8760a = new o80(8);
    public int b;

    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        long jD = v40Var.d();
        long j = 1024;
        if (jD != -1 && jD <= 1024) {
            j = jD;
        }
        int i = (int) j;
        v40Var.c(this.f8760a.f8644a, 0, 4);
        long jL = this.f8760a.l();
        this.b = 4;
        while (jL != 440786851) {
            int i2 = this.b + 1;
            this.b = i2;
            if (i2 == i) {
                return false;
            }
            v40Var.c(this.f8760a.f8644a, 0, 1);
            jL = ((jL << 8) & (-256)) | ((long) (this.f8760a.f8644a[0] & 255));
        }
        long jB = b(v40Var);
        long j2 = this.b;
        if (jB == Long.MIN_VALUE) {
            return false;
        }
        if (jD != -1 && j2 + jB >= jD) {
            return false;
        }
        while (true) {
            int i3 = this.b;
            long j3 = j2 + jB;
            if (i3 >= j3) {
                return ((long) i3) == j3;
            }
            if (b(v40Var) == Long.MIN_VALUE) {
                return false;
            }
            long jB2 = b(v40Var);
            if (jB2 < 0 || jB2 > 2147483647L) {
                break;
            }
            if (jB2 != 0) {
                v40Var.c((int) jB2);
                this.b = (int) (((long) this.b) + jB2);
            }
        }
        return false;
    }

    public final long b(v40 v40Var) throws InterruptedException, IOException {
        int i = 0;
        v40Var.c(this.f8760a.f8644a, 0, 1);
        int i2 = this.f8760a.f8644a[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        int i5 = i2 & (~i3);
        v40Var.c(this.f8760a.f8644a, 1, i4);
        while (i < i4) {
            i++;
            i5 = (this.f8760a.f8644a[i] & 255) + (i5 << 8);
        }
        this.b += i4 + 1;
        return i5;
    }
}
