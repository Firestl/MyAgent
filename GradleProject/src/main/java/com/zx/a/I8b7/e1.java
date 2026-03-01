package com.zx.a.I8b7;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e1 implements Closeable {

    public class a extends e1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ m0 f6212a;
        public final /* synthetic */ long b;
        public final /* synthetic */ InputStream c;

        public a(m0 m0Var, long j, InputStream inputStream) {
            this.f6212a = m0Var;
            this.b = j;
            this.c = inputStream;
        }
    }

    public static e1 a(m0 m0Var, long j, InputStream inputStream) {
        if (inputStream != null) {
            return new a(m0Var, j, inputStream);
        }
        throw new NullPointerException("byte stream is null");
    }

    public final String b() throws IOException {
        return new String(a(), StandardCharsets.UTF_8);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        k1.a(((a) this).c);
    }

    public final byte[] a() throws IOException {
        a aVar = (a) this;
        long j = aVar.b;
        if (j <= 2147483647L) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = aVar.c;
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (Throwable th) {
                    k1.a(inputStream);
                    throw th;
                }
            }
            k1.a(inputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (j == -1 || j == byteArray.length) {
                return byteArray;
            }
            throw new IOException("Content-Length (" + j + ") and stream length (" + byteArray.length + ") disagree");
        }
        throw new IOException("Cannot buffer entire body for content length: " + j);
    }
}
