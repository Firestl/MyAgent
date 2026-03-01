package supwisdom;

import android.util.Log;
import java.io.IOException;

/* JADX INFO: compiled from: WavHeaderReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class y40 {

    /* JADX INFO: compiled from: WavHeaderReader.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9846a;
        public final long b;

        public a(int i, long j) {
            this.f9846a = i;
            this.b = j;
        }

        public static a a(v40 v40Var, o80 o80Var) throws InterruptedException, IOException {
            v40Var.c(o80Var.f8644a, 0, 8);
            o80Var.c(0);
            return new a(o80Var.n(), o80Var.m());
        }
    }

    public static x40 a(v40 v40Var) throws InterruptedException, IOException {
        e80.a(v40Var);
        o80 o80Var = new o80(16);
        if (a.a(v40Var, o80Var).f9846a != x80.g("RIFF")) {
            return null;
        }
        v40Var.c(o80Var.f8644a, 0, 4);
        o80Var.c(0);
        int iN = o80Var.n();
        if (iN != x80.g("WAVE")) {
            Log.e("WavHeaderReader", "Unsupported RIFF format: " + iN);
            return null;
        }
        a aVarA = a.a(v40Var, o80Var);
        while (aVarA.f9846a != x80.g("fmt ")) {
            v40Var.c((int) aVarA.b);
            aVarA = a.a(v40Var, o80Var);
        }
        e80.b(aVarA.b >= 16);
        v40Var.c(o80Var.f8644a, 0, 16);
        o80Var.c(0);
        int i = o80Var.i();
        int i2 = o80Var.i();
        int iU = o80Var.u();
        int iU2 = o80Var.u();
        int i3 = o80Var.i();
        int i4 = o80Var.i();
        int i5 = (i2 * i4) / 8;
        if (i3 != i5) {
            throw new com.google.android.exoplayer2.n("Expected block alignment: " + i5 + "; got: " + i3);
        }
        int iB = x80.b(i4);
        if (iB == 0) {
            Log.e("WavHeaderReader", "Unsupported WAV bit depth: " + i4);
            return null;
        }
        if (i == 1 || i == 65534) {
            v40Var.c(((int) aVarA.b) - 16);
            return new x40(i2, iU, iU2, i3, i4, iB);
        }
        Log.e("WavHeaderReader", "Unsupported WAV format type: " + i);
        return null;
    }

    public static void a(v40 v40Var, x40 x40Var) throws InterruptedException, IOException {
        e80.a(v40Var);
        e80.a(x40Var);
        v40Var.a();
        o80 o80Var = new o80(8);
        a aVarA = a.a(v40Var, o80Var);
        while (aVarA.f9846a != x80.g("data")) {
            Log.w("WavHeaderReader", "Ignoring unknown WAV chunk: " + aVarA.f9846a);
            long j = aVarA.b + 8;
            if (aVarA.f9846a == x80.g("RIFF")) {
                j = 12;
            }
            if (j <= 2147483647L) {
                v40Var.b((int) j);
                aVarA = a.a(v40Var, o80Var);
            } else {
                throw new com.google.android.exoplayer2.n("Chunk is too large (~2GB+) to skip; id: " + aVarA.f9846a);
            }
        }
        v40Var.b(8);
        x40Var.a(v40Var.c(), aVarA.b);
    }
}
