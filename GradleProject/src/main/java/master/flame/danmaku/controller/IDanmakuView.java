package master.flame.danmaku.controller;

import android.view.View;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

/* JADX INFO: loaded from: classes3.dex */
public interface IDanmakuView {
    public static final int THREAD_TYPE_HIGH_PRIORITY = 2;
    public static final int THREAD_TYPE_LOW_PRIORITY = 3;
    public static final int THREAD_TYPE_MAIN_THREAD = 1;
    public static final int THREAD_TYPE_NORMAL_PRIORITY = 0;

    public interface OnDanmakuClickListener {
        boolean onDanmakuClick(IDanmakus iDanmakus);

        boolean onViewClick(IDanmakuView iDanmakuView);
    }

    void addDanmaku(BaseDanmaku baseDanmaku);

    void clearDanmakusOnScreen();

    void enableDanmakuDrawingCache(boolean z);

    DanmakuContext getConfig();

    long getCurrentTime();

    IDanmakus getCurrentVisibleDanmakus();

    int getHeight();

    OnDanmakuClickListener getOnDanmakuClickListener();

    View getView();

    int getWidth();

    void hide();

    long hideAndPauseDrawTask();

    void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z);

    boolean isDanmakuDrawingCacheEnabled();

    boolean isHardwareAccelerated();

    boolean isPaused();

    boolean isPrepared();

    boolean isShown();

    void pause();

    void prepare(BaseDanmakuParser baseDanmakuParser, DanmakuContext danmakuContext);

    void release();

    void removeAllDanmakus(boolean z);

    void removeAllLiveDanmakus();

    void resume();

    void seekTo(Long l);

    void setCallback(DrawHandler.Callback callback);

    void setDrawingThreadType(int i);

    void setOnDanmakuClickListener(OnDanmakuClickListener onDanmakuClickListener);

    void setVisibility(int i);

    void show();

    void showAndResumeDrawTask(Long l);

    void showFPS(boolean z);

    void start();

    void start(long j);

    void stop();

    void toggle();
}
