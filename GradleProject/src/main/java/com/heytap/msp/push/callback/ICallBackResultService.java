package com.heytap.msp.push.callback;

/* JADX INFO: loaded from: classes.dex */
public interface ICallBackResultService {
    void onGetNotificationStatus(int i, int i2);

    void onGetPushStatus(int i, int i2);

    void onRegister(int i, String str);

    void onSetPushTime(int i, String str);

    void onUnRegister(int i);
}
