package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: LongArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class j80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8032a;
    public long[] b;

    public j80() {
        this(32);
    }

    public void a(long j) {
        int i = this.f8032a;
        long[] jArr = this.b;
        if (i == jArr.length) {
            this.b = Arrays.copyOf(jArr, i * 2);
        }
        long[] jArr2 = this.b;
        int i2 = this.f8032a;
        this.f8032a = i2 + 1;
        jArr2[i2] = j;
    }

    public long[] b() {
        return Arrays.copyOf(this.b, this.f8032a);
    }

    public j80(int i) {
        this.b = new long[i];
    }

    public long a(int i) {
        if (i >= 0 && i < this.f8032a) {
            return this.b[i];
        }
        throw new IndexOutOfBoundsException("Invalid index " + i + ", size is " + this.f8032a);
    }

    public int a() {
        return this.f8032a;
    }
}
