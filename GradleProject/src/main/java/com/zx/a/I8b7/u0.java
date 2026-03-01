package com.zx.a.I8b7;

import com.zx.a.I8b7.c0;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class u0 implements c0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6288a;
    public final List<c0> b;
    public final a1 c;
    public final HttpURLConnection d;

    public u0(List<c0> list, HttpURLConnection httpURLConnection, int i, a1 a1Var) {
        this.b = list;
        this.d = httpURLConnection;
        this.f6288a = i;
        this.c = a1Var;
    }

    public d1 a(a1 a1Var, HttpURLConnection httpURLConnection) throws IOException {
        if (this.f6288a >= this.b.size()) {
            throw new AssertionError();
        }
        List<c0> list = this.b;
        int i = this.f6288a;
        u0 u0Var = new u0(list, httpURLConnection, i + 1, a1Var);
        c0 c0Var = list.get(i);
        d1 d1VarA = c0Var.a(u0Var);
        if (d1VarA == null) {
            throw new NullPointerException("interceptor " + c0Var + " returned null");
        }
        if (d1VarA.f6207e != null) {
            return d1VarA;
        }
        throw new IllegalStateException("interceptor " + c0Var + " returned a response with no body");
    }
}
