package com.zx.a.I8b7;

import android.app.Activity;
import android.os.Handler;
import com.zx.a.I8b7.r2;
import com.zx.sdk.api.PermissionCallback;

/* JADX INFO: loaded from: classes2.dex */
public class d2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PermissionCallback f6210a;
    public final /* synthetic */ Activity b;

    public d2(e2 e2Var, PermissionCallback permissionCallback, Activity activity) {
        this.f6210a = permissionCallback;
        this.b = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            q0 q0Var = new q0(this.f6210a);
            Handler handler = r2.f6274a;
            r2 r2Var = r2.a.f6275a;
            if (r2Var.b()) {
                Activity activity = this.b;
                r2Var.getClass();
                r2.f6274a.post(new q2(r2Var, activity, q0Var));
            } else if (r2Var.a()) {
                q0Var.onAuthorized();
            } else {
                q0Var.onUnauthorized();
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.registerListener(listener) failed: "));
        }
    }
}
