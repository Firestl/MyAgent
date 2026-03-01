package com.igexin.push.core.a.a;

import android.os.Message;
import android.text.TextUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.b.w;
import com.igexin.push.core.d;
import com.igexin.push.core.e.f.AnonymousClass1;
import com.igexin.push.core.e.f.AnonymousClass12;
import com.igexin.push.core.e.f.AnonymousClass17;
import com.igexin.push.core.k;
import com.igexin.push.core.l;
import com.igexin.push.d.c;
import com.igexin.push.f.b.d;
import com.igexin.push.g.j;
import com.igexin.sdk.main.FeedbackImpl;
import com.igexin.sdk.router.GTBoater;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends com.igexin.push.core.a.a {
    public static final String b = "LoginResult";

    /* JADX INFO: renamed from: com.igexin.push.core.a.a.c$1, reason: invalid class name */
    public class AnonymousClass1 extends com.igexin.push.f.d {
        public AnonymousClass1() {
        }

        @Override // com.igexin.push.f.d
        public final void b() {
            try {
                com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis != com.igexin.push.core.e.R) {
                    com.igexin.push.core.e.R = jCurrentTimeMillis;
                    com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) fVarA.new AnonymousClass12(), false, true);
                }
                com.igexin.push.core.c.a aVarA = com.igexin.push.core.c.a.a();
                ArrayList arrayList = new ArrayList();
                aVarA.a((List<w>) arrayList);
                if (arrayList.isEmpty()) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("action", "reportapplist");
                    jSONObject.put("session_last", com.igexin.push.core.e.z);
                    JSONArray jSONArray = new JSONArray();
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("appid", arrayList.get(i).c);
                        jSONObject2.put("name", arrayList.get(i).f3372a);
                        jSONObject2.put("version", arrayList.get(i).b);
                        jSONObject2.put(com.heytap.mcssdk.d.p, arrayList.get(i).d);
                        jSONObject2.put("notificationEnabled", arrayList.get(i).f3373e);
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put("applist", jSONArray);
                } catch (Exception e2) {
                    com.igexin.c.a.c.a.a(e2);
                }
                com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.f.a.a(new com.igexin.push.core.h.a(SDKUrlConfig.getBiUploadServiceUrl(), jSONObject.toString().getBytes())), false, true);
                com.igexin.c.a.c.a.a("reportAL", new Object[0]);
                if (com.igexin.push.config.d.ak) {
                    com.igexin.c.a.c.a.a("reportAL = " + jSONObject.toString(), new Object[0]);
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.a.a.c$2, reason: invalid class name */
    public class AnonymousClass2 extends com.igexin.push.f.d {
        public AnonymousClass2() {
        }

        @Override // com.igexin.push.f.d
        public final void b() {
            try {
                com.igexin.push.core.e.d dVarA = com.igexin.push.core.e.d.a(com.igexin.push.core.e.l);
                JSONObject jSONObjectA = dVarA.a();
                if (jSONObjectA == null) {
                    return;
                }
                Iterator<String> itKeys = jSONObjectA.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONObject jSONObject = jSONObjectA.getJSONObject(next);
                    com.igexin.c.a.c.a.a("LoginResult|send unFeedback taskid = ".concat(String.valueOf(next)), new Object[0]);
                    jSONObject.put("appid", com.igexin.push.core.e.f3403a);
                    FeedbackImpl.getInstance().feedbackMultiBrandMessageAction(jSONObject, jSONObject.getString("multaid"));
                    itKeys.remove();
                }
                dVarA.b();
            } catch (Throwable th) {
                com.igexin.c.a.c.a.b(c.b, "feedbackMultiBrandPushMessage exception :" + th.toString());
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    private void d() {
        com.igexin.c.a.c.a.d.a().a("[LoginResult] Login successed with cid = " + com.igexin.push.core.e.A);
        com.igexin.push.d.c cVar = c.b.f3517a;
        cVar.c = System.currentTimeMillis();
        if (cVar.b) {
            com.igexin.c.a.c.a.a(com.igexin.push.d.c.f3514a, "loginRsp| enter polling");
            cVar.f3515e = new com.igexin.push.d.e();
            d.a.f3561a.g();
            cVar.d = 0;
        } else {
            cVar.b();
        }
        String str = com.igexin.push.core.e.A;
        boolean z = com.igexin.push.core.e.v;
        com.igexin.c.a.c.a.a("loginRsp|" + com.igexin.push.core.e.A + "|success", new Object[0]);
        StringBuilder sb = new StringBuilder("isCidBroadcasted|");
        sb.append(com.igexin.push.core.e.v);
        com.igexin.c.a.c.a.a(sb.toString(), new Object[0]);
        if (!com.igexin.push.core.e.v) {
            l.a().c();
            com.igexin.push.core.e.v = true;
        }
        com.igexin.push.core.e.u = true;
        j.g();
        l.a().b();
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.g();
        if (TextUtils.isEmpty(com.igexin.push.core.e.H)) {
            com.igexin.c.a.c.a.a("LoginResult device id is empty, get device id from server +++++", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.h();
        }
        if ((System.currentTimeMillis() - com.igexin.push.core.e.R) - 86400000 >= 0) {
            com.igexin.c.a.c.a.a("LoginResult, over 24h, start upload AL", new Object[0]);
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass1(), false, true);
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.Q;
        com.igexin.c.a.c.a.a("LoginResult|lastAddphoneinfoTime: " + com.igexin.push.core.e.Q, new Object[0]);
        boolean z2 = jCurrentTimeMillis - 86400000 > 0;
        boolean z3 = !com.igexin.c.b.a.a(com.igexin.push.core.e.K, com.igexin.push.core.e.I);
        boolean z4 = !com.igexin.push.core.e.A.equals(com.igexin.push.core.e.B);
        boolean zB = com.igexin.push.g.c.b(com.igexin.push.core.e.l);
        boolean z5 = com.igexin.push.core.e.J != zB;
        if (z5) {
            com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
            if (com.igexin.push.core.e.J != zB) {
                com.igexin.push.core.e.J = zB ? 1 : 0;
                com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) fVarA.new AnonymousClass17(), false, true);
            }
        }
        com.igexin.c.a.c.a.a("LoginResult|isOverOneDay = " + z2 + ", isDeviceTokenDiff = " + z3 + ", isCidDiff = " + z4 + ", isNotificationEnableDiff= " + z5, new Object[0]);
        if (z2 || z3 || z4 || z5) {
            com.igexin.push.core.a.b.d().i();
        }
        com.igexin.push.core.c.a.a();
        com.igexin.push.core.c.a.b();
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.core.e.f.a().new AnonymousClass1(), false, true);
        if (!com.igexin.push.core.e.A.equals(com.igexin.push.core.e.B)) {
            com.igexin.push.core.e.B = com.igexin.push.core.e.A;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = com.igexin.push.core.b.W;
        messageObtain.obj = new Object();
        d.a.f3384a.a(messageObtain);
        GTBoater.getInstance().initialize();
        if (com.igexin.assist.sdk.a.a().c()) {
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass2(), false, true);
        }
    }

    public static void e() {
        long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.Q;
        com.igexin.c.a.c.a.a("LoginResult|lastAddphoneinfoTime: " + com.igexin.push.core.e.Q, new Object[0]);
        boolean z = jCurrentTimeMillis - 86400000 > 0;
        boolean z2 = !com.igexin.c.b.a.a(com.igexin.push.core.e.K, com.igexin.push.core.e.I);
        boolean z3 = !com.igexin.push.core.e.A.equals(com.igexin.push.core.e.B);
        boolean zB = com.igexin.push.g.c.b(com.igexin.push.core.e.l);
        boolean z4 = com.igexin.push.core.e.J != zB;
        if (z4) {
            com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
            if (com.igexin.push.core.e.J != zB) {
                com.igexin.push.core.e.J = zB ? 1 : 0;
                com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) fVarA.new AnonymousClass17(), false, true);
            }
        }
        com.igexin.c.a.c.a.a("LoginResult|isOverOneDay = " + z + ", isDeviceTokenDiff = " + z2 + ", isCidDiff = " + z3 + ", isNotificationEnableDiff= " + z4, new Object[0]);
        if (z || z2 || z3 || z4) {
            com.igexin.push.core.a.b.d().i();
        }
    }

    public static void f() {
        com.igexin.c.a.c.a.d.a().a("[LoginResult] Login " + com.igexin.push.core.e.A + " failed");
        com.igexin.c.a.c.a.a(b, "login failed, clear session or cid");
        com.igexin.c.a.c.a.a("LoginResult login failed, clear session or cid", new Object[0]);
        com.igexin.push.core.e.f.a().b();
        k.a();
        k.c();
    }

    private void g() {
        if ((System.currentTimeMillis() - com.igexin.push.core.e.R) - 86400000 < 0) {
            return;
        }
        com.igexin.c.a.c.a.a("LoginResult, over 24h, start upload AL", new Object[0]);
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass1(), false, true);
    }

    public static void h() {
        if (com.igexin.push.core.e.A.equals(com.igexin.push.core.e.B)) {
            return;
        }
        com.igexin.push.core.e.B = com.igexin.push.core.e.A;
    }

    private void i() {
        if (com.igexin.assist.sdk.a.a().c()) {
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass2(), false, true);
        }
    }

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (obj instanceof com.igexin.push.d.c.k) {
            com.igexin.push.core.e.b(0L);
            if (!com.igexin.push.core.e.u) {
                com.igexin.push.c.c.a().d().e();
                if (((com.igexin.push.d.c.k) obj).b) {
                    com.igexin.c.a.c.a.d.a().a("[LoginResult] Login successed with cid = " + com.igexin.push.core.e.A);
                    com.igexin.push.d.c cVar = c.b.f3517a;
                    cVar.c = System.currentTimeMillis();
                    if (cVar.b) {
                        com.igexin.c.a.c.a.a(com.igexin.push.d.c.f3514a, "loginRsp| enter polling");
                        cVar.f3515e = new com.igexin.push.d.e();
                        d.a.f3561a.g();
                        cVar.d = 0;
                    } else {
                        cVar.b();
                    }
                    String str = com.igexin.push.core.e.A;
                    boolean z = com.igexin.push.core.e.v;
                    com.igexin.c.a.c.a.a("loginRsp|" + com.igexin.push.core.e.A + "|success", new Object[0]);
                    StringBuilder sb = new StringBuilder("isCidBroadcasted|");
                    sb.append(com.igexin.push.core.e.v);
                    com.igexin.c.a.c.a.a(sb.toString(), new Object[0]);
                    if (!com.igexin.push.core.e.v) {
                        l.a().c();
                        com.igexin.push.core.e.v = true;
                    }
                    com.igexin.push.core.e.u = true;
                    j.g();
                    l.a().b();
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.g();
                    if (TextUtils.isEmpty(com.igexin.push.core.e.H)) {
                        com.igexin.c.a.c.a.a("LoginResult device id is empty, get device id from server +++++", new Object[0]);
                        com.igexin.push.core.a.b.d();
                        com.igexin.push.core.a.b.h();
                    }
                    if ((System.currentTimeMillis() - com.igexin.push.core.e.R) - 86400000 >= 0) {
                        com.igexin.c.a.c.a.a("LoginResult, over 24h, start upload AL", new Object[0]);
                        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass1(), false, true);
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.Q;
                    com.igexin.c.a.c.a.a("LoginResult|lastAddphoneinfoTime: " + com.igexin.push.core.e.Q, new Object[0]);
                    boolean z2 = jCurrentTimeMillis - 86400000 > 0;
                    boolean z3 = !com.igexin.c.b.a.a(com.igexin.push.core.e.K, com.igexin.push.core.e.I);
                    boolean z4 = !com.igexin.push.core.e.A.equals(com.igexin.push.core.e.B);
                    boolean zB = com.igexin.push.g.c.b(com.igexin.push.core.e.l);
                    boolean z5 = com.igexin.push.core.e.J != zB;
                    if (z5) {
                        com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
                        if (com.igexin.push.core.e.J != zB) {
                            com.igexin.push.core.e.J = zB ? 1 : 0;
                            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) fVarA.new AnonymousClass17(), false, true);
                        }
                    }
                    com.igexin.c.a.c.a.a("LoginResult|isOverOneDay = " + z2 + ", isDeviceTokenDiff = " + z3 + ", isCidDiff = " + z4 + ", isNotificationEnableDiff= " + z5, new Object[0]);
                    if (z2 || z3 || z4 || z5) {
                        com.igexin.push.core.a.b.d().i();
                    }
                    com.igexin.push.core.c.a.a();
                    com.igexin.push.core.c.a.b();
                    com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.core.e.f.a().new AnonymousClass1(), false, true);
                    if (!com.igexin.push.core.e.A.equals(com.igexin.push.core.e.B)) {
                        com.igexin.push.core.e.B = com.igexin.push.core.e.A;
                    }
                    Message messageObtain = Message.obtain();
                    messageObtain.what = com.igexin.push.core.b.W;
                    messageObtain.obj = new Object();
                    d.a.f3384a.a(messageObtain);
                    GTBoater.getInstance().initialize();
                    if (com.igexin.assist.sdk.a.a().c()) {
                        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass2(), false, true);
                    }
                } else {
                    com.igexin.c.a.c.a.d.a().a("[LoginResult] Login " + com.igexin.push.core.e.A + " failed");
                    com.igexin.c.a.c.a.a(b, "login failed, clear session or cid");
                    com.igexin.c.a.c.a.a("LoginResult login failed, clear session or cid", new Object[0]);
                    com.igexin.push.core.e.f.a().b();
                    k.a();
                    k.c();
                }
            }
        }
        return true;
    }

    @Override // com.igexin.push.core.a.a
    public final void b() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean c() {
        return false;
    }
}
