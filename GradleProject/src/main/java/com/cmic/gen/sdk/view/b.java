package com.cmic.gen.sdk.view;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b f1767a;
    public a b;

    public interface a {
        void a();
    }

    public static b a() {
        if (f1767a == null) {
            synchronized (b.class) {
                if (f1767a == null) {
                    f1767a = new b();
                }
            }
        }
        return f1767a;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public a b() {
        return this.b;
    }

    public void c() {
        if (this.b != null) {
            this.b = null;
        }
    }
}
