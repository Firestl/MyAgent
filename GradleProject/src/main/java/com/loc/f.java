package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import com.loc.e;

/* JADX INFO: compiled from: ApsServiceCore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f3795a;
    public Context b;
    public Messenger c = null;

    public f(Context context) {
        this.f3795a = null;
        this.b = null;
        this.b = context.getApplicationContext();
        this.f3795a = new e(this.b);
    }

    public final IBinder a(Intent intent) {
        e.a aVar;
        e eVar = this.f3795a;
        String stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            l.a(eVar.f3766e, stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("b");
        eVar.f3765a = stringExtra2;
        k.a(stringExtra2);
        String stringExtra3 = intent.getStringExtra(cn.com.chinatelecom.account.api.a.d.f1473a);
        if (!TextUtils.isEmpty(stringExtra3)) {
            n.a(stringExtra3);
        }
        e eVar2 = this.f3795a;
        if ("true".equals(intent.getStringExtra("as")) && (aVar = eVar2.d) != null) {
            aVar.sendEmptyMessageDelayed(9, 100L);
        }
        Messenger messenger = new Messenger(this.f3795a.d);
        this.c = messenger;
        return messenger.getBinder();
    }

    public final void a() {
        try {
            e.d();
            this.f3795a.j = ep.b();
            this.f3795a.k = ep.a();
            this.f3795a.a();
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final void b() {
        try {
            if (this.f3795a != null) {
                this.f3795a.d.sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "onDestroy");
        }
    }
}
