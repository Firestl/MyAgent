package master.flame.danmaku.danmaku.model.objectpool;

import master.flame.danmaku.danmaku.model.objectpool.Poolable;

/* JADX INFO: loaded from: classes3.dex */
public interface Pool<T extends Poolable<T>> {
    T acquire();

    void release(T t);
}
