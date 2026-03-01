package com.igexin.push.f.a;

import android.os.Process;
import com.loopj.android.http.RequestParams;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class b extends com.igexin.c.a.d.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3546a = "com.igexin.push.f.a.b";
    public static final int b = -2147483639;
    public static final int d = 20000;
    public d c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HttpURLConnection f3547e;

    public b(d dVar) {
        super(0);
        this.c = dVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v17, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.InputStream] */
    private byte[] a(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                this.f3547e = httpURLConnection;
                httpURLConnection.setConnectTimeout(20000);
                this.f3547e.setReadTimeout(20000);
                this.f3547e.setRequestMethod("GET");
                this.f3547e.setDoInput(true);
                str = this.f3547e.getInputStream();
            } catch (Exception e2) {
                e = e2;
                str = 0;
                byteArrayOutputStream2 = null;
            } catch (Throwable th2) {
                byteArrayOutputStream = null;
                th = th2;
                str = 0;
            }
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } catch (Exception e3) {
                e = e3;
                byteArrayOutputStream2 = null;
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                if (str != 0) {
                    try {
                        str.close();
                    } catch (Exception e4) {
                        com.igexin.c.a.c.a.a(e4);
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e5) {
                        com.igexin.c.a.c.a.a(e5);
                    }
                }
                g();
                throw th;
            }
            try {
                if (this.f3547e.getResponseCode() == 200) {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = str.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, i);
                    }
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    if (str != 0) {
                        try {
                            str.close();
                        } catch (Exception e6) {
                            com.igexin.c.a.c.a.a(e6);
                        }
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e7) {
                        com.igexin.c.a.c.a.a(e7);
                    }
                    g();
                    return byteArray;
                }
                if (str != 0) {
                    try {
                        str.close();
                    } catch (Exception e8) {
                        com.igexin.c.a.c.a.a(e8);
                    }
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception e9) {
                    e = e9;
                    com.igexin.c.a.c.a.a(e);
                }
            } catch (Exception e10) {
                e = e10;
                com.igexin.c.a.c.a.a(e);
                if (str != 0) {
                    try {
                        str.close();
                    } catch (Exception e11) {
                        com.igexin.c.a.c.a.a(e11);
                    }
                }
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e12) {
                        e = e12;
                        com.igexin.c.a.c.a.a(e);
                    }
                }
            }
            g();
            return null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v24, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    private byte[] a(String str, byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        ?? byteArrayOutputStream2;
        Exception e2;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                this.f3547e = httpURLConnection;
                httpURLConnection.setDoInput(true);
                this.f3547e.setDoOutput(true);
                this.f3547e.setRequestMethod("POST");
                this.f3547e.setUseCaches(false);
                this.f3547e.setInstanceFollowRedirects(true);
                this.f3547e.setRequestProperty("Content-Type", RequestParams.APPLICATION_OCTET_STREAM);
                this.f3547e.setConnectTimeout(20000);
                this.f3547e.setReadTimeout(20000);
                this.f3547e.connect();
                str = new DataOutputStream(this.f3547e.getOutputStream());
            } catch (Exception e3) {
                e = e3;
                str = 0;
                bArr = 0;
            } catch (Throwable th2) {
                bArr = 0;
                byteArrayOutputStream = null;
                th = th2;
                str = 0;
            }
            try {
                str.write(bArr, 0, bArr.length);
                str.flush();
            } catch (Exception e4) {
                e = e4;
                bArr = 0;
                str = str;
                byteArrayOutputStream2 = bArr;
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                bArr = 0;
            }
            if (this.f3547e.getResponseCode() != 200) {
                try {
                    str.close();
                } catch (Exception e5) {
                    e2 = e5;
                    com.igexin.c.a.c.a.a(e2);
                }
                g();
                return null;
            }
            bArr = this.f3547e.getInputStream();
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int i = bArr.read(bArr2);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr2, 0, i);
                    }
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    try {
                        str.close();
                    } catch (Exception e6) {
                        com.igexin.c.a.c.a.a(e6);
                    }
                    if (bArr != 0) {
                        try {
                            bArr.close();
                        } catch (Exception e7) {
                            com.igexin.c.a.c.a.a(e7);
                        }
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e8) {
                        com.igexin.c.a.c.a.a(e8);
                    }
                    g();
                    return byteArray;
                } catch (Exception e9) {
                    e = e9;
                }
            } catch (Exception e10) {
                e = e10;
                byteArrayOutputStream2 = 0;
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th = th4;
                if (str != 0) {
                    try {
                        str.close();
                    } catch (Exception e11) {
                        com.igexin.c.a.c.a.a(e11);
                    }
                }
                if (bArr != 0) {
                    try {
                        bArr.close();
                    } catch (Exception e12) {
                        com.igexin.c.a.c.a.a(e12);
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e13) {
                        com.igexin.c.a.c.a.a(e13);
                    }
                }
                g();
                throw th;
            }
            com.igexin.c.a.c.a.a(e);
            if (str != 0) {
                try {
                    str.close();
                } catch (Exception e14) {
                    com.igexin.c.a.c.a.a(e14);
                }
            }
            if (bArr != 0) {
                try {
                    bArr.close();
                } catch (Exception e15) {
                    com.igexin.c.a.c.a.a(e15);
                }
            }
            if (byteArrayOutputStream2 != 0) {
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception e16) {
                    e2 = e16;
                    com.igexin.c.a.c.a.a(e2);
                }
            }
            g();
            return null;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private void g() {
        HttpURLConnection httpURLConnection = this.f3547e;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
                this.f3547e = null;
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.a
    public final void a() {
        super.a();
        g();
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        byte[] bArr;
        super.b_();
        Process.setThreadPriority(10);
        d dVar = this.c;
        if (dVar == null || dVar.f == null || ((bArr = dVar.g) != null && bArr.length > com.igexin.push.config.d.A * 1024)) {
            k();
            com.igexin.c.a.c.a.a(f3546a, "run return ###");
            com.igexin.c.a.c.a.a(f3546a + "|run return ###", new Object[0]);
            return;
        }
        try {
            byte[] bArrA = this.c.g == null ? a(this.c.f) : a(this.c.f, this.c.g);
            if (bArrA == null) {
                Exception exc = new Exception("Http response ＝＝ null");
                this.c.a(exc);
                throw exc;
            }
            try {
                this.c.a(bArrA);
                com.igexin.c.a.b.e.a().a(this.c);
                com.igexin.c.a.b.e.a().b();
            } catch (Exception e2) {
                this.c.a(e2);
                throw e2;
            }
        } catch (Exception e3) {
            this.c.a(e3);
            throw e3;
        }
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return -2147483639;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d() {
        this.o = true;
    }

    @Override // com.igexin.c.a.d.f
    public final void e() {
        g();
    }

    @Override // com.igexin.c.a.d.f
    public final void f() {
    }
}
