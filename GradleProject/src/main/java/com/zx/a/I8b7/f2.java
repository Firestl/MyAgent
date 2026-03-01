package com.zx.a.I8b7;

import com.zx.sdk.api.SAIDCallback;

/* JADX INFO: loaded from: classes2.dex */
public class f2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f6218a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f6219e;
    public final /* synthetic */ String f;
    public final /* synthetic */ SAIDCallback g;

    public f2(e2 e2Var, String str, String str2, String str3, String str4, String str5, String str6, SAIDCallback sAIDCallback) {
        this.f6218a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.f6219e = str5;
        this.f = str6;
        this.g = sAIDCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            e2.a().a(this.f6218a, this.b, this.c, this.d, this.f6219e, this.f, this.g);
        } catch (Throwable th) {
            SAIDCallback sAIDCallback = this.g;
            if (sAIDCallback != null) {
                sAIDCallback.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getSAID() failed: "));
        }
    }
}
