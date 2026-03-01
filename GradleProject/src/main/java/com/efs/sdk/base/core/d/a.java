package com.efs.sdk.base.core.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1838a;
    public byte b;
    public int c = 0;
    public String d = "none";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1839e = 1;
    public long f = 0;
    public int g = 1;

    public a(String str, byte b) {
        this.b = (byte) 2;
        this.f1838a = str;
        if (b <= 0 || 3 < b) {
            throw new IllegalArgumentException("log protocol flag invalid : ".concat(String.valueOf((int) b)));
        }
        this.b = b;
    }
}
