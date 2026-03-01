package com.zx.a.I8b7;

import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.bm;
import com.zx.a.I8b7.a1;
import com.zx.a.I8b7.c3;
import com.zx.a.I8b7.s;
import com.zx.a.I8b7.t1;
import com.zx.module.annotation.Java2C;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import javax.crypto.SecretKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f6308a = "";
    public static SecretKey c;
    public static byte[] d;
    public static LinkedList<String> b = new LinkedList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final SecureRandom f6309e = new SecureRandom();

    @Java2C.Method2C
    public static native String a(String str, String str2, JSONObject jSONObject);

    @Java2C.Method2C
    public static native String a(String str, JSONObject jSONObject);

    @Java2C.Method2C
    public static native HashMap<String, String> a() throws JSONException;

    @Java2C.Method2C
    public static native HashMap<String, String> a(String str);

    @Java2C.Method2C
    public static native HashMap<String, String> a(JSONArray jSONArray) throws JSONException;

    @Java2C.Method2C
    public static native HashMap<String, String> a(JSONArray jSONArray, JSONObject jSONObject) throws JSONException;

    @Java2C.Method2C
    public static native JSONObject a(JSONArray jSONArray, HashMap<String, String> map, JSONObject jSONObject);

    @Java2C.Method2C
    public static native void a(HashMap<String, String> map, String str, JSONObject jSONObject, HashMap<String, String> map2, boolean z) throws JSONException;

    @Java2C.Method2C
    public static native void a(JSONObject jSONObject) throws Throwable;

    @Java2C.Method2C
    public static native void a(JSONObject jSONObject, HashMap<String, String> map, String str, String str2) throws JSONException;

    @Java2C.Method2C
    public static native JSONObject b();

    @Java2C.Method2C
    public static native JSONObject b(JSONArray jSONArray, HashMap<String, String> map, JSONObject jSONObject);

    @Java2C.Method2C
    public static native synchronized String c();

    @Java2C.Method2C
    public static native JSONObject c(JSONArray jSONArray, HashMap<String, String> map, JSONObject jSONObject);

    @Java2C.Method2C
    public static native String d() throws Throwable;

    @Java2C.Method2C
    public static native JSONObject e();

    @Java2C.Method2C
    public static native String f();

    @Java2C.Method2C
    public static native JSONObject g();

    public static void h() throws Exception {
        if (u.a()) {
            return;
        }
        a1.a aVar = new a1.a();
        HashMap<String, String> mapB = w.b(c());
        aVar.c.clear();
        aVar.c.putAll(mapB);
        a1.a aVarA = aVar.a("https://zxid-m.mobileservice.cn/sdk/channel/report");
        aVarA.b = "POST";
        aVarA.d = c1.a(m0.b("application/json; charset=utf-8"), d());
        aVarA.f6196e = "request zxid api";
        v1 v1Var = w.f6300a;
        a1 a1Var = new a1(aVar);
        v1Var.getClass();
        d1 d1VarA = new t0(v1Var, a1Var).a();
        if (d1VarA.b != 200) {
            throw new RuntimeException("response errCode: " + d1VarA.a("Udid-Error-Code") + ", errMsg: " + d1VarA.a("Udid-Error-Message"));
        }
        t1 t1Var = t1.a.f6285a;
        b3 b3Var = t1Var.f6284a;
        long jCurrentTimeMillis = System.currentTimeMillis();
        b3Var.getClass();
        if (jCurrentTimeMillis != t2.t) {
            t2.t = jCurrentTimeMillis;
            t1Var.f6284a.a(8, t2.t + "", false);
            y1.a("lastRequestTime had changed refresh:" + t2.t);
        }
        if (!TextUtils.isEmpty(f6308a)) {
            b3 b3Var2 = t1Var.f6284a;
            String str = f6308a;
            b3Var2.getClass();
            if (!TextUtils.equals(str, t2.l)) {
                t2.l = str;
                t1Var.f6284a.a(28, str, true);
                y1.a("lastReportExtListMD5 had changed refresh:" + t2.l);
            }
            f6308a = "";
        }
        s sVar = s.b.f6278a;
        sVar.getClass();
        try {
            c3.e.f6204a.b.execute(new t(sVar));
        } catch (Throwable th) {
            y1.a(th);
        }
        JSONObject jSONObject = new JSONObject(d1VarA.f6207e.b());
        int i = jSONObject.getInt("syncId");
        t1 t1Var2 = t1.a.f6285a;
        t1Var2.f6284a.d(i);
        JSONObject jSONObject2 = new JSONObject(j.a(Base64.decode(jSONObject.getString("data"), 2), c, "UDID_ENC_AUTHTAG"));
        String string = jSONObject2.getString(bm.al);
        t1Var2.f6284a.getClass();
        if (!TextUtils.equals(string, t2.i)) {
            t2.i = string;
            t1Var2.f6284a.a(1, string, true);
            y1.a("zid had changed refresh:" + string);
        }
        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("aids");
        JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("aidsExt");
        if (jSONObjectOptJSONObject2 == null) {
            jSONObjectOptJSONObject2 = new JSONObject();
        }
        if (!TextUtils.isEmpty(t2.f) && jSONObjectOptJSONObject != null) {
            jSONObjectOptJSONObject2.put(t2.f, jSONObjectOptJSONObject);
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("tags", jSONObject2.optJSONArray("tags"));
        jSONObject3.put("aids", jSONObjectOptJSONObject2);
        if (jSONObject2.has("openid")) {
            jSONObject3.put("openid", d3.c.get("ed6e6f5009a2"));
        } else {
            jSONObject3.put("openid", "OPENID_CLOSED");
        }
        JSONObject jSONObject4 = new JSONObject();
        if (jSONObject2.has("zxc1")) {
            jSONObject4.put("zxc1", jSONObject2.getString("zxc1"));
        }
        t1Var2.f6284a.getClass();
        String string2 = jSONObject4.toString();
        t2.k = string2;
        t1Var2.f6284a.a(30, string2, true);
        y1.a("zxc had changed refresh:" + t2.k);
        t1Var2.f6284a.getClass();
        String string3 = jSONObject3.toString();
        if (!TextUtils.isEmpty(string3)) {
            t2.j = string3;
            t1Var2.f6284a.a(16, string3, true);
            y1.a("ext had changed refresh:" + jSONObject3);
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cmds");
        b3 b3Var3 = t1Var2.f6284a;
        String str2 = t2.D;
        b3Var3.getClass();
        if (!TextUtils.isEmpty(str2) && !TextUtils.equals(str2, t2.C)) {
            t2.C = str2;
            t1Var2.f6284a.a(13, str2, true);
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("iaps");
        try {
            JSONArray jSONArray = new JSONArray();
            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    jSONArray.put(j.a(Base64.decode(jSONArrayOptJSONArray2.getString(i2), 2), c, "UDID_ENC_AUTHTAG"));
                }
            }
            t1 t1Var3 = t1.a.f6285a;
            t1Var3.f6284a.getClass();
            t1Var3.f6284a.a(25, jSONArray.toString(), true);
        } catch (Throwable unused) {
        }
        if (jSONArrayOptJSONArray != null) {
            try {
                if (jSONArrayOptJSONArray.length() == 0) {
                    return;
                }
                for (int i3 = 0; i3 < jSONArrayOptJSONArray.length(); i3++) {
                    int i4 = jSONArrayOptJSONArray.getInt(i3);
                    if (i4 == 1) {
                        y1.a("cmd 1 REQUEST_CONFIG ");
                        c3.e.f6204a.d.execute(new v0());
                    } else if (i4 == 2) {
                        t1.a.f6285a.f6284a.d(0);
                    } else if (i4 == 3) {
                        c3.e.f6204a.d.execute(new w0());
                    } else if (i4 == 4) {
                        c3.e.f6204a.d.execute(new x0());
                    } else if (i4 == 5) {
                        c3.e.f6204a.d.execute(new y0());
                    }
                }
            } catch (Throwable th2) {
                y1.a(th2);
            }
        }
    }
}
