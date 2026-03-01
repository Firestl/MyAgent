package com.efs.sdk.base.core.c;

import com.efs.sdk.base.http.HttpResponse;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.efs.sdk.base.core.d.b f1817a;
    public c b;
    public String c;

    public e(com.efs.sdk.base.core.d.b bVar, c cVar, String str) {
        this.f1817a = bVar;
        this.b = cVar;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        c cVar;
        com.efs.sdk.base.core.d.b bVar = this.f1817a;
        HttpResponse httpResponse = (bVar == null || (cVar = this.b) == null) ? new HttpResponse() : cVar.a(bVar, true);
        d.a().a(this.c, httpResponse.succ ? 0 : httpResponse.getHttpCode());
        this.c = null;
        this.b = null;
    }
}
