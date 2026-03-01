package master.flame.danmaku.danmaku.model.objectpool;

/* JADX INFO: loaded from: classes3.dex */
public interface Poolable<T> {
    T getNextPoolable();

    boolean isPooled();

    void setNextPoolable(T t);

    void setPooled(boolean z);
}
