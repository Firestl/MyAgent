package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class j61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f8024a;
    public final int b;
    public final int c;

    public j61(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("start < 0");
        }
        if (i2 < i) {
            throw new IllegalArgumentException("end < start");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("end > bytes.length");
        }
        this.f8024a = bArr;
        this.b = i;
        this.c = i2 - i;
    }

    public void a(byte[] bArr, int i) {
        int length = bArr.length - i;
        int i2 = this.c;
        if (length < i2) {
            throw new IndexOutOfBoundsException("(out.length - offset) < size()");
        }
        System.arraycopy(this.f8024a, this.b, bArr, i, i2);
    }

    public int a() {
        return this.c;
    }

    public j61(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }
}
