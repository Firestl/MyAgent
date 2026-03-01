package master.flame.danmaku.controller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import master.flame.danmaku.controller.IDrawTask;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.ICacheManager;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.DrawingCache;
import master.flame.danmaku.danmaku.model.android.DrawingCachePoolManager;
import master.flame.danmaku.danmaku.model.objectpool.Pool;
import master.flame.danmaku.danmaku.model.objectpool.Pools;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import tv.cjump.jni.NativeBitmapFactory;

/* JADX INFO: loaded from: classes3.dex */
public class CacheManagingDrawTask extends DrawTask {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_CACHE_SCREEN_SIZE = 3;
    public CacheManager mCacheManager;
    public DanmakuTimer mCacheTimer;
    public final Object mDrawingNotify;
    public int mMaxCacheSize;
    public int mRemaininCacheCount;

    public class CacheManager implements ICacheManager {
        public static final byte RESULT_FAILED = 1;
        public static final byte RESULT_FAILED_OVERSIZE = 2;
        public static final byte RESULT_SUCCESS = 0;
        public static final String TAG = "CacheManager";
        public Pool<DrawingCache> mCachePool;
        public DrawingCachePoolManager mCachePoolManager;
        public Danmakus mCaches = new Danmakus();
        public boolean mEndFlag;
        public CacheHandler mHandler;
        public int mMaxSize;
        public int mRealSize;
        public int mScreenSize;
        public HandlerThread mThread;

        public class CacheHandler extends Handler {
            public static final int ADD_DANMAKKU = 2;
            public static final int BIND_CACHE = 18;
            public static final int BUILD_CACHES = 3;
            public static final int CLEAR_ALL_CACHES = 7;
            public static final int CLEAR_OUTSIDE_CACHES = 8;
            public static final int CLEAR_OUTSIDE_CACHES_AND_RESET = 9;
            public static final int CLEAR_TIMEOUT_CACHES = 4;
            public static final int DISABLE_CANCEL_FLAG = 19;
            public static final int DISPATCH_ACTIONS = 16;
            public static final int PREPARE = 1;
            public static final int QUIT = 6;
            public static final int REBUILD_CACHE = 17;
            public static final int SEEK = 5;
            public boolean mCancelFlag;
            public boolean mIsPlayerPause;
            public boolean mPause;
            public boolean mSeekedFlag;

            public CacheHandler(Looper looper) {
                super(looper);
            }

            private final void addDanmakuAndBuildCache(BaseDanmaku baseDanmaku) {
                if (baseDanmaku.isTimeOut()) {
                    return;
                }
                if (baseDanmaku.getActualTime() <= CacheManagingDrawTask.this.mCacheTimer.currMillisecond + CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION || baseDanmaku.isLive) {
                    if (baseDanmaku.priority == 0 && baseDanmaku.isFiltered()) {
                        return;
                    }
                    IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
                    if (drawingCache == null || drawingCache.get() == null) {
                        buildCache(baseDanmaku, true);
                    }
                }
            }

