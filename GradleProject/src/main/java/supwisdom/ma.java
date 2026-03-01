package supwisdom;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class ma<T> extends la<T> {
    public final Object c;

    public ma(int i) {
        super(i);
        this.c = new Object();
    }

    @Override // supwisdom.la, supwisdom.ka
    public T acquire() {
        T t;
        synchronized (this.c) {
            t = (T) super.acquire();
        }
        return t;
    }

    @Override // supwisdom.la, supwisdom.ka
    public boolean release(T t) {
        boolean zRelease;
        synchronized (this.c) {
            zRelease = super.release(t);
        }
        return zRelease;
    }
}
