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
/* JADX INFO: loaded from: classes3.dex */
public final class ju1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final iu1[] f8089a = {new iu1(iu1.i, ""), new iu1(iu1.f, "GET"), new iu1(iu1.f, "POST"), new iu1(iu1.g, "/"), new iu1(iu1.g, "/index.html"), new iu1(iu1.h, "http"), new iu1(iu1.h, "https"), new iu1(iu1.f7987e, "200"), new iu1(iu1.f7987e, "204"), new iu1(iu1.f7987e, "206"), new iu1(iu1.f7987e, "304"), new iu1(iu1.f7987e, "400"), new iu1(iu1.f7987e, "404"), new iu1(iu1.f7987e, "500"), new iu1("accept-charset", ""), new iu1("accept-encoding", HttpHeaders.HEAD_VALUE_ACCEPT_ENCODING), new iu1("accept-language", ""), new iu1("accept-ranges", ""), new iu1("accept", ""), new iu1("access-control-allow-origin", ""), new iu1("age", ""), new iu1("allow", ""), new iu1("authorization", ""), new iu1("cache-control", ""), new iu1("content-disposition", ""), new iu1("content-encoding", ""), new iu1("content-language", ""), new iu1("content-length", ""), new iu1("content-location", ""), new iu1("content-range", ""), new iu1("content-type", ""), new iu1(SerializableCookie.COOKIE, ""), new iu1("date", ""), new iu1("etag", ""), new iu1("expect", ""), new iu1("expires", ""), new iu1(RemoteMessageConst.FROM, ""), new iu1("host", ""), new iu1("if-match", ""), new iu1("if-modified-since", ""), new iu1("if-none-match", ""), new iu1("if-range", ""), new iu1("if-unmodified-since", ""), new iu1("last-modified", ""), new iu1(AbsURIAdapter.LINK, ""), new iu1("location", ""), new iu1("max-forwards", ""), new iu1("proxy-authenticate", ""), new iu1("proxy-authorization", ""), new iu1(AbsoluteConst.PULL_REFRESH_RANGE, ""), new iu1("referer", ""), new iu1("refresh", ""), new iu1("retry-after", ""), new iu1("server", ""), new iu1("set-cookie", ""), new iu1("strict-transport-security", ""), new iu1(Http2Codec.TRANSFER_ENCODING, ""), new iu1(WXHttpUtil.KEY_USER_AGENT, ""), new iu1("vary", ""), new iu1("via", ""), new iu1("www-authenticate", "")};
    public static final Map<ByteString, Integer> b = a();

    /* JADX INFO: compiled from: Hpack.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<iu1> f8090a;
        public final BufferedSource b;
        public final int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public iu1[] f8091e;
        public int f;
        public int g;
        public int h;

        public a(int i, Source source) {
            this(i, i, source);
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
            Arrays.fill(this.f8091e, (Object) null);
            this.f = this.f8091e.length - 1;
            this.g = 0;
            this.h = 0;
        }

        public List<iu1> c() {
            ArrayList arrayList = new ArrayList(this.f8090a);
            this.f8090a.clear();
            return arrayList;
        }

        public final boolean d(int i) {
            return i >= 0 && i <= ju1.f8089a.length - 1;
        }

        public final void e(int i) throws IOException {
            if (d(i)) {
                this.f8090a.add(ju1.f8089a[i]);
                return;
            }
            int iA = a(i - ju1.f8089a.length);
            if (iA >= 0) {
                iu1[] iu1VarArr = this.f8091e;
                if (iA < iu1VarArr.length) {
                    this.f8090a.add(iu1VarArr[iA]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        public void f() throws IOException {
            while (!this.b.exhausted()) {
                int i = this.b.readByte() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((i & 128) == 128) {
                    e(a(i, 127) - 1);
                } else if (i == 64) {
                    g();
                } else if ((i & 64) == 64) {
                    f(a(i, 63) - 1);
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
                    g(a(i, 15) - 1);
                }
            }
        }

        public final void g(int i) throws IOException {
            this.f8090a.add(new iu1(c(i), e()));
        }

        public final void h() throws IOException {
            ByteString byteStringE = e();
            ju1.a(byteStringE);
            this.f8090a.add(new iu1(byteStringE, e()));
        }

        public a(int i, int i2, Source source) {
            this.f8090a = new ArrayList();
            this.f8091e = new iu1[8];
            this.f = r0.length - 1;
            this.g = 0;
            this.h = 0;
            this.c = i;
            this.d = i2;
            this.b = Okio.buffer(source);
        }

        public final int d() throws IOException {
            return this.b.readByte() & 255;
        }

        public final ByteString c(int i) throws IOException {
            if (d(i)) {
                return ju1.f8089a[i].f7988a;
            }
            int iA = a(i - ju1.f8089a.length);
            if (iA >= 0) {
                iu1[] iu1VarArr = this.f8091e;
                if (iA < iu1VarArr.length) {
                    return iu1VarArr[iA].f7988a;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        public final int a(int i) {
            return this.f + 1 + i;
        }

        public final void g() throws IOException {
            ByteString byteStringE = e();
            ju1.a(byteStringE);
            a(-1, new iu1(byteStringE, e()));
        }

        public final void a(int i, iu1 iu1Var) {
            this.f8090a.add(iu1Var);
            int i2 = iu1Var.c;
            if (i != -1) {
                i2 -= this.f8091e[a(i)].c;
            }
            int i3 = this.d;
            if (i2 > i3) {
                b();
                return;
            }
            int iB = b((this.h + i2) - i3);
            if (i == -1) {
                int i4 = this.g + 1;
                iu1[] iu1VarArr = this.f8091e;
                if (i4 > iu1VarArr.length) {
                    iu1[] iu1VarArr2 = new iu1[iu1VarArr.length * 2];
                    System.arraycopy(iu1VarArr, 0, iu1VarArr2, iu1VarArr.length, iu1VarArr.length);
                    this.f = this.f8091e.length - 1;
                    this.f8091e = iu1VarArr2;
                }
                int i5 = this.f;
                this.f = i5 - 1;
                this.f8091e[i5] = iu1Var;
                this.g++;
            } else {
                this.f8091e[i + a(i) + iB] = iu1Var;
            }
            this.h += i2;
        }

        public final int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f8091e.length;
                while (true) {
                    length--;
                    if (length < this.f || i <= 0) {
                        break;
                    }
                    iu1[] iu1VarArr = this.f8091e;
                    i -= iu1VarArr[length].c;
                    this.h -= iu1VarArr[length].c;
                    this.g--;
                    i2++;
                }
                iu1[] iu1VarArr2 = this.f8091e;
                int i3 = this.f;
                System.arraycopy(iu1VarArr2, i3 + 1, iu1VarArr2, i3 + 1 + i2, this.g);
                this.f += i2;
            }
            return i2;
        }

        public ByteString e() throws IOException {
            int iD = d();
            boolean z = (iD & 128) == 128;
            int iA = a(iD, 127);
            if (z) {
                return ByteString.of(qu1.b().a(this.b.readByteArray(iA)));
            }
            return this.b.readByteString(iA);
        }

        public final void f(int i) throws IOException {
            a(-1, new iu1(c(i), e()));
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

    /* JADX INFO: compiled from: Hpack.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Buffer f8092a;
        public final boolean b;
        public int c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8093e;
        public iu1[] f;
        public int g;
        public int h;
        public int i;

        public b(Buffer buffer) {
            this(4096, true, buffer);
        }

        public final int a(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f.length;
                while (true) {
                    length--;
                    if (length < this.g || i <= 0) {
                        break;
                    }
                    iu1[] iu1VarArr = this.f;
                    i -= iu1VarArr[length].c;
                    this.i -= iu1VarArr[length].c;
                    this.h--;
                    i2++;
                }
                iu1[] iu1VarArr2 = this.f;
                int i3 = this.g;
                System.arraycopy(iu1VarArr2, i3 + 1, iu1VarArr2, i3 + 1 + i2, this.h);
                iu1[] iu1VarArr3 = this.f;
                int i4 = this.g;
                Arrays.fill(iu1VarArr3, i4 + 1, i4 + 1 + i2, (Object) null);
                this.g += i2;
            }
            return i2;
        }

        public final void b() {
            Arrays.fill(this.f, (Object) null);
            this.g = this.f.length - 1;
            this.h = 0;
            this.i = 0;
        }

        public b(int i, boolean z, Buffer buffer) {
            this.c = Integer.MAX_VALUE;
            this.f = new iu1[8];
            this.g = r0.length - 1;
            this.h = 0;
            this.i = 0;
            this.f8093e = i;
            this.b = z;
            this.f8092a = buffer;
        }

        public void b(int i) {
            int iMin = Math.min(i, 16384);
            int i2 = this.f8093e;
            if (i2 == iMin) {
                return;
            }
            if (iMin < i2) {
                this.c = Math.min(this.c, iMin);
            }
            this.d = true;
            this.f8093e = iMin;
            a();
        }

        public final void a(iu1 iu1Var) {
            int i = iu1Var.c;
            int i2 = this.f8093e;
            if (i > i2) {
                b();
                return;
            }
            a((this.i + i) - i2);
            int i3 = this.h + 1;
            iu1[] iu1VarArr = this.f;
            if (i3 > iu1VarArr.length) {
                iu1[] iu1VarArr2 = new iu1[iu1VarArr.length * 2];
                System.arraycopy(iu1VarArr, 0, iu1VarArr2, iu1VarArr.length, iu1VarArr.length);
                this.g = this.f.length - 1;
                this.f = iu1VarArr2;
            }
            int i4 = this.g;
            this.g = i4 - 1;
            this.f[i4] = iu1Var;
            this.h++;
            this.i += i;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(java.util.List<supwisdom.iu1> r14) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 238
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.ju1.b.a(java.util.List):void");
        }

        public void a(int i, int i2, int i3) {
            if (i < i2) {
                this.f8092a.writeByte(i | i3);
                return;
            }
            this.f8092a.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f8092a.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.f8092a.writeByte(i4);
        }

        public void a(ByteString byteString) throws IOException {
            if (this.b && qu1.b().a(byteString) < byteString.size()) {
                Buffer buffer = new Buffer();
                qu1.b().a(byteString, buffer);
                ByteString byteString2 = buffer.readByteString();
                a(byteString2.size(), 127, 128);
                this.f8092a.write(byteString2);
                return;
            }
            a(byteString.size(), 127, 0);
            this.f8092a.write(byteString);
        }

        public final void a() {
            int i = this.f8093e;
            int i2 = this.i;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    a(i2 - i);
                }
            }
        }
    }

    public static Map<ByteString, Integer> a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f8089a.length);
        int i = 0;
        while (true) {
            iu1[] iu1VarArr = f8089a;
            if (i >= iu1VarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(iu1VarArr[i].f7988a)) {
                linkedHashMap.put(f8089a[i].f7988a, Integer.valueOf(i));
            }
            i++;
        }
    }

    public static ByteString a(ByteString byteString) throws IOException {
        int size = byteString.size();
        for (int i = 0; i < size; i++) {
            byte b2 = byteString.getByte(i);
            if (b2 >= 65 && b2 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }
}
