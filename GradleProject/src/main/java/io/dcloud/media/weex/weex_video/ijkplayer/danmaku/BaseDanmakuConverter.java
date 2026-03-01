package io.dcloud.media.weex.weex_video.ijkplayer.danmaku;

import io.dcloud.media.weex.weex_video.ijkplayer.danmaku.BaseDanmakuData;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.FBDanmaku;
import master.flame.danmaku.danmaku.model.FTDanmaku;
import master.flame.danmaku.danmaku.model.R2LDanmaku;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseDanmakuConverter<T extends BaseDanmakuData> {
    public abstract T convertDanmaku(BaseDanmaku baseDanmaku);

    public void initData(T t, BaseDanmaku baseDanmaku) {
        int i = 1;
        if (!(baseDanmaku instanceof R2LDanmaku)) {
            if (baseDanmaku instanceof FBDanmaku) {
                i = 4;
            } else if (baseDanmaku instanceof FTDanmaku) {
                i = 5;
            }
        }
        t.setType(i);
        t.setContent(baseDanmaku.text.toString());
        t.setTime(baseDanmaku.getTime());
        t.setTextSize(baseDanmaku.textSize);
        t.setTextColor(baseDanmaku.textColor);
    }
}
