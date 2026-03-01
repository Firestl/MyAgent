package supwisdom;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import supwisdom.e50;
import supwisdom.u40;

/* JADX INFO: compiled from: TsExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class t40 implements y30 {
    public static final long m;
    public static final long n;
    public static final long o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9247a;
    public final List<u80> b;
    public final o80 c;
    public final n80 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SparseIntArray f9248e;
    public final u40.c f;
    public final SparseArray<u40> g;
    public final SparseBooleanArray h;
    public z40 i;
    public int j;
    public boolean k;
    public u40 l;

    /* JADX INFO: compiled from: TsExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new t40()};
        }
    }

    /* JADX INFO: compiled from: TsExtractor.java */
    public class b implements p40 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n80 f9249a = new n80(new byte[4]);

        public b() {
        }

        @Override // supwisdom.p40
        public void a(o80 o80Var) {
            if (o80Var.g() != 0) {
                return;
            }
            o80Var.d(7);
            int iB = o80Var.b() / 4;
            for (int i = 0; i < iB; i++) {
                o80Var.a(this.f9249a, 4);
                int iC = this.f9249a.c(16);
                this.f9249a.b(3);
                if (iC == 0) {
                    this.f9249a.b(13);
                } else {
                    int iC2 = this.f9249a.c(13);
                    t40.this.g.put(iC2, new q40(t40.this.new c(iC2)));
                    t40.b(t40.this);
                }
            }
            if (t40.this.f9247a != 2) {
                t40.this.g.remove(0);
            }
        }

        @Override // supwisdom.p40
        public void a(u80 u80Var, z40 z40Var, u40.d dVar) {
        }
    }

    static {
        new a();
        m = x80.g("AC-3");
        n = x80.g("EAC3");
        o = x80.g("HEVC");
    }

    public t40() {
        this(0);
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public t40(int i) {
        this(1, i);
    }

    public static /* synthetic */ int b(t40 t40Var) {
        int i = t40Var.j;
        t40Var.j = i + 1;
        return i;
    }

    public t40(int i, int i2) {
        this(i, new u80(0L), new d40(i2));
    }

    public t40(int i, u80 u80Var, u40.c cVar) {
        e80.a(cVar);
        this.f = cVar;
        this.f9247a = i;
        if (i != 1 && i != 2) {
            ArrayList arrayList = new ArrayList();
            this.b = arrayList;
            arrayList.add(u80Var);
        } else {
            this.b = Collections.singletonList(u80Var);
        }
        this.c = new o80(940);
        this.d = new n80(new byte[3]);
        this.h = new SparseBooleanArray();
        this.g = new SparseArray<>();
        this.f9248e = new SparseIntArray();
        a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
    
        r2 = r2 + 1;
     */
    @Override // supwisdom.y30
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(supwisdom.v40 r7) throws java.lang.InterruptedException, java.io.IOException {
        /*
            r6 = this;
            supwisdom.o80 r0 = r6.c
            byte[] r0 = r0.f8644a
            r1 = 0
            r2 = 940(0x3ac, float:1.317E-42)
            r7.c(r0, r1, r2)
            r2 = 0
        Lb:
            r3 = 188(0xbc, float:2.63E-43)
            if (r2 >= r3) goto L27
            r3 = 0
        L10:
            r4 = 5
            if (r3 != r4) goto L18
            r7.b(r2)
            r7 = 1
            return r7
        L18:
            int r4 = r3 * 188
            int r4 = r4 + r2
            r4 = r0[r4]
            r5 = 71
            if (r4 == r5) goto L24
            int r2 = r2 + 1
            goto Lb
        L24:
            int r3 = r3 + 1
            goto L10
        L27:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.t40.a(supwisdom.v40):boolean");
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.i = z40Var;
        z40Var.a(new e50.a(-9223372036854775807L));
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.b.get(i).d();
        }
        this.c.a();
        this.f9248e.clear();
        a();
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        boolean z;
        u40 u40Var;
        o80 o80Var = this.c;
        byte[] bArr = o80Var.f8644a;
        if (940 - o80Var.d() < 188) {
            int iB = this.c.b();
            if (iB > 0) {
                System.arraycopy(bArr, this.c.d(), bArr, 0, iB);
            }
            this.c.a(bArr, iB);
        }
        while (this.c.b() < 188) {
            int iC = this.c.c();
            int iA = v40Var.a(bArr, iC, 940 - iC);
            if (iA == -1) {
                return -1;
            }
            this.c.b(iC + iA);
        }
        int iC2 = this.c.c();
        int iD = this.c.d();
        while (iD < iC2 && bArr[iD] != 71) {
            iD++;
        }
        this.c.c(iD);
        int i = iD + 188;
        if (i > iC2) {
            return 0;
        }
        this.c.d(1);
        this.c.a(this.d, 3);
        if (this.d.d()) {
            this.c.c(i);
            return 0;
        }
        boolean zD = this.d.d();
        this.d.b(1);
        int iC3 = this.d.c(13);
        this.d.b(2);
        boolean zD2 = this.d.d();
        boolean zD3 = this.d.d();
        int iC4 = this.d.c(4);
        if (this.f9247a != 2) {
            int i2 = this.f9248e.get(iC3, iC4 - 1);
            this.f9248e.put(iC3, iC4);
            if (i2 != iC4) {
                z = iC4 != (i2 + 1) % 16;
            } else if (zD3) {
                this.c.c(i);
                return 0;
            }
        }
        if (zD2) {
            this.c.d(this.c.g());
        }
        if (zD3 && (u40Var = this.g.get(iC3)) != null) {
            if (z) {
                u40Var.a();
            }
            this.c.b(i);
            u40Var.a(this.c, zD);
            e80.b(this.c.d() <= i);
            this.c.b(iC2);
        }
        this.c.c(i);
        return 0;
    }

    /* JADX INFO: compiled from: TsExtractor.java */
    public class c implements p40 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n80 f9250a = new n80(new byte[5]);
        public final int b;

        public c(int i) {
            this.b = i;
        }

        @Override // supwisdom.p40
        public void a(o80 o80Var) {
            u80 u80Var;
            u40 u40VarA;
            if (o80Var.g() != 2) {
                return;
            }
            if (t40.this.f9247a == 1 || t40.this.f9247a == 2 || t40.this.j == 1) {
                u80Var = (u80) t40.this.b.get(0);
            } else {
                u80Var = new u80(((u80) t40.this.b.get(0)).a());
                t40.this.b.add(u80Var);
            }
            o80Var.d(2);
            int iH = o80Var.h();
            int i = 5;
            o80Var.d(5);
            o80Var.a(this.f9250a, 2);
            int i2 = 4;
            this.f9250a.b(4);
            o80Var.d(this.f9250a.c(12));
            if (t40.this.f9247a == 2 && t40.this.l == null) {
                u40.b bVar = new u40.b(21, null, null, new byte[0]);
                t40 t40Var = t40.this;
                t40Var.l = t40Var.f.a(21, bVar);
                t40.this.l.a(u80Var, t40.this.i, new u40.d(iH, 21, 8192));
            }
            int iB = o80Var.b();
            while (iB > 0) {
                o80Var.a(this.f9250a, i);
                int iC = this.f9250a.c(8);
                this.f9250a.b(3);
                int iC2 = this.f9250a.c(13);
                this.f9250a.b(i2);
                int iC3 = this.f9250a.c(12);
                u40.b bVarA = a(o80Var, iC3);
                if (iC == 6) {
                    iC = bVarA.f9366a;
                }
                iB -= iC3 + 5;
                int i3 = t40.this.f9247a == 2 ? iC : iC2;
                if (!t40.this.h.get(i3)) {
                    t40.this.h.put(i3, true);
                    if (t40.this.f9247a == 2 && iC == 21) {
                        u40VarA = t40.this.l;
                    } else {
                        u40VarA = t40.this.f.a(iC, bVarA);
                        if (u40VarA != null) {
                            u40VarA.a(u80Var, t40.this.i, new u40.d(iH, i3, 8192));
                        }
                    }
                    if (u40VarA != null) {
                        t40.this.g.put(iC2, u40VarA);
                    }
                }
                i = 5;
                i2 = 4;
            }
            if (t40.this.f9247a == 2) {
                if (t40.this.k) {
                    return;
                }
                t40.this.i.a();
                t40.this.j = 0;
                t40.this.k = true;
                return;
            }
            t40.this.g.remove(this.b);
            t40 t40Var2 = t40.this;
            t40Var2.j = t40Var2.f9247a != 1 ? t40.this.j - 1 : 0;
            if (t40.this.j == 0) {
                t40.this.i.a();
                t40.this.k = true;
            }
        }

        @Override // supwisdom.p40
        public void a(u80 u80Var, z40 z40Var, u40.d dVar) {
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final supwisdom.u40.b a(supwisdom.o80 r13, int r14) {
            /*
                r12 = this;
                int r0 = r13.d()
                int r14 = r14 + r0
                r1 = 0
                r2 = -1
                r2 = r1
                r3 = -1
            L9:
                int r4 = r13.d()
                if (r4 >= r14) goto L9e
                int r4 = r13.g()
                int r5 = r13.g()
                int r6 = r13.d()
                int r6 = r6 + r5
                r5 = 5
                r7 = 89
                r8 = 135(0x87, float:1.89E-43)
                r9 = 129(0x81, float:1.81E-43)
                if (r4 != r5) goto L46
                long r4 = r13.l()
                long r10 = supwisdom.t40.b()
                int r7 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
                if (r7 != 0) goto L32
                goto L4a
            L32:
                long r9 = supwisdom.t40.d()
                int r7 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
                if (r7 != 0) goto L3b
                goto L51
            L3b:
                long r7 = supwisdom.t40.e()
                int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r9 != 0) goto L94
                r3 = 36
                goto L94
            L46:
                r5 = 106(0x6a, float:1.49E-43)
                if (r4 != r5) goto L4d
            L4a:
                r3 = 129(0x81, float:1.81E-43)
                goto L94
            L4d:
                r5 = 122(0x7a, float:1.71E-43)
                if (r4 != r5) goto L54
            L51:
                r3 = 135(0x87, float:1.89E-43)
                goto L94
            L54:
                r5 = 123(0x7b, float:1.72E-43)
                if (r4 != r5) goto L5b
                r3 = 138(0x8a, float:1.93E-43)
                goto L94
            L5b:
                r5 = 10
                r8 = 3
                if (r4 != r5) goto L69
                java.lang.String r1 = r13.e(r8)
                java.lang.String r1 = r1.trim()
                goto L94
            L69:
                if (r4 != r7) goto L94
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
            L70:
                int r3 = r13.d()
                if (r3 >= r6) goto L92
                java.lang.String r3 = r13.e(r8)
                java.lang.String r3 = r3.trim()
                int r4 = r13.g()
                r5 = 4
                byte[] r9 = new byte[r5]
                r10 = 0
                r13.a(r9, r10, r5)
                supwisdom.u40$a r5 = new supwisdom.u40$a
                r5.<init>(r3, r4, r9)
                r2.add(r5)
                goto L70
            L92:
                r3 = 89
            L94:
                int r4 = r13.d()
                int r6 = r6 - r4
                r13.d(r6)
                goto L9
            L9e:
                r13.c(r14)
                supwisdom.u40$b r4 = new supwisdom.u40$b
                byte[] r13 = r13.f8644a
                byte[] r13 = java.util.Arrays.copyOfRange(r13, r0, r14)
                r4.<init>(r3, r1, r2, r13)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.t40.c.a(supwisdom.o80, int):supwisdom.u40$b");
        }
    }

    public final void a() {
        this.h.clear();
        this.g.clear();
        SparseArray<u40> sparseArrayA = this.f.a();
        int size = sparseArrayA.size();
        for (int i = 0; i < size; i++) {
            this.g.put(sparseArrayA.keyAt(i), sparseArrayA.valueAt(i));
        }
        this.g.put(0, new q40(new b()));
        this.l = null;
    }
}
