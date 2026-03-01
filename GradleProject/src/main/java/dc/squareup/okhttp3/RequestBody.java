package dc.squareup.okhttp3;

import dc.squareup.okhttp3.internal.Util;
import dc.squareup.okio.BufferedSink;
import dc.squareup.okio.ByteString;
import dc.squareup.okio.Okio;
import dc.squareup.okio.Source;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RequestBody {
    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset;
        Charset charset2 = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) != null) {
            charset2 = charset;
        }
        return create(mediaType, str.getBytes(charset2));
    }

    public long contentLength() throws IOException {
        return -1L;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static RequestBody create(final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() { // from class: dc.squareup.okhttp3.RequestBody.1
            @Override // dc.squareup.okhttp3.RequestBody
            public long contentLength() throws IOException {
                return byteString.size();
            }

            @Override // dc.squareup.okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // dc.squareup.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr != null) {
            Util.checkOffsetAndCount(bArr.length, i, i2);
            return new RequestBody() { // from class: dc.squareup.okhttp3.RequestBody.2
                @Override // dc.squareup.okhttp3.RequestBody
                public long contentLength() {
                    return i2;
                }

                @Override // dc.squareup.okhttp3.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // dc.squareup.okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink) throws IOException {
                    bufferedSink.write(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() { // from class: dc.squareup.okhttp3.RequestBody.3
                @Override // dc.squareup.okhttp3.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // dc.squareup.okhttp3.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // dc.squareup.okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink) throws IOException {
                    Source source = null;
                    try {
                        source = Okio.source(file);
                        bufferedSink.writeAll(source);
                    } finally {
                        Util.closeQuietly(source);
                    }
                }
            };
        }
        throw new NullPointerException("file == null");
    }
}
