package com.zx.a.I8b7;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public URL f6193a;
    public String b;
    public Map<String, String> c;
    public c1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6194e;

    public a1(a aVar) {
        this.f6193a = aVar.f6195a;
        this.b = aVar.b;
        HashMap map = new HashMap();
        this.c = map;
        map.putAll(aVar.c);
        this.d = aVar.d;
        this.f6194e = aVar.f6196e;
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public URL f6195a;
        public String b;
        public Map<String, String> c;
        public c1 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f6196e;

        public a() {
            this.b = "GET";
            this.c = new HashMap();
            this.f6196e = "";
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            try {
                this.f6195a = new URL(str);
                return this;
            } catch (MalformedURLException e2) {
                throw new IllegalArgumentException(e2);
            }
        }

        public a(a1 a1Var) {
            this.f6195a = a1Var.f6193a;
            this.b = a1Var.b;
            this.d = a1Var.d;
            this.c = a1Var.c;
            this.f6196e = a1Var.f6194e;
        }
    }
}
