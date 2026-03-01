package com.igexin.c.a.b;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public InputStream f3154a;
    public int[] b;
    public int c = 0;
    public boolean d = false;

    public a(InputStream inputStream) {
        this.f3154a = inputStream;
    }

    private void a() throws IOException {
        int i;
        char[] cArr = new char[4];
        int i2 = 0;
        do {
            int i3 = this.f3154a.read();
            i = 1;
            if (i3 == -1) {
                if (i2 != 0) {
                    throw new IOException("Bad base64 stream");
                }
                this.b = new int[0];
                this.d = true;
                return;
            }
            char c = (char) i3;
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(c) != -1 || c == '=') {
                cArr[i2] = c;
                i2++;
            } else if (c != '\r' && c != '\n') {
                throw new IOException("Bad base64 stream");
            }
        } while (i2 < 4);
        boolean z = false;
        for (int i4 = 0; i4 < 4; i4++) {
            if (cArr[i4] != '=') {
                if (z) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (!z) {
                z = true;
            }
        }
        if (cArr[3] != '=') {
            i = 3;
        } else {
            if (this.f3154a.read() != -1) {
                throw new IOException("Bad base64 stream");
            }
            this.d = true;
            if (cArr[2] != '=') {
                i = 2;
            }
        }
        int iIndexOf = 0;
        for (int i5 = 0; i5 < 4; i5++) {
            if (cArr[i5] != '=') {
                iIndexOf |= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(cArr[i5]) << ((3 - i5) * 6);
            }
        }
        this.b = new int[i];
        for (int i6 = 0; i6 < i; i6++) {
            this.b[i6] = (iIndexOf >>> ((2 - i6) * 8)) & 255;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f3154a.close();
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        int[] iArr = this.b;
        if (iArr == null || this.c == iArr.length) {
            if (this.d) {
                return -1;
            }
            char[] cArr = new char[4];
            int i = 0;
            while (true) {
                int i2 = this.f3154a.read();
                int i3 = 1;
                if (i2 != -1) {
                    char c = (char) i2;
                    if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(c) != -1 || c == '=') {
                        cArr[i] = c;
                        i++;
                    } else if (c != '\r' && c != '\n') {
                        throw new IOException("Bad base64 stream");
                    }
                    if (i >= 4) {
                        boolean z = false;
                        for (int i4 = 0; i4 < 4; i4++) {
                            if (cArr[i4] != '=') {
                                if (z) {
                                    throw new IOException("Bad base64 stream");
                                }
                            } else if (!z) {
                                z = true;
                            }
                        }
                        if (cArr[3] != '=') {
                            i3 = 3;
                        } else {
                            if (this.f3154a.read() != -1) {
                                throw new IOException("Bad base64 stream");
                            }
                            this.d = true;
                            if (cArr[2] != '=') {
                                i3 = 2;
                            }
                        }
                        int iIndexOf = 0;
                        for (int i5 = 0; i5 < 4; i5++) {
                            if (cArr[i5] != '=') {
                                iIndexOf |= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(cArr[i5]) << ((3 - i5) * 6);
                            }
                        }
                        this.b = new int[i3];
                        for (int i6 = 0; i6 < i3; i6++) {
                            this.b[i6] = (iIndexOf >>> ((2 - i6) * 8)) & 255;
                        }
                    }
                } else {
                    if (i != 0) {
                        throw new IOException("Bad base64 stream");
                    }
                    this.b = new int[0];
                    this.d = true;
                }
            }
            if (this.b.length == 0) {
                this.b = null;
                return -1;
            }
            this.c = 0;
        }
        int[] iArr2 = this.b;
        int i7 = this.c;
        this.c = i7 + 1;
        return iArr2[i7];
    }
}
