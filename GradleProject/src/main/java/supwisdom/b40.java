package supwisdom;

import java.io.IOException;
import supwisdom.e50;
import supwisdom.u40;

/* JADX INFO: compiled from: AdtsExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class b40 implements y30 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f7019e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f7020a;
    public final o80 b;
    public c40 c;
    public boolean d;

    /* JADX INFO: compiled from: AdtsExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new b40()};
        }
    }

    static {
        new a();
        f7019e = x80.g("ID3");
    }

    public b40() {
        this(0L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0048, code lost:
    
        if ((r5 - r4) < 8192) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x004a, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003f, code lost:
    
        r11.a();
        r5 = r5 + 1;
     */
    @Override // supwisdom.y30
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(supwisdom.v40 r11) throws java.lang.InterruptedException, java.io.IOException {
        /*
            r10 = this;
            supwisdom.o80 r0 = new supwisdom.o80
            r1 = 10
            r0.<init>(r1)
            supwisdom.n80 r2 = new supwisdom.n80
            byte[] r3 = r0.f8644a
            r2.<init>(r3)
            r3 = 0
            r4 = 0
        L10:
            byte[] r5 = r0.f8644a
            r11.c(r5, r3, r1)
            r0.c(r3)
            int r5 = r0.k()
            int r6 = supwisdom.b40.f7019e
            if (r5 == r6) goto L74
            r11.a()
            r11.c(r4)
            r5 = r4
        L27:
            r1 = 0
            r6 = 0
        L29:
            byte[] r7 = r0.f8644a
            r8 = 2
            r11.c(r7, r3, r8)
            r0.c(r3)
            int r7 = r0.h()
            r8 = 65526(0xfff6, float:9.1821E-41)
            r7 = r7 & r8
            r8 = 65520(0xfff0, float:9.1813E-41)
            if (r7 == r8) goto L4f
            r11.a()
            int r5 = r5 + 1
            int r1 = r5 - r4
            r6 = 8192(0x2000, float:1.148E-41)
            if (r1 < r6) goto L4b
            return r3
        L4b:
            r11.c(r5)
            goto L27
        L4f:
            r7 = 1
            int r1 = r1 + r7
            r8 = 4
            if (r1 < r8) goto L59
            r9 = 188(0xbc, float:2.63E-43)
            if (r6 <= r9) goto L59
            return r7
        L59:
            byte[] r7 = r0.f8644a
            r11.c(r7, r3, r8)
            r7 = 14
            r2.a(r7)
            r7 = 13
            int r7 = r2.c(r7)
            r8 = 6
            if (r7 > r8) goto L6d
            return r3
        L6d:
            int r8 = r7 + (-6)
            r11.c(r8)
            int r6 = r6 + r7
            goto L29
        L74:
            r5 = 3
            r0.d(r5)
            int r5 = r0.s()
            int r6 = r5 + 10
            int r4 = r4 + r6
            r11.c(r5)
            goto L10
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.b40.a(supwisdom.v40):boolean");
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public b40(long j) {
        this.f7020a = j;
        this.b = new o80(200);
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        c40 c40Var = new c40(true);
        this.c = c40Var;
        c40Var.a(z40Var, new u40.d(0, 1));
        z40Var.a();
        z40Var.a(new e50.a(-9223372036854775807L));
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.d = false;
        this.c.a();
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        int iA = v40Var.a(this.b.f8644a, 0, 200);
        if (iA == -1) {
            return -1;
        }
        this.b.c(0);
        this.b.b(iA);
        if (!this.d) {
            this.c.a(this.f7020a, true);
            this.d = true;
        }
        this.c.a(this.b);
        return 0;
    }
}
