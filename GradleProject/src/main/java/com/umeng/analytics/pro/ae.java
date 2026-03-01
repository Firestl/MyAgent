package com.umeng.analytics.pro;

/* JADX INFO: compiled from: DelayedStartCondition.java */
/* JADX INFO: loaded from: classes2.dex */
public class ae implements ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5162a;

    public ae(long j) {
        this.f5162a = 0L;
        this.f5162a = j;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean b() {
        return !a();
    }

    @Override // com.umeng.analytics.pro.ac
    public long c() {
        return this.f5162a;
    }
}
