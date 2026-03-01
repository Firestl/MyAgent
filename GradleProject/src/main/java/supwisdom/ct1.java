package supwisdom;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ct1 {

    /* JADX INFO: compiled from: RequestBody.java */
    public class a extends ct1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ xs1 f7253a;
        public final /* synthetic */ ByteString b;

        public a(xs1 xs1Var, ByteString byteString) {
            this.f7253a = xs1Var;
            this.b = byteString;
        }

        @Override // supwisdom.ct1
        public long contentLength() throws IOException {
            return this.b.size();
        }

        @Override // supwisdom.ct1
        @Nullable
        public xs1 contentType() {
            return this.f7253a;
        }

        @Override // supwisdom.ct1
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(this.b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    public class b extends ct1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ xs1 f7254a;
        public final /* synthetic */ int b;
        public final /* synthetic */ byte[] c;
        public final /* synthetic */ int d;

        public b(xs1 xs1Var, int i, byte[] bArr, int i2) {
            this.f7254a = xs1Var;
            this.b = i;
            this.c = bArr;
            this.d = i2;
        }

        @Override // supwisdom.ct1
        public long contentLength() {
            return this.b;
        }

        @Override // supwisdom.ct1
        @Nullable
        public xs1 contentType() {
            return this.f7254a;
        }

        @Override // supwisdom.ct1
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(this.c, this.d, this.b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    public class c extends ct1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ xs1 f7255a;
        public final /* synthetic */ File b;

        public c(xs1 xs1Var, File file) {
            this.f7255a = xs1Var;
            this.b = file;
        }

        @Override // supwisdom.ct1
        public long contentLength() {
            return this.b.length();
        }

        @Override // supwisdom.ct1
        @Nullable
        public xs1 contentType() {
            return this.f7255a;
        }

        @Override // supwisdom.ct1
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Source source = null;
            try {
                source = Okio.source(this.b);
                bufferedSink.writeAll(source);
            } finally {
                kt1.a(source);
            }
        }
    }

    public static ct1 create(@Nullable xs1 xs1Var, String str) {
        Charset charsetA = kt1.i;
        if (xs1Var != null && (charsetA = xs1Var.a()) == null) {
            charsetA = kt1.i;
            xs1Var = xs1.b(xs1Var + "; charset=utf-8");
        }
        return create(xs1Var, str.getBytes(charsetA));
    }

    public long contentLength() throws IOException {
        return -1L;
    }

    @Nullable
    public abstract xs1 contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static ct1 create(@Nullable xs1 xs1Var, ByteString byteString) {
        return new a(xs1Var, byteString);
    }

    public static ct1 create(@Nullable xs1 xs1Var, byte[] bArr) {
        return create(xs1Var, bArr, 0, bArr.length);
    }

    public static ct1 create(@Nullable xs1 xs1Var, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            kt1.a(bArr.length, i, i2);
            return new b(xs1Var, i2, bArr, i);
        }
        throw new NullPointerException("content == null");
    }

    public static ct1 create(@Nullable xs1 xs1Var, File file) {
        if (file != null) {
            return new c(xs1Var, file);
        }
        throw new NullPointerException("file == null");
    }
}
