package io.dcloud.common.DHInterface;

import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public interface IReqListener {

    public enum NetState {
        NET_INIT,
        NET_REQUEST_BEGIN,
        NET_TIMEOUT,
        NET_CONNECTED,
        NET_ERROR,
        NET_HANDLE_BEGIN,
        NET_HANDLE_ING,
        NET_HANDLE_END,
        NET_PAUSE
    }

    void onNetStateChanged(NetState netState, boolean z);

    int onReceiving(InputStream inputStream) throws Exception;

    void onResponsing(InputStream inputStream);
}
