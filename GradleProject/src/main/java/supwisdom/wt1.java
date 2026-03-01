package supwisdom;

import com.efs.sdk.base.Constants;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.util.List;
import okio.GzipSource;
import okio.Okio;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.us1;
import supwisdom.ws1;

/* JADX INFO: compiled from: BridgeInterceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wt1 implements ws1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final os1 f9656a;

    public wt1(os1 os1Var) {
        this.f9656a = os1Var;
    }

    public final String a(List<ns1> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            ns1 ns1Var = list.get(i);
            sb.append(ns1Var.e());
            sb.append('=');
            sb.append(ns1Var.i());
        }
        return sb.toString();
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws IOException {
        bt1 bt1VarRequest = aVar.request();
        bt1.a aVarF = bt1VarRequest.f();
        ct1 ct1VarA = bt1VarRequest.a();
        if (ct1VarA != null) {
            xs1 xs1VarContentType = ct1VarA.contentType();
            if (xs1VarContentType != null) {
                aVarF.b("Content-Type", xs1VarContentType.toString());
            }
            long jContentLength = ct1VarA.contentLength();
            if (jContentLength != -1) {
                aVarF.b(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Long.toString(jContentLength));
                aVarF.a("Transfer-Encoding");
            } else {
                aVarF.b("Transfer-Encoding", "chunked");
                aVarF.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            }
        }
        boolean z = false;
        if (bt1VarRequest.a("Host") == null) {
            aVarF.b("Host", kt1.a(bt1VarRequest.g(), false));
        }
        if (bt1VarRequest.a(HttpHeaders.HEAD_KEY_CONNECTION) == null) {
            aVarF.b(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        }
        if (bt1VarRequest.a(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING) == null && bt1VarRequest.a(HttpHeaders.HEAD_KEY_RANGE) == null) {
            z = true;
            aVarF.b(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, Constants.CP_GZIP);
        }
        List<ns1> listA = this.f9656a.a(bt1VarRequest.g());
        if (!listA.isEmpty()) {
            aVarF.b("Cookie", a(listA));
        }
        if (bt1VarRequest.a("User-Agent") == null) {
            aVarF.b("User-Agent", lt1.a());
        }
        dt1 dt1VarA = aVar.a(aVarF.a());
        au1.a(this.f9656a, bt1VarRequest.g(), dt1VarA.e());
        dt1.a aVarH = dt1VarA.h();
        aVarH.a(bt1VarRequest);
        if (z && Constants.CP_GZIP.equalsIgnoreCase(dt1VarA.a(HttpHeaders.HEAD_KEY_CONTENT_ENCODING)) && au1.b(dt1VarA)) {
            GzipSource gzipSource = new GzipSource(dt1VarA.a().source());
            us1.a aVarB = dt1VarA.e().b();
            aVarB.b(HttpHeaders.HEAD_KEY_CONTENT_ENCODING);
            aVarB.b(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            aVarH.a(aVarB.a());
            aVarH.a(new du1(dt1VarA.a("Content-Type"), -1L, Okio.buffer(gzipSource)));
        }
        return aVarH.a();
    }
}
