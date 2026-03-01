package com.getui.gtc.base.http;

/* JADX INFO: loaded from: classes.dex */
public interface Call {

    public interface Callback {
        void onFailure(Call call, Exception exc);

        void onResponse(Call call, Response response);
    }

    void cancel();

    void enqueue(Callback callback);

    Response execute() throws Exception;

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
