package supwisdom;

/* JADX INFO: compiled from: FlacStreamInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class i80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7928a;
    public final int b;
    public final int c;
    public final long d;

    public i80(byte[] bArr, int i) {
        n80 n80Var = new n80(bArr);
        n80Var.a(i * 8);
        n80Var.c(16);
        n80Var.c(16);
        n80Var.c(24);
        n80Var.c(24);
        this.f7928a = n80Var.c(20);
        this.b = n80Var.c(3) + 1;
        this.c = n80Var.c(5) + 1;
        this.d = n80Var.c(36);
    }

    public int a() {
        return this.c * this.f7928a;
    }

    public long b() {
        return (this.d * 1000000) / ((long) this.f7928a);
    }
}
