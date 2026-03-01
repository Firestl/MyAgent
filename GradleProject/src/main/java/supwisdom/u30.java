package supwisdom;

import java.io.IOException;
import java.util.ArrayList;
import supwisdom.s30;
import supwisdom.v30;

/* JADX INFO: compiled from: VorbisReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class u30 extends s30 {
    public a n;
    public int o;
    public boolean p;
    public v30.d q;
    public v30.b r;

    /* JADX INFO: compiled from: VorbisReader.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final v30.d f9362a;
        public final byte[] b;
        public final v30.c[] c;
        public final int d;

        public a(v30.d dVar, v30.b bVar, byte[] bArr, v30.c[] cVarArr, int i) {
            this.f9362a = dVar;
            this.b = bArr;
            this.c = cVarArr;
            this.d = i;
        }
    }

    public static int a(byte b, int i, int i2) {
        return (b >> i2) & (255 >>> (8 - i));
    }

    public static boolean c(o80 o80Var) {
        try {
            return v30.a(1, o80Var, true);
        } catch (com.google.android.exoplayer2.n unused) {
            return false;
        }
    }

    @Override // supwisdom.s30
    public void a(boolean z) {
        super.a(z);
        if (z) {
            this.n = null;
            this.q = null;
            this.r = null;
        }
        this.o = 0;
        this.p = false;
    }

    public a b(o80 o80Var) throws IOException {
        if (this.q == null) {
            this.q = v30.a(o80Var);
            return null;
        }
        if (this.r == null) {
            this.r = v30.b(o80Var);
            return null;
        }
        byte[] bArr = new byte[o80Var.c()];
        System.arraycopy(o80Var.f8644a, 0, bArr, 0, o80Var.c());
        return new a(this.q, this.r, bArr, v30.a(o80Var, this.q.f9476a), v30.a(r5.length - 1));
    }

    @Override // supwisdom.s30
    public void c(long j) {
        super.c(j);
        this.p = j != 0;
        v30.d dVar = this.q;
        this.o = dVar != null ? dVar.d : 0;
    }

    @Override // supwisdom.s30
    public long a(o80 o80Var) {
        byte[] bArr = o80Var.f8644a;
        if ((bArr[0] & 1) == 1) {
            return -1L;
        }
        int iA = a(bArr[0], this.n);
        long j = this.p ? (this.o + iA) / 4 : 0;
        a(o80Var, j);
        this.p = true;
        this.o = iA;
        return j;
    }

    @Override // supwisdom.s30
    public boolean a(o80 o80Var, long j, s30.b bVar) throws InterruptedException, IOException {
        if (this.n != null) {
            return false;
        }
        a aVarB = b(o80Var);
        this.n = aVarB;
        if (aVarB == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.n.f9362a.f);
        arrayList.add(this.n.b);
        v30.d dVar = this.n.f9362a;
        bVar.f9118a = com.google.android.exoplayer2.j.a(null, "audio/vorbis", null, dVar.c, -1, dVar.f9476a, (int) dVar.b, arrayList, null, 0, null);
        return true;
    }

    public static void a(o80 o80Var, long j) {
        o80Var.b(o80Var.c() + 4);
        o80Var.f8644a[o80Var.c() - 4] = (byte) (j & 255);
        o80Var.f8644a[o80Var.c() - 3] = (byte) ((j >>> 8) & 255);
        o80Var.f8644a[o80Var.c() - 2] = (byte) ((j >>> 16) & 255);
        o80Var.f8644a[o80Var.c() - 1] = (byte) ((j >>> 24) & 255);
    }

    public static int a(byte b, a aVar) {
        if (!aVar.c[a(b, aVar.d, 1)].f9475a) {
            return aVar.f9362a.d;
        }
        return aVar.f9362a.f9477e;
    }
}
