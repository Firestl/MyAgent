package com.zx.a.I8b7;

import com.zx.a.I8b7.t1;
import com.zx.sdk.api.PermissionCallback;

/* JADX INFO: loaded from: classes2.dex */
public class b2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PermissionCallback f6201a;

    public b2(e2 e2Var, PermissionCallback permissionCallback) {
        this.f6201a = permissionCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            t1.a.f6285a.f6284a.c(1);
            l.a("用户已授权获取卓信ID");
            try {
                e2.a().a(t2.f6286a);
            } catch (Exception e2) {
                l.b(e2.getMessage());
            }
            this.f6201a.onAuthorized();
        } catch (Throwable th) {
            n2.a(th, m2.a("卓信ID授权失败 error: "));
        }
    }
}
