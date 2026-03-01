package supwisdom;

import dc.squareup.okhttp3.internal.http2.Header;
import dc.squareup.okhttp3.internal.http2.Http2Codec;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.http2.ErrorCode;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import supwisdom.dt1;
import supwisdom.us1;
import supwisdom.ws1;

/* JADX INFO: compiled from: Http2Codec.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lu1 implements yt1 {
    public static final List<String> f = kt1.a(Http2Codec.CONNECTION, "host", "keep-alive", Http2Codec.PROXY_CONNECTION, Http2Codec.TE, Http2Codec.TRANSFER_ENCODING, Http2Codec.ENCODING, Http2Codec.UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8);
    public static final List<String> g = kt1.a(Http2Codec.CONNECTION, "host", "keep-alive", Http2Codec.PROXY_CONNECTION, Http2Codec.TE, Http2Codec.TRANSFER_ENCODING, Http2Codec.ENCODING, Http2Codec.UPGRADE);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ws1.a f8317a;
    public final vt1 b;
    public final mu1 c;
    public ou1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Protocol f8318e;

    /* JADX INFO: compiled from: Http2Codec.java */
    public class a extends ForwardingSource {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8319a;
        public long b;

        public a(Source source) {
            super(source);
            this.f8319a = false;
            this.b = 0L;
        }

        public final void a(IOException iOException) {
            if (this.f8319a) {
                return;
            }
            this.f8319a = true;
            lu1 lu1Var = lu1.this;
            lu1Var.b.a(false, lu1Var, this.b, iOException);
        }

        @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            a(null);
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            try {
                long j2 = delegate().read(buffer, j);
                if (j2 > 0) {
                    this.b += j2;
                }
                return j2;
            } catch (IOException e2) {
                a(e2);
                throw e2;
            }
        }
    }

    public lu1(zs1 zs1Var, ws1.a aVar, vt1 vt1Var, mu1 mu1Var) {
        this.f8317a = aVar;
        this.b = vt1Var;
        this.c = mu1Var;
        this.f8318e = zs1Var.s().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_2;
    }

    public static List<iu1> b(bt1 bt1Var) {
        us1 us1VarC = bt1Var.c();
        ArrayList arrayList = new ArrayList(us1VarC.c() + 4);
        arrayList.add(new iu1(iu1.f, bt1Var.e()));
        arrayList.add(new iu1(iu1.g, eu1.a(bt1Var.g())));
        String strA = bt1Var.a("Host");
        if (strA != null) {
            arrayList.add(new iu1(iu1.i, strA));
        }
        arrayList.add(new iu1(iu1.h, bt1Var.g().n()));
        int iC = us1VarC.c();
        for (int i = 0; i < iC; i++) {
            ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8(us1VarC.a(i).toLowerCase(Locale.US));
            if (!f.contains(byteStringEncodeUtf8.utf8())) {
                arrayList.add(new iu1(byteStringEncodeUtf8, us1VarC.b(i)));
            }
        }
        return arrayList;
    }

    @Override // supwisdom.yt1
    public Sink a(bt1 bt1Var, long j) {
        return this.d.d();
    }

    @Override // supwisdom.yt1
    public void cancel() {
        ou1 ou1Var = this.d;
        if (ou1Var != null) {
            ou1Var.c(ErrorCode.CANCEL);
        }
    }

    @Override // supwisdom.yt1
    public void finishRequest() throws IOException {
        this.d.d().close();
    }

    @Override // supwisdom.yt1
    public void flushRequest() throws IOException {
        this.c.flush();
    }

    @Override // supwisdom.yt1
    public dt1.a readResponseHeaders(boolean z) throws IOException {
        dt1.a aVarA = a(this.d.j(), this.f8318e);
        if (z && it1.f7984a.a(aVarA) == 100) {
            return null;
        }
        return aVarA;
    }

    @Override // supwisdom.yt1
    public void a(bt1 bt1Var) throws IOException {
        if (this.d != null) {
            return;
        }
        ou1 ou1VarA = this.c.a(b(bt1Var), bt1Var.a() != null);
        this.d = ou1VarA;
        ou1VarA.h().timeout(this.f8317a.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.d.l().timeout(this.f8317a.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
    }

    public static dt1.a a(us1 us1Var, Protocol protocol) throws IOException {
        us1.a aVar = new us1.a();
        int iC = us1Var.c();
        gu1 gu1VarA = null;
        for (int i = 0; i < iC; i++) {
            String strA = us1Var.a(i);
            String strB = us1Var.b(i);
            if (strA.equals(Header.RESPONSE_STATUS_UTF8)) {
                gu1VarA = gu1.a("HTTP/1.1 " + strB);
            } else if (!g.contains(strA)) {
                it1.f7984a.a(aVar, strA, strB);
            }
        }
        if (gu1VarA != null) {
            dt1.a aVar2 = new dt1.a();
            aVar2.a(protocol);
            aVar2.a(gu1VarA.b);
            aVar2.a(gu1VarA.c);
            aVar2.a(aVar.a());
            return aVar2;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // supwisdom.yt1
    public et1 a(dt1 dt1Var) throws IOException {
        vt1 vt1Var = this.b;
        vt1Var.f.responseBodyStart(vt1Var.f9549e);
        return new du1(dt1Var.a("Content-Type"), au1.a(dt1Var), Okio.buffer(new a(this.d.e())));
    }
}
