package supwisdom;

/* JADX INFO: compiled from: Timestamped.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qz1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f8976a;
    public final T b;

    public qz1(long j, T t) {
        this.b = t;
        this.f8976a = j;
    }

    public long a() {
        return this.f8976a;
    }

    public T b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof qz1)) {
            return false;
        }
        qz1 qz1Var = (qz1) obj;
        if (this.f8976a == qz1Var.f8976a) {
            T t = this.b;
            T t2 = qz1Var.b;
            if (t == t2) {
                return true;
            }
            if (t != null && t.equals(t2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.f8976a;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        T t = this.b;
        return i + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", Long.valueOf(this.f8976a), this.b.toString());
    }
}
