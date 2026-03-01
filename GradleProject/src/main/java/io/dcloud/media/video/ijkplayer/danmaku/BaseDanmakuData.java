package io.dcloud.media.video.ijkplayer.danmaku;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseDanmakuData {
    public String content;
    public int textColor;
    public float textSize;
    public long time;
    public int type;

    public String getContent() {
        return this.content;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public long getTime() {
        return this.time;
    }

    public int getType() {
        return this.type;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "BaseDanmakuData{type=" + this.type + ", content='" + this.content + Operators.SINGLE_QUOTE + ", time=" + this.time + ", textSize=" + this.textSize + ", textColor=" + this.textColor + Operators.BLOCK_END;
    }
}
