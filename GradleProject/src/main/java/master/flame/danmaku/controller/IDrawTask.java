package master.flame.danmaku.controller;

import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;

/* JADX INFO: loaded from: classes3.dex */
public interface IDrawTask {
    public static final int PLAY_STATE_PAUSE = 2;
    public static final int PLAY_STATE_PLAYING = 1;

    public interface TaskListener {
        void onDanmakuAdd(BaseDanmaku baseDanmaku);

        void onDanmakuConfigChanged();

        void onDanmakuShown(BaseDanmaku baseDanmaku);

        void onDanmakusDrawingFinished();

        void ready();
    }

    void addDanmaku(BaseDanmaku baseDanmaku);

    void clearDanmakusOnScreen(long j);

    IRenderer.RenderingState draw(AbsDisplayer absDisplayer);

    IDanmakus getVisibleDanmakusOnTime(long j);

    void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z);

    void onPlayStateChanged(int i);

    void prepare();

    void quit();

    void removeAllDanmakus(boolean z);

    void removeAllLiveDanmakus();

    void requestClear();

    void requestClearRetainer();

    void requestHide();

    void requestSync(long j, long j2, long j3);

    void reset();

    void seek(long j);

    void setParser(BaseDanmakuParser baseDanmakuParser);

    void start();
}
