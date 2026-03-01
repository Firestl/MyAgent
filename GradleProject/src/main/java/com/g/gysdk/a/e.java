package com.g.gysdk.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.al;
import com.g.gysdk.a.an;
import com.g.gysdk.a.n;
import com.g.gysdk.a.z;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f2002a = new AtomicInteger(0);
    public static boolean b = false;
    public static volatile com.g.gysdk.b c;

    public static abstract class a extends BroadcastReceiver {
        public final Context d;

        public a(Context context) {
            this.d = context;
        }

        public void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.d.registerReceiver(this, intentFilter);
        }

        public abstract void a(Context context);

        public void b() {
            this.d.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && ar.b(context)) {
                try {
                    a(context);
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
    }

    public static com.g.gysdk.b a() {
        return c;
    }

    public static void a(z.b bVar, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", d.f2000e);
            jSONObject.put("b", System.currentTimeMillis());
            jSONObject.put("c", d.g);
            jSONObject.put(cn.com.chinatelecom.account.api.a.d.f1473a, bVar.a());
            jSONObject.put("e", b || !z);
            if (TextUtils.isEmpty(d.h)) {
                d.h = bVar.a();
            }
        } catch (Throwable th) {
            ak.e(th);
        }
        g.a().a(1, jSONObject.toString());
        s.a().a(n.e.a(bVar.b(), bVar.c()), n.e.a(bVar.f(), bVar.j()), n.e.a(bVar.d(), bVar.e()));
    }

    public static int b() {
        return f2002a.get();
    }

    public static com.g.gysdk.b b(boolean z) {
        z.b bVarA = z.a(z);
        int iG = bVarA != null ? bVarA.g() : -1;
        if (iG != 1000) {
            if (iG == 40004) {
                return new com.g.gysdk.b(GyCode.UNKNOWN_ERROR, GyErrorCode.APPID_INVALID, bVarA.i());
            }
            if (iG != 29001 && iG != 29002) {
                return new com.g.gysdk.b(GyCode.UNKNOWN_ERROR, GyErrorCode.SERVER_RETURN_ERROR, bVarA != null ? bVarA.i() : "注册网络请求错误");
            }
        }
        a(bVarA, z);
        return new com.g.gysdk.b(GyCode.SUCCESS, GyErrorCode.SUCCESS, "注册成功");
    }

    public static void b(final an anVar, final int i, final boolean z, final long j) {
        al.a(al.b.Work, new Runnable() { // from class: com.g.gysdk.a.e.2
            @Override // java.lang.Runnable
            public void run() {
                GyErrorCode gyErrorCodeG;
                try {
                    ak.a("checkRegister retryCount=" + i);
                    com.g.gysdk.b bVarB = e.b(z);
                    if (bVarB.g() == GyErrorCode.SERVER_RETURN_ERROR && i > 0) {
                        e.b(anVar, i - 1, z, j);
                        return;
                    }
                    boolean zA = bVarB.a();
                    String strB = com.g.gysdk.b.b(System.currentTimeMillis() - j);
                    if (zA) {
                        anVar.a((Object) true);
                        ak.a("checkRegister http success");
                        gyErrorCodeG = GyErrorCode.SUCCESS;
                    } else {
                        anVar.a(bVarB);
                        ak.c("checkRegister http failed, response: " + bVarB);
                        gyErrorCodeG = bVarB.g();
                    }
                    b.a("gyuid_register", gyErrorCodeG, "", strB);
                } catch (Throwable th) {
                    anVar.a(com.g.gysdk.b.a(GyCode.UNKNOWN_ERROR, th));
                    ak.e("checkRegister http error :" + th);
                }
            }
        }, ((long) (2 - i)) * 100);
    }

    public static void c() {
        if (!f2002a.compareAndSet(0, 1)) {
            ak.c("checkRegister already done");
        } else {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            an.b(MiPushClient.COMMAND_REGISTER).a(new an.b() { // from class: com.g.gysdk.a.e.1
                @Override // com.g.gysdk.a.an.b
                public void a(final an anVar) {
                    ak.a("checkRegister start");
                    int iH = e.h();
                    if (iH == 1) {
                        ak.a("checkRegister success and not expired");
                        anVar.a((Object) true);
                        if (e.b) {
                            return;
                        }
                    } else if (iH == 2) {
                        ak.a("checkRegister success but expired");
                        anVar.a((Object) true);
                    }
                    Context contextC = d.c();
                    final boolean z = iH == 0;
                    if (ar.b(contextC)) {
                        ak.a("checkRegister http start because has network");
                        e.b(anVar, 2, z, jCurrentTimeMillis);
                    } else {
                        ak.c("checkRegister http failed because no network");
                        b.a("gyuid_register", GyErrorCode.NO_NET, "", com.g.gysdk.b.b(System.currentTimeMillis() - jCurrentTimeMillis));
                        new a(contextC) { // from class: com.g.gysdk.a.e.1.1
                            @Override // com.g.gysdk.a.e.a
                            public void a(Context context) {
                                ak.a("checkRegister http restart because has network");
                                e.b(anVar, 2, z, jCurrentTimeMillis);
                            }
                        }.a();
                    }
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    boolean z = (obj instanceof Boolean) && ((Boolean) obj).booleanValue();
                    e.f2002a.set(z ? 2 : 3);
                    if (z) {
                        aj.a("checkRegister success");
                        s.a().c();
                        return;
                    }
                    if (obj instanceof com.g.gysdk.b) {
                        com.g.gysdk.b unused = e.c = (com.g.gysdk.b) obj;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("checkRegister failed, response:");
                    sb.append(e.c != null ? e.c.toString() : "");
                    aj.c(sb.toString());
                }
            });
        }
    }

    public static int h() {
        try {
            String strA = g.a().a(1);
            if (TextUtils.isEmpty(strA)) {
                return 0;
            }
            JSONObject jSONObject = new JSONObject(strA);
            String string = jSONObject.getString("a");
            String string2 = jSONObject.getString(cn.com.chinatelecom.account.api.a.d.f1473a);
            b = jSONObject.optBoolean("e", false);
            if (!TextUtils.equals(string, d.f2000e) || TextUtils.isEmpty(string2)) {
                return 0;
            }
            if (TextUtils.isEmpty(d.h)) {
                d.h = string2;
            }
            if (TextUtils.equals(d.g, jSONObject.getString("c")) && TextUtils.equals(d.h, string2)) {
                return System.currentTimeMillis() - jSONObject.getLong("b") > j.c() * 1000 ? 2 : 1;
            }
            return 0;
        } catch (Throwable th) {
            ak.c(th);
            return 0;
        }
    }
}
