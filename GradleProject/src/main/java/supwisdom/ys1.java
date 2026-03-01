package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

/* JADX INFO: compiled from: MultipartBody.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ys1 extends ct1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final xs1 f9911e = xs1.a("multipart/mixed");
    public static final xs1 f;
    public static final byte[] g;
    public static final byte[] h;
    public static final byte[] i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteString f9912a;
    public final xs1 b;
    public final List<b> c;
    public long d = -1;

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ByteString f9913a;
        public xs1 b;
        public final List<b> c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a a(xs1 xs1Var) {
            if (xs1Var == null) {
                throw new NullPointerException("type == null");
            }
            if (xs1Var.c().equals("multipart")) {
                this.b = xs1Var;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + xs1Var);
        }

        public a(String str) {
            this.b = ys1.f9911e;
            this.c = new ArrayList();
            this.f9913a = ByteString.encodeUtf8(str);
        }

        public a a(@Nullable us1 us1Var, ct1 ct1Var) {
            a(b.a(us1Var, ct1Var));
            return this;
        }

        public a a(String str, String str2) {
            a(b.a(str, str2));
            return this;
        }

        public a a(String str, @Nullable String str2, ct1 ct1Var) {
            a(b.a(str, str2, ct1Var));
            return this;
        }

        public a a(b bVar) {
            if (bVar != null) {
                this.c.add(bVar);
                return this;
            }
            throw new NullPointerException("part == null");
        }

        public ys1 a() {
            if (!this.c.isEmpty()) {
                return new ys1(this.f9913a, this.b, this.c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    static {
        xs1.a("multipart/alternative");
        xs1.a("multipart/digest");
        xs1.a("multipart/parallel");
        f = xs1.a("multipart/form-data");
        g = new byte[]{58, 32};
        h = new byte[]{13, 10};
        i = new byte[]{45, 45};
    }

    public ys1(ByteString byteString, xs1 xs1Var, List<b> list) {
        this.f9912a = byteString;
        this.b = xs1.a(xs1Var + "; boundary=" + byteString.utf8());
        this.c = kt1.a(list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long a(@Nullable BufferedSink bufferedSink, boolean z) throws IOException {
        Buffer buffer;
        if (z) {
            bufferedSink = new Buffer();
            buffer = bufferedSink;
        } else {
            buffer = 0;
        }
        int size = this.c.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.c.get(i2);
            us1 us1Var = bVar.f9914a;
            ct1 ct1Var = bVar.b;
            bufferedSink.write(i);
            bufferedSink.write(this.f9912a);
            bufferedSink.write(h);
            if (us1Var != null) {
                int iC = us1Var.c();
                for (int i3 = 0; i3 < iC; i3++) {
                    bufferedSink.writeUtf8(us1Var.a(i3)).write(g).writeUtf8(us1Var.b(i3)).write(h);
                }
            }
            xs1 xs1VarContentType = ct1Var.contentType();
            if (xs1VarContentType != null) {
                bufferedSink.writeUtf8("Content-Type: ").writeUtf8(xs1VarContentType.toString()).write(h);
            }
            long jContentLength = ct1Var.contentLength();
            if (jContentLength != -1) {
                bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(jContentLength).write(h);
            } else if (z) {
                buffer.clear();
                return -1L;
            }
            bufferedSink.write(h);
            if (z) {
                j += jContentLength;
            } else {
                ct1Var.writeTo(bufferedSink);
            }
            bufferedSink.write(h);
        }
        bufferedSink.write(i);
        bufferedSink.write(this.f9912a);
        bufferedSink.write(i);
        bufferedSink.write(h);
        if (!z) {
            return j;
        }
        long size2 = j + buffer.size();
        buffer.clear();
        return size2;
    }

    @Override // supwisdom.ct1
    public long contentLength() throws IOException {
        long j = this.d;
        if (j != -1) {
            return j;
        }
        long jA = a((BufferedSink) null, true);
        this.d = jA;
        return jA;
    }

    @Override // supwisdom.ct1
    public xs1 contentType() {
        return this.b;
    }

    @Override // supwisdom.ct1
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        a(bufferedSink, false);
    }

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public final us1 f9914a;
        public final ct1 b;

        public b(@Nullable us1 us1Var, ct1 ct1Var) {
            this.f9914a = us1Var;
            this.b = ct1Var;
        }

        public static b a(@Nullable us1 us1Var, ct1 ct1Var) {
            if (ct1Var == null) {
                throw new NullPointerException("body == null");
            }
            if (us1Var != null && us1Var.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (us1Var == null || us1Var.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH) == null) {
                return new b(us1Var, ct1Var);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static b a(String str, String str2) {
            return a(str, null, ct1.create((xs1) null, str2));
        }

        public static b a(String str, @Nullable String str2, ct1 ct1Var) {
            if (str != null) {
                StringBuilder sb = new StringBuilder("form-data; name=");
                ys1.a(sb, str);
                if (str2 != null) {
                    sb.append("; filename=");
                    ys1.a(sb, str2);
                }
                return a(us1.a(HttpHeaders.HEAD_KEY_CONTENT_DISPOSITION, sb.toString()), ct1Var);
            }
            throw new NullPointerException("name == null");
        }
    }

    public static StringBuilder a(StringBuilder sb, String str) {
        sb.append(Operators.QUOTE);
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\n') {
                sb.append("%0A");
            } else if (cCharAt == '\r') {
                sb.append("%0D");
            } else if (cCharAt != '\"') {
                sb.append(cCharAt);
            } else {
                sb.append("%22");
            }
        }
        sb.append(Operators.QUOTE);
        return sb;
    }
}
