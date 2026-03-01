package master.flame.danmaku.danmaku.parser;

import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseDanmakuParser {
    public DanmakuContext mContext;
    public IDanmakus mDanmakus;
    public IDataSource<?> mDataSource;
    public IDisplayer mDisp;
    public float mDispDensity;
    public int mDispHeight;
    public int mDispWidth;
    public float mScaledDensity;
    public DanmakuTimer mTimer;

    public IDanmakus getDanmakus() {
        IDanmakus iDanmakus = this.mDanmakus;
        if (iDanmakus != null) {
            return iDanmakus;
        }
        this.mContext.mDanmakuFactory.resetDurationsData();
        this.mDanmakus = parse();
        releaseDataSource();
        this.mContext.mDanmakuFactory.updateMaxDanmakuDuration();
        return this.mDanmakus;
    }

    public IDisplayer getDisplayer() {
        return this.mDisp;
    }

    public DanmakuTimer getTimer() {
        return this.mTimer;
    }

    public float getViewportSizeFactor() {
        return 1.0f / (this.mDispDensity - 0.6f);
    }

    public BaseDanmakuParser load(IDataSource<?> iDataSource) {
        this.mDataSource = iDataSource;
        return this;
    }

    public abstract IDanmakus parse();

    public void release() {
        releaseDataSource();
    }

    public void releaseDataSource() {
        IDataSource<?> iDataSource = this.mDataSource;
        if (iDataSource != null) {
            iDataSource.release();
        }
        this.mDataSource = null;
    }

    public BaseDanmakuParser setConfig(DanmakuContext danmakuContext) {
        DanmakuContext danmakuContext2 = this.mContext;
        if (danmakuContext2 != null && danmakuContext2 != danmakuContext) {
            this.mDanmakus = null;
        }
        this.mContext = danmakuContext;
        return this;
    }

    public BaseDanmakuParser setDisplayer(IDisplayer iDisplayer) {
        this.mDisp = iDisplayer;
        this.mDispWidth = iDisplayer.getWidth();
        this.mDispHeight = iDisplayer.getHeight();
        this.mDispDensity = iDisplayer.getDensity();
        this.mScaledDensity = iDisplayer.getScaledDensity();
        this.mContext.mDanmakuFactory.updateViewportState(this.mDispWidth, this.mDispHeight, getViewportSizeFactor());
        this.mContext.mDanmakuFactory.updateMaxDanmakuDuration();
        return this;
    }

    public BaseDanmakuParser setTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
        return this;
    }
}
