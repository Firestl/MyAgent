package supwisdom;

import com.google.android.exoplayer2.i.r;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import supwisdom.k90;
import supwisdom.nb0;
import supwisdom.r90;

/* JADX INFO: compiled from: ChunkSampleStream.java */
/* JADX INFO: loaded from: classes.dex */
public class q90<T extends r90> implements r.a<n90>, mb0, nb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8897a;
    public final int[] b;
    public final boolean[] c;
    public final T d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final nb0.a<q90<T>> f8898e;
    public final k90.a f;
    public final int g;
    public final com.google.android.exoplayer2.i.r h = new com.google.android.exoplayer2.i.r("Loader:ChunkSampleStream");
    public final p90 i = new p90();
    public final LinkedList<l90> j;
    public final x20 k;
    public final x20[] l;
    public final m90 m;
    public com.google.android.exoplayer2.j n;
    public long o;
    public long p;
    public boolean q;

    /* JADX INFO: compiled from: ChunkSampleStream.java */
    public final class a implements mb0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q90<T> f8899a;
        public final x20 b;
        public final int c;

        public a(q90<T> q90Var, x20 x20Var, int i) {
            this.f8899a = q90Var;
            this.b = x20Var;
            this.c = i;
        }

        @Override // supwisdom.mb0
        public boolean a() {
            q90 q90Var = q90.this;
            return q90Var.q || !(q90Var.f() || this.b.d());
        }

        @Override // supwisdom.mb0
        public void b() throws IOException {
        }

        @Override // supwisdom.mb0
        public void c(long j) {
            if (!q90.this.q || j <= this.b.h()) {
                this.b.a(j, true);
            } else {
                this.b.i();
            }
        }

        @Override // supwisdom.mb0
        public int a(e90 e90Var, y10 y10Var, boolean z) {
            if (q90.this.f()) {
                return -3;
            }
            x20 x20Var = this.b;
            q90 q90Var = q90.this;
            return x20Var.a(e90Var, y10Var, z, q90Var.q, q90Var.p);
        }

        public void c() {
            e80.b(q90.this.c[this.c]);
            q90.this.c[this.c] = false;
        }
    }

    public q90(int i, int[] iArr, T t, nb0.a<q90<T>> aVar, q70 q70Var, long j, int i2, k90.a aVar2) {
        this.f8897a = i;
        this.b = iArr;
        this.d = t;
        this.f8898e = aVar;
        this.f = aVar2;
        this.g = i2;
        LinkedList<l90> linkedList = new LinkedList<>();
        this.j = linkedList;
        Collections.unmodifiableList(linkedList);
        int i3 = 0;
        int length = iArr == null ? 0 : iArr.length;
        this.l = new x20[length];
        this.c = new boolean[length];
        int i4 = length + 1;
        int[] iArr2 = new int[i4];
        x20[] x20VarArr = new x20[i4];
        x20 x20Var = new x20(q70Var);
        this.k = x20Var;
        iArr2[0] = i;
        x20VarArr[0] = x20Var;
        while (i3 < length) {
            x20 x20Var2 = new x20(q70Var);
            this.l[i3] = x20Var2;
            int i5 = i3 + 1;
            x20VarArr[i5] = x20Var2;
            iArr2[i5] = iArr[i3];
            i3 = i5;
        }
        this.m = new m90(iArr2, x20VarArr);
        this.o = j;
        this.p = j;
    }

    public void b(long j) {
        int i = 0;
        while (true) {
            x20[] x20VarArr = this.l;
            if (i >= x20VarArr.length) {
                return;
            }
            if (!this.c[i]) {
                x20VarArr[i].a(j, true);
            }
            i++;
        }
    }

    public T c() {
        return this.d;
    }

    public long d() {
        if (this.q) {
            return Long.MIN_VALUE;
        }
        if (f()) {
            return this.o;
        }
        long jMax = this.p;
        l90 last = this.j.getLast();
        if (!last.f()) {
            if (this.j.size() > 1) {
                last = this.j.get(r2.size() - 2);
            } else {
                last = null;
            }
        }
        if (last != null) {
            jMax = Math.max(jMax, last.g);
        }
        return Math.max(jMax, this.k.h());
    }

    public void e() {
        this.k.c();
        for (x20 x20Var : this.l) {
            x20Var.c();
        }
        this.h.c();
    }

    public boolean f() {
        return this.o != -9223372036854775807L;
    }

    @Override // supwisdom.nb0
    public long i() {
        if (f()) {
            return this.o;
        }
        if (this.q) {
            return Long.MIN_VALUE;
        }
        return this.j.getLast().g;
    }

    @Override // supwisdom.mb0
    public void c(long j) {
        if (!this.q || j <= this.k.h()) {
            this.k.a(j, true);
        } else {
            this.k.i();
        }
    }

    @Override // supwisdom.mb0
    public void b() throws IOException {
        this.h.d();
        if (this.h.a()) {
            return;
        }
        this.d.a();
    }

    public q90<T>.a a(long j, int i) {
        for (int i2 = 0; i2 < this.l.length; i2++) {
            if (this.b[i2] == i) {
                e80.b(!this.c[i2]);
                this.c[i2] = true;
                this.l[i2].a(j, true);
                return new a(this, this.l[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(long r7) {
        /*
            r6 = this;
            r6.p = r7
            boolean r0 = r6.f()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L1f
            supwisdom.x20 r0 = r6.k
            long r3 = r6.i()
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 >= 0) goto L16
            r3 = 1
            goto L17
        L16:
            r3 = 0
        L17:
            boolean r0 = r0.a(r7, r3)
            if (r0 == 0) goto L1f
            r0 = 1
            goto L20
        L1f:
            r0 = 0
        L20:
            if (r0 == 0) goto L51
        L22:
            java.util.LinkedList<supwisdom.l90> r0 = r6.j
            int r0 = r0.size()
            if (r0 <= r2) goto L44
            java.util.LinkedList<supwisdom.l90> r0 = r6.j
            java.lang.Object r0 = r0.get(r2)
            supwisdom.l90 r0 = (supwisdom.l90) r0
            int r0 = r0.a(r1)
            supwisdom.x20 r3 = r6.k
            int r3 = r3.e()
            if (r0 > r3) goto L44
            java.util.LinkedList<supwisdom.l90> r0 = r6.j
            r0.removeFirst()
            goto L22
        L44:
            supwisdom.x20[] r0 = r6.l
            int r3 = r0.length
        L47:
            if (r1 >= r3) goto L7a
            r4 = r0[r1]
            r4.a(r7, r2)
            int r1 = r1 + 1
            goto L47
        L51:
            r6.o = r7
            r6.q = r1
            java.util.LinkedList<supwisdom.l90> r7 = r6.j
            r7.clear()
            com.google.android.exoplayer2.i.r r7 = r6.h
            boolean r7 = r7.a()
            if (r7 == 0) goto L68
            com.google.android.exoplayer2.i.r r7 = r6.h
            r7.b()
            goto L7a
        L68:
            supwisdom.x20 r7 = r6.k
            r7.a(r2)
            supwisdom.x20[] r7 = r6.l
            int r8 = r7.length
        L70:
            if (r1 >= r8) goto L7a
            r0 = r7[r1]
            r0.a(r2)
            int r1 = r1 + 1
            goto L70
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.q90.d(long):void");
    }

    @Override // supwisdom.mb0
    public boolean a() {
        return this.q || !(f() || this.k.d());
    }

    @Override // supwisdom.mb0
    public int a(e90 e90Var, y10 y10Var, boolean z) {
        if (f()) {
            return -3;
        }
        a(this.k.e());
        return this.k.a(e90Var, y10Var, z, this.q, this.p);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(n90 n90Var, long j, long j2) {
        this.d.a(n90Var);
        this.f.a(n90Var.f8485a, n90Var.b, this.f8897a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, j, j2, n90Var.d());
        this.f8898e.a(this);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(n90 n90Var, long j, long j2, boolean z) {
        this.f.b(n90Var.f8485a, n90Var.b, this.f8897a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, j, j2, n90Var.d());
        if (z) {
            return;
        }
        this.k.a(true);
        for (x20 x20Var : this.l) {
            x20Var.a(true);
        }
        this.f8898e.a(this);
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public int a(n90 n90Var, long j, long j2, IOException iOException) {
        boolean z;
        long jD = n90Var.d();
        boolean zA = a(n90Var);
        if (this.d.a(n90Var, !zA || jD == 0 || this.j.size() > 1, iOException)) {
            if (zA) {
                l90 l90VarRemoveLast = this.j.removeLast();
                e80.b(l90VarRemoveLast == n90Var);
                this.k.b(l90VarRemoveLast.a(0));
                int i = 0;
                while (true) {
                    x20[] x20VarArr = this.l;
                    if (i >= x20VarArr.length) {
                        break;
                    }
                    x20 x20Var = x20VarArr[i];
                    i++;
                    x20Var.b(l90VarRemoveLast.a(i));
                }
                if (this.j.isEmpty()) {
                    this.o = this.p;
                }
            }
            z = true;
        } else {
            z = false;
        }
        this.f.a(n90Var.f8485a, n90Var.b, this.f8897a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, j, j2, jD, iOException, z);
        if (!z) {
            return 0;
        }
        this.f8898e.a(this);
        return 2;
    }

    @Override // supwisdom.nb0
    public boolean a(long j) {
        if (this.q || this.h.a()) {
            return false;
        }
        T t = this.d;
        l90 last = this.j.isEmpty() ? null : this.j.getLast();
        long j2 = this.o;
        if (j2 == -9223372036854775807L) {
            j2 = j;
        }
        t.a(last, j2, this.i);
        p90 p90Var = this.i;
        boolean z = p90Var.b;
        n90 n90Var = p90Var.f8778a;
        p90Var.a();
        if (z) {
            this.q = true;
            return true;
        }
        if (n90Var == null) {
            return false;
        }
        if (a(n90Var)) {
            this.o = -9223372036854775807L;
            l90 l90Var = (l90) n90Var;
            l90Var.a(this.m);
            this.j.add(l90Var);
        }
        this.f.a(n90Var.f8485a, n90Var.b, this.f8897a, n90Var.c, n90Var.d, n90Var.f8486e, n90Var.f, n90Var.g, this.h.a(n90Var, this, this.g));
        return true;
    }

    public final boolean a(n90 n90Var) {
        return n90Var instanceof l90;
    }

    public final void a(int i) {
        while (this.j.size() > 1 && this.j.get(1).a(0) <= i) {
            this.j.removeFirst();
        }
        l90 first = this.j.getFirst();
        com.google.android.exoplayer2.j jVar = first.c;
        if (!jVar.equals(this.n)) {
            this.f.a(this.f8897a, jVar, first.d, first.f8486e, first.f);
        }
        this.n = jVar;
    }
}
