package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.aa;
import com.umeng.analytics.pro.f;
import com.umeng.analytics.pro.k;
import com.umeng.analytics.pro.l;
import com.umeng.analytics.pro.m;
import com.umeng.analytics.pro.n;
import com.umeng.analytics.pro.o;
import com.umeng.analytics.pro.p;
import com.umeng.analytics.pro.q;
import com.umeng.analytics.pro.r;
import com.umeng.analytics.pro.u;
import com.umeng.analytics.pro.v;
import com.umeng.analytics.pro.w;
import com.umeng.analytics.pro.x;
import com.umeng.common.ISysListener;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.commonsdk.utils.d;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InternalAgent.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements p, v {
    public static final String A = "umsp_2";
    public static final String B = "umsp_3";
    public static final String C = "umsp_4";
    public static final String D = "umsp_5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f5145a = null;
    public static final String h = "sp_uapp";
    public static final String i = "prepp_uapp";
    public static final int o = 128;
    public static final int p = 256;
    public static String q = "";
    public static String r = "";
    public static final String t = "ekv_bl_ver";
    public static final String w = "ekv_wl_ver";
    public static final String z = "umsp_1";
    public ISysListener b;
    public r c;
    public x d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m f5146e;
    public w f;
    public n g;
    public boolean j;
    public volatile JSONObject k;
    public volatile JSONObject l;
    public volatile JSONObject m;
    public boolean n;
    public com.umeng.analytics.filter.a u;
    public com.umeng.analytics.filter.b x;
    public o y;
    public static final String s = f.ar;
    public static final String v = f.as;

    /* JADX INFO: compiled from: InternalAgent.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5147a = new b();
    }

    static {
        Context appContext = UMGlobalContext.getAppContext();
        if (appContext != null) {
            f5145a = appContext.getApplicationContext();
        }
    }

    public static b a() {
        return a.f5147a;
    }

    private void i(Context context) {
        try {
            if (context == null) {
                MLog.e("unexpected null context in getNativeSuperProperties");
                return;
            }
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (this.k == null) {
                this.k = new JSONObject();
            }
            if (this.l == null) {
                this.l = new JSONObject();
            }
            String string = sharedPreferences.getString(i, null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.m = new JSONObject(string);
                } catch (JSONException unused) {
                }
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
        } catch (Throwable unused2) {
        }
    }

    public JSONObject b() {
        return this.k;
    }

    public JSONObject c() {
        return this.m;
    }

    public JSONObject d() {
        return this.l;
    }

    public void e() {
        this.l = null;
    }

    public String f() {
        if (UMUtils.isMainProgress(f5145a)) {
            return q;
        }
        MLog.e("getOnResumedActivityName can not be called in child process");
        return null;
    }

    public String g() {
        if (UMUtils.isMainProgress(f5145a)) {
            return r;
        }
        MLog.e("getOnPausedActivityName can not be called in child process");
        return null;
    }

    public void h() {
        try {
            if (f5145a != null) {
                if (!UMUtils.isMainProgress(f5145a)) {
                    MLog.e("onStartSessionInternal can not be called in child process");
                    return;
                } else {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    UMWorkDispatch.sendEvent(f5145a, 4352, CoreProtocol.getInstance(f5145a), Long.valueOf(jCurrentTimeMillis));
                    UMWorkDispatch.sendEvent(f5145a, 4103, CoreProtocol.getInstance(f5145a), Long.valueOf(jCurrentTimeMillis));
                }
            }
            if (this.b != null) {
                this.b.onAppResume();
            }
        } catch (Throwable unused) {
        }
    }

    public void j() {
        try {
            if (f5145a == null) {
                return;
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("onProfileSignOff can not be called in child process");
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ts", jCurrentTimeMillis);
            UMWorkDispatch.sendEvent(f5145a, 4102, CoreProtocol.getInstance(f5145a), jSONObject);
            UMWorkDispatch.sendEvent(f5145a, q.a.o, CoreProtocol.getInstance(f5145a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    public synchronized void k() {
        if (f5145a == null) {
            return;
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("unregisterSuperPropertyByCoreProtocol can not be called in child process");
            return;
        }
        if (this.k != null) {
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(f5145a).edit();
            editorEdit.putString(h, this.k.toString());
            editorEdit.commit();
        } else {
            this.k = new JSONObject();
        }
    }

    public synchronized JSONObject l() {
        if (f5145a == null) {
            return null;
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("getSuperPropertiesJSONObject can not be called in child process");
            return null;
        }
        if (this.k == null) {
            this.k = new JSONObject();
        }
        return this.k;
    }

    public synchronized void m() {
        try {
            if (f5145a != null) {
                if (!UMUtils.isMainProgress(f5145a)) {
                    MLog.e("clearSuperPropertiesByCoreProtocol can not be called in child process");
                } else {
                    SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(f5145a).edit();
                    editorEdit.remove(h);
                    editorEdit.commit();
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.umeng.analytics.pro.p
    public void n() {
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onIntoBackground triggered.");
        if (AnalyticsConfig.enable && FieldManager.b()) {
            if (!FieldManager.allow(d.D)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> 退出发送策略: 云控控制字关闭。功能不生效");
            } else {
                if (UMWorkDispatch.eventHasExist(q.a.B)) {
                    return;
                }
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> 退出时发送策略 被触发！");
                Context context = f5145a;
                UMWorkDispatch.sendEvent(context, q.a.B, CoreProtocol.getInstance(context), null);
            }
        }
    }

    public b() {
        this.c = new r();
        this.d = new x();
        this.f5146e = new m();
        this.f = w.a();
        this.g = null;
        this.j = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.u = null;
        this.x = null;
        this.y = null;
        this.c.a(this);
    }

    private boolean e(String str) {
        if (this.u.enabled() && this.u.matchHit(str)) {
            return true;
        }
        if (!this.x.enabled()) {
            return false;
        }
        if (!this.x.matchHit(str)) {
            return true;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> white list match! id = " + str);
        return false;
    }

    public void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (this.u == null) {
                com.umeng.analytics.filter.a aVar = new com.umeng.analytics.filter.a(s, "ekv_bl_ver");
                this.u = aVar;
                aVar.register(f5145a);
            }
            if (this.x == null) {
                com.umeng.analytics.filter.b bVar = new com.umeng.analytics.filter.b(v, "ekv_wl_ver");
                this.x = bVar;
                bVar.register(f5145a);
            }
            if (UMUtils.isMainProgress(f5145a)) {
                if (!this.j) {
                    this.j = true;
                    i(f5145a);
                }
                if (Build.VERSION.SDK_INT > 13) {
                    synchronized (this) {
                        if (!this.n) {
                            n nVarA = n.a(context);
                            this.g = nVarA;
                            if (nVarA.a()) {
                                this.n = true;
                            }
                            this.y = o.a();
                            try {
                                o.a(context);
                                this.y.a(this);
                            } catch (Throwable unused) {
                            }
                        }
                    }
                } else {
                    this.n = true;
                }
                if (UMConfigure.isDebugLog()) {
                    UMLog.mutlInfo(l.B, 3, "", null, null);
                }
                UMWorkDispatch.registerConnStateObserver(CoreProtocol.getInstance(f5145a));
            }
        } catch (Throwable unused2) {
        }
    }

    public void b(String str) {
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("onPageEnd can not be called in child process");
            return;
        }
        try {
            if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.d.b(str);
            }
        } catch (Throwable unused) {
        }
    }

    public void c(Context context) {
        if (context == null) {
            UMLog.aq(l.p, 0, "\\|");
            return;
        }
        if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("onPause can not be called in child process");
            return;
        }
        if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
            UMLog.aq(l.q, 2, "\\|");
        }
        try {
            if (!this.j || !this.n) {
                a(context);
            }
            if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_MANUAL) {
                this.f5146e.b(context.getClass().getName());
            }
            i();
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e("Exception occurred in Mobclick.onRause(). ", th);
            }
        }
        if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
            r = context.getClass().getName();
        }
    }

    public void d(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("onKillProcess can not be called in child process");
                return;
            }
            if (this.g != null) {
                this.g.c();
            }
            n.a(context, "onKillProcess");
            if (this.f5146e != null) {
                this.f5146e.b();
            }
            if (this.d != null) {
                this.d.b();
            }
            if (f5145a != null) {
                if (this.f != null) {
                    this.f.c(f5145a, Long.valueOf(System.currentTimeMillis()));
                }
                q.a(f5145a).d();
                x.a(f5145a);
                if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                    n.c(f5145a);
                }
                PreferenceWrapper.getDefault(f5145a).edit().commit();
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void f(Context context) {
        if (context == null) {
            UMLog.aq(l.ah, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("clearSuperProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        this.k = new JSONObject();
        UMWorkDispatch.sendEvent(f5145a, q.a.u, CoreProtocol.getInstance(f5145a), null);
    }

    public synchronized void g(Context context) {
        if (context == null) {
            UMLog.aq(l.ap, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("clearPreProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        if (this.m.length() > 0) {
            UMWorkDispatch.sendEvent(f5145a, q.a.y, CoreProtocol.getInstance(f5145a), null);
        }
        this.m = new JSONObject();
    }

    public void b(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onResume");
            return;
        }
        if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("onResume can not be called in child process");
            return;
        }
        if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
            UMLog.aq(l.o, 2, "\\|");
        }
        try {
            if (!this.j || !this.n) {
                a(context);
            }
            if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_MANUAL) {
                this.f5146e.a(context.getClass().getName());
            }
            h();
            if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                q = context.getClass().getName();
            }
        } catch (Throwable th) {
            MLog.e("Exception occurred in Mobclick.onResume(). ", th);
        }
    }

    public synchronized Object e(Context context, String str) {
        if (context == null) {
            UMLog.aq(l.ai, 0, "\\|");
            return null;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("getSuperProperty can not be called in child process");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            UMLog.aq(l.ag, 0, "\\|");
            return null;
        }
        if (!str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C) && !str.equals(D)) {
            MLog.e("please check key or value, must be correct!");
            return null;
        }
        if (this.k != null) {
            if (this.k.has(str)) {
                return this.k.opt(str);
            }
        } else {
            this.k = new JSONObject();
        }
        return null;
    }

    public synchronized JSONObject h(Context context) {
        if (context == null) {
            UMLog.aq(l.aq, 0, "\\|");
            return null;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("getPreProperties can not be called in child process");
            return null;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        if (this.m.length() > 0) {
            try {
                jSONObject = new JSONObject(this.m.toString());
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public void i() {
        try {
            if (f5145a != null) {
                if (!UMUtils.isMainProgress(f5145a)) {
                    MLog.e("onEndSessionInternal can not be called in child process");
                    return;
                }
                UMWorkDispatch.sendEvent(f5145a, q.a.h, CoreProtocol.getInstance(f5145a), Long.valueOf(System.currentTimeMillis()));
                UMWorkDispatch.sendEvent(f5145a, 4100, CoreProtocol.getInstance(f5145a), null);
                UMWorkDispatch.sendEvent(f5145a, 4099, CoreProtocol.getInstance(f5145a), null);
                UMWorkDispatch.sendEvent(f5145a, 4105, CoreProtocol.getInstance(f5145a), null);
            }
        } catch (Throwable unused) {
        }
        ISysListener iSysListener = this.b;
        if (iSysListener != null) {
            iSysListener.onAppPause();
        }
    }

    public synchronized void f(Context context, String str) {
        if (context == null) {
            UMLog.aq(l.an, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("unregisterPreProperty can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        if (str != null && str.length() > 0) {
            if (this.m.has(str)) {
                this.m.remove(str);
                UMWorkDispatch.sendEvent(f5145a, 8200, CoreProtocol.getInstance(f5145a), this.m.toString());
            } else if (UMConfigure.isDebugLog()) {
                UMLog.aq(l.ao, 0, "\\|");
            }
            return;
        }
        MLog.e("please check propertics, property is null!");
    }

    private boolean g(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length >= 0 && length < 256) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        MLog.e("The length of profile value must be less than 256 bytes.");
        return false;
    }

    public void c(Context context, String str) {
        if (context == null) {
            UMLog.aq(l.z, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("setSecret can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        AnalyticsConfig.a(f5145a, str);
    }

    public synchronized void d(Context context, String str) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (context == null) {
            UMLog.aq(l.ah, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("unregisterSuperProperty can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        if (TextUtils.isEmpty(str)) {
            UMLog.aq(l.ag, 0, "\\|");
            return;
        }
        if (!str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C) && !str.equals(D)) {
            MLog.e("please check key or value, must be correct!");
            return;
        }
        if (this.k == null) {
            this.k = new JSONObject();
        }
        if (this.k.has(str)) {
            this.k.remove(str);
            UMWorkDispatch.sendEvent(f5145a, q.a.v, CoreProtocol.getInstance(f5145a), str);
        }
    }

    public void b(Context context, String str) {
        try {
            if (context == null) {
                UMLog.aq(l.N, 0, "\\|");
                return;
            }
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("onDeepLinkReceived can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(f5145a);
            }
            if (!TextUtils.isEmpty(str)) {
                HashMap map = new HashMap();
                map.put(f.aK, str);
                a(f5145a, f.aJ, (Map<String, Object>) map, -1L, false);
                return;
            }
            UMLog.aq(l.O, 0, "\\|");
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public void a(String str) {
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("onPageStart can not be called in child process");
            return;
        }
        try {
            if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.d.a(str);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean c(String str, Object obj) {
        int length;
        if (TextUtils.isEmpty(str)) {
            MLog.e("key is " + str + ", please check key, illegal");
            return false;
        }
        try {
            length = str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            length = 0;
        }
        if (length > 128) {
            MLog.e("key length is " + length + ", please check key, illegal");
            return false;
        }
        if (obj instanceof String) {
            if (((String) obj).getBytes("UTF-8").length <= 256) {
                return true;
            }
            MLog.e("value length is " + ((String) obj).getBytes("UTF-8").length + ", please check value, illegal");
            return false;
        }
        if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float)) {
            return true;
        }
        MLog.e("value is " + obj + ", please check value, type illegal");
        return false;
    }

    public synchronized String e(Context context) {
        if (context == null) {
            UMLog.aq(l.ai, 0, "\\|");
            return null;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("getSuperProperties can not be called in child process");
            return null;
        }
        if (this.k != null) {
            return this.k.toString();
        }
        this.k = new JSONObject();
        return null;
    }

    public void a(ISysListener iSysListener) {
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("setSysListener can not be called in child process");
        } else {
            this.b = iSysListener;
        }
    }

    public void a(Context context, int i2) {
        if (context == null) {
            MLog.e("unexpected null context in setVerticalType");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("setVerticalType can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        AnalyticsConfig.a(f5145a, i2);
    }

    private void b(String str, Object obj) {
        try {
            if (this.k == null) {
                this.k = new JSONObject();
            }
            int i2 = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    if (strArr.length > 10) {
                        return;
                    }
                    JSONArray jSONArray = new JSONArray();
                    while (i2 < strArr.length) {
                        if (strArr[i2] != null && !HelperUtils.checkStrLen(strArr[i2], 256)) {
                            jSONArray.put(strArr[i2]);
                        }
                        i2++;
                    }
                    this.k.put(str, jSONArray);
                    return;
                }
                if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    JSONArray jSONArray2 = new JSONArray();
                    while (i2 < jArr.length) {
                        jSONArray2.put(jArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray2);
                    return;
                }
                if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    JSONArray jSONArray3 = new JSONArray();
                    while (i2 < iArr.length) {
                        jSONArray3.put(iArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray3);
                    return;
                }
                if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    JSONArray jSONArray4 = new JSONArray();
                    while (i2 < fArr.length) {
                        jSONArray4.put(fArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray4);
                    return;
                }
                if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    JSONArray jSONArray5 = new JSONArray();
                    while (i2 < dArr.length) {
                        jSONArray5.put(dArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray5);
                    return;
                }
                if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    JSONArray jSONArray6 = new JSONArray();
                    while (i2 < sArr.length) {
                        jSONArray6.put((int) sArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray6);
                    return;
                }
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray7 = new JSONArray();
                while (i2 < list.size()) {
                    Object obj2 = list.get(i2);
                    if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                        jSONArray7.put(list.get(i2));
                    }
                    i2++;
                }
                this.k.put(str, jSONArray7);
                return;
            }
            if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                this.k.put(str, obj);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean f(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length < 128) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        MLog.e("The length of profile key must be less than 128 bytes.");
        return false;
    }

    public void c(String str) {
        if (g(str)) {
            a(f.O, (Object) str);
        }
    }

    public void a(Context context, String str, HashMap<String, Object> map) {
        if (context == null) {
            return;
        }
        try {
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("onGKVEvent can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(f5145a);
            }
            String string = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                string = this.k.toString();
            }
            u.a(f5145a).a(str, map, string);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public void d(String str) {
        if (g(str)) {
            a(f.P, (Object) str);
        }
    }

    public void a(Context context, String str) {
        if (context == null) {
            UMLog.aq(l.w, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("reportError can not be called in child process");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (UMConfigure.isDebugLog()) {
                UMLog.aq(l.x, 0, "\\|");
                return;
            }
            return;
        }
        try {
            if (!this.j || !this.n) {
                a(f5145a);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put(f.W, 2);
            jSONObject.put(f.X, str);
            jSONObject.put("__ii", this.f.c());
            UMWorkDispatch.sendEvent(f5145a, 4106, CoreProtocol.getInstance(f5145a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public void a(Context context, Throwable th) {
        if (context != null && th != null) {
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("reportError can not be called in child process");
                return;
            }
            try {
                if (!this.j || !this.n) {
                    a(f5145a);
                }
                a(f5145a, DataHelper.convertExceptionToString(th));
                return;
            } catch (Exception e2) {
                if (MLog.DEBUG) {
                    MLog.e(e2);
                    return;
                }
                return;
            }
        }
        UMLog.aq(l.y, 0, "\\|");
    }

    public void a(Context context, String str, String str2, long j, int i2) {
        if (context == null) {
            return;
        }
        try {
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (!this.j || !this.n) {
                a(f5145a);
            }
            if (e(str)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            String string = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                string = this.k.toString();
            }
            u.a(f5145a).a(str, str2, j, i2, string);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public synchronized void b(Object obj) {
        if (f5145a == null) {
            return;
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("updateNativePrePropertiesByCoreProtocol can not be called in child process");
            return;
        }
        SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(f5145a).edit();
        if (obj != null) {
            String str = (String) obj;
            if (editorEdit != null && !TextUtils.isEmpty(str)) {
                editorEdit.putString(i, str).commit();
            }
        } else if (editorEdit != null) {
            editorEdit.remove(i).commit();
        }
    }

    public void a(Context context, String str, Map<String, Object> map, long j) {
        try {
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            if (Arrays.asList(f.aL).contains(str)) {
                UMLog.aq(l.b, 0, "\\|");
                return;
            }
            if (map.isEmpty()) {
                UMLog.aq(l.d, 0, "\\|");
                return;
            }
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                if (Arrays.asList(f.aL).contains(it.next().getKey())) {
                    UMLog.aq(l.f5294e, 0, "\\|");
                    return;
                }
            }
            a(context, str, map, j, false);
            return;
        }
        UMLog.aq(l.c, 0, "\\|");
    }

    public void a(Context context, String str, Map<String, Object> map) {
        a(context, str, map, -1L, true);
    }

    private void a(Context context, String str, Map<String, Object> map, long j, boolean z2) {
        try {
            if (context == null) {
                MLog.e("context is null in onEventNoCheck, please check!");
                return;
            }
            if (f5145a == null) {
                f5145a = context.getApplicationContext();
            }
            if (!this.j || !this.n) {
                a(f5145a);
            }
            if (e(str)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            String string = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                string = this.k.toString();
            }
            u.a(f5145a).a(str, map, j, string, z2);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    @Override // com.umeng.analytics.pro.v
    public void a(Throwable th) {
        try {
            if (f5145a == null) {
                return;
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("onAppCrash can not be called in child process");
                return;
            }
            if (AnalyticsConfig.enable) {
                if (this.d != null) {
                    this.d.b();
                }
                n.a(f5145a, "onAppCrash");
                if (this.f5146e != null) {
                    this.f5146e.b();
                }
                if (this.g != null) {
                    this.g.c();
                }
                if (this.f != null) {
                    this.f.c(f5145a, Long.valueOf(System.currentTimeMillis()));
                }
                if (th != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(f.W, 1);
                    jSONObject.put(f.X, DataHelper.convertExceptionToString(th));
                    k.a(f5145a).a(this.f.c(), jSONObject.toString(), 1);
                }
                q.a(f5145a).d();
                x.a(f5145a);
                if (UMConfigure.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                    n.c(f5145a);
                }
                PreferenceWrapper.getDefault(f5145a).edit().commit();
            }
        } catch (Exception e2) {
            if (MLog.DEBUG) {
                MLog.e("Exception in onAppCrash", e2);
            }
        }
    }

    public void a(String str, String str2) {
        try {
            if (f5145a == null) {
                return;
            }
            if (!UMUtils.isMainProgress(f5145a)) {
                MLog.e("onProfileSignIn can not be called in child process");
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f.M, str);
            jSONObject.put("uid", str2);
            jSONObject.put("ts", jCurrentTimeMillis);
            UMWorkDispatch.sendEvent(f5145a, 4101, CoreProtocol.getInstance(f5145a), jSONObject);
            UMWorkDispatch.sendEvent(f5145a, q.a.o, CoreProtocol.getInstance(f5145a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    public void a(boolean z2) {
        Context context = f5145a;
        if (context == null) {
            return;
        }
        if (!UMUtils.isMainProgress(context)) {
            MLog.e("setCatchUncaughtExceptions can not be called in child process");
        } else {
            if (AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW) {
                return;
            }
            AnalyticsConfig.CATCH_EXCEPTION = z2;
        }
    }

    public void a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    public void a(double d, double d2) {
        Context context = f5145a;
        if (context == null) {
            return;
        }
        if (!UMUtils.isMainProgress(context)) {
            MLog.e("setLocation can not be called in child process");
            return;
        }
        if (AnalyticsConfig.f5135a == null) {
            AnalyticsConfig.f5135a = new double[2];
        }
        double[] dArr = AnalyticsConfig.f5135a;
        dArr[0] = d;
        dArr[1] = d2;
    }

    public void a(Context context, MobclickAgent.EScenarioType eScenarioType) {
        if (context == null) {
            MLog.e("unexpected null context in setScenarioType");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("setScenarioType can not be called in child process");
            return;
        }
        if (eScenarioType != null) {
            a(f5145a, eScenarioType.toValue());
        }
        if (this.j && this.n) {
            return;
        }
        a(f5145a);
    }

    public void a(long j) {
        Context context = f5145a;
        if (context == null) {
            return;
        }
        if (!UMUtils.isMainProgress(context)) {
            MLog.e("setSessionContinueMillis can not be called in child process");
        } else {
            AnalyticsConfig.kContinueSessionMillis = j;
            aa.a().a(AnalyticsConfig.kContinueSessionMillis);
        }
    }

    public synchronized void a(Context context, String str, Object obj) {
        int i2 = 0;
        if (context == null) {
            UMLog.aq(l.af, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("registerSuperProperty can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        if (!TextUtils.isEmpty(str) && obj != null) {
            if (!str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C) && !str.equals(D)) {
                MLog.e("property name is " + str + ", please check key, must be correct!");
                return;
            }
            if ((obj instanceof String) && !HelperUtils.checkStrLen(obj.toString(), 256)) {
                MLog.e("property value is " + obj + ", please check value, lawless!");
                return;
            }
            try {
                if (this.k == null) {
                    this.k = new JSONObject();
                }
                if (obj.getClass().isArray()) {
                    if (obj instanceof String[]) {
                        String[] strArr = (String[]) obj;
                        if (strArr.length > 10) {
                            MLog.e("please check value, size is " + strArr.length + ", overstep 10!");
                            return;
                        }
                        JSONArray jSONArray = new JSONArray();
                        while (i2 < strArr.length) {
                            if (strArr[i2] != null && HelperUtils.checkStrLen(strArr[i2], 256)) {
                                jSONArray.put(strArr[i2]);
                                i2++;
                            }
                            MLog.e("please check value, length is " + strArr[i2].length() + ", overlength 256!");
                            return;
                        }
                        this.k.put(str, jSONArray);
                    } else if (obj instanceof long[]) {
                        long[] jArr = (long[]) obj;
                        if (jArr.length > 10) {
                            MLog.e("please check value, size is " + jArr.length + ", overstep 10!");
                            return;
                        }
                        JSONArray jSONArray2 = new JSONArray();
                        while (i2 < jArr.length) {
                            jSONArray2.put(jArr[i2]);
                            i2++;
                        }
                        this.k.put(str, jSONArray2);
                    } else if (obj instanceof int[]) {
                        int[] iArr = (int[]) obj;
                        if (iArr.length > 10) {
                            MLog.e("please check value, size is " + iArr.length + ", overstep 10!");
                            return;
                        }
                        JSONArray jSONArray3 = new JSONArray();
                        while (i2 < iArr.length) {
                            jSONArray3.put(iArr[i2]);
                            i2++;
                        }
                        this.k.put(str, jSONArray3);
                    } else if (obj instanceof float[]) {
                        float[] fArr = (float[]) obj;
                        if (fArr.length > 10) {
                            MLog.e("please check value, size is " + fArr.length + ", overstep 10!");
                            return;
                        }
                        JSONArray jSONArray4 = new JSONArray();
                        while (i2 < fArr.length) {
                            jSONArray4.put(fArr[i2]);
                            i2++;
                        }
                        this.k.put(str, jSONArray4);
                    } else if (obj instanceof double[]) {
                        double[] dArr = (double[]) obj;
                        if (dArr.length > 10) {
                            MLog.e("please check value, size is " + dArr.length + ", overstep 10!");
                            return;
                        }
                        JSONArray jSONArray5 = new JSONArray();
                        while (i2 < dArr.length) {
                            jSONArray5.put(dArr[i2]);
                            i2++;
                        }
                        this.k.put(str, jSONArray5);
                    } else if (obj instanceof short[]) {
                        short[] sArr = (short[]) obj;
                        if (sArr.length > 10) {
                            MLog.e("please check value, size is " + sArr.length + ", overstep 10!");
                            return;
                        }
                        JSONArray jSONArray6 = new JSONArray();
                        while (i2 < sArr.length) {
                            jSONArray6.put((int) sArr[i2]);
                            i2++;
                        }
                        this.k.put(str, jSONArray6);
                    } else {
                        MLog.e("please check value, illegal type!");
                        return;
                    }
                } else {
                    if (!(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof Short)) {
                        MLog.e("please check value, illegal type!");
                        return;
                    }
                    this.k.put(str, obj);
                }
            } catch (Throwable unused) {
            }
            UMWorkDispatch.sendEvent(f5145a, q.a.t, CoreProtocol.getInstance(f5145a), this.k.toString());
            return;
        }
        UMLog.aq(l.ag, 0, "\\|");
    }

    public synchronized void a(Object obj) {
        if (f5145a == null) {
            return;
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("registerSuperPropertyByCoreProtocol can not be called in child process");
            return;
        }
        if (obj != null) {
            String str = (String) obj;
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(f5145a).edit();
            if (editorEdit != null && !TextUtils.isEmpty(str)) {
                editorEdit.putString(h, this.k.toString()).commit();
            }
        }
    }

    public synchronized void a(Context context, List<String> list) {
        try {
        } catch (Throwable th) {
            MLog.e(th);
        }
        if (context == null) {
            UMLog.aq(l.aj, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("setFirstLaunchEvent can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        u.a(f5145a).a(list);
    }

    public synchronized void a(Context context, JSONObject jSONObject) {
        if (context == null) {
            UMLog.aq(l.al, 0, "\\|");
            return;
        }
        if (f5145a == null) {
            f5145a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(f5145a)) {
            MLog.e("registerPreProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(f5145a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        if (jSONObject != null && jSONObject.length() > 0) {
            JSONObject jSONObject2 = null;
            try {
                jSONObject2 = new JSONObject(this.m.toString());
            } catch (Exception unused) {
            }
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            Iterator<String> itKeys = jSONObject.keys();
            if (itKeys != null) {
                while (itKeys.hasNext()) {
                    try {
                        String string = itKeys.next().toString();
                        Object obj = jSONObject.get(string);
                        if (c(string, obj)) {
                            jSONObject2.put(string, obj);
                            if (jSONObject2.length() > 10) {
                                MLog.e("please check propertics, size overlength!");
                                return;
                            }
                            continue;
                        } else {
                            return;
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
            this.m = jSONObject2;
            if (this.m.length() > 0) {
                UMWorkDispatch.sendEvent(f5145a, q.a.w, CoreProtocol.getInstance(f5145a), this.m.toString());
            }
            return;
        }
        UMLog.aq(l.am, 0, "\\|");
    }

    public void a(String str, Object obj) {
        if (f(str)) {
            if (!(obj instanceof String) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short) && !(obj instanceof Float) && !(obj instanceof Double)) {
                MLog.e("userProfile: Invalid value type, please check!");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pk", str);
                if (obj instanceof String) {
                    String strTrim = (String) obj;
                    if (strTrim.length() > 0) {
                        strTrim = strTrim.trim();
                    }
                    if (!g(strTrim)) {
                        return;
                    } else {
                        jSONObject.put("pv", strTrim);
                    }
                } else {
                    jSONObject.put("pv", obj);
                }
                UMWorkDispatch.sendEvent(f5145a, q.a.q, CoreProtocol.getInstance(f5145a), jSONObject);
            } catch (Throwable unused) {
            }
        }
    }
}
