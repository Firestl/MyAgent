package supwisdom;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: OggPacket.java */
/* JADX INFO: loaded from: classes.dex */
public final class o30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p30 f8611a = new p30();
    public final o80 b = new o80(new byte[65025], 0);
    public int c = -1;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8612e;

    public void a() {
        this.f8611a.a();
        this.b.a();
        this.c = -1;
        this.f8612e = false;
    }

    public p30 b() {
        return this.f8611a;
    }

    public o80 c() {
        return this.b;
    }

    public void d() {
        o80 o80Var = this.b;
        byte[] bArr = o80Var.f8644a;
        if (bArr.length == 65025) {
            return;
        }
        o80Var.f8644a = Arrays.copyOf(bArr, Math.max(65025, o80Var.c()));
    }

    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        int i;
        e80.b(v40Var != null);
        if (this.f8612e) {
            this.f8612e = false;
            this.b.a();
        }
        while (!this.f8612e) {
            if (this.c < 0) {
                if (!this.f8611a.a(v40Var, true)) {
                    return false;
                }
                p30 p30Var = this.f8611a;
                int iA = p30Var.f8764e;
                if ((p30Var.b & 1) == 1 && this.b.c() == 0) {
                    iA += a(0);
                    i = this.d + 0;
                } else {
                    i = 0;
                }
                v40Var.b(iA);
                this.c = i;
            }
            int iA2 = a(this.c);
            int i2 = this.c + this.d;
            if (iA2 > 0) {
                if (this.b.e() < this.b.c() + iA2) {
                    o80 o80Var = this.b;
                    o80Var.f8644a = Arrays.copyOf(o80Var.f8644a, o80Var.c() + iA2);
                }
                o80 o80Var2 = this.b;
                v40Var.b(o80Var2.f8644a, o80Var2.c(), iA2);
                o80 o80Var3 = this.b;
                o80Var3.b(o80Var3.c() + iA2);
                this.f8612e = this.f8611a.g[i2 + (-1)] != 255;
            }
            if (i2 == this.f8611a.d) {
                i2 = -1;
            }
            this.c = i2;
        }
        return true;
    }

    public final int a(int i) {
        int i2;
        int i3 = 0;
        this.d = 0;
        do {
            int i4 = this.d;
            int i5 = i + i4;
            p30 p30Var = this.f8611a;
            if (i5 >= p30Var.d) {
                break;
            }
            int[] iArr = p30Var.g;
            this.d = i4 + 1;
            i2 = iArr[i4 + i];
            i3 += i2;
        } while (i2 == 255);
        return i3;
    }
}
