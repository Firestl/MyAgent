package com.zx.a.I8b7;

import android.content.Context;
import android.text.TextUtils;
import com.zx.a.I8b7.t1;
import com.zx.sdk.api.Callback;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f6197a;
    public final /* synthetic */ Callback b;

    public a2(e2 e2Var, Context context, Callback callback) {
        this.f6197a = context;
        this.b = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strA;
        try {
            Context context = this.f6197a;
            if (TextUtils.isEmpty(t2.j) || "{}".equals(t2.j)) {
                t2.f6286a = context.getApplicationContext();
                t1 t1Var = t1.a.f6285a;
                t2.a(t1Var.f6284a);
                strA = t1Var.f6284a.a(16);
                t2.j = strA;
            } else {
                strA = t2.j;
            }
            boolean zEquals = false;
            try {
                zEquals = "OPENID_CLOSED".equals(new JSONObject(strA).optString("openid"));
            } catch (Throwable unused) {
            }
            if (zEquals) {
                this.b.onFailed(10001, "未开通");
                return;
            }
            String strA2 = d3.a("62");
            Callback callback = this.b;
            if (TextUtils.isEmpty(strA2)) {
                strA2 = "";
            }
            callback.onSuccess(strA2);
        } catch (Throwable th) {
            Callback callback2 = this.b;
            if (callback2 != null) {
                callback2.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getZXID(zxidListener) failed: "));
        }
    }
}
