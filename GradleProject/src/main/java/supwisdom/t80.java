package supwisdom;

import android.os.SystemClock;

/* JADX INFO: compiled from: StandaloneMediaClock.java */
/* JADX INFO: loaded from: classes.dex */
public final class t80 implements k80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9263a;
    public long b;
    public long c;
    public g90 d = g90.d;

    public void a() {
        if (this.f9263a) {
            return;
        }
        this.c = SystemClock.elapsedRealtime();
        this.f9263a = true;
    }

    public void b() {
        if (this.f9263a) {
            a(o());
            this.f9263a = false;
        }
    }

    @Override // supwisdom.k80
    public long o() {
        long j = this.b;
        if (!this.f9263a) {
            return j;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.c;
        g90 g90Var = this.d;
        return j + (g90Var.f7706a == 1.0f ? b20.b(jElapsedRealtime) : g90Var.a(jElapsedRealtime));
    }

    @Override // supwisdom.k80
    public g90 p() {
        return this.d;
    }

    public void a(long j) {
        this.b = j;
        if (this.f9263a) {
            this.c = SystemClock.elapsedRealtime();
        }
    }

    public void a(k80 k80Var) {
        a(k80Var.o());
        this.d = k80Var.p();
    }

    @Override // supwisdom.k80
    public g90 a(g90 g90Var) {
        if (this.f9263a) {
            a(o());
        }
        this.d = g90Var;
        return g90Var;
    }
}
