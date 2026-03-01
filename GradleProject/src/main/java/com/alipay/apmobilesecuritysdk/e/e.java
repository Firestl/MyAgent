package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import org.json.JSONObject;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static f a(Context context) {
        if (context == null) {
            return null;
        }
        String strA = com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (lq.a(strA)) {
            strA = com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key");
        }
        if (lq.a(strA)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(strA);
            f fVar = new f();
            fVar.a(jSONObject.getString(com.umeng.commonsdk.statistics.idtracking.g.f5442a));
            fVar.b(jSONObject.getString("imsi"));
            fVar.c(jSONObject.getString(com.umeng.commonsdk.statistics.idtracking.h.f5443a));
            fVar.d(jSONObject.getString("bluetoothmac"));
            fVar.e(jSONObject.getString("gsi"));
            return fVar;
        } catch (Exception e2) {
            com.alipay.apmobilesecuritysdk.c.a.a(e2);
            return null;
        }
    }
}
