package io.dcloud.common.DHInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IFrameViewStatus {
    public static final byte STATUS_ON_DESTROY = 4;
    public static final byte STATUS_ON_INIT = 0;
    public static final byte STATUS_ON_LOADING = 2;
    public static final byte STATUS_ON_PAGE_CHANGED = 5;
    public static final byte STATUS_ON_PRESHOW = 3;
    public static final byte STATUS_ON_PRE_LOADING = 1;

    void addFrameViewListener(IEventCallback iEventCallback);

    byte obtainStatus();

    void onDestroy();

    void onInit();

    void onLoading();

    void onPreLoading();

    void onPreShow(IFrameView iFrameView);

    void removeFrameViewListener(IEventCallback iEventCallback);
}
