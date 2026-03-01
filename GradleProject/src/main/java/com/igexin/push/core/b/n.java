package com.igexin.push.core.b;

/* JADX INFO: loaded from: classes2.dex */
public final class n {
    public static final byte f = 2;
    public static final byte g = 3;
    public static final byte h = 5;
    public static final byte i = 6;
    public static final byte j = 7;
    public static final byte k = 8;
    public static final byte l = 11;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3360a;
    public String b;
    public byte c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3361e;

    public n(long j2, String str, byte b, long j3) {
        this.f3360a = j2;
        this.b = str;
        this.c = b;
        this.d = j3;
    }

    private long a() {
        return this.f3360a;
    }

    private n a(int i2) {
        this.f3361e = i2;
        return this;
    }

    private void a(byte b) {
        this.c = b;
    }

    private void a(long j2) {
        this.f3360a = j2;
    }

    private void a(String str) {
        this.b = str;
    }

    private String b() {
        return this.b;
    }

    private void b(long j2) {
        this.d = j2;
    }

    private byte c() {
        return this.c;
    }

    private long d() {
        return this.d;
    }

    private int e() {
        return this.f3361e;
    }
}
