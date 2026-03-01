package supwisdom;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.bumptech.glide.BuildConfig;
import com.loopj.android.http.RequestParams;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.dp;

/* JADX INFO: loaded from: classes.dex */
public abstract class ip {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7971a = true;
    public boolean b = true;

    public Map<String, String> a(boolean z, String str) {
        HashMap map = new HashMap();
        map.put("msp-gzip", String.valueOf(z));
        map.put("Operation-Type", "alipay.msp.cashier.dispatch.bytes");
        map.put("content-type", RequestParams.APPLICATION_OCTET_STREAM);
        map.put("Version", UMCrashManager.CM_VERSION);
        map.put("AppId", "TAOBAO");
        map.put("Msp-Param", ep.a(str));
        map.put("des-mode", "CBC");
        return map;
    }

    public abstract JSONObject a() throws JSONException;

    public String b() {
        return BuildConfig.VERSION_NAME;
    }

    public String c() throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        map.put(ConstantHelper.LOG_DE, Build.MODEL);
        map.put("namespace", "com.alipay.mobilecashier");
        map.put("api_name", "com.alipay.mcpay");
        map.put("api_version", b());
        return a(map, new HashMap<>());
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    public String a(pp ppVar, String str, JSONObject jSONObject) {
        qp qpVarD = qp.d();
        rp rpVarB = rp.b(qpVarD.a());
        JSONObject jSONObjectA = up.a(new JSONObject(), jSONObject);
        try {
            jSONObjectA.put("external_info", str);
            jSONObjectA.put("tid", rpVarB.a());
            jSONObjectA.put("user_agent", qpVarD.b().a(ppVar, rpVarB));
            jSONObjectA.put("has_alipay", bq.b(ppVar, qpVarD.a(), po.d));
            jSONObjectA.put("has_msp_app", bq.a(qpVarD.a()));
            jSONObjectA.put("app_key", "2014052600006128");
            jSONObjectA.put(com.umeng.commonsdk.statistics.idtracking.k.f5447a, qpVarD.c());
            jSONObjectA.put("new_client_key", rpVarB.b());
            jSONObjectA.put("pa", wo.a(qpVarD.a()));
        } catch (Throwable th) {
            so.a(ppVar, "biz", "BodyErr", th);
            vp.a(th);
        }
        return jSONObjectA.toString();
    }

    public static boolean a(dp.b bVar) {
        return Boolean.valueOf(a(bVar, "msp-gzip")).booleanValue();
    }

    public static String a(dp.b bVar, String str) {
        Map<String, List<String>> map;
        List<String> list;
        if (bVar == null || str == null || (map = bVar.f7370a) == null || (list = map.get(str)) == null) {
            return null;
        }
        return TextUtils.join(",", list);
    }

    public String a(HashMap<String, String> map, HashMap<String, String> map2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put("params", jSONObject3);
        }
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (!jSONObject.has("params")) {
                return false;
            }
            String strOptString = jSONObject.getJSONObject("params").optString("public_key", null);
            if (TextUtils.isEmpty(strOptString)) {
                return false;
            }
            wo.a(strOptString);
            return true;
        } catch (JSONException e2) {
            vp.a(e2);
            return false;
        }
    }

    public fp a(pp ppVar, Context context) throws Throwable {
        return a(ppVar, context, "");
    }

    public fp a(pp ppVar, Context context, String str) throws Throwable {
        return a(ppVar, context, str, aq.a(context));
    }

    public fp a(pp ppVar, Context context, String str, String str2) throws Throwable {
        return a(ppVar, context, str, str2, true);
    }

    public fp a(pp ppVar, Context context, String str, String str2, boolean z) throws Throwable {
        vp.a("mspl", "Packet: " + str2);
        gp gpVar = new gp(this.b);
        fp fpVar = new fp(c(), a(ppVar, str, a()));
        Map<String, String> mapA = a(false, str);
        hp hpVarA = gpVar.a(fpVar, this.f7971a, mapA.get("iSr"));
        dp.b bVarA = dp.a(context, new dp.a(str2, a(hpVarA.a(), str), hpVarA.b()));
        if (bVarA != null) {
            fp fpVarA = gpVar.a(new hp(a(bVarA), bVarA.b), mapA.get("iSr"));
            return (fpVarA != null && a(fpVarA.a()) && z) ? a(ppVar, context, str, str2, false) : fpVarA;
        }
        throw new RuntimeException("Response is null.");
    }
}
