package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import supwisdom.dt1;
import supwisdom.ws1;

/* JADX INFO: compiled from: CallServerInterceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xt1 implements ws1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9810a;

    /* JADX INFO: compiled from: CallServerInterceptor.java */
    public static final class a extends ForwardingSink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f9811a;

        public a(Sink sink) {
            super(sink);
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            this.f9811a += j;
        }
    }

    public xt1(boolean z) {
        this.f9810a = z;
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws IOException {
        dt1 dt1VarA;
        cu1 cu1Var = (cu1) aVar;
        yt1 yt1VarC = cu1Var.c();
        vt1 vt1VarD = cu1Var.d();
        st1 st1Var = (st1) cu1Var.connection();
        bt1 bt1VarRequest = cu1Var.request();
        long jCurrentTimeMillis = System.currentTimeMillis();
        cu1Var.b().requestHeadersStart(cu1Var.a());
        yt1VarC.a(bt1VarRequest);
        cu1Var.b().requestHeadersEnd(cu1Var.a(), bt1VarRequest);
        dt1.a responseHeaders = null;
        if (bu1.b(bt1VarRequest.e()) && bt1VarRequest.a() != null) {
            if ("100-continue".equalsIgnoreCase(bt1VarRequest.a("Expect"))) {
                yt1VarC.flushRequest();
                cu1Var.b().responseHeadersStart(cu1Var.a());
                responseHeaders = yt1VarC.readResponseHeaders(true);
            }
            if (responseHeaders == null) {
                cu1Var.b().requestBodyStart(cu1Var.a());
                a aVar2 = new a(yt1VarC.a(bt1VarRequest, bt1VarRequest.a().contentLength()));
                BufferedSink bufferedSinkBuffer = Okio.buffer(aVar2);
                bt1VarRequest.a().writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
                cu1Var.b().requestBodyEnd(cu1Var.a(), aVar2.f9811a);
            } else if (!st1Var.d()) {
                vt1VarD.e();
            }
        }
        yt1VarC.finishRequest();
        if (responseHeaders == null) {
            cu1Var.b().responseHeadersStart(cu1Var.a());
            responseHeaders = yt1VarC.readResponseHeaders(false);
        }
        responseHeaders.a(bt1VarRequest);
        responseHeaders.a(vt1VarD.c().c());
        responseHeaders.b(jCurrentTimeMillis);
        responseHeaders.a(System.currentTimeMillis());
        dt1 dt1VarA2 = responseHeaders.a();
        int iC = dt1VarA2.c();
        if (iC == 100) {
            dt1.a responseHeaders2 = yt1VarC.readResponseHeaders(false);
            responseHeaders2.a(bt1VarRequest);
            responseHeaders2.a(vt1VarD.c().c());
            responseHeaders2.b(jCurrentTimeMillis);
            responseHeaders2.a(System.currentTimeMillis());
            dt1VarA2 = responseHeaders2.a();
            iC = dt1VarA2.c();
        }
        cu1Var.b().responseHeadersEnd(cu1Var.a(), dt1VarA2);
        if (this.f9810a && iC == 101) {
            dt1.a aVarH = dt1VarA2.h();
            aVarH.a(kt1.c);
            dt1VarA = aVarH.a();
        } else {
            dt1.a aVarH2 = dt1VarA2.h();
            aVarH2.a(yt1VarC.a(dt1VarA2));
            dt1VarA = aVarH2.a();
        }
        if ("close".equalsIgnoreCase(dt1VarA.k().a(HttpHeaders.HEAD_KEY_CONNECTION)) || "close".equalsIgnoreCase(dt1VarA.a(HttpHeaders.HEAD_KEY_CONNECTION))) {
            vt1VarD.e();
        }
        if ((iC != 204 && iC != 205) || dt1VarA.a().contentLength() <= 0) {
            return dt1VarA;
        }
        throw new ProtocolException("HTTP " + iC + " had non-zero Content-Length: " + dt1VarA.a().contentLength());
    }
}
