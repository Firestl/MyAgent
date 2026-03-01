package com.zx.a.I8b7;

import com.zx.a.I8b7.c0;
import com.zx.a.I8b7.d1;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class f implements c0 {
    @Override // com.zx.a.I8b7.c0
    public d1 a(c0.a aVar) throws IOException {
        u0 u0Var = (u0) aVar;
        a1 a1Var = u0Var.c;
        HttpURLConnection httpURLConnection = u0Var.d;
        if (httpURLConnection.getDoOutput() && a1Var.d != null) {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            b1 b1Var = (b1) a1Var.d;
            outputStream.write(b1Var.c, b1Var.d, b1Var.b);
            k1.a(outputStream);
        }
        int responseCode = httpURLConnection.getResponseCode();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        m0 m0VarB = m0.b("text/json; charset=utf-8");
        if (httpURLConnection.getContentType() != null) {
            m0VarB = m0.b(httpURLConnection.getContentType());
        }
        String responseMessage = httpURLConnection.getResponseMessage();
        d1.a aVar2 = new d1.a();
        aVar2.b = responseCode;
        aVar2.d = new HashMap(headerFields);
        aVar2.c = responseMessage;
        aVar2.f6209e = e1.a(m0VarB, httpURLConnection.getContentLength(), responseCode == 200 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream());
        aVar2.f6208a = a1Var;
        return aVar2.a();
    }
}
