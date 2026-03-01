package com.vivo.push;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.igexin.sdk.PushConsts;
import com.vivo.push.c.y;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.VivoPushException;
import com.vivo.push.util.s;
import com.vivo.push.util.w;
import com.vivo.push.util.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile e f5606a;
    public Context h;
    public com.vivo.push.util.b j;
    public String k;
    public String l;
    public Boolean o;
    public Long p;
    public boolean q;
    public int s;
    public long b = -1;
    public long c = -1;
    public long d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f5607e = -1;
    public long f = -1;
    public long g = -1;
    public boolean i = true;
    public SparseArray<a> m = new SparseArray<>();
    public int n = 0;
    public IPushClientFactory r = new d();

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.l = null;
        this.j.b("APP_ALIAS");
    }

    private boolean n() {
        if (this.o == null) {
            this.o = Boolean.valueOf(l() >= 1230 && z.e(this.h));
        }
        return this.o.booleanValue();
    }

    public final boolean d() {
        if (this.h == null) {
            com.vivo.push.util.o.d("PushClientManager", "support:context is null");
            return false;
        }
        Boolean boolValueOf = Boolean.valueOf(n());
        this.o = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public final boolean e() {
        return this.q;
    }

    public final String f() {
        if (!TextUtils.isEmpty(this.k)) {
            return this.k;
        }
        com.vivo.push.util.b bVar = this.j;
        String strB = bVar != null ? bVar.b("APP_TOKEN", (String) null) : "";
        c(strB);
        return strB;
    }

    public final boolean g() {
        return this.i;
    }

    public final Context h() {
        return this.h;
    }

    public final void i() {
        this.j.a();
    }

    public final String j() {
        return this.l;
    }

    public final int k() {
        return this.s;
    }

    public final long l() {
        Context context = this.h;
        if (context == null) {
            return -1L;
        }
        if (this.p == null) {
            this.p = Long.valueOf(z.b(context));
        }
        return this.p.longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        m.a(new k(this, str));
    }

    public static synchronized e a() {
        if (f5606a == null) {
            f5606a = new e();
        }
        return f5606a;
    }

    public final void b() throws VivoPushException {
        Context context = this.h;
        if (context != null) {
            z.c(context);
        }
    }

    public final List<String> c() {
        String strB = this.j.b("APP_TAGS", (String) null);
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(strB)) {
                return arrayList;
            }
            Iterator<String> itKeys = new JSONObject(strB).keys();
            while (itKeys.hasNext()) {
                arrayList.add(itKeys.next());
            }
        } catch (JSONException unused) {
            this.j.b("APP_TAGS");
            arrayList.clear();
            com.vivo.push.util.o.d("PushClientManager", "getTags error");
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: PushClientManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IPushActionListener f5608a;
        public com.vivo.push.b.c b;
        public IPushActionListener c;
        public Runnable d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object[] f5609e;

        public a(com.vivo.push.b.c cVar, IPushActionListener iPushActionListener) {
            this.b = cVar;
            this.f5608a = iPushActionListener;
        }

        public final void a(int i, Object... objArr) {
            this.f5609e = objArr;
            IPushActionListener iPushActionListener = this.c;
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(i);
            }
            IPushActionListener iPushActionListener2 = this.f5608a;
            if (iPushActionListener2 != null) {
                iPushActionListener2.onStateChanged(i);
            }
        }

        public final Object[] b() {
            return this.f5609e;
        }

        public final void a(Runnable runnable) {
            this.d = runnable;
        }

        public final void a() {
            Runnable runnable = this.d;
            if (runnable == null) {
                com.vivo.push.util.o.a("PushClientManager", "task is null");
            } else {
                runnable.run();
            }
        }

        public final void a(IPushActionListener iPushActionListener) {
            this.c = iPushActionListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized a d(String str) {
        if (str != null) {
            try {
                int i = Integer.parseInt(str);
                a aVar = this.m.get(i);
                this.m.delete(i);
                return aVar;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final void b(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String strB = this.j.b("APP_TAGS", (String) null);
            if (TextUtils.isEmpty(strB)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(strB);
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONObject.remove(it.next());
            }
            String string = jSONObject.toString();
            if (TextUtils.isEmpty(string)) {
                this.j.b("APP_TAGS");
            } else {
                this.j.a("APP_TAGS", string);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.j.b("APP_TAGS");
        }
    }

    public final synchronized void a(Context context) {
        if (this.h == null) {
            this.h = ContextDelegate.getContext(context);
            this.q = s.c(context, context.getPackageName());
            w.b().a(this.h);
            a(new com.vivo.push.b.g());
            com.vivo.push.util.b bVar = new com.vivo.push.util.b();
            this.j = bVar;
            bVar.a(this.h, "com.vivo.push_preferences.appconfig_v1");
            this.k = f();
            this.l = this.j.b("APP_ALIAS", (String) null);
        }
    }

    public final void c(List<String> list) {
        if (list.contains(this.l)) {
            m();
        }
    }

    private void c(String str) {
        m.c(new f(this, str));
    }

    public final void a(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String strB = this.j.b("APP_TAGS", (String) null);
            if (TextUtils.isEmpty(strB)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(strB);
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONObject.put(it.next(), System.currentTimeMillis());
            }
            String string = jSONObject.toString();
            if (TextUtils.isEmpty(string)) {
                this.j.b("APP_TAGS");
            } else {
                this.j.a("APP_TAGS", string);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.j.b("APP_TAGS");
        }
    }

    public final void b(IPushActionListener iPushActionListener) {
        if (this.h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        if ("".equals(this.k)) {
            iPushActionListener.onStateChanged(0);
            return;
        }
        if (!a(this.c)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
                return;
            }
            return;
        }
        this.c = SystemClock.elapsedRealtime();
        String packageName = this.h.getPackageName();
        a aVarA = null;
        if (this.h != null) {
            com.vivo.push.b.b bVar = new com.vivo.push.b.b(false, packageName);
            bVar.d();
            bVar.e();
            bVar.g();
            bVar.a(100);
            if (this.q) {
                if (n()) {
                    aVarA = new a(bVar, iPushActionListener);
                    String strA = a(aVarA);
                    bVar.b(strA);
                    aVarA.a(new j(this, bVar, strA));
                } else if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
            } else if (bVar.a(this.h) == 2) {
                aVarA = a(bVar, iPushActionListener);
            } else {
                a(bVar);
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(0);
                }
            }
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(102);
        }
        if (aVarA == null) {
            return;
        }
        aVarA.a(new i(this));
        aVarA.a();
    }

    public final void a(String str) {
        this.k = str;
        this.j.a("APP_TOKEN", str);
    }

    public final void a(boolean z) {
        this.i = z;
    }

    public final void a(IPushActionListener iPushActionListener) {
        if (this.h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        String strF = f();
        this.k = strF;
        if (!TextUtils.isEmpty(strF)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
                return;
            }
            return;
        }
        if (!a(this.b)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
                return;
            }
            return;
        }
        this.b = SystemClock.elapsedRealtime();
        String packageName = this.h.getPackageName();
        a aVarA = null;
        if (this.h != null) {
            com.vivo.push.b.b bVar = new com.vivo.push.b.b(true, packageName);
            bVar.g();
            bVar.d();
            bVar.e();
            bVar.a(100);
            if (this.q) {
                if (n()) {
                    aVarA = a(bVar, iPushActionListener);
                } else if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
            } else if (bVar.a(this.h) == 2) {
                aVarA = a(bVar, iPushActionListener);
            } else {
                a(bVar);
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(0);
                }
            }
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(102);
        }
        if (aVarA == null) {
            return;
        }
        aVarA.a(new g(this, aVarA));
        aVarA.a();
    }

    public final void b(String str, IPushActionListener iPushActionListener) {
        if (this.h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(this.l)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        com.vivo.push.b.a aVar = new com.vivo.push.b.a(false, this.h.getPackageName(), arrayList);
        aVar.a(100);
        if (this.q) {
            if (!n()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!a(this.f5607e)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.f5607e = SystemClock.elapsedRealtime();
            String strA = a(new a(aVar, iPushActionListener));
            aVar.b(strA);
            if (TextUtils.isEmpty(this.k)) {
                a(strA, PushConsts.ALIAS_ERROR_FREQUENCY);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                a(strA, PushConsts.ALIAS_OPERATE_PARAM_ERROR);
                return;
            } else if (str.length() > 70) {
                a(strA, PushConsts.ALIAS_REQUEST_FILTER);
                return;
            } else {
                a(aVar);
                e(strA);
                return;
            }
        }
        a(aVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    private a a(com.vivo.push.b.b bVar, IPushActionListener iPushActionListener) {
        a aVar = new a(bVar, iPushActionListener);
        String strA = a(aVar);
        bVar.b(strA);
        aVar.a(new h(this, bVar, strA));
        return aVar;
    }

    public final void a(String str, int i, Object... objArr) {
        a aVarD = d(str);
        if (aVarD != null) {
            aVarD.a(i, objArr);
        } else {
            com.vivo.push.util.o.d("PushClientManager", "notifyApp token is null");
        }
    }

    public final void a(String str, IPushActionListener iPushActionListener) {
        if (this.h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(this.l) && this.l.equals(str)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        com.vivo.push.b.a aVar = new com.vivo.push.b.a(true, this.h.getPackageName(), arrayList);
        aVar.a(100);
        if (this.q) {
            if (!n()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!a(this.d)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.d = SystemClock.elapsedRealtime();
            String strA = a(new a(aVar, iPushActionListener));
            aVar.b(strA);
            if (TextUtils.isEmpty(this.k)) {
                a(strA, PushConsts.ALIAS_ERROR_FREQUENCY);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                a(strA, PushConsts.ALIAS_OPERATE_PARAM_ERROR);
                return;
            } else if (str.length() > 70) {
                a(strA, PushConsts.ALIAS_REQUEST_FILTER);
                return;
            } else {
                a(aVar);
                e(strA);
                return;
            }
        }
        a(aVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    public final void b(String str) {
        this.l = str;
        this.j.a("APP_ALIAS", str);
    }

    public final void b(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        Context context = this.h;
        if (context == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        com.vivo.push.b.z zVar = new com.vivo.push.b.z(false, context.getPackageName(), arrayList);
        zVar.a(500);
        if (this.q) {
            if (!n()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!a(this.g)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.g = SystemClock.elapsedRealtime();
            String strA = a(new a(zVar, iPushActionListener));
            zVar.b(strA);
            if (TextUtils.isEmpty(this.k)) {
                a(strA, 20001);
                return;
            }
            if (arrayList.size() < 0) {
                a(strA, 20002);
                return;
            }
            if (arrayList.size() > 500) {
                a(strA, 20004);
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().length() > 70) {
                    a(strA, 20003);
                    return;
                }
            }
            a(zVar);
            e(strA);
            return;
        }
        a(zVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    public static boolean a(long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        return j == -1 || jElapsedRealtime <= j || jElapsedRealtime >= j + 2000;
    }

    public final void a(String str, int i) {
        a aVarD = d(str);
        if (aVarD != null) {
            aVarD.a(i, new Object[0]);
        } else {
            com.vivo.push.util.o.d("PushClientManager", "notifyStatusChanged token is null");
        }
    }

    private synchronized String a(a aVar) {
        int i;
        this.m.put(this.n, aVar);
        i = this.n;
        this.n = i + 1;
        return Integer.toString(i);
    }

    public final void a(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        Context context = this.h;
        if (context == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        com.vivo.push.b.z zVar = new com.vivo.push.b.z(true, context.getPackageName(), arrayList);
        zVar.a(500);
        if (this.q) {
            if (!n()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!a(this.f)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.f = SystemClock.elapsedRealtime();
            String strA = a(new a(zVar, iPushActionListener));
            zVar.b(strA);
            if (TextUtils.isEmpty(this.k)) {
                a(strA, 20001);
                return;
            }
            if (arrayList.size() < 0) {
                a(strA, 20002);
                return;
            }
            if (arrayList.size() + c().size() > 500) {
                a(strA, 20004);
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().length() > 70) {
                    a(strA, 20003);
                    return;
                }
            }
            a(zVar);
            e(strA);
            return;
        }
        a(zVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    public final void a(Intent intent, PushMessageCallback pushMessageCallback) {
        o oVarCreateReceiverCommand = this.r.createReceiverCommand(intent);
        Context context = a().h;
        if (oVarCreateReceiverCommand == null) {
            com.vivo.push.util.o.a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                com.vivo.push.util.o.c(context, "[执行指令失败]指令空！");
                return;
            }
            return;
        }
        y yVarCreateReceiveTask = this.r.createReceiveTask(oVarCreateReceiverCommand);
        if (yVarCreateReceiveTask == null) {
            com.vivo.push.util.o.a("PushClientManager", "sendCommand, null command task! pushCommand = ".concat(String.valueOf(oVarCreateReceiverCommand)));
            if (context != null) {
                com.vivo.push.util.o.c(context, "[执行指令失败]指令" + oVarCreateReceiverCommand + "任务空！");
                return;
            }
            return;
        }
        if (context != null && !(oVarCreateReceiverCommand instanceof com.vivo.push.b.n)) {
            com.vivo.push.util.o.a(context, "[接收指令]".concat(String.valueOf(oVarCreateReceiverCommand)));
        }
        yVarCreateReceiveTask.a(pushMessageCallback);
        m.a((l) yVarCreateReceiveTask);
    }

    public final void a(o oVar) {
        Context context = a().h;
        if (oVar == null) {
            com.vivo.push.util.o.a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                com.vivo.push.util.o.c(context, "[执行指令失败]指令空！");
                return;
            }
            return;
        }
        l lVarCreateTask = this.r.createTask(oVar);
        if (lVarCreateTask == null) {
            com.vivo.push.util.o.a("PushClientManager", "sendCommand, null command task! pushCommand = ".concat(String.valueOf(oVar)));
            if (context != null) {
                com.vivo.push.util.o.c(context, "[执行指令失败]指令" + oVar + "任务空！");
                return;
            }
            return;
        }
        com.vivo.push.util.o.d("PushClientManager", "client--sendCommand, command = ".concat(String.valueOf(oVar)));
        m.a(lVarCreateTask);
    }
}
