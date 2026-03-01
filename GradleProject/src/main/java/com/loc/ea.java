package com.loc;

import com.amap.api.location.AMapLocation;

/* JADX INFO: compiled from: LastLocationInfo.java */
/* JADX INFO: loaded from: classes2.dex */
@af(a = "c")
public class ea {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ag(a = "a2", b = 6)
    public String f3770a;

    @ag(a = "a3", b = 5)
    public long b;

    @ag(a = "a4", b = 6)
    public String c;
    public AMapLocation d;

    public final AMapLocation a() {
        return this.d;
    }

    public final void a(long j) {
        this.b = j;
    }

    public final void a(AMapLocation aMapLocation) {
        this.d = aMapLocation;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final String b() {
        return this.c;
    }

    public final void b(String str) {
        this.f3770a = str;
    }

    public final String c() {
        return this.f3770a;
    }

    public final long d() {
        return this.b;
    }
}
