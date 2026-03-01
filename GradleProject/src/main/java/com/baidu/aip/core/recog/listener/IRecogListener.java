package com.baidu.aip.core.recog.listener;

import com.baidu.aip.core.recog.RecogResult;

/* JADX INFO: loaded from: classes.dex */
public interface IRecogListener {
    void onAsrAudio(byte[] bArr, int i, int i2);

    void onAsrBegin();

    void onAsrEnd();

    void onAsrExit();

    void onAsrFinalResult(String[] strArr, RecogResult recogResult);

    void onAsrFinish(RecogResult recogResult);

    void onAsrFinishError(int i, int i2, String str, RecogResult recogResult);

    void onAsrLongFinish();

    void onAsrOnlineNluResult(String str);

    void onAsrPartialResult(String[] strArr, RecogResult recogResult);

    void onAsrReady();

    void onAsrVolume(int i, int i2);

    void onOfflineLoaded();

    void onOfflineUnLoaded();
}
