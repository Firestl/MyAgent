package supwisdom;

/* JADX INFO: compiled from: TimestampAdjuster.java */
/* JADX INFO: loaded from: classes.dex */
public final class u80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f9385a;
    public long b;
    public volatile long c = -9223372036854775807L;

    public u80(long j) {
        a(j);
    }

    public synchronized void a(long j) {
        e80.b(this.c == -9223372036854775807L);
        this.f9385a = j;
    }

    public long b() {
        if (this.c != -9223372036854775807L) {
            return this.c;
        }
        long j = this.f9385a;
        if (j != Long.MAX_VALUE) {
            return j;
        }
        return -9223372036854775807L;
    }

    public long c() {
        if (this.f9385a == Long.MAX_VALUE) {
            return 0L;
        }
        if (this.c == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.b;
    }

    public void d() {
        this.c = -9223372036854775807L;
    }

    public synchronized void e() throws InterruptedException {
        while (this.c == -9223372036854775807L) {
            wait();
        }
    }

    public static long d(long j) {
        return (j * 1000000) / 90000;
    }

    public long b(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            long jE = e(this.c);
            long j2 = (4294967296L + jE) / 8589934592L;
            long j3 = ((j2 - 1) * 8589934592L) + j;
            j += j2 * 8589934592L;
            if (Math.abs(j3 - jE) < Math.abs(j - jE)) {
                j = j3;
            }
        }
        return c(d(j));
    }

    public long c(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            this.c = j;
        } else {
            long j2 = this.f9385a;
            if (j2 != Long.MAX_VALUE) {
                this.b = j2 - j;
            }
            synchronized (this) {
                this.c = j;
                notifyAll();
            }
        }
        return j + this.b;
    }

    public static long e(long j) {
        return (j * 90000) / 1000000;
    }

    public long a() {
        return this.f9385a;
    }
}
