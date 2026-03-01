package com.baidu.aip.core.recog.listener;

import com.baidu.aip.core.recog.IStatus;
import com.baidu.aip.core.recog.RecogResult;

/* JADX INFO: loaded from: classes.dex */
public class StatusRecogListener implements IRecogListener, IStatus {
    public static final String TAG = "StatusRecogListener";
    public int status = 2;

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrAudio(byte[] bArr, int i, int i2) {
        if (i == 0 && bArr.length == i2) {
            return;
        }
        System.arraycopy(bArr, 0, new byte[i2], 0, i2);
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrBegin() {
        this.status = 4;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrEnd() {
        this.status = 5;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrExit() {
        this.status = 2;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrFinalResult(String[] strArr, RecogResult recogResult) {
        this.status = 13;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrFinish(RecogResult recogResult) {
        this.status = 13;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrFinishError(int i, int i2, String str, RecogResult recogResult) {
        this.status = 12;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrLongFinish() {
        this.status = 7;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrOnlineNluResult(String str) {
        this.status = 13;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrPartialResult(String[] strArr, RecogResult recogResult) {
        this.status = 11;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrReady() {
        this.status = 3;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onAsrVolume(int i, int i2) {
        this.status = 100;
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onOfflineLoaded() {
    }

    @Override // com.baidu.aip.core.recog.listener.IRecogListener
    public void onOfflineUnLoaded() {
    }
}
