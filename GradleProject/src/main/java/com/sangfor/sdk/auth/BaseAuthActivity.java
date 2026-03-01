package com.sangfor.sdk.auth;

import android.R;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.sangfor.sdk.base.BaseActivity;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.g71;
import supwisdom.i71;
import supwisdom.q81;
import supwisdom.v71;
import supwisdom.x71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class BaseAuthActivity extends BaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g71 f3889a = null;
    public Dialog b = null;
    public q81.b c = null;
    public DialogInterface.OnClickListener d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public x71 f3890e;

    /* JADX INFO: compiled from: Proguard */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseAuthActivity.this.f3890e.setVisibility(0);
            BaseAuthActivity.this.f3890e.a(0);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseAuthActivity.this.f3890e.setVisibility(4);
        }
    }

    public final void b() {
        x71 x71Var = new x71(this);
        this.f3890e = x71Var;
        x71Var.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.f3890e.setVisibility(4);
        this.f3890e.a(4);
        ((ViewGroup) getWindow().getDecorView()).addView(this.f3890e);
    }

    public void c() {
        x71 x71Var = this.f3890e;
        if (x71Var != null) {
            x71Var.post(new b());
        }
    }

    public void d() {
        Dialog dialog = this.b;
        if (dialog != null && dialog.isShowing()) {
            this.b.dismiss();
        }
        this.b = null;
    }

    public void e() {
        x71 x71Var = this.f3890e;
        if (x71Var != null) {
            x71Var.post(new a());
        }
    }

    @Override // android.app.Activity
    public void finish() {
        d();
        this.f3889a.b(this);
        super.finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        SFLogN.a("EasyApp.BaseAuthActivity", "onConfigurationChanged");
        v71.b((Context) this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        v71.b((Context) this);
        g71 g71VarA = g71.a();
        this.f3889a = g71VarA;
        g71VarA.a(this);
        b();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        this.f3889a.b(this);
        d();
        q81.b bVar = this.c;
        if (bVar != null && bVar.b() != null && this.c.b().isShowing()) {
            this.c.b().dismiss();
            this.c = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((intent.getFlags() & 131072) > 0) {
            ((ActivityManager) getSystemService("activity")).moveTaskToFront(getTaskId(), 2);
        }
    }

    public void a(String str, String str2) {
        a(str, str2, "", null);
    }

    public void a(String str, String str2, String str3, DialogInterface.OnClickListener onClickListener) {
        if (this.c == null) {
            q81.b bVar = new q81.b(this);
            this.c = bVar;
            bVar.a(str);
            bVar.a(true, str2);
            bVar.a(false);
            bVar.a(i71.b.z, new DialogInterface.OnClickListener() { // from class: supwisdom.a81
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f6874a.a(dialogInterface, i);
                }
            });
            bVar.a();
        }
        this.d = onClickListener;
        if (!TextUtils.isEmpty(str3)) {
            this.c.c(str3);
        }
        if (TextUtils.isEmpty(str2)) {
            this.c.b(false);
        } else {
            this.c.b(true);
        }
        if (this.c.b().isShowing()) {
            this.c.b().dismiss();
        }
        if (isFinishing()) {
            SFLogN.b("EasyApp.BaseAuthActivity", "showFailedDialog failed", "activity isFinishing" + this);
            return;
        }
        SFLogN.c("EasyApp.BaseAuthActivity", "show failed msg:" + str2);
        this.c.d(str);
        this.c.b(str2);
        this.c.b().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        DialogInterface.OnClickListener onClickListener = this.d;
        if (onClickListener != null) {
            onClickListener.onClick(dialogInterface, i);
        }
        dialogInterface.dismiss();
    }
}
