package com.zx.a.I8b7;

import com.zx.a.I8b7.c3;
import com.zx.sdk.api.PermissionCallback;

/* JADX INFO: loaded from: classes2.dex */
public class q0 implements PermissionCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PermissionCallback f6266a;

    public q0(PermissionCallback permissionCallback) {
        this.f6266a = permissionCallback;
    }

    @Override // com.zx.sdk.api.PermissionCallback
    public void onAuthorized() {
        try {
            if (this.f6266a != null) {
                e2 e2VarB = e2.b();
                PermissionCallback permissionCallback = this.f6266a;
                e2VarB.getClass();
                c3.e.f6204a.f6203a.execute(new b2(e2VarB, permissionCallback));
            }
        } catch (Throwable th) {
            y1.a(th);
        }
    }

    @Override // com.zx.sdk.api.PermissionCallback
    public void onUnauthorized() {
        try {
            if (this.f6266a != null) {
                e2 e2VarB = e2.b();
                e2VarB.getClass();
                c3.e.f6204a.f6203a.execute(new c2(e2VarB));
                this.f6266a.onUnauthorized();
            }
        } catch (Throwable th) {
            y1.a(th);
        }
    }
}
