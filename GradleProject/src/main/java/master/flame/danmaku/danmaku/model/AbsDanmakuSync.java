package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsDanmakuSync {
    public static final int SYNC_STATE_HALT = 1;
    public static final int SYNC_STATE_PLAYING = 2;

    public abstract int getSyncState();

    public long getThresholdTimeMills() {
        return 1500L;
    }

    public abstract long getUptimeMillis();

    public boolean isSyncPlayingState() {
        return false;
    }
}
