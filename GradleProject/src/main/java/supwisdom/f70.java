package supwisdom;

import android.os.SystemClock;
import supwisdom.k70;

/* JADX INFO: compiled from: AdaptiveTrackSelection.java */
/* JADX INFO: loaded from: classes.dex */
public class f70 extends g70 {
    public final r70 g;
    public final int h;
    public final long i;
    public final long j;
    public final float k;
    public int l;
    public int m;

    /* JADX INFO: compiled from: AdaptiveTrackSelection.java */
    public static final class a implements k70.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final r70 f7571a;
        public final int b;
        public final int c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f7572e;
        public final float f;

        public a(r70 r70Var) {
            this(r70Var, 800000, 10000, 25000, 25000, 0.75f);
        }

        @Override // supwisdom.k70.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public f70 a(pb0 pb0Var, int... iArr) {
            return new f70(pb0Var, iArr, this.f7571a, this.b, this.c, this.d, this.f7572e, this.f);
        }

        public a(r70 r70Var, int i, int i2, int i3, int i4, float f) {
            this.f7571a = r70Var;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.f7572e = i4;
            this.f = f;
        }
    }

    public f70(pb0 pb0Var, int[] iArr, r70 r70Var, int i, long j, long j2, long j3, float f) {
        super(pb0Var, iArr);
        this.g = r70Var;
        this.h = i;
        this.i = j * 1000;
        this.j = j2 * 1000;
        this.k = f;
        this.l = b(Long.MIN_VALUE);
        this.m = 1;
    }

    @Override // supwisdom.k70
    public void a(long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i = this.l;
        com.google.android.exoplayer2.j jVarF = f();
        int iB = b(jElapsedRealtime);
        com.google.android.exoplayer2.j jVarA = a(iB);
        this.l = iB;
        if (jVarF != null && !b(iB, jElapsedRealtime)) {
            if (jVarA.b > jVarF.b && j < this.i) {
                this.l = i;
            } else if (jVarA.b < jVarF.b && j >= this.j) {
                this.l = i;
            }
        }
        if (this.l != i) {
            this.m = 3;
        }
    }

    @Override // supwisdom.k70
    public int b() {
        return this.m;
    }

    @Override // supwisdom.k70
    public Object c() {
        return null;
    }

    public final int b(long j) {
        long jA = this.g.a();
        long j2 = jA == -1 ? this.h : (long) (jA * this.k);
        int i = 0;
        for (int i2 = 0; i2 < this.b; i2++) {
            if (j == Long.MIN_VALUE || !b(i2, j)) {
                if (a(i2).b <= j2) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    @Override // supwisdom.k70
    public int a() {
        return this.l;
    }
}
