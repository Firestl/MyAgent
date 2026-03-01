package com.efs.sdk.base.core.util.a;

import com.efs.sdk.base.http.HttpResponse;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.efs.sdk.base.core.util.concurrent.d<HttpResponse> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1859a;

    public c(b bVar) {
        super(bVar);
        this.f1859a = bVar;
    }

    public final HttpResponse b() {
        this.f1859a.f1858e = "post";
        return a();
    }
}
