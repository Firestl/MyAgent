package com.getui.gtc.base.util.io;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class Base64InputStream extends InputStream {
    public int[] buffer;
    public int bufferCounter = 0;
    public boolean eof = false;
    public InputStream inputStream;

    public Base64InputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void acquire() throws IOException {
        int i;
        char[] cArr = new char[4];
        int i2 = 0;
        do {
            int i3 = this.inputStream.read();
            i = 1;
            if (i3 == -1) {
                if (i2 != 0) {
                    throw new IOException("Bad base64 stream");
                }
                this.buffer = new int[0];
                this.eof = true;
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
            if (this.inputStream.read() != -1) {
                throw new IOException("Bad base64 stream");
            }
            this.eof = true;
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
        this.buffer = new int[i];
        for (int i6 = 0; i6 < i; i6++) {
            this.buffer[i6] = (iIndexOf >>> ((2 - i6) * 8)) & 255;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int[] iArr = this.buffer;
        if (iArr == null || this.bufferCounter == iArr.length) {
            if (this.eof) {
                return -1;
            }
            acquire();
            if (this.buffer.length == 0) {
                this.buffer = null;
                return -1;
            }
            this.bufferCounter = 0;
        }
        int[] iArr2 = this.buffer;
        int i = this.bufferCounter;
        this.bufferCounter = i + 1;
        return iArr2[i];
    }
}
