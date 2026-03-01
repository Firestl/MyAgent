package master.flame.danmaku.controller;

import master.flame.danmaku.controller.IDrawTask;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.renderer.android.DanmakuRenderer;
import master.flame.danmaku.danmaku.util.SystemClock;

/* JADX INFO: loaded from: classes3.dex */
public class DrawTask implements IDrawTask {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public boolean clearRetainerFlag;
    public IDanmakus danmakuList;
    public final DanmakuContext mContext;
    public final AbsDisplayer mDisp;
    public boolean mIsHidden;
    public long mLastBeginMills;
    public BaseDanmaku mLastDanmaku;
    public long mLastEndMills;
    public BaseDanmakuParser mParser;
    public int mPlayState;
    public boolean mReadyState;
    public final IRenderer mRenderer;
    public IDanmakus mRunningDanmakus;
    public IDrawTask.TaskListener mTaskListener;
    public DanmakuTimer mTimer;
    public IDanmakus danmakus = new Danmakus(4);
    public long mStartRenderTime = 0;
    public final IRenderer.RenderingState mRenderingState = new IRenderer.RenderingState();
    public Danmakus mLiveDanmakus = new Danmakus(4);
    public DanmakuContext.ConfigChangedCallback mConfigChangedCallback = new DanmakuContext.ConfigChangedCallback() { // from class: master.flame.danmaku.controller.DrawTask.1
        @Override // master.flame.danmaku.danmaku.model.android.DanmakuContext.ConfigChangedCallback
        public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
            return DrawTask.this.onDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr);
        }
    };

    public DrawTask(DanmakuTimer danmakuTimer, DanmakuContext danmakuContext, IDrawTask.TaskListener taskListener) {
        if (danmakuContext == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = danmakuContext;
        this.mDisp = danmakuContext.getDisplayer();
        this.mTaskListener = taskListener;
        DanmakuRenderer danmakuRenderer = new DanmakuRenderer(danmakuContext);
        this.mRenderer = danmakuRenderer;
        danmakuRenderer.setOnDanmakuShownListener(new IRenderer.OnDanmakuShownListener() { // from class: master.flame.danmaku.controller.DrawTask.2
            @Override // master.flame.danmaku.danmaku.renderer.IRenderer.OnDanmakuShownListener
            public void onDanmakuShown(BaseDanmaku baseDanmaku) {
                IDrawTask.TaskListener taskListener2 = DrawTask.this.mTaskListener;
                if (taskListener2 != null) {
                    taskListener2.onDanmakuShown(baseDanmaku);
                }
            }
        });
        this.mRenderer.setVerifierEnabled(this.mContext.isPreventOverlappingEnabled() || this.mContext.isMaxLinesLimited());
        initTimer(danmakuTimer);
        Boolean boolValueOf = Boolean.valueOf(this.mContext.isDuplicateMergingEnabled());
        if (boolValueOf != null) {
            if (boolValueOf.booleanValue()) {
                this.mContext.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
            } else {
                this.mContext.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
            }
        }
    }

    private void beginTracing(IRenderer.RenderingState renderingState, IDanmakus iDanmakus, IDanmakus iDanmakus2) {
        renderingState.reset();
        renderingState.timer.update(SystemClock.uptimeMillis());
        renderingState.indexInScreen = 0;
        renderingState.totalSizeInScreen = (iDanmakus != null ? iDanmakus.size() : 0) + (iDanmakus2 != null ? iDanmakus2.size() : 0);
    }

    private void endTracing(IRenderer.RenderingState renderingState) {
        boolean z = renderingState.totalDanmakuCount == 0;
        renderingState.nothingRendered = z;
        if (z) {
            renderingState.beginTime = -1L;
        }
        BaseDanmaku baseDanmaku = renderingState.lastDanmaku;
        renderingState.lastDanmaku = null;
        renderingState.endTime = baseDanmaku != null ? baseDanmaku.getActualTime() : -1L;
        renderingState.consumingTime = renderingState.timer.update(SystemClock.uptimeMillis());
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void addDanmaku(BaseDanmaku baseDanmaku) {
        boolean zAddItem;
        boolean zAddItem2;
        if (this.danmakuList == null) {
            return;
        }
        if (baseDanmaku.isLive) {
            this.mLiveDanmakus.addItem(baseDanmaku);
            removeUnusedLiveDanmakusIn(10);
        }
        baseDanmaku.index = this.danmakuList.size();
        boolean z = true;
        if (this.mLastBeginMills <= baseDanmaku.getActualTime() && baseDanmaku.getActualTime() <= this.mLastEndMills) {
            synchronized (this.danmakus) {
                zAddItem2 = this.danmakus.addItem(baseDanmaku);
            }
            z = zAddItem2;
        } else if (baseDanmaku.isLive) {
            z = false;
        }
        synchronized (this.danmakuList) {
            zAddItem = this.danmakuList.addItem(baseDanmaku);
        }
        if (!z) {
            this.mLastEndMills = 0L;
            this.mLastBeginMills = 0L;
        }
        if (zAddItem && this.mTaskListener != null) {
            this.mTaskListener.onDanmakuAdd(baseDanmaku);
        }
        if (this.mLastDanmaku == null || (baseDanmaku != null && this.mLastDanmaku != null && baseDanmaku.getActualTime() > this.mLastDanmaku.getActualTime())) {
            this.mLastDanmaku = baseDanmaku;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void clearDanmakusOnScreen(long j) {
        reset();
        this.mContext.mGlobalFlagValues.updateVisibleFlag();
        this.mContext.mGlobalFlagValues.updateFirstShownFlag();
        this.mStartRenderTime = j;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized IRenderer.RenderingState draw(AbsDisplayer absDisplayer) {
        return drawDanmakus(absDisplayer, this.mTimer);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState drawDanmakus(master.flame.danmaku.danmaku.model.AbsDisplayer r22, master.flame.danmaku.danmaku.model.DanmakuTimer r23) {
        /*
            r21 = this;
            r0 = r21
            boolean r1 = r0.clearRetainerFlag
            r2 = 0
            if (r1 == 0) goto Le
            master.flame.danmaku.danmaku.renderer.IRenderer r1 = r0.mRenderer
            r1.clearRetainer()
            r0.clearRetainerFlag = r2
        Le:
            master.flame.danmaku.danmaku.model.IDanmakus r1 = r0.danmakuList
            r3 = 0
            if (r1 == 0) goto Lc0
            java.lang.Object r1 = r22.getExtraData()
            android.graphics.Canvas r1 = (android.graphics.Canvas) r1
            master.flame.danmaku.controller.DrawHelper.clearCanvas(r1)
            boolean r1 = r0.mIsHidden
            if (r1 == 0) goto L23
            master.flame.danmaku.danmaku.renderer.IRenderer$RenderingState r1 = r0.mRenderingState
            return r1
        L23:
            master.flame.danmaku.danmaku.renderer.IRenderer$RenderingState r1 = r0.mRenderingState
            r4 = r23
            long r4 = r4.currMillisecond
            master.flame.danmaku.danmaku.model.android.DanmakuContext r6 = r0.mContext
            master.flame.danmaku.danmaku.model.android.DanmakuFactory r6 = r6.mDanmakuFactory
            long r6 = r6.MAX_DANMAKU_DURATION
            long r8 = r4 - r6
            r10 = 100
            long r8 = r8 - r10
            long r6 = r6 + r4
            master.flame.danmaku.danmaku.model.IDanmakus r10 = r0.danmakus
            long r11 = r0.mLastBeginMills
            int r13 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r13 > 0) goto L46
            long r13 = r0.mLastEndMills
            int r15 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r15 <= 0) goto L44
            goto L46
        L44:
            r6 = r10
            goto L57
        L46:
            master.flame.danmaku.danmaku.model.IDanmakus r4 = r0.danmakuList
            master.flame.danmaku.danmaku.model.IDanmakus r4 = r4.sub(r8, r6)
            if (r4 == 0) goto L50
            r0.danmakus = r4
        L50:
            r0.mLastBeginMills = r8
            r0.mLastEndMills = r6
            r13 = r6
            r11 = r8
            r6 = r4
        L57:
            master.flame.danmaku.danmaku.model.IDanmakus r4 = r0.mRunningDanmakus
            r0.beginTracing(r1, r4, r6)
            r5 = 1
            if (r4 == 0) goto L76
            boolean r7 = r4.isEmpty()
            if (r7 != 0) goto L76
            master.flame.danmaku.danmaku.renderer.IRenderer$RenderingState r7 = r0.mRenderingState
            r7.isRunningDanmakus = r5
            master.flame.danmaku.danmaku.renderer.IRenderer r15 = r0.mRenderer
            r18 = 0
            r16 = r22
            r17 = r4
            r20 = r7
            r15.draw(r16, r17, r18, r20)
        L76:
            master.flame.danmaku.danmaku.renderer.IRenderer$RenderingState r4 = r0.mRenderingState
            r4.isRunningDanmakus = r2
            if (r6 == 0) goto Lb9
            boolean r2 = r6.isEmpty()
            if (r2 != 0) goto Lb9
            master.flame.danmaku.danmaku.renderer.IRenderer r4 = r0.mRenderer
            master.flame.danmaku.danmaku.model.AbsDisplayer r5 = r0.mDisp
            long r7 = r0.mStartRenderTime
            r9 = r1
            r4.draw(r5, r6, r7, r9)
            r0.endTracing(r1)
            boolean r2 = r1.nothingRendered
            if (r2 == 0) goto Lb8
            master.flame.danmaku.danmaku.model.BaseDanmaku r2 = r0.mLastDanmaku
            if (r2 == 0) goto La6
            boolean r2 = r2.isTimeOut()
            if (r2 == 0) goto La6
            r0.mLastDanmaku = r3
            master.flame.danmaku.controller.IDrawTask$TaskListener r2 = r0.mTaskListener
            if (r2 == 0) goto La6
            r2.onDanmakusDrawingFinished()
        La6:
            long r2 = r1.beginTime
            r4 = -1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto Lb0
            r1.beginTime = r11
        Lb0:
            long r2 = r1.endTime
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto Lb8
            r1.endTime = r13
        Lb8:
            return r1
        Lb9:
            r1.nothingRendered = r5
            r1.beginTime = r11
            r1.endTime = r13
            return r1
        Lc0:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.controller.DrawTask.drawDanmakus(master.flame.danmaku.danmaku.model.AbsDisplayer, master.flame.danmaku.danmaku.model.DanmakuTimer):master.flame.danmaku.danmaku.renderer.IRenderer$RenderingState");
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public IDanmakus getVisibleDanmakusOnTime(long j) {
        long j2 = this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION;
        IDanmakus iDanmakusSubnew = this.danmakuList.subnew((j - j2) - 100, j + j2);
        Danmakus danmakus = new Danmakus();
        if (iDanmakusSubnew != null && !iDanmakusSubnew.isEmpty()) {
            IDanmakuIterator it = iDanmakusSubnew.iterator();
            while (it.hasNext()) {
                BaseDanmaku next = it.next();
                if (next.isShown() && !next.isOutside()) {
                    danmakus.addItem(next);
                }
            }
        }
        return danmakus;
    }

    public boolean handleOnDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object[] objArr) {
        Boolean bool;
        if (danmakuConfigTag == null || DanmakuContext.DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(danmakuConfigTag)) {
            return true;
        }
        if (DanmakuContext.DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(danmakuConfigTag)) {
            Boolean bool2 = (Boolean) objArr[0];
            if (bool2 != null) {
                if (bool2.booleanValue()) {
                    this.mContext.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
                    return true;
                }
                this.mContext.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
                return true;
            }
        } else if (DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
            requestClearRetainer();
        } else {
            if (DanmakuContext.DanmakuConfigTag.MAXIMUN_LINES.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.OVERLAPPING_ENABLE.equals(danmakuConfigTag)) {
                IRenderer iRenderer = this.mRenderer;
                if (iRenderer == null) {
                    return true;
                }
                iRenderer.setVerifierEnabled(this.mContext.isPreventOverlappingEnabled() || this.mContext.isMaxLinesLimited());
                return true;
            }
            if (DanmakuContext.DanmakuConfigTag.ALIGN_BOTTOM.equals(danmakuConfigTag) && (bool = (Boolean) objArr[0]) != null) {
                IRenderer iRenderer2 = this.mRenderer;
                if (iRenderer2 == null) {
                    return true;
                }
                iRenderer2.alignBottom(bool.booleanValue());
                return true;
            }
        }
        return false;
    }

    public void initTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        this.mContext.getDisplayer().getCacheStuffer().clearCache(baseDanmaku);
        int i = baseDanmaku.requestFlags | 2;
        baseDanmaku.requestFlags = i;
        if (z) {
            baseDanmaku.paintWidth = -1.0f;
            baseDanmaku.paintHeight = -1.0f;
            baseDanmaku.requestFlags = i | 1;
            baseDanmaku.measureResetFlag++;
        }
    }

    public void loadDanmakus(BaseDanmakuParser baseDanmakuParser) {
        IDanmakus danmakus = baseDanmakuParser.setConfig(this.mContext).setDisplayer(this.mDisp).setTimer(this.mTimer).getDanmakus();
        this.danmakuList = danmakus;
        if (danmakus != null && !danmakus.isEmpty() && this.danmakuList.first().flags == null) {
            IDanmakuIterator it = this.danmakuList.iterator();
            while (it.hasNext()) {
                BaseDanmaku next = it.next();
                if (next != null) {
                    next.flags = this.mContext.mGlobalFlagValues;
                }
            }
        }
        this.mContext.mGlobalFlagValues.resetAll();
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus != null) {
            this.mLastDanmaku = iDanmakus.last();
        }
    }

    public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        boolean zHandleOnDanmakuConfigChanged = handleOnDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr);
        IDrawTask.TaskListener taskListener = this.mTaskListener;
        if (taskListener != null) {
            taskListener.onDanmakuConfigChanged();
        }
        return zHandleOnDanmakuConfigChanged;
    }

    public void onDanmakuRemoved(BaseDanmaku baseDanmaku) {
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void onPlayStateChanged(int i) {
        this.mPlayState = i;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void prepare() {
        loadDanmakus(this.mParser);
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        IDrawTask.TaskListener taskListener = this.mTaskListener;
        if (taskListener != null) {
            taskListener.ready();
            this.mReadyState = true;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void quit() {
        this.mContext.unregisterAllConfigChangedCallbacks();
        IRenderer iRenderer = this.mRenderer;
        if (iRenderer != null) {
            iRenderer.release();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void removeAllDanmakus(boolean z) {
        if (this.danmakuList != null && !this.danmakuList.isEmpty()) {
            synchronized (this.danmakuList) {
                if (!z) {
                    IDanmakus iDanmakusSubnew = this.danmakuList.subnew((this.mTimer.currMillisecond - this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) - 100, this.mTimer.currMillisecond + this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                    if (iDanmakusSubnew != null) {
                        this.danmakus = iDanmakusSubnew;
                    }
                }
                this.danmakuList.clear();
            }
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void removeAllLiveDanmakus() {
        if (this.danmakus != null && !this.danmakus.isEmpty()) {
            synchronized (this.danmakus) {
                IDanmakuIterator it = this.danmakus.iterator();
                while (it.hasNext()) {
                    BaseDanmaku next = it.next();
                    if (next.isLive) {
                        it.remove();
                        onDanmakuRemoved(next);
                    }
                }
            }
        }
    }

    public synchronized void removeUnusedLiveDanmakusIn(int i) {
        BaseDanmaku next;
        boolean zIsTimeOut;
        if (this.danmakuList != null && !this.danmakuList.isEmpty() && !this.mLiveDanmakus.isEmpty()) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            IDanmakuIterator it = this.mLiveDanmakus.iterator();
            while (it.hasNext() && (zIsTimeOut = (next = it.next()).isTimeOut())) {
                it.remove();
                this.danmakuList.removeItem(next);
                onDanmakuRemoved(next);
                if (!zIsTimeOut || SystemClock.uptimeMillis() - jUptimeMillis > i) {
                    break;
                }
            }
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestClear() {
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        this.mIsHidden = false;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestClearRetainer() {
        this.clearRetainerFlag = true;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestHide() {
        this.mIsHidden = true;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestSync(long j, long j2, long j3) {
        IDanmakus iDanmakusObtainRunningDanmakus = this.mRenderingState.obtainRunningDanmakus();
        this.mRunningDanmakus = iDanmakusObtainRunningDanmakus;
        IDanmakuIterator it = iDanmakusObtainRunningDanmakus.iterator();
        while (it.hasNext()) {
            BaseDanmaku next = it.next();
            if (next.isOutside()) {
                it.remove();
            } else {
                next.setTimeOffset(next.timeOffset + j3);
                next.isOffset = true;
            }
        }
        this.mStartRenderTime = j2;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void reset() {
        if (this.danmakus != null) {
            this.danmakus = new Danmakus();
        }
        IRenderer iRenderer = this.mRenderer;
        if (iRenderer != null) {
            iRenderer.clear();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void seek(long j) {
        BaseDanmaku baseDanmakuLast;
        reset();
        this.mContext.mGlobalFlagValues.updateVisibleFlag();
        this.mContext.mGlobalFlagValues.updateFirstShownFlag();
        this.mContext.mGlobalFlagValues.updateSyncOffsetTimeFlag();
        this.mContext.mGlobalFlagValues.updatePrepareFlag();
        this.mRunningDanmakus = new Danmakus(4);
        if (j < 1000) {
            j = 0;
        }
        this.mStartRenderTime = j;
        this.mRenderingState.reset();
        this.mRenderingState.endTime = this.mStartRenderTime;
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus == null || (baseDanmakuLast = iDanmakus.last()) == null || baseDanmakuLast.isTimeOut()) {
            return;
        }
        this.mLastDanmaku = baseDanmakuLast;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void setParser(BaseDanmakuParser baseDanmakuParser) {
        this.mParser = baseDanmakuParser;
        this.mReadyState = false;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void start() {
        this.mContext.registerConfigChangedCallback(this.mConfigChangedCallback);
    }
}
