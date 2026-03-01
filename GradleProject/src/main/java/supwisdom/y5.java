package supwisdom;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public interface y5<T> {
    void a(T[] tArr, int i);

    T acquire();

    boolean release(T t);
}
