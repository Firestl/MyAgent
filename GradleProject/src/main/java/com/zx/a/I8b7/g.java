package com.zx.a.I8b7;

import android.util.Base64;
import com.umeng.analytics.pro.bm;
import com.zx.module.annotation.Java2C;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SecretKey f6220a;
    public static byte[] b;
    public static final SecureRandom c = new SecureRandom();

    @Java2C.Method2C
    public static native synchronized String a();

    @Java2C.Method2C
    public static native void a(String str);

    public static String b(String str) throws Exception {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("lid", t2.a(t2.h));
        jSONObject2.put(bm.al, t2.i);
        jSONObject.put("ctx", jSONObject2);
        jSONObject.put("code", str);
        return new String(Base64.encode(j.a(jSONObject.toString(), f6220a, "UDID_ENC_AUTHTAG"), 2), StandardCharsets.UTF_8);
    }
}
