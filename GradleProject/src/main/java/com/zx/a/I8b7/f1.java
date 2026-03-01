package com.zx.a.I8b7;

import com.zx.a.I8b7.a1;
import com.zx.module.annotation.Java2C;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes2.dex */
public class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SecretKey f6217a;
    public static byte[] b;
    public static final SecureRandom c = new SecureRandom();

    @Java2C.Method2C
    public static native synchronized String a();

    @Java2C.Method2C
    public static native String a(String str, String str2) throws Exception;

    public static void b(String str, String str2) throws Exception {
        a1.a aVar = new a1.a();
        HashMap<String, String> mapB = w.b(a());
        aVar.c.clear();
        aVar.c.putAll(mapB);
        a1.a aVarA = aVar.a("https://zxid-m.mobileservice.cn/sdk/uaid/reportAuthToken");
        aVarA.b = "POST";
        aVarA.d = c1.a(m0.b("application/json; charset=utf-8"), a(str, str2));
        aVarA.f6196e = "SAIDCodeRequest get api";
        v1 v1Var = w.f6300a;
        a1 a1Var = new a1(aVar);
        v1Var.getClass();
        new t0(v1Var, a1Var).a();
    }
}
