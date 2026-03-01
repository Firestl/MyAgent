package supwisdom;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: DefaultExtractorInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class r20 implements v40 {
    public static final byte[] g = new byte[4096];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s70 f9002a;
    public final long b;
    public long c;
    public byte[] d = new byte[65536];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9003e;
    public int f;

    public r20(s70 s70Var, long j, long j2) {
        this.f9002a = s70Var;
        this.c = j;
        this.b = j2;
    }

    @Override // supwisdom.v40
    public int a(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int iD = d(bArr, i, i2);
        if (iD == 0) {
            iD = a(bArr, i, i2, 0, true);
        }
        g(iD);
        return iD;
    }

    @Override // supwisdom.v40
    public void b(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        a(bArr, i, i2, false);
    }

    @Override // supwisdom.v40
    public void c(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        b(bArr, i, i2, false);
    }

    @Override // supwisdom.v40
    public long d() {
        return this.b;
    }

    public final int e(int i) {
        int iMin = Math.min(this.f, i);
        f(iMin);
        return iMin;
    }

    public final void f(int i) {
        int i2 = this.f - i;
        this.f = i2;
        this.f9003e = 0;
        byte[] bArr = this.d;
        if (i2 < bArr.length - 524288) {
            bArr = new byte[i2 + 65536];
        }
        System.arraycopy(this.d, i, bArr, 0, this.f);
        this.d = bArr;
    }

    public final void g(int i) {
        if (i != -1) {
            this.c += (long) i;
        }
    }

    @Override // supwisdom.v40
    public void b(int i) throws InterruptedException, IOException {
        a(i, false);
    }

    @Override // supwisdom.v40
    public void c(int i) throws InterruptedException, IOException {
        b(i, false);
    }

    public final void d(int i) {
        int i2 = this.f9003e + i;
        byte[] bArr = this.d;
        if (i2 > bArr.length) {
            this.d = Arrays.copyOf(this.d, x80.a(bArr.length * 2, 65536 + i2, i2 + 524288));
        }
    }

    @Override // supwisdom.v40
    public boolean b(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        if (!b(i2, z)) {
            return false;
        }
        System.arraycopy(this.d, this.f9003e - i2, bArr, i, i2);
        return true;
    }

    @Override // supwisdom.v40
    public long c() {
        return this.c;
    }

    @Override // supwisdom.v40
    public boolean a(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        int iD = d(bArr, i, i2);
        while (iD < i2 && iD != -1) {
            iD = a(bArr, i, i2, iD, z);
        }
        g(iD);
        return iD != -1;
    }

    public boolean b(int i, boolean z) throws InterruptedException, IOException {
        d(i);
        int iMin = Math.min(this.f - this.f9003e, i);
        while (iMin < i) {
            iMin = a(this.d, this.f9003e, i, iMin, z);
            if (iMin == -1) {
                return false;
            }
        }
        int i2 = this.f9003e + i;
        this.f9003e = i2;
        this.f = Math.max(this.f, i2);
        return true;
    }

    public final int d(byte[] bArr, int i, int i2) {
        int i3 = this.f;
        if (i3 == 0) {
            return 0;
        }
        int iMin = Math.min(i3, i2);
        System.arraycopy(this.d, 0, bArr, i, iMin);
        f(iMin);
        return iMin;
    }

    @Override // supwisdom.v40
    public int a(int i) throws InterruptedException, IOException {
        int iE = e(i);
        if (iE == 0) {
            byte[] bArr = g;
            iE = a(bArr, 0, Math.min(i, bArr.length), 0, true);
        }
        g(iE);
        return iE;
    }

    @Override // supwisdom.v40
    public long b() {
        return this.c + ((long) this.f9003e);
    }

    public boolean a(int i, boolean z) throws InterruptedException, IOException {
        int iE = e(i);
        while (iE < i && iE != -1) {
            byte[] bArr = g;
            iE = a(bArr, -iE, Math.min(i, bArr.length + iE), iE, z);
        }
        g(iE);
        return iE != -1;
    }

    @Override // supwisdom.v40
    public void a() {
        this.f9003e = 0;
    }

    public final int a(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (!Thread.interrupted()) {
            int iA = this.f9002a.a(bArr, i + i3, i2 - i3);
            if (iA != -1) {
                return i3 + iA;
            }
            if (i3 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        throw new InterruptedException();
    }
}
