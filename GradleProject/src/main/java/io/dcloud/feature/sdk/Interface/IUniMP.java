package io.dcloud.feature.sdk.Interface;

/* JADX INFO: loaded from: classes3.dex */
public interface IUniMP {
    boolean closeUniMP();

    String getAppid();

    String getCurrentPageUrl();

    boolean hideUniMP();

    boolean isRuning();

    boolean sendUniMPEvent(String str, Object obj);

    boolean showUniMP();
}
