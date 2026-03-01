package com.lzy.okgo.request;

import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.request.base.NoBodyRequest;
import supwisdom.bt1;
import supwisdom.ct1;

/* JADX INFO: loaded from: classes2.dex */
public class GetRequest<T> extends NoBodyRequest<T, GetRequest<T>> {
    public GetRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public bt1 generateRequest(ct1 ct1Var) {
        bt1.a aVarGenerateRequestBuilder = generateRequestBuilder(ct1Var);
        aVarGenerateRequestBuilder.b();
        aVarGenerateRequestBuilder.b(this.url);
        aVarGenerateRequestBuilder.a(this.tag);
        return aVarGenerateRequestBuilder.a();
    }

    @Override // com.lzy.okgo.request.base.Request
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}
