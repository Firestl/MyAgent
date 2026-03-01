package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: TrackGroup.java */
/* JADX INFO: loaded from: classes.dex */
public final class pb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8788a;
    public final com.google.android.exoplayer2.j[] b;
    public int c;

    public pb0(com.google.android.exoplayer2.j... jVarArr) {
        e80.b(jVarArr.length > 0);
        this.b = jVarArr;
        this.f8788a = jVarArr.length;
    }

    public com.google.android.exoplayer2.j a(int i) {
        return this.b[i];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || pb0.class != obj.getClass()) {
            return false;
        }
        pb0 pb0Var = (pb0) obj;
        return this.f8788a == pb0Var.f8788a && Arrays.equals(this.b, pb0Var.b);
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = 527 + Arrays.hashCode(this.b);
        }
        return this.c;
    }

    public int a(com.google.android.exoplayer2.j jVar) {
        int i = 0;
        while (true) {
            com.google.android.exoplayer2.j[] jVarArr = this.b;
            if (i >= jVarArr.length) {
                return -1;
            }
            if (jVar == jVarArr[i]) {
                return i;
            }
            i++;
        }
    }
}
