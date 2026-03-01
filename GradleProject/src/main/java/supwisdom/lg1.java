package supwisdom;

import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.HeadersMode;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.List;
import java.util.zip.Deflater;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Okio;
import okio.Sink;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import supwisdom.ag1;

/* JADX INFO: compiled from: Spdy3.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lg1 implements og1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f8287a;

    static {
        try {
            f8287a = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(gf1.c.name());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }

    @Override // supwisdom.og1
    public ag1 a(BufferedSource bufferedSource, boolean z) {
        return new a(bufferedSource, z);
    }

    @Override // supwisdom.og1
    public bg1 a(BufferedSink bufferedSink, boolean z) {
        return new b(bufferedSink, z);
    }

    /* JADX INFO: compiled from: Spdy3.java */
    public static final class b implements bg1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSink f8289a;
        public final Buffer b;
        public final BufferedSink c;
        public final boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8290e;

        public b(BufferedSink bufferedSink, boolean z) {
            this.f8289a = bufferedSink;
            this.d = z;
            Deflater deflater = new Deflater();
            deflater.setDictionary(lg1.f8287a);
            Buffer buffer = new Buffer();
            this.b = buffer;
            this.c = Okio.buffer(new DeflaterSink((Sink) buffer, deflater));
        }

        @Override // supwisdom.bg1
        public void a(kg1 kg1Var) {
        }

        @Override // supwisdom.bg1
        public synchronized void a(boolean z, boolean z2, int i, int i2, List<cg1> list) throws IOException {
            if (this.f8290e) {
                throw new IOException("closed");
            }
            a(list);
            int size = (int) (this.b.size() + 10);
            int i3 = (z ? 1 : 0) | (z2 ? 2 : 0);
            this.f8289a.writeInt(-2147287039);
            this.f8289a.writeInt(((i3 & 255) << 24) | (size & X25519Field.M24));
            this.f8289a.writeInt(i & Integer.MAX_VALUE);
            this.f8289a.writeInt(Integer.MAX_VALUE & i2);
            this.f8289a.writeShort(0);
            this.f8289a.writeAll(this.b);
            this.f8289a.flush();
        }

        @Override // supwisdom.bg1
        public synchronized void b(kg1 kg1Var) throws IOException {
            if (this.f8290e) {
                throw new IOException("closed");
            }
            int iC = kg1Var.c();
            this.f8289a.writeInt(-2147287036);
            this.f8289a.writeInt((((iC * 8) + 4) & X25519Field.M24) | 0);
            this.f8289a.writeInt(iC);
            for (int i = 0; i <= 10; i++) {
                if (kg1Var.f(i)) {
                    this.f8289a.writeInt(((kg1Var.a(i) & 255) << 24) | (i & X25519Field.M24));
                    this.f8289a.writeInt(kg1Var.b(i));
                }
            }
            this.f8289a.flush();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            this.f8290e = true;
            gf1.a((Closeable) this.f8289a, (Closeable) this.c);
        }

        @Override // supwisdom.bg1
        public synchronized void connectionPreface() {
        }

        @Override // supwisdom.bg1
        public synchronized void flush() throws IOException {
            if (this.f8290e) {
                throw new IOException("closed");
            }
            this.f8289a.flush();
        }

        @Override // supwisdom.bg1
        public int maxDataLength() {
            return 16383;
        }

        @Override // supwisdom.bg1
        public synchronized void ping(boolean z, int i, int i2) throws IOException {
            if (this.f8290e) {
                throw new IOException("closed");
            }
            if (z != (this.d != ((i & 1) == 1))) {
                throw new IllegalArgumentException("payload != reply");
            }
            this.f8289a.writeInt(-2147287034);
            this.f8289a.writeInt(4);
            this.f8289a.writeInt(i);
            this.f8289a.flush();
        }

        @Override // supwisdom.bg1
        public void pushPromise(int i, int i2, List<cg1> list) throws IOException {
        }

        @Override // supwisdom.bg1
        public synchronized void windowUpdate(int i, long j) throws IOException {
            if (this.f8290e) {
                throw new IOException("closed");
            }
            if (j == 0 || j > 2147483647L) {
                throw new IllegalArgumentException("windowSizeIncrement must be between 1 and 0x7fffffff: " + j);
            }
            this.f8289a.writeInt(-2147287031);
            this.f8289a.writeInt(8);
            this.f8289a.writeInt(i);
            this.f8289a.writeInt((int) j);
            this.f8289a.flush();
        }

        @Override // supwisdom.bg1
        public synchronized void a(int i, ErrorCode errorCode) throws IOException {
            if (!this.f8290e) {
                if (errorCode.spdyRstCode != -1) {
                    this.f8289a.writeInt(-2147287037);
                    this.f8289a.writeInt(8);
                    this.f8289a.writeInt(i & Integer.MAX_VALUE);
                    this.f8289a.writeInt(errorCode.spdyRstCode);
                    this.f8289a.flush();
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // supwisdom.bg1
        public synchronized void a(boolean z, int i, Buffer buffer, int i2) throws IOException {
            a(i, z ? 1 : 0, buffer, i2);
        }

        public void a(int i, int i2, Buffer buffer, int i3) throws IOException {
            if (this.f8290e) {
                throw new IOException("closed");
            }
            long j = i3;
            if (j <= 16777215) {
                this.f8289a.writeInt(i & Integer.MAX_VALUE);
                this.f8289a.writeInt(((i2 & 255) << 24) | (16777215 & i3));
                if (i3 > 0) {
                    this.f8289a.write(buffer, j);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("FRAME_TOO_LARGE max size is 16Mib: " + i3);
        }

        public final void a(List<cg1> list) throws IOException {
            if (this.b.size() == 0) {
                this.c.writeInt(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ByteString byteString = list.get(i).f7209a;
                    this.c.writeInt(byteString.size());
                    this.c.write(byteString);
                    ByteString byteString2 = list.get(i).b;
                    this.c.writeInt(byteString2.size());
                    this.c.write(byteString2);
                }
                this.c.flush();
                return;
            }
            throw new IllegalStateException();
        }

        @Override // supwisdom.bg1
        public synchronized void a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
            if (!this.f8290e) {
                if (errorCode.spdyGoAwayCode != -1) {
                    this.f8289a.writeInt(-2147287033);
                    this.f8289a.writeInt(8);
                    this.f8289a.writeInt(i);
                    this.f8289a.writeInt(errorCode.spdyGoAwayCode);
                    this.f8289a.flush();
                } else {
                    throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
                }
            } else {
                throw new IOException("closed");
            }
        }
    }

    /* JADX INFO: compiled from: Spdy3.java */
    public static final class a implements ag1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSource f8288a;
        public final boolean b;
        public final hg1 c;

        public a(BufferedSource bufferedSource, boolean z) {
            this.f8288a = bufferedSource;
            this.c = new hg1(bufferedSource);
            this.b = z;
        }

        @Override // supwisdom.ag1
        public boolean a(ag1.a aVar) throws IOException {
            try {
                int i = this.f8288a.readInt();
                int i2 = this.f8288a.readInt();
                boolean z = (Integer.MIN_VALUE & i) != 0;
                int i3 = ((-16777216) & i2) >>> 24;
                int i4 = i2 & X25519Field.M24;
                if (!z) {
                    aVar.a((i3 & 1) != 0, i & Integer.MAX_VALUE, this.f8288a, i4);
                    return true;
                }
                int i5 = (2147418112 & i) >>> 16;
                int i6 = i & 65535;
                if (i5 != 3) {
                    throw new ProtocolException("version != 3: " + i5);
                }
                switch (i6) {
                    case 1:
                        g(aVar, i3, i4);
                        return true;
                    case 2:
                        f(aVar, i3, i4);
                        return true;
                    case 3:
                        d(aVar, i3, i4);
                        return true;
                    case 4:
                        e(aVar, i3, i4);
                        return true;
                    case 5:
                    default:
                        this.f8288a.skip(i4);
                        return true;
                    case 6:
                        c(aVar, i3, i4);
                        return true;
                    case 7:
                        a(aVar, i3, i4);
                        return true;
                    case 8:
                        b(aVar, i3, i4);
                        return true;
                    case 9:
                        h(aVar, i3, i4);
                        return true;
                }
            } catch (IOException unused) {
                return false;
            }
        }

        public final void b(ag1.a aVar, int i, int i2) throws IOException {
            aVar.a(false, false, this.f8288a.readInt() & Integer.MAX_VALUE, -1, this.c.a(i2 - 4), HeadersMode.SPDY_HEADERS);
        }

        public final void c(ag1.a aVar, int i, int i2) throws IOException {
            if (i2 != 4) {
                a("TYPE_PING length: %d != 4", Integer.valueOf(i2));
                throw null;
            }
            int i3 = this.f8288a.readInt();
            aVar.ping(this.b == ((i3 & 1) == 1), i3, 0);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.c.a();
        }

        public final void d(ag1.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                a("TYPE_RST_STREAM length: %d != 8", Integer.valueOf(i2));
                throw null;
            }
            int i3 = this.f8288a.readInt() & Integer.MAX_VALUE;
            int i4 = this.f8288a.readInt();
            ErrorCode errorCodeFromSpdy3Rst = ErrorCode.fromSpdy3Rst(i4);
            if (errorCodeFromSpdy3Rst != null) {
                aVar.a(i3, errorCodeFromSpdy3Rst);
            } else {
                a("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i4));
                throw null;
            }
        }

        public final void e(ag1.a aVar, int i, int i2) throws IOException {
            int i3 = this.f8288a.readInt();
            if (i2 != (i3 * 8) + 4) {
                a("TYPE_SETTINGS length: %d != 4 + 8 * %d", Integer.valueOf(i2), Integer.valueOf(i3));
                throw null;
            }
            kg1 kg1Var = new kg1();
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = this.f8288a.readInt();
                kg1Var.a(i5 & X25519Field.M24, ((-16777216) & i5) >>> 24, this.f8288a.readInt());
            }
            aVar.a((i & 1) != 0, kg1Var);
        }

        public final void f(ag1.a aVar, int i, int i2) throws IOException {
            aVar.a(false, (i & 1) != 0, this.f8288a.readInt() & Integer.MAX_VALUE, -1, this.c.a(i2 - 4), HeadersMode.SPDY_REPLY);
        }

        public final void g(ag1.a aVar, int i, int i2) throws IOException {
            int i3 = this.f8288a.readInt() & Integer.MAX_VALUE;
            int i4 = this.f8288a.readInt() & Integer.MAX_VALUE;
            this.f8288a.readShort();
            aVar.a((i & 2) != 0, (i & 1) != 0, i3, i4, this.c.a(i2 - 10), HeadersMode.SPDY_SYN_STREAM);
        }

        public final void h(ag1.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                a("TYPE_WINDOW_UPDATE length: %d != 8", Integer.valueOf(i2));
                throw null;
            }
            int i3 = this.f8288a.readInt() & Integer.MAX_VALUE;
            long j = this.f8288a.readInt() & Integer.MAX_VALUE;
            if (j != 0) {
                aVar.windowUpdate(i3, j);
            } else {
                a("windowSizeIncrement was 0", Long.valueOf(j));
                throw null;
            }
        }

        @Override // supwisdom.ag1
        public void m() {
        }

        public final void a(ag1.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                a("TYPE_GOAWAY length: %d != 8", Integer.valueOf(i2));
                throw null;
            }
            int i3 = this.f8288a.readInt() & Integer.MAX_VALUE;
            int i4 = this.f8288a.readInt();
            ErrorCode errorCodeFromSpdyGoAway = ErrorCode.fromSpdyGoAway(i4);
            if (errorCodeFromSpdyGoAway != null) {
                aVar.a(i3, errorCodeFromSpdyGoAway, ByteString.EMPTY);
            } else {
                a("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i4));
                throw null;
            }
        }

        public static IOException a(String str, Object... objArr) throws IOException {
            throw new IOException(String.format(str, objArr));
        }
    }
}
