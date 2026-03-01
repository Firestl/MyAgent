package supwisdom;

import supwisdom.t20;

/* JADX INFO: compiled from: ConstantBitrateSeeker.java */
/* JADX INFO: loaded from: classes.dex */
public final class s20 implements t20.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f9111a;
    public final int b;
    public final long c;

    public s20(long j, int i, long j2) {
        this.f9111a = j;
        this.b = i;
        this.c = j2 == -1 ? -9223372036854775807L : a(j2);
    }

    @Override // supwisdom.e50
    public boolean a() {
        return this.c != -9223372036854775807L;
    }

    @Override // supwisdom.e50
    public long b(long j) {
        if (this.c == -9223372036854775807L) {
            return 0L;
        }
        return ((j * ((long) this.b)) / 8000000) + this.f9111a;
    }

    @Override // supwisdom.t20.b
    public long a(long j) {
        return ((Math.max(0L, j - this.f9111a) * 1000000) * 8) / ((long) this.b);
    }

    @Override // supwisdom.e50
    public long b() {
        return this.c;
    }
}
