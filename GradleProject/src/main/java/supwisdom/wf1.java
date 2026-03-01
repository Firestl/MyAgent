package supwisdom;

import com.squareup.okhttp.Protocol;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.internal.http2.Http2Codec;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import supwisdom.oe1;
import supwisdom.ue1;

/* JADX INFO: compiled from: SpdyTransport.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wf1 implements yf1 {
    public static final List<ByteString> d = gf1.a(ByteString.encodeUtf8(Http2Codec.CONNECTION), ByteString.encodeUtf8("host"), ByteString.encodeUtf8("keep-alive"), ByteString.encodeUtf8(Http2Codec.PROXY_CONNECTION), ByteString.encodeUtf8(Http2Codec.TRANSFER_ENCODING));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final List<ByteString> f9620e = gf1.a(ByteString.encodeUtf8(Http2Codec.CONNECTION), ByteString.encodeUtf8("host"), ByteString.encodeUtf8("keep-alive"), ByteString.encodeUtf8(Http2Codec.PROXY_CONNECTION), ByteString.encodeUtf8(Http2Codec.TE), ByteString.encodeUtf8(Http2Codec.TRANSFER_ENCODING), ByteString.encodeUtf8(Http2Codec.ENCODING), ByteString.encodeUtf8(Http2Codec.UPGRADE));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final of1 f9621a;
    public final mg1 b;
    public ng1 c;

    public wf1(of1 of1Var, mg1 mg1Var) {
        this.f9621a = of1Var;
        this.b = mg1Var;
    }

    @Override // supwisdom.yf1
    public Sink a(se1 se1Var, long j) throws IOException {
        return this.c.e();
    }

    @Override // supwisdom.yf1
    public void a() {
    }

    @Override // supwisdom.yf1
    public ue1.b b() throws IOException {
        return a(this.c.d(), this.b.b());
    }

    @Override // supwisdom.yf1
    public boolean c() {
        return true;
    }

    @Override // supwisdom.yf1
    public void finishRequest() throws IOException {
        this.c.e().close();
    }

    @Override // supwisdom.yf1
    public void a(se1 se1Var) throws IOException {
        if (this.c != null) {
            return;
        }
        this.f9621a.p();
        boolean zK = this.f9621a.k();
        String strA = tf1.a(this.f9621a.e().d());
        mg1 mg1Var = this.b;
        ng1 ng1VarA = mg1Var.a(a(se1Var, mg1Var.b(), strA), zK, true);
        this.c = ng1VarA;
        ng1VarA.i().timeout(this.f9621a.f8665a.p(), TimeUnit.MILLISECONDS);
    }

    @Override // supwisdom.yf1
    public void a(uf1 uf1Var) throws IOException {
        uf1Var.a(this.c.e());
    }

    public static List<cg1> a(se1 se1Var, Protocol protocol, String str) {
        oe1 oe1VarC = se1Var.c();
        ArrayList arrayList = new ArrayList(oe1VarC.b() + 10);
        arrayList.add(new cg1(cg1.f7208e, se1Var.e()));
        arrayList.add(new cg1(cg1.f, tf1.a(se1Var.h())));
        String strB = of1.b(se1Var.h());
        if (Protocol.SPDY_3 == protocol) {
            arrayList.add(new cg1(cg1.j, str));
            arrayList.add(new cg1(cg1.i, strB));
        } else if (Protocol.HTTP_2 == protocol) {
            arrayList.add(new cg1(cg1.h, strB));
        } else {
            throw new AssertionError();
        }
        arrayList.add(new cg1(cg1.g, se1Var.h().getProtocol()));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int iB = oe1VarC.b();
        for (int i = 0; i < iB; i++) {
            ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8(oe1VarC.a(i).toLowerCase(Locale.US));
            String strB2 = oe1VarC.b(i);
            if (!a(protocol, byteStringEncodeUtf8) && !byteStringEncodeUtf8.equals(cg1.f7208e) && !byteStringEncodeUtf8.equals(cg1.f) && !byteStringEncodeUtf8.equals(cg1.g) && !byteStringEncodeUtf8.equals(cg1.h) && !byteStringEncodeUtf8.equals(cg1.i) && !byteStringEncodeUtf8.equals(cg1.j)) {
                if (linkedHashSet.add(byteStringEncodeUtf8)) {
                    arrayList.add(new cg1(byteStringEncodeUtf8, strB2));
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        }
                        if (((cg1) arrayList.get(i2)).f7209a.equals(byteStringEncodeUtf8)) {
                            arrayList.set(i2, new cg1(byteStringEncodeUtf8, a(((cg1) arrayList.get(i2)).b.utf8(), strB2)));
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }

    public static String a(String str, String str2) {
        return str + (char) 0 + str2;
    }

    public static ue1.b a(List<cg1> list, Protocol protocol) throws IOException {
        oe1.b bVar = new oe1.b();
        bVar.c(rf1.f9058e, protocol.toString());
        int size = list.size();
        String str = null;
        String str2 = "HTTP/1.1";
        for (int i = 0; i < size; i++) {
            ByteString byteString = list.get(i).f7209a;
            String strUtf8 = list.get(i).b.utf8();
            int i2 = 0;
            while (i2 < strUtf8.length()) {
                int iIndexOf = strUtf8.indexOf(0, i2);
                if (iIndexOf == -1) {
                    iIndexOf = strUtf8.length();
                }
                String strSubstring = strUtf8.substring(i2, iIndexOf);
                if (byteString.equals(cg1.d)) {
                    str = strSubstring;
                } else if (byteString.equals(cg1.j)) {
                    str2 = strSubstring;
                } else if (!a(protocol, byteString)) {
                    bVar.a(byteString.utf8(), strSubstring);
                }
                i2 = iIndexOf + 1;
            }
        }
        if (str != null) {
            xf1 xf1VarA = xf1.a(str2 + Operators.SPACE_STR + str);
            ue1.b bVar2 = new ue1.b();
            bVar2.a(protocol);
            bVar2.a(xf1VarA.b);
            bVar2.a(xf1VarA.c);
            bVar2.a(bVar.a());
            return bVar2;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // supwisdom.yf1
    public ve1 a(ue1 ue1Var) throws IOException {
        return new sf1(ue1Var.g(), Okio.buffer(this.c.f()));
    }

    public static boolean a(Protocol protocol, ByteString byteString) {
        if (protocol == Protocol.SPDY_3) {
            return d.contains(byteString);
        }
        if (protocol == Protocol.HTTP_2) {
            return f9620e.contains(byteString);
        }
        throw new AssertionError(protocol);
    }
}
