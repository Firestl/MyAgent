package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: TIOStreamTransport.java */
/* JADX INFO: loaded from: classes2.dex */
public class dg extends di {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public InputStream f5255a;
    public OutputStream b;

    public dg() {
        this.f5255a = null;
        this.b = null;
    }

    @Override // com.umeng.analytics.pro.di
    public int a(byte[] bArr, int i, int i2) throws dj {
        InputStream inputStream = this.f5255a;
        if (inputStream == null) {
            throw new dj(1, "Cannot read from null inputStream");
        }
        try {
            int i3 = inputStream.read(bArr, i, i2);
            if (i3 >= 0) {
                return i3;
            }
            throw new dj(4);
        } catch (IOException e2) {
            throw new dj(0, e2);
        }
    }

    @Override // com.umeng.analytics.pro.di
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.di
    public void b() throws dj {
    }

    @Override // com.umeng.analytics.pro.di
    public void b(byte[] bArr, int i, int i2) throws dj {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new dj(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i, i2);
        } catch (IOException e2) {
            throw new dj(0, e2);
        }
    }

    @Override // com.umeng.analytics.pro.di
    public void c() {
        InputStream inputStream = this.f5255a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.f5255a = null;
        }
        OutputStream outputStream = this.b;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // com.umeng.analytics.pro.di
    public void d() throws dj {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new dj(1, "Cannot flush null outputStream");
        }
        try {
            outputStream.flush();
        } catch (IOException e2) {
            throw new dj(0, e2);
        }
    }

    public dg(InputStream inputStream) {
        this.f5255a = null;
        this.b = null;
        this.f5255a = inputStream;
    }

    public dg(OutputStream outputStream) {
        this.f5255a = null;
        this.b = null;
        this.b = outputStream;
    }

    public dg(InputStream inputStream, OutputStream outputStream) {
        this.f5255a = null;
        this.b = null;
        this.f5255a = inputStream;
        this.b = outputStream;
    }
}
