package supwisdom;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import supwisdom.ju1;

/* JADX INFO: compiled from: Http2Reader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nu1 implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Logger f8568e = Logger.getLogger(ku1.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BufferedSource f8569a;
    public final a b;
    public final boolean c;
    public final ju1.a d;

    /* JADX INFO: compiled from: Http2Reader.java */
    public static final class a implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSource f8570a;
        public int b;
        public byte c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8571e;
        public short f;

        public a(BufferedSource bufferedSource) {
            this.f8570a = bufferedSource;
        }

        public final void a() throws IOException {
            int i = this.d;
            int iA = nu1.a(this.f8570a);
            this.f8571e = iA;
            this.b = iA;
            byte b = (byte) (this.f8570a.readByte() & 255);
            this.c = (byte) (this.f8570a.readByte() & 255);
            if (nu1.f8568e.isLoggable(Level.FINE)) {
                nu1.f8568e.fine(ku1.a(true, this.d, this.b, b, this.c));
            }
            int i2 = this.f8570a.readInt() & Integer.MAX_VALUE;
            this.d = i2;
            if (b != 9) {
                ku1.b("%s != TYPE_CONTINUATION", Byte.valueOf(b));
                throw null;
            }
            if (i2 == i) {
                return;
            }
            ku1.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            throw null;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            while (true) {
                int i = this.f8571e;
                if (i != 0) {
                    long j2 = this.f8570a.read(buffer, Math.min(j, i));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.f8571e = (int) (((long) this.f8571e) - j2);
                    return j2;
                }
                this.f8570a.skip(this.f);
                this.f = (short) 0;
                if ((this.c & 4) != 0) {
                    return -1L;
                }
                a();
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.f8570a.timeout();
        }
    }

    /* JADX INFO: compiled from: Http2Reader.java */
    public interface b {
        void a(int i, ErrorCode errorCode);

        void a(int i, ErrorCode errorCode, ByteString byteString);

        void a(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException;

        void a(boolean z, su1 su1Var);

        void ackSettings();

        void headers(boolean z, int i, int i2, List<iu1> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<iu1> list) throws IOException;

        void windowUpdate(int i, long j);
    }

    public nu1(BufferedSource bufferedSource, boolean z) {
        this.f8569a = bufferedSource;
        this.c = z;
        a aVar = new a(bufferedSource);
        this.b = aVar;
        this.d = new ju1.a(4096, aVar);
    }

    public void a(b bVar) throws IOException {
        if (this.c) {
            if (a(true, bVar)) {
                return;
            }
            ku1.b("Required SETTINGS preface not received", new Object[0]);
            throw null;
        }
        ByteString byteString = this.f8569a.readByteString(ku1.f8208a.size());
        if (f8568e.isLoggable(Level.FINE)) {
            f8568e.fine(kt1.a("<< CONNECTION %s", byteString.hex()));
        }
        if (ku1.f8208a.equals(byteString)) {
            return;
        }
        ku1.b("Expected a connection header but was %s", byteString.utf8());
        throw null;
    }

    public final void b(b bVar, int i, byte b2, int i2) throws IOException {
        if (i < 8) {
            ku1.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            ku1.b("TYPE_GOAWAY streamId != 0", new Object[0]);
            throw null;
        }
        int i3 = this.f8569a.readInt();
        int i4 = this.f8569a.readInt();
        int i5 = i - 8;
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i4);
        if (errorCodeFromHttp2 == null) {
            ku1.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i4));
            throw null;
        }
        ByteString byteString = ByteString.EMPTY;
        if (i5 > 0) {
            byteString = this.f8569a.readByteString(i5);
        }
        bVar.a(i3, errorCodeFromHttp2, byteString);
    }

    public final void c(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            ku1.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        short s = (b2 & 8) != 0 ? (short) (this.f8569a.readByte() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            a(bVar, i2);
            i -= 5;
        }
        bVar.headers(z, i2, -1, a(a(i, b2, s), s, b2, i2));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8569a.close();
    }

    public final void d(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 8) {
            ku1.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            ku1.b("TYPE_PING streamId != 0", new Object[0]);
            throw null;
        }
        bVar.ping((b2 & 1) != 0, this.f8569a.readInt(), this.f8569a.readInt());
    }

    public final void e(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 5) {
            ku1.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            a(bVar, i2);
        } else {
            ku1.b("TYPE_PRIORITY streamId == 0", new Object[0]);
            throw null;
        }
    }

    public final void f(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            ku1.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw null;
        }
        short s = (b2 & 8) != 0 ? (short) (this.f8569a.readByte() & 255) : (short) 0;
        bVar.pushPromise(i2, this.f8569a.readInt() & Integer.MAX_VALUE, a(a(i - 4, b2, s), s, b2, i2));
    }

    public final void g(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            ku1.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            throw null;
        }
        if (i2 == 0) {
            ku1.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
            throw null;
        }
        int i3 = this.f8569a.readInt();
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i3);
        if (errorCodeFromHttp2 != null) {
            bVar.a(i2, errorCodeFromHttp2);
        } else {
            ku1.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i3));
            throw null;
        }
    }

    public final void h(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 != 0) {
            ku1.b("TYPE_SETTINGS streamId != 0", new Object[0]);
            throw null;
        }
        if ((b2 & 1) != 0) {
            if (i == 0) {
                bVar.ackSettings();
                return;
            } else {
                ku1.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                throw null;
            }
        }
        if (i % 6 != 0) {
            ku1.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            throw null;
        }
        su1 su1Var = new su1();
        for (int i3 = 0; i3 < i; i3 += 6) {
            int i4 = this.f8569a.readShort() & 65535;
            int i5 = this.f8569a.readInt();
            if (i4 == 2) {
                if (i5 != 0 && i5 != 1) {
                    ku1.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                    throw null;
                }
            } else if (i4 == 3) {
                i4 = 4;
            } else if (i4 == 4) {
                i4 = 7;
                if (i5 < 0) {
                    ku1.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    throw null;
                }
            } else if (i4 == 5 && (i5 < 16384 || i5 > 16777215)) {
                ku1.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i5));
                throw null;
            }
            su1Var.a(i4, i5);
        }
        bVar.a(false, su1Var);
    }

    public final void i(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            ku1.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            throw null;
        }
        long j = ((long) this.f8569a.readInt()) & 2147483647L;
        if (j != 0) {
            bVar.windowUpdate(i2, j);
        } else {
            ku1.b("windowSizeIncrement was 0", Long.valueOf(j));
            throw null;
        }
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.f8569a.require(9L);
            int iA = a(this.f8569a);
            if (iA < 0 || iA > 16384) {
                ku1.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(iA));
                throw null;
            }
            byte b2 = (byte) (this.f8569a.readByte() & 255);
            if (z && b2 != 4) {
                ku1.b("Expected a SETTINGS frame but was %s", Byte.valueOf(b2));
                throw null;
            }
            byte b3 = (byte) (this.f8569a.readByte() & 255);
            int i = this.f8569a.readInt() & Integer.MAX_VALUE;
            if (f8568e.isLoggable(Level.FINE)) {
                f8568e.fine(ku1.a(true, i, iA, b2, b3));
            }
            switch (b2) {
                case 0:
                    a(bVar, iA, b3, i);
                    return true;
                case 1:
                    c(bVar, iA, b3, i);
                    return true;
                case 2:
                    e(bVar, iA, b3, i);
                    return true;
                case 3:
                    g(bVar, iA, b3, i);
                    return true;
                case 4:
                    h(bVar, iA, b3, i);
                    return true;
                case 5:
                    f(bVar, iA, b3, i);
                    return true;
                case 6:
                    d(bVar, iA, b3, i);
                    return true;
                case 7:
                    b(bVar, iA, b3, i);
                    return true;
                case 8:
                    i(bVar, iA, b3, i);
                    return true;
                default:
                    this.f8569a.skip(iA);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public final List<iu1> a(int i, short s, byte b2, int i2) throws IOException {
        a aVar = this.b;
        aVar.f8571e = i;
        aVar.b = i;
        aVar.f = s;
        aVar.c = b2;
        aVar.d = i2;
        this.d.f();
        return this.d.c();
    }

    public final void a(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            ku1.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        if (!((b2 & 32) != 0)) {
            short s = (b2 & 8) != 0 ? (short) (this.f8569a.readByte() & 255) : (short) 0;
            bVar.a(z, i2, this.f8569a, a(i, b2, s));
            this.f8569a.skip(s);
            return;
        }
        ku1.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        throw null;
    }

    public final void a(b bVar, int i) throws IOException {
        int i2 = this.f8569a.readInt();
        bVar.priority(i, i2 & Integer.MAX_VALUE, (this.f8569a.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
    }

    public static int a(BufferedSource bufferedSource) throws IOException {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8);
    }

    public static int a(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        ku1.b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }
}
