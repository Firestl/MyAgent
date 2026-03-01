package supwisdom;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class et1 implements Closeable {

    @Nullable
    public Reader reader;

    /* JADX INFO: compiled from: ResponseBody.java */
    public class a extends et1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ xs1 f7522a;
        public final /* synthetic */ long b;
        public final /* synthetic */ BufferedSource c;

        public a(xs1 xs1Var, long j, BufferedSource bufferedSource) {
            this.f7522a = xs1Var;
            this.b = j;
            this.c = bufferedSource;
        }

        @Override // supwisdom.et1
        public long contentLength() {
            return this.b;
        }

        @Override // supwisdom.et1
        @Nullable
        public xs1 contentType() {
            return this.f7522a;
        }

        @Override // supwisdom.et1
        public BufferedSource source() {
            return this.c;
        }
    }

    /* JADX INFO: compiled from: ResponseBody.java */
    public static final class b extends Reader {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedSource f7523a;
        public final Charset b;
        public boolean c;

        @Nullable
        public Reader d;

        public b(BufferedSource bufferedSource, Charset charset) {
            this.f7523a = bufferedSource;
            this.b = charset;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.c = true;
            Reader reader = this.d;
            if (reader != null) {
                reader.close();
            } else {
                this.f7523a.close();
            }
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.c) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.d;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.f7523a.inputStream(), kt1.a(this.f7523a, this.b));
                this.d = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i, i2);
        }
    }

    private Charset charset() {
        xs1 xs1VarContentType = contentType();
        return xs1VarContentType != null ? xs1VarContentType.a(kt1.i) : kt1.i;
    }

    public static et1 create(@Nullable xs1 xs1Var, String str) {
        Charset charsetA = kt1.i;
        if (xs1Var != null && (charsetA = xs1Var.a()) == null) {
            charsetA = kt1.i;
            xs1Var = xs1.b(xs1Var + "; charset=utf-8");
        }
        Buffer bufferWriteString = new Buffer().writeString(str, charsetA);
        return create(xs1Var, bufferWriteString.size(), bufferWriteString);
    }

    public final InputStream byteStream() {
        return source().inputStream();
    }

    public final byte[] bytes() throws IOException {
        long jContentLength = contentLength();
        if (jContentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + jContentLength);
        }
        BufferedSource bufferedSourceSource = source();
        try {
            byte[] byteArray = bufferedSourceSource.readByteArray();
            kt1.a(bufferedSourceSource);
            if (jContentLength == -1 || jContentLength == byteArray.length) {
                return byteArray;
            }
            throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + byteArray.length + ") disagree");
        } catch (Throwable th) {
            kt1.a(bufferedSourceSource);
            throw th;
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        b bVar = new b(source(), charset());
        this.reader = bVar;
        return bVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        kt1.a(source());
    }

    public abstract long contentLength();

    @Nullable
    public abstract xs1 contentType();

    public abstract BufferedSource source();

    public final String string() throws IOException {
        BufferedSource bufferedSourceSource = source();
        try {
            return bufferedSourceSource.readString(kt1.a(bufferedSourceSource, charset()));
        } finally {
            kt1.a(bufferedSourceSource);
        }
    }

    public static et1 create(@Nullable xs1 xs1Var, byte[] bArr) {
        return create(xs1Var, bArr.length, new Buffer().write(bArr));
    }

    public static et1 create(@Nullable xs1 xs1Var, ByteString byteString) {
        return create(xs1Var, byteString.size(), new Buffer().write(byteString));
    }

    public static et1 create(@Nullable xs1 xs1Var, long j, BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            return new a(xs1Var, j, bufferedSource);
        }
        throw new NullPointerException("source == null");
    }
}
