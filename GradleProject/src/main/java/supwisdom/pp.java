package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import io.dcloud.common.util.JSUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class pp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8815a;
    public String b;
    public Context c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f8816e;
    public final int f;
    public final String g;
    public final ActivityInfo h;
    public final to i;

    public pp(Context context, String str, String str2) {
        String str3;
        this.f8815a = "";
        this.b = "";
        this.c = null;
        boolean zIsEmpty = TextUtils.isEmpty(str2);
        this.i = new to(context, zIsEmpty);
        this.d = c(str, this.b);
        this.f8816e = SystemClock.elapsedRealtime();
        this.f = bq.d();
        this.h = bq.h(context);
        this.g = str2;
        if (!zIsEmpty) {
            so.b(this, "biz", "eptyp", str2 + "|" + this.d);
            if (this.h != null) {
                str3 = this.h.name + "|" + this.h.launchMode;
            } else {
                str3 = com.igexin.push.core.b.m;
            }
            so.b(this, "biz", "actInfo", str3);
            so.b(this, "biz", "sys", bq.a(this));
        }
        try {
            this.c = context.getApplicationContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f8815a = packageInfo.versionName;
            this.b = packageInfo.packageName;
        } catch (Exception e2) {
            vp.a(e2);
        }
        if (!zIsEmpty) {
            so.a(this, "biz", com.umeng.analytics.pro.bm.aL + bq.d());
            so.b(this, "biz", "PgApiInvoke", "" + SystemClock.elapsedRealtime());
            so.a(context, this, str, this.d);
        }
        if (zIsEmpty || !vo.v().n()) {
            return;
        }
        vo.v().a(this, this.c);
    }

    public static pp e() {
        return null;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.f8815a;
    }

    public Context c() {
        return this.c;
    }

    public final String d(String str) {
        try {
            String strA = a(str, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty(strA)) {
                return str + "&" + b("bizcontext=\"", JSUtil.QUOTE);
            }
            if (!strA.endsWith(JSUtil.QUOTE)) {
                strA = strA + JSUtil.QUOTE;
            }
            int iIndexOf = str.indexOf(strA);
            return str.substring(0, iIndexOf) + a(strA, "bizcontext=\"", JSUtil.QUOTE, false) + str.substring(iIndexOf + strA.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    public String a(String str) {
        return (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) ? str : b(str) ? c(str) : d(str);
    }

    public final boolean b(String str) {
        return !str.contains("\"&");
    }

    public final String c(String str) {
        try {
            String strA = a(str, "&", "bizcontext=");
            if (TextUtils.isEmpty(strA)) {
                str = str + "&" + b("bizcontext=", "");
            } else {
                int iIndexOf = str.indexOf(strA);
                str = str.substring(0, iIndexOf) + a(strA, "bizcontext=", "", true) + str.substring(iIndexOf + strA.length());
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final HashMap<UUID, pp> f8817a = new HashMap<>();
        public static final HashMap<String, pp> b = new HashMap<>();

        public static void a(pp ppVar, Intent intent) {
            if (ppVar == null || intent == null) {
                return;
            }
            UUID uuidRandomUUID = UUID.randomUUID();
            f8817a.put(uuidRandomUUID, ppVar);
            intent.putExtra("i_uuid_b_c", uuidRandomUUID);
        }

        public static pp a(Intent intent) {
            if (intent == null) {
                return null;
            }
            Serializable serializableExtra = intent.getSerializableExtra("i_uuid_b_c");
            if (serializableExtra instanceof UUID) {
                return f8817a.remove((UUID) serializableExtra);
            }
            return null;
        }

        public static void a(pp ppVar, String str) {
            if (ppVar == null || TextUtils.isEmpty(str)) {
                return;
            }
            b.put(str, ppVar);
        }

        public static pp a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return b.remove(str);
        }
    }

    public final String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + a("", "") + str2;
    }

    public final String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(str2);
        for (int i = 0; i < strArrSplit.length; i++) {
            if (!TextUtils.isEmpty(strArrSplit[i]) && strArrSplit[i].startsWith(str3)) {
                return strArrSplit[i];
            }
        }
        return null;
    }

    public static String c(String str, String str2) {
        try {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[4];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            objArr[2] = Long.valueOf(System.currentTimeMillis());
            objArr[3] = UUID.randomUUID().toString();
            return String.format("EP%s%s_%s", "1", bq.f(String.format(locale, "%s%s%d%s", objArr)), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable unused) {
            return "-";
        }
    }

    public final JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ap_link_token", this.d);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public String a(String str, String str2) {
        String str3;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", "2014052600006128");
            jSONObject.put("ty", "and_lite");
            jSONObject.put("sv", "h.a.3.8.00");
            if (!this.b.contains(Constant.Name.SETTING) || !bq.b(this.c)) {
                jSONObject.put("an", this.b);
            }
            jSONObject.put("av", this.f8815a);
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
            jSONObject.put("extInfo", d());
            if (this.h != null) {
                str3 = this.h.name + "|" + this.h.launchMode;
            } else {
                str3 = com.igexin.push.core.b.m;
            }
            jSONObject.put("act_info", str3);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            vp.a(th);
            return "";
        }
    }

    public final String a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String strSubstring = str.substring(str2.length());
        boolean z2 = false;
        String strSubstring2 = strSubstring.substring(0, strSubstring.length() - str3.length());
        if (strSubstring2.length() >= 2 && strSubstring2.startsWith(JSUtil.QUOTE) && strSubstring2.endsWith(JSUtil.QUOTE)) {
            jSONObject = new JSONObject(strSubstring2.substring(1, strSubstring2.length() - 1));
            z2 = true;
        } else {
            jSONObject = new JSONObject(strSubstring2);
        }
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", "2014052600006128");
        }
        if (!jSONObject.has("ty")) {
            jSONObject.put("ty", "and_lite");
        }
        if (!jSONObject.has("sv")) {
            jSONObject.put("sv", "h.a.3.8.00");
        }
        if (!jSONObject.has("an") && (!this.b.contains(Constant.Name.SETTING) || !bq.b(this.c))) {
            jSONObject.put("an", this.b);
        }
        if (!jSONObject.has("av")) {
            jSONObject.put("av", this.f8815a);
        }
        if (!jSONObject.has("sdk_start_time")) {
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
        }
        if (!jSONObject.has("extInfo")) {
            jSONObject.put("extInfo", d());
        }
        String string = jSONObject.toString();
        if (z2) {
            string = JSUtil.QUOTE + string + JSUtil.QUOTE;
        }
        return str2 + string + str3;
    }

    public static HashMap<String, String> a(pp ppVar) {
        HashMap<String, String> map = new HashMap<>();
        if (ppVar != null) {
            map.put(HiAnalyticsConstant.BI_KEY_SDK_VER, "15.8.00");
            map.put("app_name", ppVar.b);
            map.put("token", ppVar.d);
            map.put("call_type", ppVar.g);
            map.put("ts_api_invoke", String.valueOf(ppVar.f8816e));
        }
        return map;
    }
}
