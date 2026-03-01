package supwisdom;

import com.google.android.exoplayer2.f.a;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.va0;

/* JADX INFO: compiled from: HlsMediaChunk.java */
/* JADX INFO: loaded from: classes.dex */
public final class fb0 extends w90 {
    public static final AtomicInteger G = new AtomicInteger();
    public int A;
    public int B;
    public boolean C;
    public jb0 D;
    public volatile boolean E;
    public volatile boolean F;
    public final int j;
    public final int k;
    public final va0.a l;
    public final s70 m;
    public final u70 n;
    public final boolean o;
    public final boolean p;
    public final u80 q;
    public final String r;
    public final y30 s;
    public final boolean t;
    public final boolean u;
    public final List<com.google.android.exoplayer2.j> v;
    public final boolean w;
    public final l50 x;
    public final o80 y;
    public y30 z;

    public fb0(s70 s70Var, u70 u70Var, u70 u70Var2, va0.a aVar, List<com.google.android.exoplayer2.j> list, int i, Object obj, long j, long j2, int i2, int i3, boolean z, u80 u80Var, fb0 fb0Var, byte[] bArr, byte[] bArr2) {
        super(a(s70Var, bArr, bArr2), u70Var, aVar.b, i, obj, j, j2, i2);
        this.k = i3;
        this.n = u70Var2;
        this.l = aVar;
        this.v = list;
        this.p = z;
        this.q = u80Var;
        this.o = this.h instanceof ab0;
        String lastPathSegment = u70Var.f9382a.getLastPathSegment();
        this.r = lastPathSegment;
        boolean z2 = lastPathSegment.endsWith(".aac") || this.r.endsWith(".ac3") || this.r.endsWith(".ec3") || this.r.endsWith(".mp3");
        this.w = z2;
        if (fb0Var != null) {
            this.x = fb0Var.x;
            this.y = fb0Var.y;
            this.s = fb0Var.z;
            boolean z3 = fb0Var.l != aVar;
            this.t = z3;
            this.u = fb0Var.k != i3 || z3;
        } else {
            this.x = z2 ? new l50() : null;
            this.y = this.w ? new o80(10) : null;
            this.s = null;
            this.t = false;
            this.u = true;
        }
        this.m = s70Var;
        this.j = G.getAndIncrement();
    }

