package com.efs.sdk.base.http;

import com.efs.sdk.base.core.util.a.a;
import com.efs.sdk.base.core.util.concurrent.b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HttpEnv {
    public List<b<HttpResponse>> mHttpListenerList;
    public IHttpUtil mHttpUtil;

    public static class SingletonHolder {
        public static final HttpEnv INSTANCE = new HttpEnv();
    }

    public static HttpEnv getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addListener(AbsHttpListener absHttpListener) {
        this.mHttpListenerList.add(absHttpListener);
    }

    public List<b<HttpResponse>> getHttpListenerList() {
        return new ArrayList(this.mHttpListenerList);
    }

    public IHttpUtil getHttpUtil() {
        return this.mHttpUtil;
    }

    public void removeListener(AbsHttpListener absHttpListener) {
        this.mHttpListenerList.remove(absHttpListener);
    }

    public void setHttpUtil(IHttpUtil iHttpUtil) {
        this.mHttpUtil = iHttpUtil;
    }

    public HttpEnv() {
        this.mHttpUtil = a.a();
        this.mHttpListenerList = new ArrayList(1);
    }
}
