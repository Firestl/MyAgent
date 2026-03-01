package io.dcloud.common.DHInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IWebviewStateListener extends ICallBack {
    public static final int ON_LOAD_RESOURCE = 2;
    public static final int ON_PAGE_FINISHED = 1;
    public static final int ON_PAGE_STARTED = 0;
    public static final int ON_PROGRESS_CHANGED = 3;
    public static final int ON_RECEIVED_ERROR = 5;
    public static final int ON_RECEIVED_TITLE = 4;
    public static final int ON_WEBVIEW_READY = -1;
    public static final int ON_WEBVIEW_RENDERING = 6;
}
