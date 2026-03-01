package io.dcloud.feature.weex_media.option;

/* JADX INFO: loaded from: classes3.dex */
public enum EnumPlayStrategy {
    DEFAULT(0),
    PLAY_SMOOTH(1),
    START_QUICK(2),
    M3U8_SMOOTH(3);

    public int flagVal;

    EnumPlayStrategy(int i) {
        this.flagVal = i;
    }

    public int getFlagVal() {
        return this.flagVal;
    }
}
