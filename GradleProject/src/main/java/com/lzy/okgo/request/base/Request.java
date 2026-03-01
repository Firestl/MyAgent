package com.lzy.okgo.request.base;

import android.text.TextUtils;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import supwisdom.bt1;
import supwisdom.bw0;
import supwisdom.ct1;
import supwisdom.dt1;
import supwisdom.dw0;
import supwisdom.fv0;
import supwisdom.gs1;
import supwisdom.gv0;
import supwisdom.hv0;
import supwisdom.iv0;
import supwisdom.jv0;
import supwisdom.lv0;
import supwisdom.rv0;
import supwisdom.sv0;
import supwisdom.zs1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Request<T, R extends Request> implements Serializable {
    public static final long serialVersionUID = -7174118653689916252L;
    public String baseUrl;
    public String cacheKey;
    public CacheMode cacheMode;
    public transient lv0<T> cachePolicy;
    public long cacheTime;
    public transient iv0<T> call;
    public transient rv0<T> callback;
    public transient zs1 client;
    public transient sv0<T> converter;
    public transient bt1 mRequest;
    public int retryCount;
    public transient Object tag;
    public transient bw0.c uploadInterceptor;
    public String url;
    public HttpParams params = new HttpParams();
    public HttpHeaders headers = new HttpHeaders();

    public Request(String str) {
        this.url = str;
        this.baseUrl = str;
        fv0 fv0VarI = fv0.i();
        String acceptLanguage = HttpHeaders.getAcceptLanguage();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers(HttpHeaders.HEAD_KEY_ACCEPT_LANGUAGE, acceptLanguage);
        }
        String userAgent = HttpHeaders.getUserAgent();
        if (!TextUtils.isEmpty(userAgent)) {
            headers("User-Agent", userAgent);
        }
        if (fv0VarI.d() != null) {
            params(fv0VarI.d());
        }
        if (fv0VarI.c() != null) {
            headers(fv0VarI.c());
        }
        this.retryCount = fv0VarI.h();
        this.cacheMode = fv0VarI.a();
        this.cacheTime = fv0VarI.b();
    }

    public iv0<T> adapt() {
        iv0<T> iv0Var = this.call;
        return iv0Var == null ? new hv0(this) : iv0Var;
    }

    public R addUrlParams(String str, List<String> list) {
        this.params.putUrlParams(str, list);
        return this;
    }

    public R cacheKey(String str) {
        dw0.a(str, "cacheKey == null");
        this.cacheKey = str;
        return this;
    }

    public R cacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    public R cachePolicy(lv0<T> lv0Var) {
        dw0.a(lv0Var, "cachePolicy == null");
        this.cachePolicy = lv0Var;
        return this;
    }

    public R cacheTime(long j) {
        if (j <= -1) {
            j = -1;
        }
        this.cacheTime = j;
        return this;
    }

    public R call(iv0<T> iv0Var) {
        dw0.a(iv0Var, "call == null");
        this.call = iv0Var;
        return this;
    }

    public R client(zs1 zs1Var) {
        dw0.a(zs1Var, "OkHttpClient == null");
        this.client = zs1Var;
        return this;
    }

    public R converter(sv0<T> sv0Var) {
        dw0.a(sv0Var, "converter == null");
        this.converter = sv0Var;
        return this;
    }

    public dt1 execute() throws IOException {
        return getRawCall().execute();
    }

    public abstract bt1 generateRequest(ct1 ct1Var);

    public abstract ct1 generateRequestBody();

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public CacheMode getCacheMode() {
        return this.cacheMode;
    }

    public lv0<T> getCachePolicy() {
        return this.cachePolicy;
    }

    public long getCacheTime() {
        return this.cacheTime;
    }

    public sv0<T> getConverter() {
        if (this.converter == null) {
            this.converter = this.callback;
        }
        dw0.a(this.converter, "converter == null, do you forget to call Request#converter(Converter<T>) ?");
        return this.converter;
    }

    public HttpParams.FileWrapper getFileParam(String str) {
        List<HttpParams.FileWrapper> list = this.params.fileParamsMap.get(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public abstract HttpMethod getMethod();

    public HttpParams getParams() {
        return this.params;
    }

    public gs1 getRawCall() {
        ct1 ct1VarGenerateRequestBody = generateRequestBody();
        if (ct1VarGenerateRequestBody != null) {
            bw0 bw0Var = new bw0(ct1VarGenerateRequestBody, this.callback);
            bw0Var.a(this.uploadInterceptor);
            this.mRequest = generateRequest(bw0Var);
        } else {
            this.mRequest = generateRequest(null);
        }
        if (this.client == null) {
            this.client = fv0.i().g();
        }
        return this.client.a(this.mRequest);
    }

    public bt1 getRequest() {
        return this.mRequest;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public Object getTag() {
        return this.tag;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUrlParam(String str) {
        List<String> list = this.params.urlParamsMap.get(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public R headers(HttpHeaders httpHeaders) {
        this.headers.put(httpHeaders);
        return this;
    }

    public R params(HttpParams httpParams) {
        this.params.put(httpParams);
        return this;
    }

    public R removeAllHeaders() {
        this.headers.clear();
        return this;
    }

    public R removeAllParams() {
        this.params.clear();
        return this;
    }

    public R removeHeader(String str) {
        this.headers.remove(str);
        return this;
    }

    public R removeParam(String str) {
        this.params.remove(str);
        return this;
    }

    public R retryCount(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        this.retryCount = i;
        return this;
    }

    public void setCallback(rv0<T> rv0Var) {
        this.callback = rv0Var;
    }

    public R tag(Object obj) {
        this.tag = obj;
        return this;
    }

    public R uploadInterceptor(bw0.c cVar) {
        this.uploadInterceptor = cVar;
        return this;
    }

    public void execute(rv0<T> rv0Var) {
        dw0.a(rv0Var, "callback == null");
        this.callback = rv0Var;
        adapt().a(rv0Var);
    }

    public R headers(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public R params(Map<String, String> map, boolean... zArr) {
        this.params.put(map, zArr);
        return this;
    }

    public <E> E adapt(jv0<T, E> jv0Var) {
        iv0<T> hv0Var = this.call;
        if (hv0Var == null) {
            hv0Var = new hv0<>(this);
        }
        return jv0Var.a(hv0Var, null);
    }

    public R params(String str, String str2, boolean... zArr) {
        this.params.put(str, str2, zArr);
        return this;
    }

    public R params(String str, int i, boolean... zArr) {
        this.params.put(str, i, zArr);
        return this;
    }

    public R params(String str, float f, boolean... zArr) {
        this.params.put(str, f, zArr);
        return this;
    }

    public <E> E adapt(gv0 gv0Var, jv0<T, E> jv0Var) {
        iv0<T> hv0Var = this.call;
        if (hv0Var == null) {
            hv0Var = new hv0<>(this);
        }
        return jv0Var.a(hv0Var, gv0Var);
    }

    public R params(String str, double d, boolean... zArr) {
        this.params.put(str, d, zArr);
        return this;
    }

    public R params(String str, long j, boolean... zArr) {
        this.params.put(str, j, zArr);
        return this;
    }

    public R params(String str, char c, boolean... zArr) {
        this.params.put(str, c, zArr);
        return this;
    }

    public R params(String str, boolean z, boolean... zArr) {
        this.params.put(str, z, zArr);
        return this;
    }
}
