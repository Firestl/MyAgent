package com.umeng.analytics.pro;

import java.io.Serializable;

/* JADX INFO: compiled from: FieldValueMetaData.java */
/* JADX INFO: loaded from: classes2.dex */
public class ci implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f5228a;
    public final byte b;
    public final String c;
    public final boolean d;

    public ci(byte b, boolean z) {
        this.b = b;
        this.f5228a = false;
        this.c = null;
        this.d = z;
    }

    public boolean a() {
        return this.f5228a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == 12;
    }

    public boolean d() {
        byte b = this.b;
        return b == 15 || b == 13 || b == 14;
    }

    public boolean e() {
        return this.d;
    }

    public ci(byte b) {
        this(b, false);
    }

    public ci(byte b, String str) {
        this.b = b;
        this.f5228a = true;
        this.c = str;
        this.d = false;
    }
}
