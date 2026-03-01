package master.flame.danmaku.danmaku.model.android;

import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.objectpool.Poolable;

/* JADX INFO: loaded from: classes3.dex */
public class DrawingCache implements IDrawingCache<DrawingCacheHolder>, Poolable<DrawingCache> {
    public boolean mIsPooled;
    public DrawingCache mNextElement;
    public int mSize = 0;
    public int referenceCount = 0;
    public final DrawingCacheHolder mHolder = new DrawingCacheHolder();

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public void build(int i, int i2, int i3, boolean z) {
        this.mHolder.buildCache(i, i2, i3, z);
        this.mSize = this.mHolder.bitmap.getRowBytes() * this.mHolder.bitmap.getHeight();
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public synchronized void decreaseReference() {
        this.referenceCount--;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public void destroy() {
        DrawingCacheHolder drawingCacheHolder = this.mHolder;
        if (drawingCacheHolder != null) {
            drawingCacheHolder.recycle();
        }
        this.mSize = 0;
        this.referenceCount = 0;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public void erase() {
        this.mHolder.erase();
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public synchronized boolean hasReferences() {
        return this.referenceCount > 0;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public int height() {
        return this.mHolder.height;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public synchronized void increaseReference() {
        this.referenceCount++;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Poolable
    public boolean isPooled() {
        return this.mIsPooled;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Poolable
    public void setPooled(boolean z) {
        this.mIsPooled = z;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public int size() {
        return this.mSize;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public int width() {
        return this.mHolder.width;
    }

    @Override // master.flame.danmaku.danmaku.model.IDrawingCache
    public DrawingCacheHolder get() {
        DrawingCacheHolder drawingCacheHolder = this.mHolder;
        if (drawingCacheHolder.bitmap == null) {
            return null;
        }
        return drawingCacheHolder;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Poolable
    public DrawingCache getNextPoolable() {
        return this.mNextElement;
    }

    @Override // master.flame.danmaku.danmaku.model.objectpool.Poolable
    public void setNextPoolable(DrawingCache drawingCache) {
        this.mNextElement = drawingCache;
    }
}
