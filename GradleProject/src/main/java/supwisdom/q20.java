package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: VarintReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class q20 {
    public static final long[] d = {128, 64, 32, 16, 8, 4, 2, 1};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f8865a = new byte[8];
    public int b;
    public int c;

    public void a() {
        this.b = 0;
        this.c = 0;
    }

    public int b() {
        return this.c;
    }

    public long a(v40 v40Var, boolean z, boolean z2, int i) throws InterruptedException, IOException {
        if (this.b == 0) {
            if (!v40Var.a(this.f8865a, 0, 1, z)) {
                return -1L;
            }
            int iA = a(this.f8865a[0] & 255);
            this.c = iA;
            if (iA != -1) {
                this.b = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int i2 = this.c;
        if (i2 > i) {
            this.b = 0;
            return -2L;
        }
        if (i2 != 1) {
            v40Var.b(this.f8865a, 1, i2 - 1);
        }
        this.b = 0;
        return a(this.f8865a, this.c, z2);
    }

    public static int a(int i) {
        int i2 = 0;
        while (true) {
            long[] jArr = d;
            if (i2 >= jArr.length) {
                return -1;
            }
            if ((jArr[i2] & ((long) i)) != 0) {
                return i2 + 1;
            }
            i2++;
        }
    }

    public static long a(byte[] bArr, int i, boolean z) {
        long j = ((long) bArr[0]) & 255;
        if (z) {
            j &= ~d[i - 1];
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (((long) bArr[i2]) & 255);
        }
        return j;
    }
}
