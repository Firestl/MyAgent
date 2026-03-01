package com.getui.gtc.dim;

/* JADX INFO: loaded from: classes.dex */
public interface AppDataProvider {
    Object getAppData(String str);

    void onDataFailed(String str, Throwable th);
}
