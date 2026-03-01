package com.efs.sdk.base.core.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.http.HttpResponse;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static volatile long b = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1798a;

    /* JADX INFO: renamed from: com.efs.sdk.base.core.a.a$a, reason: collision with other inner class name */
    public static class C0018a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1799a = new a(0);
    }

    public /* synthetic */ a(byte b2) {
        this();
    }

    public static a a() {
        return C0018a.f1799a;
    }

    public static long b() {
        return b == -1 ? System.currentTimeMillis() : SystemClock.elapsedRealtime() + b;
    }

    public a() {
        this.f1798a = true;
    }

    public final HttpResponse a(String str, c cVar, File file, boolean z) {
        String strB = cVar.b();
        String strA = a(str, cVar);
        if (this.f1798a) {
            Log.i("efs.px.api", "Upload file, url is ".concat(String.valueOf(strA)));
        }
        HashMap map = new HashMap(1);
        map.put("wpk-header", strB);
        com.efs.sdk.base.core.util.a.d dVarA = new com.efs.sdk.base.core.util.a.d(strA).a(map);
        dVarA.f1860a.d = file;
        com.efs.sdk.base.core.util.a.d dVarA2 = dVarA.a("type", cVar.h);
        StringBuilder sb = new StringBuilder();
        sb.append(cVar.m);
        return dVarA2.a(AbsoluteConst.JSON_KEY_SIZE, sb.toString()).a("flow_limit", Boolean.toString(z)).a(d.a()).a().b();
    }

    public static String a(String str, c cVar) {
        byte b2 = cVar.g;
        return str + (b2 != 1 ? b2 != 2 ? b2 != 3 ? "/api/v1/raw/upload" : "/api/v1/mix/upload" : "/perf_upload" : "/apm_logs");
    }

    public static void a(HttpResponse httpResponse) {
        if (httpResponse == null || !httpResponse.succ || TextUtils.isEmpty(httpResponse.data)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(httpResponse.data);
            String strOptString = jSONObject.optString("code", "-1");
            httpResponse.setBizCode(strOptString);
            if (!"0".equals(strOptString)) {
                httpResponse.succ = false;
            }
            if (jSONObject.has("cver")) {
                ((Map) httpResponse.extra).put("cver", jSONObject.getString("cver"));
            }
            long j = jSONObject.getLong(ConstantHelper.LOG_STM) * 1000;
            if (Math.abs(j - b()) > 1500000) {
                b = j - SystemClock.elapsedRealtime();
            }
        } catch (Throwable th) {
            Log.e("efs.px.api", "checkPxReturn error", th);
        }
    }
}
