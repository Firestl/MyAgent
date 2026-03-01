package supwisdom;

import android.util.Base64;
import java.io.IOException;
import java.util.ArrayList;
import supwisdom.k90;
import supwisdom.nb0;
import supwisdom.sb0;
import supwisdom.ta0;
import supwisdom.tb0;

/* JADX INFO: compiled from: SsMediaPeriod.java */
/* JADX INFO: loaded from: classes.dex */
public final class ub0 implements ta0, nb0.a<q90<tb0>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final tb0.a f9390a;
    public final b80 b;
    public final int c;
    public final k90.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q70 f9391e;
    public final qb0 f;
    public final i30[] g;
    public ta0.a h;
    public sb0 i;
    public q90<tb0>[] j;
    public y90 k;

    public ub0(sb0 sb0Var, tb0.a aVar, int i, k90.a aVar2, b80 b80Var, q70 q70Var) {
        this.f9390a = aVar;
        this.b = b80Var;
        this.c = i;
        this.d = aVar2;
        this.f9391e = q70Var;
        this.f = b(sb0Var);
        sb0.a aVar3 = sb0Var.b;
        if (aVar3 != null) {
            this.g = new i30[]{new i30(true, 8, a(aVar3.b))};
        } else {
            this.g = null;
        }
        this.i = sb0Var;
        q90<tb0>[] q90VarArrA = a(0);
        this.j = q90VarArrA;
        this.k = new y90(q90VarArrA);
    }

    public static qb0 b(sb0 sb0Var) {
        pb0[] pb0VarArr = new pb0[sb0Var.c.length];
        int i = 0;
        while (true) {
            sb0.b[] bVarArr = sb0Var.c;
            if (i >= bVarArr.length) {
                return new qb0(pb0VarArr);
            }
            pb0VarArr[i] = new pb0(bVarArr[i].c);
            i++;
        }
    }

    @Override // supwisdom.ta0
    public void b(long j) {
    }

    @Override // supwisdom.ta0
    public void c() throws IOException {
        this.b.d();
    }

    @Override // supwisdom.ta0
    public qb0 d() {
        return this.f;
    }

    @Override // supwisdom.ta0
    public long e() {
        return -9223372036854775807L;
    }

    @Override // supwisdom.ta0
    public long f() {
        long jMin = Long.MAX_VALUE;
        for (q90<tb0> q90Var : this.j) {
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
        return this.k.i();
    }

    public void a(sb0 sb0Var) {
        this.i = sb0Var;
        for (q90<tb0> q90Var : this.j) {
            ((tb0) q90Var.c()).a(sb0Var);
        }
        this.h.a(this);
    }

    @Override // supwisdom.ta0
    public long d(long j) {
        for (q90<tb0> q90Var : this.j) {
            q90Var.d(j);
        }
        return j;
    }

    public void a() {
        for (q90<tb0> q90Var : this.j) {
            q90Var.e();
        }
    }

    @Override // supwisdom.ta0
    public void a(ta0.a aVar) {
        this.h = aVar;
        aVar.a((ta0) this);
    }

    @Override // supwisdom.ta0
    public long a(k70[] k70VarArr, boolean[] zArr, mb0[] mb0VarArr, boolean[] zArr2, long j) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < k70VarArr.length; i++) {
            if (mb0VarArr[i] != null) {
                q90 q90Var = (q90) mb0VarArr[i];
                if (k70VarArr[i] != null && zArr[i]) {
                    arrayList.add(q90Var);
                } else {
                    q90Var.e();
                    mb0VarArr[i] = null;
                }
            }
            if (mb0VarArr[i] == null && k70VarArr[i] != null) {
                q90<tb0> q90VarA = a(k70VarArr[i], j);
                arrayList.add(q90VarA);
                mb0VarArr[i] = q90VarA;
                zArr2[i] = true;
            }
        }
        q90<tb0>[] q90VarArrA = a(arrayList.size());
        this.j = q90VarArrA;
        arrayList.toArray(q90VarArrA);
        this.k = new y90(this.j);
        return j;
    }

    @Override // supwisdom.ta0, supwisdom.nb0
    public boolean a(long j) {
        return this.k.a(j);
    }

    @Override // supwisdom.nb0.a
    public void a(q90<tb0> q90Var) {
        this.h.a(this);
    }

    public final q90<tb0> a(k70 k70Var, long j) {
        int iA = this.f.a(k70Var.d());
        return new q90<>(this.i.c[iA].f9147a, null, this.f9390a.a(this.b, this.i, iA, k70Var, this.g), this, this.f9391e, j, this.c, this.d);
    }

    public static q90<tb0>[] a(int i) {
        return new q90[i];
    }

    public static byte[] a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i += 2) {
            sb.append((char) bArr[i]);
        }
        String string = sb.toString();
        byte[] bArrDecode = Base64.decode(string.substring(string.indexOf("<KID>") + 5, string.indexOf("</KID>")), 0);
        a(bArrDecode, 0, 3);
        a(bArrDecode, 1, 2);
        a(bArrDecode, 4, 5);
        a(bArrDecode, 6, 7);
        return bArrDecode;
    }

    public static void a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }
}
