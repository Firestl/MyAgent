package com.zx.a.I8b7;

import com.efs.sdk.base.Constants;
import com.lzy.okgo.model.HttpHeaders;
import com.zx.a.I8b7.a1;
import com.zx.a.I8b7.c0;
import com.zx.a.I8b7.d1;
import com.zx.a.I8b7.e1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class e implements c0 {
    @Override // com.zx.a.I8b7.c0
    public d1 a(c0.a aVar) throws IOException {
        e1 e1Var;
        u0 u0Var = (u0) aVar;
        a1 a1Var = u0Var.c;
        a1.a aVar2 = new a1.a(a1Var);
        HttpURLConnection httpURLConnection = (HttpURLConnection) a1Var.f6193a.openConnection();
        c1 c1Var = a1Var.d;
        if (c1Var != null) {
            b1 b1Var = (b1) c1Var;
            m0 m0Var = b1Var.f6200a;
            if (m0Var != null) {
                aVar2.c.put("Content-Type", m0Var.f6241a);
            }
            long j = b1Var.b;
            if (j != -1) {
                aVar2.c.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Long.toString(j));
                aVar2.c.remove("Transfer-Encoding");
            } else {
                aVar2.c.put("Transfer-Encoding", "chunked");
                aVar2.c.remove(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            }
        }
        if (a1Var.c.get("Host") == null) {
            aVar2.c.put("Host", a1Var.f6193a.getHost());
        }
        if (a1Var.c.get(HttpHeaders.HEAD_KEY_CONNECTION) == null) {
            aVar2.c.put(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        }
        boolean z = false;
        if (a1Var.c.get(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING) == null && a1Var.c.get(HttpHeaders.HEAD_KEY_RANGE) == null) {
            z = true;
            aVar2.c.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, Constants.CP_GZIP);
        }
        d1 d1VarA = u0Var.a(new a1(aVar2), httpURLConnection);
        d1.a aVar3 = new d1.a(d1VarA);
        aVar3.f6208a = a1Var;
        if (z && Constants.CP_GZIP.equalsIgnoreCase(d1VarA.a(HttpHeaders.HEAD_KEY_CONTENT_ENCODING)) && (e1Var = d1VarA.f6207e) != null) {
            aVar3.f6209e = e1.a(((e1.a) e1Var).f6212a, -1L, new GZIPInputStream(((e1.a) d1VarA.f6207e).c));
            aVar3.d.remove(HttpHeaders.HEAD_KEY_CONTENT_ENCODING);
            aVar3.d.remove(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
        }
        return aVar3.a();
    }
}
