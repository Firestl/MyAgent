package com.g.gysdk.a;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f1948a;
    public long b;
    public int c;
    public String d;

    public long a() {
        return this.f1948a;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(long j) {
        this.f1948a = j;
    }

    public void a(String str) {
        this.d = str;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public int c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean e() {
        return System.currentTimeMillis() > this.b + 604800000;
    }
}
