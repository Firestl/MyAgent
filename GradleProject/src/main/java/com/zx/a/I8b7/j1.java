package com.zx.a.I8b7;

import android.util.Base64;
import com.zx.a.I8b7.a1;
import com.zx.module.annotation.Java2C;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.crypto.SecretKey;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SecretKey f6234a;
    public static byte[] b;
    public static final SecureRandom c = new SecureRandom();

    @Java2C.Method2C
    public static native synchronized String a();

    @Java2C.Method2C
    public static native String b() throws Exception;

    public static String c() throws Exception {
        a1.a aVar = new a1.a();
        HashMap<String, String> mapB = w.b(a());
        aVar.c.clear();
        aVar.c.putAll(mapB);
        a1.a aVarA = aVar.a("https://zxid-m.mobileservice.cn/sdk/extend/tag");
        aVarA.b = "POST";
        aVarA.d = c1.a(m0.b("application/json; charset=utf-8"), b());
        aVarA.f6196e = "tagInfoRequest get api";
        v1 v1Var = w.f6300a;
        a1 a1Var = new a1(aVar);
        v1Var.getClass();
        d1 d1VarA = new t0(v1Var, a1Var).a();
        if (d1VarA.b == 200) {
            return j.a(Base64.decode(new JSONObject(d1VarA.f6207e.b()).getString("data"), 2), f6234a, "UDID_ENC_AUTHTAG");
        }
        throw new RuntimeException("response errCode: " + d1VarA.a("Udid-Error-Code") + ", errMsg: " + d1VarA.a("Udid-Error-Message"));
    }
}
