package com.baidu.speech;

/* JADX INFO: loaded from: classes.dex */
public interface EventManager {
    void registerListener(EventListener eventListener);

    void send(String str, String str2, byte[] bArr, int i, int i2);

    void unregisterListener(EventListener eventListener);
}
