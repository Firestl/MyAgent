package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.loc.p;
import com.tencent.connect.common.Constants;
import io.dcloud.common.adapter.util.Logger;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthConfigManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3812a = -1;
    public static String b = "";
    public static Context c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile boolean f3813e = true;
    public static Vector<e> f = new Vector<>();
    public static Map<String, Integer> g = new HashMap();
    public static String h = null;
    public static long i = 0;
    public static volatile boolean d = false;
    public static volatile ConcurrentHashMap<String, Long> j = new ConcurrentHashMap<>(8);
    public static volatile ConcurrentHashMap<String, Long> k = new ConcurrentHashMap<>(8);
    public static volatile ConcurrentHashMap<String, d> l = new ConcurrentHashMap<>(8);

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public interface a {
        void a(b bVar);
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Deprecated
        public JSONObject f3815a;

        @Deprecated
        public JSONObject b;
        public String c;
        public int d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f3816e = 0;
        public JSONObject f;
        public a g;
        public C0081b h;
        public boolean i;

        /* JADX INFO: compiled from: AuthConfigManager.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f3817a;
            public boolean b;
            public JSONObject c;
        }

        /* JADX INFO: renamed from: com.loc.l$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AuthConfigManager.java */
        public static class C0081b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f3818a;
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class c extends ar {
        public String f;
        public Map<String, String> g;
        public boolean h;
        public String i;
        public String j;
        public String k;

        public c(Context context, t tVar, String str, String str2, String str3, String str4) {
            super(context, tVar);
            this.f = str;
            this.g = null;
            this.h = Build.VERSION.SDK_INT != 19;
            this.i = str2;
            this.j = str3;
            this.k = str4;
        }

        public final boolean a() {
            return this.h;
        }

        @Override // com.loc.ar
        public final byte[] a_() {
            return null;
        }

        @Override // com.loc.av
        public final Map<String, String> b() {
            if (TextUtils.isEmpty(this.k)) {
                return null;
            }
            HashMap map = new HashMap();
            map.put("host", this.k);
            return map;
        }

        @Override // com.loc.av
        public final String c() {
            String str = this.h ? "https://restsdk.amap.com/v3/iasdkauth" : "http://restsdk.amap.com/v3/iasdkauth";
            try {
                return !TextUtils.isEmpty(this.i) ? str.replace("restsdk.amap.com", this.i) : str;
            } catch (Throwable unused) {
                return str;
            }
        }

        @Override // com.loc.q, com.loc.av
        public final String d() {
            try {
                String str = this.h ? "https://restsdk.amap.com/v3/iasdkauth" : "http://restsdk.amap.com/v3/iasdkauth";
                try {
                    if (!TextUtils.isEmpty(this.j)) {
                        return str.replace("restsdk.amap.com", this.j);
                    }
                } catch (Throwable unused) {
                }
                Uri uri = Uri.parse(str);
                return uri.buildUpon().authority("dualstack-" + uri.getAuthority()).build().toString();
            } catch (Throwable unused2) {
                return null;
            }
        }

        @Override // com.loc.ar
        public final byte[] f() {
            String strW = n.w(this.f3649a);
            if (!TextUtils.isEmpty(strW)) {
                strW = r.a(new StringBuilder(strW).reverse().toString());
            }
            HashMap map = new HashMap();
            map.put("authkey", TextUtils.isEmpty(this.f) ? "" : this.f);
            map.put("plattype", "android");
            map.put("product", this.b.a());
            map.put("version", this.b.b());
            map.put("output", "json");
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            map.put("androidversion", sb.toString());
            map.put("deviceId", strW);
            map.put("manufacture", Build.MANUFACTURER);
            Map<String, String> map2 = this.g;
            if (map2 != null && !map2.isEmpty()) {
                map.putAll(this.g);
            }
            map.put("abitype", u.a(this.f3649a));
            map.put("ext", this.b.d());
            return u.a(u.a(map));
        }

        @Override // com.loc.ar
        public final String g() {
            return "3.0";
        }

        @Override // com.loc.av
        public final String h() {
            return !TextUtils.isEmpty(this.k) ? this.k : super.h();
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public t f3819a;
        public String b;
        public a c;

        public d() {
        }

        public /* synthetic */ d(byte b) {
            this();
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3820a;
        public String b;
        public AtomicInteger c;

        public e(String str, String str2, int i) {
            this.f3820a = str;
            this.b = str2;
            this.c = new AtomicInteger(i);
        }

        public static e b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new e(jSONObject.optString("a"), jSONObject.optString(cn.com.chinatelecom.account.api.e.f.f1517a), jSONObject.optInt("h"));
            } catch (Throwable unused) {
                return null;
            }
        }

        public final int a() {
            AtomicInteger atomicInteger = this.c;
            if (atomicInteger == null) {
                return 0;
            }
            return atomicInteger.get();
        }

        public final void a(String str) {
            this.b = str;
        }

        public final String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("a", this.f3820a);
                jSONObject.put(cn.com.chinatelecom.account.api.e.f.f1517a, this.b);
                jSONObject.put("h", this.c.get());
                return jSONObject.toString();
            } catch (Throwable unused) {
                return "";
            }
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static boolean f3821a = true;
        public static boolean b = false;
        public static boolean c = true;
        public static int d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f3822e = false;
        public static int f;
    }

    public static b a(Context context, t tVar, String str, String str2, String str3, String str4) {
        return b(context, tVar, str, str2, str3, str4);
    }

    public static void a(int i2) {
        if (i2 != 2) {
            return;
        }
        try {
            e eVarB = b(c, "IPV6_CONFIG_NAME");
            String strA = u.a(System.currentTimeMillis(), Logger.TIMESTAMP_YYYY_MM_DD);
            if (!strA.equals(eVarB.b)) {
                eVarB.a(strA);
                eVarB.c.set(0);
            }
            eVarB.c.incrementAndGet();
            Context context = c;
            if (eVarB != null && !TextUtils.isEmpty(eVarB.f3820a)) {
                String strB = eVarB.b();
                if (TextUtils.isEmpty(strB) || context == null) {
                    return;
                }
                new x("IPV6_CONFIG_NAME").a(context, "i", strB);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context) {
        if (context != null) {
            c = context.getApplicationContext();
        }
    }

    public static void a(Context context, t tVar, String str) {
        HashMap map = new HashMap();
        map.put("amap_sdk_auth_fail", "1");
        map.put("amap_sdk_auth_fail_type", str);
        map.put("amap_sdk_name", tVar.a());
        map.put("amap_sdk_version", tVar.c());
        String string = new JSONObject(map).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            bb bbVar = new bb(context, "core", "1.0", "O001");
            bbVar.a(string);
            bc.a(bbVar, context);
        } catch (j unused) {
        }
    }

    public static synchronized void a(Context context, t tVar, String str, a aVar) {
        if (context == null || tVar == null) {
            return;
        }
        try {
            if (c == null) {
                c = context.getApplicationContext();
            }
            String strA = tVar.a();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            a(tVar);
            if (l == null) {
                l = new ConcurrentHashMap<>(8);
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            if (j == null) {
                j = new ConcurrentHashMap<>(8);
            }
            if (!l.containsKey(strA)) {
                d dVar = new d((byte) 0);
                dVar.f3819a = tVar;
                dVar.b = str;
                dVar.c = aVar;
                l.put(strA, dVar);
                j.put(strA, Long.valueOf(x.c(c, "open_common", strA)));
            }
        } catch (Throwable th) {
            y.a(th, "at", "rglc");
        }
    }

    public static void a(Context context, t tVar, String str, b bVar, JSONObject jSONObject) throws JSONException {
        boolean zA;
        String[] strArr;
        b.a aVar = new b.a();
        aVar.f3817a = false;
        aVar.b = false;
        bVar.g = aVar;
        try {
            String[] strArrSplit = str.split(";");
            if (strArrSplit != null && strArrSplit.length > 0) {
                int length = strArrSplit.length;
                int i2 = 0;
                while (i2 < length) {
                    String str2 = strArrSplit[i2];
                    if (jSONObject.has(str2)) {
                        strArr = strArrSplit;
                        bVar.f.putOpt(str2, jSONObject.get(str2));
                    } else {
                        strArr = strArrSplit;
                    }
                    i2++;
                    strArrSplit = strArr;
                }
            }
        } catch (Throwable th) {
            y.a(th, "at", "co");
        }
        if (u.a(jSONObject, "16H")) {
            try {
                bVar.i = a(jSONObject.getJSONObject("16H").optString("able"), false);
            } catch (Throwable th2) {
                y.a(th2, "AuthConfigManager", "load 16H");
            }
        }
        if (u.a(jSONObject, "11K")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("11K");
                aVar.f3817a = a(jSONObject2.getString("able"), false);
                if (jSONObject2.has("off")) {
                    aVar.c = jSONObject2.getJSONObject("off");
                }
            } catch (Throwable th3) {
                y.a(th3, "AuthConfigManager", "load 11K");
            }
        }
        if (u.a(jSONObject, "145")) {
            try {
                bVar.f3815a = jSONObject.getJSONObject("145");
            } catch (Throwable th4) {
                y.a(th4, "AuthConfigManager", "load 145");
            }
        }
        if (u.a(jSONObject, "14D")) {
            try {
                bVar.b = jSONObject.getJSONObject("14D");
            } catch (Throwable th5) {
                y.a(th5, "AuthConfigManager", "load 14D");
            }
        }
        if (u.a(jSONObject, "151")) {
            try {
                JSONObject jSONObject3 = jSONObject.getJSONObject("151");
                b.C0081b c0081b = new b.C0081b();
                if (jSONObject3 != null) {
                    c0081b.f3818a = a(jSONObject3.optString("able"), false);
                }
                bVar.h = c0081b;
            } catch (Throwable th6) {
                y.a(th6, "AuthConfigManager", "load 151");
            }
        }
        if (u.a(jSONObject, "17S")) {
            try {
                JSONObject jSONObject4 = jSONObject.getJSONObject("17S");
                if (jSONObject4 != null && (zA = a(jSONObject4.optString("able"), false)) != f3813e) {
                    f3813e = zA;
                    if (context != null) {
                        SharedPreferences.Editor editorB = x.b(context, "open_common");
                        x.a(editorB, "a2", zA);
                        x.a(editorB);
                    }
                }
            } catch (Throwable th7) {
                y.a(th7, "AuthConfigManager", "load 17S");
            }
        }
        if (u.a(jSONObject, "15K")) {
            try {
                JSONObject jSONObject5 = jSONObject.getJSONObject("15K");
                if (jSONObject5 != null) {
                    boolean zA2 = a(jSONObject5.optString("ucf"), f.f3821a);
                    boolean zA3 = a(jSONObject5.optString("fsv2"), f.b);
                    boolean zA4 = a(jSONObject5.optString("usc"), f.c);
                    int iOptInt = jSONObject5.optInt("umv", f.d);
                    boolean zA5 = a(jSONObject5.optString("ust"), f.f3822e);
                    int iOptInt2 = jSONObject5.optInt("ustv", f.f);
                    if (zA2 != f.f3821a || zA3 != f.b || zA4 != f.c || iOptInt != f.d || zA5 != f.f3822e || iOptInt2 != f.d) {
                        f.f3821a = zA2;
                        f.b = zA3;
                        f.c = zA4;
                        f.d = iOptInt;
                        f.f3822e = zA5;
                        f.f = iOptInt2;
                        try {
                            SharedPreferences.Editor editorB2 = x.b(context, "open_common");
                            x.a(editorB2, "ucf", f.f3821a);
                            x.a(editorB2, "fsv2", f.b);
                            x.a(editorB2, "usc", f.c);
                            x.a(editorB2, "umv", f.d);
                            x.a(editorB2, "ust", f.f3822e);
                            x.a(editorB2, "ustv", f.f);
                            x.a(editorB2);
                        } catch (Throwable unused) {
                        }
                    }
                }
            } catch (Throwable th8) {
                y.a(th8, "AuthConfigManager", "load 15K");
            }
        }
        if (u.a(jSONObject, "183")) {
            try {
                as.a(tVar, jSONObject.getJSONObject("183"));
            } catch (Throwable th9) {
                y.a(th9, "AuthConfigManager", "load 183");
            }
        }
    }

    public static void a(Context context, t tVar, Throwable th) {
        a(context, tVar, th.getMessage());
    }

    public static void a(Context context, String str) {
        k.a(context, str);
    }

    public static void a(t tVar) {
        if (tVar != null) {
            try {
                if (TextUtils.isEmpty(tVar.a())) {
                    return;
                }
                String strC = tVar.c();
                if (TextUtils.isEmpty(strC)) {
                    strC = tVar.b();
                }
                if (TextUtils.isEmpty(strC)) {
                    return;
                }
                v.a(tVar.a(), strC);
            } catch (Throwable unused) {
            }
        }
    }

    public static synchronized void a(final String str, boolean z, final String str2, final String str3, final String str4) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            k.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
            if (l == null) {
                return;
            }
            if (l.containsKey(str)) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                if (z) {
                    as.a(true, str);
                }
                ab.d().submit(new Runnable() { // from class: com.loc.l.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        d dVar = (d) l.l.get(str);
                        if (dVar == null) {
                            return;
                        }
                        a aVar = dVar.c;
                        b bVarA = l.a(l.c, dVar.f3819a, dVar.b, str2, str3, str4);
                        if (bVarA == null || aVar == null) {
                            return;
                        }
                        aVar.a(bVarA);
                    }
                });
            }
        } catch (Throwable th) {
            y.a(th, "at", "lca");
        }
    }

    public static void a(String str, boolean z, boolean z2, boolean z3, long j2) {
        if (TextUtils.isEmpty(str) || c == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("url", str);
        map.put("downLevel", String.valueOf(z2));
        map.put("ant", n.q(c) == 0 ? "0" : "1");
        map.put("type", z ? Constants.VIA_SHARE_TYPE_INFO : "4");
        map.put("status", z3 ? "0" : "1");
        map.put("duration", String.valueOf(j2));
        String string = new JSONObject(map).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            bb bbVar = new bb(c, "core", "1.0", "O002");
            bbVar.a(string);
            bc.a(bbVar, c);
        } catch (j unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0067 A[Catch: all -> 0x00ee, TryCatch #0 {all -> 0x00ee, blocks: (B:5:0x0008, B:7:0x0014, B:9:0x001a, B:11:0x0022, B:14:0x0032, B:16:0x0038, B:20:0x004a, B:21:0x0061, B:23:0x0067, B:25:0x0077, B:26:0x0084, B:28:0x008a, B:30:0x0098, B:32:0x00a0, B:33:0x00a3, B:35:0x00a7, B:37:0x00af, B:39:0x00bf, B:42:0x00c6, B:44:0x00cf, B:45:0x00d7, B:47:0x00dd, B:49:0x00e5, B:17:0x003b), top: B:69:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a() {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.a():boolean");
    }

    public static synchronized boolean a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (l == null) {
                return false;
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            if (l.containsKey(str) && !k.containsKey(str)) {
                k.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
                return true;
            }
        } finally {
        }
        return false;
    }

    public static synchronized boolean a(String str, long j2) {
        boolean z = false;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (j2 > c(str)) {
                long jLongValue = 0;
                if (k != null && k.containsKey(str)) {
                    jLongValue = k.get(str).longValue();
                }
                if (SystemClock.elapsedRealtime() - jLongValue > com.igexin.push.config.c.k) {
                    z = true;
                }
            }
        } catch (Throwable unused) {
        }
        return z;
    }

    public static boolean a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] strArrSplit = URLDecoder.decode(str).split("/");
            return strArrSplit[strArrSplit.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z;
        }
    }

    public static boolean a(InetAddress inetAddress) {
        return inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.loc.l.b b(android.content.Context r22, com.loc.t r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 696
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.b(android.content.Context, com.loc.t, java.lang.String, java.lang.String, java.lang.String, java.lang.String):com.loc.l$b");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized com.loc.l.e b(android.content.Context r6, java.lang.String r7) {
        /*
            java.lang.Class<com.loc.l> r0 = com.loc.l.class
            monitor-enter(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L73
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L2c
            r1 = 0
        Lc:
            java.util.Vector<com.loc.l$e> r4 = com.loc.l.f     // Catch: java.lang.Throwable -> L73
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L73
            if (r1 >= r4) goto L2c
            java.util.Vector<com.loc.l$e> r4 = com.loc.l.f     // Catch: java.lang.Throwable -> L73
            java.lang.Object r4 = r4.get(r1)     // Catch: java.lang.Throwable -> L73
            com.loc.l$e r4 = (com.loc.l.e) r4     // Catch: java.lang.Throwable -> L73
            if (r4 == 0) goto L29
            java.lang.String r5 = com.loc.l.e.c(r4)     // Catch: java.lang.Throwable -> L73
            boolean r5 = r7.equals(r5)     // Catch: java.lang.Throwable -> L73
            if (r5 == 0) goto L29
            goto L2d
        L29:
            int r1 = r1 + 1
            goto Lc
        L2c:
            r4 = r2
        L2d:
            if (r4 == 0) goto L31
            monitor-exit(r0)
            return r4
        L31:
            if (r6 != 0) goto L35
            monitor-exit(r0)
            return r2
        L35:
            com.loc.x r1 = new com.loc.x     // Catch: java.lang.Throwable -> L73
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = "i"
            java.lang.String r6 = r1.a(r6, r7)     // Catch: java.lang.Throwable -> L73
            com.loc.l$e r6 = com.loc.l.e.b(r6)     // Catch: java.lang.Throwable -> L73
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = "yyyyMMdd"
            java.lang.String r7 = com.loc.u.a(r1, r7)     // Catch: java.lang.Throwable -> L73
            if (r6 != 0) goto L58
            com.loc.l$e r6 = new com.loc.l$e     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = "IPV6_CONFIG_NAME"
            r6.<init>(r1, r7, r3)     // Catch: java.lang.Throwable -> L73
        L58:
            java.lang.String r1 = com.loc.l.e.a(r6)     // Catch: java.lang.Throwable -> L73
            boolean r1 = r7.equals(r1)     // Catch: java.lang.Throwable -> L73
            if (r1 != 0) goto L6c
            r6.a(r7)     // Catch: java.lang.Throwable -> L73
            java.util.concurrent.atomic.AtomicInteger r7 = com.loc.l.e.b(r6)     // Catch: java.lang.Throwable -> L73
            r7.set(r3)     // Catch: java.lang.Throwable -> L73
        L6c:
            java.util.Vector<com.loc.l$e> r7 = com.loc.l.f     // Catch: java.lang.Throwable -> L73
            r7.add(r6)     // Catch: java.lang.Throwable -> L73
            monitor-exit(r0)
            return r6
        L73:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.b(android.content.Context, java.lang.String):com.loc.l$e");
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        f3813e = x.a(context, "open_common", "a2", true);
    }

    public static synchronized void b(String str) {
        if (k == null) {
            return;
        }
        if (k.containsKey(str)) {
            k.remove(str);
        }
    }

    public static synchronized void b(String str, long j2) {
        try {
            if (l != null && l.containsKey(str)) {
                if (j == null) {
                    j = new ConcurrentHashMap<>(8);
                }
                j.put(str, Long.valueOf(j2));
                if (c != null) {
                    SharedPreferences.Editor editorB = x.b(c, "open_common");
                    x.a(editorB, str, j2);
                    x.a(editorB);
                }
            }
        } catch (Throwable th) {
            y.a(th, "at", "ucut");
        }
    }

    public static synchronized void b(String str, boolean z) {
        a(str, z, (String) null, (String) null, (String) null);
    }

    public static boolean b() {
        Integer num;
        Context context = c;
        if (context == null) {
            return false;
        }
        String strV = n.v(context);
        return (TextUtils.isEmpty(strV) || (num = g.get(strV.toUpperCase())) == null || num.intValue() != 2) ? false : true;
    }

    public static synchronized long c(String str) {
        try {
            if (j == null) {
                j = new ConcurrentHashMap<>(8);
            }
            if (j.containsKey(str)) {
                return j.get(str).longValue();
            }
        } finally {
        }
        return 0L;
    }

    public static boolean c() {
        Integer num;
        Context context = c;
        if (context == null) {
            return false;
        }
        String strV = n.v(context);
        return (TextUtils.isEmpty(strV) || (num = g.get(strV.toUpperCase())) == null || num.intValue() < 2) ? false : true;
    }

    public static void d() {
        if (d) {
            return;
        }
        try {
            d = true;
            Context context = c;
            if (context == null) {
                return;
            }
            p.a.f3831a.a(c);
            b(c);
            f.f3821a = x.a(context, "open_common", "ucf", f.f3821a);
            f.b = x.a(context, "open_common", "fsv2", f.b);
            f.c = x.a(context, "open_common", "usc", f.c);
            f.d = x.a(context, "open_common", "umv", f.d);
            f.f3822e = x.a(context, "open_common", "ust", f.f3822e);
            f.f = x.a(context, "open_common", "ustv", f.f);
        } catch (Throwable unused) {
        }
    }
}
