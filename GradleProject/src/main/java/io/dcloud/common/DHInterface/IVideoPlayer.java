package io.dcloud.common.DHInterface;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IVideoPlayer {
    void addEventListener(String str, String str2, String str3);

    void close();

    void exitFullScreen();

    boolean isFullScreen();

    boolean isPointInRect(float f, float f2);

    boolean isVideoHandleTouch();

    void pause();

    void play();

    void playbackRate(String str);

    void release();

    void requestFullScreen(String str);

    void resume();

    void seek(String str);

    void sendDanmu(JSONObject jSONObject);

    void setOptions(JSONObject jSONObject);

    void stop();
}
