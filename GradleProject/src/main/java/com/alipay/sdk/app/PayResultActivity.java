package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import supwisdom.pp;
import supwisdom.qo;
import supwisdom.so;
import supwisdom.vp;

/* JADX INFO: loaded from: classes.dex */
public final class PayResultActivity extends Activity {
    public static final HashMap<String, Object> b = new HashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public pp f1606a = null;

    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f1607a;

        public a(Activity activity) {
            this.f1607a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1607a.finish();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static volatile String f1608a;
        public static volatile String b;
    }

    public static void a(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        Intent intent = new Intent();
        try {
            intent.setPackage("hk.alipay.wallet");
            intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
        } catch (UnsupportedEncodingException e2) {
            vp.a(e2);
        }
        if (activity != null) {
            try {
                activity.startActivity(intent);
            } catch (Throwable unused) {
                activity.finish();
            }
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            if (!TextUtils.isEmpty(intent.getStringExtra("orderSuffix"))) {
                b.f1608a = intent.getStringExtra("phonecashier.pay.hash");
                String stringExtra = intent.getStringExtra("orderSuffix");
                String stringExtra2 = intent.getStringExtra("externalPkgName");
                pp ppVarA = pp.a.a(intent);
                this.f1606a = ppVarA;
                if (ppVarA == null) {
                    finish();
                }
                a(this, b.f1608a, stringExtra, stringExtra2);
                a(this, 300);
                return;
            }
            if (this.f1606a == null) {
                finish();
            }
            String stringExtra3 = intent.getStringExtra("phonecashier.pay.result");
            int intExtra = intent.getIntExtra("phonecashier.pay.resultOrderHash", 0);
            if (intExtra != 0 && TextUtils.equals(b.f1608a, String.valueOf(intExtra))) {
                if (TextUtils.isEmpty(stringExtra3)) {
                    a(b.f1608a);
                } else {
                    a(stringExtra3, b.f1608a);
                }
                b.f1608a = "";
                a(this, 300);
                return;
            }
            so.a(this.f1606a, "biz", "SchemePayWrongHashEx", "Expected " + b.f1608a + ", got " + intExtra);
            a(b.f1608a);
            a(this, 300);
        } catch (Throwable unused) {
            finish();
        }
    }

    public static void a(String str) {
        b.b = qo.c();
        a(b, str);
    }

    public static void a(String str, String str2) {
        b.b = str;
        a(b, str2);
    }

    public static void a(Activity activity, int i) {
        new Handler().postDelayed(new a(activity), i);
    }

    public static boolean a(HashMap<String, Object> map, String str) {
        Object obj;
        if (map == null || str == null || (obj = map.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}
