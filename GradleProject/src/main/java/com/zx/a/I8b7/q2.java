package com.zx.a.I8b7;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.zx.sdk.api.PermissionCallback;

/* JADX INFO: loaded from: classes2.dex */
public class q2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Activity f6267a;
    public final /* synthetic */ PermissionCallback b;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s2 f6268a;

        public a(s2 s2Var) {
            this.f6268a = s2Var;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6268a.dismiss();
            q2.this.b.onAuthorized();
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s2 f6269a;

        public b(s2 s2Var) {
            this.f6269a = s2Var;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6269a.dismiss();
            q2.this.b.onUnauthorized();
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s2 f6270a;

        public c(s2 s2Var) {
            this.f6270a = s2Var;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6270a.dismiss();
            y1.a("用户点击了解更多");
            q2.this.f6267a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://aid.mobileservice.cn/")));
        }
    }

    public q2(r2 r2Var, Activity activity, PermissionCallback permissionCallback) {
        this.f6267a = activity;
        this.b = permissionCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            s2 s2Var = new s2(this.f6267a);
            s2Var.b = new a(s2Var);
            s2Var.f6281a = new b(s2Var);
            s2Var.c = new c(s2Var);
            s2Var.show();
        } catch (Throwable th) {
            n2.a(th, m2.a("卓信ID授权弹窗异常: "));
        }
    }
}
