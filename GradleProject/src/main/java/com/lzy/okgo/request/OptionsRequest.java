package com.lzy.okgo.request;

import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.request.base.BodyRequest;
import supwisdom.bt1;
import supwisdom.ct1;

/* JADX INFO: loaded from: classes2.dex */
public class OptionsRequest<T> extends BodyRequest<T, OptionsRequest<T>> {
    public OptionsRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public bt1 generateRequest(ct1 ct1Var) {
        bt1.a aVarGenerateRequestBuilder = generateRequestBuilder(ct1Var);
        aVarGenerateRequestBuilder.a("OPTIONS", ct1Var);
        aVarGenerateRequestBuilder.b(this.url);
        aVarGenerateRequestBuilder.a(this.tag);
        return aVarGenerateRequestBuilder.a();
    }

    @Override // com.lzy.okgo.request.base.Request
    public HttpMethod getMethod() {
        return HttpMethod.OPTIONS;
    }
}
