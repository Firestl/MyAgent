package com.igexin.c.a.b.a.a;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BufferedOutputStream f3169a;

    public i(OutputStream outputStream) throws IOException {
        this.f3169a = new BufferedOutputStream(outputStream);
    }

    public i(Socket socket) throws IOException {
        this.f3169a = new BufferedOutputStream(socket.getOutputStream());
    }

    private void a() throws IOException {
        this.f3169a.close();
    }

    private void a(byte[] bArr) throws IOException {
        this.f3169a.write(bArr, 0, bArr.length);
        this.f3169a.flush();
    }

    private void a(byte[] bArr, int i, int i2) throws IOException {
        this.f3169a.write(bArr, i, i2);
        this.f3169a.flush();
    }
}
