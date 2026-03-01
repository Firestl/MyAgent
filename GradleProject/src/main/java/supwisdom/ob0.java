package supwisdom;

import supwisdom.yb0;

/* JADX INFO: compiled from: SinglePeriodTimeline.java */
/* JADX INFO: loaded from: classes.dex */
public final class ob0 extends yb0 {
    public static final Object h = new Object();
    public final long b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f8652e;
    public final boolean f;
    public final boolean g;

    public ob0(long j, boolean z) {
        this(j, j, 0L, 0L, z, false);
    }

    @Override // supwisdom.yb0
    public yb0.c a(int i, yb0.c cVar, boolean z, long j) {
        e80.a(i, 0, 1);
        Object obj = z ? h : null;
        long j2 = this.f8652e;
        if (this.g) {
            j2 += j;
            if (j2 > this.c) {
                j2 = -9223372036854775807L;
            }
        }
        cVar.a(obj, -9223372036854775807L, -9223372036854775807L, this.f, this.g, j2, this.c, 0, 0, this.d);
        return cVar;
    }

    @Override // supwisdom.yb0
    public int b() {
        return 1;
    }

    @Override // supwisdom.yb0
    public int c() {
        return 1;
    }

    public ob0(long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.f8652e = j4;
        this.f = z;
        this.g = z2;
    }

    @Override // supwisdom.yb0
    public yb0.b a(int i, yb0.b bVar, boolean z) {
        e80.a(i, 0, 1);
        Object obj = z ? h : null;
        bVar.a(obj, obj, 0, this.b, -this.d, false);
        return bVar;
    }

    @Override // supwisdom.yb0
    public int a(Object obj) {
        return h.equals(obj) ? 0 : -1;
    }
}
