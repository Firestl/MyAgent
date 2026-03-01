package supwisdom;

/* JADX INFO: compiled from: OpenHashSet.java */
/* JADX INFO: loaded from: classes3.dex */
public final class by1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f7124a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public T[] f7125e;

    public by1() {
        this(16, 0.75f);
    }

    public static int a(int i) {
        int i2 = i * (-1640531527);
        return i2 ^ (i2 >>> 16);
    }

    public boolean a(T t) {
        T t2;
        T[] tArr = this.f7125e;
        int i = this.b;
        int iA = a(t.hashCode()) & i;
        T t3 = tArr[iA];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                iA = (iA + 1) & i;
                t2 = tArr[iA];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[iA] = t;
        int i2 = this.c + 1;
        this.c = i2;
        if (i2 >= this.d) {
            b();
        }
        return true;
    }

    public boolean b(T t) {
        T t2;
        T[] tArr = this.f7125e;
        int i = this.b;
        int iA = a(t.hashCode()) & i;
        T t3 = tArr[iA];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return a(iA, tArr, i);
        }
        do {
            iA = (iA + 1) & i;
            t2 = tArr[iA];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return a(iA, tArr, i);
    }

    public void c() {
        this.c = 0;
        this.f7125e = (T[]) new Object[0];
    }

    public T[] d() {
        return this.f7125e;
    }

    public by1(int i, float f) {
        this.f7124a = f;
        int iA = ty1.a(i);
        this.b = iA - 1;
        this.d = (int) (f * iA);
        this.f7125e = (T[]) new Object[iA];
    }

    public void b() {
        T[] tArr = this.f7125e;
        int length = tArr.length;
        int i = length << 1;
        int i2 = i - 1;
        T[] tArr2 = (T[]) new Object[i];
        int i3 = this.c;
        while (true) {
            int i4 = i3 - 1;
            if (i3 != 0) {
                do {
                    length--;
                } while (tArr[length] == null);
                int iA = a(tArr[length].hashCode()) & i2;
                if (tArr2[iA] != null) {
                    do {
                        iA = (iA + 1) & i2;
                    } while (tArr2[iA] != null);
                }
                tArr2[iA] = tArr[length];
                i3 = i4;
            } else {
                this.b = i2;
                this.d = (int) (i * this.f7124a);
                this.f7125e = tArr2;
                return;
            }
        }
    }

    public boolean a(int i, T[] tArr, int i2) {
        int i3;
        T t;
        this.c--;
        while (true) {
            int i4 = i + 1;
            while (true) {
                i3 = i4 & i2;
                t = tArr[i3];
                if (t == null) {
                    tArr[i] = null;
                    return true;
                }
                int iA = a(t.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= iA || iA > i3) {
                        break;
                    }
                    i4 = i3 + 1;
                } else if (i < iA || iA <= i3) {
                    i4 = i3 + 1;
                }
            }
            tArr[i] = t;
            i = i3;
        }
    }

    public boolean a() {
        return this.c == 0;
    }
}