    public void a(jb0 jb0Var) {
        this.D = jb0Var;
        jb0Var.a(this.j, this.t);
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public boolean b() {
        return this.E;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public void c() throws InterruptedException, IOException {
        if (this.z == null && !this.w) {
            this.z = i();
        }
        g();
        if (this.E) {
            return;
        }
        h();
    }

    @Override // supwisdom.n90
    public long d() {
        return this.B;
    }

    @Override // supwisdom.w90
    public boolean f() {
        return this.F;
    }

    public final void g() throws InterruptedException, IOException {
        u70 u70Var;
        if (this.s == this.z || this.C || (u70Var = this.n) == null) {
            return;
        }
        u70 u70VarA = x80.a(u70Var, this.A);
        try {
            r20 r20Var = new r20(this.m, u70VarA.c, this.m.a(u70VarA));
            int iA = 0;
            while (iA == 0) {
                try {
                    if (this.E) {
                        break;
                    } else {
                        iA = this.z.a(r20Var, (d50) null);
                    }
                } finally {
                    this.A = (int) (r20Var.c() - this.n.c);
                }
            }
            x80.a(this.h);
            this.C = true;
        } catch (Throwable th) {
            x80.a(this.h);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004b A[Catch: all -> 0x009e, TryCatch #1 {all -> 0x009e, blocks: (B:15:0x0037, B:17:0x004b, B:19:0x0058, B:21:0x0061, B:20:0x005f, B:23:0x0069, B:32:0x008a, B:30:0x007d, B:31:0x0089, B:25:0x0070, B:27:0x0074), top: B:40:0x0037, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069 A[Catch: all -> 0x009e, TRY_LEAVE, TryCatch #1 {all -> 0x009e, blocks: (B:15:0x0037, B:17:0x004b, B:19:0x0058, B:21:0x0061, B:20:0x005f, B:23:0x0069, B:32:0x008a, B:30:0x007d, B:31:0x0089, B:25:0x0070, B:27:0x0074), top: B:40:0x0037, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() throws java.lang.InterruptedException, java.io.IOException {
        /*
            r13 = this;
            boolean r0 = r13.o
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Le
            supwisdom.u70 r0 = r13.f8485a
            int r3 = r13.B
            if (r3 == 0) goto L16
            r3 = 1
            goto L17
        Le:
            supwisdom.u70 r0 = r13.f8485a
            int r3 = r13.B
            supwisdom.u70 r0 = supwisdom.x80.a(r0, r3)
        L16:
            r3 = 0
        L17:
            boolean r4 = r13.p
            if (r4 != 0) goto L21
            supwisdom.u80 r4 = r13.q
            r4.e()
            goto L37
        L21:
            supwisdom.u80 r4 = r13.q
            long r4 = r4.a()
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L37
            supwisdom.u80 r4 = r13.q
            long r5 = r13.f
            r4.a(r5)
        L37:
            supwisdom.r20 r4 = new supwisdom.r20     // Catch: java.lang.Throwable -> L9e
            supwisdom.s70 r8 = r13.h     // Catch: java.lang.Throwable -> L9e
            long r9 = r0.c     // Catch: java.lang.Throwable -> L9e
            supwisdom.s70 r5 = r13.h     // Catch: java.lang.Throwable -> L9e
            long r11 = r5.a(r0)     // Catch: java.lang.Throwable -> L9e
            r7 = r4
            r7.<init>(r8, r9, r11)     // Catch: java.lang.Throwable -> L9e
            supwisdom.y30 r0 = r13.z     // Catch: java.lang.Throwable -> L9e
            if (r0 != 0) goto L67
            long r5 = r13.a(r4)     // Catch: java.lang.Throwable -> L9e
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L5f
            supwisdom.u80 r0 = r13.q     // Catch: java.lang.Throwable -> L9e
            long r5 = r0.b(r5)     // Catch: java.lang.Throwable -> L9e
            goto L61
        L5f:
            long r5 = r13.f     // Catch: java.lang.Throwable -> L9e
        L61:
            supwisdom.y30 r0 = r13.a(r5)     // Catch: java.lang.Throwable -> L9e
            r13.z = r0     // Catch: java.lang.Throwable -> L9e
        L67:
            if (r3 == 0) goto L6e
            int r0 = r13.B     // Catch: java.lang.Throwable -> L9e
            r4.b(r0)     // Catch: java.lang.Throwable -> L9e
        L6e:
            if (r2 != 0) goto L8a
            boolean r0 = r13.E     // Catch: java.lang.Throwable -> L7c
            if (r0 != 0) goto L8a
            supwisdom.y30 r0 = r13.z     // Catch: java.lang.Throwable -> L7c
            r2 = 0
            int r2 = r0.a(r4, r2)     // Catch: java.lang.Throwable -> L7c
            goto L6e
        L7c:
            r0 = move-exception
            long r1 = r4.c()     // Catch: java.lang.Throwable -> L9e
            supwisdom.u70 r3 = r13.f8485a     // Catch: java.lang.Throwable -> L9e
            long r3 = r3.c     // Catch: java.lang.Throwable -> L9e
            long r1 = r1 - r3
            int r2 = (int) r1     // Catch: java.lang.Throwable -> L9e
            r13.B = r2     // Catch: java.lang.Throwable -> L9e
            throw r0     // Catch: java.lang.Throwable -> L9e
        L8a:
            long r2 = r4.c()     // Catch: java.lang.Throwable -> L9e
            supwisdom.u70 r0 = r13.f8485a     // Catch: java.lang.Throwable -> L9e
            long r4 = r0.c     // Catch: java.lang.Throwable -> L9e
            long r2 = r2 - r4
            int r0 = (int) r2     // Catch: java.lang.Throwable -> L9e
            r13.B = r0     // Catch: java.lang.Throwable -> L9e
            supwisdom.s70 r0 = r13.h
            supwisdom.x80.a(r0)
            r13.F = r1
            return
        L9e:
            r0 = move-exception
            supwisdom.s70 r1 = r13.h
            supwisdom.x80.a(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fb0.h():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.y30 i() {
        /*
            r6 = this;
            supwisdom.va0$a r0 = r6.l
            com.google.android.exoplayer2.j r0 = r0.b
            java.lang.String r0 = r0.f
            java.lang.String r1 = "text/vtt"
            boolean r0 = r1.equals(r0)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L93
            java.lang.String r0 = r6.r
            java.lang.String r3 = ".webvtt"
            boolean r0 = r0.endsWith(r3)
            if (r0 != 0) goto L93
            java.lang.String r0 = r6.r
            java.lang.String r3 = ".vtt"
            boolean r0 = r0.endsWith(r3)
            if (r0 == 0) goto L26
            goto L93
        L26:
            boolean r0 = r6.u
            if (r0 != 0) goto L2e
            supwisdom.y30 r0 = r6.s
            goto L9f
        L2e:
            java.lang.String r0 = r6.r
            java.lang.String r3 = ".mp4"
            boolean r0 = r0.endsWith(r3)
            if (r0 != 0) goto L8b
            java.lang.String r0 = r6.r
            int r3 = r0.length()
            int r3 = r3 + (-4)
            java.lang.String r4 = ".m4"
            boolean r0 = r0.startsWith(r4, r3)
            if (r0 == 0) goto L49
            goto L8b
        L49:
            r0 = 16
            java.util.List<com.google.android.exoplayer2.j> r1 = r6.v
            if (r1 == 0) goto L52
            r0 = 48
            goto L56
        L52:
            java.util.List r1 = java.util.Collections.emptyList()
        L56:
            com.google.android.exoplayer2.j r3 = r6.c
            java.lang.String r3 = r3.c
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L7c
            java.lang.String r4 = supwisdom.l80.e(r3)
            java.lang.String r5 = "audio/mp4a-latm"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L6e
            r0 = r0 | 2
        L6e:
            java.lang.String r3 = supwisdom.l80.d(r3)
            java.lang.String r4 = "video/avc"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L7c
            r0 = r0 | 4
        L7c:
            supwisdom.t40 r3 = new supwisdom.t40
            supwisdom.u80 r4 = r6.q
            supwisdom.d40 r5 = new supwisdom.d40
            r5.<init>(r0, r1)
            r0 = 2
            r3.<init>(r0, r4, r5)
            r0 = r3
            goto L9e
        L8b:
            supwisdom.c30 r0 = new supwisdom.c30
            supwisdom.u80 r3 = r6.q
            r0.<init>(r1, r3)
            goto L9e
        L93:
            supwisdom.lb0 r0 = new supwisdom.lb0
            com.google.android.exoplayer2.j r1 = r6.c
            java.lang.String r1 = r1.y
            supwisdom.u80 r3 = r6.q
            r0.<init>(r1, r3)
        L9e:
            r1 = 1
        L9f:
            if (r1 == 0) goto La6
            supwisdom.jb0 r1 = r6.D
            r0.a(r1)
        La6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fb0.i():supwisdom.y30");
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public void a() {
        this.E = true;
    }

    public final long a(v40 v40Var) throws InterruptedException, IOException {
        com.google.android.exoplayer2.f.a aVarA;
        v40Var.a();
        if (!v40Var.b(this.y.f8644a, 0, 10, true)) {
            return -9223372036854775807L;
        }
        this.y.a(10);
        if (this.y.k() != l50.b) {
            return -9223372036854775807L;
        }
        this.y.d(3);
        int iS = this.y.s();
        int i = iS + 10;
        if (i > this.y.e()) {
            o80 o80Var = this.y;
            byte[] bArr = o80Var.f8644a;
            o80Var.a(i);
            System.arraycopy(bArr, 0, this.y.f8644a, 0, 10);
        }
        if (!v40Var.b(this.y.f8644a, 10, iS, true) || (aVarA = this.x.a(this.y.f8644a, iS)) == null) {
            return -9223372036854775807L;
        }
        int iA = aVarA.a();
        for (int i2 = 0; i2 < iA; i2++) {
            a.InterfaceC0053a interfaceC0053aA = aVarA.a(i2);
            if (interfaceC0053aA instanceof com.google.android.exoplayer2.f.b.i) {
                com.google.android.exoplayer2.f.b.i iVar = (com.google.android.exoplayer2.f.b.i) interfaceC0053aA;
                if ("com.apple.streaming.transportStreamTimestamp".equals(iVar.b)) {
                    System.arraycopy(iVar.c, 0, this.y.f8644a, 0, 8);
                    this.y.a(8);
                    return this.y.p();
                }
            }
        }
        return -9223372036854775807L;
    }

    public static s70 a(s70 s70Var, byte[] bArr, byte[] bArr2) {
        return (bArr == null || bArr2 == null) ? s70Var : new ab0(s70Var, bArr, bArr2);
    }

    public final y30 a(long j) {
        y30 z30Var;
        if (this.r.endsWith(".aac")) {
            z30Var = new b40(j);
        } else if (!this.r.endsWith(".ac3") && !this.r.endsWith(".ec3")) {
            if (this.r.endsWith(".mp3")) {
                z30Var = new t20(0, j);
            } else {
                throw new IllegalArgumentException("Unkown extension for audio file: " + this.r);
            }
        } else {
            z30Var = new z30(j);
        }
        z30Var.a(this.D);
        return z30Var;
    }
}
