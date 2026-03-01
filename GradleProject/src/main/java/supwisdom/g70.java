package supwisdom;

import android.os.SystemClock;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: BaseTrackSelection.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g70 implements k70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final pb0 f7698a;
    public final int b;
    public final int[] c;
    public final com.google.android.exoplayer2.j[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long[] f7699e;
    public int f;

    /* JADX INFO: compiled from: BaseTrackSelection.java */
    public static final class b implements Comparator<com.google.android.exoplayer2.j> {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(com.google.android.exoplayer2.j jVar, com.google.android.exoplayer2.j jVar2) {
            return jVar2.b - jVar.b;
        }
    }

    public g70(pb0 pb0Var, int... iArr) {
        int i = 0;
        e80.b(iArr.length > 0);
        e80.a(pb0Var);
        this.f7698a = pb0Var;
        int length = iArr.length;
        this.b = length;
        this.d = new com.google.android.exoplayer2.j[length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.d[i2] = pb0Var.a(iArr[i2]);
        }
        Arrays.sort(this.d, new b());
        this.c = new int[this.b];
        while (true) {
            int i3 = this.b;
            if (i >= i3) {
                this.f7699e = new long[i3];
                return;
            } else {
                this.c[i] = pb0Var.a(this.d[i]);
                i++;
            }
        }
    }

    @Override // supwisdom.k70
    public final com.google.android.exoplayer2.j a(int i) {
        return this.d[i];
    }

    @Override // supwisdom.k70
    public final int b(int i) {
        return this.c[i];
    }

    @Override // supwisdom.k70
    public final int c(int i) {
        for (int i2 = 0; i2 < this.b; i2++) {
            if (this.c[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    @Override // supwisdom.k70
    public final pb0 d() {
        return this.f7698a;
    }

    @Override // supwisdom.k70
    public final int e() {
        return this.c.length;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g70 g70Var = (g70) obj;
        return this.f7698a == g70Var.f7698a && Arrays.equals(this.c, g70Var.c);
    }

    @Override // supwisdom.k70
    public final com.google.android.exoplayer2.j f() {
        return this.d[a()];
    }

    @Override // supwisdom.k70
    public final int g() {
        return this.c[a()];
    }

    public int hashCode() {
        if (this.f == 0) {
            this.f = (System.identityHashCode(this.f7698a) * 31) + Arrays.hashCode(this.c);
        }
        return this.f;
    }

    @Override // supwisdom.k70
    public final int a(com.google.android.exoplayer2.j jVar) {
        for (int i = 0; i < this.b; i++) {
            if (this.d[i] == jVar) {
                return i;
            }
        }
        return -1;
    }

    public final boolean b(int i, long j) {
        return this.f7699e[i] > j;
    }

    @Override // supwisdom.k70
    public final boolean a(int i, long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zB = b(i, jElapsedRealtime);
        int i2 = 0;
        while (i2 < this.b && !zB) {
            zB = (i2 == i || b(i2, jElapsedRealtime)) ? false : true;
            i2++;
        }
        if (!zB) {
            return false;
        }
        long[] jArr = this.f7699e;
        jArr[i] = Math.max(jArr[i], jElapsedRealtime + j);
        return true;
    }
}
