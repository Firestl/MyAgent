package com.cmic.gen.sdk.a;

/* JADX INFO: loaded from: classes.dex */
public class a implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1658a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1659e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public int k;
    public int l;

    /* JADX INFO: renamed from: com.cmic.gen.sdk.a.a$a, reason: collision with other inner class name */
    public static class C0014a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f1660a = new a();

        public C0014a a(int i) {
            this.f1660a.k = i;
            return this;
        }

        public C0014a a(String str) {
            this.f1660a.f1658a = str;
            return this;
        }

        public C0014a a(boolean z) {
            this.f1660a.f1659e = z;
            return this;
        }

        public a a() {
            return this.f1660a;
        }

        public C0014a b(int i) {
            this.f1660a.l = i;
            return this;
        }

        public C0014a b(String str) {
            this.f1660a.b = str;
            return this;
        }

        public C0014a b(boolean z) {
            this.f1660a.f = z;
            return this;
        }

        public C0014a c(String str) {
            this.f1660a.c = str;
            return this;
        }

        public C0014a c(boolean z) {
            this.f1660a.g = z;
            return this;
        }

        public C0014a d(String str) {
            this.f1660a.d = str;
            return this;
        }

        public C0014a d(boolean z) {
            this.f1660a.h = z;
            return this;
        }

        public C0014a e(boolean z) {
            this.f1660a.i = z;
            return this;
        }

        public C0014a f(boolean z) {
            this.f1660a.j = z;
            return this;
        }
    }

    public a() {
        this.f1658a = "rcs.cmpassport.com";
        this.b = "rcs.cmpassport.com";
        this.c = "config2.cmpassport.com";
        this.d = "log2.cmpassport.com:9443";
        this.f1659e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = 3;
        this.l = 1;
    }

    public String a() {
        return this.f1658a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean e() {
        return this.f1659e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public int l() {
        return this.l;
    }

    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public a clone() throws CloneNotSupportedException {
        return (a) super.clone();
    }
}
