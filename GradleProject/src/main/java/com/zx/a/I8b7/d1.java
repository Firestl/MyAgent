package com.zx.a.I8b7;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class d1 implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1 f6206a;
    public final int b;
    public final String c;
    public final Map<String, List<String>> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e1 f6207e;

    public d1(a aVar) {
        this.f6206a = aVar.f6208a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = new HashMap(aVar.d);
        this.f6207e = aVar.f6209e;
    }

    public String a(String str) {
        List<String> list = this.d.get(str);
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("; ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        e1 e1Var = this.f6207e;
        if (e1Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        e1Var.close();
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a1 f6208a;
        public int b;
        public String c;
        public Map<String, List<String>> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e1 f6209e;

        public a() {
            this.b = -1;
            this.d = new HashMap();
        }

        public d1 a() {
            if (this.f6208a == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.b >= 0) {
                if (this.c != null) {
                    return new d1(this);
                }
                throw new IllegalStateException("message == null");
            }
            StringBuilder sbA = m2.a("code < 0: ");
            sbA.append(this.b);
            throw new IllegalStateException(sbA.toString());
        }

        public a(d1 d1Var) {
            this.b = -1;
            this.f6208a = d1Var.f6206a;
            this.b = d1Var.b;
            this.c = d1Var.c;
            this.d = new HashMap(d1Var.d);
            this.f6209e = d1Var.f6207e;
        }
    }
}
