package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.api.ConnectionResult;
import java.util.Random;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class rp {
    public static Context g;
    public static rp h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9080a;
    public String b;
    public long c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9081e;
    public boolean f = false;

    public static synchronized rp b(Context context) {
        if (h == null) {
            h = new rp();
        }
        if (g == null) {
            h.a(context);
        }
        return h;
    }

    public final void a(Context context) {
        if (context != null) {
            g = context.getApplicationContext();
        }
        if (this.f) {
            return;
        }
        this.f = true;
        d();
    }

    public String c() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            r9 = this;
            java.lang.String r0 = ""
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r2 = 0
            java.lang.String r3 = "alipay_tid_storage"
            java.lang.String r4 = "tidinfo"
            r5 = 1
            java.lang.String r3 = supwisdom.rp.a.a(r3, r4, r5)     // Catch: java.lang.Exception -> L52
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L52
            if (r4 != 0) goto L4e
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L52
            r4.<init>(r3)     // Catch: java.lang.Exception -> L52
            java.lang.String r3 = "tid"
            java.lang.String r3 = r4.optString(r3, r0)     // Catch: java.lang.Exception -> L52
            java.lang.String r5 = "client_key"
            java.lang.String r5 = r4.optString(r5, r0)     // Catch: java.lang.Exception -> L4b
            java.lang.String r6 = "timestamp"
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L48
            long r6 = r4.optLong(r6, r7)     // Catch: java.lang.Exception -> L48
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> L48
            java.lang.String r6 = "vimei"
            java.lang.String r6 = r4.optString(r6, r0)     // Catch: java.lang.Exception -> L48
            java.lang.String r7 = "vimsi"
            java.lang.String r0 = r4.optString(r7, r0)     // Catch: java.lang.Exception -> L46
            goto L5a
        L46:
            r0 = move-exception
            goto L56
        L48:
            r0 = move-exception
            r6 = r2
            goto L56
        L4b:
            r0 = move-exception
            r5 = r2
            goto L55
        L4e:
            r0 = r2
            r5 = r0
            r6 = r5
            goto L5b
        L52:
            r0 = move-exception
            r3 = r2
            r5 = r3
        L55:
            r6 = r5
        L56:
            supwisdom.vp.a(r0)
            r0 = r2
        L5a:
            r2 = r3
        L5b:
            java.lang.String r3 = "mspl"
            java.lang.String r4 = "tid_str: load"
            supwisdom.vp.a(r3, r4)
            boolean r3 = r9.a(r2, r5, r6, r0)
            if (r3 == 0) goto L6c
            r9.e()
            goto L7a
        L6c:
            r9.f9080a = r2
            r9.b = r5
            long r1 = r1.longValue()
            r9.c = r1
            r9.d = r6
            r9.f9081e = r0
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.rp.d():void");
    }

    public final void e() {
        this.f9080a = "";
        this.b = c();
        this.c = System.currentTimeMillis();
        this.d = f();
        this.f9081e = f();
        a.a("alipay_tid_storage", "tidinfo");
    }

    public final String f() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(ConnectionResult.NETWORK_ERROR) + 1000);
    }

    public final void g() {
    }

    public final void h() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.f9080a);
            jSONObject.put("client_key", this.b);
            jSONObject.put("timestamp", this.c);
            jSONObject.put("vimei", this.d);
            jSONObject.put("vimsi", this.f9081e);
            a.a("alipay_tid_storage", "tidinfo", jSONObject.toString(), true);
        } catch (Exception e2) {
            vp.a(e2);
        }
    }

    public static class a {
        public static void a(String str, String str2) {
            if (rp.g == null) {
                return;
            }
            rp.g.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }

        public static String a(String str, String str2, boolean z) {
            if (rp.g == null) {
                return null;
            }
            String string = rp.g.getSharedPreferences(str, 0).getString(str2, null);
            if (!TextUtils.isEmpty(string) && z) {
                string = bp.b(a(), string, string);
                if (TextUtils.isEmpty(string)) {
                    vp.a("mspl", "tid_str: pref failed");
                }
            }
            vp.a("mspl", "tid_str: from local");
            return string;
        }

        public static void a(String str, String str2, String str3, boolean z) {
            if (rp.g == null) {
                return;
            }
            SharedPreferences sharedPreferences = rp.g.getSharedPreferences(str, 0);
            if (z) {
                String strA = a();
                String strA2 = bp.a(strA, str3, str3);
                if (TextUtils.isEmpty(strA2)) {
                    String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, strA);
                }
                str3 = strA2;
            }
            sharedPreferences.edit().putString(str2, str3).apply();
        }

        public static String a() {
            String packageName;
            try {
                packageName = rp.g.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                vp.a(th);
                packageName = "";
            }
            return (packageName + "0000000000000000000000000000").substring(0, 24);
        }
    }

    public String a() {
        return this.f9080a;
    }

    public final boolean a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    public String b() {
        return this.b;
    }

    public void a(String str, String str2) {
        vp.a("mspl", "tid_str: save");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f9080a = str;
        this.b = str2;
        this.c = System.currentTimeMillis();
        h();
        g();
    }
}
