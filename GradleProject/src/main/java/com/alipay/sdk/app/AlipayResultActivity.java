package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.api.ConnectionResult;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.umeng.analytics.pro.f;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import supwisdom.pp;
import supwisdom.so;

/* JADX INFO: loaded from: classes.dex */
public class AlipayResultActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, a> f1599a = new ConcurrentHashMap<>();

    public interface a {
        void a(int i, String str, String str2);
    }

    public final void a(String str, Bundle bundle) {
        a aVarRemove = f1599a.remove(str);
        if (aVarRemove == null) {
            return;
        }
        try {
            aVarRemove.a(bundle.getInt("endCode"), bundle.getString("memo"), bundle.getString("result"));
        } finally {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JSONObject jSONObject;
        Bundle bundle2;
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            try {
                String stringExtra = intent.getStringExtra(f.aC);
                Bundle bundleExtra = intent.getBundleExtra("result");
                String stringExtra2 = intent.getStringExtra("scene");
                pp ppVarA = pp.a.a(stringExtra);
                if (ppVarA == null) {
                    finish();
                    return;
                }
                so.b(ppVarA, "biz", "BSPSession", stringExtra + "|" + SystemClock.elapsedRealtime());
                if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                    a(stringExtra, bundleExtra);
                    return;
                }
                if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                        jSONObject = jSONObject2.getJSONObject("result");
                        stringExtra = jSONObject2.getString(f.aC);
                        so.b(ppVarA, "biz", "BSPUriSession", stringExtra);
                        bundle2 = new Bundle();
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        Iterator<String> itKeys = jSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            bundle2.putString(next, jSONObject.getString(next));
                        }
                        bundleExtra = bundle2;
                    } catch (Throwable th2) {
                        th = th2;
                        bundleExtra = bundle2;
                        so.a(ppVarA, "biz", "BSPResEx", th);
                        so.a(ppVarA, "biz", "ParseSchemeQueryError", th);
                    }
                }
                if (TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
                    so.b(this, ppVarA, "", ppVarA.d);
                    finish();
                    return;
                }
                try {
                    so.b(ppVarA, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
                    so.b(ppVarA, "biz", "PgReturnV", bundleExtra.getInt("endCode", -1) + "|" + bundleExtra.getString("memo", "-"));
                    OpenAuthTask.a(stringExtra, ConnectionResult.NETWORK_ERROR, WXModalUIModule.OK, bundleExtra);
                    so.b(this, ppVarA, "", ppVarA.d);
                    finish();
                } catch (Throwable th3) {
                    so.b(this, ppVarA, "", ppVarA.d);
                    finish();
                    throw th3;
                }
            } catch (Throwable th4) {
                so.a((pp) null, "biz", "BSPSerError", th4);
                so.a((pp) null, "biz", "ParseBundleSerializableError", th4);
                finish();
            }
        } catch (Throwable unused) {
            finish();
        }
    }
}
