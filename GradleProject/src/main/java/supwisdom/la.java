package supwisdom;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class la<T> implements ka<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f8266a;
    public int b;

    public la(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f8266a = new Object[i];
    }

    public final boolean a(T t) {
        for (int i = 0; i < this.b; i++) {
            if (this.f8266a[i] == t) {
                return true;
            }
        }
        return false;
    }

    @Override // supwisdom.ka
    public T acquire() {
        int i = this.b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f8266a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.b = i - 1;
        return t;
    }

    @Override // supwisdom.ka
    public boolean release(T t) {
        if (a(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i = this.b;
        Object[] objArr = this.f8266a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.b = i + 1;
        return true;
    }
}
