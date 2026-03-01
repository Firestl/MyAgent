package supwisdom;

import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.exoplayer2.i.r;
import java.io.IOException;
import java.util.LinkedList;
import supwisdom.cb0;
import supwisdom.k90;
import supwisdom.nb0;
import supwisdom.va0;
import supwisdom.x20;

/* JADX INFO: compiled from: HlsSampleStreamWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class jb0 implements x20.d, z40, r.a<n90>, nb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8037a;
    public final b b;
    public final cb0 c;
    public final q70 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final com.google.android.exoplayer2.j f8038e;
    public final int f;
    public final k90.a h;
    public boolean n;
    public boolean o;
    public int p;
    public com.google.android.exoplayer2.j q;
    public int r;
    public boolean s;
    public qb0 t;
    public int u;
    public boolean[] v;
    public long w;
    public long x;
    public boolean y;
    public final com.google.android.exoplayer2.i.r g = new com.google.android.exoplayer2.i.r("Loader:HlsSampleStreamWrapper");
    public final cb0.b i = new cb0.b();
    public final SparseArray<x20> j = new SparseArray<>();
    public final LinkedList<fb0> k = new LinkedList<>();
    public final Runnable l = new a();
    public final Handler m = new Handler();

    /* JADX INFO: compiled from: HlsSampleStreamWrapper.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            jb0.this.h();
        }
    }

    /* JADX INFO: compiled from: HlsSampleStreamWrapper.java */
    public interface b extends nb0.a<jb0> {
        void a(va0.a aVar);

        void g();
    }

    public jb0(int i, b bVar, cb0 cb0Var, q70 q70Var, long j, com.google.android.exoplayer2.j jVar, int i2, k90.a aVar) {
        this.f8037a = i;
        this.b = bVar;
        this.c = cb0Var;
        this.d = q70Var;
        this.f8038e = jVar;
        this.f = i2;
        this.h = aVar;
        this.w = j;
        this.x = j;
    }

    @Override // supwisdom.z40
    public void a(e50 e50Var) {
    }

    public void b() {
        if (this.o) {
            return;
        }
        a(this.w);
    }

    public void c() throws IOException {
        g();
    }

    public qb0 d() {
        return this.t;
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:506)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:509)
        */
    public long e() {
        /*
            r6 = this;
            boolean r0 = r6.y
            if (r0 == 0) goto L7
            r0 = -9223372036854775808
            return r0
        L7:
            boolean r0 = r6.k()
            if (r0 == 0) goto L10
            long r0 = r6.x
            return r0
        L10:
            long r0 = r6.w
            java.util.LinkedList<supwisdom.fb0> r2 = r6.k
            java.lang.Object r2 = r2.getLast()
            supwisdom.fb0 r2 = (supwisdom.fb0) r2
            boolean r3 = r2.f()
            if (r3 == 0) goto L21
            goto L3a
        L21:
            java.util.LinkedList<supwisdom.fb0> r2 = r6.k
            int r2 = r2.size()
            r3 = 1
            if (r2 <= r3) goto L39
            java.util.LinkedList<supwisdom.fb0> r2 = r6.k
            int r3 = r2.size()
            int r3 = r3 + (-2)
            java.lang.Object r2 = r2.get(r3)
            supwisdom.fb0 r2 = (supwisdom.fb0) r2
            goto L3a
        L39:
            r2 = 0
        L3a:
            if (r2 == 0) goto L42
            long r2 = r2.g
            long r0 = java.lang.Math.max(r0, r2)
        L42:
            android.util.SparseArray<supwisdom.x20> r2 = r6.j
            int r2 = r2.size()
            r3 = 0
        L49:
            if (r3 >= r2) goto L5e
            android.util.SparseArray<supwisdom.x20> r4 = r6.j
            java.lang.Object r4 = r4.valueAt(r3)
            supwisdom.x20 r4 = (supwisdom.x20) r4
            long r4 = r4.h()
            long r0 = java.lang.Math.max(r0, r4)
            int r3 = r3 + 1
            goto L49
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.jb0.e():long");
    }

    public void f() {
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            this.j.valueAt(i).c();
        }
        this.g.c();
        this.m.removeCallbacksAndMessages(null);
        this.s = true;
    }

    public void g() throws IOException {
        this.g.d();
        this.c.a();
    }

    public final void h() {
        if (this.s || this.o || !this.n) {
            return;
        }
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            if (this.j.valueAt(i).g() == null) {
                return;
            }
        }
        j();
        this.o = true;
        this.b.g();
    }

    @Override // supwisdom.nb0
    public long i() {
        if (k()) {
            return this.x;
        }
        if (this.y) {
            return Long.MIN_VALUE;
        }
        return this.k.getLast().g;
    }

    public final void j() {
        int size = this.j.size();
        int i = 0;
        char c = 0;
        int i2 = -1;
        while (true) {
            if (i >= size) {
                break;
            }
            String str = this.j.valueAt(i).g().f;
            char c2 = l80.b(str) ? (char) 3 : l80.a(str) ? (char) 2 : l80.c(str) ? (char) 1 : (char) 0;
            if (c2 > c) {
                i2 = i;
                c = c2;
            } else if (c2 == c && i2 != -1) {
                i2 = -1;
            }
            i++;
        }
        pb0 pb0VarB = this.c.b();
        int i3 = pb0VarB.f8788a;
        this.u = -1;
        this.v = new boolean[size];
        pb0[] pb0VarArr = new pb0[size];
        for (int i4 = 0; i4 < size; i4++) {
            com.google.android.exoplayer2.j jVarG = this.j.valueAt(i4).g();
            if (i4 == i2) {
                com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[i3];
                for (int i5 = 0; i5 < i3; i5++) {
                    jVarArr[i5] = a(pb0VarB.a(i5), jVarG);
                }
                pb0VarArr[i4] = new pb0(jVarArr);
                this.u = i4;
            } else {
                pb0VarArr[i4] = new pb0(a((c == 3 && l80.a(jVarG.f)) ? this.f8038e : null, jVarG));
            }
        }
        this.t = new qb0(pb0VarArr);
    }

    public final boolean k() {
        return this.x != -9223372036854775807L;
    }

    public void b(com.google.android.exoplayer2.j jVar) {
        a(0, -1).a(jVar);
        this.n = true;
        h();
    }

    public boolean a(k70[] k70VarArr, boolean[] zArr, mb0[] mb0VarArr, boolean[] zArr2, boolean z) {
        e80.b(this.o);
        for (int i = 0; i < k70VarArr.length; i++) {
            if (mb0VarArr[i] != null && (k70VarArr[i] == null || !zArr[i])) {
                int i2 = ((ib0) mb0VarArr[i]).f7935a;
                b(i2, false);
                this.j.valueAt(i2).c();
                mb0VarArr[i] = null;
            }
        }
        k70 k70Var = null;
        boolean z2 = false;
        for (int i3 = 0; i3 < k70VarArr.length; i3++) {
            if (mb0VarArr[i3] == null && k70VarArr[i3] != null) {
                k70 k70Var2 = k70VarArr[i3];
                int iA = this.t.a(k70Var2.d());
                b(iA, true);
                if (iA == this.u) {
                    this.c.a(k70Var2);
                    k70Var = k70Var2;
                }
                mb0VarArr[i3] = new ib0(this, iA);
                zArr2[i3] = true;
                z2 = true;
            }
        }
        if (z) {
            int size = this.j.size();
            for (int i4 = 0; i4 < size; i4++) {
                if (!this.v[i4]) {
                    this.j.valueAt(i4).c();
                }
            }
            if (k70Var != null && !this.k.isEmpty()) {
                k70Var.a(0L);
                if (k70Var.g() != this.c.b().a(this.k.getLast().c)) {
                    b(this.w);
                }
            }
        }
        if (this.p == 0) {
            this.c.c();
            this.q = null;
            this.k.clear();
            if (this.g.a()) {
                this.g.b();
            }
        }
        return z2;
    }

    public void b(long j) {
        this.w = j;
        this.x = j;
        this.y = false;
        this.k.clear();
        if (this.g.a()) {
            this.g.b();
            return;
        }
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            this.j.valueAt(i).a(this.v[i]);
        }
    }

    @Override // supwisdom.z40
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public x20 a(int i, int i2) {
        if (this.j.indexOfKey(i) >= 0) {
            return this.j.get(i);
        }
        x20 x20Var = new x20(this.d);
        x20Var.a(this);
        x20Var.a(this.r);
        this.j.put(i, x20Var);
        return x20Var;
    }

    public final void b(int i, boolean z) {
        e80.b(this.v[i] != z);
        this.v[i] = z;
        this.p += z ? 1 : -1;
    }

    public static String b(String str) {
        return a(str, 2);
    }

    public void a(boolean z) {
        this.c.a(z);
    }

    public void a(va0.a aVar, long j) {
        this.c.a(aVar, j);
    }

    public boolean a(int i) {
        return this.y || !(k() || this.j.valueAt(i).d());
    }

    public int a(int i, e90 e90Var, y10 y10Var, boolean z) {
        if (k()) {
            return -3;
        }
        while (this.k.size() > 1 && a(this.k.getFirst())) {
            this.k.removeFirst();
        }
        fb0 first = this.k.getFirst();
        com.google.android.exoplayer2.j jVar = first.c;
        if (!jVar.equals(this.q)) {
            this.h.a(this.f8037a, jVar, first.d, first.f8486e, first.f);
        }
        this.q = jVar;
        return this.j.valueAt(i).a(e90Var, y10Var, z, this.y, this.w);
    }

    public void a(int i, long j) {
        x20 x20VarValueAt = this.j.valueAt(i);
        if (this.y && j > x20VarValueAt.h()) {
            x20VarValueAt.i();
        } else {
            x20VarValueAt.a(j, true);
        }
    }

    public final boolean a(fb0 fb0Var) {
        int i = fb0Var.j;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            if (this.v[i2] && this.j.valueAt(i2).f() == i) {
                return false;
            }
        }
        return true;
    }

    @Override // supwisdom.nb0
    public boolean a(long j) {
        if (this.y || this.g.a()) {
            return false;
        }
        cb0 cb0Var = this.c;
        fb0 last = this.k.isEmpty() ? null : this.k.getLast();
        long j2 = this.x;
        if (j2 == -9223372036854775807L) {
            j2 = j;
        }
        cb0Var.a(last, j2, this.i);
        cb0.b bVar = this.i;
        boolean z = bVar.b;
        n90 n90Var = bVar.f7173a;
        va0.a aVar = bVar.c;
        bVar.a();
        if (z) {
            this.y = true;
            return true;
        }
        if (n90Var == null) {
            if (aVar != null) {
                this.b.a(aVar);
            }
            return false;
        }
        if (a(n90Var)) {
            this.x = -9223372036854775807L;
            fb0 fb0Var = (fb0) n90Var;
            fb0Var.a(this);
            this.k.add(fb0Var);
        }
        this.h.a(n90Var.f8485a, n90Var.b, this.f8037a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, this.g.a(n90Var, this, this.f));
        return true;
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(n90 n90Var, long j, long j2) {
        this.c.a(n90Var);
        this.h.a(n90Var.f8485a, n90Var.b, this.f8037a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, j, j2, n90Var.d());
        if (!this.o) {
            a(this.w);
        } else {
            this.b.a(this);
        }
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(n90 n90Var, long j, long j2, boolean z) {
        this.h.b(n90Var.f8485a, n90Var.b, this.f8037a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, j, j2, n90Var.d());
        if (z) {
            return;
        }
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            this.j.valueAt(i).a(this.v[i]);
        }
        this.b.a(this);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public int a(n90 n90Var, long j, long j2, IOException iOException) {
        long jD = n90Var.d();
        boolean zA = a(n90Var);
        boolean z = true;
        if (!this.c.a(n90Var, !zA || jD == 0, iOException)) {
            z = false;
        } else if (zA) {
            e80.b(this.k.removeLast() == n90Var);
            if (this.k.isEmpty()) {
                this.x = this.w;
            }
        }
        this.h.a(n90Var.f8485a, n90Var.b, this.f8037a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, j, j2, n90Var.d(), iOException, z);
        if (!z) {
            return 0;
        }
        if (!this.o) {
            a(this.w);
            return 2;
        }
        this.b.a(this);
        return 2;
    }

    public void a(int i, boolean z) {
        this.r = i;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            this.j.valueAt(i2).a(i);
        }
        if (z) {
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                this.j.valueAt(i3).a();
            }
        }
    }

    @Override // supwisdom.z40
    public void a() {
        this.n = true;
        this.m.post(this.l);
    }

    @Override // supwisdom.x20.d
    public void a(com.google.android.exoplayer2.j jVar) {
        this.m.post(this.l);
    }

    public static com.google.android.exoplayer2.j a(com.google.android.exoplayer2.j jVar, com.google.android.exoplayer2.j jVar2) {
        if (jVar == null) {
            return jVar2;
        }
        String strB = null;
        int iG = l80.g(jVar2.f);
        if (iG == 1) {
            strB = a(jVar.c);
        } else if (iG == 2) {
            strB = b(jVar.c);
        }
        return jVar2.a(jVar.f2303a, strB, jVar.b, jVar.j, jVar.k, jVar.x, jVar.y);
    }

    public final boolean a(n90 n90Var) {
        return n90Var instanceof fb0;
    }

    public static String a(String str) {
        return a(str, 1);
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("(\\s*,\\s*)|(\\s*$)");
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (i == l80.h(str2)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str2);
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }
}
