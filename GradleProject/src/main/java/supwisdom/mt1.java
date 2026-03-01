package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import supwisdom.dt1;
import supwisdom.ot1;
import supwisdom.us1;
import supwisdom.ws1;

/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mt1 implements ws1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final pt1 f8429a;

    /* JADX INFO: compiled from: CacheInterceptor.java */
    public class a implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8430a;
        public final /* synthetic */ BufferedSource b;
        public final /* synthetic */ nt1 c;
        public final /* synthetic */ BufferedSink d;

        public a(mt1 mt1Var, BufferedSource bufferedSource, nt1 nt1Var, BufferedSink bufferedSink) {
            this.b = bufferedSource;
            this.c = nt1Var;
            this.d = bufferedSink;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.f8430a && !kt1.a(this, 100, TimeUnit.MILLISECONDS)) {
                this.f8430a = true;
                this.c.abort();
            }
            this.b.close();
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            try {
                long j2 = this.b.read(buffer, j);
                if (j2 != -1) {
                    buffer.copyTo(this.d.buffer(), buffer.size() - j2, j2);
                    this.d.emitCompleteSegments();
                    return j2;
                }
                if (!this.f8430a) {
                    this.f8430a = true;
                    this.d.close();
                }
                return -1L;
            } catch (IOException e2) {
                if (!this.f8430a) {
                    this.f8430a = true;
                    this.c.abort();
                }
                throw e2;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.b.timeout();
        }
    }

    public mt1(pt1 pt1Var) {
        this.f8429a = pt1Var;
    }

    public static dt1 a(dt1 dt1Var) {
        if (dt1Var == null || dt1Var.a() == null) {
            return dt1Var;
        }
        dt1.a aVarH = dt1Var.h();
        aVarH.a((et1) null);
        return aVarH.a();
    }

    public static boolean b(String str) {
        return (HttpHeaders.HEAD_KEY_CONNECTION.equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws IOException {
        pt1 pt1Var = this.f8429a;
        dt1 dt1VarB = pt1Var != null ? pt1Var.b(aVar.request()) : null;
        ot1 ot1VarC = new ot1.a(System.currentTimeMillis(), aVar.request(), dt1VarB).c();
        bt1 bt1Var = ot1VarC.f8710a;
        dt1 dt1Var = ot1VarC.b;
        pt1 pt1Var2 = this.f8429a;
        if (pt1Var2 != null) {
            pt1Var2.a(ot1VarC);
        }
        if (dt1VarB != null && dt1Var == null) {
            kt1.a(dt1VarB.a());
        }
        if (bt1Var == null && dt1Var == null) {
            dt1.a aVar2 = new dt1.a();
            aVar2.a(aVar.request());
            aVar2.a(Protocol.HTTP_1_1);
            aVar2.a(504);
            aVar2.a("Unsatisfiable Request (only-if-cached)");
            aVar2.a(kt1.c);
            aVar2.b(-1L);
            aVar2.a(System.currentTimeMillis());
            return aVar2.a();
        }
        if (bt1Var == null) {
            dt1.a aVarH = dt1Var.h();
            aVarH.a(a(dt1Var));
            return aVarH.a();
        }
        try {
            dt1 dt1VarA = aVar.a(bt1Var);
            if (dt1VarA == null && dt1VarB != null) {
            }
            if (dt1Var != null) {
                if (dt1VarA.c() == 304) {
                    dt1.a aVarH2 = dt1Var.h();
                    aVarH2.a(a(dt1Var.e(), dt1VarA.e()));
                    aVarH2.b(dt1VarA.l());
                    aVarH2.a(dt1VarA.j());
                    aVarH2.a(a(dt1Var));
                    aVarH2.c(a(dt1VarA));
                    dt1 dt1VarA2 = aVarH2.a();
                    dt1VarA.a().close();
                    this.f8429a.trackConditionalCacheHit();
                    this.f8429a.a(dt1Var, dt1VarA2);
                    return dt1VarA2;
                }
                kt1.a(dt1Var.a());
            }
            dt1.a aVarH3 = dt1VarA.h();
            aVarH3.a(a(dt1Var));
            aVarH3.c(a(dt1VarA));
            dt1 dt1VarA3 = aVarH3.a();
            if (this.f8429a != null) {
                if (au1.b(dt1VarA3) && ot1.a(dt1VarA3, bt1Var)) {
                    return a(this.f8429a.a(dt1VarA3), dt1VarA3);
                }
                if (bu1.a(bt1Var.e())) {
                    try {
                        this.f8429a.a(bt1Var);
                    } catch (IOException unused) {
                    }
                }
            }
            return dt1VarA3;
        } finally {
            if (dt1VarB != null) {
                kt1.a(dt1VarB.a());
            }
        }
    }

    public final dt1 a(nt1 nt1Var, dt1 dt1Var) throws IOException {
        Sink sinkBody;
        if (nt1Var == null || (sinkBody = nt1Var.body()) == null) {
            return dt1Var;
        }
        a aVar = new a(this, dt1Var.a().source(), nt1Var, Okio.buffer(sinkBody));
        String strA = dt1Var.a("Content-Type");
        long jContentLength = dt1Var.a().contentLength();
        dt1.a aVarH = dt1Var.h();
        aVarH.a(new du1(strA, jContentLength, Okio.buffer(aVar)));
        return aVarH.a();
    }

    public static us1 a(us1 us1Var, us1 us1Var2) {
        us1.a aVar = new us1.a();
        int iC = us1Var.c();
        for (int i = 0; i < iC; i++) {
            String strA = us1Var.a(i);
            String strB = us1Var.b(i);
            if ((!"Warning".equalsIgnoreCase(strA) || !strB.startsWith("1")) && (a(strA) || !b(strA) || us1Var2.a(strA) == null)) {
                it1.f7984a.a(aVar, strA, strB);
            }
        }
        int iC2 = us1Var2.c();
        for (int i2 = 0; i2 < iC2; i2++) {
            String strA2 = us1Var2.a(i2);
            if (!a(strA2) && b(strA2)) {
                it1.f7984a.a(aVar, strA2, us1Var2.b(i2));
            }
        }
        return aVar.a();
    }

    public static boolean a(String str) {
        return HttpHeaders.HEAD_KEY_CONTENT_LENGTH.equalsIgnoreCase(str) || HttpHeaders.HEAD_KEY_CONTENT_ENCODING.equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }
}
