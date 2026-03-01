package master.flame.danmaku.danmaku.renderer.android;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.ICacheManager;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.renderer.Renderer;
import master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuRenderer extends Renderer {
    public ICacheManager mCacheManager;
    public final DanmakuContext mContext;
    public final DanmakusRetainer mDanmakusRetainer;
    public IRenderer.OnDanmakuShownListener mOnDanmakuShownListener;
    public DanmakuTimer mStartTimer;
    public DanmakusRetainer.Verifier mVerifier;
    public final DanmakusRetainer.Verifier verifier = new DanmakusRetainer.Verifier() { // from class: master.flame.danmaku.danmaku.renderer.android.DanmakuRenderer.1
        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.Verifier
        public boolean skipLayout(BaseDanmaku baseDanmaku, float f, int i, boolean z) {
            if (baseDanmaku.priority != 0 || !DanmakuRenderer.this.mContext.mDanmakuFilters.filterSecondary(baseDanmaku, i, 0, DanmakuRenderer.this.mStartTimer, z, DanmakuRenderer.this.mContext)) {
                return false;
            }
            baseDanmaku.setVisibility(false);
            return true;
        }
    };

    public DanmakuRenderer(DanmakuContext danmakuContext) {
        this.mContext = danmakuContext;
        this.mDanmakusRetainer = new DanmakusRetainer(danmakuContext.isAlignBottom());
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void alignBottom(boolean z) {
        DanmakusRetainer danmakusRetainer = this.mDanmakusRetainer;
        if (danmakusRetainer != null) {
            danmakusRetainer.alignBottom(z);
        }
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void clear() {
        clearRetainer();
        this.mContext.mDanmakuFilters.clear();
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void clearRetainer() {
        this.mDanmakusRetainer.clear();
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void draw(IDisplayer iDisplayer, IDanmakus iDanmakus, long j, IRenderer.RenderingState renderingState) {
        this.mStartTimer = renderingState.timer;
        IDanmakuIterator it = iDanmakus.iterator();
        BaseDanmaku next = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            next = it.next();
            if (next.isTimeOut()) {
                iDisplayer.recycle(next);
            } else if (renderingState.isRunningDanmakus || !next.isOffset()) {
                if (!next.hasPassedFilter()) {
                    DanmakuContext danmakuContext = this.mContext;
                    danmakuContext.mDanmakuFilters.filter(next, renderingState.indexInScreen, renderingState.totalSizeInScreen, renderingState.timer, false, danmakuContext);
                }
                if (next.getActualTime() >= j && (next.priority != 0 || !next.isFiltered())) {
                    if (next.isLate()) {
                        IDrawingCache<?> drawingCache = next.getDrawingCache();
                        if (this.mCacheManager != null && (drawingCache == null || drawingCache.get() == null)) {
                            this.mCacheManager.addDanmaku(next);
                        }
                    } else {
                        if (next.getType() == 1) {
                            renderingState.indexInScreen++;
                        }
                        if (!next.isMeasured()) {
                            next.measure(iDisplayer, false);
                        }
                        if (!next.isPrepared()) {
                            next.prepare(iDisplayer, false);
                        }
                        this.mDanmakusRetainer.fix(next, iDisplayer, this.mVerifier);
                        if (next.isShown() && (next.lines != null || next.getBottom() <= iDisplayer.getHeight())) {
                            int iDraw = next.draw(iDisplayer);
                            if (iDraw == 1) {
                                renderingState.cacheHitCount++;
                            } else if (iDraw == 2) {
                                renderingState.cacheMissCount++;
                                ICacheManager iCacheManager = this.mCacheManager;
                                if (iCacheManager != null) {
                                    iCacheManager.addDanmaku(next);
                                }
                            }
                            renderingState.addCount(next.getType(), 1);
                            renderingState.addTotalCount(1);
                            renderingState.appendToRunningDanmakus(next);
                            IRenderer.OnDanmakuShownListener onDanmakuShownListener = this.mOnDanmakuShownListener;
                            if (onDanmakuShownListener != null) {
                                int i = next.firstShownFlag;
                                int i2 = this.mContext.mGlobalFlagValues.FIRST_SHOWN_RESET_FLAG;
                                if (i != i2) {
                                    next.firstShownFlag = i2;
                                    onDanmakuShownListener.onDanmakuShown(next);
                                }
                            }
                        }
                    }
                }
            } else {
                it.remove();
            }
        }
        renderingState.lastDanmaku = next;
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void release() {
        this.mDanmakusRetainer.release();
        this.mContext.mDanmakuFilters.clear();
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void removeOnDanmakuShownListener() {
        this.mOnDanmakuShownListener = null;
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void setCacheManager(ICacheManager iCacheManager) {
        this.mCacheManager = iCacheManager;
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void setOnDanmakuShownListener(IRenderer.OnDanmakuShownListener onDanmakuShownListener) {
        this.mOnDanmakuShownListener = onDanmakuShownListener;
    }

    @Override // master.flame.danmaku.danmaku.renderer.IRenderer
    public void setVerifierEnabled(boolean z) {
        this.mVerifier = z ? this.verifier : null;
    }
}
