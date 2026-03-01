package com.cmic.gen.sdk.d;

import android.content.Context;
import android.text.TextUtils;
import com.cmic.gen.sdk.e.f;
import com.cmic.gen.sdk.e.m;
import com.cmic.gen.sdk.e.n;
import com.cmic.gen.sdk.e.o;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static C0017a<String, String> f1721a = new C0017a<>();

    /* JADX INFO: renamed from: com.cmic.gen.sdk.d.a$a, reason: collision with other inner class name */
    public static class C0017a<K, V> extends HashMap<K, V> {
        public C0017a() {
        }

        public V a(Object obj, V v) {
            return (!containsKey(obj) || get(obj) == null) ? v : get(obj);
        }
    }

    public static void a() {
        String strValueOf = String.valueOf(0);
        f1721a.put("authPageIn", strValueOf);
        f1721a.put("authPageOut", strValueOf);
        f1721a.put("authClickFailed", strValueOf);
        f1721a.put("authClickSuccess", strValueOf);
        f1721a.put("timeOnAuthPage", strValueOf);
        f1721a.put("authPrivacyState", strValueOf);
    }

    public static void a(Context context, final com.cmic.gen.sdk.a aVar) {
        try {
            if (aVar.b().j()) {
                return;
            }
            com.cmic.gen.sdk.view.a aVar2 = new com.cmic.gen.sdk.view.a();
            String strValueOf = String.valueOf(0);
            aVar2.e(!f1721a.a("authPageIn", strValueOf).equals(strValueOf) ? f1721a.get("authPageIn") : null);
            aVar2.f(!f1721a.a("authPageOut", strValueOf).equals(strValueOf) ? f1721a.get("authPageOut") : null);
            aVar2.c(!f1721a.a("authClickSuccess", strValueOf).equals(strValueOf) ? f1721a.get("authClickSuccess") : null);
            aVar2.b(!f1721a.a("authClickFailed", strValueOf).equals(strValueOf) ? f1721a.get("authClickFailed") : null);
            aVar2.d(f1721a.a("timeOnAuthPage", strValueOf).equals(strValueOf) ? null : f1721a.get("timeOnAuthPage"));
            aVar2.a(f1721a.a("authPrivacyState", strValueOf));
            JSONObject jSONObjectA = aVar2.a();
            final c cVar = new c();
            cVar.b(aVar.b("appid", ""));
            cVar.s(aVar.b("traceId"));
            cVar.b(aVar.b("appid"));
            cVar.j(f.a(context));
            cVar.k(f.b(context));
            cVar.l(aVar.b("timeOut"));
            cVar.t(f1721a.a("authPageInTime", ""));
            cVar.w(f1721a.a("authPageOutTime", ""));
            cVar.x("eventTracking5");
            cVar.o(aVar.b("operatortype", ""));
            cVar.y(aVar.b("networktype", 0) + "");
            cVar.v(aVar.b("networkClass"));
            cVar.f(m.a());
            cVar.p(m.b());
            cVar.q(m.c());
            cVar.n(aVar.b("simCardNum"));
            cVar.c(aVar.b("hsaReadPhoneStatePermission", false) ? "1" : "0");
            cVar.a(jSONObjectA);
            cVar.d(aVar.b("imsiState", "0"));
            cVar.m((System.currentTimeMillis() - aVar.b("methodTimes", 0L)) + "");
            com.cmic.gen.sdk.e.c.a("EventUtils", "埋点日志上报" + cVar.b());
            n.a(new n.a() { // from class: com.cmic.gen.sdk.d.a.1
                @Override // com.cmic.gen.sdk.e.n.a
                public void a() {
                    new d().a(cVar.b(), aVar);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(String str) {
        try {
            String str2 = f1721a.get(str);
            f1721a.put(str, String.valueOf((TextUtils.isEmpty(str2) ? 0 : Integer.parseInt(str2)) + 1));
            f1721a.put(str + "Time", o.a());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(String str, String str2) {
        f1721a.put(str, str2);
    }
}
