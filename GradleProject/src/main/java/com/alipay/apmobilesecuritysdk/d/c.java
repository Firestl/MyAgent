package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.f;
import com.umeng.commonsdk.statistics.idtracking.g;
import com.umeng.commonsdk.statistics.idtracking.h;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import supwisdom.lq;
import supwisdom.oq;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static Map<String, String> a(Context context) {
        oq oqVarB = oq.b();
        HashMap map = new HashMap();
        f fVarA = com.alipay.apmobilesecuritysdk.e.e.a(context);
        String strA = oq.a(context);
        String strB = oq.b(context);
        String strK = oq.k(context);
        String strM = oq.m(context);
        if (fVarA != null) {
            if (lq.a(strA)) {
                strA = fVarA.a();
            }
            if (lq.a(strB)) {
                strB = fVarA.b();
            }
            if (lq.a(strK)) {
                strK = fVarA.c();
            }
            if (lq.a(strM)) {
                strM = fVarA.e();
            }
        }
        f fVar = new f(strA, strB, strK, "", strM);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(g.f5442a, fVar.a());
                jSONObject.put("imsi", fVar.b());
                jSONObject.put(h.f5443a, fVar.c());
                jSONObject.put("bluetoothmac", fVar.d());
                jSONObject.put("gsi", fVar.e());
                String string = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key", string);
                com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", string);
            } catch (Exception e2) {
                com.alipay.apmobilesecuritysdk.c.a.a(e2);
            }
        }
        map.put("AD1", strA);
        map.put("AD2", strB);
        map.put("AD3", oq.f(context));
        map.put("AD5", oq.h(context));
        map.put("AD6", oq.i(context));
        map.put("AD7", oq.j(context));
        map.put("AD8", strK);
        map.put("AD9", oq.l(context));
        map.put("AD10", strM);
        map.put("AD11", oq.e());
        map.put("AD12", oqVarB.a());
        map.put("AD13", oq.f());
        map.put("AD14", oq.h());
        map.put("AD15", oq.i());
        map.put("AD16", oq.j());
        map.put("AD17", "");
        map.put("AD19", oq.n(context));
        map.put("AD20", oq.k());
        map.put("AD22", "");
        map.put("AD23", oq.p(context));
        map.put("AD24", lq.g(oq.g(context)));
        map.put("AD26", oq.e(context));
        map.put("AD27", oq.p());
        map.put("AD28", oq.r());
        map.put("AD29", oq.t());
        map.put("AD30", oq.q());
        map.put("AD31", oq.s());
        map.put("AD32", oq.n());
        map.put("AD33", oq.o());
        map.put("AD34", oq.r(context));
        map.put("AD35", oq.s(context));
        map.put("AD36", oq.q(context));
        map.put("AD37", oq.m());
        map.put("AD38", oq.l());
        map.put("AD39", oq.c(context));
        map.put("AD40", oq.d(context));
        map.put("AD41", oq.c());
        map.put("AD42", oq.d());
        map.put("AL3", oq.o(context));
        return map;
    }
}
