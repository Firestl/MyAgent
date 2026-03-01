package supwisdom;

import java.io.IOException;
import supwisdom.e50;
import supwisdom.u40;

/* JADX INFO: compiled from: Ac3Extractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class z30 implements y30 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f9965e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f9966a;
    public final o80 b;
    public a40 c;
    public boolean d;

    /* JADX INFO: compiled from: Ac3Extractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new z30()};
        }
    }

    static {
        new a();
        f9965e = x80.g("ID3");
    }

    public z30() {
        this(0L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x003b, code lost:
    
        if ((r4 - r3) < 8192) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003d, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0032, code lost:
    
        r8.a();
        r4 = r4 + 1;
     */
    @Override // supwisdom.y30
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(supwisdom.v40 r8) throws java.lang.InterruptedException, java.io.IOException {
        /*
            r7 = this;
            supwisdom.o80 r0 = new supwisdom.o80
            r1 = 10
            r0.<init>(r1)
            r2 = 0
            r3 = 0
        L9:
            byte[] r4 = r0.f8644a
            r8.c(r4, r2, r1)
            r0.c(r2)
            int r4 = r0.k()
            int r5 = supwisdom.z30.f9965e
            if (r4 == r5) goto L58
            r8.a()
            r8.c(r3)
            r4 = r3
        L20:
            r1 = 0
        L21:
            byte[] r5 = r0.f8644a
            r6 = 5
            r8.c(r5, r2, r6)
            r0.c(r2)
            int r5 = r0.h()
            r6 = 2935(0xb77, float:4.113E-42)
            if (r5 == r6) goto L42
            r8.a()
            int r4 = r4 + 1
            int r1 = r4 - r3
            r5 = 8192(0x2000, float:1.148E-41)
            if (r1 < r5) goto L3e
            return r2
        L3e:
            r8.c(r4)
            goto L20
        L42:
            r5 = 1
            int r1 = r1 + r5
            r6 = 4
            if (r1 < r6) goto L48
            return r5
        L48:
            byte[] r5 = r0.f8644a
            int r5 = supwisdom.k10.a(r5)
            r6 = -1
            if (r5 != r6) goto L52
            return r2
        L52:
            int r5 = r5 + (-5)
            r8.c(r5)
            goto L21
        L58:
            r4 = 3
            r0.d(r4)
            int r4 = r0.s()
            int r5 = r4 + 10
            int r3 = r3 + r5
            r8.c(r4)
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.z30.a(supwisdom.v40):boolean");
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public z30(long j) {
        this.f9966a = j;
        this.b = new o80(2786);
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        a40 a40Var = new a40();
        this.c = a40Var;
        a40Var.a(z40Var, new u40.d(0, 1));
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
        int iA = v40Var.a(this.b.f8644a, 0, 2786);
        if (iA == -1) {
            return -1;
        }
        this.b.c(0);
        this.b.b(iA);
        if (!this.d) {
            this.c.a(this.f9966a, true);
            this.d = true;
        }
        this.c.a(this.b);
        return 0;
    }
}
