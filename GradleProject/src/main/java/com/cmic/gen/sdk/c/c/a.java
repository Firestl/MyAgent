package com.cmic.gen.sdk.c.c;

import android.os.SystemClock;
import com.cmic.gen.sdk.b;
import com.cmic.gen.sdk.c.b.e;
import com.cmic.gen.sdk.c.b.f;
import com.cmic.gen.sdk.c.b.h;
import com.cmic.gen.sdk.e.i;
import com.cmic.gen.sdk.e.k;
import com.cmic.gen.sdk.e.m;
import com.cmic.gen.sdk.e.o;
import com.cmic.gen.sdk.e.q;
import com.tencent.open.SocialConstants;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import io.dcloud.common.adapter.util.DeviceInfo;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f1715a;

    public static a a() {
        if (f1715a == null) {
            synchronized (a.class) {
                if (f1715a == null) {
                    f1715a = new a();
                }
            }
        }
        return f1715a;
    }

    private void a(final c cVar, final d dVar, final com.cmic.gen.sdk.a aVar) {
        com.cmic.gen.sdk.c.a.d dVar2 = new com.cmic.gen.sdk.c.a.d();
        com.cmic.gen.sdk.c.a.c cVar2 = new com.cmic.gen.sdk.c.a.c();
        com.cmic.gen.sdk.c.a.a aVar2 = new com.cmic.gen.sdk.c.a.a();
        dVar2.a(cVar2);
        cVar2.a(aVar2);
        cVar.a(SystemClock.elapsedRealtime());
        dVar2.a(cVar, new com.cmic.gen.sdk.c.d.c() { // from class: com.cmic.gen.sdk.c.c.a.1
            private void a() {
                if (cVar.a().contains("uniConfig")) {
                    return;
                }
                q.c(aVar, String.valueOf(SystemClock.elapsedRealtime() - cVar.i()));
            }

            @Override // com.cmic.gen.sdk.c.d.c
            public void a(com.cmic.gen.sdk.c.d.a aVar3) {
                if (cVar.g()) {
                    a();
                    q.b(aVar, String.valueOf(aVar3.a()));
                    dVar.a(String.valueOf(aVar3.a()), aVar3.b(), com.cmic.gen.sdk.auth.d.a(String.valueOf(aVar3.a()), aVar3.b()));
                }
            }

            @Override // com.cmic.gen.sdk.c.d.c
            public void a(com.cmic.gen.sdk.c.d.b bVar) {
                if (cVar.g()) {
                    try {
                        a();
                        JSONObject jSONObject = new JSONObject(bVar.c());
                        String string = jSONObject.has("resultcode") ? jSONObject.getString("resultcode") : jSONObject.getString("resultCode");
                        q.b(aVar, string);
                        dVar.a(string, jSONObject.optString(SocialConstants.PARAM_APP_DESC), jSONObject);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        a(com.cmic.gen.sdk.c.d.a.a(102223));
                    }
                }
            }
        }, aVar);
    }

    public void a(com.cmic.gen.sdk.a aVar, d dVar) {
        String str;
        c cVar;
        String strA;
        int iC = aVar.c("networktype");
        h hVar = new h();
        hVar.b("1.0");
        hVar.c(com.cmic.gen.sdk.auth.c.SDK_VERSION);
        hVar.d(aVar.b("appid"));
        hVar.e(aVar.b("operatortype"));
        hVar.f(iC + "");
        hVar.g(m.a());
        hVar.h(m.b());
        hVar.i(m.c());
        hVar.j("0");
        hVar.k("3.0");
        hVar.l(q.b());
        hVar.m(o.a());
        hVar.o(aVar.b("apppackage"));
        hVar.p(aVar.b("appsign"));
        hVar.a_(k.b("AID", ""));
        if (aVar.c("logintype") == 3 || aVar.b("isRisk", false)) {
            str = "pre";
        } else {
            hVar.w(aVar.b("userCapaid"));
            hVar.w(aVar.c("logintype") == 1 ? "200" : "50");
            str = "authz";
        }
        hVar.s(str);
        q.a(aVar, "scripAndTokenForHttps");
        com.cmic.gen.sdk.a.a aVarB = aVar.b();
        if (aVar.b("isCacheScrip", false) || aVar.c("logintype") == 1 || aVar.b("isGotScrip", false)) {
            hVar.v(aVar.b("phonescrip"));
            hVar.n(hVar.u(aVar.b("appkey")));
            cVar = new c(DeviceInfo.HTTPS_PROTOCOL + aVarB.a() + "/unisdk/rs/scripAndTokenForHttps", hVar, "POST", aVar.b("traceId"));
            cVar.a("defendEOF", "0");
        } else {
            e eVar = new e();
            eVar.a(aVar.a(b.a.f1689a));
            eVar.b(aVar.a(b.a.b));
            eVar.a(hVar);
            eVar.a(false);
            aVar.a("isCloseIpv4", aVarB.h());
            aVar.a("isCloseIpv6", aVarB.i());
            String str2 = DeviceInfo.HTTPS_PROTOCOL + aVarB.b() + "/unisdk/rs/scripAndTokenForHttps";
            if (aVar.b("use2048PublicKey", false)) {
                com.cmic.gen.sdk.e.c.a("BaseRequest", "使用2对应的编码");
                eVar.b("2");
                strA = i.a().b(aVar.a(b.a.f1689a));
            } else {
                strA = i.a().a(aVar.a(b.a.f1689a));
            }
            eVar.c(strA);
            cVar = new b(str2, eVar, "POST", aVar.b("traceId"));
            cVar.a("defendEOF", "1");
            if (iC == 3) {
                cVar.a(true);
                aVar.a("doNetworkSwitch", true);
            } else {
                cVar.a(false);
                aVar.a("doNetworkSwitch", false);
            }
        }
        cVar.a("interfaceVersion", "3.0");
        a(cVar, dVar, aVar);
    }

    public void a(JSONObject jSONObject, com.cmic.gen.sdk.a aVar, d dVar) {
        f fVar = new f();
        f.a aVar2 = new f.a();
        f.b bVar = new f.b();
        bVar.e(q.b());
        bVar.f(o.a());
        bVar.b(UMCrashManager.CM_VERSION);
        bVar.c(aVar.b("appid", ""));
        bVar.d(bVar.u(""));
        aVar2.a(jSONObject);
        fVar.a(aVar2);
        fVar.a(bVar);
        a(new c(DeviceInfo.HTTPS_PROTOCOL + aVar.b().d() + "/log/logReport", fVar, "POST", aVar.b("traceId")), dVar, aVar);
    }

    public void a(boolean z, com.cmic.gen.sdk.a aVar, d dVar) {
        com.cmic.gen.sdk.c.b.b bVar = new com.cmic.gen.sdk.c.b.b();
        bVar.b("1.0");
        bVar.c("Android");
        bVar.d(k.b("AID", ""));
        bVar.e(z ? "1" : "0");
        bVar.f(com.cmic.gen.sdk.auth.c.SDK_VERSION);
        bVar.g(aVar.b("appid"));
        bVar.h(bVar.u("iYm0HAnkxQtpvN44"));
        a(new c(DeviceInfo.HTTPS_PROTOCOL + aVar.b().c() + "/client/uniConfig", bVar, "POST", aVar.b("traceId")), dVar, aVar);
    }
}
