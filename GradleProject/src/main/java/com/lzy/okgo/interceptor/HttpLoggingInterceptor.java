package com.lzy.okgo.interceptor;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.Protocol;
import okio.Buffer;
import supwisdom.au1;
import supwisdom.bt1;
import supwisdom.ct1;
import supwisdom.dt1;
import supwisdom.et1;
import supwisdom.ew0;
import supwisdom.fw0;
import supwisdom.ks1;
import supwisdom.us1;
import supwisdom.ws1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class HttpLoggingInterceptor implements ws1 {
    public static final Charset d = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Level f3848a = Level.NONE;
    public java.util.logging.Level b;
    public Logger c;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String str) {
        this.c = Logger.getLogger(str);
    }

    public static boolean b(xs1 xs1Var) {
        if (xs1Var == null) {
            return false;
        }
        if (xs1Var.c() != null && xs1Var.c().equals("text")) {
            return true;
        }
        String strB = xs1Var.b();
        if (strB != null) {
            String lowerCase = strB.toLowerCase();
            if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains("xml") || lowerCase.contains("html")) {
                return true;
            }
        }
        return false;
    }

    public void a(Level level) {
        if (this.f3848a == null) {
            throw new NullPointerException("printLevel == null. Use Level.NONE instead.");
        }
        this.f3848a = level;
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws Exception {
        bt1 bt1VarRequest = aVar.request();
        if (this.f3848a == Level.NONE) {
            return aVar.a(bt1VarRequest);
        }
        a(bt1VarRequest, aVar.connection());
        try {
            return a(aVar.a(bt1VarRequest), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - System.nanoTime()));
        } catch (Exception e2) {
            a("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    public void a(java.util.logging.Level level) {
        this.b = level;
    }

    public final void a(String str) {
        this.c.log(this.b, str);
    }

    public final void a(bt1 bt1Var, ks1 ks1Var) throws IOException {
        StringBuilder sb;
        boolean z = this.f3848a == Level.BODY;
        boolean z2 = this.f3848a == Level.BODY || this.f3848a == Level.HEADERS;
        ct1 ct1VarA = bt1Var.a();
        boolean z3 = ct1VarA != null;
        try {
            try {
                a("--> " + bt1Var.e() + ' ' + bt1Var.g() + ' ' + (ks1Var != null ? ks1Var.protocol() : Protocol.HTTP_1_1));
                if (z2) {
                    if (z3) {
                        if (ct1VarA.contentType() != null) {
                            a("\tContent-Type: " + ct1VarA.contentType());
                        }
                        if (ct1VarA.contentLength() != -1) {
                            a("\tContent-Length: " + ct1VarA.contentLength());
                        }
                    }
                    us1 us1VarC = bt1Var.c();
                    int iC = us1VarC.c();
                    for (int i = 0; i < iC; i++) {
                        String strA = us1VarC.a(i);
                        if (!"Content-Type".equalsIgnoreCase(strA) && !HttpHeaders.HEAD_KEY_CONTENT_LENGTH.equalsIgnoreCase(strA)) {
                            a("\t" + strA + ": " + us1VarC.b(i));
                        }
                    }
                    a(Operators.SPACE_STR);
                    if (z && z3) {
                        if (b(ct1VarA.contentType())) {
                            a(bt1Var);
                        } else {
                            a("\tbody: maybe [binary body], omitted!");
                        }
                    }
                }
                sb = new StringBuilder();
            } catch (Exception e2) {
                fw0.a(e2);
                sb = new StringBuilder();
            }
            sb.append("--> END ");
            sb.append(bt1Var.e());
            a(sb.toString());
        } catch (Throwable th) {
            a("--> END " + bt1Var.e());
            throw th;
        }
    }

    public final dt1 a(dt1 dt1Var, long j) {
        dt1 dt1VarA = dt1Var.h().a();
        et1 et1VarA = dt1VarA.a();
        boolean z = true;
        boolean z2 = this.f3848a == Level.BODY;
        if (this.f3848a != Level.BODY && this.f3848a != Level.HEADERS) {
            z = false;
        }
        try {
            try {
                a("<-- " + dt1VarA.c() + ' ' + dt1VarA.g() + ' ' + dt1VarA.k().g() + " (" + j + "ms）");
                if (z) {
                    us1 us1VarE = dt1VarA.e();
                    int iC = us1VarE.c();
                    for (int i = 0; i < iC; i++) {
                        a("\t" + us1VarE.a(i) + ": " + us1VarE.b(i));
                    }
                    a(Operators.SPACE_STR);
                    if (z2 && au1.b(dt1VarA)) {
                        if (et1VarA == null) {
                            return dt1Var;
                        }
                        if (b(et1VarA.contentType())) {
                            byte[] bArrA = ew0.a(et1VarA.byteStream());
                            a("\tbody:" + new String(bArrA, a(et1VarA.contentType())));
                            et1 et1VarCreate = et1.create(et1VarA.contentType(), bArrA);
                            dt1.a aVarH = dt1Var.h();
                            aVarH.a(et1VarCreate);
                            return aVarH.a();
                        }
                        a("\tbody: maybe [binary body], omitted!");
                    }
                }
            } catch (Exception e2) {
                fw0.a(e2);
            }
            return dt1Var;
        } finally {
            a("<-- END HTTP");
        }
    }

    public static Charset a(xs1 xs1Var) {
        Charset charsetA = xs1Var != null ? xs1Var.a(d) : d;
        return charsetA == null ? d : charsetA;
    }

    public final void a(bt1 bt1Var) {
        try {
            ct1 ct1VarA = bt1Var.f().a().a();
            if (ct1VarA == null) {
                return;
            }
            Buffer buffer = new Buffer();
            ct1VarA.writeTo(buffer);
            a("\tbody:" + buffer.readString(a(ct1VarA.contentType())));
        } catch (Exception e2) {
            fw0.a(e2);
        }
    }
}
