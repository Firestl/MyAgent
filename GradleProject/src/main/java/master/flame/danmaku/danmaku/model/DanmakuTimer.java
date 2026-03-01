package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuTimer {
    public long currMillisecond;
    public long lastInterval;

    public long add(long j) {
        return update(this.currMillisecond + j);
    }

    public long lastInterval() {
        return this.lastInterval;
    }

    public long update(long j) {
        long j2 = j - this.currMillisecond;
        this.lastInterval = j2;
        this.currMillisecond = j;
        return j2;
    }
}
