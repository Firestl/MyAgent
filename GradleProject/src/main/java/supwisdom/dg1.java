package supwisdom;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.lzy.okgo.cookie.SerializableCookie;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.http.WXHttpUtil;
import dc.squareup.okhttp3.internal.http2.Http2Codec;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final cg1[] f7337a = {new cg1(cg1.h, ""), new cg1(cg1.f7208e, "GET"), new cg1(cg1.f7208e, "POST"), new cg1(cg1.f, "/"), new cg1(cg1.f, "/index.html"), new cg1(cg1.g, "http"), new cg1(cg1.g, "https"), new cg1(cg1.d, "200"), new cg1(cg1.d, "204"), new cg1(cg1.d, "206"), new cg1(cg1.d, "304"), new cg1(cg1.d, "400"), new cg1(cg1.d, "404"), new cg1(cg1.d, "500"), new cg1("accept-charset", ""), new cg1("accept-encoding", HttpHeaders.HEAD_VALUE_ACCEPT_ENCODING), new cg1("accept-language", ""), new cg1("accept-ranges", ""), new cg1("accept", ""), new cg1("access-control-allow-origin", ""), new cg1("age", ""), new cg1("allow", ""), new cg1("authorization", ""), new cg1("cache-control", ""), new cg1("content-disposition", ""), new cg1("content-encoding", ""), new cg1("content-language", ""), new cg1("content-length", ""), new cg1("content-location", ""), new cg1("content-range", ""), new cg1("content-type", ""), new cg1(SerializableCookie.COOKIE, ""), new cg1("date", ""), new cg1("etag", ""), new cg1("expect", ""), new cg1("expires", ""), new cg1(RemoteMessageConst.FROM, ""), new cg1("host", ""), new cg1("if-match", ""), new cg1("if-modified-since", ""), new cg1("if-none-match", ""), new cg1("if-range", ""), new cg1("if-unmodified-since", ""), new cg1("last-modified", ""), new cg1(AbsURIAdapter.LINK, ""), new cg1("location", ""), new cg1("max-forwards", ""), new cg1("proxy-authenticate", ""), new cg1("proxy-authorization", ""), new cg1(AbsoluteConst.PULL_REFRESH_RANGE, ""), new cg1("referer", ""), new cg1("refresh", ""), new cg1("retry-after", ""), new cg1("server", ""), new cg1("set-cookie", ""), new cg1("strict-transport-security", ""), new cg1(Http2Codec.TRANSFER_ENCODING, ""), new cg1(WXHttpUtil.KEY_USER_AGENT, ""), new cg1("vary", ""), new cg1("via", ""), new cg1("www-authenticate", "")};
    public static final Map<ByteString, Integer> b = c();

    /* JADX INFO: compiled from: Hpack.java */
    public static final class a {
        public final BufferedSource b;
        public int c;
        public int d;
        public int f;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<cg1> f7338a = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public cg1[] f7339e = new cg1[8];
        public int g = 0;
        public int h = 0;

        public a(int i, Source source) {
            this.f = r0.length - 1;
            this.c = i;
            this.d = i;
            this.b = Okio.buffer(source);
        }

        public final void a() {
            int i = this.d;
            int i2 = this.h;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    b(i2 - i);
                }
            }
        }

        public final void b() {
            this.f7338a.clear();
            Arrays.fill(this.f7339e, (Object) null);
            this.f = this.f7339e.length - 1;
            this.g = 0;
            this.h = 0;
        }

        public List<cg1> c() {
            ArrayList arrayList = new ArrayList(this.f7338a);
            this.f7338a.clear();
            return arrayList;
        }

        public void d(int i) {
            this.c = i;
            this.d = i;
            a();
        }

        public final boolean e(int i) {
            return i >= 0 && i <= dg1.f7337a.length - 1;
        }

        public void f() throws IOException {
            while (!this.b.exhausted()) {
                int i = this.b.readByte() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((i & 128) == 128) {
                    f(a(i, 127) - 1);
                } else if (i == 64) {
                    g();
                } else if ((i & 64) == 64) {
                    g(a(i, 63) - 1);
                } else if ((i & 32) == 32) {
                    int iA = a(i, 31);
                    this.d = iA;
                    if (iA < 0 || iA > this.c) {
                        throw new IOException("Invalid dynamic table size update " + this.d);
                    }
                    a();
                } else if (i == 16 || i == 0) {
                    h();
                } else {
                    h(a(i, 15) - 1);
                }
            }
        }

        public final void g(int i) throws IOException {
            a(-1, new cg1(c(i), e()));
        }

        public final void h(int i) throws IOException {
            this.f7338a.add(new cg1(c(i), e()));
        }

        public ByteString e() throws IOException {
            int iD = d();
            boolean z = (iD & 128) == 128;
            int iA = a(iD, 127);
            return z ? ByteString.of(fg1.b().a(this.b.readByteArray(iA))) : this.b.readByteString(iA);
        }

        public final ByteString c(int i) {
            return e(i) ? dg1.f7337a[i].f7209a : this.f7339e[a(i - dg1.f7337a.length)].f7209a;
        }

        public final int a(int i) {
            return this.f + 1 + i;
        }

        public final int d() throws IOException {
            return this.b.readByte() & 255;
        }

        public final void g() throws IOException {
            ByteString byteStringE = e();
            dg1.a(byteStringE);
            a(-1, new cg1(byteStringE, e()));
        }

        public final void h() throws IOException {
            ByteString byteStringE = e();
            dg1.a(byteStringE);
            this.f7338a.add(new cg1(byteStringE, e()));
        }

        public final void a(int i, cg1 cg1Var) {
            this.f7338a.add(cg1Var);
            int i2 = cg1Var.c;
            if (i != -1) {
                i2 -= this.f7339e[a(i)].c;
            }
            int i3 = this.d;
            if (i2 > i3) {
                b();
                return;
            }
            int iB = b((this.h + i2) - i3);
            if (i == -1) {
                int i4 = this.g + 1;
                cg1[] cg1VarArr = this.f7339e;
                if (i4 > cg1VarArr.length) {
                    cg1[] cg1VarArr2 = new cg1[cg1VarArr.length * 2];
                    System.arraycopy(cg1VarArr, 0, cg1VarArr2, cg1VarArr.length, cg1VarArr.length);
                    this.f = this.f7339e.length - 1;
                    this.f7339e = cg1VarArr2;
                }
                int i5 = this.f;
                this.f = i5 - 1;
                this.f7339e[i5] = cg1Var;
                this.g++;
            } else {
                this.f7339e[i + a(i) + iB] = cg1Var;
            }
            this.h += i2;
        }

        public final int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f7339e.length;
                while (true) {
                    length--;
                    if (length < this.f || i <= 0) {
                        break;
                    }
                    cg1[] cg1VarArr = this.f7339e;
                    i -= cg1VarArr[length].c;
                    this.h -= cg1VarArr[length].c;
                    this.g--;
                    i2++;
                }
                cg1[] cg1VarArr2 = this.f7339e;
                int i3 = this.f;
                System.arraycopy(cg1VarArr2, i3 + 1, cg1VarArr2, i3 + 1 + i2, this.g);
                this.f += i2;
            }
            return i2;
        }

        public final void f(int i) throws IOException {
            if (e(i)) {
                this.f7338a.add(dg1.f7337a[i]);
                return;
            }
            int iA = a(i - dg1.f7337a.length);
            if (iA >= 0) {
                cg1[] cg1VarArr = this.f7339e;
                if (iA <= cg1VarArr.length - 1) {
                    this.f7338a.add(cg1VarArr[iA]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        public int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int iD = d();
                if ((iD & 128) == 0) {
                    return i2 + (iD << i4);
                }
                i2 += (iD & 127) << i4;
                i4 += 7;
            }
        }
    }

    public static Map<ByteString, Integer> c() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f7337a.length);
        int i = 0;
        while (true) {
            cg1[] cg1VarArr = f7337a;
            if (i >= cg1VarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(cg1VarArr[i].f7209a)) {
                linkedHashMap.put(f7337a[i].f7209a, Integer.valueOf(i));
            }
            i++;
        }
    }

    public static /* synthetic */ ByteString a(ByteString byteString) throws IOException {
        b(byteString);
        return byteString;
    }

    public static ByteString b(ByteString byteString) throws IOException {
        int size = byteString.size();
        for (int i = 0; i < size; i++) {
            byte b2 = byteString.getByte(i);
            if (b2 >= 65 && b2 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }

    /* JADX INFO: compiled from: Hpack.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Buffer f7340a;

        public b(Buffer buffer) {
            this.f7340a = buffer;
        }

        public void a(List<cg1> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ByteString asciiLowercase = list.get(i).f7209a.toAsciiLowercase();
                Integer num = (Integer) dg1.b.get(asciiLowercase);
                if (num != null) {
                    a(num.intValue() + 1, 15, 0);
                    a(list.get(i).b);
                } else {
                    this.f7340a.writeByte(0);
                    a(asciiLowercase);
                    a(list.get(i).b);
                }
            }
        }

        public void a(int i, int i2, int i3) throws IOException {
            if (i < i2) {
                this.f7340a.writeByte(i | i3);
                return;
            }
            this.f7340a.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f7340a.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.f7340a.writeByte(i4);
        }

        public void a(ByteString byteString) throws IOException {
            a(byteString.size(), 127, 0);
            this.f7340a.write(byteString);
        }
    }
}
