package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public class GlobalFlagValues {
    public int MEASURE_RESET_FLAG = 0;
    public int VISIBLE_RESET_FLAG = 0;
    public int FILTER_RESET_FLAG = 0;
    public int FIRST_SHOWN_RESET_FLAG = 0;
    public int SYNC_TIME_OFFSET_RESET_FLAG = 0;
    public int PREPARE_RESET_FLAG = 0;

    public void resetAll() {
        this.VISIBLE_RESET_FLAG = 0;
        this.MEASURE_RESET_FLAG = 0;
        this.FILTER_RESET_FLAG = 0;
        this.FIRST_SHOWN_RESET_FLAG = 0;
        this.SYNC_TIME_OFFSET_RESET_FLAG = 0;
        this.PREPARE_RESET_FLAG = 0;
    }

    public void updateFilterFlag() {
        this.FILTER_RESET_FLAG++;
    }

    public void updateFirstShownFlag() {
        this.FIRST_SHOWN_RESET_FLAG++;
    }

    public void updateMeasureFlag() {
        this.MEASURE_RESET_FLAG++;
    }

    public void updatePrepareFlag() {
        this.PREPARE_RESET_FLAG++;
    }

    public void updateSyncOffsetTimeFlag() {
        this.SYNC_TIME_OFFSET_RESET_FLAG++;
    }

    public void updateVisibleFlag() {
        this.VISIBLE_RESET_FLAG++;
    }
}
