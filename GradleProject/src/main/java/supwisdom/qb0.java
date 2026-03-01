package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: TrackGroupArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class qb0 {
    public static final qb0 d = new qb0(new pb0[0]);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8907a;
    public final pb0[] b;
    public int c;

    public qb0(pb0... pb0VarArr) {
        this.b = pb0VarArr;
        this.f8907a = pb0VarArr.length;
    }

    public pb0 a(int i) {
        return this.b[i];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || qb0.class != obj.getClass()) {
            return false;
        }
        qb0 qb0Var = (qb0) obj;
        return this.f8907a == qb0Var.f8907a && Arrays.equals(this.b, qb0Var.b);
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b);
        }
        return this.c;
    }

    public int a(pb0 pb0Var) {
        for (int i = 0; i < this.f8907a; i++) {
            if (this.b[i] == pb0Var) {
                return i;
            }
        }
        return -1;
    }
}
