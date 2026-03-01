package supwisdom;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.c.a;
import com.taobao.weex.wson.Wson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import supwisdom.e50;
import supwisdom.y20;

/* JADX INFO: compiled from: FragmentedMp4Extractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class c30 implements y30 {
    public static final int E;
    public static final byte[] F;
    public z40 A;
    public f50 B;
    public f50[] C;
    public boolean D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7136a;
    public final h30 b;
    public final SparseArray<c> c;
    public final o80 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o80 f7137e;
    public final o80 f;
    public final o80 g;
    public final u80 h;
    public final o80 i;
    public final byte[] j;
    public final Stack<y20.a> k;
    public final LinkedList<b> l;
    public int m;
    public int n;
    public long o;
    public int p;
    public o80 q;
    public long r;
    public int s;
    public long t;
    public long u;
    public c v;
    public int w;
    public int x;
    public int y;
    public boolean z;

    /* JADX INFO: compiled from: FragmentedMp4Extractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new c30()};
        }
    }

    /* JADX INFO: compiled from: FragmentedMp4Extractor.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f7138a;
        public final int b;

        public b(long j, int i) {
            this.f7138a = j;
            this.b = i;
        }
    }

    static {
        new a();
        E = x80.g("seig");
        F = new byte[]{-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, Wson.NUMBER_LONG_TYPE, 66, 124, Wson.NUMBER_DOUBLE_TYPE, -115, -12};
    }

    public c30() {
        this(0);
    }

    public static long d(o80 o80Var) {
        o80Var.c(8);
        return y20.a(o80Var.n()) == 1 ? o80Var.v() : o80Var.l();
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        return g30.a(v40Var);
    }

    public final boolean b(v40 v40Var) throws InterruptedException, IOException {
        if (this.p == 0) {
            if (!v40Var.a(this.i.f8644a, 0, 8, true)) {
                return false;
            }
            this.p = 8;
            this.i.c(0);
            this.o = this.i.l();
            this.n = this.i.n();
        }
        if (this.o == 1) {
            v40Var.b(this.i.f8644a, 8, 8);
            this.p += 8;
            this.o = this.i.v();
        }
        if (this.o < this.p) {
            throw new com.google.android.exoplayer2.n("Atom size less than header length (unsupported).");
        }
        long jC = v40Var.c() - ((long) this.p);
        if (this.n == y20.L) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                j30 j30Var = this.c.valueAt(i).f7139a;
                j30Var.b = jC;
                j30Var.d = jC;
                j30Var.c = jC;
            }
        }
        int i2 = this.n;
        if (i2 == y20.i) {
            this.v = null;
            this.r = jC + this.o;
            if (!this.D) {
                this.A.a(new e50.a(this.t));
                this.D = true;
            }
            this.m = 2;
            return true;
        }
        if (b(i2)) {
            long jC2 = (v40Var.c() + this.o) - 8;
            this.k.add(new y20.a(this.n, jC2));
            if (this.o == this.p) {
                a(jC2);
            } else {
                a();
            }
        } else if (a(this.n)) {
            if (this.p != 8) {
                throw new com.google.android.exoplayer2.n("Leaf atom defines extended atom size (unsupported).");
            }
            long j = this.o;
            if (j > 2147483647L) {
                throw new com.google.android.exoplayer2.n("Leaf atom with length > 2147483647 (unsupported).");
            }
            o80 o80Var = new o80((int) j);
            this.q = o80Var;
            System.arraycopy(this.i.f8644a, 0, o80Var.f8644a, 0, 8);
            this.m = 1;
        } else {
            if (this.o > 2147483647L) {
                throw new com.google.android.exoplayer2.n("Skipping atom with length > 2147483647 (unsupported).");
            }
            this.q = null;
            this.m = 1;
        }
        return true;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public final void c(v40 v40Var) throws InterruptedException, IOException {
        int i = ((int) this.o) - this.p;
        o80 o80Var = this.q;
        if (o80Var != null) {
            v40Var.b(o80Var.f8644a, 8, i);
            a(new y20.b(this.n, this.q), v40Var.c());
        } else {
            v40Var.b(i);
        }
        a(v40Var.c());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final boolean e(v40 v40Var) throws InterruptedException, IOException {
        byte[] bArr;
        int iA;
        int i = 4;
        int i2 = 1;
        int i3 = 0;
        if (this.m == 3) {
            if (this.v == null) {
                c cVarA = a(this.c);
                if (cVarA == null) {
                    int iC = (int) (this.r - v40Var.c());
                    if (iC < 0) {
                        throw new com.google.android.exoplayer2.n("Offset to end of mdat was negative.");
                    }
                    v40Var.b(iC);
                    a();
                    return false;
                }
                int iC2 = (int) (cVarA.f7139a.g[cVarA.g] - v40Var.c());
                if (iC2 < 0) {
                    Log.w("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
                    iC2 = 0;
                }
                v40Var.b(iC2);
                this.v = cVarA;
            }
            c cVar = this.v;
            j30 j30Var = cVar.f7139a;
            this.w = j30Var.i[cVar.f7140e];
            if (j30Var.m) {
                int iA2 = a(cVar);
                this.x = iA2;
                this.w += iA2;
            } else {
                this.x = 0;
            }
            if (this.v.c.g == 1) {
                this.w -= 8;
                v40Var.b(8);
            }
            this.m = 4;
            this.y = 0;
        }
        c cVar2 = this.v;
        j30 j30Var2 = cVar2.f7139a;
        h30 h30Var = cVar2.c;
        f50 f50Var = cVar2.b;
        int i4 = cVar2.f7140e;
        int i5 = h30Var.k;
        if (i5 == 0) {
            while (true) {
                int i6 = this.x;
                int i7 = this.w;
                if (i6 >= i7) {
                    break;
                }
                this.x += f50Var.a(v40Var, i7 - i6, false);
            }
        } else {
            byte[] bArr2 = this.f7137e.f8644a;
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[2] = 0;
            int i8 = i5 + 1;
            int i9 = 4 - i5;
            while (this.x < this.w) {
                int i10 = this.y;
                if (i10 == 0) {
                    v40Var.b(bArr2, i9, i8);
                    this.f7137e.c(i3);
                    this.y = this.f7137e.t() - i2;
                    this.d.c(i3);
                    f50Var.a(this.d, i);
                    f50Var.a(this.f7137e, i2);
                    this.z = this.C != null && m80.a(h30Var.f.f, bArr2[i]);
                    this.x += 5;
                    this.w += i9;
                } else {
                    if (this.z) {
                        this.f.a(i10);
                        v40Var.b(this.f.f8644a, i3, this.y);
                        f50Var.a(this.f, this.y);
                        iA = this.y;
                        o80 o80Var = this.f;
                        int iA3 = m80.a(o80Var.f8644a, o80Var.c());
                        this.f.c("video/hevc".equals(h30Var.f.f) ? 1 : 0);
                        this.f.b(iA3);
                        x50.a(j30Var2.b(i4) * 1000, this.f, this.C);
                    } else {
                        iA = f50Var.a(v40Var, i10, false);
                    }
                    this.x += iA;
                    this.y -= iA;
                    i = 4;
                    i2 = 1;
                    i3 = 0;
                }
            }
        }
        long jB = j30Var2.b(i4) * 1000;
        int i11 = (j30Var2.m ? 1073741824 : 0) | (j30Var2.l[i4] ? 1 : 0);
        int i12 = j30Var2.f8015a.f6850a;
        if (j30Var2.m) {
            i30 i30Var = j30Var2.o;
            bArr = i30Var != null ? i30Var.b : h30Var.h[i12].b;
        } else {
            bArr = null;
        }
        u80 u80Var = this.h;
        if (u80Var != null) {
            jB = u80Var.c(jB);
        }
        f50Var.a(jB, i11, this.w, 0, bArr);
        while (!this.l.isEmpty()) {
            b bVarRemoveFirst = this.l.removeFirst();
            int i13 = this.s;
            int i14 = bVarRemoveFirst.b;
            int i15 = i13 - i14;
            this.s = i15;
            this.B.a(jB + bVarRemoveFirst.f7138a, 1, i14, i15, null);
        }
        c cVar3 = this.v;
        cVar3.f7140e++;
        int i16 = cVar3.f + 1;
        cVar3.f = i16;
        int[] iArr = j30Var2.h;
        int i17 = cVar3.g;
        if (i16 == iArr[i17]) {
            cVar3.g = i17 + 1;
            cVar3.f = 0;
            this.v = null;
        }
        this.m = 3;
        return true;
    }

    public c30(int i) {
        this(i, null);
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.A = z40Var;
        h30 h30Var = this.b;
        if (h30Var != null) {
            c cVar = new c(z40Var.a(0, h30Var.b));
            cVar.a(this.b, new a30(0, 0, 0, 0));
            this.c.put(0, cVar);
            b();
            this.A.a();
        }
    }

    public c30(int i, u80 u80Var) {
        this(i, u80Var, null);
    }

    /* JADX INFO: compiled from: FragmentedMp4Extractor.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final j30 f7139a = new j30();
        public final f50 b;
        public h30 c;
        public a30 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7140e;
        public int f;
        public int g;

        public c(f50 f50Var) {
            this.b = f50Var;
        }

        public void a(h30 h30Var, a30 a30Var) {
            e80.a(h30Var);
            this.c = h30Var;
            e80.a(a30Var);
            this.d = a30Var;
            this.b.a(h30Var.f);
            a();
        }

        public void a() {
            this.f7139a.a();
            this.f7140e = 0;
            this.g = 0;
            this.f = 0;
        }

        public void a(com.google.android.exoplayer2.c.a aVar) {
            this.b.a(this.c.f.a(aVar));
        }
    }

    public c30(int i, u80 u80Var, h30 h30Var) {
        this.f7136a = i | (h30Var != null ? 16 : 0);
        this.h = u80Var;
        this.b = h30Var;
        this.i = new o80(16);
        this.d = new o80(m80.f8362a);
        this.f7137e = new o80(5);
        this.f = new o80();
        this.g = new o80(1);
        this.j = new byte[16];
        this.k = new Stack<>();
        this.l = new LinkedList<>();
        this.c = new SparseArray<>();
        this.t = -9223372036854775807L;
        this.u = -9223372036854775807L;
        a();
    }

    public final void d(v40 v40Var) throws InterruptedException, IOException {
        int size = this.c.size();
        c cVarValueAt = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            j30 j30Var = this.c.valueAt(i).f7139a;
            if (j30Var.r) {
                long j2 = j30Var.d;
                if (j2 < j) {
                    cVarValueAt = this.c.valueAt(i);
                    j = j2;
                }
            }
        }
        if (cVarValueAt == null) {
            this.m = 3;
            return;
        }
        int iC = (int) (j - v40Var.c());
        if (iC >= 0) {
            v40Var.b(iC);
            cVarValueAt.f7139a.a(v40Var);
            return;
        }
        throw new com.google.android.exoplayer2.n("Offset to encryption data was negative.");
    }

    public final void c(y20.a aVar) throws com.google.android.exoplayer2.n {
        a(aVar, this.c, this.f7136a, this.j);
        com.google.android.exoplayer2.c.a aVarA = a(aVar.Q0);
        if (aVarA != null) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                this.c.valueAt(i).a(aVarA);
            }
        }
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            this.c.valueAt(i).a();
        }
        this.l.clear();
        this.s = 0;
        this.k.clear();
        a();
    }

    public static long c(o80 o80Var) {
        o80Var.c(8);
        return y20.a(o80Var.n()) == 0 ? o80Var.l() : o80Var.v();
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        while (true) {
            int i = this.m;
            if (i != 0) {
                if (i == 1) {
                    c(v40Var);
                } else if (i != 2) {
                    if (e(v40Var)) {
                        return 0;
                    }
                } else {
                    d(v40Var);
                }
            } else if (!b(v40Var)) {
                return -1;
            }
        }
    }

    public final void a() {
        this.m = 0;
        this.p = 0;
    }

    public final void a(long j) throws com.google.android.exoplayer2.n {
        while (!this.k.isEmpty() && this.k.peek().P0 == j) {
            a(this.k.pop());
        }
        a();
    }

    public final void a(y20.b bVar, long j) throws com.google.android.exoplayer2.n {
        if (!this.k.isEmpty()) {
            this.k.peek().a(bVar);
            return;
        }
        int i = bVar.f9840a;
        if (i == y20.B) {
            Pair<Long, k20> pairA = a(bVar.P0, j);
            this.u = ((Long) pairA.first).longValue();
            this.A.a((e50) pairA.second);
            this.D = true;
            return;
        }
        if (i == y20.G0) {
            a(bVar.P0);
        }
    }

    public final void a(y20.a aVar) throws com.google.android.exoplayer2.n {
        int i = aVar.f9840a;
        if (i == y20.C) {
            b(aVar);
        } else if (i == y20.L) {
            c(aVar);
        } else {
            if (this.k.isEmpty()) {
                return;
            }
            this.k.peek().a(aVar);
        }
    }

    public final void a(o80 o80Var) {
        if (this.B == null) {
            return;
        }
        o80Var.c(12);
        o80Var.x();
        o80Var.x();
        long jA = x80.a(o80Var.l(), 1000000L, o80Var.l());
        o80Var.c(12);
        int iB = o80Var.b();
        this.B.a(o80Var, iB);
        long j = this.u;
        if (j != -9223372036854775807L) {
            this.B.a(j + jA, 1, iB, 0, null);
        } else {
            this.l.addLast(new b(jA, iB));
            this.s += iB;
        }
    }

    public final void b(y20.a aVar) throws com.google.android.exoplayer2.n {
        int i;
        int i2 = 0;
        e80.b(this.b == null, "Unexpected moov box.");
        com.google.android.exoplayer2.c.a aVarA = a(aVar.Q0);
        y20.a aVarE = aVar.e(y20.N);
        SparseArray sparseArray = new SparseArray();
        int size = aVarE.Q0.size();
        long jC = -9223372036854775807L;
        for (int i3 = 0; i3 < size; i3++) {
            y20.b bVar = aVarE.Q0.get(i3);
            int i4 = bVar.f9840a;
            if (i4 == y20.z) {
                Pair<Integer, a30> pairB = b(bVar.P0);
                sparseArray.put(((Integer) pairB.first).intValue(), pairB.second);
            } else if (i4 == y20.O) {
                jC = c(bVar.P0);
            }
        }
        SparseArray sparseArray2 = new SparseArray();
        int size2 = aVar.R0.size();
        int i5 = 0;
        while (i5 < size2) {
            y20.a aVar2 = aVar.R0.get(i5);
            if (aVar2.f9840a == y20.E) {
                i = i5;
                h30 h30VarA = z20.a(aVar2, aVar.d(y20.D), jC, aVarA, false);
                if (h30VarA != null) {
                    sparseArray2.put(h30VarA.f7794a, h30VarA);
                }
            } else {
                i = i5;
            }
            i5 = i + 1;
        }
        int size3 = sparseArray2.size();
        if (this.c.size() == 0) {
            while (i2 < size3) {
                h30 h30Var = (h30) sparseArray2.valueAt(i2);
                c cVar = new c(this.A.a(i2, h30Var.b));
                cVar.a(h30Var, (a30) sparseArray.get(h30Var.f7794a));
                this.c.put(h30Var.f7794a, cVar);
                this.t = Math.max(this.t, h30Var.f7795e);
                i2++;
            }
            b();
            this.A.a();
            return;
        }
        e80.b(this.c.size() == size3);
        while (i2 < size3) {
            h30 h30Var2 = (h30) sparseArray2.valueAt(i2);
            this.c.get(h30Var2.f7794a).a(h30Var2, (a30) sparseArray.get(h30Var2.f7794a));
            i2++;
        }
    }

    public static void a(y20.a aVar, SparseArray<c> sparseArray, int i, byte[] bArr) throws com.google.android.exoplayer2.n {
        int size = aVar.R0.size();
        for (int i2 = 0; i2 < size; i2++) {
            y20.a aVar2 = aVar.R0.get(i2);
            if (aVar2.f9840a == y20.M) {
                b(aVar2, sparseArray, i, bArr);
            }
        }
    }

    public static void a(y20.a aVar, c cVar, long j, int i) {
        List<y20.b> list = aVar.Q0;
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            y20.b bVar = list.get(i4);
            if (bVar.f9840a == y20.A) {
                o80 o80Var = bVar.P0;
                o80Var.c(12);
                int iT = o80Var.t();
                if (iT > 0) {
                    i3 += iT;
                    i2++;
                }
            }
        }
        cVar.g = 0;
        cVar.f = 0;
        cVar.f7140e = 0;
        cVar.f7139a.a(i2, i3);
        int i5 = 0;
        int iA = 0;
        for (int i6 = 0; i6 < size; i6++) {
            y20.b bVar2 = list.get(i6);
            if (bVar2.f9840a == y20.A) {
                iA = a(cVar, i5, j, i, bVar2.P0, iA);
                i5++;
            }
        }
    }

    public static void a(i30 i30Var, o80 o80Var, j30 j30Var) throws com.google.android.exoplayer2.n {
        int i;
        int i2 = i30Var.f7906a;
        o80Var.c(8);
        if ((y20.b(o80Var.n()) & 1) == 1) {
            o80Var.d(8);
        }
        int iG = o80Var.g();
        int iT = o80Var.t();
        if (iT == j30Var.f) {
            if (iG == 0) {
                boolean[] zArr = j30Var.n;
                i = 0;
                for (int i3 = 0; i3 < iT; i3++) {
                    int iG2 = o80Var.g();
                    i += iG2;
                    zArr[i3] = iG2 > i2;
                }
            } else {
                i = (iG * iT) + 0;
                Arrays.fill(j30Var.n, 0, iT, iG > i2);
            }
            j30Var.a(i);
            return;
        }
        throw new com.google.android.exoplayer2.n("Length mismatch: " + iT + ", " + j30Var.f);
    }

    public final void b() {
        if ((this.f7136a & 4) != 0 && this.B == null) {
            f50 f50VarA = this.A.a(this.c.size(), 4);
            this.B = f50VarA;
            f50VarA.a(com.google.android.exoplayer2.j.a((String) null, "application/x-emsg", Long.MAX_VALUE));
        }
        if ((this.f7136a & 8) == 0 || this.C != null) {
            return;
        }
        f50 f50VarA2 = this.A.a(this.c.size() + 1, 3);
        f50VarA2.a(com.google.android.exoplayer2.j.a((String) null, "application/cea-608", (String) null, -1, 0, (String) null, (com.google.android.exoplayer2.c.a) null));
        this.C = new f50[]{f50VarA2};
    }

    public static Pair<Integer, a30> b(o80 o80Var) {
        o80Var.c(12);
        return Pair.create(Integer.valueOf(o80Var.n()), new a30(o80Var.t() - 1, o80Var.t(), o80Var.t(), o80Var.n()));
    }

    public static void a(o80 o80Var, j30 j30Var) throws com.google.android.exoplayer2.n {
        o80Var.c(8);
        int iN = o80Var.n();
        if ((y20.b(iN) & 1) == 1) {
            o80Var.d(8);
        }
        int iT = o80Var.t();
        if (iT == 1) {
            j30Var.d += y20.a(iN) == 0 ? o80Var.l() : o80Var.v();
        } else {
            throw new com.google.android.exoplayer2.n("Unexpected saio entry count: " + iT);
        }
    }

    public static void b(y20.a aVar, SparseArray<c> sparseArray, int i, byte[] bArr) throws com.google.android.exoplayer2.n {
        c cVarA = a(aVar.d(y20.y).P0, sparseArray, i);
        if (cVarA == null) {
            return;
        }
        j30 j30Var = cVarA.f7139a;
        long jD = j30Var.s;
        cVarA.a();
        if (aVar.d(y20.x) != null && (i & 2) == 0) {
            jD = d(aVar.d(y20.x).P0);
        }
        a(aVar, cVarA, jD, i);
        y20.b bVarD = aVar.d(y20.d0);
        if (bVarD != null) {
            a(cVarA.c.h[j30Var.f8015a.f6850a], bVarD.P0, j30Var);
        }
        y20.b bVarD2 = aVar.d(y20.e0);
        if (bVarD2 != null) {
            a(bVarD2.P0, j30Var);
        }
        y20.b bVarD3 = aVar.d(y20.i0);
        if (bVarD3 != null) {
            b(bVarD3.P0, j30Var);
        }
        y20.b bVarD4 = aVar.d(y20.f0);
        y20.b bVarD5 = aVar.d(y20.g0);
        if (bVarD4 != null && bVarD5 != null) {
            a(bVarD4.P0, bVarD5.P0, j30Var);
        }
        int size = aVar.Q0.size();
        for (int i2 = 0; i2 < size; i2++) {
            y20.b bVar = aVar.Q0.get(i2);
            if (bVar.f9840a == y20.h0) {
                a(bVar.P0, j30Var, bArr);
            }
        }
    }

    public static c a(o80 o80Var, SparseArray<c> sparseArray, int i) {
        o80Var.c(8);
        int iB = y20.b(o80Var.n());
        int iN = o80Var.n();
        if ((i & 16) != 0) {
            iN = 0;
        }
        c cVar = sparseArray.get(iN);
        if (cVar == null) {
            return null;
        }
        if ((iB & 1) != 0) {
            long jV = o80Var.v();
            j30 j30Var = cVar.f7139a;
            j30Var.c = jV;
            j30Var.d = jV;
        }
        a30 a30Var = cVar.d;
        cVar.f7139a.f8015a = new a30((iB & 2) != 0 ? o80Var.t() - 1 : a30Var.f6850a, (iB & 8) != 0 ? o80Var.t() : a30Var.b, (iB & 16) != 0 ? o80Var.t() : a30Var.c, (iB & 32) != 0 ? o80Var.t() : a30Var.d);
        return cVar;
    }

    public static int a(c cVar, int i, long j, int i2, o80 o80Var, int i3) {
        boolean z;
        int iT;
        boolean z2;
        int iN;
        boolean z3;
        boolean z4;
        boolean z5;
        o80Var.c(8);
        int iB = y20.b(o80Var.n());
        h30 h30Var = cVar.c;
        j30 j30Var = cVar.f7139a;
        a30 a30Var = j30Var.f8015a;
        j30Var.h[i] = o80Var.t();
        long[] jArr = j30Var.g;
        jArr[i] = j30Var.c;
        if ((iB & 1) != 0) {
            jArr[i] = jArr[i] + ((long) o80Var.n());
        }
        boolean z6 = (iB & 4) != 0;
        int iT2 = a30Var.d;
        if (z6) {
            iT2 = o80Var.t();
        }
        boolean z7 = (iB & 256) != 0;
        boolean z8 = (iB & 512) != 0;
        boolean z9 = (iB & 1024) != 0;
        boolean z10 = (iB & 2048) != 0;
        long[] jArr2 = h30Var.i;
        long jA = 0;
        if (jArr2 != null && jArr2.length == 1 && jArr2[0] == 0) {
            jA = x80.a(h30Var.j[0], 1000L, h30Var.c);
        }
        int[] iArr = j30Var.i;
        int[] iArr2 = j30Var.j;
        long[] jArr3 = j30Var.k;
        boolean[] zArr = j30Var.l;
        int i4 = iT2;
        boolean z11 = h30Var.b == 2 && (i2 & 1) != 0;
        int i5 = i3 + j30Var.h[i];
        long j2 = h30Var.c;
        long j3 = jA;
        long j4 = i > 0 ? j30Var.s : j;
        int i6 = i3;
        while (i6 < i5) {
            int iT3 = z7 ? o80Var.t() : a30Var.b;
            if (z8) {
                z = z7;
                iT = o80Var.t();
            } else {
                z = z7;
                iT = a30Var.c;
            }
            if (i6 == 0 && z6) {
                z2 = z6;
                iN = i4;
            } else if (z9) {
                z2 = z6;
                iN = o80Var.n();
            } else {
                z2 = z6;
                iN = a30Var.d;
            }
            if (z10) {
                z3 = z10;
                z4 = z8;
                z5 = z9;
                iArr2[i6] = (int) (((long) (o80Var.n() * 1000)) / j2);
            } else {
                z3 = z10;
                z4 = z8;
                z5 = z9;
                iArr2[i6] = 0;
            }
            jArr3[i6] = x80.a(j4, 1000L, j2) - j3;
            iArr[i6] = iT;
            zArr[i6] = ((iN >> 16) & 1) == 0 && (!z11 || i6 == 0);
            i6++;
            j4 += (long) iT3;
            j2 = j2;
            z7 = z;
            z6 = z2;
            z10 = z3;
            z8 = z4;
            z9 = z5;
        }
        j30Var.s = j4;
        return i5;
    }

    public static void b(o80 o80Var, j30 j30Var) throws com.google.android.exoplayer2.n {
        a(o80Var, 0, j30Var);
    }

    public static boolean b(int i) {
        return i == y20.C || i == y20.E || i == y20.F || i == y20.G || i == y20.H || i == y20.L || i == y20.M || i == y20.N || i == y20.Q;
    }

    public static void a(o80 o80Var, j30 j30Var, byte[] bArr) throws com.google.android.exoplayer2.n {
        o80Var.c(8);
        o80Var.a(bArr, 0, 16);
        if (Arrays.equals(bArr, F)) {
            a(o80Var, 16, j30Var);
        }
    }

    public static void a(o80 o80Var, int i, j30 j30Var) throws com.google.android.exoplayer2.n {
        o80Var.c(i + 8);
        int iB = y20.b(o80Var.n());
        if ((iB & 1) == 0) {
            boolean z = (iB & 2) != 0;
            int iT = o80Var.t();
            if (iT == j30Var.f) {
                Arrays.fill(j30Var.n, 0, iT, z);
                j30Var.a(o80Var.b());
                j30Var.a(o80Var);
                return;
            } else {
                throw new com.google.android.exoplayer2.n("Length mismatch: " + iT + ", " + j30Var.f);
            }
        }
        throw new com.google.android.exoplayer2.n("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    public static void a(o80 o80Var, o80 o80Var2, j30 j30Var) throws com.google.android.exoplayer2.n {
        o80Var.c(8);
        int iN = o80Var.n();
        if (o80Var.n() != E) {
            return;
        }
        if (y20.a(iN) == 1) {
            o80Var.d(4);
        }
        if (o80Var.n() == 1) {
            o80Var2.c(8);
            int iN2 = o80Var2.n();
            if (o80Var2.n() != E) {
                return;
            }
            int iA = y20.a(iN2);
            if (iA == 1) {
                if (o80Var2.l() == 0) {
                    throw new com.google.android.exoplayer2.n("Variable length decription in sgpd found (unsupported)");
                }
            } else if (iA >= 2) {
                o80Var2.d(4);
            }
            if (o80Var2.l() == 1) {
                o80Var2.d(2);
                boolean z = o80Var2.g() == 1;
                if (z) {
                    int iG = o80Var2.g();
                    byte[] bArr = new byte[16];
                    o80Var2.a(bArr, 0, 16);
                    j30Var.m = true;
                    j30Var.o = new i30(z, iG, bArr);
                    return;
                }
                return;
            }
            throw new com.google.android.exoplayer2.n("Entry count in sgpd != 1 (unsupported).");
        }
        throw new com.google.android.exoplayer2.n("Entry count in sbgp != 1 (unsupported).");
    }

    public static Pair<Long, k20> a(o80 o80Var, long j) throws com.google.android.exoplayer2.n {
        long jV;
        long jV2;
        o80Var.c(8);
        int iA = y20.a(o80Var.n());
        o80Var.d(4);
        long jL = o80Var.l();
        if (iA == 0) {
            jV = o80Var.l();
            jV2 = o80Var.l();
        } else {
            jV = o80Var.v();
            jV2 = o80Var.v();
        }
        long j2 = jV;
        long j3 = j + jV2;
        long jA = x80.a(j2, 1000000L, jL);
        o80Var.d(2);
        int iH = o80Var.h();
        int[] iArr = new int[iH];
        long[] jArr = new long[iH];
        long[] jArr2 = new long[iH];
        long[] jArr3 = new long[iH];
        long j4 = j2;
        long j5 = jA;
        int i = 0;
        while (i < iH) {
            int iN = o80Var.n();
            if ((iN & Integer.MIN_VALUE) == 0) {
                long jL2 = o80Var.l();
                iArr[i] = iN & Integer.MAX_VALUE;
                jArr[i] = j3;
                jArr3[i] = j5;
                long j6 = j4 + jL2;
                long[] jArr4 = jArr2;
                long[] jArr5 = jArr3;
                int i2 = iH;
                int[] iArr2 = iArr;
                long jA2 = x80.a(j6, 1000000L, jL);
                jArr4[i] = jA2 - jArr5[i];
                o80Var.d(4);
                j3 += (long) iArr2[i];
                i++;
                iArr = iArr2;
                jArr3 = jArr5;
                jArr2 = jArr4;
                jArr = jArr;
                iH = i2;
                j4 = j6;
                j5 = jA2;
            } else {
                throw new com.google.android.exoplayer2.n("Unhandled indirect reference");
            }
        }
        return Pair.create(Long.valueOf(jA), new k20(iArr, jArr, jArr2, jArr3));
    }

    public static c a(SparseArray<c> sparseArray) {
        int size = sparseArray.size();
        c cVar = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            c cVarValueAt = sparseArray.valueAt(i);
            int i2 = cVarValueAt.g;
            j30 j30Var = cVarValueAt.f7139a;
            if (i2 != j30Var.f8016e) {
                long j2 = j30Var.g[i2];
                if (j2 < j) {
                    cVar = cVarValueAt;
                    j = j2;
                }
            }
        }
        return cVar;
    }

    public final int a(c cVar) {
        j30 j30Var = cVar.f7139a;
        o80 o80Var = j30Var.q;
        int i = j30Var.f8015a.f6850a;
        i30 i30Var = j30Var.o;
        if (i30Var == null) {
            i30Var = cVar.c.h[i];
        }
        int i2 = i30Var.f7906a;
        boolean z = j30Var.n[cVar.f7140e];
        this.g.f8644a[0] = (byte) ((z ? 128 : 0) | i2);
        this.g.c(0);
        f50 f50Var = cVar.b;
        f50Var.a(this.g, 1);
        f50Var.a(o80Var, i2);
        if (!z) {
            return i2 + 1;
        }
        int iH = o80Var.h();
        o80Var.d(-2);
        int i3 = (iH * 6) + 2;
        f50Var.a(o80Var, i3);
        return i2 + 1 + i3;
    }

    public static com.google.android.exoplayer2.c.a a(List<y20.b> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            y20.b bVar = list.get(i);
            if (bVar.f9840a == y20.V) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = bVar.P0.f8644a;
                UUID uuidA = f30.a(bArr);
                if (uuidA == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new a.C0050a(uuidA, "video/mp4", bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new com.google.android.exoplayer2.c.a(arrayList);
    }

    public static boolean a(int i) {
        return i == y20.T || i == y20.S || i == y20.D || i == y20.B || i == y20.U || i == y20.x || i == y20.y || i == y20.P || i == y20.z || i == y20.A || i == y20.V || i == y20.d0 || i == y20.e0 || i == y20.i0 || i == y20.h0 || i == y20.f0 || i == y20.g0 || i == y20.R || i == y20.O || i == y20.G0;
    }
}
