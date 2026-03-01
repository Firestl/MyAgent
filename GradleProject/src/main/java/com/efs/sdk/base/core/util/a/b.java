package com.efs.sdk.base.core.util.a;

import com.efs.sdk.base.http.HttpResponse;
import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b implements com.efs.sdk.base.core.util.concurrent.c<HttpResponse> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1857a;
    public Map<String, String> b;
    public byte[] c;
    public File d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1858e;
    public Map<String, String> f;
    public boolean g = false;

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    @Override // com.efs.sdk.base.core.util.concurrent.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ com.efs.sdk.base.http.HttpResponse a() {
        /*
            r4 = this;
            java.lang.String r0 = r4.f1858e
            int r1 = r0.hashCode()
            r2 = 102230(0x18f56, float:1.43255E-40)
            r3 = 1
            if (r1 == r2) goto L1c
            r2 = 3446944(0x3498a0, float:4.830197E-39)
            if (r1 == r2) goto L12
            goto L26
        L12:
            java.lang.String r1 = "post"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L26
            r0 = 1
            goto L27
        L1c:
            java.lang.String r1 = "get"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L26
            r0 = 0
            goto L27
        L26:
            r0 = -1
        L27:
            if (r0 == 0) goto L8b
            if (r0 == r3) goto L47
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "request not support method '"
            r0.<init>(r1)
            java.lang.String r1 = r4.f1858e
            r0.append(r1)
            java.lang.String r1 = "'"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "efs.util.http"
            com.efs.sdk.base.core.util.Log.e(r1, r0)
            r0 = 0
            return r0
        L47:
            byte[] r0 = r4.c
            if (r0 == 0) goto L78
            int r0 = r0.length
            if (r0 <= 0) goto L78
            boolean r0 = r4.g
            if (r0 == 0) goto L65
            com.efs.sdk.base.http.HttpEnv r0 = com.efs.sdk.base.http.HttpEnv.getInstance()
            com.efs.sdk.base.http.IHttpUtil r0 = r0.getHttpUtil()
            java.lang.String r1 = r4.f1857a
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.b
            byte[] r3 = r4.c
            com.efs.sdk.base.http.HttpResponse r0 = r0.postAsFile(r1, r2, r3)
            return r0
        L65:
            com.efs.sdk.base.http.HttpEnv r0 = com.efs.sdk.base.http.HttpEnv.getInstance()
            com.efs.sdk.base.http.IHttpUtil r0 = r0.getHttpUtil()
            java.lang.String r1 = r4.f1857a
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.b
            byte[] r3 = r4.c
            com.efs.sdk.base.http.HttpResponse r0 = r0.post(r1, r2, r3)
            return r0
        L78:
            com.efs.sdk.base.http.HttpEnv r0 = com.efs.sdk.base.http.HttpEnv.getInstance()
            com.efs.sdk.base.http.IHttpUtil r0 = r0.getHttpUtil()
            java.lang.String r1 = r4.f1857a
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.b
            java.io.File r3 = r4.d
            com.efs.sdk.base.http.HttpResponse r0 = r0.post(r1, r2, r3)
            return r0
        L8b:
            com.efs.sdk.base.http.HttpEnv r0 = com.efs.sdk.base.http.HttpEnv.getInstance()
            com.efs.sdk.base.http.IHttpUtil r0 = r0.getHttpUtil()
            java.lang.String r1 = r4.f1857a
            java.util.Map<java.lang.String, java.lang.String> r2 = r4.b
            com.efs.sdk.base.http.HttpResponse r0 = r0.get(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.efs.sdk.base.core.util.a.b.a():java.lang.Object");
    }
}
