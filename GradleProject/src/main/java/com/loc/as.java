package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.lzy.okgo.cookie.SerializableCookie;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.constant.AbsoluteConst;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpLimitUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, c> f3650a = new ConcurrentHashMap<>(8);
    public static volatile List<String> b = Collections.synchronizedList(new ArrayList(8));
    public static volatile ConcurrentHashMap<String, b> c = new ConcurrentHashMap<>(8);
    public static Random d = new Random();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ConcurrentHashMap<String, String> f3651e = new ConcurrentHashMap<>(8);
    public static List<bb> f = Collections.synchronizedList(new ArrayList(16));

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3652a;
        public int b;
        public double c;

        public a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public aw f3653a;
        public long b;

        public b() {
        }

        public /* synthetic */ b(byte b) {
            this();
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Map<String, List<a>> f3654a;
        public Map<String, String> b;

        public c() {
            this.f3654a = new HashMap(8);
            this.b = new HashMap(8);
        }

        public /* synthetic */ c(byte b) {
            this();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && c.class == obj.getClass()) {
                c cVar = (c) obj;
                if (this.f3654a.equals(cVar.f3654a) && this.b.equals(cVar.b)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Map<String, List<a>> map = this.f3654a;
            int iHashCode = map != null ? map.hashCode() : 0;
            Map<String, String> map2 = this.b;
            return iHashCode + (map2 != null ? map2.hashCode() : 0);
        }
    }

    public static aw a(String str, String str2, String str3) {
        Uri uri;
        if (c == null) {
            return null;
        }
        if (c.containsKey(AbsoluteConst.XML_APP)) {
            b bVar = c.get(AbsoluteConst.XML_APP);
            if (SystemClock.elapsedRealtime() <= bVar.b) {
                aw awVar = bVar.f3653a;
                if (awVar != null) {
                    awVar.f3663e = false;
                }
                a(true, str3, str, 1);
                return awVar;
            }
            c.remove(AbsoluteConst.XML_APP);
        } else {
            if (!TextUtils.isEmpty(str)) {
                str2 = str;
            }
            if (!TextUtils.isEmpty(str2) && (uri = Uri.parse(str2)) != null) {
                String path = uri.getPath();
                if (c.containsKey(path)) {
                    b bVar2 = c.get(path);
                    if (SystemClock.elapsedRealtime() <= bVar2.b) {
                        aw awVar2 = bVar2.f3653a;
                        if (awVar2 != null) {
                            awVar2.f3663e = false;
                        }
                        a(true, str3, str, 2);
                        return awVar2;
                    }
                    c.remove(path);
                }
            }
        }
        return null;
    }

    public static synchronized String a(String str, String str2) throws j {
        try {
            try {
                System.currentTimeMillis();
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                    Context context = l.c;
                    try {
                        if (b == null) {
                            b = Collections.synchronizedList(new ArrayList(8));
                        }
                        if (context != null && !b.contains(str2)) {
                            b.add(str2);
                            String strB = x.b(context, "Yb3Blbl9odHRwX2NvbnRyb2w", str2);
                            if (!TextUtils.isEmpty(strB)) {
                                a(str2, new JSONObject(strB));
                            }
                        }
                    } catch (Throwable th) {
                        y.a(th, "hlUtil", "llhl");
                    }
                    if (f3650a != null && f3650a.size() > 0) {
                        if (!f3650a.containsKey(str2)) {
                            return str;
                        }
                        c cVar = f3650a.get(str2);
                        if (cVar == null) {
                            return str;
                        }
                        if (a(str, cVar, str2)) {
                            throw new j("服务QPS超限");
                        }
                        return b(str, cVar, str2);
                    }
                    return str;
                }
                return str;
            } catch (j e2) {
                throw e2;
            } catch (Throwable th2) {
                y.a(th2, "hlUtil", "pcr");
                return str;
            }
        } finally {
        }
    }

    public static void a() {
        try {
            Context context = l.c;
            if (context == null) {
                return;
            }
            bc.a(b(), context);
        } catch (Throwable unused) {
        }
    }

    public static void a(c cVar, JSONObject jSONObject) {
        JSONArray jSONArrayNames;
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("domainMap");
            if (jSONObjectOptJSONObject == null || (jSONArrayNames = jSONObjectOptJSONObject.names()) == null) {
                return;
            }
            HashMap map = new HashMap(8);
            int length = jSONArrayNames.length();
            for (int i = 0; i < length; i++) {
                String strOptString = jSONArrayNames.optString(i);
                map.put(strOptString, jSONObjectOptJSONObject.optString(strOptString));
            }
            cVar.b = map;
        } catch (Throwable th) {
            y.a(th, "hlUtil", "pdr");
        }
    }

    public static synchronized void a(t tVar, JSONObject jSONObject) {
        if (tVar == null) {
            return;
        }
        try {
            String strA = tVar.a();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            if (jSONObject == null) {
                a(strA);
            }
            if (!l.a(jSONObject.optString("able", null), false)) {
                a(strA);
            } else {
                x.a(l.c, "Yb3Blbl9odHRwX2NvbnRyb2w", strA, jSONObject.toString());
                a(strA, jSONObject);
            }
        } catch (Throwable th) {
            y.a(th, "hlUtil", "par");
        }
    }

    public static synchronized void a(String str) {
        try {
            if (f3650a.containsKey(str)) {
                f3650a.remove(str);
            }
            SharedPreferences.Editor editorB = x.b(l.c, "Yb3Blbl9odHRwX2NvbnRyb2w");
            x.a(editorB, str);
            x.a(editorB);
        } catch (Throwable th) {
            y.a(th, "hlUtil", "rc");
        }
    }

    public static void a(String str, JSONObject jSONObject) {
        try {
            byte b2 = 0;
            c cVar = new c(b2);
            try {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(AbsoluteConst.JSON_VALUE_BLOCK);
                if (jSONArrayOptJSONArray != null) {
                    HashMap map = new HashMap(8);
                    int i = 0;
                    while (i < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null) {
                            String strOptString = jSONObjectOptJSONObject.optString("api");
                            if (!TextUtils.isEmpty(strOptString)) {
                                if (!strOptString.startsWith("/")) {
                                    strOptString = "/" + strOptString;
                                }
                                if (strOptString.endsWith("/")) {
                                    strOptString = strOptString.substring(b2, strOptString.length() - 1);
                                }
                                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("periods");
                                if (jSONArrayOptJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    int i2 = 0;
                                    while (i2 < jSONArrayOptJSONArray2.length()) {
                                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                                        if (jSONObjectOptJSONObject2 != null) {
                                            a aVar = new a(b2);
                                            aVar.f3652a = jSONObjectOptJSONObject2.optString("begin");
                                            aVar.b = jSONObjectOptJSONObject2.optInt("duration");
                                            aVar.c = jSONObjectOptJSONObject2.optDouble("percent");
                                            arrayList.add(aVar);
                                        }
                                        i2++;
                                        b2 = 0;
                                    }
                                    map.put(strOptString, arrayList);
                                }
                            }
                        }
                        i++;
                        b2 = 0;
                    }
                    cVar.f3654a = map;
                }
            } catch (Throwable th) {
                y.a(th, "hlUtil", "pbr");
            }
            a(cVar, jSONObject);
            if (cVar.b == null && cVar.f3654a == null) {
                a(str);
                return;
            }
            try {
                if (f3650a == null) {
                    f3650a = new ConcurrentHashMap<>(8);
                }
                f3650a.put(str, cVar);
            } catch (Throwable th2) {
                y.a(th2, "hlUtil", "ucr");
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(URL url, aw awVar) {
        List<String> list;
        try {
            if (c == null) {
                c = new ConcurrentHashMap<>(8);
            }
            if (awVar.b != null && awVar.b.containsKey("nb") && (list = awVar.b.get("nb")) != null && list.size() > 0) {
                byte b2 = 0;
                String[] strArrSplit = list.get(0).split("#");
                if (strArrSplit.length < 2) {
                    return;
                }
                int i = Integer.parseInt(strArrSplit[0]);
                long j = Integer.parseInt(strArrSplit[1]);
                b bVar = new b(b2);
                bVar.f3653a = awVar;
                if (j <= 0) {
                    j = 30;
                }
                bVar.b = SystemClock.elapsedRealtime() + (j * 1000);
                if (i == 1) {
                    c.put(AbsoluteConst.XML_APP, bVar);
                } else {
                    if (i != 2 || url == null) {
                        return;
                    }
                    c.put(url.getPath(), bVar);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(boolean z, String str) {
        try {
            Context context = l.c;
            if (context != null && !TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                jSONObject.put("type", z ? v.g : v.f);
                jSONObject.put("name", str);
                jSONObject.put("version", v.a(str));
                String string = jSONObject.toString();
                bb bbVar = new bb(context, "core", "4.1.0", "O005");
                bbVar.a(string);
                bc.a(bbVar, context);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(boolean z, String str, String str2, int i) {
        String str3;
        Integer num;
        try {
            Context context = l.c;
            if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", System.currentTimeMillis());
                String strA = v.a(str);
                if (z) {
                    str3 = "type";
                    num = v.i;
                } else {
                    str3 = "type";
                    num = v.h;
                }
                jSONObject.put(str3, num);
                jSONObject.put("name", str);
                jSONObject.put("version", strA);
                jSONObject.put("uri", Uri.parse(str2).getPath());
                jSONObject.put("blockLevel", i);
                String string = jSONObject.toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                bb bbVar = new bb(context, "core", "4.1.0", "O005");
                bbVar.a(string);
                if (f == null) {
                    f = Collections.synchronizedList(new ArrayList(16));
                }
                synchronized (f) {
                    f.add(bbVar);
                    if (f.size() >= 15) {
                        a();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean a(String str, c cVar, String str2) {
        Map<String, List<a>> map;
        try {
            map = cVar.f3654a;
        } catch (Throwable th) {
            y.a(th, "hlUtil", "inb");
        }
        if (map != null && map.size() > 0) {
            if (map.containsKey(Operators.MUL)) {
                Iterator<Map.Entry<String, List<a>>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    if (a(it.next().getValue())) {
                        a(false, str2, str, 1);
                        return true;
                    }
                }
            } else {
                String path = Uri.parse(str).getPath();
                if (map.containsKey(path) && a(map.get(path))) {
                    a(false, str2, str, 2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(java.util.List<com.loc.as.a> r10) {
        /*
            r0 = 0
            if (r10 == 0) goto L88
            int r1 = r10.size()
            if (r1 > 0) goto Lb
            goto L88
        Lb:
            java.util.Iterator r10 = r10.iterator()
        Lf:
            boolean r1 = r10.hasNext()
            if (r1 == 0) goto L88
            java.lang.Object r1 = r10.next()
            com.loc.as$a r1 = (com.loc.as.a) r1
            r2 = 1
            if (r1 == 0) goto L84
            double r3 = r1.c
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L84
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.String r5 = r1.f3652a
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L84
            int r5 = r1.b
            if (r5 <= 0) goto L84
            java.lang.String r5 = r1.f3652a
            java.lang.String r6 = "HH:mm:ss"
            java.util.Calendar r5 = com.loc.u.a(r5, r6)
            long r5 = r5.getTimeInMillis()
            long r5 = r3 - r5
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L84
            int r7 = r1.b
            int r7 = r7 * 1000
            long r7 = (long) r7
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L84
            double r5 = r1.c
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L5d
        L5b:
            r1 = 1
            goto L85
        L5d:
            java.util.Random r5 = com.loc.as.d
            if (r5 != 0) goto L68
            java.util.Random r5 = new java.util.Random
            r5.<init>()
            com.loc.as.d = r5
        L68:
            java.util.Random r5 = com.loc.as.d
            java.util.UUID r6 = java.util.UUID.randomUUID()
            int r6 = r6.hashCode()
            long r6 = (long) r6
            long r6 = r6 + r3
            r5.setSeed(r6)
            java.util.Random r3 = com.loc.as.d
            double r3 = r3.nextDouble()
            double r5 = r1.c
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L84
            goto L5b
        L84:
            r1 = 0
        L85:
            if (r1 == 0) goto Lf
            return r2
        L88:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.as.a(java.util.List):boolean");
    }

    public static String b(String str, c cVar, String str2) {
        Map<String, String> map;
        try {
            map = cVar.b;
        } catch (Throwable th) {
            y.a(th, "hlUtil", "pdr");
        }
        if (map != null && map.size() > 0) {
            Uri uri = Uri.parse(str);
            String authority = uri.getAuthority();
            if (map.containsKey(authority)) {
                String str3 = map.get(authority);
                str = uri.buildUpon().authority(str3).toString();
                try {
                    Context context = l.c;
                    if (context != null && !TextUtils.isEmpty(str2)) {
                        if (f3651e == null) {
                            f3651e = new ConcurrentHashMap<>(8);
                        }
                        synchronized (f3651e) {
                            if (!f3651e.containsKey(authority)) {
                                f3651e.put(authority, str3);
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("timestamp", System.currentTimeMillis());
                                jSONObject.put("type", v.j);
                                jSONObject.put("name", str2);
                                jSONObject.put("version", v.a(str2));
                                jSONObject.put(SerializableCookie.DOMAIN, authority + "#" + str3);
                                String string = jSONObject.toString();
                                if (!TextUtils.isEmpty(string)) {
                                    bb bbVar = new bb(context, "core", "4.1.0", "O005");
                                    bbVar.a(string);
                                    bc.a(bbVar, context);
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
            }
            return str;
        }
        return str;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:19:0x002a
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static java.util.List<com.loc.bb> b() {
        /*
            r0 = 0
            java.util.List<com.loc.bb> r1 = com.loc.as.f     // Catch: java.lang.Throwable -> L2c
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L2c
            java.util.List<com.loc.bb> r2 = com.loc.as.f     // Catch: java.lang.Throwable -> L22
            if (r2 == 0) goto L20
            java.util.List<com.loc.bb> r2 = com.loc.as.f     // Catch: java.lang.Throwable -> L22
            int r2 = r2.size()     // Catch: java.lang.Throwable -> L22
            if (r2 <= 0) goto L20
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L22
            r2.<init>()     // Catch: java.lang.Throwable -> L22
            java.util.List<com.loc.bb> r0 = com.loc.as.f     // Catch: java.lang.Throwable -> L2a
            r2.addAll(r0)     // Catch: java.lang.Throwable -> L2a
            java.util.List<com.loc.bb> r0 = com.loc.as.f     // Catch: java.lang.Throwable -> L2a
            r0.clear()     // Catch: java.lang.Throwable -> L2a
            r0 = r2
        L20:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L22
            goto L2c
        L22:
            r2 = move-exception
            r3 = r2
            r2 = r0
            r0 = r3
        L26:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L2a
            throw r0     // Catch: java.lang.Throwable -> L28
        L28:
            r0 = r2
            goto L2c
        L2a:
            r0 = move-exception
            goto L26
        L2c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.as.b():java.util.List");
    }
}
