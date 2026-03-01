package supwisdom;

import android.os.Handler;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import supwisdom.jb0;
import supwisdom.k90;
import supwisdom.ta0;
import supwisdom.va0;
import supwisdom.za0;

/* JADX INFO: compiled from: HlsMediaPeriod.java */
/* JADX INFO: loaded from: classes.dex */
public final class gb0 implements ta0, za0.b, jb0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final za0 f7710a;
    public final db0 b;
    public final int c;
    public final k90.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q70 f7711e;
    public final IdentityHashMap<mb0, Integer> f = new IdentityHashMap<>();
    public final kb0 g = new kb0();
    public final Handler h = new Handler();
    public final long i;
    public ta0.a j;
    public int k;
    public boolean l;
    public qb0 m;
    public jb0[] n;
    public jb0[] o;
    public y90 p;

    public gb0(za0 za0Var, db0 db0Var, int i, k90.a aVar, q70 q70Var, long j) {
        this.f7710a = za0Var;
        this.b = db0Var;
        this.c = i;
        this.d = aVar;
        this.f7711e = q70Var;
        this.i = j;
    }

    public final void b() {
        va0 va0VarB = this.f7710a.b();
        ArrayList arrayList = new ArrayList(va0VarB.b);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            va0.a aVar = (va0.a) arrayList.get(i);
            if (aVar.b.k > 0 || a(aVar, "avc")) {
                arrayList2.add(aVar);
            } else if (a(aVar, "mp4a")) {
                arrayList3.add(aVar);
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList = arrayList2;
        } else if (arrayList3.size() < arrayList.size()) {
            arrayList.removeAll(arrayList3);
        }
        List<va0.a> list = va0VarB.c;
        List<va0.a> list2 = va0VarB.d;
        jb0[] jb0VarArr = new jb0[list.size() + 1 + list2.size()];
        this.n = jb0VarArr;
        this.k = jb0VarArr.length;
        e80.a(!arrayList.isEmpty());
        va0.a[] aVarArr = new va0.a[arrayList.size()];
        arrayList.toArray(aVarArr);
        jb0 jb0VarA = a(0, aVarArr, va0VarB.f9496e, va0VarB.f);
        this.n[0] = jb0VarA;
        jb0VarA.a(true);
        jb0VarA.b();
        int i2 = 0;
        int i3 = 1;
        while (i2 < list.size()) {
            jb0 jb0VarA2 = a(1, new va0.a[]{list.get(i2)}, null, Collections.emptyList());
            this.n[i3] = jb0VarA2;
            jb0VarA2.b();
            i2++;
            i3++;
        }
        int i4 = 0;
        while (i4 < list2.size()) {
            va0.a aVar2 = list2.get(i4);
            jb0 jb0VarA3 = a(3, new va0.a[]{aVar2}, null, Collections.emptyList());
            jb0VarA3.b(aVar2.b);
            this.n[i3] = jb0VarA3;
            i4++;
            i3++;
        }
    }

    @Override // supwisdom.ta0
    public void b(long j) {
    }

    @Override // supwisdom.ta0
    public void c() throws IOException {
        jb0[] jb0VarArr = this.n;
        if (jb0VarArr != null) {
            for (jb0 jb0Var : jb0VarArr) {
                jb0Var.c();
            }
        }
    }

    @Override // supwisdom.ta0
    public qb0 d() {
        return this.m;
    }

    @Override // supwisdom.ta0
    public long e() {
        return -9223372036854775807L;
    }

    @Override // supwisdom.ta0
    public long f() {
        long jMin = Long.MAX_VALUE;
        for (jb0 jb0Var : this.o) {
            long jE = jb0Var.e();
            if (jE != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jE);
            }
        }
        if (jMin == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return jMin;
    }

    @Override // supwisdom.jb0.b
    public void g() {
        int i = this.k - 1;
        this.k = i;
        if (i > 0) {
            return;
        }
        int i2 = 0;
        for (jb0 jb0Var : this.n) {
            i2 += jb0Var.d().f8907a;
        }
        pb0[] pb0VarArr = new pb0[i2];
        int i3 = 0;
        for (jb0 jb0Var2 : this.n) {
            int i4 = jb0Var2.d().f8907a;
            int i5 = 0;
            while (i5 < i4) {
                pb0VarArr[i3] = jb0Var2.d().a(i5);
                i5++;
                i3++;
            }
        }
        this.m = new qb0(pb0VarArr);
        this.j.a((ta0) this);
    }

    @Override // supwisdom.za0.b
    public void h() {
        j();
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public long i() {
        return this.p.i();
    }

    public final void j() {
        if (this.m != null) {
            this.j.a(this);
            return;
        }
        for (jb0 jb0Var : this.n) {
            jb0Var.b();
        }
    }

    public void a() {
        this.f7710a.b(this);
        this.h.removeCallbacksAndMessages(null);
        jb0[] jb0VarArr = this.n;
        if (jb0VarArr != null) {
            for (jb0 jb0Var : jb0VarArr) {
                jb0Var.f();
            }
        }
    }

    @Override // supwisdom.ta0
    public long d(long j) {
        this.g.a();
        for (jb0 jb0Var : this.o) {
            jb0Var.b(j);
        }
        return j;
    }

    @Override // supwisdom.ta0
    public void a(ta0.a aVar) {
        this.f7710a.a(this);
        this.j = aVar;
        b();
    }

    @Override // supwisdom.ta0
    public long a(k70[] k70VarArr, boolean[] zArr, mb0[] mb0VarArr, boolean[] zArr2, long j) {
        long j2;
        int[] iArr = new int[k70VarArr.length];
        int[] iArr2 = new int[k70VarArr.length];
        for (int i = 0; i < k70VarArr.length; i++) {
            iArr[i] = mb0VarArr[i] == null ? -1 : this.f.get(mb0VarArr[i]).intValue();
            iArr2[i] = -1;
            if (k70VarArr[i] != null) {
                pb0 pb0VarD = k70VarArr[i].d();
                int i2 = 0;
                while (true) {
                    jb0[] jb0VarArr = this.n;
                    if (i2 >= jb0VarArr.length) {
                        break;
                    }
                    if (jb0VarArr[i2].d().a(pb0VarD) != -1) {
                        iArr2[i] = i2;
                        break;
                    }
                    i2++;
                }
            }
        }
        this.f.clear();
        int length = k70VarArr.length;
        mb0[] mb0VarArr2 = new mb0[length];
        mb0[] mb0VarArr3 = new mb0[k70VarArr.length];
        k70[] k70VarArr2 = new k70[k70VarArr.length];
        ArrayList arrayList = new ArrayList(this.n.length);
        int i3 = 0;
        boolean zA = false;
        while (i3 < this.n.length) {
            for (int i4 = 0; i4 < k70VarArr.length; i4++) {
                k70 k70Var = null;
                mb0VarArr3[i4] = iArr[i4] == i3 ? mb0VarArr[i4] : null;
                if (iArr2[i4] == i3) {
                    k70Var = k70VarArr[i4];
                }
                k70VarArr2[i4] = k70Var;
            }
            int i5 = i3;
            ArrayList arrayList2 = arrayList;
            k70[] k70VarArr3 = k70VarArr2;
            zA |= this.n[i3].a(k70VarArr2, zArr, mb0VarArr3, zArr2, !this.l);
            boolean z = false;
            for (int i6 = 0; i6 < k70VarArr.length; i6++) {
                if (iArr2[i6] == i5) {
                    e80.b(mb0VarArr3[i6] != null);
                    mb0VarArr2[i6] = mb0VarArr3[i6];
                    this.f.put(mb0VarArr3[i6], Integer.valueOf(i5));
                    z = true;
                } else if (iArr[i6] == i5) {
                    e80.b(mb0VarArr3[i6] == null);
                }
            }
            if (z) {
                arrayList2.add(this.n[i5]);
            }
            i3 = i5 + 1;
            arrayList = arrayList2;
            k70VarArr2 = k70VarArr3;
        }
        ArrayList arrayList3 = arrayList;
        System.arraycopy(mb0VarArr2, 0, mb0VarArr, 0, length);
        jb0[] jb0VarArr2 = new jb0[arrayList3.size()];
        this.o = jb0VarArr2;
        arrayList3.toArray(jb0VarArr2);
        jb0[] jb0VarArr3 = this.o;
        if (jb0VarArr3.length > 0) {
            jb0VarArr3[0].a(true);
            int i7 = 1;
            while (true) {
                jb0[] jb0VarArr4 = this.o;
                if (i7 >= jb0VarArr4.length) {
                    break;
                }
                jb0VarArr4[i7].a(false);
                i7++;
            }
        }
        this.p = new y90(this.o);
        if (this.l && zA) {
            j2 = j;
            d(j2);
            for (int i8 = 0; i8 < k70VarArr.length; i8++) {
                if (mb0VarArr[i8] != null) {
                    zArr2[i8] = true;
                }
            }
        } else {
            j2 = j;
        }
        this.l = true;
        return j2;
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public boolean a(long j) {
        return this.p.a(j);
    }

    @Override // supwisdom.jb0.b
    public void a(va0.a aVar) {
        this.f7710a.d(aVar);
    }

    @Override // supwisdom.nb0.a
    public void a(jb0 jb0Var) {
        if (this.m == null) {
            return;
        }
        this.j.a(this);
    }

    @Override // supwisdom.za0.b
    public void a(va0.a aVar, long j) {
        for (jb0 jb0Var : this.n) {
            jb0Var.a(aVar, j);
        }
        j();
    }

    public final jb0 a(int i, va0.a[] aVarArr, com.google.android.exoplayer2.j jVar, List<com.google.android.exoplayer2.j> list) {
        return new jb0(i, this, new cb0(this.f7710a, aVarArr, this.b, this.g, list), this.f7711e, this.i, jVar, this.c, this.d);
    }

    public static boolean a(va0.a aVar, String str) {
        String str2 = aVar.b.c;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        for (String str3 : str2.split("(\\s*,\\s*)|(\\s*$)")) {
            if (str3.startsWith(str)) {
                return true;
            }
        }
        return false;
    }
}
