package com.zx.a.I8b7;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesCbcKS;
import com.zx.a.I8b7.a1;
import com.zx.a.I8b7.t1;
import com.zx.module.annotation.Java2C;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SecretKey f6224a;
    public static byte[] b;
    public static final SecureRandom c = new SecureRandom();

    @Java2C.Method2C
    public static native synchronized String a();

    @Java2C.Method2C
    public static native String b() throws Exception;

    public static void c() throws Exception {
        a1.a aVar = new a1.a();
        HashMap<String, String> mapB = w.b(a());
        aVar.c.clear();
        aVar.c.putAll(mapB);
        a1.a aVarA = aVar.a("https://zxid-m.mobileservice.cn/sdk/config/init");
        aVarA.b = "POST";
        aVarA.d = c1.a(m0.b("application/json; charset=utf-8"), b());
        aVarA.f6196e = "request config api";
        v1 v1Var = w.f6300a;
        a1 a1Var = new a1(aVar);
        v1Var.getClass();
        d1 d1VarA = new t0(v1Var, a1Var).a();
        if (d1VarA.b != 200) {
            throw new RuntimeException("response errCode: " + d1VarA.a("Udid-Error-Code") + ", errMsg: " + d1VarA.a("Udid-Error-Message"));
        }
        JSONObject jSONObject = new JSONObject(j.a(Base64.decode(new JSONObject(d1VarA.f6207e.b()).getString("data"), 2), f6224a, "UDID_ENC_AUTHTAG"));
        String string = jSONObject.getString("configVersion");
        t1 t1Var = t1.a.f6285a;
        t1Var.f6284a.getClass();
        if (!TextUtils.equals(string, t2.n)) {
            t2.n = string;
            t1Var.f6284a.a(4, string, false);
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("fieldConfig");
        b3 b3Var = t1Var.f6284a;
        String string2 = jSONObject2.toString();
        b3Var.getClass();
        if (!TextUtils.equals(string2, t2.w)) {
            t2.w = string2;
            t1Var.f6284a.a(11, string2, true);
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject("reportConfig");
        b3 b3Var2 = t1Var.f6284a;
        String string3 = jSONObject3.toString();
        b3Var2.getClass();
        if (!TextUtils.equals(string3, t2.x)) {
            t2.x = string3;
            t1Var.f6284a.a(12, string3, true);
        }
        JSONObject jSONObject4 = jSONObject.getJSONObject("cryptoConfig");
        b3 b3Var3 = t1Var.f6284a;
        String string4 = jSONObject4.toString();
        b3Var3.getClass();
        if (!TextUtils.equals(string4, t2.y)) {
            t2.y = string4;
            t1Var.f6284a.a(15, string4, true);
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("appConfig");
        if (jSONObjectOptJSONObject != null) {
            y1.a("处理 appConfig ");
            try {
                JSONArray jSONArray = jSONObjectOptJSONObject.getJSONArray("list");
                if (jSONArray == null || jSONArray.length() <= 0) {
                    y1.b("appConfig list is empty");
                } else {
                    int length = jSONArray.length();
                    int i = jSONObjectOptJSONObject.getInt("type");
                    if (i == 1) {
                        for (int i2 = 0; i2 < length; i2++) {
                            jSONArray.put(i2, j.a(Base64.decode(jSONArray.getString(i2), 2), f6224a, "UDID_ENC_AUTHTAG"));
                        }
                    } else if (i == 3) {
                        SecretKey secretKeyA = j.a(b, t2.a(t2.h));
                        for (int i3 = 0; i3 < length; i3++) {
                            jSONArray.put(i3, new String(j.a(AesCbcKS.c, secretKeyA, new IvParameterSpec("UDID_ENC_AUTHTAG".getBytes(StandardCharsets.UTF_8)), Base64.decode(jSONArray.getString(i3), 2)), StandardCharsets.UTF_8));
                        }
                    }
                    t1 t1Var2 = t1.a.f6285a;
                    b3 b3Var4 = t1Var2.f6284a;
                    String string5 = jSONObjectOptJSONObject.toString();
                    b3Var4.getClass();
                    if (!TextUtils.equals(string5, t2.z)) {
                        t2.z = string5;
                        t1Var2.f6284a.a(21, string5, true);
                    }
                }
            } catch (Exception e2) {
                y1.a(e2);
            }
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("commonConfig");
        if (jSONObjectOptJSONObject2 != null) {
            t1 t1Var3 = t1.a.f6285a;
            b3 b3Var5 = t1Var3.f6284a;
            String string6 = jSONObjectOptJSONObject2.toString();
            b3Var5.getClass();
            if (!TextUtils.equals(string6, t2.A)) {
                t2.A = string6;
                t1Var3.f6284a.a(22, string6, true);
            }
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("invokeConfig");
        if (jSONObjectOptJSONObject3 != null) {
            t1 t1Var4 = t1.a.f6285a;
            b3 b3Var6 = t1Var4.f6284a;
            String string7 = jSONObjectOptJSONObject3.toString();
            synchronized (b3Var6) {
                if (!TextUtils.equals(string7, t2.B)) {
                    t2.B = string7;
                    t2.c();
                    t1Var4.f6284a.a(19, t2.B, true);
                }
            }
        }
        if (t2.o) {
            return;
        }
        t1 t1Var5 = t1.a.f6285a;
        t1Var5.f6284a.getClass();
        if (true != t2.o) {
            t2.o = true;
            t1Var5.f6284a.a(6, t2.o + "", false);
        }
    }
}
