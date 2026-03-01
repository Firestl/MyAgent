package master.flame.danmaku.danmaku.renderer;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.ICacheManager;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;

/* JADX INFO: loaded from: classes3.dex */
public interface IRenderer {
    public static final int CACHE_RENDERING = 1;
    public static final int NOTHING_RENDERING = 0;
    public static final int TEXT_RENDERING = 2;

    public static class Area {
        public int mMaxHeight;
        public int mMaxWidth;
        public final float[] mRefreshRect = new float[4];

        public void reset() {
            set(this.mMaxWidth, this.mMaxHeight, 0.0f, 0.0f);
        }

        public void resizeToMax() {
            set(0.0f, 0.0f, this.mMaxWidth, this.mMaxHeight);
        }

        public void set(float f, float f2, float f3, float f4) {
            float[] fArr = this.mRefreshRect;
            fArr[0] = f;
            fArr[1] = f2;
            fArr[2] = f3;
            fArr[3] = f4;
        }

        public void setEdge(int i, int i2) {
            this.mMaxWidth = i;
            this.mMaxHeight = i2;
        }
    }

    public interface OnDanmakuShownListener {
        void onDanmakuShown(BaseDanmaku baseDanmaku);
    }

    public static class RenderingState {
        public static final int UNKNOWN_TIME = -1;
        public long beginTime;
        public long cacheHitCount;
        public long cacheMissCount;
        public long consumingTime;
        public long endTime;
        public int fbDanmakuCount;
        public int ftDanmakuCount;
        public int indexInScreen;
        public boolean isRunningDanmakus;
        public int l2rDanmakuCount;
        public BaseDanmaku lastDanmaku;
        public int lastTotalDanmakuCount;
        public boolean mIsObtaining;
        public boolean nothingRendered;
        public int r2lDanmakuCount;
        public int specialDanmakuCount;
        public long sysTime;
        public int totalDanmakuCount;
        public int totalSizeInScreen;
        public DanmakuTimer timer = new DanmakuTimer();
        public IDanmakus runningDanmakus = new Danmakus(4);

        public int addCount(int i, int i2) {
            if (i == 1) {
                int i3 = this.r2lDanmakuCount + i2;
                this.r2lDanmakuCount = i3;
                return i3;
            }
            if (i == 4) {
                int i4 = this.fbDanmakuCount + i2;
                this.fbDanmakuCount = i4;
                return i4;
            }
            if (i == 5) {
                int i5 = this.ftDanmakuCount + i2;
                this.ftDanmakuCount = i5;
                return i5;
            }
            if (i == 6) {
                int i6 = this.l2rDanmakuCount + i2;
                this.l2rDanmakuCount = i6;
                return i6;
            }
            if (i != 7) {
                return 0;
            }
            int i7 = this.specialDanmakuCount + i2;
            this.specialDanmakuCount = i7;
            return i7;
        }

        public int addTotalCount(int i) {
            int i2 = this.totalDanmakuCount + i;
            this.totalDanmakuCount = i2;
            return i2;
        }

        public void appendToRunningDanmakus(BaseDanmaku baseDanmaku) {
            if (this.mIsObtaining) {
                return;
            }
            this.runningDanmakus.addItem(baseDanmaku);
        }

        public IDanmakus obtainRunningDanmakus() {
            IDanmakus iDanmakus;
            this.mIsObtaining = true;
            synchronized (this) {
                iDanmakus = this.runningDanmakus;
                this.runningDanmakus = new Danmakus(4);
            }
            this.mIsObtaining = false;
            return iDanmakus;
        }

        public void reset() {
            this.lastTotalDanmakuCount = this.totalDanmakuCount;
            this.totalDanmakuCount = 0;
            this.specialDanmakuCount = 0;
            this.fbDanmakuCount = 0;
            this.ftDanmakuCount = 0;
            this.l2rDanmakuCount = 0;
            this.r2lDanmakuCount = 0;
            this.consumingTime = 0L;
            this.endTime = 0L;
            this.beginTime = 0L;
            this.sysTime = 0L;
            this.nothingRendered = false;
            synchronized (this) {
                this.runningDanmakus.clear();
            }
        }

        public void set(RenderingState renderingState) {
            if (renderingState == null) {
                return;
            }
            this.lastTotalDanmakuCount = renderingState.lastTotalDanmakuCount;
            this.r2lDanmakuCount = renderingState.r2lDanmakuCount;
            this.l2rDanmakuCount = renderingState.l2rDanmakuCount;
            this.ftDanmakuCount = renderingState.ftDanmakuCount;
            this.fbDanmakuCount = renderingState.fbDanmakuCount;
            this.specialDanmakuCount = renderingState.specialDanmakuCount;
            this.totalDanmakuCount = renderingState.totalDanmakuCount;
            this.consumingTime = renderingState.consumingTime;
            this.beginTime = renderingState.beginTime;
            this.endTime = renderingState.endTime;
            this.nothingRendered = renderingState.nothingRendered;
            this.sysTime = renderingState.sysTime;
            this.cacheHitCount = renderingState.cacheHitCount;
            this.cacheMissCount = renderingState.cacheMissCount;
        }
    }

    void alignBottom(boolean z);

    void clear();

    void clearRetainer();

    void draw(IDisplayer iDisplayer, IDanmakus iDanmakus, long j, RenderingState renderingState);

    void release();

    void removeOnDanmakuShownListener();

    void setCacheManager(ICacheManager iCacheManager);

    void setOnDanmakuShownListener(OnDanmakuShownListener onDanmakuShownListener);

    void setVerifierEnabled(boolean z);
}
