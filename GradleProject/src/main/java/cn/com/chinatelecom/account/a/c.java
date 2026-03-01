package cn.com.chinatelecom.account.a;

import android.content.Context;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.Helper;
import cn.com.chinatelecom.account.api.e.g;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends cn.com.chinatelecom.account.api.d.a {
    public static final String b = "c";

    public static void a(Context context, int i) {
        try {
            cn.com.chinatelecom.account.api.e.c.a(context, "key_c_l_l_v", i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r6, java.lang.String r7) {
        /*
            int r0 = r7.hashCode()
            r1 = 64897(0xfd81, float:9.094E-41)
            r2 = 2
            r3 = 1
            r4 = -1
            r5 = 0
            if (r0 == r1) goto L2c
            r1 = 78159(0x1314f, float:1.09524E-40)
            if (r0 == r1) goto L22
            r1 = 66247144(0x3f2d9e8, float:1.42735105E-36)
            if (r0 == r1) goto L18
            goto L36
        L18:
            java.lang.String r0 = "ERROR"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L36
            r7 = 1
            goto L37
        L22:
            java.lang.String r0 = "OFF"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L36
            r7 = 2
            goto L37
        L2c:
            java.lang.String r0 = "ALL"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L36
            r7 = 0
            goto L37
        L36:
            r7 = -1
        L37:
            if (r7 == 0) goto L40
            if (r7 == r3) goto L41
            if (r7 == r2) goto L3e
            goto L40
        L3e:
            r4 = -2
            goto L41
        L40:
            r4 = 0
        L41:
            a(r6, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.c.a(android.content.Context, java.lang.String):void");
    }

    public static void a(Context context, List<String> list) {
        int iB = b(context);
        if (iB == -2) {
            return;
        }
        b(context, list, iB);
    }

    public static int b(Context context) {
        try {
            return cn.com.chinatelecom.account.api.e.c.b(context, "key_c_l_l_v", 0);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public static String b(Context context, String str) {
        return a.a(context, g.c(), str);
    }

    public static String b(Context context, Queue<String> queue) {
        JSONArray jSONArray = new JSONArray();
        String string = jSONArray.toString();
        if (!queue.isEmpty()) {
            Iterator<String> it = queue.iterator();
            while (it.hasNext()) {
                try {
                    jSONArray.put(new JSONObject(it.next()));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (jSONArray.length() <= 0) {
            return "";
        }
        String string2 = jSONArray.toString();
        if (!TextUtils.isEmpty(string2)) {
            try {
                string = URLEncoder.encode(Helper.guulam(context, string2), "UTF-8");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return b(context, string);
    }

    public static void b(final Context context, final List<String> list, final int i) {
        cn.com.chinatelecom.account.api.d.a.a(new Runnable() { // from class: cn.com.chinatelecom.account.a.c.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                try {
                    Queue queueC = c.c(context, list, i);
                    if (queueC.isEmpty()) {
                        return;
                    }
                    String strB = c.b(context, (Queue<String>) queueC);
                    JSONObject jSONObject = null;
                    int i2 = -1;
                    try {
                        if (!TextUtils.isEmpty(strB)) {
                            JSONObject jSONObject2 = new JSONObject(strB);
                            try {
                                i2 = jSONObject2.getInt("code");
                                jSONObject = jSONObject2;
                            } catch (Exception e2) {
                                e = e2;
                                jSONObject = jSONObject2;
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                    if (jSONObject == null || i2 != 0) {
                        c.b(context, (Queue<String>) queueC, i);
                    } else {
                        c.c(context);
                        queueC.clear();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public static void b(Context context, Queue<String> queue, int i) throws Throwable {
        String strEneulret;
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        if (queue != null && !queue.isEmpty()) {
            Iterator<String> it = queue.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                try {
                    jSONObject = new JSONObject(it.next());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (i != -1 || jSONObject.getInt("rt") != 0) {
                    jSONArray.put(jSONObject);
                    i2++;
                    if (i2 > 10) {
                        break;
                    }
                }
            }
        }
        if (jSONArray.length() > 0) {
            try {
                strEneulret = Helper.eneulret(jSONArray.toString());
            } catch (Exception e3) {
                e3.printStackTrace();
                strEneulret = null;
            }
        } else {
            strEneulret = "";
        }
        if (TextUtils.isEmpty(strEneulret)) {
            return;
        }
        b.a(context, strEneulret);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004c A[Catch: all -> 0x0083, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0014, B:7:0x0021, B:10:0x002f, B:12:0x0035, B:13:0x003c, B:14:0x003f, B:17:0x0046, B:20:0x004c, B:21:0x0050, B:23:0x0056, B:24:0x005c, B:26:0x0069, B:29:0x006e, B:32:0x0077, B:34:0x007d, B:31:0x0074), top: B:43:0x0003, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007d A[Catch: all -> 0x0083, LOOP:2: B:32:0x0077->B:34:0x007d, LOOP_END, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0014, B:7:0x0021, B:10:0x002f, B:12:0x0035, B:13:0x003c, B:14:0x003f, B:17:0x0046, B:20:0x004c, B:21:0x0050, B:23:0x0056, B:24:0x005c, B:26:0x0069, B:29:0x006e, B:32:0x0077, B:34:0x007d, B:31:0x0074), top: B:43:0x0003, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.util.Queue<java.lang.String> c(android.content.Context r7, java.util.List<java.lang.String> r8, int r9) {
        /*
            java.lang.Class<cn.com.chinatelecom.account.a.c> r0 = cn.com.chinatelecom.account.a.c.class
            monitor-enter(r0)
            java.util.concurrent.ConcurrentLinkedQueue r1 = new java.util.concurrent.ConcurrentLinkedQueue     // Catch: java.lang.Throwable -> L83
            r1.<init>()     // Catch: java.lang.Throwable -> L83
            java.lang.String r2 = cn.com.chinatelecom.account.a.b.a(r7)     // Catch: java.lang.Throwable -> L83
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L83
            r4 = 10
            if (r3 != 0) goto L49
            byte[] r2 = cn.com.chinatelecom.account.api.a.c.a(r2)     // Catch: java.lang.Throwable -> L83
            byte[] r2 = cn.com.chinatelecom.account.api.Helper.dneulret(r2)     // Catch: java.lang.Throwable -> L83
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Throwable -> L83
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L83
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
            r2.<init>(r3)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
            r3 = 0
            int r5 = r2.length()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
        L2b:
            if (r3 >= r5) goto L3f
            if (r3 > r4) goto L3f
            org.json.JSONObject r6 = r2.getJSONObject(r3)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
            if (r6 == 0) goto L3c
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
            r1.add(r6)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
        L3c:
            int r3 = r3 + 1
            goto L2b
        L3f:
            java.lang.String r2 = ""
            cn.com.chinatelecom.account.a.b.a(r7, r2)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L83
            goto L49
        L45:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L83
        L49:
            r7 = -1
            if (r9 != r7) goto L72
            java.util.Iterator r7 = r8.iterator()     // Catch: java.lang.Throwable -> L83
        L50:
            boolean r8 = r7.hasNext()     // Catch: java.lang.Throwable -> L83
            if (r8 == 0) goto L77
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L83
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L83
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L83
            r9.<init>(r8)     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L83
            java.lang.String r2 = "rt"
            int r9 = r9.getInt(r2)     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L83
            if (r9 == 0) goto L50
            r1.add(r8)     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L83
            goto L50
        L6d:
            r8 = move-exception
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L83
            goto L50
        L72:
            if (r9 != 0) goto L77
            r1.addAll(r8)     // Catch: java.lang.Throwable -> L83
        L77:
            int r7 = r1.size()     // Catch: java.lang.Throwable -> L83
            if (r7 <= r4) goto L81
            r1.poll()     // Catch: java.lang.Throwable -> L83
            goto L77
        L81:
            monitor-exit(r0)
            return r1
        L83:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.c.c(android.content.Context, java.util.List, int):java.util.Queue");
    }

    public static void c(Context context) throws Throwable {
        b.a(context, "");
    }
}
