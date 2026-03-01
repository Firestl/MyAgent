package com.igexin.push.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.igexin.push.c.a;
import com.igexin.push.c.b;
import com.igexin.push.c.e.AnonymousClass1;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import com.lzy.okgo.cookie.SerializableCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3267e = b.f3254a + h.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3268a;
    public Handler i;
    public final Map<String, e> b = new LinkedHashMap();
    public final Map<String, d> c = new HashMap();
    public final Object f = new Object();
    public final Object g = new Object();
    public a d = new a();
    public final Comparator<Map.Entry<String, d>> h = new Comparator<Map.Entry<String, d>>() { // from class: com.igexin.push.c.h.1
        public static int a(Map.Entry<String, d> entry, Map.Entry<String, d> entry2) {
            return (int) (entry.getValue().c() - entry2.getValue().c());
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(Map.Entry<String, d> entry, Map.Entry<String, d> entry2) {
            return (int) (entry.getValue().c() - entry2.getValue().c());
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:6:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public h(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.c.h.<init>(java.lang.String, java.lang.String):void");
    }

    public static d a(JSONObject jSONObject) throws Exception {
        if (!jSONObject.has(SerializableCookie.DOMAIN)) {
            return null;
        }
        d dVar = new d();
        dVar.a(jSONObject.getString(SerializableCookie.DOMAIN));
        if (jSONObject.has("port")) {
            dVar.b = jSONObject.getInt("port");
        }
        if (jSONObject.has("ip")) {
            dVar.f3259a = jSONObject.getString("ip");
        }
        if (jSONObject.has("consumeTime")) {
            dVar.c = jSONObject.getLong("consumeTime");
        }
        if (jSONObject.has("detectSuccessTime")) {
            dVar.d = jSONObject.getLong("detectSuccessTime");
        }
        if (jSONObject.has("isDomain")) {
            dVar.f3260e = jSONObject.getBoolean("isDomain");
        }
        return dVar;
    }

    public static List<String> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getJSONObject(i).getString(SerializableCookie.DOMAIN));
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
        return arrayList;
    }

    private void a() {
        this.f3268a = 0L;
        if (r()) {
            if (com.igexin.push.core.e.ap != null) {
                com.igexin.push.core.e.f.a().b(com.igexin.push.core.b.m, true);
            }
        } else if (com.igexin.push.core.e.aq != null) {
            com.igexin.push.core.e.f.a().b(com.igexin.push.core.b.m, false);
        }
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        ArrayList arrayList = new ArrayList();
        for (String str : defaultXfrList) {
            d dVar = new d(str, Integer.parseInt(com.igexin.c.a.b.g.a(str)[2]));
            if (defaultXfrList.size() > 1) {
                b(dVar);
            }
            arrayList.add(dVar);
        }
        this.d.b(arrayList);
        defaultXfrList.clear();
    }

    public static List<String> b() {
        return SDKUrlConfig.getDefaultXfrList();
    }

    private void b(d dVar) {
        e eVar = new e();
        eVar.d = c() == b.EnumC0076b.f3257a;
        eVar.a(d());
        eVar.b = dVar;
        synchronized (this.g) {
            this.b.put(dVar.a(), eVar);
        }
    }

    private void b(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str)) {
            a();
            return;
        }
        JSONArray jSONArray = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            com.igexin.c.a.c.a.a(e2);
            jSONObject = null;
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            a();
            return;
        }
        if (jSONObject.has("lastDetectTime")) {
            try {
                this.f3268a = jSONObject.getLong("lastDetectTime");
            } catch (JSONException e3) {
                com.igexin.c.a.c.a.a(e3);
            }
        }
        if (Math.abs(System.currentTimeMillis() - this.f3268a) >= b.c) {
            a();
            return;
        }
        if (jSONObject.has("list")) {
            try {
                jSONArray = jSONObject.getJSONArray("list");
            } catch (JSONException e4) {
                com.igexin.c.a.c.a.a(e4);
            }
        }
        if (jSONArray == null || jSONArray.length() == 0) {
            a();
            return;
        }
        List<String> listA = a(jSONArray);
        if (listA.isEmpty()) {
            a();
            return;
        }
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        ArrayList arrayList = new ArrayList(defaultXfrList);
        arrayList.retainAll(listA);
        if (arrayList.size() == listA.size()) {
            com.igexin.c.a.c.a.a(f3267e, "db cache xfr == default, use cache");
            com.igexin.c.a.c.a.a(f3267e + " | db cache xfr == default, use cache", new Object[0]);
            b(jSONArray);
            return;
        }
        com.igexin.c.a.c.a.a(f3267e, "db cache xfr != default, use default");
        com.igexin.c.a.c.a.a(f3267e + " | db cache xfr != default, use default", new Object[0]);
        arrayList.clear();
        defaultXfrList.clear();
        listA.clear();
        a();
    }

    private void b(JSONArray jSONArray) {
        d dVar;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has(SerializableCookie.DOMAIN)) {
                    dVar = new d();
                    dVar.a(jSONObject.getString(SerializableCookie.DOMAIN));
                    if (jSONObject.has("port")) {
                        dVar.b = jSONObject.getInt("port");
                    }
                    if (jSONObject.has("ip")) {
                        dVar.f3259a = jSONObject.getString("ip");
                    }
                    if (jSONObject.has("consumeTime")) {
                        dVar.c = jSONObject.getLong("consumeTime");
                    }
                    if (jSONObject.has("detectSuccessTime")) {
                        dVar.d = jSONObject.getLong("detectSuccessTime");
                    }
                    if (jSONObject.has("isDomain")) {
                        dVar.f3260e = jSONObject.getBoolean("isDomain");
                    }
                } else {
                    dVar = null;
                }
                if (dVar != null) {
                    this.c.put(dVar.a(), dVar);
                } else {
                    try {
                        dVar = d(jSONObject.getString(SerializableCookie.DOMAIN));
                    } catch (Exception e2) {
                        com.igexin.c.a.c.a.a(e2);
                        com.igexin.c.a.c.a.a(f3267e + "|initWithCacheData exception " + e2.toString(), new Object[0]);
                        this.c.clear();
                        a();
                        return;
                    }
                }
                if (dVar != null) {
                    b(dVar);
                    arrayList.add(dVar);
                }
            } catch (Exception e3) {
                com.igexin.c.a.c.a.a(e3);
                com.igexin.c.a.c.a.a(f3267e + "|initWithCacheData exception " + e3.toString(), new Object[0]);
                return;
            }
        }
        this.d.b(arrayList);
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        if (jSONObject.has("loginFailedlCnt")) {
            try {
                this.d.g = jSONObject.getInt("loginFailedlCnt");
            } catch (JSONException e3) {
                com.igexin.c.a.c.a.a(e3);
            }
        }
        if (jSONObject.has("lastChange2BackupTime")) {
            try {
                this.d.h = jSONObject.getLong("lastChange2BackupTime");
            } catch (JSONException e4) {
                com.igexin.c.a.c.a.a(e4);
            }
        }
        if (jSONObject.has("lastOfflineTime")) {
            try {
                this.d.i = jSONObject.getLong("lastOfflineTime");
            } catch (JSONException e5) {
                com.igexin.c.a.c.a.a(e5);
            }
        }
        if (jSONObject.has("domainType")) {
            try {
                this.d.f3248e = a.EnumC0075a.a(jSONObject.getInt("domainType"));
                if (this.d.f3248e == a.EnumC0075a.BACKUP) {
                    this.d.f.set(true);
                }
            } catch (JSONException e6) {
                com.igexin.c.a.c.a.a(e6);
            }
        }
    }

    public static d d(String str) {
        d dVar = new d();
        String[] strArrA = com.igexin.c.a.b.g.a(str);
        dVar.a(str);
        dVar.b = Integer.parseInt(strArrA[2]);
        return dVar;
    }

    public static void k() {
        com.igexin.push.core.e.f.a().b(com.igexin.push.core.b.m, true);
        com.igexin.push.core.e.f.a().b(com.igexin.push.core.b.m, false);
    }

    private void p() {
        synchronized (this.f) {
            this.c.clear();
        }
    }

    private boolean q() {
        long jAbs = Math.abs(System.currentTimeMillis() - this.f3268a);
        if (jAbs >= (b.c * 2) - 3600) {
            com.igexin.c.a.c.a.a(f3267e + "|current time - last detect time > " + (b.c / 1000) + " s, detect = true", new Object[0]);
            f.f3264a.set(true);
            return true;
        }
        if (!f.f3264a.getAndSet(true)) {
            long jAbs2 = Math.abs(b.c - jAbs);
            f.g().a(jAbs2, TimeUnit.MILLISECONDS);
            com.igexin.c.a.c.a.a(f3267e + "|set next detect time = " + jAbs2, new Object[0]);
        }
        return false;
    }

    private boolean r() {
        return c() == b.EnumC0076b.b;
    }

    public final e a(String str) {
        synchronized (this.g) {
            for (Map.Entry<String, e> entry : this.b.entrySet()) {
                if (entry.getKey().equals(str)) {
                    return entry.getValue();
                }
            }
            return null;
        }
    }

    public final void a(d dVar) {
        synchronized (this.f) {
            this.c.put(dVar.a(), dVar);
        }
        a aVar = this.d;
        synchronized (aVar.d) {
            aVar.b = 0;
            Collections.sort(aVar.c, aVar.k);
        }
    }

    public abstract int c();

    public abstract i d();

    /*  JADX ERROR: ConcurrentModificationException in pass: ConstructorVisitor
        java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList$Itr.checkForComodification(Unknown Source)
        	at java.base/java.util.ArrayList$Itr.next(Unknown Source)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:139)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public final void e() {
        /*
            r8 = this;
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r8.f3268a
            long r0 = r0 - r2
            long r0 = java.lang.Math.abs(r0)
            long r2 = com.igexin.push.c.b.c
            r4 = 2
            long r2 = r2 * r4
            r4 = 3600(0xe10, double:1.7786E-320)
            long r2 = r2 - r4
            r4 = 1
            r5 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto L46
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.igexin.push.c.h.f3267e
            r0.append(r1)
            java.lang.String r1 = "|current time - last detect time > "
            r0.append(r1)
            long r1 = com.igexin.push.c.b.c
            r6 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r6
            r0.append(r1)
            java.lang.String r1 = " s, detect = true"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r5]
            com.igexin.c.a.c.a.a(r0, r1)
            java.util.concurrent.atomic.AtomicBoolean r0 = com.igexin.push.c.f.f3264a
            r0.set(r4)
            goto L7b
        L46:
            java.util.concurrent.atomic.AtomicBoolean r2 = com.igexin.push.c.f.f3264a
            boolean r2 = r2.getAndSet(r4)
            if (r2 != 0) goto L7a
            long r2 = com.igexin.push.c.b.c
            long r2 = r2 - r0
            long r0 = java.lang.Math.abs(r2)
            com.igexin.push.c.f r2 = com.igexin.push.c.f.g()
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS
            r2.a(r0, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = com.igexin.push.c.h.f3267e
            r2.append(r3)
            java.lang.String r3 = "|set next detect time = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.Object[] r1 = new java.lang.Object[r5]
            com.igexin.c.a.c.a.a(r0, r1)
        L7a:
            r4 = 0
        L7b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            if (r4 != 0) goto L97
            r0.<init>()
            java.lang.String r1 = com.igexin.push.c.h.f3267e
            r0.append(r1)
            java.lang.String r1 = "|startDetect detect = false, return !!!"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r5]
            com.igexin.c.a.c.a.a(r0, r1)
            return
        L97:
            r0.<init>()
            java.lang.String r1 = com.igexin.push.c.h.f3267e
            r0.append(r1)
            java.lang.String r1 = "|startDetect detect = true, start detect !!!"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r5]
            com.igexin.c.a.c.a.a(r0, r1)
            r8.i()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.c.h.e():void");
    }

    public final void f() {
        synchronized (this.g) {
            for (Map.Entry<String, e> entry : this.b.entrySet()) {
                entry.getValue().a((i) null);
                entry.getValue().a();
            }
        }
    }

    public final void g() {
        f();
        p();
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        synchronized (this.g) {
            int size = this.b.size();
            if (defaultXfrList.size() < size) {
                int size2 = size - defaultXfrList.size();
                Iterator<Map.Entry<String, e>> it = this.b.entrySet().iterator();
                for (int i = 0; it.hasNext() && i < size2; i++) {
                    it.next().getValue().b();
                    it.remove();
                }
            }
            ArrayList arrayList = new ArrayList(this.b.values());
            this.b.clear();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < defaultXfrList.size(); i2++) {
                d dVar = new d();
                String[] strArrA = com.igexin.c.a.b.g.a(defaultXfrList.get(i2));
                dVar.a(defaultXfrList.get(i2));
                dVar.b = Integer.parseInt(strArrA[2]);
                if (i2 < size) {
                    e eVar = (e) arrayList.get(i2);
                    eVar.b = dVar;
                    this.b.put(dVar.a(), eVar);
                } else {
                    b(dVar);
                }
                arrayList2.add(dVar);
            }
            this.d.b(arrayList2);
        }
    }

    public final void h() {
        f();
        p();
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        synchronized (this.g) {
            Iterator<Map.Entry<String, e>> it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().b();
            }
            this.b.clear();
            ArrayList arrayList = new ArrayList();
            d dVar = new d();
            String[] strArrA = com.igexin.c.a.b.g.a(defaultXfrList.get(0));
            dVar.a(defaultXfrList.get(0));
            dVar.b = Integer.parseInt(strArrA[2]);
            arrayList.add(dVar);
            this.d.b(arrayList);
            arrayList.clear();
        }
    }

    public final void i() {
        this.f3268a = System.currentTimeMillis();
        synchronized (this.g) {
            for (Map.Entry<String, e> entry : this.b.entrySet()) {
                entry.getValue();
                entry.getValue().a(d());
                if (entry.getValue().b != null) {
                    entry.getValue().b.b();
                }
                e value = entry.getValue();
                synchronized (i.class) {
                    if (value.c != null) {
                        value.f3262a = com.igexin.b.a.a().f3132a.submit(value.new AnonymousClass1());
                    }
                }
            }
        }
    }

    public final synchronized void j() {
        this.f3268a = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        synchronized (this.g) {
            try {
                jSONObject.put("lastDetectTime", this.f3268a);
                jSONObject.put("list", jSONArray);
                Iterator<Map.Entry<String, e>> it = this.b.entrySet().iterator();
                while (it.hasNext()) {
                    JSONObject jSONObjectF = it.next().getValue().b.f();
                    if (jSONObjectF != null) {
                        jSONArray.put(jSONObjectF);
                    }
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
        if (jSONObject.length() > 0) {
            if (r()) {
                com.igexin.push.core.e.f.a().b(jSONObject.toString(), true);
                return;
            }
            com.igexin.push.core.e.f.a().b(jSONObject.toString(), false);
        }
    }

    public final synchronized void l() {
        a aVar = this.d;
        a.EnumC0075a enumC0075a = aVar.f3248e;
        com.igexin.c.a.c.a.a(a.f3247a + "|detect success, current type = " + aVar.f3248e, new Object[0]);
        if (aVar.f3248e == a.EnumC0075a.BACKUP) {
            aVar.a(a.EnumC0075a.TRY_NORMAL);
            com.igexin.push.core.d unused = d.a.f3384a;
            com.igexin.push.e.a.a(true);
        }
    }

    public final void m() {
        synchronized (h.class) {
            if (this.i == null) {
                HandlerThread handlerThread = new HandlerThread("NetDetect-T");
                handlerThread.start();
                this.i = new Handler(handlerThread.getLooper());
            }
        }
        this.i.removeCallbacksAndMessages("detToken");
        this.i.postAtTime(new Runnable() { // from class: com.igexin.push.c.h.2
            @Override // java.lang.Runnable
            public final void run() {
                String unused = h.f3267e;
                try {
                    h.this.j();
                } catch (Throwable th) {
                    com.igexin.c.a.c.a.a(th);
                }
            }
        }, "detToken", SystemClock.uptimeMillis() + 5000);
    }

    public final synchronized void n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("loginFailedlCnt", this.d.g);
            jSONObject.put("lastChange2BackupTime", this.d.h);
            jSONObject.put("lastOfflineTime", this.d.i);
            jSONObject.put("domainType", this.d.f3248e.d);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        if (jSONObject.length() > 0) {
            if (r()) {
                com.igexin.push.core.e.f.a().a(jSONObject.toString(), true);
                return;
            }
            com.igexin.push.core.e.f.a().a(jSONObject.toString(), false);
        }
    }
}
