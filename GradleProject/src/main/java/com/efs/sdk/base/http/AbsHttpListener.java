package com.efs.sdk.base.http;

import com.efs.sdk.base.core.util.concurrent.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsHttpListener implements b<HttpResponse> {
    public abstract void onError(HttpResponse httpResponse);

    public abstract void onSuccess(HttpResponse httpResponse);

    @Override // com.efs.sdk.base.core.util.concurrent.b
    public void result(HttpResponse httpResponse) {
        if (httpResponse == null || !httpResponse.succ) {
            onError(httpResponse);
        } else {
            onSuccess(httpResponse);
        }
    }
}
