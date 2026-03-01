package com.heytap.mcssdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import com.heytap.mcssdk.f.e;
import com.heytap.mcssdk.f.g;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.mode.MessageStat;
import com.mcs.aidl.IMcsSdkService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2570a = "push_register";
    public static final String b = "push_transmit";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f2571e = "com.mcs.action.RECEIVE_SDK_MESSAGE";
    public static final String f = "type";
    public static final int g = 1019;
    public static final String h = "eventID";
    public static final String i = "taskID";
    public static final String j = "appPackage";
    public static final String k = "extra";
    public static final String l = "messageType";
    public static final String m = "messageID";
    public static final String n = "globalID";
    public static final String o = "supportOpenPush";
    public static final String p = "versionName";
    public static final String q = "versionCode";
    public static final String r = "pushSdkVersion";
    public static final int s = 23;
    public static final int t = 59;
    public static final int u = 24;
    public static final int v = 1000;
    public static String x;
    public static boolean y;
    public Context A;
    public List<com.heytap.mcssdk.e.c> B;
    public List<com.heytap.mcssdk.d.d> C;
    public String D;
    public String E;
    public String F;
    public ICallBackResultService G;
    public final Object z;
    public static final int[] c = {99, 111, 109, 46, 99, 111, 108, 111, 114, 111, 115, 46, 109, 99, 115};
    public static final int[] d = {99, 111, 109, 46, 99, 111, 108, 111, 114, 111, 115, 46, 109, 99, 115, 115, 100, 107, 46, 97, 99, 116, 105, 111, 110, 46, 82, 69, 67, 69, 73, 86, 69, 95, 83, 68, 75, 95, 77, 69, 83, 83, 65, 71, 69};
    public static int w = 0;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f2573a = new d();
    }

    public d() {
        this.z = new Object();
        this.B = new ArrayList();
        this.C = new ArrayList();
        this.F = null;
        synchronized (d.class) {
            if (w > 0) {
                throw new RuntimeException("PushService can't create again!");
            }
            w++;
        }
        a(new com.heytap.mcssdk.d.b());
        a(new com.heytap.mcssdk.d.a());
        a(new com.heytap.mcssdk.e.b());
        a(new com.heytap.mcssdk.e.a());
    }

    private String a(Context context) {
        boolean z;
        boolean z2;
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent(f2571e), 8192);
        if (Build.VERSION.SDK_INT >= 24) {
            Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
            while (it.hasNext()) {
                String str = it.next().serviceInfo.packageName;
                try {
                    z = (context.getPackageManager().getApplicationInfo(str, 0).flags & 1) == 1;
                    z2 = context.getPackageManager().getPackageUid(str, 0) == context.getPackageManager().getPackageUid("android", 0);
                } catch (PackageManager.NameNotFoundException unused) {
                }
                if (z || z2) {
                    return str;
                }
            }
        }
        return null;
    }

    private void a(int i2, String str, JSONObject jSONObject) {
        synchronized (this.z) {
            this.A.startService(b(i2, str, jSONObject));
        }
    }

    public static void a(Context context, MessageStat messageStat) {
        e.a(context, messageStat);
    }

    public static void a(Context context, List<MessageStat> list) {
        e.a(context, list);
    }

    private void a(com.heytap.mcssdk.d.d dVar) {
        synchronized (this) {
            if (dVar != null) {
                this.C.add(dVar);
            }
        }
    }

    private void a(com.heytap.mcssdk.e.c cVar) {
        synchronized (this) {
            if (cVar != null) {
                this.B.add(cVar);
            }
        }
    }

    private Intent b(int i2, String str, JSONObject jSONObject) {
        Intent intent = new Intent();
        intent.setAction(m());
        intent.setPackage(l());
        intent.putExtra("type", i2);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt(p, g.c(this.A, this.A.getPackageName()));
            jSONObject2.putOpt(q, Integer.valueOf(g.b(this.A, this.A.getPackageName())));
            if (jSONObject != null) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject2.putOpt(next, jSONObject.get(next));
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            intent.putExtra("extra", jSONObject2.toString());
            throw th;
        }
        intent.putExtra("extra", jSONObject2.toString());
        intent.putExtra("params", str);
        intent.putExtra("appPackage", this.A.getPackageName());
        intent.putExtra(com.heytap.mcssdk.a.a.l, this.D);
        intent.putExtra(com.heytap.mcssdk.a.a.m, this.E);
        intent.putExtra(com.heytap.mcssdk.a.a.n, this.F);
        intent.putExtra(com.heytap.mcssdk.a.a.o, s());
        return intent;
    }

    private void b(int i2, JSONObject jSONObject) {
        a(i2, "", jSONObject);
    }

    @Deprecated
    public static void b(Context context) {
        a(context, new MessageStat(context.getPackageName(), "app_start", null));
    }

    public static d k() {
        return a.f2573a;
    }

    public static String s() {
        return "2.1.0";
    }

    private boolean v() {
        return this.A != null;
    }

    private boolean w() {
        return this.F != null;
    }

    private boolean x() {
        return v() && w();
    }

    public d a(Context context, boolean z) {
        this.A = context.getApplicationContext();
        new com.heytap.mcssdk.b.a().a(this.A);
        com.heytap.mcssdk.f.c.f(z);
        return this;
    }

    @Override // com.heytap.mcssdk.b
    public String a() {
        return this.F;
    }

    @Override // com.heytap.mcssdk.b
    public void a(int i2) {
        a(i2, (JSONObject) null);
    }

    @Override // com.heytap.mcssdk.b
    public void a(int i2, JSONObject jSONObject) {
        if (x()) {
            a(com.heytap.mcssdk.a.b.w, String.valueOf(i2), jSONObject);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.b
    public void a(Context context, String str, String str2, ICallBackResultService iCallBackResultService) {
        a(context, str, str2, (JSONObject) null, iCallBackResultService);
    }

    @Override // com.heytap.mcssdk.b
    public void a(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        if (context == null) {
            if (iCallBackResultService != null) {
                iCallBackResultService.onRegister(-2, null);
                return;
            }
            return;
        }
        a(context, new MessageStat(context.getPackageName(), f2570a, null));
        if (!n()) {
            if (iCallBackResultService != null) {
                iCallBackResultService.onRegister(-2, null);
            }
        } else {
            this.D = str;
            this.E = str2;
            this.A = context.getApplicationContext();
            this.G = iCallBackResultService;
            b(12289, jSONObject);
        }
    }

    public void a(ICallBackResultService iCallBackResultService) {
        this.G = iCallBackResultService;
    }

    public void a(DataMessage dataMessage, String str, int i2) {
        try {
            Intent intent = new Intent();
            intent.setAction(m());
            intent.setPackage(l());
            intent.putExtra("type", com.heytap.mcssdk.a.b.o);
            intent.putExtra("taskID", dataMessage.getTaskID());
            intent.putExtra("appPackage", dataMessage.getAppPackage());
            intent.putExtra("messageID", dataMessage.getMessageID());
            intent.putExtra("messageType", i2);
            intent.putExtra("eventID", str);
            this.A.startService(intent);
        } catch (Exception e2) {
            com.heytap.mcssdk.f.c.e("statisticMessage--Exception" + e2.getMessage());
        }
    }

    @Override // com.heytap.mcssdk.b
    public void a(String str) {
        this.F = str;
    }

    public void a(String str, String str2) {
        this.D = str;
        this.E = str2;
    }

    @Override // com.heytap.mcssdk.b
    public void a(List<Integer> list, int i2, int i3, int i4, int i5) {
        a(list, i2, i3, i4, i5, null);
    }

    @Override // com.heytap.mcssdk.b
    public void a(List<Integer> list, int i2, int i3, int i4, int i5, JSONObject jSONObject) {
        if (!x()) {
            if (q() != null) {
                q().onSetPushTime(-2, "please call the register first!");
                return;
            }
            return;
        }
        if (list == null || list.size() <= 0 || i2 < 0 || i3 < 0 || i4 < i2 || i4 > 23 || i5 < i3 || i5 > 59) {
            throw new IllegalArgumentException("params are not all right,please check params");
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("weekDays", com.heytap.mcssdk.c.a.a(list));
            jSONObject2.put("startHour", i2);
            jSONObject2.put("startMin", i3);
            jSONObject2.put("endHour", i4);
            jSONObject2.put("endMin", i5);
            a(com.heytap.mcssdk.a.b.q, jSONObject2.toString(), jSONObject);
        } catch (JSONException e2) {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, e2.getLocalizedMessage());
        }
    }

    @Override // com.heytap.mcssdk.b
    public void a(JSONObject jSONObject) {
        if (v()) {
            b(12289, jSONObject);
        } else if (q() != null) {
            q().onRegister(-2, null);
        }
    }

    @Override // com.heytap.mcssdk.b
    public void b() {
        a((JSONObject) null);
    }

    public void b(int i2) {
        final Intent intentB = b(i2, "", null);
        this.A.bindService(intentB, new ServiceConnection() { // from class: com.heytap.mcssdk.d.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Bundle bundle = new Bundle();
                bundle.putAll(intentB.getExtras());
                try {
                    IMcsSdkService.Stub.asInterface(iBinder).process(bundle);
                } catch (Exception e2) {
                    com.heytap.mcssdk.f.c.b("bindMcsService exception:".concat(String.valueOf(e2)));
                }
                d.this.A.unbindService(this);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
            }
        }, 1);
    }

    public void b(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        this.D = str;
        this.E = str2;
        this.A = context.getApplicationContext();
        this.G = iCallBackResultService;
        b(jSONObject);
    }

    @Override // com.heytap.mcssdk.b
    public void b(JSONObject jSONObject) {
        if (v()) {
            b(com.heytap.mcssdk.a.b.n, jSONObject);
        } else if (q() != null) {
            q().onUnRegister(-2);
        }
    }

    @Override // com.heytap.mcssdk.b
    public void c() {
        b((JSONObject) null);
    }

    @Override // com.heytap.mcssdk.b
    public void c(JSONObject jSONObject) {
        if (x()) {
            b(com.heytap.mcssdk.a.b.y, jSONObject);
        } else if (q() != null) {
            q().onGetNotificationStatus(-2, 0);
        }
    }

    @Override // com.heytap.mcssdk.b
    public void d() {
        c(null);
    }

    @Override // com.heytap.mcssdk.b
    public void d(JSONObject jSONObject) {
        if (x()) {
            b(com.heytap.mcssdk.a.b.x, jSONObject);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.b
    public void e() {
        d(null);
    }

    @Override // com.heytap.mcssdk.b
    public void e(JSONObject jSONObject) {
        if (v()) {
            b(com.heytap.mcssdk.a.b.A, jSONObject);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.b
    public void f() {
        e(null);
    }

    @Override // com.heytap.mcssdk.b
    public void f(JSONObject jSONObject) {
        if (x()) {
            b(com.heytap.mcssdk.a.b.z, jSONObject);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.b
    public void g() {
        f(null);
    }

    @Override // com.heytap.mcssdk.b
    public void g(JSONObject jSONObject) {
        if (x()) {
            b(com.heytap.mcssdk.a.b.r, jSONObject);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.b
    public void h() {
        g(null);
    }

    @Override // com.heytap.mcssdk.b
    public void h(JSONObject jSONObject) {
        if (x()) {
            b(com.heytap.mcssdk.a.b.s, jSONObject);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.b
    public void i() {
        h(null);
    }

    @Override // com.heytap.mcssdk.b
    public void j() {
        if (v()) {
            b(com.heytap.mcssdk.a.b.C);
        } else {
            com.heytap.mcssdk.f.c.e(com.heytap.mcssdk.f.c.f2577a, "please call the register first!");
        }
    }

    public String l() {
        boolean z;
        if (x == null) {
            String strA = a(this.A);
            if (strA == null) {
                x = g.a(c);
                z = false;
            } else {
                x = strA;
                z = true;
            }
            y = z;
        }
        return x;
    }

    public String m() {
        if (x == null) {
            a(this.A);
        }
        return y ? f2571e : g.a(d);
    }

    public boolean n() {
        String strL = l();
        return g.a(this.A, strL) && g.b(this.A, strL) >= 1019 && g.a(this.A, strL, o);
    }

    public List<com.heytap.mcssdk.d.d> o() {
        return this.C;
    }

    public List<com.heytap.mcssdk.e.c> p() {
        return this.B;
    }

    public ICallBackResultService q() {
        return this.G;
    }

    public void r() {
        if (x()) {
            b(com.heytap.mcssdk.a.b.v, null);
        } else if (q() != null) {
            q().onGetPushStatus(-2, 0);
        }
    }

    public String t() {
        return v() ? g.c(this.A, l()) : "";
    }

    public int u() {
        if (v()) {
            return g.b(this.A, l());
        }
        return 0;
    }
}
