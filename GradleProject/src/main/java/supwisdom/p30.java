package supwisdom;

import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: OggPageHeader.java */
/* JADX INFO: loaded from: classes.dex */
public final class p30 {
    public static final int i = x80.g("OggS");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8763a;
    public int b;
    public long c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8764e;
    public int f;
    public final int[] g = new int[255];
    public final o80 h = new o80(255);

    public void a() {
        this.f8763a = 0;
        this.b = 0;
        this.c = 0L;
        this.d = 0;
        this.f8764e = 0;
        this.f = 0;
    }

    public boolean a(v40 v40Var, boolean z) throws InterruptedException, IOException {
        this.h.a();
        a();
        if (!(v40Var.d() == -1 || v40Var.d() - v40Var.b() >= 27) || !v40Var.b(this.h.f8644a, 0, 27, true)) {
            if (z) {
                return false;
            }
            throw new EOFException();
        }
        if (this.h.l() != i) {
            if (z) {
                return false;
            }
            throw new com.google.android.exoplayer2.n("expected OggS capture pattern at begin of page");
        }
        int iG = this.h.g();
        this.f8763a = iG;
        if (iG != 0) {
            if (z) {
                return false;
            }
            throw new com.google.android.exoplayer2.n("unsupported bit stream revision");
        }
        this.b = this.h.g();
        this.c = this.h.q();
        this.h.m();
        this.h.m();
        this.h.m();
        int iG2 = this.h.g();
        this.d = iG2;
        this.f8764e = iG2 + 27;
        this.h.a();
        v40Var.c(this.h.f8644a, 0, this.d);
        for (int i2 = 0; i2 < this.d; i2++) {
            this.g[i2] = this.h.g();
            this.f += this.g[i2];
        }
        return true;
    }
}
