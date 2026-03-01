package com.alipay.sdk.app;

import android.content.Intent;
import android.net.Uri;
import com.igexin.push.core.b;
import supwisdom.pp;
import supwisdom.so;

/* JADX INFO: loaded from: classes.dex */
public class H5OpenAuthActivity extends H5PayActivity {
    public boolean h = false;

    @Override // com.alipay.sdk.app.H5PayActivity
    public void a() {
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity
    public void onDestroy() {
        if (this.h) {
            try {
                pp ppVarA = pp.a.a(getIntent());
                if (ppVarA != null) {
                    so.b(this, ppVarA, "", ppVarA.d);
                }
            } catch (Throwable unused) {
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            pp ppVarA = pp.a.a(intent);
            try {
                super.startActivity(intent);
                Uri data = intent != null ? intent.getData() : null;
                if (data == null || !data.toString().startsWith("alipays://platformapi/startapp")) {
                    return;
                }
                finish();
            } catch (Throwable th) {
                String string = (intent == null || intent.getData() == null) ? b.m : intent.getData().toString();
                if (ppVarA != null) {
                    so.a(ppVarA, "biz", "StartActivityEx", th, string);
                }
                this.h = true;
                throw th;
            }
        } catch (Throwable unused) {
            finish();
        }
    }
}
