package com.g.gysdk.a;

import com.getui.gtc.dyc.b.b;

/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.getui.gtc.dyc.b.b f2010a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.a f2011a = new b.a();

        public a a(String str) {
            this.f2011a.a(str);
            return this;
        }

        public k a() {
            return new k(this);
        }

        public a b(String str) {
            this.f2011a.b(str);
            return this;
        }

        public a c(String str) {
            this.f2011a.c(str);
            return this;
        }

        public a d(String str) {
            this.f2011a.d(str);
            return this;
        }

        public a e(String str) {
            this.f2011a.f(str);
            return this;
        }
    }

    public k(a aVar) {
        this.f2010a = aVar.f2011a.i();
    }

    public com.getui.gtc.dyc.b.b a() {
        return this.f2010a;
    }

    public void a(long j) {
        this.f2010a.h(j);
    }

    public void a(l lVar) {
        this.f2010a.i(lVar.a());
    }

    public void a(String str) {
        this.f2010a.a(str);
    }

    public String b() {
        return this.f2010a.a();
    }
}
