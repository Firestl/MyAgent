package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: DDShareApiV2.java */
/* JADX INFO: loaded from: classes.dex */
public class mr implements or {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8425a;
    public String b;
    public boolean c;

    public mr(Context context, String str, boolean z) {
        this.f8425a = context;
        this.b = str;
        this.c = z;
    }

    @Override // supwisdom.or
    public boolean a(Intent intent, nr nrVar) {
        String stringExtra = intent.getStringExtra("android.intent.ding.EXTRA_MESSAGE_APP_PACKAGE_NAME");
        if (stringExtra == null || stringExtra.length() == 0 || nrVar == null) {
            Log.e("TAG", "invalid argument");
            return false;
        }
        int intExtra = intent.getIntExtra("android.intent.ding.EXTRA_COMMAND_TYPE", 0);
        if (intExtra == 1) {
            nrVar.a(new yr(intent.getExtras()));
            return true;
        }
        if (intExtra == 2) {
            nrVar.a(new yr(intent.getExtras()));
            return true;
        }
        if (intExtra == 3) {
            nrVar.a(new xr(intent.getExtras()));
            return true;
        }
        if (intExtra == 4) {
            nrVar.a(new xr(intent.getExtras()));
            return true;
        }
        if (intExtra != 100) {
            return false;
        }
        wr wrVar = new wr();
        wrVar.a(intent.getExtras());
        nrVar.a(wrVar);
        return true;
    }

    @Override // supwisdom.or
    public boolean b() {
        return b(this.f8425a);
    }

    public boolean c(Context context) {
        return a(context) >= 20151201;
    }

    public boolean b(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.alibaba.android.rimet", 64) != null) {
                return a(context, "com.alibaba.android.rimet");
            }
            return false;
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            return false;
        }
    }

    @Override // supwisdom.or
    public boolean a() {
        return c(this.f8425a);
    }

    @Override // supwisdom.or
    public boolean a(qr qrVar) {
        return a(this.f8425a, qrVar);
    }

    public int a(Context context) {
        if (!b(context)) {
            Log.e("DDShareApiV2", "open dd app failed, not installed or signature check failed");
            return 0;
        }
        return zr.a(context, 0);
    }

    public final boolean a(Context context, qr qrVar) {
        if (!c(context)) {
            Log.e("DDShareApiV2", "sendReq failed for dd not supported baseRequest");
            return false;
        }
        if (qrVar.b() > a(context)) {
            Log.e("DDShareApiV2", "sendReq failed for dd not supported baseRequest,minSupportVersion:" + qrVar.b() + ",dingtalkSupportSdkVersion:" + a(context));
            return false;
        }
        if (!a(context, "com.alibaba.android.rimet")) {
            Log.e("DDShareApiV2", "sendReq failed for dd app signature check failed");
            return false;
        }
        if (!qrVar.a()) {
            Log.e("DDShareApiV2", "sendReq checkArgs fail");
            return false;
        }
        Bundle bundle = new Bundle();
        qrVar.b(bundle);
        if (qrVar.c() == 100) {
            return ur.a(context, this.b, bundle);
        }
        return ur.b(context, this.b, bundle);
    }

    public boolean a(Context context, String str) {
        if (!this.c) {
            Log.d("DDShareApiV2", "ignore dd app signature validation");
            return true;
        }
        return TextUtils.equals(as.a(context, str), "d2cef93010963d9273440efe6a05dd8d");
    }
}
