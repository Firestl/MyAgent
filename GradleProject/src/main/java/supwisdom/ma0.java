package supwisdom;

import android.util.Pair;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import supwisdom.aa0;
import supwisdom.k90;
import supwisdom.nb0;
import supwisdom.q90;
import supwisdom.ta0;

/* JADX INFO: compiled from: DashMediaPeriod.java */
/* JADX INFO: loaded from: classes.dex */
public final class ma0 implements ta0, nb0.a<q90<aa0>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8369a;
    public final aa0.a b;
    public final int c;
    public final k90.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f8370e;
    public final b80 f;
    public final q70 g;
    public final qb0 h;
    public final a[] i;
    public ta0.a j;
    public q90<aa0>[] k;
    public y90 l;
    public ca0 m;
    public int n;
    public List<ba0> o;

    /* JADX INFO: compiled from: DashMediaPeriod.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8371a;
        public final int b;

        public a(int i, int i2) {
            this.f8371a = i;
            this.b = i2;
        }
    }

    public ma0(int i, ca0 ca0Var, int i2, aa0.a aVar, int i3, k90.a aVar2, long j, b80 b80Var, q70 q70Var) {
        this.f8369a = i;
        this.m = ca0Var;
        this.n = i2;
        this.b = aVar;
        this.c = i3;
        this.d = aVar2;
        this.f8370e = j;
        this.f = b80Var;
        this.g = q70Var;
        q90<aa0>[] q90VarArrA = a(0);
        this.k = q90VarArrA;
        this.l = new y90(q90VarArrA);
        List<ba0> list = ca0Var.a(i2).c;
        this.o = list;
        Pair<qb0, a[]> pairA = a(list);
        this.h = (qb0) pairA.first;
        this.i = (a[]) pairA.second;
    }

    @Override // supwisdom.ta0
    public void b(long j) {
        for (q90<aa0> q90Var : this.k) {
            q90Var.b(j);
        }
    }

    @Override // supwisdom.ta0
    public void c() throws IOException {
        this.f.d();
    }

    @Override // supwisdom.ta0
    public qb0 d() {
        return this.h;
    }

    @Override // supwisdom.ta0
    public long e() {
        return -9223372036854775807L;
    }

    @Override // supwisdom.ta0
    public long f() {
        long jMin = Long.MAX_VALUE;
        for (q90<aa0> q90Var : this.k) {
            long jD = q90Var.d();
            if (jD != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jD);
            }
        }
        if (jMin == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return jMin;
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public long i() {
        return this.l.i();
    }

    public void a(ca0 ca0Var, int i) {
        this.m = ca0Var;
        this.n = i;
        this.o = ca0Var.a(i).c;
        q90<aa0>[] q90VarArr = this.k;
        if (q90VarArr != null) {
            for (q90<aa0> q90Var : q90VarArr) {
                ((aa0) q90Var.c()).a(ca0Var, i);
            }
            this.j.a(this);
        }
    }

    @Override // supwisdom.ta0
    public long d(long j) {
        for (q90<aa0> q90Var : this.k) {
            q90Var.d(j);
        }
        return j;
    }

    public static int b(List<ba0> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ba0 ba0Var = list.get(i2);
            if (a(ba0Var)) {
                i++;
            }
            if (b(ba0Var)) {
                i++;
            }
        }
        return i;
    }

    public static boolean b(ba0 ba0Var) {
        List<ha0> list = ba0Var.d;
        for (int i = 0; i < list.size(); i++) {
            if ("urn:scte:dash:cc:cea-608:2015".equals(list.get(i).f7819a)) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        for (q90<aa0> q90Var : this.k) {
            q90Var.e();
        }
    }

    @Override // supwisdom.ta0
    public void a(ta0.a aVar) {
        this.j = aVar;
        aVar.a((ta0) this);
    }

    @Override // supwisdom.ta0
    public long a(k70[] k70VarArr, boolean[] zArr, mb0[] mb0VarArr, boolean[] zArr2, long j) {
        int iA;
        boolean z;
        mb0 mb0VarA;
        int iA2;
        int size = this.o.size();
        HashMap map = new HashMap();
        for (int i = 0; i < k70VarArr.length; i++) {
            if (mb0VarArr[i] instanceof q90) {
                q90 q90Var = (q90) mb0VarArr[i];
                if (k70VarArr[i] != null && zArr[i]) {
                    map.put(Integer.valueOf(this.h.a(k70VarArr[i].d())), q90Var);
                } else {
                    q90Var.e();
                    mb0VarArr[i] = null;
                }
            }
            if (mb0VarArr[i] == null && k70VarArr[i] != null && (iA2 = this.h.a(k70VarArr[i].d())) < size) {
                q90<aa0> q90VarA = a(iA2, k70VarArr[i], j);
                map.put(Integer.valueOf(iA2), q90VarA);
                mb0VarArr[i] = q90VarA;
                zArr2[i] = true;
            }
        }
        for (int i2 = 0; i2 < k70VarArr.length; i2++) {
            if (((mb0VarArr[i2] instanceof q90.a) || (mb0VarArr[i2] instanceof z90)) && (k70VarArr[i2] == null || !zArr[i2])) {
                a(mb0VarArr[i2]);
                mb0VarArr[i2] = null;
            }
            if (k70VarArr[i2] != null && (iA = this.h.a(k70VarArr[i2].d())) >= size) {
                a aVar = this.i[iA - size];
                q90 q90Var2 = (q90) map.get(Integer.valueOf(aVar.f8371a));
                mb0 mb0Var = mb0VarArr[i2];
                if (q90Var2 == null) {
                    z = mb0Var instanceof z90;
                } else {
                    z = (mb0Var instanceof q90.a) && ((q90.a) mb0Var).f8899a == q90Var2;
                }
                if (!z) {
                    a(mb0Var);
                    if (q90Var2 == null) {
                        mb0VarA = new z90();
                    } else {
                        mb0VarA = q90Var2.a(j, aVar.b);
                    }
                    mb0VarArr[i2] = mb0VarA;
                    zArr2[i2] = true;
                }
            }
        }
        this.k = a(map.size());
        map.values().toArray(this.k);
        this.l = new y90(this.k);
        return j;
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public boolean a(long j) {
        return this.l.a(j);
    }

    @Override // supwisdom.nb0.a
    public void a(q90<aa0> q90Var) {
        this.j.a(this);
    }

    public static Pair<qb0, a[]> a(List<ba0> list) {
        int size = list.size();
        int iB = b(list);
        pb0[] pb0VarArr = new pb0[size + iB];
        a[] aVarArr = new a[iB];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ba0 ba0Var = list.get(i2);
            List<ga0> list2 = ba0Var.c;
            int size2 = list2.size();
            com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[size2];
            for (int i3 = 0; i3 < size2; i3++) {
                jVarArr[i3] = list2.get(i3).f7707a;
            }
            pb0VarArr[i2] = new pb0(jVarArr);
            if (a(ba0Var)) {
                pb0VarArr[size + i] = new pb0(com.google.android.exoplayer2.j.a(ba0Var.f7043a + ":emsg", "application/x-emsg", null, -1, null));
                aVarArr[i] = new a(i2, 4);
                i++;
            }
            if (b(ba0Var)) {
                pb0VarArr[size + i] = new pb0(com.google.android.exoplayer2.j.a(ba0Var.f7043a + ":cea608", "application/cea-608", (String) null, -1, 0, (String) null, (com.google.android.exoplayer2.c.a) null));
                aVarArr[i] = new a(i2, 3);
                i++;
            }
        }
        return Pair.create(new qb0(pb0VarArr), aVarArr);
    }

    public final q90<aa0> a(int i, k70 k70Var, long j) {
        ba0 ba0Var = this.o.get(i);
        int[] iArr = new int[2];
        boolean zA = a(ba0Var);
        int i2 = 0;
        if (zA) {
            iArr[0] = 4;
            i2 = 1;
        }
        boolean zB = b(ba0Var);
        if (zB) {
            iArr[i2] = 3;
            i2++;
        }
        return new q90<>(ba0Var.b, i2 < 2 ? Arrays.copyOf(iArr, i2) : iArr, this.b.a(this.f, this.m, this.n, i, k70Var, this.f8370e, zA, zB), this, this.g, j, this.c, this.d);
    }

    public static boolean a(ba0 ba0Var) {
        List<ga0> list = ba0Var.c;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).d.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static q90<aa0>[] a(int i) {
        return new q90[i];
    }

    public static void a(mb0 mb0Var) {
        if (mb0Var instanceof q90.a) {
            ((q90.a) mb0Var).c();
        }
    }
}
