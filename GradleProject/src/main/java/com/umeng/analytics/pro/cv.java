package com.umeng.analytics.pro;

/* JADX INFO: compiled from: TProtocolException.java */
/* JADX INFO: loaded from: classes2.dex */
public class cv extends cb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f5246a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f5247e = 4;
    public static final int f = 5;
    public static final long h = 1;
    public int g;

    public cv() {
        this.g = 0;
    }

    public int a() {
        return this.g;
    }

    public cv(int i) {
        this.g = 0;
        this.g = i;
    }

    public cv(int i, String str) {
        super(str);
        this.g = 0;
        this.g = i;
    }

    public cv(String str) {
        super(str);
        this.g = 0;
    }

    public cv(int i, Throwable th) {
        super(th);
        this.g = 0;
        this.g = i;
    }

    public cv(Throwable th) {
        super(th);
        this.g = 0;
    }

    public cv(String str, Throwable th) {
        super(str, th);
        this.g = 0;
    }

    public cv(int i, String str, Throwable th) {
        super(str, th);
        this.g = 0;
        this.g = i;
    }
}
