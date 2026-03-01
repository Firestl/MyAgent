package supwisdom;

import com.dmcbig.mediapicker.PickerConfig;
import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.HeadersMode;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import supwisdom.ag1;
import supwisdom.dg1;

/* JADX INFO: compiled from: Http2.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eg1 implements og1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f7493a = Logger.getLogger(b.class.getName());
    public static final ByteString b = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* JADX INFO: compiled from: Http2.java */
    public static final class a implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSource f7494a;
        public int b;
        public byte c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7495e;
        public short f;

        public a(BufferedSource bufferedSource) {
            this.f7494a = bufferedSource;
        }

        public final void a() throws IOException {
            int i = this.d;
            int iB = eg1.b(this.f7494a);
            this.f7495e = iB;
            this.b = iB;
            byte b = (byte) (this.f7494a.readByte() & 255);
            this.c = (byte) (this.f7494a.readByte() & 255);
            if (eg1.f7493a.isLoggable(Level.FINE)) {
                eg1.f7493a.fine(b.a(true, this.d, this.b, b, this.c));
            }
            int i2 = this.f7494a.readInt() & Integer.MAX_VALUE;
            this.d = i2;
            if (b != 9) {
                eg1.a("%s != TYPE_CONTINUATION", new Object[]{Byte.valueOf(b)});
                throw null;
            }
            if (i2 == i) {
                return;
            }
            eg1.a("TYPE_CONTINUATION streamId changed", new Object[0]);
            throw null;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            while (true) {
                int i = this.f7495e;
                if (i != 0) {
                    long j2 = this.f7494a.read(buffer, Math.min(j, i));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.f7495e = (int) (((long) this.f7495e) - j2);
                    return j2;
                }
                this.f7494a.skip(this.f);
                this.f = (short) 0;
                if ((this.c & 4) != 0) {
                    return -1L;
                }
                a();
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.f7494a.timeout();
        }
    }

    public static IllegalArgumentException c(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public static IOException d(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }

    public static /* synthetic */ IOException a(String str, Object[] objArr) throws IOException {
        d(str, objArr);
        throw null;
    }

    public static /* synthetic */ IllegalArgumentException b(String str, Object[] objArr) {
        c(str, objArr);
        throw null;
    }

    /* JADX INFO: compiled from: Http2.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final String[] f7496a = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        public static final String[] b = new String[64];
        public static final String[] c = new String[256];

        static {
            int i = 0;
            int i2 = 0;
            while (true) {
                String[] strArr = c;
                if (i2 >= strArr.length) {
                    break;
                }
                strArr[i2] = String.format("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
                i2++;
            }
            String[] strArr2 = b;
            strArr2[0] = "";
            strArr2[1] = "END_STREAM";
            int[] iArr = {1};
            strArr2[8] = "PADDED";
            for (int i3 = 0; i3 < 1; i3++) {
                int i4 = iArr[i3];
                b[i4 | 8] = b[i4] + "|PADDED";
            }
            String[] strArr3 = b;
            strArr3[4] = "END_HEADERS";
            strArr3[32] = "PRIORITY";
            strArr3[36] = "END_HEADERS|PRIORITY";
            int[] iArr2 = {4, 32, 36};
            for (int i5 = 0; i5 < 3; i5++) {
                int i6 = iArr2[i5];
                for (int i7 = 0; i7 < 1; i7++) {
                    int i8 = iArr[i7];
                    int i9 = i8 | i6;
                    b[i9] = b[i8] + '|' + b[i6];
                    b[i9 | 8] = b[i8] + '|' + b[i6] + "|PADDED";
                }
            }
            while (true) {
                String[] strArr4 = b;
                if (i >= strArr4.length) {
                    return;
                }
                if (strArr4[i] == null) {
                    strArr4[i] = c[i];
                }
                i++;
            }
        }

        public static String a(boolean z, int i, int i2, byte b2, byte b3) {
            String[] strArr = f7496a;
            String str = b2 < strArr.length ? strArr[b2] : String.format("0x%02x", Byte.valueOf(b2));
            String strA = a(b2, b3);
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = str;
            objArr[4] = strA;
            return String.format("%s 0x%08x %5d %-13s %s", objArr);
        }

        public static String a(byte b2, byte b3) {
            if (b3 == 0) {
                return "";
            }
            if (b2 != 2 && b2 != 3) {
                if (b2 == 4 || b2 == 6) {
                    return b3 == 1 ? "ACK" : c[b3];
                }
                if (b2 != 7 && b2 != 8) {
                    String[] strArr = b;
                    String str = b3 < strArr.length ? strArr[b3] : c[b3];
                    if (b2 != 5 || (b3 & 4) == 0) {
                        return (b2 != 0 || (b3 & 32) == 0) ? str : str.replace("PRIORITY", PickerConfig.COMPRESSED);
                    }
                    return str.replace("HEADERS", "PUSH_PROMISE");
                }
            }
            return c[b3];
        }
    }

    public static int b(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }

    public static int b(BufferedSource bufferedSource) throws IOException {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8);
    }

    /* JADX INFO: compiled from: Http2.java */
    public static final class d implements bg1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSink f7498a;
        public final boolean b;
        public final Buffer c;
        public final dg1.b d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7499e;
        public boolean f;

        public d(BufferedSink bufferedSink, boolean z) {
            this.f7498a = bufferedSink;
            this.b = z;
            Buffer buffer = new Buffer();
            this.c = buffer;
            this.d = new dg1.b(buffer);
            this.f7499e = 16384;
        }

        @Override // supwisdom.bg1
        public synchronized void a(kg1 kg1Var) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            this.f7499e = kg1Var.d(this.f7499e);
            a(0, 0, (byte) 4, (byte) 1);
            this.f7498a.flush();
        }

        @Override // supwisdom.bg1
        public synchronized void b(kg1 kg1Var) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            int i = 0;
            a(0, kg1Var.c() * 6, (byte) 4, (byte) 0);
            while (i < 10) {
                if (kg1Var.f(i)) {
                    this.f7498a.writeShort(i == 4 ? 3 : i == 7 ? 4 : i);
                    this.f7498a.writeInt(kg1Var.b(i));
                }
                i++;
            }
            this.f7498a.flush();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            this.f = true;
            this.f7498a.close();
        }

        @Override // supwisdom.bg1
        public synchronized void connectionPreface() throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            if (this.b) {
                if (eg1.f7493a.isLoggable(Level.FINE)) {
                    eg1.f7493a.fine(String.format(">> CONNECTION %s", eg1.b.hex()));
                }
                this.f7498a.write(eg1.b.toByteArray());
                this.f7498a.flush();
            }
        }

        @Override // supwisdom.bg1
        public synchronized void flush() throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            this.f7498a.flush();
        }

        @Override // supwisdom.bg1
        public int maxDataLength() {
            return this.f7499e;
        }

        @Override // supwisdom.bg1
        public synchronized void ping(boolean z, int i, int i2) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.f7498a.writeInt(i);
            this.f7498a.writeInt(i2);
            this.f7498a.flush();
        }

        @Override // supwisdom.bg1
        public synchronized void pushPromise(int i, int i2, List<cg1> list) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            if (this.c.size() != 0) {
                throw new IllegalStateException();
            }
            this.d.a(list);
            long size = this.c.size();
            int iMin = (int) Math.min(this.f7499e - 4, size);
            long j = iMin;
            a(i, iMin + 4, (byte) 5, size == j ? (byte) 4 : (byte) 0);
            this.f7498a.writeInt(i2 & Integer.MAX_VALUE);
            this.f7498a.write(this.c, j);
            if (size > j) {
                a(i, size - j);
            }
        }

        @Override // supwisdom.bg1
        public synchronized void windowUpdate(int i, long j) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            if (j == 0 || j > 2147483647L) {
                eg1.b("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[]{Long.valueOf(j)});
                throw null;
            }
            a(i, 4, (byte) 8, (byte) 0);
            this.f7498a.writeInt((int) j);
            this.f7498a.flush();
        }

        @Override // supwisdom.bg1
        public synchronized void a(boolean z, boolean z2, int i, int i2, List<cg1> list) throws IOException {
            try {
                if (!z2) {
                    if (!this.f) {
                        a(z, i, list);
                    } else {
                        throw new IOException("closed");
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        public void a(boolean z, int i, List<cg1> list) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            if (this.c.size() == 0) {
                this.d.a(list);
                long size = this.c.size();
                int iMin = (int) Math.min(this.f7499e, size);
                long j = iMin;
                byte b = size == j ? (byte) 4 : (byte) 0;
                if (z) {
                    b = (byte) (b | 1);
                }
                a(i, iMin, (byte) 1, b);
                this.f7498a.write(this.c, j);
                if (size > j) {
                    a(i, size - j);
                    return;
                }
                return;
            }
            throw new IllegalStateException();
        }

        public final void a(int i, long j) throws IOException {
            while (j > 0) {
                int iMin = (int) Math.min(this.f7499e, j);
                long j2 = iMin;
                j -= j2;
                a(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
                this.f7498a.write(this.c, j2);
            }
        }

        @Override // supwisdom.bg1
        public synchronized void a(int i, ErrorCode errorCode) throws IOException {
            if (!this.f) {
                if (errorCode.spdyRstCode != -1) {
                    a(i, 4, (byte) 3, (byte) 0);
                    this.f7498a.writeInt(errorCode.httpCode);
                    this.f7498a.flush();
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // supwisdom.bg1
        public synchronized void a(boolean z, int i, Buffer buffer, int i2) throws IOException {
            if (!this.f) {
                a(i, z ? (byte) 1 : (byte) 0, buffer, i2);
            } else {
                throw new IOException("closed");
            }
        }

        public void a(int i, byte b, Buffer buffer, int i2) throws IOException {
            a(i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.f7498a.write(buffer, i2);
            }
        }

        @Override // supwisdom.bg1
        public synchronized void a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
            if (!this.f) {
                if (errorCode.httpCode != -1) {
                    a(0, bArr.length + 8, (byte) 7, (byte) 0);
                    this.f7498a.writeInt(i);
                    this.f7498a.writeInt(errorCode.httpCode);
                    if (bArr.length > 0) {
                        this.f7498a.write(bArr);
                    }
                    this.f7498a.flush();
                } else {
                    eg1.b("errorCode.httpCode == -1", new Object[0]);
                    throw null;
                }
            } else {
                throw new IOException("closed");
            }
        }

        public void a(int i, int i2, byte b, byte b2) throws IOException {
            if (eg1.f7493a.isLoggable(Level.FINE)) {
                eg1.f7493a.fine(b.a(false, i, i2, b, b2));
            }
            int i3 = this.f7499e;
            if (i2 > i3) {
                eg1.b("FRAME_SIZE_ERROR length > %d: %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
                throw null;
            }
            if ((Integer.MIN_VALUE & i) != 0) {
                eg1.b("reserved bit set: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            eg1.b(this.f7498a, i2);
            this.f7498a.writeByte(b & 255);
            this.f7498a.writeByte(b2 & 255);
            this.f7498a.writeInt(i & Integer.MAX_VALUE);
        }
    }

    @Override // supwisdom.og1
    public ag1 a(BufferedSource bufferedSource, boolean z) {
        return new c(bufferedSource, 4096, z);
    }

    public static void b(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    @Override // supwisdom.og1
    public bg1 a(BufferedSink bufferedSink, boolean z) {
        return new d(bufferedSink, z);
    }

    /* JADX INFO: compiled from: Http2.java */
    public static final class c implements ag1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSource f7497a;
        public final a b;
        public final boolean c;
        public final dg1.a d;

        public c(BufferedSource bufferedSource, int i, boolean z) {
            this.f7497a = bufferedSource;
            this.c = z;
            a aVar = new a(bufferedSource);
            this.b = aVar;
            this.d = new dg1.a(i, aVar);
        }

        @Override // supwisdom.ag1
        public boolean a(ag1.a aVar) throws IOException {
            try {
                this.f7497a.require(9L);
                int iB = eg1.b(this.f7497a);
                if (iB < 0 || iB > 16384) {
                    eg1.a("FRAME_SIZE_ERROR: %s", new Object[]{Integer.valueOf(iB)});
                    throw null;
                }
                byte b = (byte) (this.f7497a.readByte() & 255);
                byte b2 = (byte) (this.f7497a.readByte() & 255);
                int i = this.f7497a.readInt() & Integer.MAX_VALUE;
                if (eg1.f7493a.isLoggable(Level.FINE)) {
                    eg1.f7493a.fine(b.a(true, i, iB, b, b2));
                }
                switch (b) {
                    case 0:
                        a(aVar, iB, b2, i);
                        return true;
                    case 1:
                        c(aVar, iB, b2, i);
                        return true;
                    case 2:
                        e(aVar, iB, b2, i);
                        return true;
                    case 3:
                        g(aVar, iB, b2, i);
                        return true;
                    case 4:
                        h(aVar, iB, b2, i);
                        return true;
                    case 5:
                        f(aVar, iB, b2, i);
                        return true;
                    case 6:
                        d(aVar, iB, b2, i);
                        return true;
                    case 7:
                        b(aVar, iB, b2, i);
                        return true;
                    case 8:
                        i(aVar, iB, b2, i);
                        return true;
                    default:
                        this.f7497a.skip(iB);
                        return true;
                }
            } catch (IOException unused) {
                return false;
            }
        }

        public final void b(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i < 8) {
                eg1.a("TYPE_GOAWAY length < 8: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            if (i2 != 0) {
                eg1.a("TYPE_GOAWAY streamId != 0", new Object[0]);
                throw null;
            }
            int i3 = this.f7497a.readInt();
            int i4 = this.f7497a.readInt();
            int i5 = i - 8;
            ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i4);
            if (errorCodeFromHttp2 == null) {
                eg1.a("TYPE_GOAWAY unexpected error code: %d", new Object[]{Integer.valueOf(i4)});
                throw null;
            }
            ByteString byteString = ByteString.EMPTY;
            if (i5 > 0) {
                byteString = this.f7497a.readByteString(i5);
            }
            aVar.a(i3, errorCodeFromHttp2, byteString);
        }

        public final void c(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i2 == 0) {
                eg1.a("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
                throw null;
            }
            boolean z = (b & 1) != 0;
            short s = (b & 8) != 0 ? (short) (this.f7497a.readByte() & 255) : (short) 0;
            if ((b & 32) != 0) {
                a(aVar, i2);
                i -= 5;
            }
            aVar.a(false, z, i2, -1, a(eg1.b(i, b, s), s, b, i2), HeadersMode.HTTP_20_HEADERS);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f7497a.close();
        }

        public final void d(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 8) {
                eg1.a("TYPE_PING length != 8: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            if (i2 != 0) {
                eg1.a("TYPE_PING streamId != 0", new Object[0]);
                throw null;
            }
            aVar.ping((b & 1) != 0, this.f7497a.readInt(), this.f7497a.readInt());
        }

        public final void e(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 5) {
                eg1.a("TYPE_PRIORITY length: %d != 5", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            if (i2 != 0) {
                a(aVar, i2);
            } else {
                eg1.a("TYPE_PRIORITY streamId == 0", new Object[0]);
                throw null;
            }
        }

        public final void f(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i2 == 0) {
                eg1.a("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
                throw null;
            }
            short s = (b & 8) != 0 ? (short) (this.f7497a.readByte() & 255) : (short) 0;
            aVar.pushPromise(i2, this.f7497a.readInt() & Integer.MAX_VALUE, a(eg1.b(i - 4, b, s), s, b, i2));
        }

        public final void g(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                eg1.a("TYPE_RST_STREAM length: %d != 4", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            if (i2 == 0) {
                eg1.a("TYPE_RST_STREAM streamId == 0", new Object[0]);
                throw null;
            }
            int i3 = this.f7497a.readInt();
            ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i3);
            if (errorCodeFromHttp2 != null) {
                aVar.a(i2, errorCodeFromHttp2);
            } else {
                eg1.a("TYPE_RST_STREAM unexpected error code: %d", new Object[]{Integer.valueOf(i3)});
                throw null;
            }
        }

        public final void h(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i2 != 0) {
                eg1.a("TYPE_SETTINGS streamId != 0", new Object[0]);
                throw null;
            }
            if ((b & 1) != 0) {
                if (i == 0) {
                    aVar.ackSettings();
                    return;
                } else {
                    eg1.a("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                    throw null;
                }
            }
            if (i % 6 != 0) {
                eg1.a("TYPE_SETTINGS length %% 6 != 0: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            kg1 kg1Var = new kg1();
            for (int i3 = 0; i3 < i; i3 += 6) {
                short s = this.f7497a.readShort();
                int i4 = this.f7497a.readInt();
                switch (s) {
                    case 1:
                    case 6:
                        break;
                    case 2:
                        if (i4 != 0 && i4 != 1) {
                            eg1.a("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                            throw null;
                        }
                        break;
                        break;
                    case 3:
                        s = 4;
                        break;
                    case 4:
                        s = 7;
                        if (i4 < 0) {
                            eg1.a("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                            throw null;
                        }
                        break;
                        break;
                    case 5:
                        if (i4 < 16384 || i4 > 16777215) {
                            eg1.a("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[]{Integer.valueOf(i4)});
                            throw null;
                        }
                        break;
                        break;
                    default:
                        eg1.a("PROTOCOL_ERROR invalid settings id: %s", new Object[]{Short.valueOf(s)});
                        throw null;
                }
                kg1Var.a(s, 0, i4);
            }
            aVar.a(false, kg1Var);
            if (kg1Var.b() >= 0) {
                this.d.d(kg1Var.b());
            }
        }

        public final void i(ag1.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                eg1.a("TYPE_WINDOW_UPDATE length !=4: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            long j = ((long) this.f7497a.readInt()) & 2147483647L;
            if (j != 0) {
                aVar.windowUpdate(i2, j);
            } else {
                eg1.a("windowSizeIncrement was 0", new Object[]{Long.valueOf(j)});
                throw null;
            }
        }

        @Override // supwisdom.ag1
        public void m() throws IOException {
            if (this.c) {
                return;
            }
            ByteString byteString = this.f7497a.readByteString(eg1.b.size());
            if (eg1.f7493a.isLoggable(Level.FINE)) {
                eg1.f7493a.fine(String.format("<< CONNECTION %s", byteString.hex()));
            }
            if (eg1.b.equals(byteString)) {
                return;
            }
            eg1.a("Expected a connection header but was %s", new Object[]{byteString.utf8()});
            throw null;
        }

        public final List<cg1> a(int i, short s, byte b, int i2) throws IOException {
            a aVar = this.b;
            aVar.f7495e = i;
            aVar.b = i;
            aVar.f = s;
            aVar.c = b;
            aVar.d = i2;
            this.d.f();
            return this.d.c();
        }

        public final void a(ag1.a aVar, int i, byte b, int i2) throws IOException {
            boolean z = (b & 1) != 0;
            if (!((b & 32) != 0)) {
                short s = (b & 8) != 0 ? (short) (this.f7497a.readByte() & 255) : (short) 0;
                aVar.a(z, i2, this.f7497a, eg1.b(i, b, s));
                this.f7497a.skip(s);
                return;
            }
            eg1.a("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            throw null;
        }

        public final void a(ag1.a aVar, int i) throws IOException {
            int i2 = this.f7497a.readInt();
            aVar.priority(i, i2 & Integer.MAX_VALUE, (this.f7497a.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
        }
    }
}
