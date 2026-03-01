package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public class FTDanmaku extends BaseDanmaku {
    public int mLastDispWidth;
    public float mLastLeft;
    public float mLastPaintWidth;
    public float x = 0.0f;
    public float y = -1.0f;
    public float[] RECT = null;

    public FTDanmaku(Duration duration) {
        this.duration = duration;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getBottom() {
        return this.y + this.paintHeight;
    }

    public float getLeft(IDisplayer iDisplayer) {
        if (this.mLastDispWidth == iDisplayer.getWidth() && this.mLastPaintWidth == this.paintWidth) {
            return this.mLastLeft;
        }
        float width = (iDisplayer.getWidth() - this.paintWidth) / 2.0f;
        this.mLastDispWidth = iDisplayer.getWidth();
        this.mLastPaintWidth = this.paintWidth;
        this.mLastLeft = width;
        return width;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float[] getRectAtTime(IDisplayer iDisplayer, long j) {
        if (!isMeasured()) {
            return null;
        }
        float left = getLeft(iDisplayer);
        if (this.RECT == null) {
            this.RECT = new float[4];
        }
        float[] fArr = this.RECT;
        fArr[0] = left;
        float f = this.y;
        fArr[1] = f;
        fArr[2] = left + this.paintWidth;
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
        return 5;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void layout(IDisplayer iDisplayer, float f, float f2) {
        DanmakuTimer danmakuTimer = this.mTimer;
        if (danmakuTimer != null) {
            long actualTime = danmakuTimer.currMillisecond - getActualTime();
            if (actualTime <= 0 || actualTime >= this.duration.value) {
                setVisibility(false);
                this.y = -1.0f;
                this.x = iDisplayer.getWidth();
            } else {
                if (isShown()) {
                    return;
                }
                this.x = getLeft(iDisplayer);
                this.y = f2;
                setVisibility(true);
            }
        }
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getLeft() {
        return this.x;
    }
}