            private byte buildCache(BaseDanmaku baseDanmaku, boolean z) {
                if (!baseDanmaku.isMeasured()) {
                    baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                }
                DrawingCache drawingCache = null;
                try {
                    BaseDanmaku baseDanmakuFindReuseableCache = CacheManager.this.findReuseableCache(baseDanmaku, true, 20);
                    DrawingCache drawingCache2 = baseDanmakuFindReuseableCache != null ? (DrawingCache) baseDanmakuFindReuseableCache.cache : null;
                    try {
                        if (drawingCache2 != null) {
                            drawingCache2.increaseReference();
                            baseDanmaku.cache = drawingCache2;
                            CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, 0, z);
                            return (byte) 0;
                        }
                        BaseDanmaku baseDanmakuFindReuseableCache2 = CacheManager.this.findReuseableCache(baseDanmaku, false, 50);
                        if (baseDanmakuFindReuseableCache2 != null) {
                            drawingCache2 = (DrawingCache) baseDanmakuFindReuseableCache2.cache;
                        }
                        if (drawingCache2 != null) {
                            baseDanmakuFindReuseableCache2.cache = null;
                            baseDanmaku.cache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, drawingCache2);
                            CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, 0, z);
                            return (byte) 0;
                        }
                        if (!z) {
                            if (CacheManager.this.mRealSize + DanmakuUtils.getCacheSize((int) baseDanmaku.paintWidth, (int) baseDanmaku.paintHeight) > CacheManager.this.mMaxSize) {
                                return (byte) 1;
                            }
                        }
                        DrawingCache drawingCacheBuildDanmakuDrawingCache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, (DrawingCache) CacheManager.this.mCachePool.acquire());
                        baseDanmaku.cache = drawingCacheBuildDanmakuDrawingCache;
                        boolean zPush = CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, CacheManager.this.sizeOf(baseDanmaku), z);
                        if (!zPush) {
                            releaseDanmakuCache(baseDanmaku, drawingCacheBuildDanmakuDrawingCache);
                        }
                        return !zPush ? (byte) 1 : (byte) 0;
                    } catch (Exception unused) {
                        drawingCache = drawingCache2;
                        releaseDanmakuCache(baseDanmaku, drawingCache);
                        return (byte) 1;
                    } catch (OutOfMemoryError unused2) {
                        drawingCache = drawingCache2;
                        releaseDanmakuCache(baseDanmaku, drawingCache);
                        return (byte) 1;
                    }
                } catch (Exception unused3) {
                } catch (OutOfMemoryError unused4) {
                }
            }

            private long dispatchAction() {
                long j = CacheManagingDrawTask.this.mCacheTimer.currMillisecond;
                CacheManager cacheManager = CacheManager.this;
                CacheManagingDrawTask cacheManagingDrawTask = CacheManagingDrawTask.this;
                if (j <= cacheManagingDrawTask.mTimer.currMillisecond - cacheManagingDrawTask.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                    cacheManager.evictAllNotInScreen();
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    sendEmptyMessage(3);
                    return 0L;
                }
                float poolPercent = cacheManager.getPoolPercent();
                BaseDanmaku baseDanmakuFirst = CacheManager.this.mCaches.first();
                long actualTime = baseDanmakuFirst != null ? baseDanmakuFirst.getActualTime() - CacheManagingDrawTask.this.mTimer.currMillisecond : 0L;
                CacheManagingDrawTask cacheManagingDrawTask2 = CacheManagingDrawTask.this;
                long j2 = cacheManagingDrawTask2.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION;
                long j3 = 2 * j2;
                if (poolPercent < 0.6f && actualTime > j2) {
                    cacheManagingDrawTask2.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    removeMessages(3);
                    sendEmptyMessage(3);
                    return 0L;
                }
                if (poolPercent > 0.4f && actualTime < (-j3)) {
                    removeMessages(4);
                    sendEmptyMessage(4);
                    return 0L;
                }
                if (poolPercent >= 0.9f) {
                    return 0L;
                }
                long j4 = CacheManagingDrawTask.this.mCacheTimer.currMillisecond - CacheManagingDrawTask.this.mTimer.currMillisecond;
                if (baseDanmakuFirst != null && baseDanmakuFirst.isTimeOut()) {
                    CacheManagingDrawTask cacheManagingDrawTask3 = CacheManagingDrawTask.this;
                    if (j4 < (-cacheManagingDrawTask3.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION)) {
                        cacheManagingDrawTask3.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                        sendEmptyMessage(8);
                        sendEmptyMessage(3);
                        return 0L;
                    }
                }
                if (j4 > j3) {
                    return 0L;
                }
                removeMessages(3);
                sendEmptyMessage(3);
                return 0L;
            }

            private void preMeasure() {
                IDanmakus iDanmakusSubnew;
                try {
                    long j = CacheManagingDrawTask.this.mTimer.currMillisecond;
                    iDanmakusSubnew = CacheManagingDrawTask.this.danmakuList.subnew(j - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION, (CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * 2) + j);
                } catch (Exception unused) {
                    iDanmakusSubnew = null;
                }
                if (iDanmakusSubnew == null || iDanmakusSubnew.isEmpty()) {
                    return;
                }
                IDanmakuIterator it = iDanmakusSubnew.iterator();
                while (it.hasNext() && !this.mPause) {
                    BaseDanmaku next = it.next();
                    if (!next.hasPassedFilter()) {
                        DanmakuContext danmakuContext = CacheManagingDrawTask.this.mContext;
                        danmakuContext.mDanmakuFilters.filter(next, 0, 0, null, true, danmakuContext);
                    }
                    if (!next.isFiltered()) {
                        if (!next.isMeasured()) {
                            next.measure(CacheManagingDrawTask.this.mDisp, true);
                        }
                        if (!next.isPrepared()) {
                            next.prepare(CacheManagingDrawTask.this.mDisp, true);
                        }
                    }
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:110:0x0188 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:58:0x0115  */
            /* JADX WARN: Removed duplicated region for block: B:83:0x016b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            private long prepareCaches(boolean r31) {
                /*
                    Method dump skipped, instruction units count: 452
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.CacheHandler.prepareCaches(boolean):long");
            }

            private void releaseDanmakuCache(BaseDanmaku baseDanmaku, DrawingCache drawingCache) {
                if (drawingCache == null) {
                    drawingCache = (DrawingCache) baseDanmaku.cache;
                }
                baseDanmaku.cache = null;
                if (drawingCache == null) {
                    return;
                }
                drawingCache.destroy();
                CacheManager.this.mCachePool.release(drawingCache);
            }

            public void begin() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
            }

            public boolean createCache(BaseDanmaku baseDanmaku) {
                DrawingCache drawingCacheBuildDanmakuDrawingCache;
                if (!baseDanmaku.isMeasured()) {
                    baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                }
                try {
                    drawingCacheBuildDanmakuDrawingCache = (DrawingCache) CacheManager.this.mCachePool.acquire();
                    try {
                        drawingCacheBuildDanmakuDrawingCache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, drawingCacheBuildDanmakuDrawingCache);
                        baseDanmaku.cache = drawingCacheBuildDanmakuDrawingCache;
                        return true;
                    } catch (Exception unused) {
                        if (drawingCacheBuildDanmakuDrawingCache != null) {
                            CacheManager.this.mCachePool.release(drawingCacheBuildDanmakuDrawingCache);
                        }
                        baseDanmaku.cache = null;
                        return false;
                    } catch (OutOfMemoryError unused2) {
                        if (drawingCacheBuildDanmakuDrawingCache != null) {
                            CacheManager.this.mCachePool.release(drawingCacheBuildDanmakuDrawingCache);
                        }
                        baseDanmaku.cache = null;
                        return false;
                    }
                } catch (Exception unused3) {
                    drawingCacheBuildDanmakuDrawingCache = null;
                } catch (OutOfMemoryError unused4) {
                    drawingCacheBuildDanmakuDrawingCache = null;
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:46:0x013d  */
            @Override // android.os.Handler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void handleMessage(android.os.Message r9) {
                /*
                    Method dump skipped, instruction units count: 484
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.CacheHandler.handleMessage(android.os.Message):void");
            }

            public boolean isPause() {
                return this.mPause;
            }

            public void onPlayStateChanged(boolean z) {
                this.mIsPlayerPause = !z;
            }

            public void pause() {
                this.mPause = true;
                removeCallbacksAndMessages(null);
                sendEmptyMessage(6);
            }

            public void requestBuildCacheAndDraw(long j) {
                removeMessages(3);
                this.mSeekedFlag = true;
                sendEmptyMessage(19);
                CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond + j);
                sendEmptyMessage(3);
            }

            public void requestCancelCaching() {
                this.mCancelFlag = true;
            }

            public void resume() {
                sendEmptyMessage(19);
                this.mPause = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
            }
        }

        public CacheManager(int i, int i2) {
            DrawingCachePoolManager drawingCachePoolManager = new DrawingCachePoolManager();
            this.mCachePoolManager = drawingCachePoolManager;
            this.mCachePool = Pools.finitePool(drawingCachePoolManager, 800);
            this.mScreenSize = 3;
            this.mEndFlag = false;
            this.mRealSize = 0;
            this.mMaxSize = i;
            this.mScreenSize = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long clearCache(BaseDanmaku baseDanmaku) {
            IDrawingCache<?> iDrawingCache = baseDanmaku.cache;
            if (iDrawingCache == null) {
                return 0L;
            }
            if (iDrawingCache.hasReferences()) {
                iDrawingCache.decreaseReference();
                baseDanmaku.cache = null;
                return 0L;
            }
            long jSizeOf = sizeOf(baseDanmaku);
            iDrawingCache.destroy();
            baseDanmaku.cache = null;
            return jSizeOf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCachePool() {
            while (true) {
                DrawingCache drawingCache = (DrawingCache) this.mCachePool.acquire();
                if (drawingCache == null) {
                    return;
                } else {
                    drawingCache.destroy();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeOutCaches() {
            clearTimeOutCaches(CacheManagingDrawTask.this.mTimer.currMillisecond);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAll() {
            Danmakus danmakus = this.mCaches;
            if (danmakus != null) {
                IDanmakuIterator it = danmakus.iterator();
                while (it.hasNext()) {
                    entryRemoved(true, it.next(), null);
                }
                this.mCaches.clear();
            }
            this.mRealSize = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAllNotInScreen() {
            evictAllNotInScreen(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BaseDanmaku findReuseableCache(BaseDanmaku baseDanmaku, boolean z, int i) {
            IDanmakuIterator it = this.mCaches.iterator();
            int i2 = 0;
            int slopPixel = !z ? CacheManagingDrawTask.this.mDisp.getSlopPixel() * 2 : 0;
            while (it.hasNext()) {
                int i3 = i2 + 1;
                if (i2 >= i) {
                    return null;
                }
                BaseDanmaku next = it.next();
                IDrawingCache<?> drawingCache = next.getDrawingCache();
                if (drawingCache != null && drawingCache.get() != null) {
                    if (next.paintWidth == baseDanmaku.paintWidth && next.paintHeight == baseDanmaku.paintHeight && next.underlineColor == baseDanmaku.underlineColor && next.borderColor == baseDanmaku.borderColor && next.textColor == baseDanmaku.textColor && next.text.equals(baseDanmaku.text) && next.tag == baseDanmaku.tag) {
                        return next;
                    }
                    if (z) {
                        continue;
                    } else {
                        if (!next.isTimeOut()) {
                            return null;
                        }
                        if (drawingCache.hasReferences()) {
                            continue;
                        } else {
                            float fWidth = drawingCache.width() - baseDanmaku.paintWidth;
                            float fHeight = drawingCache.height() - baseDanmaku.paintHeight;
                            if (fWidth >= 0.0f) {
                                float f = slopPixel;
                                if (fWidth <= f && fHeight >= 0.0f && fHeight <= f) {
                                    return next;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                i2 = i3;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean push(BaseDanmaku baseDanmaku, int i, boolean z) {
            while (true) {
                if (this.mRealSize + i <= this.mMaxSize || this.mCaches.size() <= 0) {
                    break;
                }
                BaseDanmaku baseDanmakuFirst = this.mCaches.first();
                if (baseDanmakuFirst.isTimeOut()) {
                    entryRemoved(false, baseDanmakuFirst, baseDanmaku);
                    this.mCaches.removeItem(baseDanmakuFirst);
                } else if (!z) {
                    return false;
                }
            }
            this.mCaches.addItem(baseDanmaku);
            this.mRealSize += i;
            return true;
        }

        @Override // master.flame.danmaku.danmaku.model.ICacheManager
        public void addDanmaku(BaseDanmaku baseDanmaku) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                if (!baseDanmaku.isLive) {
                    cacheHandler.obtainMessage(2, baseDanmaku).sendToTarget();
                } else if (!baseDanmaku.forceBuildCacheInSameThread) {
                    cacheHandler.obtainMessage(18, baseDanmaku).sendToTarget();
                } else {
                    if (baseDanmaku.isTimeOut()) {
                        return;
                    }
                    this.mHandler.createCache(baseDanmaku);
                }
            }
        }

        public void begin() {
            this.mEndFlag = false;
            if (this.mThread == null) {
                HandlerThread handlerThread = new HandlerThread("DFM Cache-Building Thread");
                this.mThread = handlerThread;
                handlerThread.start();
            }
            if (this.mHandler == null) {
                this.mHandler = new CacheHandler(this.mThread.getLooper());
            }
            this.mHandler.begin();
        }

        public void end() {
            this.mEndFlag = true;
            synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                CacheManagingDrawTask.this.mDrawingNotify.notifyAll();
            }
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.pause();
                this.mHandler = null;
            }
            HandlerThread handlerThread = this.mThread;
            if (handlerThread != null) {
                try {
                    handlerThread.join();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                this.mThread.quit();
                this.mThread = null;
            }
        }

        public void entryRemoved(boolean z, BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
            if (drawingCache != null) {
                long jClearCache = clearCache(baseDanmaku);
                if (baseDanmaku.isTimeOut()) {
                    CacheManagingDrawTask.this.mContext.getDisplayer().getCacheStuffer().releaseResource(baseDanmaku);
                }
                if (jClearCache <= 0) {
                    return;
                }
                this.mRealSize = (int) (((long) this.mRealSize) - jClearCache);
                this.mCachePool.release((DrawingCache) drawingCache);
            }
        }

        public long getFirstCacheTime() {
            BaseDanmaku baseDanmakuFirst;
            Danmakus danmakus = this.mCaches;
            if (danmakus == null || danmakus.size() <= 0 || (baseDanmakuFirst = this.mCaches.first()) == null) {
                return 0L;
            }
            return baseDanmakuFirst.getActualTime();
        }

        public float getPoolPercent() {
            int i = this.mMaxSize;
            if (i == 0) {
                return 0.0f;
            }
            return this.mRealSize / i;
        }

        public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.requestCancelCaching();
                this.mHandler.obtainMessage(17, baseDanmaku).sendToTarget();
            }
        }

        public boolean isPoolFull() {
            return this.mRealSize + 5120 >= this.mMaxSize;
        }

        public void onPlayStateChanged(int i) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.onPlayStateChanged(i == 1);
            }
        }

        public void post(Runnable runnable) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.post(runnable);
        }

        public void requestBuild(long j) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.requestBuildCacheAndDraw(j);
            }
        }

        public void requestClearAll() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(3);
            this.mHandler.removeMessages(19);
            this.mHandler.requestCancelCaching();
            this.mHandler.removeMessages(7);
            this.mHandler.sendEmptyMessage(7);
        }

        public void requestClearTimeout() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(4);
            this.mHandler.sendEmptyMessage(4);
        }

        public void requestClearUnused() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(9);
            this.mHandler.sendEmptyMessage(9);
        }

        public void resume() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.resume();
            } else {
                begin();
            }
        }

        public void seek(long j) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.requestCancelCaching();
            this.mHandler.removeMessages(3);
            this.mHandler.obtainMessage(5, Long.valueOf(j)).sendToTarget();
        }

        public int sizeOf(BaseDanmaku baseDanmaku) {
            IDrawingCache<?> iDrawingCache = baseDanmaku.cache;
            if (iDrawingCache == null || iDrawingCache.hasReferences()) {
                return 0;
            }
            return baseDanmaku.cache.size();
        }

        private void clearTimeOutCaches(long j) {
            IDanmakuIterator it = this.mCaches.iterator();
            while (it.hasNext() && !this.mEndFlag) {
                BaseDanmaku next = it.next();
                if (!next.isTimeOut()) {
                    return;
                }
                synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                    try {
                        CacheManagingDrawTask.this.mDrawingNotify.wait(30L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                entryRemoved(false, next, null);
                it.remove();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAllNotInScreen(boolean z) {
            Danmakus danmakus = this.mCaches;
            if (danmakus != null) {
                IDanmakuIterator it = danmakus.iterator();
                while (it.hasNext()) {
                    BaseDanmaku next = it.next();
                    IDrawingCache<?> iDrawingCache = next.cache;
                    boolean z2 = iDrawingCache != null && iDrawingCache.hasReferences();
                    if (z && z2) {
                        if (iDrawingCache.get() != null) {
                            this.mRealSize -= iDrawingCache.size();
                            iDrawingCache.destroy();
                        }
                        entryRemoved(true, next, null);
                        it.remove();
                    } else if (next.isOutside()) {
                        entryRemoved(true, next, null);
                        it.remove();
                    }
                }
            }
            this.mRealSize = 0;
        }
    }

    public CacheManagingDrawTask(DanmakuTimer danmakuTimer, DanmakuContext danmakuContext, IDrawTask.TaskListener taskListener, int i) {
        super(danmakuTimer, danmakuContext, taskListener);
        this.mMaxCacheSize = 2;
        this.mDrawingNotify = new Object();
        NativeBitmapFactory.loadLibs();
        this.mMaxCacheSize = i;
        if (NativeBitmapFactory.isInNativeAlloc()) {
            this.mMaxCacheSize = i * 2;
        }
        CacheManager cacheManager = new CacheManager(i, 3);
        this.mCacheManager = cacheManager;
        this.mRenderer.setCacheManager(cacheManager);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void addDanmaku(BaseDanmaku baseDanmaku) {
        super.addDanmaku(baseDanmaku);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            return;
        }
        cacheManager.addDanmaku(baseDanmaku);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public IRenderer.RenderingState draw(AbsDisplayer absDisplayer) {
        CacheManager cacheManager;
        IRenderer.RenderingState renderingStateDraw = super.draw(absDisplayer);
        synchronized (this.mDrawingNotify) {
            this.mDrawingNotify.notify();
        }
        if (renderingStateDraw != null && (cacheManager = this.mCacheManager) != null && renderingStateDraw.totalDanmakuCount - renderingStateDraw.lastTotalDanmakuCount < -20) {
            cacheManager.requestClearTimeout();
            this.mCacheManager.requestBuild(-this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
        }
        return renderingStateDraw;
    }

    @Override // master.flame.danmaku.controller.DrawTask
    public void initTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
        DanmakuTimer danmakuTimer2 = new DanmakuTimer();
        this.mCacheTimer = danmakuTimer2;
        danmakuTimer2.update(danmakuTimer.currMillisecond);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            super.invalidateDanmaku(baseDanmaku, z);
        } else {
            cacheManager.invalidateDanmaku(baseDanmaku, z);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask
    public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        CacheManager cacheManager;
        CacheManager cacheManager2;
        if (!super.handleOnDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr)) {
            if (DanmakuContext.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
                this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
                requestClear();
            } else if (danmakuConfigTag.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && objArr[0] != null && ((!(objArr[0] instanceof Boolean) || ((Boolean) objArr[0]).booleanValue()) && (cacheManager2 = this.mCacheManager) != null)) {
                    cacheManager2.requestBuild(0L);
                }
                requestClear();
            } else if (DanmakuContext.DanmakuConfigTag.TRANSPARENCY.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.DANMAKU_STYLE.equals(danmakuConfigTag)) {
                if (DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag)) {
                    this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
                }
                CacheManager cacheManager3 = this.mCacheManager;
                if (cacheManager3 != null) {
                    cacheManager3.requestClearAll();
                    this.mCacheManager.requestBuild(-this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                }
            } else {
                CacheManager cacheManager4 = this.mCacheManager;
                if (cacheManager4 != null) {
                    cacheManager4.requestClearUnused();
                    this.mCacheManager.requestBuild(0L);
                }
            }
        }
        if (this.mTaskListener == null || (cacheManager = this.mCacheManager) == null) {
            return true;
        }
        cacheManager.post(new Runnable() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.1
            @Override // java.lang.Runnable
            public void run() {
                CacheManagingDrawTask.this.mTaskListener.onDanmakuConfigChanged();
            }
        });
        return true;
    }

    @Override // master.flame.danmaku.controller.DrawTask
    public void onDanmakuRemoved(BaseDanmaku baseDanmaku) {
        super.onDanmakuRemoved(baseDanmaku);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            int i = this.mRemaininCacheCount + 1;
            this.mRemaininCacheCount = i;
            if (i > 5) {
                cacheManager.requestClearTimeout();
                this.mRemaininCacheCount = 0;
                return;
            }
            return;
        }
        IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
        if (drawingCache != null) {
            if (drawingCache.hasReferences()) {
                drawingCache.decreaseReference();
            } else {
                drawingCache.destroy();
            }
            baseDanmaku.cache = null;
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void onPlayStateChanged(int i) {
        super.onPlayStateChanged(i);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.onPlayStateChanged(i);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void prepare() {
        loadDanmakus(this.mParser);
        this.mCacheManager.begin();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void quit() {
        super.quit();
        reset();
        this.mRenderer.setCacheManager(null);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.end();
            this.mCacheManager = null;
        }
        NativeBitmapFactory.releaseLibs();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void removeAllDanmakus(boolean z) {
        super.removeAllDanmakus(z);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.requestClearAll();
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void requestSync(long j, long j2, long j3) {
        super.requestSync(j, j2, j3);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.seek(j2);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void seek(long j) {
        super.seek(j);
        if (this.mCacheManager == null) {
            start();
        }
        this.mCacheManager.seek(j);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void start() {
        super.start();
        NativeBitmapFactory.loadLibs();
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.resume();
            return;
        }
        CacheManager cacheManager2 = new CacheManager(this.mMaxCacheSize, 3);
        this.mCacheManager = cacheManager2;
        cacheManager2.begin();
        this.mRenderer.setCacheManager(this.mCacheManager);
    }
}
