package com.zx.a.I8b7;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.bm;
import com.zx.a.I8b7.a1;
import com.zx.a.I8b7.t1;
import com.zx.module.annotation.Java2C;
import io.dcloud.common.adapter.ui.webview.WebLoadEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.crypto.SecretKey;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SecretKey f6295a;
    public static byte[] b;
    public static final SecureRandom c = new SecureRandom();

    @Java2C.Method2C
    public static native synchronized String a();

    public static void b() throws Exception {
        String string;
        a1.a aVar = new a1.a();
        HashMap<String, String> mapB = w.b(a());
        aVar.c.clear();
        aVar.c.putAll(mapB);
        a1.a aVarA = aVar.a("https://zxid-m.mobileservice.cn/sdk/module/getCoreModule");
        aVarA.b = "POST";
        m0 m0VarB = m0.b("application/json; charset=utf-8");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("lid", t2.a(t2.h));
        jSONObject2.put(bm.al, t2.i);
        jSONObject.put("ctx", jSONObject2);
        jSONObject.put("sdkInfo", w.d());
        jSONObject.put("deviceInfo", w.b());
        aVarA.d = c1.a(m0VarB, new String(Base64.encode(j.a(jSONObject.toString(), f6295a, "UDID_ENC_AUTHTAG"), 2), StandardCharsets.UTF_8));
        aVarA.f6196e = "request getCoreModule api";
        v1 v1Var = w.f6300a;
        a1 a1Var = new a1(aVar);
        v1Var.getClass();
        d1 d1VarA = new t0(v1Var, a1Var).a();
        if (d1VarA.b != 200) {
            throw new RuntimeException("response errCode: " + d1VarA.a("Udid-Error-Code") + ", errMsg: " + d1VarA.a("Udid-Error-Message"));
        }
        JSONObject jSONObject3 = new JSONObject(j.a(Base64.decode(new JSONObject(d1VarA.f6207e.b()).getString("data"), 2), f6295a, "UDID_ENC_AUTHTAG"));
        if (!jSONObject3.getBoolean(WebLoadEvent.ENABLE)) {
            b3 b3Var = t1.a.f6285a.f6284a;
            if (b3Var.b == null) {
                b3Var.b = b3Var.d();
            }
            try {
                SQLiteDatabase sQLiteDatabase = b3Var.b;
                StringBuilder sb = new StringBuilder();
                sb.append("key in(");
                sb.append("17,18");
                sb.append(")");
                sQLiteDatabase.delete("zx_table", sb.toString(), null);
                t2.E = null;
                y1.a("clearCoreModule success");
            } catch (Exception e2) {
                StringBuilder sbA = m2.a("clearCoreModule error:");
                sbA.append(e2.getMessage());
                y1.b(sbA.toString());
            }
            y1.a("coreModule enable is false");
            return;
        }
        JSONObject jSONObject4 = jSONObject3.getJSONObject("module");
        jSONObject4.getString("version");
        String string2 = jSONObject4.getString("checksum");
        byte[] bArrDecode = Base64.decode(jSONObject4.getString("data"), 0);
        if (!TextUtils.equals(string2, j.a("SHA256", bArrDecode))) {
            throw new IOException("zx checksum1 exception");
        }
        y1.a("verify checksum finished");
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("mainVersion", t2.b);
        jSONObject5.put("coreVersion", t2.d);
        jSONObject5.put("checksum", string2);
        t1.a.f6285a.f6284a.getClass();
        String string3 = jSONObject5.getString("coreVersion");
        try {
            string = t2.E.getString("coreVersion");
        } catch (Exception unused) {
            string = "";
        }
        if (!TextUtils.isEmpty(string3) && !TextUtils.equals(string3, string)) {
            b3 b3Var2 = t1.a.f6285a.f6284a;
            if (b3Var2.b == null) {
                b3Var2.b = b3Var2.d();
            }
            try {
                String str = new String(Base64.encode(j.b("AES/CBC/PKCS5Padding", t2.u, t2.v, bArrDecode), 0), StandardCharsets.UTF_8);
                ContentValues contentValues = new ContentValues();
                contentValues.put("key", (Integer) 17);
                contentValues.put("value", str);
                y1.a("replace resultId = " + b3Var2.b.replace("zx_table", null, contentValues));
            } catch (Exception e3) {
                y1.b("ZXID updateDBValue valueID:17,value:" + bArrDecode + ",error:" + e3.toString());
            }
            t1.a.f6285a.f6284a.a(18, jSONObject5.toString(), true);
            t2.E = jSONObject5;
        }
        y1.a("decrypt and checksum finished");
    }
}
