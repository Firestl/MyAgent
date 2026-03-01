package com.getui.gtc.base.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class RequestBody {
    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() { // from class: com.getui.gtc.base.http.RequestBody.2
                @Override // com.getui.gtc.base.http.RequestBody
                public final long contentLength() {
                    return file.length();
                }

                @Override // com.getui.gtc.base.http.RequestBody
                public final MediaType contentType() {
                    return mediaType;
                }

                @Override // com.getui.gtc.base.http.RequestBody
                public final void writeTo(OutputStream outputStream) throws Throwable {
                    FileInputStream fileInputStream;
                    FileInputStream fileInputStream2 = null;
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i == -1) {
                                Util.closeQuietly(fileInputStream);
                                return;
                            }
                            outputStream.write(bArr, 0, i);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        Util.closeQuietly(fileInputStream2);
                        throw th;
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(bArr.length, i, i2);
        return new RequestBody() { // from class: com.getui.gtc.base.http.RequestBody.1
            @Override // com.getui.gtc.base.http.RequestBody
            public final long contentLength() {
                return i2;
            }

            @Override // com.getui.gtc.base.http.RequestBody
            public final MediaType contentType() {
                return mediaType;
            }

            @Override // com.getui.gtc.base.http.RequestBody
            public final void writeTo(OutputStream outputStream) throws IOException {
                outputStream.write(bArr, i, i2);
            }
        };
    }

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract void writeTo(OutputStream outputStream) throws IOException;
}
