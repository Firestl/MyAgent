package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseDanmaku {
    public static final String DANMAKU_BR_CHAR = "/n";
    public static final int FLAG_REQUEST_INVALIDATE = 2;
    public static final int FLAG_REQUEST_REMEASURE = 1;
    public static final int INVISIBLE = 0;
    public static final int TYPE_FIX_BOTTOM = 4;
    public static final int TYPE_FIX_TOP = 5;
    public static final int TYPE_MOVEABLE_XXX = 0;
    public static final int TYPE_SCROLL_LR = 6;
    public static final int TYPE_SCROLL_RL = 1;
    public static final int TYPE_SPECIAL = 7;
    public static final int VISIBLE = 1;
    public IDrawingCache<?> cache;
    public Duration duration;
    public boolean forceBuildCacheInSameThread;
    public int index;
    public boolean isGuest;
    public boolean isLive;
    public boolean isOffset;
    public String[] lines;
    public DanmakuTimer mTimer;
    public Object obj;
    public long realTime;
    public float rotationY;
    public float rotationZ;
    public Object tag;
    public CharSequence text;
    public int textColor;
    public int textShadowColor;
    public long time;
    public long timeOffset;
    public String userHash;
    public int visibility;
    public int underlineColor = 0;
    public float textSize = -1.0f;
    public int borderColor = 0;
    public int padding = 0;
    public byte priority = 0;
    public float paintWidth = -1.0f;
    public float paintHeight = -1.0f;
    public int visibleResetFlag = 0;
    public int measureResetFlag = 0;
    public int syncTimeOffsetResetFlag = 0;
    public int prepareResetFlag = -1;
    public int userId = 0;
    public int alpha = AlphaValue.MAX;
    public int mFilterParam = 0;
    public int filterResetFlag = -1;
    public GlobalFlagValues flags = null;
    public int requestFlags = 0;
    public int firstShownFlag = -1;

    public int draw(IDisplayer iDisplayer) {
        return iDisplayer.draw(this);
    }

    public long getActualTime() {
        GlobalFlagValues globalFlagValues = this.flags;
        return (globalFlagValues == null || globalFlagValues.SYNC_TIME_OFFSET_RESET_FLAG != this.syncTimeOffsetResetFlag) ? this.time : this.time + this.timeOffset;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public abstract float getBottom();

    public IDrawingCache<?> getDrawingCache() {
        return this.cache;
    }

    public long getDuration() {
        return this.duration.value;
    }

    public abstract float getLeft();

    public abstract float[] getRectAtTime(IDisplayer iDisplayer, long j);

    public abstract float getRight();

    public long getTime() {
        return this.time;
    }

    public DanmakuTimer getTimer() {
        return this.mTimer;
    }

    public abstract float getTop();

    public abstract int getType();

    public boolean hasPassedFilter() {
        if (this.filterResetFlag == this.flags.FILTER_RESET_FLAG) {
            return true;
        }
        this.mFilterParam = 0;
        return false;
    }

    public boolean isFiltered() {
        return this.filterResetFlag == this.flags.FILTER_RESET_FLAG && this.mFilterParam != 0;
    }

    public boolean isFilteredBy(int i) {
        return this.filterResetFlag == this.flags.FILTER_RESET_FLAG && (this.mFilterParam & i) == i;
    }

    public boolean isLate() {
        DanmakuTimer danmakuTimer = this.mTimer;
        return danmakuTimer == null || danmakuTimer.currMillisecond < getActualTime();
    }

    public boolean isMeasured() {
        return this.paintWidth > -1.0f && this.paintHeight > -1.0f && this.measureResetFlag == this.flags.MEASURE_RESET_FLAG;
    }

    public boolean isOffset() {
        GlobalFlagValues globalFlagValues = this.flags;
        if (globalFlagValues == null || globalFlagValues.SYNC_TIME_OFFSET_RESET_FLAG != this.syncTimeOffsetResetFlag) {
            return false;
        }
        return this.isOffset;
    }

    public boolean isOutside() {
        DanmakuTimer danmakuTimer = this.mTimer;
        return danmakuTimer == null || isOutside(danmakuTimer.currMillisecond);
    }

    public boolean isPrepared() {
        return this.prepareResetFlag == this.flags.PREPARE_RESET_FLAG;
    }

    public boolean isShown() {
        return this.visibility == 1 && this.visibleResetFlag == this.flags.VISIBLE_RESET_FLAG;
    }

    public boolean isTimeOut() {
        DanmakuTimer danmakuTimer = this.mTimer;
        return danmakuTimer == null || isTimeOut(danmakuTimer.currMillisecond);
    }

    public abstract void layout(IDisplayer iDisplayer, float f, float f2);

    public void measure(IDisplayer iDisplayer, boolean z) {
        iDisplayer.measure(this, z);
        this.measureResetFlag = this.flags.MEASURE_RESET_FLAG;
    }

    public void prepare(IDisplayer iDisplayer, boolean z) {
        iDisplayer.prepare(this, z);
        this.prepareResetFlag = this.flags.PREPARE_RESET_FLAG;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public void setTime(long j) {
        this.time = j;
        this.timeOffset = 0L;
    }

    public void setTimeOffset(long j) {
        this.timeOffset = j;
        this.syncTimeOffsetResetFlag = this.flags.SYNC_TIME_OFFSET_RESET_FLAG;
    }

    public void setTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
    }

    public void setVisibility(boolean z) {
        if (!z) {
            this.visibility = 0;
        } else {
            this.visibleResetFlag = this.flags.VISIBLE_RESET_FLAG;
            this.visibility = 1;
        }
    }

    public boolean isOutside(long j) {
        long actualTime = j - getActualTime();
        return actualTime <= 0 || actualTime >= this.duration.value;
    }

    public boolean isTimeOut(long j) {
        return j - getActualTime() >= this.duration.value;
    }
}
