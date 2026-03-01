package master.flame.danmaku.danmaku.model.objectpool;

import master.flame.danmaku.danmaku.model.objectpool.Poolable;

/* JADX INFO: loaded from: classes3.dex */
public class FinitePool<T extends Poolable<T>> implements Pool<T> {
    public final boolean mInfinite;
    public final int mLimit;
    public final PoolableManager<T> mManager;
    public int mPoolCount;
    public T mRoot;

    public FinitePool(PoolableManager<T> poolableManager) {
        this.mManager = poolableManager;
        this.mLimit = 0;
        this.mInfinite = true;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Pool
    public T acquire() {
        T t = this.mRoot;
        if (t != null) {
            this.mRoot = (T) t.getNextPoolable();
            this.mPoolCount--;
        } else {
            t = (T) this.mManager.newInstance();
        }
        if (t != null) {
            t.setNextPoolable(null);
            t.setPooled(false);
            this.mManager.onAcquired(t);
        }
        return t;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Pool
    public void release(T t) {
        if (t.isPooled()) {
            System.out.print("[FinitePool] Element is already in pool: " + t);
            return;
        }
        if (this.mInfinite || this.mPoolCount < this.mLimit) {
            this.mPoolCount++;
            t.setNextPoolable(this.mRoot);
            t.setPooled(true);
            this.mRoot = t;
        }
        this.mManager.onReleased(t);
    }

    public FinitePool(PoolableManager<T> poolableManager, int i) {
        if (i > 0) {
            this.mManager = poolableManager;
            this.mLimit = i;
            this.mInfinite = false;
            return;
        }
        throw new IllegalArgumentException("The pool limit must be > 0");
    }
}
