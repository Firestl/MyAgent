package com.igexin.push.f.a;

import android.os.Process;
import com.loopj.android.http.RequestParams;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends com.igexin.c.a.d.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3553a = -2147483638;
    public static final String c = "SimpleHttpTask";
    public static final int d = 20000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3554e = 3;
    public d b;
    public HttpURLConnection f;

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3555a;
        public byte[] b;

        public a(boolean z, byte[] bArr) {
            this.f3555a = z;
            this.b = bArr;
        }
    }

    public g(d dVar) {
        super(0);
        this.b = dVar;
    }

    private a a(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            this.f = httpURLConnection;
            httpURLConnection.setConnectTimeout(20000);
            this.f.setReadTimeout(20000);
            this.f.setRequestMethod("GET");
            this.f.setDoInput(true);
            HttpURLConnection httpURLConnection2 = this.f;
            this.f = httpURLConnection2;
            byte[] bArrA = a(httpURLConnection2);
            if (bArrA != null) {
                return b(bArrA);
            }
        } finally {
            try {
            } finally {
            }
        }
        g();
        return new a(false, null);
    }

    private a a(String str, byte[] bArr) {
        DataOutputStream dataOutputStream;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            this.f = httpURLConnection;
            httpURLConnection.setDoInput(true);
            this.f.setDoOutput(true);
            this.f.setRequestMethod("POST");
            this.f.setUseCaches(false);
            this.f.setInstanceFollowRedirects(true);
            this.f.setRequestProperty("Content-Type", RequestParams.APPLICATION_OCTET_STREAM);
            this.f.setConnectTimeout(20000);
            this.f.setReadTimeout(20000);
            this.f = this.f;
        } catch (Throwable th) {
            th = th;
            dataOutputStream = null;
        }
        if (bArr == null) {
            a aVar = new a(true, null);
            com.igexin.c.a.b.g.a((Closeable) null);
            g();
            return aVar;
        }
        byte[] bArrB = com.igexin.c.b.a.b(bArr);
        this.f.connect();
        dataOutputStream = new DataOutputStream(this.f.getOutputStream());
        try {
            dataOutputStream.write(bArrB, 0, bArrB.length);
            dataOutputStream.flush();
            byte[] bArrA = a(this.f);
            if (bArrA != null) {
                return b(bArrA);
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                com.igexin.c.a.c.a.a(th);
            } finally {
                com.igexin.c.a.b.g.a(dataOutputStream);
                g();
            }
        }
        com.igexin.c.a.b.g.a(dataOutputStream);
        g();
        return new a(false, null);
    }

    private void a(byte[] bArr) {
        try {
            this.b.a(bArr);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    public static byte[] a(HttpURLConnection httpURLConnection) throws Exception {
        Throwable th;
        InputStream errorStream;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (httpURLConnection.getResponseCode() == 200) {
                errorStream = httpURLConnection.getInputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = errorStream.read(bArr);
                        if (i == -1) {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            com.igexin.c.a.b.g.a(errorStream);
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    th = th2;
                    com.igexin.c.a.b.g.a(errorStream);
                    throw th;
                }
            } else {
                errorStream = httpURLConnection.getErrorStream();
            }
        } catch (Exception unused2) {
            errorStream = null;
        } catch (Throwable th3) {
            th = th3;
            errorStream = null;
        }
        com.igexin.c.a.b.g.a(errorStream);
        return null;
    }

    private a b(byte[] bArr) {
        try {
            return new a(false, bArr);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return new a(true, null);
        }
    }

    private HttpURLConnection b(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        this.f = httpURLConnection;
        httpURLConnection.setDoInput(true);
        this.f.setDoOutput(true);
        this.f.setRequestMethod("POST");
        this.f.setUseCaches(false);
        this.f.setInstanceFollowRedirects(true);
        this.f.setRequestProperty("Content-Type", RequestParams.APPLICATION_OCTET_STREAM);
        this.f.setConnectTimeout(20000);
        this.f.setReadTimeout(20000);
        return this.f;
    }

    private HttpURLConnection c(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        this.f = httpURLConnection;
        httpURLConnection.setConnectTimeout(20000);
        this.f.setReadTimeout(20000);
        this.f.setRequestMethod("GET");
        this.f.setDoInput(true);
        return this.f;
    }

    private void g() {
        HttpURLConnection httpURLConnection = this.f;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
                this.f = null;
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
    }

    private boolean h() {
        return this.b.l && com.igexin.push.g.a.a();
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        Process.setThreadPriority(10);
        d dVar = this.b;
        if (dVar == null || dVar.f == null) {
            g();
            com.igexin.c.a.c.a.a(c, "run return ###");
            com.igexin.c.a.c.a.a("SimpleHttpTask|run return ###", new Object[0]);
            return;
        }
        for (int i = 0; i < 3; i++) {
            d dVar2 = this.b;
            byte[] bArr = dVar2.g;
            String str = dVar2.f;
            byte[] bArr2 = (bArr == null ? a(str) : a(str, bArr)).b;
            if (bArr2 != null) {
                try {
                    this.b.a(bArr2);
                    return;
                } catch (Exception e2) {
                    com.igexin.c.a.c.a.a(e2);
                    return;
                }
            }
            if (i == 2) {
                this.b.a(new Exception("try up to limit"));
                com.igexin.c.a.c.a.a(c, "http request exception, try times = " + (i + 1));
            }
        }
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return -2147483638;
    }

    @Override // com.igexin.c.a.d.f
    public final void e() {
        g();
    }

    @Override // com.igexin.c.a.d.f
    public final void f() {
    }
}
