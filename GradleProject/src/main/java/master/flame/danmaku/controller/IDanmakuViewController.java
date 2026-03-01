package master.flame.danmaku.controller;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface IDanmakuViewController {
    void clear();

    long drawDanmakus();

    Context getContext();

    int getHeight();

    int getWidth();

    boolean isDanmakuDrawingCacheEnabled();

    boolean isHardwareAccelerated();

    boolean isViewReady();
}
