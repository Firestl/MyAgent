package com.baidu.speech.asr;

/* JADX INFO: loaded from: classes.dex */
public interface ASRListener {
    void onEvent(String str, String str2, byte[] bArr, int i, int i2);

    void onEvent(String str, String str2, byte[] bArr, int i, int i2, boolean z);
}
