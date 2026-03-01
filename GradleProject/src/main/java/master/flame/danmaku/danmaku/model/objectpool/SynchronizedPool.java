package master.flame.danmaku.danmaku.model.objectpool;

import master.flame.danmaku.danmaku.model.objectpool.Poolable;

/* JADX INFO: loaded from: classes3.dex */
public class SynchronizedPool<T extends Poolable<T>> implements Pool<T> {
    public final Object mLock;
    public final Pool<T> mPool;

    public SynchronizedPool(Pool<T> pool) {
        this.mPool = pool;
        this.mLock = this;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Pool
    public T acquire() {
        T t;
        synchronized (this.mLock) {
            t = (T) this.mPool.acquire();
        }
        return t;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Pool
    public void release(T t) {
        synchronized (this.mLock) {
            this.mPool.release(t);
        }
    }

    public SynchronizedPool(Pool<T> pool, Object obj) {
        this.mPool = pool;
        this.mLock = obj;
    }
}
