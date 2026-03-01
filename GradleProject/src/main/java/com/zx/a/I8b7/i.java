package com.zx.a.I8b7;

import com.zx.a.I8b7.c0;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class i implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v1 f6227a;

    public i(v1 v1Var) {
        this.f6227a = v1Var;
    }

    @Override // com.zx.a.I8b7.c0
    public d1 a(c0.a aVar) throws IOException {
        u0 u0Var = (u0) aVar;
        a1 a1Var = u0Var.c;
        HttpURLConnection httpURLConnection = u0Var.d;
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(a1Var.d != null);
        httpURLConnection.setConnectTimeout(this.f6227a.f);
        httpURLConnection.setReadTimeout(this.f6227a.g);
        httpURLConnection.setInstanceFollowRedirects(this.f6227a.f6297e);
        this.f6227a.getClass();
        httpURLConnection.setUseCaches(false);
        if ("https".equalsIgnoreCase(a1Var.f6193a.getProtocol())) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SSLSocketFactory sSLSocketFactory = this.f6227a.c;
            if (sSLSocketFactory != null) {
                httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
            }
            HostnameVerifier hostnameVerifier = this.f6227a.d;
            if (hostnameVerifier != null) {
                httpsURLConnection.setHostnameVerifier(hostnameVerifier);
            }
        }
        Map<String, String> map = a1Var.c;
        if (map != null && map.size() > 0) {
            for (String str : map.keySet()) {
                httpURLConnection.setRequestProperty(str, map.get(str));
            }
        }
        httpURLConnection.connect();
        return u0Var.a(a1Var, httpURLConnection);
    }
}
