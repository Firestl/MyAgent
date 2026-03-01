package supwisdom;

import android.net.Uri;
import android.os.SystemClock;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import supwisdom.va0;
import supwisdom.wa0;

/* JADX INFO: compiled from: HlsChunkSource.java */
/* JADX INFO: loaded from: classes.dex */
public class cb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s70 f7171a;
    public final s70 b;
    public final kb0 c;
    public final va0.a[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final za0 f7172e;
    public final pb0 f;
    public final List<com.google.android.exoplayer2.j> g;
    public boolean h;
    public byte[] i;
    public IOException j;
    public va0.a k;
    public Uri l;
    public byte[] m;
    public String n;
    public byte[] o;
    public k70 p;

    /* JADX INFO: compiled from: HlsChunkSource.java */
    public static final class a extends u90 {
        public final String l;
        public byte[] m;

        public a(s70 s70Var, u70 u70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, byte[] bArr, String str) {
            super(s70Var, u70Var, 3, jVar, i, obj, bArr);
            this.l = str;
        }

        @Override // supwisdom.u90
        public void a(byte[] bArr, int i) throws IOException {
            this.m = Arrays.copyOf(bArr, i);
        }

        public byte[] f() {
            return this.m;
        }
    }

    /* JADX INFO: compiled from: HlsChunkSource.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public n90 f7173a;
        public boolean b;
        public va0.a c;

        public b() {
            a();
        }

        public void a() {
            this.f7173a = null;
            this.b = false;
            this.c = null;
        }
    }

    public cb0(za0 za0Var, va0.a[] aVarArr, db0 db0Var, kb0 kb0Var, List<com.google.android.exoplayer2.j> list) {
        this.f7172e = za0Var;
        this.d = aVarArr;
        this.c = kb0Var;
        this.g = list;
        com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[aVarArr.length];
        int[] iArr = new int[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            jVarArr[i] = aVarArr[i].b;
            iArr[i] = i;
        }
        this.f7171a = db0Var.a(1);
        this.b = db0Var.a(3);
        pb0 pb0Var = new pb0(jVarArr);
        this.f = pb0Var;
        this.p = new c(pb0Var, iArr);
    }

    public void a() throws IOException {
        IOException iOException = this.j;
        if (iOException != null) {
            throw iOException;
        }
        va0.a aVar = this.k;
        if (aVar != null) {
            this.f7172e.c(aVar);
        }
    }

    public pb0 b() {
        return this.f;
    }

    public void c() {
        this.j = null;
    }

    public final void d() {
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
    }

    public void a(k70 k70Var) {
        this.p = k70Var;
    }

    /* JADX INFO: compiled from: HlsChunkSource.java */
    public static final class c extends g70 {
        public int g;

        public c(pb0 pb0Var, int[] iArr) {
            super(pb0Var, iArr);
            this.g = a(pb0Var.a(0));
        }

        @Override // supwisdom.k70
        public void a(long j) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (b(this.g, jElapsedRealtime)) {
                for (int i = this.b - 1; i >= 0; i--) {
                    if (!b(i, jElapsedRealtime)) {
                        this.g = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        @Override // supwisdom.k70
        public int b() {
            return 0;
        }

        @Override // supwisdom.k70
        public Object c() {
            return null;
        }

        @Override // supwisdom.k70
        public int a() {
            return this.g;
        }
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void a(fb0 fb0Var, long j, b bVar) {
        int size;
        int iA = fb0Var == null ? -1 : this.f.a(fb0Var.c);
        this.k = null;
        this.p.a(fb0Var != null ? Math.max(0L, fb0Var.f - j) : 0L);
        int iG = this.p.g();
        boolean z = iA != iG;
        va0.a aVar = this.d[iG];
        if (!this.f7172e.b(aVar)) {
            bVar.c = aVar;
            this.k = aVar;
            return;
        }
        wa0 wa0VarA = this.f7172e.a(aVar);
        if (fb0Var != null && !z) {
            size = fb0Var.e();
        } else {
            long j2 = fb0Var == null ? j : fb0Var.f;
            if (!wa0VarA.j && j2 > wa0VarA.a()) {
                size = wa0VarA.g + wa0VarA.m.size();
            } else {
                int iA2 = x80.a((List<? extends Comparable<? super Long>>) wa0VarA.m, Long.valueOf(j2 - wa0VarA.d), true, !this.f7172e.e() || fb0Var == null);
                int i = wa0VarA.g;
                int iE = iA2 + i;
                if (iE < i && fb0Var != null) {
                    aVar = this.d[iA];
                    wa0 wa0VarA2 = this.f7172e.a(aVar);
                    iE = fb0Var.e();
                    wa0VarA = wa0VarA2;
                    iG = iA;
                }
                size = iE;
            }
        }
        int i2 = size;
        va0.a aVar2 = aVar;
        int i3 = wa0VarA.g;
        if (i2 < i3) {
            this.j = new com.google.android.exoplayer2.source.b();
            return;
        }
        int i4 = i2 - i3;
        if (i4 >= wa0VarA.m.size()) {
            if (wa0VarA.j) {
                bVar.b = true;
                return;
            } else {
                bVar.c = aVar2;
                this.k = aVar2;
                return;
            }
        }
        wa0.a aVar3 = wa0VarA.m.get(i4);
        if (aVar3.f9606e) {
            Uri uriA = w80.a(wa0VarA.f9726a, aVar3.f);
            if (!uriA.equals(this.l)) {
                bVar.f7173a = a(uriA, aVar3.g, iG, this.p.b(), this.p.c());
                return;
            } else if (!x80.a(aVar3.g, this.n)) {
                a(uriA, aVar3.g, this.m);
            }
        } else {
            d();
        }
        wa0.a aVar4 = wa0VarA.l;
        u70 u70Var = aVar4 != null ? new u70(w80.a(wa0VarA.f9726a, aVar4.f9605a), aVar4.h, aVar4.i, null) : null;
        long j3 = wa0VarA.d + aVar3.d;
        int i5 = wa0VarA.f + aVar3.c;
        bVar.f7173a = new fb0(this.f7171a, new u70(w80.a(wa0VarA.f9726a, aVar3.f9605a), aVar3.h, aVar3.i, null), u70Var, aVar2, this.g, this.p.b(), this.p.c(), j3, j3 + aVar3.b, i2, i5, this.h, this.c.a(i5), fb0Var, this.m, this.o);
    }

    public void a(n90 n90Var) {
        if (n90Var instanceof a) {
            a aVar = (a) n90Var;
            this.i = aVar.e();
            a(aVar.f8485a.f9382a, aVar.l, aVar.f());
        }
    }

    public boolean a(n90 n90Var, boolean z, IOException iOException) {
        if (z) {
            k70 k70Var = this.p;
            if (s90.a(k70Var, k70Var.c(this.f.a(n90Var.c)), iOException)) {
                return true;
            }
        }
        return false;
    }

    public void a(va0.a aVar, long j) {
        int iC;
        int iA = this.f.a(aVar.b);
        if (iA == -1 || (iC = this.p.c(iA)) == -1) {
            return;
        }
        this.p.a(iC, j);
    }

    public final a a(Uri uri, String str, int i, int i2, Object obj) {
        return new a(this.b, new u70(uri, 0L, -1L, null, 1), this.d[i].b, i2, obj, this.i, str);
    }

    public final void a(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(str.toLowerCase(Locale.getDefault()).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (16 - byteArray.length) + length, byteArray.length - length);
        this.l = uri;
        this.m = bArr;
        this.n = str;
        this.o = bArr2;
    }
}
