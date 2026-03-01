package com.lzy.okgo.request.base;

import com.lzy.okgo.request.base.NoBodyRequest;
import supwisdom.bt1;
import supwisdom.ct1;
import supwisdom.dw0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NoBodyRequest<T, R extends NoBodyRequest> extends Request<T, R> {
    public static final long serialVersionUID = 1200621102761691196L;

    public NoBodyRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public ct1 generateRequestBody() {
        return null;
    }

    public bt1.a generateRequestBuilder(ct1 ct1Var) {
        this.url = dw0.a(this.baseUrl, this.params.urlParamsMap);
        bt1.a aVar = new bt1.a();
        dw0.a(aVar, this.headers);
        return aVar;
    }
}
