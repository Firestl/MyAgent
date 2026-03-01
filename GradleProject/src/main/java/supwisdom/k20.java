package supwisdom;

/* JADX INFO: compiled from: ChunkIndex.java */
/* JADX INFO: loaded from: classes.dex */
public final class k20 implements e50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8113a;
    public final int[] b;
    public final long[] c;
    public final long[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long[] f8114e;
    public final long f;

    public k20(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.b = iArr;
        this.c = jArr;
        this.d = jArr2;
        this.f8114e = jArr3;
        int length = iArr.length;
        this.f8113a = length;
        if (length > 0) {
            this.f = jArr2[length - 1] + jArr3[length - 1];
        } else {
            this.f = 0L;
        }
    }

    public int a(long j) {
        return x80.a(this.f8114e, j, true, true);
    }

    @Override // supwisdom.e50
    public boolean a() {
        return true;
    }

    @Override // supwisdom.e50
    public long b() {
        return this.f;
    }

    @Override // supwisdom.e50
    public long b(long j) {
        return this.c[a(j)];
    }
}
