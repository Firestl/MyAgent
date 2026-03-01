package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public class R2LDanmaku extends BaseDanmaku {
    public static final long CORDON_RENDERING_TIME = 40;
    public static final long MAX_RENDERING_TIME = 100;
    public int mDistance;
    public long mLastTime;
    public float mStepX;
    public float x = 0.0f;
    public float y = -1.0f;
    public float[] RECT = null;

    public R2LDanmaku(Duration duration) {
        this.duration = duration;
    }

    public float getAccurateLeft(IDisplayer iDisplayer, long j) {
        long actualTime = j - getActualTime();
        return actualTime >= this.duration.value ? -this.paintWidth : iDisplayer.getWidth() - (actualTime * this.mStepX);
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getBottom() {
        return this.y + this.paintHeight;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getLeft() {
        return this.x;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float[] getRectAtTime(IDisplayer iDisplayer, long j) {
        if (!isMeasured()) {
            return null;
        }
        float accurateLeft = getAccurateLeft(iDisplayer, j);
        if (this.RECT == null) {
            this.RECT = new float[4];
        }
        float[] fArr = this.RECT;
        fArr[0] = accurateLeft;
        float f = this.y;
        fArr[1] = f;
        fArr[2] = accurateLeft + this.paintWidth;
        fArr[3] = f + this.paintHeight;
        return fArr;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getRight() {
        return this.x + this.paintWidth;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getTop() {
        return this.y;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public int getType() {
        return 1;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void layout(IDisplayer iDisplayer, float f, float f2) {
        DanmakuTimer danmakuTimer = this.mTimer;
        if (danmakuTimer != null) {
            long j = danmakuTimer.currMillisecond;
            long actualTime = j - getActualTime();
            if (actualTime > 0 && actualTime < this.duration.value) {
                this.x = getAccurateLeft(iDisplayer, j);
                if (!isShown()) {
                    this.y = f2;
                    setVisibility(true);
                }
                this.mLastTime = j;
                return;
            }
            this.mLastTime = j;
        }
        setVisibility(false);
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void measure(IDisplayer iDisplayer, boolean z) {
        super.measure(iDisplayer, z);
        int width = (int) (iDisplayer.getWidth() + this.paintWidth);
        this.mDistance = width;
        this.mStepX = width / this.duration.value;
    }
}
