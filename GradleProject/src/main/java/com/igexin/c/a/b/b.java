package com.igexin.c.a.b;

import io.dcloud.common.util.Base64;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public OutputStream f3172a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3173e;

    public b(OutputStream outputStream) {
        this(outputStream, 76);
    }

    public b(OutputStream outputStream, int i) {
        this.f3172a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.f3173e = 0;
        this.f3172a = outputStream;
        this.f3173e = i;
    }

    public final void a() throws IOException {
        if (this.c > 0) {
            int i = this.f3173e;
            if (i > 0 && this.d == i) {
                this.f3172a.write(Base64.CRLF.getBytes());
                this.d = 0;
            }
            char cCharAt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 8) >>> 26);
            char cCharAt2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 14) >>> 26);
            char cCharAt3 = this.c < 2 ? '=' : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 20) >>> 26);
            char cCharAt4 = this.c >= 3 ? "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 26) >>> 26) : '=';
            this.f3172a.write(cCharAt);
            this.f3172a.write(cCharAt2);
            this.f3172a.write(cCharAt3);
            this.f3172a.write(cCharAt4);
            this.d += 4;
            this.c = 0;
            this.b = 0;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        a();
        this.f3172a.close();
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        int i2 = this.c;
        this.b = ((i & 255) << (16 - (i2 * 8))) | this.b;
        int i3 = i2 + 1;
        this.c = i3;
        if (i3 == 3) {
            a();
        }
    }
}
