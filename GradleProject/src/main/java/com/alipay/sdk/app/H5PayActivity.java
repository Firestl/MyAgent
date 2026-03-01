package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.lzy.okgo.cookie.SerializableCookie;
import supwisdom.bq;
import supwisdom.eq;
import supwisdom.fq;
import supwisdom.pp;
import supwisdom.qo;
import supwisdom.ro;
import supwisdom.so;
import supwisdom.vo;
import supwisdom.vp;

/* JADX INFO: loaded from: classes.dex */
public class H5PayActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public eq f1603a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1604e;
    public boolean f;
    public String g;

    public void a() {
        Object obj = PayTask.d;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    public final void b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            vp.a(th);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        a();
        super.finish();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1010) {
            ro.a(pp.a.a(getIntent()), i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        eq eqVar = this.f1603a;
        if (eqVar == null) {
            finish();
            return;
        }
        if (eqVar.c()) {
            eqVar.b();
            return;
        }
        if (!eqVar.b()) {
            super.onBackPressed();
        }
        qo.a(qo.c());
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        b();
        super.onCreate(bundle);
        try {
            pp ppVarA = pp.a.a(getIntent());
            if (ppVarA == null) {
                finish();
                return;
            }
            if (vo.v().b()) {
                setRequestedOrientation(3);
            } else {
                setRequestedOrientation(1);
            }
            try {
                Bundle extras = getIntent().getExtras();
                String string = extras.getString("url", null);
                this.b = string;
                if (!bq.d(string)) {
                    finish();
                    return;
                }
                this.d = extras.getString(SerializableCookie.COOKIE, null);
                this.c = extras.getString("method", null);
                this.f1604e = extras.getString("title", null);
                this.g = extras.getString("version", "v1");
                this.f = extras.getBoolean("backisexit", false);
                try {
                    fq fqVar = new fq(this, ppVarA, this.g);
                    setContentView(fqVar);
                    fqVar.a(this.f1604e, this.c, this.f);
                    fqVar.a(this.b, this.d);
                    fqVar.a(this.b);
                    this.f1603a = fqVar;
                } catch (Throwable th) {
                    so.a(ppVarA, "biz", "GetInstalledAppEx", th);
                    finish();
                }
            } catch (Exception unused) {
                finish();
            }
        } catch (Exception unused2) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        eq eqVar = this.f1603a;
        if (eqVar != null) {
            eqVar.a();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Throwable th) {
            try {
                so.a(pp.a.a(getIntent()), "biz", "H5PayDataAnalysisError", th);
            } catch (Throwable unused) {
            }
        }
    }
}
