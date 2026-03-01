package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Method f1548a;
    public byte[] b;
    public String c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1549e;
    public boolean f;

    public a(Method method, int i, String str, byte[] bArr, String str2, boolean z) {
        this.f1548a = method;
        this.d = i;
        this.c = str;
        this.b = bArr;
        this.f1549e = str2;
        this.f = z;
    }
}
