package supwisdom;

import android.net.Uri;

/* JADX INFO: compiled from: RangedUri.java */
/* JADX INFO: loaded from: classes.dex */
public final class fa0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f7580a;
    public final long b;
    public final String c;
    public int d;

    public fa0(String str, long j, long j2) {
        this.c = str == null ? "" : str;
        this.f7580a = j;
        this.b = j2;
    }

    public Uri a(String str) {
        return w80.a(str, this.c);
    }

    public String b(String str) {
        return w80.b(str, this.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || fa0.class != obj.getClass()) {
            return false;
        }
        fa0 fa0Var = (fa0) obj;
        return this.f7580a == fa0Var.f7580a && this.b == fa0Var.b && this.c.equals(fa0Var.c);
    }

    public int hashCode() {
        if (this.d == 0) {
            this.d = ((((527 + ((int) this.f7580a)) * 31) + ((int) this.b)) * 31) + this.c.hashCode();
        }
        return this.d;
    }

    public fa0 a(fa0 fa0Var, String str) {
        String strB = b(str);
        if (fa0Var != null && strB.equals(fa0Var.b(str))) {
            long j = this.b;
            if (j != -1) {
                long j2 = this.f7580a;
                if (j2 + j == fa0Var.f7580a) {
                    long j3 = fa0Var.b;
                    return new fa0(strB, j2, j3 != -1 ? j + j3 : -1L);
                }
            }
            long j4 = fa0Var.b;
            if (j4 != -1) {
                long j5 = fa0Var.f7580a;
                if (j5 + j4 == this.f7580a) {
                    long j6 = this.b;
                    return new fa0(strB, j5, j6 != -1 ? j4 + j6 : -1L);
                }
            }
        }
        return null;
    }
}
